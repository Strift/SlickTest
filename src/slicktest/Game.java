package slicktest;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

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
    	if(key == Input.KEY_UP) {
    		environment.getPlayer().setDirection(0);
    		environment.getPlayer().setMoving(true);
    	} else if (key == Input.KEY_DOWN) {
    		environment.getPlayer().setDirection(2) ;
    		environment.getPlayer().setMoving(true);
    	} else if (key == Input.KEY_LEFT) {
    		environment.getPlayer().setDirection(3);
    		environment.getPlayer().setMoving(true);
    	} else if (key == Input.KEY_RIGHT) {
    		environment.getPlayer().setDirection(1) ;
    		environment.getPlayer().setMoving(true);
    	} else if (key == Input.KEY_SPACE) {
    		environment.getPlayer().setSpeed(4);
    	}
    };
    
    @Override
    public void keyReleased(int key, char c) {
        if(key == Input.KEY_UP || key == Input.KEY_DOWN || key == Input.KEY_LEFT || key == Input.KEY_RIGHT) {
        	environment.getPlayer().setMoving(false);
        } else if (key == Input.KEY_SPACE) {
        	environment.getPlayer().setSpeed(2);
        }
    }

}
