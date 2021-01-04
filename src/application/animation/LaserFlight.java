package application.animation;

import application.fonction.GameLoop;
import application.model.spaceship.Laser;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class LaserFlight {

	Timeline timeline;

	public void exec(Laser laser) {
		timeline = new Timeline();
		KeyFrame k = new KeyFrame(Duration.seconds(0.5),
				new KeyValue(laser.translateYProperty(), laser.getTranslateY() - 950));
		timeline.getKeyFrames().add(k);
		timeline.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				GameLoop.getController().deleteWeapon(laser);
			}
		});
		timeline.play();
	}

}
