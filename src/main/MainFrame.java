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
import sortingPanel.SortingPanel;

/**
 * This is the actual MainWindow with included JPanels with differing content
 * 
 * @author Malte
 *
 */
public class MainFrame extends JFrame {
	// default Path
	public String path = ".";
	// ContentPane
	private Container contentPane;
	// pointer to currently visible Component
	private JComponent activeComonent;

	public MainFrame() {
		// basic setup
		super("MP3-HoerbuchHandler");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		contentPane = this.getContentPane();

		/* Move Window to center
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);*/
		this.setLocation(0,0);

		// initialise the components
		ComponentStorage.MAIN_FRAME = this;
		ComponentStorage.DATA_SELECTION_PANEL = new DataSelectionPanel(".");
		ComponentStorage.SORTING_PANEL = new SortingPanel();

		// Opens new directory view
		new PathChooser();
	}

	/**
	 * changes the root directory to the
	 * 
	 * @param newPath
	 */
	public void changePath(String newPath) {
		System.out.println("New path was parsed to MainFrame as: " + newPath);
		path = newPath;
		// updating the selection panel path
		ComponentStorage.DATA_SELECTION_PANEL.updatePath(path);
	}

	public void changeView(JComponent newComponent) {
		// check if view is already setup
		if (activeComonent != null) {
			//removing last visible pane
			contentPane.remove(activeComonent);
			//updating to new component
			activeComonent = newComponent;
			contentPane.add(newComponent, BorderLayout.CENTER);
			//visual update
			this.pack();
			this.revalidate();
			System.out.println("Changed visible component to: " + newComponent.toString());
		} else {
			//updating to new component
			activeComonent = newComponent;
			contentPane.add(newComponent, BorderLayout.CENTER);
			//visual update
			this.pack();
			setVisible(true);
			System.out.println("Changed visible component to: " + newComponent.toString());
		}

	}

	/*
	 * Good old main method
	 */
	public static void main(String[] args) {
		new MainFrame();
	}

}
