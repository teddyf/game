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
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.Event;
import java.util.*;
import javafx.scene.shape.*;

public class Menu {
	Text t = new Text();
	int height;
	int width;
	private Scene myScene;
	private ImageView backGroundImage;
	private Group group;
	ImageView backgrndImage;
	Button start;
	
	volatile boolean exitMenu;
	
	boolean goToGame;
	
	public final static Image BACKGROUND_PICTURE = new Image("Space.jpg");
	
	public Scene init(int width, int height){
		this.height = height;
		this.width = width;
		this.group = new Group();
		this.backGroundImage = new ImageView(BACKGROUND_PICTURE); 
		backGroundImage.setFitHeight(600);
		backGroundImage.setFitWidth(800);
		
		t.setText("Galasteroids");
		t.setFont(Font.font ("Verdana", 50));
		t.setFill(Color.BLUE);
		t.setLayoutX(width/2-150);
		t.setLayoutY(height/3);
		
		this.start = new Button("start");
		start.setLayoutX(this.width/2-25);
		start.setLayoutY(this.height/2 + 100);
		group.getChildren().add(backGroundImage);
		group.getChildren().add(start);
		group.getChildren().add(t);
		
		this.myScene = new Scene(group,width,height);
		
		return this.myScene;
	}
	

}
