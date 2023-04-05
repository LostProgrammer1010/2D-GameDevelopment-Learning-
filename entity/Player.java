package entity;

import main.GamePanel;
import main.KeyHandler;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public boolean playerHasWand = false;
    public boolean playerAttack = false;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
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

    public void attack(String attackType){
        if (!playerAttack){
        if (attackType.equals("Wand")){
            playerAttack = true;
            spriteCounter = 0;
            spriteNum = 1;
            System.out.println("Attack with wand");
        }
    }
    }

    public void getPlayerImage() {

        // Loads the images for the player movement
            up1 = setup("player/wizard_walk_up_1", gp.tileSize, gp.tileSize);
            up2 = setup("player/wizard_walk_up_2", gp.tileSize, gp.tileSize);
            up3 = setup("player/wizard_walk_up_3", gp.tileSize, gp.tileSize);
            up4 = setup("player/wizard_walk_up_4", gp.tileSize, gp.tileSize);
            down1 = setup("player/wizard_walk_down_1", gp.tileSize, gp.tileSize);
            down2 = setup("player/wizard_walk_down_2", gp.tileSize, gp.tileSize);
            down3 = setup("player/wizard_walk_down_3", gp.tileSize, gp.tileSize);
            down4 = setup("player/wizard_walk_down_4", gp.tileSize, gp.tileSize);
            right1 = setup("player/wizard_walk_right_1", gp.tileSize, gp.tileSize);
            right2 = setup("player/wizard_walk_right_2", gp.tileSize, gp.tileSize);
            right3 = setup("player/wizard_walk_right_3", gp.tileSize, gp.tileSize);
            right4 = setup("player/wizard_walk_right_4", gp.tileSize, gp.tileSize);
            left1 = setup("player/wizard_walk_left_1", gp.tileSize, gp.tileSize);
            left2 = setup("player/wizard_walk_left_2", gp.tileSize, gp.tileSize);
            left3 = setup("player/wizard_walk_left_3", gp.tileSize, gp.tileSize);
            left4 = setup("player/wizard_walk_left_4", gp.tileSize, gp.tileSize);
            attackRight1 = setup("player/wizard_wand_attack_right_1", 2*gp.tileSize-2, gp.tileSize-1);
            attackRight2 = setup("player/wizard_wand_attack_right_2", 2*gp.tileSize-3, gp.tileSize-1);
            attackRight3 = setup("player/wizard_wand_attack_right_3", 2*gp.tileSize-1, gp.tileSize-2);
            

    }

    public void update() {
        if (playerAttack){
            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } 
                else if (spriteNum == 2){
                    spriteNum = 3;
                }
                else if (spriteNum == 3){
                    spriteNum = 4;
                }
                else{
                    spriteNum = 1;
                }
                spriteCounter = 0;
        }
        
    }

        // SpriteCounter will increase on key press which will change the spiteNum to
        // create player movement
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } 
                else if (spriteNum == 2){
                    spriteNum = 3;
                }
                else if (spriteNum == 3){
                    spriteNum = 4;
                }
                else{
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

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

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

        // When a key press it will update the player location based on key pressed

    }
}

    public void pickUpObject(int i) {
        if (i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Wand":
                    gp.obj[i] = null;
                    down1 = setup("object/wizard_walk_wand_down_1", gp.tileSize, gp.tileSize);
                    down2 = setup("object/wizard_walk_wand_down_2", gp.tileSize, gp.tileSize);
                    down3 = setup("object/wizard_walk_wand_down_3", gp.tileSize, gp.tileSize);
                    down4 = setup("object/wizard_walk_wand_down_4", gp.tileSize, gp.tileSize);
                    right1 = setup("object/wizard_walk_wand_right_1", gp.tileSize, gp.tileSize);
                    right2 = setup("object/wizard_walk_wand_right_2", gp.tileSize, gp.tileSize);
                    right3 = setup("object/wizard_walk_wand_right_3", gp.tileSize, gp.tileSize);
                    right4 = setup("object/wizard_walk_wand_right_4", gp.tileSize, gp.tileSize);
                    left1 = setup("object/wizard_walk_wand_left_1", gp.tileSize, gp.tileSize);
                    left2 = setup("object/wizard_walk_wand_left_2", gp.tileSize, gp.tileSize);
                    left3 = setup("object/wizard_walk_wand_left_3", gp.tileSize, gp.tileSize);
                    left4 = setup("object/wizard_walk_wand_left_4", gp.tileSize, gp.tileSize);
                    playerHasWand = true;
                    System.out.println(playerHasWand);
                    break;
            

        }
    }

    }

    public void interactNPC(int i){
        if (i != 999) {
            System.out.println("hitting npc");

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
                if (spriteNum == 3){
                    image = up3;
                }
                if (spriteNum == 4){
                    image = up4;
                }
                

                break;

            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3){
                    image = down3;
                }
                if (spriteNum == 4){
                    image = down4;
                }

                break;

            case "right":
            if (!playerAttack){
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3){
                    image = right3;
                }
                if (spriteNum == 4){
                    image = right4;
                }
            }else{
                if (spriteNum == 1){
                    image = attackRight1;
                }
                if (spriteNum == 2) {
                    image = attackRight2;
                }
                if (spriteNum == 3){
                    image = attackRight3;
                }
                if (spriteNum == 4){
                    image = right4;
                    playerAttack = false;
                }
            }

                break;

            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3){
                    image = left3;
                }
                if (spriteNum == 4){
                    image = left4;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }



}
