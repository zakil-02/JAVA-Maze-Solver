package graphicInterface;

import javax.swing.*;

import java.awt.*;

/**
 * A custom JPanel used as the main panel for our application and it will contain 2 different 
 * panels : configurationPanel and displayPanel.
 */
public class MainPanel extends JPanel {
	private final MainApp mainApp;
	private final ConfigPanel configPanel;
	private final DisplayPanel displayPanel;
    private static final long serialVersionUID = 1L;
    
    /**
     * Construct a new instance of MainPanel as a new JPanel containing 2 other panels.
     */
	public MainPanel(MainApp mainApp) {
        super();
        this.mainApp=mainApp;
        setBackground(Color.white);
        setLayout(new BorderLayout());
        add(configPanel=new ConfigPanel(mainApp), BorderLayout.WEST);
        add(displayPanel=new DisplayPanel(mainApp),BorderLayout.CENTER);
    }
	//Getters.
	public ConfigPanel getConfigPanel() {return configPanel;}
	public DisplayPanel getDisplayPanel() {return displayPanel;}
	public MainApp getMainApp() {return mainApp;}
	
	/**
	 * Notifies the configuration panel and display panel that an update is needed.
	 * This method should be called whenever changes are made to the data that
	 * these panels display.
	 */
	public void notifyForUpdate() {
		   configPanel.notifyForUpdate() ;
		   displayPanel.notifyForUpdate();
	}
	
}
