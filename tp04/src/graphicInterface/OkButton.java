package graphicInterface;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class OkButton extends JButton implements ActionListener{
	
	private MainApp mainApp;
	private static final long serialVersionUID = 1L;
	/**
     * Constructs a new OkButton object with a reference to the given MainApp object.
     * It adds ActionListener to it and set the back and the fore ground in the specified colors.
     * @param mainApp the MainApp object that this button is associated with.
     */
	public OkButton(MainApp mainApp) {
		super("OK");
		this.mainApp= mainApp;
		this.setBackground(Color.DARK_GRAY);
		this.setForeground(Color.WHITE);
		
		addActionListener(this);
	}
	/**
	 * Notifies the okButton that it should update its state and be repainted.
	*/
	public void notifyForUpdate() {
		repaint();
	}
	
	/**
	 * Invoked when the OkButton is clicked, in every click it affects the dimensions in the textFields to the dimensions of the model.
	 * @param e the ActionEvent representing the button press.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int width=Integer.valueOf(mainApp.getMainPanel().getConfigPanel().getwField().getText());
		int height=Integer.valueOf(mainApp.getMainPanel().getConfigPanel().gethField().getText());
		//Reset the maze with new dimensions and empty type boxes
		mainApp.getMazeAppModel().reset(width, height);
	}

}
