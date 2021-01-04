package com.Initial;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class PlayMusic extends Thread{

	/**
	 *  开局音乐播放
	 */
	public void playLogin() {
		File file = new File("src/com/yc/music/(开局音乐)aGreatGiantPlay.wav");
		MusicPlay(file);
	}
	/**
	 * 背景音乐播放
	 */
	public void playMusic() {
		 File file = new File("src/com/yc/music/三国杀背景音乐1.wav");
		MusicPlay(file);
	}
	/**
	 * 按钮音
	 */
	public void ButtonMusic () {
		File file = new File("src/com/yc/music/按钮选中音.wav");
		ShortMusicPlay(file);
	}
	/**
	 * 出牌音效
	 */
	public void HitMusic () {
		File file = new File("src/com/yc/music/出牌.wav");
		ShortMusicPlay(file);
	}
	/**
	 * 手牌音效
	 * @CardName 输入牌名
	 */
	public void Cards (String CardName) {
		File file = new File("src/com/yc/music/"+CardName+".wav");
		ShortMusicPlay(file);
	}
	/**
	 * 调用文件播放方法
	 */
	public void MusicPlay(File file) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			AudioFormat aif = ais.getFormat();
			final SourceDataLine sdl;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(aif);
			sdl.start();
			int nByte = 0;
			int writeByte = 0;
			final int SIZE = 1024 * 64;
			byte[] buffer = new byte[SIZE];
			while (nByte != -1) {
				
					sdl.write(buffer, 0, nByte);
					nByte = ais.read(buffer, 0, SIZE);
					
			}
			sdl.stop();
			ais.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 调用文件播放方法(适用于短音频)
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException 
	 * @throws InterruptedException 
	 */
	public void ShortMusicPlay(File file) {
		
		try {
			AudioInputStream audioInputStream;
			audioInputStream = AudioSystem.getAudioInputStream(file);
			DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());// 获取格式

			Clip clip = (Clip) AudioSystem.getLine(info);// 强转类型给剪辑（clip）赋值
			clip.addLineListener(new LineListener() {// 注册一个监听器
				public void update(LineEvent e) {// 监听到的事件类型 e 传入update中
					if (e.getType() == LineEvent.Type.STOP) {// 判断 e 是否为STOP类型//主要是防止线程等待时间长了未响应
						synchronized (clip) {// 只有一个线程能使用的代码块
							clip.notify();// 唤醒一个线程，并继续往下走
						}
					}
				}
			});
			clip.open(audioInputStream);
			clip.start();// 音频剪辑的回放可以使用 start 和 stop 方法开始和终止
			synchronized (clip) {
				clip.wait();// 线程等待
			}
			clip.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 获取文件数据

	}
}
