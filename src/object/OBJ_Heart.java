package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Heart extends SuperObject{

    GamePanel gp;

    public OBJ_Heart(GamePanel gp){
        this.gp = gp;
        name = "Heart";

        try {
            image = ImageIO.read(new File("./src/Images/Object/heart_full.png"));
            image2 = ImageIO.read(new File("./src/Images/Object/heart_half.png"));
            image3 = ImageIO.read(new File("./src/Images/Object/heart_blank.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
