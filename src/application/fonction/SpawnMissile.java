package application.fonction;

import application.model.spaceship.Missile;

public class SpawnMissile {
	private static double sideLaunch = 25;

	public static Missile exec(boolean leftRight, double x, double y) {
		x = (leftRight) ? x + sideLaunch : x - sideLaunch;
		Missile missile = new Missile((int) x, (int) y);
		return missile;
	}

}
