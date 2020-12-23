package application.model.meteor;

public class IcebergMeteor extends IceMeteor {

	public IcebergMeteor(int windowWidth) {
		super(windowWidth);
		this.size *= 2;
		this.damage *= 2;
		this.scoreValue = 5;
		this.setFitWidth(this.boundsInParentProperty().get().getWidth());
		System.out.println(this.boundsInParentProperty().get().getWidth() * 2);
	}

}
