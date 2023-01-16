package graphicInterface;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPathButton extends JButton implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MainApp mainApp;
	public ShowPathButton(MainApp mainApp) {
		super("ShortestPath");
		this.mainApp=mainApp;
		this.setPreferredSize(new Dimension(170,30));
		this.setBackground(Color.DARK_GRAY);
		this.setForeground(Color.WHITE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
