
package org.great;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import org.great.ExitAction;
import org.great.MainFPanel;

public class MainFFrame extends JFrame {
    public MainFFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle bounds = new Rectangle(screenSize);
        this.setBounds(bounds);
        this.setUndecorated(true);
        this.add(new MainFPanel(this));
        this.setVisible(true);
        this.addKeyListener(new ExitAction());
    }

    public static void main(String[] args) {
        MainFFrame frame = new MainFFrame();
    }
}