package mainPackage;

import java.util.ArrayList;
import java.util.List;
import Labyrinthe.*;
import graph.*;
/**
 * A class to contain the main method to be executed to test the Dijkstra algorithm.
 */
public class Main {
	public static void main(String[] args) throws MazeReadingException, Exception{
		List<List<MazeBox>> boxes = new ArrayList<>();
		for (int i=0; i<10;i++) {
			boxes.add(new ArrayList<>());
		}
		//Instance of maze
		Maze maze = new Maze(10,10, boxes);
		//Initializing from file
		maze.initFromTextFile("data/labyrinthe.maze");
		
		List<Vertex> cheminSolution=maze.getSolution().subList(1, maze.getSolution().size()-1);
		System.out.println("The path to follow:");
		System.out.println(cheminSolution);
		
		System.out.println("The result:");
		
		//Printing the solution in the console
		for (int i=0;i<maze.getLength();i++) {
			for (int j=0;j<maze.getWidth();j++) {
				MazeBox box = maze.getBoxes().get(i).get(j);
				//We want to keep A and D visible.
				if (cheminSolution.contains(box)) {
					System.out.print("\033[34m* \033[0m");//Drawing the path in blue.
				}else {
					System.out.print(box.getLabel()+" ");
				}
			}
			System.out.println();
			if (i%2==0) {System.out.print(" ");}
			//All this is to show the hexagonal aspect.	
		}
			
	}
	

}
