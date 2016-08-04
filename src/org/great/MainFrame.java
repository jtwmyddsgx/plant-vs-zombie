package org.great;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame  extends JFrame{
	
	public MainFrame() {
		//先取得屏幕大小
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle bounds  = new Rectangle(screenSize);//尺寸对象
		setBounds(bounds);
		setUndecorated(true);		//去掉窗口
		add(new MainPanel(this));// 把第二个窗口对象传进第二个面板对象
		setVisible(true);
		//添加退出的事件
		this.addKeyListener( new ExitAction());
		
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
	}
	

	
	

}
