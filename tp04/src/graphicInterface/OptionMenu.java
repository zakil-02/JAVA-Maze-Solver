package graphicInterface;

import javax.swing.JMenu;

/**
 * A subclass of JMenu that represents a menu for displaying help information.
 */
public class OptionMenu extends JMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MainApp mainApp;
	private final QuitItem quitItem;
	private final ResetItem resetItem;
	/**
     * Constructs a new option menu with a reference to specified mainApp and with the label "Options".
     * It adds a ResetItem and a QuitItem to it.
     * @param mainApp the MainApp object that this option menu is associated with.
     */
	public OptionMenu(MainApp mainApp) {
		super("Options");
		this.mainApp=mainApp;
		quitItem = new QuitItem(mainApp);
		resetItem = new ResetItem(mainApp);
		
		add(resetItem);
		add(quitItem);
	}
	//Getters
	public MainApp getMainApp() {
		return mainApp;
	}

	public QuitItem getQuitItem() {
		return quitItem;
	}

	public ResetItem getResetItem() {
		return resetItem;
	}

}
