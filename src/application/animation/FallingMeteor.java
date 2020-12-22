package application.animation;

import application.model.meteor.Meteor;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class FallingMeteor {
	Timeline timeline;

	public void play(Meteor meteor) {
		double speed = meteor.getSpeed();
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE); // animation continue
		KeyFrame k = new KeyFrame(Duration.seconds(10 / speed), new KeyValue(meteor.translateYProperty(), 900),
				new KeyValue(meteor.rotateProperty(), 360));
		// creation d'une frame de 2 seconds pour changer de status des étoiles
		timeline.getKeyFrames().add(k); // ajouter la frame de changement de status
		timeline.setAutoReverse(true); // retour au status initial
		timeline.play();
	}
}
