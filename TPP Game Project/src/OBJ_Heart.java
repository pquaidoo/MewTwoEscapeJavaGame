import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Heart extends Character {
    GamePanel gp;
    public OBJ_Heart(GamePanel gp) {
        super(gp);

        name = "Heart";
        image = setup("TPP Game Project/res/Objects/heart_full");
        image2 = setup("TPP Game Project/res/Objects/heart_half");
        image3 = setup("TPP Game Project/res/Objects/heart_blank");

    }
}
