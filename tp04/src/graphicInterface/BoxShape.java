package graphicInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;

public class BoxShape extends Polygon{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//size of the box
	private final int size=30;
	//color of the box
	private Color color;
	//Position (x,y) of the box
	private final int x;
	private final int y;
	
	public BoxShape(int x, int y, Color color) {
		super();
		this.x=x;
		this.y=y;
		this.color=color;
	}
	private int[] getXarray() {
		int[] xArray = {0,0,0,0,0,0}; //pour regrouper les x 
		xArray[0]=x+size;
		//point2
		xArray[1]=x+dx();
		//point3
		xArray[2]=x-dx();
		//point4
		xArray[3]=x-size;
		//point5
		xArray[4]=x-dx();
		//point6
		xArray[5]=x+dx();
		
		return xArray;
	}
	private int[] getYarray() {
		int[] yArray = {0,0,0,0,0,0};// pour regrouper les y correspondants
		//point1
		yArray[0]=y;
		
		yArray[1]=y-dy();
		
		yArray[2]=y-dy();
		
		yArray[3]=y;
		
		yArray[4]=y+dy();
		
		yArray[5]=y+dy();
		
		return yArray;
		
		
	}
	
	// les deltas pour aller d'un point de l'hexagone au point voisin.
	public int dx() {
		return (int) Math.round(Math.cos(Math.toRadians(60)) * size) ;
	}
	public int dy() {
		return (int) Math.round(Math.sin(Math.toRadians(60)) * size) ;
	}
	
	
    //getters
	public final int getX() {
		return x;
	}
	public final int getY() {
		return y;
	}
	public final Color getColor() {
		return color;
	}
	//setters
	public final void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int[] xPoints = {size / 2, size, size, size / 2, 0, 0};
	    int[] yPoints = {0, 0, size / 2, size, size / 2, 0};
	    g.setColor(color);
	    g.fillPolygon(xPoints, yPoints, 6);
    }

}
