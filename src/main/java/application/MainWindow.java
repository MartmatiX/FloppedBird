package application;

import application.models.Birb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow {

    private final JPanel panel;

    private final Birb birb;

    private boolean movingUp = false;
    private boolean movingDown = false;

    public MainWindow() {
        JFrame frame = new JFrame();
        int WIDTH = 800;
        int HEIGHT = 600;
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setTitle("Flopped Bird");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        birb = new Birb("(⌒▽⌒)", 50, HEIGHT / 2);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawString(birb.getCharacter(), birb.getX(), birb.getY());
                g.drawRect(100, 100, 10, 10);
            }
        };

        Timer timer = new Timer(15, e -> birb.flop(panel));
        timer.start();

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> movingUp = true;
                    case KeyEvent.VK_DOWN -> movingDown = true;
                    case KeyEvent.VK_ESCAPE -> System.exit(0);
                    default -> panel.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> movingUp = false;
                    case KeyEvent.VK_DOWN -> movingDown = false;
                }
            }
        });

        Timer timerMovement = new Timer(10, e -> {
            if (birb.getY() < 0 || birb.getY() > HEIGHT - 40) {
                System.exit(0);
            }
            if (movingUp) {
                birb.setY(birb.getY() - 5);
            }
            if (movingDown) {
                birb.setY(birb.getY() + 5);
            }
            panel.repaint();
        });
        timerMovement.start();

        frame.add(panel);
    }

}
