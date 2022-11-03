package se.nackademin.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class GameUtilsTest {

    @Test
    void should_return_description_as_a_int() {
        String filePath = this.getClass().getClassLoader().getResource("bricka_1.png").getFile();
        Icon icon = new ImageIcon(filePath, "123");
        JLabel jLabel = new JLabel(icon);

        int actual = GameUtils.getImageIconDescriptionAsInt(jLabel);

        assertEquals(123, actual);
    }

    @Test
    void should_return_negative_one_if_description_was_not_int() {
        String filePath = this.getClass().getClassLoader().getResource("bricka_1.png").getFile();
        Icon icon = new ImageIcon(filePath, "String");
        JLabel jLabel = new JLabel(icon);

        int actual = GameUtils.getImageIconDescriptionAsInt(jLabel);

        assertEquals(-1, actual);
    }

    @Test
    void should_return_negative_one_if_JLabel_has_no_image() {
        JLabel jLabel = new JLabel("Hello world!");

        int actual = GameUtils.getImageIconDescriptionAsInt(jLabel);

        assertEquals(-1, actual);
    }

    @Test
    void should_return_true_if_images_description_are_setting_sequentially() {
        String filePath = this.getClass().getClassLoader().getResource("bricka_1.png").getFile();

        List<JLabel> jLabels = new ArrayList<>();
        jLabels.add(new JLabel(new ImageIcon(filePath,"1")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"2")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"3")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"4")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"5")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"6")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"7")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"8")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"9")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"10")));


        boolean actual = GameUtils.isSuccess(jLabels, new JLabel());

        assertTrue(actual);
    }

    @Test
    void should_return_false_if_images_description_are_not_setting_sequentially() {
        String filePath = this.getClass().getClassLoader().getResource("bricka_1.png").getFile();

        List<JLabel> jLabels = new ArrayList<>();
        jLabels.add(new JLabel(new ImageIcon(filePath,"1")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"2")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"3")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"4")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"5")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"7")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"6")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"8")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"9")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"10")));


        boolean actual = GameUtils.isSuccess(jLabels, new JLabel());

        assertFalse(actual);
    }

    @Test
    void should_return_correct_index() {
        String filePath = this.getClass().getClassLoader().getResource("bricka_1.png").getFile();

        List<JLabel> jLabels = new ArrayList<>();

        jLabels.add(new JLabel(new ImageIcon(filePath,"1")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"2")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"3")));
        jLabels.add(new JLabel("Hello"));
        jLabels.add(new JLabel(new ImageIcon(filePath,"5")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"7")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"6")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"8")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"9")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"10")));

        int actual = GameUtils.getIndexOfEmptyJLabel(jLabels);

        assertEquals(3, actual);
    }

    @Test
    void should_return_negative_one_if_none_of_component_miss_image() {
        String filePath = this.getClass().getClassLoader().getResource("bricka_1.png").getFile();

        List<JLabel> jLabels = new ArrayList<>();

        jLabels.add(new JLabel(new ImageIcon(filePath,"1")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"2")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"3")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"4")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"5")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"7")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"6")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"8")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"9")));
        jLabels.add(new JLabel(new ImageIcon(filePath,"10")));

        int actual = GameUtils.getIndexOfEmptyJLabel(jLabels);

        assertEquals(-1, actual);
    }
}