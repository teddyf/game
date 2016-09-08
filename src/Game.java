import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.util.*;
import javafx.scene.shape.*;

public class Game {
	int width;
	int height;
	private Scene myScene;
	private Player player;
	private ImageView playerImage;
	private Group gameGraphics = new Group();
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	public final static Image BACKGROUND_PICTURE = new Image("Space.jpg");
	private HealthBar healthBar;
	protected double playerReflectSpeed = 1;
	protected double enemyReflectSpeed = 1;
	protected Score score;
	int count = 0;
	int gameStatus = 0;
	int scoreIncrement = 1;
	ImageView backgrndView = new ImageView();
	Button goToEndScreen = new Button("You died!");
	protected double blasterSpeed = -400;
	BossEnemy drm1;

	/**
	 * Initializes Scene in the game
	 * @param width: width of scene
	 * @param height: height of scene
	 * @return :Returns scene to be called in main 
	 */
	public Scene init(int width, int height) {

		// Sets field dimensions
		this.width = width;
		this.height = height;

		// Sets up wallpaper of game
		backgrndView.setImage(BACKGROUND_PICTURE);
		backgrndView.setFitHeight(600);
		backgrndView.setFitWidth(800);
		gameGraphics.getChildren().add(backgrndView);

		// Builds player and places on board
		player = new Player(width/2, height/2);
		myScene = new Scene(this.gameGraphics, width, height, Color.BLACK);
		playerImage = player.getImage();
		gameGraphics.getChildren().add(playerImage);
		
		hud();
		
		// Handles Keyboard control
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return myScene;
	}

	// Defines what occurs during each time stamp
	/**
	 * Defines what occurs during each time step of the game
	 * @param timeElapsed
	 */
	public void step(double timeElapsed) {
		
		if(isAlive()==false & count == 0){
			gameGraphics.getChildren().add(goToEndScreen);
			setScoreIncrement(0);
			count++;
		}
		
		if(score.getScore() == 500){
			drm1 = new BossEnemy(width/2,250);
			drm1.setVelX(50);
			gameObjects.add(drm1);		
			gameGraphics.getChildren().add(drm1.getImage());
			
			asteroidRemovalAndCollision(timeElapsed);
			this.gameStatus = 1;
		}
		
		score.setScore(score.getScore()+scoreIncrement);
		
		//Spawns asteroids
		asteroidSpawn();
		
		//Move on to boss fight
		if (gameStatus == 1){
			inBounds(drm1,enemyReflectSpeed);
			for(int i = 0; i < gameObjects.size();i++){
				GameObject g = gameObjects.get(i);
				g.step(timeElapsed);
				inBounds(g, enemyReflectSpeed);
				damageGameObject(g,drm1,.1);
				if(drm1.getHealth() <= 0){
					gameGraphics.getChildren().remove(drm1.getImage());
				}	
			}
			if(score.getScore()%30==0 && drm1.isAlive()){
				shoot(drm1,-1);
			}
		}
		
		
		double healthBefore = player.getHealth();
		asteroidRemovalAndCollision(timeElapsed);
		inBounds(player, 1);
		
		//System.out.println(playerBounds[0] + " to " + playerBounds[1] + ", " + playerBounds[2] + " to " + playerBounds[3]);
		player.step(timeElapsed);
		//player.setHealth(player.getHealth() - 0.001);
		healthBar.step((healthBefore - player.getHealth()) / 100);
		//System.out.println(player.getHealth());
	}

	// Handles object wall collisions
	/**
	 * Checks to see if game object g is within the bounds of the world.  If not, the speed of the object is multiplied by -1 in order to
	 * reflect it off the walls
	 * @param g (game object that is checked)
	 * @param reflectionSpeed (speed to be multiplied by when object encounters wall)
	 * @return
	 */
	public boolean inBounds(GameObject g, double reflectionSpeed) {
		if (g.getPosX() > width || g.getPosX() < 0) {
			g.setVelX(g.getVelX() * -1 * reflectionSpeed);
			//System.out.println("edge");
			return false;
		}

		if (g.getPosY() > height || g.getPosY() < 0) {
			g.setVelY(g.getVelY() * -1 * reflectionSpeed);
			return false;
		}
		
		return true;

	}

	/**
	 * Maps keys to events and includes cheat codes
	 * @param event
	 */
	public void handleKeyInput(KeyCode event) {

		switch (event) {
		case D:
			// System.out.println("right");
			player.setVelX(player.getVelX() + 100);
			break;
		case A:
			// System.out.println("LEft");
			player.setVelX(player.getVelX() - 100);
			break;
		case W:
			// System.out.println("up");
			player.setVelY(player.getVelY() - 100);
			break;
		case S:
			// System.out.println("down");
			player.setVelY(player.getVelY() + 100);
			break;
		
		case J:
			player.setXResistance(1);
			player.setYResistance(1);
			
		case SPACE:
			shoot(player,1);
		default:
			break;
		}

	}
	
	//Edits speed of player when reflected off of a wall (cheat code)
	/**
	 * Sets up the reflect speed when a player encounters a wall
	 * @param speed
	 */
	public void setPlayerReflectSpeed(double speed){
		this.playerReflectSpeed = speed;
	}
	
	//Edits speed of player when reflected off of a wall (cheat code)
	/**
	 * Edits enemy reflect speed
	 * @param speed
	 */
	public void setEnemyReflectSpeed(double speed){
		this.enemyReflectSpeed = speed;
	}
	
	/**
	 * Checks if the game objects are intersecting or not 
	 * @param objBounds1 (defines hit box of object 1 in the format: x1,x2,y1,y2)
	 * @param objBounds2 (defines hit box of object 2)
	 * @return
	 */
	public boolean collision(double[] objBounds1, double[] objBounds2){
		if(objBounds1[0] > objBounds2[0] && objBounds1[0] < objBounds2[1]){
			if(objBounds1[2] > objBounds2[2] && objBounds1[2] < objBounds2[3]){
				//System.out.println("Collide!!!");
				return true;
			}
			if(objBounds1[2] < objBounds2[0] && objBounds1[2] > objBounds2[3]){
				//System.out.println("Collide!!!");
				return true;
				
			}
			return false;
		}
		else if(objBounds1[1]<objBounds2[1] && objBounds1[1]>objBounds2[0]){
			if(objBounds1[3]<objBounds2[3] && objBounds1[3]>objBounds2[2]){
				//System.out.println("Collide!!!");
				return true;
			}
			if(objBounds1[2] > objBounds2[3] && objBounds1[2]<objBounds2[3]){
				//System.out.println("Collide!!!");
				return true;
			}
			return false;
		}
		return false;
	}
	
	//Checks if player is alive
	/**
	 * Checks to see if player has health
	 * @return True if player ha health, False if not
	 */
	public boolean isAlive(){
		if(this.player.getHealth() > 0){
			return true;
		}
		else return false;
	}
	
	/**
	 * Sets score increment in game
	 * @param a :score to be added if survived a step
	 */
	public void setScoreIncrement(int a){
		this.scoreIncrement = a;
	}
	
	/**
	 * Removes asteroids if they leave the map
	 * @param timeElapsed
	 */
	public void asteroidRemovalAndCollision(double timeElapsed){
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject g = gameObjects.get(i);
			if (inBounds(g, 1) == false && (g.getType()==1 || g.getType()==2)){
				remove(g,i);
			}
			damageGameObject(g,player,2);
			g.step(timeElapsed);
		}

	}
	
	/**
	 * Sets up projectile firing for player and enemies
	 * @param g
	 * @param direction
	 */
	public void shoot(GameObject g, int direction){
		Lazor lazor = new Lazor((int)(g.getPosX()),(int)(g.getPosY())+40);
		lazor.setVelY(direction*blasterSpeed);
		gameGraphics.getChildren().add(lazor.getImage());
		gameObjects.add(lazor);
	}
	
	/**
	 * Sets up the HUD for the games
	 */
	public void hud(){
		// Sets up health bar
		this.healthBar = new HealthBar(20, width);
		gameGraphics.getChildren().add(healthBar.getShape());

		// Puts score on screen
		score = new Score(0,width-150,height-50);
		gameGraphics.getChildren().add(score.getText());
	}
	
	/**
	 * Removes game objects from object list and stops rendering it
	 * @param g
	 * @param index
	 */
	public void remove(GameObject g, int index){
		gameGraphics.getChildren().remove(g.getImage());
		gameObjects.remove(index);
	}
	
	/**
	 * Damages object as called
	 * @param a: Gameobject possibly inflicting the object
	 * @param b: Gameobject geing damaged
	 * @param amount: Amount by which the object is damaged
	 */
	public void damageGameObject(GameObject a, GameObject b,double amount){
		if(collision(a.getBounds(),b.getBounds())==true){
			if(a.getType() == 1 || a.getType()==2){
				b.setHealth(b.getHealth()-amount);
			}
		}
	}
	
	/**
	 * Spawns asteroids, sets speeds
	 */
	public void asteroidSpawn(){
		if (gameObjects.size() < 30 && gameStatus == 0) {
			//WeakEnemy a = new WeakEnemy(0,0);
			WeakEnemy a = new WeakEnemy((int) (Math.random() * 800),0);
			if(collision(a.getBounds(), player.getBounds()) == false){
				a.setVelX(Math.random() * 20);
				a.setVelY(Math.random() * 400+200);
				gameObjects.add(a);
				gameGraphics.getChildren().add(a.getImage());
			}
		}
	}
	

}