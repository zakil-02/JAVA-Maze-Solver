package graphicInterface;
import javax.swing.*;
import java.awt.*;


//cette classe est pour representer les types parmi lesquelles, on selectionne un.
public class ChoiceBox extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MainApp mainApp;
	private final ConfigPanel configPanel;
	private final String Blabel; 
	
	public ChoiceBox(MainApp mainApp, ConfigPanel configPanel, String Blabel) {
		super();
		this.mainApp=mainApp;
		this.configPanel=configPanel;
		this.Blabel=Blabel;
	}
	

}
