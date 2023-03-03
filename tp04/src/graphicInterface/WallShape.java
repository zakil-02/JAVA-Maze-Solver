package graphicInterface;

import java.awt.Color;
/**
 * A class that extends the BoxShape class to represent the type Wall.
*/
public class WallShape extends BoxShape{
	private static final long serialVersionUID = 1L;
	/**
	 * Creates an empty shape object with the specified x and y coordinates, a label of "W", and a dark gray color.
	 * @param x the x coordinate of the shape.
	 * @param y the y coordinate of the shape.
	*/
	public WallShape(int x, int y) {
		super(x, y, "W", Color.DARK_GRAY);
	}

}
