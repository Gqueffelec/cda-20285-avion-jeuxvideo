package application.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class IdenfiedFlyingObject extends ImageView {
	private int life;
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
		System.out.println(uri);
		this.setImage(new Image(uri));
		this.setPreserveRatio(true);
		this.soundDestroy = soundDestroy;
	}

	public int getLife() {
		return life;
	}

	public double getSize() {
		return size;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public String getUri() {
		return uri;
	}

	public String getSoundDestroy() {
		return soundDestroy;
	}

}
