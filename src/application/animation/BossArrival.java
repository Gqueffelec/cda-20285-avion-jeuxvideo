package application.animation;

import application.model.ennemi.ship.BossShip;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class BossArrival {
	Timeline timeline;

	public void exec(BossShip boss) {
		timeline = new Timeline();
		timeline.setCycleCount(1);
		KeyFrame k = new KeyFrame(Duration.seconds(5), new KeyValue(boss.translateYProperty(), -300));
		timeline.getKeyFrames().add(k);
		timeline.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				boss.setActive(true);
			}
		});
		timeline.play();

	}
}
