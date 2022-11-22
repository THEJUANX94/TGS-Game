package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject{

    public OBJ_Chest(){
        name = "Chest";

        try {
            image = ImageIO.read(new File("./src/Images/Object/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
