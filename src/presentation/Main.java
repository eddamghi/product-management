package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
	 public static void main(String[] args) {
		  launch(args);
	 }

	 @Override
	 public void start(Stage stage) throws Exception {
		  Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("products/productsLayout.fxml")));
		  Scene scene = new Scene(root,780,545);
		  scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/style.css")).toString());
		  stage.setTitle("Product Management");
		  stage.setScene(scene);
		  stage.show();

	 }
}