package graphicInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

/**
 * A subclass of JMenuItem that represents a menu item for importing a maze from a text file to choose.
 */
public class ImportItem extends JMenuItem{
	private static final long serialVersionUID = 1L;
	private final MainApp mainApp;
	/**
     * Constructs a new ImportItem with a reference to specified mainApp.
     * It adds an actionListener to it, so that when clicked a choice dialog window display.
     * @param mainApp the MainApp object that this import item is associated with.
     */
	public ImportItem(MainApp mainApp) {
		super("Import maze file");
		this.mainApp=mainApp;
		addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		JFileChooser fChooser = new JFileChooser();
        		int option = fChooser.showOpenDialog(null);
        		if (option == JFileChooser.APPROVE_OPTION) {
        			File file= fChooser.getSelectedFile();
        			try {mainApp.getMazeAppModel().importFromOs(file);}catch(Exception e) {e.getMessage();}
        			
        		}
        	}
        });
	}
	public MainApp getMainApp() {
		return mainApp;
	}

}
