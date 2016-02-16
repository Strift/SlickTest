package entities;

import org.newdawn.slick.SlickException;

/**
 * This class extends the Character class
 * It defines a character that can be controlled through user input
 * @author Strift
 *
 */
public class Player extends Character {
	
	public Player(String file, int x, int y) throws SlickException {
		super(file, x, y) ;
	}
	
}
