public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Character chara) {
        int charLeftWorldX = chara.worldX + chara.solidArea.x;
        int charRightWorldX = chara.worldX + chara.solidArea.x + chara.solidArea.width;
        int charTopWorldY = chara.worldY + chara.solidArea.y;
        int charBottomWorldY = chara.worldY + chara.solidArea.y + chara.solidArea.height;

        int charLeftCol = charLeftWorldX / gp.tileSize;
        int charRightCol = charRightWorldX / gp.tileSize;
        int charTopRow = charTopWorldY / gp.tileSize;
        int charBottomRow = charBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (chara.direction) {
            case "up":
                charTopRow = (charTopWorldY - chara.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[charLeftCol][charTopRow];
                tileNum2 = gp.tileM.mapTileNum[charRightCol][charTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    chara.collisionOn = true;
                }
                break;
            case "down":
                charBottomRow = (charBottomWorldY + chara.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[charLeftCol][charBottomRow];
                tileNum2 = gp.tileM.mapTileNum[charRightCol][charBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    chara.collisionOn = true;
                }
                break;
            case "left":
                charLeftCol = (charLeftWorldX - chara.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[charLeftCol][charTopRow];
                tileNum2 = gp.tileM.mapTileNum[charLeftCol][charTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    chara.collisionOn = true;
                }
                break;

            case "right":
                charRightCol = (charRightWorldX + chara.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[charRightCol][charTopRow];
                tileNum2 = gp.tileM.mapTileNum[charRightCol][charTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    chara.collisionOn = true;
                }
                break;

        }
    }
}
