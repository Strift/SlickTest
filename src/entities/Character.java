package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

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
		this.spriteSheet = new SpriteSheet(file, this.width, this.height) ;
		this.setDirection(Direction.Right) ;
		this.setSpeed(2);
		this.initAnimations() ;
	}
	
	/**
	 * This method initializes the character's animations.
	 * It sets animations in the corresponding field using the spritesheet given at instanciation.
	 */
	public void initAnimations() {
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
	 * Get the animation animation associated with index
	 * @param index
	 * @return
	 */
	public Animation getAnimation(int index) {
		return this.animations[index] ;
	}
	
	/**
	 * This method creates and returns an animation based on the character sprite sheet.
	 * @param startX : first sprite to use in the animation
	 * @param endX : last sprite to use in the animation
	 * @param y : the line of the animation
	 * @return
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
	 * Get the sprite index in sheet associated with current direction
	 * @return
	 */
	public int getSpriteIndex() {
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
	
	@Override
	public Rectangle getHitbox() {
		return new Rectangle(position.x, position.y, width, height);
	}
	
}
