package com.zakaria.mazesolver.algorithm;

/**
 * Interface for calculating distance between vertices in a graph.
 */
public interface Distance {
    /**
     * Returns the weight of the arc between two vertices.
     *
     * @param from the vertex from which to depart
     * @param to the vertex at which to arrive
     * @return the distance between the specified vertices
     */
    int getDistance(Vertex from, Vertex to);
}
