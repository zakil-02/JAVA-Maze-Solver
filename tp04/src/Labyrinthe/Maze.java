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
	public List<List<MazeBox>> getBoxes(){
		return boxes;
	}
	public ArrayList<Vertex> getSuccessors(Vertex s) {
		/*
		En effet, Si la liste des successeurs ne considere pas les WallBoxes, et le startVertex n'est pas un WallBox alors notre 
		chemin ne va jamais contenir des wallBoxes. Cela nous decharge d'enlever les WallBoxes de la liste de AllVertexes
		
		Il y'en a 6 voisins à déterminer en fonction de la parité de i l'indice de la ligne.
		*/
		
		ArrayList<Vertex> res = new ArrayList<Vertex>();
		MazeBox box = (MazeBox) s ;
		int i = box.getX();
		int j = box.getY();
		try {
			MazeBox Neighbour1 = boxes.get(i).get(j-1);
			if(!Neighbour1.getIsWall()){
				res.add(Neighbour1);
			}}catch( Exception e) {};
		try {
			MazeBox Neighbour2 = boxes.get(i).get(j+1);
			if(!Neighbour2.getIsWall()){
				res.add(Neighbour2);
			}}catch( Exception e) {};	
		try {
			MazeBox Neighbour3 = boxes.get(i-1).get(j);
			if(!Neighbour3.getIsWall()){
				res.add(Neighbour3);
			}}catch( Exception e) {};	
		try {
			MazeBox Neighbour4 = boxes.get(i+1).get(j);
			if(!Neighbour4.getIsWall()){
				res.add(Neighbour4);
			}}catch( Exception e) {};
		try {
			MazeBox Neighbour5;
			if (i%2==0) {
				Neighbour5 = boxes.get(i-1).get(j-1);
			}else {Neighbour5 = boxes.get(i-1).get(j+1);}
			
			if(!Neighbour5.getIsWall()){
				res.add(Neighbour5);
			}}catch( Exception e) {};
		try {
			MazeBox Neighbour6;
			if (i%2==0) {
				Neighbour6 = boxes.get(i+1).get(j-1);
			}else {Neighbour6 = boxes.get(i+1).get(j+1);}
			
			if(!Neighbour6.getIsWall()){
				res.add(Neighbour6);
			}}catch( Exception e) {};
	/*
	 * J'ai adopté try pour ne pas sortir du labyrinthe ni tomber sur un WallBox plutot que de faire une instruction if qui 
	 * va s'assurer de l'existence du voisin avant de l'appeler.		
	 */
	return res;
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
		/*getDistance ne va vraiment servir que s'il y avait 
		  des poids, pour notre cas le poids est la fonction qui 
		  associe 1 a chaque 2 sommets voisins.*/
		if (getSuccessors(departure).contains(arrival)) {
			return 1;
		}
		return Integer.MAX_VALUE;
		
		
	}
	public final void initFromTextFile(String fileName) throws MazeReadingException, IOException{
		BufferedReader br=null;
		try{
			br = new BufferedReader(new FileReader(fileName));
			String line= br.readLine();
			int lineNumber =1;
			int compteurDeparts = 0;// A utiliser pour compter le nombre de "D" dans le fichier.
			int compteurArrivals =0;// A utiliser pour compter le nombre de "A" dans le fichier.
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
						compteurArrivals++;
						break;
					case "D":
						boxes.get(lineNumber-1).add(new DepartureBox(i, lineNumber, Maze.this));
						compteurDeparts++;
						break;
					default:
						throw new MazeReadingException(fileName, lineNumber, "Invalid format");
					}
					i++;                
				}
				line = br.readLine();
				lineNumber++;
			}
			if (compteurDeparts!=1 || compteurArrivals!=1) {//Le programme supporte une seule destination et un seul depart!
				throw new IOException("Multiple destinations/ departures have been received");
			}
			if (lineNumber-1!=length) {
				throw new MazeReadingException(fileName, lineNumber, "length not respected");
			}
			br.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {br.close();}
	}
	/* On adoptera PrintWriter pour representer le labyrinthe
	  sur un fichier
	 */
	public final void saveToTextFile(String fileName) {
		try {
			PrintWriter pw = new PrintWriter(new File(fileName));
			for (int i=0;i<length;i++) {
				for (int k=0;k<width;k++) {
					pw.print(boxes.get(i).get(k).getLabel());
				}
				pw.println();//Pour sauter la ligne et garder la forme.
				
			}
			pw.close();
			System.out.println("The file succussefully saved");
		}catch (Exception e){
			System.out.println("The file to save is invalid");
			
		}
	}
	
	
	
	
	

}
