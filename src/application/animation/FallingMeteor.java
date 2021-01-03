package application.animation;

import application.controller.InGameController;
import application.fonction.GameLoop;
import application.model.meteor.Meteor;
import application.model.meteor.ZigZagMeteor;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class FallingMeteor {
	Timeline timeline;
	private boolean zigzag = Boolean.TRUE;
	private int zig = 50;
	private int zag = -50;
	private int newX;
	private static int maxSpeed = 1;

	public void play(Meteor meteor) {
		double speed = meteor.getSpeed() * maxSpeed;
		timeline = new Timeline();
		if (meteor instanceof ZigZagMeteor) {
			int zigzagY = -350;
			int zigzagRota = 45;
			for (int i = 1; i < 11; i++) {
				newX = (zigzag) ? zig : zag;
				newX += meteor.getTranslateX();
				KeyFrame k = new KeyFrame(Duration.seconds(i / speed), new KeyValue(meteor.translateXProperty(), newX),
						new KeyValue(meteor.translateYProperty(), zigzagY),
						new KeyValue(meteor.rotateProperty(), zigzagRota));
				zigzag = !zigzag;
				zigzagY += 100;
				zigzagRota += 45;
				timeline.getKeyFrames().add(k);
			}
		} else {
			KeyFrame k = new KeyFrame(Duration.seconds(10 / speed), new KeyValue(meteor.translateYProperty(), 500),
					new KeyValue(meteor.rotateProperty(), 360));
			timeline.getKeyFrames().add(k);
		}
		timeline.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				InGameController controller = GameLoop.getController();
				controller.deleteMeteor(meteor, false);
			}
		});
		timeline.play();
	}

}
