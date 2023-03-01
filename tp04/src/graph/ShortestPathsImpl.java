package graph;
import java.util.*;

/**
 * Implementation of the ShortestPaths interface that maps vertices with their fathers.
 */
public class ShortestPathsImpl implements ShortestPaths{
	private HashMap<Vertex, Vertex> previous;
	
	/**
	 * Constructs a new ShortestPathsImpl object with the specified list of vertices.
	 * Initializes the previous vertex map with each vertex in the list mapping to null.
	 *
	 * @param l the list of vertices to initialize the previous vertex map with
	 */
	public ShortestPathsImpl(List<Vertex> l) {
		HashMap<Vertex, Vertex> eMap=new HashMap<>();
		for (Vertex vertex:l) {
			eMap.put(vertex, null); //We initialize the Map as all the fathers are null.
		}
		this.previous=eMap;
	}
	/**
     * Returns the HashMap that contains all the vertices mapped to their previous vertices in the shortest path.
     * 
     * @return A HashMap containing all the vertices in the graph mapped to their previous vertices in the shortest path.
     */
	public HashMap<Vertex, Vertex> getAll() {
		return previous;
	}
	/**
     * Returns the previous vertex in the shortest path to the specified vertex.
     * 
     * @param v The vertex to get the previous vertex for.
     * @return The previous vertex in the shortest path to the specified vertex.
     */
	public Vertex getPrevious(Vertex v) {
		return previous.get(v);
	}
	/**
     * Sets the specified vertex as the previous vertex in the shortest path to the specified child vertex.
     * 
     * @param pere The vertex to set as the previous vertex.
     * @param fils The child vertex.
     */
	public void setPrevious(Vertex fils, Vertex pere) {
		previous.put(fils,pere);
	}
	/**
	 * Computes the shortest path from some starting vertex to the specified end vertex using Dijkstra's algorithm.
	 * @param endVertex The destination vertex for the shortest path.
	 * @return A List of Vertex objects representing the shortest path from the starting vertex to the specified end vertex.
	 */
	public List<Vertex> getShortestPath(Vertex endVertex){
		Vertex v=endVertex;
		List<Vertex> courtChemin = new ArrayList<>();
		while(v!=null) {
			//The break condition in while is equivalent to get into the departure vertex
			courtChemin.add(0,v);// In order to have the way like : departure-...-arrival
			v=getPrevious(v);
		}
		return courtChemin;
	}

}
