package minions;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Minion implements Constants {
	private static ArrayList<Minion> minions;
	Circle circle;
	HealthBar bar;
	int respawnCount;
	int reloadCount;
	boolean dead;
	
	public Minion(Pane pane, double x, double y){
		circle = new Circle(x, y, MINION_CASTER_SIZE);
		circle.setFill(MINION_COLOR);
		pane.getChildren().add(circle);
		bar = new HealthBar(pane, this);
		dead = false;
		reloadCount = 5;
		//minions.add(this);
	}

	public Minion(Pane pane, double x, double y, double r) {
		circle = new Circle(x, y, r);
		circle.setFill(MINION_COLOR);
		pane.getChildren().add(circle);
		bar = new HealthBar(pane, this);
		dead = false;
		reloadCount = 5;
		//minions.add(this);
	}
	
	public static void setArrayList(ArrayList<Minion> list){
		minions = list;
	}
	
	public static Minion getRandomMinion(){
		return minions.get((int)(Math.random() * minions.size()));
	}
	
	public static Minion getRandomMinion(Minion targetToAvoid){
		Minion ret = getRandomMinion();
		while(ret.equals(targetToAvoid) || ret.dead){
			ret = getRandomMinion();
		}
		return ret;
	}
	
	public void reset(){
		circle.setVisible(true);
		bar.reset();
		dead = false;
		reloadCount = 5;
	}

	public double getRadius(){
		return circle.getRadius();
	}
	public double getX(){
		return circle.getCenterX();
	}
	public double getY(){
		return circle.getCenterY();
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
	
	public void shoot(Pane pane, Minion target){
		new Projectile(pane, this, target);
		//new Projectile(pane, this, minions.get(0));
	}
	
	public void reload(){
		reloadCount = MINION_RELOAD_TIME;
	}
	
	public void tick(Pane pane){
		if(dead){
			respawnCount--;
			if(respawnCount == 0)
				reset();
		}
		else{
			if(reloadCount == 0){
				shoot(pane, getRandomMinion(this));
				reload();
			}
			else{
				reloadCount--;
			}
		}
	}
}
