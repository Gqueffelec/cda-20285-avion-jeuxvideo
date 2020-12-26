package application.model.spaceship;

public abstract class Weapons extends Bonus {
	private int damage;
	private int size;
	private int speed;
	private int duration;
	private String uri;
	private String soundfire;

	public Weapons(int damage, int size, int speed, String uri, String sound) {
		super(0, 0, uri, sound);
		this.damage = damage;
		this.size = size;
		this.speed = speed;
		this.uri = uri;
		this.soundfire = sound;
	}

	public int getDamage() {
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
