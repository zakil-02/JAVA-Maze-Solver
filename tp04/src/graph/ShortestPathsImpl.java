package graph;
import java.util.*;

public class ShortestPathsImpl implements ShortestPaths{
	private HashMap<Vertex, Vertex> previous;
	//On definit un constructeur convenable {vertex:null pour chaque vertex}
	public ShortestPathsImpl(Set<Vertex> l) {
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

}
