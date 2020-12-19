package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.animation.GameOverStars;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class GameOverController implements Initializable {
	@FXML
	private Text exit;
	@FXML
	private Text replay;
	@FXML
	private Text score;
	@FXML
	private ImageView stars1;
	@FXML
	private ImageView stars2;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		GameOverStars starsAnimation = new GameOverStars();
		starsAnimation.play(stars1);
		starsAnimation.play(stars2);
	}

}
