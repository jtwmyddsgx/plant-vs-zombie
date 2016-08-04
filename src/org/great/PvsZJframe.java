package org.great;

import javax.swing.JFrame;

//为软件提供窗口
public class PvsZJframe extends JFrame {
	
	public PvsZJframe() {
		//初始化设定
		this.setBounds(200, 10, 1024, 720);
		//没有标题的窗体
		this.setUndecorated(true);
		//加载面板
		PvsZJpanel panel = new PvsZJpanel(this);
		this.add(panel);
		//显示
		this.setVisible(true);
	}
	
	
	

}
