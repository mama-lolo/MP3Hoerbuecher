package sortingPanel;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import main.ComponentStorage;

public class SortingPanel extends JPanel {
	String[][] data = {{""}};
	String [] name = {""};
	public JTable dataTable = new JTable(data,name);
	public JScrollPane scroller = new JScrollPane(dataTable);

	public SortingPanel() {
		super();
		scroller.setVisible(true);
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
				if (s != null&&s!="") {
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
		String[] names = new String[longestFile];
		for(String s : names){
			s = new String("plese add a title");
		}
		//converting the Lists to Object[][]
		data = new String[rows.size()][longestFile];	
		for (int i = 0;i<data.length;i++) {
			for(int j=0;j<data[i].length;j++){
				try{
					if(rows.get(i).get(j)!=null&&rows.get(i).get(j)!=""){
					data[i][j] = rows.get(i).get(j);}else{
						data[i][j]="";
					}
				}catch(IndexOutOfBoundsException e ){
					data[i][j]= new String("");
				}
			}
		}
		for(String[] sarray:data){
		System.out.println(Arrays.toString(sarray));}
		dataTable=new JTable(data,names);
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
