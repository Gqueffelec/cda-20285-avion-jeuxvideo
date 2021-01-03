package application.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuController {
	Parent root = null;
	Stage stage;

	@FXML
	private Text txtNouvellePartie;
	@FXML
	private Text txtDisplayScores;
	@FXML
	private Text txtQuitGame;

	public void nouvellePartie(MouseEvent e) {
		stage = (Stage) txtNouvellePartie.getScene().getWindow();
		try {
			root = FXMLLoader.load(getClass().getResource("/application/view/Name.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		stage.setScene(new Scene(root));
	}

	public void displayScores(MouseEvent e) {
		System.out.println("Les scores");
	}

	public void quitGame(MouseEvent e) {
		System.exit(0);
	}
}
