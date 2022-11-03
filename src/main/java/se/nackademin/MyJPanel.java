package se.nackademin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MyJPanel extends JPanel {
    private Image backgroundImage;

    public MyJPanel(String fileName)  {
        try {
            backgroundImage = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println(fileName + " does not find!");
        }
    }

    public MyJPanel(String fileName, LayoutManager layoutManager)  {
        this(fileName);
        super.setLayout(layoutManager);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }

}
