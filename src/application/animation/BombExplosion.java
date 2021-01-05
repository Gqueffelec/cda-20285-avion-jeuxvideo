package application.animation;

import application.model.ennemi.ship.EnemyBomb;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class BombExplosion {
	Timeline timeline;

	public void play(EnemyBomb bomb) {
		timeline = new Timeline();
		timeline.setCycleCount(1);
		KeyFrame k = new KeyFrame(Duration.seconds(3), new KeyValue(bomb.translateYProperty(), 0),
				new KeyValue(bomb.rotateProperty(), 360));
		timeline.getKeyFrames().add(k);
		timeline.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				LaserExplosion.exec();
				bomb.setVisible(false);
			}
		});
		timeline.play();
	}
}
