import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Builds the end screen scene
 * @author theodorefranceschi
 *
 */
public class EndScreen {
	private Text t = new Text();
	private int height;
	private int width;
	private Scene myScene;
	private ImageView backGroundImage;
	private Group group;
	private int score;
	private int WIN_LOSS_THRESHOLD = 5000;
	private final static Image BACKGROUND_PICTURE = new Image("Space.jpg");
	
	/**
	 * Constructs end screen scene with set width, height, and score
	 * @param width: width of end screen
	 * @param height: height of end screen
	 * @param score: displays score at end game
	 * @return
	 */
	public Scene init(int width, int height, int score){
		this.height = height;
		this.width = width;
		this.group = new Group();
		this.score = score;
		this.backGroundImage = new ImageView(BACKGROUND_PICTURE); 
		backGroundImage.setFitHeight(600);
		backGroundImage.setFitWidth(800);
		
		setMessage();

		group.getChildren().add(backGroundImage);
		group.getChildren().add(t);
		
		this.myScene = new Scene(group,width,height);	
		return this.myScene;
	}
	
	/**
	 * Sets the end screen message
	 */
	private void setMessage(){
		String message = new String();
		if(score > WIN_LOSS_THRESHOLD){
			message = "Good Job!!!";
		}
		else{
			message = "You Lost!!!";
		}
		t.setText(message);
		t.setFont(Font.font ("Verdana", 50));
		t.setFill(Color.RED);
		t.setLayoutX(0.0+width/2-150);
		t.setLayoutY(0.0+height/3);
	}
}
