package graph;
import java.util.*;

/**
 * The ShortestPaths interface defines the methods required to manage shortest paths in a graph.
 */
public interface ShortestPaths {
	 /**
     * Returns the HashMap that contains all the vertices mapped to their previous vertices in the shortest path.
     * 
     * @return A HashMap containing all the vertices in the graph mapped to their previous vertices in the shortest path.
     */
	public HashMap<Vertex, Vertex>getAll();
	/**
     * Returns the previous vertex in the shortest path to the specified vertex.
     * 
     * @param v The vertex to get the previous vertex for.
     * @return The previous vertex in the shortest path to the specified vertex.
     */
	public Vertex getPrevious(Vertex v);
	/**
     * Sets the specified vertex as the previous vertex in the shortest path to the specified child vertex.
     * 
     * @param pere The vertex to set as the previous vertex.
     * @param fils The child vertex.
     */
	public void setPrevious(Vertex pere, Vertex fils);
	 /**
     * Returns the shortest path from the start vertex to the specified end vertex.
     * 
     * @param endVertex The end vertex of the path.
     * @return A list of vertices representing the shortest path from the start vertex to the end vertex.
     */
	public List<Vertex> getShortestPath(Vertex endVertex);
}
