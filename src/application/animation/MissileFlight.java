package application.animation;

import application.Main;
import application.model.spaceship.Missile;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MissileFlight {
	Timeline timeline;

	public void exec(Missile missile, ImageView fire) {

		timeline = new Timeline();
		KeyFrame k = new KeyFrame(Duration.seconds(1),
				new KeyValue(missile.translateYProperty(), missile.getTranslateY() - 950),
				new KeyValue(fire.translateYProperty(), fire.getTranslateY() - 950));
		timeline.getKeyFrames().add(k);
		timeline.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Main.getController().deleteMissile(missile);
			}
		});
		timeline.play();
	}

}
