package mainPackage;

import java.util.ArrayList;
import java.util.List;
import Labyrinthe.*;
import graph.*;

public class Main {
	public static void main(String[] args) throws MazeReadingException, Exception{
		//Creation d'un tableau pour représenter l'attribut boxes de Maze.
		List<List<MazeBox>> boxes = new ArrayList<>();
		for (int i=0; i<10;i++) {
			boxes.add(new ArrayList<>());
		}
		//Instance de Maze
		Maze maze = new Maze(10,10, boxes);
		//Initialisons maze avec notre fichier text
		maze.initFromTextFile("data/labyrinthe.maze");
		//List de tous les sommets
		List<Vertex> sommets=maze.getAllVertexes();
		//Instance de ShortestPath
		ShortestPaths sP = new ShortestPathsImpl(sommets);
		//Instance de ProcessedVertexes
		ProcessedVertexes pV= new ProcessedVertexesImpl(maze.getLength()*maze.getWidth());
		//Instance de MinDistance
		MinDistance mD = new MinDistanceImpl(sommets);
		//casting de maze comme Distance
		Distance distance=(Distance) maze;
		//Casting de maze comme graph
		Graph graph = (Graph) maze;
		
		
		
		
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
		
		System.out.println("Le plus court chemin à suivre :");
		System.out.println(cheminSolution);
		
		System.out.println("Le resultat:");
		//Affichage de la solution dans la console :
		
		for (int i=0;i<maze.getLength();i++) {
			for (int j=0;j<maze.getWidth();j++) {
				MazeBox box = maze.getBoxes().get(i).get(j);
				//Je veux laisser visibles A et D, d'ou les 2 dernières conditions
				if (cheminSolution.contains(box) && !box.equals(startVertex) && !box.equals(endVertex)) {
					System.out.print("\033[34m* \033[0m");//Tracage du chemin en bleu.
				}else {
					System.out.print(box.getLabel()+" ");
				}
			}
			System.out.println();
			if (i%2==0) {System.out.print(" ");}
			//Tout cela pour construire la forme du labyrinthe hexagonal.	
		}
			
	} 
	

}
