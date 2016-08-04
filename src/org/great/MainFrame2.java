package org.great;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

//开打界面
public class MainFrame2 extends JFrame {
	public MainFrame2(MainPanel mainPanel) {
		// 先取得屏幕大小
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle bounds = new Rectangle(screenSize);// 尺寸对象
		setBounds(bounds);
		setUndecorated(true); // 去掉窗口
		this.add(new MainPanel2(mainPanel));
		setVisible(true);
		// 添加退出的事件
		this.addKeyListener(new ExitAction());
	}

}
