import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Part of Coding Master Piece!!!
 * 
 * Player object defines variables, methods, and graphics of the player
 * Shouldn't fail unless you don't initialize it wrong (as in inputting a string instead of an int
 * or giving no values)
 * Depends on GameObject (extends it)
 * 
 * @author theodorefranceschi
 *
 */
public class Player extends GameObject {

    protected double xResistance;
    protected double yResistance;

    /**
     * Constructor for player that sets intial x and y position
     * 
     * @param posX
     * @param posY
     */
    public Player (int posX, int posY) {
        super(posX, posY);
        this.type = 0;
        this.image = new Image("playerSprite.png");
        this.imageView = new ImageView(image);
        this.imageView.setFitHeight(20);
        this.imageView.setFitWidth(20);
        this.width = 20;
        this.height = 20;
        this.imageView.setX(posX - width / 2);
        this.imageView.setY(posY - height / 2);
        this.xResistance = 1;
        this.yResistance = 1;
    }

    /**
     * Updates player position, speed, and rendering for each time step
     */
    public void step (double timeElapsed) {
        posX = posX + velX * timeElapsed;
        posY = posY + velY * timeElapsed;
        this.imageView.setX(posX - width / 2);
        this.imageView.setY(posY - height / 2);
        setVelX(velX * xResistance);
        setVelY(velY * yResistance);
    }

    /**
     * Returns the bounds of the player
     */
    public double[] getBounds () {
        double[] bounds =
                { posX - width / 2, posX + width / 2, posY - height / 2, posY + height / 2 };
        return bounds;
    }

    /**
     * Returns the type of the player
     */
    public int getType () {
        return this.type;
    }

    /**
     * Sets the x resistance of ship in space
     * 
     * @param a: resistance value between 0 and 1
     */
    public void setXResistance (double a) {
        this.xResistance = a;
    }

    /**
     * Sets the y resistance of the ship in space
     * 
     * @param a: resistance between 0 and 1
     */
    public void setYResistance (double a) {
        this.yResistance = a;
    }
}
