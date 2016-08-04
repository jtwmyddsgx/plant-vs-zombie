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
	
	BufferedImage[] filesImage ;//�洢�ļ����µ�ͼƬ
	public BufferedImage[] getAllImages(BufferedImage[] fileImage,String imagesFile){//�����ļ���������ͼƬ
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
	// ��һ��������
	MainPanel mainPanel;
	// 3�仰
	BufferedImage[] startReadys = new BufferedImage[3];
	int index = 0;// �������δ�ӡ3�仰
	Graphics pen;//����
	TileMap map;// ��Ա��
	public String zhiWuName;
	
	public BufferedImage[] xiangRiKuiImages;//���տ�
	public BufferedImage[] sheShouImage;    //����ͼƬ
	public BufferedImage[] tuDouImage;      //����ͼƬ
	public BufferedImage[] currentImage;	//��ǰ���ŵĶ�����
	public int animaNum = 0;				//��ǰ���ŵı��
	public BufferedImage mouseImage;		//�����ѡ�е�ͼ
	public int mouseX,mouseY;				//����ƶ���λ��
	public int zha =0;//  ը
	
	public BufferedImage ziDan ;//�ӵ�ͼ
	public BufferedImage ziDan2 ;//�ӵ�����ͼ
	public ArrayList<ZiDan> ziDans = new ArrayList<ZiDan>();//װ�ӵ��ļ���
	public ArrayList<ZiDan> ziDanPoLie = new ArrayList<ZiDan>(); //�ӵ����ѵļ���
	public boolean[][] position = new boolean[5][9];//���ӣ��ж��Ƿ���ֲ��
	
	public MainPanel2(MainPanel mainPanel) {
		addMouseMotionListener(new mouseMoveListener(this));//�������ƶ�����
		this.mainPanel = mainPanel;
		//�������տ�����
		xiangRiKuiImages = getAllImages(xiangRiKuiImages, "images/xiangrikui");
		//�������ֶ���
		sheShouImage = getAllImages(sheShouImage, "images/plant_wandousheshou");
		//������������
		tuDouImage = getAllImages(tuDouImage, "images/jiangguo");
		//�����ӵ�ͼƬ
		try {
			ziDan = ImageIO.read(new File("images/bullet_01.png"));
			ziDan2 = ImageIO.read(new File("images/PeaBulletHit.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//���뿨Ƭ�ƶ���ͼ
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
		// ���ó�ʼ����ʬ
		initZombie();

		// ͨ���������߳���������仯,ǰ����������ǰ3�仰�Ѿ�����
		addMainThread();
		
		//��Ӵ���ֲ��ļ�����
		for (int i = 0; i < mainPanel.cardsp.size(); i++) 
		{
			addMouseListener(new MoveCardListener(this,80+i*50,8,i));//�����꿨Ƭ������
		}
		//���ս������ļ�����
		addMouseListener(this);
	}

	private void initZombie() {
		// ����5����ʬ
		for (int i = 0; i < 5; i++) {
			Zombie zombie = new Zombie(1100, 80 + 142 * i, 40,this);
			map.addCreature(zombie);
		}
	}

	// ��װһ�����߳�
	private void addMainThread() {
		// ����һ���߳�
		new Thread() {
			public void run() {
				//�ý�ʬһֱ��
				while(true){
					//��ǰ����Ա�������н�ʬ������
					
					//3�仰����
					if(index==3){
					for(int i =0;i<map.creatures.size();i++){
						Creature creature = map.creatures.get(i);
						//���������ת��Ϊ�����������ת��ʹ��ǿ������ת�����п��ܳ���һ���쳣�����ʹ���
							if(creature instanceof Zombie){//�����ǰcreature������Zombie���ͣ���ô����ֵΪtrue
								Zombie zombie = (Zombie) creature;//ǿת
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
		arg0.drawImage(mainPanel.background, -380, 0, 2500, 900, null);// ʹ����һ��������ı���ͼ�滭
		arg0.drawImage(mainPanel.seedBank, 0, 0, null);
		// ��ֲ��
		for (int i = 0; i < mainPanel.cardsp.size(); i++) {
			arg0.drawImage(mainPanel.cardsp.get(i).cardImage, 80 + i * 50, 8,50, 70, null);
		}
		
		if (index < startReadys.length) {
			arg0.drawImage(startReadys[index], 555, 370, null);
			index++;
			repaint();
		} else {
			// ���λ�����Ա
			for (int i = 0; i < map.creatures.size(); i++) {
				map.creatures.get(i).draw(arg0);// ��Ա�Լ�ȥ��
			}
		}
		
		//������ѡ�е�ͼƬ
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
		//���ӵ�
		
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
		//ս������
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
