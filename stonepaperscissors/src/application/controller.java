package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class controller implements Initializable{
	@FXML
	private Button stonebtn,paperbtn,scissorsbtn;
	
	@FXML
	private Label playerscore,computerscore,result;
	
	@FXML
	private ImageView player,computer;
	
	private static final String paper = "paper";
	private static final String stone = "stone";
	private static final String scissors = "scissors";
	private Image image;
	
	@FXML
	private void playerturn(ActionEvent event) {
		String playerchoice=null;
		switch(((Button) event.getSource()).getId()){
			case "paperbtn":
				image = new Image("papper.png");
				playerchoice = paper;
				break;
			case "stonebtn":
				image = new Image("stone.png");
				playerchoice = stone;
				break;
			case "scissorsbtn":
				image = new Image("scissors.png");
				playerchoice = scissors;
				break;
		}
		player.setImage(image);
		winner(playerchoice,computerturn());
	}
	private String computerturn() {
		String computerchoice = null;
		int index = (int) (Math.random() *3);
		switch(index) {
		case 0:
			image = new Image("papper.png");
			computerchoice = paper;
			break;
		case 1:
			image = new Image("stone.png");
			computerchoice = stone;
			break;
		case 2:
			image = new Image("scissors.png");
			computerchoice = scissors;
			break;
		}
		computer.setImage(image);
		return computerchoice;
	}
	
	public void playerwin() {
		result.setText("You won!");
		playerscore.setText(String.valueOf(Integer.parseInt(playerscore.getText())+1));
	}
	public void computerwin() {
		result.setText("You lost!");
		computerscore.setText(String.valueOf(Integer.parseInt(computerscore.getText())+1));
	}
	
	public void winner(String p,String c) {
		if(p.equals(c)) {
			result.setText("Draw!");
		}
		else if(p.equals(stone)) {
			if(c.equals(paper)) {
				computerwin();
			}
			else {
				playerwin();
			}
		}
		else if(p.equals(paper)) {
			if(c.equals(stone)) {
				playerwin();
			}
			else {
				computerwin();
			}
		}
		else if(p.equals(scissors)) {
			if(c.equals(stone)) {
				computerwin();
			}
			else {
				playerwin();
			}
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
