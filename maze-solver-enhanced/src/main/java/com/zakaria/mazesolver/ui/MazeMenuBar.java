package com.zakaria.mazesolver.ui;

import com.zakaria.mazesolver.model.BoxType;
import com.zakaria.mazesolver.model.Maze;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

/**
 * Menu bar for the application.
 */
public class MazeMenuBar extends JMenuBar {
    private final MainFrame mainFrame;
    private Maze maze;
    private final MazePanel mazePanel;
    
    public MazeMenuBar(MainFrame mainFrame, Maze maze, MazePanel mazePanel) {
        this.mainFrame = mainFrame;
        this.maze = maze;
        this.mazePanel = mazePanel;
        
        add(createFileMenu());
        add(createEditMenu());
        add(createHelpMenu());
    }
    
    public void updateMaze(Maze newMaze) {
        this.maze = newMaze;
    }
    
    private JMenu createFileMenu() {
        JMenu menu = new JMenu("File");
        menu.setMnemonic('F');
        
        JMenuItem importItem = new JMenuItem("Import Maze...");
        importItem.setAccelerator(KeyStroke.getKeyStroke("control O"));
        importItem.addActionListener(e -> importMaze());
        
        JMenuItem exportItem = new JMenuItem("Export Maze...");
        exportItem.setAccelerator(KeyStroke.getKeyStroke("control S"));
        exportItem.addActionListener(e -> exportMaze());
        
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke("control Q"));
        exitItem.addActionListener(e -> System.exit(0));
        
        menu.add(importItem);
        menu.add(exportItem);
        menu.addSeparator();
        menu.add(exitItem);
        
        return menu;
    }
    
    private JMenu createEditMenu() {
        JMenu menu = new JMenu("Edit");
        menu.setMnemonic('E');
        
        JMenuItem clearSolutionItem = new JMenuItem("Clear Solution");
        clearSolutionItem.setAccelerator(KeyStroke.getKeyStroke("control L"));
        clearSolutionItem.addActionListener(e -> {
            maze.clearSolution();
            mazePanel.repaint();
        });
        
        JMenuItem resetItem = new JMenuItem("Reset Maze");
        resetItem.setAccelerator(KeyStroke.getKeyStroke("control R"));
        resetItem.addActionListener(e -> {
            maze.reset();
            mazePanel.repaint();
        });
        
        menu.add(clearSolutionItem);
        menu.add(resetItem);
        
        return menu;
    }
    
    private JMenu createHelpMenu() {
        JMenu menu = new JMenu("Help");
        menu.setMnemonic('H');
        
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAboutDialog());
        
        JMenuItem instructionsItem = new JMenuItem("Instructions");
        instructionsItem.setAccelerator(KeyStroke.getKeyStroke("F1"));
        instructionsItem.addActionListener(e -> showInstructions());
        
        menu.add(instructionsItem);
        menu.addSeparator();
        menu.add(aboutItem);
        
        return menu;
    }
    
    private void importMaze() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Maze files (*.maze, *.txt)", "maze", "txt"));
        
        if (fileChooser.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                loadMazeFromFile(file);
                mainFrame.getStatusPanel().setMessage("Maze imported from " + file.getName());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    mainFrame,
                    "Error importing maze: " + ex.getMessage(),
                    "Import Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    private void exportMaze() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Maze files (*.maze)", "maze"));
        
        if (fileChooser.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().endsWith(".maze")) {
                file = new File(file.getAbsolutePath() + ".maze");
            }
            
            try {
                saveMazeToFile(file);
                mainFrame.getStatusPanel().setMessage("Maze exported to " + file.getName());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    mainFrame,
                    "Error exporting maze: " + ex.getMessage(),
                    "Export Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    private void loadMazeFromFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            maze.reset();
            int y = 0;
            String line;
            
            while ((line = reader.readLine()) != null && y < maze.getHeight()) {
                String[] cells = line.trim().split("");
                for (int x = 0; x < Math.min(cells.length, maze.getWidth()); x++) {
                    BoxType type = BoxType.fromCode(cells[x]);
                    maze.setBoxType(x, y, type);
                }
                y++;
            }
            
            mazePanel.repaint();
        }
    }
    
    private void saveMazeToFile(File file) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (int y = 0; y < maze.getHeight(); y++) {
                for (int x = 0; x < maze.getWidth(); x++) {
                    writer.print(maze.getBox(x, y).getType().getCode());
                }
                writer.println();
            }
        }
    }
    
    private void showAboutDialog() {
        String message = """
            <html>
            <h2>Maze Solver - Modern Edition</h2>
            <p><b>Version:</b> 2.0.0</p>
            <p><b>Author:</b> Zakaria AKIL</p>
            <br>
            <p>A modern hexagonal maze solver using Dijkstra's algorithm.</p>
            <p>Built with Java 21 and FlatLaf UI.</p>
            </html>
            """;
        
        JOptionPane.showMessageDialog(
            mainFrame,
            message,
            "About Maze Solver",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private void showInstructions() {
        String message = """
            <html>
            <h3>How to Use Maze Solver</h3>
            <ol>
            <li><b>Set Dimensions:</b> Choose maze size and click Apply</li>
            <li><b>Draw Maze:</b>
                <ul>
                <li>Select a tool (Start, End, Wall, Empty)</li>
                <li>Click on hexagons to place them</li>
                <li>You need exactly one Start and one End point</li>
                </ul>
            </li>
            <li><b>Solve:</b> Click "Find Shortest Path" to see the solution</li>
            <li><b>Import/Export:</b> Save and load mazes using File menu</li>
            </ol>
            <br>
            <p><b>Keyboard Shortcuts:</b></p>
            <ul>
            <li>Ctrl+O: Import maze</li>
            <li>Ctrl+S: Export maze</li>
            <li>Ctrl+L: Clear solution</li>
            <li>Ctrl+R: Reset maze</li>
            <li>F1: Show this help</li>
            </ul>
            </html>
            """;
        
        JOptionPane.showMessageDialog(
            mainFrame,
            message,
            "Instructions",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
