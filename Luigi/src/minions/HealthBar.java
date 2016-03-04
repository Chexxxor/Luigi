package minions;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthBar implements Constants {
	int x;
	int y;
	int maxHealth;
	int currentHealth;
	Rectangle current;
	Rectangle max;

	public HealthBar(Pane pane, Minion minion) {
		x = (int)minion.getX() - HEALTH_WIDTH / 2;
		y = (int)minion.getY() - MINION_CASTER_SIZE;
		maxHealth = MINION_CASTER_HEALTH;
		currentHealth = maxHealth;
		max = new Rectangle(x + HEALTH_WIDTH, y, 0, HEALTH_HEIGHT);
		current = new Rectangle(x, y, HEALTH_WIDTH, HEALTH_HEIGHT);
		max.setFill(Color.BLACK);
		current.setFill(Color.RED);
		pane.getChildren().add(max);
		pane.getChildren().add(current);
	}
	
	public void reset(){
		maxHealth = MINION_CASTER_HEALTH;
		currentHealth = maxHealth;
		max.setVisible(true);
		current.setVisible(true);
		update();
	}
	
	private double getFactor(){
		return (double)currentHealth / (double)maxHealth;
	}
	
	private int getSeparation(){
		return (int)(HEALTH_WIDTH * getFactor());
	}
	
	private void update(){
		current.setWidth(getSeparation());
		max.setWidth(HEALTH_WIDTH-getSeparation());
		max.relocate(x + getSeparation(), y);
	}

	public boolean hit(int damage){
		currentHealth -= damage;
		update();
		if(currentHealth > 0)
			return false;
		max.setVisible(false);
		current.setVisible(false);
		return true;
	}
}
