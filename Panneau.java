import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panneau extends JPanel implements MouseListener
{
	private JLabel file, titre, close1, close2;
	private String title;
	private Centre centre;
	
	public Panneau(String title, ImageIcon img, Centre centre)
	{
		setOpaque(false);
		
		this.title = title;
		this.centre = centre;
		
		file = new JLabel(img);
		titre = new JLabel(title);
		close1 = new JLabel(new ImageIcon("icons/symbol-delete-icon.png"));
		close1.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		close1.addMouseListener(this);
		close2 = new JLabel(new ImageIcon("icons/symbol-delete-icon2.png"));
		close2.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		close2.addMouseListener(this);
        
		add(file);
        add(titre);
        add(close1);
	
	}
	
	public void setTitre(String title)
	{
		titre.setText(title);
	}
	
	public String getTitre()
	{
		return this.titre.getText();
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		centre.supprimerOngletClique(this);
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		remove(close1);
		add(close2);
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		remove(close2);
		add(close1);		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
}
