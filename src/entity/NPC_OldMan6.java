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
        dialogues[0] = "Hola!";
        dialogues[1] = "Conozco sobre TGS";
        dialogues[2] = "Ludwig Von Bertalanffy fue uno de los primeros en dar el termino de sistema y de intercambios entre sistemas";
        dialogues[3] = "Tambien señalo la frase de Aristoteles \"El todo es mas que la supa de sus partes\"";
    }

    public void speak() {
       super.speak();
    }
}
