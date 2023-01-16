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
		this.setLayout(new BorderLayout());
		JLabel descriptionLabel=new JLabel("<html>Choose what to put in<br>the maze: <html>");
		descriptionLabel.setForeground(Color.white);
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 14));
	
		this.add(descriptionLabel);
		this.setLayout(new FlowLayout());
		this.add(wButton= new BoxButton("Wall"));
		this.add(aButton= new BoxButton("Arrival"));
		this.add(eButton= new BoxButton("Empty"));
		this.add(dButton= new BoxButton("Departure"));
		this.add(pathButton= new ShowPathButton(mainApp), BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(180, 200));
		this.setBackground(Color.RED);
		
		
	}
	

}
