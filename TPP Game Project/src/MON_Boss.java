import jdk.jfr.Event;

import java.awt.*;
import java.util.Random;

public class MON_Boss extends Character {
    int tempPlayerX;
    int tempPlayerY;
    int lives=1;

    int shotAvailableCounter1;
    public MON_Boss(GamePanel gp) {
        super(gp);
        type = 3;
        name = "Boss";
        speed = 0;
        maxLife = 10;
        life = maxLife;
        attack = 10;
        defense = 0;

        int size = gp.tileSize*5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48*2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage() {
        int i = 5;
        if(inRage == false) {
            up1 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
        }
        if(inRage == true) {
            up1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
        }
        if(isRage == true) {
            up1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
        }
    }
    public void update(){
        super.update();
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if(onPath == false && tileDistance < 5){
            int i = new Random().nextInt(100)+1;
            if(i>50){
                onPath= true;
            }

        }
        if(onPath==true&&tileDistance>20){
            onPath = false;
        }
    }
    public void setAction() {
        if(isRage==true){
            getImage();
            speed++;
            System.out.println("he is mister rage");
        } else if(inRage == false && life < 50) {
            inRage = true;
            getImage();
            speed++;
        }

        if(life == 0&&lives==0) {
            BossDead = true;
            gp.eHandler.setBossbattle(false);
        }else if(life==1){
            reviving=true;
            Revive();
            reviving=false;

        }
        if(getTileDistance(gp.player) < 100) {
            moveTowardPlayer(60);
            if(shotAvailableCounter1%25==0) {
                System.out.println(shotAvailableCounter1+"\n"+"--------");
                tempPlayerX=gp.player.worldX;
                tempPlayerY=gp.player.worldY;
                if(shotAvailableCounter%15==0) {
                    System.out.println(shotAvailableCounter);

                    OBJ_Boss_Projectile proj = new OBJ_Boss_Projectile(gp, this);
                    proj.set(getCenterX(), getCenterY(), tempPlayerX, tempPlayerY, "polar", true, this);
                    gp.projectileList.add(proj);
                }
            }
            if(shotAvailableCounter1==50){

            }
            if(shotAvailableCounter1 == 100){
                    shotAvailableCounter1 = 0;


            }
            if(shotAvailableCounter1 < 100) {
                shotAvailableCounter1++;
            }



        } else {
            tempPlayerX=gp.player.worldX;
            tempPlayerY= gp.player.worldY;
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
    public void checkDrop() {
        gp.obj[0][8] = new OBJ_Key(gp);
        gp.obj[0][8].worldX = worldX;
        gp.obj[0][8].worldY = worldY;
    }
    public void damageReaction() {

        actionLockCounter = 0;

        onPath=true;
        if(gp.player.direction.equals("up-right") || gp.player.direction.equals("down-right")) {
            direction = "right";
        } else if(gp.player.direction.equals("up-left") || gp.player.direction.equals("down-left")) {
            direction = "left";
        } else {
            direction = gp.player.direction;
        }

    }
    public void Revive(){
        lives =0;
        while(life!=maxLife){
            life+=(double)life/10;
            reviving=true;
            isRage=true;
        }
        reviving=false;
        invincible=false;


    }

}

//        type = 2;
//                name = monName;
//                defaultSpeed = 1;
//                speed = defaultSpeed;
//                maxLife = 50;
//                life = maxLife;
//                attack = 10;
//                defense = 2;
//                exp = 50;
//                projectile = new OBJ_ElecticBall(gp);
//                //      knockBackPower = 5;
//
//                int size = gp.tileSize * 5;
//                solidArea.x = 48;
//                solidArea.y = 48;
//                solidArea.width = size - 48 * 2;
//                solidArea.height = size - 48;
//                solidAreaDefaultX = solidArea.x;
//                solidAreaDefaultY = solidArea.y;
//                attackArea.width = 170;
//                attackArea.height = 170;

