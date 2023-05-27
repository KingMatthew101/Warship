import processing.core.PImage;

public class Button {
	// * ----- * Instance Variables * ----- * //
	
	// general and location info
	private int x, y, xShift, yShift, size, length, width;
	private int ID;
	private int shape; // 0 = square; 1 = circle;
	
	// stored text and/or image values and info
	private String name;
	private String text;
	private PImage image;
	
	private int textSize;
	
	// what??? I genuinely have no idea what this is doing. Please don't remove points ;)
	private int normalColor = 5;
	private int highlightedColor = 5;
	// upon further reflection, I may have been planning on eventually adding different colors when normal versus being highlighted with cursor
	
	
	// trash instance of main battleship because Processing likes to be the difficult child
	Battleship b;
	
	
	// * ----- * Constructors * ----- * //
	
	/**
	 * Class Constructor - Simple shape
	 * 
	 * @param X		the x position
	 * @param Y		the y position
	 * @param s		the side length size
	 * @param c		the desired shape (0 = square, 1 = circle)
	 */
	public Button(int X, int Y, int s, int c) {
		x = X;
		y = Y;
		size = s;
		length = s;
		width = s;
		xShift = x + size;
		yShift = y + size;
		
		x -= width / 2;
		y -= length / 2;
		
		shape = c;
	}
	
	
	/**
	 * Class Constructor - Simple shape with name and displayed text 
	 * 
	 * @param X		the x position
	 * @param Y		the y position
	 * @param s		the side length size
	 * @param c		the desired shape (0 = square, 1 = circle)
	 * @param n		the name of the object
	 * @param t		the text to display
	 * @param ts	the size of displayed text
	 */
	public Button(int X, int Y, int s, int c, String n, String t, int ts) {
		x = X;
		y = Y;
		size = s;
		length = s;
		width = s;
		xShift = x + size;
		yShift = y + size;
		
		x -= width / 2;
		y -= length / 2;
		
		name = n;
		text = t;
		shape = c;
		textSize = ts;
	}
	
	
	/**
	 * Class Constructor - Simple shape with name and displayed image
	 * 
	 * @param X		the x position
	 * @param Y		the y position
	 * @param s		the side length size
	 * @param c		the desired shape (0 = square, 1 = circle)
	 * @param n		the name of the object
	 * @param i		the image to display
	 */
	public Button(int X, int Y, int s, int c, String n, PImage i) {
		x = X;
		y = Y;
		size = s;
		length = s;
		width = s;
		xShift = x + size;
		yShift = y + size;
		
		x -= width / 2;
		y -= length / 2;
		
		name = n;
		image = i;
		shape = c;
	}
	
	
	/**
	 * Class Constructor - Specific shape
	 * 
	 * @param X		the x position
	 * @param Y		the y position
	 * @param w		the width length
	 * @param l		the height length
	 * @param c		the desired shape (0 = square, 1 = circle)
	 */
	public Button(int X, int Y, int w, int l, int c) {
		x = X;
		y = Y;
		length = l;
		width = w;
		xShift = x + width;
		yShift = y + length;
		
		x -= width / 2;
		y -= length / 2;
		
		shape = c;
	}
	
	
	/**
	 * Class Constructor - Specific shape with name and displayed text
	 * 
	 * @param X		the x position
	 * @param Y		the y position
	 * @param w		the width length
	 * @param l		the height length
	 * @param c		the desired shape (0 = square, 1 = circle)
	 * @param n		the name of the object
	 * @param t		the text to display
	 * @param ts	the size of displayed text
	 */
	public Button(int X, int Y, int w, int l, int c, String n, String t, int ts) {
		x = X;
		y = Y;
		length = l;
		width = w;
		xShift = x + width;
		yShift = y + length;
		
		x -= width / 2;
		y -= length / 2;
		
		name = n;
		text = t;
		shape = c;
		textSize = ts;
	}
	
	
	/**
	 * Class Constructor - Specific shape with name and displayed image
	 * 
	 * @param X		the x position
	 * @param Y		the y position
	 * @param w		the width length
	 * @param l		the height length
	 * @param c		the desired shape (0 = square, 1 = circle)
	 * @param n		the name of the object
	 * @param i		the image to display
	 */
	public Button(int X, int Y, int w, int l, int c, String n, PImage i) {
		x = X;
		y = Y;
		length = l;
		width = w;
		xShift = x + width;
		yShift = y + length;
		
		x -= width / 2;
		y -= length / 2;
		
		name = n;
		image = i;
		shape = c;
	}
	
	
	// * ----- * Core Methods * ----- * //
	
	/**
	 * 
	 * @return		x position
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @return		y position
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 
	 * @return		x position + width
	 */
	public int getXShift() {
		return xShift;
	}
	
	/**
	 * 
	 * @return		y position + length
	 */
	public int getYShift() {
		return yShift;
	}
	
	/**
	 * 
	 * @return		ship side length
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * 
	 * @return		ship length / height
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * 
	 * @return		ship width
	 */
	public int getWidth() {
		return width;
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
	 * Updates button and draws it on screen
	 * 
	 * @param B		the instance of Battleship class for drawing
	 */
	public void update(Battleship B) {
		b = B;
		
		if (shape == 0) {
			b.fill(200, 200, 200);
			b.rect(x, y, width, length);
			b.textSize(textSize);
			b.fill(0, 0, 0);
			b.text(text, x + ((width - b.textWidth(text)) / 2), y + ((length - textSize) / 2) + (textSize)); // TODO: adjust location of text!!!
		}
	}
}
