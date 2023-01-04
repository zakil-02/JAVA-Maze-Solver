package Labyrinthe;

public abstract class MazeBox implements graph.Vertex{
	private final int x;
	private final int y;
	private final Maze maze;
	private final String label;
	
	public MazeBox(String label,int x, int y, Maze maze) {
		this.label=label;
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
	public Maze getMaze() {
		return maze;
	}
	public String getLabel() {
		return label;
		
	}
	
	

}
