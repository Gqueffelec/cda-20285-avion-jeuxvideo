package application.model.meteor;

import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.image.Image;

public class IceMeteor extends Meteor {

	public IceMeteor(int windowWidth) {
		super(windowWidth);
		this.damage = 2;
		this.size = 1.25;
		this.scoreValue = 3;
		this.setImage(new Image("/application/assets/ice" + ThreadLocalRandom.current().nextInt(0, 10) + ".png"));
	}

}
