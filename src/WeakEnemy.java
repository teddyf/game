import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.*;

public class WeakEnemy extends GameObject {

	public WeakEnemy(int posX, int posY) {
		super(posX, posY);
		this.type = 1;
		Circle circ = new Circle();
		circ.setFill(Color.RED);
		int radius = (int) (10 * Math.random() + 10);
		circ.setRadius(radius);
		this.shape = circ;
		this.width = radius * 2;
		this.height = radius * 2;
		this.shape.setLayoutX(posX);
		this.shape.setLayoutY(posY);
	}

	public void step(double timeElapsed) {

		//updates position of weak enemy object
		posX += velX * timeElapsed;
		posY += velY * timeElapsed;
		
		//animates position of object
		this.shape.setLayoutX(posX);
		this.shape.setLayoutY(posY);
	}

	public Shape getShape() {
		return this.shape;
	}

	// Returns bounds of an object in an array (x1,x2,y1,y2)
	public double[] getBounds() {
		double[] bounds = { posX - width / 2, posX + width / 2, posY - height / 2, posY + height / 2 };
		return bounds;
	}

	public int getType(){
		return this.type;
	}
}
