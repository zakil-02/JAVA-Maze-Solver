package graphicInterface;
import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel{
	private HeightField hField;
	private WidthField wField;
	private OkButton okButton; //Boutton de confirmation de choix 
	private ChoiceBoxPanel cBoxPanel; // Le panel qui contient les differents types de boxes
	
	private static final long serialVersionUID = 1L;
	public ConfigPanel(MainApp mainApp) {
		super();
		//normalement le size doit dependre du size de la fenetre.
		setPreferredSize(new Dimension(200,600));
		setBackground(Color.RED);
		//Layout manager
		this.setLayout(new FlowLayout());
		//Insersion d'une icone à size modifié.
		ImageIcon icon = new ImageIcon("telcomParisLogo.jpg");
		Image image = icon.getImage();
		int width =icon.getIconWidth()/2;
		int height=icon.getIconHeight()/2;
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		JLabel iconlabel = new JLabel(icon);
		this.add(iconlabel);
		
		hField=new HeightField();
		wField=new WidthField();
		okButton= new OkButton();
		
		JLabel wLabel = new JLabel("Enter maze width: ");
		wLabel.setForeground(Color.WHITE);
		wLabel.setLabelFor(wField);
		
		JLabel hLabel = new JLabel("Enter maze height: ");
		hLabel.setForeground(Color.WHITE);
		hLabel.setLabelFor(hField);
	
		
		this.add(wLabel, BorderLayout.PAGE_END);
		this.add(wField);
		this.add(hLabel);
		this.add(hField);
		
		okButton.setBackground(Color.PINK);
		this.add(okButton, BorderLayout.LINE_END);
		cBoxPanel= new ChoiceBoxPanel(this);
		this.add(cBoxPanel);
		
		

	}

}
