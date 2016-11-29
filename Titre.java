import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

public class Titre extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel labNomTitre;
	private JTextField tfTitre;
	private JButton valid;
	private JTextPane textPane;
	private Onglet onglet;

	public Titre(Onglet onglet) {
		
		this.setTitle("Nouveau titre");
		this.setLayout(new GridLayout(3,1));
		this.setSize(400, 100);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.onglet = onglet;
		this.textPane = onglet.getJTextPane();
		
		JPanel pBas = new JPanel(new BorderLayout());
		
		this.labNomTitre = new JLabel("Entrez le nom du titre");
		add(this.labNomTitre, BorderLayout.NORTH);
		this.tfTitre = new JTextField();
		add(this.tfTitre);
		this.valid = new JButton("Valider");
		this.valid.addActionListener(this);
		pBas.add(this.valid, BorderLayout.EAST);
		
		add(pBas, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.valid) {		
			try {
				onglet.getKit().insertHTML(onglet.getDoc(), this.textPane.getCaretPosition(), "<h1 style=\"text-align:center;\">"+this.tfTitre.getText()+"</h1>\n\n", 0, 0, null);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.dispose();
		}
	}
}
