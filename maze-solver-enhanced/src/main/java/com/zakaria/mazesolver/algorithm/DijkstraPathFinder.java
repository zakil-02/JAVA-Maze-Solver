package com.zakaria.mazesolver.algorithm;

import java.util.*;

/**
 * Implementation of Dijkstra's shortest path algorithm.
 */
public class DijkstraPathFinder implements PathFinder {
    
    @Override
    public PathResult findPath(Graph graph, Vertex start, Vertex end, Distance distance) 
            throws PathNotFoundException {
        long startTime = System.currentTimeMillis();
        
        Map<Vertex, Integer> minDistance = new HashMap<>();
        Map<Vertex, Vertex> previous = new HashMap<>();
        Set<Vertex> processed = new HashSet<>();
        PriorityQueue<VertexDistance> queue = new PriorityQueue<>();
        
        // Initialize distances
        for (Vertex vertex : graph.getAllVertices()) {
            minDistance.put(vertex, Integer.MAX_VALUE);
        }
        minDistance.put(start, 0);
        queue.offer(new VertexDistance(start, 0));
        
        int nodesExplored = 0;
        
        while (!queue.isEmpty()) {
            VertexDistance current = queue.poll();
            Vertex currentVertex = current.vertex();
            
            if (processed.contains(currentVertex)) {
                continue;
            }
            
            processed.add(currentVertex);
            nodesExplored++;
            
            if (currentVertex.equals(end)) {
                break;
            }
            
            for (Vertex neighbor : graph.getSuccessors(currentVertex)) {
                if (processed.contains(neighbor)) {
                    continue;
                }
                
                int newDistance = minDistance.get(currentVertex) + distance.getDistance(currentVertex, neighbor);
                
                if (newDistance < minDistance.get(neighbor)) {
                    minDistance.put(neighbor, newDistance);
                    previous.put(neighbor, currentVertex);
                    queue.offer(new VertexDistance(neighbor, newDistance));
                }
            }
        }
        
        // Check if path was found
        if (!previous.containsKey(end) && !start.equals(end)) {
            throw new PathNotFoundException("No path found from " + start.getLabel() + " to " + end.getLabel());
        }
        
        // Reconstruct path
        List<Vertex> path = reconstructPath(previous, start, end);
        int totalDistance = minDistance.get(end);
        long executionTime = System.currentTimeMillis() - startTime;
        
        return new PathResult(path, totalDistance, nodesExplored, executionTime);
    }
    
    private List<Vertex> reconstructPath(Map<Vertex, Vertex> previous, Vertex start, Vertex end) {
        List<Vertex> path = new ArrayList<>();
        Vertex current = end;
        
        while (current != null) {
            path.add(0, current);
            if (current.equals(start)) {
                break;
            }
            current = previous.get(current);
        }
        
        return path;
    }
    
    @Override
    public String getAlgorithmName() {
        return "Dijkstra's Algorithm";
    }
    
    /**
     * Helper record for priority queue.
     */
    private record VertexDistance(Vertex vertex, int distance) implements Comparable<VertexDistance> {
        @Override
        public int compareTo(VertexDistance other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
}
