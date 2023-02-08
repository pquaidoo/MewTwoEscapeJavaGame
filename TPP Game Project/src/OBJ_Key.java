import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
public class OBJ_Key extends SuperObject {
    public OBJ_Key() {
        name = "Key";
        try {
            image = ImageIO.read(new FileInputStream("TPP Game Project/res/Objects/key.png"));

            //solidArea.x = 6; this would be to customize hit box of items.
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
