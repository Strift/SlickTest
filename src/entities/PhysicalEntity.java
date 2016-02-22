package entities;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import physics.Movement;
import slicktest.Map;

/**
 * This class represents an entity that has a position and can move on the map
 * @author Strift
 *
 */
public abstract class PhysicalEntity extends Entity {

	protected static Map map;
	
	protected final static float GRAVITY_FORCE = 2f;
	
	protected Vector2f position;
	protected Movement movement;
	
	public static void setMap(Map map) {
		PhysicalEntity.map = map;
	}
	
	/**
	 * Default constructor
	 */
	public PhysicalEntity() {
		super();
		position = new Vector2f();
		movement = new Movement();
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
	 * Get the entity's hitbox
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
	
	/**
	 * Get the entity's velocity
	 * @return Vector2f
	 */
	public abstract Vector2f getVelocity();
}
