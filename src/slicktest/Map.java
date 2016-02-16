package slicktest;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.geom.Path;
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
	
	private TiledMap tiledMap;
	private Vector2f initialPosition;
	private ArrayList<Path> fields;
	
	/**
	 * Constructor : build a map from the Tiled map path
	 * @param path
	 * @throws SlickException
	 */
	public Map(String path) throws SlickException {
		tiledMap = new TiledMap(path);
		initialPosition = new Vector2f(
				Float.parseFloat(tiledMap.getMapProperty("startX", "0")) * Map.TILE_WIDTH, 
				Float.parseFloat(tiledMap.getMapProperty("startY", "0")) * Map.TILE_WIDTH
			);
		fields = new ArrayList<Path>();
		Path groundLine = new Path(0, 224);
		groundLine.lineTo(800, 224);
		fields.add(groundLine);
		//this.loadObjects();
	}
	
	@SuppressWarnings("unused")
	private void loadObjects() {
		// Loop through the object groups
		for (int objectGroupId = 0; objectGroupId < tiledMap.getObjectGroupCount(); objectGroupId++) {
			// Loop through the objects in the group
			for (int objectId = 0; objectId < tiledMap.getObjectCount(objectGroupId); objectId++) {
				String objectType = tiledMap.getObjectType(objectGroupId, objectId);
				if (objectType == "field") {
					// We need to parse the XML to read the objects :(
				}
			}
		}
	}
	
	/**
	 * Get the map's height in pixels
	 * @return int
	 */
	public int getHeight() {
		return tiledMap.getHeight() * TILE_HEIGHT;
	}
	
	/**
	 * Get the map's width in pixels
	 * @return int
	 */
	public int getWidth() {
		return tiledMap.getWidth() * TILE_WIDTH;
	}
	
	/**
	 * Get the encapsulated tiled map
	 * @return TiledMap
	 */
	public TiledMap getTiledMap() {
		return tiledMap;
	}
	
	/**
	 * Get the initial player position of the map
	 * @return Vector2f
	 */
	public Vector2f getInitialPosition() {
		return initialPosition;
	}
	
	/**
	 * Get the list of fields in the map
	 * @return ArrayList<Path>
	 */
	public ArrayList<Path> getFields() {
		return fields;
	}
	
}
