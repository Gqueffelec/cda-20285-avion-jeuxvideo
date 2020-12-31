package application.model.spaceship;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Bonus extends Circle {

	private String uri;
	private String sound;

	public Bonus(int x, int y, String uri, String sound) {
		super();
		this.uri = uri;
		System.out.println(this.uri);
		this.sound = sound;
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.setRadius(25);
		this.setFill(new ImagePattern(new Image(this.uri)));
	}

	public String getUri() {
		return uri;
	}

	public String getSound() {
		return sound;
	}
}
