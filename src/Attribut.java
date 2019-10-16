import org.apache.commons.lang.StringEscapeUtils;
import org.json.*;
/**
 * Builts an attribute as a pair (Name, Value) e.g. (gender, masculine)
 * @author Cristina Vertan
 *@see Tag
 */
public class Attribut {
	/**
	 *  name of the attribute e.g. gender
	 */
	private String name;
	/**
	 * value of the attribute e.g. masculine
	 */
	private String value;
	
	/**
	 * Costructor of the attribute
	 * @param name the name to the attribute
	 * @param value the value of the attribute
	 */
	public Attribut(String name, String value){
		this.name=name;
		this.value=value;
	}
	/**
	 * generates an {@link Attribut} object 
	 * @return Attribut object
	 */
	public Attribut copyAttribut(){
		Attribut a=new Attribut(name,value);
		return a;
	}
	/**
	 * serializes an Attribut object as a JSON object
	 * @return a JSON object
	 */
	public JSONObject toJson(){
		JSONObject o=new JSONObject();
		o.put("N", name);
		o.put("V", value);
		return o;
	}
	/**
	 * serializes the {@link Attribut} objects with {@link #name} gender or number for the ANNIS -Export
	 * <p> This is necessary in order to generate a single string from the combinations gender-pattern, gender-syntax, gender-nature respectively number-syntax number-pattern
	 * @return a JSON object
	 */
	public JSONObject toJson1(){
		JSONObject o=new JSONObject();
		
		String nameP="";
		String nameS="";
        String nameN="";
        String name1="";
		//"NPS","NS","NP","PS" "N", "P","S";
		//gender pattern; gender nature; gender syntax; gender
        if (name.equals("gender")){
		if (value.indexOf("P")>0) nameP="gender pattern";
		if (value.indexOf("S")>0) nameS="gender syntax";
		if (value.indexOf("N")>0) nameN="gender nature";
		if((value.indexOf("P")<=0)&&(value.indexOf("N")<=0)&&(value.indexOf("S")<=0)) name1="gender";
        
        String valueP="";
		if (nameP.length()>0){
			if ((value.indexOf("feminineP")>=0)||(value.indexOf("femininePS")>=0)||(value.indexOf("feminineNP")>=0)||(value.indexOf("feminineNPS")>=0))
		    valueP=valueP+"Feminine";
			if ((value.indexOf("masculineP")>=0)||(value.indexOf("masculinePS")>=0)||(value.indexOf("masculineNP")>=0)||(value.indexOf("masculineNPS")>=0))
			    if (valueP.length()>0) valueP=valueP+" Masculine";
			    else valueP=valueP+"Masculine";
		//o.put(nameP, valueP);
		o.put("N1", nameP);
		o.put("V1", valueP);
		}
		String valueS="";
		if (nameP.length()>0){
			if ((value.indexOf("feminineS")>=0)||(value.indexOf("femininePS")>=0)||(value.indexOf("feminineNS")>=0)||(value.indexOf("feminineNPS")>=0))
		    valueS=valueS+"Feminine";
			if ((value.indexOf("masculineS")>=0)||(value.indexOf("masculinePS")>=0)||(value.indexOf("masculineNS")>=0)||(value.indexOf("masculineNPS")>=0))
			    if (valueS.length()>0) valueS=valueS+" Masculine";
			    else valueS=valueS+"Masculine";
			o.put("N2", nameS);
			o.put("V2", valueS);
		}
		
		String valueN="";
		if (nameN.length()>0){
			if ((value.indexOf("feminineN")>=0)||(value.indexOf("feminineNS")>=0)||(value.indexOf("feminineNP")>=0)||(value.indexOf("feminineNPS")>=0))
		    valueN=valueN+"Feminine";
			if ((value.indexOf("masculineN")>=0)||(value.indexOf("masculineNS")>=0)||(value.indexOf("masculineNP")>=0)||(value.indexOf("masculineNPS")>=0))
			    if (valueN.length()>0) valueN=valueN+" Masculine";
			    else valueN=valueN+"Masculine";
			o.put("N3", nameN);
			o.put("V3", valueN);
		}
		String value1="";
		if (name1.length()>0){
			if ((value.indexOf("feminineS")<0)&&(value.indexOf("femininePS")<0)&&(value.indexOf("feminineNS")<0)&&(value.indexOf("feminineNPS")<0)&&((value.indexOf("feminine")>=0)||(value.indexOf("Feminine")>=0)))
		    value1=value1+"Feminine";
			if ((value.indexOf("masculineS")<0)&&(value.indexOf("masculinePS")<0)&&(value.indexOf("masculineNS")<0)&&(value.indexOf("masculineNPS")<0)&&((value.indexOf("masculine")>=0)||(value.indexOf("Masculine")>=0)))
			    if (value1.length()>0) valueS=valueS+" Masculine";
			    else value1=value1+"Masculine";
			if ((value.indexOf("communis")>=0)||(value.indexOf("Communis")>=0)) value1="Communis";
			if ((value.indexOf("unmarked")>=0)||(value.indexOf("Unmarked")>=0)) value1="Unmarked";
			o.put("N", name1);
			o.put("V", value1);
		}
        }
        else if (name.equals("number")){
        	//SingularS ; PluralS; unmarkedS
        	//PluralIntP; PluralExtP; PluralPlP; unmarkedP
        	if((value.indexOf("rS")>0)||(value.indexOf("lS")>0)||(value.indexOf("dS")>0)||(value.indexOf("rP")>0)||(value.indexOf("IntP")>0)||(value.indexOf("ExtP")>0)||(value.indexOf("PlP")>0)||(value.indexOf("dP")>0)){
        	if ((value.indexOf("SingularS")>=0)|| (value.indexOf("PluralS")>=0)||(value.indexOf("unmarkedS")>=0)){
        		String tmpS="";
        		if( (value.indexOf("unmarkedS")>=0) ||(value.indexOf("UnmarkedS")>=0)){
        			
        			o.put("N2","number syntax");
        			o.put("V2", "Unmarked");
        		}
        		else if ((value.indexOf("SingularS")>=0)&&(value.indexOf("PluralS")>=0)) {
        			o.put("N2","number syntax");
        			o.put("V2", "Singular Plural");
        		}
        		else if ((value.indexOf("SingularS")>=0)){
        			o.put("N2","number syntax");
        			o.put("V2", "Singular");
        		}
        		else if ((value.indexOf("PluralS")>=0)){
        			o.put("N2","number syntax");
        			o.put("V2", "Plural");
        		}
        	}
        	if ((value.indexOf("SingularP")>=0)||(value.indexOf("PluralIntP")>=0)|| (value.indexOf("PluralExtP")>=0)||(value.indexOf("PluralPlP")>=0)||(value.indexOf("unmarkedP")>=0)||(value.indexOf("UnmarkedP")>=0)){
        		String tmpS="";
        		if ((value.indexOf("unmarkedP")>=0)||(value.indexOf("UnmarkedP")>=0)){
        			o.put("N1","number pattern");
        			o.put("V1", "Unmarked");
        		}
        		else if (value.indexOf("SingularP")>=0){
        			o.put("N1","number pattern");
        			o.put("V1", "Singular");
        		}
        		else if ((value.indexOf("PluralIntP")>=0)) {
        			o.put("N1","number pattern");
        			o.put("V1", "Plural Intern");
        		}
        		else if ((value.indexOf("PluralExtP")>=0)) {
        			o.put("N1","number pattern");
        			o.put("V1", "Plural Extern");
        		}
        		else if ((value.indexOf("PluralPlP")>=0)){
        			o.put("N1","number pattern");
        			o.put("V1", "Plural of Plural");
        		}
        	}
        	}
        	else {
            	o.put("N", name);
        		o.put("V", value);
            }
        }
        else if(name.equals("comment")){
        	o.put("N", name);
    		o.put("V", StringEscapeUtils.escapeHtml(value));
        }
        else if(name.equals("lex")) {
        	o.put("N", name);
        	String value1=value.substring(0,value.indexOf("--"));
        	String value2=value.substring(value.indexOf("--")+2);
        	o.put("V", value2 +"    http://betamasaheft.eu/api/Dillmann/"+value1+"/teientry");
        }
        else {
        	o.put("N", name);
    		o.put("V", value);
        }
		return o;
	}
	/**
	 * 
	 * @return {@link #value}
	 */
	public String getValue(){
		
		return value;
	}
/**
 * 
 * @return {@link #name}
 */
	public String getName(){
		return name;
	}
	
	/**
	* assigns a new value  to #name
	*@param s, the value to be assigned to #name
*/
	public void setName(String s){
		this.name=s;
	}
	/**
	 * assigns a new value to #value
	 * @param s, the new value to be assigned to #value
	 */
	public void setValue(String s){
		this.value=s;
	}

}
