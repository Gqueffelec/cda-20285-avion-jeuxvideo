package application.animation;

import application.model.IdenfiedFlyingObject;
import application.model.ennemi.ship.BossShip;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class EnemyExplosion {

	private static final int COLUMNS = 8;
	private static final int COUNT = 32;
	private static final int OFFSET_X = 0;
	private static final int OFFSET_Y = 0;
	private static final int WIDTH = 64;
	private static final int HEIGHT = 64;

	public static void exec(IdenfiedFlyingObject enemy) {
		Image asteroidDestroy = new Image(
				enemy.getClass().getResource("/application/assets/explosion.png").toExternalForm());
		final Animation animation = new SpriteAnimation(enemy, asteroidDestroy, Duration.millis(1000), COUNT, COLUMNS,
				OFFSET_X, OFFSET_Y, WIDTH, HEIGHT);
		if (enemy instanceof BossShip) {
			animation.setCycleCount(2);
		} else {
			animation.setCycleCount(1);
		}
		animation.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				enemy.setVisible(false);
			}
		});
		animation.play();
	}
}
