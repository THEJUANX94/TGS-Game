package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    private KeyHandler keys;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keys) {
        super(gp);
        this.keys = keys;

        screenX = gp.screenWidth / 2;
        screenY = gp.screenHeight / 2;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 24 - (gp.tileSize / 2);
        worldY = gp.tileSize * 21 - (gp.tileSize / 2);
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        up1 = setUp("player/boy_up_1");
        up2 = setUp("player/boy_up_2");
        down1 = setUp("player/boy_down_1");
        down2 = setUp("player/boy_down_2");
        left1 = setUp("player/boy_left_1");
        left2 = setUp("player/boy_left_2");
        right1 = setUp("player/boy_right_1");
        right2 = setUp("player/boy_right_2");
    }

    public void update() {
        if (keys.wPressed == true || keys.sPressed == true || keys.aPressed == true || keys.dPressed == true) {
            if (keys.wPressed == true) {
                direction = "up";
            } else if (keys.sPressed == true) {
                direction = "down";
            } else if (keys.aPressed == true) {
                direction = "left";
            } else if (keys.dPressed == true) {
                direction = "right";
            }

            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // chech the object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            if (collisionOn == false) {
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

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    private void interactNPC(int i) {
        if (i != 999) {
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {

        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
            default:
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }

}
