import java.util.ArrayList;
public class TokenisationVariant {
	String tokens;
	ArrayList<String> annList;
    
	
	public TokenisationVariant(String s, ArrayList<String> annList){
		this.tokens=s;
		this.annList=annList;
	}
	public TokenisationVariant(String s){
		this.tokens=s;
		this.annList=null;
	}
	public String getTokenisationVariant(){
		return tokens;
	}
	
	public ArrayList<String> getTokenVariantAnnot(){
		return annList;
	}
	
	public void setTokenVariantAnnot(ArrayList<String>a){
		annList=a;
	}
}
