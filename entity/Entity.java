package entity;

import java.awt.image.BufferedImage;

import main.GamePanel;

import java.awt.Rectangle;
/*
Abstract class
 */
public class Entity {
    GamePanel gp;
    public int worldX, worldY, speed;

    public BufferedImage up0, up1, up2, idle0,  idle1,  idle2,  down0,  down1,  down2, left0,  left1,  left2, right0,  right1,  right2;      

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public String direction;

    public Rectangle solidArea = new Rectangle(0,0,48,48);//Colision por defecto, cambiar dentro de cada npc
    public boolean collisionOn = false;

//abstract class significa que no instanciamos la clase directamente, siempre por Player class o npc class
    /*public Entity(GamePanel gp){

    }*/

}
