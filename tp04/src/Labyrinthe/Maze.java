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
			//Le programme supporte une seule destination et un seul depart!
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
			//e.printStackTrace();
			e.getMessage();
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
	
	public List<Vertex> getSolution() throws Exception{
			
		//List de tous les sommets
		List<Vertex> sommets=getAllVertexes();
		//Instance de ShortestPath
		ShortestPaths sP = new ShortestPathsImpl(sommets);
		//Instance de ProcessedVertexes
		ProcessedVertexes pV= new ProcessedVertexesImpl(getLength()*getWidth());
		//Instance de MinDistance
		MinDistance mD = new MinDistanceImpl(sommets);
		//casting de maze comme Distance
		Distance distance=(Distance) this;
		//Casting de maze comme graph
		Graph graph = (Graph) this;
		
		//Recherche du sommet de depart et du sommet d'arrivée:
		Vertex startVertex=sommets.get(0);// Cette valeur est arbitraire apres on cherchera le vrai startVertex.
		Vertex endVertex=sommets.get(1);// Cette valeur est arbitraire apres on cherchera le vrai endVertex.
		//Je n'ai pas fais de try car j'ai déja programmer une exception qui se lève si le fichier ne contient pas un unique D et un unique A.
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
			
		//Application de l'algorithme de Dijkstra
		sP = Dijkstra.dijkstra(graph,startVertex, endVertex, pV, mD, distance, sP);
		
		//Extraction du chemin solution.
		List<Vertex> cheminSolution = sP.getShortestPath(endVertex);
		return cheminSolution;
			
		}
	
	
	
	
	

}
