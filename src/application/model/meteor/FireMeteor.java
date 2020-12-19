package application.model.meteor;

public class FireMeteor extends Meteor {

	public FireMeteor(int windowWidth) {
		super(windowWidth);
		this.damage = 2;
		this.speed = 0.5;
		this.size = 1.5;
		this.scoreValue = 1;
	}

}
