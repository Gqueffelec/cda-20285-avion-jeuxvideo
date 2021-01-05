package application.model.ennemi;

import application.model.IdenfiedFlyingObject;

public abstract class Ennemi extends IdenfiedFlyingObject {
	protected int damage;
	protected int scoreValue;

	public Ennemi(int life, int x, int y, int size, double speed, String uri, String soundDestroy) {
		super(life, x, y, size, speed, uri, soundDestroy);
	}

	public abstract int getScoreValue();

	public abstract int getDamage();

	public void damageLife(double damage) {
		this.life -= damage;
	}
}
