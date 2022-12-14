package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class EntityNpc extends Entity{

    GamePanel gp;

    public int worldXPosition;
    public int worldYPosition;
    
    public EntityNpc(GamePanel gp){
        this.gp = gp;
        setDefaultValues();
    }

    public void setDefaultValues(){
        worldXPosition = gp.tileSize * 2;
        worldYPosition = gp.tileSize * 2;
    }

    public void draw(Graphics2D g2, int positionX, int positionY){
        g2.setColor(Color.white);
        g2.fillRect(positionX, positionY, gp.tileSize, gp.tileSize);
    }
}
