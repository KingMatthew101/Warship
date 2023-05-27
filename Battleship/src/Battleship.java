import java.util.*;
import processing.core.PApplet;
import processing.core.PImage;

public class Battleship extends PApplet {
	// * ----- * Global Variables * ----- * //
	
	// Screen and back-end
	private static Scanner keyboard;
	private int screenX = 1200;
	private int screenY = 675;
	
	// Grid information
	public Grid playerBoard;
	public Grid enemyBoard;
	private int gridSize = 10;
	private int numberOfShips = 5;
	private int boxSize = 50;
	
	// Game state info
	private int state; // 0 = title, 1 = game, 2 = gameOver		(Maybe use an enum in future???)
	private boolean setupPhase;
	private int turn;
	
	// Ship images
	private PImage tugBoat;
	private PImage destroyer;
	private PImage submarine;
	private PImage battleship;
	private PImage aircraftCarrier;
	
	// Button info
	private Button startButton;
	private String welcome = "Welcome to Battleship!";
	private String end;
	
	
	// * ----- * Core Systems * ----- * //
	
	// Debug system
	public static void main(String[] args) {
		// Java Runtime Environment Check
		//System.out.println(System.getProperty("java.runtime.version"));
		
		
		// Setup Variables and Declarations
		keyboard = new Scanner(System.in);
		
		
		// Integrating Processing
		String[] processingArgs = {"BattleShip"};
		Battleship mySketch = new Battleship();
		PApplet.runSketch(processingArgs, mySketch);
		
	}
	
	
	// method used only for setting the size of the window
    public void settings(){
        // 16:9 aspect ratio
    	size(screenX, screenY);
    }

    
    // Start()
    // identical use to setup in Processing IDE except for size()
    public void setup(){
    	// Load Image files
    	tugBoat = loadImage("resource/Battleship_Resources/Tug-Boat.png");
		destroyer = loadImage("resource/Battleship_Resources/Destroyer.png");
		submarine = loadImage("resource/Battleship_Resources/Submarine.png");
		battleship = loadImage("resource/Battleship_Resources/Battleship.png");
		aircraftCarrier = loadImage("resource/Battleship_Resources/AirCraft-Carrier.png");
		
		// Set image sizes
		tugBoat.resize(boxSize * 2, boxSize);
		destroyer.resize(boxSize * 3, boxSize);
		submarine.resize(boxSize * 3, boxSize);
		battleship.resize(boxSize * 4, boxSize);
		aircraftCarrier.resize(boxSize * 5, boxSize);
		
    	// Secondary setup() command to initialize all game-instance specific data
    	start();
    }

    
    // Separate Setup command to reinitialize all starting data when called
    public void start() {
    	// Setup player and enemy boards
    	playerBoard = new Grid(gridSize, numberOfShips, this, boxSize, 0);
		enemyBoard = new Grid(gridSize, numberOfShips, this, boxSize, 1);
		
		// Skip setupPhase because it's not implemented yet
		turn = 2; // TODO: REMOVE!
		
		// Randomly generate positions
		playerBoard.generatePositions(); // Remove if player is choosing positions
		enemyBoard.generatePositions();
		
		setupPhase = false; // turn to true to enable setupPhase
		
		// Start game at title screen
		state = 0; // switch to debug different states (0 is title, 1 is game, 2 is gameOver)
		
		// Create title screen "Start" Button
		startButton = new Button(screenX / 2, screenY * 2 / 3, 200, 100, 0, "Start Button", "Start", 50);
		
		// Used for debugging in Eclipse Console
		playerBoard.printGrid();
		// enemyBoard.printGrid();
    }
    
    
    // Update()
    // identical use to draw in Processing IDE
    public void draw(){
    	background(204, 204, 204);	// Refresh Screen each frame
    	
    	if(state == 0) {	// if state is title, update title
    		updateTitle();
    	}
    	else if(state == 1) {	// if state is game, update game screen with player text and both grid boards
    		int textSize = 30;
        	textSize(textSize);
        	fill(0);
        	text("Player 1", ((gridSize * boxSize) - textWidth("Player 1")) / 2,  textSize + 4);
        	text("Player 2", screenX - textWidth("Player 2") - (((gridSize * boxSize) - textWidth("Player 2")) / 2),  textSize + 4);
    		
    		playerBoard.update(this, turn, setupPhase);
    		enemyBoard.update(this, turn, setupPhase);
    	}
    	else if(state == 2) {	// if state is gameOver, continue to update game screen but overlay game overe message
    		int textSize = 30;
        	textSize(textSize);
        	fill(0);
        	text("Player 1", ((gridSize * boxSize) - textWidth("Player 1")) / 2,  textSize + 4);
        	text("Player 2", screenX - textWidth("Player 2") - (((gridSize * boxSize) - textWidth("Player 2")) / 2),  textSize + 4);
    		
    		playerBoard.update(this, turn, setupPhase);
    		enemyBoard.update(this, turn, setupPhase);
    		
    		// * Game Over Message * //
    		
    		int tempWidth = 600;
    		int tempHeight = 400;
    		
    		fill(255, 255, 255, 190);
    		rect((screenX - tempWidth)/ 2, (screenY - tempHeight) / 2, tempWidth, tempHeight);
    		
    		end = playerWhoWon() + " has won this battle";
    		
    		textSize = 50;
        	textSize(textSize);
        	fill(0);
        	text(end, (screenX - textWidth(end)) / 2, (screenY - textSize) / 2);
    	}
    }
    
    
    // Detects mouse inputs (from Processing)
    public void mousePressed() {
    	if(state == 0) {		// if state is title, detect title screen mouse updates
    		mousePressTitle();
    	}
    	else if(state == 1) {	// if state is game, detect either player or enemy mouse updates
    		if(turn % 2 == 0 && !setupPhase) {
    			enemyBoard.mousePress(this);
    		}
    		else if(turn % 2 == 1 && !setupPhase) {
    			playerBoard.mousePress(this);
    		}
    	}
    	else if(state == 2) {	// if state is gameOver, next mouse update resets game instance
    		start();
    	}
    }
    
    // Begin gameplay (not in use)
    public void exitSetupPhase() {
    	setupPhase = false;
    }
    
    // Increment time keeper (variable 'turn')
    public void nextTurn() {
    	turn++;
    }
    
    // Returns stored image data depending on index input
    public PImage getImage(int n) {
    	switch(n) {
    		case 0:
    			return tugBoat;
    		case 1:
    			return destroyer;
    		case 2:
    			return submarine;
    		case 3:
    			return battleship;
    		case 4:
    			return aircraftCarrier;
    	}
    	return null;
    }
	
    // Displays Title screen
    public void updateTitle() {
    	int textSize = 100;
    	textSize(textSize);
    	fill(0, 408, 612);
    	text(welcome, (screenX - textWidth(welcome)) / 2, (screenY - textSize) / 3);
    	startButton.update(this);
    }
    
    // Actions for Title screen on mouse input
    public void mousePressTitle() {
    	if(startButton.isHighlighted(this))
    		state = 1;
    }
    
    // Set game state
    public void setState(int n) {
    	state = n;
    }
    
    // Return name of player who won
    public String playerWhoWon() {
    	if(playerBoard.isDefeated())
    		return "Player 2";
    	else
    		return "Player 1";
    }
    
}
