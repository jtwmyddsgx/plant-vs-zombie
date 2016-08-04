package org.great;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

// CTRL+ SHIFT +F
public class PvsZJpanel extends JPanel implements MouseListener {
	BufferedImage loginImage;// ��������
	//����һ��ͼƬ�������飬��ʾ32��ͼƬ
	BufferedImage[] login  = new BufferedImage[32];
	String loginStr;	//������ص�½���ļ��������������
	int num =1;//���������±�
	BufferedImage startImg; //�����ʼͼƬ
	BufferedImage overImg; //�����ʼ״̬��ͼƬ
	boolean isPress;//Ĭ����false �Ƿ���갴��
	PvsZJframe frame;//��ǰ���ڶ���
	
	// ��һ��ͼƬ
	public PvsZJpanel(PvsZJframe frame) {//�β�
		this.frame = frame;
		File file = new File("images/login.png");// ���ļ�ת�سɶ���
		try {
			loginImage = ImageIO.read(file);
			//ѭ��������½������
			for(int i =0;i<32;i++){
				//�ж�
				if(i<9){
					loginStr = "0"+(i+1);
				}else{
					loginStr = ""+(i+1);//���ַ���+���� ����ַ���
				}
				login[i] = ImageIO.read(new File("images/loading/loading-"+loginStr+".png"));
			}
			startImg = ImageIO.read(new File("images/start_leave.png"));//���ͼƬ
			overImg =  ImageIO.read(new File("images/start_over.png"));//���ͼƬ
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//ע�������
		this.addMouseListener(this);

	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(loginImage, 0, 0, null); // null ��	
	
		if(num<32){
			g.drawImage(login[num], 350, 550, null);//��32��login��
			
			//����һ���ȽϺ�ʱ�Ĳ�������jֵ�ñ仯��һ��
			for(int z=0;z<90000000;z++){}
			num++;//�ı�num
			repaint();//�ػ棬����ִ��һ��paint����
		}else{
			g.drawImage(login[31], 350, 550, null);//�����һ�ţ����Ҳ�����
			for(int z=0;z<999999999;z++){}
			//���ع����ˣ������ʼ����
			if(isPress==false){
				g.drawImage(startImg, 385, 622, null);//û����
			}else{
				g.drawImage(overImg, 385, 622, null);//���µ�
			}
		}
		
		
	}

	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		//����ʱ������갴�����ĳ�������ʱ��ִ��
		// e: ��������
		//�ڱ������������õ���Ĳ�λ�����¼�
		if(e.getX()>=385&&e.getX()<=690&&e.getY()>=622&&e.getY()<=654){
			//�����λ����
			isPress = true;
			repaint();//�мǣ�����ˢ��ҳ��	
		}
		
		
	}


	public void mouseReleased(MouseEvent e) {
		isPress = false;
		repaint();
		
		//����һ����ʱ�Ĳ�����������UI�ı仯
		for(int i=0;i<999999999;i++){}
		
		//�ѵ�ǰ�������ص�������
		if(num>=32){//�Ͻ����ȴ�loading�������	
			frame.setVisible(false);
			frame.dispose();
			new MainFFrame();
		}
		
	}
	
	

}
