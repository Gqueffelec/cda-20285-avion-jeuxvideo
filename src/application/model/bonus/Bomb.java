package application.model.bonus;

import java.util.Random;

public class Bomb extends Bonus{

	public Bomb(int windowWidth) {
		super((new Random().nextInt((windowWidth - 100) / 2 + (windowWidth - 100) / 2) - (windowWidth - 100) / 2), -450,
				"/application/assets/nuclear1.png");	
	}
}
