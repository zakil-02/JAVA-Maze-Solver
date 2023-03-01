package graphicInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The DisplayPanel class is a JPanel that displays our maze and listens for mouse events.
 * It will be the panel that let the user interact with the maze.
 */
public class DisplayPanel extends JPanel implements MouseListener{
    private static final long serialVersionUID = 1L;
    private final MainApp mainApp;
    
    /**
     * Constructs a new DisplayPanel object with a reference to the given MainApp object.
     * It adds MouseListener to it and set the background in white.
     * @param mainApp the MainApp object that this display panel is associated with.
     */
	public DisplayPanel(MainApp mainApp) {
        super();
        this.mainApp=mainApp;
        setBackground(Color.WHITE);
        addMouseListener(this);
    }
	/**
	 * Overrides the default paintComponent method to draw the BoxShape objects contained in the DisplayPanel's associated
	 * MainApp object on the panel. The method retrieves the BoxShape objects from the MainApp's MazeAppModel, and then
	 * uses the Graphics object to draw each box as a filled and outlined polygon with the appropriate color.
	 *
	 * @param g the Graphics object used to paint the panel and draw the BoxShape objects.
	 */
	 @Override
	 public void paintComponent(Graphics g) {
	   super.paintComponent(g);
	   BoxShape[][] map=mainApp.getMazeAppModel().getBoxes();
	   for(int i=0;i<map.length;i++) {
		   for(int j=0;j<map[0].length;j++) {
			   BoxShape box=map[i][j];
			   g.setColor(box.getColor());
			   g.fillPolygon(box);
			   g.setColor(Color.black);
			   g.drawPolygon(box);
			 }
		 }
	   
	 }
	 
	/**
	 * Invoked when a mouse button is pressed on the component, and if a box is pressed, it will change her type depending on the 
	 * selected type in the model.
	 * @param e the MouseEvent representing the button press
	 */
	public void mousePressed(MouseEvent e) {
		 int w= mainApp.getMazeAppModel().getWidth();
		 int h = mainApp.getMazeAppModel().getHeight();
		 //We get the matrix of boxes from the model.
		 BoxShape[][] map=mainApp.getMazeAppModel().getBoxes();
		 for (int i=0;i<w;i++) {
	    	for (int j=0;j<h;j++) {
	    		BoxShape s=map[i][j];
	    		if (s.contains(e.getPoint())) {
	    			//if the box we want to change the type is departure or arrival, we should decrement the correspondent counter.
	    			if (s.getLabel()=="D"){mainApp.getMazeAppModel().setnDepartures(mainApp.getMazeAppModel().getnDepartures() - 1);}
	    			if (s.getLabel()=="A"){mainApp.getMazeAppModel().setnArrivals(mainApp.getMazeAppModel().getnArrivals() - 1);}
	    			//We get the selected type from the model.
	    			String sel = mainApp.getMazeAppModel().getSelectedType();
	    			if (sel !=null) {
	    				switch (sel) {
			    			case("w"):
			    				map[i][j] = new WallShape(s.getX(), s.getY());
			    				repaint();
			    				break;
			    			case("d"):
			    				map[i][j] = new DepartureShape(s.getX(), s.getY());
			    				//incrementing the number of departures.
			    				mainApp.getMazeAppModel().setnDepartures(mainApp.getMazeAppModel().getnDepartures() + 1);
				    			repaint();
			    				break;
			    			case("a"):
			    				map[i][j] = new ArrivalShape(s.getX(), s.getY());
			    				//incrementing the number of arrivals
			    				mainApp.getMazeAppModel().setnArrivals(mainApp.getMazeAppModel().getnArrivals() + 1);
				    			repaint();
			    				break;
			    			case("e"):
			    				map[i][j] = new EmptyShape(s.getX(), s.getY());
				    			repaint();
			    				break;
		    			}
	    			}
	    			
	    		}
	    		
	    	}
	      
		}
	}
	
	/**
	 * Notifies the displayPanel that it should update its state and be repainted.
	*/
	public void notifyForUpdate() {
		repaint();
	}
	 
	 

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}