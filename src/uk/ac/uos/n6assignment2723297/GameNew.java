package uk.ac.uos.n6assignment2723297;

import java.awt.*;

import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import game2D.*;
/**
 * Code for the N6 assignment
 * @author 2723297
 *
 */
public class GameNew extends GameCore {

	/** Screen Width */
	static int screenWidth = 1024;

	/** Screen Height */
	static int screenHeight = 576;

	/** Player Sprite */
	Sprite player;

	/** Sprite for the turtle */
	Sprite turtle;

	/** Sprite for the apples */
	Sprite apple;

	/** Sprite for the bananas */
	Sprite banana;

	/** Sprite for the pigs */
	Sprite pig;

	/** Sprite for the bats */
	Sprite bat;

	/** Sprite for the rock */
	Sprite rock;

	/** Sprite for the tresure */
	Sprite tresure;

	/** Sprite for the apples at level2 */
	Sprite apple2;

	/** Sprite for the bananas at level2 */
	Sprite banana2;

	/** Sprite for the cave */
	Sprite cave;

	/** Animation for right Idle */
	Animation idlerightplayer;

	/** Animation for right Idle */
	Animation idleleftplayer;

	/** Animation for running right */
	Animation runrightplayer;

	/** Animation for running left */
	Animation runleftplayer;

	/** Animation for rage run left */
	Animation ragerunleft;

	/** Animation for rage run right */
	Animation ragerunright;

	/** Animation for rage idle left */
	Animation rageidleleft;

	/** Animation for rage idle right */
	Animation rageidleright;

	/** Animation for the turtle idle */
	Animation turtleidle;

	/** Animation for the turtle spike out */
	Animation turtlespikeout;

	/** Animation for the turtle spike in */
	Animation turtlespikein;

	/** Animation for the turtle idle with spike out */
	Animation turtleidlespike;

	/** Animation for the pig run left */
	Animation pigrunleft;

	/** Animation for the pig run right */
	Animation pigrunright;

	/** Animation for the pig idle left */
	Animation pigidleleft;

	/** Animation for the pig idle right */
	Animation pigidleright;

	/** Animation for apple */
	Animation applean;

	/** Animation for banana */
	Animation bananaan;

	/** Animation for the cave */
	Animation Cave;

	/** Animation for the bat flying left */
	Animation batflyingleft;

	/** Animation for the bat flying right */
	Animation batflyingright;

	/** Animation for the rock idle left */
	Animation rockidleleft;

	/** Animation for the rock idle right */
	Animation rockidleright;

	/** Animation for the rock run left */
	Animation rockrunleft;

	/** Animation for the rock run right */
	Animation rockrunright;

	/** Animation for the treasure chest */
	Animation tresurechest;

	/** Background music */
	MusicPlay background;

	/** Sound for Jumping */
	Sound jumping;

	/** Sound for player getting hit */
	Sound hit;

	/** Sound for player eating food */
	Sound food;

	/** Sound for player turning in rage mode */
	Sound ragesound;

	/** Sound for enemies getting hit */
	Sound enemyhit;

	/** Sound with filter for player eating food in the cave */
	SoundFilterPlay foodcave;

	/** Sound with filter for player hitting enemy in the cave */
	SoundFilterPlay hitcave;

	/** Sound with filter for openinf the treasure chest */
	SoundFilterPlay treasureSound;

	/** Tile map level 1 */
	TileMap tmap_level1 = new TileMap();

	/** Tile map level 2 */
	TileMap tmap_level2 = new TileMap();

	/** Boolean for left position */
	Boolean left = false;

	/** Boolean for right position */
	Boolean right = false;

	/** Boolean for idle */
	Boolean idle = true;

	/** Boolean jump */
	Boolean jump = false;

	/** Boolean rage */
	Boolean rage = false;

	/** Boolean reset */
	Boolean reset = false;
	
	/**music boolean*/
	Boolean music=false;

	/** X Velocity */
	float xVelocity = 0.2f;

	/** Gravity applied to sprites */
	float gravity = 0.0006f;

	/** Life */
	int life = 5;

	/** Score for player hitted */
	int score = 0;

	/** Value that keep track of the level */
	int level = 0;

	/** Start rage timer */
	long startRage;

	/** Imagine for the mountain */
	Image mountain;

	/** Imagine for the sky */
	Image sky;

	/** Imagine for the heart */
	Image heart;

	/** Imagine for the cave background 1 */
	Image cave_bck1;

	/** Imagine for the cave background 2 */
	Image cave_bck2;

	/** Imagine for the cave background 3 */
	Image cave_bck3;

	/** Imagine for the cave background 4 */
	Image cave_bck4;

	/** Imagine for the starting menu */
	Image startMenu;

	/** Array that store the turtles sprites */
	ArrayList<Sprite> turtles;

	/** Array that store the pigs sprites */
	ArrayList<Sprite> pigs;

	/** Array that store the apples sprites */
	ArrayList<Sprite> apples;

	/** Array that store the bananas sprites */
	ArrayList<Sprite> bananas;

	/** Array that store the bats sprites */
	ArrayList<Sprite> bats;

	/** Array that store the rocks sprites */
	ArrayList<Sprite> rocks;

	/** Array that store the bananas sprites at level 2 */
	ArrayList<Sprite> bananas2;

	/** Array that store the apples sprites at level 2 */
	ArrayList<Sprite> apples2;

	/**
	 * The obligatory main method that creates an instance of our class and starts
	 * it running
	 * 
	 * @param args The list of parameters this program might use (ignored)
	 */
	public static void main(String[] args) {
		GameNew gct = new GameNew();
		gct.init();
		// Start in windowed mode with the given screen height and width
		gct.run(false, screenWidth, screenHeight);
	}

	/**
	 * Method that initialise all the content
	 */
	public void init() {

		// close the program on exit
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// start the background music
		background = new MusicPlay("sounds/backgroundmusic.wav");
		background.start();
        
		// Load the tile maps
		tmap_level1.loadMap("maps", "map-level1.txt");

		tmap_level2.loadMap("maps", "map-level2.txt");

		// Initialise the sprite and animation
		loadAnimation();

		turtles = new ArrayList<Sprite>();

		pigs = new ArrayList<Sprite>();

		bananas = new ArrayList<Sprite>();

		apples = new ArrayList<Sprite>();

		bananas2 = new ArrayList<Sprite>();

		apples2 = new ArrayList<Sprite>();

		bats = new ArrayList<Sprite>();

		rocks = new ArrayList<Sprite>();

		player = new Sprite(idlerightplayer);

		cave = new Sprite(Cave);

		bat = new Sprite(batflyingleft);

		rock = new Sprite(rockidleleft);

		tresure = new Sprite(tresurechest);

		// Initialise all the images

		sky = new ImageIcon("images/sky_cloud.png").getImage();

		mountain = new ImageIcon("images/mountain2.png").getImage();

		heart = new ImageIcon("images/pixel-heart.png").getImage();

		cave_bck1 = new ImageIcon("images/background1.png").getImage();

		cave_bck2 = new ImageIcon("images/background2.png").getImage();

		cave_bck3 = new ImageIcon("images/background3.png").getImage();

		cave_bck4 = new ImageIcon("images/background4a.png").getImage();

		startMenu = new ImageIcon("images/start.png").getImage();

		// Set the game size
		setSize(screenWidth, screenHeight);

		// Set the visibility to true
		setVisible(true);

		// Initialise the Game
		initialiseGame();

	}

	/**
	 * Initialise all the animation
	 */
	public void loadAnimation() {

		// Initialise the animation for the player
		idlerightplayer = new Animation();
		idlerightplayer.loadAnimationFromSheet("images/idle-right.png", 11, 1, 60);

		idleleftplayer = new Animation();
		idleleftplayer.loadAnimationFromSheetOpposite("images/idle-left.png", 11, 1, 60);

		runrightplayer = new Animation();
		runrightplayer.loadAnimationFromSheet("images/run-right.png", 12, 1, 60);

		runleftplayer = new Animation();
		runleftplayer.loadAnimationFromSheetOpposite("images/run-left.png", 12, 1, 60);

		ragerunleft = new Animation();
		ragerunleft.loadAnimationFromSheetOpposite("images/rage-run-left.png", 12, 1, 60);

		ragerunright = new Animation();
		ragerunright.loadAnimationFromSheet("images/rage-run-right.png", 12, 1, 60);

		rageidleleft = new Animation();
		rageidleleft.loadAnimationFromSheetOpposite("images/rage-idle-left.png", 11, 1, 60);

		rageidleright = new Animation();
		rageidleright.loadAnimationFromSheet("images/rage-idle-right.png", 11, 1, 60);

		// Initialise animation for the turtles
		turtleidle = new Animation();
		turtleidle.loadAnimationFromSheet("images/turtleidle.png", 14, 1, 500);

		turtleidlespike = new Animation();
		turtleidlespike.loadAnimationFromSheet("images/turtleidlespike.png", 14, 1, 500);

		turtlespikein = new Animation();
		turtlespikein.loadAnimationFromSheet("images/turtlespikein.png", 8, 1, 500);

		turtlespikeout = new Animation();
		turtlespikeout.loadAnimationFromSheet("images/turtlespikeout.png", 8, 1, 500);

		// Initialise Animation for the pigs
		pigidleleft = new Animation();
		pigidleleft.loadAnimationFromSheet("images/pig-idle-left.png", 9, 1, 500);

		pigidleright = new Animation();
		pigidleright.loadAnimationFromSheetOpposite("images/pig-idle-right.png", 9, 1, 500);

		pigrunleft = new Animation();
		pigrunleft.loadAnimationFromSheet("images/pig-run-left.png", 12, 1, 200);

		pigrunright = new Animation();
		pigrunright.loadAnimationFromSheetOpposite("images/pig-run-right.png", 12, 1, 200);

		// Apple animation
		applean = new Animation();
		applean.loadAnimationFromSheet("images/Apple.png", 17, 1, 500);

		// Banana animation
		bananaan = new Animation();
		bananaan.loadAnimationFromSheet("images/Bananas.png", 17, 1, 500);

		// Cave animation
		Cave = new Animation();
		Cave.loadAnimationFromSheet("images/cave-pixel.png", 1, 1, 500);

		// Initialise Animation for the bats
		batflyingleft = new Animation();
		batflyingleft.loadAnimationFromSheet("images/bat-flying-left.png", 7, 1, 250);

		batflyingright = new Animation();
		batflyingright.loadAnimationFromSheetOpposite("images/bat-flying-right.png", 7, 1, 250);

		// Initialise Animation for the rocks
		rockidleleft = new Animation();
		rockidleleft.loadAnimationFromSheet("images/rock-idle-left.png", 14, 1, 500);

		rockidleright = new Animation();
		rockidleright.loadAnimationFromSheetOpposite("images/rock-idle-left.png", 14, 1, 500);

		rockrunleft = new Animation();
		rockrunleft.loadAnimationFromSheet("images/rock-run-left.png", 14, 1, 300);

		rockrunright = new Animation();
		rockrunright.loadAnimationFromSheetOpposite("images/rock-run-right.png", 14, 1, 300);

		// Treasure animation
		tresurechest = new Animation();
		tresurechest.loadAnimationFromSheet("images/treasure.png", 1, 1, 500);
	}

	/**
	 * Set the sprite and the player position in the map
	 */
	public void initialiseGame() {

		// set sprite for level1 and the cave
		setSpriteLevel1();
		cave.setX(3000);
		cave.setY(120);
		cave.show();

		// set sprite for level 2 and the treasure
		setSpriteLevel2();
		tresure.setX(4000);
		tresure.setY(500);
		tresure.show();

		// Set the player when the level is equal to 1
		if (level != 0) {
			player.setVelocityX(0);
			player.setVelocityY(0);
			player.setX(0);
			player.setY(400);
			player.show();
		}

	}

	/**
	 * Set the sprite information for level 1
	 */
	public void setSpriteLevel1() {
		// Set sprite using a loop and setting them into the array
		// Add x to the next index into the array
		for (int i = 0; i < 7; i++) {
			turtle = new Sprite(turtleidle);
			if (i == 0) {
				turtle.setX(400);
			} else {
				turtle.setX(turtles.get(i - 1).getX() + 400);
			}
			turtle.setY(400);
			turtle.setVelocityX(0);
			turtle.setVelocityY(0);
			turtles.add(turtle);
			turtles.get(i).show();

		}
		for (int i = 0; i < 4; i++) {
			pig = new Sprite(pigidleleft);
			if (i == 0) {
				pig.setX(500);
			} else {
				pig.setX(pigs.get(i - 1).getX() + 800);
			}
			pig.setY(400);
			pig.setVelocityX(0);
			pig.setVelocityY(0);
			pigs.add(pig);
			pigs.get(i).show();

		}
		for (int i = 0; i < 2; i++) {
			apple = new Sprite(applean);
			if (i == 0) {
				apple.setX(100);
			} else {
				apple.setX(apples.get(i - 1).getX() + 100);
			}
			apple.setY(400);
			apple.setVelocityX(0);
			apple.setVelocityY(0);
			apples.add(apple);
			apples.get(i).show();

		}
		for (int i = 0; i <= 5; i++) {
			banana = new Sprite(bananaan);
			if (i == 0) {
				banana.setX(300);
			} else {
				banana.setX(bananas.get(i - 1).getX() + 300);
			}
			banana.setY(500);
			banana.setVelocityX(0);
			banana.setVelocityY(0);
			bananas.add(banana);
			bananas.get(i).show();

		}
	}

	/**
	 * Set the sprite for level 2
	 */
	public void setSpriteLevel2() {
		// Set sprite using a loop and setting them into the array
		// Add x to the next index into the array

		for (int i = 0; i < 4; i++) {
			bat = new Sprite(batflyingleft);
			if (i == 0) {
				bat.setX(100);
				bat.setY(400);
			} else {
				bat.setY(bats.get(i - 1).getY() - 60);
				bat.setX(bats.get(i - 1).getX() + 800);
			}

			bat.setVelocityX(-0.1f);
			bat.setVelocityY(0);
			bats.add(bat);
			bats.get(i).show();

		}
		for (int i = 0; i < 7; i++) {
			rock = new Sprite(rockidleleft);
			if (i == 0) {
				rock.setX(400);
				rock.setY(400);
			} else {
				rock.setX(rocks.get(i - 1).getX() + 480);
				rock.setY(400);
			}
			rock.setVelocityX(0);
			rock.setVelocityY(0);
			rocks.add(rock);
			rocks.get(i).show();

		}
		for (int i = 0; i < 4; i++) {
			apple2 = new Sprite(applean);
			if (i == 0) {
				apple2.setX(100);
			} else {
				apple2.setX(apples2.get(i - 1).getX() + 500);

			}
			apple2.setY(500);
			apple2.setVelocityX(0);
			apple2.setVelocityY(0);
			apples2.add(apple2);
			apples2.get(i).show();

		}
		for (int i = 0; i <= 4; i++) {
			banana2 = new Sprite(bananaan);
			if (i == 0) {
				banana2.setX(300);
			}

			else {
				banana2.setX(bananas2.get(i - 1).getX() + 1000);
			}
			banana2.setY(500);
			banana2.setVelocityX(0);
			banana2.setVelocityY(0);
			bananas2.add(banana2);
			bananas2.get(i).show();

		}
	}

	/**
	 * Draw all the sprite and images into the window
	 */
	public void draw(Graphics2D g) {

		// Drawing the objects
		int xo = 0;
		int yo = 0;

		// Apply offsets to player and draw
		if ((int) player.getX() >= getWidth() / 2) {
			xo = -(int) player.getX() + getWidth() / 2;
		}
		// draw the image for level 0, the menu
		if (level == 0) {
			g.drawImage(sky, 0, 0, screenWidth * 2, screenHeight, null, null);
			g.drawImage(mountain, 0, 0, screenWidth * 2, screenHeight, null, null);
			g.drawImage(startMenu, screenWidth / 2 - 288, 0, screenWidth / 2, screenHeight, null, null);
		}
		// draw the image,sprite and tile map for level 1
		else if (level == 1) {
			g.drawImage(sky, 0, 0, screenWidth * 2, screenHeight, null, null);
			g.drawImage(mountain, 0, 0, screenWidth * 2, screenHeight, null, null);

			drawSpriteLevel1(g, xo, yo);
			tmap_level1.draw(g, xo, yo);
			cave.setOffsets(xo, yo);
			cave.draw(g);

		}
		// draw the image,sprite and tile map for level 2
		else if (level == 2) {
			g.drawImage(cave_bck1, 0, 0, screenWidth * 2, screenHeight, null, null);
			g.drawImage(cave_bck2, 0, 0, screenWidth * 2, screenHeight, null, null);
			g.drawImage(cave_bck3, 0, 0, screenWidth * 2, screenHeight, null, null);
			g.drawImage(cave_bck4, 0, 0, screenWidth * 2, screenHeight, null, null);
			tmap_level2.draw(g, xo, yo);
			drawSpriteLevel2(g, xo, yo);
			tresure.setOffsets(xo, yo);
			tresure.draw(g);
		}

		// Draw the life counter on the top right
		g.drawImage(heart, getWidth() - 80, 35, 25, 25, null, null);
		String msg = String.format("X " + life);
		g.setColor(Color.white);
		g.drawString(msg, getWidth() - 50, 52);

		// Draw the score counter on the top right
		String scoremsg = String.format("Score: " + score);
		g.setColor(Color.white);
		g.drawString(scoremsg, getWidth() - 140, 50);

		// draw the player
		player.setOffsets(xo, yo);
		player.draw(g);

	}

	/**
	 * Draw the sprite for level one
	 * 
	 * @param g the graphic 2D
	 * @param x the offset value for the x
	 * @param y the offset value for the y
	 */
	public void drawSpriteLevel1(Graphics2D g, int x, int y) {
		// use a for each loop throught the array
		for (Sprite turtle : turtles) {
			turtle.setOffsets(x, y);
			turtle.draw(g);
		}
		for (Sprite pig : pigs) {
			pig.setOffsets(x, y);
			pig.draw(g);

		}
		for (Sprite apple : apples) {
			apple.setOffsets(x, y);
			apple.draw(g);

		}
		for (Sprite banana : bananas) {
			banana.setOffsets(x, y);
			banana.draw(g);
		}
	}

	/**
	 * Draw the sprite for level 2
	 * 
	 * @param g the graphic 2D
	 * @param x the offset value for the x
	 * @param y the offset value for the y
	 */
	public void drawSpriteLevel2(Graphics2D g, int x, int y) {
		// use a for each loop throught the array
		for (Sprite bat : bats) {
			bat.setOffsets(x, y);
			bat.draw(g);
		}

		for (Sprite rock : rocks) {
			rock.setOffsets(x, y);
			rock.draw(g);

		}
		for (Sprite apple2 : apples2) {
			apple2.setOffsets(x, y);
			apple2.draw(g);

		}
		for (Sprite banana2 : bananas2) {
			banana2.setOffsets(x, y);
			banana2.draw(g);
		}
	}

	/**
	 * Update the game, game loop
	 * 
	 */
	public void update(long elapsed) {

		// set info for level 1
		if (level == 1) {
			canJump(player, tmap_level1);
			// if the sprite is not touching any tile then add the gravity factor.
			if (!checkTileCollision(player, tmap_level1)) {
				player.setVelocityY(player.getVelocityY() + (gravity * elapsed));
			}
			getSpriteCollisionsLevel1(elapsed);
			cave.update(elapsed);
			cave(cave);
		} else if (level == 2) {
			canJump(player, tmap_level2);
			// if the sprite is not touching any tile then add the gravity factor.
			if (!checkTileCollision(player, tmap_level2)) {
				player.setVelocityY(player.getVelocityY() + (gravity * elapsed));
			}
			getSpriteCollisionsLevel2(elapsed);
			tresure.update(elapsed);
			treasureChest();
		}
		player.update(elapsed);

		// After ten second and if in rage the rage get deactivated
		if (rage && (System.currentTimeMillis() - startRage) >= 10000) {
			rage = false;
			if (left) {
				player.setAnimation(idleleftplayer);
			}
			if (right) {
				player.setAnimation(idlerightplayer);
			}
		}

		// If life is equal to 0 the score get resetted and game resetted
		if (life <= 0) {
			score = 0;
			resetGame();

		}

		// If the music finish, the music restart
		if (background.getFinished() == true) {
			background = new MusicPlay("sounds/backgroundmusic.wav");
			background.start();
		}
	}

	/**
	 * Reset the game
	 * 
	 * If the player die the game get resetted but the sprite are not redraw giving
	 * an easier task to the player
	 */
	public void resetGame() {
		life = 5;
		rage = false;

		if (level == 1) {

			player.setX(0);
			player.setY(500);
			player.setVelocityX(0);
			player.setVelocityY(0);
			player.show();

		} else if (level == 2) {

			player.setX(5);
			player.setY(500);
			player.setVelocityX(0);
			player.setVelocityY(0);
			player.show();
		}
	}

	/**
	 * Check the collision and movemen/interaction for the enemy sprites
	 * 
	 * @param elapsed game loop
	 */
	public void getSpriteCollisionsLevel1(long elapsed) {
		for (Sprite turtle : turtles) {
			turtle.update(elapsed);
			// if the sprite is not touching any tile then add the gravity factor.
			if (!checkTileCollision(turtle, tmap_level1)) {
				turtle.setVelocityY(turtle.getVelocityY() + (gravity * elapsed));
			}
			turtleInteraction(turtle);

		}
		for (Sprite pig : pigs) {
			pig.update(elapsed);
			// if the sprite is not touching any tile then add the gravity factor.
			if (!checkTileCollision(pig, tmap_level1)) {
				pig.setVelocityY(pig.getVelocityY() + (gravity * elapsed));
			}
			pigInteraction(pig);

			// check collision with turtles
			for (Sprite turtle : turtles) {
				enemyCollisionsEach(pig, turtle);
			}

		}
		for (Sprite apple : apples) {
			apple.update(elapsed);
			// if the sprite is not touching any tile then add the gravity factor.
			if (!checkTileCollision(apple, tmap_level1)) {
				apple.setVelocityY(apple.getVelocityY() + (gravity * elapsed));
			}
			powerupCollision(apple);

		}
		for (Sprite banana : bananas) {
			banana.update(elapsed);
			// if the sprite is not touching any tile then add the gravity factor.
			if (!checkTileCollision(banana, tmap_level1)) {
				banana.setVelocityY(banana.getVelocityY() + (gravity * elapsed));
			}

			powerupCollision(banana);

		}
	}

	/**
	 * Check the collision and movemen/interaction for the enemy sprites
	 * 
	 * @param elapsed game loop
	 */
	public void getSpriteCollisionsLevel2(long elapsed) {
		for (Sprite bat : bats) {
			bat.update(elapsed);
			checkTileCollisionBat(bat, tmap_level2);
			enemyCollision(bat);

			// check collision to rocks
			for (Sprite rock : rocks) {
				batRockCollision(bat, rock);
			}

		}
		for (Sprite rock : rocks) {
			rock.update(elapsed);
			// if the sprite is not touching any tile then add the gravity factor.
			if (!checkTileCollision(rock, tmap_level2)) {
				rock.setVelocityY(rock.getVelocityY() + (gravity * elapsed));
			}
			rockInteraction(rock);

			// check collsion with bats
			for (Sprite bat : bats) {
				batRockCollision(bat, rock);
			}

		}
		for (Sprite apple2 : apples2) {
			apple2.update(elapsed);
			// if the sprite is not touching any tile then add the gravity factor.
			if (!checkTileCollision(apple2, tmap_level2)) {
				apple2.setVelocityY(apple2.getVelocityY() + (gravity * elapsed));
			}
			powerupCollision(apple2);

		}
		for (Sprite banana2 : bananas2) {
			banana2.update(elapsed);
			// if the sprite is not touching any tile then add the gravity factor.
			if (!checkTileCollision(banana2, tmap_level2)) {
				banana2.setVelocityY(banana2.getVelocityY() + (gravity * elapsed));
			}

			powerupCollision(banana2);
		}

	}

	/**
	 * Set the methods needed for the pig
	 * 
	 * @param s a temporary sprite reference
	 */
	public void pigInteraction(Sprite s) {
		pigMovement(s);
		enemyCollision(s);
	}

	/**
	 * Set the pig movement
	 * 
	 * @param s a reference sprite
	 */
	public void pigMovement(Sprite s) {
		// if the player is ahead of the pig
		if (player.getX() > s.getX()) {
			s.setAnimation(pigidleright);
			// if the player is in between 200 pixel then the pig chase him
			if (0 <= (player.getX() - s.getX()) && (player.getX() - s.getX()) <= 200) {
				s.setAnimation(pigrunright);
				s.setVelocityX(0.04f);
			}
		}
		// if the player is behind of the pig
		else if (player.getX() < s.getX()) {
			s.setAnimation(pigidleleft);
			// if the player is in between 200 pixel then the pig chase him
			if (0 <= (s.getX() - player.getX()) && (s.getX() - player.getX()) <= 200) {
				s.setAnimation(pigrunleft);
				s.setVelocityX(-0.04f);
			}
		}
	}

	/**
	 * method that manage turtle movement and collision
	 * 
	 * @param s a temporary sprite reference
	 */
	public void turtleInteraction(Sprite s) {

		turtleMovement(s);
		enemyCollision(s);

	}

	/**
	 * Check the turtle movement
	 * 
	 * @param s a temporary sprite reference
	 */
	public void turtleMovement(Sprite s) {
		// if the sprite is between 100 pixel , then the turtle get the spike out
		if (0 <= (s.getX() - player.getX()) && (s.getX() - player.getX()) <= 100
				|| 0 <= (player.getX() - s.getX()) && (player.getX() - s.getX()) <= 100) {
			s.setAnimation(turtlespikeout);
			s.setAnimation(turtleidlespike);

		}

		// if the player leave the 100 pixel the turtle get the spike back in
		else if (s.getAnimation() == turtleidlespike) {
			s.setAnimation(turtlespikein);
			s.setAnimation(turtleidle);
		}

	}

	/**
	 * deal with the cave interaction
	 * 
	 * @param s a temporary sprite reference
	 */
	public void cave(Sprite s) {
		// If the cave and player collide
		if (boundingBoxCollision(player, s)) {
			// set the level to 2
			level++;
			// reset the player position
			resetGame();
		}
	}

	/**
	 * check the rock movement
	 * 
	 * @param s a temporary sprite reference
	 */
	public void rockMovement(Sprite s) {
		// if the player is aorund 20 pixel of y
		if (player.getY() >= s.getY() - 20) {
			// if the player is ahead of rock
			if (player.getX() > s.getX()) {
				s.setAnimation(rockidleright);
				// if the player is between 100 pixel then the rock chase the player
				if (0 <= (player.getX() - s.getX()) && (player.getX() - s.getX()) <= 100) {
					s.setAnimation(rockrunright);
					s.setVelocityX(0.04f);
				}
			}
			// if the player is behind the rock
			else if (player.getX() < s.getX()) {
				s.setAnimation(rockidleleft);
				// if the player is between 100 pixel then the rock chase the player
				if (0 <= (s.getX() - player.getX()) && (s.getX() - player.getX()) <= 100) {
					s.setAnimation(rockrunleft);
					s.setVelocityX(-0.04f);
				}
			}
		}
	}

	/**
	 * check the rock interactions
	 * 
	 * @param s a temporary sprite reference
	 */
	public void rockInteraction(Sprite s) {
		rockMovement(s);
		enemyCollision(s);
	}

	/**
	 * Collision for the bat and movement
	 * 
	 * @param s    a temporary sprite reference
	 * @param tmap the tmap where to check the tile for
	 */
	public void checkTileCollisionBat(Sprite s, TileMap tmap) {

		// Take a note of a sprite's current position
		float sx = s.getX();
		float sy = s.getY();

		// Find out how wide and how tall a tile is
		float tileWidth = tmap.getTileWidth();
		float tileHeight = tmap.getTileHeight();

		// Divide the sprites x coordinate by the width of a tile, to get
		// the number of tiles across the x axis that the sprite is positioned at
		int xtile = (int) (sx / tileWidth);

		// The same applies to the y coordinate
		int ytile = (int) (sy / tileHeight);

		// What tile character is at the top left of the sprite s?
		char ch = tmap.getTileChar(xtile, ytile);

		if (ch != '.') // If it's not a dot (empty space)
		{
			// set the velocity to negative
			s.setVelocityX(-s.getVelocityX());
			s.setAnimation(batflyingright);

		}

		// Check the bottom left.
		xtile = (int) (sx / tileWidth);
		ytile = (int) ((sy + s.getHeight()) / tileHeight);
		ch = tmap.getTileChar(xtile, ytile);
		// If it's not empty space
		if (ch != '.') {
			// set the velocity to negative
			s.setVelocityX(-s.getVelocityX());
			s.setAnimation(batflyingright);

		}
		// Collision for the corner of the top right
		xtile = (int) ((sx + s.getWidth()) / tileWidth);
		ytile = (int) ((sy + s.getHeight()) / tileHeight);
		ch = tmap.getTileChar(xtile, ytile);
		// If it's not empty space
		if (ch != '.') {
			// set the velocity to negative
			s.setVelocityX(-s.getVelocityX());
			s.setAnimation(batflyingleft);

		}

		// Collision for the corner of the bottom right
		xtile = (int) ((sx + s.getWidth()) / tileWidth);
		ytile = (int) (sy / tileHeight);
		ch = tmap.getTileChar(xtile, ytile);
		// If it's not empty space
		if (ch != '.') {
			// set the velocity to negative
			s.setVelocityX(-s.getVelocityX());
			s.setAnimation(batflyingleft);

		}
		// if it is the end of the screen
		if (s.getX() >= screenWidth * 2 - 100) {
			// set the velocity to negative
			s.setVelocityX(-0.1f);
			s.setAnimation(batflyingleft);
		}

		// if it is at the start of the screen
		else if (s.getX() <= 0) {
			// set the velocity to positive
			s.setVelocityX(+0.1f);
			s.setAnimation(batflyingright);
		}

	}

	/**
	 * deal with the power up collision
	 * 
	 * @param s a temporary sprite reference
	 */
	public void powerupCollision(Sprite s) {
		// if the player is between 20 pixel on x and y
		if (s.getX() - 20 <= player.getX() && player.getX() <= s.getX() + 20 && s.getY() - 20 <= player.getY()
				&& player.getY() <= s.getY() + 20 && s.isVisible()) {
			// set the different sounds
			if (level == 1) {
				food = new Sound("sounds/crunch.wav");
				food.start();
			} else if (level == 2) {
				foodcave = new SoundFilterPlay("sounds/crunch.wav");
				foodcave.start();

			}

			// if the sprite is a banana
			if (s.getAnimation() == bananaan) {
				// start the rage and rage counter
				ragesound = new Sound("sounds/rage.wav");
				ragesound.start();
				rage = true;
				startRage = System.currentTimeMillis();
				s.hide();

			}
			// if it is an apple
			else if (s.getAnimation() == applean) {
				// add a life to the counter
				life = life + 1;
				s.hide();
			}

		}

	}

	/**
	 * Check the collision between rock and bats
	 * 
	 * @param b a temporary sprite reference for bat
	 * @param r a temporary sprite reference for rock
	 */
	public void batRockCollision(Sprite b, Sprite r) {
		// if they are colliding and visible
		if (boundingBoxCollision(b, r) && (b.isVisible() || r.isVisible())) {
			// stop the rock
			r.stop();
			r.setVelocityX(0);
			// if the bat is in front of the rock then set velocity to positive
			if (b.getX() > r.getX()) {
				b.setAnimation(batflyingright);
				b.setVelocityX(+0.1f);
			}
			// if the bat is behind the rock then set velocity to negative
			else if (b.getX() < r.getX()) {
				b.setAnimation(batflyingleft);
				b.setVelocityX(-0.1f);
			}
		}
	}

	/**
	 * deal with the treasure interaction
	 */
	public void treasureChest() {

		// if the player and treasure collide
		if (boundingBoxCollision(player, tresure)) {
			// set the sound and show a box with the score
			treasureSound = new SoundFilterPlay("sounds/treasure.wav");
			treasureSound.start();
			JFrame frame = new JFrame();
			JLabel label = new JLabel(
					"<html><center>CONGRATULATION, You Have Finished The Game<br> Your Score Was:" + (score + life));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			JOptionPane.showMessageDialog(null, label, "GG", JOptionPane.PLAIN_MESSAGE);
			System.exit(EXIT_ON_CLOSE);

		}
	}

	/**
	 * check the collision between enemies
	 * 
	 * @param s a temporary sprite reference
	 * @param v a temporary sprite reference
	 */
	public void enemyCollisionsEach(Sprite s, Sprite v) {
		// if the collide and visible, then stop s (the moving sprite)
		// used for the pig and turtle
		if (boundingBoxCollision(s, v) && v.isVisible()) {
			s.stop();
			s.setVelocityX(0);
		}
	}

	/**
	 * Collision between enemy and player
	 * 
	 * @param s a temporary sprite reference
	 */
	public void enemyCollision(Sprite s) {
		// if they are colliding and the sprite is visible
		if (boundingBoxCollision(s, player) && s.isVisible()) {
			// if it is not in rage
			if (!rage) {
				// set the sounds
				if (level == 1) {
					hit = new Sound("sounds/hit.wav");
					hit.start();
				} else if (level == 2) {

					hitcave = new SoundFilterPlay("sounds/hit.wav");
					hitcave.start();

				}
				// take a life out
				life = life - 1;
				// if the player is facing right
				if (right) {
					// if is infront then add 50 to the x
					if (player.getX() > s.getX()) {
						player.setX(player.getX() + 50);
						player.setAnimation(idlerightplayer);
					}
					// if is behind then subtract 50 to the x
					else if (player.getX() < s.getX()) {
						player.setX(player.getX() - 50);
						player.setAnimation(idlerightplayer);
					}
				}
				// if the player is facing left
				if (left) {
					// if is infront then add 50 to the x
					if (player.getX() > s.getX()) {
						player.setX(player.getX() + 50);
						player.setAnimation(idleleftplayer);
					}
					// if is behind then subtract 50 to the x
					else if (player.getX() < s.getX()) {
						player.setX(player.getX() - 50);
						player.setAnimation(idleleftplayer);
					}

				}

			}
			// if the player is in rage mode
			if (rage) {
				// hide the sprite,make the sound and add the score
				s.hide();
				enemyhit = new Sound("sounds/enemy-hit.wav");
				enemyhit.start();
				score++;
			}
		}

	}

	/**
	 * check if the player can jump
	 * 
	 * @param s    a temporary sprite reference
	 * @param tmap reference to the tile map
	 * @return a boolean of jump
	 */
	public boolean canJump(Sprite s, TileMap tmap) {

		float sx = s.getX();
		float sy = s.getY();

		float tileWidth = tmap.getTileWidth();
		float tileHeight = tmap.getTileHeight();

		// check the middle of the sprite
		int xtile = (int) ((sx + (s.getWidth() / 2)) / tileWidth);
		int ytile = (int) ((sy + s.getHeight() +4) / tileHeight);
		// if the player is colliding with the tiles
		// jump can be true
		char ch = tmap.getTileChar(xtile, ytile);
		if (ch != '.') {
			jump = true;

		}

		// else i cant jump
		else {
			jump = false;

		}
		return jump;
	}

	/**
	 * Check the tile collision
	 * 
	 * @param s    a temporary sprite reference
	 * @param tmap the reference to the tilemap
	 * @return if the sprite are touching the tile
	 */
	public boolean checkTileCollision(Sprite s, TileMap tmap) {

		// if the sprite is colliding
		boolean colliding = false;
		// Take a note of a sprite's current position
		float sx = s.getX();
		float sy = s.getY();

		// Find out how wide and how tall a tile is
		float tileWidth = tmap.getTileWidth();
		float tileHeight = tmap.getTileHeight();

		// Divide the sprites x coordinate by the width of a tile, to get
		// the number of tiles across the x axis that the sprite is positioned at
		int xtile = (int) (sx / tileWidth);

		// The same applies to the y coordinate
		int ytile = (int) (sy / tileHeight);

		// What tile character is at the top left of the sprite s?
		char ch = tmap.getTileChar(xtile, ytile);

		if (ch != '.') // If it's not a dot (empty space), handle it
		{
			// Here we just stop the sprite.
			s.stop();
			s.setX(s.getX() + xVelocity);

			// You should move the sprite to a position that is not colliding
		}

		// We need to consider the other corners of the sprite
		// The above looked at the top left position, let's look at the bottom left.
		xtile = (int) (sx / tileWidth);
		ytile = (int) ((sy + s.getHeight()) / tileHeight);
		ch = tmap.getTileChar(xtile, ytile);
		// If it's not empty space
		if (ch != '.') {
			// Let's make the sprite bounce
			s.stop();
			s.setY(sy-4);
			colliding = true;

		}
		// Collision for the corner of the top right
		xtile = (int) ((sx + s.getWidth()) / tileWidth);
		ytile = (int) ((sy + s.getHeight()) / tileHeight);
		ch = tmap.getTileChar(xtile, ytile);
		// If it's not empty space
		if (ch != '.') {
			// Let's make the sprite bounce
			s.stop();
			// s.setX(s.getX() - xVelocity);
			s.setY(sy-4);
			colliding = true;

		}

		// Collision for the corner of the bottom right
		xtile = (int) ((sx + s.getWidth()) / tileWidth);
		ytile = (int) (sy / tileHeight);
		ch = tmap.getTileChar(xtile, ytile);
		// If it's not empty space
		if (ch != '.') {
			// Let's make the sprite bounce
			s.stop();
			// s.setX(s.getX() - xVelocity);

		}

		if (s.getX() <= 0) {
			s.stop();
			s.setX(s.getX() + 10);
		}

		// check if the sprite hitted a hole
		xtile = (int) (((sx + s.getWidth()) / 2) / tileWidth);
		ytile = (int) (sy + s.getHeight() / tileHeight);
		ch = tmap.getTileChar(xtile, ytile);
		// If it's not empty space
		if ((s.getY() + s.getHeight()) >= screenHeight - 4) {
			s.stop();
			// if it is a player then he died
			if (s.equals(player)) {
				life = 0;
			}
			// else it just disappear
			else {
				s.hide();
			}

		}
		// return if it is colliding
		return colliding;
	}

	/**
	 * check if two sprite are colliding between each other
	 * 
	 * @param s1 a temporary sprite reference
	 * @param s2 a temporary sprite reference
	 * @return if the sprite are colliding
	 */
	public boolean boundingBoxCollision(Sprite s1, Sprite s2) {
		return ((s1.getX() + s1.getImage().getWidth(null) > s2.getX())
				&& (s1.getX() < (s2.getX() + s2.getImage().getWidth(null)))
				&& ((s1.getY() + s1.getImage().getHeight(null) > s2.getY())
						&& (s1.getY() < s2.getY() + s2.getImage().getHeight(null))));
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

		// allow to move left
		case KeyEvent.VK_LEFT:
			left = true;
			right = false;
			player.setVelocityX(-xVelocity);
			if (!rage)
				player.setAnimation(runleftplayer);
			if (rage)
				player.setAnimation(ragerunleft);
			break;

		// allow to move right
		case KeyEvent.VK_RIGHT:
			right = true;
			left = false;
			player.setVelocityX(xVelocity);
			if (!rage)
				player.setAnimation(runrightplayer);
			if (rage)
				player.setAnimation(ragerunright);

			break;

		// allow to jump
		case KeyEvent.VK_UP:

			if (jump) {
				jumping = new Sound("sounds/jump.wav");
				jumping.start();
				player.setVelocityY(-0.4f);
				jump = false;

			}

			break;
		// cheat code for raging
		case KeyEvent.VK_C:
			rage = true;
			startRage = System.currentTimeMillis();
			break;
		// cheat code to level 1
		// !it does not restart the level, it keeps the reference to the old one!
		case KeyEvent.VK_1:
			if (level != 1) {
				level = 1;
				resetGame();

			}
			break;
		// button to start the game
		case KeyEvent.VK_SPACE:
			if (level == 0) {
				level = 1;
				resetGame();
			}
			break;

		// cheat code to level 2
		// !it does not restart the level, it keeps the reference to the old one!
		case KeyEvent.VK_2:
			if (level != 2) {
				level = 2;
				resetGame();
			}
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

		// stop the player if released
		case KeyEvent.VK_LEFT:
			player.setVelocityX(0);
			if (!rage)
				player.setAnimation(idleleftplayer);
			if (rage)
				player.setAnimation(rageidleleft);
			break;

		// stop the player if released
		case KeyEvent.VK_RIGHT:
			player.setVelocityX(0);
			if (!rage)
				player.setAnimation(idlerightplayer);
			if (rage)
				player.setAnimation(rageidleright);
			break;

		// stop the player if released
		case KeyEvent.VK_UP:
			if (!rage) {
				if (right)
					player.setAnimation(idlerightplayer);
				if (left)
					player.setAnimation(idleleftplayer);
			}
			if (rage) {
				if (right)
					player.setAnimation(rageidleright);
				if (left)
					player.setAnimation(rageidleleft);
			}
			break;

		default:
			break;
		}
	}
}
