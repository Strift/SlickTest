package game;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * This class represents a game map.
 * It encapsulates the slick Tiled map
 * @author Strift
 *
 */
public class Map {
	
	public final static int TILE_HEIGHT = 16;
	public final static int TILE_WIDTH = 16;
	
	protected TiledMap tiledMap;
	
	/**
	 * Constructor : build a map from the Tiled map path
	 * @param path
	 * @throws SlickException
	 */
	public Map(String path) throws SlickException {
		tiledMap = new TiledMap(path);
	}
	
	/**
	 * Get the map's height in tiles
	 * @return int
	 */
	public int getHeight() {
		return tiledMap.getHeight();
	}
	
	/**
	 * Get the map's width in tiles
	 * @return int
	 */
	public int getWidth() {
		return tiledMap.getWidth();
	}
	
	/**
	 * Get the map's height in pixels
	 * @return int
	 */
	public int getRealHeight() {
		return tiledMap.getHeight() * TILE_HEIGHT;
	}
	
	/**
	 * Get the map's width in pixels
	 * @return int
	 */
	public int getRealWidth() {
		return tiledMap.getWidth() * TILE_WIDTH;
	}
	
	/**
	 * Get the encapsulated tiled map
	 * @return TiledMap
	 */
	public TiledMap getTiledMap() {
		return tiledMap;
	}
	
}
