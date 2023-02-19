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
		
		List<Vertex> cheminSolution=maze.getSolution().subList(1, maze.getSolution().size()-1);
		System.out.println("Le plus court chemin à suivre :");
		System.out.println(cheminSolution);
		
		System.out.println("Le resultat:");
		//Affichage de la solution dans la console :
		
		for (int i=0;i<maze.getLength();i++) {
			for (int j=0;j<maze.getWidth();j++) {
				MazeBox box = maze.getBoxes().get(i).get(j);
				//Je veux laisser visibles A et D, d'ou les 2 dernières conditions
				if (cheminSolution.contains(box)) {
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
