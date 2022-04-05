package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class mp3player extends Application implements Initializable {
	@FXML
	private Button playbutton,pausebutton,resetbutton,nextbutton,previousbutton;
	@FXML
	private AnchorPane mp3player;
	@FXML
	private Pane pane;
	@FXML
	private ComboBox<String> speedbox;
	@FXML
	private Slider volume;
	@FXML
	private ProgressBar songprogressbar;
	@FXML
	private Label songlabel;
	private File directory;
	private File[] files;
	private ArrayList<File> songs;
	private int[] speeds = {25,50,75,100,125,150,175,200};
	private int songnumber;
	private Timer timer;
	private TimerTask task;
	private boolean running;
	private Media media;
	private MediaPlayer mediaplayer;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		songs = new ArrayList<File>();
		
		directory = new File("music");
		files = directory.listFiles();
		
		if(files != null) {
			for(File file : files) {
				songs.add(file);
			}
		}
		media = new Media(songs.get(songnumber).toURI().toString());
		mediaplayer = new MediaPlayer(media);
		songlabel.setText(songs.get(songnumber).getName());
		for(int i = 0; i < speeds.length;i++) {
			speedbox.getItems().add(Integer.toString(speeds[i])+"%");
		}
		
		speedbox.setOnAction(this::changespeed);
		volume.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				mediaplayer.setVolume(volume.getValue()*0.01);
			}
		});
		songprogressbar.setStyle("-fx-accent: #9cb1b0");
	}
	
	public void playmedia() {
		begintimer();
		changespeed(null);
		mediaplayer.setVolume(volume.getValue()*0.01);
		mediaplayer.play();
	}
	public void pausemedia() {
		cancletimer();
		mediaplayer.pause();
	}
    public void resetmedia() {
    	songprogressbar.setProgress(0);
		mediaplayer.seek(Duration.seconds(0.0));
	}
    public void nextmedia() {
		if(songnumber < songs.size()-1) {
    	songnumber++;
    	mediaplayer.stop();
    	if(running) {
			cancletimer();
		}
    	media = new Media(songs.get(songnumber).toURI().toString());
		mediaplayer = new MediaPlayer(media);
		songlabel.setText(songs.get(songnumber).getName());
    	playmedia();
		}
		else {
			songnumber = 0;
			mediaplayer.stop();
			if(running) {
    			cancletimer();
    		}
	    	media = new Media(songs.get(songnumber).toURI().toString());
			mediaplayer = new MediaPlayer(media);
			songlabel.setText(songs.get(songnumber).getName());
			playmedia();
		}
	}
    public void previousmedia() {
    	if(songnumber == 0) {
    		mediaplayer.stop();
    		if(running) {
    			cancletimer();
    		}
    		songnumber = songs.size()-1;
    		media = new Media(songs.get(songnumber).toURI().toString());
    		mediaplayer = new MediaPlayer(media);
    		songlabel.setText(songs.get(songnumber).getName());
    		playmedia();
    	}
    	else {
    		mediaplayer.stop();
    		songnumber --; 
    		if(running) {
    			cancletimer();
    		}
    		media = new Media(songs.get(songnumber).toURI().toString());
    		mediaplayer = new MediaPlayer(media);
    		songlabel.setText(songs.get(songnumber).getName());
    		playmedia();
    	}
	}
    public void changespeed(ActionEvent event) {
    	if(speedbox.getValue() == null) {
    		mediaplayer.setRate(1.0);
    	}
    	else {
    	mediaplayer.setRate(Integer.parseInt(speedbox.getValue().substring(0, speedbox.getValue().length()-1))*0.01);
    	}
    }
    public void begintimer() {
		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				running = true;
				double current = mediaplayer.getCurrentTime().toSeconds();
				double end = media.getDuration().toSeconds();
				songprogressbar.setProgress(current/end);
				
				if(current/end == 1) {
					cancletimer();
					
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
	}
    public void cancletimer() {
		running = false;
		timer.cancel();
	}
	@Override
	public void start(Stage stage) {
		try{
			Parent root =FXMLLoader.load(getClass().getResource("mp3player.fxml"));
			Scene scene =new Scene(root);
			Image icon = new Image("mp3.png");
			stage.getIcons().add(icon);
			stage.setScene(scene);
			stage.setTitle("MP3 Player!");
			stage.show();
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent arg0) {
					Platform.exit();
					System.exit(0);
				}
			});
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		launch(args);
	}
}
