package graphicInterface;

import java.awt.Color;
/**
 * A class that extends the BoxShape class to represent the type Solution.
*/
public class SolutionShape extends BoxShape{
	private static final long serialVersionUID = 1L;
	/**
	 * Creates an Solution shape object with the specified x and y coordinates, a label of "*", and a red color.
	 * @param x the x coordinate of the shape.
	 * @param y the y coordinate of the shape.
	*/
	public SolutionShape(int x, int y) {
		super(x, y, "*", Color.red);
	}

}
