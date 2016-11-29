import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;

/*
 * Cette classe corresspond à l'ensembles des onglets
 */

public class Centre extends JPanel implements ChangeListener {
	private VueProjet vp;
	private JTabbedPane onglets;

	public Centre(VueProjet vp) {
		this.vp = vp;

		setLayout(new BorderLayout());
		onglets = new JTabbedPane(JTabbedPane.TOP,
				JTabbedPane.SCROLL_TAB_LAYOUT);
		onglets.addChangeListener(this);
		onglets.setOpaque(false);

		add(onglets);
	}

	public Onglet getOngletSelectedIndex() {
		if (getNbOnglets() == 0)
			return null;
		else {
			Onglet ong = (Onglet) onglets.getSelectedComponent();
			vp.getPArbo().selectionnerNode(new DefaultMutableTreeNode(ong.getFichier()));
			return ong;
		}
	}

	/*
	 * public Onglet addNouveauOnglet() { Onglet nouveauOnglet = new Onglet();
	 * onglets.addTab("Fichier " + onglets.getTabCount(), nouveauOnglet);
	 * onglets.setTabComponentAt(getNbOnglets() - 1, new Panneau("Fichier " +
	 * onglets.getTabCount(), this)); return nouveauOnglet; }
	 */

	public void afficherOnglet(Fichier fichier) {
		Onglet verifOnglet = null;
		for (int i = 0; i < onglets.getTabCount(); i++) {
			Onglet test = (Onglet) onglets.getComponentAt(i);

			if (fichier == test.getFichier()) {
				verifOnglet = test;
				break;
			}
		}
		
		if (verifOnglet == null) {
			ImageIcon img = new ImageIcon("icons/file.png");
			if(fichier instanceof PageHTML) {
				verifOnglet = new OngletPage(fichier);
				img = new ImageIcon("icons/file.png");
			}
			else if(fichier instanceof FichierImage){
				verifOnglet = new OngletImage(fichier);
				img = new ImageIcon("icons/picture16.png");
			}
			else if(fichier instanceof FichierExterne) {
				verifOnglet = new OngletFichierExt(fichier);
				img = new ImageIcon("icons/fileext.png");
			}
			onglets.addTab(fichier.getNomFichier(), verifOnglet);
			
			onglets.setTabComponentAt(getNbOnglets() - 1, new Panneau(
					fichier.getNomFichier(), img, this));
		}
		onglets.setSelectedComponent(verifOnglet);
	}

	public void setTitreOnglet(int index, String titre) {
		((Panneau) onglets.getTabComponentAt(index)).setTitre(titre);
	}

	public void setTitreOngletSelected(String titre) {
		((Panneau) onglets.getTabComponentAt(getSelectedIndex()))
				.setTitre(titre);
	}

	public void suprimerOnglet(int index) {
		Onglet ong = (Onglet) onglets.getComponentAt(index);
		
		if(ong instanceof OngletPage)
			((OngletPage) ong).majContenu();
		
		onglets.remove(index);
	}

	public void supprimerOngletClique(Panneau p) {

		int indice = -1;

		for (int i = 0; i < getNbOnglets(); i++) {
			if (onglets.getTabComponentAt(i).equals(p)) {
				indice = i;
			}
		}

		if (indice != -1)
			suprimerOnglet(indice);
	}

	public void fermerOngletActif() {
		onglets.remove(getSelectedIndex());
	}

	public int getSelectedIndex() {
		return onglets.getSelectedIndex();
	}

	public int getNbOnglets() {
		return onglets.getTabCount();
	}

	@SuppressWarnings("static-access")
	public void stateChanged(ChangeEvent chE) {
		if(getNbOnglets() > 0)
			vp.setTitre(onglets.getTitleAt(getSelectedIndex()));
		else
			vp.setTitre(vp.NOM_APPLICATION);
	}

	public JTabbedPane getOnglets() {
		return onglets;
	}

	public void setOnglets(JTabbedPane onglets) {
		this.onglets = onglets;
	}
	
	public void majContenu() {
		for (int i = 0; i < getNbOnglets(); i++) {
			Onglet ong = (Onglet) onglets.getComponentAt(i);
			
			if(ong instanceof OngletPage)
				((OngletPage) ong).majContenu();
		}
	}
	
	public void supprimerTousOnglets()
	{
		onglets.removeAll();
	}
}
