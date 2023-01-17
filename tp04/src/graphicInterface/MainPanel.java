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
        add(displayPanel=new DisplayPanel(mainApp),BorderLayout.CENTER);
    }
}
