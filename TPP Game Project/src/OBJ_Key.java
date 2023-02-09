import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
public class OBJ_Key extends SuperObject {
    GamePanel gp;
    public OBJ_Key(GamePanel gp) {
        this.gp = gp;
        name = "Key";
        try {
            image = ImageIO.read(new FileInputStream("TPP Game Project/res/Objects/key.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

            //solidArea.x = 6; this would be to customize hit box of items.
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
