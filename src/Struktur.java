import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.json.*;
public class Struktur {
  private String id;
  private int level;
  private String worterIdStart;
  private String worterIdStop;
  private Struktur parent;
  private Map<String, ArrayList<String>> children= new HashMap<String, ArrayList<String>>();
  private ArrayList<Attribut> features;
  
  public Struktur(String id, int level, String widStart, String widStop, ArrayList<Attribut> s){
	  this.id=id;
      this.level=level;
      this.worterIdStart=widStart;
      this.worterIdStop=widStop;
      features=new ArrayList<Attribut>();
      for (int i=2; i<=4;i++){
    	  children.put(""+i,new ArrayList<String>());
      }
      for (int i=0;i<s.size();i++)
          features.add(s.get(i));
     
     
  }
 
  public Struktur  copyStruktur(){
	  ArrayList<Attribut> a= new ArrayList<Attribut>();
	  for(int i=0;i<features.size();i++)
		  a.add(new Attribut(features.get(i).getName(),features.get(i).getValue()));
	  Struktur ns=new Struktur(id,level,worterIdStart,worterIdStop,a);
	  ns.setParent(parent.copyStruktur());
	  ArrayList<Struktur> c=new ArrayList<Struktur>();
	  
	  for (int i=2;i<=4;i++){
		  ArrayList<String> st=new ArrayList<String>();
		  if (children.get(""+i).size()>0){
			  for(int j=0;j<children.get(""+i).size();j++){
				  st.add(children.get(""+i).get(j));
			  }
		  }
		  ns.getChildren().put(""+i,st);
	  }
	
	  return ns;
  }
 
 
  public Struktur getParent(){
	  return parent;
  }
  public void setParent(Struktur s){
	  parent=s.copyStruktur();
  }

  
  public String getLevelNr(){
	 int i=0; boolean found=false;
	 String wert="";
	  while((i<features.size())&&(!found)){
		  if (features.get(i).getName().equals("number")){
			  wert=features.get(i).getValue();
			  found=true;
		  }
		  else i=i+1;
	  }
	  return wert;
  }
  public Map<String,ArrayList<String>> getChildren(){
	  return children;
  }
  public JSONObject toJson(){
	  JSONObject o=new JSONObject();
	  o.put("Id", id);
	  o.put("Le", ""+level);
	  o.put("Wstart", ""+worterIdStart);
	  o.put("Wstop", ""+worterIdStop);
	//  o.put("Att", features.toJson());
	  JSONArray a=new JSONArray();
	  for(int i=0;i<features.size();i++)
		  a.put(features.get(i).toJson());
	  o.put("Att", a);
	  return o;
  }
  public ArrayList<Attribut> getStrukturAttributes(){
	  return features;
  }
  
  public String getValueForName(String s){
	  int i=0; boolean found =false;
	  String tmp=null;
	  while((i<features.size())&&(!found)){
		  if (features.get(i).getName().equals(s)){
			  found=true; tmp=features.get(i).getValue();
			  
		  }
		  else i=i+1;
	  }
	  return tmp;
  }
  public String getId(){
	  return id;
  }
  public int getLevel(){
	  return level;
  }
  public void setLevel(int l){
	  level=l;
  }
  public String getWordIdStart(){
	  return worterIdStart;
  }
  public String getWordIdStop(){
	  return worterIdStop;
  }
  
  public void setWordIdStart(String s){
	   worterIdStart=s;
  }
  
  public void setWordIdStop(String s){
	   worterIdStop=s;
 }
  public void setStructAttrib(ArrayList<Attribut> s){
	  features=new ArrayList<Attribut>();
	  for (int i=0;i<s.size();i++)
		  features.add(s.get(i));
  }
  
  
  public ArrayList<Attribut> getFeatAnnot(){
	  return features;
  }
  public void addFeature(Attribut a, int pos){
	  features.add(pos,a);
  }
  public void setFeature(Attribut a, int pos){
	  features.set(pos, a);
  }
  public void removefeature(int pos){
	  features.remove(pos);
  }
  public void removeFeature(String s){
	  features.remove(s);
  }
       
}
