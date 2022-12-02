package ihm;
import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        super();
        setBackground(Color.darkGray);
        setLayout(new BorderLayout());
        ButtonPanel p2 = new ButtonPanel();
        add(p2, BorderLayout.SOUTH);
    }
}
