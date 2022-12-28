package tp04;

import java.io.IOException;
import java.util.*;
import Labyrinthe.*;

public class MainTest {
	public static void main(String[] args) throws MazeReadingException, IOException{
		List<List<MazeBox>> boxes = new ArrayList<>();
		for (int i=0; i<10;i++) {
			boxes.add(new ArrayList<>());
		}
		Maze maze = new Maze(10, 10, boxes);
		maze.initFromTextFile("data/labyrinthe.maze");
		maze.saveToTextFile("data/labyrinthe2.maze");
	}

}
