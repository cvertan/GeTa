	import javax.swing.DefaultListModel;
	import com.alee.extended.label.*;
	import java.awt.Font;
import org.apache.commons.lang.StringEscapeUtils;
import java.util.ArrayList;
public class WortListModel extends DefaultListModel{
	
	TagGUI interf;
	int n;int m;
	   public WortListModel (TagGUI interf){
		   this.interf=interf;
		   n=interf.getTypScript();
		   m=interf.getTypDoc();
	   }
	    public Object getElementAt(int index) {
	    	WortNode word = interf.getWords().get(index);
	    	String s="";
	    	if (word.getSpecDivs()!=null) {
	    			if (word.getSpecDivs().size()==1)
	    				 return  "<html><font size=\'+1\' color=\'blue\'> &lt;DIV/&gt;</font><font size=\'+2\'>"+word.getFidalEdLabel(n,m)+"</font></html>";
	    				
	    			 if (word.getSpecDivs().size()>1)
	    				return "<html><font size=\'+1\' color=\'blue\'> &lt;DIV/&gt;*</font><font size=\'+2\'>"+word.getFidalEdLabel(n,m)+"</font></html>";
	    	//WebStyledLabel label = new WebStyledLabel ( word.getFidalEdLabel(n,m));
	    	//label.addStyleRange ( new StyleRange ( 0, word.getFidalEdLabel(n,m).length(), Font.ITALIC));
	    			else   return  "<html><font size=\'+2\'>"+word.getFidalEdLabel(n,m)+"</font></html>";
	    	}
	    	 else return  "<html><font size=\'+2\'>"+word.getFidalEdLabel(n,m)+"</font></html>";
	      // return  "<html><font size=\'+1\'>"+word.getFidalEdLabel(n,m)+"</font></html>";
	    // return  "<html>"+word.getFidalEdLabel(n,m)+"</html>";
	      //  return word.getFidalEdLabel(n,m);
	    }
	    
    public void addElement(Object obj, int i) {
		       
            
            this.add(i,obj);
       
    }
    public void removeElement(int i) {
	       
        
    	WortNode word= interf.getWords().get(i);
    	this.removeElement(word);
       
    }
    public void update()
    {
        fireContentsChanged(this, 0,interf.getWords().size()-1);
    }
}