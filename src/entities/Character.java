package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * This class represents a character within the game.
 * @author elythiel
 *  
 */
public class Character extends PhysicalEntity {
	
	public enum Direction {
		Forward, Backward, Left, Right
	}

	// Physics attributes
	protected Direction direction;
	protected int height;
	protected int width;
	
	// Render attributes
	protected SpriteSheet spriteSheet;
	protected Animation[] animations;
	
	/**
	 * @param file : spritesheet file path
	 * @param width : sprites width
	 * @param height : sprites height
	 * @throws SlickException
	 */
	public Character(String file, int width, int height) throws SlickException {
		this.width = width ;	
		this.height = height ;
		direction = Direction.Right;
		spriteSheet = new SpriteSheet(file, this.width, this.height) ;
		speed = 1;
		this.initAnimations() ;
	}
	
	/**
	 * This method initializes the character's animations.
	 * It sets animations in the corresponding field using the sprite sheet given at construction.
	 */
	private void initAnimations() {
		if(this.animations == null) {
			this.animations = new Animation[8] ;
		}
		int nbSprite = spriteSheet.getHorizontalCount() ;
		// Not moving animations
		for(int i = 0 ; i < 4 ; i++) 
			this.animations[i] = loadAnim(0, 1, i) ;
    	// Moving animations
		for(int i = 0 ; i < 4 ; i++) 
			this.animations[i+4] = loadAnim(1, nbSprite, i) ;
	}
	
	/**
	 * This method creates and returns an animation based on the character sprite sheet.
	 * @param startX : first sprite to use in the animation
	 * @param endX : last sprite to use in the animation
	 * @param y : the line of the animation
	 * @return Animation
	 */
    private Animation loadAnim(int startX, int endX, int y) {
    	Animation animation = new Animation() ;
    	for(int x = startX ; x < endX ; x++) {
    		animation.addFrame(spriteSheet.getSprite(x, y), 100);
    	}
    	return animation ;
    }
	
    /**
     * Set the direction that the character is facing
     * @param direction
     */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * Get the direction that the character is facing
	 * @return Direction
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * Get the current animation 
	 * @return
	 */
	public Animation getAnimation() {
		return animations[this.getSpriteIndex() + (this.isMoving() ? 4 : 0)] ;
	}
	
	/**
	 * Get the sprite index in sheet associated with current direction
	 * @return
	 */
	private int getSpriteIndex() {
		if (direction == Direction.Forward) {
			return 2;
		} else if (direction == Direction.Backward) {
			return 0;
		} else if (direction == Direction.Left) {
			return 3;
		} else {
			return 1;
		}		
	}
	
	/**
	 * Get the character's height in pixels
	 * @return int
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Get the character's width in pixels
	 * @return int
	 */
	public int getWidth() {
		return width;
	}
	
	@Override
	public Rectangle getHitbox() {
		return new Rectangle(position.x, position.y, width, height);
	}
	
	/**
	 * Set running to true to add 1 point to character's speed
	 * @param running
	 */
	public void setRunning(boolean running) {
		if (running) {
			speed += 1f;
		} else {
			speed -= 1f;
		}
	}
	
	/**
	 * Increment character's velocity vector X-component based on the given direction and updates character's direction
	 * @param direction
	 */
	public void walk(Character.Direction direction) {
		this.direction = direction;
		if (direction == Character.Direction.Left) {
			velocity.x -= 1f;
		} else if (direction == Character.Direction.Right) {
			velocity.x += 1f;
		}
	}
	
	/**
	 * Decrement character's velocity vector X-component based on the given direction
	 * @param direction
	 */
	public void stopWalking(Character.Direction direction) {
		if (direction == Character.Direction.Left) {
			velocity.x += 1f;
		} else if (direction == Character.Direction.Right) {
			velocity.x -= 1f;
		}
	}
	
	public void jump() {
		if (falling == false) {
			falling = true;
			movement.add(new Vector2f(0, -4f), 150);
		}
	}
	
}
