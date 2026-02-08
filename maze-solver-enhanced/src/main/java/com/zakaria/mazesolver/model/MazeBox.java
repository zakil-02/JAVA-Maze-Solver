package com.zakaria.mazesolver.model;

import com.zakaria.mazesolver.algorithm.Vertex;

import java.util.Objects;

/**
 * Represents a single box/cell in the maze.
 */
public class MazeBox implements Vertex {
    private final int x;
    private final int y;
    private BoxType type;
    
    public MazeBox(int x, int y, BoxType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public BoxType getType() {
        return type;
    }
    
    public void setType(BoxType type) {
        this.type = type;
    }
    
    public boolean isWalkable() {
        return type.isWalkable();
    }
    
    @Override
    public String getLabel() {
        return type.getCode() + "(" + x + "," + y + ")";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeBox mazeBox = (MazeBox) o;
        return x == mazeBox.x && y == mazeBox.y;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    @Override
    public String toString() {
        return "MazeBox{" + "x=" + x + ", y=" + y + ", type=" + type + '}';
    }
}
