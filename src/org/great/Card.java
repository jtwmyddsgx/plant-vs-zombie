package org.great;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

//��ʾһ�ſ�Ƭ
public class Card extends MouseAdapter{
	//ͼ
	BufferedImage cardImage;
	
	//�Ƿ����
	boolean isCard;
	//��Ƭ��������
	int bx ,by;
	//���
	int num;
	//��ǰ���
   MainPanel panel;
	
	//�����ö�����ҪΪĳЩ��Ա���Ը�ֵ��ʱ��
	public Card(BufferedImage cardImage, boolean isCard, int bx, int by, int num,MainPanel panel) {
		super();
		this.cardImage = cardImage;
		this.isCard = isCard;
		this.bx = bx;
		this.by = by;
		this.num = num;
	    this.panel = panel;
	    panel.addMouseListener(this);//ע�ᵱǰ��Ƭ�ɵ�
	}
	
	
	//���ſ�ƬҪ�ǵ���ˣ�Ӧ����ʲô���飺
	@Override
	public void mousePressed(MouseEvent e) {

		//�ж��Ƿ�㵽��ǰͼƬ
		//���Ϸ���ĳ��Ԫ�ز���get(index) 
		if(e.getX()>=bx&&e.getX()<=bx+50&&e.getY()>=by&&e.getY()<=by+70&&panel.cards.get(num-1).isCard){
			
			//���ϵ�ǰ���ڱ�ѡ����ļ�����Ŀ�Ƭ��״̬ʱtrue
			
			//�ӱ�ѡ���ó���ǰ��Ƭ������չ�����ϣ�����ɾ���ÿ�Ƭ�ڱ�ѡ��
			panel.cardsp.add(panel.cards.get(num-1));
			panel.cards.remove(num-1);
		}
		//80+i*50, 10
		if(e.getX()>=50+bx&&e.getX()<=bx+100&&e.getY()>=10&&e.getY()<=80){
			
			
		//�ѿ�Ƭ�ӱ�ս������
			for(int i=0;i<panel.cards.size();i++){
				if(panel.cardsp.get(num-1).num<panel.cards.get(i).num){
					
					panel.cards.add(i, panel.cardsp.get(num-1));
					break;
				}
			}
			panel.cardsp.remove(num-1);
			
		}
		panel.repaint();//ˢ��ҳ��
		
	}

	
	

}
