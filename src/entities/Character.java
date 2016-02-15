package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * This class represents a character within the game.
 * @author elythiel
 *  
 */
public class Character extends PhysicalEntity {
	
	// Render attributes
	protected SpriteSheet spriteSheet;
	protected Animation[] animations;

	// Physics attributes
	protected boolean moving ;
	protected int direction ;
	protected int moveSpeed ;
	protected int height ;
	protected int width ;
	
	// Specific attributes
	protected String name ;
	
	/**
	 * @param name : character name
	 * @param file : spritesheet file path
	 * @param width : sprites width
	 * @param height : sprites height
	 * @throws SlickException
	 */
	public Character(String name, String file, int width, int height) throws SlickException {
		this.setName(name) ;
		this.width = width ;	
		this.height = height ;
		this.spriteSheet = new SpriteSheet(file, this.width, this.height) ;
		this.setMoving(false) ;
		this.setDirection(1) ;
		this.setMoveSpeed(2);
		this.initAnimations() ;
	}
	
	/**
	 * This method initialize the character animations.
	 * It sets animations in the corresponding field using the spritesheet given at instanciation.
	 */
	public void initAnimations() {
		if(this.animations == null) {
			this.animations = new Animation[8] ;
		}
		int nbSprite = this.getSpriteSheet().getHorizontalCount() ;
		// Not moving animations
		for(int i = 0 ; i < 4 ; i++) 
			this.animations[i] = loadAnim(0, 1, i) ;
    	// Moving animations
		for(int i = 0 ; i < 4 ; i++) 
			this.animations[i+4] = loadAnim(1, nbSprite, i) ;
	}
	
	public Animation[] getAnimations() {
		return this.animations ;
	}
	
	public Animation getAnimation(int i) {
		return this.animations[i] ;
	}
	
	/**
	 * This method create and return an animation based on the character spritesheet.
	 * @param startX : first sprite to use in the animation
	 * @param endX : last sprite to use in the animation
	 * @param y : the line of the animation
	 * @return
	 */
    private Animation loadAnim(int startX, int endX, int y) {
    	Animation animation = new Animation() ;
    	for(int x = startX ; x < endX ; x++) {
    		animation.addFrame(this.getSprite(x, y), 100);
    	}
    	return animation ;
    }
    
    public void render (Graphics g) {
    	g.drawAnimation(animations[direction + (this.isMoving() ? 4 : 0)], position.x, position.y);
    }
	
	public void setDirection(int direction) {
		this.direction = direction ;
	}
	
	public int getDirection() {
		return this.direction ;
	}
	
	/**
	 * This method defines if the character is moving or not.
	 * @param moving
	 */
	public void setMoving(boolean moving) {
		this.moving = moving ;	
	}
	
	public boolean isMoving() {
		return this.moving ;
	}
	
	/**
	 * 
	 * @param path : sprite sheet file path
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public void setSpriteSheet(String path, int x, int y) throws SlickException {
		this.spriteSheet = new SpriteSheet(path, x, y) ;
	}
	
	public Image getSprite(int x, int y) {
		return this.spriteSheet.getSprite(x, y) ;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public SpriteSheet getSpriteSheet() {
		return this.spriteSheet ;
	}
	
	public void setName(String name) {
		this.name = name ;
	}
	
	public String getName() {
		return this.name ;
	}
	
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed ;
	}
	
	public int getMoveSpeed() {
		return this.moveSpeed ;
	}
	
	public void moveForward() {
		this.position.y += 1 ;
	}
	
	public void moveBackward() {
		this.position.y -= 1 ;
	}
	
	public void moveLeft() {
		this.position.x -= 1 ;
	}
	
	public void moveRight() {
		this.position.y += 1 ;
	}
	
}
