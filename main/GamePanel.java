package main;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    final int orginalTileSize = 16; // 16x16 Tile size
    final int scale = 3;

    public final int tileSize = orginalTileSize * scale; // 48x48 tile size
    final int maxScreenCol = 16; //
    final int maxScreenRow = 12; //
    final int screenWidth = tileSize * maxScreenCol; // 768 Pixels 48x16
    final int screenHeight = tileSize * maxScreenRow; // 576 Pixels 48x12

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Helps with rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void statGameThread() {

        gameThread = new Thread(this);
        gameThread.start(); // Calls the run method
    }

    @Override
    public void run() {

        double drawInterval = 1_000_000_000 / FPS; // 0.01666 second for each update of screen
        double nextDrawTime = System.nanoTime() + drawInterval;

        // This have the game running
        while (gameThread != null) {

            // 1 Update: update information such as character postion
            update();

            // 2 Draw: the screen with the updated information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1_000_000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {

        // Super is the parent class which is JPanel
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);

        g2.dispose();

    }

}
