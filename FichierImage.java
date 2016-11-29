
import java.io.File;
import java.io.Serializable;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

public class FichierImage extends Fichier implements TreeNode, Serializable {

	protected String chemin;

	public FichierImage(String nomFichier, String chemin) {
		super(nomFichier);
		this.nomFichier = nomFichier;
		this.chemin = chemin;
	}

	public String getNomFormat() {
		return StringVerif.alphanum(nomFichier);
	}

	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public String toString() {
		return this.getNomFichier();
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return null;
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
