package org.great;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame  extends JFrame{
	
	public MainFrame() {
		//��ȡ����Ļ��С
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle bounds  = new Rectangle(screenSize);//�ߴ����
		setBounds(bounds);
		setUndecorated(true);		//ȥ������
		add(new MainPanel(this));// �ѵڶ������ڶ��󴫽��ڶ���������
		setVisible(true);
		//����˳����¼�
		this.addKeyListener( new ExitAction());
		
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
	}
	

	
	

}
