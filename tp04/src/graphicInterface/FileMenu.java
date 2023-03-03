package graphicInterface;

import javax.swing.JMenu;
/**
 * A subclass of JMenu that represents a menu for handling the files.
 */
public class FileMenu extends JMenu{
	private static final long serialVersionUID = 1L;
	private final MainApp mainApp;
	private final SaveItem saveItem;
	private final ImportItem importItem;
	/**
     * Constructs a new file menu with a reference to specified mainApp and with the label "File".
     * It adds a SaveItem and an ImportItem to it.
     * @param mainApp the MainApp object that this file menu is associated with.
     */
	public FileMenu(MainApp mainApp) {
		super("File");
		this.mainApp=mainApp;
		saveItem= new SaveItem(mainApp);
		importItem = new ImportItem(mainApp);
	}

	//Getters
	public ImportItem getImportItem() {
		return importItem;
	}

	public SaveItem getSaveItem() {
		return saveItem;
	}

	public MainApp getMainApp() {
		return mainApp;
	}

}
