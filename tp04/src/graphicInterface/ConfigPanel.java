package graphicInterface;
import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel{
	private MainApp mainApp;
	private HeightField hField;
	private WidthField wField;
	private OkButton okButton; //Boutton de confirmation de choix 
	private ChoiceBoxPanel cBoxPanel; // Le panel qui contient les differents types de boxes
	
	private static final long serialVersionUID = 1L;
	public ConfigPanel(MainApp mainApp) {
		super();
		this.mainApp=mainApp;
		//normalement le size doit dependre du size de la fenetre.
		setPreferredSize(new Dimension(200,600));
		Color sc= Color.RED;
		Color fc= Color.BLACK;
		GradientPaint gradient=new GradientPaint(0,0,sc,100,100,fc);
		setBackground(gradient.getColor1());
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
		okButton= new OkButton(mainApp);
		
		JLabel wLabel = new JLabel("Enter maze width: ");
		wLabel.setForeground(Color.WHITE);
		wLabel.setFont(new Font("Arial", Font.BOLD, 14));
		wLabel.setLabelFor(wField);
		
		JLabel hLabel = new JLabel("Enter maze height: ");
		hLabel.setForeground(Color.WHITE);
		hLabel.setFont(new Font("Arial", Font.BOLD, 14));
		hLabel.setLabelFor(hField);
	
		
		this.add(wLabel);
		this.add(wField);
		this.add(hLabel);
		this.add(hField);
		
		this.add(okButton);
		cBoxPanel= new ChoiceBoxPanel(this);
		this.add(new JLabel("     "));
		this.add(cBoxPanel);
		
		
		

	}
	public void notifyForUpdate() {
		   okButton.notifyForUpdate() ;
		   cBoxPanel.notifyForUpdate() ;
		   hField.notifyForUpdate() ;
		   wField.notifyForUpdate() ;
		}

}
