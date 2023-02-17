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
    public boolean attackCanceled = false;
    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH=keyH;
        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;  //sets camera size

        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea = new Rectangle(0,0,36,36);

        getPlayerAttackImage();
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
        life = 5;//maxlife;
        strength =1;
        dexterity =1;
        exp = 0;
        nextLevelExp= 5;
        coin= 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack= getAttack();
        defense = getDefense();
    }
    public int getAttack(){
        return attack = strength*currentWeapon.attackValue;
    }
    public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
    }


    /**
     *  Gets player sprites from res directory.
     */
    public void getplayerImage(){
        //up1. ImageIO.read(getClass().getResourceAsStream("TPP Game Project/res/player/boy_up_1"));
        //what was used in tutorial ^
        up1 = setup("TPP Game Project/res/player/boy_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("TPP Game Project/res/player/boy_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("TPP Game Project/res/player/boy_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("TPP Game Project/res/player/boy_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("TPP Game Project/res/player/boy_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("TPP Game Project/res/player/boy_right_2",gp.tileSize,gp.tileSize);
        left1 = setup("TPP Game Project/res/player/boy_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("TPP Game Project/res/player/boy_left_2",gp.tileSize,gp.tileSize);
    }
    public void getPlayerAttackImage(){
        attackUp1 = setup("TPP Game Project/res/player/boy_attack_up_1",gp.tileSize,gp.tileSize*2);
        attackUp2 = setup("TPP Game Project/res/player/boy_attack_up_2",gp.tileSize,gp.tileSize*2);
        attackDown1 = setup("TPP Game Project/res/player/boy_attack_down_1",gp.tileSize,gp.tileSize*2);
        attackDown2 = setup("TPP Game Project/res/player/boy_attack_down_2",gp.tileSize,gp.tileSize*2);
        attackRight1 = setup("TPP Game Project/res/player/boy_attack_right_1",gp.tileSize*2,gp.tileSize);
        attackRight2 = setup("TPP Game Project/res/player/boy_attack_right_2",gp.tileSize*2,gp.tileSize);
        attackLeft1 = setup("TPP Game Project/res/player/boy_attack_left_1",gp.tileSize*2,gp.tileSize);
        attackLeft2 = setup("TPP Game Project/res/player/boy_attack_left_2",gp.tileSize*2,gp.tileSize);


    }
    /**
         *  Updates the player data (60FPS).
     */
    public void update(){
        //System.out.println(attacking);
        if(attacking == true){
            attacking();
            //System.out.println("attacking is true");
        } else if(keyH.upPressed==true||keyH.downPressed==true||
                keyH.leftPressed==true||keyH.rightPressed==true|| keyH.enterPressed == true) { //Checks all player input to see if moving. (so player animation doesn't move when player is still).
            //System.out.println("attacking is false");
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
            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check object collision
            int objIndex = gp.cChecker.checkObject(this,true);
            pickupObject(objIndex);

            // Check NPC collision
            int npcIndex = gp.cChecker.checkCharacter(this, gp.npc);
            interactNPC(npcIndex);

            // Check monster collision
            int monsterIndex = gp.cChecker.checkCharacter(this, gp.monster);
            contactMonster(monsterIndex);

            // Check event
            gp.eHandler.checkEvent();



            //if collision is false player can move
            if(collisionOn == false && keyH.enterPressed == false){

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
                                       worldX += speed;
                }
            }

            if(keyH.enterPressed == true && attackCanceled== false){
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }
            attackCanceled = false;
           gp.keyH.enterPressed = false;//resets



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

        if(invincible == true) {

            invincibleCounter++;
            if(invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void attacking(){
        spriteCounter++;

        if(spriteCounter <=5){//for speed of animation
            spriteNum =1;

        }if(spriteCounter>5 &&spriteCounter<=25){
            spriteNum=2;

            //SAVE CURRENT X, Y, AND SOLID AREA
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            //ADJUST PLAYER'S X,Y, AND SOLID AREA
            switch(direction){
                case"up":worldY-=attackArea.height; break;
                case"down":worldY+=attackArea.height;break;
                case"left":worldX-=attackArea.width;break;
                case"right":worldX+=attackArea.width; break;

            }
            //attack area becomes solid area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            //check monster collision with the updated worldx, worldy and solid area
            int monsterIndex = gp.cChecker.checkCharacter(this,gp.monster);
            damageMonster(monsterIndex);

            worldX=currentWorldX;
            worldY=currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;


        }
        if(spriteCounter>25){
            spriteNum=1;
            spriteCounter =0;
            attacking=false;
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

        if(gp.keyH.enterPressed==true){
            if(i != 999) {
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();

                }
            }
        }


    public void contactMonster(int i) {
        if(i != 999) {
            if(invincible == false) {
                gp.playSE(6);
                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i){
        if(i !=999){

            if(gp.monster[i].invincible == false){
                gp.playSE(5);
                gp.monster[i].life-=1;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();
                System.out.println(gp.monster[i].life);
                if(gp.monster[i].life<=0){
                    gp.monster[i].dying = true;
                }
            }
        }
    }
    /**
     * Updates screen with player images (60FPS).
     */
    public void draw(Graphics2D graphics2) {
//        graphics2.setColor(Color.white);
//        graphics2.fillRect(x,y, /*width*/ gp.tileSize, /*height*/ gp.tileSize);   //Player Character

        //When direction switches: calls appropriate sprite & alternates between them for animation.

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize;//corrects displacement of wide sprite
                    if (spriteNum == 1) {

                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                }
                break;
            case "right", "up-right", "down-right":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }
                }

                break;
            case "left", "up-left", "down-left":
                if(attacking == false) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if (attacking == true) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }
                }
                break;
        }

        if(invincible == true) {
            graphics2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        graphics2.drawImage(image, tempScreenX, tempScreenY, null);

        // Reset alpha
        graphics2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //Changes image, puts it where it goes, changes how big it is.
       // graphics2.drawImage(image, screenX, screenY,null);
       // graphics2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);


    }

}
