package skillshots;

import javafx.scene.layout.Pane;
import minions.Minion;
import minions.Projectile;

public class Player extends Minion implements Constants {
  private boolean[] moves = {false, false, false, false};
  private int lastMove;

  public Player(Pane pane, double x, double y) {
    super(pane, x, y);
    lastMove = 5;
  }

  public Player(Pane pane, double x, double y, double r) {
    super(pane, x, y, r);
    lastMove = 5;
  }

  public void keyLeft(boolean pressed){
    moves[0] = pressed;
  }
  public void keyRight(boolean pressed){
    moves[1] = pressed;
  }
  public void keyUp(boolean pressed){
    moves[2] = pressed;
  }
  public void keyDown(boolean pressed){
    moves[3] = pressed;
  }

  public void shoot(Pane pane){
    new Projectile(pane, getX(), getY(), lastMove, SHOT_SPEED, PLAYER_DAMAGE);
  }
  
  public void tick(){
    if(moves[0] != moves[1]){
      if(moves[0])
        circle.setCenterX(Math.max(getX() - STEP_SIZE, PLAYER_SIZE));
      else
        circle.setCenterX(Math.min(getX() + STEP_SIZE, Constants.WIDTH - PLAYER_SIZE));
    }
    if(moves[2] != moves[3]){
      if(moves[2])
        circle.setCenterY(Math.max(getY() - STEP_SIZE, PLAYER_SIZE));
      else if(moves[3] && !moves[2])
        circle.setCenterY(Math.min(getY() + STEP_SIZE, Constants.HEIGHT - PLAYER_SIZE));
    }
  }

}
