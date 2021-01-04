package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.animation.StarsAnimation;
import application.music.MusicMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuController implements Initializable {

	private static final MusicMenu MUSIC = new MusicMenu();

	@FXML
	private ImageView stars3;

	@FXML
	private ImageView stars4;
	@FXML
	private Text txtNouvellePartie;
	@FXML
	private Text txtDisplayScores;
	@FXML
	private Text txtQuitGame;

	public void nouvellePartie(MouseEvent e) {
		Stage stage = (Stage) txtNouvellePartie.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/view/Name.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		stage.setScene(new Scene(root));
	}

	public void displayScores(MouseEvent e) {
		ScoreController.setPreviousScene("Menu");
		Stage stage = (Stage) txtDisplayScores.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/view/Score.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		stage.setScene(new Scene(root));
	}

	public void quitGame(MouseEvent e) {
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		StarsAnimation starsAnimation = new StarsAnimation();
		starsAnimation.play(stars3);
		starsAnimation.play(stars4);
		MUSIC.play();
	}

	public static MusicMenu getMusic() {
		return MUSIC;
	}
}
