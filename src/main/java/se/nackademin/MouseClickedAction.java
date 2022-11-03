package se.nackademin;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;

import static se.nackademin.utils.GameUtils.*;

public class MouseClickedAction extends MouseAdapter {
    private JPanel gamePanel;

    public MouseClickedAction(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

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
}
