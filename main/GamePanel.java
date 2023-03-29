package main;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


import entity.Player;
import objects.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    final int orginalTileSize = 16; // 16x16 Tile size
    final int scale = 3;

    public final int tileSize = orginalTileSize * scale; // 48x48 tile size
    public final int maxScreenCol = 16; //
    public final int maxScreenRow = 12; //
    public final int screenWidth = tileSize * maxScreenCol; // 768 Pixels 48x16
    public final int screenHeight = tileSize * maxScreenRow; // 576 Pixels 48x12

    // World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 55;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    

    public GamePanel() {

        // Sets the size of the sceen the back ground color
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Helps with rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();

        playMusic(0);
    }

    public void statGameThread() {
        // Thread allows for the game to be updating all the time
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

        // Draw the tiles first because it is like a layer
        tileM.draw(g2);

        // OBjECT
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // PlAYER
        player.draw(g2);

        g2.dispose();

    }

    public void playMusic(int i){

        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){

        sound.stop();
    }

    public void playSoundEffect(int i){

        sound.setFile(i);
        sound.play();

    }

}
