import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Defines the lazor object which are projectiles the boss enemy and player shoot
 * Shouldn't fail unless you don't initialize it wrong (as in inputting a string instead of an int or giving no values)
 * Depends on GameObject (extends it)
 * @author theodorefranceschi
 *
 */
public class Lazor extends GameObject{

	protected double xResistance;
	protected double yResistance;
	
	/**
	 * Constructor of Laser object aka Lazor
	 * @param posX: initial x position of lazor
	 * @param posY: initial y position of lazor
	 */
	public Lazor(int posX, int posY){
		super(posX, posY);
		this.type = 2;
		
		this.image = new Image("upLazor.png");
		this.imageView = new ImageView(image);
		this.imageView.setFitHeight(20);
		
		this.width = 10;
		this.height = 100;
		
		this.imageView.setX(posX-width/2);
		this.imageView.setY(posY-height/2);
	}
	
	/**
	 * Determines the status of the lazor at each time step
	 */
	public void step(double timeElapsed){
		posX = posX + velX*timeElapsed;
		posY = posY + velY*timeElapsed;
		this.imageView.setX(posX-width/2);
		this.imageView.setY(posY-height/2);
		}
	
	/**
	 * Returns the bounds of the lazor
	 */
	public double[] getBounds(){
		double[] bounds = {posX-width/2,posX+width/2,posY-height/2,posY+height/2};
		return bounds;
	}
	
	/**
	 * Returns type of the lazor(2)
	 */
	public int getType(){
		return this.type;
	}
	
}