package slicktest;

import org.newdawn.slick.GameContainer;

/**
 * This class represents the camera associated with an environment
 * @author Strift
 *
 */
public class Camera {
	
	GameContainer gc;
	Environment environment;
	private float x;
	private float y;
	
	/**
	 * Constructor takes in parameter the game container
	 * @param gc
	 */
	public Camera(GameContainer gc) {
		this.gc = gc;
		x = 0;
		y = 0;
	}
	
	/**
	 * Set the environment the camera is associated with
	 * @param environment
	 */
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	/**
	 * Center the camera around the player of the environment
	 */
	public void center() {
		// X axis centering
		if (environment.getPlayer().getPosition().x < gc.getWidth()/2) {
			x = 0;
		} else if (environment.getPlayer().getPosition().x > environment.getMap().getWidth() - gc.getWidth()/2) {
			x = environment.getMap().getWidth() - gc.getWidth();
		} else {
			x = environment.getPlayer().getPosition().x - (gc.getWidth() / 2);
		}
		// Y axis centering
		if(environment.getPlayer().getPosition().y < gc.getHeight()/2) {
			y = 0 ;
		} else if (environment.getPlayer().getPosition().y > environment.getMap().getHeight() - gc.getHeight()/2) {
			y = environment.getMap().getHeight() - gc.getHeight();
		} else {
			y = environment.getPlayer().getPosition().y - (gc.getHeight() / 2);
		}
	}
	
	/**
	 * Get the camera position on X axis
	 * @return float
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Get the camera position on Y axis
	 * @return float
	 */
	public float getY() {
		return y;
	}

}
