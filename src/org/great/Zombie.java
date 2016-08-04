package org.great;

import java.awt.Graphics;
import java.awt.font.NumericShaper.Range;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

// ��ʬ��
public class Zombie extends Creature {
	int hp;// ����ֵ
	
	
	// �Ƿ�����ֲ��
	boolean isAttack = false;
	int cont=-1;//����ͼƬ�ı䣬�������Ʊ���
	int zx,zy;
	boolean  isDo = false;
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
		k=k-1;
		BufferedImage[] filesImage1 = new BufferedImage[k];  
		for(int i =0;i<k;i++){
			filesImage1[i]= filesImage[i];
		}
		return filesImage1;
	}
	
	BufferedImage[] zombieImage;//��ʬͼƬ����
	MainPanel2 panel;
	public Zombie(int x, int y, int hp,MainPanel2 panel) {
		// ���췽������ʼ������
		// ÿ����ʬ�����ڽ���ʱ���������Ѫֵ
		this.x = x;
		this.y = y;
		this.hp = hp;
		this.panel = panel;

		// ��ʼ����ʬͼƬ
		switch (hp) {
		case 15:// ��ͨ��ʬ
			zombieImage = getAllImages(zombieImage, "images/putongjiangshi");
			break;
		case 25:// ·�Ͻ�ʬ
			zombieImage =getAllImages(zombieImage, "images/luzhangjiangshi");
			break;
		case 40:// ��Ͱ��ʬ
			zombieImage = getAllImages(zombieImage, "images/zombie_tietongjiangshi");
			break;
		default:
			break;
		}

	}
	
	//��ʬ���ƶ�
	public void move(ArrayList<ZiDan> ziDanPoLie) {
		
		cont++;
		Zombie.this.x--;
		changeZombieImage();
		isAttacked(ziDanPoLie);
		//�ı�ͼƬ
		
	}
	
	//��ʬ��ֲ��
	public void eat(){
		for(int i=0;i<panel.map.creatures.size();i++){
			Creature creature = panel.map.creatures.get(i);
			if(creature instanceof Zombie){
				Zombie zombie = (Zombie) creature;//ǿת
				if(zombie.x>=x&&zombie.y>=(y-70)&&zombie.y<=(y+70)){
					
				}
			}
		}
	}
	public void isAttacked(ArrayList<ZiDan> ziDanPoLie){
		//System.out.println("x"+x);
		//System.out.println("y"+y);
		for(int i=0;i<panel.ziDans.size();i++){
			
			if(panel.ziDans.get(i).x>=x&&panel.ziDans.get(i).y>=(y-70)&&panel.ziDans.get(i).y<=(y+70)){
				System.out.println(y);
				zx=panel.ziDans.get(i).x;
				zy=panel.ziDans.get(i).y;
				ziDanPoLie.add(panel.ziDans.get(i));
				panel.ziDans.remove(i);//�ӵ���ʧ
				for(int j =0;j<panel.map.creatures.size();j++)
				{
					if(panel.map.creatures.get(j).x==x&&panel.map.creatures.get(j).y==y){
						hp-=1;
						if(hp<=0){
						panel.map.creatures.remove(j);
						}
					}
				}
			}
		}
		
		
	}

	public void ziDanXiaoShi(Graphics g){
		for(int i=0;i<30;i++){
			g.drawImage(panel.ziDan2, zx, zy, null);
		}
	}
	private void changeZombieImage() {
		
		//�ı�ͼƬ
		//��·�Ͻ�ʬ������7~15֮�䣬��û������ֲ��
		if(hp<=40&&hp>=0&&!isAttack){
			//����contֵͬʱ�ı�ͼƬ
			changCont();
		}
			
	}

	private void changCont() {//index:��ʾͼƬ���롢ͼƬ��ַ
		
		//index������������
		if(cont>=zombieImage.length)
		{
			cont=0;
		}
		//����ͼƬ
		creature = zombieImage[cont];
		
	}
	
	
	
	
	
	

}
