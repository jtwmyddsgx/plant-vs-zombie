package org.great;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class MainPanel2 extends JPanel implements MouseListener{
	
	BufferedImage[] filesImage ;//存储文件夹下的图片
	public BufferedImage[] getAllImages(BufferedImage[] fileImage,String imagesFile){//载入文件夹下所有图片
		File file = new File(imagesFile);
		File[] files = file.listFiles();
		filesImage = new BufferedImage[files.length];
		int k =0;
		for(int i =0;i<files.length;i++){
			if(files[i].getName().endsWith(".png")){
			try {
				filesImage[k]=ImageIO.read(files[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			k++;
			}
		}
		BufferedImage [] newBufferedImage = new BufferedImage[k];
		for(int i=0;i<k;i++)
		{
			newBufferedImage[i]= filesImage[i];
		}
		return newBufferedImage;
	}
	//******************
	
	public BufferedImage [] moveCardImages;
	// 上一个面板对象
	MainPanel mainPanel;
	// 3句话
	BufferedImage[] startReadys = new BufferedImage[3];
	int index = 0;// 控制依次打印3句话
	Graphics pen;//画笔
	TileMap map;// 演员表
	public String zhiWuName;
	
	public BufferedImage[] xiangRiKuiImages;//向日葵
	public BufferedImage[] sheShouImage;    //射手图片
	public BufferedImage[] tuDouImage;      //土豆图片
	public BufferedImage[] currentImage;	//当前播放的动画集
	public int animaNum = 0;				//当前播放的编号
	public BufferedImage mouseImage;		//被鼠标选中的图
	public int mouseX,mouseY;				//鼠标移动的位置
	public int zha =0;//  炸
	
	public BufferedImage ziDan ;//子弹图
	public BufferedImage ziDan2 ;//子弹破裂图
	public ArrayList<ZiDan> ziDans = new ArrayList<ZiDan>();//装子弹的集合
	public ArrayList<ZiDan> ziDanPoLie = new ArrayList<ZiDan>(); //子弹破裂的集合
	public boolean[][] position = new boolean[5][9];//格子：判断是否有植物
	
	public MainPanel2(MainPanel mainPanel) {
		addMouseMotionListener(new mouseMoveListener(this));//添加鼠标移动监听
		this.mainPanel = mainPanel;
		//载入向日葵动画
		xiangRiKuiImages = getAllImages(xiangRiKuiImages, "images/xiangrikui");
		//载入射手动画
		sheShouImage = getAllImages(sheShouImage, "images/plant_wandousheshou");
		//载入土豆动画
		tuDouImage = getAllImages(tuDouImage, "images/jiangguo");
		//载入子弹图片
		try {
			ziDan = ImageIO.read(new File("images/bullet_01.png"));
			ziDan2 = ImageIO.read(new File("images/PeaBulletHit.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//载入卡片移动的图
		moveCardImages = getAllImages(moveCardImages,"images/moveCard" );
		for (int i = 0; i < startReadys.length; i++) {
			try {
				startReadys[i] = ImageIO.read(new File("images/word/StartReady"
						+ (i + 1) + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map = new TileMap();
		// 调用初始化僵尸
		initZombie();

		// 通过调用主线程启动界面变化,前提条件：当前3句话已经结束
		addMainThread();
		
		//添加创建植物的监听器
		for (int i = 0; i < mainPanel.cardsp.size(); i++) 
		{
			addMouseListener(new MoveCardListener(this,80+i*50,8,i));//添加鼠标卡片监听器
		}
		//添加战斗区域的监听器
		addMouseListener(this);
	}

	private void initZombie() {
		// 创建5个僵尸
		for (int i = 0; i < 5; i++) {
			Zombie zombie = new Zombie(1100, 80 + 142 * i, 40,this);
			map.addCreature(zombie);
		}
	}

	// 封装一个主线程
	private void addMainThread() {
		// 创建一个线程
		new Thread() {
			public void run() {
				//让僵尸一直动
				while(true){
					//当前的演员表里所有僵尸动起来
					
					//3句话结束
					if(index==3){
					for(int i =0;i<map.creatures.size();i++){
						Creature creature = map.creatures.get(i);
						//将父类对象转化为子类对象，向下转型使用强制类型转换，有可能出现一个异常，类型错误
							if(creature instanceof Zombie){//如果当前creature对象是Zombie类型，那么返回值为true
								Zombie zombie = (Zombie) creature;//强转
								zombie.move(ziDanPoLie);
							}else if (creature instanceof Zhiwu) {
								Zhiwu zhiwu = (Zhiwu)creature;
								zhiwu.move();
							}
					}
					repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				}
				
			};
		}.start();

	}
	
	@Override
	public void paint(Graphics arg0) {
		super.paint(arg0);
		pen = arg0;
		arg0.drawImage(mainPanel.background, -380, 0, 2500, 900, null);// 使用上一个面板对象的背景图绘画
		arg0.drawImage(mainPanel.seedBank, 0, 0, null);
		// 画植物
		for (int i = 0; i < mainPanel.cardsp.size(); i++) {
			arg0.drawImage(mainPanel.cardsp.get(i).cardImage, 80 + i * 50, 8,50, 70, null);
		}
		
		if (index < startReadys.length) {
			arg0.drawImage(startReadys[index], 555, 370, null);
			index++;
			repaint();
		} else {
			// 依次画出演员
			for (int i = 0; i < map.creatures.size(); i++) {
				map.creatures.get(i).draw(arg0);// 演员自己去画
			}
		}
		
		//画出被选中的图片
		arg0.drawImage(mouseImage, mouseX, mouseY, null);
		
		if(mouseImage!=null)
		{
			if(animaNum==currentImage.length)
				animaNum=0;
			mouseImage = currentImage[animaNum];
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			animaNum++;
			repaint();
		}
		//画子弹
		
		for(int i =0;i<ziDans.size();i++){
			ziDans.get(i).cd++;
			ziDans.get(i).zhiDanAnima(arg0);
			
		}
		
		for(int i =0;i<ziDanPoLie.size();i++){
			ziDanPoLie.get(i).zhiDanPoLieDongHua(arg0);
			zha ++;
			if(zha >= 100){
				ziDanPoLie.remove(i);
				zha = 0;
			}
		}
		
		repaint();
		
	}
	//public boolean isW
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
		// TODO Auto-generated method stub
		//战斗区域
		if(e.getX()>80 && e.getX()<1340&&e.getY()>130&&e.getY()<850 && mouseImage!=null)
		{
			int i ,j;
			for (i = 0; i < 5; i++) {
				if(e.getY()-(130+144*i)<144)
				{
					break;
				}
			}
			
			for (j =0; j < 9 ; j++)
			{
				if(e.getX()-(80+140*j)<140)
				{
					break;
				}
			}
			
			if (position[i][j] == false) {
				position[i][j] = true;
				Zhiwu mZhiWu = new  Zhiwu(mouseImage,90 + 145*j,135+145*i,15,this,zhiWuName);
				map.addCreature(mZhiWu);
				mouseImage =null;
			}
			
		}
	}

	public void mouseReleased(MouseEvent e) {

	}
	
	

}
