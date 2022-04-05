package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class mediaview extends Application implements Initializable{
	@FXML
	private MediaView myview;
	
	@FXML
	private Button play,pause,reset;
	
	private File file;
	private Media media;
	private MediaPlayer mediaplayer;
	
	
	@Override
	public void initialize(URL arg0,ResourceBundle arg1) {
		file = new File("sample.mp4");
		media= new Media(file.toURI().toString());
		mediaplayer = new MediaPlayer(media);
		myview.setMediaPlayer(mediaplayer);
	}
	public void playmedia() {
		mediaplayer.play();
	}
    public void pausemedia() {
		mediaplayer.pause();
	}
    public void resetmedia() {
    	if(mediaplayer.getStatus()  != MediaPlayer.Status.READY) {
    	mediaplayer.seek(Duration.seconds(0.0));
    	}
	}
    
	@Override
	public void start(Stage stage) {
		try {
		Parent root = FXMLLoader.load(getClass().getResource("mediaview.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	public static void  main(String args[]) {
		launch(args);
	}
} 
