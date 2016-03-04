package minions;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Projectile implements Constants {
	private static ProjectileManager manager;
	Minion target;
	private Circle circle;
	double dx, dy;
	int damage;
	
	public Projectile(Pane pane, Minion target, double x, double y, int damage){
		circle = new Circle(PROJECTILE_RADIUS, PROJECTILE_COLOR);
		circle.setTranslateX(x);
		circle.setTranslateY(y);
		setSpeedPolar(PROJECTILE_SPEED, getDirectionToPoint(target.getX(), target.getY()));
		pane.getChildren().add(circle);
		this.damage = damage;
		manager.add(this);
		this.target = target;
	}

	public Projectile(Pane pane, Minion source, Minion target){
		circle = new Circle(PROJECTILE_RADIUS, PROJECTILE_COLOR);
		circle.setTranslateX(source.getX());
		circle.setTranslateY(source.getY());
		setSpeedPolar(PROJECTILE_SPEED, getDirectionToPoint(target.getX(), target.getY()));
		pane.getChildren().add(circle);
		damage = MINION_DAMAGE;
		manager.add(this);
		this.target = target;
	}
	
	public static ProjectileManager getManager(){
		return manager;
	}
	public static void setManager(ProjectileManager man){
		manager = man;
	}

	public double getX(){
		return circle.getTranslateX();
	}
	public double getY(){
		return circle.getTranslateY();
	}
	public double getRadius(){
		return circle.getRadius();
	}
	public Circle getCircle(){
		return circle;
	}
	public double getBoundsLeft(){
		return circle.getTranslateX() - circle.getRadius();
	}
	public double getBoundsRight(){
		return circle.getTranslateX() + circle.getRadius();
	}
	public double getBoundsTop(){
		return circle.getTranslateY() - circle.getRadius();
	}
	public double getBoundsBottom(){
		return circle.getTranslateY() + circle.getRadius();
	}
	public double getDirectionAngle(){
		return Math.atan2(dy, dx);
	}
	public double getDirectionToPoint(double x, double y){
		return Math.atan2(y - getY(), x - getX());
	}
	public double getSpeed(){
		return Math.sqrt(dx*dx + dy*dy);
	}
	public double getDistanceSquared(double x, double y){
		return Math.pow(x - getX(), 2) + Math.pow(y - getY(), 2);
	}
	public double getDistanceSquared(Minion target){
		return Math.pow(target.getX() - getX(), 2) + Math.pow(target.getY() - getY(), 2);
	}
	public void setSpeedCoords(double x, double y){
		dx = x;
		dy = y;
	}
	public void setSpeedPolar(double speed, double rad){
		dx = speed * Math.cos(rad);
		dy = speed * Math.sin(rad);
	}
	
	public boolean reachedTarget(){
		if(getDistanceSquared(target) < Math.pow(getRadius() + target.getRadius(), 2))
			return true;
		return false;
	}
	
	public void die(){
		circle.setVisible(false);
		//manager.remove(this);
	}
	
	public void tick(){
		if(circle.isVisible()){
			circle.setTranslateX(getX() + dx);
			circle.setTranslateY(getY() + dy);
			if(reachedTarget()){
				target.hit(damage);
				die();
			}
		}
	}
}
