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
	private JButton selected=null;
	
	private final MainApp mainApp;
	private BoxButton wButton;
	private BoxButton dButton;
	private BoxButton aButton;
	private BoxButton eButton; 
	private ShowPathButton pathButton;
	
	public ChoiceBoxPanel(MainApp mainApp) {
		super();
		this.mainApp=mainApp;
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
		pathButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mainApp.getMazeAppModel().solve();
			}
		});
	}
	
	public final JButton getSelected() {
		return selected;
	}
	public final void setSelected(JButton selectedType) {
		this.selected = selectedType;
	}
	
	public void notifyForUpdate() {
		
		wButton.repaint();
		dButton.repaint();
		eButton.repaint();
		aButton.repaint();
		pathButton.repaint(); 
	}}
