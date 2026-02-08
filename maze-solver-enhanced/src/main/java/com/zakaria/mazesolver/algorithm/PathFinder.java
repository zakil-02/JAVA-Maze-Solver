package com.zakaria.mazesolver.algorithm;

import java.util.*;

/**
 * Interface for path finding algorithms.
 */
public interface PathFinder {
    /**
     * Finds the shortest path between start and end vertices.
     *
     * @param graph the graph to search
     * @param start the starting vertex
     * @param end the destination vertex
     * @param distance the distance calculator
     * @return the result containing the path and statistics
     * @throws PathNotFoundException if no path exists
     */
    PathResult findPath(Graph graph, Vertex start, Vertex end, Distance distance) throws PathNotFoundException;
    
    /**
     * Gets the name of this algorithm.
     *
     * @return the algorithm name
     */
    String getAlgorithmName();
}
