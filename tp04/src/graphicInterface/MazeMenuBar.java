package graphicInterface;

import javax.swing.*;

/**
 * The MazeMenuBar class is a Swing component that represents a menu bar for
 * the Maze application. It extends the JMenuBar class and provides custom
 * functionality specific to the Maze application, it contains a file, an option and a help menu.
 */
public class MazeMenuBar extends JMenuBar{
	private static final long serialVersionUID = 1L;
	private final JMenu fileMenu;
	private final JMenu optionMenu;
	private final JMenu helpMenu;
	private final MainApp mainApp;
	/**
     * Constructs a new MazeMenuBar with a reference to specified mainApp.
     * It adds 3 menus : 'File', 'Options' and '?'.
     * @param mainApp the MainApp object that this menu bar is associated with.
     */
	public MazeMenuBar(MainApp mainApp) {
		super();
		this.mainApp=mainApp;
		//Add this menus to the menuBar.
		this.add(fileMenu = new FileMenu(mainApp));
		this.add(optionMenu = new OptionMenu(mainApp));
		this.add(helpMenu = new HelpMenu(mainApp));
	}
	/**
	 * Repaints the 3 Menus.
	*/
	public void notifyForUpdate() {
		fileMenu.repaint();
		optionMenu.repaint();
		helpMenu.repaint();
	}
	public MainApp getMainApp() {
		return mainApp;
	}

}
