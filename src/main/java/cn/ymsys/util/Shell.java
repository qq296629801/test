package cn.ymsys.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Shell {
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean ps() {
		boolean flag = false;
		StringBuilder str = new StringBuilder();
		try {
			String[] cmds = { "/bin/sh", "-c", "ps -ef |grep " + fileName + ".ts" };
			Process pro = Runtime.getRuntime().exec(cmds);
			pro.waitFor();
			InputStream in = pro.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = read.readLine()) != null) {
				if (line.lastIndexOf("h264_mp4toannexb test.ts") != -1) {
					flag = true;
				}
				str.append(line);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public String ts() {
		StringBuilder str = new StringBuilder();
		try {
			String[] cmds = { "/bin/sh", "-c",
					"ffmpeg -i " + fileName + ".mp4 -c copy -bsf h264_mp4toannexb " + fileName + ".ts" };
			Process pro = Runtime.getRuntime().exec(cmds);
			pro.waitFor();
			InputStream in = pro.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = read.readLine()) != null) {
				str.append(line);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.toString();
	}

	public String m3u8() {
		StringBuilder str = new StringBuilder();
		try {
			String[] cmds = { "/bin/sh", "-c", "ffmpeg -i " + fileName + ".ts -c copy -map 0 -f segment -segment_list "
					+ fileName + ".m3u8 -segment_time 5 " + fileName + "%03d.ts" };
			Process pro = Runtime.getRuntime().exec(cmds);
			pro.waitFor();
			InputStream in = pro.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = read.readLine()) != null) {
				str.append(line);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.toString();
	}

}
