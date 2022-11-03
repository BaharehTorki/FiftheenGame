package se.nackademin;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class Game extends JFrame {
    Border border = BorderFactory.createCompoundBorder( BorderFactory.createMatteBorder(3,3,3,3,Color.darkGray),BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    private final JLabelListGenerator jLListGenerator = new JLabelListGenerator();

    public Game() {

        JPanel mainPanel        = new JPanel(new BorderLayout());
        JPanel topPanel         = new JPanel();
        JPanel rightSidePanel   = new JPanel();
        JPanel leftSidePanel    = new JPanel();
        JPanel controllerPanel  = new JPanel(new FlowLayout(FlowLayout.CENTER, 40,40));
        JPanel gamePanel        = new JPanel(new GridLayout(4, 4));

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