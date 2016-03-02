package slicktest;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Path;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import entities.PhysicalEntity;
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
		// If player is moving
		if (player.isWalking() || player.isFalling()) {
			Vector2f nextPos = player.nextPosition(delta);
			int xMovement = 0;
			if (player.getPosition().x < nextPos.x) {
				xMovement = 1;
			} else if (player.getPosition().x > nextPos.x) {
				xMovement = -1;
			}
			while(!isInsideMap(player, nextPos)) {
				nextPos.x -= xMovement; 
			}
			player.setPosition(nextPos);
			if (touchesGround(player)) {
				player.hasLanded();
			}
		}
		//checkTeleport() ;
	}
	
	private boolean isInsideMap(PhysicalEntity entity, Vector2f position) {
		return (position.x >= 0 && position.x < map.getWidth() - entity.getHitbox().getWidth());
	}
	
	private boolean touchesGround(PhysicalEntity entity) {
		for (Path field : map.getFields()) {
			if (entity.getHitbox().intersects(field)) {
				return true;
			}
		}
		return false;
	}
	
	public void render(Graphics g) {
		for (int i = 0 ; i < map.getTiledMap().getLayerCount() ; i++) {
			map.getTiledMap().render(0, 0, i);
			if(map.getTiledMap().getLayerProperty(i, "level", "none").equals("0")) {
				Player player = this.getPlayer();
				g.drawAnimation(player.getAnimation(), player.getPosition().x, player.getPosition().y);
			}
		}
		// Draw fields for debug purposes
		for (Path path : map.getFields()) {
			g.draw(path);
		}
	}
	
	/** 
	 * This method checks if player isn't on a teleport.
	 * It checks every objects with "teleport" type and verify if player is in the object's box.
	 */
	@SuppressWarnings("unused")
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
