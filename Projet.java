

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Projet implements Serializable {
	private String nom;
	private String auteur;
	private Dossier racine;
	private boolean niveauExpert;
	private String emplacement;
	private String emplacementHTML;

	public Projet(String nom, String auteur, 
			boolean niveauExpert) {
		super();
		this.nom = nom;
		this.auteur = auteur;
		this.racine = new Dossier(nom);
		this.emplacementHTML = null;
		
		
		this.racine.ajouterFichier(new PageHTML("Accueil"));
		
		Dossier dossierenfant2 = new Dossier("Images");
		this.racine.ajouterFichier(dossierenfant2);

		Dossier dossierenfant3 = new Dossier("Fichiers externes");
		this.racine.ajouterFichier(dossierenfant3);

		FichierExterne enfantssdossier211 = new FichierExterne("Test.pdf", "");
		dossierenfant3.ajouterFichier(enfantssdossier211);
		
		this.niveauExpert = niveauExpert;
		this.emplacement = null;
	}

	public String getNom() {
		return nom;
	}
	
	public String getNomFormat() {
		return StringVerif.alphanum(nom);
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public Dossier getRacine() {
		return racine;
	}

	public void setRacine(Dossier racine) {
		this.racine = racine;
	}

	public boolean isNiveauExpert() {
		return niveauExpert;
	}

	public void setNiveauExpert(boolean niveauExpert) {
		this.niveauExpert = niveauExpert;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	
	public String getEmplacementHTLM() {
		return emplacementHTML;
	}

	public void setEmplacementHTML(String emplacementHTML) {
		this.emplacementHTML = emplacementHTML;
	}
	
	public Dossier getDossierImages(){
		for(Fichier f : this.getRacine().getArFichiers()) {
			if(f instanceof Dossier && f.getNomFichier().equals("Images"))
				return (Dossier) f;
		}
		return null;
	}
	
	public Dossier getDossierFichierExt(){
		for(Fichier f : this.getRacine().getArFichiers()) {
			if(f instanceof Dossier && f.getNomFichier().equals("Fichiers externes"))
				return (Dossier) f;
		}
		return null;
	}
}