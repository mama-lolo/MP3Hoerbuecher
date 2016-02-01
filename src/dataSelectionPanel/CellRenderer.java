package dataSelectionPanel;

//Main content downloaded from StackOverflow
import java.awt.Component;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 * adapting display
 * @author Malte
 *
 */
public class CellRenderer extends DefaultListCellRenderer{
	
	private String rootPath;
	public CellRenderer(String root){
		rootPath=root;
	}
	/**
	 * sets displayed string
	 */
    public Component getListCellRendererComponent(
        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        //DefaultListCellRenderer always returns a JLabel
        //super setups up all the defaults
    	
    	JLabel label = (JLabel)super.getListCellRendererComponent(list, value.toString().replace(rootPath, ""), index, isSelected, cellHasFocus);
        // "value" is whatever object you put into the list, you can use it however you want here
        return label;

    }
    /**
     * update path to newPath
     * @param newPath
     */
    public void updatePath(String newPath){
    	rootPath = newPath;
    }
}
