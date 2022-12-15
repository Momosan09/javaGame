package main;

import object.OBJ_0;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        //La posicion 0 del vector de obj[10] gamePanel vale la subclase de "SuperObject", OBJ_0
        gp.obj[0] = new OBJ_0();
        gp.obj[0].worldX = 2 * gp.tileSize;
        gp.obj[0].worldY = 2 * gp.tileSize;

        gp.obj[1] = new OBJ_0();
        gp.obj[1].worldX = 5 * gp.tileSize;
        gp.obj[1].worldY = 5 * gp.tileSize;


    }
}
