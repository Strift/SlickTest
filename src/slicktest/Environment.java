package slicktest;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class Environment {
	
	private ArrayList<Map> maps ;
	private Player player ;
	
	public Environment() throws SlickException {
		this.maps = new ArrayList<Map>();
		this.maps.add(new Map("/maps/map2d.tmx")) ;
		this.player = new Player("Martelle", "/images/sonic.png", 24, 32) ;
    	this.player.setLocation(1, (maps.get(0).getHeight()-8) * Map.TILE_HEIGHT);
	}

	public void update() {
		if(player.isMoving()) {
        	if(player.getDirection() == 0) {
        	} else if (player.getDirection() == 1) {
        		if (player.getLocationX() <= maps.get(0).getWidth()* Map.TILE_WIDTH - 32) 
        			this.player.setLocationX(player.getLocationX()+player.getMoveSpeed());
        	} else if (player.getDirection() == 2) {
        	} else if (player.getDirection() == 3) {
        		if (player.getLocationX() > 1)
        			this.player.setLocationX(player.getLocationX()-player.getMoveSpeed());
        	}
    	}
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Map> getMaps() {
		return maps;
	}

	public void setMaps(ArrayList<Map> maps) {
		this.maps = maps;
	}
}
