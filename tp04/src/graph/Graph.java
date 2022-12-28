package graph;
import java.util.ArrayList;

public interface Graph {
	//Obtenir la liste des successeurs d'un sommet
	public ArrayList<Vertex> getSuccessors(Vertex s);
	//Obtenir la liste des sommets
	public ArrayList<Vertex> getAllVertexes();
	
	

}
