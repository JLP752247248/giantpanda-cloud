package com.couldtec.webserver.apps.restful.controller;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadFileThread implements Runnable{
	String url="";
	HttpServletRequest req;
	HttpServletResponse response;
	ReadFileThread(String url,HttpServletRequest req,HttpServletResponse resp){
		this.url=url;
		this.req=req;
		this.response=resp;
	}
	@Override
	public void run() {

				File file = new File(url);
			      // 后缀名
			      String suffixName = url.substring(url.lastIndexOf("."));
			      //判断文件是否存在如果不存在就返回默认图片或者进行异常处理
			      if (!(file.exists() && file.canRead())) {
			          System.out.println("文件不存在");
			      }
			      FileInputStream inputStream = null;
			      OutputStream stream =null;
			      BufferedInputStream bis=null;
			      OutputStream out = null;
			      try {
			    	  if (file.exists()) {
							long p = 0L;
							long toLength = 0L;
							long contentLength = 0L;
							int rangeSwitch = 0; // 0,从头开始的全文下载；1,从某字节开始的下载（bytes=27000-）；2,从某字节开始到某字节结束的下载（bytes=27000-39000）
							long fileLength;
							String rangBytes = "";
							fileLength = file.length();

							// get file content
							InputStream ins = new FileInputStream(file);
							bis = new BufferedInputStream(ins);

							// tell the client to allow accept-ranges
							response.reset();
							response.setHeader("Accept-Ranges", "bytes");
							//response.setContentType("vedio/mp4");
							// client requests a file block download start byte
							String range = req.getHeader("Range");
							if (range != null && range.trim().length() > 0 && !"null".equals(range)) {
								response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
								rangBytes = range.replaceAll("bytes=", "");
								if (rangBytes.endsWith("-")) { // bytes=270000-
									rangeSwitch = 1;
									p = Long.parseLong(rangBytes.substring(0, rangBytes.indexOf("-")));
									contentLength = fileLength - p; // 客户端请求的是270000之后的字节（包括bytes下标索引为270000的字节）
								} else { // bytes=270000-320000
									rangeSwitch = 2;
									String temp1 = rangBytes.substring(0, rangBytes.indexOf("-"));
									String temp2 = rangBytes.substring(rangBytes.indexOf("-") + 1, rangBytes.length());
									p = Long.parseLong(temp1);
									toLength = Long.parseLong(temp2);
									contentLength = toLength - p + 1; // 客户端请求的是 270000-320000 之间的字节
								}
							} else {
								contentLength = fileLength;
							}

							// 如果设设置了Content-Length，则客户端会自动进行多线程下载。如果不希望支持多线程，则不要设置这个参数。
							// Content-Length: [文件的总大小] - [客户端请求的下载的文件块的开始字节]
							response.setHeader("Content-Length", new Long(contentLength).toString());

							// 断点开始
							// 响应的格式是:
							// Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
							if (rangeSwitch == 1) {
								String contentRange = new StringBuffer("bytes ").append(new Long(p).toString()).append("-")
										.append(new Long(fileLength - 1).toString()).append("/")
										.append(new Long(fileLength).toString()).toString();
								response.setHeader("Content-Range", contentRange);
								bis.skip(p);
							} else if (rangeSwitch == 2) {
								String contentRange = range.replace("=", " ") + "/" + new Long(fileLength).toString();
								response.setHeader("Content-Range", contentRange);
								bis.skip(p);
							} else {
								String contentRange = new StringBuffer("bytes ").append("0-").append(fileLength - 1).append("/")
										.append(fileLength).toString();
								response.setHeader("Content-Range", contentRange);
							}

							String fileName = file.getName();
							//response.setContentType("application/octet-stream");
							//response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

							out = response.getOutputStream();
							int n = 0;
							long readLength = 0;
							int bsize = 1024;
							byte[] bytes = new byte[bsize];
							if (rangeSwitch == 2) {
								// 针对 bytes=27000-39000 的请求，从27000开始写数据
								while (readLength <= contentLength - bsize) {
									n = bis.read(bytes);
									readLength += n;
									out.write(bytes, 0, n);
								}
								if (readLength <= contentLength) {
									n = bis.read(bytes, 0, (int) (contentLength - readLength));
									out.write(bytes, 0, n);
								}
							} else {
								int k=0;
								while ((n = bis.read(bytes)) != -1) {
									//System.out.println(k++);
									out.write(bytes, 0, n);
								}
							}
							out.flush();
						}
			      } catch (FileNotFoundException e) {
			          e.printStackTrace();
			      } catch (IOException e) {
			          e.printStackTrace();
			      }finally{
			    	  IOUtils.closeQuietly(out);
					  IOUtils.closeQuietly(bis);
					  IOUtils.closeQuietly(inputStream);
					  IOUtils.closeQuietly(stream);
			      }
				
			
	    	  
	      
		
	}

}
