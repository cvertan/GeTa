import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
public class RefWord {
	private String wid;
	private ArrayList<String> tid;
	public RefWord(String wid, ArrayList<String> tid){
		this.wid=wid;
		this.tid=new ArrayList<String>();
		for(int i=0;i<tid.size();i++)
			this.tid.add(tid.get(i));
	}

	public String getWID(){
		return wid;
	}
	public ArrayList<String> getTIDs(){
		return tid;
	}
	public void setWID(String x){
		wid=x;
	}
	public void setTIDs(ArrayList<String> x){
		this.tid.clear();
		for(int i=0;i<x.size();i++)
			this.tid.add(x.get(i));
	}
	public int getNrTokensNE(){
		return tid.size();
	}
	public JSONObject toJson(){
		JSONObject o =new JSONObject();
		  o.put("WId", wid);
		  JSONArray a=new JSONArray();
		  for(int i=0;i<tid.size();i++)
	          a.put(tid.get(i));
		  o.put("TID", a);
		  return o;
	}
	
}
