

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.tree.TreeNode;

public class Dossier extends Fichier {

	private ArrayList<Fichier> arFichiers;
		
	public Dossier(String nomFichier) {
		super(nomFichier);
		arFichiers = new ArrayList<Fichier>();
	}

	public ArrayList<Fichier> getArFichiers() {
		return arFichiers;
	}
	
	public void ajouterFichier(Fichier fichier) {
		assert fichier != null;
		this.arFichiers.add(fichier);
		fichier.setDossierParent(this);
		Collections.sort(this.arFichiers);
	}
	
	public void supprimerFichier(Fichier fichier) {
		this.arFichiers.remove(fichier);
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return this.arFichiers.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return this.arFichiers.size();
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
		return true;
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