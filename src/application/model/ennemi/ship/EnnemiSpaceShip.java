package application.model.ennemi.ship;

import java.util.Random;

import application.model.ennemi.Ennemi;

public class EnnemiSpaceShip extends Ennemi {

	public EnnemiSpaceShip(int windowWidth) {
		super(3, -350, new Random().nextInt(100) - 400, 1, 1, "/application/assets/ennemiShip.png",
				"/application/assets/SpaceShipBoom.mp3");
		this.damage = 5;
		this.scoreValue = 5;
	}

	@Override
	public int getScoreValue() {
		return this.scoreValue;
	}

	@Override
	public int getDamage() {
		return this.damage;
	}

}
