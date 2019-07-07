import java.util.ArrayList;
import org.json.*;
public class NamedEntity{
  private String id;
  private String typ;
  private ArrayList<RefWord> refw;
  private ArrayList<Attribut> feat;
  private boolean imp;
  private boolean autom;
  private String refid;
  /**
   * 
   * @param id id of the NE ; id is given by the GeTa system
   * @param refid  reference id on the betametsaheft server
   * @param typ type of NE, a string
   * @param rw reference entry on the beta metsaheft 
   * @param f features of the NE
   * @param imp
   * @param autom true if the NE was built automatically
   */
  public NamedEntity(String id, String refid,String typ, ArrayList<RefWord>rw, ArrayList<Attribut> f,boolean imp,boolean autom){
	  this.id=id;
	  this.refid=refid;
      this.typ=typ;
      this.imp=imp;
      this.autom=autom;
       refw=new ArrayList<RefWord>();
       for (int i=0;i<rw.size();i++){
    	   refw.add(new RefWord(rw.get(i).getWID(),rw.get(i).getTIDs()));
       }
       feat=new ArrayList<Attribut>();
       if(f !=null)
         for(int i=0;i<f.size();i++)
        	 feat.add(f.get(i));
           
  }
  public ArrayList<RefWord> getRefIDS(){
	  return refw;
  }
  public ArrayList<Attribut> getAttr(){
	  return feat;
  }
  public String getrefId(){
	  return refid;
  }
public void modifyTokenIds(String id, ArrayList<String> ref){
	int i=0; boolean found=false;
	while((i<refw.size())&&(!found)){
		if( refw.get(i).getWID().equals(id)){
			found =true;
			refw.get(i).getTIDs().clear();
			for(int j=0;j<ref.size();j++)
				refw.get(i).getTIDs().add(ref.get(j));
		}
		else i=i+1;
	}
	
}
  public JSONObject toJson(){
	  JSONObject o =new JSONObject();
	  o.put("Id", id);
	  o.put("T", typ);
	  o.put("imp", imp);
	  o.put("aut", autom);
	  o.put("R", refid);
	  JSONArray a=new JSONArray();
	  for(int i=0;i<refw.size();i++)
          a.put(refw.get(i).toJson());
	  o.put("ref", a);
	  if (feat !=null){
	      a=new JSONArray();
	         for(int i=0;i<feat.size();i++)
		            a.put(feat.get(i).toJson());
	  }
	  o.put("feat", a);
	  return o;
  }
  public JSONObject toJson1(){
	  JSONObject o =new JSONObject();
	  o.put("Id", id);
	  o.put("T", typ);
	  o.put("R", "https://betamasaheft.eu/api/"+refid+"/tei");
	  //o.put("imp", imp);
	  //o.put("aut", autom);
	  JSONArray a=new JSONArray();
	  for(int i=0;i<refw.size();i++)
          a.put(refw.get(i).toJson());
	  o.put("ref", a);
	  if (feat !=null){
	      a=new JSONArray();
	         for(int i=0;i<feat.size();i++)
		            a.put(feat.get(i).toJson());
	  }
	  o.put("feat", a);
	  return o;
  }
  public String getId(){
	  return id;
  }
  public String getTyp(){
	  return typ;
  }
  public void setTyp(String l){
	  typ=l;
  }
public boolean hasAbbreviation() {
	boolean found=false;
	if (feat.size()>0) {
		int k=0;
		while((k<feat.size())&&(!found)) {
			if((feat.get(k).getName()=="Abrev")&&(feat.get(k).getValue()=="yes")) found=true;
			else k=k+1;
		}
		
	}
	return found;
}
    public String giveAttributes() {
    	String s="";
    	for(int i=0;i<feat.size();i++) {
    		if (!feat.get(i).getName().equals("Abrev")) s=s+feat.get(i).getName()+ "   "+feat.get(i).getValue()+"\n";
    	}
    	if (!s.isEmpty())s=s.substring(0,s.length()-1);
    	return s;
    }
}
