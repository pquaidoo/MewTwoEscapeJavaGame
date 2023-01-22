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

    Thread gameThread;//Creates time in game for FPS , implements runnable, calls run method

    public GamePanel( ){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //improved rendering performance
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();//calls run method
    }
    @Override
    public void run() {//Game loop
        while(gameThread!=null){

            System.out.println("game loop lmao");// prints in terminal
            //1 UPDATE: update info such as char positions
            update();//calls update method.


            //2 DRAW: shows on screen with updated info
            repaint();//calls paintComponent method.
        }
    }
    public void update(){

    }
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);                 //Referencing JPanel

        Graphics2D graphics2= (Graphics2D)graphics;     //Graphics2D is a class that helps control sophisticated visuals.

        graphics2.setColor(Color.white);    //

        graphics2.fillRect(100,100, /*width*/ tileSize, /*height*/ tileSize);   //Player Character

        graphics2.dispose();    //Helps Performance

    }
}
