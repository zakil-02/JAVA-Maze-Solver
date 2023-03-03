package graphicInterface;

import javax.swing.JMenu;
/**
 * A subclass of JMenu that represents a menu for displaying help information.
 */
public class HelpMenu extends JMenu{
	private static final long serialVersionUID = 1L;
	private final helpItem hItem;
	private final MainApp mainApp;
	
	/**
     * Constructs a new help menu with a reference to specified mainApp and with the label "?".
     * It adds a helpItem to it.
     * @param mainApp the MainApp object that this help menu is associated with.
     */
	public HelpMenu(MainApp mainApp) {
		super("?");
		this.mainApp=mainApp;
		hItem = new helpItem(mainApp);
		
		add(hItem);
	}
	
	
	public helpItem gethItem() {
		return hItem;
	}
	public MainApp getMainApp() {
		return mainApp;
	}

}
