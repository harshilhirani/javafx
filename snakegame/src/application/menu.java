package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class menu{
	private Stage stage1;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Button exit;
	
	@FXML
	public AnchorPane pane;
	
		
	
	@FXML
	public void play(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("snake.fxml"));
		root = loader.load();
		stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage1.setScene(scene);
		stage1.show();
	}
	
	
	@FXML
	public void exitgame(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit Game?");
		alert.setContentText("You Sure?");
		if(alert.showAndWait().get() == ButtonType.OK) {
			Main.stage = (Stage) exit.getScene().getWindow();
			Main.stage.close();
		}
	}

}
