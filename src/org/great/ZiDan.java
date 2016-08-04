package org.great;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class ZiDan {
	BufferedImage ziDan;
	BufferedImage ziDanPoLie;
	MainPanel2 panel;
	int x,y;
	int xx,yy;
	public ZiDan(int x,int y ,MainPanel2 panel){
		try {
			ziDan = ImageIO.read(new File("images/bullet_01.png"));//ÔØÈë×Óµ¯Í¼Æ¬
			ziDanPoLie = ImageIO.read(new File("images/PeaBulletHit.png"));//ÔØÈë×Óµ¯ÆÆÁÑÍ¼Æ¬
			//PeaBulletHit.png
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.panel = panel;
		xx=x;
		yy=y;
		
		this.x = x;
		this.y = y;
		
	}
	public int cd=0;//CD
	boolean canZidan = false;//ÅÐ¶Ï×Óµ¯ÊÇ·ñ¸´ÖÆ¹ý
	
	public void zhiDanPoLieDongHua(Graphics a){
		a.drawImage(ziDanPoLie, x, y, null);
		
	}
	
	public void zhiDanAnima(Graphics a){
		
		x++;

		
		a.drawImage(ziDan, x+40, y+10, null);
		
		if(cd>150&&!canZidan){
			canZidan=true;
			ZiDan ziD= new ZiDan(xx, yy, panel);
			panel.ziDans.add(ziD);
			cd=0;
		}
	}
	
}
