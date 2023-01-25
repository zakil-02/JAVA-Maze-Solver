package graphicInterface;
import javax.swing.*;
import javax.swing.text.*;
public class HeightField extends JTextField{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private final MainApp mainApp;
	public HeightField(MainApp mainApp) {
		super(14);
		this.mainApp=mainApp;
		((AbstractDocument) this.getDocument()).setDocumentFilter(new NumericFilter());
		this.setText("8");
	
	}
	//On utilise une classe imbriqué pour construire un filtre de saisie de l'utilisateur, on ne considere que les chiffres de 0 à 9.
	private class NumericFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string,
                                 AttributeSet attr) throws BadLocationException {
            fb.insertString(offset, string.replaceAll("[^\\d]", ""), attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String string,
                            AttributeSet attr) throws BadLocationException {
            fb.replace(offset, length, string.replaceAll("[^\\d]", ""), attr);
        }
    }
	public void notifyForUpdate() {
		repaint();
	}

}
