package application.model.spaceship;

import application.music.SoundLauncher;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Weapons extends ImageView {
	private double damage;
	private int size;
	private int speed;
	private int duration;
	private String uri;
	private String soundfire;

	public Weapons(int x, int y, double d, int size, int speed, String uri, String sound) {
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.uri = uri;
		this.setImage(new Image(getClass().getResource(uri).toExternalForm()));
		this.damage = d;
		this.size = size;
		this.speed = speed;
		this.soundfire = sound;
		SoundLauncher missileLaunchSound = new SoundLauncher();
		missileLaunchSound.music(soundfire);
	}

	public double getDamage() {
		return damage;
	}

	public int getSize() {
		return size;
	}

	public int getSpeed() {
		return speed;
	}

	public String getUri() {
		return uri;
	}

	public String getSoundfire() {
		return soundfire;
	}

	public int getDuration() {
		return duration;
	}

}
