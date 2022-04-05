package application;

import java.util.Random;


import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Food {
	private Position position;
	private Rectangle rectangle;

	private AnchorPane pane;
	private Random random = new Random();
	private Color color = Color.color(Math.random(),Math.random(), Math.random());
	private int size;
	
	public Food(double xpos,double ypos, AnchorPane pane,double size) {
		this.pane = pane;
		this.size = (int) size;
		position = new Position(xpos,ypos);
		rectangle = new Rectangle(position.getxpos(),position.getypos(),size,size);
		rectangle.setFill(color);
		rectangle.setArcHeight(20.0);
		rectangle.setArcWidth(20.0);
		pane.getChildren().add(rectangle);
	}
	public Position getposition() {return position;}
	public void movefood() { getrandomspotforfood();}
	
	
	public Rectangle getrectangle() {
		return rectangle;
	}
	public void getrandomspotforfood() {
		int positionx = random.nextInt(22);
		int positiony = random.nextInt(22);
		
		rectangle.setX(positionx * size);
		rectangle.setY(positiony * size);
		
		position.setxpos(positionx * size);
		position.setypos(positiony * size);
	}
}
