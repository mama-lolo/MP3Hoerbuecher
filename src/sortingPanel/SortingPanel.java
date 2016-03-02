package sortingPanel;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import main.ComponentStorage;

public class SortingPanel extends JPanel {
	Vector<Vector> data = new Vector();
	public JTable dataTable = new JTable(data, new Vector());
	public JScrollPane scroller = new JScrollPane(dataTable);

	public SortingPanel() {
		super();
		this.add(scroller, BorderLayout.CENTER);
		revalidate();

	}

	// has to be fixed
	public void updateData() {
		ArrayList<File> mp3readingData = new ArrayList<File>();
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
		DefaultListModel<File> tempModel = ComponentStorage.DATA_SELECTION_PANEL.model;
		ArrayList<Integer> activatedIndicies = ComponentStorage.DATA_SELECTION_PANEL.selectedIndicies;
		System.out.println("Started adding Files to the sortable List.");
		// inserting the different MP3 Files
		for (Integer i : activatedIndicies) {
			System.out.println("Added File at pos: " + i);
			add(tempModel.get(i), mp3readingData);
		}

		for (File f : mp3readingData) {
			String path = f.getAbsolutePath();
			path = path.replace(ComponentStorage.MAIN_FRAME.path, "");
			System.out.println(ComponentStorage.MAIN_FRAME.path);
			path = path.replace(".mp3", "");
			path = path.replace("_", "~");
			path = path.replace("\\", "~");
			path = path.replace("-", "~");
			path = path.replace(";", "~");
			path = path.replace("/", "~");
			path = path.replace("#", "~");
			ArrayList<String> row = new ArrayList<String>();
			for (String s : path.split("~")) {
				if (s != null) {
					System.out.print("added :" + s + " || ");
					row.add(s);
				}
			}
			System.out.println();
			rows.add(row);
		}

		Vector<String> columns = new Vector();
		for (int i = 0; i < data.size(); i++) {
			columns.add("Spalte: " + i);
		}
		dataTable = new JTable(data, columns);
		revalidate();
		System.out.println(data);
	}

	private void add(File file, ArrayList<File> mp3Data) {
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				add(f, mp3Data);
			}
		} else if (file.getAbsolutePath().contains(".mp3")
				|| file.getAbsolutePath().contains(".wav")) {
			mp3Data.add(file);
		}

	}
}
