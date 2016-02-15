package slicktest;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Environment {
	
	private ArrayList<Map> maps ;
	private Player player ;
	private int currentMap ;

	public Environment() throws SlickException {
		this.maps = new ArrayList<Map>();
		this.maps.add(new Map("/maps/map2d.tmx")) ;
		this.player = new Player("Martelle", "/images/sonic.png", 24, 32) ;
		
		currentMap = 0 ;
		
    	this.player.setLocation(
    			(Integer.parseInt(maps.get(currentMap).getTiledMap().getMapProperty("startX", "0"))-1) * Map.TILE_WIDTH, 
    			Integer.parseInt(maps.get(currentMap).getTiledMap().getMapProperty("startY", "0")) * Map.TILE_HEIGHT - player.height
    		);
	}

	public void update() {
		if(player.isMoving()) {
        	if(player.getDirection() == 0) {
        	} else if (player.getDirection() == 1) {
        		if (player.getLocationX() <= maps.get(currentMap).getWidth()* Map.TILE_WIDTH - 32) 
        			this.player.setLocationX(player.getLocationX()+player.getMoveSpeed());
        	} else if (player.getDirection() == 2) {
        	} else if (player.getDirection() == 3) {
        		if (player.getLocationX() > 1)
        			this.player.setLocationX(player.getLocationX()-player.getMoveSpeed());
        	}
    	}
		checkTeleport() ;
	}
	
	/** 
	 * This method checks if player isn't on a teleport.
	 * It checks every objects with "teleport" type and verify if player is in the object's box.
	 */
	private void checkTeleport() {
		TiledMap map = maps.get(currentMap).getTiledMap() ;
		// We browse all objects on the map
		for(int i = 0 ; i < map.getObjectCount(0) ; i++ ) {
			// If object is a teleporter, we check if the player is in the object's box
			// Then we teleport the player to his new Location
			if(		"teleport".equals(map.getObjectType(0, i))
				&&	player.getLocationX() > map.getObjectX(0, i)
				&&	player.getLocationX() + player.width < map.getObjectX(0, i) + map.getObjectWidth(0, i)
				&&	player.getLocationY() - player.height < map.getObjectY(0, i)
				&& player.getLocationY() < map.getObjectY(0, i) + map.getObjectHeight(0, i) 
			) {
				player.setLocation(
						Integer.parseInt(map.getObjectProperty(0, i, "x", "0")) * 16,
						Integer.parseInt(map.getObjectProperty(0, i, "y", "0")) * 16 - player.height );
			}	
		}
		
	}
	
	/** 
	 * This method return the current map.
	 * @return Map
	 */
	public Map getCurrentMap() {
		return maps.get(currentMap);
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
