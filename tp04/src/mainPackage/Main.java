package mainPackage;

import java.util.ArrayList;
import java.util.List;
import Labyrinthe.*;
import graph.*;

public class Main {
	public static void main(String[] args) throws MazeReadingException, Exception{
		//Creation d'un tableau pour representer l'attribut boxes de Maze.
		List<List<MazeBox>> boxes = new ArrayList<>();
		for (int i=0; i<10;i++) {
			boxes.add(new ArrayList<>());
		}
		//Creation de l'objet Maze
		Maze maze = new Maze(10, 10, boxes);
		maze.initFromTextFile("data/labyrinthe.maze");
		List<Vertex> sommets=maze.getAllVertexes();
		ShortestPaths sP = new ShortestPathsImpl(sommets);
		ProcessedVertexes pV= new ProcessedVertexesImpl(maze.getLength()*maze.getWidth());
		MinDistance mD = new MinDistanceImpl(sommets);
		Distance distance=(Distance) maze;
		Graph graph = (Graph) maze;
		Vertex startVertex=sommets.get(0);// Cette valeur est arbitraire apres on cherchera le vrai startVertex.
		Vertex endVertex=sommets.get(1);// Cette valeur est arbitraire apres on cherchera le vrai endVertex.
		
		//Trouvons le sommet de depart et le sommet d'arrivee:
		
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
		List<Vertex> cheminSolution = sP.getShortestPath(endVertex);
		
		//Affichage de la solutioin dans la console :
		
		for (int i=0;i<maze.getLength();i++) {
			for (int j=0;j<maze.getWidth();j++) {
				MazeBox box = maze.getBoxes().get(i).get(j);
				if (cheminSolution.contains(box)) {
					System.out.print(".");//Tracage du chemin.
				}else {
					System.out.print(box.getLabel());
				}
			}
			System.out.println();//Pour sauter la ligne et garder la forme.	
		}
			
	} 
	

}
