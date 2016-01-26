package pathGetter;

import javax.swing.JFileChooser;

import main.ComponentStorage;
public class PathChooser{
	JFileChooser fc;

	public PathChooser() {
		// Create a file chooser
		fc = new JFileChooser();
		fc.getAcceptAllFileFilter();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fc.showOpenDialog(ComponentStorage.MAIN_FRAME);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String path = fc.getSelectedFile().toString();
				System.out.println("Selected path: "+path);
				ComponentStorage.MAIN_FRAME.changePath(path);
				ComponentStorage.MAIN_FRAME.changeView(ComponentStorage.DATA_SELECTION_PANEL);
				
				
			} else {
				System.out.println("Cancelled");
			}
		}
}
