package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class tictac extends Application implements Initializable{
	@FXML
	private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,restart;
	
	@FXML
	private Label mylabel;
	
	private int playerturn=0; 
	private int turn =0;
	ArrayList<Button> buttons;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buttons = new ArrayList<>(Arrays.asList(b1,b2,b3,b4,b5,b6,b7,b8,b9));
	
		buttons.forEach(button ->{
			setupButton(button);
			button.setFocusTraversable(false);
		});
	}
	@FXML
	void restartgame(ActionEvent event) {
		buttons.forEach(this :: resetButton);
	}
	public void resetButton(Button button) {
		button.setDisable(false);
		button.setText("");
		mylabel.setText("Tic Tac Toe");
	}
	public void setupButton(Button button) {
		button.setOnMouseClicked(nouseEvent ->{
			setPlayerSymbol(button);
			button.setDisable(true);
			turn ++;
			checkifgameover();
		});
	}
	
	private void checkifgameover() {
		for(int a = 0; a<8; a++) {
			String line = switch(a) {
			case 0 -> b1.getText() + b2.getText() + b3.getText();
			case 1 -> b4.getText() + b5.getText() + b6.getText();
			case 2 -> b7.getText() + b8.getText() + b9.getText();
			case 3 -> b1.getText() + b5.getText() + b9.getText();
			case 4 -> b3.getText() + b5.getText() + b7.getText();
			case 5 -> b1.getText() + b4.getText() + b7.getText();
			case 6 -> b2.getText() + b5.getText() + b8.getText();
			case 7 -> b3.getText() + b6.getText() + b9.getText();
			default -> null;
			};
				if(line.equals("XXX")) {
					mylabel.setText("X Won!"); 
					turn =0;
					buttons.forEach(button ->{
						button.setDisable(true);
					});
				}
				else if(line.equals("OOO")) {
					mylabel.setText("O Won!");
					turn = 0;
					buttons.forEach(button ->{
						button.setDisable(true);
					});
				}
				else if(turn == 9) {
					mylabel.setText("Game Draw!");
					turn =0;
				}
			
		}
}

	private void setPlayerSymbol(Button button) {
		if(playerturn % 2 == 0) {
			button.setText("X"); 
			playerturn = 1;
		}
		else{
			button.setText("O");
			playerturn = 0;
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("tictac1.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Tic Tac Toe");
		Image i = new Image("tictac.png");
		stage.getIcons().add(i);
		stage.show();
	}
	public static void main(String args[]) {
		launch(args);
	}
	

}
