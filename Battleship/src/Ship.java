import processing.core.PImage;

public class Ship {
	// * ----- * Instance Variables * ----- * //
	
	private int size;
	private boolean vertical;
	private int ID;
	
	private int x;
	private int y;
	
	private int health;
	private boolean isAlive;
	
	private PImage shipImage;
	
	
	// * ----- * Constructors * ----- * //
	
	/**
	 * Class Constructor
	 * 
	 * @param n		the size of the ship
	 * @param i		the image of the ship
	 */
	public Ship(int n, PImage i) {
		size = n;
		health = size;
		isAlive = true;
		shipImage = i;
	}
	
	
	// * ----- * Core Methods * ----- * //
	
	/**
	 * 
	 * @return		the size of ship
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Rotates ship
	 */
	public void rotate() {
		vertical = !vertical;
	}
	
	/**
	 * 
	 * @return		if the ship is vertical
	 */
	public boolean isVertical() {
		return vertical;
	}
	
	/**
	 * Sets x position of ship
	 * 
	 * @param pos		the x position of ship
	 */
	public void setXPos(int pos) {
		x = pos;
	}
	
	/**
	 * Sets y position of ship
	 * 
	 * @param pos		the y position of ship
	 */
	public void setYPos(int pos) {
		y = pos;
	}
	
	/**
	 * Sets position of ship
	 * 
	 * @param pos		the x position of ship
	 */
	public void setPos(int xPos, int yPos) {
		x = xPos;
		y = yPos;
	}
	
	/**
	 * 
	 * @return		x position of ship
	 */
	public int getXPos() {
		return x;
	}
	
	/**
	 * 
	 * @return		y position of ship
	 */
	public int getYPos() {
		return y;
	}

	/**
	 * Sets the ship's ID
	 * 
	 * @param n		the ship's ID number
	 */
	public void setID(int n) {
		ID = n;
	}

	/**
	 * 
	 * @return		ship's ID
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * 
	 * @return		ship's health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Deal damage to ship if it's alive
	 */
	public void damage() {
		if (isAlive) health--;
		if (health == 0) isAlive = false;
	}
	
	/**
	 * 
	 * @return		if ship isAlive
	 */
	public boolean isAlive() {
		return isAlive;
	}
	
	/**
	 * 
	 * @return		the ship's image
	 */
	public PImage getShipImage() {
		return shipImage;
	}

}
