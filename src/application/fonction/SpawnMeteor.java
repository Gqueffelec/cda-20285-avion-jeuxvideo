package application.fonction;

import java.util.Random;

import application.controller.InGameController;
import application.model.meteor.FireMeteor;
import application.model.meteor.IceMeteor;
import application.model.meteor.IcebergMeteor;
import application.model.meteor.Meteor;
import application.model.meteor.ZigZagMeteor;

public class SpawnMeteor {
	public static Meteor exec() {
		Random rand = new Random();
		Meteor meteor = null;
		switch (rand.nextInt(5)) {
		case 0:
			meteor = new Meteor(600);
			break;
		case 1:
			meteor = new FireMeteor(600);
			break;
		case 2:
			meteor = new IceMeteor(600);
			break;
		case 3:
			meteor = new ZigZagMeteor(600);
			break;
		case 4:
			meteor = new IcebergMeteor(600);
			break;
		}
		System.out.println(meteor.getUri());
		InGameController.increaseActualMeteor();
		return meteor;
	}
}
