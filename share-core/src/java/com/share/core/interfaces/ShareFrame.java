package com.share.core.interfaces;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ShareFrame extends JFrame {
	private static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	protected JPanel mainFrame = null;

	/**
	 * 构造函数
	 * @throws Exception 
	 */
	public ShareFrame() throws Exception {
		init(1000, 700);
	}

	/**
	 * 窗体初始化
	 * @author ruan
	 * @param width 宽
	 * @param height 高
	 * @throws Exception 
	 */
	private void init(int width, int height) throws Exception {
		// 使窗体居中
		setBounds((SCREEN_WIDTH - width) / 2, (SCREEN_HEIGHT - height - 60) / 2, width, height);

		// 禁止改变窗体大小
		setResizable(false);		

		// 点击关闭按钮关闭程序
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainFrame = new JPanel();

		mainFrame.setLayout(null);
	} 
	
	/**
	 * 把所有控件添加到主面板
	 * @author ruan
	 */
	protected void addPanel(){
		add(mainFrame);
		setVisible(true);
	}
	
	/**
	 * 弹出报错框
	 * @param str
	 */
	protected void error(String str) {
		JOptionPane.showMessageDialog(null, str, "出错啦！T.T", JOptionPane.ERROR_MESSAGE);
	}
}
