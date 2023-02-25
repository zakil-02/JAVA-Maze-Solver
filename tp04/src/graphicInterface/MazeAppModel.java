package graphicInterface;
import java.util.*;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Labyrinthe.ArrivalBox;
import Labyrinthe.DepartureBox;
import Labyrinthe.EmptyBox;
import Labyrinthe.Maze;
import Labyrinthe.MazeBox;
import Labyrinthe.MazeReadingException;
import Labyrinthe.WallBox;
import graph.*;


public class MazeAppModel {
	private final List<ChangeListener> listeners = new ArrayList<ChangeListener>() ;
	//Les dimension de la maze.
	private int width;
	private int height;
	private String selectedType;
	private BoxShape[][] boxes;
	private int nArrivals=0;
	private int nDepartures=0;
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
			for (int i=0;i<width;i++) {
				for (int k=0;k<height;k++) {
					pw.print(boxes[i][k].getLabel());
				}
				pw.println();//Pour sauter la ligne et garder la forme.
				
			}
			pw.close();
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	private int widthFileCounter(File file) throws IOException{
		int nLines=0;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file.getPath()));
			while (br.readLine() != null) {
            nLines++;
			}
			
		}catch(IOException e ) {e.getMessage();}
		
		return nLines;
	}
	
	
	public void importFromOs(File file) throws MazeReadingException, IOException{
		
		BufferedReader br=null;
		try{
			br = new BufferedReader(new FileReader(file.getPath()));
			String line= br.readLine();
			System.out.println(line);
			
			this.height=line.length();
			this.width= widthFileCounter(file);
			
			reset(width, height);
			MainApp mainApp = (MainApp)listeners.get(0);
			mainApp.getMainPanel().getConfigPanel().getwField().setText(String.valueOf(width));
			mainApp.getMainPanel().getConfigPanel().gethField().setText(String.valueOf(height));
			
			int lineNumber =1;
			while (line!= null) {
				if (line.length()!= height) {
					 throw new MazeReadingException(file.getPath(), lineNumber, "Width not respected");
				}
				System.out.println(line);
				int i=0;
				while (i<height) {
					String label=String.valueOf(line.charAt(i));
					System.out.println(label);
					BoxShape box = boxes[lineNumber-1][i];
					switch (label){
					//il y a 4 cas : wall, empty, arrival ou departure.
					case "W":
						boxes[lineNumber-1][i]= new WallShape(box.getX(), box.getY());
						break;
					case "E":
						boxes[lineNumber-1][i]= new EmptyShape(box.getX(), box.getY());
						break;
					case "A":
						boxes[lineNumber-1][i]= new ArrivalShape(box.getX(), box.getY());
						nArrivals++;
						break;
					case "D":
						boxes[lineNumber-1][i]= new DepartureShape(box.getX(), box.getY());
						nDepartures++;
						break;
					default:
						throw new MazeReadingException(file.getPath(), lineNumber, "Invalid format");
					}
					i++;                
				}
				line = br.readLine(); //on passe à ligne suivante.
				System.out.println(line);
				lineNumber++;
			}
			br.close();
		}catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		stateChanges();
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
		
		//J'ai fait la sublist pour enlever le depart et l'arrivée de la liste
		List<Vertex> cheminSolution = maze.getSolution().subList(1, maze.getSolution().size()-1);
		
		for (Vertex vertex:cheminSolution) {
			MazeBox mb=(MazeBox) vertex;
			int i=mb.getY();
			int j=mb.getX();
			
			boxes[i][j].setColor(Color.RED);
			
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

	public int getnDepartures() {
		return nDepartures;
	}

	public void setnDepartures(int nDepartures) {
		this.nDepartures = nDepartures;
	}

	public int getnArrivals() {
		return nArrivals;
	}

	public void setnArrivals(int nArrivals) {
		this.nArrivals = nArrivals;
	}
	
	

}
