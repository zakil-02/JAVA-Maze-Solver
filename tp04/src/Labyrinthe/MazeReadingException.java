package Labyrinthe;

public class MazeReadingException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int Nline;
	private final String fileName;
	private String errorMessage;
	public MazeReadingException(String fileName, int Nline, String errorMessage) {
		this.errorMessage=errorMessage;
		this.Nline=Nline;
		this.fileName=fileName;
		
	}
	public final int getNline() {
		return Nline;
	}
	public final String getFileName() {
		return fileName;
	}
	public final String getErrorMessage() {
		return errorMessage;
	}
	
	
	
	

}
