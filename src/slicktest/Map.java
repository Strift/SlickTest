package slicktest;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
	
	public final static int TILE_HEIGHT = 16;
	public final static int TILE_WIDTH = 16;
	
	protected TiledMap tiledMap;
	
	public Map(String path) throws SlickException {
		tiledMap = new TiledMap(path);
	}
	
}
