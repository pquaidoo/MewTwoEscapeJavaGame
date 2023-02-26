public class OBJ_Player_Projectile extends Projectile{
    int VelX;
    int VelY;
    public OBJ_Player_Projectile(GamePanel gp,int x,int y, int mx, int my) {
        super(gp);
        VelX = (mx-x)/10;
        VelY = (my-y)/10;
        set2(VelX, VelY, gp.player);
        System.out.println("firing");

    }

}
