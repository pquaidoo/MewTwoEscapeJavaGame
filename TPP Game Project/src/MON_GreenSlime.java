import java.util.Random;

public class MON_GreenSlime extends Character {
    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        type = 2;
        name = "Green Slime";
        speed = 1;
        maxLife = 15;
        life = maxLife;
        attack = 5;
        defense = 0;
        projectile = new OBJ_ElecticBall(gp);


        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage() {
        up1 = setup("TPP Game Project/res/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("TPP Game Project/res/monster/greenslime_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("TPP Game Project/res/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("TPP Game Project/res/monster/greenslime_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("TPP Game Project/res/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("TPP Game Project/res/monster/greenslime_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("TPP Game Project/res/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("TPP Game Project/res/monster/greenslime_down_2",gp.tileSize,gp.tileSize);
    }
    public void setAction() {

        actionLockCounter++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) +1;

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
        int i = new Random().nextInt(100)+1;
        if(i>99&&projectile.alive==false&&shotAvailableCounter == 45){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter=0;//d
        }
    }
    public void damageReaction() {

        actionLockCounter = 0;
        if(gp.player.direction.equals("up-right") || gp.player.direction.equals("down-right")) {
            direction = "right";
        } else if(gp.player.direction.equals("up-left") || gp.player.direction.equals("down-left")) {
            direction = "left";
        } else {
            direction = gp.player.direction;
        }

    }
}
