import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {

  public void playMusic() {
    try {
      String musicLocation = "Files\\jingle.wav";
      File musicPath = new File(musicLocation);
      if (musicPath.exists()) {
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInput);
        clip.start();
        //clip.loop(Clip.LOOP_CONTINUOUSLY);

        long clipTimePosition = clip.getMicrosecondPosition();
        clip.stop();

        clip.setMicrosecondPosition(clipTimePosition);
        clip.start();

      } else {
        System.out.println("Can't find file");
      }
    } catch (Exception ex) {
      ex.printStackTrace();

    }
  }
}
