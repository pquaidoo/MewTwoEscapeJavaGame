import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound {
   // Class testClass = getClass();

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() throws MalformedURLException {
        //soundURL[0] = getClass().getResource("/TPP Game Project/res/sound/BlueBoyAdventure.wav");
        File file = new File("TPP Game Project/res/sound/BlueBoyAdventure.wav");//Alternative way to get sound
        soundURL[0] = file.toURI().toURL();
        File file1 =new File("TPP Game Project/res/sound/coin.wav");
        soundURL[1] = file1.toURI().toURL();
        File file2 = new File("TPP Game Project/res/sound/fanfare.wav");
        soundURL[2] = file2.toURI().toURL();
        File file3 = new File("TPP Game Project/res/sound/powerup.wav");
        soundURL[3] = file3.toURI().toURL();
        File file4 = new File("TPP Game Project/res/sound/unlock.wav");
        soundURL[4] = file4.toURI().toURL();
    }

    public void setFile(int i){
        try{

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException e) {

        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
