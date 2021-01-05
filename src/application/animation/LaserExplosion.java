package application.animation;

import application.controller.InGameController;
import application.fonction.GameLoop;
import application.fonction.SpawnLaser;
import application.model.ennemi.ship.EnemyLaser;

public class LaserExplosion {
	public static void exec() {
		double rotate = 90;
		double rotateAngle = 0;
		InGameController controller = GameLoop.getController();
		LaserFlight laseranimation = new LaserFlight();
		for (int i = 0; i < 16; i++) {
			EnemyLaser laser = SpawnLaser.execEnnemi(0, 0);
			laser.setRotate(rotate);
			double angle = rotateAngle * Math.PI / 180;
			double x = 600 * Math.cos(angle);
			double y = -600 * Math.sin(angle);
			controller.getMain().getChildren().add(laser);
			laseranimation.exec(laser, (int) x, (int) y);
			controller.getEnemyList().add(laser);
			rotate -= 22.5;
			rotateAngle += 22.5;
		}
	}
}
