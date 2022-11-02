package se.nackademin.utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameUtils {

    public static int getImageIconDescriptionAsInt(JLabel jLabel) {
        if (jLabel.getIcon() == null)
            return -1;

        ImageIcon imageIcon = (ImageIcon) jLabel.getIcon();

        try {
            return Integer.parseInt(imageIcon.getDescription());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static List<JLabel> convertToListOfJLabel(Component[] components) {
        List<JLabel> result = new ArrayList<>();
        for (Component c : components) {
            if (c instanceof JLabel) {
                result.add((JLabel) c);
            }
        }
        return result;
    }

    public static int getIndexOfEmptyJLabel(List<JLabel> jLabels) {
        for (int i = 0; i < jLabels.size(); i++) {
            if (jLabels.get(i).getIcon() == null) {
                return i;
            }
        }
        return -1;
    }

    public static int getIndexOfChosen(JLabel chosen, List<JLabel> jLabels) {
        if (chosen.getIcon() == null) {
            return -1;
        }

        int i = getIndexOfChosenJLabel(chosen, jLabels);
        int[] neighbours = getNeighbours(i);
        if (isAnySpace(jLabels, neighbours)) {
            return i;
        }
        return -1;
    }

    public static boolean isSuccess(List<JLabel> jLabels) {
        for (int i = 0; i < jLabels.size() - 1; i++) {
            if (getImageIconDescriptionAsInt(jLabels.get(i)) != (i + 1)) {
                System.out.println("check " + (i + 1) + "th");
                return false;
            }
        }
        return true;
    }

    private static int[] getNeighbours(int i) {
        int a, b, c, d;

        a = ((i - 1) >= 0 && (i % 4 != 0)) ?
                (i - 1)
                : -1;

        b = (i % 4 != 3) ?
                (i + 1)
                : -1;

        c = ((i - 4) >= 0) ?
                (i - 4)
                : -1;

        d = ((i + 4) <= 15) ?
                (i + 4)
                : -1;

        return new int[]{a, b, c, d};
    }

    private static int getIndexOfChosenJLabel(JLabel chosen, List<JLabel> jLabels) {
        for (int i = 0; i < jLabels.size(); i++) {
            if (chosen.getIcon().equals(jLabels.get(i).getIcon())) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isAnySpace(List<JLabel> jLabels, int[] existence) {
        for (int i : existence) {
            if (i != -1 && jLabels.get(i).getIcon() == null) {
                return true;
            }
        }
        return false;
    }
}
