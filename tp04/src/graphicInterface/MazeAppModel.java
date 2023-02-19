package graphicInterface;
import java.util.*;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Labyrinthe.Maze;
import Labyrinthe.MazeBox;
import Labyrinthe.MazeReadingException;
import graph.*;


public class MazeAppModel {
	private final List<ChangeListener> listeners = new ArrayList<ChangeListener>() ;
	//Les dimension de la maze.
	private int width;
	private int height;
	private String selectedType;
	private BoxShape[][] boxes;
	private static final int startPixel=70;
    private static final int size=30;

	public MazeAppModel() {
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
		stateChanges();
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
		stateChanges();
	}
	public void stateChanges() {
		ChangeEvent evt = new ChangeEvent(this);
		for(ChangeListener listener : listeners) {
			listener.stateChanged(evt);
		}
	}
	public void saveBoxesInFile() throws Exception{
		try {
			String path="processingData/ActualMaze"; //On choisit un emplacement pour mettre ce fichier qui 
			PrintWriter pw = new PrintWriter(new File(path));
			for (int i=0;i<height;i++) {
				for (int k=0;k<width;k++) {
					pw.print(boxes[i][k].getLabel());
				}
				pw.println();//Pour sauter la ligne et garder la forme.
				
			}
			pw.close();
		}catch (Exception e){
			throw new Exception("The file to save is invalid");
		}
	}
	
	
	public void solve() throws MazeReadingException, Exception{
		//Il faut tout d'abord créer un fichier text qui représente le labyrinthe à partir de ce qui est affiché.
		saveBoxesInFile();
		
		List<List<MazeBox>> map = new ArrayList<>();
		for (int i=0; i<width;i++) {
			map.add(new ArrayList<>());
		}
		//Instance de Maze
		Maze maze = new Maze(width,height, map);
		//Initialisons maze avec notre fichier text
		maze.initFromTextFile("processingData/ActualMaze");
		
		List<Vertex> cheminSolution = maze.getSolution().subList(1, maze.getSolution().size()-1);
		
		for (Vertex vertex:cheminSolution) {
			MazeBox mb=(MazeBox) vertex;
			int i=mb.getY();
			int j=mb.getX();
			
			boxes[i][j].setColor(Color.DARK_GRAY);
			
		}
		stateChanges();
	}
	
	
	//Méthode pour le reset du labyrinthe.
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
