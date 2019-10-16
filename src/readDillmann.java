import java.io.*;
import org.apache.poi.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.net.URL;
import java.util.Iterator;
public class readDillmann {

	public static void main(String[] args) throws XmlException, OpenXML4JException, IOException{
		// TODO Auto-generated method stub
	
		  // File file=new File("Dillmann_Lexicon_Version_Tool.docx");
		   File file=new File("NominaListeSHFinal.docx");	  
		   FileInputStream fs = new FileInputStream(file);
	       // OPCPackage d = OPCPackage.open(fs);
	        //XWPFWordExtractor xw = new XWPFWordExtractor(d);
	      //XWPFDocument=new XWPFDocument(fs);
	      //  System.out.println(xw.getText()); 
	       //FileInputStream fis = new FileInputStream("D:/docx/read-test.docx");
	   	 XWPFDocument xdoc=new XWPFDocument(OPCPackage.open(fs));String text="";
	        for (XWPFParagraph paragraph : xdoc.getParagraphs()) {
                int pos = 0; 
                for (XWPFRun run : paragraph.getRuns()) {
                	System.out.println(run.text().toString());
                	//String s="";
                	// for (char c : run.text().toCharArray()) {

                        // System.out.print(c);
                	//	 s=""+c;
                      //   pos++;
                     //}
                    //System.out.println("Current run IsBold : " + run.isBold());
                	String italic="";
                	String superscr="";
                //String proc
                	//if(run.text().toString().indexOf("\n")>0) System.out.println("LINE");
                    if (run.isItalic()) 
                    	{italic=run.text().toString();
                    	if (!italic.equals(" ")) {
                    	//	System.out.println(italic);
                    	italic="<i>"+italic+"</i>";
                    	}
                        text=text+italic;
                    	}
                    	//System.out.println(run.text().toCharArray());
                    else if(run.getSubscript().equals(VerticalAlign.SUPERSCRIPT)){
                	   superscr=run.text().toString();
                	  // System.out.println(run.text().toCharArray());
                	   
                	   if (!superscr.equals(" ")){
                		  // System.out.println(superscr);
                		   superscr="<sup>"+superscr+"</sup>";
                	   }
                	   text=text+superscr;
                   }
                    else {
                    	
                    	text=text+run.text().toString();
                    }
                	
                }
            }
	       // System.out.println("Transform ");
	        //System.out.println(text);
	        text=text.replaceAll(" CR ", "\n");
	        text=text.replaceAll("————————————", "\n");
	    	try{
			    Writer outDill = new BufferedWriter(new OutputStreamWriter(
			    
			  // new FileOutputStream("/home/vertan/Arbeitsfläche/Lexicon/Dillmann_Sanskrit.txt"), "UTF-16"));
			   // new FileOutputStream("ProcessedDillmann"), "UTF-8"));
			    new FileOutputStream("ProcessedNominaListe"), "UTF-8"));
			   // new FileOutputStream("/home/vertan/Arbeitsfläche/Lexicon/Dillmann_Syrisch.txt"), "UTF-16"));
			   // new FileOutputStream("/home/vertan/Arbeitsfläche/Lexicon/Dillmann_Arabisch.txt"), "UTF-16"));
			    //new FileOutputStream("/home/vertan/Arbeitsfläche/Lexicon/Dillmann_Hebraeisch.txt"), "UTF-16"));
			    	try {
			    		//Iterator<String> it=coptic.iterator();
			    		//(it.hasNext()){
			              //   outSanskrit.write(it.next());
			                // outSanskrit.write("\n\n");
	                     outDill.write(text);
			    		
			    	} finally {
			    		outDill.close();
			    	}
			    }catch (Exception e) {} 
	}

}
