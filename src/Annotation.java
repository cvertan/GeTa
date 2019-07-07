import java.util.ArrayList;
import org.json.*;
/**
 * builds an Annotation object which consists of a list of {@link Tag} objects and additional information about the way how the annotation was generated (automatic or manual)
 * the completion status and if it was as "global complete" annotated
 * @author Cristina Vertan
 *
 */
public class Annotation {	
	/**
	 * true if the annotation was done automatic
	 * the information id needed for the visualisation in {@link TagGUI#showProgress()} and in {@link TagGUI} in lingAnnotFrame.addInternalFrameListener
	 */
  private boolean autom;
  /**
   * true if the annotation was done global
   */
  private boolean global;
  /**
   * true if the annotation is complete (no further annotation necessary)
   *  needed by {@link TranslitListModel#getElementAt(int);} the token is coloured blue
   */
  private boolean complete;
  /**
   * true if the annotation is complete 
   *  needed by {@link TranslitListModel#getElementAt(int);} the token is coloured red 
   */
  private boolean inProgress;
  /**
   * true if the token with this Annotation is part of an NE
   *  needed by  {@link TagGUI} in lingAnnotFrame.addInternalFrameListener
   */
  private boolean isNE;
  /**
   * the list of {@link Tag} objects composing the Annotation
   */
  private ArrayList<Tag> listTag;
  /**
   * builds a {@link Tag} object
   * @param autom true if the annotation is done automatically
   * @param global true if the annotation is performed global
   * @param complete true if the annotation is complete
   * @param ne true if the token is part of an NE
   * @param lt list of {@link Tag} objects
   */
  public Annotation( boolean autom, boolean global,boolean complete, boolean ne, ArrayList<Tag>lt){

	  this.autom=autom;
	  this.global=global;
	  this.complete=complete;
	  this.inProgress=false;
	  this.isNE=ne;
	  listTag=new ArrayList<Tag>();
	  for(int i=0;i<lt.size();i++) listTag.add(lt.get(i));
	
  }
  /**
   * returns a copy of the current object
   * @return an {@link Annotation} object 
   */
  public Annotation copyAnnotation(){
	  ArrayList<Tag> t=new ArrayList<Tag>();
	  for(int i=0;i<listTag.size();i++)
		  t.add(listTag.get(i).copyTag());
	  Annotation an=new Annotation(autom,global,complete,isNE,t);
	  return an;
  }
  /**
   * 
   * @return true if the annoated token is part of an NE
   */
  public boolean isNEPart(){
		return this.isNE;
	}
  /**
   * decides if the annotated token is a part of an NE
   * @param b true if the annoated token is oart of an NE
   */
	public void setIsNE(boolean b){
		this.isNE=b;
	}
	/**
	 * returns from the list of tags the one with a given name; if there is no such tag returns null
	 * @param s name {@link Tag#getName()} of the Tag to be retrieved
	 * @return a {@link Tag}
	 */
  public Tag getSpecificTag(String s){
	  int i=0;
	  boolean found=false;
	  Tag tag=null;
			  
	 while((i<listTag.size())&&(!found)){
		 if (listTag.get(i).getName().equals(s)){
			 found=true; tag=listTag.get(i);
		 }
		 else i=i+1;
	 }
	 return tag;
  }
  /**
	 * returns from the list of tags the one with a given name and with acertain value; if there is no such tag returns null
	 * used by {@link TagGUI}
	 * @param s name {@link Tag#getName()} of the Tag to be retrieved
	 * @param s1 first letter of the value to be searched
	 * @return an integer the index of  the found {@link Tag}
	 */
  public int getSpecificTag(String s,String s1){
	  int i=0;
	  boolean found=false;
	  Tag tag=null;
		int t=0;	  
	 while((i<listTag.size())){
		 if (listTag.get(i).getName().equals(s)){
			 int j=0;
			 //boolean found1=false;   
			 while((j<listTag.get(i).getAttrList().size()) ) {
				 if(listTag.get(i).getAttrList().get(j).getValue().substring(0, 1).equals(s1)) {
			                 //tag=listTag.get(i);
					 t=t+1;
			             //    found1=true;
				 }
				  j=j+1;
			 }
		 }
		  i=i+1;
	 }
	 return t;
  }
  /**
   * modifies the name of the tag having a given name s with a new name ns
   * @param s the name of the tag to be modifies
   * @param ns the new name of the tag
   */
  public void setSpecificTag(String s, String ns){
	  int i=0;
	  boolean found=false;
	  Tag tag=null;
			  
	 while((i<listTag.size())&&(!found)){
		 if (listTag.get(i).getName().equals(s)){
			 found=true; ;listTag.get(i).setName(ns);
		 }
		 else i=i+1;
	 }
	
  }
  /**
   * search for a tag havinh as attribute the one with a given name
   * @param name {@link Attribut#getName()} of the morphological feature to be searched
   * @return a string representing the values of the searched feature
   */
  public String getMorphoValue(String name){
	  String s="";
	  Tag t=listTag.get(0);
	  if(t.getAttrList()!=null){
		  int i=0;
		  boolean found=false;
		  while((i<t.getAttrList().size())&&(!found)){
			  if(t.getAttrList().get(i).getName().equalsIgnoreCase(name)){ found=true;s=t.getAttrList().get(i).getValue();}
			  else i=i+1;
		  }
	  }
	  return s;
  }
  /**
   * repales the name of a feature of a given tag. Used when inconsistencies in the names of the features occur, in correction processes
   * @param name name of the {@link Attribut} object to be renames 
   * @param value value of the attribute for which we give a new naem
   * @param newName new name to be assigned to the attribute
   */
  public void findDoubleTagName(String name, String value, String newName){
	  String s="";
	  Tag t=listTag.get(0);
	  boolean found=false;
	  if(t.getAttrList()!=null){
		  int i=0;
		   found=false;
		  while((i<t.getAttrList().size())&&(!found)){
			  if(t.getAttrList().get(i).getName().equalsIgnoreCase(name)&&(t.getAttrList().get(i).getValue().indexOf(value)>=0)){ found=true;t.getAttrList().get(i).setName(newName); }
			  else i=i+1;
		  }
	  }
  }
  /**
   * find if there are within the same annotation two tags with same name
   * used to eliminate duplicates dieffering e.g in capitaliaztion
   */
	  public void findDoubleTags(){
		  String s="";
		  Tag t=listTag.get(0);
		  boolean found=false;
		  if(t.getAttrList()!=null){
			  int i=0;
			   found=false;
			  while(i<t.getAttrList().size()){
				  int j=0;  
			      while (j<t.getAttrList().size()) {
				   if((j!=i)&&(t.getAttrList().get(i).getName().equalsIgnoreCase(t.getAttrList().get(j).getName()))){
					   System.out.println("found "+ t.getAttrList().get(i).getName() );    
					   t.getAttrList().remove(j) ;
					       }
				   j=j+1;
			      }
			      i=i+1;
			  }
			  
		  }
		  this.getListTag().set(0, t);
	  
	  
  }
	  /**
	   * changes the value of a feature with a given name within a morphological annotation
	   * a morphological annoation has just one tag
	   * @param name name of the feature
	   * @param value new value of the feature
	   */
  public void setMorphoValue(String name,String value){
	  String s="";
	  Tag t=listTag.get(0);
	  if(t.getAttrList()!=null){
		  int i=0;
		  boolean found=false;
		  while((i<t.getAttrList().size())&&(!found)){
			  if(t.getAttrList().get(i).getName().equalsIgnoreCase(name)){ found=true;t.getAttrList().get(i).setValue(value);}
			  else i=i+1;
		  }
	  }
	  
  }
  /**
   * changes the name of a feature of a mophological annotation
   * @param oldname the old name of the feature
   * @param newname the new name of a feature
   */
  public void setMorphoNewName(String oldname,String newname){
	  String s="";
	  Tag t=listTag.get(0);
	  if(t.getAttrList()!=null){
		  int i=0;
		  boolean found=false;
		  while((i<t.getAttrList().size())&&(!found)){
			  if(t.getAttrList().get(i).getName().equalsIgnoreCase(oldname)){ found=true;t.getAttrList().get(i).setName(newname);}
			  else i=i+1;
		  }
	  }
  }
  /**
   * 
   *@return a string containing all values of the morphological annoatation
   */
  public String getEntireMorphoValue(){
	  String s="";
	  Tag t=listTag.get(0);
	  if(t.getAttrList()!=null){
		 
		  for(int i=0;i<t.getAttrList().size();i++)
		 
			  s=s+t.getAttrList().get(i).getValue()+ " ";
			 
		  
	  }
	  return s;
  }
  /**
   * 
   * @return a string the lemma of a token
   */
  public String getEntireDeepValues(){
	  String s="";
	  Tag t=listTag.get(0);
	  if(t.getAttrList()!=null){
		 
		  for(int i=0;i<t.getAttrList().size();i++)
		       if(!t.getAttrList().get(i).getName().equalsIgnoreCase("lex"))
			         s=s+t.getAttrList().get(i).getValue()+ " ";
			 
		  
	  }
	  return s;
  }
  /**
   * 
   * @return true if the morphological annotation has a comment
   */
  public boolean hasComment(){
	  
    boolean co=false;
	  Tag t=listTag.get(0);
	  if(t.getAttrList()!=null){
		 int i=0;
		  while((i<t.getAttrList().size()&&(!co))){
			 
			  
		    if (t.getAttrList().get(i).getName().equalsIgnoreCase("Comment"))  {
		    	if (!t.getAttrList().get(i).getValue().isEmpty())
		    	    co=true;
		    	else i=i+1;
		    }
		    else i=i+1;
		  }
	  }
		  return co;
  }
  /**
   * 
   * @return a string containing the lemma or the attached comment
   */
  public String getEntireMorphoValue1(){
	  String s="";
	  Tag t=listTag.get(0);
	  if(t.getAttrList()!=null){
		 
		  for(int i=0;i<t.getAttrList().size();i++){
			  //System.out.println(t.getAttrList().get(i).getName());
			  
		    if (t.getAttrList().get(i).getName().equalsIgnoreCase("Comment"))  {
		    	if(!t.getAttrList().get(i).getValue().isEmpty())
		    	s=s+"<br/>"+"<font color='blue'>Comment:</font>"+t.getAttrList().get(i).getValue()+ " ";
		    }
		    else  if (t.getAttrList().get(i).getName().equalsIgnoreCase("lex"))  {
		    	if(!t.getAttrList().get(i).getValue().isEmpty())
		    	s=s+"<font color='blue'>lex:</font>"+t.getAttrList().get(i).getValue().substring(t.getAttrList().get(i).getValue().indexOf('-')+2)+ " ";
		    }
		    else s=s+"<font color='blue'>"+t.getAttrList().get(i).getName()+":</font>"+t.getAttrList().get(i).getValue()+ " ";
		  }
		  
	  }
	  return s;
  }
  /**
   * serializes the Annoation to a JSON object
   * @return a JSON object
   */
  public JSONObject toJson(){
	  JSONObject o=new JSONObject();
		o.put("aut",""+autom);
		o.put("g", ""+global);
		o.put("c", ""+complete);
		o.put("p", ""+inProgress);
		o.put("ne", ""+isNE);
		JSONArray a=new JSONArray();
		for(int i=0;i<listTag.size();i++){
			
		a.put(listTag.get(i).toJson());
		}
	   o.put("LT", a);
	   return o;
  }
 /**
  *  serializes t a JSON Object for the ANNIS-Export without the informatio about automatization
  * @return a JSON object
  */
  public JSONObject toJson1(){
	  JSONObject o=new JSONObject();
	  o.put("ne", ""+isNE);
		JSONArray a=new JSONArray();
		for(int i=0;i<listTag.size();i++){
			
		a.put(listTag.get(i).toJson1());
		}
	   o.put("LT", a);
	   return o;
  }
  /**
   *  serializes t a JSON Object for the ANNIS-Export without the informatio about automatization and the aooartenenece to an NE
   * @return a JSON object
   */
  public JSONObject toJson2(){
	  JSONObject o=new JSONObject();
		JSONArray a=new JSONArray();
		for(int i=0;i<listTag.size();i++){
			
		a.put(listTag.get(i).toJson1());
		}
	   o.put("LT", a);
	   return o;
  }
  /**
   * 
   * @return true if the annotation is complete
   */
 public boolean isComplete(){
	 return complete;
 }
 public void setComplete (boolean b){
	 complete=b;
 }
 /**
  * 
  * @return true if the annotation is in progress
  */
 public boolean isInProgress(){
	 return inProgress;
 }
 /**
  * 
  * @param b boolean set if the annotation is in progress
  */
 public void setInProgress(boolean b){
	 inProgress=b;
 }
 /**
  * 
  * @return true if the annotation is done automatically
  */
  public boolean isAutom(){
	  return autom;
  }
  /**
   * 
   * @param b boolean set if the annotation is done automatic
   */
  public void setAutom(boolean b){
	  autom=b;
  }
  /**
   * 
   * @return true if the annotation is done global
   */
  public boolean isGlobal(){
	  return global;
  }
  /**
   * 
   * @param b boolean set if the annotation is done global
   */
  public void setGlobal(boolean b){
	  global=b;
  }
  /**
   * 
   * @return the list of {@link Tag}
   */
  public ArrayList<Tag> getListTag(){
	  return listTag;
  }
  /**
   * 
   * @return the list of {@link Tag}
   */
  public ArrayList<Tag> getListIds(){
	  return listTag;
  }
  /**
   * 
   * @param t a new Tag to be added to the list
   */
  public void addTag(Tag t){
	  listTag.add(t);
  }
  /**
   * 
   * @param t a  Tag to be removed from the list
   */
  public void removeTag(Tag t){
	  listTag.remove(t);
  }
 

}
