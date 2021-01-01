package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.animation.FallingBonus;
import application.animation.FallingMeteor;
import application.fonction.SpawnBonus;
import application.fonction.SpawnMeteor;
import application.model.meteor.Meteor;
import application.model.spaceship.Bonus;
import application.model.spaceship.Shield;
import application.model.spaceship.SpaceShip;
import application.music.MusicLauncher;
import application.music.SoundLauncher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class InGameController implements Initializable {
	private static int maxMeteor = 3;
	private static int actualMeteor = 0;
	private static FallingMeteor meteorFall = new FallingMeteor();
	private static FallingBonus bonusFall = new FallingBonus();
	private static int score = 0;
	private static int life = 5;

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
	}

	public void deleteMeteor(Meteor meteor, boolean collision) {
		System.out.println("meteor delete");
		if (meteors.remove(meteor)) {
			if (!collision) {
				System.out.println("pas de collision");
				decreaseActualMeteor();
				increaseScore(meteor);
				displayScore.setText(String.valueOf(score));
			} else {
				System.out.println("collision");
				decreaseActualMeteor();
			}
		}
	}

	public void spawnBonus() {
		Bonus bonus = SpawnBonus.exec();
		actualBonus = bonus;
		main.getChildren().add(bonus);
		bonusFall.play(bonus);
	}

	public void deleteBonus(Bonus bonus) {
		actualBonus = null;
	}

	public void moveShipBy(int dx, int dy) {
		if (dx == 0 && dy == 0)
			return;
		final double cx = player.getAbs();
		final double cy = player.getOrd();
		double x = cx + dx;
		double y = cy + dy;
		System.out.println(x);
		System.out.println(y);

		moveShipTo(x, y);
	}

	private void moveShipTo(double x, double y) {
		// final double cx = player.getBoundsInLocal().getWidth() / 2;
		// final double cy = player.getBoundsInLocal().getHeight() / 2;

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

	public void grabBonus() {
		if (actualBonus != null && player.getBoundsInParent().intersects(actualBonus.getBoundsInParent())) {
			System.out.println("test");
			System.out.println("Bonus !");
			if (actualBonus instanceof Shield) {
				player.setShield((Shield) actualBonus);
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
					decreaseLife();
					displayLife.setText(String.valueOf(life));
				}
			}
		}
		if (collisonMeteor != null) {
			deleteMeteor(collisonMeteor, true);
		}
	}

	public static void increaseScore(Meteor meteor) {
		String typeMeteor = meteor.getClass().getSimpleName();
		if (typeMeteor.equals("Meteor")) {
			score += meteor.getDamage();
		} else if (typeMeteor.equals("ZigZagMeteor")) {
			score += 5;
		} else if (typeMeteor.equals("FireMeteor")) {
			score += 1;
		} else if (typeMeteor.equals("IcebergMeteor")) {
			score += 3;
		}
	}

	public static void decreaseLife() {
		life--;
	}

}
