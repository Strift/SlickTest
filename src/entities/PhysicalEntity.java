package entities;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 * This class represents an entity that has a position and can move on the map
 * @author Strift
 *
 */
public abstract class PhysicalEntity extends Entity {
	
	protected Vector2f position;
	protected int speed;
	
	/**
	 * Default constructor
	 */
	public PhysicalEntity() {
		super();
		position = new Vector2f();
	}
	
	/**
	 * Get the position vector
	 * @return Vector2f
	 */
	public Vector2f getPosition() {
		return position;
	}
	
	/**
	 * Set the position vector
	 * @param position
	 */
	public void setPosition(Vector2f position) {
		this.position = position;
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
	 * Get the Entity move speed
	 * @return int
	 */
	public int getSpeed() {
		return this.speed ;
	}
	
	/**
	 * Set the move speed
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed ;
	}
	
	/**
	 * Get the entity hitbox
	 * @return Shape
	 */
	public abstract Shape getHitbox();
	
	/**
	 * Returns true if the two PhysicalEntities hitbox intersects
	 * @param other
	 * @return boolean
	 */
	public boolean collidesWith(PhysicalEntity other) {
		return this.getHitbox().intersects(other.getHitbox());
	}
}
