package physics;

import org.newdawn.slick.geom.Vector2f;

/**
 * This class represents a movement vector that can be applied to an entity for a given duration 
 * @author Strift
 *
 */
public class Force {
	
	private Vector2f vector;
	private int duration;
	
	/**
	 * Construct a movement of given vector value and duration
	 * @param value
	 * @param duration : in millisecond
	 */
	public Force(Vector2f value, int duration) {
		this.vector = value;
		this.duration = duration;
	}
	
	/**
	 * Construct a movement of given vector X and Y value and duration
	 * @param vector
	 * @param duration : in millisecond
	 */
	public Force(float x, float y, int duration) {
		this.vector = new Vector2f(x, y);
		this.duration = duration;
	}
	
	/**
	 * Get the force vector
	 * @return Vector2f
	 */
	public Vector2f getVector() {
		return vector;
	}
	
	/**
	 * Get the movement duration in milliseconds
	 * @return int
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * Reduce the force duration by the given value
	 * @param value
	 */
	public void reduceDuration(int value) {
		this.duration -= value;
	}

}
