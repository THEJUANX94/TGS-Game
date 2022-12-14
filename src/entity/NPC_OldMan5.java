package entity;

import main.GamePanel;

public class NPC_OldMan5 extends Entity {

    public NPC_OldMan5(GamePanel gp) {
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

    public void setDialogue() {
        dialogues[0] = "Hola!";
        dialogues[1] = "Peter Senge, se quien es";
        dialogues[2] = "Fue el autor de las 11 leyes para tomar en cuenta al desarrollar un sistema";
        dialogues[3] = "Estas leyes se siguen aplicando hasta el dia de hoy en diferentes ambitos laborales";
    }

    public void speak() {
       super.speak();
    }
}
