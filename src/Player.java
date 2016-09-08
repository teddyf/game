import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Player extends GameObject{
	
	protected double xResistance;
	protected double yResistance;
	//Player constructor that sets initial position
	public Player(int posX, int posY){
		super(posX, posY);
		this.type = 0;
		this.image = new Image("playerSprite.png");
		this.imageView = new ImageView(image);
		this.imageView.setFitHeight(20);
		this.imageView.setFitWidth(20);
		this.width = 20;
		this.height = 20;
		this.imageView.setX(posX-width/2);
		this.imageView.setY(posY-height/2);
		this.xResistance = .9;
		this.yResistance = .9;
	}
	
	public void step(double timeElapsed){
		posX = posX + velX*timeElapsed;
		posY = posY + velY*timeElapsed;
		this.imageView.setX(posX-width/2);
		this.imageView.setY(posY-height/2);
		setVelX(velX*xResistance);
		setVelY(velY*yResistance);
		}
	
	public double[] getBounds(){
		double[] bounds = {posX-width/2,posX+width/2,posY-height/2,posY+height/2};
		return bounds;
	}
	
	public int getType(){
		return this.type;
	}
	
	public void setXResistance(double a){
		this.xResistance = a;
	}
	
	public void setYResistance(double a){
		this.yResistance = a;
	}
}
