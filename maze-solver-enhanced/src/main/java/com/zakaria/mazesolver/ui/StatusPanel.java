package com.zakaria.mazesolver.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Status bar panel for displaying messages.
 */
public class StatusPanel extends JPanel {
    private final JLabel statusLabel;
    
    public StatusPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        statusLabel = new JLabel("Ready");
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        
        add(statusLabel, BorderLayout.WEST);
        
        // Right side with version and author
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setOpaque(false);
        
        JLabel authorLabel = new JLabel("By: Zakaria Akil");
        authorLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        authorLabel.setForeground(Color.GRAY);
        
        JLabel versionLabel = new JLabel("v2.0.0");
        versionLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        versionLabel.setForeground(Color.GRAY);
        
        rightPanel.add(authorLabel);
        rightPanel.add(new JLabel("|"));
        rightPanel.add(versionLabel);
        
        add(rightPanel, BorderLayout.EAST);
    }
    
    public void setMessage(String message) {
        statusLabel.setText(message);
    }
}
