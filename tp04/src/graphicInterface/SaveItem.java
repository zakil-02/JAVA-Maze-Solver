package graphicInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

/**
 * A subclass of JMenuItem that represents a menu item for saving the solution in a text file.
 */
public class SaveItem extends JMenuItem{
	private static final long serialVersionUID = 1L;
	private final MainApp mainApp;
	
	/**
     * Constructs a new SaveItem with a reference to specified mainApp.
     * It adds an actionListener to it, so that when clicked a choice dialog window display.
     * @param mainApp the MainApp object that this saving item is associated with.
     */
	public SaveItem(MainApp mainApp) {
		super("Save solution in a text file");
		this.mainApp=mainApp;
		addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                	JFileChooser fChooser = new JFileChooser();
            		int selection = fChooser.showSaveDialog(null);
            		if (selection == JFileChooser.APPROVE_OPTION) {
            			File file= fChooser.getSelectedFile();
            			mainApp.getMazeAppModel().saveSolution(file);
            		}
					
				} catch (Exception e) {
					e.getMessage();
				} 
            }
        });
	}
	public MainApp getMainApp() {
		return mainApp;
	}

}
