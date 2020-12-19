package application.model.spaceship;

import application.model.IdenfiedFlyingObject;
import application.model.interfaces.SpaceShipMouvement;

public class SpaceShip extends IdenfiedFlyingObject implements SpaceShipMouvement {
	private Shield shield;
	private Weapons weapon;

	public SpaceShip(int windowWidth, int windowHeight) {
		super(5, windowWidth / 2, windowHeight - 50, 1, 1, "/application/assets/vaisseau.png",
				"/application/assets/SpaceShipBoom.wav");
		this.shield = null;
		this.weapon = null;
	}

	public Shield getShield() {
		return shield;
	}

	public void setShield(Shield shield) {
		this.shield = shield;
	}

	public void setWeapon(Weapons weapon) {
		this.weapon = weapon;
	}

	public Weapons getWeapon() {
		return weapon;
	}
}
