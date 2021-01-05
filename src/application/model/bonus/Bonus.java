package application.model.bonus;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Bonus extends Circle {

	private String uri;

	public Bonus(int x, int y, String uri) {
		super();
		this.uri = uri;
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.setRadius(25);
		this.setFill(new ImagePattern(new Image(this.uri)));
	}

	public String getUri() {
		return uri;
	}
}
