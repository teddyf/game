import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
public class Lazor extends GameObject{
	
	protected double xResistance;
	protected double yResistance;
	//Player constructor that sets initial position
	public Lazor(int posX, int posY){
		super(posX, posY);
		this.type = 0;
		this.image = new Image("upLazor.png");
		this.imageView = new ImageView(image);
		this.imageView.setFitHeight(20);
		//this.imageView.preserveRatioProperty();
		this.width = 10;
		this.height = 100;
		this.imageView.setX(posX-width/2);
		this.imageView.setY(posY-height/2);
	}
	
	//Defines behavior of player object for each time increment
	public void step(double timeElapsed){
		posX = posX + velX*timeElapsed;
		posY = posY + velY*timeElapsed;
		this.imageView.setX(posX-width/2);
		this.imageView.setY(posY-height/2);
		}
	
	//Returns bounds of an object in an array (x1,x2,y1,y2)
	public double[] getBounds(){
		double[] bounds = {posX-width/2,posX+width/2,posY-height/2,posY+height/2};
		return bounds;
	}
	
	//Returns type of game object 
	public int getType(){
		return this.type;
	}
	
}