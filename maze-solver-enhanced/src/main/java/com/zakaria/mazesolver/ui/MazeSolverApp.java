package com.zakaria.mazesolver.ui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;

import javax.swing.*;
import java.awt.*;

/**
 * Main application class for the Maze Solver.
 */
public class MazeSolverApp {
    
    public static void main(String[] args) {
        // Set FlatLaf look and feel before creating any Swing components
        SwingUtilities.invokeLater(() -> {
            try {
                // Use FlatLaf Dark theme
                UIManager.setLookAndFeel(new FlatDarkLaf());
                
                // Customize UI properties
                customizeUI();
                
                // Create and show the main frame
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                    "Failed to initialize application: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    private static void customizeUI() {
        // Customize FlatLaf properties for better appearance
        UIManager.put("Button.arc", 10);
        UIManager.put("Component.arc", 10);
        UIManager.put("ProgressBar.arc", 10);
        UIManager.put("TextComponent.arc", 10);
        UIManager.put("ScrollBar.showButtons", true);
        UIManager.put("ScrollBar.width", 12);
        UIManager.put("TitlePane.unifiedBackground", false);
        
        // Custom colors
        UIManager.put("Button.hoverBackground", new Color(70, 130, 180));
        UIManager.put("Button.pressedBackground", new Color(50, 110, 160));
    }
    
    /**
     * Toggles between light and dark theme with animation.
     */
    public static void toggleTheme() {
        FlatAnimatedLafChange.showSnapshot();
        
        try {
            if (UIManager.getLookAndFeel() instanceof FlatDarkLaf) {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } else {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            }
            
            // Update all windows
            FlatLaf.updateUI();
            
            // Trigger animation
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
