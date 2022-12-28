package graph;
import java.util.*;
public class ProcessedVertexesImpl implements ProcessedVertexes {
	private HashSet<Vertex> processedVertexes;
	@Override
	public void addVertex(Vertex vertex) {
		processedVertexes.add(vertex);
		
	}
	public boolean containsVertex(Vertex vertex) {
		return processedVertexes.contains(vertex);
	}
	

}
