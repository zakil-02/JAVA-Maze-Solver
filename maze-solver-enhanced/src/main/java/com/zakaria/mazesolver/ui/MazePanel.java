package com.zakaria.mazesolver.ui;

import com.zakaria.mazesolver.model.BoxType;
import com.zakaria.mazesolver.model.Maze;
import com.zakaria.mazesolver.model.MazeBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Panel for displaying and interacting with the maze.
 */
public class MazePanel extends JPanel {
    private static final int MIN_HEX_SIZE = 15;
    private static final int MAX_HEX_SIZE = 50;
    private static final int PADDING = 40; // Increased padding to prevent cutoff
    private static final double SQRT_3 = Math.sqrt(3);
    
    private Maze maze;
    private int hexSize = 30;
    private BoxType currentBrushType = BoxType.WALL;
    private final Map<Point, Polygon> hexagonCache = new HashMap<>();
    private MazeBox hoveredBox = null;
    
    public MazePanel(Maze maze) {
        this.maze = maze;
        updateBackgroundColor();
        setupMouseListeners();
        calculateOptimalHexSize();
        updatePreferredSize();
    }
    
    private void updateBackgroundColor() {
        // Use UIManager colors that respond to theme changes
        Color bgColor = UIManager.getColor("Panel.background");
        if (bgColor != null) {
            setBackground(bgColor);
        }
    }
    
    public void setMaze(Maze newMaze) {
        this.maze = newMaze;
        hexagonCache.clear();
        calculateOptimalHexSize();
        updatePreferredSize();
        updateBackgroundColor();
        revalidate();
        repaint();
    }
    
    /**
     * Updates the panel appearance when theme changes.
     * This should be called after theme toggle.
     */
    public void updateTheme() {
        updateBackgroundColor();
        repaint();
    }
    
    private void calculateOptimalHexSize() {
        // Calculate optimal hex size based on maze dimensions
        int width = maze.getWidth();
        int height = maze.getHeight();
        
        // Estimate available space (assuming typical window size)
        int availableWidth = 800;
        int availableHeight = 600;
        
        // Calculate hex size that fits the maze
        double hexWidth = SQRT_3 * hexSize;
        double hexHeight = hexSize * 2;
        
        int sizeByWidth = (int) ((availableWidth - PADDING * 2) / (width * SQRT_3));
        int sizeByHeight = (int) ((availableHeight - PADDING * 2) / (height * 1.5));
        
        hexSize = Math.min(sizeByWidth, sizeByHeight);
        hexSize = Math.max(MIN_HEX_SIZE, Math.min(MAX_HEX_SIZE, hexSize));
    }
    
    private void setupMouseListeners() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e.getPoint());
            }
            
            @Override
            public void mouseMoved(MouseEvent e) {
                handleMouseMove(e.getPoint());
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseClick(e.getPoint());
            }
        };
        
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }
    
    private void handleMouseClick(Point point) {
        MazeBox box = getBoxAtPoint(point);
        if (box != null && currentBrushType != null) {
            maze.setBoxType(box.getX(), box.getY(), currentBrushType);
            repaint();
        }
    }
    
    private void handleMouseMove(Point point) {
        MazeBox box = getBoxAtPoint(point);
        if (box != hoveredBox) {
            hoveredBox = box;
            repaint();
        }
    }
    
    private MazeBox getBoxAtPoint(Point point) {
        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                Polygon hex = getHexagon(x, y);
                if (hex.contains(point)) {
                    return maze.getBox(x, y);
                }
            }
        }
        return null;
    }
    
    public void setBrushType(BoxType type) {
        this.currentBrushType = type;
    }
    
    public BoxType getBrushType() {
        return currentBrushType;
    }
    
    private void updatePreferredSize() {
        double hexWidth = hexSize * SQRT_3;
        double hexHeight = hexSize * 2;
        
        // Add extra space to ensure all hexagons are fully visible
        int width = (int) (maze.getWidth() * hexWidth + hexWidth + PADDING * 2);
        int height = (int) (maze.getHeight() * hexHeight * 0.75 + hexHeight + PADDING * 2);
        setPreferredSize(new Dimension(width, height));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Enable antialiasing for smooth hexagons
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        // Draw all hexagons
        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                drawHexagon(g2d, x, y);
            }
        }
    }
    
    private void drawHexagon(Graphics2D g2d, int x, int y) {
        MazeBox box = maze.getBox(x, y);
        Polygon hex = getHexagon(x, y);
        
        // Determine colors based on theme
        boolean isDarkTheme = isDarkTheme();
        Color fillColor = getBoxColor(box.getType(), isDarkTheme);
        Color borderColor = isDarkTheme ? new Color(80, 80, 80) : new Color(200, 200, 200);
        
        // Highlight hovered box
        boolean isHovered = box.equals(hoveredBox);
        if (isHovered) {
            fillColor = brighten(fillColor, isDarkTheme ? 0.3f : 0.2f);
            borderColor = isDarkTheme ? new Color(120, 120, 120) : new Color(100, 100, 100);
        }
        
        // Draw filled hexagon
        g2d.setColor(fillColor);
        g2d.fillPolygon(hex);
        
        // Draw border
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(isHovered ? 2.5f : 1.5f));
        g2d.drawPolygon(hex);
        
        // Draw label for special boxes
        if (box.getType() != BoxType.EMPTY && box.getType() != BoxType.WALL) {
            drawLabel(g2d, hex, box.getType());
        }
    }
    
    private boolean isDarkTheme() {
        Color bgColor = UIManager.getColor("Panel.background");
        if (bgColor != null) {
            // Calculate brightness (0-255)
            int brightness = (bgColor.getRed() + bgColor.getGreen() + bgColor.getBlue()) / 3;
            return brightness < 128;
        }
        return false;
    }
    
    private Color getBoxColor(BoxType type, boolean isDarkTheme) {
        return switch (type) {
            case EMPTY -> isDarkTheme ? new Color(50, 50, 50) : new Color(240, 240, 240);
            case WALL -> isDarkTheme ? new Color(30, 30, 30) : new Color(60, 60, 60);
            case DEPARTURE -> new Color(76, 175, 80);  // Green - same in both themes
            case ARRIVAL -> new Color(244, 67, 54);    // Red - same in both themes
            case SOLUTION -> new Color(33, 150, 243);  // Blue - same in both themes
        };
    }
    
    private void drawLabel(Graphics2D g2d, Polygon hex, BoxType type) {
        Rectangle bounds = hex.getBounds();
        int centerX = bounds.x + bounds.width / 2;
        int centerY = bounds.y + bounds.height / 2;
        
        String label = switch (type) {
            case DEPARTURE -> "S";
            case ARRIVAL -> "E";
            case SOLUTION -> "•";
            default -> "";
        };
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("SansSerif", Font.BOLD, type == BoxType.SOLUTION ? 20 : 14));
        
        FontMetrics fm = g2d.getFontMetrics();
        int labelWidth = fm.stringWidth(label);
        int labelHeight = fm.getHeight();
        
        g2d.drawString(label, centerX - labelWidth / 2, centerY + labelHeight / 4);
    }
    
    private Polygon getHexagon(int x, int y) {
        Point key = new Point(x, y);
        return hexagonCache.computeIfAbsent(key, k -> createHexagon(x, y));
    }
    
    private Polygon createHexagon(int gridX, int gridY) {
        double hexWidth = hexSize * SQRT_3;
        double hexHeight = hexSize * 2;
        
        // Calculate center position with proper hexagonal offset
        // Add hexSize to PADDING to ensure top hexagons are fully visible
        double centerX = PADDING + hexSize + gridX * hexWidth;
        if (gridY % 2 == 1) {
            centerX += hexWidth / 2;
        }
        double centerY = PADDING + hexSize + gridY * hexHeight * 0.75;
        
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        
        // Create hexagon with flat top (pointy sides)
        for (int i = 0; i < 6; i++) {
            double angle = Math.PI / 3 * i - Math.PI / 6; // Start from top
            xPoints[i] = (int) (centerX + hexSize * Math.cos(angle));
            yPoints[i] = (int) (centerY + hexSize * Math.sin(angle));
        }
        
        return new Polygon(xPoints, yPoints, 6);
    }
    
    private Color brighten(Color color, float factor) {
        int r = Math.min(255, (int) (color.getRed() + (255 - color.getRed()) * factor));
        int g = Math.min(255, (int) (color.getGreen() + (255 - color.getGreen()) * factor));
        int b = Math.min(255, (int) (color.getBlue() + (255 - color.getBlue()) * factor));
        return new Color(r, g, b);
    }
}
