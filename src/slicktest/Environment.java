package slicktest;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import entities.Player;

public class Environment {
	
	private Map map ;
	private Player player ;

	public Environment() throws SlickException {
		map = new Map("/maps/map2d.tmx") ;
		player = new Player("/images/sonic.png", 24, 32) ;
		player.setPosition(map.getInitialPosition().x, map.getInitialPosition().y - player.getHeight());
	}

	public void update(int delta) {
		if(player.isMoving()) {
			switch(player.getDirection()) {
			case Backward:
				break;
			case Forward:
				break;
			case Left:
        		if (player.getPosition().x > 0) {
        			player.update(delta);
        		}
				break;
			case Right:
        		if (player.getPosition().x < map.getRealWidth() - player.getWidth()) {
        			player.update(delta);
        		}
				break;
			}
    	}
		checkTeleport() ;
	}
	
	public void render(Graphics g) {
		for (int i = 0 ; i < map.getTiledMap().getLayerCount() ; i++) {
			map.getTiledMap().render(0, 0, i);
			if(map.getTiledMap().getLayerProperty(i, "level", "none").equals("0")) {
				Player player = this.getPlayer();
				g.drawAnimation(player.getAnimation(), player.getPosition().x, player.getPosition().y);
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
			// If object is a teleport, we check if the player is in the object's box
			// Then we teleport the player to his new Location
			if("teleport".equals(tiledMap.getObjectType(0, i))
				&&	player.getPosition().x > tiledMap.getObjectX(0, i)
				&&	player.getPosition().x + player.getWidth() < tiledMap.getObjectX(0, i) + tiledMap.getObjectWidth(0, i)
				&&	player.getPosition().y - player.getHeight() < tiledMap.getObjectY(0, i)
				&& player.getPosition().y < tiledMap.getObjectY(0, i) + tiledMap.getObjectHeight(0, i) 
			) {
				player.setPosition(
						Integer.parseInt(tiledMap.getObjectProperty(0, i, "x", "0")) * 16,
						Integer.parseInt(tiledMap.getObjectProperty(0, i, "y", "0")) * 16 - player.getHeight());
			}	
		}
		
	}
	
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
