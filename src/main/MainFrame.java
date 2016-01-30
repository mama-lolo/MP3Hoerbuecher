package main;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JFrame;

import dataSelectionPanel.DataSelectionPanel;
import pathGetter.PathChooser;

/**
 * This is the actual MainWindow with included JPanels with differing content
 * @author Malte
 *
 */
public class MainFrame {
	//gets the programm path and formats it
	public JFrame frame1 = new JFrame("MP3-HoerbuchHandler");
	private String path=".";
	private Container contentPane;
	
	//pointer to currently visible Component
	private JComponent activeComonent;
	
	//Creating the different components
	
	public MainFrame() {
		//basic setup
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setResizable(false);
		contentPane = frame1.getContentPane();
		frame1.setLocationRelativeTo(null);
		
		//initialise the components
		ComponentStorage.MAIN_FRAME=this;
		ComponentStorage.DATA_SELECTION_PANEL=new DataSelectionPanel(".");
		
		//Opens new directory view
		new PathChooser();
	}
	
	
	public void changePath(String newPath){
		System.out.println("New path was parsed to MainFrame as: "+newPath);
		path = newPath;
		ComponentStorage.DATA_SELECTION_PANEL.updatePath(path);
	}
	
	public void changeView(JComponent newComponent){
		//check if already setup
		if(activeComonent!=null) {
			contentPane.remove(activeComonent);
			activeComonent= newComponent;
			contentPane.add(newComponent,BorderLayout.CENTER);
			frame1.pack();
			frame1.revalidate();
			System.out.println("Changed visible component to: " + newComponent.toString());
		}else {
			activeComonent= newComponent;
			contentPane.add(newComponent,BorderLayout.CENTER);
			frame1.pack();
			frame1.setVisible(true);
			System.out.println("Changed visible component to: " + newComponent.toString());
		}
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}