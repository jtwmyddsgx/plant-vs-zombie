package org.great;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

//�������
public class MainFrame2 extends JFrame {
	public MainFrame2(MainPanel mainPanel) {
		// ��ȡ����Ļ��С
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle bounds = new Rectangle(screenSize);// �ߴ����
		setBounds(bounds);
		setUndecorated(true); // ȥ������
		this.add(new MainPanel2(mainPanel));
		setVisible(true);
		// ����˳����¼�
		this.addKeyListener(new ExitAction());
	}

}
