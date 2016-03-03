package experiments;

import java.util.GregorianCalendar;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
//import javafx.scene.effect.BlendMode;
//import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
//import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyFX extends Application {
	@Override
	public void start(Stage primary) throws Exception {
		//Initialization
		StackPane pane = new StackPane();
		for(int i = 0; i < 6; i++){
			Circle circle = new Circle();
			circle.setRadius(50);
			circle.setStroke(Color.rgb(255, 50*i, 0));
			//circle.setBlendMode(BlendMode.ADD);
			pane.getChildren().add(circle);
			pane.setBackground(new Background(new BackgroundFill(Color.BLACK,null, null)));
		}
		
		//EventHandler for change of keyframe
		EventHandler<ActionEvent> eHandler = e -> {
			GregorianCalendar date = new GregorianCalendar();
			double rad = ((double)(date.getTimeInMillis())/1000.0 % (12 * Math.PI / 2));
			for(int i = 0; i < pane.getChildren().size(); i++){
				pane.getChildren().get(5-i).setTranslateY(Math.sin(rad*(i+1))*100);
				pane.getChildren().get(5-i).setTranslateX(Math.cos(rad*(i+1))*100);
			}
		};

		//Timeline
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(40), eHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		
		//Staging the scene
		Scene paneScene = new Scene(pane, 400, 400);
		primary.setTitle("Java FX Application");
		primary.setScene(paneScene);
		primary.show();
	}
	
	public static void main(String[] args){
		Application.launch(args);
	}

}
