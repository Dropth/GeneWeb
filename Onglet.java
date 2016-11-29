import java.awt.BorderLayout;
import java.awt.Insets;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import javax.swing.undo.UndoManager;

/*
 *	Dans cette classe, un onglet correspond à un fichier
 */

public abstract class Onglet extends JPanel //implements UndoableEditListener
{
	private static final long serialVersionUID = 1L;

	protected Fichier fichier;

	public Onglet(Fichier fichier)
	{
		setLayout(new BorderLayout());
		
		this.fichier = fichier;
	}
	
	public void setFichier(Fichier fichier)
	{
		this.fichier = fichier;
	}
	
	public Fichier getFichier()
	{
		return fichier;
	}
	
	/*public void majContenu() {
		this.pageHTML.setContenu(this.textPane.getText());
	}*/

}
