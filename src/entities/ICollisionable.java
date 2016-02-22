package entities;

import org.newdawn.slick.geom.Shape;

/**
 * Classes implementing this interface have a hitbox
 * @author Strift
 *
 */
public interface ICollisionable {
	
	/**
	 * Get the entity's hitbox
	 * @return Shape
	 */
	public Shape getHitbox();
}
