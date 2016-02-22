package slicktest;

import org.newdawn.slick.GameContainer;

public class Camera {
	
	GameContainer gc;
	Environment environment;
	private float x;
	private float y;
	
	public Camera(GameContainer gc) {
		this.gc = gc;
		x = 0;
		y = 0;
	}
	
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
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
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

}
