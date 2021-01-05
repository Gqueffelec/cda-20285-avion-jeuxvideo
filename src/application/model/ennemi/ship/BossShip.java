package application.model.ennemi.ship;

import javafx.scene.image.Image;

public class BossShip extends EnnemiSpaceShip {
	private boolean isActive = false;

	public BossShip(int windowWidth) {
		super(windowWidth);
		this.setImage(new Image(getClass().getResource("/application/assets/shipBoss.png").toExternalForm()));
		this.setTranslateX(0);
		this.setTranslateY(-700);
		this.life = 10;
		this.scoreValue = 50;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
