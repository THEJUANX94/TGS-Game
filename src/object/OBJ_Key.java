package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject{

    public OBJ_Key(){
        name = "Key";

        try {
            image = ImageIO.read(new File("./src/Images/Object/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}