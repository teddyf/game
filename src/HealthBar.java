 import javafx.scene.Group;
 import javafx.scene.Scene;
 import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import javafx.scene.input.KeyCode;
 import javafx.scene.paint.Color;
 import javafx.scene.shape.Rectangle;
 import javafx.scene.shape.Shape;
 
public class HealthBar {
	
	protected double height;
	protected double width;
	protected Shape shape;
	protected double posX;
	protected double posY;
	
	public HealthBar(double height, double width){
		this.height = height;
		this.width = width;
		Rectangle rect = new Rectangle(width,height);
		rect.setFill(Color.GREEN);
		this.shape = rect;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
		this.shape = new Rectangle(width,height);
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
		this.shape = new Rectangle(width,height);
	}
	
	public Shape getShape(){
		return this.shape;
	}
	
	public void step(double healthLossFraction){
		posX -= healthLossFraction*width;
		this.shape.setLayoutX(posX);
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}
	
}
