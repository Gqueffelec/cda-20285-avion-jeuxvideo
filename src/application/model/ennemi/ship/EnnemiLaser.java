package application.model.ennemi.ship;

import application.model.spaceship.Weapons;

public class EnnemiLaser extends Weapons {
	public EnnemiLaser(int x, int y) {
		super(x, y, 1, 1, 3, "/application/assets/laserEnnemi.png", "laser");
	}
}
