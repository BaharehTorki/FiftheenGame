package se.nackademin;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;

import static se.nackademin.utils.GameUtils.*;

public class Game extends JFrame {
    Border border = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
    private final JLabelListGenerator jLListGenerator = new JLabelListGenerator();
    final List<JLabel> jLabelList;

    public Game() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(border);
        JPanel gamePanel = new JPanel(new GridLayout(4, 4));
        gamePanel.setBorder(border);
        JPanel controllerPanel = new MyJPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        controllerPanel.setBorder(border);
        JPanel monitoringPanel = new JPanel(new GridLayout(4, 1));
        monitoringPanel.setBorder(border);
        JPanel sidePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        add(mainPanel);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(controllerPanel, BorderLayout.SOUTH);
        mainPanel.add(monitoringPanel, BorderLayout.EAST);
        mainPanel.add(sidePanel, BorderLayout.WEST);

        controllerPanel.add(new JButton("new game"));
        controllerPanel.add(new JButton("Finish"));

        monitoringPanel.add(new Label("How many movement should be to finish "));
        monitoringPanel.add(new Label("Left number of movement: "));
        monitoringPanel.add(new Label("Need to fix: "));
        monitoringPanel.add(new Label("Best achievement: "));


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
                            JOptionPane.showMessageDialog(null, "Grattis, du vann!!");
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