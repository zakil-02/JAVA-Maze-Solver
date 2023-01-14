package graphicInterface;
import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private final MainApp mainApp;
	public ConfigPanel(MainApp mainApp) {
		super();
		this.mainApp=mainApp;
		//normalement le size doit dependre du size de la fenetre.
		setPreferredSize(new Dimension(200,400));
		setBackground(Color.RED);
		ImageIcon icon = new ImageIcon("logoGUI.png");
		//Image image = icon.getImage();
		//int width =icon.getIconWidth()/1;
		//int height=icon.getIconHeight()/1;
		//Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
		//icon = new ImageIcon(newimg);
		JLabel label = new JLabel(icon);
		this.add(label);

	}

}
