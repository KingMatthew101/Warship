import processing.core.PApplet;

public class Tile extends PApplet{
	// Use Tile instead of int in map[][]
	
	
	// * ----- * Instance Variables * ----- * //
	
	// General info
	private int value;
	private int size;
	private boolean clicked;
	
	// Position info
	private int x;
	private int y;
	private int xShift;
	private int yShift;
	
	// Lazy location to store color info
	private int normalColor = color(50,86,168);
	private int highlightedColor = color(90, 132, 230);
	private int hitColor = color(227, 50, 50);
	private int missColor = color(250, 250, 250);	// 200, 200, 204
	
	private int grayedNormalColor = color(132, 148, 186);	// Not even being used. Why is it still here? I dunno...
	
	// Just a main battleship reference to access Processing capabilities
	Battleship b;
	
	
	// * ----- * Constructors * ----- * //
	
	/**
	 * Class Constructor
	 * 
	 * @param X		x position of tile
	 * @param Y		y position of tile
	 * @param b		the instance of Battleship class for mouse detection
	 */
	public Tile(int X, int Y, int b) {
		x = X;
		y = Y;
		size = b;
		
		xShift = x + size;
		yShift = y + size;
		
		clicked = false;
	}
	
	
	// * ----- * Core Methods * ----- * //
	
	/**
	 * Sets value of tile (aka ship ID or 0)
	 * 
	 * @param n		the ID value to be stored
	 */
	public void setValue(int n) {
		value = n;
	}
	
	/**
	 * 
	 * @return		ID value stored in tile
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * 
	 * @return		x position of tile
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @return		y position of tile
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 
	 * @return		x position + width of tile
	 */
	public int getXShift() {
		return xShift;
	}
	
	/**
	 * 
	 * @return		y position + height of tile
	 */
	public int getYShift() {
		return yShift;
	}
	
	/**
	 * 
	 * @return		size of tile side length
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Checks if tile is being highlighted
	 * 
	 * @param B		the instance of Battleship class for mouse detection
	 * 
	 * @return		if tile isHighlighted
	 */
	public boolean isHighlighted(Battleship B) {
		b = B;
		if (b.mouseX > x && b.mouseX < xShift && b.mouseY > y && b.mouseY < yShift) {
			return true;
		}
		else
			return false;
	}
	
	/**
	 * 
	 * @return		color when highlighted
	 */
	public int getColorHighlight() {
		return highlightedColor;
	}
	
	/**
	 * 
	 * @return		color when normal
	 */
	public int getColorNormal() {
		return normalColor;
	}
	
	/**
	 * 
	 * @return		color when hit
	 */
	public int getColorHit() {
		return hitColor;
	}
	
	/**
	 * 
	 * @return		color when miss
	 */
	public int getColorMiss() {
		return missColor;
	}
	
	/**
	 * 
	 * @return		if tile has been clicked
	 */
	public boolean clicked() {
		return clicked;
	}
	
	/**
	 * Mark tile as clicked
	 */
	public void click() {
		clicked = true;
	}
	
}
