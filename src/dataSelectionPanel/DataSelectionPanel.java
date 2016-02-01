package dataSelectionPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * This is the Panel in which the user chooses the data to go through
 * 
 * @author Malte
 *
 */
public class DataSelectionPanel extends JPanel {
	// Basic setup of list, model, pane and SelectionHandler
	public DefaultListModel<File> model = new DefaultListModel<File>();
	public JList<File> list = new JList<File>(model); // Liste mit Strings
	public JScrollPane scroller = new JScrollPane(list);
	public SharedListSelectionHandler selectionHandler = new SharedListSelectionHandler();

	// attention this List is created empty!!!
	public DataSelectionPanel(String rootPath) {
		super();

		// Visual presets for the panel
		this.setPreferredSize(new Dimension(500, 700));
		// implementing SelectionModel
		ListSelectionModel selModel = list.getSelectionModel();
		selModel.addListSelectionListener(selectionHandler);

		// Sets CellRenderer for adapted display
		list.setCellRenderer(new CellRenderer(rootPath));
		// visual presets for the scrollpane
		scroller.setPreferredSize(new Dimension(400, 700));
		this.add(scroller, BorderLayout.CENTER);
		// necessary Graphical updates
		this.revalidate();
		this.setVisible(true);
	}

	/**
	 * updates the root directory path
	 * 
	 * @param path
	 */
	public void updatePath(String path) {
		System.out.println("DataSelectionPanel updated with path: " + path);
		// update model content
		model.removeAllElements();
		// update renderer
		list.setCellRenderer(new CellRenderer(path));
		File temp = new File(path);
		for (File f : temp.listFiles()) {
			model.addElement(f);
		}
		this.revalidate();
	}

}
