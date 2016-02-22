package slicktest;

/**
 * This class represents the camera associated with an environment
 * @author Strift
 *
 */
public class Camera {
	
	private int screenWidth;
	private int screenHeight;
	private Environment environment;
	private float x;
	private float y;
	private boolean horizontalCentering;
	private boolean verticalCentering;
	
	/**
	 * Constructor takes in parameter the game container
	 * @param gc
	 */
	public Camera(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		x = 0;
		y = 0;
		horizontalCentering = true;
		verticalCentering = true;
	}
	
	/**
	 * Set the environment the camera is associated with
	 * @param environment
	 */
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	/**
	 * Enable/disable vertical centering
	 * @param following
	 */
	public void setVerticalCentering(boolean following) {
		verticalCentering = following;
	}
	
	/**
	 * Center the camera around the player of the environment
	 */
	public void center() {
		// X axis centering
		if (horizontalCentering) {
			if (environment.getPlayer().getPosition().x < screenWidth/2) {
				x = 0;
			} else if (environment.getPlayer().getPosition().x > environment.getMap().getWidth() - screenWidth/2) {
				x = environment.getMap().getWidth() - screenWidth;
			} else {
				x = environment.getPlayer().getPosition().x - (screenWidth/2);
			}
		}
		// Y axis centering
		if (verticalCentering) {
			if(environment.getPlayer().getPosition().y < screenHeight/2) {
				y = 0 ;
			} else if (environment.getPlayer().getPosition().y > environment.getMap().getHeight() - screenHeight/2) {
				y = environment.getMap().getHeight() - screenHeight;
			} else {
				y = environment.getPlayer().getPosition().y - (screenHeight/2);
			}
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
