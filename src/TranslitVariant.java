import java.util.ArrayList;
public class TranslitVariant {

	String translit;
	ArrayList<TokenisationVariant> token;
	public TranslitVariant(String translit){
		this.translit=translit;
		this.token=null;
	}
	
	public TranslitVariant(String translit, ArrayList<TokenisationVariant> token){
		this.translit=translit;
		this.token=token;
	}
	
	public String getTranslitVariant(){
		return translit;
	}
	
	public void setTranslitVariant(String s){
		translit=s;
	}
	
	
	public ArrayList<TokenisationVariant> getTokenisVariants(){
		return token;
		
	}
	public void setTokenisVariants( ArrayList<TokenisationVariant> t){
		 token=t;
		
	}
	
}
