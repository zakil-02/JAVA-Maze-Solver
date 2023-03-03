package graph;
import java.util.HashSet;
import java.util.List;

/**
 * The ProcessedVertexes interface defines the methods required to manage the vertexes that have been processed when using dijkstra algorithm.
 */
public interface ProcessedVertexes {
	/**
	 * Adds the specified vertex to the set of processed vertices.
	 *
	 * @param vertex the vertex to add to the set of processed vertices
	 */
	public void addVertex(Vertex vertex);
	/**
	 * Returns true if the set of processed vertices contains the specified vertex.
	 *
	 * @param vertex the vertex to check for membership in the set of processed vertices
	 * @return true if the set of processed vertices contains the specified vertex; false otherwise
	 */
	public boolean containsVertex(Vertex vertex);

	/**
	 * Returns true if the set of processed vertices contains all of the vertices in the specified list.
	 *
	 * @param l the list of vertices to check for membership in the set of processed vertices
	 * @return true if the set of processed vertices contains all of the vertices in the specified list; false otherwise
	 */
    public boolean containsAll(List<Vertex> l);
    /**
   	 * Returns a set of all vertices that have been processed.
   	 *
   	 * @return a set of all vertices that have been processed
   	 */
    public HashSet<Vertex> getAll();
}
