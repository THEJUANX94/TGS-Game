package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {

    private GamePanel gp;
    private KeyHandler keys;

    public Player(GamePanel gp, KeyHandler keys) {
        this.gp = gp;
        this.keys = keys;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(new File("./src/Images/player/boy_up_1.png"));
            up2 = ImageIO.read(new File("./src/Images/player/boy_up_2.png"));
            down1 = ImageIO.read(new File("./src/Images/player/boy_down_1.png"));
            down2 = ImageIO.read(new File("./src/Images/player/boy_down_2.png"));
            left1 = ImageIO.read(new File("./src/Images/player/boy_left_1.png"));
            left2 = ImageIO.read(new File("./src/Images/player/boy_left_2.png"));
            right1 = ImageIO.read(new File("./src/Images/player/boy_right_1.png"));
            right2 = ImageIO.read(new File("./src/Images/player/boy_right_2.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void update() {
        if (keys.wPressed == true || keys.sPressed == true || keys.aPressed == true || keys.dPressed == true) {
            if (keys.wPressed == true) {
                direction = "up";
                y -= speed;
            } else if (keys.sPressed == true) {
                direction = "down";
                y += speed;
            } else if (keys.aPressed == true) {
                direction = "left";
                x -= speed;
            } else if (keys.dPressed == true) {
                direction = "right";
                x += speed;
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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
