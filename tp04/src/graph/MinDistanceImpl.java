package graph;
import java.util.*;

/**
 * Implementation of the MinDistance interface that maps vertices with distances to the start vertex.
 */
public class MinDistanceImpl implements MinDistance{
	private HashMap<Vertex, Integer> minDistance;
	/**
	 * Constructs a new instance of the MinDistanceImpl class with the specified list of Vertex objects.
	 * @param vertexes The list of Vertex objects to use for initializing the minimum distance map.
	 */
	public MinDistanceImpl(List<Vertex> vertexes) {
		// Initialize an empty map to represent minimum distances.
		HashMap<Vertex, Integer> emptyMap=new HashMap<>();
		this.minDistance=emptyMap;
	}
	/**
	 * Returns the minimum distance for the specified vertex.
	 *
	 * @param v the vertex whose minimum distance is to be returned
	 * @return the minimum distance for the specified vertex
	 */
	public int getFor(Vertex v) {
		return minDistance.get(v);
	}
	/**
	 * Returns a mapping of vertices to their minimum distances.
	 *
	 * @return a mapping of vertices to their minimum distances
	 */
	public HashMap<Vertex,Integer> getAll(){
		return minDistance;
	}
	/**
	 * Updates the minimum distance for the specified vertex.
	 *
	 * @param v the vertex whose minimum distance is to be updated
	 * @param d the new minimum distance for the specified vertex
	 */
	public void updateDistance(Vertex v, int d) {
		minDistance.put(v, d);
	}

}
