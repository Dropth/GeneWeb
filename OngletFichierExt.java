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

public class OngletFichierExt extends Onglet //implements UndoableEditListener
{
	private static final long serialVersionUID = 1L;


	public OngletFichierExt(Fichier fichier)
	{
		super(fichier);
		setLayout(new BorderLayout());
	}
}
