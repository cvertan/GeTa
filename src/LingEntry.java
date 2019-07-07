import java.util.ArrayList;
public class LingEntry {
	
	String initialF;
	String initialT;
	ArrayList<TranslitVariant> trList;
	
	public LingEntry(String initialF,String initialT, ArrayList<TranslitVariant> trList){
		this.initialF=initialF;
		this.initialT=initialT;
		this.trList=trList;
	}

	public String getIntialFidal(){
		return initialF;
	}
	
	public void setIntialFidal(String s){
		initialF=s;
	}
	
	public String getIntialTranslit(){
		return initialT;
	}
	
	public void setIntialTranslit(String s){
		initialT=s;
	}
	
	public ArrayList<TranslitVariant> getTranslitVar(){
		return trList;
	}
	
	public void setTranslitVar(ArrayList<TranslitVariant> tr){
		trList=tr;
	}
}
