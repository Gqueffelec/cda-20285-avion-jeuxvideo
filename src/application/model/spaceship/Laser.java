package application.model.spaceship;

public class Laser extends Weapons {

	public Laser(int damage, int size, int speed, String uri, String soundfire) {
		super(1, 1, 3, "/application/assets/Laser.png", "/application/assets/Laser.wav");
	}

}
