package minions;

import java.util.ArrayList;
import java.util.Iterator;

public class ProjectileManager {
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	
	public ProjectileManager(){
		Projectile.setManager(this);
	}
	
	public void add(Projectile projectile){
		projectiles.add(projectile);
	}

	public void remove(Projectile projectile){
		for(Iterator<Projectile> p = projectiles.iterator(); p.hasNext();){
			if(p.equals(projectile)){
				p.remove();
			}
		}
	}
	
	public void tick(){
		for(Projectile projectile : projectiles){
			projectile.tick();
		}
	}
	
}
