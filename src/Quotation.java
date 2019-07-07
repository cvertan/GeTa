import java.util.ArrayList;
import org.json.*;
public class Quotation {
  private String id;
  private ArrayList<WortNode> worter;
  private Annotation quot;
  
  public Quotation(String id, ArrayList<WortNode>w, Annotation s){
	  this.id=id;
      quot=s;
      w=new ArrayList<WortNode>();
      for(int i=0;i<w.size();i++) worter.add(w.get(i));
  }
  
  public JSONObject toJson(){
	  JSONObject o =new JSONObject();
	  o.put("Id", id);		  
      JSONArray a=new JSONArray();
      for(int i=0;i<worter.size();i++){
    	  a.put(worter.get(i).toJson());
      }
      o.put("W", a);
      o.put("Ann", quot.toJson());
      return o;
  }
  
  public Annotation getQuotAnnot(){
	  return quot;
  }
  public String getId(){
	  return id;
  }
 

  public ArrayList<WortNode> getWords(){
	  return worter;
  }
  public void setQuotationAnnot(Annotation s){
	  quot=s;
  }
  public Annotation getQuotationAnnot(){
	  return quot;
  }
  public void addWort(WortNode w, int pos){
	  worter.add(pos,w);
  }
  public void setWort(WortNode w, int pos){
	  worter.set(pos, w);
  }
  public void removeWort(int pos){
	  worter.remove(pos);
  }
  public void removeWort(WortNode s){
	  worter.remove(s);
  }
       
}
