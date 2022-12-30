package graph;
import java.util.*;

public class ShortestPathsImpl implements ShortestPaths{
	private HashMap<Vertex, Vertex> previous;
	//On definit un constructeur convenable {vertex:null pour chaque vertex}
	public ShortestPathsImpl(List<Vertex> l) {
		HashMap<Vertex, Vertex> emptyMap=new HashMap<>();
		for (Vertex vertex:l) {
			emptyMap.put(vertex, null);
		}
		this.previous=emptyMap;
	}
	public Vertex getPrevious(Vertex v) {
		return previous.get(v);
	}
	public void setPrevious(Vertex pere, Vertex fils) {
		previous.put(fils,pere);
	}
	public List<Vertex> getShortestPath(Vertex endVertex){
		Vertex v=endVertex;
		List<Vertex> courtChemin = new ArrayList<>();
		while(v!=null) {
			//la condition d'arret est equivalente a atteindre le sommet de depart
			courtChemin.add(0, v);// C'est pour avoir un chemin Depart-....-Arrival
			v=previous.get(v);
		}
		return courtChemin;
	}

}
