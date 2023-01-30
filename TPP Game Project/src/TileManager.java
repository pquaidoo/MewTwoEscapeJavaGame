import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tiles[] tile;


        public TileManager(GamePanel gp){
            this.gp=gp;
            tile = new Tiles[10];

            getTileImage();
        }

        public void getTileImage(){

            try{
                //ImageIO.read(get.Class().getResourceAsStream("TPP Game Project/tiles/grass.png"));
                //tutorial version ^
                tile[0] = new Tiles();
                tile[0].image = ImageIO.read(new FileInputStream("TPP Game Project/res/tiles/grass.png"));
                tile[1] = new Tiles();
                tile[1].image = ImageIO.read(new FileInputStream("TPP Game Project/res/tiles/water.png"));
                tile[2] = new Tiles();
                tile[2].image = ImageIO.read(new FileInputStream("TPP Game Project/res/tiles/wall.png"));

            }catch(IOException e){
                e.printStackTrace();
            }
        }
        public void draw(Graphics2D graphics2){
            graphics2.drawImage(tile[0].image,0,0,gp.tileSize,gp.tileSize, null);
        }

}
