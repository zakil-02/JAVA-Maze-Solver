package graph;
import java.util.*;

public interface ShortestPaths {
	public HashMap<Vertex, Vertex>getAll();
	public Vertex getPrevious(Vertex v);
	public void setPrevious(Vertex pere, Vertex fils);
	public List<Vertex> getShortestPath(Vertex endVertex);
}
