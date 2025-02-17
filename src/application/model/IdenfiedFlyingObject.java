package application.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class IdenfiedFlyingObject extends ImageView {
	protected double life;
	protected double size;
	protected double speed;
	protected String uri;
	private String soundDestroy;

	public IdenfiedFlyingObject(int life, int x, int y, int size, double speed, String uri, String soundDestroy) {
		this.life = life;
		this.size = size;
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.speed = speed;
		this.uri = uri;
		this.setImage(new Image(getClass().getResource(uri).toExternalForm()));
		this.setPreserveRatio(true);
		this.soundDestroy = soundDestroy;
	}

	public double getLife() {
		return life;
	}

	public double getSpeed() {
		return speed;
	}

	public String getSoundDestroy() {
		return soundDestroy;
	}

}
