package graphicInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.channels.SelectableChannel;

public class DisplayPanel extends JPanel implements MouseListener{
    private static final long serialVersionUID = 1L;
    private static final int startPixel=70;
    private static final int size=30;
    private final MainApp mainApp;
    //private BoxShape[][] map;
	public DisplayPanel(MainApp mainApp) {
        super();
        this.mainApp=mainApp;
        setBackground(Color.WHITE);
        //map=mainApp.getMazeAppModel().getBoxes();
        //Ajout du listener de la souris 
        addMouseListener(this);
    }
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
	 //getter of the map
	 //public BoxShape[][] getMap(){
		 //return map;
	 //}
	 
	
	public void mousePressed(MouseEvent e) {
		 int w= mainApp.getMazeAppModel().getWidth();
		 int h = mainApp.getMazeAppModel().getHeight();
		 BoxShape[][] map=mainApp.getMazeAppModel().getBoxes();
		 for (int i=0;i<w;i++) {
	    	for (int j=0;j<h;j++) {
	    		BoxShape s=map[i][j];
	    		if (s.contains(e.getPoint())) {
	    			String sel = mainApp.getMazeAppModel().getSelectedType();
	    			if (sel !=null) {
	    				switch (sel) {
			    			case("w"):
			    				map[i][j] = new WallShape(s.getX(), s.getY());
			    				repaint();
			    				break;
			    			case("d"):
			    				map[i][j] = new DepartureShape(s.getX(), s.getY());
				    			repaint();
			    				break;
			    			case("a"):
			    				map[i][j] = new ArrivalShape(s.getX(), s.getY());
				    			repaint();
			    				break;
			    			case("e"):
			    				map[i][j] = new EmptyShape(s.getX(), s.getY());
				    			repaint();
			    				break;
			    			default:
			    				map[i][j] = new ArrivalShape(s.getX(), s.getY());
		    			}
	    			}
	    			
	    		}
	    		
	    	}
	      
		 }
	 }
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