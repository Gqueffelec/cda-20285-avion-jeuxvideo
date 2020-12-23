package application.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class GameOverStars {

	private Timeline timeline;

	// Creation d'une timeline d'une frame avec une opacité et un glow opposé a
	// celui de base, permet de faire disparaitre et apparaitre les étoiles de
	// chaque fichier

	public void play(ImageView stars) {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE); // animation continue
		int opacity;
		Glow glow = new Glow();
		if (stars.getOpacity() == 1) {
			opacity = 0;
			glow.setLevel(0);
		} else {
			opacity = 1;
			glow.setLevel(1);
		}
		KeyFrame k = new KeyFrame(Duration.seconds(2), new KeyValue(stars.opacityProperty(), opacity),
				new KeyValue(stars.effectProperty(), glow));
		// creation d'une frame de 2 seconds pour changer de status des étoiles
		timeline.getKeyFrames().add(k); // ajouter la frame de changement de status
		timeline.setAutoReverse(true); // retour au status initial
		timeline.play();
	}

}
