
package org.great;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.great.MainFFrame;
import org.great.MainFrame;

public class MainFPanel extends JPanel implements MouseListener {
    BufferedImage choose;
    MainFFrame frame;
    BufferedImage choose1;
    BufferedImage choose2;
    BufferedImage choose3;
    BufferedImage choose4;
    BufferedImage choose11;
    BufferedImage choose22;
    BufferedImage choose33;
    BufferedImage choose44;
    Boolean press = false;
    int q;

    public MainFPanel(MainFFrame frame) {
        this.frame = frame;
        File file = new File("images/choose/bgLogin.png");
        try {
            this.choose = ImageIO.read(file);
            this.choose1 = ImageIO.read(new File("images/choose/SelectorScreen_StartAdventure_Button1.png"));
            this.choose2 = ImageIO.read(new File("images/choose/SelectorScreen_Challenges_button.png"));
            this.choose3 = ImageIO.read(new File("images/choose/SelectorScreen_Vasebreaker_button.png"));
            this.choose4 = ImageIO.read(new File("images/choose/SelectorScreen_Survival_button.png"));
            this.choose11 = ImageIO.read(new File("images/choose/SelectorScreen_StartAdventure_Highlight.png"));
            this.choose22 = ImageIO.read(new File("images/choose/SelectorScreen_Challenges_highlight.png"));
            this.choose33 = ImageIO.read(new File("images/choose/SelectorScreen_Vasebreaker_highlight.png"));
            this.choose44 = ImageIO.read(new File("images/choose/SelectorScreen_Survival_highlight.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(this.choose, 0, 0, 1600, 900, null);
        g.drawImage(this.choose1, 950, 230, 500, 170, null);
        g.drawImage(this.choose2, 950, 360, 480, 160, null);
        g.drawImage(this.choose3, 950, 460, 450, 150, null);
        g.drawImage(this.choose4, 950, 560, 420, 140, null);
        if (!this.press.booleanValue()) {
            g.drawImage(this.choose1, 950, 230, 500, 170, null);
            g.drawImage(this.choose2, 950, 360, 480, 160, null);
            g.drawImage(this.choose3, 950, 460, 450, 150, null);
            g.drawImage(this.choose4, 950, 560, 420, 140, null);
        } else {
            g.drawImage(this.choose11, 950, 230, 500, 170, null);
            g.drawImage(this.choose22, 950, 360, 480, 160, null);
            g.drawImage(this.choose33, 950, 460, 450, 150, null);
            g.drawImage(this.choose44, 950, 560, 420, 140, null);
        }
    }

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getX() >= 950 && e.getX() <= 1270 && e.getY() >= 230 && e.getY() <= 373) {
            this.press = true;
            this.q = 1;
            this.repaint();
        }
        if (e.getX() >= 950 && e.getX() <= 1236 && e.getY() >= 380 && e.getY() <= 493) {
            this.press = true;
            this.q = 2;
            this.repaint();
        }
        if (e.getX() >= 950 && e.getX() <= 1270 && e.getY() >= 460 && e.getY() <= 593) {
            this.press = true;
            this.q = 3;
            this.repaint();
        }
        if (e.getX() >= 950 && e.getX() <= 1270 && e.getY() >= 560 && e.getY() <= 700) {
            this.press = true;
            this.q = 4;
            this.repaint();
        }
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		 this.press = false;
	        this.repaint();
	        if (this.q == 1) {
	            for (int i = 0; i < 999999; ++i) {
	            }
	           // this.frame.setVisible(false);
	            this.frame.dispose();
	            new org.great.MainFrame();
	        }
	    }
	}


