package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y, speed;

    public BufferedImage up0, up1, up2, idle0,  idle1,  idle2,  down0,  down1,  down2, left0,  left1,  left2, right0,  right1,  right2;      

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public String direction;

}
