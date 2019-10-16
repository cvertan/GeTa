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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import javax.swing.text.html.HTMLDocument;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import ngjmy.swing.suggestionlist.*;
/**
 * builds the Frame when the lemma is searched on the netametsaheft server
 * the access is done throgh an API call to the server "https://betamasaheft.eu/api/Dillmann/"+searchLemma.getText()+"/txt"
 */
public class LexiconFrame1 extends JInternalFrame{
	Font etiopic1=new Font("Ethiopic Unicode",Font.PLAIN,14);
	private final String USER_AGENT = "Mozilla/5.0";
	String lm1;
	 JTextField searchLemma;
	 JTextField foundLemma;
	 JButton search;
	 final JButton ok=new JButton("Assign");
	public static String id="";
	 final JTextPane entryDictionary = new JTextPane();
	public LexiconFrame1(String lm){
	super ("Ge\'ez Lexicon");
	setIconifiable(true);
	this.lm1=lm;
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
	 searchLemma=new JTextField(30);
	 foundLemma=new JTextField(30);
	 searchLemma.setFont(etiopic1);
	foundLemma.setFont(etiopic1); foundLemma.setEditable(false);
	 if (!lm1.isEmpty()) {foundLemma.setText(lm1.substring(lm1.indexOf('-')+2));
	 searchLemma.setText(lm1.substring(0,lm1.indexOf('-')));
	 }
	
	 search=new JButton("Search Lemma");
	 
	
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
		JScrollPane  scroller = new JScrollPane(entryDictionary);
	     scroller.setPreferredSize( new Dimension( 400, 200 ) ); 
	     entryDictionary.setEnabled(true);;entryDictionary.setEditable(false);
	
	
	//if(!lm1.isEmpty()) searchLemma.setFocusable(true);
    searchLemma.addFocusListener(new FocusListener(){
        @Override public void focusGained(FocusEvent e) { 
        //	try { 
        
         //   Robot robot = new Robot(); 

           // robot.keyPress(KeyEvent.VK_ENTER); 
        //} catch (AWTException ex) { 
        //ex.printStackTrace(); 
        //} 
        	
      lm1=""; }
        @Override public void focusLost(FocusEvent e) {  }
        
    });
	 
		     
		    search.addActionListener(new ActionListener(){
		            @Override public void actionPerformed(ActionEvent e) { 
		            String s=sendGet( "https://betamasaheft.eu/api/Dillmann/"+searchLemma.getText()+"/txt");
		           
		            if(!s.equals("Error")) {
		            	 String s1=s.substring(s.indexOf("$")+1);
				            String s2=s1.substring(0,s1.indexOf("$"));
		            	foundLemma.setText(s2);
		            	entryDictionary.setText(s);
		            	ok.setEnabled(true);
		            }
		            else entryDictionary.setText("Lemma not found");
		            }
		        });
		     
	
		     ok.addActionListener(new ActionListener(){
		    	 public void actionPerformed(ActionEvent e){
		    		
		    		// id="";
		    		 if(!foundLemma.getText().isEmpty()) {
		    		 TagGUI.LexIDSel=searchLemma.getText()+"--"+foundLemma.getText();
		          //   System.out.println("ID "+ id);
		            dispose();
		    		 }
		    	 }
		     });
	         entryDictionary.setText("");
	        
		     
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
		     JLabel searchL=new JLabel("Search for ID Lemma");
		    p.add(searchL,g);
		    g.gridx=1;
		    p.add(searchLemma,g);
		    g.gridx=0;g.gridy=1;
		    JLabel foundL=new JLabel("Lemma");
		    p.add(foundL,g);
		    g.gridx=1;
		    p.add(foundLemma,g);
		    g.gridwidth=2;g.gridy=2;
		     p.add(scroller,g);
		     g.gridwidth=1;g.gridy=3;
		     g.gridx=0;
		     p.add(search,g);
		     g.gridx=1;
		     p.add(ok,g);
		     pack();
	
	}
	
	public String getSelectedLexId(){
		return id;
	}
	public static void setSelectedLexID(String s){
		id=s;
	}
	private String sendGet(String url){

		try{	

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent",USER_AGENT);
			con.setRequestProperty("Accept", "*/*");
			con.setRequestProperty("Content-Type","application/json; charset=UTF-8");
		  
			int responseCode = con.getResponseCode();
			//if (responseCode!=200)
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	if (responseCode==200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream(),"UTF-8"));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				//response.append("\n");
			}
			in.close();
		
			return response.toString();
	}
	else return "Error";
		}
	catch(Exception e){ return "Error";}
			//print result
			//System.out.println(response.toString());

		}
	 
}