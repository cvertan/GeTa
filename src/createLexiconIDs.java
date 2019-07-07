import java.util.UUID;

public class createLexiconIDs {
	
	public static void main(String arg[]){
		
		for (int i=0; i<100;i++){
		 String id="L"+UUID.randomUUID();
  	     id=id.replaceAll("-", "");
  	     System.out.println(id);
		}
	}

}
