import java.util.ArrayList;
import org.json.*;
/**
 * builds a Token as a collection of {@link LatinLetterNode}
 * @author Cristina Vertan
 *
 */
public class Token{
	/**
	 * unique id of the token
	 */
	private String id;
	/**
	 * true if the token was created automatically
	 * used by {@link TranslitListmodel}
	 */
	private boolean autom;
	/**
	 * list of the {@link LatinLetterNode} components
	 */
	private ArrayList<LatinLetterNode> lettersComponents;
	/**
	 * the morphological annotation
	 * @see Annotation
	 */
	private Annotation morphoAnnotation;
	/**
	 * id of the NE {@link NamedEntity} containing this token
	 */
	private String neRef;
	public Token(String id, boolean autom,ArrayList<LatinLetterNode>letters){
		this.id=id;
		this.autom=autom;
		lettersComponents=new ArrayList<LatinLetterNode>();
		if(letters !=null)
		    for(int i=0;i<letters.size();i++) lettersComponents.add(letters.get(i));
		else lettersComponents=null;
		morphoAnnotation=null;
		neRef="";
	}
	
	public Token (Token t){
		this.id=t.getId();
		this.autom=t.getAutom();
		this.setLetters(t.getLetters());
		this.setMorphoAnnotation(t.getMorphoAnnotation());
		this.setNERef(t.getNERef());
	}
	 public void setNERef(String s){
		  neRef=s;
	  }
	  public String getNERef(){
		  return neRef;
	  }
	public Token copyToken(){
		ArrayList<LatinLetterNode> l=new ArrayList<LatinLetterNode>();
		if(lettersComponents !=null){
		for(int i=0;i<lettersComponents.size();i++){
			l.add(lettersComponents.get(i).copyLatinLetterNode());
		}}
		Token t=new Token(id,autom,l);
		if(morphoAnnotation!=null)
		t.setMorphoAnnotation(morphoAnnotation.copyAnnotation());
		t.setNERef(neRef);
		return t;
	}
	public boolean getAutom(){
		return autom;
	}
	public JSONObject toJson(){
		JSONObject o =new JSONObject();
		o.put("Id", id);
		o.put("A", ""+autom);
		/*JSONArray a =new JSONArray();
		for(int i=0;i<lettersComponents.size();i++){
			a.put(lettersComponents.get(i).toJson());
		}
		o.put("LC", a);*/
		if(morphoAnnotation!=null)
		    o.put("M", morphoAnnotation.toJson());
		if(neRef!=null)
		o.put("NEId", neRef);
		return o;
	}
	public JSONObject toJson1(){
		JSONObject o =new JSONObject();
		o.put("Id", id);
		String s=getLabel();
		o.put("TOKL", s);
		/*JSONArray a =new JSONArray();
		for(int i=0;i<lettersComponents.size();i++){
			a.put(lettersComponents.get(i).toJson());
		}
		o.put("LC", a);*/
		if(morphoAnnotation!=null)
		    o.put("M", morphoAnnotation.toJson1());
		if(neRef!=null)
		o.put("NEId", neRef);
		return o;
	}
	public String getLabel(){
		String s="";
		if(lettersComponents !=null)
		for(int i=0;i<lettersComponents.size();i++){
			s=s+lettersComponents.get(i).getEntireTranslit();
		}
		return s;
	}
	public boolean isAutom(){
		return autom;
	}
	public void setAutom(boolean b){
		autom=b;
	}
	public ArrayList<LatinLetterNode> getLetters(){
		return lettersComponents;
	}
	public String getId(){
		return id;
	}
    public Annotation getMorphoAnnotation(){
    	return morphoAnnotation;
    }
  
    
    public void setMorphoAnnotation(Annotation m){
    	morphoAnnotation=m;
    }
    
   
    
 
    public void setLetters(ArrayList<LatinLetterNode> let){
    	lettersComponents=new ArrayList<LatinLetterNode>();
    	for(int i=0;i<let.size();i++) lettersComponents.add(let.get(i));
    }
   
    public void addLetter(LatinLetterNode l, int pos){
    	lettersComponents.add(pos,l);
    }
    public void setLetter(LatinLetterNode l, int pos){
    	lettersComponents.set(pos, l);
    }
    public void removeLetter(int pos){
    	lettersComponents.remove(pos);
    }
    public void removeLetter(LatinLetterNode s){
    	lettersComponents.remove(s);
    }
}
