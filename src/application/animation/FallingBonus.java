package application.animation;

import application.Main;
import application.model.spaceship.Bonus;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class FallingBonus {
	Timeline timeline;
//	private static int maxSpeed;

	public void play(Bonus bonus) {
		timeline = new Timeline();
		KeyFrame k = new KeyFrame(Duration.seconds(5), new KeyValue(bonus.translateYProperty(), 500));
		timeline.getKeyFrames().add(k);
		timeline.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Main.getController().deleteBonus(bonus);
			}
		});
		timeline.play();
	}

}
