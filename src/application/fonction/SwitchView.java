package application.fonction;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SwitchView {
	public void exec(Button button) {
		System.err.println(button.getAccessibleText());
		Stage stage = (Stage) button.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/view/" + button.getAccessibleText() + ".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.setScene(new Scene(root));
	}

	public void exec(Text text) {
		System.err.println(text.getAccessibleText());
		Stage stage = (Stage) text.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/view/" + text.getAccessibleText() + ".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.setScene(new Scene(root));
	}
}
