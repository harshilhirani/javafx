package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class controller implements Initializable{

	private final Double snakesize = 25.;
	
	private Rectangle snakehead ;
	Rectangle snaketail_1;
	
	double xpos; 
	double ypos;
	Food food;
	private Direction direction ; 
	
	private final List<Position> position = new ArrayList<>(); 
	
	private final ArrayList<Rectangle> snakebody = new ArrayList<>();
	
	private int gameticks = 0;
	
	private Color color1;

	
	@FXML
	private AnchorPane anchorpane;
	
	@FXML
	private Label titlelabel,score,labelhigh;
	@FXML
	private Button startbutton,back; 
	
	private int score1 = 0;
	public static int highscore = 0;
	
	Timeline timeline ; 
	 
	private  boolean canchangedirection;
	private double time = 0.2;
	private double shape = 20.0;
	
	
	
	@FXML
	void start(MouseEvent event) {
		for(Rectangle snake : snakebody) {
			anchorpane.getChildren().remove(snake);
		}
		titlelabel.setText("Snake Game!");
		score1 = 0;
		score.setText("Score: " + score1);
		gameticks = 0;
		position.clear();
		snakebody.clear();
		snakehead = new Rectangle(25,25,snakesize,snakesize);
		snaketail_1  = new Rectangle(snakehead.getX() - snakesize,snakehead.getY(),snakesize,snakesize);
		snaketail_1.setFill(Color.GRAY);
		snaketail_1.setArcHeight(shape);
		snaketail_1.setArcWidth(shape);
		snakehead.setArcHeight(shape);
		snakehead.setArcWidth(shape);
		xpos = snakehead.getLayoutX();
		ypos = snakehead.getLayoutY();
		direction = Direction.RIGHT; 
		canchangedirection = true; 
		food.movefood();
		snakebody.add(snakehead);		
		snakehead.setFill(Color.RED); 
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		snakebody.add(snaketail_1);
		anchorpane.getChildren().addAll(snakehead,snaketail_1);
	}
	
	@Override 
	public void initialize(URL url,ResourceBundle resoursebundle) {
		timeline = new Timeline(new KeyFrame(Duration.seconds(time),e ->{
			position.add(new Position(snakehead.getX() + xpos, snakehead.getY() + ypos));
			movesnakehead(snakehead);
			
			for(int i = 1 ; i < snakebody.size(); i++) {
				movesnaketail(snakebody.get(i),i);
				}
			canchangedirection = true;
			eatfood();
			gameticks++;
			if(checkifgameover(snakehead)) {
				timeline.stop();
			}
			
		}));
		food = new Food(-50,-50,anchorpane,snakesize);

	}
	
	
	
	public boolean checkifgameover(Rectangle snakehead) {
		if(xpos > 550.0 || xpos < -50.0 || ypos < -50.0 || ypos > 550.0) {
			titlelabel.setText("Game Over!");
			
			return true;
		}
		else if(snakeselfhit()) {
			titlelabel.setText("Game Over!");

			return true;
		}
		return false;
	}
	
	public boolean snakeselfhit() {
		int size = position.size()-1;
		if(size >2) {
			for(int i = size - snakebody.size(); i < size; i++ ) {
				if(position.get(size).getxpos() == (position.get(i).getxpos()) && position.get(size).getypos() == (position.get(i).getypos())) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void eatfood() {
		if(xpos + snakehead.getX() == food.getposition().getxpos() && ypos + snakehead.getY() == food.getposition().getypos()) {
			foodcantspawninsidesnake();
			addsnaketail();
			time = time + 0.025;
			score1++;
			if(score1 > highscore) {
				highscore = score1;
			}
			score.setText("Score: "+score1);
			labelhigh.setText("Highscore: "+highscore);

			color1 =  Color.color(Math.random(),Math.random(), Math.random());
			if(color1 != Color.BLACK) {
			food.getrectangle().setFill(color1);
			}
			else {
				color1 =  Color.color(Math.random(),Math.random(), Math.random());
				food.getrectangle().setFill(color1);

			}
		}
	}
	
	private void foodcantspawninsidesnake() {
		food.movefood();
		while(isfoodinsidesnake()) {
			food.movefood();
		}
	}
	
	private boolean isfoodinsidesnake() {
		int size = position.size();
		if(size > 2) {
			for(int i = size - snakebody.size(); i < size; i++) {
				if(food.getposition().getxpos() == (position.get(i).getxpos()) 
						&& food.getposition().getypos() == (position.get(i).getypos())) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	@FXML
	void mousekeypressed(KeyEvent event) {
		if(canchangedirection) {
		if((event.getCode().equals(KeyCode.W) ||event.getCode().equals(KeyCode.UP))&& direction != Direction.DOWN) {
			direction = Direction.UP;
		}
		else if((event.getCode().equals(KeyCode.S) || event.getCode().equals(KeyCode.DOWN)) && direction != Direction.UP) {
			direction = Direction.DOWN;
		}
		else if((event.getCode().equals(KeyCode.A) ||event.getCode().equals(KeyCode.LEFT)) && direction != Direction.RIGHT) {
			direction = Direction.LEFT;
		}
		else if((event.getCode().equals(KeyCode.D) ||event.getCode().equals(KeyCode.RIGHT)) && direction != Direction.LEFT) {
			direction = Direction.RIGHT;
		}
		canchangedirection =false;
		}
	}
		
		
	
	@FXML
	void addbodypart(ActionEvent event) {
		addsnaketail();
	} 
	
	private void movesnakehead(Rectangle snakehead) {
		if(direction.equals(Direction.RIGHT)) {
			xpos = xpos + snakesize;
			snakehead.setTranslateX(xpos);
		}
		else if(direction.equals(Direction.LEFT)) {
			xpos = xpos - snakesize;
			snakehead.setTranslateX(xpos);
		}
		else if(direction.equals(Direction.UP)) {
			ypos = ypos - snakesize;
			snakehead.setTranslateY(ypos);
		}
		else if(direction.equals(Direction.DOWN)) {
			ypos = ypos + snakesize;
			snakehead.setTranslateY(ypos);
		}
	}
	
	public void movesnaketail(Rectangle snaketail,int tailnumber) {
		double ypos = position.get(gameticks - tailnumber +1).getypos() - snaketail.getY();
		double xpos = position.get(gameticks - tailnumber +1).getxpos()-snaketail.getX();
		snaketail.setTranslateX(xpos);
		snaketail.setTranslateY(ypos);
	}
	
	private void addsnaketail() {
		Rectangle rectangle = snakebody.get(snakebody.size()-1);
		Rectangle snaketail = new Rectangle(
				 snakebody.get(1).getX() + xpos + snakesize,
				 snakebody.get(1).getY() + ypos,
				snakesize,snakesize);
		snaketail.setFill(Color.GRAY);
		snaketail.setArcWidth(shape);
		snaketail.setArcHeight(shape);
		snakebody.add(snaketail);
		anchorpane.getChildren().add(snaketail);
				
	}
	private Stage stage1;
	private Scene scene;
	
	@FXML
	public void previous(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
		Parent root = loader.load();
		stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage1.setScene(scene);
		stage1.show();
	}

}
