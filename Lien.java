import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTML;

public class Lien extends JDialog implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JLabel labLien, labNomLien;
	private JTextField tfLien, tfNomLien;
	private JButton valid;
	private VueProjet vp;

	public Lien(VueProjet vp) {
		super(vp, "Nouveau Lien", true);
		setLayout(new GridLayout(5,1));
		setSize(400, 160);
		setLocationRelativeTo(null);
		setResizable(false);
		
		this.vp = vp;
		
		JPanel pBas = new JPanel(new BorderLayout());
		
		this.labLien = new JLabel("Entrez le lien URL");
		add(this.labLien, BorderLayout.NORTH);
		this.tfLien = new JTextField();
		add(this.tfLien);
		this.labNomLien = new JLabel("Entrez le titre du lien sous lequel il apparaîtra");
		add(this.labNomLien);
		this.tfNomLien = new JTextField();
		add(this.tfNomLien);
		this.valid = new JButton("Valider");
		this.valid.addActionListener(this);
		pBas.add(this.valid, BorderLayout.EAST);
		
		add(pBas, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.valid) {
			try {
				URL url = new URL(this.tfLien.getText());
				((OngletPage)vp.getOngletSelectedIndex()).getJTextPane().addHyperlinkListener(new HyperlinkListener() {
                    public void hyperlinkUpdate(HyperlinkEvent e) {
                        if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                            Desktop desktop = Desktop.getDesktop();
                            try {
                                desktop.browse(e.getURL().toURI());
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
				((OngletPage)vp.getOngletSelectedIndex()).getJTextPane().addKeyListener(this);			
				
		        try {
					((OngletPage)vp.getOngletSelectedIndex()).ajouterElement("<a style=\"text-decoration: underline;\" href="+this.tfLien.getText()+">"+this.tfNomLien.getText()+"</a>&nbsp;", HTML.Tag.A);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        this.dispose();
				
				
			} catch (MalformedURLException e1) {
				
				JFrame f = new JFrame();
				f.setTitle("Erreur URL");
				f.setResizable(false);
				f.setSize(200, 70);
				JLabel labErr = new JLabel("\tAdresse URL invalide");
				f.add(labErr);
				f.setVisible(true);
				f.setLocationRelativeTo(null);
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if(e.getModifiers() == KeyEvent.CTRL_MASK) {
			((OngletPage)vp.getOngletSelectedIndex()).getJTextPane().setEditable(false);
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getModifiers() != KeyEvent.CTRL_MASK) {
			((OngletPage)vp.getOngletSelectedIndex()).getJTextPane().setEditable(true);
		}
	}
	public void keyTyped(KeyEvent arg0) {}
}
