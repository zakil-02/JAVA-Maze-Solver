package graphicInterface;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainApp extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String args[]) {
        new MainApp();
        
    }
    public MainApp() {
        super("Maze Game");
        MainPanel panel = new MainPanel();

        setContentPane(panel);
        //Ajout d'un menubar
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        
        //mettre des menus sur le menubar
        JMenu menu1 = new JMenu("File");
        JMenu menu2=new JMenu("setting");
        JMenu menu3=new JMenu("help");
        mb.add(menu1);
        mb.add(menu2);
        mb.add(menu3);
        //mettre une option dans le menu1
        JMenuItem menuItem = new JMenuItem("Quit");
        menu1.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        
        //
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000,600));
        
        //centrage de la fenetre 
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/5 - getWidth()/5, dim.height/5 - getHeight()/5);
        
        //Ajout d'une image comme fond 
        Container c =getContentPane();
        JLabel label =new JLabel();
        label.setIcon(new ImageIcon("Maze.jpg"));
        Dimension size = label.getPreferredSize();
        label.setBounds(50, 30, size.width, size.height);
        c.add(label);
        
        pack();
        setVisible(true);
    }

}
