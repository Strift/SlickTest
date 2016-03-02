package entities;

import org.newdawn.slick.geom.Vector2f;

import physics.ForceList;

/**
 * This class represents an entity that is subject to physics
 * @author Strift
 *
 */
public abstract class PhysicalEntity extends Entity implements ICollisionable {
	
	protected final static float GRAVITY_FORCE = 2f;
	
	protected Vector2f position;
	protected ForceList forces;
	
	/**
	 * Default constructor
	 */
	public PhysicalEntity() {
		super();
		position = new Vector2f();
		forces = new ForceList();
	}
	
	/**
	 * Get the entity's position
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
	 * Returns true if the two PhysicalEntities hitbox intersects
	 * @param other
	 * @return boolean
	 */
	public boolean collidesWith(PhysicalEntity other) {
		return this.getHitbox().intersects(other.getHitbox());
	}
}
