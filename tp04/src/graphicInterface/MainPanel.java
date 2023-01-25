package graphicInterface;

import javax.swing.*;

import java.awt.*;

public class MainPanel extends JPanel {
	private final MainApp mainApp;
	private ConfigPanel configPanel;
	private DisplayPanel displayPanel;
    private static final long serialVersionUID = 1L;

	public MainPanel(MainApp mainApp) {
        super();
        this.mainApp=mainApp;
        setBackground(Color.white);
        setLayout(new BorderLayout());
        add(configPanel=new ConfigPanel(mainApp), BorderLayout.WEST);
        
        int width = mainApp.getMazeAppModel().getWidth(); //Le model nous donne les dimensions.
        int height = mainApp.getMazeAppModel().getHeight();
        add(displayPanel=new DisplayPanel(mainApp,width,height),BorderLayout.CENTER);
    }
	
	public ConfigPanel getConfigPanel() {return configPanel;}
	
	public void notifyForUpdate() {
		   configPanel.notifyForUpdate() ;
		   displayPanel.notifyForUpdate();
		   repaint();
		}
}
