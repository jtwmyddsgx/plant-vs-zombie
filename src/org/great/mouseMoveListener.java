package org.great;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class mouseMoveListener implements MouseMotionListener{
	MainPanel2 mainPanel2;
	public mouseMoveListener(MainPanel2 mainPanel2) {
		this.mainPanel2 = mainPanel2;
	}
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mainPanel2.mouseX = e.getX()-25;
		mainPanel2.mouseY = e.getY()-30;
		mainPanel2.repaint();
	}

}
