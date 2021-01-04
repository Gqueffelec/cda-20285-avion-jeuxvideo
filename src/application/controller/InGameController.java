package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import application.animation.AsteroidExplosion;
import application.animation.FallingBonus;
import application.animation.FallingMeteor;
import application.animation.MissileFlight;
import application.animation.MovingBackground;
import application.animation.StarsAnimation;
import application.fonction.SpawnBonus;
import application.fonction.SpawnMeteor;
import application.fonction.SpawnMissile;
import application.model.bonus.Bonus;
import application.model.bonus.Shield;
import application.model.meteor.Meteor;
import application.model.spaceship.Missile;
import application.model.spaceship.SpaceShip;
import application.music.MusicLauncher;
import application.music.SoundLauncher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class InGameController implements Initializable {
	private static int maxMeteor = 3;
	private static int actualMeteor;
	private static FallingMeteor meteorFall = new FallingMeteor();
	private static FallingBonus bonusFall = new FallingBonus();
	private static int score;
	private static int life;
	private boolean leftRight = true;
	private boolean missileArmed = false;
	private int missilesQuantity = 0;

	@FXML
	private StackPane background1;
	@FXML
	private StackPane background2;
	@FXML
	private StackPane background3;
	@FXML
	private ImageView stars1;
	@FXML
	private ImageView stars2;
	@FXML
	private ImageView stars3;
	@FXML
	private Map<Missile, ImageView> missiles = new HashMap<>();
	@FXML
	private List<Meteor> meteors = new ArrayList<>();
	@FXML
	private StackPane main;
	@FXML
	private SpaceShip player;
	@FXML
	private Text Nom;
	@FXML
	private Text displayScore;
	@FXML
	private Text displayLife;
	@FXML
	private Bonus actualBonus;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		life = 5;
		actualMeteor = 0;
		score = 0;
		StarsAnimation starsAnimation = new StarsAnimation();
		starsAnimation.play(stars1);
		starsAnimation.play(stars2);
		starsAnimation.play(stars3);
		StackPane[] background = { background1, background2, background3 };
		MovingBackground moveBackground = new MovingBackground();
		moveBackground.exec(background);
		Nom.setText(NameController.getName());
		displayScore.setText(String.valueOf(score));
		displayLife.setText(String.valueOf(life));
		player = new SpaceShip(600, 900);
		main.getChildren().add(player);
		MusicLauncher startMusic = new MusicLauncher();
		startMusic.music();
	}

	public void spawnMeteor() {
		Meteor meteor = SpawnMeteor.exec();
		meteors.add(meteor);
		main.getChildren().add(meteor);
		meteorFall.play(meteor);
		for (Meteor meteor1 : meteors) {
			System.out.println("meteor life " + meteor1.getLife());
		}
	}

	public void deleteMeteor(Meteor meteor, boolean collision) {
		System.out.println("meteor delete");
		if (meteors.remove(meteor)) {
			if (!collision) {
				increaseScore(meteor);
				displayScore.setText(String.valueOf(score));
			}
			decreaseActualMeteor();
		}
//		main.getChildren().remove(meteor);
	}

	public void spawnBonus() {
		if (actualBonus == null) {
			Bonus bonus = SpawnBonus.exec();
			actualBonus = bonus;
			main.getChildren().add(bonus);
			bonusFall.play(bonus);
		}

	}

	public void deleteBonus(Bonus bonus) {
		actualBonus = null;
		main.getChildren().remove(bonus);
	}

	public void moveShipBy(int dx, int dy) {

		if (dx == 0) {
			player.setRotate(0);
			if (dy == 0) {
				return;
			}
		}
		if (dx < 0) {
			player.setRotate(-5);
			player.setRotate(-10);
		} else if (dx > 0) {
			player.setRotate(5);
			player.setRotate(10);
		}

		final double cx = player.getAbs();
		final double cy = player.getOrd();
		double x = cx + dx;
		double y = cy + dy;
		moveShipTo(x, y);

	}

	private void moveShipTo(double x, double y) {

		if (x >= -285 && x <= 285 && y >= -415 && y <= 415) {
			player.setTranslateX(x);
			player.setTranslateY(y);
			player.setAbs(x);
			player.setOrd(y);
		}
	}

	public int getActualMeteor() {
		return actualMeteor;
	}

	public static void increaseActualMeteor() {
		InGameController.actualMeteor++;
	}

	public static void decreaseActualMeteor() {
		InGameController.actualMeteor--;
	}

	public int getMaxMeteor() {
		return maxMeteor;
	}

	public static void setMaxMeteor(int maxMeteor) {
		InGameController.maxMeteor = maxMeteor;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		InGameController.score = score;
	}

	public SpaceShip getSpaceShip() {
		return player;
	}

	public List<Meteor> getMeteors() {
		return meteors;
	}

	public Bonus getBonus() {
		return actualBonus;
	}

	public void launchMissile() {
		Missile missile = SpawnMissile.exec(leftRight, player.getTranslateX(), player.getTranslateY());
		ImageView fire = new ImageView();
		fire.setImage(new Image(getClass().getResource("/application/assets/fire.png").toExternalForm()));
		fire.setTranslateX(missile.getTranslateX());
		fire.setTranslateY(missile.getTranslateY() + 25);
		main.getChildren().add(fire);
		main.getChildren().add(missile);
		missiles.put(missile, fire);
//		missilesQuantity++;
		MissileFlight missileFlight = new MissileFlight();
		missileFlight.exec(missile, fire);
		leftRight = !leftRight;
		if (missilesQuantity > 0) {
			missilesQuantity--;
		}
		
		if (missilesQuantity == 0) {
			missileArmed = Boolean.FALSE;
		}
	}

	public void grabBonus() {
		if (actualBonus != null && player.getBoundsInParent().intersects(actualBonus.getBoundsInParent())) {
			System.out.println(actualBonus.getClass().getSimpleName());
			switch (actualBonus.getClass().getSimpleName()) {
			case "Missile":
				missileArmed = Boolean.TRUE;
				missilesQuantity = 5;
				break;
			case "Shield":
				player.setShield((Shield) actualBonus);
				break;
			case "Life":
				increaseLife();
				displayLife.setText(String.valueOf(life));
				break;
			}
			actualBonus.setVisible(false);
			actualBonus = null;
		}
	}

	public void collision() {
		Meteor collisonMeteor = null;
		for (Meteor meteor : meteors) {
			if (meteor != null && player.getBoundsInParent().intersects(meteor.getBoundsInParent())) {
				collisonMeteor = meteor;
				collisonMeteor.setVisible(false);
				if (player.getShield() == null) {
					System.out.println("BOOM!");
					SoundLauncher soundPlayer = new SoundLauncher();
					soundPlayer.music("SpaceShipBoom");
					decreaseLife(meteor);
					displayLife.setText(String.valueOf(life));
				}
			}
		}
		if (collisonMeteor != null) {
			deleteMeteor(collisonMeteor, true);
		}
	}

	public static void increaseScore(Meteor meteor) {
		score += meteor.getScoreValue();
	}

	public static void increaseLife() {
		life++;
	}

	public static void decreaseLife(Meteor meteor) {
		life -= meteor.getDamage();
	}

	public boolean isMissileArmed() {
		return missileArmed;
	}

	public void destroyMeteor() {
		Meteor collisionMeteor = null;
		Missile collisionMissile = null;
		for (Meteor meteor : meteors) {
			for (Missile missile : missiles.keySet()) {
				if (meteor != null && missile.getBoundsInParent().intersects(meteor.getBoundsInParent())) {
					meteor.damageLife(missile.getDamage());
					if (meteor.getLife() <= 0) {
						AsteroidExplosion.exec(meteor);
						collisionMeteor = meteor;
					}
					collisionMissile = missile;
					collisionMissile.setVisible(false);
					missiles.get(collisionMissile).setVisible(false);
				}
			}
		}
		if (collisionMeteor != null) {
			increaseScore(collisionMeteor);
			deleteMeteor(collisionMeteor, true);
		}
		missiles.remove(collisionMissile);
	}

	public void deleteMissile(Missile missile) {
		missiles.remove(missile);
	}

	public int getLife() {
		return life;
	}

	public Node getDisplayLife() {
		return displayLife;
	}

	public SpaceShip getPlayer() {
		return player;
	}

	public void setLife() {
		life = (int) player.getLife();
	}

}
