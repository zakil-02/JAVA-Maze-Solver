package graphicInterface;
import javax.swing.*;
import javax.swing.text.*;

/**
 * A custom text field that is used to receive the width of the maze 
 * the user want to create.
 * This class extends the JTextField class.
 */
public class WidthField extends JTextField{
	private static final long serialVersionUID = 1L;
	private final MainApp mainApp;
	
	/**
     * Constructs a new WidthField object with a default width of 14 columns and a reference to the given MainApp object.
     * It sets the numeric filter for the input, and "10" as default text.
     * @param mainApp the MainApp object that this button is associated with.
     */
	public WidthField(MainApp mainApp) {
		super(14);
		this.mainApp=mainApp;
		((AbstractDocument) this.getDocument()).setDocumentFilter(new NumericFilter());
		this.setText("10");
	}
	/**
	 * A class that filters input into this text field so that only numeric characters are allowed.
	 * Thus, we will avoid even receiving a non numeric input from the user. 
	 * This class extends the DocumentFilter class from the javax.swing.text package.
	 */
	private class NumericFilter extends DocumentFilter {
		/**
	     * Called by the text field when a new string is inserted.
	     * Filters the input string to remove any non-numeric characters before inserting it into the document.
	     *
	     * @param fb the FilterBypass object that allows you to insert or remove text directly into the underlying document
	     * @param offset the position where the new text will be inserted
	     * @param string the new text to be inserted
	     * @param attr the attributes for the new text
	     * @throws BadLocationException if the given position is not a valid position within the document
	     */
        @Override
        public void insertString(FilterBypass fb, int offset, String string,
                                 AttributeSet attr) throws BadLocationException {
            fb.insertString(offset, string.replaceAll("[^\\d]", ""), attr);
        }
        /**
         * Called by the text field when an existing string is replaced.
         * Filters the replacement string to remove any non-numeric characters before inserting it into the document.
         *
         * @param fb the FilterBypass object that allows you to insert or remove text directly into the underlying document
         * @param offset the position where the replacement will start
         * @param length the number of characters to be replaced
         * @param string the replacement text
         * @param attr the attributes for the replacement text
         * @throws BadLocationException if the given position is not a valid position within the document
         */
        @Override
        public void replace(FilterBypass fb, int offset, int length, String string,
                            AttributeSet attr) throws BadLocationException {
            fb.replace(offset, length, string.replaceAll("[^\\d]", ""), attr);
        }
    }
	/**
	 * Repaints the widthField.
	*/
	public void notifyForUpdate() {
		repaint();
	}
	
	public MainApp getMainApp() {
		return mainApp;
	}
}
