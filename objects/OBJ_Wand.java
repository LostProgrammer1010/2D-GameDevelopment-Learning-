package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Wand extends SuperObject{

    GamePanel gp;

    public OBJ_Wand(GamePanel gp) {
        name = "Wand";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/wand.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}