package se.nackademin;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;

public class MyJPanel extends JPanel {
    public MyJPanel(LayoutManager layoutManager) {
        super(layoutManager);
    }

    @Override
    protected void paintComponent(Graphics g) {
        URL resource = this.getClass().getClassLoader().getResource("background.jpg");
        super.paintComponent(g);
        g.drawImage(Toolkit.getDefaultToolkit().createImage(resource),0,100,null);
    }
}
