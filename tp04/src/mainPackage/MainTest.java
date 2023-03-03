package mainPackage;
import java.io.IOException;
import java.util.*;
import Labyrinthe.*;

/**
 * A class to contain the main method to be executed to test InitFrom file and the save in file methods.
 */
public class MainTest {
	public static void main(String[] args) throws MazeReadingException, IOException{
		List<List<MazeBox>> boxes = new ArrayList<>();
		for (int i=0; i<10;i++) {
			boxes.add(new ArrayList<>());
		}
		//We just want to test, so we keep 10x10 size in the maze of labyrinthe.maze file.
		Maze maze = new Maze(10, 10, boxes);
		maze.initFromTextFile("data/labyrinthe.maze");
		maze.saveToTextFile("data/labyrinthe2.maze");
		
	}

}
