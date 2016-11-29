import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class AttributsTableau extends JDialog implements ActionListener
{
	private int nbLignes;
	private int nbColonnes;
	private JLabel[] titreLabel;
	private JTextField[] titre;
	private JButton valider, annuler;
	
	public AttributsTableau(JFrame parent, String title, boolean modal, int nbLignes, int nbColonnes)
	{
		super(parent, title, modal);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(400, 200);
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		
		titreLabel = new JLabel[nbColonnes];
		titre = new JTextField[nbColonnes];
		
		
		this.initComponent();
	}
	
	public void initComponent()
	{
		
		JPanel centre = new JPanel(new GridLayout(nbColonnes, 2));
		
		char defaut = 'A';
		
		for(int i = 0; i < nbColonnes; i++)
		{

			titreLabel[i] = new JLabel("Champ " + (i+1) + " :");
			titreLabel[i].setFont(new Font("default",Font.BOLD,12));
			titreLabel[i].setHorizontalAlignment(JLabel.CENTER);
			titre[i] = new JTextField(String.valueOf(defaut));
			titre[i].setPreferredSize(new Dimension(150, 25));
			titre[i].addCaretListener(new CaretListener(){

				@Override
				public void caretUpdate(CaretEvent arg0)
				{
					boolean verification = true;
					
					for(int i = 0; i < titre.length; i++)
					{
						if(titre[i].getText().isEmpty())
						{
							verification = false;
						}
					}
					
					if(verification)
						valider.setEnabled(false);
					else
						valider.setEnabled(true);
						
				}
				
			});
			
			titre[i].addKeyListener(new KeyListener(){

				@Override
				public void keyPressed(KeyEvent arg0)
				{
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyReleased(KeyEvent arg0)
				{
					if(!valider.isEnabled() && arg0.getKeyCode() == KeyEvent.VK_ENTER)
					{
						nouveauTableau();
					}
				}

				@Override
				public void keyTyped(KeyEvent arg0)
				{
					// TODO Auto-generated method stub
					
				}
				
			});
			
			centre.add(titreLabel[i]);
			centre.add(titre[i]);
			defaut++;
		}
		

		
		JPanel control = new JPanel();
		
		valider = new JButton("Valider");
		valider.addActionListener(this);

		
		annuler = new JButton("Annuler");
		annuler.addActionListener(this);
		
		control.add(annuler);
		control.add(valider);
		
		JScrollPane sclScroll = new JScrollPane(centre);
		
		add(sclScroll, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);
		
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(annuler))
		{
			this.dispose();
		}
		else if(e.getSource().equals(valider))
		{
			nouveauTableau();
		}
		
	}
	
	public void nouveauTableau()
	{
		
	}
}
