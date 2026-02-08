package com.zakaria.mazesolver.ui;

import com.zakaria.mazesolver.algorithm.PathNotFoundException;
import com.zakaria.mazesolver.algorithm.PathResult;
import com.zakaria.mazesolver.algorithm.Vertex;
import com.zakaria.mazesolver.model.BoxType;
import com.zakaria.mazesolver.model.Maze;
import com.zakaria.mazesolver.model.MazeBox;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

/**
 * Control panel for maze configuration and actions.
 */
public class ControlPanel extends JPanel {
    private final MainFrame mainFrame;
    private final MazePanel mazePanel;
    
    private JSpinner widthSpinner;
    private JSpinner heightSpinner;
    private ButtonGroup brushGroup;
    
    public ControlPanel(MainFrame mainFrame, Maze maze, MazePanel mazePanel) {
        this.mainFrame = mainFrame;
        this.mazePanel = mazePanel;
        
        setLayout(new MigLayout("fillx, insets 10", "[grow]", "[]10[]10[]10[]"));
        setPreferredSize(new Dimension(300, 0));
        
        add(createDimensionsPanel(maze), "growx, wrap");
        add(createBrushPanel(), "growx, wrap");
        add(createActionsPanel(), "growx, wrap");
        add(createLegendPanel(), "growx, wrap");
    }
    
    private JPanel createDimensionsPanel(Maze maze) {
        JPanel panel = new JPanel(new MigLayout("fillx, insets 10", "[][grow]"));
        panel.setBorder(createStyledBorder("Maze Dimensions"));
        
        widthSpinner = new JSpinner(new SpinnerNumberModel(maze.getWidth(), 5, 30, 1));
        heightSpinner = new JSpinner(new SpinnerNumberModel(maze.getHeight(), 5, 30, 1));
        
        JButton applyButton = createStyledButton("Apply", new Color(76, 175, 80));
        applyButton.addActionListener(e -> applyDimensions());
        
        panel.add(new JLabel("Width:"));
        panel.add(widthSpinner, "growx, wrap");
        panel.add(new JLabel("Height:"));
        panel.add(heightSpinner, "growx, wrap");
        panel.add(applyButton, "span 2, growx");
        
        return panel;
    }
    
    private JPanel createBrushPanel() {
        JPanel panel = new JPanel(new MigLayout("fillx, insets 10", "[grow]"));
        panel.setBorder(createStyledBorder("Drawing Tool"));
        
        brushGroup = new ButtonGroup();
        
        addBrushButton(panel, "Start Point", BoxType.DEPARTURE, new Color(76, 175, 80));
        addBrushButton(panel, "End Point", BoxType.ARRIVAL, new Color(244, 67, 54));
        addBrushButton(panel, "Wall", BoxType.WALL, new Color(60, 60, 60));
        addBrushButton(panel, "Empty", BoxType.EMPTY, new Color(240, 240, 240));
        
        return panel;
    }
    
    private void addBrushButton(JPanel panel, String text, BoxType type, Color color) {
        JToggleButton button = new JToggleButton(text);
        button.setBackground(color);
        button.setForeground(type == BoxType.EMPTY ? Color.BLACK : Color.WHITE);
        button.addActionListener(e -> mazePanel.setBrushType(type));
        
        brushGroup.add(button);
        panel.add(button, "growx, wrap");
        
        if (type == BoxType.WALL) {
            button.setSelected(true);
        }
    }
    
    private JPanel createActionsPanel() {
        JPanel panel = new JPanel(new MigLayout("fillx, insets 10", "[grow]"));
        panel.setBorder(createStyledBorder("Actions"));
        
        JButton solveButton = createStyledButton("Find Shortest Path", new Color(33, 150, 243));
        solveButton.addActionListener(e -> solveMaze());
        
        JButton clearSolutionButton = createStyledButton("Clear Solution", new Color(255, 152, 0));
        clearSolutionButton.addActionListener(e -> clearSolution());
        
        JButton resetButton = createStyledButton("Reset Maze", new Color(244, 67, 54));
        resetButton.addActionListener(e -> resetMaze());
        
        JButton themeButton = createStyledButton("Toggle Theme", new Color(156, 39, 176));
        themeButton.addActionListener(e -> {
            MazeSolverApp.toggleTheme();
            // Update maze panel colors after theme change
            SwingUtilities.invokeLater(() -> mazePanel.updateTheme());
        });
        
        panel.add(solveButton, "growx, wrap");
        panel.add(clearSolutionButton, "growx, wrap");
        panel.add(resetButton, "growx, wrap");
        panel.add(themeButton, "growx, wrap");
        
        return panel;
    }
    
    private JPanel createLegendPanel() {
        JPanel panel = new JPanel(new MigLayout("fillx, insets 10", "[][grow]"));
        panel.setBorder(createStyledBorder("Legend"));
        
        addLegendItem(panel, BoxType.DEPARTURE);
        addLegendItem(panel, BoxType.ARRIVAL);
        addLegendItem(panel, BoxType.WALL);
        addLegendItem(panel, BoxType.EMPTY);
        addLegendItem(panel, BoxType.SOLUTION);
        
        return panel;
    }
    
    private void addLegendItem(JPanel panel, BoxType type) {
        JPanel colorBox = new JPanel();
        colorBox.setBackground(type.getColor());
        colorBox.setPreferredSize(new Dimension(20, 20));
        colorBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        panel.add(colorBox);
        panel.add(new JLabel(type.getDisplayName()), "wrap");
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        return button;
    }
    
    private TitledBorder createStyledBorder(String title) {
        return BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            title,
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 12)
        );
    }
    
    private void applyDimensions() {
        int newWidth = (int) widthSpinner.getValue();
        int newHeight = (int) heightSpinner.getValue();
        
        // Validate dimensions
        if (newWidth < 5 || newWidth > 30 || newHeight < 5 || newHeight > 30) {
            JOptionPane.showMessageDialog(
                this,
                "Dimensions must be between 5 and 30!",
                "Invalid Dimensions",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        int result = JOptionPane.showConfirmDialog(
            this,
            "This will reset the current maze. Continue?",
            "Confirm",
            JOptionPane.YES_NO_OPTION
        );
        
        if (result == JOptionPane.YES_OPTION) {
            // Create new maze with new dimensions
            Maze newMaze = new Maze(newWidth, newHeight);
            mainFrame.setMaze(newMaze);
            mainFrame.getStatusPanel().setMessage("Maze dimensions updated to " + newWidth + "x" + newHeight);
        }
    }
    
    private void solveMaze() {
        Maze maze = mainFrame.getMaze();
        if (!maze.isValid()) {
            JOptionPane.showMessageDialog(
                this,
                "Please set both start and end points!",
                "Invalid Maze",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        maze.clearSolution();
        
        SwingWorker<PathResult, Void> worker = new SwingWorker<>() {
            @Override
            protected PathResult doInBackground() throws Exception {
                mainFrame.getStatusPanel().setMessage("Finding shortest path...");
                return mainFrame.getPathFinder().findPath(
                    maze,
                    maze.getStartBox(),
                    maze.getEndBox(),
                    maze
                );
            }
            
            @Override
            protected void done() {
                try {
                    PathResult result = get();
                    highlightPath(result.path());
                    
                    String message = String.format(
                        "Path found! Distance: %d, Nodes explored: %d, Time: %dms",
                        result.totalDistance(),
                        result.nodesExplored(),
                        result.executionTimeMs()
                    );
                    mainFrame.getStatusPanel().setMessage(message);
                    
                    JOptionPane.showMessageDialog(
                        ControlPanel.this,
                        message,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    
                } catch (Exception e) {
                    String errorMsg = e.getCause() instanceof PathNotFoundException
                        ? "No path found between start and end points!"
                        : "Error solving maze: " + e.getMessage();
                    
                    mainFrame.getStatusPanel().setMessage(errorMsg);
                    JOptionPane.showMessageDialog(
                        ControlPanel.this,
                        errorMsg,
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        };
        
        worker.execute();
    }
    
    private void highlightPath(List<Vertex> path) {
        Maze maze = mainFrame.getMaze();
        for (Vertex vertex : path) {
            MazeBox box = (MazeBox) vertex;
            if (box.getType() != BoxType.DEPARTURE && box.getType() != BoxType.ARRIVAL) {
                maze.setBoxType(box.getX(), box.getY(), BoxType.SOLUTION);
            }
        }
        mazePanel.repaint();
    }
    
    private void clearSolution() {
        mainFrame.getMaze().clearSolution();
        mazePanel.repaint();
        mainFrame.getStatusPanel().setMessage("Solution cleared");
    }
    
    private void resetMaze() {
        int result = JOptionPane.showConfirmDialog(
            this,
            "This will clear the entire maze. Continue?",
            "Confirm Reset",
            JOptionPane.YES_NO_OPTION
        );
        
        if (result == JOptionPane.YES_OPTION) {
            mainFrame.getMaze().reset();
            mazePanel.repaint();
            mainFrame.getStatusPanel().setMessage("Maze reset");
        }
    }
}
