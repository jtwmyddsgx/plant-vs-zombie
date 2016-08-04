package org.great;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

// CTRL+ SHIFT +F
public class PvsZJpanel extends JPanel implements MouseListener {
	BufferedImage loginImage;// 申明变量
	//创建一个图片对象数组，表示32个图片
	BufferedImage[] login  = new BufferedImage[32];
	String loginStr;	//处理加载登陆条文件名里的数字问题
	int num =1;//控制数组下标
	BufferedImage startImg; //点击开始图片
	BufferedImage overImg; //点击开始状态的图片
	boolean isPress;//默认是false 是否鼠标按下
	PvsZJframe frame;//当前窗口对象
	
	// 画一张图片
	public PvsZJpanel(PvsZJframe frame) {//形参
		this.frame = frame;
		File file = new File("images/login.png");// 找文件转载成对象
		try {
			loginImage = ImageIO.read(file);
			//循环创建登陆条数组
			for(int i =0;i<32;i++){
				//判断
				if(i<9){
					loginStr = "0"+(i+1);
				}else{
					loginStr = ""+(i+1);//空字符串+数组 变成字符串
				}
				login[i] = ImageIO.read(new File("images/loading/loading-"+loginStr+".png"));
			}
			startImg = ImageIO.read(new File("images/start_leave.png"));//点击图片
			overImg =  ImageIO.read(new File("images/start_over.png"));//点击图片
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//注册监听器
		this.addMouseListener(this);

	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(loginImage, 0, 0, null); // null 无	
	
		if(num<32){
			g.drawImage(login[num], 350, 550, null);//画32次login条
			
			//构建一个比较耗时的操作，让j值得变化慢一点
			for(int z=0;z<90000000;z++){}
			num++;//改变num
			repaint();//重绘，重新执行一遍paint方法
		}else{
			g.drawImage(login[31], 350, 550, null);//画最后一张，并且不动了
			for(int z=0;z<999999999;z++){}
			//加载滚完了，点击开始出现
			if(isPress==false){
				g.drawImage(startImg, 385, 622, null);//没按下
			}else{
				g.drawImage(overImg, 385, 622, null);//按下的
			}
		}
		
		
	}

	
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
		//调用时机：鼠标按下面板某块区域的时候执行
		// e: 被监听者
		//在被监听者上设置点击的部位触发事件
		if(e.getX()>=385&&e.getX()<=690&&e.getY()>=622&&e.getY()<=654){
			//点击的位置上
			isPress = true;
			repaint();//切记，重新刷新页面	
		}
		
		
	}


	public void mouseReleased(MouseEvent e) {
		isPress = false;
		repaint();
		
		//制造一个耗时的操作，来感受UI的变化
		for(int i=0;i<999999999;i++){}
		
		//把当前窗口隐藏掉且销毁
		if(num>=32){//严谨，等待loading加载完成	
			frame.setVisible(false);
			frame.dispose();
			new MainFFrame();
		}
		
	}
	
	

}
