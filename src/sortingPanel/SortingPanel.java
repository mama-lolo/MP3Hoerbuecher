package sortingPanel;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import main.ComponentStorage;

public class SortingPanel extends JPanel {
	Vector<Vector<String>> data = new Vector<Vector<String>>();
	Vector<String> names = new Vector<String>();
	public JTable dataTable = new JTable(data,names);
	public JScrollPane scroller = new JScrollPane(dataTable);
	public JButton button = new JButton();

	public SortingPanel() {
		super();
		this.setLayout(new BorderLayout());
		scroller.setVisible(true);
		button.setVisible(true);
		this.add(button,BorderLayout.SOUTH);
		this.add(scroller, BorderLayout.CENTER);
		revalidate();

	}

	// has to be fixed
	public void updateData() {
		int longestFile = 0;
		ArrayList<File> mp3readingData = new ArrayList<File>();
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
		DefaultListModel<File> tempModel = ComponentStorage.DATA_SELECTION_PANEL.model;
		ArrayList<Integer> activatedIndicies = ComponentStorage.DATA_SELECTION_PANEL.selectedIndicies;
		System.out.println("Started adding Files to the sortable List.");
		// inserting the different MP3 Files as File
		for (Integer i : activatedIndicies) {
			System.out.println("Added File at pos: " + i);
			add(tempModel.get(i), mp3readingData);
		}
		//creating a List of Lists representing the cut Filenames
		for (File f : mp3readingData) {
			String path = f.getAbsolutePath();
			//deleting common path
			path = path.replace(ComponentStorage.MAIN_FRAME.path, "");
			System.out.println(ComponentStorage.MAIN_FRAME.path);
			path = path.replace(".mp3", "");
			//standardising seperators to ~
			path = path.replace("_", "~");
			path = path.replace("\\", "~");
			path = path.replace("-", "~");
			path = path.replace(";", "~");
			path = path.replace("/", "~");
			path = path.replace("#", "~");
			ArrayList<String> row = new ArrayList<String>();
			//creating the List representing the Files name and its data
			for (String s : path.split("~")) {
				if (s != null&&s!=""&&s!=" "&&s.length()>=2) {
					System.out.print("added :" + s + " || ");
					row.add(s);
				}
			}
			System.out.println();
			//adding the File to the tablemodel
			if(row.size()>longestFile)longestFile=row.size();
			rows.add(row);
		}
		//adding the headers
		names = new Vector<String>();
		for(int i = 0; i<longestFile;i++){
			names.add(new String("plese add a title"));
		}
		//converting to Vectors of Vectors
		data = new Vector();	
		for (int i = 0;i<rows.size();i++) {
			data.add(new Vector<String>());
			for(int j=0;j<longestFile;j++){
				try{
					if(rows.get(i).get(j)!=null&&rows.get(i).get(j)!=""){
					data.get(i).add( rows.get(i).get(j));}
				}catch(IndexOutOfBoundsException e ){
					System.out.println("This row was shorter");
				}
			}
		}
		
		System.out.println("Datalength: "+data.size()+"|| datasublength: "+data.elementAt(0).size()+"|| namelength: "+names.size());
		dataTable=new JTable(data,names);
		scroller= new JScrollPane(dataTable);
		ComponentStorage.MAIN_FRAME.pack();
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
