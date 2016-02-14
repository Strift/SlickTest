package slicktest;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map extends TiledMap {
	
	ArrayList<Object> objects ;
	
	public Map(String ref) throws SlickException {
		super(ref);
		this.objects = new ArrayList<Object>() ;
		this.loadObjects() ;
	}
	
	private void loadObjects() {
		int nbGroup = this.getObjectGroupCount() ;
		for (int i = 0 ; i < nbGroup ; i++) {
			for (int j = 0 ; j < this.getObjectCount(i) ; j++) {
				Object object = new Object() ;
				if(object.getClass().toString() == "Rectangle")
					System.out.println(j) ;
			}
		}
	}
	
}
