package dataSelectionPanel;

//Main content downloaded from StackOverflow
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;

/**
 * adapting display
 * 
 * @author Malte
 *
 */
public class CellRenderer extends DefaultListCellRenderer {

	private String rootPath;

	public CellRenderer(String root) {
		rootPath = root;
	}

	/**
	 * sets displayed string
	 */
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		// DefaultListCellRenderer always returns a JLabel
		// super setups up all the defaults

		String localPath = value.toString().replace(rootPath, "");
		String[] paths = localPath.split("\\\\");
		// Adding the blanks for the backslashes(not equally as long but in relation to it)
		String displayPath = new String(new char[paths.length*2]).replace("\0", " ");
		// adding the blanks for the rootpath (not equally as long but in relation to it)
		for (int i = 0; i < paths.length - 1; i++) {
			displayPath = displayPath + new String(new char[paths[i].length()*2]).replace("\0", " ");
		}
		// adding the actual name
		displayPath = displayPath + paths[paths.length-1];
		JLabel label = (JLabel) super.getListCellRendererComponent(list, displayPath, index,
				isSelected, cellHasFocus);
		// "value" is whatever object you put into the list, you can use it
		// however you want here
		return label;

	}

	/**
	 * update path to newPath
	 * 
	 * @param newPath
	 */
	public void updatePath(String newPath) {
		rootPath = newPath;
	}

	/**
	 * 
	 * @param model
	 *            Model in which the object is to be searched
	 * @param value
	 *            Entry to search
	 * @return position of the entry, returns -1 if not found
	 */
	private int getPosition(ListModel model, Object value) {
		// searching for entry
		for (int i = 0; i < model.getSize(); i++) {
			if (model.getElementAt(i) == value)
				return i;
		}
		// this should definitely not happen!!!
		return -1;

	}
}
