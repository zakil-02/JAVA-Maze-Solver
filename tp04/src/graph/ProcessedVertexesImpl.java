package graph;
import java.util.*;

/**
 * Implementation of the ProcessedVertexes interface that stores processed vertices in a HashSet.
 */
public class ProcessedVertexesImpl implements ProcessedVertexes {
	private HashSet<Vertex> processedVertexes;
	
	/**
	 * Creates a new ProcessedVertexesImpl object with the specified initial capacity.
	 *
	 * @param taille the initial capacity of the HashSet used to store processed vertices
	 */
	public ProcessedVertexesImpl(int taille) {
		processedVertexes=new HashSet<>(taille);
	}
	/**
	 * Adds the specified vertex to the set of processed vertices.
	 *
	 * @param vertex the vertex to add to the set of processed vertices
	 */
	@Override
	public void addVertex(Vertex vertex) {
		processedVertexes.add(vertex);
		
	}
	/**
	 * Returns a HashSet containing all of the processed vertices.
	 *
	 * @return a HashSet containing all of the processed vertices
	 */
	public HashSet<Vertex> getAll(){
		return processedVertexes;
	}
	/**
	 * Returns true if the set of processed vertices contains all of the vertices in the specified list, false otherwise.
	 *
	 * @param l the list of vertices to check for containment in the set of processed vertices
	 * @return true if the set of processed vertices contains all of the vertices in the specified list, false otherwise
	 */
	public boolean containsAll(List<Vertex> l) {
		return processedVertexes.containsAll(l);
	}
	/**
	 * Returns true if the set of processed vertices contains the specified vertex, false otherwise.
	 *
	 * @param vertex the vertex to check for containment in the set of processed vertices
	 * @return true if the set of processed vertices contains the specified vertex, false otherwise
	 */
	public boolean containsVertex(Vertex vertex) {
		return processedVertexes.contains(vertex);
	}
	

}
