package Labyrinthe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazeReadingException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Nline;
	private String fileName;
	private String errorMessage;
	public MazeReadingException(String fileName, int Nline, String errorMessage) {
		this.errorMessage=errorMessage;
		this.Nline=Nline;
		this.fileName=fileName;
		
	}
	public final void fillBoxes(Maze maze) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			br.close();
			
		}catch (IOException e){
			
		}
		
	}
	
	

}
