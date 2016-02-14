package slicktest;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * 
 * This class is the character classe. It defines a character with his informations,
 * like current location or direction.
 * 
 * @author elythiel
 *  
 */
public class Character {
	
	private int locationX ;
	private int locationY ;
	private int moveSpeed ;
	private String name ;
	private SpriteSheet sprite ;
	private boolean moving ;
	private int direction ;
	
	public Character(String name, String file, int x, int y) throws SlickException {
		this.setName(name) ;
		this.sprite = new SpriteSheet(file, x, y) ;
		this.setMoving(false) ;
		this.setDirection(0) ;
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
	 * This method define if the character is moving or not.
	 * @param moving
	 */
	public void setMoving(boolean moving) {
		this.moving = moving ;	
	}
	
	public boolean isMoving() {
		return this.moving ;
	}

	public void setSpriteSheet(String file, int x, int y) throws SlickException {
		this.sprite = new SpriteSheet(file, x, y) ;
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
