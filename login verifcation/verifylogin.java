package application;
import javafx.application.Application;
//to run this program change login.fxml to verifyuserinput.fxml in Main class.java
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class verifylogin extends Application{
	@FXML
	private Label mylabel;
	@FXML
	private TextField mytextfield;
	@FXML
	private Button mybutton;
	int age;
	public void submit(ActionEvent event){
		try {
		age = Integer.parseInt(mytextfield.getText());
		if(age<18) {
			mylabel.setText("you are below 18!");
		}
		else {
			mylabel.setText("you are now signed up!");
		}
		}
		catch(NumberFormatException e) {
		mylabel.setText("enter only numbers!");
		}
		catch(Exception e) {
			mylabel.setText("Error...");
			}
	}
	@Override
	public void start(Stage Stage) {
		try{
		Parent root = FXMLLoader.load(getClass().getResource("verifyuserinput.fxml"));
		Scene scene = new Scene(root);
		Stage.setScene(scene);
		Stage.show();
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
