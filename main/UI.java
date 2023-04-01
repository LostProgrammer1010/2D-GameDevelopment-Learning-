package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import objects.OBJ_Key;

public class UI {

    GamePanel gp;
    Font arial_40, arial_50B; 
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageTimer = 0;
    public boolean gameOver = false;

    double playTime;

    public UI(GamePanel gp){
        this.gp = gp;
        this.arial_40 = new Font("Arial", Font.PLAIN, 40);
        this.arial_50B = new Font("Bold", Font.PLAIN, 80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }

    public void showMessage(String text){

        message = text;
        messageOn = true;


    }

    public void draw(Graphics2D g2){

        if (gameOver){

            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*2);
            g2.drawString(text, x,y);

            text = String.format("It took you %.2fs", playTime);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x,y);

            g2.setFont(arial_50B);
            g2.setColor(Color.YELLOW);
            text = "Congratulation!!!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + gp.tileSize*2;

            g2.drawString(text, x,y);

            gp.gameThread = null;

        }
        else{
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, 74, 65);

        playTime += (double)1/60;

        g2.drawString(String.format("Time: %.2f", playTime), gp.tileSize*11, 65);

        if (messageOn){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
            messageTimer++;

            if (messageTimer > 60) {
                messageTimer = 0;
                messageOn = false;
            }
        }

        }

    }
    
}
