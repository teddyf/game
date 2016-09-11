import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.*;
/**
 * Weak enemy object are the asteroids that populate the screen during the first level
 * This class defines their movement, rendering, and variables
 * @author theodorefranceschi
 *
 */
public class WeakEnemy extends GameObject {

	/**
	 * Constructor that sets initial position of weak enemy
	 * @param posX: initial x position
	 * @param posY: initial y position
	 */
	public WeakEnemy(int posX, int posY) {
		super(posX, posY);
		this.type = 1;
		this.image = new Image("Asteroid.gif");
		this.imageView = new ImageView(image);
		this.imageView.setFitHeight(20);
		this.imageView.setFitWidth(20);
		this.width = 20;
		this.height = 20;
		this.imageView.setX(posX-width/2);
		this.imageView.setY(posY-height/2);
	}

	/**
	 * Updates position and rendering of enemy during a given time step
	 */
	public void step(double timeElapsed) {
		posX += velX * timeElapsed;
		posY += velY * timeElapsed;
		
		this.imageView.setX(posX-width/2);
		this.imageView.setY(posY-height/2);
	}

	/**
	 * Returns the bounds of an enemy object
	 */
	public double[] getBounds() {
		double[] bounds = { posX - width / 2, posX + width / 2, posY - height / 2, posY + height / 2 };
		return bounds;
	}

	/**
	 * Returns the type of an objects
	 */
	public int getType(){
		return this.type;
	}
}
