package Labyrinthe;

public abstract class MazeBox implements graph.Vertex{
	private final int x;
	private final int y;
	private final Maze maze;
	
	public MazeBox(int x, int y, Maze maze) {
		this.x=x;
		this.y=y;
		this.maze=maze;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public String getLabel() {
		return Integer.toString(x) + ":" + Integer.toString(y);
		
	}
	
	

}
