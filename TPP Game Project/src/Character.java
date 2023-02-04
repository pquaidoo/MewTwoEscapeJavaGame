import java.awt.image.BufferedImage;

/**
 *  Template class for Player, Enemy, NPC
 */

public class Character {
    String name;
    public int worldX, worldY;
    public int speed;


    public BufferedImage up1, up2, down1, down2, left1,left2, right1, right2;   //Instantiates Sprite Images.
    public String direction;        //Holds direction String.

    public int spriteCounter = 0;   //Used as timer to determine when to switch sprites.
    public int spriteNum = 1;       //Used to set values to individual sprites.


}
