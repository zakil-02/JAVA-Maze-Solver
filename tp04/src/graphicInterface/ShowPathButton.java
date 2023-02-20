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
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		/*if (mainApp.getMainPanel().getDisplayPanel().getnArrivals()!=1 || mainApp.getMainPanel().getDisplayPanel().getnDepartures()!=1) {
			JOptionPane d = new JOptionPane();
			d.showMessageDialog(mainApp, "Error!\n Multiple destinations/Arrivals", " ErrorMessage ", JOptionPane.WARNING_MESSAGE);
			mainApp.getMazeAppModel().reset(mainApp.getMazeAppModel().getWidth(), mainApp.getMazeAppModel().getHeight());
		}*/
		try {
			mainApp.getMazeAppModel().solve();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(mainApp, "Error!\n"+ex.getMessage(), " ErrorMessage ", JOptionPane.WARNING_MESSAGE);
			mainApp.getMazeAppModel().reset(mainApp.getMazeAppModel().getWidth(), mainApp.getMazeAppModel().getHeight());
		}
	}
		
}


