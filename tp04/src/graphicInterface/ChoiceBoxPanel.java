package graphicInterface;
import javax.swing.*;
import java.awt.*;


//cette classe est pour representer les types parmi lesquelles, on selectionne un.
public class ChoiceBoxPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainApp mainApp;
	private final ConfigPanel configPanel;
	private BoxButton wButton;
	private BoxButton dButton;
	private BoxButton aButton;
	private BoxButton eButton; 
	private ShowPathButton pathButton;
	
	public ChoiceBoxPanel(ConfigPanel configPanel) {
		super();
		this.configPanel=configPanel;
		this.setLayout(new FlowLayout());
		JLabel descriptionLabel=new JLabel("Choose what type of box you want to put in the maze: ");
		descriptionLabel.setForeground(Color.white);
		this.add(descriptionLabel);
		this.setLayout(new FlowLayout());
		this.add(wButton= new BoxButton("Wall"));
		this.add(aButton= new BoxButton("Arrival"));
		this.add(eButton= new BoxButton("Empty"));
		this.add(dButton= new BoxButton("Departure"));
		this.add(pathButton= new ShowPathButton(mainApp));
		
		
	}
	

}
