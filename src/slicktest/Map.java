package slicktest;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map extends TiledMap {
	
	ArrayList<Object> objects ;
	
	public Map(String ref) throws SlickException {
		super(ref);
		this.objects = new ArrayList<Object>() ;
	}
	
}
