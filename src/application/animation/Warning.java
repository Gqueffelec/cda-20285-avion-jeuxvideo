package application.animation;

import application.music.SoundLauncher;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Warning {
	Timeline timeline;

	public void exec(Pane pane) {
		SoundLauncher alert = new SoundLauncher();
		alert.music("boss");
		timeline = new Timeline();
		timeline.setCycleCount(2);
		KeyFrame k = new KeyFrame(Duration.seconds(1), new KeyValue(pane.opacityProperty(), 0.2));
		KeyFrame k1 = new KeyFrame(Duration.seconds(2), new KeyValue(pane.opacityProperty(), 0));
		timeline.getKeyFrames().add(k);
		timeline.getKeyFrames().add(k1);
		timeline.autoReverseProperty();
		timeline.play();
	}
}
