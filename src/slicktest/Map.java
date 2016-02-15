package slicktest;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Our map class
 * It encapsulates the Tiled map and gives us all the needed information
 * @author Strift
 *
 */
public class Map {
	
	public final static int TILE_HEIGHT = 16;
	public final static int TILE_WIDTH = 16;
	
	protected TiledMap tiledMap;
	
	public Map(String path) throws SlickException {
		tiledMap = new TiledMap(path);
	}
	
	public int getHeight() {
		return tiledMap.getHeight();
	}
	
	public int getWidth() {
		return tiledMap.getWidth();
	}
	
	public int getRealHeight() {
		return tiledMap.getHeight() * TILE_HEIGHT;
	}
	
	public int getRealWidth() {
		return tiledMap.getWidth() * TILE_WIDTH;
	}
	
	// We will later make this class renderable but for the moment it just simplifies the process to access this
	public TiledMap getTiledMap() {
		return tiledMap;
	}
	
}
