package application.model.meteor;

import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.image.Image;

public class IcebergMeteor extends IceMeteor {

	public IcebergMeteor(int windowWidth) {
		super(windowWidth);
		this.life = 2;
		this.size *= 2;
		this.damage *= 2;
		this.scoreValue = 5;
		this.uri = "/application/assets/iceberg" + ThreadLocalRandom.current().nextInt(0, 10) + ".png";
		this.setImage(new Image(this.uri));
	}

}
