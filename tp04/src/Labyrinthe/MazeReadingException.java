package Labyrinthe;

/**

Represents an customized exception that occurs when the data in the file to be read is not valid.
*/
public class MazeReadingException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int Nline;
	private final String fileName;
	private String errorMessage;
	
	/**

	Constructs a new MazeReadingException with the specified error message, file name, and line number.
	@param fileName the name of the file being read when the exception occurred
	@param Nline the line number in the file where the error occurred
	@param errorMessage the error message describing the error that occurred
	*/
	public MazeReadingException(String fileName, int Nline, final String errorMessage) {
		super(errorMessage);
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
