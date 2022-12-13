package entity;

import main.GamePanel;

public class NPC_OldMan4 extends Entity {

    public NPC_OldMan4(GamePanel gp) {
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
        dialogues[0] = "Eh? ¿Que es un sistema abierto?";
        dialogues[1] = "Es un sistema con mucho intercambio de energia con el ambiente";
        dialogues[2] = "Por el contrario, el sistema cerrado tiene muy poco intercambio de energia e informacion y no producen nada hacia el exterior";
        dialogues[3] = "unos ejemplos serian la sociedad como sistema abierto y un reloj como sistema cerrado";
    }

    public void speak() {
       super.speak();
    }
}
