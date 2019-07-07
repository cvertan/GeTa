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
/**
 * builds the window which is used in {@link TagGUI} when the button "Insert Lemma" is pressed
 * a {@link SuggestionLink} is built dinamicall whenever the use types a letter in the "Search Lemma" text field
 */
public class LexiconFrame extends JInternalFrame{
	Font etiopic1=new Font("Ethiopic Unicode",Font.PLAIN,24);
	/**
	 * list of lexicon entries
	* see LexiconEntry
	 */
	ArrayList<LexiconEntry> entries=new ArrayList<LexiconEntry>();
	/**
	 * list of {@link SuggestionLink} elements
	 */
	ArrayList<SuggestionList> suggestL=new ArrayList<SuggestionList>();
	/**
	 * one {@link SuggestionLink}
	 */
	SuggestionList sug;
	/**
	 * one lement of the Suggestionist
	 */
	String[] sugL;
	/**
	 * the string for which a lemma is searches
	 */
	String lm1;
	/**
	 * list of possible lemmas
	 */
	ArrayList<String> lem=new ArrayList<String>();
	 Font greek=new Font("GFS Artemisia",Font.PLAIN,12);
	 Font etiopic2=new Font("Abyssinica SIL",Font.PLAIN,12);
	 Font tmr = new Font("Times New Roman",Font.PLAIN,20);
	 EntryListModel entriesModel;
	 JScrollPane scroller;
	 JTextField searchLemma;
	 JButton clear;
	 JButton search;
	 final JButton ok=new JButton("Assign");
	public static String id="";
	 int index;
	 JList entriesList;
	 final JTextPane entryDictionary = new JTextPane();
	 Lexicon lex;
	public LexiconFrame(Lexicon lex1,String lm){
	super ("Ge\'ez Lexicon");
	setIconifiable(true);
	this.lm1=lm;
	setMaximizable(true);
	this.setResizable(true);
	 lex=lex1;
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
	 if (!lm1.isEmpty()) searchLemma.setText(lm1.substring(lm1.indexOf('-')+2));
	 searchLemma.setFocusTraversalKeysEnabled(true);
	 searchLemma.setFocusable(true);
	 search=new JButton("Search Lemma");
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
		 StyleConstants.setFontFamily(style, "Ethiopic Unicode");
		 StyleConstants.setFontFamily(style1, "Ethiopic Unicode");
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
		    	  sug.bind(null);
		    	  int index1=entriesList.getSelectedIndex();System.out.println("Index "+index1);
		    	  if(index1<0) index1=0;
		    	  String s=entriesModel.getEntryAt(index1).getLexiconentryDict();
		    	      searchLemma.setText(entriesModel.getEntryAt(index1).getLexiconEntryLemma());
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
	//sug.bind(searchLemma);
	
	//if(!lm1.isEmpty()) searchLemma.setFocusable(true);
    searchLemma.addFocusListener(new FocusListener(){
        @Override public void focusGained(FocusEvent e) { 
        //	try { 
        
         //   Robot robot = new Robot(); 

           // robot.keyPress(KeyEvent.VK_ENTER); 
        //} catch (AWTException ex) { 
        //ex.printStackTrace(); 
        //} 
        	sug.bind(searchLemma);	
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
		return l.substring(0,1);
}
	/**
	 * capture the text enterd in the "Search Lemma" textfield and searches after possible lemmas in the {@link Lexicon}
	 */
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
				else  JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" >Entry " +searchLemma.getText() +"not found </font></p></html>", "Dialog",
		       		        JOptionPane.ERROR_MESSAGE);
		   // }
		    
		    }
     }
	 /**
	  * 
	  * @param lm a string representing the lemma after which is searched
	  */
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
						
						String s=entriesModel.getEntryAt(i).getLexiconentryDict();
			    		 entryDictionary.setText(s);
			    		 entryDictionary.setCaretPosition(0);
						ok.setEnabled(true);
					}
					else  JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" >Entry " +searchLemma.getText() +"not found </font></p></html>", "Dialog",
			       		        JOptionPane.ERROR_MESSAGE);
			   // }
			    
			    }
	     }
}