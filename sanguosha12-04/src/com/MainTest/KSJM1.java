package com.MainTest;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.Biz.SanGuoBiz;
import com.Initial.PlayMusic;
import com.Util.YcUtil;

import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.SWT;

public class KSJM1 {

	protected Shell shell;
	protected boolean bCancel = false;
	Display display = new Display();
	public int i = 0;
	private SanGuoBiz sgb = new SanGuoBiz();
	private PlayMusic pm = new PlayMusic();// 获取播放音乐对象

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			KSJM1 window = new KSJM1();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		/*
		 * if(i ==100) { System.out.println("nihao"); KSJM1.this.shell.dispose(); DJT
		 * djt = new DJT(); djt.open(); }
		 */
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {

		// 开启一个线程用来播放音乐
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					pm.playLogin();
				}
			}
		}).start();
		shell = new Shell(SWT.MIN);
		shell.setImage(SWTResourceManager.getImage(KSJM1.class, "/com/yc/image/06.png"));
		// shell.setBounds(300, 500, 900, 700);
		shell.setSize(852, 473);
		shell.setText("\u767B\u5F55");
		YcUtil.centerWindow(shell);

		ProgressBar progressBar = new ProgressBar(shell, SWT.NONE);
		progressBar.setForeground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		progressBar.setTouchEnabled(true);
		progressBar.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.NORMAL));
		progressBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		progressBar.setBounds(0, 405, 681, 21);
		progressBar.setMaximum(100);
		progressBar.setMinimum(0);
		final int max = progressBar.getMaximum();
		final int min = progressBar.getMinimum();
		// 创建一个线程
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				for (i = min; i < max; i++) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					shell.getDisplay().asyncExec(new Runnable() {

						@Override
						public void run() {
							if (progressBar.isDisposed()) {
								return;
							}
							if (bCancel) {
								progressBar.setSelection(0);
							}
							progressBar.setSelection(progressBar.getSelection() + 1);
							if (progressBar.getSelection() == 100) {
								KSJM1.this.shell.dispose();
								DJT djt = new DJT();
								djt.open();
							}
						}
					});
					if (bCancel) {
						bCancel = false;
						break;
					}
				}
			}
		};
		new Thread(runnable).start();
		// 初始化
		sgb.init();

		shell.pack();
		shell.open();
		while (display.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.isDisposed();
		// shell.close();

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 760, 416);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setImage(SWTResourceManager.getImage(KSJM1.class, "/com/yc/image/logo (5).png"));
		YcUtil.change_sex(lblNewLabel);
		lblNewLabel.setBounds(0, 0, 683, 426);
		
	}
}