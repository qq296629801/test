package cn.ymsys.util;

import java.io.File;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

public class ConvertVideo {

	public static String convert(File file) {

		FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(file.getAbsolutePath());
		String fileName = null;

		Frame captured_frame = null;

		FFmpegFrameRecorder recorder = null;

		try {
			frameGrabber.start();
			fileName = file.getAbsolutePath().replace(".mp4", "_edited.ts");
			recorder = new FFmpegFrameRecorder(fileName, frameGrabber.getImageWidth(), frameGrabber.getImageHeight(),
					frameGrabber.getAudioChannels());
			recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // avcodec.AV_CODEC_ID_H264  //AV_CODEC_ID_MPEG4
			recorder.setFormat("mp4");
			recorder.setFrameRate(frameGrabber.getFrameRate());
			recorder.setSampleFormat(frameGrabber.getSampleFormat());
			recorder.setSampleRate(frameGrabber.getSampleRate());
			recorder.setAudioChannels(frameGrabber.getAudioChannels());
			recorder.setFrameRate(frameGrabber.getFrameRate());
			recorder.start();
			while (true) {
				try {
					captured_frame = frameGrabber.grabFrame();

					if (captured_frame == null) {
						System.out.println("!!! Failed cvQueryFrame");
						break;
					}
					recorder.setTimestamp(frameGrabber.getTimestamp());
					recorder.record(captured_frame);

				} catch (Exception e) {
				}
			}
			recorder.stop();
			recorder.release();
			frameGrabber.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		file.delete();
		return fileName;
	}

	public static void main(String[] args) {
		convert(new File("C:\\data\\test.mp4"));
	}

}