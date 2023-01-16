package graphicInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class BoxButton extends JButton implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String boxLabel;
	public BoxButton(String boxLabel) {
		super(boxLabel);
		this.boxLabel=boxLabel;
	}
	public final String getBoxLabel() {
		return boxLabel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
