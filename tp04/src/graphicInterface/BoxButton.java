package graphicInterface;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * A custom button class that represent the box buttons in the configuration panel.
 */
public class BoxButton extends JButton{
	private static final long serialVersionUID = 1L;
	private final String boxLabel;
	private final Color color;
	private boolean selected;
	/**
	 * Constructs a new BoxButton with the specified label and background color.
	 * 
	 * @param boxLabel the label to be displayed on the button
	 * @param color the background color of the button
	 */
	public BoxButton(String boxLabel, Color color) {
		super(boxLabel);
		this.boxLabel=boxLabel;
		this.selected=false;
		//Specify buttons size
		this.setPreferredSize(new Dimension(80,50));
		//Specify button color
		this.color=color;
		this.setBackground(color);
		//Set the text in white if the background is in darkGray
		if (color.equals(Color.DARK_GRAY)) {this.setForeground(Color.WHITE);}
	}
	
	//Getters and setters
	public final boolean isSelected() {
		return selected;
	}
	public final void setSelected() {
		this.selected = true;
		this.setBorder(new LineBorder(Color.GRAY, 2));
		this.setBackground(Color.WHITE);
		this.setForeground(Color.GRAY);
	}
	public final String getBoxLabel() {
		return boxLabel;
	}
	public Color getColor() {
		return color;
	}

}
