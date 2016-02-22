package physics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;

public class ForceList {

	private List<Force> forces;
	
	/**
	 * Default constructor
	 */
	public ForceList() {
		forces = new LinkedList<Force>();
	}
	
	/**
	 * Add a force of given vector and duration
	 * @param vector
	 * @param duration : in milliseconds
	 */
	public void add(Vector2f vector, int duration){
		forces.add(new Force(vector, duration));
	}
	
	/**
	 * Update forces list and remove forces where duration is elapsed
	 * @param delta : elapsed time
	 */
	public void update(int delta) {
		for (Iterator<Force> it = forces.iterator(); it.hasNext(); /* nothing here */) {
			Force force = it.next(); // seems to be returning the iterator value and advancing it at the same time
			force.reduceDuration(delta);
			if (force.getDuration() <= 0) {
				it.remove();
			}
		}
	}
	
	/**
	 * Get the vector resulting from all forces effect
	 * @return Vector2f
	 */
	public Vector2f result() {
		Vector2f sum = new Vector2f();
		for(Force force : forces) {
			sum.add(force.getVector());
		}
		return sum;
	}
}
