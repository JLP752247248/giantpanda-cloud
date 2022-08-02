package com.couldtec.webserver.apps.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.couldtec.webserver.util.RestClient;



@Component
public class Tasks {
	public static final Logger logger = LoggerFactory.getLogger(Tasks.class);
	ThreadPoolExecutor es=(ThreadPoolExecutor) Executors.newFixedThreadPool(40);
	File aimFilePath=new File("D:/vediosdown/tmp");
	File aimFilePathLast=new File("D:/vediosdown");
	int maxQueueSize=500;
	@Resource
	private JdbcTemplate dao;
	//@Scheduled(cron="*/5 * *  * * ?")
	void downloadTask(){
		aimFilePath.mkdir();
		System.out.println("downloadTask start");
		int waitSize=es.getQueue().size();
		if(waitSize>=maxQueueSize) {
			return;
		}
		List<Map<String, Object>> mapL=dao.queryForList("select * from vedioinfo_copy2 where state=0 limit "+(maxQueueSize-waitSize));
		List<Future<?>> fut=new ArrayList<>();
		for(Map<String,Object> mp:mapL) {
			System.out.println("downloadTaskmp:"+mp);
			String name=mp.get("name").toString();
			String url=mp.get("url").toString();
			File aimFile=new File(aimFilePath,name+".mp4");
			if(aimFile.exists()) {
				System.out.println("downloadTaskmp:file exists,delete it ");
				aimFile.delete();
			}
			Runnable t=new DownThread(url,aimFile.getPath());
			Future<?> ft=es.submit(t);
			fut.add(ft);
		}
		
		System.out.println("downloadTask end");
	}
	//@Scheduled(cron="*/20 * *  * * ?")
	void printQueue(){
		Queue<Runnable> que=es.getQueue();
		int activeCount=es.getActiveCount();
		long taskCount=es.getTaskCount();
		long finishedCount=es.getCompletedTaskCount();
		logger.info("quesize[{}],activeCount[{}],taskCount[{}],finishedCount[{}]",que.size(),activeCount,taskCount,finishedCount);
	}
	
	
	class DownThread implements Runnable{
		String url;
		String aimFile;
		DownThread(String url,String aimFile){
			this.url=url;
			this.aimFile=aimFile;
		}
		@Override
		public void run() {
			logger.info("[{}],[{}]downloading...",aimFile,url);
			dao.update("update vedioinfo_copy2 set state= 2 where url='"+url+"'");
			int res=RestClient.downloadFile(url, aimFile);
			if(res<0) {
				logger.info("[{}],[{}]download failed!!!",aimFile,url);
				dao.update("update vedioinfo_copy2 set state= -1 where url='"+url+"'");
				try {
					FileUtils.forceDelete(new File(aimFile));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				dao.update("update vedioinfo_copy2 set state= 1 where url='"+url+"'");
				logger.info("[{}],[{}]downloaded success!!!",aimFile,url);
				try {
					FileUtils.moveFileToDirectory(new File(aimFile), aimFilePathLast, true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
}
