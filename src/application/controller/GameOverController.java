package application.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import application.animation.StarsAnimation;
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
		MenuController.getMusic().resume();
		StarsAnimation starsAnimation = new StarsAnimation();
		starsAnimation.play(stars1);
		starsAnimation.play(stars2);
	}

	public void switchView(MouseEvent event) {
		Text text = (Text) event.getSource();
		Stage stage = (Stage) text.getScene().getWindow();
		Parent root = null;
		System.out.println(text.getAccessibleText());
		if (text.getAccessibleText().equals("Score")) {
			System.out.println("test");
			ScoreController.setPreviousScene("GameOver");
		}
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

	@SuppressWarnings("unchecked")
	public static void writeNewScore() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		JSONObject jo = new JSONObject();
		jo.put("Name", NameController.getName() + "");
		jo.put("Score", InGameController.getScore() + "");
		jo.put("Date", dateFormat.format(date));
		JSONArray scores = new JSONArray();
		scores.add(jo);
		JSONObject finalJson = new JSONObject();
		finalJson.put("Scores", scores);
		Path jsonPath = Paths.get("historique.json");
		if (Files.exists(jsonPath)) {
			JSONParser parser = new JSONParser();
			try (FileReader reader = new FileReader("historique.json")) {
				JSONObject allData = (JSONObject) parser.parse(reader);
				JSONArray scoreData = (JSONArray) allData.get("Scores");
				scores.addAll(scoreData);
				allData.remove(scoreData);
				allData.put("Scores", scores);
				Files.write(jsonPath, allData.toJSONString().getBytes());
				System.out.println(allData);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try (PrintWriter writer = new PrintWriter("historique.json")) {
				writer.print(finalJson);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
	}
}
