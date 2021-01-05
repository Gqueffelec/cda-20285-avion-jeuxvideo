package application.fonction;

import application.controller.InGameController;
import application.model.ennemi.ship.EnnemiSpaceShip;

public class SpawnEnnemi {
	public static EnnemiSpaceShip exec() {
		InGameController.increaseActualEnnemi();
		return new EnnemiSpaceShip(600);
	}
}
