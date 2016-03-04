package minions;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LastHitTrainer extends Application implements Constants {
	ArrayList<Minion> minions;
	int score;

	public void start(Stage primary){
		Pane pane = new Pane();
		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		ProjectileManager projectileMan = new ProjectileManager();
		scene.setFill(Color.DARKBLUE);
		minions = new ArrayList<>();
		Minion.setArrayList(minions);
		score = 0;
		
		for(int i = 0; i < 3; i++){
			minions.add(new Minion(pane,
					i * (2 * MINION_CASTER_SIZE + PADDING_H) + MINION_CASTER_SIZE + PADDING_LEFT,
					PADDING_TOP + MINION_CASTER_SIZE));
			minions.add(new Minion(pane,
					i * (2 * MINION_CASTER_SIZE + PADDING_H) + MINION_CASTER_SIZE + PADDING_LEFT,
					PADDING_TOP + 5*MINION_CASTER_SIZE + 2*PADDING_V));
		}
		minions.add(new Minion(pane, WIDTH / 2, HEIGHT / 2));
		
		scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent e){
				double x = e.getSceneX();
				double y = e.getSceneY();
				
				int minionNo = getMinion(x, y);
				if(minionNo >= 0){
					hit(minions.get(minionNo));
					System.out.println(score);
				}
			}
		});

		EventHandler<ActionEvent> eHandler = e -> {
			for(Minion minion : minions){
				minion.tick(pane);
			}
			projectileMan.tick();
		};
		
	    Timeline animation = new Timeline(new KeyFrame(Duration.millis(100), eHandler));
	    animation.setCycleCount(Timeline.INDEFINITE);
	    animation.play();

	    primary.setTitle("Luigi - The Game!");
		primary.setScene(scene);
		primary.show();
	}

    public static void main(String[] args){
		Application.launch(args);
	}
    
    private int getMinion(double x, double y){
    	for(int i = 0; i < minions.size(); i++){
    		if(minions.get(i).isInside(x, y))
    				return i;
    	}
    	return -1;
    }
    
    private void hit(Minion minion){
    	if(minion.hit(PLAYER_DAMAGE)){
    		score++;
    	}
    }

}
