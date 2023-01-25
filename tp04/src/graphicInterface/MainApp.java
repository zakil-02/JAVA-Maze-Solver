package graphicInterface;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
public class MainApp extends JFrame implements ChangeListener{
	/**
	 * 
	 */
	private MazeAppModel mazeAppModel; //references vers le model
	
	private MainPanel mainPanel;
	private MazeMenuBar menuBar;
	private static final long serialVersionUID = 1L;
	//La methode main a exécuter
	public static void main(String args[]) {
        new MainApp();
    }
	//Constructeur de l'application
    public MainApp() {
        super("Maze Solver");
        mazeAppModel=new MazeAppModel();
        mazeAppModel.addObserver(this);
        mainPanel= new MainPanel(this);
        menuBar=new MazeMenuBar(this);
        //Ajouter ces attribut a l'affichage de la MainApp.
        setContentPane(mainPanel);
        setJMenuBar(menuBar);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000,600));
        
        //centrage de la fenetre 
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/5 - getWidth()/5, dim.height/5 - getHeight()/5);
        
        /*Ajout d'une image comme fond 
        Container c =getContentPane();
        JLabel label =new JLabel();
        label.setIcon(new ImageIcon("telcomParisLogo.jpg"));
        Dimension size = label.getPreferredSize();
        label.setBounds(50, 30, size.width, size.height);
        c.add(label);
        */
        
        //Set an image for the application.
        ImageIcon icon = new ImageIcon("logoGUI.png");
        setIconImage(icon.getImage());
        
        pack();
        setVisible(true);
    }
    
    //Getters and setters.
    public MazeAppModel getMazeAppModel() {
		return mazeAppModel;
	}
	public void setMazeAppModel(MazeAppModel mazeAppModel) {
		this.mazeAppModel = mazeAppModel;
	}
	public MainPanel getMainPanel() {return mainPanel;} 
	@Override
	//Cette méthode est censée redessiner la fenêtre en fonction du modèle MazeAppModel.
	public void stateChanged(ChangeEvent e) {
		mainPanel.notifyForUpdate();
	}

}
