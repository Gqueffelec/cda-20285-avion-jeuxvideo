
package application.animation;

import application.model.spaceship.SpaceShip;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.util.Duration;

public class ShieldDelay {

	private Timeline timeline;

	public void exec(SpaceShip spaceShip) {
		System.out.println("timeline shield");
		timeline = new Timeline();
		timeline.setCycleCount(20);
		Glow glow = new Glow();
		glow.setLevel(1);
		KeyFrame k = new KeyFrame(Duration.seconds(0.1), new KeyValue(spaceShip.effectProperty(), new Glow(1)));
		glow.setLevel(0);
		KeyFrame k2 = new KeyFrame(Duration.seconds(0.2), new KeyValue(spaceShip.effectProperty(), new Glow(0)));
		timeline.getKeyFrames().add(k); // ajouter la frame de changement de status
		timeline.getKeyFrames().add(k2); // ajouter la frame de changement de status
		timeline.setAutoReverse(true);
		timeline.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				spaceShip.unsetShield();
			}
		});
		timeline.play();
	}

}
