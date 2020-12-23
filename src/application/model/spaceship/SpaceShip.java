package application.model.spaceship;

import application.model.IdenfiedFlyingObject;
import application.model.interfaces.SpaceShipMouvement;

public class SpaceShip extends IdenfiedFlyingObject implements SpaceShipMouvement {
	private Shield shield;
	private Weapons weapon;

	public SpaceShip(int windowWidth, int windowHeight) {
		super(5, 0, windowHeight / 2 - 50, 1, 1, "/application/assets/shuttlenoweps.png",
				"/application/assets/SpaceShipBoom.mp3");
		this.shield = null;
		this.weapon = null;
		System.out.println(this.boundsInParentProperty().get().getWidth());
		this.setFitHeight(75);
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
