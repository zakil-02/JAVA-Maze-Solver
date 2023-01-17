package graphicInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DisplayPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    private final MainApp mainApp;
	public DisplayPanel(MainApp mainApp) {
        super();
        this.mainApp=mainApp;
        //setLayout(new GridLayout(4,4));
        setBackground(Color.BLACK);
    }
}
