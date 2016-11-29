import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextPane;

public class Video extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextPane tp;

	public Video() {
		setSize(500, 500);
		setLayout(new BorderLayout());
		tp = new JTextPane();
		tp.setContentType("text/html");
		tp.setText("<video controls src=\"1.mp4\">Video test</video>");
		
		add(tp);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new Video();
	}
}