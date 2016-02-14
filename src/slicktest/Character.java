package slicktest;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * 
 * This class is the character class. It defines a character with his informations,
 * like current location or direction.
 * 
 * @author elythiel
 *  
 */
public class Character {
	
	private int locationX ;
	private int locationY ;
	private boolean moving ;
	private int direction ;
	private int moveSpeed ;
	private String name ;
	
	private SpriteSheet sprite ;
	
	private Animation animations [] ;
	
	/**
	 * @param name : character name
	 * @param file : spritesheet file path
	 * @param x : location on X axis
	 * @param y : location on Y axis
	 * @throws SlickException
	 */
	public Character(String name, String file, int x, int y) throws SlickException {
		this.setName(name) ;
		this.sprite = new SpriteSheet(file, x, y) ;
		this.setMoving(false) ;
		this.setDirection(1) ;
		this.setMoveSpeed(2);
		this.initAnimations() ;
	}
	
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
	
    private Animation loadAnim(int startX, int endX, int y) {
    	Animation animation = new Animation() ;
    	for(int x = startX ; x < endX ; x++) {
    		animation.addFrame(this.getSprite(x, y), 100);
    	}
    	return animation ;
    }
	
	public void setLocation(int locationX, int locationY) {
		this.locationX = locationX ;
		this.locationY = locationY ;
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
		this.sprite = new SpriteSheet(path, x, y) ;
	}
	
	public Image getSprite(int x, int y) {
		return this.sprite.getSprite(x, y) ;
	}
	
	public SpriteSheet getSpriteSheet() {
		return this.sprite ;
	}
	
	public void setName(String name) {
		this.name = name ;
	}
	
	public String getName() {
		return this.name ;
	}
	
	public void setLocationX(int locationX) {
		this.locationX = locationX ;
	}
	
	public void setLocationY(int locationY) {
		this.locationY = locationY ;
	}
	
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed ;
	}
	
	public int getLocationX() {
		return this.locationX ;
	}
	
	public int getLocationY() {
		return this.locationY ;
	}
	
	public int getMoveSpeed() {
		return this.moveSpeed ;
	}
	
	public void moveForward() {
		this.locationY += 1 ;
	}
	
	public void moveBackward() {
		this.locationY -= 1 ;
	}
	
	public void moveLeft() {
		this.locationX -= 1 ;
	}
	
	public void moveRight() {
		this.locationX += 1 ;
	}
	
}
