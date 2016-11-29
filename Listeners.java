import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTML;
import javax.swing.undo.CannotUndoException;

public class Listeners extends MouseAdapter implements ItemListener, ActionListener, ChangeListener, CaretListener {
	VueProjet vp;

	/** Creates an instance of the JFileChooser class. */
	JFileChooser jfc;

	String word;
	int length;
	int value;
	int option;
	String content = null;
	String str;
	String name = null;

	public Listeners(VueProjet vp) {
		this.vp = vp;
	}

	/**
	 * Implements mouseClicked method
	 * 
	 * @param arg0
	 */
	public void mouseClicked(MouseEvent arg0) {
		if (vp.getCentre().getNbOnglets() == 0)
			return;

		JTextPane textPane = ((OngletPage) vp.getOngletSelectedIndex()).getJTextPane();

		boolean bSelected = isTextSelected();
		Color oColor = JColorChooser.showDialog(textPane, "Sélectionnez une couleur de texte", vp.labelColor.getBackground());
		if (oColor != null) {
			vp.labelColor.setBackground(oColor);
		}
		setJTextPaneFont(textPane, bSelected, null, null, null, null, null, null, vp.labelColor.getBackground(), null);
	}

	/**
	 * Implements itemStateChanged method
	 * 
	 * @param arg0
	 */
	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getStateChange() > 0) {
			if (vp.getCentre().getNbOnglets() == 0)
				return;

			JTextPane textPane = ((OngletPage) vp.getOngletSelectedIndex()).getJTextPane();

			boolean bSelected = isTextSelected();
			@SuppressWarnings("rawtypes")
			JComboBox cmbCombo = (JComboBox) arg0.getSource();
			if (cmbCombo.getName().compareTo("Polices") == 0) {
				setJTextPaneFont(textPane, bSelected, (String) cmbCombo.getSelectedItem(), null, null, null, null, null, null, null);
			} else if (cmbCombo.getName().compareTo("Taille") == 0) {
				setJTextPaneFont(textPane, bSelected, null, null, null, null, null, Integer.parseInt((String) cmbCombo.getSelectedItem()), null, null);
			}
		}
	}

	/**
	 * Implements actionPerformed method
	 * 
	 * @param arg0
	 */
	public void actionPerformed(ActionEvent arg0) {
		Component oComponent = (Component) arg0.getSource();

		boolean bSelected = false;
		JButton oButton = null;
		String sName = oComponent.getName();
		if (oComponent instanceof JButton) {
			oButton = ((JButton) oComponent);
		}

		if (sName.compareTo(vp.BUTTON_NAME_NEW) == 0) {
			newPage();
		} else if (sName.compareTo(vp.BUTTON_NAME_NEW_PROJECT) == 0) {
			newProject();
		} else if (sName.compareTo(vp.BUTTON_NAME_SAVE_AS) == 0) {
			saveAsFile();
		} else if (sName.compareTo(vp.BUTTON_NAME_SAVE) == 0) {
			saveFile();
		} else if (sName.compareTo(vp.BUTTON_NAME_OPEN) == 0) {
			open();
		} else if (sName.compareTo(vp.BUTTON_NAME_QUIT) == 0) {
			quitFile();
		} else if (sName.compareTo(vp.BUTTON_NAME_PRINT) == 0) {
			printFile();
		} else if (sName.compareTo(vp.BUTTON_NAME_PLAY) == 0) {
			this.generation();
		} else if (sName.compareTo(vp.BUTTON_NAME_PLAYAS) == 0) {
			this.generationAs();
		} else if (sName.compareTo(vp.BUTTON_NAME_ABOUT) == 0) {
			/*
			 * JFrame frame = new JFrame("A Propos"); JPanel panel = new
			 * ShowImage(); frame.getContentPane().add(panel);
			 * frame.setSize(500, 400); frame.setVisible(true);
			 * frame.setResizable(false); frame.setLocation(200, 100);
			 */
		}

		if (vp.getCentre().getNbOnglets() == 0)
			return;

		OngletPage ongletPage = (OngletPage) vp.getOngletSelectedIndex();
		JTextPane textPane = ongletPage.getJTextPane();

		if (sName.compareTo(vp.BUTTON_NAME_BOLD) == 0) {
			bSelected = !oButton.isSelected();
			oButton.setSelected(bSelected);
			setJTextPaneFont(textPane, isTextSelected(), null, bSelected, null, null, null, null, null, null);
			refreshToolBar();
		} else if (sName.compareTo(vp.BUTTON_NAME_ITALIC) == 0) {
			bSelected = !oButton.isSelected();
			oButton.setSelected(bSelected);
			setJTextPaneFont(textPane, isTextSelected(), null, null, bSelected, null, null, null, null, null);
			refreshToolBar();
		} else if (sName.compareTo(vp.BUTTON_NAME_UNDERLINE) == 0) {
			bSelected = !oButton.isSelected();
			oButton.setSelected(bSelected);
			setJTextPaneFont(textPane, isTextSelected(), null, null, null, bSelected, null, null, null, null);
			refreshToolBar();
		} else if (sName.compareTo(vp.BUTTON_NAME_STRIKETHROUGH) == 0) {
			bSelected = !oButton.isSelected();
			oButton.setSelected(bSelected);
			setJTextPaneFont(textPane, isTextSelected(), null, null, null, null, bSelected, null, null, null);
			refreshToolBar();
		} else if (sName.compareTo(vp.BUTTON_NAME_LEFT_ALIGN) == 0) {
			bSelected = !oButton.isSelected();
			oButton.setSelected(bSelected);
			setJTextPaneFont(textPane, isTextSelected(), null, null, null, null, null, null, null, StyleConstants.ALIGN_LEFT);
			refreshToolBar();
		} else if (sName.compareTo(vp.BUTTON_NAME_RIGHT_ALIGN) == 0) {
			bSelected = !oButton.isSelected();
			oButton.setSelected(bSelected);
			setJTextPaneFont(textPane, isTextSelected(), null, null, null, null, null, null, null, StyleConstants.ALIGN_RIGHT);
			refreshToolBar();
		} else if (sName.compareTo(vp.BUTTON_NAME_CENTER_ALIGN) == 0) {
			bSelected = !oButton.isSelected();
			oButton.setSelected(bSelected);
			setJTextPaneFont(textPane, isTextSelected(), null, null, null, null, null, null, null, StyleConstants.ALIGN_CENTER);
			refreshToolBar();
		} else if (sName.compareTo(vp.BUTTON_NAME_UNDO) == 0) {
			try {
				if (ongletPage.getM_Undo().canUndo()) {
					ongletPage.getM_Undo().undo();
				}
			} catch (CannotUndoException e) {
				System.out.println("Impossible d'annuler : " + e.getMessage());
				e.printStackTrace();
			}
		} else if (sName.compareTo(vp.BUTTON_NAME_REDO) == 0) {
			try {
				if (ongletPage.getM_Undo().canRedo()) {
					ongletPage.getM_Undo().redo();
				}
			} catch (CannotUndoException e) {
				System.out.println("Impossible de rétablir : " + e.getMessage());
				e.printStackTrace();
			}
		} else if (sName.compareTo(vp.BUTTON_NAME_COPY) == 0 || sName.compareTo(vp.BUTTON_NAME_CUT) == 0) {
			try {
				StringSelection ss = new StringSelection(textPane.getSelectedText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			} catch (IllegalStateException e) {
				System.out.println("Le presse papier n'est pas disponible");
			}
			if (sName.compareTo(vp.BUTTON_NAME_CUT) == 0) {
				if (vp.getCentre().getNbOnglets() == 0)
					return;
				textPane.replaceSelection("");
			}
		} else if (sName.compareTo(vp.BUTTON_NAME_PASTE) == 0) {
			Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
			try {
				String sText = "";
				if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
					sText = (String) t.getTransferData(DataFlavor.stringFlavor);
				}
				if (sText != "") {
					textPane.replaceSelection(sText);
				}
			} catch (UnsupportedFlavorException e1) {
				System.out.println("Format du presse papier inconnu");
			} catch (IOException e2) {
				System.out.println("Erreur d'Entrée/Sortie : " + e2.getMessage());
			}
		} else if (sName.compareTo(vp.BUTTON_NAME_FIND) == 0) {
			findFile();
		} else if (sName.compareTo(vp.BUTTON_NAME_DATE) == 0) {
			dateTime();
		} else if (sName.compareTo(vp.BUTTON_NAME_IMG) == 0) {
			this.ImgImport();
		} else if (sName.compareTo(vp.BUTTON_NAME_TITLE) == 0) {
			String titre = JOptionPane.showInputDialog("Entrez le titre :");
			try {
				if (titre != null)
					((OngletPage) vp.getOngletSelectedIndex()).ajouterElement("<h1 style=\"font-size: 35px;\">" + titre + "</h1><br/>", null);
			} catch (BadLocationException | IOException e) {
				e.printStackTrace();
			}
		} else if (sName.compareTo(vp.BUTTON_NAME_TITLE) == 0) {
			String soustitre = JOptionPane.showInputDialog("Entrez le sous-titre :");
			try {
				if (soustitre != null)
					((OngletPage) vp.getOngletSelectedIndex()).ajouterElement("<h3 style=\"font-size: 25px;\">" + soustitre + "</h3><br/>", null);
			} catch (BadLocationException | IOException e) {
				e.printStackTrace();
			}
		} else if (sName.compareTo(vp.BUTTON_NAME_LINK) == 0) {
			if (vp.getCentre().getNbOnglets() == 0)
				return;
			new Lien(vp);
		} else if (sName.compareTo(vp.BUTTON_NAME_BUBBLE_NEWS) == 0) {
			if (vp.getCentre().getNbOnglets() == 0)
				return;
			new InfoBulle(vp);
		} else if (sName.compareTo(vp.BUTTON_NAME_CLOSE) == 0) {
			vp.fermerOngletActif();
		} else if (sName.compareTo(vp.BUTTON_NAME_COLOR_POLICE) == 0) {
			boolean selected = isTextSelected();
			Color oColor = JColorChooser.showDialog(textPane, "Sélectionnez une couleur de texte", Color.BLACK);
			if (oColor != null) {
				setJTextPaneFont(textPane, selected, null, null, null, null, null, null, oColor, null);
			}
		} else if (sName.compareTo(vp.BUTTON_NAME_FILE_RENAME) == 0) {
			Fichier fich = ongletPage.getFichier();

			if (fich instanceof PageHTML && fich.getNomFichier().equals(PageHTML.NOM_PAGE_ACCUEIL)) {
				JOptionPane.showMessageDialog(null, "La page d'accueil ne peut pas être renommée.");
				return;
			}

			ArrayList<Fichier> arF = vp.getProjet().getRacine().getArFichiers();

			String chaine;
			while (true) {
				chaine = JOptionPane.showInputDialog("Renommer votre fichier :", ongletPage.getFichier().getNomFichier());
				String strTest = StringVerif.alphanum(chaine);
				for (Fichier f : arF) {
					if (f.getNomFormat().equals(strTest) || f.getNomFichier().equals(chaine)) {
						JOptionPane.showMessageDialog(null, "Nom invalide ou déjà utilisé, recommencez.");
						continue;
					}
				}
				
				if (strTest.length() < 3) {
					JOptionPane.showMessageDialog(null, "Nom trop court, recommencez.");
					continue;
				}

				break;
			}
			fich.setNomFichier(chaine);

			vp.getCentre().setTitreOngletSelected(chaine);
		} else if (sName.compareTo(vp.BUTTON_NAME_DELETE) == 0) {
			Fichier fich = ongletPage.getFichier();

			if (fich instanceof PageHTML && fich.getNomFichier().equals(PageHTML.NOM_PAGE_ACCUEIL)) {
				JOptionPane.showMessageDialog(null, "La page d'accueil ne peut pas être supprimée.");
				return;
			}

			int iReponse = JOptionPane.showConfirmDialog(null, "Etes-vous sûr(e) de supprimer le fichier \"" + fich.getNomFichier() + "\" de votre projet ?",
					"Enregistrement des modifications", JOptionPane.YES_NO_CANCEL_OPTION);
			if (iReponse == JOptionPane.YES_OPTION) {
				fich.getDossierParent().supprimerFichier(fich);
				vp.getCentre().fermerOngletActif();
				vp.majOuest();
			} else if (iReponse == JOptionPane.NO_OPTION) {
				vp.dispose();
			}
		}

	}

	@SuppressWarnings("unchecked")
	public void refreshToolBar() {
		if (vp.getCentre().getNbOnglets() == 0)
			return;

		JTextPane textPane = ((OngletPage) vp.getOngletSelectedIndex()).getJTextPane();

		AttributeSet oAttribute = textPane.getCharacterAttributes();
		if (StyleConstants.isBold(oAttribute)) {
			vp.btnGras.setSelected(true);
		} else {
			vp.btnGras.setSelected(false);
		}
		if (StyleConstants.isItalic(oAttribute)) {
			vp.btnItalic.setSelected(true);
		} else {
			vp.btnItalic.setSelected(false);
		}
		if (StyleConstants.isStrikeThrough(oAttribute)) {
			vp.btnBarre.setSelected(true);
		} else {
			vp.btnBarre.setSelected(false);
		}
		if (StyleConstants.isUnderline(oAttribute)) {
			vp.btnUnderlined.setSelected(true);
		} else {
			vp.btnUnderlined.setSelected(false);
		}
		// vp.labelColor.setBackground(StyleConstants.getForeground(oAttribute));
		selectCombo(vp.comboPolice, (String) StyleConstants.getFontFamily(oAttribute));
		selectCombo(vp.comboSize, new Integer(StyleConstants.getFontSize(oAttribute)).toString());

		oAttribute = textPane.getParagraphAttributes();
		if (StyleConstants.getAlignment(oAttribute) == StyleConstants.ALIGN_LEFT) {
			vp.btnLeft.setSelected(true);
		} else {
			vp.btnLeft.setSelected(false);
		}
		if (StyleConstants.getAlignment(oAttribute) == StyleConstants.ALIGN_RIGHT) {
			vp.btnRight.setSelected(true);
		} else {
			vp.btnRight.setSelected(false);
		}
		if (StyleConstants.getAlignment(oAttribute) == StyleConstants.ALIGN_CENTER) {
			vp.btnCenter.setSelected(true);
		} else {
			vp.btnCenter.setSelected(false);
		}
	}

	public void selectCombo(JComboBox<String> oCombo, String elem) {
		for (int i = 0; i < oCombo.getItemCount(); i++) {
			if (oCombo.getItemAt(i).compareTo(elem) == 0) {
				oCombo.setSelectedIndex(i);
				return;
			}
		}
	}

	public boolean isTextSelected() {
		JTextPane textPane = ((OngletPage) vp.getOngletSelectedIndex()).getJTextPane();

		return (textPane.getSelectedText() == null);
	}

	public static void setJTextPaneFont(JTextPane jtp, boolean bAllText, String sFontName, Boolean bBold, Boolean bItalic, Boolean bUnderline,
			Boolean bStrikeThrough, Integer iSize, Color oColor, Integer iAlignment) {

		MutableAttributeSet attrs = jtp.getInputAttributes();

		if (sFontName != null) {
			StyleConstants.setFontFamily(attrs, sFontName);
		}
		if (bBold != null) {
			StyleConstants.setBold(attrs, bBold);
		}
		if (bItalic != null) {
			StyleConstants.setItalic(attrs, bItalic);
		}
		if (iSize != null) {
			StyleConstants.setFontSize(attrs, iSize);
		}
		if (oColor != null) {
			StyleConstants.setForeground(attrs, oColor);
		}
		if (bUnderline != null) {
			StyleConstants.setUnderline(attrs, bUnderline);
		}
		if (bStrikeThrough != null) {
			StyleConstants.setStrikeThrough(attrs, bStrikeThrough);
		}

		StyledDocument doc = jtp.getStyledDocument();

		if (!bAllText) {
			// on modifie uniquement le texte sélectionné
			int iStart = jtp.getSelectionStart();
			doc.setCharacterAttributes(iStart, jtp.getSelectionEnd() - iStart, attrs, false);
		} else {
			// on modifie le style du jtext lui même
			jtp.setCharacterAttributes(attrs, false);
		}

		if (iAlignment != null) {
			StyleConstants.setAlignment(attrs, iAlignment);
			doc.setParagraphAttributes(jtp.getSelectionStart(), jtp.getSelectionEnd() - jtp.getSelectionStart(), attrs, false);
		}
	}

	public void printFile() {
		/*
		 * Creates an instance of the PrintClass that inputs the vp.area
		 * component and ed object of the Editor class as parameters.
		 * 
		 * pc = new PrintClass(textPane, ed); /* Calls the print() method.
		 */
		// pc.print();
	}

	public void findFile() {
		JTextPane textPane = ((OngletPage) vp.getOngletSelectedIndex()).getJTextPane();

		length = textPane.getDocument().getLength();
		try {
			/* Shows a word input dialog box. */
			word = JOptionPane.showInputDialog("Tapez le mot à trouver");
			while (textPane.getDocument().getText(0, length).indexOf(word) == -1) {
				/* Shows a message dialog box. */
				JOptionPane.showMessageDialog(null, "Mot introuvable!", "Résultat", JOptionPane.WARNING_MESSAGE);
				word = JOptionPane.showInputDialog(" Type the word to find");
			}
			/* Selects the word in the text area. */
			textPane.select(textPane.getDocument().getText(0, length).indexOf(word), textPane.getDocument().getText(0, length).indexOf(word) + word.length());
		} catch (Exception ex) {
			/* Shows an error message dialog box. */
			JOptionPane.showMessageDialog(null, "Rechercher annulée", "Annulation", JOptionPane.WARNING_MESSAGE);
		}
	}

	@SuppressWarnings("deprecation")
	public void dateTime() {
		/* Creates an object of the Date class. */
		Date d = new Date();
		str = d.toLocaleString();
		/* Appends the date and time in the text area. */
		vp.append(str);
	}

	/**
	 * Implements newFile method
	 */
	public void newProject() {
		new NewProject(vp, "Nouveau Projet", true);
		vp.revalidate();
		vp.updateResultArea();
	}

	public void newPage() {
		if (vp == null || vp.getProjet() == null)
			return;

		NewPageHTML np = new NewPageHTML(vp, "Nouvelle Page HTML", true);
		vp.revalidate();
		Dossier d = (Dossier) this.vp.getProjet().getRacine();
		PageHTML nvPage = new PageHTML(np.getNom());
		d.ajouterFichier(nvPage);

		vp.majOuest();
		vp.getCentre().afficherOnglet(nvPage);
	}

	public boolean open() {
		ImportSave save = new ImportSave();
		String path = save.getSavePath();
		String directory = save.getDirectory();

		try {
			// System.out.println(path);
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
			this.vp.setProjet((Projet) in.readObject());
			in.close();
			// On écrase l'emplacement actuel du projet au cas ou il aurait
			// était déplacé
			this.vp.getProjet().setEmplacement(directory);
			return true;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'éxiste pas");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			// La sauvegarde est annulé ;
		}
		return false;

	}

	// ######################################ALEX##########################################"

	public void quitFile() {

		if (this.vp.getProjet() == null) {
			vp.dispose();
		} else if (this.vp.getProjet().getEmplacement() == null) {
			int iReponse = JOptionPane.showConfirmDialog(null,
					"Attention, le document actuel n'a pas été enregistré.\n\nVoulez-vous enregistrer les modifications avant de quitter le programme ?",
					"Enregistrement des modifications", JOptionPane.YES_NO_CANCEL_OPTION);
			if (iReponse == JOptionPane.YES_OPTION) {
				saveAsFile();
			} else if (iReponse == JOptionPane.NO_OPTION) {
				vp.dispose();
			}
		} else if (this.vp.getProjet().getEmplacement() != null) {
			this.saveFile();
			this.vp.dispose();
		}
	}

	// ######################################ALEX##########################################"
	public void saveAsFile() {
		if (this.vp.getProjet() == null) {
			JOptionPane.showMessageDialog(null, "Aucun projet n'est en cours");
		} else {
			String savePath = "";
			JFileChooser choix = new JFileChooser();
			choix.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = choix.showDialog(this.vp, "Enregistrer sous");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File selection = choix.getSelectedFile();
				savePath = selection.getAbsolutePath();

			} else
				return;

			vp.getCentre().majContenu();

			// Créer répertoire pour les sauvegardes les dossiers images et els
			// fichies externes

			File file = new File(savePath + "\\\\" + this.vp.getProjet().getNomFormat());
			File fileImg = new File(savePath + "\\\\" + this.vp.getProjet().getNomFormat() + "\\\\" + "Images");
			File fileExterne = new File(savePath + "\\\\" + this.vp.getProjet().getNomFormat() + "\\\\" + "FichiersExternes");

			if (file.mkdir() && fileImg.mkdir() && fileExterne.mkdir()) {
				JOptionPane.showMessageDialog(null, "Le projet à bien été créé");
				savePath += "\\\\" + this.vp.getProjet().getNomFormat();
			} else {
				JOptionPane.showMessageDialog(null, "Le projet n'a pas pu être créé");
				return;
			}

			try {

				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(savePath + "\\\\" + this.vp.getProjet().getNomFormat() + ".site"));
				out.writeObject(this.vp.getProjet());
				out.close();
				// On remplace le chemin d'accès par le chemin d'accès et le nom
				// du fichier, cf + facile pour sauvegarder ensuite
				this.vp.getProjet().setEmplacement(savePath);

			} catch (IOException e) {
				System.out.println(e);
			}

		}

	}

	public void saveFile() {
		if (this.vp.getProjet() == null) {
			JOptionPane.showMessageDialog(null, "Aucun projet n'est en cours");
		} else if (this.vp.getProjet().getEmplacement() == null) {
			this.saveAsFile();
		} else {
			// sauvegarde général, nom titre etc
			vp.getCentre().majContenu();
			try {

				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.vp.getProjet().getEmplacement() + "\\\\"
						+ this.vp.getProjet().getNomFormat() + ".site"));
				out.writeObject(this.vp.getProjet());
				out.close();

			} catch (IOException e) {
				System.out.println(e);
			} catch (NullPointerException e) {
				System.out.println(e);
			}

		}
	}

	// ######################################ALEX##########################################"

	public void generationAs() {

		if (this.vp.getProjet() == null) {
			JOptionPane.showMessageDialog(null, "Aucun page html n'est en cours");
		}

		else {
			// Recherche du répertoire pour la génération

			String savePath = "";
			JFileChooser choix = new JFileChooser();
			choix.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = choix.showDialog(this.vp, "Générer");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File selection = choix.getSelectedFile();
				savePath = selection.getAbsolutePath();

			} else
				return;

			// Créer des répertoire pour placer toute les pages HTML

			File file = new File(savePath + "\\\\" + this.vp.getProjet().getNomFormat());
			File fileImg = new File(savePath + "\\\\" + this.vp.getProjet().getNomFormat() + "\\\\" + "Images");
			File fileExterne = new File(savePath + "\\\\" + this.vp.getProjet().getNomFormat() + "\\\\" + "FichiersExternes");

			if (file.mkdir() && fileImg.mkdir() && fileExterne.mkdir()) {
				JOptionPane.showMessageDialog(null, "Le dossier à bien été créé");
				savePath += "\\\\" + this.vp.getProjet().getNomFormat();
			} else {
				JOptionPane.showMessageDialog(null, "Le dossier n'a pas pu être créé");
				return;
			}
			// On donne un emplacement pour la génération HTML au projet
			this.vp.getProjet().setEmplacementHTML(savePath);

			// On parcour l'arraylist qui comporte 3 types de "fichiers" page
			// html, image et fichier externe

			ArrayList<Fichier> arDos = this.vp.getProjet().getRacine().getArFichiers();

			PageHTML menu = genererMenu();
			arDos.add(menu);

			try {
				// Utilisation d'un BufferedWritter pour avoir un fichier
				// parafaitement vierge avant d'écrire
				// la méthode avec ObjectOutPutStrem mettait toujours en début
				// de document une vieille saloperie genre fsegf-'

				vp.getCentre().majContenu();
				File tempFile;
				FileWriter fw;
				for (int i = 0; i < arDos.size(); i++) {
					Fichier f = arDos.get(i);
					if (f instanceof PageHTML) {
						tempFile = new File(this.vp.getProjet().getEmplacementHTLM() + "\\\\" + arDos.get(i).getNomFormat());
						fw = new FileWriter(tempFile.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(((PageHTML) arDos.get(i)).getContenu());
						bw.close();
					}
					if (f instanceof FichierImage) {
						System.out.println("IMAGE");
						// obligé de créer des file
						File vers = new File(this.vp.getProjet().getEmplacementHTLM() + "\\\\" + "Image");
						File image = new File(((FichierImage) f).getChemin());

						InputStream is = null;
						OutputStream os = null;
						try {
							is = new FileInputStream(image);
							os = new FileOutputStream(vers);
							byte[] buffer = new byte[1024];
							int length;
							while ((length = is.read(buffer)) > 0) {
								os.write(buffer, 0, length);
							}
						} finally {
							is.close();
							os.close();
						}
					}
				}
				Desktop.getDesktop().open(new File(this.vp.getProjet().getEmplacementHTLM()));

			} catch (IOException e) {
				System.out.println(e);
			}
			arDos.remove(menu);
		}
	}

	public void generation() {

		if (this.vp.getProjet() == null) {
			JOptionPane.showMessageDialog(null, "Aucun page html n'est en cours");
		} else if (this.vp.getProjet().getEmplacementHTLM() == null) {
			this.generationAs();
		} else {

			// On parcour l'arraylist qui comporte 3 types de "fichiers" page
			// html, image et fichier externe

			ArrayList<Fichier> arDos = this.vp.getProjet().getRacine().getArFichiers();

			try {
				// Utilisation d'un BufferedWritter pour avoir un fichier
				// parafaitement vierge avant d'écrire
				// la méthode avec ObjectOutPutStrem mettait toujours en début
				// de document une vieille saloperie genre fsegf-'

				vp.getCentre().majContenu();
				File tempFile;
				FileWriter fw;
				for (int i = 0; i < arDos.size(); i++) {
					Fichier f = arDos.get(i);
					if (f instanceof PageHTML) {
						tempFile = new File(this.vp.getProjet().getEmplacementHTLM() + "\\\\" + arDos.get(i).getNomFormat());
						fw = new FileWriter(tempFile.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(((PageHTML) arDos.get(i)).getContenu());
						bw.close();
					}
					if (f instanceof FichierImage) {
						System.out.println("IMAGE");
						// obligé de créer des file
						File vers = new File(this.vp.getProjet().getEmplacementHTLM() + "\\\\" + "Image");
						File image = new File(((FichierImage) f).getChemin());

						InputStream is = null;
						OutputStream os = null;
						try {
							is = new FileInputStream(image);
							os = new FileOutputStream(vers);
							byte[] buffer = new byte[1024];
							int length;
							while ((length = is.read(buffer)) > 0) {
								os.write(buffer, 0, length);
							}
						} finally {
							is.close();
							os.close();
						}
					}
				}
				Desktop.getDesktop().open(new File(this.vp.getProjet().getEmplacementHTLM()));

			} catch (IOException e) {
				this.generationAs();
			}
		}
	}

	public PageHTML genererMenu() {
		String html = "<html>\n\t<head>\n\t</head>\n\t<body>";
		html += "\n\t\t<h3>Menu du site</h3>";
		html += "\n\t\t<ul>";
		for (Fichier f : this.vp.getProjet().getRacine().getArFichiers()) {
			if (f instanceof PageHTML) {
				PageHTML p = (PageHTML) f;

				html += "\n\t\t\t<li><a href=\"" + p.getNomFormat() + "\">" + p.getNomFichier() + "</a></li>";
			}
		}
		html += "\n\t\t</ul>";
		html += "\n\t</body>\n</html>";

		PageHTML pfinal = new PageHTML("Menu");
		pfinal.setContenu(html);
		return pfinal;
	}

	public void ImgImport() {

		if (this.vp.getCentre().getNbOnglets() == 0) {
			JOptionPane.showMessageDialog(null, "Aucun projet n'est en cours");
		}

		else {
			// Recherche de l'image

			String savePath = "";
			String name = "";
			JFileChooser choix = new JFileChooser();
			FileNameExtensionFilter picture = new FileNameExtensionFilter("images", "jpg", "gif", "png", "jpeg", "bmp");
			choix.setFileFilter(picture);
			choix.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int returnVal = choix.showDialog(this.vp, "Insérer");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File selection = choix.getSelectedFile();
				savePath = selection.getAbsolutePath();
				name = selection.getName();

			}
			String balise = "<img src =\"file:///" + savePath + "\">";
			try {
				((OngletPage) this.vp.getOngletSelectedIndex()).ajouterElement(balise, HTML.Tag.IMG);
				vp.getProjet().getDossierImages().ajouterFichier(new FichierImage(name, savePath));
			} catch (IOException e) {
				System.out.println(e);
			} catch (BadLocationException e) {
				System.out.println(e);
			}
			vp.getCentre().majContenu();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		refreshToolBar();
	}

	@Override
	public void caretUpdate(CaretEvent arg0) {
		refreshToolBar();
	}
}
