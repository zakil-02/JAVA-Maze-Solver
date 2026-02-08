package com.zakaria.mazesolver.algorithm;

/**
 * Exception thrown when no path can be found between two vertices.
 */
public class PathNotFoundException extends Exception {
    public PathNotFoundException(String message) {
        super(message);
    }
}
