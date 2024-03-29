package entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

// Allow for different entries to be created
public class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDeafaultX, solidAreaDeafaultY;
    public boolean collisionOn = false;
}
