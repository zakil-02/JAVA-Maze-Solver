package graphicInterface;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
	public void actionPerformed(ActionEvent e){
		try {
			int compteurArrivals=mainApp.getMazeAppModel().getnArrivals();
			int compteurDeparts=mainApp.getMazeAppModel().getnDepartures();
			if (compteurDeparts<1) {
				throw new IOException("Departure has not been specified");
			}
			if (compteurDeparts>1) {
				throw new IOException("Multiple departures have been received");
			}
			if (compteurArrivals<1) {
				throw new IOException("Destination has not been specified");
			}
			if (compteurArrivals>1) {
				throw new IOException("Multiple destinations have been received");
			}
			mainApp.getMazeAppModel().solve();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(mainApp, "Error!\n" + ex.getMessage(), " ErrorMessage ", JOptionPane.WARNING_MESSAGE);
			mainApp.getMazeAppModel().reset(mainApp.getMazeAppModel().getWidth(), mainApp.getMazeAppModel().getHeight());
		}
	}
		
}


