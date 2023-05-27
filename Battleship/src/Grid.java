import processing.core.PApplet;

public class Grid extends PApplet{
	// * ----- * Instance Variables * ----- * //
	
	// Map info
	private int length; // y
	private int width;  // x
	private Tile[][] map;
	
	private int boxSize;
	private int xOffset;
	private int yOffset;
	private int shipYOffset;
	
	// Ship info
	private Ship[] ships;
	private int numOfShips;
	
	Battleship b;
	private int ID;
	private int opacity;
	private boolean defeated;
	
	
	// * ----- * Constructors * ----- * //
	
	/**
	 * Class Constructor - General
	 * 
	 * @param b		the instance of Battleship class for drawing
	 * @param s		the size of the side of a box in the grid
	 * @param id	the ID tag for this board
	 */
	public Grid(Battleship b, int s, int id) {
		length = 10;
		width = length;
		map = new Tile[length][length];
		numOfShips = 5;
		this.b = b;
		ID = id;
		
		boxSize = s; // add parameter
		xOffset = 0;
		yOffset = 675 - (length * boxSize);
		if (ID == 1) xOffset = 1200 - (width * boxSize);
		shipYOffset = 50;
		
		// create ship info
		ships = new Ship[numOfShips];
		createShips();
	}
	
	
	/**
	 * Class Constructor - Specify side length of board
	 * 					   and number of ships
	 * 
	 * @param l		the side length of board
	 * @param n		the number of ships
	 * @param b		the instance of Battleship class for drawing
	 * @param s		the size of the side of a box in the grid
	 * @param id	the ID tag for this board
	 */
	public Grid(int l, int n, Battleship b, int s, int id) {
		length = l;
		width = length;
		map = new Tile[length][length];
		numOfShips = n;
		this.b = b;
		ID = id;
		
		boxSize = s; // add parameter
		xOffset = 0;
		yOffset = 675 - (length * boxSize);
		if (ID == 1) xOffset = 1200 - (width * boxSize);
		shipYOffset = 50;
		
		// create ship info
		ships = new Ship[numOfShips];
		createShips();
	}
	
	
	/**
	 * Class Constructor - Specify length and width of board
	 * 					   and number of ships
	 * 
	 * @param l		length of board (y)
	 * @param w		width of board (x)
	 * @param n		number of ships
	 * @param b		instance of Battleship class for drawing
	 * @param s		the size of the side of a box in the grid
	 * @param id	the ID tag for this board
	 */
	public Grid(int l, int w, int n, Battleship b, int s, int id) {
		length = l;
		width = w;
		map = new Tile[length][w];
		numOfShips = n;
		this.b = b;
		ID = id;
		
		boxSize = s; // add parameter
		xOffset = 0;
		yOffset = 675 - (length * boxSize);
		if (ID == 1) xOffset = 1200 - (width * boxSize);
		shipYOffset = 50;
		
		// create ship info
		ships = new Ship[numOfShips];
		createShips();
	}
	
	
	// TODO: Suedo code to go through all numOfShips. Please fix this eventually!
	/**
	 * Instantiate ships[] with a number of ships equal to numOfShips
	 */
	private void createShips() {
			ships[0] = new Ship(2, b.getImage(0));
			ships[1] = new Ship(3, b.getImage(1));
			ships[2] = new Ship(3, b.getImage(2));
			ships[3] = new Ship(4, b.getImage(3));
			ships[4] = new Ship(5, b.getImage(4));
	}
	
	
	/**
	 * Prints full grid contents to Console
	 */
	public void printGrid() {
		if(ID == 0) {
			System.out.println("Player Board:");
			System.out.println();
		}
		else if(ID == 1) {
			System.out.println("Enemy Board:");
			System.out.println();
		}
		for(int j = 0; j < map.length; j++) {
			for(int i = 0; i < map[j].length; i++) {
				System.out.print(map[j][i].getValue() + " ");
			}
			System.out.println();
		}
	}
	
	
	// * ----- * Core Methods * ----- * //
	
	/**
	 * Handles all recurring updates such as draw methods for Grid
	 * as well as detects if a player has won
	 * 
	 * @param B				the instance of Battleship class for drawing
	 * @param turn			the current turn in the game
	 * @param setupPhase	is the game in setupPhase (not implemented)
	 */
	public void update(Battleship B, int turn, boolean setupPhase) {
		b = B;
		
		// logic to determine if player or enemy board is defending
		boolean boardIsDefending = ((turn % 2 == 0 && ID == 1) || (turn % 2 == 1 && ID == 0));
		
		if(setupPhase && boardIsDefending) { // setupPhase not yet implemented
			
			if(turn >= 2)
				b.exitSetupPhase();
		}
		else {		// Update tiles on grid and pass on whether boardIsDefending
			updateTiles(boardIsDefending);
		}
		
		drawShips();
		
		// If all ships are dead, set board status as defeated and set state to gameOver
		if(!ships[0].isAlive() && !ships[1].isAlive() && !ships[2].isAlive() && !ships[3].isAlive() && !ships[4].isAlive()) {
			defeated = true;
			b.setState(2);
		}
	}
	
	
	/**
	 * Handles actions on tiles upon mouse input. 
	 * Reveals whether a tile is a hit or miss
	 * 
	 * @param B		instance of Battleship class for mouse detection
	 */
	public void mousePress(Battleship B) {
		b = B;
		for(int j = 0; j < length; j++) {		// for every tile...
			for(int i = 0; i < width; i++) {
				Tile tile = map[j][i];
				
				if(tile.isHighlighted(b) && !tile.clicked()) {	// if tile is currently highlighted and not yet clicked
					if(tile.getValue() != 0) {		// check if tile has a ship. If yes, damage ship
						// hit
						ships[tile.getValue() - 1].damage();
					}
					// otherwise miss
					tile.click();
					b.nextTurn();
				}
			}
		}
	}
	
	
	/**
	 * Updates tile colors and draws them on screen
	 * 
	 * @param defending		is the board defending?
	 */
	public void updateTiles(boolean defending) {
		// determine if board is active (defending) or not. Set opacity to reflect this
		if (defending) {
			opacity = 255;	// Opacity values just needed to be switched and background refresh added
		}
		else {
			opacity = 150;
		}
		
		for(int j = 0; j < length; j++) {		// for each tile...
			for(int i = 0; i < width; i++) {
				Tile tile = map[j][i];
				
				if(tile.isHighlighted(b) && !tile.clicked()) {
					b.fill(tile.getColorHighlight(), opacity);	// if tile is highlighted and not clicked
				}
				else if(tile.clicked()) {
					if(tile.getValue() != 0)
						b.fill(tile.getColorHit(), opacity);	// if tile is clicked and a hit
					else
						b.fill(tile.getColorMiss(), opacity);	// if tile is clicked and a miss
				}
				else {
					b.fill(tile.getColorNormal(), opacity);		// if tile hasn't been clicked and isn't highlighted
				}
				
				b.rect(tile.getX(), tile.getY(), tile.getSize(), tile.getSize());	// draw tile
			}
		}
	}
	
	
	/**
	 * Draws the ship trackers on top of screen
	 */
	public void drawShips() {
		if(!ships[4].isAlive()) b.tint(255, 0, 0, 255);
		else b.tint(252, 181, 58); // (252, 181, 58) or (255, 108, 23)
		b.image(ships[4].getShipImage(), xOffset, shipYOffset);
		b.tint(255, 255);
		
		if(!ships[3].isAlive()) b.tint(255, 0, 0, 255);
		else b.tint(87, 39, 100); // (150, 75, 0) or (87, 39, 100)
		b.image(ships[3].getShipImage(), xOffset + (ships[4].getSize() * boxSize), shipYOffset);
		b.tint(255, 255);
		
		if(!ships[2].isAlive()) b.tint(255, 0, 0, 255);
		else b.tint(0, 161, 27);
		b.image(ships[2].getShipImage(), xOffset, shipYOffset + boxSize);
		b.tint(255, 255);
		
		if(!ships[1].isAlive()) b.tint(255, 0, 0, 255);
		else b.tint(9, 2, 153);
		b.image(ships[1].getShipImage(), xOffset + (ships[2].getSize() * boxSize), shipYOffset + boxSize);
		b.tint(255, 255);
		
		if(!ships[0].isAlive()) b.tint(255, 0, 0, 255);
		else b.tint(87, 87, 87);
		b.image(ships[0].getShipImage(), xOffset + (boxSize * (ships[2].getSize() + ships[1].getSize())), shipYOffset + boxSize);
		b.tint(255, 255);
		
		// if not alive, make red
		// else make unique "set" color
		// draw image of ship
		// reset color pallet
	}
	
	
	/**
	 * Initializes map[][]
	 */
	public void initializeMap() {
		for(int j = 0; j < length; j++) {
			for(int i = 0; i < width; i++) {
				map[j][i] = new Tile((boxSize * i) + xOffset, (boxSize * j) + yOffset, boxSize);
			}
		}
	}

	
	/**
	 * Returns x length (width)
	 * 
	 * @return		the x width
	 */
	public int getXLength() {
		return map[0].length;
	}
	
	/**
	 * Returns y length (length)
	 * 
	 * @return		the y length
	 */
	public int getYLength() {
		return map.length;
	}
	
	/**
	 * Return int value at position (x , y)
	 * 
	 * @param x		the x-value to search at
	 * @param y		the y-value to search at
	 * 
	 * @return		the value in Grid at (x , y)
	 */
	public int getValueAt(int x, int y) {
		return map[y][x].getValue();
	}
	
	/**
	 * Set int value at position (x , y)
	 * 
	 * @param x		the x-value to set at
	 * @param y		the x-value to set at
	 * @param val	the value to set Grid(x,y) to
	 */
	public void setValueAt(int x, int y, int val) {
		map[y][x].setValue(val);
	}
	
	/**
	 * Returns whether the board defeated or not
	 * 
	 * @return defeated		is this board defeated?
	 */
	public boolean isDefeated() {
		return defeated;
	}
	
	
	/**
     * Sets random location of ships on a board
     */
    public void generatePositions() {
    	initializeMap();	// make map and set all values to null/zero
    	
    	// While loop allows a bad combo to scrap and try again
    	boolean generatingLocations = true;
    	while(generatingLocations) {
   			for(int n = 0; n < ships.length; n++) {
    			
    			// Checks to see if there are any valid spaces for ship "n"
   				// If no spaces available, try the entire grid's random placement process again
    			if (checkValidSpaces(n) == 0) break;
   				
    			
    			setShipPosition(n);
    					
    			if (n >= ships.length - 1) generatingLocations = false;
    			
    		}
   			// break; // IMPORTANT!!! Remove this break when done to allow proper function of this repeat system
    	}
    }
    
    
    /**
     * Checks to see if there are any valid spaces for ship "n"
     * 
     * @param n			the position within ships[] of the current Ship object
     * 
     * @return			the number of valid spaces available
     */
    public int checkValidSpaces(int n) {
		int validSpaces = 0;
		
		// loop through board
		for(int j = 0; j < this.getYLength(); j++) {
			for(int i = 0; i < this.getXLength(); i++) {
				
				//Check availability horizontally
				if(i <= this.getXLength() - ships[n].getSize()) {	// if the current x position of loop is less than or equal to the size of board minus length of current ship... 
					int infractions = 0;
					
					// check if spaces in that position are clear
					for(int p = 0; p < ships[n].getSize(); p++) {
						if(this.getValueAt(i + p, j) != 0) infractions++;
					}
					if(infractions == 0) validSpaces++;
				}
				
				//repeat vertically
				//Check availability vertical
				if(j <= this.getYLength() - ships[n].getSize()) {
					int infractions = 0;
					
					for(int p = 0; p < ships[n].getSize(); p++) {
						if(this.getValueAt(i, j + p) != 0) infractions++;
					}
					if(infractions == 0) validSpaces++;
				}
				
			}
		}
		
		return validSpaces;
	}
	
	
	/**
	 * Randomly pick a position and rotation
	 * If that position for the chosen orientation is open,
	 * update the grid map with # n of ship and
	 * update object's position coordinates for easy access later
	 * 
	 * @param n			the position within ships[] of the current Ship object
	 */
	public void setShipPosition(int n) {
		while(true) {
			// randomly pick a coordinate and rotation to try to build a ship from
			int x = (int)(Math.random() * (this.getXLength()));
			int y = (int)(Math.random() * (this.getYLength()));
			
			if((int)(Math.random() * 2) % 2 == 0) {
				ships[n].rotate();
			}
			
			// If ship is vertical
			if(ships[n].isVertical() && y + ships[n].getSize() - 1 < this.getYLength()) {
				int infractions = 0;
				
				// checks if any ships are in the way
				for(int p = 0; p < ships[n].getSize(); p++) {
					if(this.getValueAt(x, y + p) != 0) infractions++;
				}
				
				if(infractions != 0) continue; // if not a valid location, retries the placement while loop
				
				// updates tiles at location to contain ship ID
				for(int p = 0; p < ships[n].getSize(); p++) {
					this.setValueAt(x, y + p, n + 1);
				}
				ships[n].setPos(x, y); // updates object's position variables
				break;
			}
			
			// If ship is horizontal
			if (!ships[n].isVertical() && x + ships[n].getSize() - 1 < this.getXLength()) {
				int infractions = 0;
				
				// checks if any ships are in the way
				for(int p = 0; p < ships[n].getSize(); p++) {
					if(this.getValueAt(x + p, y) != 0) infractions++;
				}
				
				if(infractions != 0) continue; // if not a valid location, retries the placement while loop
				
				// updates tiles at location to contain ship ID
				for(int p = 0; p < ships[n].getSize(); p++) {
					this.setValueAt(x + p, y, n + 1);
				}
				ships[n].setPos(x, y); // updates object's position variables
				break;
			}
			
		}
	}
	
}
