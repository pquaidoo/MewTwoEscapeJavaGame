import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Character{

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
        solidArea.x = 8;
        solidArea.y = 8;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void getImage(){
        up1 = setup("TPP Game Project/res/npc/oldman_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("TPP Game Project/res/npc/oldman_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("TPP Game Project/res/npc/oldman_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("TPP Game Project/res/npc/oldman_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("TPP Game Project/res/npc/oldman_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("TPP Game Project/res/npc/oldman_right_2",gp.tileSize,gp.tileSize);
        left1 = setup("TPP Game Project/res/npc/oldman_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("TPP Game Project/res/npc/oldman_left_2",gp.tileSize,gp.tileSize);
    }
    public void setAction() {

        if(onPath == true) {
            int goalCol = 39;
            int goalRow = 7;
            searchPath(goalCol, goalRow);

        }else {
            actionLockCounter++;

            if (actionLockCounter == 120) { // Slows down NPC so he moves every 2 seconds (60FPS)
                Random random = new Random();
                int i = random.nextInt(100) + 1; //random num 1 - 100

                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 65 && i <= 100) {
                    direction = "right";
                }

                actionLockCounter = 0;
            }
        }

    }
    public void setDialogue() {
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So you've came to the island\n for the secret treasure...";
        dialogues[2] = "I used to be a great wizard but now... \nI'm too old for taking an adventure";
        dialogues[3] = "STOP RIGHT THERE, CRIMINAL\n SCUM.";

    }
    public void speak() {
        super.speak();
        onPath = true;
    }
}
