import java.awt.Color;

import javafx.animation.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.stage.*;
import javafx.util.*;
import javafx.application.*;
import javafx.scene.control.*;

public class Main extends Application{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int FRAMES_PER_SECOND = 60;
	private static final int DELAY_IN_MILLISECONDS = 1000/FRAMES_PER_SECOND;
	private static final double DELAY_IN_SECONDS = 1.0/FRAMES_PER_SECOND;
	
	private int gameStatus;
	
	private Game myGame;
	Scene scene;
	
	@Override
	public void start(Stage s){
		
		this.gameStatus = 0;
		myGame = new Game();
		Menu menu = new Menu();
		Scene scene = menu.init(WIDTH, HEIGHT);
		s.setTitle("Game");
		s.setScene(scene);
		s.show();
		
		//Handles start button on menu screen
		menu.start.setOnAction((event) -> {
			playGame(s);
		});
			
	}
	
	//Checks status of game (menu, playing, end)
	public void setGameStatus(int status){
		this.gameStatus = status;
	}
	
	public static void main(String[] args){
		launch(args);
	}
	
	//Handles scene animation of actual gameplay
	public void playGame(Stage s){
		scene = myGame.init(WIDTH, HEIGHT);
		s.setScene(scene);
		s.show();
		
		if(myGame.goToEndScreen.equals(null)==false){
			myGame.goToEndScreen.setOnAction((event) -> {
				endGame(s);
			});
		}
		
		KeyFrame frame = new KeyFrame(Duration.millis(DELAY_IN_MILLISECONDS),
				e -> myGame.step(DELAY_IN_SECONDS));
		Timeline animate = new Timeline();
		animate.setCycleCount(Timeline.INDEFINITE);
		animate.getKeyFrames().add(frame);
		animate.play();
		/*
		while(myGame.isAlive()==true){
			System.out.println("It is alive!!!");
		}
		*/
	}
	
	//Handles end scene animation
	public void endGame(Stage s){
		EndScreen end = new EndScreen();
		scene = end.init(WIDTH,HEIGHT,myGame.score.getScore());
		s.setScene(scene);
		s.show();
	}
	
}
