 import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import javafx.scene.shape.Shape;
public abstract class GameObject {
	protected double posX;
	protected double posY;
	protected double velX;
	protected double velY;
	protected int type;
	protected double health;
	protected double width;
	protected double height;
	protected Image image;
	protected ImageView imageView;
	protected Shape shape;
	
	//Creates game object which sets initial position and health
	public GameObject(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
		this.health = 100;
	}
	
	//Method that defines action in each time commitment
	public abstract void step(double timeElapsed);
	public abstract double[] getBounds();
	
	
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
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
	public double getVelX() {
		return velX;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public double getVelY() {
		return velY;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getHealth() {
		return health;
	}
	public void setHealth(double health) {
		this.health = health;
	}
	public ImageView getImage(){
		return this.imageView;
	}
	
}
