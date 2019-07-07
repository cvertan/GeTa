import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.*;
/**
 * buils an object having as label one fidel letter
 * @author Cristina Vertan
 *
 */
public class FidalNode {
	/**
	 * list of {@link LatinLetterNode} children nodes
	 */
   private  ArrayList<LatinLetterNode> translitChildren;
   /**
    *  link to the parent node {@link WortNode}
    */
    private WortNode parent;
    /**
     * the index of the fidal letter within one grpahical unit
     */
    private int nr;
    /**
     * shows if the graphical unit ends with a ":"
     */
    boolean hasEnd;
    /**
     * specifies the and symbol after a word (difference South-Arabic vs. Ge'ez)
     */
    private String endSymbol;
    /**
     * editorial Annotation attached to a node
     */
    Annotation edAnnot;
    /**
     *number of a line break attached to a fidel object
     */
    private int posLB;
    /**
     * builds a fidal letter node
     * @param parent parent node, a graphical unit
     * @param nr index of fidal letter within the graphical unit
     * @param hasEnd true id word ends with an end Symbol
     * @param endSymbol contains the end Symbol.
     */
    public FidalNode(WortNode parent, int nr, boolean hasEnd,String endSymbol){
    	this.parent=parent;
    	translitChildren=new ArrayList<LatinLetterNode>();
    	this.nr=nr;
    	this.hasEnd=hasEnd;
    	if ((this.hasEnd)&&((endSymbol.isEmpty()||(endSymbol.equals("\u1361"))))) this.endSymbol="\u1361";
    	else this.endSymbol=endSymbol;
    	posLB=0;
    	edAnnot=null;
    }
    /**
     * 
     * @return the associated line break
     */
    public int getPosLB(){
    	return posLB;
    }
    /**
     * 
     * @param n the number of the line break
     */
    public void setPosLB(int n){
    	posLB=n;
    }
    /**
     * 
     * @return a copy of the cuurent fidal letter node
     */
    
    public FidalNode copyFidalNode(){
    	FidalNode f=new FidalNode(parent,nr,hasEnd,endSymbol);
    	ArrayList<LatinLetterNode> trChildren=new ArrayList<LatinLetterNode>();
    	for(int i=0;i<translitChildren.size();i++)
    		 trChildren.add(translitChildren.get(i).copyLatinLetterNode());
    	f.setTranslitChildren(trChildren);
    	if(edAnnot!=null)
    	f.setEdAnnot(edAnnot.copyAnnotation());
    	return f;
    }
    /**
     * 
     * @param s the symbol at the end of the graphical unit
     */
    public void setEndSymbol(String s){
    	endSymbol=s;
    }
    /**
     * 
     * @return the annotation for the line break
     */
    public String getLB(){
    	String s="";
    	if(edAnnot!=null)
    	  if( (edAnnot.getSpecificTag("lb")!=null)) s="<font color='red' size='-1'><b><sup>"+edAnnot.getSpecificTag("lb").getAttrList().get(0).getValue()+"</sup></b></font>";
    	
    	return s;
    }
    public String getLB1(){
    	String s="";
    	if(edAnnot!=null)
    	  if( (edAnnot.getSpecificTag("lb1")!=null)) s="<font color='red' size='-1'><b><sup>"+edAnnot.getSpecificTag("lb1").getAttrList().get(0).getValue()+"</sup></b></font>";
    	
    	return s;
    }
    /**
     * 
     * @return the page break annotation
     */
    public String getPB(){
    	String s="";
    	s=getPBB()+getPBA();
    	return s;
    }
    /**
     *  label for the page breaks situated before the letter
     * @return
     */
    public String getPBB(){
    	String s="";
    	if(edAnnot!=null)

    	   if (edAnnot.getSpecificTag("pb","-")>0) {
    		  if(edAnnot.getSpecificTag("pb","-")==1)
    		  s="-"+"<font color='green' size='-1'><b>&lt;PB/&gt;</b></font>";
    		  else {
    			  s="-"+"<font color='green' size='-1'><b>&lt;PB/&gt;<sup>"+edAnnot.getSpecificTag("pb","-")+"</sup></b></font>";
    		  }
    	  }
    	return s;
    }
    /**
     *  label for the page breaks situated after the letter
     * @return
     */
    public String getPBA(){
    	String s="";
    	if(edAnnot!=null) 
    	   if (edAnnot.getSpecificTag("pb","+")>0) {
    		  if(edAnnot.getSpecificTag("pb","+")==1)
    		  s="+"+"<font color='green' size='-1'><b>&lt;PB/&gt;</b></font>";
    		  else {
    			  s="+"+"<font color='green' size='-1'><b>&lt;PB/&gt;<sup>"+edAnnot.getSpecificTag("pb","+")+"</sup></b></font>";
    		  }
    	   }
    	return s;
    }
    /**
     * 
     * @return the end symbol
     */
    public String getEndSymbol(){
    	return endSymbol;
    }
    /**
     *  serializes to JSON a Fidal Letter object
     * @return a JSON object
     */
    public JSONObject toJson(){
    	JSONObject o=new JSONObject();
    	o.put("Nr",""+ nr);
    	o.put("end", ""+hasEnd);
    	if (hasEnd) o.put("endS", endSymbol);
    	if(posLB>0) o.put("pLB", posLB);
    	if(edAnnot!=null)
    	o.put("Ed", edAnnot.toJson());
    	JSONArray a =new JSONArray();
    	for(int i=0;i<translitChildren.size();i++)
    		a.put(translitChildren.get(i).toJson());
    	o.put("LL", a);
    	return o;
    }
    /**
     * 
     * @param n type of script 
     * @param m type of Document
     * @return the JSON serialisation. Used for ANNIS purposes
     */
    public JSONObject toJson1(int n,int m){
    	JSONObject o=new JSONObject();
 
    	String s=getFidalLabel(n,m);
    	if (hasEnd) s=s+ endSymbol;
    	o.put("FIDLET", s);
    	s=getFidalEdLabel(n,m);
    	o.put("FIDLETED", s);
    	s=getTranslitLabel(n);
    	o.put("TRFID", s);
    	if(posLB>0) o.put("pLB", posLB);
    	if(edAnnot!=null)
    	o.put("Ed", edAnnot.toJson2());
    	JSONArray a =new JSONArray();
    	for(int i=0;i<translitChildren.size();i++)
    		a.put(translitChildren.get(i).toJson1());
    	o.put("LL", a);
    	return o;
    }
    /**
     * 
     *asigns a new Fidel für das word
     */
    public void resetTokenId(String id){
    	for(int i=0;i<translitChildren.size();i++){
    		LatinLetterNode l1=translitChildren.get(i);
    		l1.setTokenId(id);
    		translitChildren.set(i, l1);
    	}
    }
    /**
     * 
     * @return the children of the node
     */
    public ArrayList<LatinLetterNode> getTranslitChildren(){
    	return translitChildren;
    }
    /**
     * 
     * @param children the Latin Letter Nodes to be assignes to one fidel node
     */
    public void setTranslitChildren( ArrayList<LatinLetterNode> children){
    	for(int i=0;i<children.size(); i++) {
    		translitChildren.add(children.get(i));
    	   translitChildren.get(i).setParent(this);
    	}
    }
    /**
     * 
     * @return the position of the current node among children in same hierechie
     */
    public int getNr(){
    	return nr;
    }
    /**
     * 
     * @param nr new priority for a certain child
     */
    public void setNr(int nr){
    	this.nr=nr;
    }
    /**
     * 
     * @param ed an editorial annotation to be assigned
     */
    public void setEdAnnot(Annotation ed){
    	edAnnot=ed;
    	
    }
    /** 
     * returns the editorial annotation
     * @return
     */
    public Annotation getEdAnnot(){
    	return edAnnot;
    }
    
   /**
    * adds on a certain position a new node
    * @param pos internal position
    * @param s s new LatinLetterNode annotation
    */
    public void addChild(int pos, LatinLetterNode s){
    	s.setParent(this);
    	translitChildren.add(pos,s);
    }
    /**
     * assignes a LtinLetterNode to the children list
     * @param s the LatinLetterNode object to be added
     */
    public void addChild(LatinLetterNode s){
    	s.setParent(this);
    	translitChildren.add(s);
    }
    /**
     * insertio of a new Child
     * @param pos position
     * @param s new LAtin LetterNode
     */
    public void setChild(int pos, LatinLetterNode s){
    	s.setParent(this);
    	translitChildren.set(pos, s);
    }
    /**
     * remove a TranslitLetter
     * @param pos position whrere the removal has to take place 
     */
    public void removeChild(int pos){
    	translitChildren.get(pos).setParent(null);
    	translitChildren.remove(pos);
    }
    /**
     * remove one LatinLetterNode  node
     */
    public void removeChild(LatinLetterNode s){
    	s.setParent(null);
    	translitChildren.remove(s);
    }
    /**
     * 
     * @return the Id of the FidelLetter object
     */
    public String getId(){
    	return parent.getId()+"F"+nr;
    }
    /**
     * icrease the number of children
     */
    public void increaseChildren(){
    	for(int i=0;i<translitChildren.size();i++){
    		translitChildren.get(i).setNr((byte)(translitChildren.get(i).getNr()+1));
    	}
    }
    /**
     * decrease the number of children
     */
    public void decreaseChildren(){
    	for(int i=0;i<translitChildren.size();i++){
    		translitChildren.get(i).setNr((byte)(translitChildren.get(i).getNr()-1));
    	}
    }
    /**
     * 
     * @param m type of script
     * @return string the transliteration letter
     */
    public String getTranslitLabel(int m){
    	String t="";
    	int typ;
    	char c;
    	for (int i=0;i<translitChildren.size();i++){
    		if(i>0){
    			if (!translitChildren.get(i).getTokenId().equals(translitChildren.get(i-1).getTokenId()))
    		           t=t+"-"+translitChildren.get(i).getEntireTranslit();
    			else t=t+translitChildren.get(i).getEntireTranslit();
    		}
    		else t=t+translitChildren.get(i).getEntireTranslit();
    	}
    	return t;
    
    }
    /**
     * 
     * @return string the transliteration without HTML elements
     */
    public String getTranslitLabelIntern(){
    	String t="";
    	int typ;
    	char c;
    	for (int i=0;i<translitChildren.size();i++){
    		if((i<translitChildren.size()-1)&&(translitChildren.get(i).getEntireTranslit().equals(translitChildren.get(i+1).getEntireTranslit())))
    		 t=t+"";
    		else if((i<translitChildren.size()-1)&&(translitChildren.get(i).getLetter()==translitChildren.get(i+1).getLetter())&&(translitChildren.get(i).getTyp()==0)&&(translitChildren.get(i+1).getTyp()==1))
    			 t=t+"";
    			else t=t+translitChildren.get(i).getEntireTranslit();
    		
    	}
    	//System.out.println("Label "+t);
    	return t;
    }
    /**
     * 
     * @param k position where the insertion takes place
     * @param l LatinLetterNode
     */
    public void setLetter(int k, LatinLetterNode l){
    	translitChildren.set(k, l);
    }
    /**
     * 
     * @return true if the graphical unit has an ending
     */
    public boolean hasEnding(){
    	return hasEnd;
    }
    public void setEnd(boolean b){
    	
    	hasEnd=b;
    }
    public String hasLeftED(){
    	boolean found=false;
    	String s="";
    	int i=0;
    	if (edAnnot!=null){
    	ArrayList<Attribut> at= edAnnot.getSpecificTag("ed").getAttrList();
    	while((i<at.size())&&(!found)){
    		if (at.get(i).getName().equals("b")) {found=true; s= at.get(i).getValue();}
    		else i=i+1;
    	}
    	}
    	return s;
    }
    /**
     * 
     * @return a string collected as comment annotation
      */
    public String hasCommentED(){
    	boolean found=false;
    	String s="";
    	int i=0;
    	if (edAnnot!=null){
    	ArrayList<Attribut> at= edAnnot.getSpecificTag("ed").getAttrList();
    	while((i<at.size())&&(!found)){
    		if (at.get(i).getName().equals("c")) {found=true; s= at.get(i).getValue();}
    		else i=i+1;
    	}
    	}
    	return s;
    }
    /**
     * 
     * @return a tring representing the editorial mark placed before the fidel letter
     */
    public String hasRightED(){
    	boolean found=false;
    	String s="";
    	int i=0;
    	if (edAnnot!=null){
    	ArrayList<Attribut> at= edAnnot.getSpecificTag("ed").getAttrList();
    	while((i<at.size())&&(!found)){
    		if (at.get(i).getName().equals("a")) {found=true; s= at.get(i).getValue();}
    		else i=i+1;
    	}
    	}
    	return s;
    }
    /**
     * 
     * @return a string representing the delimiter at the end of the label representing the fidel letter object
     */
    public String getEdEndSymbol(){
    
    	int i=endSymbol.indexOf('\u1361');
    	if(i<0) return "";
    	else{
    	if ((i>0)&&(i<endSymbol.length()-1)) return "<font color='blue'>"+endSymbol.substring(0,i)+"</font><font color='black'>\u1361</font>"+"<font color='blue'>"+endSymbol.substring(i+1)+"</font>";
    	else if(i>0) return "<font color='blue'>"+endSymbol.substring(0,i)+"</font><font color='black'>\u1361</font>";
    	else return "<font color='black'>\u1361</font>"+"<font color='blue'>"+endSymbol.substring(i+1)+"</font>";
    	}
    }
    /**
     * 
     * @param n the type of script 0 for Ge'ez 1 for South-Arabic
     * @param m the type of document 0 vocalized, 1 unvocalized
     * @return the label of the object i.e the letter and the delimiter; The label is calculated through concatenation of the labels of the children i.e. the corresponding {@link LatinLetterNode} object
     */
    public String getFidalLabel(int n, int m){
    	InitializeFonts geezLetters=new InitializeFonts();
    	if(n==0){
    	if (hasEnding())
    		if (m==0)
    	return geezLetters.geezTranslitR.get(getTranslitLabelIntern());
    			//+'\u1361';
    		else 
    			return geezLetters.geezTranslitR.get(getTranslitLabelIntern()+"a");
    					//+'\u1361';
    	else
    		if( m==0)
    		return geezLetters.geezTranslitR.get(getTranslitLabelIntern());
    		else 
    			return geezLetters.geezTranslitR.get(getTranslitLabelIntern()+"a");
    	}
    	else{
    		if (hasEnding())
    	    	return geezLetters.suedArabTranslitR.get(getTranslitLabelIntern());
    	    	else return geezLetters.suedArabTranslitR.get(getTranslitLabelIntern());
    	}
    }
    /**
     * Returns the label (the fidal letter) together with the editorial annotations
     * @param n the type of script 0 for Ge'ez 1 for South-Arabic
     * @param m the type of document 0 vocalized, 1 unvocalized
     * @return the label of the object i.e the letter and the delimiter; The label is calculated through concatenation of the labels of the children i.e. the corresponding {@link LatinLetterNode} object
     */
    public String getFidalEdLabel(int n, int m){
    	InitializeFonts geezLetters=new InitializeFonts();
    	if(n==0){
    	if (hasEnding())
    		if (m==0)
    	return geezLetters.geezTranslitR.get(getTranslitLabelIntern());
    			//+getEdEndSymbol()+getLB();
    		else 
    			return geezLetters.geezTranslitR.get(getTranslitLabelIntern()+"a");
    					//+getEdEndSymbol()+getLB();
    	else
    		if( m==0)
    		return geezLetters.geezTranslitR.get(getTranslitLabelIntern());
    				//+getLB();
    		else 
    			return geezLetters.geezTranslitR.get(getTranslitLabelIntern()+"a");
    					//+getLB();
    	}
    	else{
    		if (hasEnding())
    	    	return geezLetters.suedArabTranslitR.get(getTranslitLabelIntern());
    	    			//+getLB();
    	    	else return geezLetters.suedArabTranslitR.get(getTranslitLabelIntern());
    	    			//+getLB();
    	}
    }
    /**
     * calculates the label of the object (the fidal letter) without the delimiter
     * @param n the type of script 0 for Ge'ez 1 for South-Arabic
     * @param m the type of document 0 vocalized, 1 unvocalized
     * @return the label of the object; The label is calculated through concatenation of the labels of the children i.e. the corresponding {@link LatinLetterNode} object
     */
    public String getFidalInternLabel(int n, int m){
    	InitializeFonts geezLetters=new InitializeFonts();
    	if(n==0)
    		if(m==0)
    	 return geezLetters.geezTranslitR.get(getTranslitLabelIntern());
    		else return geezLetters.geezTranslitR.get(getTranslitLabelIntern()+"a");
    	else  return geezLetters.suedArabTranslitR.get(getTranslitLabelIntern());
    }
    /**
     * 
     * @return the {@link WortNode} Graphical Unit being the parent of this node
     */
    public WortNode getParent(){
    	return parent;
    }
    /**
     * 
     * @param p assigns the {@link WortNode} p as parent for the current fidal object
     */
    public void setParent(WortNode p){
    	parent=p;
    }
    /**
     * 
     * @param b true if the fidal letter is followed by a delimiter
     */
    public void setHasEnding(boolean b){
    	if(!b) endSymbol="";
    	hasEnd=b;
    }
   
    public String toString(){
    	InitializeFonts geezLetters=new InitializeFonts();
    	
    	if (hasEnding())
    		
    	return geezLetters.geezTranslitR.get(getTranslitLabelIntern());
    			//+'\u1361';
    		
    	else
    		
    		return geezLetters.geezTranslitR.get(getTranslitLabelIntern());
    		
    	}
}
