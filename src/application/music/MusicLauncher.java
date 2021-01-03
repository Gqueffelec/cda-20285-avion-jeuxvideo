package application.music;

import application.fonction.GameLoop;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * 
 * @author Guillaume
 *
 *
 */

public class MusicLauncher {

	private static MediaPlayer backgroundPlayer;

	public void music() {

		Media hit = new Media(getClass().getResource("/application/assets/startBattle.mp3").toExternalForm());
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

	public void musicFight() {
		Media hit = new Media(getClass().getResource("/application/assets/battleLoop.mp3").toExternalForm());
		backgroundPlayer = new MediaPlayer(hit);
		backgroundPlayer.setVolume(0.02);
		backgroundPlayer.setAutoPlay(true);
		backgroundPlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				backgroundPlayer.seek(Duration.ZERO);
			}
		});
		backgroundPlayer.play();
	}

	public void stop() {
		backgroundPlayer.stop();
	}

}
