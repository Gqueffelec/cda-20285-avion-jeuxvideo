package application;

import java.io.IOException;

import application.controller.InGameController;
import application.controller.NameController;
import application.music.MusicLauncher;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static long timerSpawn = 1000;
	private static long meteorTimer;
	private static long bonusTimer;
	private static InGameController controller;
	private static boolean launch = true;

	@Override
	public void start(Stage primaryStage) {
//		Parent root = null;
		try {
//			root = FXMLLoader.load(getClass().getResource("/application/view/Score.fxml"));;
			
			FXMLLoader loader = new FXMLLoader();
			Parent root = (Parent) loader
					.load(InGameController.class.getResourceAsStream("/application/view/InGame.fxml"));
			controller = (InGameController) loader.getController();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			meteorTimer = System.currentTimeMillis();
			bonusTimer = System.currentTimeMillis();
			MusicLauncher.music();
			new AnimationTimer() {
				// Animation a mettre ici, pour un refresh permanent (tant que y'a pas gameover)
				// a 60 frame par secondes
				@Override
				public void handle(long arg0) {
					if (controller.getActualMeteor() < controller.getMaxMeteor()
							&& System.currentTimeMillis() - meteorTimer > timerSpawn) {
						meteorTimer = System.currentTimeMillis();
						controller.spawnMeteor();
////						controller.spawnBonus();
					}
					if (!launch) {
						MusicLauncher.musicFight();
						launch = !launch;
					}
					if (System.currentTimeMillis() - bonusTimer > timerSpawn * 10) {
						bonusTimer = System.currentTimeMillis();
						controller.spawnBonus();
					}
				}
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static InGameController getController() {
		return controller;
	}

	public static void setLaunch(boolean launch) {
		Main.launch = launch;
	}
}
