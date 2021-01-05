package application.fonction;

import java.io.IOException;

import application.animation.FallingMeteor;
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
	private static long startTimer = System.currentTimeMillis();
	private static long bonusTimer = System.currentTimeMillis();
	private static long missileTimer = System.currentTimeMillis();
	private static long ennemiTimer = System.currentTimeMillis();
	private static long meteorTimer;
	private static long laserTimer;
	private static long ennemiLaserTimer;
	private static long timerSpawn = 1000;
	private static final long BONUSSPAWNRATE = 10000;
	private static final long MISSILESPAWNRATE = 500;
	private static final long LASERSPAWNRATE = 100;
	private static final long ENNEMISPAWNRATE = 1000;
	private static boolean shoot;
	private boolean goUp;
	private boolean goDown;
	private boolean goRight;
	private boolean goLeft;
	private AnimationTimer gameloop;

	public GameLoop(Parent arg0, InGameController pController) {
		super(arg0);
		controller = pController;
		keyPressedListener();
		keyRealeasedListener();

		MusicLauncher musicLauncher = new MusicLauncher();
		gameloop = new AnimationTimer() {
			// Animation a mettre ici, pour un refresh permanent (tant que y'a pas gameover)
			// a 60 frame par secondes
			@Override
			public void handle(long arg0) {
				gameEvent(musicLauncher);
				controller.grabBonus();
				controller.collision();
				controller.destroyEnemy();
				controller.hiByEnnemiLaser();
				controller.moveShipBy(horizontalControl(), verticalControl());
				if (controller.getLife() <= 0) {
					gameOver(musicLauncher);
				}
			}			
		};
		gameloop.start();
	}

	private void keyRealeasedListener() {
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
				case SPACE:
					shoot = false;
					break;
				case DIGIT1:
					controller.setBomb(false);
					break;
				default:
					break;
				}
			}
		});
	}

	private void keyPressedListener() {
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
				case SPACE:
					shoot = true;
					break;
				case DIGIT1:
					if (controller.getBomb()) {
						controller.destroyAllMeteors();
						InGameController.setActualMeteor(0);
						InGameController.setMaxMeteor(0);
					}
					break;
				default:
					break;
				}
			}
		});
	}

	private int horizontalControl() {
		int dx = 0;
		if (goLeft) {
			dx -= 5;
		}
		if (goRight) {
			dx += 5;
		}
		return dx;
	}
	
	private int verticalControl() {
		int dy = 0;
		if (goUp) {
			dy -= 5;
		}
		if (goDown) {
			dy += 5;
		}
		return dy;
	}	
	
	private void gameOver(MusicLauncher musicLauncher) {
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
		gameloop.stop();
		ShipExplosion.exec(gameloop, stage, root, controller.getPlayer());
	}

	public static InGameController getController() {
		return controller;
	}
	
	private static void gameEvent(MusicLauncher musicLauncher) {
		if (!launch) {
			musicLauncher.musicFight();
			launch = !launch;
		}
		if (controller.getActualMeteor() < controller.getMaxMeteor()
				&& System.currentTimeMillis() - meteorTimer > timerSpawn) {
			meteorTimer = System.currentTimeMillis();
			controller.spawnMeteor();
		}
		if (System.currentTimeMillis() - bonusTimer > BONUSSPAWNRATE) {
			bonusTimer = System.currentTimeMillis();
			controller.spawnBonus();
		}
		if (shoot && controller.isMissileArmed()
				&& System.currentTimeMillis() - missileTimer > MISSILESPAWNRATE) {
			controller.launchMissile();
			missileTimer = System.currentTimeMillis();
			shoot = false;
		}
		if (System.currentTimeMillis() - laserTimer > LASERSPAWNRATE) {
			laserTimer = System.currentTimeMillis();
			controller.fire();
		}
		if ((System.currentTimeMillis() - startTimer) > 5000 && controller.getMaxMeteor() < 11) {
			InGameController.setMaxMeteor(controller.getMaxMeteor() + 2);
			startTimer = System.currentTimeMillis();
			FallingMeteor.setMaxSpeed(FallingMeteor.getMaxSpeed() * 1.4);
			timerSpawn -= 80;
		}
		if (System.currentTimeMillis() - ennemiTimer > ENNEMISPAWNRATE
				&& controller.getActualEnnemi() < controller.getMaxEnnemi()) {
			ennemiTimer = System.currentTimeMillis();
			controller.spawnEnnemi();
		}
		if (controller.getActualEnnemi() != 0 && System.currentTimeMillis() - ennemiLaserTimer > timerSpawn) {
			ennemiLaserTimer = System.currentTimeMillis();
			controller.fireEnnemiLaser();
		}
	}
	
	public static void setEnnemiTimer(long ennemiTimer) {
		GameLoop.ennemiTimer = ennemiTimer;
	}

	public static void setLaunch(boolean b) {
		launch = b;
	}

	public static void stop() {

	}
}
