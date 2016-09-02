import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EndScreen {
	Text t = new Text();
	int height;
	int width;
	private Scene myScene;
	private ImageView backGroundImage;
	private Group group;
	ImageView backgrndImage;
	Button tryAgain;
	int score;
	
	volatile boolean exitMenu;
	
	boolean goToGame;
	
	public final static Image BACKGROUND_PICTURE = new Image("Space.jpg");
	
	public Scene init(int width, int height, int score){
		this.height = height;
		this.width = width;
		this.group = new Group();
		this.score = score;
		this.backGroundImage = new ImageView(BACKGROUND_PICTURE); 
		backGroundImage.setFitHeight(600);
		backGroundImage.setFitWidth(800);
		
		//Sets end screen title
		t.setText("You Died =(");
		t.setFont(Font.font ("Verdana", 50));
		t.setFill(Color.RED);
		t.setLayoutX(width/2-150);
		t.setLayoutY(height/3);
		
		this.tryAgain = new Button("Try Again?");
		tryAgain.setLayoutX(this.width/2-25);
		tryAgain.setLayoutY(this.height/2 + 100);
		group.getChildren().add(backGroundImage);
		group.getChildren().add(tryAgain);
		group.getChildren().add(t);
		
		this.myScene = new Scene(group,width,height);
		
		return this.myScene;
	}
	

}
