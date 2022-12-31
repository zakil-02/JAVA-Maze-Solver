package graph;
import java.util.HashSet;
import java.util.List;

public interface ProcessedVertexes {
	//C est pour ajouter les sommets traités par l'algorithme
	public void addVertex(Vertex vertex);
	public boolean containsVertex(Vertex vertex);
    public boolean containsAll(List<Vertex> l);
    public HashSet<Vertex> getAll();
}
