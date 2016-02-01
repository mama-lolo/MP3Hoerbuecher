package pathGetter;

import javax.swing.JFileChooser;

import main.ComponentStorage;

public class PathChooser {
	JFileChooser fc;

	public PathChooser() {
		// Create a file chooser
		fc = new JFileChooser();
		// set filter (may change)
		//
		fc.getAcceptAllFileFilter();
		// set selection mode to directories
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// do the selection
		int returnVal = fc.showOpenDialog(ComponentStorage.MAIN_FRAME);
		// check if approved
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String path = fc.getSelectedFile().toString();
			System.out.println("Selected path: " + path);
			// update path
			ComponentStorage.MAIN_FRAME.changePath(path);
			// change view to selection pane
			ComponentStorage.MAIN_FRAME.changeView(ComponentStorage.DATA_SELECTION_PANEL);

		} else {
			System.out.println("Cancelled");
		}
	}
}
