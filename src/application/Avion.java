package application;

import java.io.IOException;

import application.controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Avion extends Application {

	@Override
	public void start(Stage primaryStage) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MenuController.class.getResource("/application/view/Menu.fxml"));
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
