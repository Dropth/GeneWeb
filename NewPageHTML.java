import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class NewPageHTML extends JDialog implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;

	private JTextField tfNom;
	private JButton btnHelp, btnValid, btnAnnul;
	private JLabel labNom, description, imgDescription, avertissement;
	private JPanel head, foot, rightfoot, main;
	private VueProjet vp;

	public NewPageHTML(VueProjet vp, String title, boolean modal) {
		super(vp, title, modal);
		this.vp = vp;
		setSize(450, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		initComponent();
	}

	private void initComponent() {

		/******************/
		this.head = new JPanel(new BorderLayout());

		this.imgDescription = new JLabel(new ImageIcon("icons/new.png"));
		head.add(this.imgDescription, BorderLayout.EAST);

		this.description = new JLabel("Créer une nouvelle Page HTML");
		this.description.setFont(new Font("Arial", 2, 14));
		this.head.add(this.description, BorderLayout.WEST);
		this.description
				.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		this.imgDescription.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,
				20));

		head.setPreferredSize(new Dimension(getWidth(), 75));
		head.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		head.setBackground(Color.WHITE);

		add(this.head, BorderLayout.NORTH);
		/******************/
		main = new JPanel();
		main.setLayout(new BorderLayout());
		main.setBorder(new EmptyBorder(30, 0, 0, 0));

		JPanel pCentre = new JPanel();
		pCentre.setLayout(new GridLayout(5, 2, 0, 25));

		this.labNom = new JLabel("Nom");
		pCentre.add(this.labNom);
		this.tfNom = new JTextField();
		this.tfNom.addKeyListener(this);
		pCentre.add(this.tfNom);

		main.add(pCentre);

		JPanel pDroit = new JPanel();
		pDroit.setLayout(new GridLayout(5, 1, 0, 5));
		pCentre.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));

		pDroit.setBorder(BorderFactory.createEmptyBorder(90, 20, 0, 20));
		main.add(pDroit, BorderLayout.EAST);

		setFont("trebuchet MS", 4, 12);

		main.setPreferredSize(new Dimension(200, 400));
		add(main, BorderLayout.CENTER);

		/*****************************/
		this.foot = new JPanel(new BorderLayout());
		this.rightfoot = new JPanel();
		this.foot.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		this.btnHelp = new JButton(new ImageIcon("question.png"));
		this.btnHelp.addActionListener(this);
		this.btnHelp.setBorderPainted(false);
		this.btnHelp.setContentAreaFilled(false);
		foot.add(this.btnHelp, BorderLayout.WEST);
		

		this.btnValid = new JButton("Nouvelle page");
		this.btnValid.addActionListener(this);
		this.btnValid.setEnabled(false);
		rightfoot.add(this.btnValid);

		this.btnAnnul = new JButton("Annuler");
		this.btnAnnul.addActionListener(this);
		rightfoot.add(this.btnAnnul);

		this.avertissement = new JLabel("");
		this.avertissement.setForeground(Color.RED);
		foot.add(avertissement); 
		
		foot.add(rightfoot, BorderLayout.EAST);

		add(this.foot, BorderLayout.SOUTH);
		/*****************************/
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAnnul)) {
			this.dispose();
		} else if (e.getSource().equals(btnValid)) {
			this.dispose();
			nouvellePage();
		} else if (e.getSource().equals(btnHelp)) {
			// Rediriger vers une page de tutoriel (page HTML)
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && estValide()) {
			dispose();
			nouvellePage();
		}
	}

	public void keyReleased(KeyEvent e) {

		
		if (estValide())
			btnValid.setEnabled(true);
		else
			btnValid.setEnabled(false);
	}

	public void keyTyped(KeyEvent e) {
		// Nothing
	}

	private void setFont(String police, int type, int taille) {

		this.labNom.setFont(new Font(police, type, taille));
	}

	private boolean estValide() {
		/*System.out.println(this.vp);
		if (vp == null || vp.getProjet() == null)
			return false;*/

		String strTest = StringVerif.alphanum(this.tfNom.getText());
		ArrayList<Fichier> arF = vp.getProjet().getRacine().getArFichiers();
		for (Fichier f : arF) {
			if (f.getNomFormat().equals(strTest) || f.getNomFichier().equals(this.tfNom.getText())) {
				avertissement.setText("Nom de page invalide");
				return false;
			}
		}

		if (strTest.length() < 3) {
			avertissement.setText("Nom de page invalide");
			return false;
		}
		avertissement.setText("");
		return true;
	}

	private void nouvellePage() {

		// Création du nouveau Projet
		new PageHTML(this.tfNom.getText());
		// new Projet (this.tfNom.getText(),this.tfAuteur.getText(), false,
		// this.tfEmplacement.getText());
	}

	public String getNom() {
		return this.tfNom.getText();
	}
}
