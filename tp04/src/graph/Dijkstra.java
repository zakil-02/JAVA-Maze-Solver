package graph;
import java.util.*;
public class Dijkstra {
	public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex, ProcessedVertexes processedVertexes, MinDistance minDistance, Distance distance, ShortestPaths shortestPaths) {
		processedVertexes.addVertex(startVertex);
		Vertex pivotVertex = startVertex;
		minDistance.updateDistance(startVertex, 0);
		//Extraire la liste des sommets du graph
		List<Vertex> sommets= graph.getAllVertexes();
		for (Vertex v:sommets) {
			minDistance.updateDistance(v, Integer.MAX_VALUE);
			//Integer.MAX_VALUE modelise l'infini.
		}
		while(!(processedVertexes.containsVertex(endVertex))) {
			for (Vertex succV: graph.getSuccessors(pivotVertex)) {
				if (!(processedVertexes.containsVertex(succV))) {
					int d=minDistance.getFor(pivotVertex)+distance.get(pivotVertex, succV);
					if (d<minDistance.getFor(succV)) {
						minDistance.updateDistance(succV, d);
						shortestPaths.setPrevious(succV, pivotVertex);
					}
				}
			}
			//Cherchons le sommet nextV non traité de minDistance minimale
			List<Vertex> NotProcessed=new ArrayList<>();
			for (Vertex v:sommets) {
				if (!(processedVertexes.containsVertex(v))) {
					NotProcessed.add(v);
				}
			}
			Vertex nextV = NotProcessed.get(0);
			for (Vertex v:NotProcessed) {
				if(minDistance.getFor(v)<minDistance.getFor(nextV)) {
					nextV=v;
				}
			}
			pivotVertex=nextV;
			processedVertexes.addVertex(pivotVertex);
			
		}
		return shortestPaths;
	}

}
