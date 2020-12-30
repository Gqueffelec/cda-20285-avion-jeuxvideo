package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

import application.Main;
import application.animation.GameOverStars;
import application.fonction.SwitchView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

	@FXML
	void switchView(MouseEvent event) {

		Stage stage = (Stage) score.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/view/" + score.getAccessibleText() + ".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.setScene(new Scene(root));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		GameOverStars starsAnimation = new GameOverStars();
		starsAnimation.play(stars1);
		starsAnimation.play(stars2);
		File file = new File("json");

		System.out.println(file.getAbsoluteFile());

	}

}
