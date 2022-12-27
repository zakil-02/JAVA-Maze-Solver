package Labyrinthe;
import java.util.*;
import graph.Vertex;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class Maze implements graph.Graph, graph.Distance{
	private final int length;
	private final int width;
	private List<List<MazeBox>> boxes;
	public Maze(int length, int width, List<List<MazeBox>> boxes) {
		this.length=length;
		this.width=width;
		this.boxes=boxes;
	}
	public int getLength() {
		return length;
	}
	public int getWidth() {
		return width;
	}
	public ArrayList<Vertex> getSuccessors(Vertex s) {
		MazeBox box =(MazeBox) s;
		int x=box.getX();
		int y=box.getY();
		ArrayList<Vertex> boxSucc = new ArrayList<>();
		int[] ids = {0,1,0,-1,-1,0,-1,1,1,1,1,0};
		//c'est une liste qui va nous aider a preciser les 6 voisins de chaque box
		for(int k=0;k<11;k++) {
			int i =ids[k];
			int j= ids[k+1];
			if (i< length & j < width) {
				boxSucc.add(boxes.get(x+i).get(y+j));
			}
		}
		return boxSucc;
		
	}
	public ArrayList<Vertex> getAllVertexes(){
		ArrayList<Vertex> sommets = new ArrayList<>();
		for (int i=0;i<length;i++) {
			for (int k=0;k<width;k++) {
				Vertex v = (Vertex) boxes.get(i).get(k);
				sommets.add(v);
			}
		}
		return sommets;
		
	}
	public int getDistance(Vertex departure,Vertex arrival) {
		MazeBox dpBox = (MazeBox) departure;
		MazeBox arrBox = (MazeBox) arrival;
		
		
	}
	public final void initFromTextFile(String fileName) throws MazeReadingException, IOException{
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line= br.readLine();
			int lineNumber =1;
			while (line != null) {
				if (line.length()!= width) {
					 throw new MazeReadingException(fileName, lineNumber, "Width not respected");
				}
				int i=0;
				while (i<width) {
					String label=String.valueOf(line.charAt(i));
					switch (label){
					//il y a 4 cas : wall, empty, arrival ou departure.
					case "W":
						boxes.get(lineNumber-1).add(new WallBox(i, lineNumber, Maze.this));
						break;
					case "E":
						boxes.get(lineNumber-1).add(new EmptyBox(i, lineNumber, Maze.this));
						break;
					case "A":
						boxes.get(lineNumber-1).add(new ArrivalBox(i, lineNumber, Maze.this));	
						break;
					case "D":
						boxes.get(lineNumber-1).add(new DepartureBox(i, lineNumber, Maze.this));
						break;
					default:
						throw new MazeReadingException(fileName, lineNumber, "Invalid format");
						
					
					}  
					i++;                
				}
				line = br.readLine();
				lineNumber++;
			
			}
			if (lineNumber-1!=length) {
				throw new MazeReadingException(fileName, lineNumber, "length not respected");
			}
			br.close();
		}finally {}
			
			/**String line= br.readLine();
			while (line != null) {
				System.out.println(line);
				line=br.readLine();
			}
			br.close();*/
		
		
		
	}
	public final void saveToTextFile(String fileName) {
		try {
			PrintWriter pw = new PrintWriter(new File(fileName));
			for (int i=0;i<width;i++) {
				String line="";
				for (int k=0;k<width;k++) {
					line.concat(boxes.get(i).get(k).getLabel());
				}
				pw.printf(line);
				
				
			}
			System.out.println("The file succussefully saved");
		}catch (IOException e){
			System.out.println("The file to save is invalid");
			
		}
	}
	
	
	
	
	

}
