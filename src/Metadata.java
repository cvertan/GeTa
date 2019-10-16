import java.util.ArrayList;
/**
 * builds the object holding the metadata of the Text
 */

import org.json.JSONArray;
import org.json.JSONObject;
public class Metadata {
private String id;
private String et;
private String en;
private String annotator;
private String linkZ;
private String comment;
private String toolVer;
private int typeVoc;
private int typeScr;
private int noGrUnit;
private int noTokens;
private String toolCreator;
private ArrayList<String> divNames;
private String dateDoc;

	public Metadata(String id, String et,String en, String ann,  String c, String linkZ) {
		divNames=new ArrayList<String>();
		this.id=id;
		this.et=et;
		this.en=en;
		this.annotator=ann;
		this.toolVer="";
		this.comment=c;
		this.linkZ=linkZ;
		this.typeVoc=0;
		this.typeScr=0;
		this.toolCreator="Cristina Vertan";
		this.noGrUnit=0;
		this.noTokens=0;
		this.dateDoc="";
	}
	public JSONObject toJSON1() {
		 JSONObject o=new JSONObject();
		 o.put("ID","https://betamasaheft.eu/api/"+ this.id+"/tei");
		 o.put("ANNOT", annotator);
		 o.put("SOFT", toolVer + "/ Creator" + toolCreator);
		 o.put("NAME", et + "/" +en);
		 o.put("Comm", comment);
		    o.put("DATE", dateDoc);
		    o.put("EDITION", linkZ);
		    o.put("TR", ""+typeVoc);
		    o.put("SCR", typeScr);
		    if(divNames!=null){
				  JSONArray str=new JSONArray();
		    for(int i=0;i<divNames.size();i++)
				  str.put(divNames.get(i).toString());
			 
			  o.put("PARTS", str);
		    }
		 return o;
	}
	
	public JSONObject toJSON() {
		 JSONObject o=new JSONObject();
		 o.put("ID", this.id);
		 o.put("ANNOT", annotator);
		 o.put("NAMEGEZ", et);
		 o.put("NAMEEN", en);
		 o.put("EDITION", linkZ);
		 o.put("Comm", comment);
		 return o;
	}
	public ArrayList<String> getDivNames(){
		return divNames;
	}
	public void setDateExp(String s) {
		dateDoc=s;
	}
	public void setTypeDoc(int s1,int s2) {
		typeVoc=s1;
		typeScr=s2;
	}
	public void setNoGrUnits(int n) {
		noGrUnit=n;
	}
	public void setToolVer(String n) {
		toolVer=n;
	}
	public String getId() {
		return id;
	}
	public String getTitleET() {
		return et;
	}
	public String getTitleEN() {
		return en;
	}
	public String getAnnotator() {
		return annotator;
	}
	public String getLinkZotero() {
		return linkZ;
	}
	public String getComment() {
		return comment;
	}
	public void setNoTokens(int n) {
		noTokens=n;
	}
}
