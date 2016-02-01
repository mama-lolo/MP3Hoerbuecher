package dataSelectionPanel;

//Quelle docs.oracle.com, changes through Malte
import java.util.ArrayList;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * contains ArrayList selectedIndicies with the selected indicies of the corresponding list
 */
class SharedListSelectionHandler implements ListSelectionListener {
	public ArrayList<Integer> selectedIndicies = new ArrayList();
	StringBuffer output = new StringBuffer("");

	private String newline = System.getProperty("line.separator");

	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();

		int firstIndex = e.getFirstIndex();
		int lastIndex = e.getLastIndex();
		boolean isAdjusting = e.getValueIsAdjusting();
		if (isAdjusting) {
			output.append("Event for indexes " + firstIndex + " - " + lastIndex + "; selected indexes:");

			if (lsm.isSelectionEmpty()) {
				output.append(" <none>");
			} else {
				// Find out which indexes are selected.
				int minIndex = lsm.getMinSelectionIndex();
				int maxIndex = lsm.getMaxSelectionIndex();
				for (int i = minIndex; i <= maxIndex; i++) {
					if (lsm.isSelectedIndex(i)) {
						output.append(" " + i);
					}
				}
			}
			output.append(newline);
			System.out.println(output);
			output = new StringBuffer();
		}
	}
}
