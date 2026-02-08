package com.zakaria.mazesolver.algorithm;

import java.util.List;

/**
 * Result of a path finding operation, including the path and statistics.
 */
public record PathResult(
    List<Vertex> path,
    int totalDistance,
    int nodesExplored,
    long executionTimeMs
) {
    /**
     * Checks if a path was found.
     *
     * @return true if path is not empty
     */
    public boolean hasPath() {
        return path != null && !path.isEmpty();
    }
}
