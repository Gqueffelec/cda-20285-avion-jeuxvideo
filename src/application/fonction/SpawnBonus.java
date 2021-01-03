package application.fonction;

import java.util.Random;

import application.model.bonus.Bonus;
import application.model.bonus.Missile;
import application.model.bonus.Shield;

public class SpawnBonus {
	public static Bonus exec() {
		Random rand = new Random();
		if (rand.nextBoolean()) {
			Bonus bonusShield = new Shield(600);
			System.out.println(bonusShield.getUri());
			return bonusShield;
		} else {
			Bonus bonusMissile = new Missile(600);
			System.out.println(bonusMissile.getUri());
			return bonusMissile;
		}
	}
}
