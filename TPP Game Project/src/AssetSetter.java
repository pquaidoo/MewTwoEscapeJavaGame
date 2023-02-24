public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        int mapNum= 0;
        gp.obj[mapNum][0] = new OBJ_Key(gp);
        gp.obj[mapNum][0].worldX = 170 * gp.tileSize; //object location
        gp.obj[mapNum][0].worldY = 80 * gp.tileSize;

        gp.obj[mapNum][1] = new OBJ_Key(gp);
        gp.obj[mapNum][1].worldX = 80 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 170 * gp.tileSize;

        gp.obj[mapNum][2] = new OBJ_Key(gp);
        gp.obj[mapNum][2].worldX = 150 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 100 * gp.tileSize;

        gp.obj[mapNum][3] = new OBJ_Door(gp);
        gp.obj[mapNum][3].worldX = 125 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 109 * gp.tileSize;

        gp.obj[mapNum][4] = new OBJ_Door(gp);
        gp.obj[mapNum][4].worldX = 121 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 109 * gp.tileSize;

        gp.obj[mapNum][5] = new OBJ_Door(gp);
        gp.obj[mapNum][5].worldX = 125 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 73 * gp.tileSize;

        gp.obj[mapNum][3] = new OBJ_Door(gp);
        gp.obj[mapNum][3].worldX = 125 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 109 * gp.tileSize;

        gp.obj[mapNum][6] = new OBJ_Chest(gp);
        gp.obj[mapNum][6].worldX = 125 * gp.tileSize;
        gp.obj[mapNum][6].worldY = 68 * gp.tileSize;

        gp.obj[mapNum][7] = new OBJ_Boots(gp);
        gp.obj[mapNum][7].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][7].worldY = 42 * gp.tileSize;


    }
    public void setNPC() {
        int mapNum= 0;
        gp.npc[mapNum][0] = new NPC_OldMan(gp);
        gp.npc[mapNum][0].worldX = 123 * gp.tileSize;
        gp.npc[mapNum][0].worldY = 123 * gp.tileSize;
    }
    public void setMonster() {
        int mapNum= 0;
        gp.monster[mapNum][0] = new MON_GreenSlime(gp);
        gp.monster[mapNum][0].worldX = gp.tileSize * 23;
        gp.monster[mapNum][0].worldY = gp.tileSize * 36;

        gp.monster[mapNum][1] = new MON_GreenSlime(gp);
        gp.monster[mapNum][1].worldX = gp.tileSize * 23;
        gp.monster[mapNum][1].worldY = gp.tileSize * 37;

        gp.monster[mapNum][2] = new MON_Boss(gp);
        gp.monster[mapNum][2].worldX = gp.tileSize * 125;
        gp.monster[mapNum][2].worldY = gp.tileSize * 80;


    }
}
