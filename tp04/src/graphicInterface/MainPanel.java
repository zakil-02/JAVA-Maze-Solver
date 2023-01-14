package graphicInterface;
import javax.swing.*;

import java.awt.*;

public class MainPanel extends JPanel {
	private final MainApp mainApp;
    private static final long serialVersionUID = 1L;

	public MainPanel(MainApp mainApp) {
        super();
        this.mainApp=mainApp;
        setBackground(Color.white);
        setLayout(new BorderLayout());
        ButtonPanel p2 = new ButtonPanel();
        add(p2, BorderLayout.SOUTH);
        add(new ConfigPanel(mainApp), BorderLayout.WEST);
    }
}
