package graphicInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MazeMenuBar extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JMenu fileMenu;
	private final JMenu optionMenu;
	private final JMenu helpMenu;
	public MazeMenuBar(MainApp mainApp) {
		super();
		//add ces menu au menuBar
		this.add(fileMenu = new JMenu("File"));
		this.add(optionMenu = new JMenu("Options"));
		this.add(helpMenu = new JMenu("Help"));
		//Mettre les items de chaque menu.
		JMenuItem  resetItem= new JMenuItem("Reset");
        JMenuItem  quitItem= new JMenuItem("Quit");
        JMenuItem saveItem = new JMenuItem("Save the maze solution");
        JMenuItem importItem = new JMenuItem("Import maze file");
        
        fileMenu.add(importItem);
        fileMenu.add(saveItem);
        optionMenu.add(resetItem);
        optionMenu.add(quitItem);
        
        //Ajouter l'action de quitItem
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
	}

}
