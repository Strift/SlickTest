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
public class Character extends PhysicalEntity implements IMoveable {
	
	public enum Direction {
		Forward, Backward, Left, Right
	}

	// Physics attributes
	protected float speed;
	protected boolean falling;
	protected int height;
	protected int width;
	protected Direction direction;
	protected boolean walkingLeft;
	protected boolean walkingRight;
	protected Vector2f baseVelocity;
	
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
		super();
		speed = 1f;
		falling = false;
		this.width = width ;	
		this.height = height ;
		direction = Direction.Right;
		walkingLeft = false;
		walkingRight = false;
		baseVelocity = new Vector2f();
		spriteSheet = new SpriteSheet(file, this.width, this.height);
		this.initAnimations();
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
	
	@Override
	public Rectangle getHitbox() {
		return new Rectangle(position.x, position.y - height, width, height);
	}
	
	private Vector2f getVelocity() {
		Vector2f velocity = baseVelocity.copy();
		if (falling == false) {
			if (walkingLeft) {
				velocity.x -= 1f;
			}
			if (walkingRight) {
				velocity.x += 1f;
			}
			velocity.x *= speed;
		}
		return velocity;		
	}
	
	/**
	 * Return true if the character is walking left or right
	 * @return boolean
	 */
	public boolean isWalking() {
		return (walkingLeft || walkingRight);
	}
	
	/**
	 * Return true if the character is falling
	 * @return boolean
	 */
	public boolean isFalling() {
		return falling;
	}
	
	/**
	 * Get the current animation 
	 * @return
	 */
	public Animation getAnimation() {
		return animations[this.getSpriteIndex() + (this.isWalking() ? 4 : 0)] ;
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
		} else { // Right
			return 1;
		}		
	}
	
	/**
	 * Enable walking in given direction
	 * @param direction
	 */
	public void startWalking(Character.Direction direction) {
		this.direction = direction;
		if (direction == Character.Direction.Left) {
			walkingLeft = true;
		} else if (direction == Character.Direction.Right) {
			walkingRight = true;
		}
	}
	
	/**
	 * Disable walking in given direction
	 * @param direction
	 */
	public void stopWalking(Character.Direction direction) {
		if (direction == Character.Direction.Left) {
			walkingLeft = false;
		} else if (direction == Character.Direction.Right) {
			walkingRight = false;
		}
	}
	
	/**
	 * Make the character jump if possible
	 */
	public void jump() {
		if (falling == false) {
			forces.add(new Vector2f(0f, -6f), 150);
			baseVelocity.add(this.getVelocity());
			falling = true;
		}
	}
	
	/**
	 * This method is called when the character has landed
	 */
	public void hasLanded() {
		baseVelocity.x = 0;
		baseVelocity.y = 0;
		falling = false;
	}
	
	@Override
	public Vector2f nextPosition(int delta) {
		Vector2f newPos = position.copy();
		float gravityForce = 0;
		
		// Enable gravity if character is falling but has already took off
		if (falling /*&& this.touchesGround() == false*/) {
			gravityForce = PhysicalEntity.GRAVITY_FORCE;
		}
		// Movement result
		forces.update(delta);
		Vector2f result = forces.result();
		Vector2f velocity = this.getVelocity();
		// New position calculation
		newPos.x += (velocity.x + result.x) * delta/5;
		newPos.y += (velocity.y + result.y + gravityForce) * delta/5;
		return newPos;
	}
	
}
