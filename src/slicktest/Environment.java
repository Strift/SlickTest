package slicktest;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Environment {
	
	private Map map ;
	private Player player ;

	public Environment() throws SlickException {
		this.map = new Map("/maps/map2d.tmx") ;
		this.player = new Player("Martelle", "/images/sonic.png", 24, 32) ;
    	this.player.setLocation(
    			(Integer.parseInt(map.getTiledMap().getMapProperty("startX", "0"))-1) * Map.TILE_WIDTH, 
    			Integer.parseInt(map.getTiledMap().getMapProperty("startY", "0")) * Map.TILE_HEIGHT - player.height
    		);
	}

	public void update() {
		if(player.isMoving()) {
        	if(player.getDirection() == 0) {
        	} else if (player.getDirection() == 1) {
        		if (player.getLocationX() <= map.getWidth()* Map.TILE_WIDTH - 32) 
        			this.player.setLocationX(player.getLocationX()+player.getMoveSpeed());
        	} else if (player.getDirection() == 2) {
        	} else if (player.getDirection() == 3) {
        		if (player.getLocationX() > 1)
        			this.player.setLocationX(player.getLocationX()-player.getMoveSpeed());
        	}
    	}
		checkTeleport() ;
	}
	
	public void render(Graphics g) {
		for (int i = 0 ; i < map.getTiledMap().getLayerCount() ; i++) {
			map.getTiledMap().render(0, 0, i);
			if(map.getTiledMap().getLayerProperty(i, "level", "none").equals("0")) {
				this.getPlayer().render(g);
			}
		}
	}
	
	/** 
	 * This method checks if player isn't on a teleport.
	 * It checks every objects with "teleport" type and verify if player is in the object's box.
	 */
	private void checkTeleport() {
		TiledMap tiledMap = map.getTiledMap() ;
		// We browse all objects on the map
		for(int i = 0 ; i < tiledMap.getObjectCount(0) ; i++ ) {
			// If object is a teleporter, we check if the player is in the object's box
			// Then we teleport the player to his new Location
			if(		"teleport".equals(tiledMap.getObjectType(0, i))
				&&	player.getLocationX() > tiledMap.getObjectX(0, i)
				&&	player.getLocationX() + player.width < tiledMap.getObjectX(0, i) + tiledMap.getObjectWidth(0, i)
				&&	player.getLocationY() - player.height < tiledMap.getObjectY(0, i)
				&& player.getLocationY() < tiledMap.getObjectY(0, i) + tiledMap.getObjectHeight(0, i) 
			) {
				player.setLocation(
						Integer.parseInt(tiledMap.getObjectProperty(0, i, "x", "0")) * 16,
						Integer.parseInt(tiledMap.getObjectProperty(0, i, "y", "0")) * 16 - player.height );
			}	
		}
		
	}
	
	/** 
	 * This method return the current map.
	 * @return Map
	 */
	public Map getMap() {
		return map;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
