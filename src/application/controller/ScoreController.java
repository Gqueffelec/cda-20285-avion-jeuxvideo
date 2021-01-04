package application.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScoreController implements Initializable {

	private static String previousScene;

	@FXML
	private ImageView stars1;

	@FXML
	private ImageView stars2;

	@FXML
	private VBox listScore;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		JSONParser parser = new JSONParser();

		try (FileReader reader = new FileReader("historique.json")) {
			JSONObject scoreList = (JSONObject) parser.parse(reader);
			JSONArray scores = (JSONArray) scoreList.get("Scores");
			TreeMap<String, Long> scoreMap = new TreeMap<>();
			for (Object object : scores) {
				JSONObject jsonObject = (JSONObject) object;
				StringBuilder nom = new StringBuilder((String) jsonObject.get("Name"));
				for (int i = 0; i <= 6 - nom.length(); i++) {
					nom.append(" ");
				}
				String date = (String) jsonObject.get("Date");
				Long test = Long.parseLong((String) jsonObject.get("Score"));
				scoreMap.put(nom.toString() + "." + date, Long.parseLong((String) jsonObject.get("Score")));
			}
			Map<String, Long> topTwenty = scoreMap.entrySet().stream()
					.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(20).collect(Collectors
							.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
			for (Entry<String, Long> object : topTwenty.entrySet()) {
				String[] nomEtDate = object.getKey().split("\\.");
				Text text = new Text();
				text.setFill(Color.WHITE);
				text.setFont(new Font(15));
				text.setText(nomEtDate[0] + " " + object.getValue() + " " + nomEtDate[1]);
				listScore.getChildren().add(text);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void returnPreviousMenu(MouseEvent e) {
		Text text = (Text) e.getSource();
		Stage stage = (Stage) text.getScene().getWindow();
		Parent root = null;
		System.out.println(previousScene);
		try {
			root = FXMLLoader.load(getClass().getResource("/application/view/" + previousScene + ".fxml"));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		stage.setScene(new Scene(root));
	}

	public static void setPreviousScene(String pPreviousScene) {
		previousScene = pPreviousScene;
	}

}