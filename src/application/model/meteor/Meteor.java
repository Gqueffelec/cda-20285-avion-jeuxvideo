package application.model.meteor;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import application.model.IdenfiedFlyingObject;

public class Meteor extends IdenfiedFlyingObject {
	protected int damage;
	protected int scoreValue;
	private String soundImpact;

	public Meteor(int windowWidth) {
		super(1, (new Random().nextInt((windowWidth - 100) / 2 + (windowWidth - 100) / 2) - (windowWidth - 100) / 2),
				-450, 1, 1, "/application/assets/normal" + ThreadLocalRandom.current().nextInt(0, 10) + ".png",
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
