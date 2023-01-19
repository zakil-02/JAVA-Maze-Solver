package graphicInterface;
import java.util.*;
import javax.swing.event.*;

public class MazeAppModel {
	private final List<ChangeListener> listeners = new ArrayList<ChangeListener>() ;
	public MazeAppModel() {
		// TODO Auto-generated constructor stub
	}
	
	public void addObserver(ChangeListener listener) {
	listeners.add(listener) ;
	}

}
