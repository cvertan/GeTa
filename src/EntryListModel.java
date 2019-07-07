import java.util.ArrayList;

import javax.swing.DefaultListModel;
public class EntryListModel extends DefaultListModel {

    public Object getElementAt(int index) {
        LexiconEntry entry = (LexiconEntry)super.getElementAt(index);
        return entry.getLexiconEntryLemma();
    }
   
    public LexiconEntry getEntryAt(int index) {
        LexiconEntry entry = (LexiconEntry)super.getElementAt(index);
        return entry;
    }
    

    public void addElement(Object obj) {
        if(!this.contains(obj))
        {
            int i=0;
            
            while(i<this.size()){
                    i++;
           }
             
            this.add(i, obj);
        }
        
     
    }
       
    public void addElement(Object obj, int i) {
	       
        
        this.add(i,obj);
   
}
 

}
