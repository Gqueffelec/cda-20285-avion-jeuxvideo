package application.model.bonus;

import java.util.Random;

public class Life extends Bonus {

	public Life(int windowWidth) {
		super((new Random().nextInt((windowWidth - 100) / 2 + (windowWidth - 100) / 2) - (windowWidth - 100) / 2), -450,
				"/application/assets/bonusLife.png");
	}

}
