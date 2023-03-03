package graph;
import java.util.*;

/**
 * The MinDistance interface defines the methods required to manage the smallest distance between the start and every vertex in the graph.
 */
public interface MinDistance {
	/**
	 * Returns the minimum distance for the specified vertex.
	 *
	 * @param v the vertex whose minimum distance is to be returned
	 * @return the minimum distance for the specified vertex
	 */
	public int getFor(Vertex v);
	/**
	 * Returns a mapping of vertices to their minimum distances.
	 *
	 * @return a mapping of vertices to their minimum distances
	 */
   	public HashMap<Vertex,Integer> getAll();
   	/**
	 * Updates the minimum distance for the specified vertex.
	 *
	 * @param v the vertex whose minimum distance is to be updated
	 * @param d the new minimum distance for the specified vertex
	 */
	public void updateDistance(Vertex v, int d);

}
