package entities;

import org.newdawn.slick.geom.Vector2f;

/**
 * Classes implementing this interface have a velocity
 * @author Strift
 *
 */
public interface IMoveable {
	
	/**
	 * Get the entity's movement vector
	 * @param delta : elapsed time
	 * @return Vector2f
	 */
	public Vector2f nextPosition(int delta);
}
