package dataSelectionPanel;



import java.awt.BorderLayout;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * This is the Panel in which the user chooses the data to go through
 * @author Malte
 *
 */
public class DataSelectionPanel extends JPanel {
	public DefaultListModel<File> model = new DefaultListModel<File>();
	public JList<File> list = new JList<File>(model); // Liste mit Strings
	public JScrollPane scroller = new JScrollPane(list);
	SharedListSelectionHandler selectionHandler = new SharedListSelectionHandler();
	
	//attention this List is created empty!!!
	public DataSelectionPanel(String rootPath){
		super();

		//implementing SelectionModel
		ListSelectionModel selModel = list.getSelectionModel();
		selModel.addListSelectionListener(selectionHandler);
		
		//Sets CellRenderer for adapted display
		list.setCellRenderer(new CellRenderer(rootPath));
		
		this.add(scroller, BorderLayout.CENTER);
		this.revalidate();
		this.setVisible(true);
	}
	public void updatePath(String path){
		System.out.println("DataSelectionPanel updated with path: "+path);
		model.removeAllElements();
		File temp = new File(path);
		for(File f :temp.listFiles()){
			model.addElement(f);
		}
		this.revalidate();
	}

}
