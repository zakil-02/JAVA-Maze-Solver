package graphicInterface;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class OkButton extends JButton implements ActionListener{
	
	/**
	 * 
	 */
	private MainApp mainApp;
	private static final long serialVersionUID = 1L;

	public OkButton(MainApp mainApp) {
		super("OK");
		this.mainApp= mainApp;
		this.setBackground(Color.DARK_GRAY);
		this.setForeground(Color.WHITE);
		
		addActionListener(this);
	}
	public void notifyForUpdate() {
		repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
