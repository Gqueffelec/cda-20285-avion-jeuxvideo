package application.model.spaceship;

import java.util.Random;

public class Shield extends Bonus {
	private int duration;

	public Shield(int windowWidth) {
		super((new Random().nextInt((windowWidth - 100) / 2 + (windowWidth - 100) / 2) - (windowWidth - 100) / 2), -450,
				"/application/assets/shield.png", "/application/assets/shieldSound.png");
		this.duration = 10;
	}

	public int getDuration() {
		return duration;
	}

}
