import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Controls player sprites and stats.
 */
public class Player extends Character{

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int hasKey = 0;
    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH=keyH;
        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;  //sets camera size

        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getplayerImage();
        direction="down";
    }

    /**
     *  Sets Default values for player.
     */
    public void setDefaultValues(){
        worldX = gp.tileSize * 24;
        worldY = gp.tileSize * 24;
        speed=4;
        //PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }

    /**
     *  Gets player sprites from res directory.
     */
    public void getplayerImage(){
        //up1. ImageIO.read(getClass().getResourceAsStream("TPP Game Project/res/player/boy_up_1.png"));
        //what was used in tutorial ^
        up1 = setup("TPP Game Project/res/player/boy_up_1");
        up2 = setup("TPP Game Project/res/player/boy_up_2");
        down1 = setup("TPP Game Project/res/player/boy_down_1");
        down2 = setup("TPP Game Project/res/player/boy_down_2");
        right1 = setup("TPP Game Project/res/player/boy_right_1");
        right2 = setup("TPP Game Project/res/player/boy_right_2");
        left1 = setup("TPP Game Project/res/player/boy_left_1");
        left2 = setup("TPP Game Project/res/player/boy_left_2");
    }
    /**
         *  Updates the player data (60FPS).
     */
    public void update(){

        //Checks all player input to see if moving. (so player animation doesn't move when player is still).

        if(keyH.upPressed==true||keyH.downPressed==true||
                keyH.leftPressed==true||keyH.rightPressed==true) {

            //Subtracts speed from x & y coordinates and sets direction for sprite.
            if(keyH.upPressed && keyH.leftPressed) {
                direction = "up-left";

            } else if(keyH.upPressed && keyH.rightPressed) {
                direction = "up-right";

            } else if(keyH.downPressed && keyH.leftPressed) {
                direction = "down-left";

            } else if(keyH.downPressed && keyH.rightPressed) {
                direction = "down-right";

            } else if (keyH.upPressed) {
                direction = "up";

            } else if (keyH.downPressed) {
                direction = "down";

            } else if (keyH.rightPressed) {
                direction = "right";

            } else if (keyH.leftPressed) {
                direction = "left";

            }
            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check object collision
            int objIndex = gp.cChecker.checkObject(this,true);
            pickupObject(objIndex);

            //Check NPC collision
            int npcIndex = gp.cChecker.checkCharacter(this, gp.npc);
            interactNPC(npcIndex);


            //if collision is false player can move
            if(collisionOn == false){

                switch (direction){
                    case "up": worldY -= speed;
                        break;
                    case "down": worldY += speed;
                        break;
                    case "left": worldX -= speed;
                        break;
                    case "right": worldX += speed;
                        break;
                    case "up-left": worldX -= speed;
                                    worldY -= speed;
                        break;
                    case "up-right": worldY -= speed;
                                     worldX += speed;
                        break;
                    case "down-left": worldY += speed;
                                      worldX -= speed;
                        break;
                    case "down-right": worldY += speed;
                                       worldY += speed;
                }
            }


            //Alternates sprite number so draw method can switch sprites for animation.

            spriteCounter++;    //every frame increases counter by 1
            if (spriteCounter > 10) {    //changes sprite every 10 frames
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickupObject(int i){
        if(i !=999){

            String objectName= gp.obj[i].name;
            switch(objectName){
                case"Key":
                    gp.playSE(1);//calls sound effect according to soundUrl array.
                    hasKey++;
                    gp.obj[i]=null;
                    gp.ui.showMessage("You got a key!");
                    break;
                case"Door":
                    if(hasKey>0){
                        gp.playSE(4);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door!");
                    }
                    else {
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "Boots":
                    gp.playSE(3);
                    speed+=10;          // if you make this 100 you weirdly can barely move.
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed up!");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(2);
                    break;
            }
        }
    }
    public void interactNPC(int i) {
        if(i != 999) {

        }
    }

    /**
     * Updates screen with player images (60FPS).
     */
    public void draw(Graphics2D graphics2){
//        graphics2.setColor(Color.white);
//        graphics2.fillRect(x,y, /*width*/ gp.tileSize, /*height*/ gp.tileSize);   //Player Character

        //When direction switches: calls appropriate sprite & alternates between them for animation.

        BufferedImage image = null;
        switch(direction) {
            case "up":
                if (spriteNum==1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }

                break;
            case "down":
                if (spriteNum==1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "right":
                if (spriteNum==1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;
            case "left":
                if (spriteNum==1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;
            }

        //Changes image, puts it where it goes, changes how big it is.
        graphics2.drawImage(image, screenX, screenY,null);
    }

}
