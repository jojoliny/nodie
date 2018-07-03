package utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayTool implements Runnable {

	private String filePath;
	SourceDataLine auline = null;// 源数据行是可以写入数据的数据行。它充当其混频器的源。

	public MusicPlayTool(String wavfile, boolean isLoop) {
		filePath = wavfile;
		this.isLoop = isLoop;
		new Thread(this).start();
	}

	boolean isLoop = true;

	@Override
	public void run() {
		do {
			System.out.println("~~~" + Thread.currentThread().getName() + "isLoop" + isLoop);
			File audioFile = new File(filePath);
			AudioInputStream audioInputStream = null;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(audioFile);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			AudioFormat format = audioInputStream.getFormat();
			// public DataLine.Info(Class<?> lineClass,AudioFormat format)
			// 根据指定信息构造数据行的信息对象，这些信息包括单个音频格式。此构造方法通常由应用程序用于描述所需的行。
			// lineClass - 该信息对象所描述的数据行的类 format - 所需的格式
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			try {
				auline = (SourceDataLine) AudioSystem.getLine(info);
				auline.open(format); // 打开
			} catch (LineUnavailableException e) {
				e.printStackTrace();
				return;
			}
			auline.start(); // 开始播放
			int nBytesRead = 0;
			byte[] abData = new byte[512];
			try {
				while (nBytesRead != -1) {
					nBytesRead = audioInputStream.read(abData, 0, abData.length);
					if (nBytesRead >= 0)
						auline.write(abData, 0, nBytesRead);
				}
			} catch (IOException e) {
				e.printStackTrace();
				return;
			} finally {
				auline.drain();
				auline.close();
			}
			System.out.println("~~~" + Thread.currentThread().getName() + "isLoop" + isLoop);
		} while (isLoop);
	}

	public void stopMusic() {
		System.out.println("~~~" + Thread.currentThread().getName());
		System.out.println("==");
		isLoop = false;
		if (auline != null) {
			System.out.println("==");
			auline.stop();
		}
	}

	public void startMusic() {
		if (auline != null)
			auline.start();
	}

}
