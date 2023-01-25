package graphicInterface;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MazeAppModel {
	private final List<ChangeListener> listeners = new ArrayList<ChangeListener>() ;
	//Les dimension de la maze.
	private int width;
	private int height;
	private boolean edited;
	

	public MazeAppModel() {
		width=8;
		height=8;
		edited=false;
		
	}
	
	public void addObserver(ChangeListener listener) {
	listeners.add(listener) ;
	}
	public int getWidth() {return width;}
	public void setWidth(int width) {
		this.width = width ;
		edited = true ; 
		stateChanges();
	}
	//
	public boolean isEdited() {
		return edited;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
		edited=true;
		stateChanges();
	}
	public void stateChanges() {
		ChangeEvent evt = new ChangeEvent(this);
		for(ChangeListener listener : listeners) {
			listener.stateChanged(evt);
		}
	}
	
	

}
