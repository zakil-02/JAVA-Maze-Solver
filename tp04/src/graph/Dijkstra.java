package graph;
import java.util.*;
import Labyrinthe.*;
public class Dijkstra {
	public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex, ProcessedVertexes processedVertexes, MinDistance minDistance, Distance distance, ShortestPaths shortestPaths) {
		//Extraire la liste des sommets du graph
		List<Vertex> sommets= graph.getAllVertexes();
		//liste presentant les sommets non traités
		List<Vertex> NotProcessedYet=sommets;
		
		processedVertexes.addVertex(startVertex);
		System.out.println(processedVertexes.getAll());
		Vertex pivotVertex = startVertex;
		NotProcessedYet.remove(startVertex);
		minDistance.updateDistance(startVertex, 0);
		for (Vertex v:sommets) {
			if (!v.equals(startVertex)) {
				minDistance.updateDistance(v, 2000000);//Integer.MAX_VALUE modelise l'infini.
			}
		}
		while(!(processedVertexes.containsVertex(endVertex))) {
			
			for (Vertex succV: graph.getSuccessors(pivotVertex)) {
				System.out.println(succV);
				System.out.println(graph.getSuccessors(pivotVertex));
				if (!(processedVertexes.containsVertex(succV))) {
					//System.out.println(pivotVertex);
					int d= minDistance.getFor(pivotVertex)+distance.getDistance(pivotVertex, succV);
					//System.out.println(d);
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
		return shortestPaths;
	}

}
