package slicktest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * This class represents the application.
 * It can be started and run
 * @author Strift
 *
 */
public class Application {
	
	enum State {
		Uninitialized, Initialized, Running, Exiting
	}
	
	protected State state;
	protected String name = "Astralol";
	protected AppGameContainer appgc;
	
	/**
	 * Default constructor
	 */
	public Application() {
		state = State.Uninitialized;
	}
	
	/**
	 * Used to start the program
	 */
	public void start() {
		try {
			if (state != State.Uninitialized) {
				throw new MyException("Application must be uninitialized to start.");
			}
			appgc = new AppGameContainer(new Game(name), 640, 320, false);
			appgc.setTargetFrameRate(60);
	        appgc.setShowFPS(false);
	        state = State.Initialized;
	        this.run();
		} catch (MyException e) {
			e.printStackTrace();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Automatically called by the start method
	 */
	protected void run() {
		try {
			if (state != State.Initialized) {
				throw new MyException("Application must be initialized to run.");
			}
			state = State.Running;
	        appgc.start();
		} catch (MyException e) {
			e.printStackTrace();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Application app = new Application();
		app.start();
	}

}
