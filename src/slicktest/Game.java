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
	Camera camera;
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
	public void init(GameContainer gc) throws SlickException {
		environment = new Environment();
		camera = new Camera(gc);
		camera.setEnvironment(environment);
		camera.setVerticalCentering(false);
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
		camera.center();
		g.translate(-camera.getX(), -camera.getY());
		environment.render(g);
		g.setColor(Color.red);
		g.drawString("FPS: " + gc.getFPS(), environment.getPlayer().getPosition().x, environment.getPlayer().getPosition().y - 10);
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
    		break;
    	case Input.KEY_LSHIFT:
    		environment.getPlayer().setRunning(true);
    		break;
    	case Input.KEY_ESCAPE:
    		running = false;
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
    	case Input.KEY_LSHIFT:
    		environment.getPlayer().setRunning(false);
    		break;
        }
    }

}
