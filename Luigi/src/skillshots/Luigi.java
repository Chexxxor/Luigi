package skillshots;
import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import minions.ProjectileManager;

public class Luigi extends Application implements Constants {
  public void start(Stage primary){
    Pane pane = new Pane();
    Scene scene = new Scene(pane, WIDTH, HEIGHT);
    MapGrid map = new MapGrid(pane);
    ProjectileManager pManager = new ProjectileManager();
    Player player = new Player(pane, 50, 50, PLAYER_SIZE);
    scene.setFill(Color.DARKBLUE);

    scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
      @Override
      public void handle(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.W){
          player.keyUp(true);
          return;
        }
        else if(keyEvent.getCode() == KeyCode.S){
          player.keyDown(true);
          return;
        }
        else if(keyEvent.getCode() == KeyCode.A){
          player.keyLeft(true);
          return;
        }
        else if(keyEvent.getCode() == KeyCode.D){
          player.keyRight(true);
          return;
        }
        else if(keyEvent.getCode() == KeyCode.SPACE){
          player.shoot(pane);
        }
      }
    });
    scene.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>(){
      @Override
      public void handle(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.W){
          player.keyUp(false);
          return;
        }
        else if(keyEvent.getCode() == KeyCode.S){
          player.keyDown(false);
          return;
        }
        else if(keyEvent.getCode() == KeyCode.A){
          player.keyLeft(false);
          return;
        }
        else if(keyEvent.getCode() == KeyCode.D){
          player.keyRight(false);
          return;
        }
      }
    });

    EventHandler<ActionEvent> eHandler = e -> {
      pManager.tick();
      player.tick();
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
