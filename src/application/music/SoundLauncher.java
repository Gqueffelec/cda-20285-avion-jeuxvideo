package application.music;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundLauncher {

	public void music(String titre) {
		Media hit = new Media(getClass().getResource("/application/assets/" + titre + ".mp3").toExternalForm());
		MediaPlayer soundPlayer = new MediaPlayer(hit);
		soundPlayer.setVolume(0.02);
		soundPlayer.play();
	}
}
