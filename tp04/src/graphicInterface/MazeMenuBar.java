package graphicInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
public class MazeMenuBar extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JMenu fileMenu;
	private final JMenu optionMenu;
	private final JMenu helpMenu;
	private final MainApp mainApp;
	public MazeMenuBar(MainApp mainApp) {
		super();
		this.mainApp=mainApp;
		//add ces menu au menuBar
		this.add(fileMenu = new JMenu("File"));
		this.add(optionMenu = new JMenu("Options"));
		this.add(helpMenu = new JMenu("?"));
		
		//Mettre les items de chaque menu.
		JMenuItem  resetItem= new JMenuItem("Reset");
        JMenuItem  quitItem= new JMenuItem("Quit");
        JMenuItem saveItem = new JMenuItem("Save the maze solution");
        JMenuItem importItem = new JMenuItem("Import maze file");
        JMenuItem help = new JMenuItem("help");
        fileMenu.add(importItem);
        fileMenu.add(saveItem);
        optionMenu.add(resetItem);
        optionMenu.add(quitItem);
        helpMenu.add(help);
        //Ajouter l'action de quitItem
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        
        help.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainApp,
                    "You can give the maze you want to solve in two different ways : \n    -import it as a text file using these keys 'E', 'D', 'A', 'W'.\n"
                    + "    -draw it manually in the display panel, you should select the type of the box in the configuration panel and click on the boxes you want to be the type selected.\n"
                    + "Once you finish representing your maze, click on showShortestPath to show the solution\n"
                    + "Enjoy !!\n"
                    + "Rq: Telecom Paris is the best school ever ",
                    "How to use the interface",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        
        resetItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                mainApp.getMazeAppModel().reset(10, 10); //On prend comme taille initiale 10.
            }
        });
        
        importItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		JFileChooser fChooser = new JFileChooser();
        		int option = fChooser.showOpenDialog(null);
        		if (option == JFileChooser.APPROVE_OPTION) {
        			File file= fChooser.getSelectedFile();
        			try {mainApp.getMazeAppModel().importFromOs(file);}catch(Exception e) {e.getMessage();}
        			
        		}
        	}
        });
        saveItem.addActionListener(new ActionListener() {
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

}
