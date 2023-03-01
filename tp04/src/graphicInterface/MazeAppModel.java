package graphicInterface;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Labyrinthe.*;
import graph.*;

/**
 * The MazeAppModel class is a class that represent the model of our application adopting the MVC structure.
 */
public class MazeAppModel {
	private final List<ChangeListener> listeners = new ArrayList<ChangeListener>() ;
	private int width;
	private int height;
	private String selectedType;
	private BoxShape[][] boxes;
	private int nArrivals=0;
	private int nDepartures=0;
	private static final int startPixel=70;
    private static final int size=30;
    
    /**
     * Creates a new instance of the MazeAppModel class with default values.
    */
	public MazeAppModel() {
		selectedType=null;
		//I chose to have as default a 10x10 maze
		width=10;
		height=10;
        boxes = new BoxShape[width][height];
        //These 2 float were calculated in the way that the shapes be near to each other, they depend on size of the shape which is fixed in 30pixel.
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
	/**
	 * Adds a ChangeListener to the list of listeners for this MazeAppModel.
	 * @param listener The ChangeListener to be added.
	*/
	public void addObserver(ChangeListener listener) {
	listeners.add(listener) ;
	}
	
	/**
	 * Notifies all the listeners that they should update their states.
	*/
	public void stateChanges() {
		ChangeEvent evt = new ChangeEvent(this);
		for(ChangeListener listener : listeners) {
			listener.stateChanged(evt);
		}
	}
	
	/**
	 * Saves the boxes to a file at the given path.
	 * @param path the path where the file should be saved.
	 * @throws Exception if an error occurs while saving the file.
	*/
	public void saveBoxesInFile(String path) throws Exception{
		try {
			PrintWriter pw = new PrintWriter(new File(path));
			for (int i=0;i<width;i++) {
				for (int k=0;k<height;k++) {
					pw.print(boxes[i][k].getLabel());
				}
				pw.println();//Jump the line to keep the maze format. 
			}
			pw.close();
		}catch (Exception e){
			e.getMessage();
		}
	}
	/**
	 * Returns the number of lines of a file.
	 * @param file the file to determine the width of.
	 * @return the width of the file in characters.
	 * @throws IOException if an I/O error occurs while reading the file.
	*/
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
	
	
	/**
	 * Imports a maze from a file and initializes the maze accordingly.
	 * @param file the file to import the maze from.
	 * @throws MazeReadingException if there is an error while parsing the file as a maze.
	 * @throws IOException if an I/O error occurs while reading the file.
	*/
	public void importFromOs(File file) throws MazeReadingException, IOException{
		BufferedReader br=null;
		try{
			br = new BufferedReader(new FileReader(file.getPath()));
			String line= br.readLine();
			//Updating dimensions and reset the maze with them.
			this.height=line.length();
			this.width= widthFileCounter(file);
			reset(width, height);
			
			//To display the actual dimensions in the two fields.
			MainApp mainApp = (MainApp)listeners.get(0);
			mainApp.getMainPanel().getConfigPanel().getwField().setText(String.valueOf(width));
			mainApp.getMainPanel().getConfigPanel().gethField().setText(String.valueOf(height));
			
			int lineNumber =1;
			while (line!= null) {
				if (line.length()!= height) {
					br.close();
					throw new MazeReadingException(file.getPath(), lineNumber, "Width not respected");
				}
				int i=0;
				while (i<height) {
					String label=String.valueOf(line.charAt(i));
					BoxShape box = boxes[lineNumber-1][i];
					//We have 4 cases : Wall, Departure, Arrival or Empty.
					switch (label){
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
				line = br.readLine(); //To the next line.
				lineNumber++;
			}
			br.close();
		}catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		stateChanges();
	}
	
	/**
	 * Solves the maze by creating a text file representation of the actual maze, initializing
	 * the maze with the text file, getting the solution of the maze, and updating the
	 * boxes to show the solution.
	 * @throws MazeReadingException If there is an error reading the maze file.
	 * @throws Exception If there is any other exception thrown.
	*/
	public void solve() throws MazeReadingException, Exception{
		//We will first save the actual maze in a file as a processingData.
		saveBoxesInFile("processingData/ActualMaze");
		
		//Maze Instancing.
		List<List<MazeBox>> map = new ArrayList<>();
		for (int i=0; i<width;i++) {
			map.add(new ArrayList<>());
		}
		Maze maze = new Maze(width,height, map);
		//Initializing the maze object with the actualMaze.
		maze.initFromTextFile("processingData/ActualMaze");
		
		//Getting the solution path, and this sublist is to remove the departure and the arrival from the path so that we keep their color displayed.
		List<Vertex> cheminSolution = maze.getSolution().subList(1, maze.getSolution().size()-1);
		
		for (Vertex vertex:cheminSolution) {
			MazeBox mb=(MazeBox) vertex;
			//We always keep this convention : X coordinate--> columns, Y coordinate--> lines.
			int i=mb.getY();
			int j=mb.getX();
			BoxShape bs=boxes[i][j];
			boxes[i][j]= new SolutionShape(bs.getX(), bs.getY());
		}
		stateChanges();
	}
	
	
	/**
	 * Reset the maze by creating a new matrix Boxes with the new dimensions given as arguments, filled with EmptyShapes.
	 * @param width the width to initialize the maze with.
	 * @param height the height to initialize the maze with.
	*/
	public void reset(int width, int height) {
		this.width=width;
		this.height=height;
		setBoxes(new BoxShape[width][height]);
		//These 2 float were calculated in the way that the shapes be near to each other, they depend on size of the shape which is fixed in 30pixel.
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
        this.nDepartures=0;
        this.nArrivals=0;
		stateChanges();
	}
	
	/**
	 * Saves the solution in a Text file given as argument.
	 * @param file the file where we want to store our solution.
	 * @throws Exception if an error occurs while saving the file.
	*/
	public void saveSolution(File file) {
		try {saveBoxesInFile(file.getPath());}catch(Exception e){e.getMessage();}
		
	}
	
	
	//Getters and setters.
	public int getWidth() {return width;}
	public void setWidth(int width) {
		this.width = width ; 
		stateChanges();
	}
	public int getHeight() {return height;}
	public void setHeight(int height) {
		this.height = height;
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
