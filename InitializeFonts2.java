
import java.util.HashMap;
import java.util.Map;
import java.util.Hashtable;
import java.util.List;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
public class InitializeFonts2 {
	public Map <String, String> geezTranslit = new HashMap<String, String>();
	public Map <String, String> suedArabTranslit = new HashMap<String, String>();
	public Map <String, String> geezTranslitN = new HashMap<String, String>();
	public Map <String, String> thesaurusTranslit = new HashMap<String, String>();
	public Map <String, String> qw = new HashMap<String, String>();
	public Map <String, String> geezTranslitR = new HashMap<String, String>();
	public Map <String, String> suedArabTranslitR = new HashMap<String, String>();
	public Map <String, String[]> geezHomophone = new HashMap<String, String[]>();
	public Map <String, String> laringals = new HashMap<String, String>();
	public Map <String, String> waw = new HashMap<String, String>();
	public Map <String, String> yod = new HashMap<String, String>();
	public Map <String, String> dentals = new HashMap<String, String>();
	public Map <String, String> sibilants = new HashMap<String, String>();
	public Set<String> specialSymbols = new TreeSet<String>();
	public Set<String> geezSymbols = new TreeSet<String>();
	public Set<String> suedArabSymbols = new TreeSet<String>();
	public Set<String> brackets = new TreeSet<String>();
	char[] keys={'\u1200','\u1208','\u1210','\u1218','\u1220','\u1228','\u1230','\u1240','\u1260','\u1270','\u1280','\u1290','\u12A0',
			'\u12A8','\u12C8','\u12D0','\u12D8','\u12E8','\u12F0','\u1308','\u1320','\u1330','\u1338','\u1340','\u1348','\u1350','\u1248','\u1288',
			'\u12B0','\u1310','\u1238','\u1268','\u1278','\u1298','\u12B8','\u12C0','\u12E0','\u1300','\u1328','\u1369','\u136A','\u136B','\u136C',
			'\u136D','\u136E','\u136F','\u1370','\u1371','\u1372','\u1373','\u1374','\u1375','\u1376','\u1377','\u1378','\u1379','\u137A','\u137B','\u137c'};
   String[] consonants=	{"h","l","\u1e25","m","\u015b","r","s","q","b","t","\u1e2b","n",
			"\u02be","k","w","\u02bf","z","y","d","g","\u1e6d","\u1e57","\u1e63","\u1e0d","f","p","Q"
			,"X","K","G"};
   
    private static InitializeFonts2 instance=null;
    
    public static InitializeFonts2 getInstance(){
    	if(instance==null) {
    		instance=new InitializeFonts2();
    	}
    	return instance;
    }
	public InitializeFonts2(){
		
		thesaurusTranslit.put("\u12ad\u122c\u1235\u1276\u1235", "kr\u01ddstos");
		thesaurusTranslit.put("\u12a5\u130d\u12d9\u12a0\u1265\u1214\u122c","la-\u02be\u01ddgzi\u02beabÃÂ\u1e25er");
		thesaurusTranslit.put("\u12a5\u121d\u12dd","\u02be\u01ddmz\u01dd");
		//Map <String, String> geezTranslit = new HashMap<String, String>();
		//brackets
		brackets.add("[");
		brackets.add("]");
		brackets.add("<");
		brackets.add(">");
		brackets.add("(");
		brackets.add(")");
		brackets.add("{");
		brackets.add("}");
		brackets.add("[");
		brackets.add("\u2308");
		brackets.add("\u2309");
		brackets.add("\u230A");
		brackets.add("\u230B");
		//special Symbol Alphabet
		specialSymbols.add("\u25a7");
		specialSymbols.add("\u25A1");
		specialSymbols.add("\u204B");
		specialSymbols.add("\u2026");
		//Ge'ez alphabet
		//ሀ ሁ ሂ ሃ ሄ ህ ሆ
				geezSymbols.add("\u1200");
				geezSymbols.add("\u1201");
				geezSymbols.add("\u1202");
				geezSymbols.add("\u1203");
				geezSymbols.add("\u1204");
				geezSymbols.add("\u1205");
				geezSymbols.add("\u1206");
				//ለ ሉ ሊ ላ ሌ ል ሎ
				geezSymbols.add("\u1208");
				geezSymbols.add("\u1209");
				geezSymbols.add("\u120A");
				geezSymbols.add("\u120B");
				geezSymbols.add("\u120C");
				geezSymbols.add("\u120D");
				geezSymbols.add("\u120E");
				//ሐ ሑ ሒ ሓ ሔ ሕ ሖ
				geezSymbols.add("\u1210");
				geezSymbols.add("\u1211");
				geezSymbols.add("\u1212");
				geezSymbols.add("\u1213");
				geezSymbols.add("\u1214");
				geezSymbols.add("\u1215");
				geezSymbols.add("\u1216");
				//መ ሙ ሚ ማ ሜ ም ሞ
				geezSymbols.add("\u1218");
				geezSymbols.add("\u1219");
				geezSymbols.add("\u121A");
				geezSymbols.add("\u121B");
				geezSymbols.add("\u121C");
				geezSymbols.add("\u121D");
				geezSymbols.add("\u121E");
				//ሠ ሡ ሢ ሣ ሤ ሥ ሦ
				geezSymbols.add("\u1220");
				geezSymbols.add("\u1221");
				geezSymbols.add("\u1222");
				geezSymbols.add("\u1223");
				geezSymbols.add("\u1224");
				geezSymbols.add("\u1225");
				geezSymbols.add("\u1226");
				//ረ ሩ ሪ ራ ሬ ር ሮ
				geezSymbols.add("\u1228");
				geezSymbols.add("\u1229");
				geezSymbols.add("\u122A");
				geezSymbols.add("\u122B");
				geezSymbols.add("\u122C");
				geezSymbols.add("\u122D");
				geezSymbols.add("\u122E");
				//ሰ ሱ ሲ ሳ ሴ ስ ሶ
				geezSymbols.add("\u1230");
				geezSymbols.add("\u1231");
				geezSymbols.add("\u1232");
				geezSymbols.add("\u1233");
				geezSymbols.add("\u1234");
				geezSymbols.add("\u1235");
				geezSymbols.add("\u1236");
				//ቀ ቁ ቂ ቃ ቄ ቅ ቆ
				geezSymbols.add("\u1240");
				geezSymbols.add("\u1241");
				geezSymbols.add("\u1242");
				geezSymbols.add("\u1243");
				geezSymbols.add("\u1244");
				geezSymbols.add("\u1245");
				geezSymbols.add("\u1246");
				//በ ቡ ቢ ባ ቤ ብ ቦ
				geezSymbols.add("\u1260");
				geezSymbols.add("\u1261");
				geezSymbols.add("\u1262");
				geezSymbols.add("\u1263");
				geezSymbols.add("\u1264");
				geezSymbols.add("\u1265");
				geezSymbols.add("\u1266");
				//ተ ቱ ቲ ታ ቴ ት ቶ
				geezSymbols.add("\u1270");
				geezSymbols.add("\u1271");
				geezSymbols.add("\u1272");
				geezSymbols.add("\u1273");
				geezSymbols.add("\u1274");
				geezSymbols.add("\u1275");
				geezSymbols.add("\u1276");
				//ኀ ኁ ኂ ኃ ኄ ኅ ኆ
				geezSymbols.add("\u1280");
				geezSymbols.add("\u1281");
				geezSymbols.add("\u1282");
				geezSymbols.add("\u1283");
				geezSymbols.add("\u1284");
				geezSymbols.add("\u1285");
				geezSymbols.add("\u1286");
				//ነ ኑ ኒ ና ኔ ን ኖ
				geezSymbols.add("\u1290");
				geezSymbols.add("\u1291");
				geezSymbols.add("\u1292");
				geezSymbols.add("\u1293");
				geezSymbols.add("\u1294");
				geezSymbols.add("\u1295");
				geezSymbols.add("\u1296");
				//አ ኡ ኢ ኣ ኤ እ ኦ
				geezSymbols.add("\u12A0");
				geezSymbols.add("\u12A1");
				geezSymbols.add("\u12A2");
				geezSymbols.add("\u12A3");
				geezSymbols.add("\u12A4");
				geezSymbols.add("\u12A5");
				geezSymbols.add("\u12A6");
				//ከ ኩ ኪ ካ ኬ ክ ኮ
				geezSymbols.add("\u12A8");
				geezSymbols.add("\u12A9");
				geezSymbols.add("\u12AA");
				geezSymbols.add("\u12AB");
				geezSymbols.add("\u12AC");
				geezSymbols.add("\u12AD");
				geezSymbols.add("\u12AE");
				//ወ ዉ ዊ ዋ ዌ ው ዎ
				geezSymbols.add("\u12C8");
				geezSymbols.add("\u12C9");
				geezSymbols.add("\u12CA");
				geezSymbols.add("\u12CB");
				geezSymbols.add("\u12CC");
				geezSymbols.add("\u12CD");
				geezSymbols.add("\u12CE");
				//ዐ ዑ ዒ ዓ ዔ ዕ ዖ
				geezSymbols.add("\u12D0");
				geezSymbols.add("\u12D1");
				geezSymbols.add("\u12D2");
				geezSymbols.add("\u12D3");
				geezSymbols.add("\u12D4");
				geezSymbols.add("\u12D5");
				geezSymbols.add("\u12D6");
				//ዘ ዙ ዚ ዛ ዜ ዝ ዞ
				geezSymbols.add("\u12D8");
				geezSymbols.add("\u12D9");
				geezSymbols.add("\u12DA");
				geezSymbols.add("\u12DB");
				geezSymbols.add("\u12DC");
				geezSymbols.add("\u12DD");
				geezSymbols.add("\u12DE");
				//የ ዩ ዪ ያ ዬ ይ ዮ
				geezSymbols.add("\u12E8");
				geezSymbols.add("\u12E9");
				geezSymbols.add("\u12EA");
				geezSymbols.add("\u12EB");
				geezSymbols.add("\u12EC");
				geezSymbols.add("\u12ED");
				geezSymbols.add("\u12EE");
				//ደ ዱ ዲ ዳ ዴ ድ ዶ
				geezSymbols.add("\u12F0");
				geezSymbols.add("\u12F1");
				geezSymbols.add("\u12F2");
				geezSymbols.add("\u12F3");
				geezSymbols.add("\u12F4");
				geezSymbols.add("\u12F5");
				geezSymbols.add("\u12F6");
				//ገ ጉ ጊ ጋ ጌ ግ ጎ
				geezSymbols.add("\u1308");
				geezSymbols.add("\u1309");
				geezSymbols.add("\u130A");
				geezSymbols.add("\u130B");
				geezSymbols.add("\u130C");
				geezSymbols.add("\u130D");
				geezSymbols.add("\u130E");
				//ጠ ጡ ጢ ጣ ጤ ጥ ጦ
				geezSymbols.add("\u1320");
				geezSymbols.add("\u1321");
				geezSymbols.add("\u1322");
				geezSymbols.add("\u1323");
				geezSymbols.add("\u1324");
				geezSymbols.add("\u1325");
				geezSymbols.add("\u1326");
				//ጰ ጱ ጲ ጳ ጴ ጵ ጶ
				geezSymbols.add("\u1330");
				geezSymbols.add("\u1331");
				geezSymbols.add("\u1332");
				geezSymbols.add("\u1333");
				geezSymbols.add("\u1334");
				geezSymbols.add("\u1335");
				geezSymbols.add("\u1336");
				//ጸ ጹ ጺ ጻ ጼ ጽ ጾ
				geezSymbols.add("\u1338");
				geezSymbols.add("\u1339");
				geezSymbols.add("\u133A");
				geezSymbols.add("\u133B");
				geezSymbols.add("\u133C");
				geezSymbols.add("\u133D");
				geezSymbols.add("\u133E");
				//ፀ ፁ ፂ ፃ ፄ ፅ ፆ
				geezSymbols.add("\u1340");
				geezSymbols.add("\u1341");
				geezSymbols.add("\u1342");
				geezSymbols.add("\u1343");
				geezSymbols.add("\u1344");
				geezSymbols.add("\u1345");
				geezSymbols.add("\u1346");
				//ፈ ፉ ፊ ፋ ፌ ፍ ፎ
				geezSymbols.add("\u1348");
				geezSymbols.add("\u1349");
				geezSymbols.add("\u134A");
				geezSymbols.add("\u134B");
				geezSymbols.add("\u134C");
				geezSymbols.add("\u134D");
				geezSymbols.add("\u134E");
				//ፐ ፑ ፒ ፓ ፔ ፕ ፖ
				geezSymbols.add("\u1350");
				geezSymbols.add("\u1351");
				geezSymbols.add("\u1352");
				geezSymbols.add("\u1353");
				geezSymbols.add("\u1354");
				geezSymbols.add("\u1355");
				geezSymbols.add("\u1356");
				//Labiovelare
				//ቈ ቊ ቋ ቌ ቍ
				geezSymbols.add("\u1248");
				geezSymbols.add("\u124A");
				geezSymbols.add("\u124B");
				geezSymbols.add("\u124C");
				geezSymbols.add("\u124D");
				//ኈ ኊ ኋ ኌ ኍ
				geezSymbols.add("\u1288");
				geezSymbols.add("\u128A");
				geezSymbols.add("\u128B");
				geezSymbols.add("\u128C");
				geezSymbols.add("\u128D");
				//ኰ ኲ ኳ ኴ ኵ
				geezSymbols.add("\u12B0");
				geezSymbols.add("\u12B2");
				geezSymbols.add("\u12B3");
				geezSymbols.add("\u12B4");
				geezSymbols.add("\u12B5");
				//ጐ ጒ ጓ ጔ ጕ
				geezSymbols.add("\u1310");
				geezSymbols.add("\u1312");
				geezSymbols.add("\u1313");
				geezSymbols.add("\u1314");
				geezSymbols.add("\u1315");
				//Zahlzeichen
				//Interpunktion
				//amharische und tigrinische Buchstaben
				//seltene Buchstaben

				//punctuation
				//sectionmark
				geezSymbols.add("\u1360"); 
				//word sepparator
				geezSymbols.add("\u1361"); 
				geezSymbols.add("\u1362"); 
				geezSymbols.add("\u1363"); 
				geezSymbols.add("\u1364");
				geezSymbols.add("\u1365"); 
				geezSymbols.add("\u1367"); 
				geezSymbols.add("\u1368"); 
				
				
				//Zahlzeichen
				//፩ ፪ ፫ ፬ ፭ ፮ ፯ ፰ ፱ ፲ ፳ ፴ ፵ ፶ ፷ ፸ ፹ ፺ ፻ ፼
				geezSymbols.add("\u1369");
				geezSymbols.add("\u136A");
				geezSymbols.add("\u136B");
				geezSymbols.add("\u136C");
				geezSymbols.add("\u136D");
				geezSymbols.add("\u136E");
				geezSymbols.add("\u136F");
				geezSymbols.add("\u1370");
				geezSymbols.add("\u1371");
				geezSymbols.add("\u1372");
				geezSymbols.add("\u1373");
				geezSymbols.add("\u1374");
				geezSymbols.add("\u1375");
				geezSymbols.add("\u1376");
				geezSymbols.add("\u1377");
				geezSymbols.add("\u1378");
				geezSymbols.add("\u1379");
				geezSymbols.add("\u137A");
				geezSymbols.add("\u137B");
				geezSymbols.add("\u137C");
				//Interpunktion
				//amharische und tigrinische Buchstaben
				//ሸ ሹ ሺ ሻ ሼ ሽ ሾ
				geezSymbols.add("\u1238");
				geezSymbols.add("\u1239");
				geezSymbols.add("\u123A");
				geezSymbols.add("\u123B");
				geezSymbols.add("\u123C");
				geezSymbols.add("\u123D");
				geezSymbols.add("\u123E");
				//ቨ ቩ ቪ ቫ ቬ ቭ ቮ
				geezSymbols.add("\u1268");
				geezSymbols.add("\u1269");
				geezSymbols.add("\u126A");
				geezSymbols.add("\u126B");
				geezSymbols.add("\u126C");
				geezSymbols.add("\u126D");
				geezSymbols.add("\u126E");
				//ቸ ቹ ቺ ቻ ቼ ች ቾ
				geezSymbols.add("\u1278");
				geezSymbols.add("\u1279");
				geezSymbols.add("\u127A");
				geezSymbols.add("\u127B");
				geezSymbols.add("\u127C");
				geezSymbols.add("\u127D");
				geezSymbols.add("\u127E");
				//ኘ ኙ ኚ ኛ ኜ ኝ ኞ
				geezSymbols.add("\u1298");
				geezSymbols.add("\u1299");
				geezSymbols.add("\u129A");
				geezSymbols.add("\u129B");
				geezSymbols.add("\u129C");
				geezSymbols.add("\u129D");
				geezSymbols.add("\u129E");
				//ኸ ኹ ኺ ኻ ኼ ኽ ኾ
				geezSymbols.add("\u12B8");
				geezSymbols.add("\u12B9");
				geezSymbols.add("\u12BA");
				geezSymbols.add("\u12BB");
				geezSymbols.add("\u12BC");
				geezSymbols.add("\u12BD");
				geezSymbols.add("\u12BE");
				//ዀ ዂ ዃ ዄ ዅ
				geezSymbols.add("\u12C0");
				geezSymbols.add("\u12C2");
				geezSymbols.add("\u12C3");
				geezSymbols.add("\u12C4");
				geezSymbols.add("\u12C5");
				//ዠ ዡ ዢ ዣ ዤ ዥ ዦ
				geezSymbols.add("\u12E0");
				geezSymbols.add("\u12E1");
				geezSymbols.add("\u12E2");
				geezSymbols.add("\u12E3");
				geezSymbols.add("\u12E4");
				geezSymbols.add("\u12E5");
				geezSymbols.add("\u12E6");
				//ጀ ጁ ጂ ጃ ጄ ጅ ጆ
				geezSymbols.add("\u1300");
				geezSymbols.add("\u1301");
				geezSymbols.add("\u1302");
				geezSymbols.add("\u1303");
				geezSymbols.add("\u1304");
				geezSymbols.add("\u1305");
				geezSymbols.add("\u1306");
				//ጨ ጩ ጪ ጫ ጬ ጭ ጮ
				geezSymbols.add("\u1328");
				geezSymbols.add("\u1329");
				geezSymbols.add("\u132A");
				geezSymbols.add("\u132B");
				geezSymbols.add("\u132C");
				geezSymbols.add("\u132D");
				geezSymbols.add("\u132E");
		//südarabisches Alphabet
				suedArabSymbols.add("\uD802\uDE60");
				suedArabSymbols.add("\uD802\uDE61");
				suedArabSymbols.add("\uD802\uDE62");
				suedArabSymbols.add("\uD802\uDE63");
				suedArabSymbols.add("\uD802\uDE64");
				suedArabSymbols.add("\uD802\uDE65");
				suedArabSymbols.add("\uD802\uDE66");
				suedArabSymbols.add("\uD802\uDE67");
				suedArabSymbols.add("\uD802\uDE68");
				suedArabSymbols.add("\uD802\uDE69");
				suedArabSymbols.add("\uD802\uDE6A");
				suedArabSymbols.add("\uD802\uDE6B");
				suedArabSymbols.add("\uD802\uDE6C");
				suedArabSymbols.add("\uD802\uDE6D");
				suedArabSymbols.add("\uD802\uDE6E");
				suedArabSymbols.add("\uD802\uDE6F");
				suedArabSymbols.add("\uD802\uDE70");
				suedArabSymbols.add("\uD802\uDE71");
				suedArabSymbols.add("\uD802\uDE72");
				suedArabSymbols.add("\uD802\uDE73");
				suedArabSymbols.add("\uD802\uDE74");
				suedArabSymbols.add("\uD802\uDE75");
				suedArabSymbols.add("\uD802\uDE76");
				suedArabSymbols.add("\uD802\uDE77");
				suedArabSymbols.add("\uD802\uDE78");
				suedArabSymbols.add("\uD802\uDE79");
				suedArabSymbols.add("\uD802\uDE7A");
				suedArabSymbols.add("\uD802\uDE7B");
				suedArabSymbols.add("\uD802\uDE7C");
				suedArabSymbols.add("\uD802\uDE7D");
				suedArabSymbols.add(" \u2227");
				suedArabSymbols.add(" \uD802\uDE7E");
				suedArabSymbols.add(" \u25a7");
				suedArabSymbols.add(" \u25a1");
				suedArabSymbols.add(" \u2026");
				
		
		//Südarabische Buchstaben
		/*suedArabTranslit.put("\u10A60","h");
		suedArabTranslit.put("\u10A61","l");
		suedArabTranslit.put("\u10A62","\u1e25");
		suedArabTranslit.put("\u10A63","m");
		suedArabTranslit.put("\u10A64","q");
		suedArabTranslit.put("\u10A65","w");
		suedArabTranslit.put("\u10A66","\u0161");
		suedArabTranslit.put("\u10A67","r");
		suedArabTranslit.put("\u10A68","b");
		suedArabTranslit.put("\u10A69","t");
		suedArabTranslit.put("\u10A6A","s");
		suedArabTranslit.put("\u10A6B","k");
		suedArabTranslit.put("\u10A6C","n");
		suedArabTranslit.put("\u10A6D","\u1e2b");
		suedArabTranslit.put("\u10A6E","\u1e62");
		suedArabTranslit.put("\u10A6F","\u015b");
		suedArabTranslit.put("\u10A70","f");
		suedArabTranslit.put("\u10A71","\u02be");
		suedArabTranslit.put("\u10A72","\u02bf");
		suedArabTranslit.put("\u10A73","\u1e0d");
		suedArabTranslit.put("\u10A74","g");
		suedArabTranslit.put("\u10A75","d");
		suedArabTranslit.put("\u10A76","\u0121");
		suedArabTranslit.put("\u10A77","u1e6d");
		suedArabTranslit.put("\u10A78","z");
		suedArabTranslit.put("\u10A79","\u1e0f");
		suedArabTranslit.put("\u10A7A","y");
		suedArabTranslit.put("\u10A7B","\u1e6f");
		suedArabTranslit.put("\u10A7C","\u1e93");
		suedArabTranslit.put("\u10A7D"," ");
		suedArabTranslit.put("\u2227","1");
		suedArabTranslit.put("\u10A7E","50");
		suedArabTranslit.put("\u25a7","\u25a7");
		suedArabTranslit.put("\u25a1","\u25a1");
		suedArabTranslit.put("\u204b","\u204b");
		suedArabTranslit.put("\u2026","\u2026");*/
				
				suedArabTranslit.put("\uD802\uDE60","h");
				suedArabTranslit.put("\uD802\uDE61","l");
				suedArabTranslit.put("\uD802\uDE62","\u1e25");
				suedArabTranslit.put("\uD802\uDE63","m");
				suedArabTranslit.put("\uD802\uDE64","q");
				suedArabTranslit.put("\uD802\uDE65","w");
				suedArabTranslit.put("\uD802\uDE66","\u0161");
				suedArabTranslit.put("\uD802\uDE67","r");
				suedArabTranslit.put("\uD802\uDE68","b");
				suedArabTranslit.put("\uD802\uDE69","t");
				suedArabTranslit.put("\uD802\uDE6A","s");
				suedArabTranslit.put("\uD802\uDE6B","k");
				suedArabTranslit.put("\uD802\uDE6C","n");
				suedArabTranslit.put("\uD802\uDE6D","\u1e2b");
				suedArabTranslit.put("\uD802\uDE6E","\u1e62");
				suedArabTranslit.put("\uD802\uDE6F","\u015b");
				suedArabTranslit.put("\uD802\uDE70","f");
				suedArabTranslit.put("\uD802\uDE71","\u02be");
				suedArabTranslit.put("\uD802\uDE72","\u02bf");
				suedArabTranslit.put("\uD802\uDE73","\u1e0d");
				suedArabTranslit.put("\uD802\uDE74","g");
				suedArabTranslit.put("\uD802\uDE75","d");
				suedArabTranslit.put("\uD802\uDE76","\u0121");
				suedArabTranslit.put("\uD802\uDE77","u1e6d");
				suedArabTranslit.put("\uD802\uDE78","z");
				suedArabTranslit.put("\uD802\uDE79","\u1e0f");
				suedArabTranslit.put("\uD802\uDE7A","y");
				suedArabTranslit.put("\uD802\uDE7B","\u1e6f");
				suedArabTranslit.put("\uD802\uDE7C","\u1e93");
				suedArabTranslit.put("\uD802\uDE7D"," ");
				suedArabTranslit.put("\u2227","1");
				suedArabTranslit.put("\uD802\uDE7E","50");
				suedArabTranslit.put(" \u25a7","\u25a7");
				suedArabTranslit.put(" \u25a1","\u25a1");
				suedArabTranslit.put(" \u204b","\u204b");
				suedArabTranslit.put(" \u2026","\u2026");
		
		//Standard-Geez-Buchstaben
		
		//Standard-Geez-Buchstaben
		//ሀ ሁ ሂ ሃ ሄ ህ ሆ
		geezTranslit.put("\u1200", "ha");
		geezTranslit.put("\u1201", "hu");
		geezTranslit.put("\u1202", "hi");
		geezTranslit.put("\u1203", "h\u0101");
		geezTranslit.put("\u1204", "he");
		geezTranslit.put("\u1205", "h");
		geezTranslit.put("\u1206", "ho");
		//ለ ሉ ሊ ላ ሌ ል ሎ
		geezTranslit.put("\u1208", "la");
		geezTranslit.put("\u1209", "lu");
		geezTranslit.put("\u120A", "li");
		geezTranslit.put("\u120B", "l\u0101");
		geezTranslit.put("\u120C", "le");
		geezTranslit.put("\u120D", "l");
		geezTranslit.put("\u120E", "lo");
		//ሐ ሑ ሒ ሓ ሔ ሕ ሖ
		geezTranslit.put("\u1210", "\u1e25a");
		geezTranslit.put("\u1211", "\u1e25u");
		geezTranslit.put("\u1212", "\u1e25i");
		geezTranslit.put("\u1213", "\u1e25\u0101");
		geezTranslit.put("\u1214", "\u1e25e");
		geezTranslit.put("\u1215", "\u1e25");
		geezTranslit.put("\u1216", "\u1e25o");
		//መ ሙ ሚ ማ ሜ ም ሞ
		geezTranslit.put("\u1218", "ma");
		geezTranslit.put("\u1219", "mu");
		geezTranslit.put("\u121A", "mi");
		geezTranslit.put("\u121B", "m\u0101");
		geezTranslit.put("\u121C", "me");
		geezTranslit.put("\u121D", "m");
		geezTranslit.put("\u121E", "mo");
		//ሠ ሡ ሢ ሣ ሤ ሥ ሦ
		geezTranslit.put("\u1220", "\u015ba");
		geezTranslit.put("\u1221", "\u015bu");
		geezTranslit.put("\u1222", "\u015bi");
		geezTranslit.put("\u1223", "\u015b\u0101");
		geezTranslit.put("\u1224", "\u015be");
		geezTranslit.put("\u1225", "\u015b");
		geezTranslit.put("\u1226", "\u015bo");
		//ረ ሩ ሪ ራ ሬ ር ሮ
		geezTranslit.put("\u1228", "ra");
		geezTranslit.put("\u1229", "ru");
		geezTranslit.put("\u122A", "ri");
		geezTranslit.put("\u122B", "r\u0101");
		geezTranslit.put("\u122C", "re");
		geezTranslit.put("\u122D", "r");
		geezTranslit.put("\u122E", "ro");
		//ሰ ሱ ሲ ሳ ሴ ስ ሶ
		geezTranslit.put("\u1230", "sa");
		geezTranslit.put("\u1231", "su");
		geezTranslit.put("\u1232", "si");
		geezTranslit.put("\u1233", "s\u0101");
		geezTranslit.put("\u1234", "se");
		geezTranslit.put("\u1235", "s");
		geezTranslit.put("\u1236", "so");
		//ቀ ቁ ቂ ቃ ቄ ቅ ቆ
		geezTranslit.put("\u1240", "qa");
		geezTranslit.put("\u1241", "qu");
		geezTranslit.put("\u1242", "qi");
		geezTranslit.put("\u1243", "q\u0101");
		geezTranslit.put("\u1244", "qe");
		geezTranslit.put("\u1245", "q");
		geezTranslit.put("\u1246", "qo");
		//በ ቡ ቢ ባ ቤ ብ ቦ
		geezTranslit.put("\u1260", "ba");
		geezTranslit.put("\u1261", "bu");
		geezTranslit.put("\u1262", "bi");
		geezTranslit.put("\u1263", "b\u0101");
		geezTranslit.put("\u1264", "be");
		geezTranslit.put("\u1265", "b");
		geezTranslit.put("\u1266", "bo");
		//ተ ቱ ቲ ታ ቴ ት ቶ
		geezTranslit.put("\u1270", "ta");
		geezTranslit.put("\u1271", "tu");
		geezTranslit.put("\u1272", "ti");
		geezTranslit.put("\u1273", "t\u0101");
		geezTranslit.put("\u1274", "te");
		geezTranslit.put("\u1275", "t");
		geezTranslit.put("\u1276", "to");
		//ኀ ኁ ኂ ኃ ኄ ኅ ኆ
		geezTranslit.put("\u1280", "\u1e2ba");
		geezTranslit.put("\u1281", "\u1e2bu");
		geezTranslit.put("\u1282", "\u1e2bi");
		geezTranslit.put("\u1283", "\u1e2b\u0101");
		geezTranslit.put("\u1284", "\u1e2be");
		geezTranslit.put("\u1285", "\u1e2b");
		geezTranslit.put("\u1286", "\u1e2bo");
		//ነ ኑ ኒ ና ኔ ን ኖ
		geezTranslit.put("\u1290", "na");
		geezTranslit.put("\u1291", "nu");
		geezTranslit.put("\u1292", "ni");
		geezTranslit.put("\u1293", "n\u0101");
		geezTranslit.put("\u1294", "ne");
		geezTranslit.put("\u1295", "n");
		geezTranslit.put("\u1296", "no");
		//አ ኡ ኢ ኣ ኤ እ ኦ
		geezTranslit.put("\u12A0", "\u02bea");
		geezTranslit.put("\u12A1", "\u02beu");
		geezTranslit.put("\u12A2", "\u02bei");
		geezTranslit.put("\u12A3", "\u02be\u0101");
		geezTranslit.put("\u12A4", "\u02BEe");
		geezTranslit.put("\u12A5", "\u02be");
		geezTranslit.put("\u12A6", "\u02beo");
		//ከ ኩ ኪ ካ ኬ ክ ኮ
		geezTranslit.put("\u12A8", "ka");
		geezTranslit.put("\u12A9", "ku");
		geezTranslit.put("\u12AA", "ki");
		geezTranslit.put("\u12AB", "k\u0101");
		geezTranslit.put("\u12AC", "ke");
		geezTranslit.put("\u12AD", "k");
		geezTranslit.put("\u12AE", "ko");
		//ወ ዉ ዊ ዋ ዌ ው ዎ
		geezTranslit.put("\u12C8", "wa");
		geezTranslit.put("\u12C9", "wu");
		geezTranslit.put("\u12CA", "wi");
		geezTranslit.put("\u12CB", "w\u0101");
		geezTranslit.put("\u12CC", "we");
		geezTranslit.put("\u12CD", "w");
		geezTranslit.put("\u12CE", "wo");
		//ዐ ዑ ዒ ዓ ዔ ዕ ዖ
		geezTranslit.put("\u12D0", "\u02bfa");
		geezTranslit.put("\u12D1", "\u02bfu");
		geezTranslit.put("\u12D2", "\u02bfi");
		geezTranslit.put("\u12D3", "\u02bf\u0101");
		geezTranslit.put("\u12D4", "\u02bfe");
		geezTranslit.put("\u12D5", "\u02bf");
		geezTranslit.put("\u12D6", "\u02bfo");
		//ዘ ዙ ዚ ዛ ዜ ዝ ዞ
		geezTranslit.put("\u12D8", "za");
		geezTranslit.put("\u12D9", "zu");
		geezTranslit.put("\u12DA", "zi");
		geezTranslit.put("\u12DB", "z\u0101");
		geezTranslit.put("\u12DC", "ze");
		geezTranslit.put("\u12DD", "z");
		geezTranslit.put("\u12DE", "zo");
		//የ ዩ ዪ ያ ዬ ይ ዮ
		geezTranslit.put("\u12E8", "ya");
		geezTranslit.put("\u12E9", "yu");
		geezTranslit.put("\u12EA", "yi");
		geezTranslit.put("\u12EB", "y\u0101");
		geezTranslit.put("\u12EC", "ye");
		geezTranslit.put("\u12ED", "y");
		geezTranslit.put("\u12EE", "yo");
		//ደ ዱ ዲ ዳ ዴ ድ ዶ
		geezTranslit.put("\u12F0", "da");
		geezTranslit.put("\u12F1", "du");
		geezTranslit.put("\u12F2", "di");
		geezTranslit.put("\u12F3", "d\u0101");
		geezTranslit.put("\u12F4", "de");
		geezTranslit.put("\u12F5", "d");
		geezTranslit.put("\u12F6", "do");
		//ገ ጉ ጊ ጋ ጌ ግ ጎ
		geezTranslit.put("\u1308", "ga");
		geezTranslit.put("\u1309", "gu");
		geezTranslit.put("\u130A", "gi");
		geezTranslit.put("\u130B", "g\u0101");
		geezTranslit.put("\u130C", "ge");
		geezTranslit.put("\u130D", "g");
		geezTranslit.put("\u130E", "go");
		//ጠ ጡ ጢ ጣ ጤ ጥ ጦ
		geezTranslit.put("\u1320", "\u1e6da");
		geezTranslit.put("\u1321", "\u1e6du");
		geezTranslit.put("\u1322", "\u1e6di");
		geezTranslit.put("\u1323", "\u1e6d\u0101");
		geezTranslit.put("\u1324", "\u1e6de");
		geezTranslit.put("\u1325", "\u1e6d");
		geezTranslit.put("\u1326", "\u1e6do");
		//ጰ ጱ ጲ ጳ ጴ ጵ ጶ
		geezTranslit.put("\u1330", "\u1e57a");
		geezTranslit.put("\u1331", "\u1e57u");
		geezTranslit.put("\u1332", "\u1e57i");
		geezTranslit.put("\u1333", "\u1e57\u0101");
		geezTranslit.put("\u1334", "\u1e57e");
		geezTranslit.put("\u1335", "\u1e57");
		geezTranslit.put("\u1336", "\u1e57o");
		//ጸ ጹ ጺ ጻ ጼ ጽ ጾ
		geezTranslit.put("\u1338", "\u1e63a");
		geezTranslit.put("\u1339", "\u1e63u");
		geezTranslit.put("\u133A", "\u1e63i");
		geezTranslit.put("\u133B", "\u1e63\u0101");
		geezTranslit.put("\u133C", "\u1e63e");
		geezTranslit.put("\u133D", "\u1e63");
		geezTranslit.put("\u133E", "\u1e63o");
		//ፀ ፁ ፂ ፃ ፄ ፅ ፆ
		geezTranslit.put("\u1340", "\u1e0da");
		geezTranslit.put("\u1341", "\u1e0du");
		geezTranslit.put("\u1342", "\u1e0di");
		geezTranslit.put("\u1343", "\u1e0d\u0101");
		geezTranslit.put("\u1344", "\u1e0de");
		geezTranslit.put("\u1345", "\u1e0d");
		geezTranslit.put("\u1346", "\u1e0do");
		//ፈ ፉ ፊ ፋ ፌ ፍ ፎ
		geezTranslit.put("\u1348", "fa");
		geezTranslit.put("\u1349", "fu");
		geezTranslit.put("\u134A", "fi");
		geezTranslit.put("\u134B", "f\u0101");
		geezTranslit.put("\u134C", "fe");
		geezTranslit.put("\u134D", "f");
		geezTranslit.put("\u134E", "fo");
		//ፐ ፑ ፒ ፓ ፔ ፕ ፖ
		geezTranslit.put("\u1350", "pa");
		geezTranslit.put("\u1351", "pu");
		geezTranslit.put("\u1352", "pi");
		geezTranslit.put("\u1353", "p\u0101");
		geezTranslit.put("\u1354", "pe");
		geezTranslit.put("\u1355", "p");
		geezTranslit.put("\u1356", "po");
		//Labiovelare
		//ቈ ቊ ቋ ቌ ቍ
		geezTranslit.put("\u1248", "q\u02b7a");
		geezTranslit.put("\u124A", "q\u02b7i");
		geezTranslit.put("\u124B", "q\u02b7\u0101");
		geezTranslit.put("\u124C", "q\u02b7e");
		geezTranslit.put("\u124D", "q\u02b7");
		//ኈ ኊ ኋ ኌ ኍ
		geezTranslit.put("\u1288", "\u1e2b\u02b7a");
		geezTranslit.put("\u128A", "\u1e2b\u02b7i");
		geezTranslit.put("\u128B", "\u1e2b\u02b7\u0101");
		geezTranslit.put("\u128C", "\u1e2b\u02b7e");
		geezTranslit.put("\u128D", "\u1e2b\u02b7");
		//ኰ ኲ ኳ ኴ ኵ
		geezTranslit.put("\u12B0", "k\u02b7a");
		geezTranslit.put("\u12B2", "k\u02b7i");
		geezTranslit.put("\u12B3", "k\u02b7\u0101");
		geezTranslit.put("\u12B4", "k\u02b7e");
		geezTranslit.put("\u12B5", "k\u02b7");
		//ጐ ጒ ጓ ጔ ጕ
		geezTranslit.put("\u1310", "g\u02b7a");
		geezTranslit.put("\u1312", "g\u02b7i");
		geezTranslit.put("\u1313", "g\u02b7\u0101");
		geezTranslit.put("\u1314", "g\u02b7e");
		geezTranslit.put("\u1315", "g\u02b7");
		//Zahlzeichen
		//Interpunktion
		//amharische und tigrinische Buchstaben
		//seltene Buchstaben

		//punctuation
		//sectionmark
		geezTranslit.put("\u1360", "."); 
		//word sepparator
		geezTranslit.put("\u1361", " "); 
		geezTranslit.put("\u1362", "."); 
		geezTranslit.put("\u1363", ","); 
		geezTranslit.put("\u1364", ";");
		geezTranslit.put("\u1365", ","); 
		geezTranslit.put("\u1367", "?"); 
		geezTranslit.put("\u1368", "."); 
		
		
		//Zahlzeichen
		//፩ ፪ ፫ ፬ ፭ ፮ ፯ ፰ ፱ ፲ ፳ ፴ ፵ ፶ ፷ ፸ ፹ ፺ ፻ ፼
		geezTranslit.put("\u1369", "1");
		geezTranslit.put("\u136A", "2");
		geezTranslit.put("\u136B", "3");
		geezTranslit.put("\u136C", "4");
		geezTranslit.put("\u136D", "5");
		geezTranslit.put("\u136E", "6");
		geezTranslit.put("\u136F", "7");
		geezTranslit.put("\u1370", "8");
		geezTranslit.put("\u1371", "9");
		geezTranslit.put("\u1372", "10");
		geezTranslit.put("\u1373", "20");
		geezTranslit.put("\u1374", "30");
		geezTranslit.put("\u1375", "40");
		geezTranslit.put("\u1376", "50");
		geezTranslit.put("\u1377", "60");
		geezTranslit.put("\u1378", "70");
		geezTranslit.put("\u1379", "80");
		geezTranslit.put("\u137A", "90");
		geezTranslit.put("\u137B", "100");
		geezTranslit.put("\u137C", "10000");
		//Interpunktion
		//amharische und tigrinische Buchstaben
		//ሸ ሹ ሺ ሻ ሼ ሽ ሾ
		geezTranslit.put("\u1238", "\u0161a");
		geezTranslit.put("\u1239", "\u0161u");
		geezTranslit.put("\u123A", "\u0161i");
		geezTranslit.put("\u123B", "\u0161\u0101");
		geezTranslit.put("\u123C", "\u0161e");
		geezTranslit.put("\u123D", "\u0161");
		geezTranslit.put("\u123E", "\u0161o");
		//ቐ ቑ ቒ ቓ ቔ ቕ ቖ
		//geezTranslit.put("\u1250",);
		//geezTranslit.put("\u1251",);
		//geezTranslit.put("\u1252",);
		//geezTranslit.put("\u1253",);
		//geezTranslit.put("\u1254",);
		//geezTranslit.put("\u1255",);
		//geezTranslit.put("\u1256",);
		//ቘ ቚ ቛ ቜ ቝ
		//geezTranslit.put("\u1258",);
		//geezTranslit.put("\u125A",);
		//geezTranslit.put("\u125B",);
		//geezTranslit.put("\u125C",);
		//geezTranslit.put("\u125D",);
		//ቨ ቩ ቪ ቫ ቬ ቭ ቮ
		geezTranslit.put("\u1268", "va");
		geezTranslit.put("\u1269", "vu");
		geezTranslit.put("\u126A", "vi");
		geezTranslit.put("\u126B", "v\u0101");
		geezTranslit.put("\u126C", "ve");
		geezTranslit.put("\u126D", "v");
		geezTranslit.put("\u126E", "vo");
		//ቸ ቹ ቺ ቻ ቼ ች ቾ
		geezTranslit.put("\u1278", "\u010Da");
		geezTranslit.put("\u1279", "\u010Du");
		geezTranslit.put("\u127A", "\u010Di");
		geezTranslit.put("\u127B", "\u010D\u0101");
		geezTranslit.put("\u127C", "\u010De");
		geezTranslit.put("\u127D", "\u010D");
		geezTranslit.put("\u127E", "\u010Do");
		//ኘ ኙ ኚ ኛ ኜ ኝ ኞ
		geezTranslit.put("\u1298", "\u00F1a");
		geezTranslit.put("\u1299", "\u00F1u");
		geezTranslit.put("\u129A", "\u00F1i");
		geezTranslit.put("\u129B", "\u00F1\u0101");
		geezTranslit.put("\u129C", "\u00F1e");
		geezTranslit.put("\u129D", "\u00F1");
		geezTranslit.put("\u129E", "\u00F1o");
		//ኸ ኹ ኺ ኻ ኼ ኽ ኾ
		geezTranslit.put("\u12B8", "\u1E35a");
		geezTranslit.put("\u12B9", "\u1E35u");
		geezTranslit.put("\u12BA", "\u1E35i");
		geezTranslit.put("\u12BB", "\u1E35\u0101");
		geezTranslit.put("\u12BC", "\u1E35e");
		geezTranslit.put("\u12BD", "\u1E35");
		geezTranslit.put("\u12BE", "\u1E35o");
		//ዀ ዂ ዃ ዄ ዅ
		geezTranslit.put("\u12C0", "\u1e35\u02b7a");
		geezTranslit.put("\u12C2", "\u1e35\u02b7i");
		geezTranslit.put("\u12C3", "\u1e35\u02b7\u0101");
		geezTranslit.put("\u12C4", "\u1e35\u02b7e");
		geezTranslit.put("\u12C5", "\u1e35\u02b7");
		//ዠ ዡ ዢ ዣ ዤ ዥ ዦ
		geezTranslit.put("\u12E0", "\u017Ea");
		geezTranslit.put("\u12E1", "\u017Eu");
		geezTranslit.put("\u12E2", "\u017Ei");
		geezTranslit.put("\u12E3", "\u017E\u0101");
		geezTranslit.put("\u12E4", "\u017Ee");
		geezTranslit.put("\u12E5", "\u017E");
		geezTranslit.put("\u12E6", "\u017Eo");
		//ጀ ጁ ጂ ጃ ጄ ጅ ጆ
		geezTranslit.put("\u1300", "\u01E7a");
		geezTranslit.put("\u1301", "\u01E7u");
		geezTranslit.put("\u1302", "\u01E7i");
		geezTranslit.put("\u1303", "\u01E7\u0101");
		geezTranslit.put("\u1304", "\u01E7e");
		geezTranslit.put("\u1305", "\u01E7");
		geezTranslit.put("\u1306", "\u01E7o");
		//ጨ ጩ ጪ ጫ ጬ ጭ ጮ
		geezTranslit.put("\u1328", "\u1E09a");
		geezTranslit.put("\u1329", "\u1E09u");
		geezTranslit.put("\u132A", "\u1E09i");
		geezTranslit.put("\u132B", "\u1E09\u0101");
		geezTranslit.put("\u132C", "\u1E09e");
		geezTranslit.put("\u132D", "\u1E09");
		geezTranslit.put("\u132E", "\u1E09o");
		// symbols for unknown chars
		geezTranslit.put("\u25A7", "\u25A7");
		geezTranslit.put("\u25A1", "\u25A1");
		geezTranslit.put("\u204b", "\u204b");
		geezTranslit.put("\u2026", "\u2026");
		//seltene Buchstaben
		//geezTranslitN.put("\u12c8", "0");

		qw.put("g\u02b7", "G");
		qw.put("q\u02b7", "Q");
		qw.put("k\u02b7", "K");
		qw.put("\u1e2b\u02b7", "X");
		
	String[] haVariants={"\u1200","\u1210","\u1280","\u1213","\u1283","\u1203"};
	String[] huVariants={"\u1201", "\u1211","\u1281"};
	String[] hiVariants={"\u1202", "\u1212","\u1282"};
	String[] habarVariants={"\u1203", "\u1213","\u1283"};
	String[] habaraVariants={"\u1200","\u1210","\u1280","\u1203", "\u1213","\u1283"};
	String[] heVariants={"\u1204", "\u1214","\u1284"};
	String[] hVariants={"\u1205", "\u1215","\u1285"};
	String[] hoVariants={"\u1206", "\u1216","\u1286"};
	String[] hauVariants={"\u1200","\u1210","\u1280","\u1213","\u1283","\u1203","\u1201", "\u1211","\u1281"};
	String[] saVariants={"\u1220","\u1230"};
	String[] suVariants={"\u1221","\u1231"};
	String[] siVariants={"\u1222","\u1232"};
	String[] sabarVariants={"\u1223","\u1233"};
	String[] seVariants={"\u1224","\u1234"};
	String[] sVariants={"\u1225","\u1235"};
	String[] soVariants={"\u1226","\u1236"};
	String[] commaCloseaVariants={"\u12a0","\u12d0","\u12A3","\u12D3"};
	String[] commaCloseuVariants={"\u12a1","\u12d1"};
	String[] commaCloseiVariants={"\u12a2","\u12d2"};
	String[] commaCloseabarVariants={"\u12a3","\u12d3"};
	String[] commaCloseVariants={"\u12a4","\u12d4"};
	String[] commaCloseeVariants={"\u12a5","\u12d5"};
	String[] commaCloseoVariants={"\u12a6","\u12d6"};
	String[] sPunktUntenaVariants={"\u1338","\u1340"};
	String[] sPunktUntenuVariants={"\u1339","\u1341"};
	String[] sPunktUnteniVariants={"\u133A","\u1342"};
	String[] sPunktUntenabarVariants={"\u133B","\u1343"};
	String[] sPunktUnteneVariants={"\u133C","\u1344"};
	String[] sPunktUntenVariants={"\u133D","\u1345"};
	String[] sPunktUntenoVariants={"\u133E","\u1346"};
	//String[] sPunktUnten1Variants={"\u1338"};
	//Map <String, String[]> geezHomophone = new Hashtable<String, String[]>();
	geezHomophone.put("\u1200", haVariants);
	geezHomophone.put("\u1213", habaraVariants);
	geezHomophone.put("\u1283", hauVariants);
	geezHomophone.put("\u1203", habaraVariants);
	geezHomophone.put("\u1210", haVariants);
	geezHomophone.put("\u1280", haVariants);
	geezHomophone.put("\u1201", huVariants);
	geezHomophone.put("\u1211", huVariants);
	geezHomophone.put("\u1281", huVariants);
	geezHomophone.put("\u1202", hiVariants);
	geezHomophone.put("\u1212", hiVariants);
	geezHomophone.put("\u1282", hiVariants);
	geezHomophone.put("\u1204", heVariants);
	geezHomophone.put("\u1214", heVariants);
	geezHomophone.put("\u1284", heVariants);
	geezHomophone.put("\u1205", hVariants);
	geezHomophone.put("\u1215", hVariants);
	geezHomophone.put("\u1285", hVariants);
	geezHomophone.put("\u1206", hoVariants);
	geezHomophone.put("\u1216", hoVariants);
	geezHomophone.put("\u1286", hoVariants);
	geezHomophone.put("\u1220", saVariants);
	geezHomophone.put("\u1230", saVariants);
	geezHomophone.put("\u1221", suVariants);
	geezHomophone.put("\u1231", suVariants);
	geezHomophone.put("\u1222", siVariants);
	geezHomophone.put("\u1232", siVariants);
	geezHomophone.put("\u1223", sabarVariants);
	geezHomophone.put("\u1233", sabarVariants);
	geezHomophone.put("\u1224", seVariants);
	geezHomophone.put("\u1234", seVariants);
	geezHomophone.put("\u1225", sVariants);
	geezHomophone.put("\u1235", sVariants);
	geezHomophone.put("\u1226", soVariants);
	geezHomophone.put("\u1236", soVariants);
	geezHomophone.put("\u12A0", commaCloseaVariants);
	geezHomophone.put("\u12A3", commaCloseaVariants);
	geezHomophone.put("\u12D0", commaCloseaVariants);
	geezHomophone.put("\u12D3", commaCloseaVariants);
	geezHomophone.put("\u12A1", commaCloseuVariants);
	geezHomophone.put("\u12D1", commaCloseuVariants);
	geezHomophone.put("\u12A2", commaCloseiVariants);
	geezHomophone.put("\u12D2", commaCloseiVariants);
	geezHomophone.put("\u12A3", commaCloseabarVariants);
	geezHomophone.put("\u12D3", commaCloseabarVariants);
	geezHomophone.put("\u12A5", commaCloseeVariants);
	geezHomophone.put("\u12D5", commaCloseeVariants);
	geezHomophone.put("\u12A4", commaCloseVariants);
	geezHomophone.put("\u12D4", commaCloseVariants);
	geezHomophone.put("\u12A6", commaCloseoVariants);
	geezHomophone.put("\u12D6", commaCloseoVariants);
	geezHomophone.put("\u1338", sPunktUntenaVariants);
	geezHomophone.put("\u1340", sPunktUntenaVariants);
	geezHomophone.put("\u1339", sPunktUntenuVariants);
	geezHomophone.put("\u1341", sPunktUntenuVariants);
	geezHomophone.put("\u133A", sPunktUnteniVariants);
	geezHomophone.put("\u1342", sPunktUnteniVariants);
	geezHomophone.put("\u133B", sPunktUntenabarVariants);
	geezHomophone.put("\u1343", sPunktUntenabarVariants);
	geezHomophone.put("\u133C", sPunktUnteneVariants);
	geezHomophone.put("\u1344", sPunktUnteneVariants);
	geezHomophone.put("\u133D", sPunktUntenVariants);
	geezHomophone.put("\u1345", sPunktUntenVariants);
	geezHomophone.put("\u133E", sPunktUntenoVariants);
	geezHomophone.put("\u1346", sPunktUntenoVariants);
	
	// Reverse
	
	geezTranslitR.put("ha","\u1200");
	geezTranslitR.put("hu","\u1201");
	geezTranslitR.put("hi","\u1202");
	geezTranslitR.put("h\u0101","\u1203");
	geezTranslitR.put("he","\u1204");
	geezTranslitR.put("h","\u1205");
	geezTranslitR.put("h\u01dd","\u1205");
	geezTranslitR.put("ho","\u1206");
	//ለ ሉ ሊ ላ ሌ ል ሎ
	geezTranslitR.put("la","\u1208");
	geezTranslitR.put("lu","\u1209");
	geezTranslitR.put("li","\u120A");
	geezTranslitR.put("l\u0101","\u120B");
	geezTranslitR.put("le","\u120C");
	geezTranslitR.put("l","\u120D");
	geezTranslitR.put("l\u01dd","\u120D");
	geezTranslitR.put("lo","\u120E");
	//ሐ ሑ ሒ ሓ ሔ ሕ ሖ
	geezTranslitR.put("\u1e25a","\u1210" );
	geezTranslitR.put("\u1e25u", "\u1211");
	geezTranslitR.put("\u1e25i","\u1212");
	geezTranslitR.put( "\u1e25\u0101","\u1213");
	geezTranslitR.put("\u1e25e","\u1214");
	geezTranslitR.put("\u1e25","\u1215");
	geezTranslitR.put("\u1e25\u01dd","\u1215");
	geezTranslitR.put("\u1e25o","\u1216");
	//መ ሙ ሚ ማ ሜ ም ሞ
	geezTranslitR.put("ma","\u1218");
	geezTranslitR.put("mu","\u1219");
	geezTranslitR.put("mi","\u121A");
	geezTranslitR.put("m\u0101","\u121B");
	geezTranslitR.put("me","\u121C");
	geezTranslitR.put("m","\u121D");
	geezTranslitR.put("m\u01dd","\u121D");
	geezTranslitR.put("mo","\u121E");
	//ሠ ሡ ሢ ሣ ሤ ሥ ሦ
	geezTranslitR.put( "\u015ba","\u1220");
	geezTranslitR.put("\u015bu","\u1221");
	geezTranslitR.put("\u015bi","\u1222");
	geezTranslitR.put("\u015b\u0101","\u1223");
	geezTranslitR.put("\u015be","\u1224");
	geezTranslitR.put("\u015b","\u1225");
	geezTranslitR.put("\u015b\u01dd","\u1225");
	geezTranslitR.put("\u015bo","\u1226");
	//ረ ሩ ሪ ራ ሬ ር ሮ
	geezTranslitR.put("ra","\u1228");
	geezTranslitR.put("ru","\u1229");
	geezTranslitR.put("ri","\u122A");
	geezTranslitR.put("r\u0101","\u122B");
	geezTranslitR.put("re","\u122C");
	geezTranslitR.put("r","\u122D");
	geezTranslitR.put("r\u01dd","\u122D");
	geezTranslitR.put("ro", "\u122E");
	//ሰ ሱ ሲ ሳ ሴ ስ ሶ
	geezTranslitR.put("sa","\u1230");
	geezTranslitR.put("su","\u1231");
	geezTranslitR.put("si","\u1232");
	geezTranslitR.put("s\u0101","\u1233");
	geezTranslitR.put("se","\u1234");
	geezTranslitR.put("s","\u1235");
	geezTranslitR.put("s\u01dd","\u1235");
	geezTranslitR.put("so","\u1236");
	//ቀ ቁ ቂ ቃ ቄ ቅ ቆ
	geezTranslitR.put("qa","\u1240");
	geezTranslitR.put("qu","\u1241");
	geezTranslitR.put( "qi","\u1242");
	geezTranslitR.put( "q\u0101","\u1243");
	geezTranslitR.put("qe","\u1244");
	geezTranslitR.put("q","\u1245");
	geezTranslitR.put("q\u01dd","\u1245");
	geezTranslitR.put("qo","\u1246");
	//በ ቡ ቢ ባ ቤ ብ ቦ
	geezTranslitR.put("ba","\u1260");
	geezTranslitR.put("bu","\u1261");
	geezTranslitR.put("bi","\u1262");
	geezTranslitR.put("b\u0101","\u1263");
	geezTranslitR.put( "be","\u1264");
	geezTranslitR.put("b","\u1265");
	geezTranslitR.put("b\u01dd","\u1265");
	geezTranslitR.put("bo", "\u1266");
	//ተ ቱ ቲ ታ ቴ ት ቶ
	geezTranslitR.put("ta","\u1270");
	geezTranslitR.put("tu","\u1271");
	geezTranslitR.put("ti","\u1272");
	geezTranslitR.put("t\u0101","\u1273");
	geezTranslitR.put("te","\u1274");
	geezTranslitR.put("t","\u1275");
	geezTranslitR.put("t\u01dd","\u1275");
	geezTranslitR.put("to","\u1276");
	//ኀ ኁ ኂ ኃ ኄ ኅ ኆ
	geezTranslitR.put("\u1e2ba","\u1280");
	geezTranslitR.put("\u1e2bu","\u1281");
	geezTranslitR.put("\u1e2bi","\u1282");
	geezTranslitR.put("\u1e2b\u0101","\u1283");
	geezTranslitR.put("\u1e2be","\u1284");
	geezTranslitR.put("\u1e2b","\u1285");
	geezTranslitR.put("\u1e2b\u01dd","\u1285");
	geezTranslitR.put("\u1e2bo","\u1286");
	//ነ ኑ ኒ ና ኔ ን ኖ
	geezTranslitR.put("na","\u1290");
	geezTranslitR.put("nu","\u1291");
	geezTranslitR.put( "ni","\u1292");
	geezTranslitR.put("n\u0101","\u1293");
	geezTranslitR.put("ne","\u1294");
	geezTranslitR.put("n","\u1295");
	geezTranslitR.put("n\u01dd","\u1295");
	geezTranslitR.put("no","\u1296");
	//አ ኡ ኢ ኣ ኤ እ ኦ
	geezTranslitR.put("\u02bea","\u12A0");
	geezTranslitR.put("\u02beu","\u12A1");
	geezTranslitR.put("\u02bei","\u12A2");
	geezTranslitR.put("\u02be\u0101","\u12A3");
	geezTranslitR.put("\u02BEe","\u12A4");
	geezTranslitR.put("\u02be","\u12A5");
	geezTranslitR.put("\u02be\u01dd","\u12A5");
	geezTranslitR.put("\u02beo","\u12A6");
	//ከ ኩ ኪ ካ ኬ ክ ኮ
	geezTranslitR.put("ka","\u12A8");
	geezTranslitR.put("ku","\u12A9");
	geezTranslitR.put("ki","\u12AA");
	geezTranslitR.put("k\u0101","\u12AB");
	geezTranslitR.put("ke","\u12AC");
	geezTranslitR.put("k","\u12AD");
	geezTranslitR.put("k\u01dd","\u12AD");
	geezTranslitR.put( "ko","\u12AE");
	//ወ ዉ ዊ ዋ ዌ ው ዎ
	geezTranslitR.put("wa","\u12C8");
	geezTranslitR.put("wu","\u12C9");
	geezTranslitR.put("wi","\u12CA");
	geezTranslitR.put("w\u0101","\u12CB");
	geezTranslitR.put( "we","\u12CC");
	geezTranslitR.put("w","\u12CD");
	geezTranslitR.put("w\u01dd","\u12CD");
	geezTranslitR.put("wo","\u12CE");
	//ዐ ዑ ዒ ዓ ዔ ዕ ዖ
	geezTranslitR.put("\u02bfa","\u12D0");
	geezTranslitR.put("\u02bfu","\u12D1");
	geezTranslitR.put("\u02bfi","\u12D2");
	geezTranslitR.put("\u02bf\u0101","\u12D3");
	geezTranslitR.put("\u02bfe","\u12D4");
	geezTranslitR.put("\u02bf","\u12D5");
	geezTranslitR.put("\u02bf\u01dd","\u12D5");
	geezTranslitR.put("\u02bfo","\u12D6");
	//ዘ ዙ ዚ ዛ ዜ ዝ ዞ
	geezTranslitR.put("za","\u12D8");
	geezTranslitR.put("zu","\u12D9");
	geezTranslitR.put("zi","\u12DA");
	geezTranslitR.put("z\u0101","\u12DB");
	geezTranslitR.put("ze","\u12DC");
	geezTranslitR.put("z","\u12DD");
	geezTranslitR.put("z\u01dd","\u12DD");
	geezTranslitR.put("zo","\u12DE");
	//የ ዩ ዪ ያ ዬ ይ ዮ
	geezTranslitR.put("ya","\u12E8");
	geezTranslitR.put("yu","\u12E9");
	geezTranslitR.put("yi","\u12EA");
	geezTranslitR.put("y\u0101","\u12EB");
	geezTranslitR.put("ye","\u12EC");
	geezTranslitR.put("y","\u12ED");
	geezTranslitR.put("y\u01dd","\u12ED");
	geezTranslitR.put("yo","\u12EE");
	//ደ ዱ ዲ ዳ ዴ ድ ዶ
	geezTranslitR.put("da","\u12F0");
	geezTranslitR.put("du","\u12F1");
	geezTranslitR.put("di","\u12F2");
	geezTranslitR.put("d\u0101","\u12F3");
	geezTranslitR.put("de","\u12F4");
	geezTranslitR.put("d","\u12F5");
	geezTranslitR.put("d\u01dd","\u12F5");
	geezTranslitR.put("do","\u12F6");
	//ገ ጉ ጊ ጋ ጌ ግ ጎ
	geezTranslitR.put( "ga","\u1308");
	geezTranslitR.put("gu","\u1309" );
	geezTranslitR.put( "gi","\u130A");
	geezTranslitR.put("g\u0101","\u130B");
	geezTranslitR.put("ge","\u130C");
	geezTranslitR.put("g","\u130D");
	geezTranslitR.put("g\u01dd","\u130D");
	geezTranslitR.put("go","\u130E");
	//ጠ ጡ ጢ ጣ ጤ ጥ ጦ
	geezTranslitR.put("\u1e6da","\u1320");
	geezTranslitR.put("\u1e6du","\u1321");
	geezTranslitR.put("\u1e6di","\u1322");
	geezTranslitR.put("\u1e6d\u0101","\u1323");
	geezTranslitR.put("\u1e6de","\u1324");
	geezTranslitR.put("\u1e6d","\u1325");
	geezTranslitR.put("\u1e6d\u01dd","\u1325");
	geezTranslitR.put("\u1e6do","\u1326");
	//ጰ ጱ ጲ ጳ ጴ ጵ ጶ
	geezTranslitR.put("\u1e57a","\u1330");
	geezTranslitR.put("\u1e57u","\u1331");
	geezTranslitR.put("\u1e57i","\u1332");
	geezTranslitR.put("\u1e57\u0101","\u1333");
	geezTranslitR.put("\u1e57e","\u1334");
	geezTranslitR.put("\u1e57","\u1335");
	geezTranslitR.put("\u1e57\u01dd","\u1335");
	geezTranslitR.put("\u1e57o","\u1336");
	//ጸ ጹ ጺ ጻ ጼ ጽ ጾ
	geezTranslitR.put("\u1e63a","\u1338");
	geezTranslitR.put("\u1e63u","\u1339");
	geezTranslitR.put("\u1e63i","\u133A");
	geezTranslitR.put("\u1e63\u0101","\u133B");
	geezTranslitR.put("\u1e63e","\u133C");
	geezTranslitR.put("\u1e63","\u133D");
	geezTranslitR.put("\u1e63\u01dd","\u133D");
	geezTranslitR.put("\u1e63o","\u133E");
	//ፀ ፁ ፂ ፃ ፄ ፅ ፆ
	geezTranslitR.put("\u1e0da","\u1340");
	geezTranslitR.put("\u1e0du","\u1341");
	geezTranslitR.put("\u1e0di","\u1342");
	geezTranslitR.put("\u1e0d\u0101","\u1343");
	geezTranslitR.put("\u1e0de","\u1344");
	geezTranslitR.put("\u1e0d","\u1345");
	geezTranslitR.put("\u1e0d\u01dd","\u1345");
	geezTranslitR.put("\u1e0do","\u1346");
	//ፈ ፉ ፊ ፋ ፌ ፍ ፎ
	geezTranslitR.put("fa","\u1348");
	geezTranslitR.put("fu","\u1349" );
	geezTranslitR.put("fi","\u134A");
	geezTranslitR.put("f\u0101","\u134B");
	geezTranslitR.put( "fe","\u134C");
	geezTranslitR.put("f","\u134D");
	geezTranslitR.put("f\u01dd","\u134D");
	geezTranslitR.put("fo","\u134E");
	//ፐ ፑ ፒ ፓ ፔ ፕ ፖ
	geezTranslitR.put("pa","\u1350");
	geezTranslitR.put("pu","\u1351");
	geezTranslitR.put("pi","\u1352");
	geezTranslitR.put("p\u0101","\u1353");
	geezTranslitR.put("pe","\u1354");
	geezTranslitR.put("p","\u1355" );
	geezTranslitR.put("p\u01dd","\u1355" );
	geezTranslitR.put("po","\u1356");
	//Labiovelare
	//ቈ ቊ ቋ ቌ ቍ
	geezTranslitR.put("q\u02b7a","\u1248");
	geezTranslitR.put( "q\u02b7i","\u124A");
	geezTranslitR.put("q\u02b7\u0101","\u124B");
	geezTranslitR.put( "q\u02b7e","\u124C");
	geezTranslitR.put("q\u02b7","\u124D");
	geezTranslitR.put("q\u02b7\u01dd","\u124D");
	//ኈ ኊ ኋ ኌ ኍ
	geezTranslitR.put("\u1e2b\u02b7a","\u1288");
	geezTranslitR.put("\u1e2b\u02b7i","\u128A");
	geezTranslitR.put("\u1e2b\u02b7\u0101","\u128B");
	geezTranslitR.put("\u1e2b\u02b7e","\u128C");
	geezTranslitR.put("\u1e2b\u02b7","\u128D");
	geezTranslitR.put("\u1e2b\u02b7\u01dd","\u128D");
	//ኰ ኲ ኳ ኴ ኵ
	geezTranslitR.put("k\u02b7a","\u12B0");
	geezTranslitR.put("k\u02b7i","\u12B2");
	geezTranslitR.put( "k\u02b7\u0101","\u12B3");
	geezTranslitR.put("k\u02b7e","\u12B4");
	geezTranslitR.put("k\u02b7","\u12B5");
	geezTranslitR.put("k\u02b7\u01dd","\u12B5");
	//ጐ ጒ ጓ ጔ ጕ
	geezTranslitR.put("g\u02b7a","\u1310");
	geezTranslitR.put("g\u02b7i","\u1312");
	geezTranslitR.put("g\u02b7\u0101","\u1313");
	geezTranslitR.put("g\u02b7e","\u1314");
	geezTranslitR.put("g\u02b7","\u1315");
	geezTranslitR.put("g\u02b7\u01dd","\u1315");
	//Zahlzeichen
	//Interpunktion
	//amharische und tigrinische Buchstaben
	//seltene Buchstaben

	//punctuation
	//sectionmark
	geezTranslit.put(".","\u1360"); 
	//word sepparator
	geezTranslitR.put(" ","\u1361"); 
	geezTranslitR.put(".","\u1362"); 
	geezTranslitR.put( ",","\u1363"); 
	geezTranslitR.put(";","\u1364");
	geezTranslitR.put( ",","\u1365"); 
	geezTranslitR.put("?","\u1367"); 
	//geezTranslitR.put(".","\u1368"); 
	
	
	//Zahlzeichen
	//፩ ፪ ፫ ፬ ፭ ፮ ፯ ፰ ፱ ፲ ፳ ፴ ፵ ፶ ፷ ፸ ፹ ፺ ፻ ፼
	geezTranslitR.put("1","\u1369");
	geezTranslitR.put("2","\u136A");
	geezTranslitR.put("3","\u136B");
	geezTranslitR.put("4","\u136C");
	geezTranslitR.put("5","\u136D");
	geezTranslitR.put( "6","\u136E");
	geezTranslitR.put("7","\u136F");
	geezTranslitR.put("8","\u1370");
	geezTranslitR.put("9","\u1371");
	geezTranslitR.put("10","\u1372");
	geezTranslitR.put("20","\u1373");
	geezTranslitR.put("30","\u1374");
	geezTranslitR.put("40","\u1375");
	geezTranslitR.put("50","\u1376");
	geezTranslitR.put("60","\u1377");
	geezTranslitR.put("70","\u1378");
	geezTranslitR.put("80","\u1379");
	geezTranslitR.put( "90","\u137A");
	geezTranslitR.put("100","\u137B");
	geezTranslitR.put("10000","\u137C");
	//Interpunktion
	//amharische und tigrinische Buchstaben
	//ሸ ሹ ሺ ሻ ሼ ሽ ሾ
	geezTranslitR.put("\u0161a","\u1238");
	geezTranslitR.put("\u0161u","\u1239");
	geezTranslitR.put("\u0161i","\u123A");
	geezTranslitR.put("\u0161\u0101","\u123B");
	geezTranslitR.put("\u0161e","\u123C");
	geezTranslitR.put("\u0161","\u123D");
	geezTranslitR.put("\u0161\u01dd","\u123D");
	geezTranslitR.put("\u0161o","\u123E");
	//ቐ ቑ ቒ ቓ ቔ ቕ ቖ
	//geezTranslit.put("\u1250",);
	//geezTranslit.put("\u1251",);
	//geezTranslit.put("\u1252",);
	//geezTranslit.put("\u1253",);
	//geezTranslit.put("\u1254",);
	//geezTranslit.put("\u1255",);
	//geezTranslit.put("\u1256",);
	//ቘ ቚ ቛ ቜ ቝ
	//geezTranslit.put("\u1258",);
	//geezTranslit.put("\u125A",);
	//geezTranslit.put("\u125B",);
	//geezTranslit.put("\u125C",);
	//geezTranslit.put("\u125D",);
	//ቨ ቩ ቪ ቫ ቬ ቭ ቮ
	geezTranslitR.put("va","\u1268");
	geezTranslitR.put( "vu","\u1269");
	geezTranslitR.put("vi","\u126A");
	geezTranslitR.put("v\u0101","\u126B");
	geezTranslitR.put("ve","\u126C");
	geezTranslitR.put("v","\u126D");
	geezTranslitR.put("v\u01dd","\u126D");
	geezTranslitR.put("vo","\u126E");
	//ቸ ቹ ቺ ቻ ቼ ች ቾ
	geezTranslitR.put("\u010Da","\u1278");
	geezTranslitR.put("\u010Du","\u1279");
	geezTranslitR.put("\u010Di","\u127A");
	geezTranslitR.put("\u010D\u0101","\u127B");
	geezTranslitR.put("\u010De","\u127C");
	geezTranslitR.put("\u010D","\u127D");
	geezTranslitR.put("\u010D\u01dd","\u127D");
	geezTranslitR.put("\u010Do","\u127E");
	//ኘ ኙ ኚ ኛ ኜ ኝ ኞ
	geezTranslitR.put("\u00F1a","\u1298");
	geezTranslitR.put("\u00F1u","\u1299");
	geezTranslitR.put("\u00F1i","\u129A");
	geezTranslitR.put("\u00F1\u0101","\u129B");
	geezTranslitR.put("\u00F1e","\u129C");
	geezTranslitR.put("\u00F1","\u129D");
	geezTranslitR.put("\u00F1\u01dd","\u129D");
	geezTranslitR.put("\u00F1o","\u129E");
	//ኸ ኹ ኺ ኻ ኼ ኽ ኾ
	geezTranslitR.put("\u1E35a","\u12B8");
	geezTranslitR.put("\u1E35u","\u12B9");
	geezTranslitR.put( "\u1E35i","\u12BA");
	geezTranslitR.put("\u1E35\u0101","\u12BB");
	geezTranslitR.put("\u1E35e","\u12BC");
	geezTranslitR.put("\u1E35","\u12BD");
	geezTranslitR.put("\u1E35\u01dd","\u12BD");
	geezTranslitR.put("\u1E35o","\u12BE");
	//ዀ ዂ ዃ ዄ ዅ
	geezTranslitR.put("\u1e35\u02b7a","\u12C0");
	geezTranslitR.put("\u1e35\u02b7i","\u12C2");
	geezTranslitR.put("\u1e35\u02b7\u0101","\u12C3");
	geezTranslitR.put("\u1e35\u02b7e","\u12C4");
	geezTranslitR.put("\u1e35\u02b7","\u12C5");
	geezTranslitR.put("\u1e35\u02b7\u01dd","\u12C5");
	//ዠ ዡ ዢ ዣ ዤ ዥ ዦ
	geezTranslitR.put("\u017Ea","\u12E0");
	geezTranslitR.put("\u017Eu","\u12E1");
	geezTranslitR.put("\u017Ei","\u12E2");
	geezTranslitR.put("\u017E\u0101","\u12E3");
	geezTranslitR.put("\u017Ee","\u12E4");
	geezTranslitR.put("\u017E","\u12E5");
	geezTranslitR.put("\u017E\u01dd","\u12E5");
	geezTranslitR.put("\u017Eo","\u12E6");
	//ጀ ጁ ጂ ጃ ጄ ጅ ጆ
	geezTranslitR.put("\u01E7a","\u1300");
	geezTranslitR.put("\u01E7u","\u1301");
	geezTranslitR.put("\u01E7i","\u1302");
	geezTranslitR.put("\u01E7\u0101","\u1303");
	geezTranslitR.put("\u01E7e","\u1304");
	geezTranslitR.put("\u01E7","\u1305");
	geezTranslitR.put("\u01E7\u01dd","\u1305");
	geezTranslitR.put("\u01E7o","\u1306");
	//ጨ ጩ ጪ ጫ ጬ ጭ ጮ
	geezTranslitR.put("\u010D\u0323a","\u1328");
	geezTranslitR.put("\u010D\u0323u","\u1329");
	geezTranslitR.put("\u010D\u0323i","\u132A");
	geezTranslitR.put("\u010D\u0323\u0101","\u132B");
	geezTranslitR.put("\u010D\u0323e","\u132C");
	geezTranslitR.put("\u010D\u0323","\u132D");
	geezTranslitR.put("\u010D\u0323\u01dd","\u132D");
	geezTranslitR.put( "\u010D\u0323o","\u132E");
	
	geezTranslitR.put("\u1E09a","\u1328");
	geezTranslitR.put("\u1E09u","\u1329");
	geezTranslitR.put("\u1E09i","\u132A");
	geezTranslitR.put("\u1E09\u0101","\u132B");
	geezTranslitR.put("\u1E09e","\u132C");
	geezTranslitR.put("\u1E09","\u132D");
	geezTranslitR.put("\u1E09\u01dd","\u132D");
	geezTranslitR.put( "\u1E09o","\u132E");
	
	geezTranslitR.put("\u25a7", "\u25A7");
	geezTranslitR.put("\u25A1", "\u25A1");
	geezTranslitR.put("\u204b", "\u204b");
	geezTranslitR.put("\u2026", "\u2026");
	
	geezTranslitR.put("\u25a7a", "\u25A7");
	geezTranslitR.put("\u25A1a", "\u25A1");
	geezTranslitR.put("\u204ba", "\u204b");
	geezTranslitR.put("\u2026a", "\u2026");
	//End reverse
	
	//reverse
	
	//Südarabische Buchstaben
			/*suedArabTranslitR.put("h","\u10A60");
			suedArabTranslitR.put("l","\u10A61");
			suedArabTranslitR.put("\u1e25","\u10A62");
			suedArabTranslitR.put("m","\u10A63");
			suedArabTranslitR.put("q","\u10A64");
			suedArabTranslitR.put("w","\u10A65");
			suedArabTranslitR.put("\u0161","\u10A66");
			suedArabTranslitR.put("r","\u10A67");
			suedArabTranslitR.put("b","\u10A68");
			suedArabTranslitR.put("t","\u10A69");
			suedArabTranslitR.put("s","\u10A6A");
			suedArabTranslitR.put("k","\u10A6B");
			suedArabTranslitR.put("n","\u10A6C");
			suedArabTranslitR.put("\u1e2b","\u10A6D");
			suedArabTranslitR.put("\u1e62","\u10A6E");
			suedArabTranslitR.put("\u015b","\u10A6F");
			suedArabTranslitR.put("f","\u10A70");
			suedArabTranslitR.put("\u02be","\u10A71");
			suedArabTranslitR.put("\u02bf","\u10A72");
			suedArabTranslitR.put("\u1e0d","\u10A73");
			suedArabTranslitR.put("g","\u10A74");
			suedArabTranslitR.put("d","\u10A75");
			suedArabTranslitR.put("\u0121","\u10A76");
			suedArabTranslitR.put("u1e6d","\u10A77");
			suedArabTranslitR.put("z","\u10A78");
			suedArabTranslitR.put("\u1e0f","\u10A79");
			suedArabTranslitR.put("y","\u10A7A");
			suedArabTranslitR.put("\u1e6f","\u10A7B");
			suedArabTranslitR.put("\u1e93","\u10A7C");
			suedArabTranslitR.put(" ","\u10A7D");
			suedArabTranslitR.put("1","\u2227");
			suedArabTranslitR.put("50","\u10A7E");
			suedArabTranslitR.put("\u25A7","\u25a7");
			suedArabTranslitR.put("\u25a1","\u25a1");
			suedArabTranslitR.put("\u204b","\u204b");
			suedArabTranslitR.put("\u2026","\u2026");*/
	//end Reverse
	
	suedArabTranslitR.put("h","\uD802\uDE60");
	suedArabTranslitR.put("l","\uD802\uDE61");
	suedArabTranslitR.put("\u1e25","\uD802\uDE62");
	suedArabTranslitR.put("m","\uD802\uDE63");
	suedArabTranslitR.put("q","\uD802\uDE64");
	suedArabTranslitR.put("w","\uD802\uDE65");
	suedArabTranslitR.put("\u0161","\uD802\uDE66");
	suedArabTranslitR.put("r","\uD802\uDE67");
	suedArabTranslitR.put("b","\uD802\uDE68");
	suedArabTranslitR.put("t","\uD802\uDE69");
	suedArabTranslitR.put("s","\uD802\uDE6A");
	suedArabTranslitR.put("k","\uD802\uDE6B");
	suedArabTranslitR.put("n","\uD802\uDE6C");
	suedArabTranslitR.put("\u1e2b","\uD802\uDE6D");
	suedArabTranslitR.put("\u1e62","\uD802\uDE6E");
	suedArabTranslitR.put("\u015b","\uD802\uDE6F");
	suedArabTranslitR.put("f","\uD802\uDE70");
	suedArabTranslitR.put("\u02be","\uD802\uDE71");
	suedArabTranslitR.put("\u02bf","\uD802\uDE72");
	suedArabTranslitR.put("\u1e0d","\uD802\uDE73");
	suedArabTranslitR.put("g","\uD802\uDE74");
	suedArabTranslitR.put("d","\uD802\uDE75");
	suedArabTranslitR.put("\u0121","\uD802\uDE76");
	suedArabTranslitR.put("u1e6d","\uD802\uDE77");
	suedArabTranslitR.put("z","\uD802\uDE78");
	suedArabTranslitR.put("\u1e0f","\uD802\uDE79");
	suedArabTranslitR.put("y","\uD802\uDE7A");
	suedArabTranslitR.put("\u1e6f","\uD802\uDE7B");
	suedArabTranslitR.put("\u1e93","\uD802\uDE7C");
	suedArabTranslitR.put(" ","\uD802\uDE7D");
	suedArabTranslitR.put("1","\u2227");
	suedArabTranslitR.put("50","\uD802\uDE7E");
	suedArabTranslitR.put("\u25A7","\u25a7");
	suedArabTranslitR.put("\u25a1","\u25a1");
	suedArabTranslitR.put("\u204b","\u204b");
	suedArabTranslitR.put("\u2026","\u2026");
	
	laringals.put("h","L");
	laringals.put("\u1e25","L");
	laringals.put("\u1e2b","L");
	laringals.put("\u02be", "L");
	laringals.put("\u02bf","L");
	laringals.put("\u1e2b\u02b7a", "L");
	laringals.put("H", "L");
	//laringals.put("\u1e6d","L");
	//hewe noch den fall mit w
	waw.put("w", "W");
	yod.put("y", "Y");
	dentals.put("d", "D");
	dentals.put("t", "D");
	dentals.put("\u1e6D", "D");
	sibilants.put("\u015b", "S");
	sibilants.put("s", "S");
	sibilants.put("z", "S");
	sibilants.put("\u1e63", "S");
	sibilants.put("\1e0d", "S");
	
	}
}