package org.great;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class MoveCardListener extends MouseAdapter {
	int mouseX,mouseY,mouseXb,mouseYb,i;
	MainPanel2 mainPanel2;
	
	public MoveCardListener(MainPanel2 mainPanel2,int x,int y ,int i){
		this.i = i;
		mouseX = x;
		mouseY = y;
		mouseXb = mouseX+50;
		mouseYb= mouseY+70;
		
		this.mainPanel2 = mainPanel2;
	}
	boolean isGetPlant = false;
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getX()>=mouseX&&e.getX()<=mouseXb&&e.getY()>=mouseY&&e.getY()<mouseYb){
			isGetPlant = true;
			//鼠标上的图片根据植物栏的图片显示
			mainPanel2.mouseImage = mainPanel2.moveCardImages[(mainPanel2.mainPanel.cardsp.get(i).num)-1];
			
			switch ((mainPanel2.mainPanel.cardsp.get(i).num)) {
			case 1:
				mainPanel2.currentImage = mainPanel2.xiangRiKuiImages;
				mainPanel2.zhiWuName ="1";
				break;
			case 2:
				mainPanel2.currentImage = mainPanel2.sheShouImage;
				mainPanel2.zhiWuName ="2";
				break;
			case 3:
				mainPanel2.currentImage = mainPanel2.tuDouImage;
				mainPanel2.zhiWuName ="3";
				break;
			default:
				break;
			}
			mainPanel2.animaNum =0;
			mainPanel2.mouseX=e.getX()-25;
			mainPanel2.mouseY= e.getY()-30;
			mainPanel2.repaint();
			
			
		}
		
	}
	

}
