package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class webview extends Application implements Initializable{
	
	@FXML
	private WebView myview;
	
	@FXML
	private TextField myfield;
	
	private WebEngine engine;
	private String homepage;
	private WebHistory history;
	private double webzoom;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		engine = myview.getEngine();
		homepage = "www.google.com";
		myfield.setText(homepage);
		webzoom =1;
		loadpage();
	}
	public void loadpage() {
		
		//engine.load("http://www.google.com");
		engine.load("http://"+myfield.getText());
	}
	public void refreshpage() {
		engine.reload();
	}
	public void zoomin() {
		webzoom += 0.25;
		myview.setZoom(webzoom);
	}
	public void zoomout() {
		webzoom -= 0.25;
		myview.setZoom(webzoom);
	}
	public void diaplayhistory() {
		history= engine.getHistory();
		ObservableList<WebHistory.Entry> entries = history.getEntries();
		for(WebHistory.Entry entry : entries) {
			System.out.println(entry.getUrl()+" "+entry.getLastVisitedDate());
			//System.out.println(entry);
		}
	}
	public void back() {
		history= engine.getHistory();
		ObservableList<WebHistory.Entry> entries = history.getEntries();
		history.go(-1);
		myfield.setText(entries.get(history.getCurrentIndex()).getUrl());
		
	}
	public void farward() {
		history= engine.getHistory();
		ObservableList<WebHistory.Entry> entries = history.getEntries();
		history.go(1);
		myfield.setText(entries.get(history.getCurrentIndex()).getUrl());

	}
	public void exicutejs() {
		engine.executeScript("window.location = \"https://www.youtube.com\";");
	}

	@Override
	public void start(Stage stage) {
		try {
		Parent root = FXMLLoader.load(getClass().getResource("webview.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("WebView!");
		stage.setMaximized(true);
		stage.show();
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	public static void  main(String args[]) {
		launch(args);
	}
	
}
