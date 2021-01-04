package application.fonction;

import application.model.spaceship.Laser;

public class SpawnLaser {

	public static Laser exec(double x, double y) {
		Laser laser = new Laser((int) x, (int) y);
		return laser;
	}

}
