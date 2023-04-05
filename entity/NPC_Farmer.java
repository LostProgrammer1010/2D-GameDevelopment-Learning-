package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Farmer extends Entity{

    public NPC_Farmer(GamePanel gp){
        super(gp);

        direction = "up";
        speed = 1;

                // Loads the images for the player movement
                up1 = setup("npc/farmer_npc_idle_up_1");
                up2 = setup("npc/farmer_npc_idle_up_3");
                down1 = setup("npc/farmer_npc_idle_1");
                down2 = setup("npc/farmer_npc_idle_3");
                right1 = setup("npc/farmer_npc_idle_right_1");
                right2 = setup("npc/farmer_npc_idle_right_3");
                left1 = setup("npc/farmer_npc_idle_left_1");
                left2 = setup("npc/farmer_npc_idle_left_3");
    
        }

        public void setAction(){
            actionLockCounter++;
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if (actionLockCounter == 120){
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75){
                direction = "left";
            }
            if (i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;
        }
        
    }

    }
