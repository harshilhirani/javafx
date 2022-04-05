package application;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import calc.EvaluateString;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class calculator implements Initializable{
	
	@FXML
	Label expression,answer; 
	
	private  ArrayList<String> calculationhist = new ArrayList<>();

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	public  void addcalculation(String expression, String result) {
		this.calculationhist.add(expression + " = " + result);
	}
	
	
	public void onmouseclick(MouseEvent mouseevent){
		Button button = (Button)mouseevent.getSource();
		String s = button.getText();
		
		
		switch(s) {
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "0":
		case ".":
			insertno(s);
			break;
		case "+":
		case "*":
		case "/":
		case "-":
			insertopr(s);
			break;
		case "Clear":
			clearexp();
			break;
		case"=":
			int ans = (EvaluateString.evalutate(this.expression.getText()));
			setresult(String.valueOf(ans));
			addcalculation(expression.getText(),Integer.toString(ans));
			break;
		case"Ans":
			expression.setText(answer.getText().substring(2,answer.getText().length()));
			break;
		case "Delete":
			if(expression.getText().isEmpty()) {
				break;
			}
			else {
			expression.setText(expression.getText().substring(0, expression.getText().length()-1));
			break;
			}
		case "History":
			openhistorywindow();
			break;
		}
	}

	public void insertno(String number) {
		expression.setText(expression.getText()+number);

	}
	public void insertopr(String opr) {
		expression.setText(expression.getText()+" "+opr+" ");
	}
	public void clearexp() {
		expression.setText("");
	} 
	public Label getexp() {
		return expression;
	}
	public Label getans() {
		return answer;
	}
	public void setresult(String newans) {
		this.answer.setText("= "+ newans);
	}
	
	public void openhistorywindow() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("calchistory.fxml"));
			Parent root1 = loader.load();
			calcmain.gethistorystage().setScene(new Scene(root1)); 
			calchistory ch = loader.getController();
			ch.initializecalculation(calculationhist);
			calcmain.gethistorystage().show();
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
	
	
}
