package org.great;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

//表示一张卡片
public class Card extends MouseAdapter{
	//图
	BufferedImage cardImage;
	
	//是否可用
	boolean isCard;
	//卡片绘制坐标
	int bx ,by;
	//编号
	int num;
	//当前面板
   MainPanel panel;
	
	//创建该对象需要为某些成员属性赋值的时候：
	public Card(BufferedImage cardImage, boolean isCard, int bx, int by, int num,MainPanel panel) {
		super();
		this.cardImage = cardImage;
		this.isCard = isCard;
		this.bx = bx;
		this.by = by;
		this.num = num;
	    this.panel = panel;
	    panel.addMouseListener(this);//注册当前卡片可点
	}
	
	
	//这张卡片要是点击了，应该做什么事情：
	@Override
	public void mousePressed(MouseEvent e) {

		//判断是否点到当前图片
		//集合访问某个元素采用get(index) 
		if(e.getX()>=bx&&e.getX()<=bx+50&&e.getY()>=by&&e.getY()<=by+70&&panel.cards.get(num-1).isCard){
			
			//加上当前所在被选区里的集合里的卡片的状态时true
			
			//从被选区拿出当前卡片交给参展区集合，并且删掉该卡片在备选区
			panel.cardsp.add(panel.cards.get(num-1));
			panel.cards.remove(num-1);
		}
		//80+i*50, 10
		if(e.getX()>=50+bx&&e.getX()<=bx+100&&e.getY()>=10&&e.getY()<=80){
			
			
		//把卡片从备战区返回
			for(int i=0;i<panel.cards.size();i++){
				if(panel.cardsp.get(num-1).num<panel.cards.get(i).num){
					
					panel.cards.add(i, panel.cardsp.get(num-1));
					break;
				}
			}
			panel.cardsp.remove(num-1);
			
		}
		panel.repaint();//刷新页面
		
	}

	
	

}
