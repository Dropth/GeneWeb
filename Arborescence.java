import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

public class Arborescence extends JTree implements TreeSelectionListener {

	private VueProjet vp;
	private IconsTree it;
	
	public Arborescence(VueProjet vp, Dossier racine) {
		super(new DefaultTreeModel(new DefaultMutableTreeNode(racine)));
		this.vp = vp;

		this.it = new IconsTree();
		setCellRenderer(this.it);

		try {
			for (Fichier child : racine.getArFichiers()) {
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(child);

				// System.out.println(child);
				((DefaultMutableTreeNode) this.getModel().getRoot()).add(this.listeEnfants(child, node));
			}
		} catch (NullPointerException e) {
		}
		for (int i = 0; i < getRowCount(); i++)
			expandRow(i);

		addTreeSelectionListener(this);
		setEditable(true);
	}

	private DefaultMutableTreeNode listeEnfants(Fichier fichier, DefaultMutableTreeNode node) {

		if (fichier instanceof Dossier) {
			ArrayList<Fichier> arListe = ((Dossier) fichier).getArFichiers();
			if (arListe.size() == 0) {
				System.out.println(fichier);
				return new DefaultMutableTreeNode(fichier, false);
			}

			for (Fichier child : arListe) {
				DefaultMutableTreeNode nodeChild = new DefaultMutableTreeNode(child);

				node.add(listeEnfants(child, nodeChild));
			}
			return node;
		} else {
			return new DefaultMutableTreeNode(fichier);
		}
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		Fichier select = (Fichier) ((DefaultMutableTreeNode) getLastSelectedPathComponent()).getUserObject();

		if (!(select instanceof Dossier)) {
			Fichier fichier = (Fichier) select;

			vp.getCentre().afficherOnglet(fichier);
		}
	}
	
	public void selectionnerNode(DefaultMutableTreeNode node)
	{
		this.it.getTreeCellRendererComponent(this, node, true, false, true, 0, false);
	}

	class IconsTree implements TreeCellRenderer {
		private JLabel label;
		private Font fontBase;

		IconsTree() {
			label = new JLabel();
			label.setOpaque(true);
			fontBase = label.getFont();
		}

		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
			Fichier f = (Fichier) ((DefaultMutableTreeNode) value).getUserObject();

			label.setText(f.getNomFichier());
			label.setFont(fontBase);
			
			if (f instanceof PageHTML) {
				label.setIcon(new ImageIcon("icons/file.png"));
				
				if(f.getNomFichier().equals(PageHTML.NOM_PAGE_ACCUEIL))
					label.setFont(new Font(fontBase.getName(), Font.ITALIC, fontBase.getSize()));
				
				
			} else if (f instanceof FichierImage) {
				label.setIcon(new ImageIcon("icons/pict.png"));
			} else if (f instanceof FichierExterne) {
				label.setIcon(new ImageIcon("icons/fileext.png"));
			} else if (f instanceof Dossier) {
				label.setIcon(new ImageIcon("icons/folder.png"));
				if(f == vp.getProjet().getRacine())
					label.setFont(new Font(fontBase.getName(), Font.BOLD, fontBase.getSize()));
				
			} else
				label.setIcon(new ImageIcon("icons/file.png"));

			
			
			
			label.setBorder(new EmptyBorder(0,5,0,5)); 
			
			if (selected) {
				label.setBackground(Color.BLUE);
				label.setForeground(Color.WHITE);
			} else {
				label.setBackground(Color.WHITE);
				label.setForeground(Color.BLACK);
				// setBorderSelectionColor(null);
			}

			return label;
		}
	}
}