package application.model;

public abstract class IdenfiedFlyingObject {
	private int life;
	private int x;
	private int y;
	protected double size;
	protected double speed;
	private String uri;
	private String soundDestroy;

	public IdenfiedFlyingObject(int life, int x, int y, int size, double speed, String uri, String soundDestroy) {
		this.life = life;
		this.x = x;
		this.y = y;
		this.size = size;
		this.speed = speed;
		this.uri = uri;
		this.soundDestroy = soundDestroy;
	}

	public int getLife() {
		return life;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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
