package entity;

import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 3;
        direction = "idle";

    }

    public void getPlayerImage() {
        try {
            up0 = ImageIO.read(getClass().getResourceAsStream("../res/Player/PlayerUp_0.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("../res/Player/PlayerUp_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../res/Player/PlayerUp_2.png"));
            down0 = ImageIO.read(getClass().getResourceAsStream("../res/Player/PlayerDown_0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../res/Player/PlayerDown_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../res/Player/PlayerDown_2.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("../res/Player/PlayerRight_0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../res/Player/PlayerRight_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../res/Player/PlayerRight_2.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("../res/Player/PlayerLeft_0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../res/Player/PlayerLeft_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../res/Player/PlayerLeft_2.png"));
            idle0 = ImageIO.read(getClass().getResourceAsStream("../res/Player/PlayerIdle_0.png"));
            idle1 = ImageIO.read(getClass().getResourceAsStream("../res/Player/PlayerIdle_1.png"));
            idle2 = ImageIO.read(getClass().getResourceAsStream("../res/Player/PlayerIdle_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            /*Aca tendria que meter todos los if de abajo si no tubiese los sprites "Idle" */
        }

        if (keyH.upPressed) {
            direction = "up";
            y -= speed;
        } else if (keyH.downPressed) {
            direction = "down";
            y += speed;
        } else if (keyH.leftPressed) {
            direction = "left";
            x -= speed;
        } else if (keyH.rightPressed) {
            direction = "right";
            x += speed;
        } else {
            direction = "idle";
        }

    }

    public void draw(Graphics2D g2) {
        /*
         * g2.setColor(Color.white);
         * 
         * g2.fillRect(x, y, gp.tileSize, gp.tileSize); // x,y,width,height
         */
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up0;
                }
                if (spriteNum == 2) {
                    image = up1;
                }
                if (spriteNum == 3) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down0;
                }
                if (spriteNum == 2) {
                    image = down1;
                }
                if (spriteNum == 3) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left0;
                }
                if (spriteNum == 2) {
                    image = left1;
                }
                if (spriteNum == 3) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right0;
                }
                if (spriteNum == 2) {
                    image = right1;
                }
                if (spriteNum == 3) {
                    image = right2;
                }
                break;

            case "idle":
                if (spriteNum == 1) {
                    image = idle0;
                }
                if (spriteNum == 2) {
                    image = idle1;
                }
                if (spriteNum == 3) {
                    image = idle2;
                }
                break;

            }
            spriteCounter++;

        if (spriteCounter > 60) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}