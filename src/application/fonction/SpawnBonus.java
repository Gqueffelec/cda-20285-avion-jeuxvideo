package application.fonction;

import application.model.spaceship.Bonus;
import application.model.spaceship.Shield;

public class SpawnBonus {
	public static Bonus exec() {
		Bonus bonus = new Shield(600);
		System.out.println(bonus.getUri());
		return bonus;
	}
}
