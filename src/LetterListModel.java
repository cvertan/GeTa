	import javax.swing.DefaultListModel;
	import java.util.ArrayList;
public class LetterListModel extends DefaultListModel{
	
	 
	    public Object getElementAt(int index) {
	       LatinLetterNode let = (LatinLetterNode) super.getElementAt(index);
	        return  "<html><font size=\'+1\'>"+let.getEntireTranslit()+"</font></html>";
	    }
}