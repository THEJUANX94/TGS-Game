package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getOldManImage();
        setDialogue();
    }

    public void getOldManImage() {
        up1 = setUp("NPC/oldman_up_1");
        up2 = setUp("NPC/oldman_up_2");
        down1 = setUp("NPC/oldman_down_1");

        down2 = setUp("NPC/oldman_down_2");
        left1 = setUp("NPC/oldman_left_1");
        left2 = setUp("NPC/oldman_left_2");
        right1 = setUp("NPC/oldman_right_1");
        right2 = setUp("NPC/oldman_right_2");
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }

    public void setDialogue() {
        dialogues[0] = "Hola!";
        dialogues[1] = "¿Vienes a buscar conocimiento de TGS?";
        dialogues[2] = "Fui un profesor pero ahora estoy demasiado\n viejo para ello";
        dialogues[3] = "Busca a mis compañeros!";
    }

    public void speak() {
       super.speak();
    }
}
