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
	private static String scoree = String.valueOf(score);
	private static int life = 10;
	private static String lifee = String.valueOf(life);

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
		displayScore.setText(scoree);
		displayLife.setText(lifee);
		SpaceShip player = new SpaceShip(600, 900);
		main.getChildren().add(player);
		MusicLauncher.music();

	}

	public static String getScoree() {
		return scoree;
	}

	public void spawnMeteor() {
		Meteor meteor = SpawnMeteor.exec();
		meteors.add(meteor);
		main.getChildren().add(meteor);
		meteorFall.play(meteor);
	}

	public void deleteMeteor(Meteor meteor) {
		meteors.remove(meteor);
		decreaseActualMeteor();
		score++;
		scoree = String.valueOf(score);
		displayScore.setText(scoree);
		life--;
		lifee = String.valueOf(life);
		displayLife.setText(lifee);
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
			if (meteor != null && player.getBoundsInParent().intersects(meteor.getBoundsInParent())
					&& player.getShield() == null) {
				System.out.println("BOOM!");
				collisonMeteor = meteor;
				collisonMeteor.setVisible(false);
				SoundLauncher.music("SpaceShipBoom");
			}
		}
		if (collisonMeteor != null) {
			meteors.remove(collisonMeteor);
		}
	}

}
