import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Gets input from keyboard and sets booleans accordingly so game can react to it.
 */
public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {}

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    /**
     * Changes booleans if key is pressed.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();      //returns int of keyCode associated with key pressed.

        if(code == KeyEvent.VK_W){
            upPressed=true;
        }
        if(code == KeyEvent.VK_S){
            downPressed=true;

        }
        if(code == KeyEvent.VK_A){
            leftPressed=true;

        }
        if(code == KeyEvent.VK_D){
            rightPressed=true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            if(gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            }
            else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }


    }

    /**
     * Changes boolean if button is no longer pressed.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

        int code= e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed=false;
        }
        if(code == KeyEvent.VK_S){
            downPressed=false;

        }
        if(code == KeyEvent.VK_A){
            leftPressed=false;

        }
        if(code == KeyEvent.VK_D){
            rightPressed=false;
        }

    }
}
