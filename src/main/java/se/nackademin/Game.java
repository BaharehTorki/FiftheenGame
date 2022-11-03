package se.nackademin;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static se.nackademin.utils.GameUtils.*;

public class Game extends JFrame {
    Border border = BorderFactory.createCompoundBorder( BorderFactory.createMatteBorder(3,3,3,3,Color.darkGray),BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    private final JLabelListGenerator jLListGenerator = new JLabelListGenerator();

    public Game() {
        String backgroundFilePath = getClass().getClassLoader().getResource("background.jpg").getFile();
        String gamePanelFilePath = getClass().getClassLoader().getResource("gamePanelBack.jpg").getFile();


        JPanel mainPanel        = new JPanel(new BorderLayout());
        JPanel topPanel         = new MyJPanel(backgroundFilePath);
        JPanel rightSidePanel   = new MyJPanel(backgroundFilePath);
        JPanel leftSidePanel    = new MyJPanel(backgroundFilePath);
        JPanel controllerPanel  = new MyJPanel(backgroundFilePath, new FlowLayout(FlowLayout.CENTER, 40,40));
        JPanel gamePanel        = new MyJPanel(gamePanelFilePath, new GridLayout(4, 4));

        mainPanel.setBorder(border);
        gamePanel.setBorder(border);

        add(mainPanel);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(rightSidePanel, BorderLayout.EAST);
        mainPanel.add(leftSidePanel, BorderLayout.WEST);
        mainPanel.add(controllerPanel, BorderLayout.SOUTH);

        JButton new_game = new JButton("new game");
        new_game.addActionListener(e -> {
                puttingListOfJLabelInGamePanel(gamePanel);
                revalidate();
                repaint();
        });

        JButton finish = new JButton("Finish");
        finish.addActionListener(e -> {
            System.exit(0);
        });
        controllerPanel.add(new_game);
        controllerPanel.add(finish);

        puttingListOfJLabelInGamePanel(gamePanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void puttingListOfJLabelInGamePanel(JPanel gamePanel) {
        gamePanel.removeAll();
        final List<JLabel> jLabelList = jLListGenerator.createShuffleJLabelList();

        for (JLabel l : jLabelList) {
            gamePanel.add(l);
            l.addMouseListener(new MouseClickedAction(gamePanel));
        }
    }

}