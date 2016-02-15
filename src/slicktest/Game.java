package slicktest;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {
	
	private Environment environment;
	
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
		
//		environment.getMaps().get(0).getTiledMap().render(0, 0); // this will change once the class will be renderable
		drawMap(g) ;
	}
	
	private void drawMap(Graphics g) {
		for (int i = 0 ; i < environment.getMaps().get(0).getTiledMap().getLayerCount() ; i++) {
			environment.getMaps().get(0).getTiledMap().render(0, 0, i);
			if(environment.getMaps().get(0).getTiledMap().getLayerProperty(i, "level", "none").equals("0")) {
				drawPlayer(g) ;
			}
		}
	}
	
	private void drawPlayer(Graphics g) {
		g.drawAnimation(
	    	environment.getPlayer().getAnimations()[environment.getPlayer().getDirection() + (environment.getPlayer().isMoving() ? 4 : 0)], 
			environment.getPlayer().getLocationX(), 
			environment.getPlayer().getLocationY()
		);
	}
	
	/**
	 * This method moves all the Graphics using the player location.
	 * Player is always at center of the screen (x axe).
	 * @param gc Game Container
	 * @param g Graphics
	 */
	private void centerCamera(GameContainer gc, Graphics g) {
		int playerLocation = environment.getPlayer().getLocationX();
		int mapSize = environment.getMaps().get(0).getRealWidth();
		int screenSize = gc.getWidth();
		int cameraLocation = playerLocation - screenSize / 2;
		if(cameraLocation < 0) {
			cameraLocation = 0;
		}
		g.translate(-cameraLocation, 0);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
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
    		environment.getPlayer().setMoveSpeed(4);
    	}
    };
    
    @Override
    public void keyReleased(int key, char c) {
        if(key == Input.KEY_UP || key == Input.KEY_DOWN || key == Input.KEY_LEFT || key == Input.KEY_RIGHT) {
        	environment.getPlayer().setMoving(false);
        } else if (key == Input.KEY_SPACE) {
        	environment.getPlayer().setMoveSpeed(2);
        }
    }

}
