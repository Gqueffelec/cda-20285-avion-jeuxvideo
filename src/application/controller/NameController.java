package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.animation.GameOverStars;
import application.fonction.GameLoop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
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
	private Label retryName;

	@FXML
	void switchView(ActionEvent event) throws IOException {

		String nom = textField1.getText();
		Stage stage = (Stage) bouton1.getScene().getWindow();
		Parent root = null;
		InGameController controller = null;
		if (nom.length() > 2 & nom.length() < 7) {
			try {

				name = textField1.getText();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(InGameController.class.getResource("/application/view/InGame.fxml"));
				root = (Parent) loader.load();
				controller = (InGameController) loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
			stage.setScene(new GameLoop(root, controller));
			Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
			double x = bounds.getMinX() + (bounds.getWidth()) * 0.35;
			stage.setX(x);
			stage.setY(0);
		} else {
			retryName.setText("Veuillez saisir un autre nom!");
		}

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
