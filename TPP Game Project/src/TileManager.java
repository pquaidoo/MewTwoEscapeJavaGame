import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gp;
    Tiles[] tile;

    int mapTileNum[][];


        public TileManager(GamePanel gp){
            this.gp=gp;
            tile = new Tiles[10];
            mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
            getTileImage();
            loadMap("TPP Game Project/res/maps/map01.txt");
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
                tile[3] = new Tiles();
                tile[3].image = ImageIO.read(new FileInputStream("TPP Game Project/res/tiles/earth.png"));
                tile[4] = new Tiles();
                tile[4].image = ImageIO.read(new FileInputStream("TPP Game Project/res/tiles/tree.png"));
                tile[5] = new Tiles();
                tile[5].image = ImageIO.read(new FileInputStream("TPP Game Project/res/tiles/sand.png"));

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    public void loadMap(String mapFilePath){
        try{
            InputStream is = new FileInputStream((mapFilePath));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));//reads contents of text file.
            int col=0;
            int row=0;
            while(col<gp.maxWorldCol && row< gp.maxWorldRow){

                String line = br.readLine();//reads whole line of text.

                while (col < gp.maxScreenCol) {
                    String numbers[]=line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;

                }
                if(col==gp.maxScreenCol){
                    col=0;
                    row++;
                }
            }
        }catch(Exception e){

        }
    }
        public void draw(Graphics2D graphics2){
            int col = 0;
            int row=0;
            int x=0;
            int y=0;
            while (col < gp.maxScreenCol&& row<gp.maxScreenRow){

                int tileNum=mapTileNum[col][row];

                graphics2.drawImage(tile[tileNum].image,x ,y, gp.tileSize,gp.tileSize,null);
                col++;
                x+=gp.tileSize;
                if(col == gp.maxScreenCol){
                    col=0;
                    x=0;
                    row++;
                    y+=gp.tileSize;
                }

            }

        }

}
