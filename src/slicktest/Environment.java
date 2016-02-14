package slicktest;

import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Environment extends BasicGame {
	
	private ArrayList<Map> maps ;
	
	private Player player ;
	
	public Environment(String title) {
		super(title);
	}
	
	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		if(player.isMoving()) {
        	if(player.getDirection() == 0) {
        	} else if (player.getDirection() == 1) {
        		if (player.getLocationX() <= maps.get(0).getWidth()*maps.get(0).getTileWidth()-32) 
        			this.player.setLocationX(player.getLocationX()+player.getMoveSpeed());
        	} else if (player.getDirection() == 2) {
        	} else if (player.getDirection() == 3) {
        		if (player.getLocationX() > 1)
        			this.player.setLocationX(player.getLocationX()-player.getMoveSpeed());
        	}
    	}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		this.centerCamera(gc, g);
		this.maps.get(0).render(0, 0);
    	g.drawAnimation(
    		this.player.getAnimations()[player.getDirection() + (this.player.isMoving() ? 4 : 0)], 
			this.player.getLocationX(), 
			this.player.getLocationY()
		);
	}
	
	/**
	 * This method moves all the Graphics using the player location.
	 * Player is always at center of the screen (x axe).
	 * @param gc Game Container
	 * @param g Graphics
	 */
	private void centerCamera(GameContainer gc, Graphics g) {
		int cameraLocation = gc.getWidth() / 2 - this.player.getLocationX() ;
		if(cameraLocation < 0) {
			g.translate(cameraLocation, 0);
		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		this.maps = new ArrayList<Map>();
		this.maps.add(new Map("/maps/map2d.tmx")) ;
		
		this.player = new Player("Martelle", "/images/sonic.png", 24, 32) ;
    	this.player.setLocation(1, gc.getHeight()-8*16);
    	    	
	}
	
    @Override
    public void keyPressed(int key, char c) {
    	if(key == Input.KEY_UP) {
    		this.player.setDirection(0);
    		this.player.setMoving(true);
    	} else if (key == Input.KEY_DOWN) {
    		this.player.setDirection(2) ;
    		this.player.setMoving(true);
    	} else if (key == Input.KEY_LEFT) {
    		this.player.setDirection(3);
    		this.player.setMoving(true);
    	} else if (key == Input.KEY_RIGHT) {
    		this.player.setDirection(1) ;
    		this.player.setMoving(true);
    	} else if (key == Input.KEY_SPACE) {
    		this.player.setMoveSpeed(4);
    	}
    };
    
    @Override
    public void keyReleased(int key, char c) {
        if(key == Input.KEY_UP || key == Input.KEY_DOWN || key == Input.KEY_LEFT || key == Input.KEY_RIGHT) {
        	this.player.setMoving(false);
        } else if (key == Input.KEY_SPACE) {
        	this.player.setMoveSpeed(2);
        }
    }

}
