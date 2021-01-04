package com.Util;

import java.awt.Dimension;

import java.awt.Toolkit;

import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * 帮助类
 * 
 * @author 浪痞小霸王队
 *
 */
public class YcUtil {

	public static int count = 0; // 记录所选英雄的数量
	public static Label label;

	/**
	 * 窗口居中
	 * 
	 * @param shell
	 */
	public static void centerWindow(Shell shell) {
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize(); // 使用Toolkit 对屏幕的宽和高进行获取
		// 计算
		shell.setLocation((ds.width - shell.getSize().x) / 2, // （屏幕的宽-窗口的宽）/2 ==》保证窗口左右居中
				(ds.height - shell.getSize().y) / 2); // （屏幕的长-窗口的长）/2==》保证窗口上居中
	}

	/**
	 * 面板背景图片自适应
	 * 
	 * @param cp
	 * @param imgPath
	 */
	public static void imgResize(Composite cp, String imgPath) {
		// 获取面板的背景图片
		Image image = cp.getBackgroundImage();
		// Image image =SWTResourceManager.getImage(MainWin.class,imgPath);
		// //获取背景面板和背景图片路径
		cp.addPaintListener(new PaintListener() { // 对图片进行重新绘制
			@Override
			public void paintControl(PaintEvent e) {
				Point size = cp.getSize(); // 获取图片的大小
				Point location = cp.getLocation(); // 获取图片要绘制的开始结束位置
				e.gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, // 获取图片，图片绘制的初始位置
						location.x, location.y - 30, size.x, size.y); // 绘制后图片的大小，长宽随着窗口大小自适应变化

			}
		});
	}

	/**
	 * 按钮中图片自适应
	 * 
	 * @param button
	 */
	public static void change_sex(Button button) {
		button.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = button.getImage();
				int h = button.getBounds().height; // 获取控件的高
				int w = button.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
	}

	/**
	 * 标签中图片自适应
	 * 
	 * @param label
	 */
	public static void change_sex(Label label) {
		label.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = label.getImage();
				int h = label.getBounds().height; // 获取控件的高
				int w = label.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
	}

	/**
	 * 界面中图片自适应
	 * 
	 * @param shell
	 */
	public static void change_sex(Shell shell) {
		shell.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = shell.getImage();
				int h = shell.getBounds().height; // 获取控件的高
				int w = shell.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
	}

}