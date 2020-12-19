package application.model.meteor;

public class IceMeteor extends Meteor {

	public IceMeteor(int windowWidth) {
		super(windowWidth);
		this.damage = 2;
		this.size = 1.25;
		this.scoreValue = 3;
	}

}
