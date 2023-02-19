package graphicInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.event.*;
public class BoxShape extends Polygon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//size of the box
	private static final int size=30;
	//color of the box
	private Color color;
	//Position (x,y) of the box
	private final String label;
	private final int x;
	private final int y;
	
	public BoxShape(int x, int y, String label, Color color) {
		super();
		this.x=x;
		this.y=y;
		this.color=color;
		this.label=label;
		//Ajouter les 6 points au polygone pour construire l'hexagone
		this.addPoint(x, y+size);
		this.addPoint(x-dx(), y+dy());
		this.addPoint(x-dx(), y-dy());
		this.addPoint(x, y-size);
		this.addPoint(x+dx(), y-dy());
		this.addPoint(x+dx(), y+dy());
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
	public static int dx() {
		return (int) Math.round(Math.sin(Math.toRadians(60)) * size) ;
	}
	public static int dy() {
		return (int) Math.round(Math.cos(Math.toRadians(60)) * size) ;
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
	public final void setColor(Color color) {
		this.color=color;
	}
	public String getLabel() {
		return label;
	}
	

}
