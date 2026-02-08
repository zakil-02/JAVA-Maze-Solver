package com.zakaria.mazesolver.ui;

import com.zakaria.mazesolver.algorithm.DijkstraPathFinder;
import com.zakaria.mazesolver.algorithm.PathFinder;
import com.zakaria.mazesolver.model.Maze;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Main application window.
 */
public class MainFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 800;
    
    private Maze maze;
    private final PathFinder pathFinder;
    private final MazePanel mazePanel;
    private final ControlPanel controlPanel;
    private final StatusPanel statusPanel;
    private MazeMenuBar menuBar;
    
    public MainFrame() {
        this.maze = new Maze(15, 10);
        this.pathFinder = new DijkstraPathFinder();
        
        setTitle("Maze Solver - Modern Edition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        
        // Set application icon
        try {
            setIconImage(createAppIcon());
        } catch (Exception e) {
            // Icon creation failed, continue without it
        }
        
        // Create UI components
        this.mazePanel = new MazePanel(maze);
        this.controlPanel = new ControlPanel(this, maze, mazePanel);
        this.statusPanel = new StatusPanel();
        
        // Setup layout
        setupLayout();
        
        // Setup menu bar
        this.menuBar = new MazeMenuBar(this, maze, mazePanel);
        setJMenuBar(menuBar);
    }
    
    public Maze getMaze() {
        return maze;
    }
    
    public void setMaze(Maze newMaze) {
        this.maze = newMaze;
        mazePanel.setMaze(newMaze);
        if (menuBar != null) {
            menuBar.updateMaze(newMaze);
        }
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        
        // Main content area with maze
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add title panel
        JPanel titlePanel = createTitlePanel();
        centerPanel.add(titlePanel, BorderLayout.NORTH);
        
        // Add maze panel with scroll pane
        JScrollPane scrollPane = new JScrollPane(mazePanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Maze Grid"));
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(centerPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.EAST);
        add(statusPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createTitlePanel() {
        JPanel panel = new JPanel(new MigLayout("fillx, insets 10"));
        panel.setOpaque(false);
        
        JLabel titleLabel = new JLabel("Hexagonal Maze Solver");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        JLabel subtitleLabel = new JLabel("Create your maze and find the shortest path using Dijkstra's algorithm");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitleLabel.setForeground(Color.GRAY);
        
        panel.add(titleLabel, "wrap");
        panel.add(subtitleLabel);
        
        return panel;
    }
    
    private Image createAppIcon() {
        // Create a simple icon programmatically
        int size = 64;
        BufferedImage icon = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = icon.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw hexagon
        g2d.setColor(new Color(33, 150, 243));
        int[] xPoints = {size/2, size*3/4, size*3/4, size/2, size/4, size/4};
        int[] yPoints = {size/8, size*3/8, size*5/8, size*7/8, size*5/8, size*3/8};
        g2d.fillPolygon(xPoints, yPoints, 6);
        
        g2d.dispose();
        return icon;
    }
    
    public PathFinder getPathFinder() {
        return pathFinder;
    }
    
    public StatusPanel getStatusPanel() {
        return statusPanel;
    }
    
    public MazePanel getMazePanel() {
        return mazePanel;
    }
}
