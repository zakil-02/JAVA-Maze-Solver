package com.zakaria.mazesolver.model;

import com.zakaria.mazesolver.algorithm.Distance;
import com.zakaria.mazesolver.algorithm.Graph;
import com.zakaria.mazesolver.algorithm.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a hexagonal maze as a graph.
 */
public class Maze implements Graph, Distance {
    private final int width;
    private final int height;
    private final MazeBox[][] boxes;
    private MazeBox startBox;
    private MazeBox endBox;
    
    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.boxes = new MazeBox[height][width];
        initializeBoxes();
    }
    
    private void initializeBoxes() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boxes[y][x] = new MazeBox(x, y, BoxType.EMPTY);
            }
        }
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public MazeBox getBox(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return boxes[y][x];
        }
        return null;
    }
    
    public void setBoxType(int x, int y, BoxType type) {
        MazeBox box = getBox(x, y);
        if (box != null) {
            // Clear previous start/end if setting new one
            if (type == BoxType.DEPARTURE && startBox != null && startBox != box) {
                startBox.setType(BoxType.EMPTY);
            }
            if (type == BoxType.ARRIVAL && endBox != null && endBox != box) {
                endBox.setType(BoxType.EMPTY);
            }
            
            box.setType(type);
            
            if (type == BoxType.DEPARTURE) {
                startBox = box;
            } else if (type == BoxType.ARRIVAL) {
                endBox = box;
            }
        }
    }
    
    public MazeBox getStartBox() {
        return startBox;
    }
    
    public MazeBox getEndBox() {
        return endBox;
    }
    
    public boolean isValid() {
        return startBox != null && endBox != null;
    }
    
    public void clearSolution() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (boxes[y][x].getType() == BoxType.SOLUTION) {
                    boxes[y][x].setType(BoxType.EMPTY);
                }
            }
        }
    }
    
    public void reset() {
        startBox = null;
        endBox = null;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boxes[y][x].setType(BoxType.EMPTY);
            }
        }
    }
    
    @Override
    public List<Vertex> getSuccessors(Vertex vertex) {
        List<Vertex> successors = new ArrayList<>();
        MazeBox box = (MazeBox) vertex;
        int x = box.getX();
        int y = box.getY();
        
        // Hexagonal grid neighbors (6 neighbors)
        // Top and bottom
        addIfWalkable(successors, x, y - 1);
        addIfWalkable(successors, x, y + 1);
        
        // Left and right
        addIfWalkable(successors, x - 1, y);
        addIfWalkable(successors, x + 1, y);
        
        // Diagonal neighbors (depend on row parity for hexagonal grid)
        if (y % 2 == 0) {
            // Even row
            addIfWalkable(successors, x - 1, y - 1);
            addIfWalkable(successors, x - 1, y + 1);
        } else {
            // Odd row
            addIfWalkable(successors, x + 1, y - 1);
            addIfWalkable(successors, x + 1, y + 1);
        }
        
        return successors;
    }
    
    private void addIfWalkable(List<Vertex> list, int x, int y) {
        MazeBox box = getBox(x, y);
        if (box != null && box.isWalkable()) {
            list.add(box);
        }
    }
    
    @Override
    public List<Vertex> getAllVertices() {
        List<Vertex> vertices = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                vertices.add(boxes[y][x]);
            }
        }
        return vertices;
    }
    
    @Override
    public int getDistance(Vertex from, Vertex to) {
        // Uniform cost for all edges
        return 1;
    }
}
