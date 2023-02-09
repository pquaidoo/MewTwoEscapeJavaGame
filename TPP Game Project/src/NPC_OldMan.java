import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Character{
    GamePanel gp;
    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
    }
    public void getImage(){
        up1 = setup("TPP Game Project/res/npc/oldman_up_1");
        up2 = setup("TPP Game Project/res/npc/oldman_up_2");
        down1 = setup("TPP Game Project/res/npc/oldman_down_1");
        down2 = setup("TPP Game Project/res/npc/oldman_down_2");
        right1 = setup("TPP Game Project/res/npc/oldman_right_1");
        right2 = setup("TPP Game Project/res/npc/oldman_right_2");
        left1 = setup("TPP Game Project/res/npc/oldman_left_1");
        left2 = setup("TPP Game Project/res/npc/oldman_left_2");
    }
    public void setAction() {

        actionLockCounter++;

        if(actionLockCounter == 120) { // Slows down NPC so he moves every 2 seconds (60FPS)
            Random random = new Random();
            int i = random.nextInt(100) +1; //random num 1 - 100

            if(i <= 25) {
                direction = "up";
            }
            if(i > 25 && i <= 50) {
                direction =  "down";
            }
            if(i > 50 && i <= 75) {
                direction = "left";
            }
            if(i > 65 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }

    }
}
