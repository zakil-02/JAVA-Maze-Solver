package graph;
import java.util.*;

public class ShortestPathsImpl implements ShortestPaths{
	private HashMap<Vertex, Vertex> previous;
	//On definit un constructeur convenable {vertex:null pour chaque vertex}
	public ShortestPathsImpl(List<Vertex> l) {
		HashMap<Vertex, Vertex> eMap=new HashMap<>();
		for (Vertex vertex:l) {
			eMap.put(vertex, null);
		}
		this.previous=eMap;
	}
	public HashMap<Vertex, Vertex>getAll() {
		return previous;
	}
	public Vertex getPrevious(Vertex v) {
		return previous.get(v);
	}
	public void setPrevious(Vertex fils, Vertex pere) {
		previous.put(fils,pere);
	}
	public List<Vertex> getShortestPath(Vertex endVertex){
		Vertex v=endVertex;
		List<Vertex> courtChemin = new ArrayList<>();
		while(v!=null) {
			//la condition d'arret est equivalente a atteindre le sommet de depart
			
			courtChemin.add(0,v);// C'est pour avoir un chemin Depart-....-Arrival
			v=getPrevious(v);
		}
		return courtChemin;
	}

}
