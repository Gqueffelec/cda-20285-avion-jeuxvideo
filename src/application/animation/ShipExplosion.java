package application.animation;

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
		spaceShip
				.setImage(new Image(spaceShip.getClass().getResource("/application/assets/boom.png").toExternalForm()));
		final Animation animation = new SpriteAnimation(spaceShip, Duration.millis(1000), COUNT, COLUMNS, OFFSET_X,
				OFFSET_Y, WIDTH, HEIGHT);
		animation.setCycleCount(1);
		animation.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gameloop.stop();
				stage.setScene(new Scene(root));
			}
		});
		animation.play();

//		Image[] images = new Image[3];
//		for (int i = 0; i < 3; i++) {
//			images[i] = new Image(
//					spaceShip.getClass().getResource("/application/assets/explosion" + i + ".png").toExternalForm());
//		}
//		Timeline timeLine = new Timeline();
//		Collection<KeyFrame> frames = timeLine.getKeyFrames();
//		Duration frameGap = Duration.millis(256);
//		Duration frameTime = Duration.ZERO;
//		for (Image img : images) {
//			frameTime = frameTime.add(frameGap);
//			frames.add(new KeyFrame(frameTime, e -> spaceShip.setImage(img)));
//		}
//		timeLine.setCycleCount(Timeline.INDEFINITE);
//		timeLine.play();

//		timeline = new Timeline();
//		for (int i = 0; i < 3; i++) {
//			String test = i + "";
//			KeyFrame k = new KeyFrame(Duration.seconds(0.2),)
//					spaceShip.setExplo("/application/assets/explosion" + test + ".png"));
//			timeline.getKeyFrames().add(k);
//		}
//		timeline.setOnFinished(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent arg0) {
//				gameloop.stop();
//				stage.setScene(new Scene(root));
//			}
//		});
//		timeline.play();
	}
}
