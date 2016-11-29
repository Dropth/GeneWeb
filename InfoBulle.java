import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolTip;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTML;

public class InfoBulle extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel labInfo, labText;
	private JTextField tfInfo, tfText;
	private JButton valid;
	
	private VueProjet vp;

	public InfoBulle(VueProjet vp) {
		super(vp, "Nouvelle InfoBulle", true);
		this.setLayout(new GridLayout(5,1));
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.vp = vp;
		
		JPanel pBas = new JPanel(new BorderLayout());
		
		this.labText = new JLabel("Entrez votre texte");
		add(this.labText, BorderLayout.NORTH);
		this.tfText = new JTextField();
		add(this.tfText);
		
		this.labInfo = new JLabel("Entrez votre infobulle");
		add(this.labInfo, BorderLayout.NORTH);
		this.tfInfo = new JTextField();
		add(this.tfInfo);
		
		this.valid = new JButton("Valider");
		this.valid.addActionListener(this);
		pBas.add(this.valid, BorderLayout.EAST);
		
		add(pBas, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.valid) {
            try {
				((OngletPage)vp.getOngletSelectedIndex()).ajouterElement("<span title=\""+this.tfInfo.getText()+"\">" + this.tfText.getText() + "</span>", HTML.Tag.SPAN);
				JToolTip tooltip = new JToolTip();
				tooltip.setComponent(this.tfText);
				tooltip.setTipText(this.tfInfo.getText());
				
			}
            catch (BadLocationException e1) {}
            catch (IOException e1) {}
			this.dispose();
		}
	}
}
