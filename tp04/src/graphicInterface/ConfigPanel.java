package graphicInterface;
import javax.swing.*;
import java.awt.*;

/**
 * The ConfigPanel class is a JPanel that will help the user to configure the maze he want to solve, he can change the dimensions and selected type..
 * It will be the panel that let the user interact with the maze.
 */
public class ConfigPanel extends JPanel{
	private final MainApp mainApp;
	private final HeightField hField;
	private final WidthField wField;
	private final OkButton okButton; 
	private final ChoiceBoxPanel cBoxPanel; 
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructs a new ConfigPanel object with a reference to the given MainApp object.
     * It sets his position in the MainApp, his color, and it adds to it a new ChoicePanel,
     * a new OkButton, and new field for height and width.
     * It adds to this ConfigPanel an icon of TELECOM PARIS.
     * @param mainApp the MainApp object that this display panel is associated with.
     */
	public ConfigPanel(MainApp mainApp) {
		super();
		this.mainApp=mainApp;
		setPreferredSize(new Dimension(200,600));
		setBackground(Color.RED);
		this.setLayout(new FlowLayout());
		
		//Inserting an icon of TELECOM PARIS .
		ImageIcon icon = new ImageIcon("telcomParisLogo.jpg");
		Image image = icon.getImage();
		int width =icon.getIconWidth()/2;
		int height=icon.getIconHeight()/2;
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		JLabel iconlabel = new JLabel(icon);
		this.add(iconlabel);
		
		hField=new HeightField(mainApp);
		wField=new WidthField(mainApp);
		okButton= new OkButton(mainApp);
		//Setting label for width field
		JLabel wLabel = new JLabel("Enter maze width: ");
		wLabel.setForeground(Color.WHITE);
		wLabel.setFont(new Font("Arial", Font.BOLD, 14));
		wLabel.setLabelFor(wField);
		//Setting label for height field
		JLabel hLabel = new JLabel("Enter maze height: ");
		hLabel.setForeground(Color.WHITE);
		hLabel.setFont(new Font("Arial", Font.BOLD, 14));
		hLabel.setLabelFor(hField);
	
		
		this.add(wLabel);
		this.add(wField);
		this.add(hLabel);
		this.add(hField);
		this.add(okButton);
		cBoxPanel= new ChoiceBoxPanel(mainApp);
		this.add(new JLabel("     "));
		this.add(cBoxPanel);
	}
	//Getters
	public ChoiceBoxPanel getChoiceBoxPanel() {
		return cBoxPanel;
	}
	public HeightField gethField() {
		return hField;
	}
	public WidthField getwField() {
		return wField;
	}
	public MainApp getMainApp() {
		return mainApp;
	}
	
	/**
	 * Notifies all the components in this object that they should update their state and be repainted.
	*/
	public void notifyForUpdate() {
		   okButton.notifyForUpdate() ;
		   cBoxPanel.notifyForUpdate() ;
		   hField.notifyForUpdate() ;
		   wField.notifyForUpdate() ;
	}
	
}
