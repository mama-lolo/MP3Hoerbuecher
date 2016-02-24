package sortingPanel;

import java.awt.ScrollPane;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JTable;

import main.ComponentStorage;

public class SortingPanel extends JPanel {
	public JTable data;
	public ScrollPane scroller;
	public SortingPanel(){
		super();
		
	}

	
	public void updateData(){
		Vector<Vector> data = new Vector();
		ArrayList<File> mp3Data = new ArrayList<File>();
		DefaultListModel<File> tempModel = ComponentStorage.DATA_SELECTION_PANEL.model;
		ArrayList<Integer> activatedIndicies = ComponentStorage.DATA_SELECTION_PANEL.selectionHandler.selectedIndicies;
		System.out.println("Started adding Files to the sortable List.");
		System.out.println(activatedIndicies.size()+1+" Indicies have been activated");
		for(Integer i : activatedIndicies){
			System.out.println("Added File at pos: "+i);
			add(tempModel.get(i),mp3Data);
		}
	}


	private void add(File file, ArrayList<File> mp3Data) {
		System.out.println("Executed add function");
		if(file.isDirectory()){
			for(File f : file.listFiles()){
				add(f,mp3Data);
			}
		}else if(file.getAbsolutePath().contains(".mp3")||file.getAbsolutePath().contains(".wav")){
			mp3Data.add(file);
			System.out.println(file.getPath());
		}
		
	}
}
