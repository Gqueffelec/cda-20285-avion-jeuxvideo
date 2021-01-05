package application.animation;

import application.model.IdenfiedFlyingObject;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class AsteroidExplosion {

	private static final int COLUMNS = 8;
	private static final int COUNT = 32;
	private static final int OFFSET_X = 0;
	private static final int OFFSET_Y = 0;
	private static final int WIDTH = 64;
	private static final int HEIGHT = 64;

	public static void exec(IdenfiedFlyingObject ennemi) {
//		meteor.setImage(new Image(meteor.getClass().getResource("/application/assets/boom.png").toExternalForm()));
		Image asteroidDestroy = new Image(
				ennemi.getClass().getResource("/application/assets/explosion.png").toExternalForm());
		final Animation animation = new SpriteAnimation(ennemi, asteroidDestroy, Duration.millis(1000), COUNT, COLUMNS,
				OFFSET_X, OFFSET_Y, WIDTH, HEIGHT);
		animation.setCycleCount(1);
		animation.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				ennemi.setVisible(false);
			}
		});
		animation.play();
	}
}
