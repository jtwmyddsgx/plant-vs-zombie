package org.great;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// �����˳�
public class ExitAction  extends KeyAdapter{
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode()==27){
			System.exit(0);//�˳�
		}
	}

}
