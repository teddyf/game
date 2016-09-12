import javafx.scene.paint.Color;
import javafx.scene.text.*;


/**
 * Score object displays score of player during game
 * Depends on javafx tools
 * Easiest solution is just not to touch my code. Should work unless someone chooses to screw with
 * it as in delete a semicolon or something dumb
 * 
 * @author theodorefranceschi
 *
 */
public class Score {
    private Text scoreShown;
    private int score;
    private int posX;
    private int posY;

    public Score (int score, int posX, int posY) {
        this.score = score;
        this.scoreShown = new Text();
        this.posX = posX;
        this.posY = posY;
        this.scoreShown.setLayoutX(posX);
        this.scoreShown.setLayoutY(posY);
        this.scoreShown.setFill(Color.BLUE);
        this.scoreShown.setFont(Font.font("Verdana", 50));
    }

    public int getScore () {
        return this.score;
    }

    public void setScore (int score) {
        this.score = score;
        String scoreAsString = "" + score;
        this.scoreShown.setText(scoreAsString);
    }

    public Text getText () {
        return this.scoreShown;
    }

}
