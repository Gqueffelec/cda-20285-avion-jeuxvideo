package application.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class ScoreController implements Initializable {

	@FXML
	private ImageView stars1;

	@FXML
	private ImageView stars2;

	@FXML
	private ListView<?> listScore;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		JSONParser parser = new JSONParser();

		try (FileReader reader = new FileReader("historique.json")) {
			Object obj = parser.parse(reader);
			JSONArray scoreList = (JSONArray) obj;
			System.out.println(scoreList);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}