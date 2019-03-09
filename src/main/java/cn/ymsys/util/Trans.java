package cn.ymsys.util;

import java.io.File;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.jfinal.plugin.activerecord.Record;

import cn.ymsys.api.service.TranService;

public class Trans implements Runnable {
	private TranService tranService = new TranService();
	private Shell shell = new Shell();
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
		List<Record> list = tranService.getTrans();
		for (Record re : list) {
			String fileName = re.get("file_name");
			shell.setFileName(fileName);
			shell.ts();
			if (!shell.ps()) {
				shell.m3u8();
				if (fileHave(Const.ROOT + fileName)) {
					tranService.del(re.getInt("id"));
					tranService.updateFile(re.getInt("fid"));
				}
			}
		}
	}

	public boolean fileHave(String path) {
		File file = new File(path);
		return file.exists();
	}

}