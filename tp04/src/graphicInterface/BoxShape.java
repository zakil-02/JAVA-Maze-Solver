package graphicInterface;
import java.awt.*;

/**

An abstract class that extends the Polygon class to create an hexagonal box shape.
*/
public abstract class BoxShape extends Polygon {
	private static final long serialVersionUID = 1L;
	//I fixed the size of the shape
	private static final int size=30;
	//color of the box
	private final Color color;
	//Position (x,y) of the shape center in pixels.
	private final int x;
	private final int y;
	private final String label;
	/**
	 * Creates a new BoxShape object with the specified coordinates, label, and color.
	 * @param x the x coordinate of the box's upper-left corner
	 * @param y the y coordinate of the box's upper-left corner
	 * @param label the label to be displayed inside the box
	 * @param color the color of the box
	*/
	public BoxShape(int x, int y, String label, Color color) {
		super();
		this.x=x;
		this.y=y;
		this.color=color;
		this.label=label;
		//Adding the 6 points of the Hexagonal shape.
		this.addPoint(x, y+size);
		this.addPoint(x-dx(), y+dy());
		this.addPoint(x-dx(), y-dy());
		this.addPoint(x, y-size);
		this.addPoint(x+dx(), y-dy());
		this.addPoint(x+dx(), y+dy());
	}
	
	/**
	 * Calculates the change in x-coordinate for the hexagonal shape with the fixed size.
	 * @return the change in x-coordinate
	*/
	private static int dx() {
		return (int) Math.round(Math.sin(Math.toRadians(60)) * size) ;
	}
	/**
	 * Calculates the change in y-coordinate for the hexagonal shape with the fixed size.
	 * @return the change in y-coordinate
	*/
	private static int dy() {
		return (int) Math.round(Math.cos(Math.toRadians(60)) * size) ;
	}
	
	
    //Getters
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Color getColor() {
		return color;
	}
	public String getLabel() {
		return label;
	}
	

}
