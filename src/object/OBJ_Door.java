package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Door extends SuperObject{

        public OBJ_Door(GamePanel gp){
            name = "Door";
    
            try {
                image = ImageIO.read(new File("./src/Images/Object/door.png"));
                uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
            collision = true;  
        }

 
}
