package graphicInterface;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;

/**
* The MainApp class represents the main application window for the program. It extends
* the JFrame class to provide a graphical user interface and implements the ChangeListener
* interface to handle changes to components within the frame.
*/
public class MainApp extends JFrame implements ChangeListener{
	/**
	 * 
	 */
	private MazeAppModel mazeAppModel;
	
	private final MainPanel mainPanel;
	private final MazeMenuBar menuBar;
	private static final long serialVersionUID = 1L;
	
	/**
     * Launches the application.
     * @param args command line arguments (not used)
     */
	public static void main(String args[]) {
        new MainApp();
    }
	
	/**
	 * Constructs a new MainApp object.
	 */
    public MainApp() {
        super("Maze Solver");
        mazeAppModel=new MazeAppModel();
        mazeAppModel.addObserver(this);
        mainPanel= new MainPanel(this);
        menuBar=new MazeMenuBar(this);
        //Setting the mainPanel and the menuBar in the MainApp.
        setContentPane(mainPanel);
        setJMenuBar(menuBar);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000,600));
        
        //Centering the window.
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/5 - getWidth()/5, dim.height/5 - getHeight()/5);
        
        
        //Set the Logo I created as icon of the application.
        ImageIcon icon = new ImageIcon("logoGUI.png");
        setIconImage(icon.getImage());
        
        pack();
        setVisible(true);
    }
    
    //Getters and setters.
    public MazeAppModel getMazeAppModel() {
		return mazeAppModel;
	}
	public void setMazeAppModel(MazeAppModel mazeAppModel) {
		this.mazeAppModel = mazeAppModel;
	}
	public MainPanel getMainPanel() {return mainPanel;}
	
	 /**
     * Responds to changes in components within the frame.
     * It is supposed to redraw the window in function of the model.
     * @param e the ChangeEvent that occurred
     */
	@Override
	public void stateChanged(ChangeEvent e) {
		mainPanel.notifyForUpdate();
		menuBar.notifyForUpdate();
	}

}
