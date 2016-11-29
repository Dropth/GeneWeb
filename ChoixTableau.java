import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChoixTableau extends JPanel implements MouseMotionListener
{

	private JLabel[] square;
	private Icon whiteSquare;
	private Icon blueSquare;

	public ChoixTableau()
	{
		super();
		
		GridLayout gl = new GridLayout(8,10);
		gl.setHgap(3);
		gl.setVgap(3);
		
		setLayout(gl);
		
		square = new JLabel[80];
		whiteSquare = new ImageIcon("icons/white-square.png");
		blueSquare = new ImageIcon("icons/square_blue.png");
		
		addMouseMotionListener(this);
		
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		for(int i = 0; i < 80; i++)
		{
			square[i] = new JLabel(whiteSquare);
			square[i].setName(Integer.toString(i));
			square[i].addMouseListener(new MouseAdapter(){
				
				public void mouseClicked(MouseEvent arg0)
				{
					if(arg0.getSource() instanceof JLabel)
					{
						int positionClick = Integer.parseInt(arg0.getComponent().getName());
						
						new AttributsTableau(null, "Paramètres tableau", true, (positionClick/10 + 1),(positionClick%10 + 1));
						
						System.out.println("Nombre de lignes : " + (positionClick/10 + 1));
						System.out.println("Nombre de colonnes : " + (positionClick%10 + 1));
					}
				}
				
			});
			square[i].addMouseMotionListener(this);
				
				
			add(square[i]);
			
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0)
	{	
		if(arg0.getSource() instanceof ChoixTableau)
		{
			if(arg0.getX() < 10 || arg0.getY() < 10 || arg0.getX() > getWidth() - 10 || arg0.getY() > getHeight() - 10)
			{
				for(int i = 0; i < square.length; i++)
				{
					square[i].setIcon(whiteSquare);
				}
			}
			
		}
		
		if(arg0.getSource() instanceof JLabel)
		{
			int positionMouse = Integer.parseInt(arg0.getComponent().getName());
			
			int positionXMouse = positionMouse%10;
			
			int positionYMouse = positionMouse/10;
			
			int positionXother, positionYother;
			
			for(int i = 0; i < square.length; i++)
			{
				positionXother = i%10;
				positionYother = i/10;
				
				if(positionXother <= positionXMouse && positionYother <= positionYMouse)
				{
					square[i].setIcon(blueSquare);
				}
				else
				{
					square[i].setIcon(whiteSquare);
				}
			}
		}
	}	
		
}
