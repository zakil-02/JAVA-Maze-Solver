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
		
	} 
	

}
