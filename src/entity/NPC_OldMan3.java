package entity;

import main.GamePanel;

public class NPC_OldMan3 extends Entity {

    public NPC_OldMan3(GamePanel gp) {
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
        dialogues[0] = "¿Quien eres? ¿Eres un estudiante?";
        dialogues[1] = "Espero ayudarte con la explicacion sobre las caracteristicas de los sistemas";
        dialogues[2] = "Existe la entropia y la neguentropia, estos 2 son contrarios";
        dialogues[3] = "La entropia es el camino hacia el desorden maximo, y la neguentropia es el espacio de orden donde se desea mantener el sistema";
    }

    public void speak() {
       super.speak();
    }

}