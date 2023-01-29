import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Character{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){

        this.gp=gp;
        this.keyH=keyH;
        setDefaultValues();
        getplayerImage();
        direction="down";

    }
    public void getplayerImage(){
        try{
            up1= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_up_1.png"));
            up2= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_up_2.png"));
            down1= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_down_1.png"));
            down2= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_down_2.png"));
            right1= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_right_1.png"));
            right2= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_right_2.png"));
            left1= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_left_1.png"));
            left2= ImageIO.read(new FileInputStream("TPP Game Project/res/player/boy_left_2.png"));





        }catch(IOException e){
            e.printStackTrace();


        }
    }
    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
    }
    public void update(){
        //Array coordinates so values increase as they go right or down
        if(keyH.upPressed){//Checks if true
            direction = "up";
            y-=speed;
        } else if (keyH.downPressed) {
            direction = "down";
            y+=speed;

        } else if (keyH.rightPressed) {
            direction = "right";
            x+=speed;

        }else if(keyH.leftPressed){
            direction = "left";
            x-=speed;
        }
    }

    public void draw(Graphics2D graphics2){
//        graphics2.setColor(Color.white);    //
//        graphics2.fillRect(x,y, /*width*/ gp.tileSize, /*height*/ gp.tileSize);   //Player Character

        BufferedImage image = null;
        switch(direction) {
        case "up":
            image = up1;
            break;
        case "down":
            image = down1;
            break;
        case "right":
            image = right1;
            break;
        case "left":
            image = left1;
            break;

        }
        graphics2.drawImage(image, x, y, gp.tileSize,gp.tileSize,null);
    }
}
