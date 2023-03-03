package Labyrinthe;
import java.util.*;

import graph.Dijkstra;
import graph.Distance;
import graph.Graph;
import graph.MinDistance;
import graph.MinDistanceImpl;
import graph.ProcessedVertexes;
import graph.ProcessedVertexesImpl;
import graph.ShortestPaths;
import graph.ShortestPathsImpl;
import graph.Vertex;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

/**

Represents a maze as a graph with a distance function that assigns a weight of 1 to each edge in the graph.
*/
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
		Si la liste des successeurs ne considère pas les WallBoxes, et le startVertex n'est pas un WallBox alors notre 
		chemin ne va jamais contenir des wallBoxes. Cela nous décharge d'enlever les WallBoxes de la liste de AllVertexes.
		
		Il y'en a 6 voisins à déterminer en fonction de la parité de i l'indice de la ligne.
		*/
		/**

		Returns a list of vertices that are successors of the given vertex in the graph.
		A vertex is considered a successor if there is a directed edge from the given vertex to the successor.
		@param s the vertex whose successors are to be retrieved
		@return an ArrayList of vertices that are successors of the given vertex in the graph
		*/
		ArrayList<Vertex> res = new ArrayList<Vertex>();
		MazeBox box = (MazeBox) s;
		int j = box.getX();  //par définition, abscisse --> colonnes et ordonnées --> lignes
		int i = box.getY();
		int rows=length;
		int cols=width;
		//On ajoute en cas de validité les 4 voisins qui ne dépendent pas de la parité de i. 
		if (i > 0) {
	        MazeBox neighbour = boxes.get(i-1).get(j);
	        if (!neighbour.getIsWall()) {
	            res.add(neighbour);
	        }
	    }
	    if (i < rows - 1) {
	        MazeBox neighbour = boxes.get(i+1).get(j);
	        if (!neighbour.getIsWall()) {
	            res.add(neighbour);
	        }
	    }
	    if (j > 0) {
	        MazeBox neighbour = boxes.get(i).get(j-1);
	        if (!neighbour.getIsWall()) {
	            res.add(neighbour);
	        }
	    }
	    if (j < cols - 1 ) {
	        MazeBox neighbour = boxes.get(i).get(j+1);
	        if (!neighbour.getIsWall()) {
	            res.add(neighbour);
	        }
	    }
	    
	    //Pour les 2 voisins qui restent, ils dépendent de la parité de i. 
	    if (i % 2 == 0) {
	        if (i > 0 && j > 0) {
	            MazeBox neighbour = boxes.get(i-1).get(j-1);
	            if (!neighbour.getIsWall()) {
	                res.add(neighbour);
	            }
	        }
	        if (i < rows - 1 && j > 0) {
	            MazeBox neighbour = boxes.get(i+1).get(j-1);
	            if (!neighbour.getIsWall()) {
	                res.add(neighbour);
	            }
	        }
	    }else {
	    	if (i > 0 && j < cols - 1) {
	            MazeBox neighbour = boxes.get(i-1).get(j+1);
	            if (!neighbour.getIsWall()) {
	                res.add(neighbour);
	            }
	        }
	        if (i < rows - 1 && j < cols - 1) {
	            MazeBox neighbour = boxes.get(i+1).get(j+1);
	            if (!neighbour.getIsWall()) {
	                res.add(neighbour);
	            }
	        }
	    	
	    }
	
		return res;
	}
	
	/**

	Returns a list of all vertices in the graph.
	@return an ArrayList of all vertices in the graph
	*/
	public ArrayList<Vertex> getAllVertexes(){
		ArrayList<Vertex> sommets = new ArrayList<>();
		for (int i=0;i<width;i++) {
			for (int k=0;k<length;k++) {
				Vertex v = (Vertex) boxes.get(k).get(i);
				sommets.add(v);
			}
		}
		return sommets;
		
	}
	/**

	Returns the distance between two neighbors in the graph, represented by their departure and arrival vertices.
	The distance is defined as the length of the shortest path between the two vertices in the graph.
	@param departure the vertex from which the distance is to be measured
	@param arrival the vertex to which the distance is to be measured
	@return the weight of this arc (departure, arrival) in the graph which is 1 if they are neighbors and Integer.MAX_VALUE if not.
	*/
	public int getDistance(Vertex departure,Vertex arrival) {
		if (getSuccessors(departure).contains(arrival)) {
			return 1;
		}
		return Integer.MAX_VALUE;
	}
	
	/**

	Reads a maze from a text file and initializes the object with the maze data.
	@param fileName the name of the text file containing the maze data
	@throws MazeReadingException if there is an error with the file data.
	@throws IOException if there is an error reading the file
	*/
	public final void initFromTextFile(String fileName) throws MazeReadingException, IOException{
		BufferedReader br=null;
		try{
			br = new BufferedReader(new FileReader(fileName));
			String line= br.readLine();
			int lineNumber =1;
			int compteurDeparts = 0;
			int compteurArrivals =0;
			while (line != null) {
				if (line.length()!= width) {
					 throw new MazeReadingException(fileName, lineNumber, "Width not respected");
				}
				int i=0;
				while (i<width) {
					String label=String.valueOf(line.charAt(i));
					switch (label){
					//Only 4 letters are valid : A, D, E, W.
					case "W":
						boxes.get(lineNumber-1).add(new WallBox(i, lineNumber-1, Maze.this));
						break;
					case "E":
						boxes.get(lineNumber-1).add(new EmptyBox(i, lineNumber-1, Maze.this));
						break;
					case "A":
						boxes.get(lineNumber-1).add(new ArrivalBox(i, lineNumber-1, Maze.this));	
						compteurArrivals++;
						break;
					case "D":
						boxes.get(lineNumber-1).add(new DepartureBox(i, lineNumber-1, Maze.this));
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
			//Only one departure and one arrival are supported!
			if (compteurDeparts<1) {
				throw new IOException("Departure has not been specified");
			}
			if (compteurDeparts>1) {
				throw new IOException("Multiple departures have been received");
			}
			if (compteurArrivals<1) {
				throw new IOException("Destination has not been specified");
			}
			if (compteurArrivals>1) {
				throw new IOException("Multiple destinations have been received");
			}
			if (lineNumber-1!=length) {
				throw new MazeReadingException(fileName, lineNumber, "length not respected");
			}
			br.close();
		}catch (Exception e) {
			e.getMessage();
		}
		finally {br.close();}
	}
	
	public final void saveToTextFile(String fileName) {
		try {
			PrintWriter pw = new PrintWriter(new File(fileName));
			for (int i=0;i<length;i++) {
				for (int k=0;k<width;k++) {
					pw.print(boxes.get(i).get(k).getLabel());
				}
				pw.println();
				
			}
			pw.close();
			System.out.println("The file succussefully saved");
		}catch (Exception e){
			System.out.println("The file to save is invalid");
			
		}
	}
	
	/**

	Returns a list of vertices that represents the solution to the maze.
	The solution is defined as the path from the start vertex to the end vertex that visits all cells in the maze exactly once.
	@return a List of vertices that represents the solution to the maze
	@throws Exception if there is an error finding the solution
	*/
	public List<Vertex> getSolution() throws Exception{
		List<Vertex> sommets = getAllVertexes();
		ShortestPaths sP = new ShortestPathsImpl(sommets);
		ProcessedVertexes pV= new ProcessedVertexesImpl(getLength()*getWidth());
		MinDistance mD = new MinDistanceImpl(sommets);
		//casting maze as Distance
		Distance distance=(Distance) this;
		//Casting maze as a Graph
		Graph graph = (Graph) this;
		
		//Searching for the startVertex and the endVertex.
		Vertex startVertex=sommets.get(0);
		Vertex endVertex=sommets.get(1);
		boolean sFlag=false, eFlag = false;
		int k=0;
		while (!(sFlag & eFlag) & k<sommets.size()) {
			Vertex s=sommets.get(k);
			if (s.getLabel()=="D") {
				startVertex=s;
				sFlag=true;
			}
			if (s.getLabel()=="A") {
				endVertex=s;
				eFlag=true;
			}
			k++;  
		}
		
		sP = Dijkstra.dijkstra(graph,startVertex, endVertex, pV, mD, distance, sP);
		//Extracting the solution list. 
		List<Vertex> cheminSolution = sP.getShortestPath(endVertex);
		return cheminSolution;
			
		}
	
	
	
	
	

}
