package skillshots;
import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Luigi extends Application {
	public void start(Stage primary){
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 800, 600);
		MapGrid map = new MapGrid(pane);
		scene.setFill(Color.DARKBLUE);
	    
	    scene.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent keyEvent){
				switch(keyEvent.getCharacter().charAt(0)){
				case 'w':
					break;
				case 'a':
					break;
				case 's':
					break;
				case 'd':
					break;
				}
			}
	    });

		EventHandler<ActionEvent> eHandler = e -> {
			//System.out.println("blablabla");
		};
		
	    Timeline animation = new Timeline(new KeyFrame(Duration.millis(40), eHandler));
	    animation.setCycleCount(Timeline.INDEFINITE);
	    animation.play();

	    primary.setTitle("Luigi - The Game!");
		primary.setScene(scene);
		primary.show();
	}

    public static void main(String[] args){
		Application.launch(args);
	}

}
