package skillshots;

import javafx.scene.layout.Pane;
import minions.Minion;
import minions.Projectile;

public class Player extends Minion implements Constants {
  private boolean[] moves = {false, false, false, false};
  private int lastMove;
  private int orientation;

  public Player(Pane pane, double x, double y) {
    super(pane, x, y);
    lastMove = 4;
    orientation = 5;
  }

  public Player(Pane pane, double x, double y, double r) {
    super(pane, x, y, r);
    lastMove = 4;
    orientation = 5;
  }

  public void keyLeft(boolean pressed){
    if(moves[0] != pressed){
      if(pressed)
        lastMove--;
      else
        lastMove++;
    }
    moves[0] = pressed;
  }
  public void keyRight(boolean pressed){
    if(moves[1] != pressed){
      if(pressed)
        lastMove++;
      else
        lastMove--;
    }
    moves[1] = pressed;
  }
  public void keyUp(boolean pressed){
    if(moves[2] != pressed){
      if(pressed)
        lastMove-=3;
      else
        lastMove+=3;
    }
    moves[2] = pressed;
  }
  public void keyDown(boolean pressed){
    if(moves[3] != pressed){
      if(pressed)
        lastMove+=3;
      else
        lastMove-=3;
    }
    moves[3] = pressed;
  }

  public void shoot(Pane pane){
    new Projectile(pane, getX(), getY(), orientation, SHOT_SPEED, PLAYER_DAMAGE);
  }
  
  public void tick(){
    if(lastMove != 4)
      orientation = lastMove;
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
