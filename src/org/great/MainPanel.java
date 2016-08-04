package org.great;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements MouseListener {
	Image background;// ����ͼƬ
	BufferedImage davids[]; // ����
	String str;// ����ͼƬ���ֵĺϳ�
	// Ϊ�������ϿΣ����·��ʡ�����һ��������Ϸ��ʼ��booleanֵ
	boolean isStart = false;// �Ƿ�ʼս��
	// ����һ�������ļ���
	int count = 0;
	// װ�����Ļ�
	String[] spStr = {"�ۣ��öཀྵʬ","�öཀྵʬŶ��","�öཀྵʬŶŶŶŶ!" };
	// ÿ��˵�Ļ�
	String talk = "";
	int bgX;// ��������ı�ֵ
	int sbX = -446;// ս��������ı�ֵ

	// ���2�����ϣ�׼����ֲ�� ��ѡ��ֲ��
	Vector<Card> cards = new Vector<Card>();// <>���� ��ѡ��
	Vector<Card> cardsp = new Vector<Card>();// ��ս��
	BufferedImage card;// ����ʹ�õ�ֲ��ͼƬ
	BufferedImage dcard;// ������ʹ�õ�ֲ��ͼƬ
	// ���������
	BufferedImage seedBank, seedChA, seedChB, seedPacket; // ���һ����ֲ�￨Ƭ�ı���
	
	// �ӽ�ʬ
	BufferedImage buck1,buck2,buck3;
	//��ӵ�ǰ���ڶ���
	MainFrame frame ;
	
	
	public MainPanel(MainFrame frame) {
		this.frame = frame;
		background = new ImageIcon("images/background1.png").getImage();
		// ��ʼ����������
		davids = new BufferedImage[13];
		for (int i = 0; i < 13; i++) {
			// ���ͼƬ��������
			if (i < 9) {
				str = "0" + (i + 1);
			} else {
				str = "" + (i + 1);
			}
			try {
				davids[i] = ImageIO.read(new File("images/davied/david-" + str
						+ ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// ��ʼ�� ֲ��ս����
		try {
			seedBank = ImageIO.read(new File("images/cardImage/SeedBank.png"));
			seedChA = ImageIO.read(new File(
					"images/cardImage/SeedChooser_Background.png"));
			seedChB = ImageIO.read(new File(
					"images/cardImage/SeedChooser_Button_Disabled.png"));
			seedPacket = ImageIO.read(new File(
					"images/cardImage/SeedPacketSilhouette.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ͨ��ѭ���ӱ�ѡ����ֲ��,��8��ֲ�ֻ����ʱʹ��3��ͼƬ �����տ� �㶹���� ���
		for (int j = 0; j <48; j++) {
			if(j<9){
				str = "0" + (j + 1);
			}else{
				str=""+(j+1);
			}
			try {
				card = ImageIO
						.read(new File("images/card/card_" + str + ".png"));// ����ʹ��ͼƬ
				dcard = ImageIO.read(new File("images/darkCard/card_" + str
						+ ".png"));// ������ʹ��ͼƬ
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Card c;
			if (j < 3) {
				// ��ӿ���ʹ�õĿ�Ƭ
				c = new Card(card, true, 20 + j * 50, 125, j + 1, this);

				cards.add(c);
			} else {
				c = new Card(dcard, false, 20 + j * 50, 125, j + 1, this);
				cards.add(c);
			}
			
		}
		//��ʼ����ʬͼƬ
		try {
			//��Ͱ
			buck1 = ImageIO.read(new File("images/zombie_tietongjiangshi/zombie_tietongjiangshi_01.png"));
			//·��
			buck2 = ImageIO.read(new File("images/luzhangjiangshi/zombie_luzhangjiangshi_01.png"));
			//��ͨ
			buck3 = ImageIO.read(new File("images/putongjiangshi/zombie_putongjiangshi_01.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ע�������
		addMouseListener(this);
	}

	@Override
	public void paint(Graphics arg0) {
		super.paint(arg0);
		arg0.drawImage(background, bgX, 0, 1866, 900, null);
		// ������
		if (!isStart) {
			// �������
			arg0.setColor(Color.WHITE);
			arg0.fillRect(400, 135, 200, 60);// ������
			if (count < 13) {
				// ��ʱ�Ĳ���
				for (int i = 0; i < 99999999; i++) {
				}
				arg0.drawImage(davids[count], 20, 50, null);// count���ֻ����12
				// �廰

				switch (count) {
				case 2:
					talk = spStr[0];
					break;
				case 6:
					talk = spStr[1];
					break;
				case 10:
					talk = spStr[2];
					break;
				}
				Font font = new Font("����", Font.BOLD, 25);// �������� ������ �����С
				arg0.setFont(font);
				arg0.setColor(Color.RED);
				arg0.drawString(talk, 410, 170);
				count++;
				repaint();// ˢ��ҳ��
			}
			if (count == 13) {
				count = 12;
			}
		}// ս����ʼ��
		if (bgX == -352) {
			// ������������ϵ�ʱ��
			arg0.drawImage(seedBank, sbX, 0, null);
			arg0.drawImage(seedChA, sbX + 10, 85, 420, 513, null);
			arg0.drawImage(seedChB, sbX + 135, 548, null);
			// ѡ�����ֲ���
			Font font = new Font("����", Font.BOLD, 22);
			arg0.setFont(font);
			// Color c = new Color();//RGB �Զ�����ɫ
			arg0.setColor(Color.RED);
			arg0.drawString("��ʼս��", sbX + 165, 577);
			arg0.drawString("ѡ�����ֲ���", sbX + 140, 108);

			// ѭ������ѡ��ֲ��
			for (int i = 0; i < cards.size(); i++) {
				arg0.drawImage(seedPacket,sbX+20+i*50,125,null);//��0����
				arg0.drawImage(cards.get(i).cardImage,sbX+20+(i%8)*50,(i/8)*70+125,50,70,null);
			   }
			//ѡ����
			for(int i=0;i<7;i++){
				arg0.drawImage(seedPacket,sbX+80+i*50,10,null);//������
			}
			
			//��ѡ�к�ļ���
			for(int i=0;i<cardsp.size();i++){
				arg0.drawImage(cardsp.get(i).cardImage, sbX+80+i*50, 10, 50,70, null);
			}
			//����ʬ
			arg0.drawImage(buck1, bgX+1400, 200, null);
			arg0.drawImage(buck2, bgX+1450, 300, null);
			arg0.drawImage(buck3, bgX+1500, 400, null);
			arg0.drawImage(buck1, bgX+1600, 360, null);
			arg0.drawImage(buck2, bgX+1550, 500, null);
			
		}
	}

	public void mouseClicked(MouseEvent e) {
		// �����
		isStart = true;
		// ����һ��������ʼ���ر����˶�
		startBackGround();
	}

	private void startBackGround() {
		// ����һ���̣߳��������Ʊ����Ĺ���
		new Thread() {
			// �ڵ�ǰ�����ﹹ���������
			// ��дrun�����������߳����У���ִ��1�θ÷���
			public void run() {
				// һֱִ�� ��ѭ��
				for (;;) {
					// ���ϵ���
					if (bgX >= -352) {
						bgX--;
					}
					// Ϊ�˰�ȫ����
					if (bgX <= -352) {
						bgX = -352;// ��ʾ����������
						if (sbX != 0) {
							sbX++;
						}
					}
					// �̶�ÿ��2���붯һ��
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					repaint();
				}
			}
		}.start();
		// �߳���Ҫ������start����
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		//��ʼս��
		if(sbX==0&&cardsp.size()>0){//Ҫ����ѡ��һ��ֲ����ܽ�����Ϸ
			//��ʾս����������������
			if(e.getX()>138&&e.getX()<=285&&e.getY()>550&&e.getY()<585){
				new MainFrame2(this);//new ���������,���ڿ���Ҫ�õ���ǰ������
				frame.setVisible(false);
				frame.dispose();
			}
		}
	

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
