package graphicInterface;
import java.util.*;
import java.awt.Color;
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
	private String selectedType;
	private boolean edited;
	private BoxShape[][] boxes;
	private static final int startPixel=70;
    private static final int size=30;

	public MazeAppModel() {
		edited=false;
		selectedType=null;
		width=10;
		height=10;
        //La, on contruit une maze dont tous les boxes sont de type empty.
        boxes = new BoxShape[width][height];
        float d1=27;
        float d2=15;
        int l=0;
        for(int i=0;i<width;i++) {
        	if (i>=2 && i%2==0) {
					 l+=30;
        	}
			 for(int j=0;j<height;j++) {
				
				 if (i%2==0) {boxes[i][j]=new EmptyShape(startPixel+j*(2*size-7) ,(int)(startPixel +i*2*size -l ));}
				 else {boxes[i][j]=new EmptyShape((int)( startPixel +j*(2*size-7) + d1),(int)(startPixel +i*(2*size) - d2-l));}
				 
			 }
		 }
		
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
	public void solve() {
		//TODO 
	}
	public void reset(int width, int height) {
		this.width=width;
		this.height=height;
		setBoxes(new BoxShape[width][height]);
		float d1=27;
        float d2=15;
        int l=0;
        for(int i=0;i<width;i++) {
        	if (i>=2 && i%2==0) {
					 l+=30;
        	}
			 for(int j=0;j<height;j++) {
				
				 if (i%2==0) {boxes[i][j]=new EmptyShape(startPixel+j*(2*size-7) ,(int)(startPixel +i*2*size -l ));}
				 else {boxes[i][j]=new EmptyShape((int)( startPixel +j*(2*size-7) + d1),(int)(startPixel +i*(2*size) - d2-l));}
				 
			 }
		 }
		stateChanges();
	}

	public String getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
		stateChanges();
	}

	public BoxShape[][] getBoxes() {
		return boxes;
	}

	public void setBoxes(BoxShape[][] boxes) {
		this.boxes = boxes;
	}
	
	

}
