import java.util.ArrayList;
import org.json.*;
/**
 * builts an object representing one latin "Letter" ; it can have also two characters depending of its type
 * @author Cristina Vertan
 *
 */
public class LatinLetterNode {
	/**
	 * main letter of the label of the object
	 */
 private char letter;
 /**
  * type of the letter labiovelar or normal
  */
 private byte typ;
 /**
  * number of bytes on which the label is represented (used for representation of south arabic
  */
 private byte nr;
 /**
  * the parent node (a {@link FidalNode#getId()}
  */
 private FidalNode parent;
 /**
  * link to the {@link Token} object to which it belongs
  */
 private String tokenId;
 /**
  * builds an object for one "Letter" of the transcription
  * @param letter the main character of the label of the object
  * @param typ labiovelar (value 1) or not (value 0)
  * @param nr number of bytes
  * @param parent the parent node
  */
 public LatinLetterNode(char letter, byte typ, byte nr, FidalNode parent){
	 this.letter=letter;
	 this.typ=typ;
	 this.nr=nr;
	 this.parent=parent;
	 tokenId=null;
 }
 /**
  * realises a copy of the current object
  * @return a LatinLetterNode object
  */
 
 public LatinLetterNode copyLatinLetterNode(){
	 LatinLetterNode l=new LatinLetterNode(letter,typ,nr,parent);
	 l.setTokenId(tokenId);
	 return l;
 }
 /** 
  * serializes the LatinLetterNode object to a JSON Object
  * @return a  JSON object
  */
 public JSONObject toJson(){
	 JSONObject o =new JSONObject();
	 o.put("L", ""+letter);
	 o.put("T", ""+typ);
	 o.put("Nr", ""+nr);
	 if(tokenId !=null)
	 o.put("Tid", tokenId);
	 return o;
 }
 /** 
  * serializes the LatinLetterNode object to a JSON Object for the ANNIS-Export
  * @return a  JSON object
  */
 public JSONObject toJson1(){
	 JSONObject o =new JSONObject();
	 //o.put("L", ""+letter);
	 //o.put("T", ""+typ);
	 //o.put("Nr", ""+nr);
	 String s=getEntireTranslit();
	 o.put("LAT", s);
	 if(tokenId !=null)
	 o.put("Tid", tokenId);
	 return o;
 }
 /**
  * 
  * @return the parent node
  */
 public FidalNode getParent(){
	 return parent;
 }
 /**
  * 
  * @param p the node to be set as parent
  */
 public void setParent(FidalNode p){
	 parent=p;
 }
 /**
  * 
  * @return the main letter of the label of the object
  */
 public  char getLetter(){
	 return letter;
 }
 /**
  * 
  * @param c assigns a value to the main letter of the object
  */
 public void setLetter(char c){
	 letter=c;
 }
 /**
  * 
  * @return the type of the letter
  */
 public byte getTyp(){
	 return typ;
 }
 /**
  * 
  * @param b assigns a type to the object
  */
 public void setTyp(byte b){
	 typ=b;
 }
 /**
  * 
  * @return no. of bytes on which is represented the object
  */
 public byte getNr(){
	 return nr;
 }
 /**
  * 
  * @param b sets the no of byptes on which the label will be represented
  */
 public void setNr(byte b){
	 nr=b;
 }
 /**
  * 
  * @return the id of the Token to which the Fidal Node belogs
  */
 public String getTokenId(){
	 return tokenId;
 }
 /**
  * 
  * assignes an Id for the Token to which the Latin Letter Object belongs
  */
 public void setTokenId(String id){
	 tokenId=id;
 }
 /**
  * 
  * @return the id of the object
  */
 public String getLetterId(){
	 return parent.getId()+"L"+nr;
 }
 /**
  * 
  * @return a string the label of the object
  */
 public String getEntireTranslit(){
	 String t="";
	 if(typ==0){
		 if (letter=='\u1E09')t=""+"\u010D"+"\u0323";
		 else t=""+letter;
	 }
		else if(typ==1) t=letter+"\u02b7";
		else if(typ==2) t=letter+"0";
		else if(typ==3) t=letter+"00";	
		else t=letter+"0000";
	 return t;
 }
 /**
  * converts to String the label of the object
  */
 public String toString(){
	 return getEntireTranslit();
 }
}
