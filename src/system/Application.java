package system;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import slicktest.Game;

/**
 * This class represents the application.
 * It can be started and run
 * @author Strift
 *
 */
public class Application {

	public final static int FRAME_RATE = 60;
	
	enum State {
		Uninitialized, Initialized, Running, Exiting
	}
	
	private State state;
	private String name = "Astralol";
	private AppGameContainer appgc;
	
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
				throw new Error("Application must be uninitialized to start.");
			}
			appgc = new AppGameContainer(new Game(name), 640, 320, false);
			appgc.setTargetFrameRate(FRAME_RATE);
	        appgc.setShowFPS(false);
	        state = State.Initialized;
	        this.run();
		} catch (Error e) {
			e.printStackTrace();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Automatically called by the start method
	 */
	private void run() {
		try {
			if (state != State.Initialized) {
				throw new Error("Application must be initialized to run.");
			}
			state = State.Running;
	        appgc.start();
	        state = State.Exiting;
		} catch (Error e) {
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
