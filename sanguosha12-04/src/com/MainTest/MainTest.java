/**
 * 点将台，选英雄界面，将选好的英雄在出战界面内以逆时针排序   玩家为第五英雄
 */
package com.MainTest;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.Biz.SanGuoBiz;
import com.Initial.Initial;
import com.Util.YcUtil;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainTest {

	protected Shell shell;
	public String heros[] = new String[10];// 存储从点将台接受到的英雄

	private SanGuoBiz biz = new SanGuoBiz();// 业务层

	public static String p;// 攻击方

	public static String top;// 被攻击方

	Composite composite;

	/**
	 * 手牌按钮全局变量
	 */
	public static Button button_1_8;
	public static Button button_2_8;
	public static Button button_1_7;
	public static Button button_2_7;
	public static Button button_1_6;
	public static Button button_2_6;
	public static Button button_1_5;
	public static Button button_2_5;
	public static Button button_1_4;
	public static Button button_2_4;
	public static Button button_1_3;
	public static Button button_2_3;
	public static Button button_1_2;
	public static Button button_2_2;
	public static Button button_1_1;
	public static Button button_2_1;

	/**
	 * 血条
	 */
	Label lblNewLabel_10_1_9;
	Label lblNewLabel_10_1_9_1;
	Label lblNewLabel_10_1_9_1_1;
	Label lblNewLabel_10_1_9_1_1_1;

	Label lblNewLabel_10;
	Label lblNewLabel_11;
	Label lblNewLabel_12;

	Label lblNewLabel_10_1;
	Label lblNewLabel_10_1_1;
	Label lblNewLabel_10_1_2;

	Label lblNewLabel_10_1_3;
	Label lblNewLabel_10_1_4;
	Label lblNewLabel_10_1_5;

	Label lblNewLabel_10_1_6;
	Label lblNewLabel_10_1_7;
	Label lblNewLabel_10_1_8;

	public static int heroCount = 1;// 英雄变量

	public static Object[] p1ShouPai = new String[100];// p1玩家手牌路径

	public static int listNumber = 88; // 牌堆计数器，初始88张

	public static int x = 0; // x表示按钮是否有图片，x=1代表button_1_1上有手牌，0代表无手牌

	public static int naPai = 0;// 玩家选的哪个牌，1代表button_1_1上手牌

	public static int faPaiCount = 0;// 发出的牌计数器

	public static int throwNumber = 0;// 废弃牌堆计数器

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	// 不带参数构造方法
	public MainTest() {

	}

	// 将点将台的英雄传进来
	public MainTest(String heros[]) {
		this.heros = heros;
	}

	public static void main(String[] args) {
		try {
			MainTest window = new MainTest();
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
		shell = new Shell();
		shell.setSize(992, 742);
		shell.setText("SWT Application");
		YcUtil.centerWindow(shell);

		/*
		 * 获取点将台所选的英雄
		 */

		/**
		 * 初始化英雄
		 */
		biz.setHero(heros);
		// 打印所选英雄路径
		for (int i = 1; i <= 5; i++) {
			System.out.println("加载完成" + heros[i].substring(14, 16));
		}

		composite = new Composite(shell, SWT.BORDER);
		// composite.setBackgroundMode(SWT.INHERIT_FORCE);
		composite.setBounds(2, 0, 970, 695);
		// 为面板添加背景图片，使得添加的label，button能够显示背景，
		// 注意：不能将背景图片放在一个标签或者按钮里面，否则设置的透明度无效
		composite.setBackgroundImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/bg.jpg"));

		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);

		lblNewLabel_10_1_9 = new Label(composite, SWT.NONE);
		lblNewLabel_10_1_9.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_10_1_9.setBounds(939, 537, 65, 28);

		lblNewLabel_10_1_9_1 = new Label(composite, SWT.NONE);
		lblNewLabel_10_1_9_1.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_10_1_9_1.setBounds(939, 569, 71, 28);

		lblNewLabel_10_1_9_1_1 = new Label(composite, SWT.NONE);
		lblNewLabel_10_1_9_1_1.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_10_1_9_1_1.setBounds(939, 597, 76, 28);

		lblNewLabel_10_1_9_1_1_1 = new Label(composite, SWT.NONE);
		lblNewLabel_10_1_9_1_1_1
				.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_10_1_9_1_1_1.setBounds(939, 627, 81, 28);

		lblNewLabel_10_1_8 = new Label(composite, SWT.NONE);
		lblNewLabel_10_1_8.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_10_1_8.setBounds(150, 432, 59, 28);

		lblNewLabel_10_1_7 = new Label(composite, SWT.NONE);
		lblNewLabel_10_1_7.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_10_1_7.setBounds(150, 404, 59, 28);

		lblNewLabel_10_1_6 = new Label(composite, SWT.NONE);
		lblNewLabel_10_1_6.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_10_1_6.setBounds(150, 378, 70, 28);

		lblNewLabel_10_1_5 = new Label(composite, SWT.NONE);
		lblNewLabel_10_1_5.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_10_1_5.setBounds(346, 150, 59, 28);

		lblNewLabel_10_1_4 = new Label(composite, SWT.NONE);
		lblNewLabel_10_1_4.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_10_1_4.setBounds(346, 122, 59, 28);

		lblNewLabel_10_1_3 = new Label(composite, SWT.NONE);
		lblNewLabel_10_1_3.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_10_1_3.setBounds(346, 93, 37, 28);

		lblNewLabel_10_1_2 = new Label(composite, SWT.NONE);
		lblNewLabel_10_1_2.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_10_1_2.setBounds(541, 150, 59, 28);

		lblNewLabel_10_1_1 = new Label(composite, SWT.NONE);
		lblNewLabel_10_1_1.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_10_1_1.setBounds(541, 122, 59, 28);

		lblNewLabel_10_1 = new Label(composite, SWT.NONE);
		lblNewLabel_10_1.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_10_1.setBounds(541, 93, 59, 28);

		lblNewLabel_12 = new Label(composite, SWT.NONE);
		lblNewLabel_12.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_12.setBounds(734, 150, 59, 28);

		lblNewLabel_10 = new Label(composite, SWT.NONE);
		lblNewLabel_10.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_10.setBounds(734, 93, 59, 28);

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/bg_control.jpg"));
		composite_1.setBounds(0, 514, 778, 182);
		change_size3(composite_1);// 面板自适应
		Label lblNewLabel_9 = new Label(composite_1, SWT.NONE);
		lblNewLabel_9.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/bg_eq.jpg"));
		lblNewLabel_9.setBounds(10, 10, 156, 151);
		change_size(lblNewLabel_9);// label自适应

		/**
		 * 武将选择区
		 */
		//TODO
		p = heros[5];
		Button btnNewButton_5 = new Button(composite, SWT.NONE);
		Button btnNewButton_5_1 = new Button(composite, SWT.NONE);
		btnNewButton_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				top = heros[1];
				if(true) {//判断函数
					//图片按钮不可视化，选中按钮可视化
					btnNewButton_5.setVisible(false);
					btnNewButton_5_1.setVisible(true);
					//选中函数确定
					System.out.println("选中"+heros[1]);
				}
			}
		});
		btnNewButton_5.setImage(SWTResourceManager.getImage(MainTest.class, heros[1]));
		btnNewButton_5.setBounds(606, 25, 130, 70);
		change_size1(btnNewButton_5);
		btnNewButton_5.setVisible(true);
		//覆盖按钮
		btnNewButton_5_1.setText("已选中");
		btnNewButton_5_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
					//取消选中
					btnNewButton_5.setVisible(true);
					btnNewButton_5_1.setVisible(false);
				//选中函数取消
					System.out.println("取消选中"+heros[1]);
			}
		});
		btnNewButton_5_1.setBounds(606, 25, 130, 70);
		btnNewButton_5_1.setVisible(false);
		

		Button btnNewButton_4 = new Button(composite, SWT.NONE);
		Button btnNewButton_4_1 = new Button(composite, SWT.NONE);
		btnNewButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				top = heros[2];
				if(true) {//判断函数
					//图片按钮不可视化，选中按钮可视化
					btnNewButton_4.setVisible(false);
					btnNewButton_4_1.setVisible(true);
					//选中函数确定
					System.out.println("选中"+heros[2]);
				}
			}
		});
		btnNewButton_4.setImage(SWTResourceManager.getImage(MainTest.class, heros[2]));
		btnNewButton_4.setBounds(411, 27, 130, 68);
		change_size1(btnNewButton_4);
		btnNewButton_4.setVisible(true);
		//覆盖按钮
		btnNewButton_4_1.setText("已选中");
		btnNewButton_4_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
					//取消选中
					btnNewButton_4.setVisible(true);
					btnNewButton_4_1.setVisible(false);
				//选中函数取消
					System.out.println("取消选中"+heros[2]);
			}
		});
		btnNewButton_4_1.setBounds(411, 27, 130, 68);
		btnNewButton_4_1.setVisible(false);
		
		
		
		Button btnNewButton_3 = new Button(composite, SWT.NONE);
		Button btnNewButton_3_1 = new Button(composite, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				top = heros[3];
				if(true) {//判断函数
					//图片按钮不可视化，选中按钮可视化
					btnNewButton_3.setVisible(false);
					btnNewButton_3_1.setVisible(true);
					//选中函数确定
					System.out.println("选中"+heros[3]);
				}
			}
		});
		btnNewButton_3.setImage(SWTResourceManager.getImage(MainTest.class, heros[3]));
		btnNewButton_3.setBounds(214, 25, 130, 70);
		change_size1(btnNewButton_3);

		btnNewButton_3.setVisible(true);
		//覆盖按钮
		btnNewButton_3_1.setText("已选中");
		btnNewButton_3_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
					//取消选中
					btnNewButton_3.setVisible(true);
					btnNewButton_3_1.setVisible(false);
				//选中函数取消
					System.out.println("取消选中"+heros[3]);
			}
		});
		btnNewButton_3_1.setBounds(214, 25, 130, 70);
		btnNewButton_3_1.setVisible(false);
		

		Button btnNewButton_6 = new Button(composite, SWT.NONE);
		Button btnNewButton_6_1 = new Button(composite, SWT.NONE);
		btnNewButton_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				top = heros[4];
				if(true) {//判断函数
					//图片按钮不可视化，选中按钮可视化
					btnNewButton_6.setVisible(false);
					btnNewButton_6_1.setVisible(true);
					//选中函数确定
					System.out.println("选中"+heros[4]);
				}
			}
		});
		btnNewButton_6.setImage(SWTResourceManager.getImage(MainTest.class, heros[4]));
		btnNewButton_6.setBounds(24, 314, 130, 72);
		change_size1(btnNewButton_6);

		btnNewButton_6.setVisible(true);
		//覆盖按钮
		btnNewButton_6_1.setText("已选中");
		btnNewButton_6_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
					//取消选中
					btnNewButton_6.setVisible(true);
					btnNewButton_6_1.setVisible(false);
				//选中函数取消
					System.out.println("取消选中"+heros[4]);
			}
		});
		btnNewButton_6_1.setBounds(24, 314, 130, 72);
		btnNewButton_6_1.setVisible(false);	
		//TODO
		
		/**
		 * 使用手牌区,传入攻击者和被攻击者
		 */
		
		
		

		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setBounds(784, 514, 170, 182);

		Button btnNewButton_7 = new Button(composite_2, SWT.NONE);
		btnNewButton_7.setImage(SWTResourceManager.getImage(MainTest.class, heros[5]));
		btnNewButton_7.setBounds(26, 21, 130, 140);
		change_size1(btnNewButton_7);

		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/headborder.png"));
		change_size(lblNewLabel);
		lblNewLabel.setBounds(0, 0, 170, 172);
		Label lblNewLabel_5 = new Label(composite, SWT.NONE);
		lblNewLabel_5.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/bg_eq.jpg"));
		change_size(lblNewLabel_5);
		lblNewLabel_5.setBounds(24, 392, 130, 77);
		Label lblNewLabel_6 = new Label(composite, SWT.NONE);
		lblNewLabel_6.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/bg_eq.jpg"));
		lblNewLabel_6.setBounds(214, 101, 130, 77);
		change_size(lblNewLabel_6);
		Label lblNewLabel_7 = new Label(composite, SWT.NONE);
		lblNewLabel_7.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/bg_eq.jpg"));
		lblNewLabel_7.setBounds(411, 101, 130, 77);
		change_size(lblNewLabel_7);
		Label lblNewLabel_8 = new Label(composite, SWT.NONE);
		lblNewLabel_8.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/bg_eq.jpg"));
		lblNewLabel_8.setBounds(606, 101, 130, 77);
		change_size(lblNewLabel_8);

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		// lblNewLabel_1.setBackground(SWTResourceManager.getColor(139, 69, 19));
		lblNewLabel_1.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/headborder.png"));
		lblNewLabel_1.setBounds(194, 10, 170, 182);

		change_size(lblNewLabel_1);

		Label lblNewLabel_2 = new Label(composite, SWT.SHADOW_NONE);
		lblNewLabel_2.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/headborder.png"));
		lblNewLabel_2.setBounds(389, 10, 170, 182);
		change_size(lblNewLabel_2);

		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/headborder.png"));
		lblNewLabel_4.setBounds(0, 298, 170, 182);
		change_size(lblNewLabel_4);

		lblNewLabel_11 = new Label(composite, SWT.NONE);
		lblNewLabel_11.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/color_hongxin.png"));
		lblNewLabel_11.setBounds(734, 122, 59, 28);

		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/headborder.png"));
		lblNewLabel_3.setBounds(584, 10, 170, 182);
		// shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		change_size(lblNewLabel_3);

		/**
		 * 手牌中按钮(8手牌上限)
		 */

		button_1_8 = new Button(composite_1, SWT.NONE);
		button_1_8.setVisible(false);
		button_1_8.setBounds(604, 45, 100, 120);

		button_2_8 = new Button(composite_1, SWT.NONE);
		button_2_8.setVisible(false);
		button_2_8.setBounds(604, 20, 100, 120);

		button_1_7 = new Button(composite_1, SWT.NONE);
		button_1_7.setVisible(false);
		button_1_7.setBounds(542, 45, 100, 120);

		button_2_7 = new Button(composite_1, SWT.NONE);
		button_2_7.setVisible(false);
		button_2_7.setBounds(542, 20, 100, 120);

		button_1_6 = new Button(composite_1, SWT.NONE);
		button_1_6.setVisible(false);
		button_1_6.setBounds(480, 45, 100, 120);

		button_2_6 = new Button(composite_1, SWT.NONE);
		button_2_6.setVisible(false);
		button_2_6.setBounds(480, 20, 100, 120);

		button_1_5 = new Button(composite_1, SWT.NONE);
		button_1_5.setVisible(false);
		button_1_5.setBounds(418, 45, 100, 120);

		button_2_5 = new Button(composite_1, SWT.NONE);
		button_2_5.setVisible(false);
		button_2_5.setBounds(418, 20, 100, 120);

		button_1_4 = new Button(composite_1, SWT.NONE);
		button_1_4.setVisible(true);
		button_1_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 抬起
				button_2_4.setImage(button_1_4.getImage());
				YcUtil.change_sex(button_1_4);
				button_2_4.setVisible(true);
				button_1_4.setVisible(false);
			}
		});
		button_1_4.setBounds(356, 45, 100, 120);

		button_2_4 = new Button(composite_1, SWT.NONE);
		button_2_4.setVisible(false);
		button_2_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 收回
				button_2_4.setVisible(false);
				button_1_4.setVisible(true);
			}
		});
		button_2_4.setBounds(356, 20, 100, 120);

		button_1_3 = new Button(composite_1, SWT.NONE);
		button_1_3.setVisible(true);
		button_1_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 抬起
				button_2_3.setImage(button_1_3.getImage());
				YcUtil.change_sex(button_1_3);
				button_2_3.setVisible(true);
				button_1_3.setVisible(false);
			}
		});
		button_1_3.setBounds(294, 45, 100, 120);

		button_2_3 = new Button(composite_1, SWT.NONE);
		button_2_3.setVisible(false);
		button_2_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 收回
				button_2_3.setVisible(false);
				button_1_3.setVisible(true);
			}
		});
		button_2_3.setBounds(294, 20, 100, 120);

		button_1_2 = new Button(composite_1, SWT.NONE);
		button_1_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 抬起
				button_2_2.setImage(button_1_2.getImage());
				YcUtil.change_sex(button_1_2);
				button_2_2.setVisible(true);
				button_1_2.setVisible(false);
			}
		});
		button_1_2.setVisible(true);
		button_1_2.setBounds(232, 45, 100, 120);

		button_2_2 = new Button(composite_1, SWT.NONE);
		button_2_2.setVisible(false);
		button_2_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 收回
				button_2_2.setVisible(false);
				button_1_2.setVisible(true);
			}
		});
		button_2_2.setBounds(232, 20, 100, 120);

		button_1_1 = new Button(composite_1, SWT.NONE);
		button_1_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 抬起
				button_2_1.setImage(button_1_1.getImage());
				YcUtil.change_sex(button_2_1);
				button_2_1.setVisible(true);
				button_1_1.setVisible(false);
			}
		});
		button_1_1.setVisible(true);
		button_1_1.setBounds(170, 45, 100, 120);

		button_2_1 = new Button(composite_1, SWT.NONE);
		button_2_1.setVisible(false);
		button_2_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 收回
				button_2_1.setVisible(false);
				button_1_1.setVisible(true);
			}
		});
		button_2_1.setBounds(170, 20, 100, 120);

		/**
		 * 发牌
		 */
		int begin = Initial.listCard.toArray()[faPaiCount].toString().lastIndexOf("-") + 1;
		System.out.println(begin);
		System.out.println(Initial.listCard.toArray()[faPaiCount].toString().substring(begin));
		// 玩家手牌显示
		button_1_1.setImage(SWTResourceManager.getImage(MainTest.class,
				Initial.listCard.toArray()[faPaiCount].toString().substring(begin)));
		System.out.println(heros[5]);
		YcUtil.change_sex(button_1_1);
		p1ShouPai[faPaiCount] = Initial.listCard.toArray()[faPaiCount].toString().substring(begin);
		MainTest.x++;
		faPaiCount++;

		begin = Initial.listCard.toArray()[faPaiCount].toString().lastIndexOf("-") + 1;
		System.out.println(begin);
		System.out.println(Initial.listCard.toArray()[faPaiCount].toString().substring(begin));
		button_1_2.setImage(SWTResourceManager.getImage(MainTest.class,
				Initial.listCard.toArray()[faPaiCount].toString().substring(begin)));
		System.out.println(heros[5]);
		YcUtil.change_sex(button_1_2);
		p1ShouPai[faPaiCount] = Initial.listCard.toArray()[faPaiCount].toString().substring(begin);
		MainTest.x++;
		faPaiCount++;

		begin = Initial.listCard.toArray()[faPaiCount].toString().lastIndexOf("-") + 1;
		System.out.println(begin);
		System.out.println(Initial.listCard.toArray()[faPaiCount].toString().substring(begin));
		button_1_3.setImage(SWTResourceManager.getImage(MainTest.class,
				Initial.listCard.toArray()[faPaiCount].toString().substring(begin)));
		System.out.println(heros[5]);
		YcUtil.change_sex(button_1_3);
		p1ShouPai[faPaiCount] = Initial.listCard.toArray()[faPaiCount].toString().substring(begin);
		MainTest.x++;
		faPaiCount++;

		begin = Initial.listCard.toArray()[faPaiCount].toString().lastIndexOf("-") + 1;
		System.out.println(begin);
		System.out.println(Initial.listCard.toArray()[faPaiCount].toString().substring(begin));
		button_1_4.setImage(SWTResourceManager.getImage(MainTest.class,
				Initial.listCard.toArray()[faPaiCount].toString().substring(begin)));
		System.out.println(heros[5]);
		YcUtil.change_sex(button_1_4);
		p1ShouPai[faPaiCount] = Initial.listCard.toArray()[faPaiCount].toString().substring(begin);
		MainTest.x++;

//		for(Object ps:p1ShouPai) {
//			System.out.println(ps);
//		}

		/**
		 * 第一回合发四张牌
		 */
		// 第五位英雄发牌（玩家）
		biz.faPai_1(heros[5]);
		// 第一位英雄发牌
		biz.faPai_2(heros[heroCount]);
		faPaiCount += 4;
		heroCount++;
		// 第二位英雄发牌
		biz.faPai_2(heros[heroCount]);
		faPaiCount += 4;
		heroCount++;
		// 第三位英雄发牌
		biz.faPai_2(heros[heroCount]);
		faPaiCount += 4;
		heroCount++;
		// 第四位英雄发牌
		biz.faPai_2(heros[heroCount]);
		faPaiCount += 4;

		/**
		 * 确定的点击事件
		 */
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 判断哪个在抬起状态
				if (MainTest.button_2_1.getVisible() == true) {
					MainTest.button_2_1.setVisible(false);
					MainTest.button_2_1.setImage(null);// 清除图片
					MainTest.x--;
					MainTest.naPai = 1;
					// 使用手牌
					System.out.println(p + "对" + top + "使用了" + p1ShouPai[naPai - 1]);
					biz.chuPai(naPai - 1);
					/**
					 * 移动手牌图片
					 */
					biz.YiDoPai();
				}
				if (button_2_2.getVisible() == true) {
					MainTest.button_2_2.setVisible(false);
					MainTest.button_2_2.setImage(null);
					MainTest.x--;
					MainTest.naPai = 2;
					// 使用手牌

					System.out.println(p + "对" + top + "使用了" + p1ShouPai[naPai - 1]);
					biz.chuPai(naPai - 1);
					/**
					 * 移动手牌图片
					 */
					biz.YiDoPai();
				}
				if (button_2_3.getVisible() == true) {
					MainTest.button_2_3.setVisible(false);
					MainTest.button_2_3.setImage(null);
					MainTest.x--;
					MainTest.naPai = 3;
					// 使用手牌

					System.out.println(p + "对" + top + "使用了" + p1ShouPai[naPai - 1]);
					biz.chuPai(naPai - 1);
					/**
					 * 移动手牌图片
					 */
					biz.YiDoPai();
				}
				if (button_2_4.getVisible() == true) {
					MainTest.button_2_4.setVisible(false);
					MainTest.button_2_4.setImage(null);
					MainTest.x--;
					MainTest.naPai = 4;
					// 使用手牌
					System.out.println(p + "对" + top + "使用了" + p1ShouPai[naPai - 1]);
					biz.chuPai(naPai - 1);
					/**
					 * 移动手牌图片
					 */
					biz.YiDoPai();
				}
				if (button_2_5.getVisible() == true) {
					MainTest.button_2_5.setVisible(false);
					MainTest.button_2_5.setImage(null);
					MainTest.x--;
					MainTest.naPai = 5;
					// 使用手牌
					System.out.println(p + "对" + top + "使用了" + p1ShouPai[naPai - 1]);
					biz.chuPai(naPai - 1);
					/**
					 * 移动手牌图片
					 */
					biz.YiDoPai();
				}
				if (button_2_6.getVisible() == true) {
					MainTest.button_2_6.setVisible(false);
					MainTest.button_2_6.setImage(null);
					MainTest.x--;
					MainTest.naPai = 6;
					// 使用手牌

					System.out.println(p + "对" + top + "使用了" + p1ShouPai[naPai - 1]);
					biz.chuPai(naPai - 1);
					/**
					 * 移动手牌图片
					 */
					biz.YiDoPai();
				}
				if (button_2_7.getVisible() == true) {
					MainTest.button_2_7.setVisible(false);
					MainTest.button_2_7.setImage(null);
					MainTest.x--;
					MainTest.naPai = 7;
					// 使用手牌

					System.out.println(p + "对" + top + "使用了" + p1ShouPai[naPai - 1]);
					biz.chuPai(naPai - 1);
					/**
					 * 移动手牌图片
					 */
					biz.YiDoPai();
				}
				if (button_2_8.getVisible() == true) {
					MainTest.button_2_8.setVisible(false);
					MainTest.button_2_8.setImage(null);
					MainTest.x--;
					MainTest.naPai = 8;
					// 使用手牌
					System.out.println(p + "对" + top + "使用了" + p1ShouPai[naPai - 1]);
					biz.chuPai(naPai - 1);
				}
			}
		});
		btnNewButton.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/bok1.png"));
		btnNewButton.setBounds(670, 27, 98, 30);
		YcUtil.change_sex(btnNewButton);

		/**
		 * 结束出牌的点击事件
		 */
		Button btnNewButton_1 = new Button(composite_1, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				p = heros[1];
				// 结束出牌
				biz.jieShuChpai(p);
			}
		});
		btnNewButton_1.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/bok.png"));
		btnNewButton_1.setBounds(670, 64, 98, 30);
		change_size1(btnNewButton_1);

		/**
		 * 技能的点击事件
		 */
		Button btnNewButton_2 = new Button(composite_1, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

			}
		});
		btnNewButton_2.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/bok.png"));
		btnNewButton_2.setBounds(670, 118, 98, 30);
		change_size1(btnNewButton_2);

	}

	// 标签自适应
	public void change_size(Label label) {
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

	// 按钮自适应
	public void change_size1(Button button) {
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

	// 面板自适应
	public void change_size3(Composite button) {
		button.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				Image image = button.getBackgroundImage();
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
	 * 掉血方法
	 * 
	 * @param label
	 * @param composite
	 */
	public void injured(Label label, Composite composite) {
		// System.out.println(label.getBounds());
		int heigth = label.getBounds().height;
		int width = label.getBounds().width;
		int x = label.getBounds().x;
		int y = label.getBounds().y;
		// System.out.println(x+" "+y+ " "+heigth +" "+width );
		// 实现撤销标签
		label.dispose();

		// 创建新的标签
		label = new Label(composite, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(MainTest.class, "/com/yc/image/hp2.png"));
		label.setBounds(x, y, width, heigth);
		YcUtil.change_sex(label);
		change_size(label);
		composite.layout();
	}

	/**
	 * 血量刷新
	 */
	public void query() {
		// 获得各个英雄的当前血量
		int x1 = Initial.p1.getCharacter().getHp();
		int x2 = Initial.p2.getCharacter().getHp();
		int x3 = Initial.p3.getCharacter().getHp();
		int x4 = Initial.p4.getCharacter().getHp();
		int x5 = Initial.p5.getCharacter().getHp();
		// 刷新
		if (x1 == 3) {
			injured(lblNewLabel_10_1_9, composite);
		}
		if (x1 == 2) {
			injured(lblNewLabel_10_1_9_1, composite);
		}
		if (x1 == 1) {
			injured(lblNewLabel_10_1_9_1_1, composite);
		}
		if (x1 == 0) {
			injured(lblNewLabel_10_1_9_1_1_1, composite);
		}

		if (x2 == 2) {
			injured(lblNewLabel_10, composite);
		}
		if (x2 == 1) {
			injured(lblNewLabel_11, composite);
		}
		if (x2 == 0) {
			injured(lblNewLabel_12, composite);
		}
		if (x3 == 2) {
			injured(lblNewLabel_10_1, composite);
		}
		if (x3 == 1) {
			injured(lblNewLabel_10_1_1, composite);
		}
		if (x3 == 0) {
			injured(lblNewLabel_10_1_2, composite);
		}
		if (x4 == 2) {
			injured(lblNewLabel_10_1_3, composite);
		}
		if (x4 == 1) {
			injured(lblNewLabel_10_1_4, composite);
		}
		if (x4 == 0) {
			injured(lblNewLabel_10_1_5, composite);
		}
		if (x5 == 2) {
			injured(lblNewLabel_10_1_6, composite);
		}
		if (x5 == 1) {
			injured(lblNewLabel_10_1_7, composite);
		}
		if (x5 == 0) {
			injured(lblNewLabel_10_1_8, composite);
		}
	}
}
