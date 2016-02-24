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
	public JTable dataTable = new JTable(data,new Vector());
	public JScrollPane scroller = new JScrollPane(dataTable);
	public SortingPanel(){
		super();
		this.add(scroller,BorderLayout.CENTER);
		revalidate();
		
	}

	//has to be fixed
	public void updateData(){
		ArrayList<File> mp3Data = new ArrayList<File>();
		DefaultListModel<File> tempModel = ComponentStorage.DATA_SELECTION_PANEL.model;
		ArrayList<Integer> activatedIndicies = ComponentStorage.DATA_SELECTION_PANEL.selectedIndicies;
		System.out.println("Started adding Files to the sortable List.");
		//inserting the different MP3 Files
		for(Integer i : activatedIndicies){
			System.out.println("Added File at pos: "+i);
			add(tempModel.get(i),mp3Data);
		}
		
		for(File f : mp3Data){
			String path = f.getAbsolutePath();
			path.replace(ComponentStorage.MAIN_FRAME.path, "");
			path.replace("|", "_");
			path.replace("\\", "|");
			path.replace(",","|");
			path.replace(";","|");
			path.replace("/","|");
			path.replace("#","|");
			Vector <String> temporary = new Vector<String>();
			for(String s : path.split("|")){
				temporary.add(s);
			}
			data.add(temporary);
			Vector<String> columns = new Vector();
			columns.add("1");columns.add("1");columns.add("1");columns.add("1");columns.add("1");columns.add("1");columns.add("1");columns.add("1");columns.add("1");
			dataTable= new JTable(data,columns);
			revalidate();
			System.out.println(data);
		}
	}


	private void add(File file, ArrayList<File> mp3Data) {
		if(file.isDirectory()){
			for(File f : file.listFiles()){
				add(f,mp3Data);
			}
		}else if(file.getAbsolutePath().contains(".mp3")||file.getAbsolutePath().contains(".wav")){
			mp3Data.add(file);
		}
		
	}
}
