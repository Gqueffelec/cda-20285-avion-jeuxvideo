package application.fonction;

import java.io.IOException;

import application.animation.ShipExplosion;
import application.controller.InGameController;
import application.music.MusicLauncher;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class GameLoop extends Scene {

	private static boolean launch = true;
	private static InGameController controller;
	private static long meteorTimer;
	private static long bonusTimer;
	private static long missileTimer;
	private static long timerSpawn = 1000;
	boolean goUp;
	boolean goDown;
	boolean goRight;
	boolean goLeft;
	private AnimationTimer gameloop;

	public GameLoop(Parent arg0, InGameController pController) {
		super(arg0);
		controller = pController;
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
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
					goLeft = false;
					break;
				case RIGHT:
					goRight = false;
					break;
				default:
					break;
				}
			}
		});

		meteorTimer = System.currentTimeMillis();
		bonusTimer = System.currentTimeMillis();
		missileTimer = System.currentTimeMillis();
		MusicLauncher musicLauncher = new MusicLauncher();
		gameloop = new AnimationTimer() {

			// Animation a mettre ici, pour un refresh permanent (tant que y'a pas gameover)
			// a 60 frame par secondes
			@Override
			public void handle(long arg0) {
				if (!launch) {
					musicLauncher.musicFight();
					launch = !launch;
				}
				if (controller.getActualMeteor() < controller.getMaxMeteor()
						&& System.currentTimeMillis() - meteorTimer > timerSpawn) {
					meteorTimer = System.currentTimeMillis();
					controller.spawnMeteor();
				}
				if (System.currentTimeMillis() - bonusTimer > timerSpawn * 20) {
					bonusTimer = System.currentTimeMillis();
					controller.spawnBonus();
				}
				if (System.currentTimeMillis() - missileTimer > timerSpawn && controller.isMissileArmed()) {
					missileTimer = System.currentTimeMillis();
					controller.launchMissile();
				}
				controller.grabBonus();
				controller.collision();
				controller.destroyMeteor();

				int dx = 0, dy = 0;

				if (goUp)
					dy -= 5;
				if (goDown)
					dy += 5;
				if (goLeft)
					dx -= 5;
				if (goRight)
					dx += 5;
				controller.moveShipBy(dx, dy);
				if (controller.getLife() <= 0) {
					System.err.println(controller.getDisplayLife().getAccessibleText());
					Stage stage = (Stage) controller.getDisplayLife().getScene().getWindow();
					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource(
								"/application/view/" + controller.getDisplayLife().getAccessibleText() + ".fxml"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					musicLauncher.stop();
					ShipExplosion.exec(gameloop, stage, root, controller.getPlayer());
					gameloop.stop();
//					stage.setScene(new Scene(root));

				}
			}
		};
		gameloop.start();
		System.out.println("start gameloop");

	}

	public static InGameController getController() {
		return controller;
	}

	public static void setLaunch(boolean b) {
		launch = b;
	}

}
