//
import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.*;

import javax.swing.text.html.HTMLDocument;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import ngjmy.swing.suggestionlist.*;
public class BrowseNEFrame extends JInternalFrame{
	Font etiopic1=new Font("Ethiopic Unicode",Font.PLAIN,24);
	ArrayList<LexiconEntry> entries=new ArrayList<LexiconEntry>();
	ArrayList<SuggestionList> suggestL=new ArrayList<SuggestionList>();
	SuggestionList sug;
	String[] sugL;
	String lm1;
	ArrayList<String> lem=new ArrayList<String>();
	 Font greek=new Font("GFS Artemisia",Font.PLAIN,12);
	 Font etiopic2=new Font("Abyssinica SIL",Font.PLAIN,12);
	 Font tmr = new Font("Times New Roman",Font.PLAIN,20);
	 EntryListModel entriesModel;
	 JScrollPane scroller;
	 JTextField searchLemma;
	 JButton clear;
	 JButton search;
	 String neref;
	 String server;
	 final JButton ok=new JButton("Assign");
	public static String id="";
	 int index;
	 JList entriesList;
	 final JTextPane entryDictionary = new JTextPane();
	 Lexicon lex;
	public BrowseNEFrame(String server,String neref){
	super ("Named Entities");
	setIconifiable(true);
	this.neref=neref;
	this.server=server;
	setMaximizable(true);
	this.setResizable(true);
	
	this.setClosable(true);
	this.setFont(etiopic1);
	setBackground(Color.lightGray);
	Container p = this.getContentPane();
	p.setLayout(new GridBagLayout());
	InitializeFonts ff=new InitializeFonts();
	 p.setFont(etiopic1);
	 final JButton show=new JButton("Show entry");
		
		ok.setEnabled(false);
	 searchLemma=new JTextField(20);
	 searchLemma.setFont(etiopic1);
	 if (!neref.isEmpty()) searchLemma.setText(neref);
	 searchLemma.setFocusTraversalKeysEnabled(true);
	 searchLemma.setFocusable(true);
	 search=new JButton("Search NE");
	 clear=new JButton("Clear");
	
	 StyledDocument doc = (StyledDocument) entryDictionary.getDocument();
    
	 entryDictionary.setSize(10,100);
		//entryDictionary.setFont (tmr);
		// entryDictionary.setFont (etiopic1);
		entryDictionary.setContentType("text/html");
		String bodyRule = "body { font-family: " + etiopic1.getFamily() + "; " +
	            "font-size: " + etiopic1.getSize() + "pt; }";
	    ((HTMLDocument)entryDictionary.getDocument()).getStyleSheet().addRule(bodyRule);
		entryDictionary.setFont (etiopic1);
		 entryDictionary.setEditable(false);
		 entryDictionary.setCaretPosition(0);
		 Style style = doc.addStyle("StyleName", null);
	     
		 Style style1 = doc.addStyle("StyleName1", null);
		 
		 Style style2 = doc.addStyle("StyleName2", null);
		 Style style3 = doc.addStyle("StyleName", null);
		 StyleConstants.setFontFamily(style, "TITUS Cyberbit Basic");
		 StyleConstants.setFontFamily(style1, "TITUS Cyberbit Basic");
		 StyleConstants.setFontFamily(style2, "Times New Roman");
		 StyleConstants.setFontFamily(style3, "Times New Roman");
		 entryDictionary.setPreferredSize(new Dimension(400,200));
		 scroller = new JScrollPane(entryDictionary);
	     scroller.setPreferredSize( new Dimension( 400, 200 ) ); 
	     entryDictionary.setEnabled(true);;entryDictionary.setEditable(false);
	final  JComboBox c1 = new JComboBox();
	 c1.setFont(etiopic1);
	 entriesList=new JList();
		entriesList.setFont(etiopic1);
	    entriesModel=new EntryListModel();
	  for(int i=0;i<ff.keys.length;i++){
	     c1.addItem(""+ff.keys[i]);
	     for (int j=0; j<(lex.getEntriesKey(""+ff.keys[i])).size();j++){
	    	 entries.add(lex.getEntriesKey(""+ff.keys[i]).get(j));
	         lem.add(lex.getEntriesKey(""+ff.keys[i]).get(j).getLexiconEntryLemma());
	         entriesModel.addElement(lex.getEntriesKey(""+ff.keys[i]).get(j));
	     }
	  }
	  
		//System.out.println(((LexiconEntry)entriesModel.get(0)).getLexiconEntryLemma());
		entriesList.setModel(entriesModel);
		entriesList.setModel(entriesModel);
		entriesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		entriesList.setVisibleRowCount(10);
		entriesList.setSelectedIndex(0);
		entriesList.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		    	  int index1=entriesList.getSelectedIndex();System.out.println("Index "+index1);
		    	  if(index1<0) index1=0;
		    	  String s=entriesModel.getEntryAt(index1).getLexiconentryDict();
		    	 
		    		 entryDictionary.setText("<html><p>"+s+"</p></html>");
		    		 entryDictionary.setCaretPosition(0);
		    		
		    		 ok.setEnabled(true);
		    		 
	    		 }
		 });
		
	  sugL=new String[lem.size()];
	  for(int i=0;i<lem.size();i++) sugL[i]=lem.get(i);
	index=-1;
	sug=new SuggestionList(sugL);
	sug.setFont(etiopic1);
	sug.setMinimumPrefixLength(1);
	sug.bind(searchLemma);
	//if(!lm1.isEmpty()) searchLemma.setFocusable(true);
    searchLemma.addFocusListener(new FocusListener(){
        @Override public void focusGained(FocusEvent e) { 
        //	try { 
        
         //   Robot robot = new Robot(); 

           // robot.keyPress(KeyEvent.VK_ENTER); 
        //} catch (AWTException ex) { 
        //ex.printStackTrace(); 
        //} 
        filter1(lm1);lm1=""; }
        @Override public void focusLost(FocusEvent e) {  }
        
    });
	 
		     
		    searchLemma.addActionListener(new ActionListener(){
		            @Override public void actionPerformed(ActionEvent e) { filter(); }
		            
		        });
		     
	
		     show.addActionListener(new ActionListener(){
		    	 public void actionPerformed(ActionEvent e){
		    		 int index1=entriesList.getSelectedIndex();
		    		 if (index1 >-1){
		    		 System.out.println("INDEX1 " + index1);
		    		 String s=entriesModel.getEntryAt(index1).getLexiconentryDict();
		    		 entryDictionary.setText(s);
		    		 //show.setEnabled(false);
		    		 ok.setEnabled(true);
		    		 }
		    		 else entryDictionary.setText("No entry");
		    	 }
		     });
		     ok.addActionListener(new ActionListener(){
		    	 public void actionPerformed(ActionEvent e){
		    		 int index=entriesList.getSelectedIndex();
		    		 id=entries.get(index).getLexiconEntryId();
		    		 TagGUI.LexIDSel=id+"--"+ entries.get(index).getLexiconEntryLemma();
		             System.out.println("ID "+ id);
		            dispose();
		    	 }
		     });
	         entryDictionary.setText("");
	         JScrollPane scrollPane = new JScrollPane();
	         scrollPane.setViewportView(entriesList);
		     
	         GridBagConstraints g=new GridBagConstraints();
		     g.gridx=0;
		     g.gridy=0;
		     g.insets.bottom=2;
		     g.insets.top=2;
		     g.insets.left=2;
		     g.insets.right=2;
		     g.gridwidth=1; g.gridheight=1;
		     g.fill=GridBagConstraints.NONE;
		     g.anchor=GridBagConstraints.NORTHWEST;
		  
		    // g.gridy=1;
		   //  g.gridy=1; g.insets.bottom=0;
		     JLabel searchL=new JLabel("Search for Lemma");
		     p.add(searchL,g);
		     //g.gridwidth=2;
		     g.insets.bottom=2;
		     g.gridy=1;  g.insets.top=0;
		     p.add(searchLemma,g);g.insets.top=2;
		     g.gridy=2; g.gridwidth=1;
		     p.add(ok,g);
		     g.fill=GridBagConstraints.BOTH;
		     g.gridx=1;
		     g.gridy=0;
		     g.gridheight=3;
		     p.add(scrollPane,g);
		     g.gridx=2;
		     p.add(scroller,g);
		     
		     pack();
	
	}
	
	public String getSelectedLexId(){
		return id;
	}
	public static void setSelectedLexID(String s){
		id=s;
	}
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
		
		return key;
		
	}
	 private void filter() {
     	if(searchLemma.getText().length()>=1){
     		System.out.println("Search new Lemma");
         String filter = searchLemma.getText().substring(0,1);

         String key=getKeyEntry(filter);
         
        // entries=lex.getEntriesKey(key);
		   // if((entries.size()==0))
		    //{entries=new ArrayList<LexiconEntry>();
		     //entries.add(new LexiconEntry("",key,"No Entry","No Entry"));}
		    //else{ 
		      int i=0;boolean found=false;
				while ((i<entries.size())&&(!found)){
					
					if (entries.get(i).getLexiconEntryLemma().equals(searchLemma.getText()))found=true;
					else i=i+1;
					
				}
				if (found){
					entriesList.setSelectedIndex(i);
					if ((entriesList.getSelectedIndex()+5)<entriesList.getModel().getSize())
					     entriesList.ensureIndexIsVisible(entriesList.getSelectedIndex()+5);
					else {
						entriesList.ensureIndexIsVisible(entriesList.getSelectedIndex()+(entriesList.getModel().getSize()-entriesList.getSelectedIndex()-1));
					   System.out.println(entriesList.getModel().getSize()-entriesList.getSelectedIndex()-1);
					}
					entryDictionary.setCaretPosition(0);
					ok.setEnabled(true);
				}
				else  JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"TITUS Cyberbit Basic\" >Entry " +searchLemma.getText() +"not found </font></p></html>", "Dialog",
		       		        JOptionPane.ERROR_MESSAGE);
		   // }
		    
		    }
     }
	 private void filter1(String lm) {
	     	if(!lm.isEmpty()){
	     		System.out.println("Search existing Lemma");
	       //  String filter = searchLemma.getText().substring(0,1);

	      //   String key=getKeyEntry(filter);
	         
	        // entries=lex.getEntriesKey(key);
			   // if((entries.size()==0))
			    //{entries=new ArrayList<LexiconEntry>();
			     //entries.add(new LexiconEntry("",key,"No Entry","No Entry"));}
			    //else{ 
			      int i=0;boolean found=false;
					while ((i<entries.size())&&(!found)){
						System.out.println(entries.get(i).getLexiconEntryId());
						if (entries.get(i).getLexiconEntryId().equals(lm.substring(0,lm.indexOf("-"))))
								 found=true;
						else i=i+1;
					}
					
					if (found){
						System.out.println("Index Read" +i);
						entriesList.setSelectedIndex(i); entriesList.revalidate();entriesList.repaint();
						if ((entriesList.getSelectedIndex()+5)<entriesList.getModel().getSize())
						     entriesList.ensureIndexIsVisible(entriesList.getSelectedIndex()+5);
						else {
							entriesList.ensureIndexIsVisible(entriesList.getSelectedIndex()+(entriesList.getModel().getSize()-entriesList.getSelectedIndex()-1));
						   System.out.println(entriesList.getModel().getSize()-entriesList.getSelectedIndex()-1);
						}
						entryDictionary.setCaretPosition(0);
						String s=entriesModel.getEntryAt(i).getLexiconentryDict();
			    		 entryDictionary.setText(s);
						ok.setEnabled(true);
					}
					else  JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"TITUS Cyberbit Basic\" >Entry " +searchLemma.getText() +"not found </font></p></html>", "Dialog",
			       		        JOptionPane.ERROR_MESSAGE);
			   // }
			    
			    }
	     }
}