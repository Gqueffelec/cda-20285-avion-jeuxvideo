package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import application.animation.AsteroidExplosion;
import application.animation.BossArrival;
import application.animation.EnnemiShipFlight;
import application.animation.FallingBonus;
import application.animation.FallingMeteor;
import application.animation.LaserFlight;
import application.animation.MissileFlight;
import application.animation.MovingBackground;
import application.animation.StarsAnimation;
import application.fonction.GameLoop;
import application.fonction.SpawnBonus;
import application.fonction.SpawnEnnemi;
import application.fonction.SpawnLaser;
import application.fonction.SpawnMeteor;
import application.fonction.SpawnMissile;
import application.model.bonus.Bonus;
import application.model.bonus.Shield;
import application.model.ennemi.Ennemi;
import application.model.ennemi.meteor.Meteor;
import application.model.ennemi.ship.BossShip;
import application.model.ennemi.ship.EnnemiLaser;
import application.model.ennemi.ship.EnnemiSpaceShip;
import application.model.spaceship.Laser;
import application.model.spaceship.Missile;
import application.model.spaceship.SpaceShip;
import application.model.spaceship.Weapons;
import application.music.MusicLauncher;
import application.music.SoundLauncher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

public class InGameController implements Initializable {
	private final int MAX_UPGRADE = 3;
	private static int maxMeteor = 3;
	private static int maxEnnemi = 1;
	private static int actualMeteor;
	private static int actualEnnemi;
	private static int score;
	private static int life;
	private static FallingMeteor meteorFall = new FallingMeteor();
	private static FallingBonus bonusFall = new FallingBonus();
	private static EnnemiShipFlight ennemiMovement = new EnnemiShipFlight();
	private boolean leftRight = true;
	private boolean missileArmed = false;
	private boolean bomb = false;
	private int missilesQuantity = 0;
	private int currentUpgrade = 1;
	private boolean bossArrival = false;
	protected boolean bossIsActive = false;

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
	private Map<Weapons, ImageView> playerProjectiles = new HashMap<>();
	@FXML
	private List<EnnemiLaser> ennemiProjectiles = new ArrayList<>();
	@FXML
	private List<Ennemi> ennemis = new ArrayList<>();
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
	@FXML
	private BossShip boss;

	@FXML
	void keyGame(MouseEvent event) {
		GameLoop.setMouseOrKey(true);
	}

	@FXML
	void mouseGame(MouseEvent event) {
		GameLoop.setMouseOrKey(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		life = 5;
		actualMeteor = 0;
		score = 0;
		actualEnnemi = 0;
		FallingMeteor.setMaxSpeed(1);
		setMaxMeteor(3);
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

	public static void setActualMeteor(int actualMeteor) {
		InGameController.actualMeteor = actualMeteor;
	}

	public void spawnMeteor() {
		Meteor meteor = SpawnMeteor.exec();
		this.ennemis.add(meteor);
		this.main.getChildren().add(meteor);
		meteorFall.play(meteor);
	}

	public void deleteMeteor(Meteor meteor, boolean collision) {
		System.out.println("meteor delete");
		if (ennemis.remove(meteor)) {
			if (!collision) {
				increaseScore(meteor);
				displayScore.setText(String.valueOf(score));
			}
			decreaseActualMeteor();
		}
	}

	public void spawnBonus() {
		if (actualBonus == null) {
			Bonus bonus = SpawnBonus.exec();
			actualBonus = bonus;
			main.getChildren().add(bonus);
			bonusFall.play(bonus);
		}

	}

	public void moveShipMouseBy(double dx, double dy) {

		player.setTranslateX(dx);
		player.setTranslateY(dy);
		player.setAbs(dx);
		player.setOrd(dy);

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
		} else if (dx < 0) {
			player.setRotate(-5);
			player.setRotate(-10);
		} else {
			player.setRotate(5);
			player.setRotate(10);
		}
		moveShipTo(player.getAbs() + dx, player.getOrd() + dy);
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
		playerProjectiles.put(missile, fire);
		MissileFlight missileFlight = new MissileFlight();
		missileFlight.exec(missile, fire);
		leftRight = !leftRight;
		if (missilesQuantity > 0) {
			missilesQuantity--;
		} else {
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
			case "Bomb":
				bomb = Boolean.TRUE;
				break;
			case "Laser":
				if (currentUpgrade < MAX_UPGRADE) {
					currentUpgrade++;
				}
				break;
			default:
				break;
			}
			actualBonus.setVisible(false);
			actualBonus = null;
		}
	}

	public void collision() {
		Meteor collisonMeteor = null;
		for (Ennemi ennemi : ennemis) {

			if (ennemi != null && player.getBoundsInParent().intersects(ennemi.getBoundsInParent())) {
				if (ennemi instanceof Meteor) {
					collisonMeteor = (Meteor) ennemi;
					collisonMeteor.setVisible(false);
				}
				if (player.getShield() == null) {
					System.out.println("BOOM!");
					SoundLauncher soundPlayer = new SoundLauncher();
					soundPlayer.music("SpaceShipBoom");
					decreaseLife(ennemi);
					displayLife.setText(String.valueOf(life));
				}
			}
		}
		if (collisonMeteor != null) {
			deleteMeteor(collisonMeteor, true);
		}
	}

	public void hitByEnnemiLaser() {
		EnnemiLaser laserImpact = null;
		for (EnnemiLaser ennemiLaser : ennemiProjectiles) {
			if (ennemiLaser != null && player.getBoundsInParent().intersects(ennemiLaser.getBoundsInParent())) {
				laserImpact = ennemiLaser;
				laserImpact.setVisible(false);
				if (player.getShield() == null) {
					decreaseLife(ennemiLaser);
					displayLife.setText(String.valueOf(life));
				}
			}
		}
		if (laserImpact != null) {
			deleteEnnemiLaser(laserImpact);
		}
	}

	private void deleteEnnemiLaser(EnnemiLaser laserImpact) {
		this.ennemiProjectiles.remove(laserImpact);
	}

	public boolean isMissileArmed() {
		return missileArmed;
	}

	private static void decreaseLife(EnnemiLaser ennemiLaser) {
		life -= ennemiLaser.getDamage();
	}

	public boolean getBomb() {
		return bomb;
	}

	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}

	public void destroyEnemy() {
		Ennemi collisionEnnemi = null;
		Weapons collisionWeapon = null;
		for (Ennemi ennemi : ennemis) {
			for (Weapons weapon : playerProjectiles.keySet()) {
				if (ennemi != null && weapon.getBoundsInParent().intersects(ennemi.getBoundsInParent())) {
					ennemi.damageLife(weapon.getDamage());
					if (ennemi.getLife() <= 0) {
						AsteroidExplosion.exec(ennemi);
						collisionEnnemi = ennemi;
					}
					collisionWeapon = weapon;
					weapon.setVisible(false);
					if (weapon instanceof Missile) {
						playerProjectiles.get(collisionWeapon).setVisible(false);
					}
				}
			}
		}
		if (collisionEnnemi != null) {
			if (collisionEnnemi instanceof Meteor) {
				deleteMeteor((Meteor) collisionEnnemi, false);
			} else if (collisionEnnemi instanceof EnnemiSpaceShip) {
				deleteEnnemi((EnnemiSpaceShip) collisionEnnemi);
			}
		}
		playerProjectiles.remove(collisionWeapon);
	}

	public void destroyAllMeteors() {
		List<Meteor> meteorsDestroyed = new ArrayList<>();
		for (Ennemi ennemi : ennemis) {
			if (ennemi instanceof Meteor) {
				AsteroidExplosion.exec(ennemi);
				meteorsDestroyed.add((Meteor) ennemi);
			}
		}

		this.ennemis.removeAll(meteorsDestroyed);
	}

	private void deleteEnnemi(EnnemiSpaceShip collisionEnnemi) {
		System.out.println("spaceship delete");
		GameLoop.setEnnemiTimer(System.currentTimeMillis());
		if (ennemis.remove(collisionEnnemi)) {
			increaseScore(collisionEnnemi);
			displayScore.setText(String.valueOf(score));
			decreaseActualEnnemi();
		}
	}

	public void deleteWeapon(Weapons weapon) {
		playerProjectiles.remove(weapon);
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

	public void fire() {
		switch (currentUpgrade) {
		case 1:
			fireLaser();
			break;
		case 2:
			fireDoubleLaser();
			break;
		case 3:
			fireTripleLaser();
			break;
		default:
			break;
		}
	}

	public void fireLaser() {
		Laser laser = SpawnLaser.simpleFireRate(player.getTranslateX(), player.getTranslateY());
		main.getChildren().add(laser);
		playerProjectiles.put(laser, null);
		LaserFlight laserFlight = new LaserFlight();
		laserFlight.exec(laser);
	}

	public void fireDoubleLaser() {
		Laser[] lasers = SpawnLaser.doubleFireRate(player.getTranslateX(), player.getTranslateY());
		main.getChildren().add(lasers[0]);
		main.getChildren().add(lasers[1]);
		playerProjectiles.put(lasers[0], null);
		playerProjectiles.put(lasers[1], null);
		LaserFlight laserFlight01 = new LaserFlight();
		LaserFlight laserFlight02 = new LaserFlight();
		laserFlight01.exec(lasers[0]);
		laserFlight02.exec(lasers[1]);
	}

	public void fireTripleLaser() {
		Laser[] lasers = SpawnLaser.tripleFireRate(player.getTranslateX(), player.getTranslateY());
		main.getChildren().add(lasers[0]);
		main.getChildren().add(lasers[1]);
		main.getChildren().add(lasers[2]);
		playerProjectiles.put(lasers[0], null);
		playerProjectiles.put(lasers[1], null);
		playerProjectiles.put(lasers[2], null);
		LaserFlight laserFlight01 = new LaserFlight();
		LaserFlight laserFlight02 = new LaserFlight();
		LaserFlight laserFlight03 = new LaserFlight();
		laserFlight01.exec(lasers[0]);
		laserFlight02.exec(lasers[1]);
		laserFlight03.exec(lasers[2]);
	}

	public void spawnEnnemi() {
		EnnemiSpaceShip ennemiShip = SpawnEnnemi.exec();
		this.ennemis.add(ennemiShip);
		this.main.getChildren().add(ennemiShip);
		ennemiMovement.exec(ennemiShip);
	}

	public int getActualEnnemi() {
		return actualEnnemi;
	}

	public static void increaseActualEnnemi() {
		InGameController.actualEnnemi++;
	}

	public static void decreaseActualEnnemi() {
		InGameController.actualEnnemi--;
	}

	public static void increaseActualMeteor() {
		InGameController.actualMeteor++;
	}

	public static void decreaseActualMeteor() {
		InGameController.actualMeteor--;
	}

	public static void increaseScore(Ennemi collisionEnnemi) {
		System.out.println("score avant " + score);
		score += collisionEnnemi.getScoreValue();
		System.out.println("score apres " + score);
	}

	public static void increaseLife() {
		life++;
	}

	public static void decreaseLife(Ennemi ennemi) {
		life -= ennemi.getDamage();
	}

	public int getMaxEnnemi() {
		return maxEnnemi;
	}

	public static void setMaxEnnemi(int maxEnnemi) {
		InGameController.maxEnnemi = maxEnnemi;
	}

	public void fireEnnemiLaser() {
		for (Ennemi ennemi : ennemis) {
			if (ennemi instanceof EnnemiSpaceShip) {
				EnnemiLaser laser = SpawnLaser.execEnnemi(ennemi.getTranslateX(), ennemi.getTranslateY());
				this.main.getChildren().add(laser);
				this.ennemiProjectiles.add(laser);
				LaserFlight laserFlight = new LaserFlight();
				laserFlight.exec(laser);
			}
		}
	}

	public void spawnBoss() {
		if (bossArrival && ennemis.size() == 0 && boss == null) {
			this.boss = new BossShip(600);
			this.main.getChildren().add(boss);
			BossArrival bossAnimation = new BossArrival();
			bossAnimation.exec(boss);
		}
	}

	public void prepareBoss() {
		actualMeteor = 20;
		actualEnnemi = 10;
		bossArrival = true;
	}

	public void fireBossLaser() {
		if (boss != null && boss.isActive()) {
			Random rand = new Random();
			EnnemiLaser laser = SpawnLaser.execEnnemi(boss.getTranslateX(), boss.getTranslateY() + 50);
			EnnemiLaser laser2 = SpawnLaser.execEnnemi(boss.getTranslateX() - rand.nextInt(100),
					boss.getTranslateY() + 50);
			EnnemiLaser laser3 = SpawnLaser.execEnnemi(boss.getTranslateX() + rand.nextInt(100),
					boss.getTranslateY() + 50);
			EnnemiLaser laser4 = SpawnLaser.execEnnemi(boss.getTranslateX() - rand.nextInt(200),
					boss.getTranslateY() + 50);
			EnnemiLaser laser5 = SpawnLaser.execEnnemi(boss.getTranslateX() + rand.nextInt(200),
					boss.getTranslateY() + 50);
			this.main.getChildren().add(laser);
			this.main.getChildren().add(laser2);
			this.main.getChildren().add(laser3);
			this.main.getChildren().add(laser4);
			this.main.getChildren().add(laser5);
			this.ennemiProjectiles.add(laser);
			this.ennemiProjectiles.add(laser2);
			this.ennemiProjectiles.add(laser3);
			this.ennemiProjectiles.add(laser4);
			this.ennemiProjectiles.add(laser5);
			LaserFlight laserFlight = new LaserFlight();
			laserFlight.exec(laser);
			laserFlight.exec(laser2);
			laserFlight.exec(laser3);
			laserFlight.exec(laser4);
			laserFlight.exec(laser5);
		}
	}

	public void destroyBoss() {
		BossShip collisionEnnemi = null;
		Weapons collisionWeapon = null;
		for (Weapons weapon : playerProjectiles.keySet()) {
			if (boss != null && weapon.getBoundsInParent().intersects(boss.getBoundsInParent())) {
				boss.damageLife(weapon.getDamage());
				if (boss.getLife() <= 0) {
					AsteroidExplosion.exec(boss);
					collisionEnnemi = boss;
				}
				collisionWeapon = weapon;
				weapon.setVisible(false);
				if (weapon instanceof Missile) {
					playerProjectiles.get(collisionWeapon).setVisible(false);
				}
			}
		}
		if (collisionEnnemi != null) {
			deleteBoss(collisionEnnemi);
		}
		playerProjectiles.remove(collisionWeapon);
	}

	private void deleteBoss(BossShip collisionEnnemi) {
		boss.setActive(false);
		score += boss.getScoreValue();
		boss = null;
		actualMeteor = 0;
		actualEnnemi = 0;
		setMaxMeteor(getMaxMeteor() + 2);
		FallingMeteor.setMaxSpeed(FallingMeteor.getMaxSpeed() * 1.4);
		GameLoop.nextStart(System.currentTimeMillis());
		bossArrival = false;
	}

	public void moveShipWithMouse(double mouseX, double mouseY) {
		this.player.setTranslateX(mouseX);
		this.player.setTranslateY(mouseY);
	}

}
