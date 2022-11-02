package se.nackademin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JLabelListGenerator {

    public List<JLabel> createShuffleJLabelList() {
        List<JLabel> list = new ArrayList<>();

        JLabel jLabel = new JLabel();
        list.add(jLabel);

        for (int i = 1; i < 16; i++) {
            list.add(createJLabelWithImage(i));
        }

        Collections.shuffle(list);

        return list;
    }

    private JLabel createJLabelWithImage(int number) {
        JLabel jLabel = new JLabel();
        ImageIcon imageIcon = createImageIcon(number);

        jLabel.setIcon(imageIcon);

        return jLabel;
    }

    private ImageIcon createImageIcon(int number) {
        String filePath = getClass().getClassLoader().getResource("brickor/bricka_" + number + ".png").getPath();

        ImageIcon imageIcon = new ImageIcon(filePath);
        Image image = imageIcon.getImage();

        imageIcon.setImage(image.getScaledInstance(125, 125, Image.SCALE_SMOOTH));
        imageIcon.setDescription(String.valueOf(number));

        return imageIcon;
    }
}
