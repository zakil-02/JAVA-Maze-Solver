package graph;
import java.util.*;
public class Dijkstra {
	public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex, ProcessedVertexes processedVertexes, MinDistance minDistance, Distance distance, ShortestPaths shortestPaths) {
		//Extraire la liste des sommets du graph
		List<Vertex> sommets= graph.getAllVertexes();
		//liste presentant les sommets non traités
		List<Vertex> NotProcessedYet=sommets;
		
		processedVertexes.addVertex(startVertex);
		Vertex pivotVertex = startVertex;
		NotProcessedYet.remove(startVertex);
		for (Vertex v:sommets) {
			minDistance.updateDistance(v, 2000000);//Integer.MAX_VALUE modelise l'infini.
		}
		minDistance.updateDistance(startVertex, 0);
		while(!(processedVertexes.containsVertex(endVertex))) {
			
			for (Vertex succV: graph.getSuccessors(pivotVertex)) {
				if (!(processedVertexes.containsVertex(succV))) {
					//System.out.println(graph.getSuccessors(pivotVertex));
					//System.out.println(startVertex.equals(pivotVertex));
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
