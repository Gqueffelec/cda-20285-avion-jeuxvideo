package application.controller;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;

//import org.json.simple.JSONObject;

import application.animation.GameOverStars;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOverController implements Initializable {

	private static FileWriter file;
	@FXML
	private Text exit;
	@FXML
	private Text replay;
	@FXML
	private Text score;
	@FXML
	private ImageView stars1;
	@FXML
	private ImageView stars2;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		JSONObject jo = new JSONObject();
		jo.put("Name", NameController.getName());
		jo.put("Score", InGameController.getScore());
		jo.put("Date", dateFormat.format(date));
		GameOverStars starsAnimation = new GameOverStars();
		starsAnimation.play(stars1);
		starsAnimation.play(stars2);
		String test = "historique.json";

		if (Files.exists(Paths.get(test))) {
			try {
				file = new FileWriter(test, true);
				file.append(jo.toJSONString());
			} catch (IOException e) {
				e.printStackTrace();

			} finally {

				try {
					file.flush();
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try (PrintWriter writer = new PrintWriter("historique.json")) {
				writer.print(jo.toJSONString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
	}

	public void switchView(MouseEvent event) {
		Text text = (Text) event.getSource();
		Stage stage = (Stage) text.getScene().getWindow();
		System.out.println(text.getAccessibleText());
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/view/" + text.getAccessibleText() + ".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.setScene(new Scene(root));
	}

	public void exit(MouseEvent event) {
		System.exit(0);
	}
}
