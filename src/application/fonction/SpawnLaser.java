package application.fonction;

import application.model.ennemi.ship.EnemyLaser;
import application.model.spaceship.Laser;

public class SpawnLaser {

	public static Laser simpleFireRate(double x, double y) {
		Laser laser = new Laser((int) x, (int) y);
		return laser;
	}

	public static Laser[] doubleFireRate(double x, double y) {
		Laser laser01 = new Laser((int) x + 25, (int) y);
		Laser laser02 = new Laser((int) x - 25, (int) y);
		Laser[] lasers = { laser01, laser02 };
		return lasers;
	}
	
	public static Laser[] tripleFireRate(double x, double y) {
		Laser laser01 = new Laser((int) x, (int) y);
		Laser laser02 = new Laser((int) x + 25, (int) y);
		Laser laser03 = new Laser((int) x - 25, (int) y);
		Laser[] lasers = { laser01, laser02, laser03 };
		return lasers;
	}

	public static EnemyLaser execEnnemi(double x, double y) {
		EnemyLaser laser = new EnemyLaser((int) x, (int) y);
		return laser;
	}
}
