package graphicInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

/**
 * A subclass of JMenuItem that represents a menu item for leaving the game.
 */
public class QuitItem extends JMenuItem{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MainApp mainApp;
	
	/**
     * Constructs a new QuitItem with a reference to specified mainApp.
     * It adds an actionListener to it, so that when clicked the window is closed.
     * @param mainApp the MainApp object that this quit item is associated with.
     */
	public QuitItem(MainApp mainApp) {
		super("Quit");
		this.mainApp=mainApp;
		addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
	}
	public MainApp getMainApp() {
		return mainApp;
	}

}
