import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import javax.swing.undo.UndoManager;

public class OngletImage extends Onglet // implements UndoableEditListener
{
	private static final long serialVersionUID = 1L;
	private ImageIcon image;

	public OngletImage(Fichier fichier) {
		super(fichier);
		setLayout(new BorderLayout());
		JPanel jp = new JPanel(new BorderLayout());
		jp.setBackground(Color.DARK_GRAY);

		image = new ImageIcon(((FichierImage) this.fichier).getChemin());
		JLabel label1 = new JLabel(this.fichier.getNomFichier() + " (" + image.getIconWidth() + "x" + image.getIconHeight() + ")", JLabel.CENTER);
		label1.setForeground(Color.WHITE);
		jp.add(label1, BorderLayout.NORTH);

		JLabel label2 = new JLabel(null, image, JLabel.CENTER);
		jp.add(label2, BorderLayout.SOUTH);

		JScrollPane sclScroll = new JScrollPane(jp);
		add(sclScroll);
	}

}
