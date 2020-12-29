package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.animation.GameOverStars;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class NameController implements Initializable {

	private static String name;

	@FXML
	private ImageView stars1;

	@FXML
	private ImageView stars2;

	@FXML
	private TextField textField1;

	@FXML
	private Button bouton1;

	@FXML
	void switchView(ActionEvent event) throws IOException {

		String nom = textField1.getText();
		System.out.println(nom);
		Stage stage = (Stage) bouton1.getScene().getWindow();
		Parent root = null;
		try {
			name = textField1.getText();
			root = FXMLLoader.load(getClass().getResource("/application/view/InGame.fxml"));

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

	}

	public static String getName() {
		return name;
	}

}
