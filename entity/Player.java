package entity;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    private GamePanel gp;
    private KeyHandler keys;

    public Player(GamePanel gp, KeyHandler keys) {
        this.gp = gp;
        this.keys = keys;
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
    }

}
