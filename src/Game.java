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
		//System.out.println(score.getScore());
		//Adds wave of enemies to bored
		
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
		
		if (gameStatus == 1){
			//System.out.println("here");
			inBounds(drm1,enemyReflectSpeed);
			for(int i = 0; i < gameObjects.size();i++){
				GameObject g = gameObjects.get(i);
				g.step(timeElapsed);
				inBounds(g, enemyReflectSpeed);
				if(g.getType()!=3){
					//System.out.println("fire" + g.getBounds()[0] + ", " + g.getBounds()[1] + ": " + g.getBounds()[2] + ", " + g.getBounds()[3]);
				}
				//System.out.println("alien" + drm1.getBounds()[0]+ ", " + drm1.getBounds()[1] + ": " + drm1.getBounds()[2] + ", " + drm1.getBounds()[3]);
				if(collision(g.getBounds(),drm1.getBounds()) && g.getType()==2){
					System.out.println(drm1.getHealth());
					drm1.setHealth(drm1.getHealth()-.1);
				}
				
				if(drm1.getHealth() <= 0){
					gameGraphics.getChildren().remove(drm1.getImage());
				}
				
				/*
				if(gameObjects.get(i).getType()==3){
					if(gameObjects.get(i).getHealth() <= 0){
						gameGraphics.getChildren().remove(gameObjects.get(i));
						gameObjects.remove(i);
						System.out.println("death to DRM!!!");
					}
					
					if(collision(player.getBounds(),gameObjects.get(i).getBounds())){
						gameObjects.get(i).setHealth(gameObjects.get(i).getHealth()-10);
						System.out.println("Take that drm!!!");
					}
					if(score.getScore()%50 == 0){
						System.out.println("shots fired");
						shoot(gameObjects.get(i),-1);
					}
				}
				*/
				
			}
			if(score.getScore()%30==0){
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
	public boolean isAlive(){
		if(this.player.getHealth() > 0){
			return true;
		}
		else return false;
	}
	
	public void setScoreIncrement(int a){
		this.scoreIncrement = a;
	}
	
	public void asteroidRemovalAndCollision(double timeElapsed){
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject g = gameObjects.get(i);
			if (inBounds(g, 1) == false && (g.getType()==1 || g.getType()==2)){
				gameGraphics.getChildren().remove(g.getImage());
				gameObjects.remove(i);
				//System.out.println("Removed Object");
			}
			if(collision(g.getBounds(),player.getBounds())==true){
				if(g.getType() == 1){
					player.setHealth(player.getHealth()-2);
				}
			}
			g.step(timeElapsed);
			//System.out.println(g.getBounds()[0] + ", " + g.getBounds()[1] + ", " + g.getBounds()[2] + ", " + g.getBounds()[3]);
		}

	}
	
	
	public void shoot(GameObject g, int direction){
		Lazor lazor = new Lazor((int)(g.getPosX()),(int)(g.getPosY())+40);
		lazor.setVelY(direction*blasterSpeed);
		gameGraphics.getChildren().add(lazor.getImage());
		gameObjects.add(lazor);
	}
	
	public void hud(){
		// Sets up health bar
		this.healthBar = new HealthBar(20, width);
		gameGraphics.getChildren().add(healthBar.getShape());

		// Puts score on screen
		score = new Score(0,width-150,height-50);
		gameGraphics.getChildren().add(score.getText());
	}
	

}