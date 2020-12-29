package application.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
		stage = new Stage();
		try {
			root = FXMLLoader.load(getClass().getResource("/application/view/Name.fxml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		stage.setScene(new Scene(root));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(txtNouvellePartie.getScene().getWindow());
		stage.showAndWait();
//		try {
//			root = FXMLLoader.load(getClass().getResource("/application/view/Test.fxml"));
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		Scene scene = new Scene(root);
//		stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
//		stage.setScene(scene);
//		stage.show();
	}
	
	public void displayScores(MouseEvent e) {
		System.out.println("Les scores");
	}
	
	public void quitGame(MouseEvent e) {
		System.exit(0);
	}
}
