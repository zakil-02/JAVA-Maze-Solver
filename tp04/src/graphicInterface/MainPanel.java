package graphicInterface;
import javax.swing.*;

import java.awt.*;

public class MainPanel extends JPanel {
    private static final long serialVersionUID = 1L;

	public MainPanel() {
        super();
        setBackground(Color.darkGray);
        setLayout(new BorderLayout());
        ButtonPanel p2 = new ButtonPanel();
        add(p2, BorderLayout.SOUTH);
    }
}
