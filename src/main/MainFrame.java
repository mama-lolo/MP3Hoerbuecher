package main;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import dataSelectionPanel.DataSelectionPanel;
import pathGetter.PathChooser;
/**
 * This is the actual MainWindow with included JPanels with differing content
 * @author Malte
 *
 */
public class MainFrame extends JFrame {
	//gets the programm path and formats it
	private String path=".";
	private Container contentPane;
	
	//pointer to currently visible Component
	private JComponent activeComonent;
	
	//Creating the different components
	
	public MainFrame() {
		//basic setup
		super("MP3-HoerbuchHandler");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		contentPane= this.getContentPane();
		//Move Window to center
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
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
		if(activeComonent!=null){
		contentPane.remove(activeComonent);
		activeComonent= newComponent;
		contentPane.add(newComponent,BorderLayout.CENTER);
		this.pack();
		this.revalidate();
		System.out.println("Changed visible component to: " + newComponent.toString());
		}else{
			activeComonent= newComponent;
			contentPane.add(newComponent,BorderLayout.CENTER);
			this.pack();
			setVisible(true);
			System.out.println("Changed visible component to: " + newComponent.toString());
		}
		
	}
	public static void main(String[] args) {
		new MainFrame();
	}

}
