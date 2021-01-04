package com.MainTest;

import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.Initial.PlayMusic;
import com.Util.YcUtil;

import org.eclipse.swt.widgets.Label;

import javax.swing.ImageIcon;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

public class DJT {
	private int count = 0; // 记录所选英雄的数量

	protected Shell shell;
	public static Composite composite_2;
	public static Composite composite_1;
	public static Composite composite_2_1;
	public static Composite composite_2_1_1;
	public static Composite composite_2_1_2;
	public static Composite composite_2_1_3;
	public static Button btnNewButton_3;
	public static Button btnNewButton_4;
	public static Button btnNewButton_5;
	public static Button btnNewButton_6;
	public static Button btnNewButton_7;
	private PlayMusic pm = new PlayMusic();// 获取播放音乐对象
	// 存储选定英雄的文件路径
	public String photo_route[] = new String[10];

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DJT window = new DJT();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.MIN);
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
		shell.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/06.png"));
		shell.setSize(838, 722);
		shell.setText("点将台");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14, SWT.BOLD | SWT.ITALIC));
		label_1.setBounds(89, 36, 77, 31);
		label_1.setText("点将台");

		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		composite.setBounds(52, 52, 731, 383);

		Label label_2_5 = new Label(composite, SWT.NONE);
		label_2_5.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = label_2_5.getImage();
				int h = label_2_5.getBounds().height; // 获取控件的高
				int w = label_2_5.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
		label_2_5.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/bg_card.jpg"));
		label_2_5.setBounds(614, 20, 107, 107);

		Label label_2_6 = new Label(composite, SWT.NONE);
		label_2_6.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = label_2_6.getImage();
				int h = label_2_6.getBounds().height; // 获取控件的高
				int w = label_2_6.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
		label_2_6.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/bg_card.jpg"));
		label_2_6.setBounds(10, 150, 107, 107);

		Label label_2_7 = new Label(composite, SWT.NONE);
		label_2_7.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {

				Image image = label_2_7.getImage();
				int h = label_2_7.getBounds().height; // 获取控件的高
				int w = label_2_7.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
		label_2_7.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/bg_card.jpg"));
		label_2_7.setBounds(130, 150, 107, 107);

		Label label_2_8 = new Label(composite, SWT.NONE);
		label_2_8.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = label_2_8.getImage();
				int h = label_2_8.getBounds().height; // 获取控件的高
				int w = label_2_8.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
		label_2_8.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/bg_card.jpg"));
		label_2_8.setBounds(247, 150, 107, 107);

		Label label_2_9 = new Label(composite, SWT.NONE);
		label_2_9.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = label_2_9.getImage();
				int h = label_2_9.getBounds().height; // 获取控件的高
				int w = label_2_9.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
		label_2_9.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/bg_card.jpg"));
		label_2_9.setBounds(366, 150, 107, 107);

		Label label_2_10 = new Label(composite, SWT.NONE);
		label_2_10.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = label_2_10.getImage();
				int h = label_2_10.getBounds().height; // 获取控件的高
				int w = label_2_10.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
		label_2_10.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/bg_card.jpg"));
		label_2_10.setBounds(490, 150, 107, 107);

		Label label_2_11 = new Label(composite, SWT.NONE);
		label_2_11.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = label_2_11.getImage();
				int h = label_2_11.getBounds().height; // 获取控件的高
				int w = label_2_11.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
		label_2_11.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/bg_card.jpg"));
		label_2_11.setBounds(614, 150, 107, 107);

		Label label_2_12 = new Label(composite, SWT.NONE);
		label_2_12.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = label_2_12.getImage();
				int h = label_2_12.getBounds().height; // 获取控件的高
				int w = label_2_12.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
		label_2_12.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/bg_card.jpg"));
		label_2_12.setBounds(10, 266, 107, 107);

		Label label_2_13 = new Label(composite, SWT.NONE);
		label_2_13.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = label_2_13.getImage();
				int h = label_2_13.getBounds().height; // 获取控件的高
				int w = label_2_13.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
		label_2_13.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/bg_card.jpg"));
		label_2_13.setBounds(130, 266, 107, 107);

		Label label_2_14 = new Label(composite, SWT.NONE);
		label_2_14.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = label_2_14.getImage();
				int h = label_2_14.getBounds().height; // 获取控件的高
				int w = label_2_14.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
		label_2_14.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/bg_card.jpg"));
		label_2_14.setBounds(247, 266, 108, 107);

		Label label_2_15 = new Label(composite, SWT.NONE);
		label_2_15.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = label_2_15.getImage();
				int h = label_2_15.getBounds().height; // 获取控件的高
				int w = label_2_15.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
		label_2_15.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/bg_card.jpg"));
		label_2_15.setBounds(366, 266, 108, 107);

		Label label_2_16 = new Label(composite, SWT.NONE);
		label_2_16.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = label_2_16.getImage();
				int h = label_2_16.getBounds().height; // 获取控件的高
				int w = label_2_16.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
		label_2_16.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/bg_card.jpg"));
		label_2_16.setBounds(490, 266, 108, 107);

		Label label_2_17 = new Label(composite, SWT.NONE);
		label_2_17.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = label_2_17.getImage();
				int h = label_2_17.getBounds().height; // 获取控件的高
				int w = label_2_17.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
		label_2_17.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/bg_card.jpg"));
		label_2_17.setBounds(614, 266, 107, 107);

		/**
		 * 第一张曹操
		 */
		Button btnNewButton = new Button(composite, SWT.NONE);

		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				count++;
				pm.ButtonMusic();
				switch (count) {
				case 1:
					create_1(composite_2, "/com/yc/image/\u66F9\u64CD.jpg");
					break;
				case 2:
					create_2(composite_2_1, "/com/yc/image/\u66F9\u64CD.jpg");
					break;
				case 3:
					create_3(composite_2_1_1, "/com/yc/image/\u66F9\u64CD.jpg");
					break;
				case 4:
					create_4(composite_2_1_2, "/com/yc/image/\u66F9\u64CD.jpg");
					break;
				case 5:
					create_5(composite_2_1_3, "/com/yc/image/\u66F9\u64CD.jpg");
					break;
				}
				photo_route[count] = "/com/yc/image/\u66F9\u64CD.jpg";// 将图片路径保存到数组中
			}
		});
		btnNewButton.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/\u66F9\u64CD.jpg"));
		change_sex(btnNewButton);// 自适应按钮大小
		btnNewButton.setBounds(10, 21, 107, 107);

		/*
		 * 第二张 张飞
		 */
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				count++;
				pm.ButtonMusic();
				switch (count) {
				case 1:
					create_1(composite_2, "/com/yc/image/\u5F20\u98DE.jpg");
					break;
				case 2:
					create_2(composite_2_1, "/com/yc/image/\u5F20\u98DE.jpg");
					break;
				case 3:
					create_3(composite_2_1_1, "/com/yc/image/\u5F20\u98DE.jpg");
					break;
				case 4:
					create_4(composite_2_1_2, "/com/yc/image/\u5F20\u98DE.jpg");
					break;
				case 5:
					create_5(composite_2_1_3, "/com/yc/image/\u5F20\u98DE.jpg");
					break;
				}
				photo_route[count] = "/com/yc/image/\u5F20\u98DE.jpg";
			}
		});
		btnNewButton_1.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/\u5F20\u98DE.jpg"));
		change_sex(btnNewButton_1);
		btnNewButton_1.setBounds(130, 20, 107, 108);

		/*
		 * 第三张 黄忠
		 */

		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				count++;
				pm.ButtonMusic();
				switch (count) {
				case 1:
					create_1(composite_2, "/com/yc/image/\u9EC4\u5FE0.jpg");
					break;
				case 2:
					create_2(composite_2_1, "/com/yc/image/\u9EC4\u5FE0.jpg");
					break;
				case 3:
					create_3(composite_2_1_1, "/com/yc/image/\u9EC4\u5FE0.jpg");
					break;
				case 4:
					create_4(composite_2_1_2, "/com/yc/image/\u9EC4\u5FE0.jpg");
					break;
				case 5:
					create_5(composite_2_1_3, "/com/yc/image/\u9EC4\u5FE0.jpg");
					break;
				}
				photo_route[count] = "/com/yc/image/\u9EC4\u5FE0.jpg";

			}
		});
		btnNewButton_2.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/\u9EC4\u5FE0.jpg"));
		change_sex(btnNewButton_2);
		btnNewButton_2.setBounds(247, 21, 113, 107);

		/*
		 * 第四张 关羽
		 */
		Button btnNewButton_3 = new Button(composite, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				count++;
				pm.ButtonMusic();
				switch (count) {
				case 1:
					create_1(composite_2, "/com/yc/image/\u5173\u7FBD.jpg");
					break;
				case 2:
					create_2(composite_2_1, "/com/yc/image/\u5173\u7FBD.jpg");
					break;
				case 3:
					create_3(composite_2_1_1, "/com/yc/image/\u5173\u7FBD.jpg");
					break;
				case 4:
					create_4(composite_2_1_2, "/com/yc/image/\u5173\u7FBD.jpg");
					break;
				case 5:
					create_5(composite_2_1_3, "/com/yc/image/\u5173\u7FBD.jpg");
					break;
				}
				photo_route[count] = "/com/yc/image/\u5173\u7FBD.jpg";
			}
		});

		btnNewButton_3.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/\u5173\u7FBD.jpg"));
		change_sex(btnNewButton_3);
		btnNewButton_3.setBounds(375, 21, 109, 107);

		/*
		 * 第五张 吕蒙
		 */
		Button btnNewButton_4 = new Button(composite, SWT.NONE);
		btnNewButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				count++;
				pm.ButtonMusic();
				switch (count) {
				case 1:
					create_1(composite_2, "/com/yc/image/\u5415\u8499.jpg");
					break;
				case 2:
					create_2(composite_2_1, "/com/yc/image/\u5415\u8499.jpg");
					break;
				case 3:
					create_3(composite_2_1_1, "/com/yc/image/\u5415\u8499.jpg");
					break;
				case 4:
					create_4(composite_2_1_2, "/com/yc/image/\u5415\u8499.jpg");
					break;
				case 5:
					create_5(composite_2_1_3, "/com/yc/image/\u5415\u8499.jpg");
					break;
				}
				photo_route[count] = "/com/yc/image/\u5415\u8499.jpg";
			}
		});
		btnNewButton_4.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/\u5415\u8499.jpg"));
		change_sex(btnNewButton_4);
		btnNewButton_4.setBounds(497, 20, 111, 107);

		composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setBounds(10, 453, 815, 232);
		// 玩家英雄面板
		composite_2_1_3 = new Composite(composite_1, SWT.BORDER);
		composite_2_1_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		composite_2_1_3.setBounds(663, 32, 126, 172);

		// 第四个英雄面板
		composite_2_1_2 = new Composite(composite_1, SWT.BORDER);
		composite_2_1_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		composite_2_1_2.setBounds(418, 32, 126, 172);

		// 第三个英雄面板
		composite_2_1_1 = new Composite(composite_1, SWT.BORDER);
		composite_2_1_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		composite_2_1_1.setBounds(286, 32, 126, 172);

		// 第二个英雄面板
		composite_2_1 = new Composite(composite_1, SWT.BORDER);
		composite_2_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		composite_2_1.setBounds(154, 32, 126, 172);

		// 第一个英雄面板
		composite_2 = new Composite(composite_1, SWT.BORDER);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		composite_2.setBounds(22, 32, 126, 172);

		/*
		 * 出战
		 */
		Button button_1 = new Button(composite_1, SWT.WRAP);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
					//播放按钮声
					pm.ButtonMusic();
					shell.dispose();// 关掉原来的面板
					new MainTest(photo_route).open();// 通过构造函数把所选英雄数组传递给出战面板
			}
		});
		button_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		button_1.setText("出战");
		button_1.setBounds(564, 150, 82, 44);
		// 撤销按钮，点击此按钮出发点击事件，执行goback方法回退
		Button button_1_1 = new Button(composite_1, SWT.WRAP);
		button_1_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//播放按钮声
				pm.ButtonMusic();
				switch (count) {
				case 1:
					goback1();
					break;
				case 2:
					goback2();
					break;
				case 3:
					goback3();
					break;
				case 4:
					goback4();
					break;
				case 5:
					goback5();
					break;
				}
				count = count - 1;// 将选定的英雄数量-1
			}

		});
		button_1_1.setText("撤销");
		button_1_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
		button_1_1.setBounds(564, 46, 82, 44);

		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = label_3.getImage();
				int h = label_3.getBounds().height; // 获取控件的高
				int w = label_3.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				arg0.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
		label_3.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/bg_control.jpg"));
		label_3.setBounds(0, 0, 815, 232);

		Label label = new Label(shell, SWT.NONE);
		YcUtil.centerWindow(shell);
		label.setImage(SWTResourceManager.getImage(DJT.class, "/com/yc/image/bg.jpg"));
		label.setBounds(0, 0, 835, 695);

	}

	public void goback1() {
		// btnNewButton_3.setEn
		btnNewButton_3.dispose();// 删除组件
		composite_2.layout();// 刷新面板

	}

	public void goback2() {
		// btnNewButton_3.setEn
		btnNewButton_4.dispose();// 删除按钮
		composite_2_1.layout();// 刷新面板
	}

	public void goback3() {
		// btnNewButton_3.setEn
		btnNewButton_5.dispose();
		composite_2_1_1.layout();
	}

	public void goback4() {
		// btnNewButton_3.setEn
		btnNewButton_6.dispose();
		composite_2_1_2.layout();
	}

	public void goback5() {
		// btnNewButton_3.setEn
		btnNewButton_7.dispose();
		composite_2_1_3.layout();
	}

	public void create_1(Composite mianban, String url) {
		btnNewButton_3 = new Button(mianban, SWT.NONE);
		btnNewButton_3.setImage(SWTResourceManager.getImage(DJT.class, url));
		change_sex(btnNewButton_3);
		btnNewButton_3.setBounds(0, 0, 126, 172);
		// btnNewButton_3.setEnabled(false);

	}

	public void create_2(Composite mianban, String url) {
		btnNewButton_4 = new Button(mianban, SWT.NONE);
		btnNewButton_4.setImage(SWTResourceManager.getImage(DJT.class, url));

		change_sex(btnNewButton_4);
		btnNewButton_4.setBounds(0, 0, 126, 172);

	}

	public void create_3(Composite mianban, String url) {
		btnNewButton_5 = new Button(mianban, SWT.NONE);
		btnNewButton_5.setImage(SWTResourceManager.getImage(DJT.class, url));

		change_sex(btnNewButton_5);
		btnNewButton_5.setBounds(0, 0, 126, 172);

	}

	public void create_4(Composite mianban, String url) {
		btnNewButton_6 = new Button(mianban, SWT.NONE);
		btnNewButton_6.setImage(SWTResourceManager.getImage(DJT.class, url));

		change_sex(btnNewButton_6);
		btnNewButton_6.setBounds(0, 0, 126, 172);

	}

	public void create_5(Composite mianban, String url) {
		btnNewButton_7 = new Button(mianban, SWT.NONE);
		btnNewButton_7.setImage(SWTResourceManager.getImage(DJT.class, url));
		change_sex(btnNewButton_7);
		btnNewButton_7.setBounds(0, 0, 126, 172);
		btnNewButton_7.setEnabled(true);

	}

	public void change_sex(Button button) {
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
}
