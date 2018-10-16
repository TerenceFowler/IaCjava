/*
 * This method updates the GUI variable and outputs them in the correct format
 */
package iac.project;
import java.util.*;
//IMPORTS
import javax.swing.DefaultListModel;
/**
 *
 * @author terencefowler
 */
public class GuiVariables 
{
    
    public DefaultListModel names;
    
    GuiVariables()
    {
        names = new DefaultListModel();
    }
    
    public void redo(ArrayList<vm> list)
    {
        names.removeAllElements();
        int len = list.size();
        String[] aStr = new String[len];
        for (int i=0; i<len; i++)
                {
                    System.out.println(list.get(i).getNameType());
                    names.addElement(list.get(i).getNameType());
                    System.out.println("redo wrote a value");
                }
    }
    
}
