package se.nackademin;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class Game extends JFrame {
    Border border = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.darkGray), BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    private final JLabelListGenerator jLListGenerator = new JLabelListGenerator();
    private final JPanel gamePanel;
    private final JLabel statusLabel;

    public Game() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        JPanel rightSidePanel = new JPanel();
        JPanel leftSidePanel = new JPanel();
        JPanel controllerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));
        gamePanel = new JPanel(new GridLayout(4, 4));

        statusLabel = new JLabel();
        mainPanel.setBorder(border);
        gamePanel.setBorder(border);

        add(mainPanel);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(rightSidePanel, BorderLayout.EAST);
        mainPanel.add(leftSidePanel, BorderLayout.WEST);
        mainPanel.add(controllerPanel, BorderLayout.SOUTH);

        puttingListOfJLabelInGamePanel();

        JButton new_game = new JButton("new game");
        new_game.addActionListener(e -> {
            int answer = JOptionPane.showConfirmDialog(null, "You will lose everything, are you sure?");
            if (answer == 0) {
                statusLabel.setText(" ");
                puttingListOfJLabelInGamePanel();
                revalidate();
                repaint();
            }
        });

        JButton finish = new JButton("Finish");
        finish.addActionListener(e -> {
            System.exit(0);
        });
        controllerPanel.add(new_game);
        controllerPanel.add(finish);
        controllerPanel.add(statusLabel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void puttingListOfJLabelInGamePanel() {
        gamePanel.removeAll();
        final List<JLabel> jLabelList = jLListGenerator.createShuffleJLabelList();

        for (JLabel l : jLabelList) {
            gamePanel.add(l);
            l.addMouseListener(new MouseClickedAction(gamePanel, statusLabel));
        }
    }
}