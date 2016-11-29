import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImportSave extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private String savePath;
	private String directory;
	
	public ImportSave() {

		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("sauvegarde", "site"));
		int returnVal = chooser.showOpenDialog(null);
	
		if(returnVal == JFileChooser.APPROVE_OPTION) {
            this.savePath = chooser.getSelectedFile().getAbsolutePath();
            this.directory = chooser.getCurrentDirectory().getAbsolutePath();
		}	
	}
	
	public String getSavePath (){
		return savePath;
	}
	
	public String getDirectory (){
		return this.directory;
	}
}