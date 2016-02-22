package entities;

import org.newdawn.slick.geom.Path;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import physics.Movement;
import slicktest.Map;
import system.Application;

/**
 * This class represents an entity that has a position and can move on the map
 * @author Strift
 *
 */
public abstract class PhysicalEntity extends Entity {
	
	private static Map map;
	
	protected Vector2f position;
	protected Vector2f velocity;
	protected float speed;
	protected boolean falling;
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
		velocity = new Vector2f();
		speed = 1f;
		falling = false;
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
	 * Add a movement vector to the entity's current movement vector
	 * @param movement
	 */
	public void addMovement(Vector2f movement) {
		this.velocity.add(movement);
	}
	
	/**
	 * Add a movement vector to the entity's current movement vector
	 * @param x
	 * @param y
	 */
	public void addMovement(float x, float y) {
		velocity.x += x;
		velocity.y += y;
	}
	
	/**
	 * Resets the entity's movement vector
	 */
	public void stopMovement() {
		velocity.x = 0;
		velocity.y = 0;
	}
	
	/**
	 * Get the entity movement vector
	 * @return Vector2f
	 */
	public Vector2f getMovement() {
		return velocity;
	}
	
	/**
	 * Return true if the entity's movement vector is non null
	 * @return boolean
	 */
	public boolean isMoving() {
		return (velocity.x != 0 || velocity.y != 0);
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
	
	/**
	 * This method handles the whole entity update process
	 * @param delta : elapsed time since last rendering
	 */
	public void update(int delta) {
		Vector2f newPos = position.copy();
		float gravityForce = 0;
		
		// Enable gravity if character is falling but has already took off
		if (falling && this.touchesGround() == false) {
			gravityForce = 2.f;
		}
		newPos.x += velocity.x * speed * Application.FRAME_RATE/delta;
		newPos.y += (velocity.y + gravityForce) * Application.FRAME_RATE/delta;
		// Movement result
		movement.update(delta);
		Vector2f result = movement.result();
		newPos.x += result.x * Application.FRAME_RATE/delta;
		newPos.y += result.y * Application.FRAME_RATE/delta;
		// While the new position is outside the map
		while(!isInsideMap(newPos)) {
			newPos.x -= velocity.x;
		}
		position = newPos;
		// Disable gravity force if entity has landed
		if (this.touchesGround()) {
			falling = false;
		}
	}
	
	/**
	 * Returns true if the given position is inside the map bounds
	 * @param position
	 * @return
	 */
	private boolean isInsideMap(Vector2f position) {
		return (position.x >= 0 && position.x < map.getWidth() - this.getHitbox().getWidth());
	}
	
	public boolean touchesGround() {
		for (Path field : PhysicalEntity.map.getFields()) {
			if (this.getHitbox().intersects(field)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isFalling() {
		return falling;
	}
}
