package application.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MenuController.getMusic().resume();
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
				JSONParser parser = new JSONParser();
				FileReader reader = new FileReader(test);
				Object obj = parser.parse(reader);
				JSONArray scoreList = (JSONArray) obj;
				PrintWriter writer = new PrintWriter(test);
				for (int i = 0; i < scoreList.size() + 1; i++) {
					if (i == 0) {
						writer.print("[ \n " + scoreList.get(0) + ", \n");
					} else if (i == scoreList.size()) {
						writer.append(jo.toJSONString() + " \n]");
						writer.close();
					} else if (i > 0 && i < scoreList.size()) {
						writer.append(scoreList.get(i).toString() + ", \n");
					}
				}
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		} else {
			try (PrintWriter writer = new PrintWriter("historique.json")) {
				writer.print("[\n" +jo.toJSONString() + "\n]");
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
