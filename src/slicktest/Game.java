package slicktest;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import entities.Character;

/**
 * This class represents the game itself. It is encapsulated within the Application class.
 * It handles the game update, the screen rendering and the user input.
 * @author Strift
 *
 */
public class Game extends BasicGame {
	
	private Environment environment;
	
	/**
	 * Default constructor
	 * @param title
	 */
	public Game(String title) {
		super(title);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		environment.update();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		this.centerCamera(gc, g);
		environment.render(g);
	}
	
	/**
	 * This method translates the graphics using the player location.
	 * Player is always at center of the screen.
	 * @param gc Game Container
	 * @param g Graphics
	 */
	private void centerCamera(GameContainer gc, Graphics g) {
		float cameraX = environment.getPlayer().getPosition().x - (gc.getWidth() / 2) ;
		float cameraY = environment.getPlayer().getPosition().y - (gc.getHeight() / 2) ;
		
		if(cameraX < 0) {
			cameraX = 0;
		}
		if(cameraY < 0) {
			cameraY = 0 ;
		}
		g.translate(-cameraX, -cameraY);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		environment = new Environment();
	}
	
    @Override
    public void keyPressed(int key, char c) {
    	switch (key) {
    	case Input.KEY_UP:
    		environment.getPlayer().setDirection(Character.Direction.Backward);
    		break;
    	case Input.KEY_DOWN:
    		environment.getPlayer().setDirection(Character.Direction.Forward) ;
    		break;
    	case Input.KEY_LEFT:
    		environment.getPlayer().setDirection(Character.Direction.Left);
    		environment.getPlayer().addMovement(-1.f, 0.f);
    		break;
    	case Input.KEY_RIGHT:
    		environment.getPlayer().setDirection(Character.Direction.Right) ;
    		environment.getPlayer().addMovement(1.f, 0.f);
    		break;
    	case Input.KEY_SPACE:
    		environment.getPlayer().setSpeed(4);
    		break;
    	}
    };
    
    @Override
    public void keyReleased(int key, char c) {
    	switch (key) {
    	case Input.KEY_UP:
    		break;
    	case Input.KEY_DOWN:
    		break;
    	case Input.KEY_LEFT:
    		environment.getPlayer().addMovement(1.f, 0.f);
    		break;
    	case Input.KEY_RIGHT:
    		environment.getPlayer().addMovement(-1.f, 0.f);
    		break;
    	case Input.KEY_SPACE:
    		environment.getPlayer().setSpeed(4);
    		break;
        }
    }

}
