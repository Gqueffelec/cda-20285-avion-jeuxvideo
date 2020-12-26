package application.music;

import java.nio.file.Paths;

import application.Main;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundLauncher {
	private static MediaPlayer soundPlayer;

	public static void music(String titre) {
		Media hit = new Media(
				Paths.get("src\\application\\assets\\" + titre + ".wav").toAbsolutePath().toUri().toString());
		soundPlayer = new MediaPlayer(hit);
		soundPlayer.setVolume(0.2);
		soundPlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				Main.setLaunch(false);
			}
		});
		soundPlayer.play();
	}
}
