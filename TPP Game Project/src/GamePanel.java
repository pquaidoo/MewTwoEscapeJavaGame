import javax.swing.*;
import java.awt.*;
import Object.SuperObject;

/**
 *  Instantiates Game and runs game loop.
 */
public class GamePanel extends JPanel implements Runnable {
    //Screen settings
        //Tile sizes
    final int originalTileSize =16;             //16x16 tiles
    final int scale = 3;

    final int tileSize = originalTileSize*scale;// 48x48 tile

    //World Settings
    public final int maxWorldCol=50;
    public final int maxWorldRow=50;
    public final int worldWidth =tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

        //Screen Resolution
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //786 px
    final int screenHeight = tileSize * maxScreenRow; //576 px
    int FPS =60;
    TileManager tileM = new TileManager(this);

    KeyHandler keyH= new KeyHandler();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;                              //Creates time in game for FPS , implements runnable, calls run method
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    /**
     * Constructor for game panel that instantiates screen size, color, input and other cool jazz.
     */
    public GamePanel( ){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);               //improved rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);                    //makes gamePanel "focused to receive input", so basically makes input faster ig.
    }

    public void setupGame() {
        aSetter.setObject();
    }

    /**
     *  Creates and starts game.
     */
    public void startGameThread(){
        gameThread = new Thread(this);          //Creates new Game.
        gameThread.start();                         //calls run method.
    }

    /**
     *  Game loop for changing data while in real time and managing frame rate.
     */
    @Override
    public void run() {//Game loop
        //Throttles actual computer speed to 60FPS, so it can update properly.
        double drawInterval = 1000000000/FPS;                   //0.0166666666 seconds if 60 fps.
        double nextDrawTime = System.nanoTime() +drawInterval;
        //Calls update and repaint method, repaint(paintComponent) only happens 60FPS
        while(gameThread!=null){
            long currentTime = System.nanoTime();
            //1 UPDATE: update info such as char positions
            update();
            //2 DRAW: shows on screen with updated info
            repaint();
            try {
                double remainingTime = nextDrawTime -currentTime;
                remainingTime=remainingTime/1000000;            //converts nanoseconds to milliseconds.
                if(remainingTime<0){
                    remainingTime=0;
                }
                Thread.sleep((long) remainingTime);              //"sleeps" until it's time to update
                nextDrawTime+=drawInterval;
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    /**
     * Calls update methods in classes
     */
    public void update(){

        player.update();                            //Calls player update method.

    }

    /**
     * Calls draw methods in classes
     *
     * @param graphics the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);                 //Referencing JPanel

        Graphics2D graphics2= (Graphics2D)graphics;     //Graphics2D is a class that helps control sophisticated visuals.

        //TILE
        tileM.draw(graphics2);                          //Tiles before player so tiles don't cover player.

        //OBJECT
        for(int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(graphics2, this);
            }
        }

        //PLAYER
        player.draw(graphics2);                         //Calls player draw method.

        graphics2.dispose();                            //Helps Performance.

    }
}
