import javafx.animation.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.util.*;
import javafx.application.*;


/**
 * The runner class. To start the game run this class
 * 
 * @author theodorefranceschi
 *         Depends on game and javafx
 *         If not tinkered or if tinkering is done within rules of javafx then you should be fine
 */
public class Main extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int DELAY_IN_MILLISECONDS = 1000 / FRAMES_PER_SECOND;
    private static final double DELAY_IN_SECONDS = 1.0 / FRAMES_PER_SECOND;
    private Game myGame;
    private int gameStatus;
    private Scene scene;

    @Override
    /**
     * Starts the game and goes to the main menu. Once start button is pressed game proceeds to
     * first stage
     */
    public void start (Stage s) {

        this.gameStatus = 0;
        myGame = new Game();
        Menu menu = new Menu();
        Scene scene = menu.init(WIDTH, HEIGHT);

        s.setTitle("Game");
        s.setScene(scene);
        s.show();

        menu.start.setOnAction( (event) -> {
            playGame(s);
        });

    }

    /**
     * Sets status of the game
     * 
     * @param status: status of the game
     */
    public void setGameStatus (int status) {
        this.gameStatus = status;
    }

    /**
     * Main method
     * 
     * @param args
     */
    public static void main (String[] args) {
        launch(args);
    }

    /**
     * Starts the first stage of the game
     * 
     * @param s
     */
    public void playGame (Stage s) {
        scene = myGame.init(WIDTH, HEIGHT);
        s.setScene(scene);
        s.show();

        if (myGame.goToEndScreen.equals(null) == false) {
            myGame.goToEndScreen.setOnAction( (event) -> {
                endGame(s);
            });
        }

        KeyFrame frame = new KeyFrame(Duration.millis(DELAY_IN_MILLISECONDS),
                                      e -> myGame.step(DELAY_IN_SECONDS));

        Timeline animate = new Timeline();

        animate.setCycleCount(Timeline.INDEFINITE);
        animate.getKeyFrames().add(frame);
        animate.play();
    }

    /**
     * Moves game to end screen scene
     * 
     * @param s
     */
    public void endGame (Stage s) {
        EndScreen end = new EndScreen();
        scene = end.init(WIDTH, HEIGHT, myGame.score.getScore());
        s.setScene(scene);
        s.show();
    }

}
