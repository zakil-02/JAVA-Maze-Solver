package Labyrinthe;
import java.util.*;
import graph.Vertex;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze implements graph.Graph {
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
	public final static void initFromTextFile(String fileName) {
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			/**String line= br.readLine();
			while (line != null) {
				System.out.println(line);
				line=br.readLine();
			}
			br.close();*/
		}catch (IOException e) {
			System.out.print("La lecture du fichier labyrinthe a échoué");
		}
		
		
		
	}
	
	
	
	
	

}
