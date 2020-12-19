package application.model.spaceship;

public class Shield {
	private int duration;
	private int size;
	private String uri;

	public Shield() {
		this.duration = 10;
		this.size = 1;
		this.uri = "/application/assets/Shield.png";
	}

	public int getDuration() {
		return duration;
	}

	public int getSize() {
		return size;
	}

	public String getUri() {
		return uri;
	}
}
