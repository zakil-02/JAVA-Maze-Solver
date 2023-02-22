package graph;
import java.util.*;
public class Dijkstra{
	public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex, ProcessedVertexes processedVertexes, MinDistance minDistance, Distance distance, ShortestPaths shortestPaths)  throws Exception{
		//Extraire la liste des sommets du graph
		List<Vertex> sommets= graph.getAllVertexes();
		//liste presentant les sommets non traités
		List<Vertex> NotProcessedYet=sommets;
		
		processedVertexes.addVertex(startVertex);
		Vertex pivotVertex = startVertex;
		NotProcessedYet.remove(startVertex);
		minDistance.updateDistance(startVertex, 0);
		for (Vertex v:sommets) {
			if (!v.equals(startVertex)) {
				minDistance.updateDistance(v, Integer.MAX_VALUE);//Integer.MAX_VALUE modélise l'infini.
			}
		}
		while(!(processedVertexes.containsVertex(endVertex))) {
			
			for (Vertex succV: graph.getSuccessors(pivotVertex)) {
				if (!(processedVertexes.containsVertex(succV))) {
					int d= minDistance.getFor(pivotVertex)+distance.getDistance(pivotVertex, succV);
					if (d<minDistance.getFor(succV)){
						minDistance.updateDistance(succV, d);
						shortestPaths.setPrevious(succV,pivotVertex);
					}
				}
			}
			//Cherchons le sommet nextV non traité de minDistance minimale
			Vertex nextV = NotProcessedYet.get(0);
			for (Vertex v:NotProcessedYet) {
				if(minDistance.getFor(v)<minDistance.getFor(nextV)) {
					nextV=v;
				}
			}
			pivotVertex=nextV;
			processedVertexes.addVertex(pivotVertex);
			NotProcessedYet.remove(pivotVertex);
			
		}
		
		
		//Gestion de l'exception : Pas de chemin possible.
		if (minDistance.getFor(endVertex)<Integer.MIN_VALUE +10000 ) { // J'ai  donné une marge de 10000. On aura jamais un labyrinthe de 100x100!
			throw new Exception("Path not found");
			
		}
		
		return shortestPaths;
	}

}
