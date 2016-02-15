package entities;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * This class represents an entity that has a position and can move on the map
 * @author Strift
 *
 */
public abstract class PhysicalEntity extends Entity {
	
	protected Vector2f position;
	protected int height;
	protected int width;
	
	/**
	 * Default constructor
	 */
	public PhysicalEntity() {
		super();
		position = new Vector2f();
	}
	
	/**
	 * Get the position vector
	 * @return
	 */
	public Vector2f getPosition() {
		return position;
	}
	
	/**
	 * Set the position vector
	 * @param x
	 * @param y
	 */
	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}
	
	/**
	 * Set the position vector
	 * @param position
	 */
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	/**
	 * Get the entity hitbox
	 * @return Rectangle
	 */
	public Rectangle getHitbox() {
		return new Rectangle(position.x, position.y, height, width);
	}
}
