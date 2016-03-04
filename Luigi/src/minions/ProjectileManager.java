package minions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectileManager {
	private List<Projectile> projectiles = new ArrayList<>();
	
	public ProjectileManager(){
		Projectile.setManager(this);
	}
	
	public void add(Projectile projectile){
		projectiles.add(projectile);
	}
	
	public void tick(){
		Projectile temp;
		for(Iterator<Projectile> p = projectiles.iterator(); p.hasNext();){
			temp = p.next();
			temp.tick();
			if(temp.isDead())
				p.remove();
		}
	}
	
}
