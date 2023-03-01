package graph;
import java.util.ArrayList;

/**
 * The Graph interface defines the methods required to manage a graph.
 */
public interface Graph {
	/**
	 * Returns a list of vertices that are successors of the specified vertex.
	 *
	 * @param s the vertex whose successors are to be returned
	 * @return a list of vertices that are successors of the specified vertex
	 */
	public ArrayList<Vertex> getSuccessors(Vertex s);
	/**
	 * Returns all the vertices in the graph in a list.
	 *
	 * @return a list of all vertices in the graph
	 */
	public ArrayList<Vertex> getAllVertexes();
	
	

}
