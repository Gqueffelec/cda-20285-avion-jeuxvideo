package application.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MovingBackground {
	Timeline timeline;
	private static int speed = 5;

	public void exec(StackPane[] background) {

		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame k = new KeyFrame(Duration.seconds(speed),
				new KeyValue(background[0].translateYProperty(), background[0].getHeight() + 600),
				new KeyValue(background[1].translateYProperty(), background[1].getHeight() + 600),
				new KeyValue(background[2].translateYProperty(), background[2].getHeight() + 600));
		timeline.getKeyFrames().add(k);
		timeline.play();
	}

	public void speedUp() {
		if (speed != 1) {
			speed--;
		}
	}
}
