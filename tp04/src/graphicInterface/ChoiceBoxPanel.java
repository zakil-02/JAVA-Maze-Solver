package graphicInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;


//cette classe est pour representer les types parmi lesquelles, on selectionne un.
public class ChoiceBoxPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton selectedType=null;
	
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
		this.add(new JLabel("     "));//Pour sauter la ligne
		this.add(pathButton= new ShowPathButton(mainApp), BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(180, 300));
		this.setBackground(Color.RED);
		
		
		wButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (selectedType!=null) {selectedType.setEnabled(true);}
		    	
		        wButton.setEnabled(false);
		        selectedType=wButton;		    }
		});
		dButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (selectedType!=null) {selectedType.setEnabled(true);}
		    	
		        dButton.setEnabled(false);
		        selectedType=dButton;
		        
		    }
		});
		aButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (selectedType!=null) {selectedType.setEnabled(true);}
		    	
		        aButton.setEnabled(false);
		        selectedType=aButton;
		    }
		});
		eButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (selectedType!=null) {selectedType.setEnabled(true);}
		    	
		        eButton.setEnabled(false);
		        selectedType=eButton;
		        
		    }
		});
		pathButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mainApp.getMazeAppModel().solve();
			}
		});
	}
	
	public final JButton getSelectedType() {
		return selectedType;
	}
	public final void setSelectedType(JButton selectedType) {
		this.selectedType = selectedType;
	}
	
	public void notifyForUpdate() {
		
		wButton.repaint();
		dButton.repaint();
		eButton.repaint();
		aButton.repaint();
		pathButton.repaint(); 
	}}
