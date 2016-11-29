import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Essai extends JFrame{
  private JPanel container = new JPanel();

  private ChoixTableau choix;

  private JMenuBar menuBar = new JMenuBar();

  private JMenu animation = new JMenu("Animation"),
    forme = new JMenu("Forme"),
    typeForme = new JMenu("Type de forme"),
    aPropos = new JMenu("À propos");

  private JMenuItem lancer = new JMenuItem("Lancer l'animation"),
    arreter = new JMenuItem("Arrêter l'animation"),
    quitter = new JMenuItem("Quitter"),
    aProposItem = new JMenuItem("?");

  private JMenuItem carre = new JMenuItem("Carré"),
    rond = new JMenuItem("Rond"),
    triangle = new JMenuItem("Triangle"),
    etoile = new JMenuItem("Etoile");


  public Essai(){
    this.setTitle("Animation");
    this.setSize(300, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
    
  //Menu animation
    animation.add(lancer);
    animation.add(arreter);
    animation.add(quitter);


    forme.add(new ChoixTableau());
    
    forme.add(new JMenuItem("Insérer un tableau..."));

    //Menu À propos
    aPropos.add(aProposItem);

    //Ajout des menus dans la barre de menus
    menuBar.add(animation);
    menuBar.add(forme);
    menuBar.add(aPropos);

    //Ajout de la barre de menus sur la fenêtre
    this.setJMenuBar(menuBar);

    this.setContentPane(container);
    this.setVisible(true);            
  }


  
  public static void main(String[] args)
  {
	  new Essai();
  }
  
}