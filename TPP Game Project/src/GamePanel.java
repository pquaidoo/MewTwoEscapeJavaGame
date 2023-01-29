import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //Screen settings
        //Tile sizes
    final int originalTileSize =16;//16x16 tiles
    final int scale = 3;

    final int tileSize = originalTileSize*scale;// 48x48 tile

        //Screen Resolution
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //786 px
    final int screenHeight = tileSize * maxScreenRow; //576 px
    int FPS =60;

    KeyHandler keyH= new KeyHandler();
    Thread gameThread;//Creates time in game for FPS , implements runnable, calls run method
    Player player = new Player(this, keyH);

    //Set res.player's default position
    int playerX=100;
    int playerY=100;
    int playerSpeed=4;

    public GamePanel( ){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //improved rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);    //makes gamePanel "focused to receive input", so basically makes input faster ig.
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();//calls run method
    }

    @Override
    public void run() {//Game loop

        double drawInterval = 1000000000/FPS;//0.0166666666 seconds if 60 fps
        double nextDrawTime = System.nanoTime() +drawInterval;

        while(gameThread!=null){

            long currentTime = System.nanoTime();


            //System.out.println("game loop lmao");// prints in terminal
            //1 UPDATE: update info such as char positions
            update();//calls update method.



            //2 DRAW: shows on screen with updated info
            repaint();//calls paintComponent method.

            try {//Checks for when its time to update to next frame, "sleeps" until it's time to update
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime=remainingTime/1000000;//converts nano seconds to miliseconds.

                if(remainingTime<0){
                    remainingTime=0;
                }

                Thread.sleep((long) remainingTime);//bc of this need try and catch

                nextDrawTime+=drawInterval;

            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    public void update(){

    player.update();

    }

    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);                 //Referencing JPanel

        Graphics2D graphics2= (Graphics2D)graphics;     //Graphics2D is a class that helps control sophisticated visuals.

        player.draw(graphics2);

        graphics2.dispose();    //Helps Performance

    }
}
