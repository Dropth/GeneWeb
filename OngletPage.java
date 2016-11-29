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

public class OngletPage extends Onglet implements UndoableEditListener
{
	private static final long serialVersionUID = 1L;

	private JTextPane textPane;
	private HTMLEditorKit m_Kit;
	private HTMLDocument m_Document;
	private StyleSheet context;
	private UndoManager m_Undo;

	public OngletPage(Fichier fichier)
	{
		super(fichier);
		setLayout(new BorderLayout());
		
		this.textPane = new JTextPane();
		this.m_Kit = new HTMLEditorKit();
        this.context = new StyleSheet();
        this.m_Document = new HTMLDocument(context);
        this.textPane.setEditorKit(m_Kit);
        this.textPane.setDocument(m_Document);
		
		try {
			context.importStyleSheet(new URL("File:css/test.css"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        
		m_Undo = new UndoManager();
		
		textPane.getDocument().addUndoableEditListener(this);
		
		this.textPane.setMargin(new Insets(50, 50, 50, 50));
		JScrollPane sclScroll = new JScrollPane(textPane);
		add(sclScroll);
		
		this.textPane.setText(((PageHTML) this.fichier).getContenu());
		//this.textPane.repaint();
	}
	
	public void ajouterElement(String texte, HTML.Tag InsertTag) throws BadLocationException, IOException
	{
		m_Kit.insertHTML(m_Document, this.textPane.getCaretPosition(), texte, 0, 0, InsertTag);
	}

	public HTMLEditorKit getM_Kit()
	{
		return m_Kit;
	}

	public void setM_Kit(HTMLEditorKit m_Kit)
	{
		this.m_Kit = m_Kit;
	}

	public StyleSheet getContext()
	{
		return context;
	}

	public void setContext(StyleSheet context)
	{
		this.context = context;
	}

	public JTextPane getJTextPane()
	{
		return textPane;
	}

	public void setJTextPane(JTextPane textPane)
	{
		this.textPane = textPane;
	}

	public HTMLDocument getM_Document()
	{
		return m_Document;
	}

	public void setM_Document(HTMLDocument m_Document)
	{
		this.m_Document = m_Document;
	}

	public UndoManager getM_Undo()
	{
		return m_Undo;
	}

	public void setM_Undo(UndoManager m_Undo)
	{
		this.m_Undo = m_Undo;
	}
	
	@Override
	public void undoableEditHappened(UndoableEditEvent arg0)
	{
		m_Undo.addEdit(arg0.getEdit());
	}
	
	public void majContenu() {
		((PageHTML) this.fichier).setContenu(this.textPane.getText());
	}

}
