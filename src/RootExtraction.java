
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.Vector;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RootExtraction {
	static String toDelete="";
	 static Calendar cal;
	 static SimpleDateFormat sdf;
	 public final static String LEXICON_ENTRY_DELIMITER="————————————";
	public static void main(String[] args){
		ArrayList<String>LexIds = new ArrayList<String>();
		ArrayList<String>Lemmas = new ArrayList<String>();
		ArrayList<String>dillEntries = new ArrayList<String>();
		String str="";
		try{
		PrintStream out = new PrintStream(System.out, true, "UTF-8");
		try {
			//File fileDir = new File("/home/vertan/Arbeitsfläche/Lexicon/test.txt");
			//File fileDir = new File("ProcessedDillmann");
			File fileDir = new File("ProcessedNominaListe");
			BufferedReader in = new BufferedReader(
			   new InputStreamReader(
	                      new FileInputStream(fileDir), "UTF8"));
	 
	       
	       while ((str = in.readLine()) != null) {
	    	   
	    	  // StringTokenizer st=new StringTokenizer(str1,"\n");
	    	  // System.out.println("No CR "+st.countTokens());
	    	  // while(st.hasMoreTokens()){
	    		  // String str=st.nextToken();
	    	      System.out.println(str);
	    	      
	    	   int posLemma=str.indexOf('\u1361');
	    	   if(posLemma>0){
	    	   String lemma=str.substring(0, posLemma);
	    	   lemma.replaceAll(" ", "");
	    	   if (lemma.indexOf("III")==0) lemma=lemma.substring(3)+" III";
	    	   else if (lemma.indexOf("II")==0) lemma=lemma.substring(2)+" II";
	    	   else if (lemma.indexOf("I")==0) lemma=lemma.substring(1)+" I";
	    	   System.out.println(lemma);
	    	   Lemmas.add(lemma);
	    	   String dill=str.substring(posLemma+2);
	    	   
	    	   //dill=dill.replaceAll("\\I*", "<i>");
	    	  // dill=dill.replaceAll("\\*I", "</i>");
	    	  // dill=dill.replaceAll("\\S*", "<sup>");
	    	  // dill=dill.replaceAll("\\*S", "</sup>");
	    	   dillEntries.add(str.substring(posLemma+1));
	    	   String id="L"+UUID.randomUUID();
	    	   id=id.replaceAll("-", "");
	    	   LexIds.add(id);
	      // }
	       
	
	       }
	       }
	                in.close();
		    } 
		    catch (UnsupportedEncodingException e) 
		    {
				System.out.println(e.getMessage());
		    } 
		    catch (IOException e) 
		    {
				System.out.println(e.getMessage());
		    }
		    catch (Exception e)
		    {
				System.out.println(e.getMessage());
		    }
		   
		
				try{
		    Writer outLex = new BufferedWriter(new OutputStreamWriter(
		    
		  
		    new FileOutputStream("LexiconNomina.txt"), "UTF-8"));
		   
		    	try {
		    		
		    		for(int i=0;i<LexIds.size();i++){
		    			String entry=LexIds.get(i)+"$"+Lemmas.get(i)+"$"+dillEntries.get(i);
		    			outLex.write(entry);
		    			outLex.write("\n");
		    			//System.out.println(entry);
		    		}
		    		
		    	} finally {
		    		System.out.println("No entries = "+Lemmas.size());
		    		outLex.close();
		    	}
		    }catch (Exception e) {} 
		} catch (Exception e){}
		
	}
}