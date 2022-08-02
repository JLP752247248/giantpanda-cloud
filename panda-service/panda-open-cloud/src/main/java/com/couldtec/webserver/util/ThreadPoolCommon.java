package com.couldtec.webserver.util;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolCommon extends ThreadPoolExecutor {

	private static int threadPoolSizeDefault = 100;
	private static ThreadPoolCommon threadPoolCommon;
	
	public ThreadPoolCommon(int poolSize) {
		super(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	}

	/**
	 * ����Ĭ�ϴ�С��ThreadPoolExecutor�̳߳�
	 * @deprecated
	 * @author gongsj
	 * @date 2010-9-25
	 * @return
	 */
	public static ThreadPoolCommon getFixedThreadPool() {
		return getFixedThreadPool(threadPoolSizeDefault);
	}
	
	/**
	 * ����ָ����С��ThreadPoolExecutor�̳߳�
	 * @deprecated
	 * @author gongsj
	 * @date 2010-9-25
	 * @param threadPoolCapacity
	 * @return
	 */
	public static ThreadPoolCommon getFixedThreadPool(int threadPoolSize) {
		threadPoolCommon = new ThreadPoolCommon(threadPoolSize);
		return threadPoolCommon;
	}
	
	/**
	 * ���ؼ����Ϣ
	 * @deprecated
	 * @author gongsj
	 * @date 2010-9-25
	 * @return
	 */
	public String getThreadPoolMonitorInfo() {
		if (null == threadPoolCommon) {
			return null;
		}
		int activeCount = threadPoolCommon.getActiveCount();
		long completedTaskCount = threadPoolCommon.getCompletedTaskCount();
		long taskCount = threadPoolCommon.getTaskCount();
		long maximumPoolSize = threadPoolCommon.getMaximumPoolSize();
		long queueCount = taskCount - completedTaskCount - activeCount;
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("POOL-INFO:")
			  .append("Active=").append(activeCount).append(";")
			  .append("Queue=").append(queueCount).append(";")
			  .append("Completed=").append(completedTaskCount).append(";")
			  .append("AllTask=").append(taskCount).append(";")
			  .append("PoolSize=").append(maximumPoolSize).append("");
		
		return buffer.toString();
	}
	/**
	 * 
	 */
	public static String getThreadPoolMonitorInfo(ThreadPoolCommon threadPoolCommon) {
		if (null == threadPoolCommon) {
			return null;
		}
		int activeCount = threadPoolCommon.getActiveCount();
		long completedTaskCount = threadPoolCommon.getCompletedTaskCount();
		long taskCount = threadPoolCommon.getTaskCount();
		long maximumPoolSize = threadPoolCommon.getMaximumPoolSize();
		long queueCount = taskCount - completedTaskCount - activeCount;
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("POOL-INFO:")
			  .append("Active=").append(activeCount).append(";")
			  .append("Queue=").append(queueCount).append(";")
			  .append("Completed=").append(completedTaskCount).append(";")
			  .append("AllTask=").append(taskCount).append(";")
			  .append("PoolSize=").append(maximumPoolSize).append("");
		
		return buffer.toString();
	}
}


















