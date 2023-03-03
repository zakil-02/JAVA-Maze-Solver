package Labyrinthe;

/**

Represents a box in a maze as a vertex in a graph.
*/
public abstract class MazeBox implements graph.Vertex{
	private final int x;
	private final int y;
	private final Maze maze;
	private final String label;
	private final boolean isWall;
	
	/**

	Constructs a new MazeBox with the specified label, position, wall status, and parent maze.
	@param label the label for the new MazeBox
	@param x the x-coordinate of the new MazeBox in the maze
	@param y the y-coordinate of the new MazeBox in the maze
	@param isWall true if the new MazeBox represents a wall, false if it represents a passage
	@param maze the parent maze that the new MazeBox belongs to
	*/
	public MazeBox(String label,int x, int y, boolean isWall, Maze maze) {
		this.label=label;
		this.x=x;
		this.y=y;
		this.maze=maze;
		this.isWall=isWall;
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
	/**

	Returns a boolean value indicating whether this vertex represents a wall in the maze.
	@return true if this vertex represents a wall, false otherwise
	*/
	public Boolean getIsWall() {
		return isWall;
	}
	
	

}
