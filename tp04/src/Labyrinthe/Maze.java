package Labyrinthe;
import java.util.ArrayList;

import graph.Vertex;

public class Maze implements graph.Graph {
	private final ArrayList<MazeBox> maze;
	public Maze() {
		maze= new ArrayList<MazeBox>();
	}
	public ArrayList<Vertex> getSuccessors(Vertex s) {
		MazeBox box =(MazeBox) s;
		int x=box.getX();
		int y=box.getY();
		int n =maze.size();
		ArrayList boxSucc = new ArrayList<MazeBox>();
		 
	}
	public ArrayList<Vertex> getAllVertexes(){
		
		return maze;
		
	}
	public int getDistance(Vertex departure,Vertex arrival) {
		MazeBox dpBox = (MazeBox) departure;
		MazeBox arrBox = (MazeBox) arrival;
		
		
	}
	public final initFromTextFile(String fileName) {
		//voir les classes API NIO2 et BufferedReader
	}
	
	
	
	
	

}
