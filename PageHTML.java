
import java.awt.BorderLayout;
import java.awt.Insets;
import java.util.Enumeration;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.tree.TreeNode;
import javax.swing.undo.UndoManager;

public class PageHTML extends Fichier {

	private String contenu;
	public final static String NOM_PAGE_ACCUEIL = "Accueil";
	
	public PageHTML(String nomFichier) {
		super(nomFichier);
		this.contenu = "";
	}

	public String getNomFormat() {
		if(nomFichier.equals(NOM_PAGE_ACCUEIL))
			return "index.html";
					
		return super.getNomFormat()+".html";
	}
	
	@Override
	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}
}
