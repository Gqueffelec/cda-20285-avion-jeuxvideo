package application.music;

import java.nio.file.Paths;

import application.Main;
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

	public static void music() {
		Media hit = new Media(
				Paths.get("src\\application\\assets\\startBattle.mp3").toAbsolutePath().toUri().toString());
		backgroundPlayer = new MediaPlayer(hit);
		backgroundPlayer.setVolume(0.1);
		backgroundPlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				Main.setLaunch(false);
			}
		});
		backgroundPlayer.play();
	}

	public static void musicFight() {
		Media hit = new Media(
				Paths.get("src\\application\\assets\\battleLoop.mp3").toAbsolutePath().toUri().toString());
		backgroundPlayer = new MediaPlayer(hit);
		backgroundPlayer.setVolume(0.1);
		backgroundPlayer.setAutoPlay(true);
		backgroundPlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				backgroundPlayer.seek(Duration.ZERO);
			}
		});
		backgroundPlayer.play();
	}

	public static void stop() {
		backgroundPlayer.stop();
	}

}
