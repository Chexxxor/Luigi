package minions;

public interface Constants {
	int PLAYER_DAMAGE = 67;

	int MINION_CASTER_HEALTH = 300;
	int MINION_MELEE_HEALTH = 468;
	int MINION_SIEGE_HEALTH = 851;
	int MINION_CASTER_SIZE = 50;
	int MINION_DAMAGE = 17;
	
	int HEALTH_WIDTH = 100;
	int HEALTH_HEIGHT = 10;
	
	int PADDING_LEFT = 100;
	int PADDING_RIGHT = 100;
	int PADDING_TOP = 100;
	int PADDING_BOT = 100;
	
	int PADDING_V = 50;
	int PADDING_H = 50;
	
	int WIDTH = 6 * MINION_CASTER_SIZE + 2 * PADDING_H + PADDING_LEFT + PADDING_RIGHT;
	int HEIGHT = WIDTH;
}