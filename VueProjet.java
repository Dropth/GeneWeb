import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/*
 * Cette classe affiche l'ensemble du projet. Elle est composée
 * de plusieurs JPanel
 * 
 */
public class VueProjet extends JFrame {

	private static final long serialVersionUID = 1L;

	private JSplitPane splitOuest;

	private Centre centre;

	private JPanel ouest;
	private Arborescence pArbo;
	private Projet projet;

	// Bouton pour le panel Est

	/** Fields list */
	JLabel labelColor;
	@SuppressWarnings("rawtypes")
	JComboBox comboPolice;
	JButton btnGras;
	JButton btnItalic;
	JButton btnUnderlined;
	JButton btnBarre;
	JButton btnCut;
	JButton btnCopy;
	JButton btnPaste;
	JButton btnLeft;
	JButton btnRight;
	JButton btnCenter;
	JButton btnUndo;
	JButton btnRedo;
	JButton btnNew;
	JButton btnOpen;
	JButton btnSaveAs;
	JButton btnSave;
	JButton btnPrint;
	JButton btnTitle;
	JButton btnSubtitle;
	JButton btnTable;
	JButton btnImage;
	JButton btnVideo;
	JButton btnLien;
	JButton btnInfoBulle;
	JButton btnPoliceColor;
	JButton btnPlay;
	JButton btnPlayAs;
	JButton btnProject;
	JMenuItem Cut_Menu;
	JMenuItem Copy_Menu;
	JMenu Edition_Menu;
	JPopupMenu Edition_Popup_Menu;
	JMenu Insertion_Menu;
	JMenuItem Popup_Menu_Cut, Popup_Menu_Copy, Popup_Menu_Paste,Popup_Menu_SelectAll;
	JComboBox<String> comboSize;
	JLabel resultArea;
	JMenu BgColor_Menu;
	ChoixTableau choixTableau;
	String[] nomCouleur = { "Blanc", "Noir", "Rouge", "Vert", "Bleu", "Jaune",
			"Orange", "Gris", "Gris clair" };
	Color[] couleur = { Color.white, Color.black, Color.red, Color.green,
			Color.blue, Color.yellow, Color.orange, Color.gray, Color.lightGray };
	/** Constant */
	public static final String NOM_APPLICATION = "Géné-Site";
	

	/** Button Image Files */
	public static final String BUTTON_IMAGE_FILE_REDO = "icons/redo.png";
	public static final String BUTTON_IMAGE_FILE_UNDO = "icons/undo.png";
	public static final String BUTTON_IMAGE_FILE_PASTE = "icons/paste.png";
	public static final String BUTTON_IMAGE_FILE_COPY = "icons/copier.png";
	public static final String BUTTON_IMAGE_FILE_CUT = "icons/couper.png";
	public static final String BUTTON_IMAGE_FILE_SAVE_AS = "icons/saveas.png";
	public static final String BUTTON_IMAGE_FILE_SAVE = "icons/save.png";
	public static final String BUTTON_IMAGE_FILE_OPEN = "icons/open.png";
	public static final String BUTTON_IMAGE_FILE_NEW = "icons/new.png";
	public static final String BUTTON_IMAGE_FILE_RIGHT_ALIGN = "icons/rightjustify.png";
	public static final String BUTTON_IMAGE_FILE_CENTER_ALIGN = "icons/centerjustify.png";
	public static final String BUTTON_IMAGE_FILE_LEFT_ALIGN = "icons/leftjustify.png";
	public static final String BUTTON_IMAGE_FILE_STRIKETHROUGH = "icons/STRIKTHR.png";
	public static final String BUTTON_IMAGE_FILE_UNDERLINE = "icons/UNDRLN.png";
	public static final String BUTTON_IMAGE_FILE_ITALIC = "icons/ITL.png";
	public static final String BUTTON_IMAGE_FILE_BOLD = "icons/bold.png";
	public static final String BUTTON_IMAGE_FILE_PRINT = "icons/print.png";
	public static final String BUTTON_IMAGE_FILE_FIND = "icons/find.png";
	public static final String BUTTON_IMAGE_FILE_SELECT_ALL = "icons/select-all.png";
	public static final String BUTTON_IMAGE_FILE_TIME = "icons/time.png";
	public static final String BUTTON_IMAGE_FILE_CREDIT = "icons/credit.png";
	public static final String BUTTON_IMAGE_FILE_QUIT = "icons/quit.png";
	public static final String BUTTON_IMAGE_FILE_ABOUT = "icons/about.png";
	public static final String BUTTON_IMAGE_NEW_PROJECT = "icons/project.png";
	public static final String BUTTON_IMAGE_FILE_COLOR_POLICE = "icons/font-color.png";
	public static final String BUTTON_IMAGE_FILE_PLAY = "icons/play.png";
	public static final String BUTTON_IMAGE_FILE_PLAYAS = "icons/playas.png";
	public static final String BUTTON_IMAGE_FILE_TITLE = "icons/title.png";
	public static final String BUTTON_IMAGE_FILE_SUBTITLE = "icons/subtitle.png";
	public static final String BUTTON_IMAGE_FILE_TABLE = "icons/table-add-icon.png";
	public static final String BUTTON_IMAGE_FILE_IMAGE = "icons/picture.png";
	public static final String BUTTON_IMAGE_FILE_VIDEO = "icons/movie-icon.png";
	public static final String BUTTON_IMAGE_FILE_LINK = "icons/link-add-icon.png";
	public static final String BUTTON_IMAGE_FILE_BUBBLE_NEWS = "icons/info.png";
	public static final String BUTTON_IMAGE_FILE_DELETE = "icons/delete-file-icon.png";
	public static final String BUTTON_IMAGE_FILE_DICTIONARY = "icons/dictionary-icon.png";
	public static final String BUTTON_IMAGE_FILE_RENAME = "icons/rename-icon.png";
	
	/** Button Names */
	public final String BUTTON_NAME_SAVE_AS = "Enregistrer sous";
	public final String BUTTON_NAME_SAVE = "Enregistrer";
	public final String BUTTON_NAME_OPEN = "Ouvrir";
	public final String BUTTON_NAME_NEW = "Nouvelle page HTML";
	public final String BUTTON_NAME_NEW_PROJECT = "Nouveau projet";
	public final String BUTTON_NAME_CLOSE = "Fermer la page HTML";
	public final String BUTTON_NAME_PASTE = "Coller";
	public final String BUTTON_NAME_CUT = "Couper";
	public final String BUTTON_NAME_COPY = "Copier";
	public final String BUTTON_NAME_REDO = "Rétablir";
	public final String BUTTON_NAME_UNDO = "Annuler";
	public final String BUTTON_NAME_CENTER_ALIGN = "Centre";
	public final String BUTTON_NAME_RIGHT_ALIGN = "Droite";
	public final String BUTTON_NAME_LEFT_ALIGN = "Gauche";
	public final String BUTTON_NAME_STRIKETHROUGH = "Barre";
	public final String BUTTON_NAME_UNDERLINE = "Souligne";
	public final String BUTTON_NAME_ITALIC = "Italique";
	public final String BUTTON_NAME_BOLD = "Gras";
	public final String BUTTON_NAME_POLICE = "Polices";
	public final String BUTTON_NAME_SIZE = "Taille";
	public final String BUTTON_NAME_PRINT = "Imprimer";
	public final String BUTTON_NAME_QUIT = "Quit";
	public final String BUTTON_NAME_BG_COLOR = "Couleur du Font";
	public final String BUTTON_NAME_FIND = "Rechercher";
	public final String BUTTON_NAME_DATE = "Date/Heure";
	public final String BUTTON_NAME_IMG = "Insérer Image";
	public final String BUTTON_NAME_ABOUT = "A Propos";
	public final String BUTTON_NAME_COLOR_POLICE = "Couleur";
	public final String BUTTON_NAME_PLAY = "Générer";
	public final String BUTTON_NAME_PLAYAS = "Générer sous";
	public final String BUTTON_NAME_TITLE = "Titre";
	public final String BUTTON_NAME_SUBTITLE = "Sous-Titre";
	public final String BUTTON_NAME_IMAGE = "Image";
	public final String BUTTON_NAME_VIDEO = "Video";
	public final String BUTTON_NAME_LINK = "Hyper-liens";
	public final String BUTTON_NAME_TABLE = "Tableau";
	public final String BUTTON_NAME_BUBBLE_NEWS = "Info-Bulle";
	public final String BUTTON_NAME_DICTIONARY = "Dictionnaire";
	public final String BUTTON_NAME_FRENCH_DICTIONARY = "Français";
	public final String BUTTON_NAME_ENGLISH_DICTIONARY = "Anglais";
	public final String BUTTON_NAME_FILE_RENAME = "Renommer";
	public final String BUTTON_NAME_DELETE = "Supprimer";
	
	Listeners listeners;

	public VueProjet()
	{

		super();

		listeners = new Listeners(this);

		setTitle("Géné-Site");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(500, 500));

		Image icon = Toolkit.getDefaultToolkit().getImage("logo/GeneWeb48.png");
		setIconImage(icon);
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} 
		catch (Exception e)
		{
			System.out.println("FrmUsers.FrmUsers : impossible d'appliquer le thème du système");
		}

		JToolBar Display_ToolBar = createDisplayToolBar();
		JToolBar File_ToolBar = createFileToolBar();
		JToolBar Elements_ToolBar = createElementToolBar();

		JMenuBar Principal_Menu = createMenu();
		this.setJMenuBar(Principal_Menu);

		JPanel panTools = new JPanel(new GridLayout(3,1));
		JPanel principal = new JPanel(new BorderLayout());
		
		panTools.add(File_ToolBar);
		panTools.add(Display_ToolBar);
		panTools.add(Elements_ToolBar);	
		add(panTools, BorderLayout.NORTH);
		
		this.ouest = new JPanel();
		
		resultArea = new JLabel();
		
		centre = new Centre(this);
		
		principal.add(centre);
		principal.add(resultArea, BorderLayout.SOUTH);

		this.splitOuest = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, ouest, principal);
		this.splitOuest.setDividerLocation(150);
		add(splitOuest);

		this.setVisible(true);
		
		new NewOrOpenProject(this);
		
	}

	public JToolBar createFileToolBar() {
		JToolBar toolFile = new JToolBar();
		
		btnProject = createButton(BUTTON_IMAGE_NEW_PROJECT, BUTTON_NAME_NEW_PROJECT);
		toolFile.add(btnProject);
		btnNew = createButton(BUTTON_IMAGE_FILE_NEW, BUTTON_NAME_NEW);
		toolFile.add(btnNew);
		btnOpen = createButton(BUTTON_IMAGE_FILE_OPEN, BUTTON_NAME_OPEN);
		toolFile.add(btnOpen);
		btnSave = createButton(BUTTON_IMAGE_FILE_SAVE, BUTTON_NAME_SAVE);
		toolFile.add(btnSave);
		btnSaveAs = createButton(BUTTON_IMAGE_FILE_SAVE_AS, BUTTON_NAME_SAVE_AS);
		toolFile.add(btnSaveAs);
		btnPrint = createButton(BUTTON_IMAGE_FILE_PRINT, BUTTON_NAME_PRINT);
		toolFile.add(btnPrint);
		toolFile.addSeparator();
		btnCut = createButton(BUTTON_IMAGE_FILE_CUT, BUTTON_NAME_CUT);
		toolFile.add(btnCut);
		btnCopy = createButton(BUTTON_IMAGE_FILE_COPY, BUTTON_NAME_COPY);
		toolFile.add(btnCopy);
		btnPaste = createButton(BUTTON_IMAGE_FILE_PASTE, BUTTON_NAME_PASTE);
		toolFile.add(btnPaste);
		toolFile.addSeparator();
		btnUndo = createButton(BUTTON_IMAGE_FILE_UNDO, BUTTON_NAME_UNDO);
		toolFile.add(btnUndo);
		btnRedo = createButton(BUTTON_IMAGE_FILE_REDO, BUTTON_NAME_REDO);
		toolFile.add(btnRedo);
		toolFile.addSeparator();
		btnPlay = createButton(BUTTON_IMAGE_FILE_PLAY, BUTTON_NAME_PLAY);
		toolFile.add(btnPlay);
		btnPlayAs = createButton(BUTTON_IMAGE_FILE_PLAYAS, BUTTON_NAME_PLAYAS);
		toolFile.add(btnPlayAs);

		return toolFile;
	}

	public JMenuBar createMenu() {
		JMenuBar Principal_Menu = new JMenuBar();
		// Fichier Menu //
		JMenu Fichier_Menu = new JMenu("Fichier");
		Principal_Menu.add(Fichier_Menu);
		// New Project //
		JMenuItem NewProject_Menu = new JMenuItem("Nouveau Projet", new ImageIcon(BUTTON_IMAGE_NEW_PROJECT));
		NewProject_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK));
		NewProject_Menu.setName(BUTTON_NAME_NEW_PROJECT);
		NewProject_Menu.addActionListener(listeners);
		// New MenuItem //
		JMenuItem New_Menu = new JMenuItem("Nouvelle Page", new ImageIcon(BUTTON_IMAGE_FILE_NEW));
		New_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		New_Menu.setName(BUTTON_NAME_NEW);
		New_Menu.addActionListener(listeners);
		// Open MenuItem //
		JMenuItem Open_Menu = new JMenuItem("Ouvrir", new ImageIcon(BUTTON_IMAGE_FILE_OPEN));
		Open_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
		Open_Menu.setName(BUTTON_NAME_OPEN);
		Open_Menu.addActionListener(listeners);
		// Save MenuItem //
		JMenuItem Save_Menu = new JMenuItem("Enregistrer", new ImageIcon(BUTTON_IMAGE_FILE_SAVE));
		Save_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		Save_Menu.setName(BUTTON_NAME_SAVE);
		Save_Menu.addActionListener(listeners);
		// SaveAs MenuItem //
		JMenuItem SaveAs_Menu = new JMenuItem("Enregistrer Sous", new ImageIcon(BUTTON_IMAGE_FILE_SAVE_AS));
		SaveAs_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_MASK));
		SaveAs_Menu.setName(BUTTON_NAME_SAVE_AS);
		SaveAs_Menu.addActionListener(listeners);
		//Play MenuItem//
		JMenuItem Play_Menu = new JMenuItem("Générer", new ImageIcon(BUTTON_IMAGE_FILE_PLAY));
		Play_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK));
		Play_Menu.setName(BUTTON_NAME_PLAY);
		Play_Menu.addActionListener(listeners);
		//PlayAs MenuItem//
		JMenuItem PlayAs_Menu = new JMenuItem("Générer sous", new ImageIcon(BUTTON_IMAGE_FILE_PLAYAS));
		Play_Menu.setName(BUTTON_NAME_PLAYAS);
		Play_Menu.addActionListener(listeners);
		//Rename MenuItem//
		JMenuItem Rename_Menu = new JMenuItem("Renommer", new ImageIcon(BUTTON_IMAGE_FILE_RENAME));
		Rename_Menu.setName(BUTTON_NAME_FILE_RENAME);
		Rename_Menu.addActionListener(listeners);
		//Close MenuItem//
		JMenuItem CloseFile_Menu = new JMenuItem("Fermer");
		CloseFile_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_MASK));
		CloseFile_Menu.setName(BUTTON_NAME_CLOSE);
		CloseFile_Menu.addActionListener(listeners);
		//Delete MenuItem//
		JMenuItem DeleteFile_Menu = new JMenuItem("Supprimer Page", new ImageIcon(BUTTON_IMAGE_FILE_DELETE));
		DeleteFile_Menu.setName(BUTTON_NAME_DELETE);
		DeleteFile_Menu.addActionListener(listeners);
		// Quit MenuItem //
		JMenuItem Quit_Menu = new JMenuItem("Quitter", new ImageIcon(BUTTON_IMAGE_FILE_QUIT));
		Quit_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,KeyEvent.CTRL_MASK));
		Quit_Menu.setName(BUTTON_NAME_QUIT);
		Quit_Menu.addActionListener(listeners);

		Fichier_Menu.add(NewProject_Menu);
		Fichier_Menu.add(New_Menu);
		Fichier_Menu.add(Open_Menu);
		Fichier_Menu.add(Save_Menu);
		Fichier_Menu.add(SaveAs_Menu);
		Fichier_Menu.addSeparator();
		Fichier_Menu.add(Play_Menu);
		Fichier_Menu.add(PlayAs_Menu);
		Fichier_Menu.addSeparator();
		Fichier_Menu.add(Rename_Menu);
		Fichier_Menu.add(CloseFile_Menu);
		Fichier_Menu.add(DeleteFile_Menu);
		Fichier_Menu.addSeparator();
		Fichier_Menu.add(Quit_Menu);

		// Edition Menu //
		Edition_Menu = new JMenu("Edition");
		Principal_Menu.add(Edition_Menu);
		Edition_Menu.addMouseListener( new MouseAdapter() { 
			public void mousePressed(MouseEvent e)
			{ 
				updateMenuOptions(); 
			} 
		});

		// Undo MenuItem //
		JMenuItem Undo_Menu = new JMenuItem("Annuler", new ImageIcon(BUTTON_IMAGE_FILE_UNDO));
		Undo_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,KeyEvent.CTRL_MASK));
		Undo_Menu.setName(BUTTON_NAME_UNDO);
		Undo_Menu.addActionListener(listeners);
		// Redo MenuItem //
		JMenuItem Redo_Menu = new JMenuItem("Rétablir", new ImageIcon(BUTTON_IMAGE_FILE_REDO));
		Redo_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_MASK));
		Redo_Menu.setName(BUTTON_NAME_REDO);
		Redo_Menu.addActionListener(listeners);
		// Cut MenuItem //
		Cut_Menu = new JMenuItem("Couper", new ImageIcon(BUTTON_IMAGE_FILE_CUT));
		Cut_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));
		Cut_Menu.setName(BUTTON_NAME_CUT);
		Cut_Menu.addActionListener(listeners);
		// Copy MenuItem //
		Copy_Menu = new JMenuItem("Copier", new ImageIcon(BUTTON_IMAGE_FILE_COPY));
		Copy_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_MASK));
		Copy_Menu.setName(BUTTON_NAME_COPY);
		Copy_Menu.addActionListener(listeners);
		// Paste MenuItem
		JMenuItem Paste_Menu = new JMenuItem("Coller", new ImageIcon(BUTTON_IMAGE_FILE_PASTE));
		Paste_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
		Paste_Menu.setName(BUTTON_NAME_PASTE);
		Paste_Menu.addActionListener(listeners);
		// Find MenuItem //
		JMenuItem Find_Menu = new JMenuItem("Rechercher", new ImageIcon(BUTTON_IMAGE_FILE_FIND));
		Find_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,KeyEvent.CTRL_MASK));
		Find_Menu.setName(BUTTON_NAME_FIND);
		Find_Menu.addActionListener(listeners);
		// SelectAll MenuItem //
		JMenuItem SelectAll_Menu = new JMenuItem("Selectionner Tout",new ImageIcon(BUTTON_IMAGE_FILE_SELECT_ALL));
		SelectAll_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
		
		 SelectAll_Menu.addActionListener(new ActionListener()
		 {
			 @Override
			 public void actionPerformed(ActionEvent e)
		 	{
				 ((OngletPage)getOngletSelectedIndex()).getJTextPane().selectAll();
			}
		 });
		 //Dictionnaire MenuItem//
		 JMenu Dictionary_Menu = new JMenu("Dictionnaire");
		 Dictionary_Menu.setIcon(new ImageIcon(BUTTON_IMAGE_FILE_DICTIONARY));
		 
		 ButtonGroup bg = new ButtonGroup();
		 JCheckBoxMenuItem francais = new JCheckBoxMenuItem("Français");
		 JCheckBoxMenuItem anglais = new JCheckBoxMenuItem("Anglais");
		 francais.setName(BUTTON_NAME_FRENCH_DICTIONARY);
		 anglais.setName(BUTTON_NAME_ENGLISH_DICTIONARY);
		 francais.addActionListener(listeners);
		 anglais.addActionListener(listeners);
		 
		 francais.setSelected(true);
		 bg.add(francais);
		 bg.add(anglais);
		 
		 Dictionary_Menu.add(francais);
		 Dictionary_Menu.add(anglais);
		 
		 
		 
		Edition_Menu.add(Undo_Menu);
		Edition_Menu.add(Redo_Menu);
		Edition_Menu.addSeparator();
		Edition_Menu.add(Cut_Menu);
		Edition_Menu.add(Copy_Menu);
		Edition_Menu.add(Paste_Menu);
		Edition_Menu.addSeparator();
		Edition_Menu.add(Find_Menu);
		Edition_Menu.add(SelectAll_Menu);
		Edition_Menu.addSeparator();
		Edition_Menu.add(Dictionary_Menu);

		// Menu Insertion //
		Insertion_Menu = new JMenu("Insertion");
		Principal_Menu.add(Insertion_Menu);

		// InsertTable MenuItem
		JMenuItem InsertTable_Menu = new JMenu("Insérer un tableau...");
		InsertTable_Menu.setIcon(new ImageIcon(BUTTON_IMAGE_FILE_TABLE));
		InsertTable_Menu.setName(BUTTON_NAME_TABLE);
		//InsertTable_Menu.addActionListener(listeners);

		choixTableau = new ChoixTableau();

		InsertTable_Menu.add(choixTableau);

		// InsertTitle MenuItem
		JMenuItem InsertTitle_Menu = new JMenuItem("Insérer titre", new ImageIcon(BUTTON_IMAGE_FILE_TITLE));
		InsertTitle_Menu.setName(BUTTON_NAME_TITLE);
		InsertTitle_Menu.addActionListener(listeners);

		// InsertTitle MenuItem
		JMenuItem InsertSubTitle_Menu = new JMenuItem("Insérer sous-titre", new ImageIcon(BUTTON_IMAGE_FILE_SUBTITLE));
		InsertSubTitle_Menu.setName(BUTTON_NAME_SUBTITLE);
		InsertSubTitle_Menu.addActionListener(listeners);

		// InsertImage MenuItem
		JMenuItem InsertImage_Menu = new JMenuItem("Insérer image", new ImageIcon(BUTTON_IMAGE_FILE_IMAGE));
		InsertImage_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_MASK));
		InsertImage_Menu.setName(BUTTON_NAME_IMG);
		InsertImage_Menu.addActionListener(listeners);

		// InsertVideo MenuItem
		JMenuItem InsertVideo_Menu = new JMenuItem("Insérer video", new ImageIcon(BUTTON_IMAGE_FILE_VIDEO));
		InsertVideo_Menu.setName(BUTTON_NAME_VIDEO);
		InsertVideo_Menu.addActionListener(listeners);

		// InsertHyperLink MenuItem
		JMenuItem InsertLink_Menu = new JMenuItem("Insérer lien", new ImageIcon(BUTTON_IMAGE_FILE_LINK));
		InsertLink_Menu.setName(BUTTON_NAME_LINK);
		InsertLink_Menu.addActionListener(listeners);

		// InsertInfo MenuItem
		JMenuItem InsertBubbleNews_Menu = new JMenuItem("Insérer info-bulle", new ImageIcon(BUTTON_IMAGE_FILE_BUBBLE_NEWS));
		InsertBubbleNews_Menu.setName(BUTTON_NAME_BUBBLE_NEWS);
		InsertBubbleNews_Menu.addActionListener(listeners);

		// DateTime MenuItem //
		JMenuItem DateTime_Menu = new JMenuItem("Date/Heure", new ImageIcon(BUTTON_IMAGE_FILE_TIME));
		DateTime_Menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,KeyEvent.CTRL_MASK));
		DateTime_Menu.setName(BUTTON_NAME_DATE);
		DateTime_Menu.addActionListener(listeners);

		Insertion_Menu.add(InsertTable_Menu);
		Insertion_Menu.add(InsertTitle_Menu);
		Insertion_Menu.add(InsertSubTitle_Menu);
		Insertion_Menu.add(InsertImage_Menu);
		Insertion_Menu.add(InsertVideo_Menu);
		Insertion_Menu.add(InsertLink_Menu);
		Insertion_Menu.add(InsertBubbleNews_Menu);
		Insertion_Menu.add(DateTime_Menu);

		// Propos Menu //
		JMenu mnuPropos = new JMenu("A Propos");
		Principal_Menu.add(mnuPropos);
		// Credits MenuItem //
		JMenuItem Credits_Menu = new JMenuItem("Credits", new ImageIcon(BUTTON_IMAGE_FILE_CREDIT));
		mnuPropos.add(Credits_Menu);
		Credits_Menu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
								null,
								"Ce Projet est développé par le Groupe 4 :\n GR AE LJB RD AB FA \n\n IUT Le Havre, département Informatique, 2013-2014\n",
								"Credits", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		// About MenuItem //
		JMenuItem About_Menu = new JMenuItem("A Propos", new ImageIcon(BUTTON_IMAGE_FILE_ABOUT));
		mnuPropos.add(About_Menu);
		About_Menu.setName(BUTTON_NAME_ABOUT);
		About_Menu.addActionListener(listeners);

		// Edition Popup_Menu //
		Edition_Popup_Menu = new JPopupMenu();

		Popup_Menu_Cut = new JMenuItem("Couper");
		Popup_Menu_Cut.setMnemonic('C');
		Popup_Menu_Cut.setName(BUTTON_NAME_CUT);
		Popup_Menu_Cut.addActionListener(listeners);
		Edition_Popup_Menu.add(Popup_Menu_Cut);

		Popup_Menu_Copy = new JMenuItem("Copier");
		Popup_Menu_Copy.setMnemonic('p');
		Popup_Menu_Copy.setName(BUTTON_NAME_COPY);
		Popup_Menu_Copy.addActionListener(listeners);
		Edition_Popup_Menu.add(Popup_Menu_Copy);

		Popup_Menu_Paste = new JMenuItem("Coller");
		Popup_Menu_Paste.setMnemonic('o');
		Popup_Menu_Paste.setName(BUTTON_NAME_PASTE);
		Popup_Menu_Paste.addActionListener(listeners);
		Edition_Popup_Menu.add(Popup_Menu_Paste);
		Edition_Popup_Menu.addSeparator();

		Popup_Menu_SelectAll = new JMenuItem("Selectionner tout");
		Popup_Menu_SelectAll.setMnemonic('t');
		Popup_Menu_SelectAll.addActionListener(new ActionListener()
		{@Override
			public void actionPerformed(ActionEvent e)
			{
				((OngletPage)getOngletSelectedIndex()).getJTextPane().selectAll();
			}
		});
		Edition_Popup_Menu.add(Popup_Menu_SelectAll);
		return Principal_Menu;
	}
	
	public JToolBar createElementToolBar()
	{
		JToolBar panElements = new JToolBar();
		
		btnTitle = createButton(BUTTON_IMAGE_FILE_TITLE, BUTTON_NAME_TITLE);
		panElements.add(btnTitle);
		
		btnSubtitle = createButton(BUTTON_IMAGE_FILE_SUBTITLE, BUTTON_NAME_SUBTITLE);
		panElements.add(btnSubtitle);
		
		panElements.addSeparator();
		
		btnTable = createButton(BUTTON_IMAGE_FILE_TABLE, BUTTON_NAME_TABLE);
		panElements.add(btnTable);
		
		panElements.addSeparator();
		
		btnImage = createButton(BUTTON_IMAGE_FILE_IMAGE, BUTTON_NAME_IMAGE);
		panElements.add(btnImage);
		
		btnVideo = createButton(BUTTON_IMAGE_FILE_VIDEO, BUTTON_NAME_VIDEO);
		panElements.add(btnVideo);
		
		panElements.addSeparator();
		
		btnLien = createButton(BUTTON_IMAGE_FILE_LINK, BUTTON_NAME_LINK);
		panElements.add(btnLien);
		
		btnInfoBulle = createButton(BUTTON_IMAGE_FILE_BUBBLE_NEWS, BUTTON_NAME_BUBBLE_NEWS);
		panElements.add(btnInfoBulle);
		
		return panElements;
	}

	public JToolBar createDisplayToolBar() {

		JToolBar panButtons = new JToolBar();

		btnGras = createButton(BUTTON_IMAGE_FILE_BOLD, BUTTON_NAME_BOLD);
		panButtons.add(btnGras);

		btnItalic = createButton(BUTTON_IMAGE_FILE_ITALIC, BUTTON_NAME_ITALIC);
		panButtons.add(btnItalic);

		btnUnderlined = createButton(BUTTON_IMAGE_FILE_UNDERLINE, BUTTON_NAME_UNDERLINE);
		panButtons.add(btnUnderlined);

		btnBarre = createButton(BUTTON_IMAGE_FILE_STRIKETHROUGH, BUTTON_NAME_STRIKETHROUGH);
		panButtons.add(btnBarre);

		panButtons.addSeparator();

		btnLeft = createButton(BUTTON_IMAGE_FILE_LEFT_ALIGN, BUTTON_NAME_LEFT_ALIGN);
		panButtons.add(btnLeft);

		btnCenter = createButton(BUTTON_IMAGE_FILE_CENTER_ALIGN, BUTTON_NAME_CENTER_ALIGN);
		panButtons.add(btnCenter);

		btnRight = createButton(BUTTON_IMAGE_FILE_RIGHT_ALIGN, BUTTON_NAME_RIGHT_ALIGN);
		panButtons.add(btnRight);

		panButtons.addSeparator();

		// Font Family
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] sPolices = ge.getAvailableFontFamilyNames();
		comboPolice = new JComboBox<String>();
		comboPolice.setName(BUTTON_NAME_POLICE);
		for (int i = 0; i < sPolices.length; i++)
			comboPolice.addItem(sPolices[i]);

		comboPolice.setFocusable(false);
		comboPolice.setPreferredSize(new Dimension(180, 24));
		panButtons.add(comboPolice);
		comboPolice.addItemListener(listeners);

		panButtons.addSeparator();
		// Font Size
		comboSize = new JComboBox<String>();
		comboSize.setName(BUTTON_NAME_SIZE);
		for (int i = 0; i < 100; i++)
			comboSize.addItem(new Integer((i * 2) + 6).toString());
		comboSize.setSelectedIndex(3);
		comboSize.setFocusable(false);
		comboSize.setPreferredSize(new Dimension(64, 24));
		panButtons.add(comboSize);
		comboSize.addItemListener(listeners);

		panButtons.addSeparator();

		btnPoliceColor = createButton(BUTTON_IMAGE_FILE_COLOR_POLICE, BUTTON_NAME_COLOR_POLICE);
		panButtons.add(btnPoliceColor);

		panButtons.addSeparator();

		return panButtons;
	}

	public JButton createButton(String sFileName, String sName)
	{
		JButton mButton = new JButton(new ImageIcon(sFileName));
		mButton.setMaximumSize(new Dimension(24, 24));
		mButton.setMargin(new Insets(15, 15, 15, 15));
		mButton.setName(sName);
		mButton.setFocusable(false);
		mButton.setPreferredSize(new Dimension(24, 24));
		mButton.setMinimumSize(new Dimension(24, 24));
		mButton.setToolTipText(sName);
		mButton.addActionListener(listeners);
		return mButton;
	}

	public Onglet getOngletSelectedIndex()
	{
		return centre.getOngletSelectedIndex();
	}
	
	public void updatePopupOptions()
	{
	      if (((OngletPage)getOngletSelectedIndex()).getJTextPane().getSelectedText() == null)
	      {
	    	  Popup_Menu_Cut.setEnabled(false);
	    	  Popup_Menu_Copy.setEnabled(false);
	      }
	      else
	      {
	    	  Popup_Menu_Cut.setEnabled(true);
	    	  Popup_Menu_Copy.setEnabled(true);
	      }
	}
	
	public void updateMenuOptions()
	{
        if (((OngletPage)getOngletSelectedIndex()).getJTextPane().getSelectedText() == null) {
        	Cut_Menu.setEnabled(false);
        	Copy_Menu.setEnabled(false);
         
        }
        else
        {
        	Cut_Menu.setEnabled(true);
        	Copy_Menu.setEnabled(true);
        }
	}

	public void fermerOngletActif()
	{
		centre.fermerOngletActif();
	}

	public Arborescence getPArbo() {
		return this.pArbo;
	}

	public void append(String s) {
		try
		{
			Document doc = ((OngletPage)getOngletSelectedIndex()).getJTextPane().getDocument();
			doc.insertString(doc.getLength(), s + "\n",
					((OngletPage)getOngletSelectedIndex()).getJTextPane().getStyle("default"));

		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public void setTitre(String title)
	{
		setTitle(NOM_APPLICATION + " - " + title);
	}

	public Centre getCentre() 
	{
		return centre;
	}

	public void setCentre(Centre centre)
	{
		this.centre = centre;
	}

	public Arborescence getpArbo()
	{
		return pArbo;
	}

	public void setpArbo(Arborescence pArbo)
	{
		this.pArbo = pArbo;
	}

	public Projet getProjet() 
	{
		return projet;
	}

	public void setProjet(Projet projet)
	{
		this.projet = projet;
		majProjet();
	}

	public void majProjet()
	{
		
		remove(ouest);
		this.ouest = new JPanel(new GridLayout(2, 1));
		pArbo = new Arborescence(this, this.projet.getRacine());
		pArbo.setBackground(Color.WHITE);

		ouest.add(pArbo);
		add(ouest);

		centre.supprimerTousOnglets();

		this.splitOuest.setLeftComponent(ouest);
		this.splitOuest.setDividerLocation(150);
		revalidate();
	}

	public void majOuest()
	{
		remove(this.ouest);
		this.ouest = new JPanel(new GridLayout(2, 1));
		pArbo = new Arborescence(this, this.projet.getRacine());
		pArbo.setBackground(Color.WHITE);

		this.ouest.add(pArbo);
		add(this.ouest);

		this.splitOuest.setLeftComponent(ouest);
		this.splitOuest.setDividerLocation(150);
		revalidate();
	}

	public void majCentre()
	{
		remove(this.centre);
		this.centre = new Centre(this);
		add(this.centre);

		this.splitOuest.setRightComponent(centre);
		this.splitOuest.setDividerLocation(150);
		revalidate();
	}
	
	public Listeners getListeners()
	{
		return listeners;
	}
	
	public String getResultAreaText()
	{
		return this.resultArea.getText();
	}
	
	public void setResultAreaString(String texte)
	{
		this.resultArea.setText(texte);
	}
	
	public void updateResultArea()
	{	
		this.resultArea.setText( " Nom du projet : " + projet.getNom() + 
				"    Auteur : " + projet.getAuteur() + "    Mode d'édition : " + 
				(projet.isNiveauExpert()?"Expert":"Simple"));
	}	
}