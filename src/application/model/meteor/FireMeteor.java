package application.model.meteor;

import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.image.Image;

public class FireMeteor extends Meteor {

	public FireMeteor(int windowWidth) {
		super(windowWidth);
		this.damage = 2;
		this.speed = 0.5;
		this.size = 1.5;
		this.scoreValue = 1;
		this.uri = "/application/assets/fire" + ThreadLocalRandom.current().nextInt(0, 10) + ".png";
		this.setImage(new Image(this.uri));
	}

}
