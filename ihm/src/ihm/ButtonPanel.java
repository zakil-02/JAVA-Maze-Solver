package ihm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    public ButtonPanel() {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200,20));
        setBackground(Color.black);
        
        JButton jb = new JButton("Close");
        jb.setBackground(Color.red);
        add(jb, BorderLayout.EAST);
        jb.setPreferredSize(new Dimension(100,20));
        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
    }
}
