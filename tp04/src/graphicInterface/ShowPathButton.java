package graphicInterface;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A button that, when pressed, displays the solution of the maze on the display panel.
 */
public class ShowPathButton extends JButton implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final MainApp mainApp;
	/**
     * Constructs a new ShowPathButton with the label "ShortestPath".
     * It adds ActionListener to it.
     * @param mainApp the MainApp object that this button is associated with.
     */
	public ShowPathButton(MainApp mainApp) {
		super("ShortestPath");
		this.mainApp=mainApp;
		this.setPreferredSize(new Dimension(170,30));
		this.setBackground(Color.DARK_GRAY);
		this.setForeground(Color.WHITE);
		addActionListener(this);
	}
	/**
     * Handles button clicks by displaying the solution of the maze if there is a solution.
     * @param e The ActionEvent representing the button click.
     * @throws Exception if the number of departures or arrivals is not 1, 
     * and handles all exceptions by showing the messages through a dialogWindow. 
     */
	@Override
	public void actionPerformed(ActionEvent e){
		try {
			//Extracting info from the model.
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
			//We try to solve it then if the solution is not already calculated and displayed.
			if (!mainApp.getMazeAppModel().isSolutionDisplayed()) {
				mainApp.getMazeAppModel().solve();
			}
			
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(mainApp, "Error!\n" + ex.getMessage(), " ErrorMessage ", JOptionPane.WARNING_MESSAGE);
			//If an error occurs, we ask the model to reset the maze.
			mainApp.getMazeAppModel().reset(mainApp.getMazeAppModel().getWidth(), mainApp.getMazeAppModel().getHeight());
		}
	}
		
}


