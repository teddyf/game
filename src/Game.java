import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
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
	protected double playerReflectSpeed = 1.5;
	protected double enemyReflectSpeed = 1;
	protected Score score;

	public Scene init(int width, int height) {

		// Sets field dimensions
		this.width = width;
		this.height = height;

		// Sets up wallpaper of game
		ImageView backgrndView = new ImageView();
		backgrndView.setImage(BACKGROUND_PICTURE);
		backgrndView.setFitHeight(600);
		backgrndView.setFitWidth(800);
		gameGraphics.getChildren().add(backgrndView);

		// Builds player and places on board
		player = new Player(width/2, height/2);
		myScene = new Scene(this.gameGraphics, width, height, Color.BLACK);
		playerImage = player.getImage();
		gameGraphics.getChildren().add(playerImage);

		// Sets up health bar
		this.healthBar = new HealthBar(20, width);
		gameGraphics.getChildren().add(healthBar.getShape());

		score = new Score(0,width-150,height-50);
		gameGraphics.getChildren().add(score.getText());
		
		
		// Handles Keyboard control
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return myScene;
	}

	// Defines what occurs during each time stamp
	public void step(double timeElapsed) {
		
		score.setScore(score.getScore()+1);
		System.out.println(score.getScore());
		//Adds wave of enemies to bored
		if (gameObjects.size() < 50) {
			//WeakEnemy a = new WeakEnemy(0,0);
			WeakEnemy a = new WeakEnemy((int) (Math.random() * 500), (int) (Math.random() * 500));
			if(collision(a.getBounds(), player.getBounds()) == false){
				a.setVelX(Math.random() * 100);
				a.setVelY(Math.random() * 100);
				gameObjects.add(a);
				gameGraphics.getChildren().add(a.getShape());
			}
			
		}
		
		double[] playerBounds = player.getBounds();
		
		double healthBefore = player.getHealth();
		for (GameObject g : gameObjects) {
			inBounds(g, 1);
			double[] enemyBounds = g.getBounds();
			if(collision(g.getBounds(),player.getBounds())==true){
				player.setHealth(player.getHealth()-2);
			}
			g.step(timeElapsed);
			//System.out.println(g.getBounds()[0] + ", " + g.getBounds()[1] + ", " + g.getBounds()[2] + ", " + g.getBounds()[3]);
		}
		inBounds(player, 1.5);
		
		//System.out.println(playerBounds[0] + " to " + playerBounds[1] + ", " + playerBounds[2] + " to " + playerBounds[3]);
		player.step(timeElapsed);
		//player.setHealth(player.getHealth() - 0.001);
		healthBar.step((healthBefore - player.getHealth()) / 100);
		//System.out.println(player.getHealth());
	}

	// Handles object wall collisions
	public void inBounds(GameObject g, double reflectionSpeed) {
		if (g.getPosX() > width || g.getPosX() < 0) {
			g.setVelX(g.getVelX() * -1 * reflectionSpeed);
		}

		if (g.getPosY() > height || g.getPosY() < 0) {
			g.setVelY(g.getVelY() * -1 * reflectionSpeed);
		}

	}

	public void handleKeyInput(KeyCode event) {

		switch (event) {
		case D:
			// System.out.println("right");
			player.setVelX(player.getVelX() + 70);
			break;
		case A:
			// System.out.println("LEft");
			player.setVelX(player.getVelX() - 70);
			break;
		case W:
			// System.out.println("up");
			player.setVelY(player.getVelY() - 70);
			break;
		case S:
			// System.out.println("down");
			player.setVelY(player.getVelY() + 70);
			break;
		default:
			break;
		}

	}
	
	//Edits speed of player when reflected off of a wall (cheat code)
	public void setPlayerReflectSpeed(double speed){
		this.playerReflectSpeed = speed;
	}
	
	//Edits speed of player when reflected off of a wall (cheat code)
	public void setEnemyReflectSpeed(double speed){
		this.enemyReflectSpeed = speed;
	}
	
	//Detects any collisions between game objects
	public boolean collision(double[] objBounds1, double[] objBounds2){
		if(objBounds1[0] > objBounds2[0] && objBounds1[0] < objBounds2[1]){
			if(objBounds1[2] > objBounds2[2] && objBounds1[2] < objBounds2[3]){
				System.out.println("Collide!!!");
				return true;
			}
			return false;
		}
		else if(objBounds1[1]<objBounds2[1] && objBounds1[1]>objBounds2[0]){
			if(objBounds1[3]<objBounds2[3] && objBounds1[3]>objBounds2[2]){
				return true;
			}
			return false;
		}
		return false;
	}
	

}