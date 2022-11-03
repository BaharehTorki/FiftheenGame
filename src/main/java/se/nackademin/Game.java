package se.nackademin;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static se.nackademin.utils.GameUtils.*;

public class Game extends JFrame {
    Border border = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
    private final JLabelListGenerator jLListGenerator = new JLabelListGenerator();
    final List<JLabel> jLabelList;

    public Game() {
        String backgroundFilePath = getClass().getClassLoader().getResource("background.jpg").getFile();

        getContentPane().add(new MyJPanel(backgroundFilePath));
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(border);
        JPanel gamePanel = new JPanel(new GridLayout(4, 4));
        gamePanel.setBorder(border);

        JPanel controllerPanel = new MyJPanel(backgroundFilePath, new FlowLayout(FlowLayout.CENTER, 40,40));
        JPanel topPanel = new MyJPanel(backgroundFilePath);
        JPanel rightSidePanel =new MyJPanel(backgroundFilePath);
        JPanel leftSidePanel = new MyJPanel(backgroundFilePath);

        add(mainPanel);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(rightSidePanel, BorderLayout.EAST);
        mainPanel.add(leftSidePanel, BorderLayout.WEST);
        mainPanel.add(controllerPanel, BorderLayout.SOUTH);

        controllerPanel.add(new JButton("new game"));
        controllerPanel.add(new JButton("Finish"));

        gamePanel.setBackground(new Color(82, 53, 31));
        gamePanel.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
        jLabelList = jLListGenerator.createShuffleJLabelList();

        for (JLabel l : jLabelList) {
            gamePanel.add(l);
            l.addMouseListener(new MouseClickedAction(gamePanel));
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}