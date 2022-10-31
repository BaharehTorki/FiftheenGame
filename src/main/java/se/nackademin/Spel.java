package se.nackademin;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Spel extends JFrame {

    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel gamePanel = new JPanel(new GridLayout(4, 4));

    private JPanel buttonPanel = new JPanel(new GridLayout(4,2));

    final List<JLabel> jLabelList;

    public Spel() {
        add(mainPanel);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(new JButton("Shuffle"));
        buttonPanel.add(new JButton("Exit"));
        buttonPanel.add(new Label("How many movement should be to finish "));
        buttonPanel.add(new Label("Left number of movement: "));
        buttonPanel.add(new Label("Need to fix: "));
        buttonPanel.add(new Label("Best achievement: "));
        buttonPanel.setBorder(BorderFactory.createBevelBorder(1));
        buttonPanel.setSize(500,400);

        gamePanel.setSize(500, 500);
        gamePanel.setBackground(Color.white);
        jLabelList = createJLabelList(true);
        for (JLabel l : jLabelList) {
            gamePanel.add(l);
            l.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel chosen = (JLabel) e.getSource();

                    int iEmpty = getIndexOfEmptyJLabel(gamePanel.getComponents());
                    int iChosen = getIndexOfChosenJLabel(chosen, gamePanel.getComponents());
                    if (iChosen != -1) {
                        List<JLabel> jLabels = convertToListOfJLabel(gamePanel.getComponents());

                        Collections.swap(jLabels, iEmpty, iChosen);
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

        ImageIcon imageIcon = new ImageIcon(filePath, String.valueOf(num));
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(125, 125, Image.SCALE_SMOOTH);
        ImageIcon newImgIcn = new ImageIcon(newImg, String.valueOf(num));
        newImgIcn.setDescription(String.valueOf(num));
        jLabel.setIcon(newImgIcn);
    }

    private List<JLabel> convertToListOfJLabel(Component[] components) {
        List<JLabel> result = new ArrayList<>();
        for (Component c : components) {
            if (c instanceof JLabel) {
                result.add((JLabel) c);
            }
        }
        return result;
    }

    private int getIndexOfChosenJLabel(JLabel chosen, Component[] components) {
        if (chosen.getIcon() == null)
            return -1;

        List<JLabel> jLabels = convertToListOfJLabel(components);
        for (int i = 0; i < jLabels.size(); i++) {
            if (chosen.getIcon().equals(jLabels.get(i).getIcon())) {
                int[] existence = new int[]
                        {(i - 1) >= 0 ? (i - 1) : -1,
                                (i + 1) <= 15 ? (i + 1) : -1,
                                (i - 4) >= 0 ? (i - 4) : -1,
                                (i + 4) <= 15 ? (i + 4) : -1};
                if (isAnySpace(jLabels, existence)) {
                    return i;
                } else {
                    return -1;
                }
            }
        }
        return -1;
    }

    private boolean isAnySpace(List<JLabel> jLabels, int[] existence) {
        for (int i : existence) {
            if (i != -1 && jLabels.get(i).getIcon() == null) {
                return true;
            }
        }
        return false;
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

    private boolean isSuccess(List<JLabel> jLabels) {
        for (int i = 0; i < jLabels.size() - 1; i++) {
            if (getImageIconDescription(jLabels.get(i)) != (i + 1)) {
                System.out.println("check " + (i + 1) + "th");
                return false;
            }
        }
        return true;
    }

    private int getImageIconDescription(JLabel jLabel) {
        if (jLabel.getIcon() == null)
            return -1;

        ImageIcon imageIcon = (ImageIcon) jLabel.getIcon();
        return Integer.parseInt(imageIcon.getDescription());
    }
}

