import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.*;

import javax.swing.JComboBox;
/**
 * builds the off-line lexicon
 * the file Lexicon.txt in which each line has the form  Id$lemma$dictionary is required
 * @author Cristina Vertan
 *
 */
public class Lexicon {
	/** 
	 *  list of {@link LexiconEntry}
	 */
	ArrayList<ArrayList<LexiconEntry>> entries = new ArrayList<ArrayList<LexiconEntry>>();
	/**
	 *  a variable wich keeps all correspondences between fidal and transcription
	 *  @see InitializeFonts
	 */
	InitializeFonts f=new InitializeFonts();
	/**
	 * Hash table with keys the {@link LexiconEntry} keys of each lemma
	 * used to have fast access to the lemmas
	 */
	private Map<String,Integer> indexKey=new HashMap<String, Integer>();
	
	/**
	 * builds the lexicon
	 * reads each ine of Lexicon.tyt, parse it (separator is $) and buuilds a {@link LexiconEnry}
	 */
	public Lexicon(){
		
		for (int i=0;i<f.keys.length;i++){
			ArrayList<LexiconEntry> l=new ArrayList<LexiconEntry>();
			entries.add(l);
			indexKey.put(""+f.keys[i], new Integer(i));
		}
		//System.out.println("Entries "+entries.size());
		try{
			
			InputStreamReader r = new InputStreamReader(TagGUI.class.getResourceAsStream("LexiconNew.txt"));
					 
				try{
				BufferedReader in = new BufferedReader(
						   new InputStreamReader(
								   TagGUI.class.getResourceAsStream("LexiconNew.txt"), "UTF-8"));
						String str;
			            //System.out.println("read from Lexicon");
						 while ((str = in.readLine()) != null) {
							// System.out.println("**"+str);
							 if(str.length()>2) {
							StringTokenizer st=new StringTokenizer(str,"$");
							
							int i=0; 
							String id=""; String lemma="";String dict="";String key="";
							while(st.hasMoreTokens()){
								i=i+1;
								String token=st.nextToken();
								if (i==1) {id=token; 
								//System.out.println("ID "+id);
								}
								if(i==2) {
									lemma=token;
									String firstL= ""+lemma.charAt(0);
									key=getKeyEntry(firstL);
									//System.out.println("Lemma "+lemma);
									
								}
								if (i==3) {dict=token; 
						//		System.out.println("Dict "+ dict);
								}
								
							}
							LexiconEntry lex=new LexiconEntry(id,key,lemma,dict) ;
							//System.out.println(" Key "+ key);
							int indexK=indexKey.get(key).intValue();
						//	System.out.println("KEY "+key +"index "+indexK);
							
							entries.get(indexK).add(lex);
						 }
						 }
						// System.out.println("Finish Lexicon");
							
						in.close(); 
				}catch(Exception e){}
		}catch(Exception e1){}		
		//System.out.println(entries.size());
		for(int i=0; i<entries.size();i++) {
			//System.out.println("i = "+i+"  "+entries.get(i));
		    Collections.sort(entries.get(i), new FidelSort());
		}
		//System.out.println(entries.size());
	}
	/**
	 * returns the representative of a key class, namely if the fidal letter has the transcription VC (where c can be one of the six vowels used in Ge'ez) then the key is the fidal represented by V+a
	 * @param l  a string, teh current lemma
	 * @return a string , the key
	 * @see InitializeFonts
	 */
	public String getKeyEntry(String l){
		String key="";
		
		if (l.equals("\u1200")||l.equals("\u1201")||l.equals("\u1202")||l.equals("\u1203")||l.equals("\u1204")||l.equals("\u1205")||l.equals("\u1206"))
				key="\u1200";
		else if (l.equals("\u1208")||l.equals("\u1209")||l.equals("\u120A")||l.equals("\u120B")||l.equals("\u120C")||l.equals("\u120D")||l.equals("\u120E"))
			key="\u1208";
		else if (l.equals("\u1210")||l.equals("\u1211")||l.equals("\u1212")||l.equals("\u1213")||l.equals("\u1214")||l.equals("\u1215")||l.equals("\u1216"))
			key="\u1210";
		else if (l.equals("\u1218")||l.equals("\u1219")||l.equals("\u121A")||l.equals("\u121B")||l.equals("\u121C")||l.equals("\u121D")||l.equals("\u121E"))
			key="\u1218";
		else if (l.equals("\u1220")||l.equals("\u1221")||l.equals("\u1222")||l.equals("\u1223")||l.equals("\u1224")||l.equals("\u1225")||l.equals("\u1226"))
			key="\u1220";
		else if (l.equals("\u1228")||l.equals("\u1229")||l.equals("\u122A")||l.equals("\u122B")||l.equals("\u122C")||l.equals("\u122D")||l.equals("\u122E"))
			key="\u1228";
		else if (l.equals("\u1230")||l.equals("\u1231")||l.equals("\u1232")||l.equals("\u1233")||l.equals("\u1234")||l.equals("\u1235")||l.equals("\u1236"))
			key="\u1230";
		else if (l.equals("\u1240")||l.equals("\u1241")||l.equals("\u1242")||l.equals("\u1243")||l.equals("\u1244")||l.equals("\u1245")||l.equals("\u1246"))
			key="\u1240";	 
		else if (l.equals("\u1260")||l.equals("\u1261")||l.equals("\u1262")||l.equals("\u1263")||l.equals("\u1264")||l.equals("\u1265")||l.equals("\u1266"))
			key="\u1260";	
		else if (l.equals("\u1270")||l.equals("\u1271")||l.equals("\u1272")||l.equals("\u1273")||l.equals("\u1274")||l.equals("\u1275")||l.equals("\u1276"))
			key="\u1270";
		else if (l.equals("\u1280")||l.equals("\u1281")||l.equals("\u1282")||l.equals("\u1283")||l.equals("\u1284")||l.equals("\u1285")||l.equals("\u1286"))
			key="\u1280";	 
		else if (l.equals("\u1290")||l.equals("\u1291")||l.equals("\u1292")||l.equals("\u1293")||l.equals("\u1294")||l.equals("\u1295")||l.equals("\u1296"))
			key="\u1290";
		else if (l.equals("\u12A0")||l.equals("\u12A1")||l.equals("\u12A2")||l.equals("\u12A3")||l.equals("\u12A4")||l.equals("\u12A5")||l.equals("\u12A6"))
			key="\u12A0";	
		else if (l.equals("\u12A8")||l.equals("\u12A9")||l.equals("\u12AA")||l.equals("\u12AB")||l.equals("\u12AC")||l.equals("\u12AD")||l.equals("\u12AE"))
			key="\u12A8";
		else if (l.equals("\u12C8")||l.equals("\u12C9")||l.equals("\u12CA")||l.equals("\u12CB")||l.equals("\u12CC")||l.equals("\u12CD")||l.equals("\u12CE"))
			key="\u12C8";	 
		else if (l.equals("\u12D0")||l.equals("\u12D1")||l.equals("\u12D2")||l.equals("\u12D3")||l.equals("\u12D4")||l.equals("\u12D5")||l.equals("\u12D6"))
			key="\u12D0";
		else if (l.equals("\u12D8")||l.equals("\u12D9")||l.equals("\u12DA")||l.equals("\u12DB")||l.equals("\u12DC")||l.equals("\u12DD")||l.equals("\u12DE"))
			key="\u12D8";
		else if (l.equals("\u12E8")||l.equals("\u12E9")||l.equals("\u12EA")||l.equals("\u12EB")||l.equals("\u12EC")||l.equals("\u12ED")||l.equals("\u12EE"))
			key="\u12E8";	
		else if (l.equals("\u12F0")||l.equals("\u12F1")||l.equals("\u12F2")||l.equals("\u12F3")||l.equals("\u12F4")||l.equals("\u12F5")||l.equals("\u12F6"))
			key="\u12F0";	 
		else if (l.equals("\u1308")||l.equals("\u1309")||l.equals("\u130A")||l.equals("\u130B")||l.equals("\u130C")||l.equals("\u130D")||l.equals("\u130E"))
			key="\u1308";	 
		else if (l.equals("\u1320")||l.equals("\u1321")||l.equals("\u1322")||l.equals("\u1323")||l.equals("\u1324")||l.equals("\u1325")||l.equals("\u1326"))
			key="\u1320";	
		else if (l.equals("\u1330")||l.equals("\u1331")||l.equals("\u1332")||l.equals("\u1333")||l.equals("\u1334")||l.equals("\u1335")||l.equals("\u1336"))
			key="\u1330";	
		else if (l.equals("\u1338")||l.equals("\u1339")||l.equals("\u133A")||l.equals("\u133B")||l.equals("\u133C")||l.equals("\u133D")||l.equals("\u133E"))
			key="\u1338";	
		else if (l.equals("\u1340")||l.equals("\u1341")||l.equals("\u1342")||l.equals("\u1343")||l.equals("\u1344")||l.equals("\u1345")||l.equals("\u1346"))
			key="\u1340";	
		else if (l.equals("\u1348")||l.equals("\u1349")||l.equals("\u134A")||l.equals("\u134B")||l.equals("\u134C")||l.equals("\u134D")||l.equals("\u134E"))
			key="\u1348";		 
		else if (l.equals("\u1350")||l.equals("\u1351")||l.equals("\u1352")||l.equals("\u1353")||l.equals("\u1354")||l.equals("\u1355")||l.equals("\u1356"))
			key="\u1350";	
		else if (l.equals("\u1248")||l.equals("\u1249")||l.equals("\u124A")||l.equals("\u124B")||l.equals("\u124C")||l.equals("\u124D"))
			key="\u1248";	
		else if (l.equals("\u1288")||l.equals("\u1289")||l.equals("\u128A")||l.equals("\u128B")||l.equals("\u128C")||l.equals("\u128D"))
			key="\u1288";		 
		else if (l.equals("\u13B0")||l.equals("\u13B1")||l.equals("\u13B2")||l.equals("\u13B3")||l.equals("\u13B4")||l.equals("\u13B5"))
			key="\u13B0";	
		else if (l.equals("\u1310")||l.equals("\u1311")||l.equals("\u1312")||l.equals("\u1313")||l.equals("\u1314")||l.equals("\u1315"))
			key="\u1310";	
		else if (l.equals("\u1238")||l.equals("\u1239")||l.equals("\u123A")||l.equals("\u123B")||l.equals("\u123C")||l.equals("\u123D")||l.equals("\u123E"))
			key="\u1238";		
		else if (l.equals("\u1268")||l.equals("\u1269")||l.equals("\u126A")||l.equals("\u126B")||l.equals("\u126C")||l.equals("\u126D")||l.equals("\u126E"))
			key="\u1268";	
		else if (l.equals("\u1278")||l.equals("\u1279")||l.equals("\u127A")||l.equals("\u127B")||l.equals("\u127C")||l.equals("\u127D")||l.equals("\u127E"))
			key="\u1278";	
		else if (l.equals("\u1298")||l.equals("\u1299")||l.equals("\u129A")||l.equals("\u129B")||l.equals("\u129C")||l.equals("\u129D")||l.equals("\u129E"))
			key="\u1298";	
		else if (l.equals("\u12B8")||l.equals("\u12B9")||l.equals("\u12BA")||l.equals("\u12BB")||l.equals("\u12BC")||l.equals("\u12BD")||l.equals("\u12BE"))
			key="\u12B8";			 
		else if (l.equals("\u12C0")||l.equals("\u12C1")||l.equals("\u12C2")||l.equals("\u12C3")||l.equals("\u12C4")||l.equals("\u12C5"))
			key="\u12C0";	
		else if (l.equals("\u12E0")||l.equals("\u12E1")||l.equals("\u12E2")||l.equals("\u12E3")||l.equals("\u12E4")||l.equals("\u12E5"))
			key="\u12E0";	
		else if (l.equals("\u1300")||l.equals("\u1301")||l.equals("\u1302")||l.equals("\u1303")||l.equals("\u1304")||l.equals("\u1305")||l.equals("\u1306"))
			key="\u1300";	
		else if (l.equals("\u1328")||l.equals("\u1329")||l.equals("\u132A")||l.equals("\u132B")||l.equals("\u132C")||l.equals("\u132D")||l.equals("\u132E"))
			key="\u1328";	
		else if(l.equals("\u12B0")||l.equals("\u12B2")||l.equals("\u12B3")||l.equals("\u12B4")||l.equals("\u12B5"))
			key="\u12B0";
		else if (l.equals("\u1369"))
			key="\u1369";
		else if (l.equals("\u136A"))
			key="\u136A";
		else if (l.equals("\u136B"))
			key="\u136B";
		else if (l.equals("\u136C"))
			key="\u136C";
		else if (l.equals("\u136D"))
			key="\u136D";
		else if (l.equals("\u136E"))
			key="\u136E";
		else if (l.equals("\u136F"))
			key="\u136F";
		else if (l.equals("\u1370"))
			key="\u1370";
		else if (l.equals("\u1371"))
			key="\u1371";
		else if (l.equals("\u1372"))
			key="\u1372";
		else if (l.equals("\u1373"))
			key="\u1373";
		else if (l.equals("\u1374"))
			key="\u1374";
		else if (l.equals("\u1375"))
			key="\u1375";
		else if (l.equals("\u1376"))
			key="\u1376";
		else if (l.equals("\u1377"))
			key="\u1377";
		else if (l.equals("\u1378"))
			key="\u1378";
		else if (l.equals("\u1379"))
			key="\u1379";
		else if (l.equals("\u137A"))
			key="\u137A";
		else if (l.equals("\u137B"))
			key="\u137B";
		else if (l.equals("\u137C"))
			key="\u137C";
		else System.out.println(l);
		return key;
		
	}
	/**
	 * 
	 * @param i an integer, position whre to search
	 * @return a {@link LexiconEntry} situated in the list at position i
	 */
	public ArrayList<LexiconEntry> getEntries(int i){
		return entries.get(i);
	}
	/**
	 * 
	 * @param s a string the lemma 
	 * @return the entire {@link LexiconEntry} attached to this lemma
	 */
	public ArrayList<LexiconEntry> getEntriesKey(String s){
		//System.out.println("**"+ indexKey.get(s).intValue());
		//System.out.println("Size "+entries.get(indexKey.get(s).intValue()).size());
		return entries.get(indexKey.get(s).intValue());
	}

}
/**
 * sort in alphabetical order the lexicon entries
 * @author Cristina Vertan
 *
 */
class FidelSort implements Comparator<LexiconEntry>{
	public int compare(LexiconEntry le1, LexiconEntry le2){
		//System.out.println("LE1 "+le1.getLexiconEntryLemma()+"LE2 "+le2.getLexiconEntryLemma());
		if(le1.getLexiconEntryLemma().compareTo(le2.getLexiconEntryLemma())>0){
			//System.out.println(" 1");
			return 1;
		}
		if(le1.getLexiconEntryLemma().compareTo(le2.getLexiconEntryLemma())==0){
			//System.out.println(" 0");
			return 0;
		}
		else {
			//System.out.println(" -1");
			return -1;
			}
	}
}
