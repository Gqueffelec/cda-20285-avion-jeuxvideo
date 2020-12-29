package application;

import java.io.IOException;

import application.controller.InGameController;
import application.controller.NameController;
import application.music.MusicLauncher;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class Main extends Application {

	private static long timerSpawn = 1000;
	private static long meteorTimer;
	private static long bonusTimer;
	private static InGameController controller;
	private static boolean launch = true;

	boolean goUp;
	boolean goDown;
	boolean goRight;
	boolean goLeft;

	@Override
	public void start(Stage primaryStage) {
//		Parent root = null;
		try {
//			root = FXMLLoader.load(getClass().getResource("/application/view/GameOver.fxml"));

			FXMLLoader loader = new FXMLLoader();
			Parent root = (Parent) loader
					.load(InGameController.class.getResourceAsStream("/application/view/InGame.fxml"));
			controller = (InGameController) loader.getController();
			Scene scene = new Scene(root);
			
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					switch (event.getCode()) {
					case UP:
						goUp = true;
						break;
					case DOWN:
						goDown = true;
						break;
					case LEFT:
						goLeft = true;
						break;
					case RIGHT:
						goRight = true;
						break;
					default:
						break;
					}

				}
			});
			
			scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					switch (event.getCode()) {
					case UP:    
						goUp = false;
						break;
					case DOWN:  
						goDown = false;
						break;
					case LEFT: 
						goLeft  = false;
						break;
					case RIGHT:
						goRight  = false;
						break;
					default : break;
		            }
		        }
		    });
			
			primaryStage.setScene(scene);
			primaryStage.show();
			meteorTimer = System.currentTimeMillis();
			bonusTimer = System.currentTimeMillis();
			MusicLauncher.music();
			new AnimationTimer() {
				@Override
	            public void handle(long now) {
	                int dx = 0, dy = 0;

	                if (goUp) dy -= 1;
	                if (goDown) dy += 1;
	                if (goLeft)  dx -= 1;
	                if (goRight)  dx += 1;
	                controller.moveShipBy(dx, dy);
	            }
				// Animation a mettre ici, pour un refresh permanent (tant que y'a pas gameover)
				// a 60 frame par secondes
				@Override
				public void handle(long arg0) {
					if (!launch) {
						MusicLauncher.musicFight();
						launch = !launch;
					}
					if (controller.getActualMeteor() < controller.getMaxMeteor()
							&& System.currentTimeMillis() - meteorTimer > timerSpawn) {
						meteorTimer = System.currentTimeMillis();
						controller.spawnMeteor();
////						controller.spawnBonus();
					}
					if (System.currentTimeMillis() - bonusTimer > timerSpawn * 10) {
						bonusTimer = System.currentTimeMillis();
						controller.spawnBonus();
					}
////					controller.grabBonus();
////					controller.collision();
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
