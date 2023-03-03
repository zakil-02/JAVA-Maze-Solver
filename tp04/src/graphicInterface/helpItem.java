package graphicInterface;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * A subclass of JMenuItem that represents a menu item for displaying help information.
 */
public class helpItem extends JMenuItem{
	private static final long serialVersionUID = 1L;
	private final MainApp mainApp;
	/**
     * Constructs a new helpItem with a reference to specified mainApp.
     * It adds an actionListener to it, so that when clicked a message dialog window display.
     * @param mainApp the MainApp object that this help item is associated with.
     */
	public helpItem(MainApp mainApp) {
		super("help");
		this.mainApp=mainApp;
		addActionListener(e -> {
            JOptionPane.showMessageDialog(mainApp,
                    "You can give the maze you want to solve in two different ways : \n    -import it as a text file using these keys 'E', 'D', 'A', 'W'.\n"
                    + "    -draw it manually in the display panel, you should select the type of the box in the configuration panel and click on the boxes you want to be the type selected.\n"
                    + "Once you finish representing your maze, click on showShortestPath to show the solution\n"
                    + "Then you can save the solution into a text file using the menuBar/File/save item"
                    + "Enjoy !!\n"
                    + "Rq: Telecom Paris is the best school ever ",
                    "How to use the interface",
                    JOptionPane.INFORMATION_MESSAGE);
        });
	}
	public MainApp getMainApp() {
		return mainApp;
	}

}
