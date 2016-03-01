package minions;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Minion implements Constants {
	Circle circle;
	HealthBar bar;
	int respawnCount;
	boolean dead;
	
	public Minion(Pane pane, double x, double y){
		circle = new Circle(x, y, MINION_CASTER_SIZE);
		circle.setFill(Color.DARKRED);
		pane.getChildren().add(circle);
		bar = new HealthBar(pane, this);
		dead = false;
	}

	public Minion(Pane pane, double x, double y, double r) {
		circle = new Circle(x, y, r);
		circle.setFill(Color.CRIMSON);
		pane.getChildren().add(circle);
		bar = new HealthBar(pane, this);
		dead = false;
	}
	
	public void reset(){
		circle.setVisible(true);
		bar.reset();
		dead = false;
	}

	public int getX(){
		return (int) circle.getCenterX();
	}
	public int getY(){
		return (int) circle.getCenterY();
	}
	
	public void die(){
		circle.setVisible(false);
		respawnCount = 5;
		dead = true;
	}
	
	public boolean isInside(double x, double y){
		if(getDistanceSquared(x, y) < Math.pow(MINION_CASTER_SIZE, 2))
			return true;
		return false;
	}
	
	public double getDistanceSquared(double x, double y){
		return Math.pow(x - getX(), 2) + Math.pow(y - getY(), 2);
	}
	
	public boolean hit(int damage){
		if(bar.hit(damage) && !dead){
			die();
			return true;
		}
		return false;
	}
	
	public void tick(){
		if(dead){
			respawnCount--;
			if(respawnCount == 0)
				reset();
		}
	}
}
