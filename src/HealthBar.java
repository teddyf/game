import javafx.scene.Group;
 import javafx.scene.Scene;
 import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import javafx.scene.input.KeyCode;
 import javafx.scene.paint.Color;
 import javafx.scene.shape.Rectangle;
 import javafx.scene.shape.Shape;
 
 /**
  * Health bar object that displays player health as a health bar on the screen
  * Depends on javafx tools.  If not tinkered there will be no issues.  IF tinkered but conventions are followed then it should be fine.
  * @author theodorefranceschi
  *
  */
public class HealthBar {
	
	protected double height;
	protected double width;
	protected Shape shape;
	protected double posX;
	protected double posY;
	
	/**
	 * Constructs health bar with given height and width
	 * @param height
	 * @param width
	 */
	public HealthBar(double height, double width){
		this.height = height;
		this.width = width;
		Rectangle rect = new Rectangle(width,height);
		rect.setFill(Color.GREEN);
		this.shape = rect;
	}

	/**
	 * Gets height of the health bar
	 * @return
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Sets height of the health bar
	 * @param height
	 */
	public void setHeight(double height) {
		this.height = height;
		this.shape = new Rectangle(width,height);
	}

	/**
	 * Gets width of the health bar
	 * @return
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Sets the width of the health bar
	 * @param width
	 */
	public void setWidth(double width) {
		this.width = width;
		this.shape = new Rectangle(width,height);
	}
	
	/**
	 * Returns the shape of the health bar
	 * @return
	 */
	public Shape getShape(){
		return this.shape;
	}
	
	/**
	 * Determine the position of the health bar at a time step.  If the player is damaged, the health bar moves left
	 * @param healthLossFraction
	 */
	public void step(double healthLossFraction){
		posX -= healthLossFraction*width;
		this.shape.setLayoutX(posX);
	}

	/**
	 * Gets x position of the health bar
	 * @return
	 */
	public double getPosX() {
		return posX;
	}

	/**
	 * Sets the x position of the health bar
	 * @param posX
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}

	/**
	 * Gets the y position of the health bar
	 * @return
	 */
	public double getPosY() {
		return posY;
	}

	/**
	 * Sets the y position of the healthbar
	 * @param posY
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}
	
}
