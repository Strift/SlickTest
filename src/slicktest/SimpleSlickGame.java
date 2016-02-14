package slicktest;


import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class SimpleSlickGame extends BasicGame
{
	
	private TiledMap currentMap ;
	private Player player ;
	private Animation playerAnims[] ;
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
    public void init(GameContainer container) throws SlickException {
    	this.currentMap  = new TiledMap("/maps/1.tmx") ;

    	this.player = new Player("Martelle", "/images/sonic.png", 24, 32) ;
    	this.player.setLocation(0, 320);
    	
    	this.playerAnims = new Animation[8] ;
    	this.playerAnims[0] = loadAnim(0, 1, 0) ;
    	this.playerAnims[1] = loadAnim(0, 1, 1) ;
    	this.playerAnims[2] = loadAnim(0, 1, 2) ;
    	this.playerAnims[3] = loadAnim(0, 1, 3) ;
    	this.playerAnims[4] = loadAnim(1, 2, 0) ;
    	this.playerAnims[5] = loadAnim(1, 2, 1) ;
    	this.playerAnims[6] = loadAnim(1, 2, 2) ;
    	this.playerAnims[7] = loadAnim(1, 2, 3) ;
    	
    }
    
    private Animation loadAnim(int startX, int endX, int y) {
    	Animation animation = new Animation() ;
    	for(int x = startX ; x < endX ; x++) {
    		animation.addFrame(player.getSprite(x, y), 500);
    	}
    	return animation ;
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	this.currentMap.render(0, 0) ;
    	g.drawAnimation(this.playerAnims[player.getDirection() + (player.isMoving() ? 4 : 0)], player.getLocationX(), player.getLocationY());
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	if(player.isMoving()) {
        	if(player.getDirection() == 0) {
        		this.player.setLocationY(player.getLocationY()-1);
        	} else if (player.getDirection() == 1) {
        		this.player.setLocationX(player.getLocationX()+1);
        	} else if (player.getDirection() == 2) {
        		this.player.setLocationY(player.getLocationY()+1);
        	} else if (player.getDirection() == 3) {
        		this.player.setLocationX(player.getLocationX()-1);

        	}
    	}
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
    	}
    };
    
    @Override
    public void keyReleased(int key, char c) {
        if(key == Input.KEY_UP || key == Input.KEY_DOWN || key == Input.KEY_LEFT || key == Input.KEY_RIGHT) {
        	this.player.setMoving(false);
        }
    }
        
    public static void main(String[] args) {
    	AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(
				new SimpleSlickGame("SlickTest"), 640, 360, false
			);
			appgc.setTargetFrameRate(60);
	        appgc.setShowFPS(false);
	        appgc.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
