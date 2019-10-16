import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.*;
public class WortNode {
  private String id;
  private boolean automToken=false;

  private ArrayList<FidalNode> fidalChildren;
  private ArrayList <String> strukturIds;
  private ArrayList <String> specDivIds;
  private ArrayList<String> quotIds;
  private ArrayList<String> tokenIds;
  private String pbString;

  private Color col;
  private String comm;
  private int error;
  private String neRef;
  public WortNode(String id){
	  this.id=id;
	
	  pbString="";
	  fidalChildren=new ArrayList<FidalNode>();
	  strukturIds=new ArrayList<String>();
	  quotIds=null;
	  specDivIds=null;
	  tokenIds=null;;
	  col=null;
	  error=0;
	  comm="";
	  neRef="";
  }
  public int getError(){
	  return error;
  }
  public String getPbString() {
	  return pbString;
  }
  public void setPbString(String s) {
	  pbString=pbString+s;
  }
  public void setError(int i){
	  error=i;
  }
  public void setNERef(String s){
	  neRef=s;
  }
  public ArrayList<String> getSpecDivs(){
	  return specDivIds;
  }
  public void setSpecDivs(ArrayList<String> v) {
	   if (specDivIds==null) specDivIds=new ArrayList<String>();
	   for(int i=0;i<v.size();i++)
		   specDivIds.add(v.get(i));
  }
  public void addSpecId(String id) {
	  if (specDivIds==null) specDivIds=new ArrayList<String>();
	  specDivIds.add(id);
  }
  public String getNERef(){
	  return neRef;
  }
  
  public boolean hasErrorNode(int n,int m){
	  int i=0;boolean found=false;
	  while((i<fidalChildren.size())&&(!found)){
		  if (fidalChildren.get(i).getFidalInternLabel(n, m)!=null) i=i+1;
		  else found=true;
		  
	  }
	  return found;
  }
  public WortNode copyWortNode(){
	  ArrayList<FidalNode> f=new ArrayList<FidalNode>();
	  for(int i=0;i<fidalChildren.size();i++)
		  f.add(fidalChildren.get(i).copyFidalNode());
	  WortNode w=new WortNode(id);
	  w.setFidalChildren(f);
	  w.setAutomToken(automToken);
	  w.setStrukturIds(this.getStrukturIds());
		w.setQuotIds(this.getQuotIds());
		w.setTokenIds(this.getTokenIds());
		w.setFColor(col);w.setComment(comm);
		w.setError(error);
		w.setNERef(neRef);
		return w;
  }
  public WortNode(WortNode w){
	  this.id=w.getId();
	  this.automToken=w.getAutomToken();
	  this.setFidalChildren(w.getFidalChildren());
	this.setStrukturIds(w.getStrukturIds());
	this.setQuotIds(w.getQuotIds());
	this.setTokenIds(w.getTokenIds());
	this.col=w.getFcolor();this.comm=w.getCooment();
	this.error=w.getError();
	this.neRef=w.getNERef();
	
  }

  public void deleteTokenID(){
	  String newId="T0>"+id;
	  tokenIds.clear();
	  tokenIds.add(newId);
	  for(int i=0;i<fidalChildren.size();i++){
		  FidalNode f=fidalChildren.get(i);
		  f.resetTokenId(newId);
		  fidalChildren.set(i, f);
	  }
  }
  public void modifyTokenID(int j,String id){
	 LatinLetterNode l=this.getLetters().get(j);
	  for(int i=0;i<fidalChildren.size();i++){
		  FidalNode f=fidalChildren.get(i);
		  for(int k=0;k<f.getTranslitChildren().size();k++)
		       if( (f.getTranslitChildren().get(k).getParent().getNr()==l.getParent().getNr())&&(f.getTranslitChildren().get(k).getNr()==l.getNr())){
		    	   LatinLetterNode let=f.getTranslitChildren().get(k);
		    	   let.setTokenId(id);
		    	   f.setLetter(k,let);
		    	   fidalChildren.set(i, f);
		       }	   
		  
	  }
  }
  
  public JSONObject toJson(){
	  JSONObject o=new JSONObject();
	  o.put("Id",id);
	  o.put("AT", automToken);
	 JSONArray a=new JSONArray();
	 for(int i=0;i<fidalChildren.size();i++)
		  a.put(fidalChildren.get(i).toJson());
	  o.put("FC", a);
	  if(strukturIds!=null){
		  JSONArray str=new JSONArray();
		  for(int i=0;i<strukturIds.size();i++)
			  str.put(strukturIds.get(i).toString());
		 
		  o.put("Sid", str);
	  }
	  
	  if(quotIds!=null)
	  o.put("Qid", new JSONArray(quotIds));
	  if(tokenIds!=null)
	  o.put("Tid", new JSONArray(tokenIds));
	  if(col!=null) o.put("Col", ""+col.getRGB());
	  if(!comm.isEmpty()) o.put("Comm", ""+comm);
	  if(!neRef.isEmpty()) o.put("NE", ""+neRef);
	  return o;
  }
  
  public JSONObject toJson1(int n,int m){
	  JSONObject o=new JSONObject();
	  o.put("Id",id);
	  String fidL=getFidalLabel(n,m);
	  o.put("FID",fidL);
	  String fidLEd=getFidalEd1Label(n,m);
	  o.put("FIDED", fidLEd);
	  String trL=getTranslitLabel(n);
	  o.put("TR", trL);
	 JSONArray a=new JSONArray();
	 for(int i=0;i<fidalChildren.size();i++)
		  a.put(fidalChildren.get(i).toJson1(n,m));
	  o.put("FC", a);
	  if(strukturIds!=null){
		  JSONArray str=new JSONArray();
		  
		  for(int i=0;i<strukturIds.size();i++)
			  str.put(strukturIds.get(i).toString());
		 
		  o.put("Sid", str);
	  }
	  if(quotIds!=null)
	  o.put("Qid", new JSONArray(quotIds));
	  if(tokenIds!=null)
	  o.put("Tid", new JSONArray(tokenIds));
	  if(!comm.isEmpty()) o.put("Comm", ""+comm);
	  if(!neRef.isEmpty()) o.put("NE", ""+neRef);
	  return o;
  }
  
public boolean hasStrukturLevel(int i){
	boolean found=false;

	if (strukturIds!=null){
		int j=0; 
		while((j<strukturIds.size())&&(!found)) {
			if (strukturIds.get(j).substring(0,1).equals(""+i))
				found=true;
			else j=j+1;
		}
	}
	return found;
}
public void addStrId(String s) {
	strukturIds.add(s);
}
public String getStrukturLevel(int i){
	boolean found=false;
    String res="";
	if (strukturIds.size()>0){
		int j=0; 
		while((j<strukturIds.size())&&(!found)) {
			if (strukturIds.get(j).substring(0,1).equals(""+i)) {
				found=true; res=strukturIds.get(j);
			}	
			else j=j+1;
		}
	}
	return res;
}

/*public int getMinimumLevel(){
	int lev=-1;
	if(strukturIds!=null){
		for (int i=0;i<4;i++){
			String tmp=""+(i+1);
			if (!strukturIds.get(tmp).isEmpty()) lev=i+1;
		}
	}
	return lev;
}*/

/*public int getMaximumLevel(){
	int lev=-1;
	if(strukturIds!=null){
		int i=0;boolean found=false;
		while((i<4)&&(!found)){
			String tmp=""+(i+1);
			if (!strukturIds.get(tmp).isEmpty()) {
				found=true;lev=i+1;
			}
			else i=i+1;
		}
	}
	return lev;
}
  */
public boolean getAutomToken(){
	return automToken;
}

public void setFColor(Color c){
	col=c;
}

public String getCooment(){
	return comm;
}
public void setComment(String c){
	comm=c;
}

public Color getFcolor(){
	return col;
}
public void setAutomToken(boolean b){
	automToken=b;
}
  public void setFidalChildren(ArrayList<FidalNode> children){
	  fidalChildren=new ArrayList<FidalNode>();
	  for(int i=0;i<children.size();i++) {
		  fidalChildren.add(children.get(i));
		  fidalChildren.get(i).setParent(this);
	  }
  }
  public void initializeStruktur(){
	  /*strukturIds= new HashMap<String, String>();
	  strukturIds.put("1", "");
	  strukturIds.put("2", "");
	  strukturIds.put("3", "");
	  strukturIds.put("4", "");*/
	  strukturIds=new ArrayList<String>();
	  
  }
  /*public boolean structureAbove(int n){
 
	  if ((!strukturIds.get("1").isEmpty())&&(n>=1)) return false;
	  else if ((!strukturIds.get("2").isEmpty())&&(n>=2)) return false;
  }*/
  public void setStrukturIds(ArrayList<String> str){
	  strukturIds.clear();
	  for(int i=0;i<str.size();i++)
		  strukturIds.add(str.get(i));
  }
  
  /*public void setStrukturIds(Map<String,String> x){
	  if (x!=null){
	  strukturIds= new HashMap<String, String>();
	// for( String key : x.keySet()){
	    // strukturIds.put(key,x.get(key));
	// }//
	  for(int i=1;i<=4;i++){
		  if (x.containsKey(""+i)) strukturIds.put(""+i,x.get(""+i));
		  else strukturIds.put(""+i,"");
	  }
	  }
	  else strukturIds=null;
  }*/
  
  public ArrayList<String> getQuotIds(){
	  return quotIds;
  }
  public void setQuotIds(ArrayList<String> ids){
	  quotIds=new ArrayList<String>();
	  if (ids!=null)
	  for(int i=0;i<ids.size();i++) quotIds.add(ids.get(i));
	  else quotIds=null;
  }
  public void setTokenIds(ArrayList<String> ids){
	  tokenIds=new ArrayList<String>();
	  for(int i=0;i<ids.size();i++) tokenIds.add(ids.get(i));
  }
  public ArrayList<LatinLetterNode> getLetters(){
	  ArrayList<LatinLetterNode> let=new ArrayList<LatinLetterNode>();
	  for(int i=0;i<fidalChildren.size();i++){
		  FidalNode f=fidalChildren.get(i);
		  for(int j=0;j<f.getTranslitChildren().size();j++)
			  let.add(f.getTranslitChildren().get(j));
	  }
	  return let;
  }
  
  public ArrayList<String> getTokenIds(){
	  return tokenIds;
  }
  
  public ArrayList<String> getStrukturIds(){
	  return strukturIds;
  }
  
  public ArrayList<FidalNode> getFidalChildren(){
	  return fidalChildren;
  }
  public void addChild(FidalNode child){
	  child.setParent(this);
	  fidalChildren.add(child);
  }
  public void addChild(int pos,FidalNode child){
	  child.setParent(this);
	  fidalChildren.add(pos,child);
  }
  public void setChild(int pos,FidalNode child){
	  child.setParent(this);
	  fidalChildren.set(pos, child);
  }
  public void removeChild(int i){
	  fidalChildren.get(i).setParent(null);
	  fidalChildren.remove(i);
  }
  public void removeChild(FidalNode s){
	  s.setParent(null);
	  fidalChildren.remove(s);
  }
  public String getId(){
	  return id;
  }
  public String getFidalLabel(int n, int m){
	  String s="";
	  if (fidalChildren.size()>0){
	  for(int i=0;i<fidalChildren.size();i++){
		  s=s+fidalChildren.get(i).getFidalLabel(n,m);
	  }}
	  else s=id;
	  return s;
  }
  public String getFidalEdLabel(int n, int m){
	  String s="";
	  if (fidalChildren.size()>0){
	  for(int i=0;i<fidalChildren.size();i++){
		  if (!fidalChildren.get(i).getPBB().isEmpty())
			  if (fidalChildren.get(i).getPBB().substring(0, 1).equals("-"))
			  s=fidalChildren.get(i).getPBB().substring(1);
		  String b=""; String a="";
		  if(fidalChildren.get(i).getEdAnnot()!=null){
			  
		  
			  if (fidalChildren.get(i).getEdAnnot().getSpecificTag("ed")!=null)
				  if (fidalChildren.get(i).getEdAnnot().getSpecificTag("ed").getName().equals("ed")){
				  for(int j=0;j<fidalChildren.get(i).getEdAnnot().getSpecificTag("ed").getAttrList().size();j++){
					  if (fidalChildren.get(i).getEdAnnot().getSpecificTag("ed").getAttrList().get(j).getName().equals("b"))
						  b=b+fidalChildren.get(i).getEdAnnot().getSpecificTag("ed").getAttrList().get(j).getValue();
					  if (fidalChildren.get(i).getEdAnnot().getSpecificTag("ed").getAttrList().get(j).getName().equals("a"))
						 a=a+fidalChildren.get(i).getEdAnnot().getSpecificTag("ed").getAttrList().get(j).getValue();
				  }
		  }
		  }
		  if (fidalChildren.get(i).getLB1()!=null)
			  s=s+fidalChildren.get(i).getLB1();
			  //+"<font color='blue'>"+StringEscapeUtils.escapeHtml(b)+"</font>"+fidalChildren.get(i).getFidalLabel(n,m)+"<font color='blue'>"+StringEscapeUtils.escapeHtml(a)+"</font>" + fidalChildren.get(i).getEdEndSymbol();
		  if (fidalChildren.get(i).getPosLB()==0)
		  s=s+"<font color='blue'>"+StringEscapeUtils.escapeHtml(b)+"</font>"+fidalChildren.get(i).getFidalLabel(n,m)+"<font color='blue'>"+StringEscapeUtils.escapeHtml(a)+"</font>" + fidalChildren.get(i).getEdEndSymbol()+fidalChildren.get(i).getLB();
		  else
			  s=s+"<font color='blue'>"+StringEscapeUtils.escapeHtml(b)+"</font>"+fidalChildren.get(i).getFidalLabel(n,m)+"<font color='blue'>"+StringEscapeUtils.escapeHtml(a)+"</font>" + fidalChildren.get(i).getLB()+fidalChildren.get(i).getEdEndSymbol();
		  if (!fidalChildren.get(i).getPBA().isEmpty())
			  if (fidalChildren.get(i).getPBA().substring(0, 1).equals("+"))
			  s=s+ fidalChildren.get(i).getPBA().substring(1);
		
	  }}
	  else s=id;
	  return s;
  }
  public String getFidalEd1Label(int n, int m){
	  String s="";
	  if (fidalChildren.size()>0){
	  for(int i=0;i<fidalChildren.size();i++){
		  String b=""; String a="";
		  if(fidalChildren.get(i).getEdAnnot()!=null){
			  
		  
			  if (fidalChildren.get(i).getEdAnnot().getSpecificTag("ed")!=null)
				  if (fidalChildren.get(i).getEdAnnot().getSpecificTag("ed").getName().equals("ed")){
				  for(int j=0;j<fidalChildren.get(i).getEdAnnot().getSpecificTag("ed").getAttrList().size();j++){
					  if (fidalChildren.get(i).getEdAnnot().getSpecificTag("ed").getAttrList().get(j).getName().equals("b"))
						  b=b+fidalChildren.get(i).getEdAnnot().getSpecificTag("ed").getAttrList().get(j).getValue();
					  if (fidalChildren.get(i).getEdAnnot().getSpecificTag("ed").getAttrList().get(j).getName().equals("a"))
						 a=a+fidalChildren.get(i).getEdAnnot().getSpecificTag("ed").getAttrList().get(j).getValue();
				  }
		  }
		  }
		  if (fidalChildren.get(i).getPosLB()==0)
		  s=s+StringEscapeUtils.escapeHtml(b)+fidalChildren.get(i).getFidalLabel(n,m)+StringEscapeUtils.escapeHtml(a)+ fidalChildren.get(i).getEdEndSymbol()+fidalChildren.get(i).getLB();
		  else
			  s=s+StringEscapeUtils.escapeHtml(b)+fidalChildren.get(i).getFidalLabel(n,m)+StringEscapeUtils.escapeHtml(a) + fidalChildren.get(i).getLB()+fidalChildren.get(i).getEdEndSymbol();
  
	  }}
	  else s=id;
	  return s;
  }
  public String getFidalInternLabel(int n, int m){
	  String s="";
	  if (fidalChildren.size()>0){
	  for(int i=0;i<fidalChildren.size();i++){
		  s=s+fidalChildren.get(i).getFidalInternLabel(n,m);
	  }}
	  else s=id;
	  return s;
  }
  
  public String getTranslitLabel(int n){
	  String s="";
	  if (fidalChildren.size()>0){
	  for(int i=0;i<fidalChildren.size();i++){
		  if(i>0){
			  int x=fidalChildren.get(i-1).getTranslitChildren().size()-1;
			 // System.out.println("Pointer Token "+ fidalChildren.get(i).getTranslitChildren().get(0).getEntireTranslit()+" "+ fidalChildren.get(i).getTranslitChildren().get(0).getTokenId());
			  if (!fidalChildren.get(i).getTranslitChildren().get(0).getTokenId().equals(fidalChildren.get(i-1).getTranslitChildren().get(x).getTokenId()))
				  s=s+"-"+fidalChildren.get(i).getTranslitLabel(n);
			  else s=s+fidalChildren.get(i).getTranslitLabel(n);
		  }
			  
		  else s=s+fidalChildren.get(i).getTranslitLabel(n);
	  }
	  }
	  else s=id;
	  if (n==0)
	  return s;
	  else return new StringBuilder(s).reverse().toString();
  }
  
  public String getTranslitInternLabel(int n){
	  String s="";
	  if (fidalChildren.size()>0){
	  for(int i=0;i<fidalChildren.size();i++){
		 s=s+fidalChildren.get(i).getTranslitLabel(n);
	  }
	  }
	  else s=id;
	  if (n==0)
		  return s;
		  else return new StringBuilder(s).reverse().toString();
  }
  public String toString(){
	  String s="";
	  if (fidalChildren.size()>0){
	  for(int i=0;i<fidalChildren.size();i++){
		  s=s+fidalChildren.get(i).toString();
	  }}
	  else s=id;
	  return s;
  }
  
}
