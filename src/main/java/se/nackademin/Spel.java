package se.nackademin;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Spel extends JFrame {

    private JPanel panel = new JPanel();
    final List<JLabel> jLabelList;

    public Spel() {
        add(panel);
        panel.setLayout(new GridLayout(4, 4));
        panel.setSize(500, 500);
        panel.setBackground(Color.white);
        jLabelList = createJLabelList(true);
        for (JLabel l : jLabelList) {
            panel.add(l);
            l.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel chosen = (JLabel) e.getSource();

                    int iEmpty = getIndexOfEmptyJLabel(panel.getComponents());
                    int iChosen = getIndexOfChosenJLabel(chosen, panel.getComponents());

                    List<JLabel> jLabels = convertToListOfJLabel(panel.getComponents());

                    Collections.swap(jLabels, iEmpty, iChosen);
                    panel.removeAll();

                    for (JLabel c : jLabels) {
                        panel.add(c);
                    }

                    panel.revalidate();
                    panel.repaint();
                }
            });
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private List<JLabel> createJLabelList() {
        return createJLabelList(false);
    }

    public List<JLabel> createJLabelList(Boolean shuffle) {
        List<JLabel> list = new ArrayList<>();

        JLabel jLabel = new JLabel();
        list.add(jLabel);
        for (int i = 1; i < 16; i++) {
            jLabel = new JLabel();
            addFormat(jLabel, i);
            list.add(jLabel);
        }
        if (shuffle) {
            Collections.shuffle(list);
        }
        return list;
    }

    private void addFormat(JLabel jLabel, int num) {
        String filePath = getClass().getClassLoader().getResource("brickor/bricka_" + num + ".png").getPath();

        ImageIcon imageIcon = new ImageIcon(filePath);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(125, 125, Image.SCALE_SMOOTH);

        jLabel.setIcon(new ImageIcon(newimg));
    }

    private List<JLabel> convertToListOfJLabel(Component[] components) {
        List<JLabel> result = new ArrayList<>();
        for (Component c : components) {
            result.add((JLabel) c);
        }
        return result;
    }

    private int getIndexOfChosenJLabel(JLabel chosen, Component[] components) {
        if (chosen.getIcon() == null)
            return -1;
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JLabel)
                if (chosen.getIcon().equals(((JLabel) components[i]).getIcon())) {
                    return i;
                }
        }
        return 0;
    }

    private int getIndexOfEmptyJLabel(Component[] components) {
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JLabel) {
                if (((JLabel) components[i]).getIcon() == null)
                    return i;
            }
        }
        return 0;
    }
}
