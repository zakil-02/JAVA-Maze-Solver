package graph;
import java.util.*;

/**

Dijkstra class is responsible for finding the shortest path between two vertices in a graph.
*/
public class Dijkstra{
	/**

	Finds the shortest path between startVertex and endVertex in a given graph using Dijkstra's algorithm.

	@param graph the graph on which the algorithm will be applied.

	@param startVertex the starting vertex.

	@param endVertex the destination vertex.

	@param processedVertexes the set of vertices already processed by the algorithm.

	@param minDistance the minimum distance to reach each vertex from the starting vertex.

	@param distance the distance between two vertices in the graph.

	@param shortestPaths the set of shortest paths from the starting vertex to all other vertices.

	@return the set of shortest paths from the starting vertex to all other vertices.

	@throws Exception if there is no path between startVertex and endVertex.
	*/
	public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex, ProcessedVertexes processedVertexes, MinDistance minDistance, Distance distance, ShortestPaths shortestPaths)  throws Exception{
		//list of all the vertices
		List<Vertex> sommets= graph.getAllVertexes();
		//list of vertices that have not been processed yet.
		List<Vertex> NotProcessedYet=sommets;
		
		processedVertexes.addVertex(startVertex);
		Vertex pivotVertex = startVertex;
		NotProcessedYet.remove(startVertex);
		minDistance.updateDistance(startVertex, 0);
		//Initialize every vertex distance into infinity except the start vertex.
		for (Vertex v:sommets) {
			if (!v.equals(startVertex)) {
				minDistance.updateDistance(v, Integer.MAX_VALUE);//Integer.MAX_VALUE gives us the biggest integer which will be considered as infinity
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
			//Searching for the next vertex that has not been processed yet and has the minimal distance.
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
		
		
		//Exception handling : no solution case.
		// I let a margin of 10000, we will not have a distance 
		if (minDistance.getFor(endVertex)<Integer.MIN_VALUE +10000 ) { // J'ai  donné une marge de 10000. On aura jamais un labyrinthe de 100x100!
			throw new Exception("Path not found");
			
		}
		
		return shortestPaths;
	}

}
