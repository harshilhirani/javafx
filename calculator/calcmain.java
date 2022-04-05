package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class calcmain extends Application{
	

	
	private static Stage historystage = null;

	public void createstage() {
		historystage = new Stage();
		historystage.setTitle(" Calculation History");
		historystage.setAlwaysOnTop(true);
		historystage.setResizable(false);
		historystage.initModality(Modality.APPLICATION_MODAL);
	}
	public static Stage gethistorystage() {
		return historystage;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("calculator.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		Image i = new Image("calculator.png");
		stage.getIcons().add(i);
		stage.show();
		createstage();
	}
	public static void main(String args[]) {
		launch(args);
	}
}
