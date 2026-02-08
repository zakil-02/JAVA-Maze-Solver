package com.zakaria.mazesolver.model;

import java.awt.Color;

/**
 * Enum representing the different types of boxes in a maze.
 */
public enum BoxType {
    EMPTY("E", "Empty", new Color(240, 240, 240)),
    WALL("W", "Wall", new Color(60, 60, 60)),
    DEPARTURE("D", "Start", new Color(76, 175, 80)),
    ARRIVAL("A", "End", new Color(244, 67, 54)),
    SOLUTION("*", "Path", new Color(33, 150, 243));
    
    private final String code;
    private final String displayName;
    private final Color color;
    
    BoxType(String code, String displayName, Color color) {
        this.code = code;
        this.displayName = displayName;
        this.color = color;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public Color getColor() {
        return color;
    }
    
    public boolean isWalkable() {
        return this != WALL;
    }
    
    public static BoxType fromCode(String code) {
        for (BoxType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return EMPTY;
    }
}
