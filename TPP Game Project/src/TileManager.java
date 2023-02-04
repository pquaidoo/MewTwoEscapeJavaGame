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

                while (col < gp.maxWorldCol) {
                    String numbers[]=line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;

                }
                if(col==gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
        }catch(Exception e){

        }
    }
        public void draw(Graphics2D graphics2){
            int worldCol = 0;
            int worldRow = 0;

            while (worldCol < gp.maxWorldCol&& worldRow<gp.maxWorldRow){

                int tileNum=mapTileNum[worldCol][worldRow];


                //usually would display only screen size from 0,0
                //with this figures out where player is and displays pixels surrounding them.
                //+gp.player.screenX screen makes it so player is not displayed in right top corner.
                int worldX = worldCol*gp.tileSize;
                int worldY = worldRow*gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;


                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {//doesn't display pixels if its within camera

                    graphics2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
                worldCol++;

                if(worldCol == gp.maxWorldCol){
                    worldCol = 0;
                    worldRow++;
                }

            }

        }

}
