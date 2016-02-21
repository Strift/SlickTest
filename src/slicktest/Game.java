package slicktest;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
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
	boolean running;
	
	/**
	 * Default constructor
	 * @param title
	 */
	public Game(String title) {
		super(title);
		running = true;
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if (running == false){
			gc.exit();
			return;
		}
		environment.update(delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		this.centerCamera(gc, g);
		environment.render(g);
		g.setColor(Color.red);
		g.drawString("FPS: " + gc.getFPS(), environment.getPlayer().getPosition().x, environment.getPlayer().getPosition().y - 10);
	}
	
	/**
	 * This method translates the graphics using the player location.
	 * Player is always at center of the screen.
	 * @param gc Game Container
	 * @param g Graphics
	 */
	private void centerCamera(GameContainer gc, Graphics g) {
		float cameraX;
		float cameraY;
		
		if (environment.getPlayer().getPosition().x < gc.getWidth()/2) {
			cameraX = 0;
		} else if (environment.getPlayer().getPosition().x > environment.getMap().getWidth() - gc.getWidth()/2) {
			cameraX = environment.getMap().getWidth() - gc.getWidth();
		} else {
			cameraX = environment.getPlayer().getPosition().x - (gc.getWidth() / 2);
		}
		if(environment.getPlayer().getPosition().y < gc.getHeight()/2) {
			cameraY = 0 ;
		} else if (environment.getPlayer().getPosition().y > environment.getMap().getHeight() - gc.getHeight()/2) {
			cameraY = environment.getMap().getHeight() - gc.getHeight();
		} else {
			cameraY = environment.getPlayer().getPosition().y - (gc.getHeight() / 2);
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
    	case Input.KEY_LEFT:
    		environment.getPlayer().walk(Character.Direction.Left);
    		break;
    	case Input.KEY_RIGHT:
    		environment.getPlayer().walk(Character.Direction.Right);
    		break;
    	case Input.KEY_SPACE:
    		environment.getPlayer().jump();
    	case Input.KEY_LSHIFT:
    		environment.getPlayer().setRunning(true);
    		break;
    	}
    };
    
    @Override
    public void keyReleased(int key, char c) {
    	switch (key) {
    	case Input.KEY_LEFT:
    		environment.getPlayer().stopWalking(Character.Direction.Left);
    		break;
    	case Input.KEY_RIGHT:
    		environment.getPlayer().stopWalking(Character.Direction.Right);
    		break;
    	case Input.KEY_SPACE:
    		environment.getPlayer().addMovement(0.f, 5f);
    	case Input.KEY_LSHIFT:
    		environment.getPlayer().setRunning(false);
    		break;
    	case Input.KEY_ESCAPE:
    		running = false;
    		break;
        }
    }

}
