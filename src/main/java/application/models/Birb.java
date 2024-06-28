package application.models;

import javax.swing.*;

public class Birb {

    private final String character;
    private final int x;
    private int y;

    public Birb(String character, int x, int y) {
        this.character = character;
        this.x = x;
        this.y = y;
    }

    public void flop(JPanel panel) {
        this.y += 1;
        SwingUtilities.invokeLater(panel::repaint);
    }

    public String getCharacter() {
        return character;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
