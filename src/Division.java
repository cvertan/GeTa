import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 * builds a Division=text structure
 * @author Cristina Vertan
 *
 */
public class Division {
	/**
	 * graphical unit at the begin of the division
	 */
	private String wbegin;
	/**
	 * graphical unit at the end of the division
	 */
	private String wend;
	/**
	 * Division id
	 */
	private String id;
	/**
	 * level of the division (minimum 1 maximum 4
	 */
	private int level;
	/**
	 * internal number of the division ; calculated automatically
	 */
	private int internalnr;
	/**
	 * number of the division given by the use; default identical with the internal number 
	 */
	private String nr;
	/**
	 * comment on the division inserted by the user
	 */
	private String comment;
	/**
	 * name of the division
	 */
	private String name;
	/**
	 * creator of the division, default is the edition
	 * @see Metadata
	 */
	private String creator;
	/**
	 * genre of the division, selected from a closed list
	 */
	private String genre;
	/**
	 * id of the parent of the division; null in case of divisions with level 1; the level of the parent must be smaller as the own level
	 */
	private String parent;
	/** 
	 * indicates if the internal {@link #internalnr}  and the given number {@link #nr} are differnet
	 */
	private boolean changenr;
	/**
	 * ids of the children divisions; thes emust have a level greater as the current division
	 */
	private ArrayList<String> children=new ArrayList<String>();
	 
	/**
	 *  builds a division
	 * @param b a string: the  id of the first graphical unit
	 * @param e a string: the id of the last graphical unit
	 * @param id a string: the unique id of the division
	 * @param l an integer: the level of the division
	 * @param nri an integer:the internal number
	 * @param b1 : true if the division was created automatically
	 */
	public Division(String b,String e,String id, int l, int nri,boolean b1) {
		this.wbegin=b;
		this.wend=e;
		this.id=id;
		this.level=l;
		this.internalnr=nri;
		this.changenr=b1;
		this.nr="";
		this.comment="";
		this.genre="";
		this.parent="";
		children=new ArrayList<String>();
	}
	public boolean getChange() {
		return changenr;
	}
	public void setChangeNr(boolean b) {
		this.changenr=b;
	}
	public Division copyDivision() {
		Division d=new Division(wbegin,wend,id,level,internalnr,changenr);
		d.setNr(nr);
		d.setGenre(genre);
		d.setParent(parent);
		d.setComment(comment);
		d.setName(name);
		for (int i=0;i<children.size();i++)
		d.getChildren().add(children.get(i));
		d.setCreator(creator);
		
		return d;
	}
	public JSONObject toJson() {
		JSONObject o=new JSONObject();
		  o.put("Id",id);
		  o.put("WB", wbegin);
		  o.put("WE", wend);
		  o.put("NRI", ""+internalnr);
		  o.put("NR", nr);
		  o.put("LE", ""+level);
		  o.put("G",genre);
		  o.put("C", comment);
		  o.put("DP", parent);
		  o.put("NA", name);
		  o.put("CR", creator);
		 JSONArray a=new JSONArray();
		 for(int i=0;i<children.size();i++)
			  a.put(children.get(i));
		  o.put("DC", a);
		  return o;
	}
	public String getWbegin() {
		return wbegin;
	}
	public void setWbegin(String w) {
		wbegin=w;
	}
	public void addChildDiv(String s) {
		children.add(s);
	}
	
	public String getWend() {
		return wend;
	}
	public void setWend(String w) {
		wend=w;
	}
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String w) {
		creator=w;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int l) {
		level=l;
	}
	public void setInternalNr(int n) {
		internalnr=n;
	}
	
	
	public int getInternalNr(){
		return internalnr;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String c) {
		comment=c;
	}
	public String getName() {
		return name;
	}
	public void setName(String c) {
		name=c;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String g) {
		genre=g;
	}
	
	public String getNr() {
		return nr;
	}
	public void setNr(String n) {
		nr=n;
	}
	public String getId() {
		return id;
	}
	public void setId(String n) {
		id=n;
	}
	
	public String getParent() {
		return parent;
	}
	public void setParent(String n) {
		parent=n;
	}
	public ArrayList<String> getChildren(){
		return children;
	}
	public void setChildren(ArrayList<String> c) {
		children.clear();
		for(int i=0;i<c.size();i++)
			children.add(c.get(i));
	}
}
