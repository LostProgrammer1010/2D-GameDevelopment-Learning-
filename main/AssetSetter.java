package main;

import objects.OBJ_Wand;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Wand(gp);
        gp.obj[0].worldX = 39 * gp.tileSize;
        gp.obj[0].worldY = 9 * gp.tileSize;

    }

}
