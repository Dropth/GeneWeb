import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SousTitre extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JLabel labNomSousTitre;
	
	private JTextField tfSousTitre;
	
	private JButton valid;
	
	private JEditorPane editPane;

	public SousTitre(JEditorPane ep) {
		this.setTitle("Nouveau sous-titre");
		this.setLayout(new GridLayout(3,1));
		this.setSize(400, 100);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.editPane = ep;
		
		JPanel pBas = new JPanel(new BorderLayout());
		
		this.labNomSousTitre = new JLabel("Entrez le nom du sous-titre");
		add(this.labNomSousTitre, BorderLayout.NORTH);
		this.tfSousTitre = new JTextField();
		add(this.tfSousTitre);
		this.valid = new JButton("Valider");
		this.valid.addActionListener(this);
		pBas.add(this.valid, BorderLayout.EAST);
		
		add(pBas, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public String getTitre() {
		return this.tfSousTitre.getText();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.valid) {
			this.editPane.setText(this.tfSousTitre.getText().concat("\n\n").concat(this.editPane.getText()));
			this.dispose();
		}
	}

}
