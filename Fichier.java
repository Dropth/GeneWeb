
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.tree.TreeNode;

public abstract class Fichier implements TreeNode, Serializable, Comparable<Fichier> {
	protected String nomFichier;
	protected Dossier dossierParent;

	public Fichier(String nomFichier) {
		super();
		this.nomFichier = nomFichier;
		this.dossierParent = null;
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

	public String toString() {
		return this.getNomFichier();
	}

	public Dossier getDossierParent() {
		return dossierParent;
	}

	public void setDossierParent(Dossier dossierParent) {
		this.dossierParent = dossierParent;
	}

	public int compareTo(Fichier fichier) {
		int diff = this.nomFichier.compareTo(fichier.nomFichier);

		if (fichier instanceof Dossier && !(this instanceof Dossier))
			diff -= 1000;
		else if (this instanceof Dossier && !(fichier instanceof Dossier))
			diff += 1000;

		return diff;
	}
}
