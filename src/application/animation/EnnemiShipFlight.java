package application.animation;

import application.model.ennemi.ship.EnnemiSpaceShip;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class EnnemiShipFlight {
	Timeline timeline;

	public void exec(EnnemiSpaceShip ennemi) {
		timeline = new Timeline();
		timeline.setCycleCount(1);
		KeyFrame k1 = new KeyFrame(Duration.seconds(3), new KeyValue(ennemi.translateXProperty(), 250));
		timeline.getKeyFrames().add(k1);
		timeline.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				timeline = new Timeline();
				timeline.setCycleCount(Timeline.INDEFINITE);
				KeyFrame k2 = new KeyFrame(Duration.seconds(3), new KeyValue(ennemi.translateXProperty(), -250));
				timeline.getKeyFrames().add(k2);
				timeline.setAutoReverse(true);
				timeline.play();
			}
		});
		timeline.play();
	}

	public void stop() {
		timeline.stop();
	}

}
