package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject{

    public OBJ_Chest(){
        name = "key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/object/Chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
