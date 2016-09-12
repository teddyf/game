import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import java.util.*;

/**
 * Game object builds the game and defines the leveling and mechanics
 * Depends on all game objects 
 * Assumes user doesn't tinker with files, and if he/she tinkers follows basic javafx rules.
 * @author theodorefranceschi
 *
 */
public class Game {
	private int width;
	private int height;
	private final static Image BACKGROUND_PICTURE = new Image("Space.jpg");
	private Scene myScene;
	private ImageView backgrndView = new ImageView();

	private Player player;
	private BossEnemy drm1;
	private ImageView playerImage;
	private Group gameGraphics = new Group();
	private HealthBar healthBar;
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	protected double playerReflectSpeed = 1;
	protected double enemyReflectSpeed = 1;
	protected Score score;
	protected int count = 0;
	protected int gameStatus = 0;
	protected int scoreIncrement = 1;
	protected Button goToEndScreen = new Button("Game Over");
	protected double blasterSpeed = -400;
	protected int damageBy = 2;

	private int BOSS_VERT_SPAWN = 250;
	private int BOSS_HORIZ_SPEED = 50;
	private int BOSS_BONUS = 10000;
	private int ADVANCING_SCORE = 1000;

	private int SCORE_WIDTH_SHIFT = 200;
	private int SCORE_HEIGHT_SHIFT = 50;

	private int justDied = 0;

	/**
	 * Initializes Scene in the game
	 * 
	 * @param width:
	 *            width of scene
	 * @param height:
	 *            height of scene
	 * @return :Returns scene to be called in main
	 */
	public Scene init(int width, int height) {

		this.width = width;
		this.height = height;

		backgrndView.setImage(BACKGROUND_PICTURE);
		backgrndView.setFitHeight(600);
		backgrndView.setFitWidth(800);
		gameGraphics.getChildren().add(backgrndView);

		player = new Player(width / 2, height / 2);
		myScene = new Scene(this.gameGraphics, width, height, Color.BLACK);
		playerImage = player.getImage();
		gameGraphics.getChildren().add(playerImage);

		hud();

		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return myScene;
	}

	/**
	 * Defines what occurs during each time step of the game
	 * 
	 * @param timeElapsed:
	 *            time between steps
	 */
	public void step(double timeElapsed) {
		double currentHealth = player.getHealth();
		if (isAlive() == false & count == 0) {
			gameGraphics.getChildren().add(goToEndScreen);
			setScoreIncrement(0);
			count++;
		}
		if (score.getScore() == ADVANCING_SCORE) {
			drm1 = new BossEnemy(width / 2, BOSS_VERT_SPAWN);
			drm1.setVelX(BOSS_HORIZ_SPEED);
			gameObjects.add(drm1);
			gameGraphics.getChildren().add(drm1.getImage());

			asteroidRemovalAndCollision(timeElapsed);
			this.gameStatus = 1;
		}
		score.setScore(score.getScore() + scoreIncrement);
		
		asteroidSpawn();
		bossLevel(timeElapsed);
		asteroidRemovalAndCollision(timeElapsed);
		
		inBounds(player, 1);
		player.step(timeElapsed);
		healthBar.step((currentHealth - player.getHealth()) / 100);
	}


	private boolean inBounds(GameObject g, double reflectionSpeed) {
		if (g.getPosX() > width || g.getPosX() < 0) {
			g.setVelX(g.getVelX() * -1 * reflectionSpeed);
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
	 * 
	 * @param event
	 */
	public void handleKeyInput(KeyCode event) {

		switch (event) {
		case D:
			player.setVelX(player.getVelX() + 100);
			break;
		case A:
			player.setVelX(player.getVelX() - 100);
			break;
		case W:
			player.setVelY(player.getVelY() - 100);
			break;
		case S:
			player.setVelY(player.getVelY() + 100);
			break;
		case J:
			player.setXResistance(.9);
			player.setYResistance(.9);
			break;
		case SPACE:
			shoot(player, 1);
			break;
		case K:
			player.setXResistance(1);
			player.setYResistance(1);
			break;
			
		case O:
			skipToBoss();
			break;
			
		case L:
			setDamageBy(0);
			break;
			
		default:
			break;
		}

	}

	/**
	 * Sets up the reflect speed when a player encounters a wall
	 * 
	 * @param speed
	 */
	private void setPlayerReflectSpeed(double speed) {
		this.playerReflectSpeed = speed;
	}

	/**
	 * Edits enemy reflect speed
	 * 
	 * @param speed
	 */
	private void setEnemyReflectSpeed(double speed) {
		this.enemyReflectSpeed = speed;
	}

	/**
	 * Checks if the game objects are intersecting or not
	 * 
	 * @param objBounds1
	 *            (defines hit box of object 1 in the format: x1,x2,y1,y2)
	 * @param objBounds2
	 *            (defines hit box of object 2)
	 * @return
	 */
	public boolean collision(double[] objBounds1, double[] objBounds2) {
		if (objBounds1[0] > objBounds2[0] && objBounds1[0] < objBounds2[1]) {
			if (objBounds1[2] > objBounds2[2] && objBounds1[2] < objBounds2[3]) {
				return true;
			}
			if (objBounds1[2] < objBounds2[0] && objBounds1[2] > objBounds2[3]) {
				return true;
			}
			return false;
		} else if (objBounds1[1] < objBounds2[1] && objBounds1[1] > objBounds2[0]) {
			if (objBounds1[3] < objBounds2[3] && objBounds1[3] > objBounds2[2]) {
				return true;
			}
			if (objBounds1[2] > objBounds2[3] && objBounds1[2] < objBounds2[3]) {
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * Checks to see if player has health
	 * 
	 * @return True if player ha health, False if not
	 */
	public boolean isAlive() {
		if (this.player.getHealth() > 0) {
			return true;
		} else
			return false;
	}

	private void setScoreIncrement(int a) {
		this.scoreIncrement = a;
	}


	private void asteroidRemovalAndCollision(double timeElapsed) {
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject g = gameObjects.get(i);
			if (inBounds(g, 1) == false && (g.getType() == 1 || g.getType() == 2)) {
				remove(g, i);
			}
			damageGameObject(g, player, damageBy);
			g.step(timeElapsed);
		}

	}

	private void shoot(GameObject g, int direction) {
		Lazor lazor = new Lazor((int) (g.getPosX()), (int) (g.getPosY()));
		lazor.setVelY(direction * blasterSpeed);
		gameGraphics.getChildren().add(lazor.getImage());
		gameObjects.add(lazor);
	}

	/**
	 * Sets up the hud of the game
	 */
	public void hud() {
		this.healthBar = new HealthBar(20, width);
		gameGraphics.getChildren().add(healthBar.getShape());

		score = new Score(0, width - SCORE_WIDTH_SHIFT, height - SCORE_HEIGHT_SHIFT);
		gameGraphics.getChildren().add(score.getText());
	}

	private void remove(GameObject g, int index) {
		gameGraphics.getChildren().remove(g.getImage());
		gameObjects.remove(index);
	}

	private void damageGameObject(GameObject a, GameObject b, double amount) {
		if (collision(a.getBounds(), b.getBounds()) == true) {
			if (a.getType() == 1 || a.getType() == 2) {
				b.setHealth(b.getHealth() - amount);
			}
		}
	}

	private void asteroidSpawn() {
		if (gameObjects.size() < 30 && gameStatus == 0) {
			WeakEnemy a = new WeakEnemy((int) (Math.random() * 800), 0);
			if (collision(a.getBounds(), player.getBounds()) == false) {
				a.setVelX(Math.random() * 20);
				a.setVelY(Math.random() * 400 + 200);
				gameObjects.add(a);
				gameGraphics.getChildren().add(a.getImage());
			}
		}
	}

	private void skipToBoss() {
		drm1 = new BossEnemy(width / 2, BOSS_VERT_SPAWN);
		drm1.setVelX(BOSS_HORIZ_SPEED);
		
		gameObjects.add(drm1);
		gameGraphics.getChildren().add(drm1.getImage());
		this.gameStatus = 1;
	}
	
	private void bossLevel(double timeElapsed){
		if (gameStatus == 1) {
			inBounds(drm1, enemyReflectSpeed);
			for (int i = 0; i < gameObjects.size(); i++) {
				GameObject g = gameObjects.get(i);
				g.step(timeElapsed);
				inBounds(g, enemyReflectSpeed);
				damageGameObject(g, drm1, .1);
				if (drm1.getHealth() <= 0) {
					score.setScore(score.getScore() + BOSS_BONUS * this.scoreIncrement);
					gameGraphics.getChildren().remove(drm1.getImage());
					setScoreIncrement(0);
					if (justDied == 0) {
						justDied++;
						gameGraphics.getChildren().add(goToEndScreen);
					}
				}
			}
			if (score.getScore() % 30 == 0 && drm1.isAlive()) {
				shoot(drm1, -1);
			}
		}
	}
	
	private void setDamageBy(int a){
		this.damageBy = a;
	}
}