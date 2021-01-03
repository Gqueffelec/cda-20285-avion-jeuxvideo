package application.music;

import application.fonction.GameLoop;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicMenu {
	private static MediaPlayer backgroundPlayer;

	public void play() {

		Media hit = new Media(getClass().getResource("/application/assets/menu.mp3").toExternalForm());
		backgroundPlayer = new MediaPlayer(hit);
		backgroundPlayer.setVolume(0.02);
		backgroundPlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				GameLoop.setLaunch(false);
			}
		});
		backgroundPlayer.play();
	}

	public void pause() {
		backgroundPlayer.pause();
	}

	public void resume() {
		backgroundPlayer.play();
	}
}
