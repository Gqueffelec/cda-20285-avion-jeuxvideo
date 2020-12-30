package application.model.spaceship;

import application.model.IdenfiedFlyingObject;
import application.model.interfaces.SpaceShipMouvement;
import javafx.scene.image.Image;

public class SpaceShip extends IdenfiedFlyingObject implements SpaceShipMouvement{
	private Shield shield;
	private Weapons weapon;
	private double abs;
	private double ord;

	public SpaceShip(int windowWidth, int windowHeight) {
		super(5, 0, windowHeight / 2 - 50, 1, 1, "/application/assets/shuttlenoweps.png",
				"/application/assets/SpaceShipBoom.mp3");
		this.abs=0;
		this.ord=windowHeight / 2 - 50;
		this.shield = null;
		this.weapon = null;
		System.out.println(this.boundsInParentProperty().get().getWidth());
		this.setFitHeight(75);
	}

	public Shield getShield() {

		return shield;
	}

	public void setShield(Shield shield) {
		if (this.shield == null) {
			this.setImage(new Image("/application/assets/shieldedShip.png"));
			this.setFitHeight(90);

		}

	}

	public void setWeapon(Weapons weapon) {
		this.weapon = weapon;
	}

	public Weapons getWeapon() {
		return weapon;
	}


	public double getAbs() {
		return abs;
	}

	public void setAbs(double abs) {
		this.abs = abs;
	}

	public double getOrd() {
		return ord;
	}

	public void setOrd(double ord) {
		this.ord = ord;
	}
}
