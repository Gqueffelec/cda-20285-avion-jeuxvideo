package application.model.bonus;

import java.util.Random;

public class Missile extends Bonus {

	public Missile(int windowWidth) {
		super((new Random().nextInt((windowWidth - 100) / 2 + (windowWidth - 100) / 2) - (windowWidth - 100) / 2), -450,
				"/application/assets/bonusWeapon1.png");
	}

}
