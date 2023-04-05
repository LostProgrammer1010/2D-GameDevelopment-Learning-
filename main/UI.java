package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_50B; 
    public boolean messageOn = false;
    public String message = "";
    int messageTimer = 0;
    public boolean gameOver = false;

    double playTime;

    public UI(GamePanel gp){
        this.gp = gp;
        this.arial_40 = new Font("Arial", Font.PLAIN, 40);
        this.arial_50B = new Font("Bold", Font.PLAIN, 80);

    }

    public void showMessage(String text){

        message = text;
        messageOn = true;


    }

    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.playState){
            // Do play state 
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }

    }

    public void drawPauseScreen(){

        g2.setFont(arial_50B);
        
        String text = "PAUSED";

        int x = getXforCenterText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public int getXforCenterText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 -length/2;
        
    }
    
}
