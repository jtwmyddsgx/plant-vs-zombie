package org.great;

import javax.swing.JFrame;

//Ϊ����ṩ����
public class PvsZJframe extends JFrame {
	
	public PvsZJframe() {
		//��ʼ���趨
		this.setBounds(200, 10, 1024, 720);
		//û�б���Ĵ���
		this.setUndecorated(true);
		//�������
		PvsZJpanel panel = new PvsZJpanel(this);
		this.add(panel);
		//��ʾ
		this.setVisible(true);
	}
	
	
	

}
