import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Abstract class that defines the variables and methods of any game object
 * Depends on javafx tools possibly.
 * This is needed for all game objects.
 * Should be fine unless someone intentially tries to carve my code. Other wise I would be surprised
 * if there is an issue here.
 * 
 * @author theodorefranceschi
 *
 */
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

    /**
     * Initializes game object at parameter positions
     * 
     * @param posX: X position where game object is placed
     * @param posY: Y position where game object is placed
     */
    public GameObject (int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.health = 100;
    }

    /**
     * Defines what happens at each step
     * 
     * @param timeElapsed: How long each time step takes
     */
    public abstract void step (double timeElapsed);

    /**
     * Gets the bounds of the game object in a double array with format (x1,x2,y1,y2)
     * 
     * @return
     */
    public abstract double[] getBounds ();

    /**
     * Returns width of game object
     * 
     * @return
     */
    public double getWidth () {
        return width;
    }

    /**
     * sets width of game object
     * 
     * @param width: desired width of game object
     */
    public void setWidth (double width) {
        this.width = width;
    }

    /**
     * Returns height of game object
     * 
     * @return
     */
    public double getHeight () {
        return height;
    }

    /**
     * Sets the height of the game object
     * 
     * @param height: Desired height of the game object
     */
    public void setHeight (double height) {
        this.height = height;
    }

    /**
     * Gets current x position of the game object
     * 
     * @return
     */
    public double getPosX () {
        return posX;
    }

    /**
     * Sets the x position of the game object
     * 
     * @param posX
     */
    public void setPosX (double posX) {
        this.posX = posX;
    }

    /**
     * Get the y position of the game object
     * 
     * @return
     */
    public double getPosY () {
        return posY;
    }

    /**
     * Sets the y position of the game object
     * 
     * @param posY
     */
    public void setPosY (double posY) {
        this.posY = posY;
    }

    /**
     * Gets the x velocity of the game object
     * 
     * @return
     */
    public double getVelX () {
        return velX;
    }

    /**
     * Sets the x velocity of the game object
     * 
     * @param velX: Desired velocity of the game object
     */
    public void setVelX (double velX) {
        this.velX = velX;
    }

    /**
     * Gets the y velocity of the game object
     * 
     * @return
     */
    public double getVelY () {
        return velY;
    }

    /**
     * Sets the y velocity of the game object
     * 
     * @param velY
     */
    public void setVelY (double velY) {
        this.velY = velY;
    }

    /**
     * Gets the type of the game object (0:player, 1:asteroid, 2:Lazer, 3:Boss
     * 
     * @return
     */
    protected int getType () {
        return type;
    }

    /**
     * Sets the type of the game object
     * 
     * @param type
     */
    protected void setType (int type) {
        this.type = type;
    }

    /**
     * Gets the health of the game object
     * 
     * @return
     */
    public double getHealth () {
        return health;
    }

    /**
     * Sets the health of the game object
     * 
     * @param health
     */
    protected void setHealth (double health) {
        this.health = health;
    }

    /**
     * Gets the image of the game object
     * 
     * @return
     */
    protected ImageView getImage () {
        return this.imageView;
    }

}
