 import javafx.scene.paint.Color;
 import javafx.scene.text.*;
 
public class Score {
	private Text scoreShown;
	private int score;
	int posX;
	int posY;
	public Score(int score,int posX, int posY){
		this.score = score;
		this.scoreShown = new Text();
		this.posX = posX;
		this.posY = posY;
		this.scoreShown.setLayoutX(posX);
		this.scoreShown.setLayoutY(posY);
		this.scoreShown.setFill(Color.BLUE);
		this.scoreShown.setFont(Font.font("Verdana", 50));
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void setScore(int score){
		this.score = score;
		String scoreAsString = ""+score;
		this.scoreShown.setText(scoreAsString);
	}
	
	public Text getText(){
		return this.scoreShown;
	}
	
}
