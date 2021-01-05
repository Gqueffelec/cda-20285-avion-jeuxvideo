package application.fonction;

import java.util.Random;

import application.model.bonus.Bomb;
import application.model.bonus.Bonus;
import application.model.bonus.Life;
import application.model.bonus.Missile;
import application.model.bonus.Shield;

public class SpawnBonus {
	public static Bonus exec() {
		Random rand = new Random();
		switch (rand.nextInt(4)) {
		case 0:
			Bonus bonusShield = new Shield(600);
			System.out.println(bonusShield.getUri());
			return bonusShield;
		case 1:
			Bonus bonusMissile = new Missile(600);
			System.out.println(bonusMissile.getUri());
			return bonusMissile;
		case 2:
			Bonus bonusBomb = new Bomb(600);
			System.out.println(bonusBomb.getUri());
			return bonusBomb;
		case 3:
			Bonus bonusLife = new Life(600);
			System.out.println(bonusLife.getUri());
			return bonusLife;
		default:
			return null;
		}
	}
}
