package application.model.bonus;

import java.util.Random;

public class Laser extends Bonus {

	public Laser(int windowWidth) {
		super((new Random().nextInt((windowWidth - 100) / 2 + (windowWidth - 100) / 2) - (windowWidth - 100) / 2), -450,
				"/application/assets/laserPlayerBonus.png");
		// TODO Auto-generated constructor stub
	}

}
