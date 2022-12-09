package Labyrinthe;
import java.util.*;
import graph.Vertex;

public class Maze implements graph.Graph {
	private final int length;
	private final int width;
	public Maze(int length, int width) {
		this.length=length;
		this.width=width;
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
		List<MazeBox> boxSucc = new ArrayList<>();
		
	}
	public ArrayList<Vertex> getAllVertexes(){
		
		
		
	}
	public int getDistance(Vertex departure,Vertex arrival) {
		MazeBox dpBox = (MazeBox) departure;
		MazeBox arrBox = (MazeBox) arrival;
		
		
	}
	public final initFromTextFile(String fileName) {
		//voir les classes API NIO2 et BufferedReader
	}
	
	
	
	
	

}
