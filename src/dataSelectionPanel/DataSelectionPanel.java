package dataSelectionPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import main.ComponentStorage;

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
	public JButton sortButton = new JButton("Sort");

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
		//adds the abillity to open subfolders
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt){
				int index = 0;
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					// Double-click detected
					index = list.locationToIndex(evt.getPoint());
					// Unterordner öffnen wenn File ein Ordner ist und nicht bereits geöffnet ist
					if (model.elementAt(index).isDirectory()
							&& !model
									.elementAt((index + 1)%model.getSize())//ArrayIndexOutOfBounds check
									.getAbsolutePath()
									.contains(
											model.elementAt(index)
													.getAbsolutePath() + "\\")) {
						File temp = new File(model.elementAt(index)
								.getAbsolutePath() + "\\");
						File[] newfiles = temp.listFiles();
						for (File f : newfiles) {
							if(f.isDirectory()||f.getAbsolutePath().contains(".mp3")||f.getAbsolutePath().contains(".wav")){
							model.add(index + 1, f);
							}
						}//Unterordner Schließen, wenn er bereits geöffnet war.
					} else if (model.elementAt(index).isDirectory()) {

						while (model
								.elementAt((index + 1)%model.getSize())//ArrayIndexOutOfBounds check
								.getAbsolutePath()
								.contains(
										model.elementAt(index)
												.getAbsolutePath() + "\\")) {

							model.remove(index+1);
						}

					}
			}
		}});
		// visual presets for the scrollpane
		scroller.setPreferredSize(new Dimension(400, 600));
		
		//Button setup
		sortButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ComponentStorage.MAIN_FRAME.changeView(ComponentStorage.SORTING_PANEL);
				ComponentStorage.SORTING_PANEL.updateData();
			}
		});
		this.add(scroller, BorderLayout.CENTER);
		this.add(sortButton);
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
			if(f.isDirectory()||f.getAbsolutePath().contains(".mp3")||f.getAbsolutePath().contains(".wav")){
			model.addElement(f);
			}
		}
		this.revalidate();
	}

}
