package se.nackademin;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;

import static se.nackademin.utils.GameUtils.*;

public class Game extends JFrame {

    private final JLabelListGenerator jLListGenerator = new JLabelListGenerator();
    final List<JLabel> jLabelList;

    public Game() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel gamePanel = new JPanel(new GridLayout(4, 4));
        JPanel buttonPanel = new JPanel(new GridLayout(4, 2));
        JPanel sidePanel = new JPanel(new GridLayout(2, 2));


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

        sidePanel.add(new JLabel("dfskljfkldj"));
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

                    int indexOfEmpty = getIndexOfEmptyJLabel(jLabels);

                    JLabel chosen = (JLabel) e.getSource();
                    int indexOfChosen = getIndexOfChosen(chosen, jLabels);

                    if (indexOfChosen != -1) {
                        Collections.swap(jLabels, indexOfEmpty, indexOfChosen);
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