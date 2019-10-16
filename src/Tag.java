import java.util.ArrayList;
import org.json.*;
/**
 * constructs a Tag object which is composed of a string (the name of the tag) and a list of features in form of attributes {@link Attribut}
 * @author Cristina Vertan
 *
 */
public class Tag {
	/**
	 * name of the Tag (e.g. Noun)
	 */
	private String name;
	/**
	 * list of features related to the tag ; each feature is an {@link Attribut} oject
	 */
	private ArrayList<Attribut> attrList;
	/**
	 * builts the Tag object; initially the least of features is empty
	 * @param name the name of the Tag
	 */
	public Tag(String name){
		this.name=name;
		attrList=null;
	}
	/**
	 * creates a copy of the current Tag object
	 * @return a Tag {@link Tag} object
	 */
	public Tag copyTag(){
		Tag t=new Tag(name);
		ArrayList<Attribut> a=new ArrayList<Attribut>();
		if (attrList!=null)
			for (int i=0;i<attrList.size();i++)
				a.add(attrList.get(i).copyAttribut());
		t.setAttrList(a);
		return t;
	}
	/**
	 * builds a Tag object given a name of the tag and its list of features
	 * @param name name of the tag
	 * @param attr list of features of the tag
	 */
	public Tag(String name, ArrayList<Attribut> attr){
		this.name=name;
		attrList=new ArrayList<Attribut>();
		for(int i=0;i<attr.size();i++){
			attrList.add(attr.get(i));
		}
	}
	/**
	 * serializes the Tag object in a JSON object
	 * @return a JSON object
	 */
	public JSONObject toJson(){
		JSONObject o=new JSONObject();
		o.put("NT", name);
		JSONArray a=new JSONArray();
		if(attrList !=null){
		   for(int i=0;i<attrList.size();i++){
			   a.put(attrList.get(i).toJson());
		}
		o.put("AL", a);}
		return o;
	}
	/**
	 * serializes the tag to a JSON object for the ANNIS-Export.
	 * the difference to {@link #toJson()} consists in the usage of {@link Attribut#toJson1()}
	 * @return a JSON object
	 */
	public JSONObject toJson1(){
		JSONObject o=new JSONObject();
		o.put("NT", name);
		JSONArray a=new JSONArray();
		if(attrList !=null){
		   for(int i=0;i<attrList.size();i++){
			   a.put(attrList.get(i).toJson1());
		}
		o.put("AL", a);}
		return o;
	}
	/**
	 * returns the name of the tag
	 * @return #name
	 */
	public String getName(){
		return name;
	}
	/**
	 * assigns a new value for the tag's name
	 * @param s the new name for the tag
	 */
	public void setName(String s){
		name=s;
	}
	/**
	 * returns the list of features of the tag in form of a list of {@link Attribut}
	 * @return #attrList
	 */
	public ArrayList<Attribut> getAttrList(){
		return attrList;
	}
	/**
	 * assigns a new list of features to the tag
	 * @param attr the list of features  i.e. a list of {@link Attribut}
	 */
	public void setAttrList(ArrayList<Attribut> attr){
		if (attrList !=null) attrList.clear();
		else attrList=new ArrayList<Attribut>();
		for(int i=0;i<attr.size();i++){
			attrList.add(attr.get(i));
		}
	}
	/**
	 * removes from the list of features the one with the name of the feature  which equals the prameter s
	 * @param s the name of the Attribut object ({@link Attribut#getName()})to be removed from the list of features {@link #attrList}
	 */
	public void removeAttribute(String s){
		if (attrList !=null) {
			boolean found=false; int i=0;
			while((i<attrList.size())&&(!found)){
				if (attrList.get(i).getName().equals(s)){
					found=true;
					attrList.remove(i);
				}
				else i=i+1;
			}
		}
	}
}
