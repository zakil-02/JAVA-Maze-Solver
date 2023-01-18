package graphicInterface;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxButton extends JButton implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String boxLabel;
	private boolean selected;
	
	public BoxButton(String boxLabel) {
		super(boxLabel);
		this.boxLabel=boxLabel;
		//On donne une taille pour les bouttons
		this.setPreferredSize(new Dimension(80,50));
		//On va choisir un coleur pour chaque boutton.
		switch (this.boxLabel) {
		
			case("Arrival"):
				this.setBackground(Color.ORANGE);
				break;
			case("Departure"):
				this.setBackground(Color.BLUE);
				break;
			case("Empty"):
				this.setBackground(Color.PINK);
				break;
			
			case("Wall"):
				this.setBackground(Color.BLACK);
				this.setForeground(Color.WHITE);
		}
	}
	public final boolean isSelected() {
		return selected;
	}
	public final void setSelected(boolean selected) {
		this.selected = selected;
		this.setBorder(new LineBorder(Color.GRAY, 2));
		this.setBackground(Color.WHITE);
		this.setForeground(Color.GRAY);
	}
	public final String getBoxLabel() {
		return boxLabel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
