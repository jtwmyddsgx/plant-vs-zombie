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
	Image background;// 背景图片
	BufferedImage davids[]; // 大卫
	String str;// 用于图片名字的合成
	// 为了明天上课，埋下伏笔。建立一个控制游戏开始的boolean值
	boolean isStart = false;// 是否开始战斗
	// 定义一个大卫的计数
	int count = 0;
	// 装大卫的话
	String[] spStr = {"哇，好多僵尸","好多僵尸哦！","好多僵尸哦哦哦哦!" };
	// 每次说的话
	String talk = "";
	int bgX;// 背景坐标改变值
	int sbX = -446;// 战斗条坐标改变值

	// 添加2个集合：准备的植物 备选的植物
	Vector<Card> cards = new Vector<Card>();// <>泛型 备选区
	Vector<Card> cardsp = new Vector<Card>();// 参战区
	BufferedImage card;// 可以使用的植物图片
	BufferedImage dcard;// 不可以使用的植物图片
	// 添加种子栏
	BufferedImage seedBank, seedChA, seedChB, seedPacket; // 最后一个是植物卡片的背景
	
	// 加僵尸
	BufferedImage buck1,buck2,buck3;
	//添加当前窗口对象
	MainFrame frame ;
	
	
	public MainPanel(MainFrame frame) {
		this.frame = frame;
		background = new ImageIcon("images/background1.png").getImage();
		// 初始化大卫数组
		davids = new BufferedImage[13];
		for (int i = 0; i < 13; i++) {
			// 解决图片名称问题
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
		// 初始化 植物战斗条
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

		// 通过循环加备选区的植物,加8个植物，只能暂时使用3张图片 ：向日葵 豌豆射手 坚果
		for (int j = 0; j <48; j++) {
			if(j<9){
				str = "0" + (j + 1);
			}else{
				str=""+(j+1);
			}
			try {
				card = ImageIO
						.read(new File("images/card/card_" + str + ".png"));// 可以使用图片
				dcard = ImageIO.read(new File("images/darkCard/card_" + str
						+ ".png"));// 不可以使用图片
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Card c;
			if (j < 3) {
				// 添加可以使用的卡片
				c = new Card(card, true, 20 + j * 50, 125, j + 1, this);

				cards.add(c);
			} else {
				c = new Card(dcard, false, 20 + j * 50, 125, j + 1, this);
				cards.add(c);
			}
			
		}
		//初始化僵尸图片
		try {
			//铁桶
			buck1 = ImageIO.read(new File("images/zombie_tietongjiangshi/zombie_tietongjiangshi_01.png"));
			//路障
			buck2 = ImageIO.read(new File("images/luzhangjiangshi/zombie_luzhangjiangshi_01.png"));
			//普通
			buck3 = ImageIO.read(new File("images/putongjiangshi/zombie_putongjiangshi_01.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 注册监听器
		addMouseListener(this);
	}

	@Override
	public void paint(Graphics arg0) {
		super.paint(arg0);
		arg0.drawImage(background, bgX, 0, 1866, 900, null);
		// 画大卫
		if (!isStart) {
			// 画聊天框
			arg0.setColor(Color.WHITE);
			arg0.fillRect(400, 135, 200, 60);// 长方形
			if (count < 13) {
				// 耗时的操作
				for (int i = 0; i < 99999999; i++) {
				}
				arg0.drawImage(davids[count], 20, 50, null);// count最大只能是12
				// 插话

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
				Font font = new Font("黑体", Font.BOLD, 25);// 字体类型 字体风格 字体大小
				arg0.setFont(font);
				arg0.setColor(Color.RED);
				arg0.drawString(talk, 410, 170);
				count++;
				repaint();// 刷新页面
			}
			if (count == 13) {
				count = 12;
			}
		}// 战斗开始了
		if (bgX == -352) {
			// 当背景滚动完毕的时候
			arg0.drawImage(seedBank, sbX, 0, null);
			arg0.drawImage(seedChA, sbX + 10, 85, 420, 513, null);
			arg0.drawImage(seedChB, sbX + 135, 548, null);
			// 选择你的植物吧
			Font font = new Font("黑体", Font.BOLD, 22);
			arg0.setFont(font);
			// Color c = new Color();//RGB 自定义颜色
			arg0.setColor(Color.RED);
			arg0.drawString("开始战斗", sbX + 165, 577);
			arg0.drawString("选择你的植物吧", sbX + 140, 108);

			// 循环画备选区植物
			for (int i = 0; i < cards.size(); i++) {
				arg0.drawImage(seedPacket,sbX+20+i*50,125,null);//卡0背景
				arg0.drawImage(cards.get(i).cardImage,sbX+20+(i%8)*50,(i/8)*70+125,50,70,null);
			   }
			//选中区
			for(int i=0;i<7;i++){
				arg0.drawImage(seedPacket,sbX+80+i*50,10,null);//卡背景
			}
			
			//画选中后的集合
			for(int i=0;i<cardsp.size();i++){
				arg0.drawImage(cardsp.get(i).cardImage, sbX+80+i*50, 10, 50,70, null);
			}
			//画僵尸
			arg0.drawImage(buck1, bgX+1400, 200, null);
			arg0.drawImage(buck2, bgX+1450, 300, null);
			arg0.drawImage(buck3, bgX+1500, 400, null);
			arg0.drawImage(buck1, bgX+1600, 360, null);
			arg0.drawImage(buck2, bgX+1550, 500, null);
			
		}
	}

	public void mouseClicked(MouseEvent e) {
		// 鼠标点击
		isStart = true;
		// 调用一个方法开始加载背景运动
		startBackGround();
	}

	private void startBackGround() {
		// 创建一个线程，单独控制背景的滚动
		new Thread() {
			// 在当前对象里构建类的内容
			// 重写run方法：调用线程运行，会执行1次该方法
			public void run() {
				// 一直执行 死循环
				for (;;) {
					// 不断倒退
					if (bgX >= -352) {
						bgX--;
					}
					// 为了安全考虑
					if (bgX <= -352) {
						bgX = -352;// 表示背景滚完了
						if (sbX != 0) {
							sbX++;
						}
					}
					// 固定每隔2毫秒动一下
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
		// 线程需要开启，start方法
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		//开始战斗
		if(sbX==0&&cardsp.size()>0){//要至少选择一个植物才能进行游戏
			//表示战斗条滚动出来结束
			if(e.getX()>138&&e.getX()<=285&&e.getY()>550&&e.getY()<585){
				new MainFrame2(this);//new 出开打界面,由于可能要用到当前面板里的
				frame.setVisible(false);
				frame.dispose();
			}
		}
	

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
