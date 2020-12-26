package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.animation.FallingMeteor;
import application.fonction.SpawnMeteor;
import application.model.meteor.Meteor;
import application.model.spaceship.Bonus;
import application.model.spaceship.SpaceShip;
import application.music.MusicLauncher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

public class InGameController implements Initializable {
	private static int maxMeteor = 3;
	private static int actualMeteor = 0;
	private static FallingMeteor meteorFall = new FallingMeteor();
	private static int timerSpawn = 1000;
	@FXML
	private List<Meteor> meteors = new ArrayList<>();
	@FXML
	private StackPane main;
	@FXML
	private SpaceShip player;
	@FXML
	private List<Bonus> allBonus = new ArrayList<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SpaceShip player = new SpaceShip(600, 900);
		main.getChildren().add(player);
		MusicLauncher.music();
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

}
