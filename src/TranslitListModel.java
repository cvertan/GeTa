import java.util.ArrayList;

import javax.swing.DefaultListModel;

import org.json.JSONObject;
import org.json.JSONTokener;
public class TranslitListModel extends DefaultListModel{
	  TagGUI interf;
	  int n;int m;
	   public TranslitListModel (TagGUI interf){
		   this.interf=interf;
		   n=interf.getTypScript();
		   m=interf.getTypDoc();
	   }
	    public Object getElementAt(int index) {
	    	WortNode word= interf.getWords().get(index);
	        
	       // WortNode word = (WortNode) super.getElementAt(index);
	        String s="";
	       
	        if (word.getFidalLabel(n,m).charAt(0)!='S'){
	        for (int i=0;i<word.getTokenIds().size();i++){
	        	Token t=interf.getTokenLabel(word,i);
	        			//tokens.get(TagGUI.indexIdToken.get(word.getTokenIds().get(i)).intValue());
	        	if(t!=null){
	        	if (t.getMorphoAnnotation()==null){
	        		if (t.isAutom()) s=s+"<i>"+t.getLabel()+"</i>-";
	        		else s=s+t.getLabel()+"-";
	        	}
	        	
	        	else {
	        		/*if (t.getMorphoAnnotation().hasComment()){
	        	       System.out.println("****");
	        			s="<mark>";
	        		}*/
	        		if(t.getMorphoAnnotation().isInProgress()){
	        			if (t.getMorphoAnnotation().hasComment())
        			        s=s+"<span style=\'background-color:#D3D3D3\'><font color=\'green\'><b>"+t.getLabel()+"</b></font></span>-";
        				else
        					s=s+"<font color=\'green\'><b>"+t.getLabel()+"</b></font>-";
        			
	        		}
	        		else{
	        		if(!t.getMorphoAnnotation().isComplete()){
	        			if((t.getMorphoAnnotation().isGlobal())){
	        				if (t.getMorphoAnnotation().hasComment())
	        			        
	        					s=s+"<span style=\'background-color:#D3D3D3\'><font color=\'red\'><b>"+t.getLabel()+"</b></font></span>-";
	        				else
	        					s=s+"<font color=\'red\'><b>"+t.getLabel()+"</b></font>-";
	        			}
	        			else {
	        				if (t.getMorphoAnnotation().hasComment())
	        				       s=s+"<span style=\'background-color:#D3D3D3\'><b>"+t.getLabel()+"</b></span>-";
	        				else
	        					s=s+"<b>"+t.getLabel()+"</b>-";
	        			}
	        		}
	        		else{
	        			if((t.getMorphoAnnotation().isGlobal()))
	        				if (t.getMorphoAnnotation().hasComment())
	        				   s=s+"<span style=\'background-color:#D3D3D3\'><font color=\'red\'>"+t.getLabel()+"</font></span>-";
	        				else
	        					s=s+"<font color=\'red\'>"+t.getLabel()+"</font>-";
	        			else 
	        				
	        				if (t.getMorphoAnnotation().hasComment())
	        				    s=s+"<span style=\'background-color:#D3D3D3\'><font color=\'blue\'>"+t.getLabel()+"</font></span>";
	        				else 
	        					s=s+"<font color=\'blue\'>"+t.getLabel()+"</font>";
	        		}
	        	/*	if (t.getMorphoAnnotation().hasComment()){
	        			
	        			s=s+"</mark>";
	        		}*/
	        	}
	        	}
	        	    
	        if(s.charAt(s.length()-1)=='-') s=s.substring(0,s.length()-1);
	       // System.out.println("S =" +word.getFidalLabel(n,m)+ "*"+ word.getId());
	      if((!t.getNERef().isEmpty()) &&(interf.viewne)) s="<u style=\"border-bottom-color: green;\">"+s+"</u>-";
	    else s=s+"-";
	        }}
	        	 s=s.substring(0,s.length()-1);}
	        else s=word.getFidalLabel(n,m);
	       // System.out.println(s);
	    //    if (word.getNERef().length()>0) return  "<html><font size=\'+2\'><u style=\"border-bottom-color: green;;\">"+s+"</u></font></html>";
	       // else
	        if(s.endsWith("-")) s=s.substring(0,s.length()-1);
	        s=s.replaceAll("--","-");
	        	return  "<html><font size=\'+2\'>"+s+"</font></html>";
	    }
	    
	   public ArrayList<String> getTokenList(int index){
		 
		   return ((WortNode) super.getElementAt(index)).getTokenIds();
	   }
	 
	 
	 
	   /* public Object getFlagAt(int index) {
	        WortFidal tokenTranslit = (WortFidal) super.getElementAt(index);
	        return ""+tokenTranslit.getFlagAnnotStruct();
	    }
	    public String getIDAt(int index) {
	        WortFidal tokenTranslit = (WortFidal) super.getElementAt(index);
	        return tokenTranslit.getWordId();
	    }
	    
	    public AnnotStructure getStructAnnotAt(int index){
	    	 WortFidal tokenTranslit = (WortFidal) super.getElementAt(index);
		        return tokenTranslit.getStructureAnnot();
	    }
	 
	    public WortFidal getWordAt(int index) {
	        WortFidal w = (WortFidal) super.getElementAt(index);
	        return w;
	    }
	    public int getFlagAnnotStructAt(int index) {
	        WortFidal w = (WortFidal) super.getElementAt(index);
	        int n=w.getFlagAnnotStruct();
	        return n;
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
	        
	     
	    }*/
	       
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
     
	  /*  public String toString(int index){
	    	WortFidal token = (WortFidal) super.getElementAt(index);
	    	
	    	return token.getTranslitLabel();
	    }
	    public String ConversionString(int index){
	    	WortFidal token = (WortFidal) super.getElementAt(index);
	        return token.getTranslitLabel();
	    }
	    
	  /*  public String showAnnot(int index){
	    	   WortFidal token = (WortFidal) super.getElementAt(index);
			   ArrayList<Div> annot=token.getStructureAnnot().getAnnotDiv();
			   int n=annot.size();
		    	if (n>=0){
		    		String d=annot.get(0).getLevel();
		    		if(n>=1){
		    			d=d+"#"+annot.get(1).getLevel();
		    			
		    			if(n>=2){
		    				d=d+"#"+annot.get(2).getLevel();
		    				
		    				if(n>=3){
		    					d=d+"#"+annot.get(3).getLevel();;
		    				}
		    			}
		    		}
		    	return 	d;
		    	}
		    	else return token.getTranslitLabel();
		   }*/

}
