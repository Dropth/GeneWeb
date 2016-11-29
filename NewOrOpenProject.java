import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class NewOrOpenProject extends JDialog implements ActionListener
{
	private JFrame parent;
	private JButton nouveau, ouvrir;
	private Icon nouveauIcon, ouvrirIcon;

	public NewOrOpenProject(final JFrame parent)
	{
		super(parent, "Bienvenue", true);
		this.parent = parent;
		setSize(400,200);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(1,2));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				parent.dispose();
			}
		});
		
		initComponent();
		
	}
	
	public void initComponent()
	{
		nouveauIcon = new ImageIcon("icons/new-project-template-icon.png");
		ouvrirIcon = new ImageIcon("icons/project-open-icon.png");
		JPanel gauche = new JPanel(new BorderLayout());
		JPanel droite = new JPanel(new BorderLayout());
		
		nouveau = new JButton(nouveauIcon);
		nouveau.addActionListener(this);
		ouvrir = new JButton(ouvrirIcon);
		ouvrir.addActionListener(this);
		
		gauche.add(nouveau);
		droite.add(ouvrir);
		gauche.add(new JLabel("Nouveau projet", SwingConstants.CENTER), BorderLayout.SOUTH);
		droite.add(new JLabel("Ouvrir un projet", SwingConstants.CENTER), BorderLayout.SOUTH);
		
		add(gauche);
		add(droite); 
		
		setVisible(true);
	}
	
	public JFrame getParent()
	{
		return parent;
	}

	public void setParent(JFrame parent)
	{
		this.parent = parent;
	}

	public JButton getNouveau()
	{
		return nouveau;
	}

	public void setNouveau(JButton nouveau)
	{
		this.nouveau = nouveau;
	}

	public JButton getOuvrir()
	{
		return ouvrir;
	}

	public void setOuvrir(JButton ouvrir)
	{
		this.ouvrir = ouvrir;
	}

	public Icon getNouveauIcon()
	{
		return nouveauIcon;
	}

	public void setNouveauIcon(Icon nouveauIcon)
	{
		this.nouveauIcon = nouveauIcon;
	}

	public Icon getOuvrirIcon()
	{
		return ouvrirIcon;
	}

	public void setOuvrirIcon(Icon ouvrirIcon)
	{
		this.ouvrirIcon = ouvrirIcon;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(nouveau))
		{
			((VueProjet)parent).getListeners().newProject();
		}
		else if(e.getSource().equals(ouvrir))
		{
			if(!((VueProjet)parent).getListeners().open())
				return;
		}
		dispose();
	}

}
