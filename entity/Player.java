package entity;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX, screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        //TAMAÑO DE LA COLISION
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 8;
        solidArea.width = gp.tileSize - 16;
        solidArea.height = gp.tileSize - 16;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        /* posicion del personaje */
        worldX = gp.tileSize * (gp.maxWorldCol / 2);
        worldY = gp.tileSize * (gp.maxScreenRow / 2);
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
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            /*
             * Aca tendria que meter todos los if de abajo si no tubiese los sprites "Idle"
             */
        }

        if (keyH.upPressed) {
            direction = "up";
        } else if (keyH.downPressed) {
            direction = "down";
        } else if (keyH.leftPressed) {
            direction = "left";
        } else if (keyH.rightPressed) {
            direction = "right";
        } else {
            direction = "idle";
        }

        // CHECK TILE COLLISION
        collisionOn = false;
        gp.cChecker.checkTile(this);

        // if collision is false, player can move
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;

                    break;
                case "down":
                    worldY += speed;

                    break;
                case "left":
                    worldX -= speed;

                    break;
                case "right":
                    worldX += speed;

                    break;
            }
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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
