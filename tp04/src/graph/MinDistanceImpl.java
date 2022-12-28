package graph;
import java.util.*;

public class MinDistanceImpl implements MinDistance{
	private HashMap<Vertex, Integer> minDistance;
	/*Definissons un constructeur qui initialise 
	MinDistance avec un Set {vertex: infini pour tout vertex}*/
	
	public MinDistanceImpl(Set<Vertex> vertexes) {
		HashMap<Vertex, Integer> emptyMap=new HashMap<>();
		for (Vertex v:vertexes) {
			emptyMap.put(v, 10000000);
			
		}
		this.minDistance=emptyMap;
	}
	//getter de distance pour un sommet 
	public int getFor(Vertex v) {
		return minDistance.get(v);
	}
	//getter de toute la HashMap
	public HashMap<Vertex,Integer> getAll(){
		return minDistance;
	}
	//mettre a jour la distance minimale d'un sommet
	public void updateDistance(Vertex v, int d) {
		minDistance.put(v, d);
	}

}
