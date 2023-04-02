package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2;
        screenY = gp.screenHeight / 2;

        solidArea = new Rectangle();

        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDeafaultX = solidArea.x;
        solidAreaDeafaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23 - gp.tileSize / 2;
        worldY = gp.tileSize * 21 - gp.tileSize / 2;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        // Loads the images for the player movement
            up1 = setup("boy_up_1");
            up2 = setup("boy_up_2");
            down1 = setup("boy_down_1");
            down2 = setup("boy_down_2");
            right1 = setup("boy_right_1");
            right2 = setup("boy_right_2");
            left1 = setup("boy_left_2");
            left2 = setup("boy_left_2");

    }

    public BufferedImage setup(String imageName){
        
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(String.format("/res/player/%s.png", imageName)));
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);


        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

        // SpriteCounter will increase on key press which will change the spiteNum to
        // create player movement
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true) {
            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            }
            // Checks plyaer tile collsions
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check for object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // If Collision is flase, player can move
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;

                }

            }

        }

        // When a key press it will update the player location based on key pressed

    }

    public void pickUpObject(int i) {
        if (i != 999) {
            

        }

    }

    public void draw(Graphics2D g2) {

        // g2.setColor(Color.white);

        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        // Draws the character movement based on the spriteNum
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }

                break;

            case "down":
                if (spriteNum == 1) {
                    image = down1;
                    ;
                }
                if (spriteNum == 2) {
                    image = down2;
                }

                break;

            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;

            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }



}
