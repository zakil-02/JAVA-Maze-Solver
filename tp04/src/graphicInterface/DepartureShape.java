package graphicInterface;

import java.awt.Color;
/**
 * A class that extends the BoxShape class to represent the type Departure.
*/
public class DepartureShape extends BoxShape{
	private static final long serialVersionUID = 1L;
	/**
	 * Creates an empty shape object with the specified x and y coordinates, a label of "D", and a blue color.
	 * @param x the x coordinate of the shape.
	 * @param y the y coordinate of the shape.
	*/
	public DepartureShape(int x, int y) {
		super(x, y, "D", Color.BLUE);
	}

}
