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
        setBackground(Color.WHITE);
        //BoxShape box1=new BoxShape(40,40,Color.LIGHT_GRAY);
    }
	 @Override
	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    BoxShape box1=new BoxShape(200,100,Color.BLUE);
	    g.setColor(Color.blue);
	    g.fillPolygon(box1);
	    g.drawPolygon(box1);
	  }
}
