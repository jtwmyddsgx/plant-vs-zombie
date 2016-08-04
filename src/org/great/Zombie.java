package org.great;

import java.awt.Graphics;
import java.awt.font.NumericShaper.Range;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

// 僵尸类
public class Zombie extends Creature {
	int hp;// 生命值
	
	
	// 是否碰到植物
	boolean isAttack = false;
	int cont=-1;//辅助图片改变，辅助控制变量
	int zx,zy;
	boolean  isDo = false;
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
		k=k-1;
		BufferedImage[] filesImage1 = new BufferedImage[k];  
		for(int i =0;i<k;i++){
			filesImage1[i]= filesImage[i];
		}
		return filesImage1;
	}
	
	BufferedImage[] zombieImage;//僵尸图片数组
	MainPanel2 panel;
	public Zombie(int x, int y, int hp,MainPanel2 panel) {
		// 构造方法：初始化数据
		// 每个僵尸诞生在界面时都有坐标和血值
		this.x = x;
		this.y = y;
		this.hp = hp;
		this.panel = panel;

		// 初始化僵尸图片
		switch (hp) {
		case 15:// 普通僵尸
			zombieImage = getAllImages(zombieImage, "images/putongjiangshi");
			break;
		case 25:// 路障僵尸
			zombieImage =getAllImages(zombieImage, "images/luzhangjiangshi");
			break;
		case 40:// 铁桶僵尸
			zombieImage = getAllImages(zombieImage, "images/zombie_tietongjiangshi");
			break;
		default:
			break;
		}

	}
	
	//僵尸的移动
	public void move(ArrayList<ZiDan> ziDanPoLie) {
		
		cont++;
		Zombie.this.x--;
		changeZombieImage();
		isAttacked(ziDanPoLie);
		//改变图片
		
	}
	
	//僵尸吃植物
	public void eat(){
		for(int i=0;i<panel.map.creatures.size();i++){
			Creature creature = panel.map.creatures.get(i);
			if(creature instanceof Zombie){
				Zombie zombie = (Zombie) creature;//强转
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
				panel.ziDans.remove(i);//子弹消失
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
		
		//改变图片
		//当路障僵尸生命在7~15之间，且没有遇到植物
		if(hp<=40&&hp>=0&&!isAttack){
			//更改cont值同时改变图片
			changCont();
		}
			
	}

	private void changCont() {//index:表示图片号码、图片地址
		
		//index不能无限增加
		if(cont>=zombieImage.length)
		{
			cont=0;
		}
		//载入图片
		creature = zombieImage[cont];
		
	}
	
	
	
	
	
	

}
