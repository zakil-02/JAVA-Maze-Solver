package graphicInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * A subclass of JMenuItem that represents a menu item for reseting the maze.
 */
public class ResetItem extends JMenuItem{
	private static final long serialVersionUID = 1L;
	private final MainApp mainApp;
	/**
     * Constructs a new ResetItem with a reference to specified mainApp.
     * It adds an actionListener to it, so that when clicked it asks the model to reset the maze.
     * @param mainApp the MainApp object that this reset item is associated with.
     */
	public ResetItem(MainApp mainApp) {
		super("Reset");
		this.mainApp=mainApp;
		addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                mainApp.getMazeAppModel().reset(10, 10); //10x10 is the default size of the maze.
            }
        });
	}

	public MainApp getMainApp() {
		return mainApp;
	}

}
