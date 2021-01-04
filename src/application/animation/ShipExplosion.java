package application.animation;

import application.controller.GameOverController;
import application.model.spaceship.SpaceShip;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ShipExplosion {

	private static final int COLUMNS = 8;
	private static final int COUNT = 64;
	private static final int OFFSET_X = 0;
	private static final int OFFSET_Y = 0;
	private static final int WIDTH = 128;
	private static final int HEIGHT = 128;

	public static void exec(AnimationTimer gameloop, Stage stage, Parent root, SpaceShip spaceShip) {
		Image shipDestroy = new Image(
				spaceShip.getClass().getResource("/application/assets/boom.png").toExternalForm());
		final Animation animation = new SpriteAnimation(spaceShip, shipDestroy, Duration.millis(1000), COUNT, COLUMNS,
				OFFSET_X, OFFSET_Y, WIDTH, HEIGHT);
		animation.setCycleCount(1);
		animation.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				GameOverController.writeNewScore();
				stage.setScene(new Scene(root));
			}
		});
		animation.play();
	}
}
