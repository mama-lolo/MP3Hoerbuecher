package dataSelectionPanel;

//Main content downloaded from StackOverflow
import java.awt.Component;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

//adapting Display mode
public class CellRenderer extends DefaultListCellRenderer{
	
	private String rootPath;
	public CellRenderer(String root){
		rootPath=root;
	}
    public Component getListCellRendererComponent(
        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        // I know DefaultListCellRenderer always returns a JLabel
        // super setups up all the defaults
    	
    	//TO-DO no actual files--> nothing displays
    	JLabel label = (JLabel)super.getListCellRendererComponent(list, value.toString().replace(rootPath, ""), index, isSelected, cellHasFocus);
        // "value" is whatever object you put into the list, you can use it however you want here
        return label;

    }
}
