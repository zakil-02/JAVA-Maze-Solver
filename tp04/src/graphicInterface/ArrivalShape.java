package graphicInterface;

import java.awt.Color;
/**
 * A class that extends the BoxShape class to represent the type Arrival.
*/
public class ArrivalShape extends BoxShape{
	private static final long serialVersionUID = 1L;
	/**
	 * Creates an empty shape object with the specified x and y coordinates, a label of "A", and a orange color.
	 * @param x the x coordinate of the shape.
	 * @param y the y coordinate of the shape.
	*/
	public ArrivalShape(int x, int y) {
		super(x, y, "A", Color.ORANGE);
	}

}
