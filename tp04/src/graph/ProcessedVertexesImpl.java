package graph;
import java.util.*;
public class ProcessedVertexesImpl implements ProcessedVertexes {
	private HashSet<Vertex> processedVertexes;
	//Constructeur qui initialise processedVertexes avec un HashSet vide de taille donnee en argument.
	public ProcessedVertexesImpl(int taille) {
		processedVertexes=new HashSet<>(taille);
	}
	@Override
	public void addVertex(Vertex vertex) {
		processedVertexes.add(vertex);
		
	}
	public boolean containsVertex(Vertex vertex) {
		return processedVertexes.contains(vertex);
	}
	

}
