package application.fonction;

import java.util.Random;

import application.model.bonus.Bonus;
import application.model.bonus.Life;
import application.model.bonus.Missile;
import application.model.bonus.Shield;

public class SpawnBonus {
	public static Bonus exec() {

		Random r = new Random();
		int n = r.nextInt(3);
		System.out.println(n);
		if (n == 0) {
			Bonus bonusShield = new Shield(600);
			System.out.println(bonusShield.getUri());
			return bonusShield;
		} else if (n == 1) {
			Bonus bonusMissile = new Missile(600);
			System.out.println(bonusMissile.getUri());
			return bonusMissile;
		} else {
			Bonus bonusLife = new Life(600);
			System.out.println(bonusLife.getUri());
			return bonusLife;

		}
	}
}
