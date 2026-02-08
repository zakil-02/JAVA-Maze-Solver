package com.zakaria.mazesolver.algorithm;

import java.util.List;

/**
 * The Graph interface defines the methods required to manage a graph.
 */
public interface Graph {
    /**
     * Returns a list of vertices that are successors of the specified vertex.
     *
     * @param vertex the vertex whose successors are to be returned
     * @return a list of vertices that are successors of the specified vertex
     */
    List<Vertex> getSuccessors(Vertex vertex);
    
    /**
     * Returns all the vertices in the graph in a list.
     *
     * @return a list of all vertices in the graph
     */
    List<Vertex> getAllVertices();
}
