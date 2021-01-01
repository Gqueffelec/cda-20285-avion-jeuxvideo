package application.music;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundLauncher {
	private static MediaPlayer soundPlayer;

	public void music(String titre) {
		Media hit = new Media(getClass().getResource("/application/assets/" + titre + ".mp3").toExternalForm());
		soundPlayer = new MediaPlayer(hit);
		soundPlayer.setVolume(0.2);
		soundPlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				soundPlayer.stop();
			}
		});
		soundPlayer.play();
	}
}
