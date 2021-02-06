package uk.ac.uos.n6assignment2723297;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import game2D.*;

public class Game extends GameCore {

	/** Screen Width */
	static int screenWidth = 512;

	/** Screen Height */
	static int screenHeight = 384;

	/** Player Sprite */
	Sprite player;

	/** Animation for Idle */
	Animation idleplayer;

	/** Animation for running */
	Animation runningplayer;

	/** Background image */
	Image background;

	/** Middleground image */
	Image middleground;

	/** Tile map */
	TileMap tmap = new TileMap();

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

		background = new ImageIcon("images/background.png").getImage();
		middleground = new ImageIcon("images/middleground.png").getImage();

		// Load the tile map and print it out so we can check it is valid
		tmap.loadMap("maps", "map.txt");

		// Initialise the sprites and animation
		idleplayer = new Animation();
		idleplayer.loadAnimationFromSheet("images/Archer_idle.png", 8, 1, 60);

		runningplayer = new Animation();
		runningplayer.loadAnimationFromSheet("images/Archer_run.png", 8, 1, 60);

		player = new Sprite(idleplayer);

		setSize(tmap.getPixelWidth() / 4, tmap.getPixelHeight());
		setVisible(true);

		initialiseGame();
	}

	/**
	 * You will probably want to put code to restart a game in a separate method so
	 * that you can call it to restart the game.
	 */
	public void initialiseGame() {

		player.setX(20);
		player.setY(250);
		player.setVelocityX(0);
		player.setVelocityY(0);
		player.show();
	}

	/**
	 * Draw the current state of the game
	 */
	public void draw(Graphics2D g) {
		
	    //Drawing the objects
		int xo = 0;
		int yo = 0;

		g.drawImage(background, 0, 0, screenWidth,screenHeight,null, null);
		g.drawImage(middleground, 0, 0, screenWidth,screenHeight,null, null);
		// Apply offsets to player and draw
		player.setOffsets(xo, yo);
		player.draw(g);

		// Apply offsets to tile map and draw it
		tmap.draw(g, xo, yo);

	}

	/**
	 * Update any sprites and check for collisions
	 * 
	 * @param elapsed The elapsed time between this call and the previous call of
	 *                elapsed
	 */
	public void update(long elapsed) {

		// Now update the sprites animation and position
		player.update(elapsed);

	}
}
