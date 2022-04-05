package application;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	static Stage stage;
	
	@FXML
	private AnchorPane pane;
	
	@Override
	public void start(Stage stage) throws Exception{
			Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			Image i = new Image("snake.png");
			stage.getIcons().add(i);
			stage.setResizable(false);
			stage.show();
		
	}
	public static Stage getstage() {
		
		return stage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
