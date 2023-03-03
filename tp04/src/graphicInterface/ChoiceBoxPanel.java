package graphicInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A custom JPanel class that represent the selecting box panel,
 * it contains 4 boxes : wall, departure, arrival and empty, and
 *  the ShortestPath button that will show the solution.
 */
public class ChoiceBoxPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton selected=null;
	private final MainApp mainApp;
	private final BoxButton wButton;
	private final BoxButton dButton;
	private final BoxButton aButton;
	private final BoxButton eButton; 
	private final ShowPathButton pathButton;
	/**
     * Constructs a new ChoiceBoxPanel object with a reference to the given MainApp object.
     * It adds the 4 BoxButtons and the pathButton to it with a specified layout manager.
     * It adds actionListener to every BoxButton.
     * @param mainApp the MainApp object that this display panel is associated with.
     */
	public ChoiceBoxPanel(MainApp mainApp) {
		super();
		this.mainApp=mainApp;
		this.setLayout(new BorderLayout());
		JLabel descriptionLabel=new JLabel("<html>Choose what to put in<br>the maze: <html>");
		descriptionLabel.setForeground(Color.white);
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 14));
	
		this.add(descriptionLabel);
		this.setLayout(new FlowLayout());
		this.add(wButton= new BoxButton("Wall", Color.DARK_GRAY));
		this.add(aButton= new BoxButton("Arrival", Color.ORANGE));
		this.add(eButton= new BoxButton("Empty", Color.PINK));
		this.add(dButton= new BoxButton("Departure", Color.BLUE));
		this.add(new JLabel("     "));//To wrap a line
		this.add(pathButton= new ShowPathButton(mainApp), BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(180, 300));
		this.setBackground(Color.RED);
		
		//I did so to keep selected (disabled) every boxButton when the action is performed.
		//One button at maximum should be selected.
		wButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (selected!=null) {selected.setEnabled(true);}
		    	
		        wButton.setEnabled(false);
		        selected=wButton;
		        mainApp.getMazeAppModel().setSelectedType("w");}
		});
		dButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (selected!=null) {selected.setEnabled(true);}
		    	
		        dButton.setEnabled(false);
		        selected=dButton;
		        mainApp.getMazeAppModel().setSelectedType("d");
		    }
		});
		aButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (selected!=null) {selected.setEnabled(true);}
		    	
		        aButton.setEnabled(false);
		        selected=aButton;
		        mainApp.getMazeAppModel().setSelectedType("a");
		    }
		});
		eButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (selected!=null) {selected.setEnabled(true);}
		    	
		        eButton.setEnabled(false);
		        selected=eButton;
		        mainApp.getMazeAppModel().setSelectedType("e");
		    }
		});
		
	}
	
	
	/**
	 * Repaints all the buttons in this panel.
	*/
	public void notifyForUpdate() {
		wButton.repaint();
		dButton.repaint();
		eButton.repaint();
		aButton.repaint();
		pathButton.repaint(); 
	}
	//Getters and setters
	public final JButton getSelected() {
		return selected;
	}
	public final void setSelected(JButton selectedType) {
		this.selected = selectedType;
	}

	public MainApp getMainApp() {
		return mainApp;
	}}
