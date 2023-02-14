package mainPackage;
import java.io.IOException;
import java.util.*;
import Labyrinthe.*;
public class MainTest {
	public static void main(String[] args) throws MazeReadingException, IOException{
		//Creation d'un tableau pour representer l'attribut boxes de Maze.
		List<List<MazeBox>> boxes = new ArrayList<>();
		for (int i=0; i<10;i++) {
			boxes.add(new ArrayList<>());
		}
		//Creation de l'objet Maze
		Maze maze = new Maze(6, 6, boxes);
		maze.initFromTextFile("data/labyrinthe.maze");
		maze.saveToTextFile("data/labyrinthe2.maze");
		
	}

}
