package uk.ac.uos.n6assignment2723297;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

import game2D.*;


public class Game extends GameCore {

	/** Screen Width */
	static int screenWidth = 512;

	/** Screen Height */
	static int screenHeight = 384;

	/** Player Sprite */
	Sprite player;

	/** Animation for right Idle */
	Animation idlerightplayer;
	
	/** Animation for right Idle */
	Animation idleleftplayer;

	/** Animation for running right */
	Animation runrightplayer;

	/** Animation for running left */
	Animation runleftplayer;
	
	/** Animation for jumping right */
	Animation jumprightplayer;
	
	/** Animation for jumping left */
	Animation jumpleftplayer;
	
	/**Animation Attack rigth*/
	Animation attackrightplayer;
	
	/**Animation Attack left*/
	Animation attackleftplayer;
	
	/**Animation Attack left*/
	Animation dierightplayer;
	
	/**Animation Attack left*/
	Animation dieleftplayer;
	

	/** Background image */
	Image background;

	/** Middleground image */
	Image middleground;

	/** Tile map */
	TileMap tmap = new TileMap();
    
	/**Boolean for left position*/
	Boolean left=false;
	
	/**Boolean for right position*/
	Boolean right=false;
	
	/** Boolean for running left */
	Boolean run = false;

	/** Boolean for idle */
	Boolean idle = true;
	
	/**Boolean jump*/
	Boolean jump=false;
	
	/**Boolean Attack*/
	Boolean attack=false;

	/**
	 * The obligatory main method that creates an instance of our class and starts
	 * it running
	 * 
	 * @param args The list of parameters this program might use (ignored)
	 */
	public static void main(String[] args) {
		Game gct = new Game();
		gct.init();
		// Start in windowed mode with the given screen height and width
		gct.run(false, screenWidth, screenHeight);
	}

	/**
	 * Initialise the class, e.g. set up variables, load images, create animations,
	 * register event handlers
	 */
	public void init() {
		/** Temporary reference for sprite */
		Sprite s;
		
		// Example of playing a sound as a thread
		//Sound backgroundmusic= new Sound("sounds/Backgroundmusic.wav");
		//backgroundmusic.start();

		loadImages();

		// Load the tile map and print it out so we can check it is valid
		tmap.loadMap("maps", "map.txt");

		// Initialise the sprite and animation
		loadAnimation();
		
		
		player = new Sprite(idlerightplayer);
      
		setSize(tmap.getPixelWidth() / 4, tmap.getPixelHeight());
		setVisible(true);

		initialiseGame();
	}
	/**
	 * Load the images for the game
	 */
	public void loadImages()
	{
		background = new ImageIcon("images/background.png").getImage();
		middleground = new ImageIcon("images/middleground.png").getImage();
	}
	/**
	 * Load the animation for the game
	 */
	public void loadAnimation()
	{
		// Initialise the sprites and animation
		idlerightplayer = new Animation();
		idlerightplayer.loadAnimationFromSheet("images/gothic-hero-idle.png", 4, 1, 60);
				
		idleleftplayer = new Animation();
		idleleftplayer.loadAnimationFromSheet("images/gothic-hero-idle-left.png", 4, 1, 60);

		runrightplayer = new Animation();
		runrightplayer.loadAnimationFromSheet("images/gothic-hero-run.png", 12, 1, 60);

		runleftplayer = new Animation();
		runleftplayer.loadAnimationFromSheet("images/gothic-hero-run-left.png", 12, 1, 60);
				
		jumprightplayer=new Animation();
		jumprightplayer.loadAnimationFromSheet("images/gothic-hero-jump.png",5,1,60);
				
		jumpleftplayer=new Animation();
		jumpleftplayer.loadAnimationFromSheetOpposite("images/gothic-hero-jump-left.png",5,1,60);
				
	    attackrightplayer=new Animation();
		attackrightplayer.loadAnimationFromSheet("images/gothic-hero-attack.png",6,1,30);
				
		attackleftplayer=new Animation();
		attackleftplayer.loadAnimationFromSheetOpposite("images/gothic-hero-attack-left.png",6,1,30);
		
		dierightplayer=new Animation();
		dierightplayer.loadAnimationFromSheet("images/gothic-hero-die.png",3,1,60);
		
		dieleftplayer=new Animation();
		dieleftplayer.loadAnimationFromSheetOpposite("images/gothic-hero-die-left.png",3,1,60);
		
	}

	/**
	 * You will probably want to put code to restart a game in a separate method so
	 * that you can call it to restart the game.
	 */
	public void initialiseGame() {

		player.setX(20);
		player.setY(275);
		player.setVelocityX(0);
		player.setVelocityY(0);
		player.show();
	}

	/**
	 * Draw the current state of the game
	 */
	public void draw(Graphics2D g) {

		// Drawing the objects
		int xo = 0;
		int yo = 0;

		g.drawImage(background, 0, 0, screenWidth, screenHeight, null, null);
		g.drawImage(middleground, 0, 0, screenWidth, screenHeight, null, null);
		// Apply offsets to player and draw
		player.setOffsets(xo, yo);
		player.draw(g);
        
		// Apply offsets to tile map and draw it
		tmap.draw(g, xo, yo);
		player.drawBoundingBox(g);

	}

	/**
	 * Update any sprites and check for collisions
	 * 
	 * @param elapsed The elapsed time between this call and the previous call of
	 *                elapsed
	 */
	public void update(long elapsed) {
       
		
		setMovement();
		
		// Now update the sprites animation and position
		player.update(elapsed);
		// Then check for any collisions that may have occurred
        //handleScreenEdge(player, tmap, elapsed);
        checkTileCollision(player, tmap);
        

	}

	/**
	 * Set the position movement and change the animation
	 * Make it a new method so it's does not overcrowed update 
	 */
	public void setMovement()
	{
		if(left)
		{
			if(run)
			{
				player.setX(player.getX() - 2);
				player.setAnimation(runleftplayer);
			}
			if(attack)
			{
				player.setAnimation(attackleftplayer);
			}
			if(idle)
			{
				player.setAnimation(idleleftplayer);
			}
			if(jump)
			{
				player.setAnimation(jumpleftplayer);
				player.setY(player.getY()-5);
	       		player.setY(player.getY()+5);
	       		
			}
		}
		
		else if(right)
		{
			if(run)
			{
				player.setX(player.getX() + 2);
				player.setAnimation(runrightplayer);
			}
			if(attack)
			{
				player.setAnimation(attackrightplayer);
			}
			if(idle)
			{
				player.setAnimation(idlerightplayer);
			}
			if(jump)
			{
				player.setAnimation(jumprightplayer);
				player.setY(player.getY()-2);
	       		player.setY(player.getY()+2);
	       		
			}
		}
		
	}
	/**
	 * Key Pressed method
	 */
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		switch (key) {

		case KeyEvent.VK_ESCAPE:
			stop();
			break;

		case KeyEvent.VK_LEFT:
			left=true;
			right=false;
			run=true;
			idle = false;
			break;

		case KeyEvent.VK_RIGHT:
			right=true;
			left=false;
			run = true;
			idle = false;
			break;
			
		case KeyEvent.VK_UP:
			jump=true;
			idle = false;
			break;
		
		case KeyEvent.VK_X:
			attack=true;
			idle = false;
			break;

		default:
			break;
		}
	}

	/**
	 * Key Released method
	 */
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_ESCAPE:
			stop();
			break;

		case KeyEvent.VK_LEFT:
			run=false;
			idle = true;
			break;

		case KeyEvent.VK_RIGHT:
			run=false;
			idle = true;
			break;
			
		case KeyEvent.VK_X:
			attack=false;
			idle =true;
			break;
			
		case KeyEvent.VK_UP:
			jump=false;
			idle = true;
			break;

		default:
			break;
		}
	}
	
	/**
     * Checks and handles collisions with the edge of the screen
     * 
     * @param s			The Sprite to check collisions for
     * @param tmap		The tile map to check 
     * @param elapsed	How much time has gone by since the last call
     */
    public void handleScreenEdge(Sprite s, TileMap tmap, long elapsed)
    {
    	// This method just checks if the sprite has gone off the bottom screen.
    	// Ideally you should use tile collision instead of this approach
    	
        if (s.getY() + s.getHeight() > tmap.getPixelHeight())
        {
        	// Put the player back on the map 1 pixel above the bottom
        	s.setY(tmap.getPixelHeight() - s.getHeight() - 1); 
        	
        	// and make them bounce
        	s.setVelocityY(-s.getVelocityY());
        }
    }
    
    public void checkTileCollision(Sprite s, TileMap tmap)
    {
    	// Take a note of a sprite's current position
    	float sx = s.getX();
    	float sy = s.getY();
    	
    	
    	// Find out how wide and how tall a tile is
    	float tileWidth = tmap.getTileWidth();
    	float tileHeight = tmap.getTileHeight();
    	
    	// Divide the sprites x coordinate by the width of a tile, to get
    	// the number of tiles across the x axis that the sprite is positioned at 
    	int	xtile = (int)(sx / tileWidth);
    	System.out.println(xtile);
    	// The same applies to the y coordinate
    	int ytile = (int)(sy / tileHeight);
    	System.out.println(ytile);
    	// What tile character is at the top left of the sprite s?
    	char ch = tmap.getTileChar(xtile, ytile);
    	System.out.println(ch);
    	
    	if (ch != '.') // If it's not a dot (empty space), handle it
    	{
    		// Here we just stop the sprite. 
           s.setX(s.getX()-2);
    		
    		// You should move the sprite to a position that is not colliding
    	}
    	
    	// We need to consider the other corners of the sprite
    	// The above looked at the top left position, let's look at the bottom left.
    	xtile = (int)(sx / tileWidth);
    	ytile = (int)((sy + s.getHeight())/ tileHeight);
    	ch = tmap.getTileChar(xtile, ytile);
    	
    	// If it's not empty space
     	if (ch != '.') 
    	{
    		// Let's make the sprite bounce
    		s.stop();
    		
    	}
     	
    }
   
    public boolean boundingBoxCollision(Sprite s1, Sprite s2)
    {
    	return false;   	
    }
    
}
