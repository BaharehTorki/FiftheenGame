package se.nackademin;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;

import static se.nackademin.utils.GameUtils.*;

public class Spel extends JFrame {

    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel gamePanel = new JPanel(new GridLayout(4, 4));

    private JPanel buttonPanel = new JPanel(new GridLayout(4, 2));
    private JPanel sidePanel = new JPanel(new GridLayout(2, 2));

    private final JLabelListGenerator jLListGenerator = new JLabelListGenerator();
    final List<JLabel> jLabelList;

    public Spel() {
        add(mainPanel);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(sidePanel, BorderLayout.WEST);

        buttonPanel.add(new JButton("Shuffle"));
        buttonPanel.add(new JButton("Exit"));
        buttonPanel.add(new Label("How many movement should be to finish "));
        buttonPanel.add(new Label("Left number of movement: "));
        buttonPanel.add(new Label("Need to fix: "));
        buttonPanel.add(new Label("Best achievement: "));
        //buttonPanel.setBorder(BorderFactory.createBevelBorder(5));

//        mainPanel.setSize(800,500);
//        gamePanel.setSize(500, 500);
//        buttonPanel.setSize(500, 400);
//        sidePanel.setSize(100,500);

        sidePanel.add(new JLabel("Left"));
        sidePanel.add(new JLabel("Left"));
        sidePanel.add(new JLabel("Left"));
        sidePanel.add(new JLabel("Left"));

        gamePanel.setBackground(new Color(82, 53, 31));

        jLabelList = jLListGenerator.createShuffleJLabelList();

        for (JLabel l : jLabelList) {
            gamePanel.add(l);
            l.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    List<JLabel> jLabels = convertToListOfJLabel(gamePanel.getComponents());

                    int iEmpty = getIndexOfEmptyJLabel(jLabels);

                    JLabel chosen = (JLabel) e.getSource();
                    int availableForMove = isAvailableForMove(chosen, jLabels);

                    if (availableForMove != -1) {
                        Collections.swap(jLabels, iEmpty, availableForMove);
                        gamePanel.removeAll();

                        for (JLabel c : jLabels) {
                            gamePanel.add(c);
                        }

                        gamePanel.revalidate();
                        gamePanel.repaint();

                        if (isSuccess(jLabels)) {
                            System.out.println(jLabels);
                            JOptionPane.showMessageDialog(null, "WWWOOOOWWW! You won!");
                        }
                    }
                }
            });
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}