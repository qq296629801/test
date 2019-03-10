package cn.ymsys.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Trans implements Runnable {
	private ScheduledExecutorService service;
	private long initialDelay = 1;// 开始时间
	private long period = 1;// 间隔时间

	public Trans() {

	}

	public void start() {
		service = Executors.newScheduledThreadPool(10);
		service.scheduleAtFixedRate(new Trans(), initialDelay, period, TimeUnit.SECONDS);
	}

	/***
	 * 马上停止`
	 */
	public void stop() {
		if (this.service != null) {
			this.service.shutdownNow();
		}
	}

	/***
	 * 执行完后面的任务再停止
	 */
	public void shutdown() {
		if (this.service != null) {
			this.service.shutdown();
		}
	}

	@Override
	public void run() {
	}

}