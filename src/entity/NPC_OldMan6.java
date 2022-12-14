package entity;

import main.GamePanel;

public class NPC_OldMan6 extends Entity {

    public NPC_OldMan6(GamePanel gp) {
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
        dialogues[0] = "Conozco sobre las diferentes taxonomias";
        dialogues[1] = "Exiten varias, como la de Jordan, la de Beer, la de Boulding, como tambien algunas metododologias";
        dialogues[2] = "Beer habla sobre la capacidad de autoorganizacion de un sistema";
        dialogues[3] = "Y Boulding creo nueve niveles, donde clasifica sistmeas intuitivamente";
    }

    public void speak() {
       super.speak();
    }
}
