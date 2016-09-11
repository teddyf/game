import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Boss object which is the main enemy in the final stage of the game
 * @author theodorefranceschi
 *
 */
public class BossEnemy extends GameObject {

	/**
	 * Constructor of boss and is set at initial position
	 * @param posX: inital x position
	 * @param posY: intial y position
	 */
	public BossEnemy(int posX, int posY) {
		super(posX, posY);
		this.type = 3;
		this.image = new Image("Boss.png");
		this.imageView = new ImageView(image);
		this.imageView.setFitHeight(300);
		this.imageView.setFitWidth(300);
		this.imageView.setX(posX-width/2);
		this.imageView.setY(posY-height/2);
		this.width = 300;
		this.height = 300;
		
	}

	/**
	 * Updates boss position and rendering at a givent time step
	 */
	public void step(double timeElapsed) {
		posX += velX * timeElapsed;
		posY += velY * timeElapsed;

		this.imageView.setX(posX-width/2);
		this.imageView.setY(posY-height/2);
	}

	/**
	 * Gets the bounds of the boss object
	 */
	public double[] getBounds() {
		double[] bounds = { posX - width / 2, posX + width / 2, posY - height / 2, posY + height / 2 };
		return bounds;
	}

	/**
	 * Gets the type of boss(3)
	 */
	public int getType(){
		return this.type;
	}
	
	/**
	 * Checks if boss object is alive (more health than 0)
	 * @return: true if alive, false if not
	 */
	public boolean isAlive(){
		if(getHealth()>0){
			return true;
		}
		else{
			return false;
		}
	}
}
