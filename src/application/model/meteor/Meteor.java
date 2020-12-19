package application.model.meteor;

import java.util.Random;

import application.model.IdenfiedFlyingObject;

public class Meteor extends IdenfiedFlyingObject {
	protected int damage;
	protected int scoreValue;
	private String soundImpact;

	public Meteor(int windowWidth) {
		super(1, (new Random().nextInt(windowWidth - 50 + 1) + 50), 0, 1, 1, "/application/assets/vaisseau.png",
				"/application/assets/SpaceShipBoom.wav");
		this.damage = 1;
		this.scoreValue = 2;
		this.soundImpact = "application/assets/AsteroidImpact.wav";
	}

	public String getSoundImpact() {
		return soundImpact;
	}

	public int getDamage() {
		return damage;
	}

	public int getScoreValue() {
		return scoreValue;
	}

}
