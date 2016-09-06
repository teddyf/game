import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.*;

public class BossEnemy extends GameObject {

	public BossEnemy(int posX, int posY) {
		super(posX, posY);
		this.type = 3;
	}

	public void step(double timeElapsed) {

		//updates position of weak enemy object
		posX += velX * timeElapsed;
		posY += velY * timeElapsed;
		this.image = new Image("Boss.png");
		this.imageView = new ImageView(image);
		this.imageView.setFitHeight(20);
		//this.imageView.preserveRatioProperty();
		this.width = 100;
		this.height = 100;
		this.imageView.setX(posX-width/2);
		this.imageView.setY(posY-height/2);
		
		//animates position of object
		this.imageView.setX(posX-width/2);
		this.imageView.setY(posY-height/2);
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
