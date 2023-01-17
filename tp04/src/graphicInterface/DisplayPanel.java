package graphicInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DisplayPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    private static final int startPixel=70;
    private static final int size=30;
    private final MainApp mainApp;
    private BoxShape[][] map;
	public DisplayPanel(MainApp mainApp, int w, int h) {
        super();
        this.mainApp=mainApp;
        setBackground(Color.WHITE);
        map = new BoxShape[w][h];
        
        float d1=27;
        float d2=15;
        for(int i=0;i<w;i++) {
			 for(int j=0;j<h;j++) {
				//La, on contruit une maze dont tous les boxes sont de type empty.
				 /*Le 7 sert à annuler l'espace entre chaque 2 boxes de meme ligne, on l'a fait entrer en facteur de i, 
				 afin que cette translation ne s'applique pas pour i=0.*/
				 
				 if (i%2==0) {map[i][j]=new BoxShape(startPixel+j*(2*size-7) ,(int)(startPixel +i*2*size ),Color.PINK);}
				 else {map[i][j]=new BoxShape((int)( startPixel +j*(2*size-7) + d1),(int)(startPixel +i*(2*size) - d2),Color.PINK);}
				 
			 }
		 }
        //BoxShape box1=new BoxShape(40,40,Color.LIGHT_GRAY);
    }
	 @Override
	 public void paintComponent(Graphics g) {
	   super.paintComponent(g);
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
	 public BoxShape[][] getMap(){
		 return map;
	 }
	 /*
	 public void drawMaze(int width, int height, Graphics g) {
		 for(int i=0;i<width;i++) {
			 for(int j=0;j<height;j++) {
				 paintComponent(g);
			 }
		 }
		 
	 }*/
	 
	 
	 
}
