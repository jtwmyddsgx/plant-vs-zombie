package org.great;

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Zhiwu extends Creature{
	int hp;
	public String zhiWuName;//÷≤ŒÔ√˚◊÷
	int count =0 ;  //∏®÷˙∏ƒ±‰Õº∆¨
	BufferedImage[] MyImage;
	BufferedImage wanDou;
	
	MainPanel2 Panel2;
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(creature, x, y, 80,80,null);
	}
	
	public Zhiwu(BufferedImage image , int x,int y,int hp,MainPanel2 Panel2,String zhiWuNmae) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.creature = image;
		this.hp = hp;
		this.Panel2 = Panel2;
		MyImage = Panel2.currentImage;
		this.zhiWuName = zhiWuNmae;
		shoot();
	}
	
	public void move() {
		// TODO Auto-generated method stub
		this.creature = MyImage[count];
		count++;
		if(count>MyImage.length-1)
		{
			count =0;
		}
	}
	public int cd=0;
	public void shoot(){
		try {
			wanDou =ImageIO.read(new File("images/moveCard/card_02.png"));//‘ÿ»ÎÕ„∂πÕº∆¨
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(zhiWuName == "2"){
			Panel2.ziDans.add(new ZiDan(x, y, Panel2));
			
			
		}
	}
	
	
}
