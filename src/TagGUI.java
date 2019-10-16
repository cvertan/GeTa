import java.awt.Font;
import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import net.atlanticbb.tantlinger.shef.*;
import org.jgraph.graph.CellView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import org.jgraph.graph.GraphLayoutCache;
import java.awt.ComponentOrientation;
import org.jgraph.graph.DefaultEdge;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import org.jgraph.graph.EdgeView;
import org.jsoup.*;
import org.jdesktop.swingx.JXList;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import com.alee.extended.label.StyleRange;
import com.alee.extended.label.WebStyledLabel;
import com.alee.laf.WebLookAndFeel;

import javax.naming.directory.Attribute;
import javax.swing.*;
import javax.swing.plaf.*;
import java.awt.event.MouseMotionAdapter;
import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.ConnectionSet;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.ParentMap;
import java.awt.Dimension;
import java.awt.Point;
import org.jgraph.JGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import java.awt.geom.Rectangle2D;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.apache.commons.io.IOUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;

import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.FlowLayout;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jfree.chart.plot.PiePlot;

import javax.swing.text.html.HTMLDocument;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.*;
import javax.swing.JToggleButton.ToggleButtonModel;
import javax.swing.event.*;
import javax.swing.text.*;

import java.util.*;

import javax.swing.tree.*;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.FilenameUtils.*;
import org.json.*;
import org.apache.commons.lang.*;
import org.json.*;
/**
 *<ul><li> implements the main user interface</li>
 * <li>provides functionality for reading, saving and exporting of files as well as tools for searching an monitorize the progress of work</li>
 *<li> implements <a href="https://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionListener.html">ActionListener</a></li>
 * <li>extends <a href="https://docs..com/javase/9/docs/api/javax/swing/JFrame.html">JFrame</a></li></ul>
 * @author Cristina Vertan
 * @version 1.0
 */
public class TagGUI extends JFrame implements ActionListener{
	/**
	 * generates a unique random Number
	 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Random.html">Random</a>
	 */
	private Random randomGenerator;
	/**
	 * builts main window
	 */
	private JDesktopPane desk;
	/**
	 * color of all main windows (border and title bar
	 */
	private Color mainFrameColor=Color.LIGHT_GRAY;
	/** 
	 * windows showing the stucture Annotation for the current (selected) graphical unit
	 */
	private ChildFrame showPosition; 
	/** 
	 * windows showing the inserted Page Breaks
	 */
	private ChildFrame showPositionPageBreak; 
	/**
	 * window which displays a Color Palette from which one can be selected for colouring a graphical unit in the transcription window
	 */
	private ChildFrame showColors;
	/**
	 * window which displays the linguistic annotation for the current (selected) graphical unit
	 */
	private ChildFrame showAnnotation;
	/**
	 *  @link Metadata object which holds all metadata information specific only to GeTa Tool as 
	 *  <br/> well as the id of the correspondind metadata on the Beta Metsaheft server
	 */
	private Metadata metadataDoc;
	/**
	 * window which displays possible errors when indexing. 
	 */
	private ChildFrame  errorWarning;
	/**
	 * marks the begin of the selected part to be saved in savePartsToFile
	 * @see savePartsToFile
	 */
	private int anfang1=0;
	/**
	 * monitorise if a NE was inserted manually or through automatic search on the server <br/>
	 * 0 if automatic search; 1 if manul inserted
	 * @see jmneI.addActionListener
	 */
	private int nrmanins=0;
	/**
	 * marks the end of the selected part to be saved in savePartsToFile
	 * @see savePartsToFile
	 */
	private int ende1=0;
	/**
	 * scroller Container for the ChildFrame translitText
	 * @used in doActionsOnFile
	 */
	private  JScrollPane scrollerTranslit ;
	/**
	 * used to mark the position from where a global annotation with a  NE is done<br/>
	 * this is the position of the current selected graphical unit +1
	 * @used by    jmneG.addActionListener
	 */
	private int startGlobalNE;
	/**
	 * used to mark the position till where a global annotation with a  NE is done<br/>
	 * this is the position of the current selected graphical unit -1
	 * @used by    jmneG.addActionListener
	 */
	private int endGlobalNE;
	/**
	 * records the positions where the same string as the selected NE is found <br/>
	 * positions are recorded as string
	 * @used in  jmneG.addActionListener
	 */
	private ArrayList<String> posNE;
	/**
	 * records the positions of the grphical units which shoul contain a NE
	 * @used in jmneI.addActionListener
	 */
	private int[] selection1;
	/**
	* records the graphical units which contain the tokens of the NE to be annotated global
	*/
	private Map <String, ArrayList<RefWord>> hm=new HashMap<String,ArrayList<RefWord>>(); 
	/**
	 * x-Coordinate of the window which shows the linguistic annotation
	 */
	private int showAnnotationX;
	/**
	 * y-Coordinate of the window which shows the linguistic annotation
	 */
	private int showAnnotationY;
	/**
	 *  paints the internal structure of a graphical unit
	 * @used in vizStructure.addActionListener
	 */
   private JGraphModelAdapter jgAdapter;
   
	private boolean showDiv1,showDiv2,showDiv3,showDiv4;
	private boolean showDiv1o,showDiv2o,showDiv3o,showDiv4o;
	private boolean showDiv1e,showDiv2e,showDiv3e,showDiv4e;
	private Font typFont1=new Font("TITUS Cyberbit Basic",Font.PLAIN,16);
	private Font etiopic1=new Font("Ethiopic Unicode",Font.PLAIN,16);
	private Font etiopic2=new Font("Ethiopic Unicode",Font.PLAIN,16);
	private Font etiopicMenu=etiopic1.deriveFont(Font.BOLD,18);
	private Font etiopicMenu1=typFont1.deriveFont(Font.BOLD,18);
	private String Fehlern="";
private int nrPB=0;
private JScrollPane sp1;
	private Font etiopicText=etiopic1.deriveFont(24);
	private Font etiopicText1=etiopic1.deriveFont(18);
	private Font etiopicMenu_1=etiopic2.deriveFont(Font.BOLD,18);
	private Font etiopicText_1=etiopic2.deriveFont(24);
	private Font etiopicText1_1=etiopic2.deriveFont(18);
	private String typFontString;
	private JComboBox level1List, level2List,level3List,level4List;
	private JCheckBox cbl1, cbl2,cbl3,cbl4;
	private JTextField tl1, tl2,tl3,tl4,tlN1, tlN2,tlN3,tlN4;
	private Set<String> div1B, div2B,div3B,div4B,div1E,div2E,div3E,div4E;
	private int startSel=-1;
	private int endSel=-1;
	private boolean did1Repl=false;
	private String labelRepl="";
	private String[] nameStruct={"-","part","book","chapter","section","poetic_verse_line","sentence","segment"};
	private JMenu openFile,importFile,exportTEIFile, annotHistory,rulesDB,statistics,divMenu,helpMenu;
	private JMenuItem exitTool,newFile,annotFile,closeFile,saveFile,saveParts, exportFile,exportWordID,neMenu,exportANNIS,saveAsFile,importTEIFile,exportTEIStruct,exportTEILing,exportTEIAll, mUndo, mRedo,annotHistoryLing,annotHistoryNE,annotHistoryStruct;
    private JMenuItem showStructureAnnotWindow,showLingAnnotWindow,errorWindow;
    private JMenuItem colorConvention, correctIndex,verifyNouns, createNewEntries, correctMorpho,correctInscriptions;
    private JMenuItem structDiv, delDivs, recognSentMenu,firstLB;
    private JMenuItem importNEs, createMeta;
    private JMenuItem searchMenu, newRulesDB,tokRulesDB,annotRulesDB,statDocument,statProgress,lexiconMenu,undoMenu;
    private StayOpenCheckBoxMenuItem div1Menu,div2Menu,div3Menu,div4Menu, persMenu, orgMenu,placesMenu,nrMenu,datesMenu,abrevMenu,titlesMenu;
    private JMenuItem geezWMenu, transcrWMenu, aboutMenu;
    public static String LexIDSel="";
   private String did1;
    private int noStruct=0;
    private   int posDiv1;
    private JTree treePOS,treePOS1;
    private int fidalW_Size;
    private int fidalH_Size;
    private int typDoc=0;
    private int typScript=0;
    private Font typFont;
    private  JEditorPane errorPane;
    private int startLBnr=1;
    private final String USER_AGENT = "Mozilla/5.0";
    String tokenNewLabel, wordNewLabel;
    int translitW_Size;
    int translitH_Size;
    int last_SIGN;
    int nrop=0;
    int nrLB;
    int pressDeep=0;
    int pressPoS=0;
    boolean assignedFeatures=false;
  private  NamedEntity rr;
    private JEditorPane searchPane;
    private JEditorPane tokenisedPane;
    private String FileOpenedName;
    private ChildFrame fidalText;
    private ChildFrame translitText;
   private JTextArea showPositionText;
   private JEditorPane showPBText;
   private JEditorPane showAnnotationText;
   private JTextArea positionCursor;
   private ArrayList<String> linkNE;
	 private JTextField positionCursor1;
	 private JEditorPane showColorsText;
	 private File fileRoot;
	 private JProgressBar progressBar_1 = new JProgressBar();
	  private  int randomInt;
	  private LatinLetterNode temp;
	   private String docID;	
	private boolean isSaved=false;
	private  JGraph jgraph;
	private InitializeFonts geez=new InitializeFonts();
	private  ArrayList<WortNode> words=new ArrayList<WortNode>();
	private  ArrayList<Division> divisions=new ArrayList<Division>();
	private ArrayList<WortNode> wordsCopy=new ArrayList<WortNode>();
	private ArrayList<WortNode> wordsBackup=new ArrayList<WortNode>();
	private ArrayList<WortNode> wordsBackup1=new ArrayList<WortNode>();
	private ArrayList<Token> tokens=new ArrayList<Token>();
	private ArrayList<Token> tokensBackup=new ArrayList<Token>();
	private ArrayList<Token> tokensBackup1=new ArrayList<Token>();
	private ArrayList<Division> divisionsBackup=new ArrayList<Division>();
	private ArrayList<Division> specDivisions=new ArrayList<Division>();
	private Map <String, ArrayList<String>> index = new HashMap<String, ArrayList<String>>();
	private Map <String, ArrayList<String>> indexT = new HashMap<String, ArrayList<String>>();
	private Map <String, ArrayList<String>> indexD = new HashMap<String, ArrayList<String>>();
	
	private Map <String,String> structLevels ;
	private Map <String,String> structLevelsBegin ;
	private Map<String,String> structLevelsBeginName;
	private boolean hasStructSpec;
	private Lexicon lex;
	private Map<String,Integer> indexIdWord=new HashMap<String, Integer>();
	//public static Map<String,LatinLetterNode> indexLetterToken=new HashMap<String, LatinLetterNode>();
	private Map<String,Integer> indexIdToken=new HashMap<String, Integer>();
	private Map<String,Integer> indexIdDivision=new HashMap<String, Integer>();
	private Map <String, Integer> indexIdSpecDivision = new HashMap<String, Integer>();
	private Map<String,String> schwaExcluded=new HashMap<String, String>();
	private JList fidalWordList=new JList();
    private JList translitWordList=new JList();
    
   private  JScrollPane scrollerFidal = new JScrollPane(fidalWordList);
    private JList tlist;
    private LetterListModel trmodel;
    private TranslitListModel modelTranslit;
     private WortListModel modelOrig;
    private boolean close=false;
    private ArrayList<Integer> posSel;
    private Token tsel;
    private String ltid,wid;
    private String wordref="";
    private WortNode wsel;
    private Map <String, String> auswahl=new HashMap<String,String>();
    private ArrayList<NamedEntity> nelist=new ArrayList<NamedEntity>();
    private ArrayList<String> inLingannot= new ArrayList<String>();
    private ArrayList<String> wselEdit= new ArrayList<String>();
    private ArrayList<String> tselEdit= new ArrayList<String>();
    private ArrayList<String> wselEdit1= new ArrayList<String>();
    private ArrayList<String> tselEdit1= new ArrayList<String>();
    private ArrayList<String> wordNLabel= new ArrayList<String>();
    private ArrayList<String> wordALabel= new ArrayList<String>();
    private ArrayList<String> tokenNLabel= new ArrayList<String>();
    private ArrayList<String> tokenALabel= new ArrayList<String>();
    private String progress;
    private  ArrayList<WortNode> wordsSel=new ArrayList<WortNode>();
    private  ArrayList<Token> tokenSel=new ArrayList<Token>();
    private ArrayList<Integer> posSelT=new ArrayList<Integer>();
    private ArrayList<Integer> posSelW=new ArrayList<Integer>();
  public boolean viewne=true;
    private ArrayList<String> haveAnnot=new ArrayList<String>();
    int counttoken,counttokenpre;
    private String versTool="";
    String selectedPos="";
    private boolean checkedN;
   private ArrayList<Attribut> attr=new ArrayList<Attribut>();
   /**
    * constructor of the class
    * extracts from the name of the main  class (the one giving the name to the .jar file ) the version of the GeTaTool and diplays it in the main window
    * intialize the random generator
    * builts the main window
    * adds menu to the main window
    */
	 public TagGUI() {
		   
			super("GeTa - TraCES Annotation Tool");
			String s="";
			   try{
				  // s=MyClass.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
				   File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
				 //  s=jarDir.getAbsolutePath();
			
				  // s=ClassLoader.getSystemClassLoader().getResource(".").getClass().getName();
				   s=System.getProperty("java.class.path");
				//   System.out.println(jarDir.getAbsolutePath());
				 //  s=TagGUI.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
				  // JOptionPane.showMessageDialog(new JFrame(), s, "Dialog",
		       		     //   JOptionPane.ERROR_MESSAGE);
				   //System.out.println(s);
				   int pT=s.indexOf("TaggingTool");
				   versTool=s.substring(pT+11);
				  
				   if(!(versTool.indexOf("bin")>=0)) { 
				       pT=versTool.indexOf(".jar");
				   }
				   else   pT=versTool.indexOf("bin")-1;
			versTool=versTool.substring(0,pT);
			   }
	            catch(Exception e){}
			  
			   this.setTitle(this.getTitle()+" "+versTool);
			   System.out.println("Version Tool "+versTool);
			   
			randomGenerator = new Random();
			this.desk=new JDesktopPane();
			desk.putClientProperty("JDesktopPane.dragMode", "outline");
		
			desk.setAutoscrolls(true);
			desk.setDesktopManager(new DefaultDesktopManager());
			//Font etiopicMenu=etiopic1.deriveFont(Font.BOLD,18);
			desk.setAutoscrolls(true); desk.setFont(etiopicMenu);
			
			setContentPane(desk);
		    setResizable(true);
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    setSize(screenSize.width, screenSize.height);
		    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowClosingAdapter(true));
		    addComponentListener(new ComponentAdapter() {  
		    	public void componentResized(ComponentEvent e)  
		    	{  
		    	mainWin_componentResized(e);  
		    	}  
		    	   
		    	});  
		    JMenuBar menubar=new JMenuBar();
		    menubar.add(createFileMenu());
		    menubar.add( Box.createHorizontalStrut( 20 ) );
		     menubar.add(createHistoryMenu());
		     menubar.add( Box.createHorizontalStrut( 20 ) );
		    menubar.add(createShowWindowMenu());
		    menubar.add( Box.createHorizontalStrut( 20 ) );
		     menubar.add(createToolsMenu());	  
		     menubar.add( Box.createHorizontalStrut( 20 ) );
		    menubar.add(createViewAnnotationsMenu());
		    menubar.add( Box.createHorizontalStrut( 20 ) );
		   menubar.add(createKeyboardsMenu());
		   menubar.add( Box.createHorizontalStrut( 20 ) );
		   menubar.add(createHelpMenu());
		   menubar.add( Box.createHorizontalStrut( 20 ) );
		   menubar.add(createAutomaticMenu());
		   menubar.add( Box.createHorizontalStrut( 20 ) );
		   menubar.add(createAboutMenu());
		    setJMenuBar(menubar);
		    //build JTree for PoS
		    JTreePOS t = new JTreePOS();
		    DefaultMutableTreeNode tModel=t.createNodes();   
		    JTreePOS t1 = new JTreePOS();
		    DefaultMutableTreeNode tModel1=t1.createNodes();
		     treePOS=new JTree(tModel);
		    treePOS.expandPath(new TreePath(treePOS.getModel().getRoot()));
		    treePOS.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		    treePOS1=new JTree(tModel1);
		   // treePOS1.expandPath(new TreePath(treePOS.getModel().getRoot()));
		    treePOS1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		    last_SIGN=0;
		    nrLB=1;nrPB=0;
		    lex=new Lexicon();
		    initializePositionWindows();
	    }
// build Menus	 
	 public TagGUI getInterf(){
		 return this;
	 }
	 /**
	  * @return  the Help menu, a JMenu object which is placed in the menu bar of the mainWindow
	  */
	 public JMenu createAboutMenu(){
	 	   JMenu dateien=new JMenu ("About");
	 	   dateien.setFont(etiopicMenu);
	 	  aboutMenu=new JMenuItem("About GeTa");
	 	  aboutMenu.setFont(etiopicMenu);
	 	  aboutMenu.addActionListener(this);
	 	dateien.add(aboutMenu);
	 	  return dateien;
	 }
	 public JMenu createHelpMenu(){
	 	   JMenu dateien=new JMenu ("Help");
	 	   dateien.setFont(etiopicMenu);
	 	  //aboutMenu=new JMenuItem("About GeTa");
	 	  //aboutMenu.setFont(etiopicMenu);
	 	  //aboutMenu.addActionListener(this);
	 	   
	 	  colorConvention=new JMenuItem("Formatting Key");
	 	  colorConvention.setFont(etiopicMenu);
	 	   colorConvention.addActionListener(this);
	 	   dateien.add(colorConvention);
	 	  correctIndex=new JMenuItem("Verify Index");
	 	  correctIndex.setFont(etiopicMenu);
	 	   correctIndex.addActionListener(this);
	 	  verifyNouns=new JMenuItem("Verify Nouns");
	 	  verifyNouns.setFont(etiopicMenu);
	 	   verifyNouns.addActionListener(this);
	 	   
	 	  correctMorpho=new JMenuItem("Correct");
	 	  correctMorpho.setFont(etiopicMenu);
	 	   correctMorpho.addActionListener(this);
	 	 
	 	  correctInscriptions=new JMenuItem("Correct Line Break Nr.");
	 	  correctInscriptions.setFont(etiopicMenu);
	 	   correctInscriptions.addActionListener(this);
	 	 //  dateien.add(aboutMenu);
	 	   dateien.add(correctIndex);
	 	   dateien.add(verifyNouns);
	 	   dateien.add(correctMorpho);
	 	   dateien.add(correctInscriptions);
	 	   return dateien;
	 } 	   
	 public JMenu createAutomaticMenu(){
	 	   JMenu dateien=new JMenu ("Automatic Annotation");
	 	   dateien.setFont(etiopicMenu);
	 	   createNewEntries=new JMenuItem("Create New Entries");
	 	  createNewEntries.setFont(etiopicMenu);
	 	
	 	   createNewEntries.addActionListener(this);
	 	   dateien.add(createNewEntries);
	 	 dateien.setEnabled(false);
	 	 
	 	   return dateien;
	 } 	   
	 
	 public JMenu createFileMenu(){
	 	   JMenu dateien=new JMenu ("File");
	 	  
	 		dateien.setFont(etiopicMenu);
	 		
	 	   dateien.setMnemonic('F');
	 	      openFile=new JMenu("Open");
	 	   openFile.setMnemonic('O');
	 	   openFile.setFont(etiopicMenu);
	 	         newFile=new JMenuItem ("New fidal file", 'n');
	 	         newFile.setFont(etiopicMenu);
	 	         setCtrlAccelerator(newFile, 'N');
	 	          newFile.addActionListener(this);
	 	          openFile.add(newFile);
	 	          annotFile=new JMenuItem ("Annotated file", 'a');
	 	          annotFile.setFont(etiopicMenu);
	 	          setCtrlAccelerator(annotFile, 'A');
	 	          annotFile.addActionListener(this);
	 	            openFile.add(annotFile);
	 	dateien.add(openFile);
	 	dateien.addSeparator();
	 	closeFile=new JMenuItem ("Close",'c');
	 	closeFile.setFont(etiopicMenu);
	 	setCtrlAccelerator(closeFile, 'C');
	 	closeFile.setEnabled(false);
	 	closeFile.addActionListener(this);
	 	dateien.add(closeFile);
	 	dateien.addSeparator();
	 	saveFile=new JMenuItem ("Save",'s');
	 	saveFile.setFont(etiopicMenu);
	 	setCtrlAccelerator(saveFile, 'S');
	 	saveFile.setEnabled(false);
	 	saveFile.addActionListener(this);
	 	dateien.add(saveFile);
	 	saveAsFile=new JMenuItem ("Save As...");
	 	saveAsFile.setFont(etiopicMenu);
	 	saveAsFile.addActionListener(this);
	 	saveAsFile.setEnabled(false);
	 	dateien.add(saveAsFile);
	 	saveParts=new JMenuItem ("Save Parts");
	 	saveParts.setFont(etiopicMenu);
	 	saveParts.addActionListener(this);
	 	//saveParts.setEnabled(false);
	 	dateien.add(saveParts);
	 	dateien.addSeparator();
	 	createMeta=new JMenuItem("Create Metadata");
	 	createMeta.setFont(etiopicMenu);
	 	createMeta.addActionListener(this);
	 	dateien.add(createMeta);
	 	dateien.addSeparator();
	 	importFile=new JMenu("Import");
	 	importFile.setFont(etiopicMenu);
	 	importFile.setMnemonic('I');
	 	importFile.setEnabled(false);
	 	importTEIFile=new JMenuItem("TEI");
	 	importTEIFile.setFont(etiopicMenu);
	 	importTEIFile.setEnabled(false);
	 	importTEIFile.addActionListener(this);
	 	importNEs=new JMenuItem("Import NEs");
	 	importNEs.setFont(etiopicMenu);
	 	importNEs.setEnabled(false);
	 	importNEs.addActionListener(this);
	 	importFile.add(importNEs);
	 	dateien.add(importFile);
	 	exportFile=new JMenu("Export");
	 	exportFile.setMnemonic('E');
	 	exportFile.setFont(etiopicMenu);
	 	exportFile.setEnabled(false);
	 	exportTEIFile=new JMenu("TEI");
	 	exportTEIFile.setFont(etiopicMenu);
	 	exportTEIFile.setMnemonic('T');
	 	exportTEIFile.setEnabled(false);
	 	exportANNIS=new JMenuItem("Export to Converter");
	 	exportANNIS.setFont(etiopicMenu);
	 	exportANNIS.setEnabled(false);
	 	exportANNIS.addActionListener(this);
	 	exportTEILing=new JMenuItem("Linguistic Annotation");
	 	exportTEILing.setFont(etiopicMenu);
	 	exportTEILing.setEnabled(false);
	 	exportTEILing.addActionListener(this);
	 //	exportTEIFile.add(exportTEILing);
	 	exportTEIStruct=new JMenuItem("Structure/NE Annotation");
	 	exportTEIStruct.setFont(etiopicMenu);
	 	exportTEIStruct.setEnabled(false);
	 	exportTEIStruct.addActionListener(this);
	 	//exportTEIFile.add(exportTEIStruct);
	 	exportTEIAll=new JMenuItem("All Annotations");
	 	exportTEIAll.setFont(etiopicMenu);
	 	exportTEIAll.setEnabled(false);
	 	exportTEIAll.addActionListener(this);
	 	exportTEIFile.add(exportTEIAll);
	 	exportWordID=new JMenuItem ("Export Words with Ids");
	 	exportWordID.setFont(etiopicMenu);
	 	exportWordID.setEnabled(false);
	 	exportWordID.addActionListener(this);
	 	exportFile.add(exportANNIS);
	 	exportFile.add(exportTEIFile);
	 	
	 	dateien.add(exportFile);
	 	dateien.add(exportWordID);
	 	dateien.addSeparator();
	 	exitTool=new JMenuItem("Exit");
	 	exitTool.setFont(etiopicMenu);
	 	exitTool.addActionListener(this);
	 	dateien.add(exitTool);
	 	return dateien;
	 	
	 }
	    
	    public JMenu createHistoryMenu(){
	    	JMenu dateien=new JMenu ("History");
	    	   
	    		dateien.setFont(etiopicMenu);
	    		
	    	   dateien.setMnemonic('H');
	    	      mUndo=new JMenuItem("Undo",'u');
	    	         mUndo.setFont(etiopicMenu);
	    	         setCtrlAccelerator(mUndo, 'U');
	    	         mUndo.setEnabled(false);
	    	          mUndo.addActionListener(this);
	    	          dateien.add(mUndo);
	    	          mRedo=new JMenuItem("Redo",'r');
	    		         mRedo.setFont(etiopicMenu);
	    		         mRedo.setEnabled(false);
	    		         setCtrlAccelerator(mRedo, 'R');
	    		          mRedo.addActionListener(this);
	    		          dateien.add(mRedo);
	    		          annotHistory=new JMenu("Annotation History");
	    		          annotHistory.setFont(etiopicMenu);
	    		          annotHistory.setEnabled(false);
	    		          annotHistoryLing=new JMenuItem("Linguistic Annotation");
	    		          annotHistoryLing.setEnabled(false);
	    		          annotHistoryLing.setFont(etiopicMenu);
	    		          annotHistoryLing.addActionListener(this);
	    		          annotHistoryStruct=new JMenuItem("Structure Annotation");
	    		          annotHistoryStruct.setFont(etiopicMenu);
	    		          annotHistoryStruct.setEnabled(false);
	    		          annotHistoryStruct.addActionListener(this);
	    		          annotHistoryNE=new JMenuItem("NE Annotation");
	    		          annotHistoryNE.setFont(etiopicMenu);
	    		          annotHistoryNE.setEnabled(false);
	    		          annotHistoryNE.addActionListener(this);
	    		          annotHistory.add(annotHistoryLing);
	    		          annotHistory.add(annotHistoryNE);
	    		          annotHistory.add(annotHistoryStruct);
	    		          dateien.setEnabled(false);
	    		          dateien.add(annotHistory);
	    		          
	    	     return dateien;     
	    	}
	    	public JMenu createShowWindowMenu(){
	    		JMenu dateien=new JMenu ("Show Window");
	    		   
	    			dateien.setFont(etiopicMenu);
	    			
	    			          showLingAnnotWindow=new JMenuItem("Linguistic Annotation Window");
	    				        showLingAnnotWindow.setFont(etiopicMenu);
	    				       showLingAnnotWindow.setEnabled(false);
	    				          showLingAnnotWindow.addActionListener(this);
	    				          dateien.add(showLingAnnotWindow);
	    				          showStructureAnnotWindow=new JMenuItem("Structure Annotation Window");
	    					        showStructureAnnotWindow.setFont(etiopicMenu);
	    					       showStructureAnnotWindow.setEnabled(false);
	    					          showStructureAnnotWindow.addActionListener(this);
	    					          dateien.add(showStructureAnnotWindow);
	    					          errorWindow=new JMenuItem("Error Window");
	  	    				        errorWindow.setFont(etiopicMenu);
	  	    				       errorWindow.setEnabled(false);
	  	    				          errorWindow.addActionListener(this);
	  	    				          dateien.add(errorWindow);
	    		          return dateien;
	    	}
	    	public JMenu createToolsMenu(){
	    		JMenu dateien=new JMenu("Tools");
	    		 
	    			dateien.setFont(etiopicMenu);
	    			searchMenu=new JMenuItem("Search");
	    	       searchMenu.setFont(etiopicMenu);
	    	       searchMenu.setEnabled(false);
	    	          searchMenu.addActionListener(this);
	    	          dateien.add(searchMenu);
	    	          recognSentMenu=new JMenuItem("Recognize Sentences");
	    	          recognSentMenu.setFont(etiopicMenu);
	    	         recognSentMenu.setEnabled(false);
	    	          recognSentMenu.addActionListener(this);
		    	         // dateien.add(recognSentMenu);
	    	          undoMenu=new JMenuItem("Undo");
		    	       undoMenu.setFont(etiopicMenu);
		    	       undoMenu.setEnabled(false);
		    	          undoMenu.addActionListener(this);
	    	        //  dateien.add(undoMenu);
	    	          rulesDB=new JMenu("Rules Databases");
	    	          rulesDB.setFont(etiopicMenu);
	    	          rulesDB.setEnabled(false);
	    	         newRulesDB=new JMenuItem("Add New Rules");
	    	         newRulesDB.setEnabled(false);
	    	         newRulesDB.setFont(etiopicMenu);
	    	         newRulesDB.addActionListener(this);
	    	        
	    	         rulesDB.add(newRulesDB);
	    	         // tokRulesDB=new JMenuItem("Tokenisation Rules");
	    	         // tokRulesDB.setFont(etiopicMenu);
	    	          //tokRulesDB.setEnabled(false);
	    	          //tokRulesDB.addActionListener(this);
	    	          //rulesDB.add(tokRulesDB);
	    	          //annotRulesDB=new JMenuItem("Linguistic Annotation Rules");
	    	          //annotRulesDB.setFont(etiopicMenu);
	    	          //annotRulesDB.setEnabled(false);
	    	          //annotRulesDB.addActionListener(this);
	    	          //rulesDB.add(annotRulesDB);
	    	          dateien.add(rulesDB);
	    	          lexiconMenu=new JMenuItem("Lexicon");
	    	          lexiconMenu.setFont(etiopicMenu);
	    	          lexiconMenu.setEnabled(false);
	    	          lexiconMenu.addActionListener(this);
	    	          dateien.add(lexiconMenu);
	    	          statistics=new JMenu("Statistics");
	    	          statistics.setFont(etiopicMenu);
	    	          statistics.setEnabled(false);
	    	         statDocument=new JMenuItem("Document");
	    	        statDocument.setEnabled(false);
	    	         statDocument.setFont(etiopicMenu);
	    	        statDocument.addActionListener(this);
	    	         statistics.add(statDocument);
	    	          statProgress=new JMenuItem("Progress");
	    	          statProgress.setFont(etiopicMenu);
	    	          statProgress.setEnabled(false);
	    	          statProgress.addActionListener(this);
	    	          statistics.add(statProgress);
	    	          dateien.add(statistics);
	    	          
	    		return dateien;
	    	}
	    	
	    	private JMenu createViewAnnotationsMenu(){
	    		JMenu dateien=new JMenu("View/Configure  Annotations");
	    		firstLB=new JMenuItem ("Set LB start number");
	    		firstLB.setFont(etiopicMenu);
	    		firstLB.setEnabled(false);
	    		firstLB.addActionListener(this);
	    		structDiv=new JMenuItem ("Define/View text structure levels");
	    		structDiv.setFont(etiopicMenu);
	    		structDiv.setEnabled(false);
	    		structDiv.addActionListener(this);
	    		dateien.setFont(etiopicMenu);
	    		
	    		delDivs=new JMenuItem ("Clear Structure");
	    		delDivs.setFont(etiopicMenu);
	    		delDivs.setEnabled(false);
	    		delDivs.addActionListener(this);
	    		
	    		divMenu=new JMenu("View Text Structure");
	           divMenu.setFont(etiopicMenu);
	           divMenu.setEnabled(false);
	              div1Menu=new StayOpenCheckBoxMenuItem("Level 1");
	              div1Menu.setFont(etiopicMenu);
	              div1Menu.setEnabled(false);
	              div1Menu.addItemListener(new ItemListener() {
	            	  public void itemStateChanged(ItemEvent e){
	            		if(div1Menu.isSelected()) {
	            			showDiv1o=true; showDiv1e=true; fidalWordList.repaint();
	            		}
	            		else {
	            			showDiv1o=false; showDiv1e=false; fidalWordList.repaint();
	            		}
	            	  }
	              });
	              divMenu.add(div1Menu);
	              div2Menu=new StayOpenCheckBoxMenuItem("Level 2");
	              div2Menu.setFont(etiopicMenu);
	              div2Menu.setEnabled(false);
	              div2Menu.addItemListener(new ItemListener() {
	            	  public void itemStateChanged(ItemEvent e){
	            		  if(div2Menu.isSelected()) {
		            			showDiv2o=true; showDiv2e=true;fidalWordList.repaint();
		            		}
		            		else {
		            			showDiv2o=false; showDiv2e=false;fidalWordList.repaint();
		            		} 
	            	  }
	              });
	              divMenu.add(div2Menu);
	              div3Menu=new StayOpenCheckBoxMenuItem("Level 3");
	              div3Menu.setFont(etiopicMenu);
	              div3Menu.setEnabled(false);
	              div3Menu.addItemListener(new ItemListener() {
	            	  public void itemStateChanged(ItemEvent e){
	            		  if(div3Menu.isSelected()) {
		            			showDiv3o=true; showDiv3e=true;fidalWordList.repaint();
		            		}
		            		else {
		            			showDiv3o=false; showDiv3e=false;fidalWordList.repaint();
		            		} 
	            	  }
	              });
	              divMenu.add(div3Menu);
	              div4Menu=new StayOpenCheckBoxMenuItem("Level 4");
	              div4Menu.setFont(etiopicMenu);
	              div4Menu.setEnabled(false);
	              div4Menu.addItemListener(new ItemListener() {
	            	  public void itemStateChanged(ItemEvent e){
	            		  if(div4Menu.isSelected()) {
		            			showDiv4o=true; showDiv4e=true;fidalWordList.repaint();
		            		}
		            		else {
		            			showDiv4o=false; showDiv4e=false;fidalWordList.repaint();
		            		}
	            	  }
	              });
	              divMenu.add(div4Menu);
	              dateien.add(firstLB);
	              dateien.add(structDiv);
	              dateien.add(delDivs);
	              dateien.add(divMenu);
	              neMenu=new JMenuItem("Disable NEs");
	              neMenu.addActionListener(this);
	            /*  neMenu.setFont(etiopicMenu);
	              neMenu.setEnabled(false);
	              persMenu=new StayOpenCheckBoxMenuItem("Persons");
	              persMenu.setFont(etiopicMenu);
	              persMenu.setEnabled(false);
	              persMenu.addItemListener(new ItemListener() {
	            	  public void itemStateChanged(ItemEvent e){
	            		//hier code  
	            	  }
	              });
	              neMenu.add(persMenu);
	              
	              orgMenu=new StayOpenCheckBoxMenuItem("Organisations");
	              orgMenu.setFont(etiopicMenu);
	              orgMenu.setEnabled(false);
	              orgMenu.addItemListener(new ItemListener() {
	            	  public void itemStateChanged(ItemEvent e){
	            		//hier code  
	            	  }
	              });
	              neMenu.add(orgMenu);
	              
	              placesMenu=new StayOpenCheckBoxMenuItem("Places");
	              placesMenu.setFont(etiopicMenu);
	              placesMenu.setEnabled(false);
	              placesMenu.addItemListener(new ItemListener() {
	            	  public void itemStateChanged(ItemEvent e){
	            		//hier code  
	            	  }
	              });
	              neMenu.add(placesMenu);
	              
	              datesMenu=new StayOpenCheckBoxMenuItem("Dates");
	              datesMenu.setFont(etiopicMenu);
	              datesMenu.setEnabled(false);
	              datesMenu.addItemListener(new ItemListener() {
	            	  public void itemStateChanged(ItemEvent e){
	            		//hier code  
	            	  }
	              });
	              neMenu.add(datesMenu);
	              
	              nrMenu=new StayOpenCheckBoxMenuItem("Numbers");
	              nrMenu.setFont(etiopicMenu);
	              nrMenu.setEnabled(false);
	              nrMenu.addItemListener(new ItemListener() {
	            	  public void itemStateChanged(ItemEvent e){
	            		//hier code  
	            	  }
	              });
	              neMenu.add(nrMenu);*/
	              
	           /*   abrevMenu=new StayOpenCheckBoxMenuItem("Abbreviations");
	              abrevMenu.setFont(etiopicMenu);
	              abrevMenu.setEnabled(false);
	              abrevMenu.addItemListener(new ItemListener() {
	            	  public void itemStateChanged(ItemEvent e){
	            		//hier code  
	            	  }
	              });
	              neMenu.add(abrevMenu);*/
	              
	            /*  titlesMenu=new StayOpenCheckBoxMenuItem("Titles");
	              titlesMenu.setFont(etiopicMenu);
	              titlesMenu.setEnabled(false);
	              titlesMenu.addItemListener(new ItemListener() {
	            	  public void itemStateChanged(ItemEvent e){
	            		//hier code  
	            	  }
	              });*/
	            //  neMenu.add(titlesMenu);
	              dateien.add(neMenu);
	              return dateien;
	    	}
	    	
	    	public JMenu  createKeyboardsMenu(){
	    		JMenu dateien=new JMenu("Keyboards");
	    		 
	    		dateien.setFont(etiopicMenu);
	    		geezWMenu=new JMenuItem("G\u01dd\u02bf\u01ddz");
	    		geezWMenu.setFont(etiopicMenu);
	    		geezWMenu.setEnabled(false);
	    		geezWMenu.addActionListener(this);
	              dateien.add(geezWMenu);
	              transcrWMenu=new JMenuItem("Transcription");
	      		transcrWMenu.setFont(etiopicMenu);
	      		transcrWMenu.setEnabled(false);
	      		transcrWMenu.addActionListener(this);
	                dateien.add(transcrWMenu);
	                dateien.setEnabled(false);
	                return dateien;
	    	}
	    	
// operations on Window and menus
	 
	 public void activateMenus(int flag){
 		if (flag>=1){
 			openFile.setEnabled(false);
 			newFile.setEnabled(false);
 			annotFile.setEnabled(false);
 			closeFile.setEnabled(true);
 			exportFile.setEnabled(true);
 			importFile.setEnabled(true);
 			importNEs.setEnabled(true);
 			createMeta.setEnabled(true);
 			exportTEIFile.setEnabled(true);
 			annotHistory.setEnabled(true);
 			//rulesDB.setEnabled(true);
 			statistics.setEnabled(true);
 			divMenu.setEnabled(true);
 			neMenu.setEnabled(true);
 			exitTool.setEnabled(true);
 			if(flag==1)saveFile.setEnabled(false);
 			else saveFile.setEnabled(true);
 			saveAsFile.setEnabled(true);
 			exportWordID.setEnabled(true);
 			importTEIFile.setEnabled(false);
 			exportTEIStruct.setEnabled(true);
 			exportANNIS.setEnabled(true);
 			exportTEILing.setEnabled(true);
 			exportTEIAll .setEnabled(true);
 			mUndo.setEnabled(false); 
 			mRedo.setEnabled(false);
 			annotHistoryLing.setEnabled(true);
 			annotHistoryNE.setEnabled(true);
 			annotHistoryStruct.setEnabled(true);
 		    
 		    showStructureAnnotWindow.setEnabled(true);
 		    showLingAnnotWindow.setEnabled(true);
 		    errorWindow.setEnabled(true);
 		    searchMenu.setEnabled(true);
 		   undoMenu.setEnabled(true);
 		  // newRulesDB.setEnabled(true);
 		  //  newRulesDB.setEnabled(true);
 		   // annotRulesDB.setEnabled(true);
 		   // statDocument.setEnabled(true);
 		    statProgress.setEnabled(true);
 		   // lexiconMenu.setEnabled(true);
 		    if (hasStructSpec){
 		    	if(noStruct==1)
 		    	{ div1Menu.setEnabled(true); div1Menu.setSelected(true);}
 		       if(noStruct==2)
 		    	   {div2Menu.setEnabled(true);div2Menu.setSelected(true);
 		    	  div1Menu.setEnabled(true);div1Menu.setSelected(true);
 		    	   }
 		       if(noStruct==3) {div3Menu.setEnabled(true);div3Menu.setSelected(true);div1Menu.setEnabled(true); div1Menu.setSelected(true);div2Menu.setEnabled(true); div2Menu.setSelected(true);}
 		      if(noStruct==4) {div4Menu.setEnabled(true);div1Menu.setSelected(true);div1Menu.setEnabled(true); div1Menu.setSelected(true);div2Menu.setEnabled(true); div2Menu.setSelected(true);div3Menu.setEnabled(true); div3Menu.setSelected(true);}
 		   delDivs.setEnabled(true);
 		    }
 		    structDiv.setEnabled(true);
 		   firstLB.setEnabled(true);
 		 //   persMenu.setEnabled(true);
 		    //orgMenu.setEnabled(true);
 		    //placesMenu.setEnabled(true);
 		    //nrMenu.setEnabled(true);
 		    //datesMenu.setEnabled(true);
 		    //abrevMenu.setEnabled(true);
 		    //titlesMenu.setEnabled(true);
 		  //  geezWMenu.setEnabled(true);
 		  //  transcrWMenu.setEnabled(true);
 		   correctMorpho.setEnabled(true);
 		   
 		}
 		else{
 			openFile.setEnabled(true);
 			newFile.setEnabled(true);
 			annotFile.setEnabled(true);
 			closeFile.setEnabled(false);
 			exportFile.setEnabled(false);
 			exportWordID.setEnabled(false);
 			importFile.setEnabled(false);
 			importNEs.setEnabled(false);
 			createMeta.setEnabled(false);
 			exportTEIFile.setEnabled(false);
 			annotHistory.setEnabled(false);
 		firstLB.setEnabled(true);
 			rulesDB.setEnabled(false);
 			statistics.setEnabled(false);
 			structDiv.setEnabled(false);
 			firstLB.setEnabled(false);
 			divMenu.setEnabled(false);
 			neMenu.setEnabled(false);
 			exitTool.setEnabled(false);
 			saveFile.setEnabled(false);
 			saveAsFile.setEnabled(false);
 			importTEIFile.setEnabled(false);
 			exportTEIStruct.setEnabled(false);
 			exportANNIS.setEnabled(false);
 			exportTEILing.setEnabled(false);
 			exportTEIAll .setEnabled(false);
 			mUndo.setEnabled(false); 
 			mRedo.setEnabled(false);
 			annotHistoryLing.setEnabled(false);
 			annotHistoryNE.setEnabled(false);
 			annotHistoryStruct.setEnabled(false);
 		   
 		    showStructureAnnotWindow.setEnabled(false);
 		    showLingAnnotWindow.setEnabled(false);
 		    errorWindow.setEnabled(false);
 		    searchMenu.setEnabled(false);
 		   undoMenu.setEnabled(true);
 		    newRulesDB.setEnabled(false);
 		   // tokRulesDB.setEnabled(false);
 		   // annotRulesDB.setEnabled(false);
 		    statDocument.setEnabled(false);
 		    statProgress.setEnabled(false);
 		    lexiconMenu.setEnabled(false);
 		    structDiv.setEnabled(false);
 		   firstLB.setEnabled(false);
 		    div1Menu.setEnabled(false);
 		    div2Menu.setEnabled(false);
 		    div3Menu.setEnabled(false);
 		    div4Menu.setEnabled(false);
 		   // persMenu.setEnabled(false);
 		   // orgMenu.setEnabled(false);
 		    //placesMenu.setEnabled(false);
 		    //nrMenu.setEnabled(false);
 		    //datesMenu.setEnabled(false);
 		    //abrevMenu.setEnabled(false);
 		    //titlesMenu.setEnabled(false);
 		    geezWMenu.setEnabled(false);
 		    transcrWMenu.setEnabled(false);
 		   correctMorpho.setEnabled(false);
 		   
 		}
 	}
	 
	 public void actionPerformed(ActionEvent event){
 		
 		String e=event.getActionCommand();
 		if (e.equals("New fidal file")){
 			
 			readNewFidalFile();
 			
 		}
 		if (e.equals("Save")){saveToFile();}
 		if(e.equals("Save As...")){saveAsToFile();}
 		if(e.equals("Save Parts")){savePartsToFile();}
 		if(e.equals("Export to Converter")){exportANNIS();}
 		if(e.equals("Export Words with Ids")){exportWordsIds();}
 		if(e.equals("All Annotations")){exportTEILing();}
 	if (e.equals("Annotated file")){readAnnotFile();}
 	if (e.equals("Close")){closeFile();}
 		if(e.equals("Exit")){exitTool();}
 		if(e.equals("Search")){showSearchWindow();}
 		if(e.equals("Undo")){doUndo();}
 		if(e.equals("Recognize Sentences")){recognizeSent();}
 		if(e.equals("Verify Index")){verifyIndex();}
 		if(e.equals("About GeTa")){
 			ChildFrame showAbout =new ChildFrame("About GeTa",Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
		    	JTextArea showAboutText=new JTextArea();
				showAboutText.setFont(etiopic1);
				showAboutText.setSize(500, 800);
				showAboutText.setEditable(false);
			      String Text="GeTa Annotation Tool \nVersion 1.0 / 2019  \n"+
				"\n The tool was developed with the funding obtained from the European Research Council (ERC) under the European Union’s 7th Framework Programme \n (ERC Advanced Grant TraCES, grant agreement 338756, PI Alessandro Bausi, <https://www.traces.uni-hamburg.de>).\n"
			      		+ "\n Available under CC BY-NC-SA 4.0 \nHiob-Ludolf Centre for Ethiopian Studies - University of Hamburg \nAuthor: Cristina Vertan - cristina.vertan@uni-hamburg.de";
			        showAboutText.setCaretPosition(0);
				
				showAboutText.setText(Text);
				JScrollPane sp=new JScrollPane(showAboutText);
				showAbout.add(sp);
				showAbout.pack(); 
				this.addChild(showAbout,10, 10, 1000, 300);
				showAbout.moveToFront();
		    //}
 			
 			
 		}
 		if(e.equals("Verify Nouns")){verifyNouns();}
 		if(e.equals("Import NEs")){importNE();}
 		if(e.equals("Create Metadata")) {createMetadata();}
 		if(e.equals("Correct")){correctMorpho();}
 		if(e.equals("Correct Line Break Nr.")){correctInscriptions();}
 		if(e.equals("Progress")){showProgress();}
 		if(e.equals("Add New Rules")){showRulesFile();}
 		if(e.equals("Define/View text structure levels")){defineLevels();}
 		if(e.equals("Set LB start number")){defineFirstLineNumber();}
 		if(e.equals("Clear Structure")){clearLevels();}
 		if(e.equals("Disable NEs")){viewne=false;neMenu.setText("View NEs");neMenu.repaint();}
 		if(e.equals("View NEs")){viewne=true;neMenu.setText("Disable NEs");neMenu.repaint();}
 		if(e.equals("Formatting Key")){
 			//if ((showColors !=null) && showColors.isClosed()) {
 		    	showColors=new ChildFrame("Show conventions in linguistic annotation",Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
 		    	showColorsText=new JTextPane();
 				showColorsText.setFont(etiopic1);
 				showColorsText.setSize(300, 200);
 				showColorsText.setContentType("text/html");
 				showColorsText.setEditorKit(new HTMLEditorKit());
 				showColorsText.setEditable(false);
 			      String Text="";
 			     showColorsText = new JEditorPane("text/html",Text);

 			         String bodyRule = "body { font-family: " + etiopic1.getFamily() + "; " +
 			                 "font-size: " + etiopic1.getSize() + "pt; }";
 			         ((HTMLDocument)showColorsText.getDocument()).getStyleSheet().addRule(bodyRule);
 			         //searchPane.setEditable(false);
 			      //   editor.set
 			      
 			        showColorsText.setCaretPosition(0);
 				String automTokenize ="<ul><li><i> Automatic Tokenisation </i> is changed automatically when linguistic or NE annotation is attached</li>";
 				String globalLingannotNotC="<li><font color=\"red\"> <b>Global Linguistic Annotation</b></font></li>";
 				String globalLingannotC="<li><font color=\"red\"> Global Linguistic Annotation Complete</font></li>";
 				String manualLingannotNotC="<li><b>Manual Linguistic Annotation</b></li>";
 				String manualLingannotC="<li><font color=\"blue\"> Manual Linguistic Annotation Complete</font></li>";
 				String ne= "<li> <u>Named Entity</u></li></ul>";
 				String note= "All that is <font color=\"blue\">blue</font> is finalized and manually checked";
 				
 				String s=automTokenize+"<br/>"+	globalLingannotNotC+"<br/>"+globalLingannotC+"<br/>"+
 						manualLingannotNotC+"<br/>"+manualLingannotC+"<br/>"+ne+"<br/>"+note;
 				showColorsText.setText(s);
 				JScrollPane sp=new JScrollPane(showColorsText);
 				showColors.add(sp);
 				showColors.pack(); 
 				this.addChild(showColors,10, 10, 350, 280);
 				showColors.moveToFront();
 		    //}
 		}
 		if(e.equals("Structure Annotation Window")){if (!showPosition.isVisible()) showPosition.show(); 
 		//if (!showPositionPageBreak.isVisible()) showPositionPageBreak.show(); 
 		if (showPosition.isClosed()) showPosition.show();
 		//if (showPositionPageBreak.isClosed()) showPositionPageBreak.show();
 		}
 		if(e.equals("Linguistic Annotation Window")){
 			//if (!showAnnotation.isVisible()) showAnnotation.show(); 
 			showAnnotation=new ChildFrame("Show selected word's annotation",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
				showAnnotationText= new JEditorPane("text/html","");
				  String bodyRule = "body { font-family: " + etiopic1.getFamily() + "; " +
			                 "font-size: " + etiopic1.getSize() + "pt; }";
			         ((HTMLDocument)showAnnotationText.getDocument()).getStyleSheet().addRule(bodyRule);
				showAnnotationText.setFont(etiopic1);
				showAnnotationText.setSize(30, 30);
				
				showAnnotation.addInternalFrameListener(new InternalFrameListener() {
					public void internalFrameActivated(InternalFrameEvent event) {
					                            		}
					public void internalFrameClosed(InternalFrameEvent event) {
						showAnnotationX=showAnnotation.getX();
						showAnnotationY=showAnnotation.getY();
						showAnnotation.doDefaultCloseAction();
					}
					public void internalFrameOpened(InternalFrameEvent event) {
						
					}
					public void internalFrameClosing(InternalFrameEvent event) {
						showAnnotationX=showAnnotation.getX();
						showAnnotationY=showAnnotation.getY();
						
					}
					public void internalFrameDeiconified(InternalFrameEvent event) {
					}
					public void internalFrameDeactivated(InternalFrameEvent event) {
						showAnnotationX=showAnnotation.getX();
						showAnnotationY=showAnnotation.getY();

					}
					public void internalFrameIconified(InternalFrameEvent event) {
						showAnnotationX=showAnnotation.getX();
						showAnnotationY=showAnnotation.getY();
					}
					});

				JScrollPane sa=new JScrollPane(showAnnotationText);
				showAnnotation.add(sa);
				showAnnotation.pack(); 
				this.addChild(showAnnotation,showAnnotationX, showAnnotationY, 750, 120);
				showAnnotation.moveToFront();	
 		
 		
 		}
 		if(e.equals("Error Window")){
 			 errorWarning=new ChildFrame("Error", Color.RED,WindowConstants.DISPOSE_ON_CLOSE);
				// errorWarning.setSize(100,50);
					Container cs= errorWarning.getContentPane();
					
					
					cs.setLayout(new GridBagLayout());
					GridBagConstraints gbc=new GridBagConstraints();
					gbc.gridx=0;
					gbc.gridy=0;
					gbc.insets.left=2;
					gbc.insets.top=2;
					gbc.insets.bottom=2;
					gbc.insets.right=2;
					gbc.weighty=100.0;
					gbc.weightx=100.0;
					gbc.fill=GridBagConstraints.BOTH;
					gbc.anchor=GridBagConstraints.NORTHWEST;
				
					 errorPane=new JTextPane();
					errorPane.setSize(100, 50);
					  errorPane.setContentType("text/html");
				        errorPane.setEditorKit(new HTMLEditorKit());
				      errorPane.setEditable(false);
				      String Text="";
				         errorPane = new JEditorPane("text/html",Text);
				         errorPane.setFont(typFont);
				         String bodyRule1 = "body { font-family: " + typFont.getFamily() + "; " +
				                 "font-size: " + typFont.getSize() + "pt; }";
				         ((HTMLDocument)errorPane.getDocument()).getStyleSheet().addRule(bodyRule1);
				         //searchPane.setEditable(false);
				      //   editor.set
				      
				       
				       errorPane.setText(updateErrors());
				       errorPane.setCaretPosition(0); 
				       MyLinkController controller = new MyLinkController();
				        errorPane.addMouseListener(controller);
				        errorPane.addMouseListener(controller);
				        JScrollPane ep=new JScrollPane(errorPane);
				        cs.add(ep, gbc);
				        errorPane.addHyperlinkListener(new HyperlinkListener(){
						    public void hyperlinkUpdate(HyperlinkEvent e) {
						        if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
						        	String s=e.getURL().toString().substring(7);
						        	int pos=Integer.parseInt(s);
						        	translitWordList.setSelectedIndex(pos);
						        	fidalWordList.setSelectedIndex(pos);
						        	if ((fidalWordList.getSelectedIndex()+20)<fidalWordList.getModel().getSize()){
									     fidalWordList.ensureIndexIsVisible(fidalWordList.getSelectedIndex()+20);
									     translitWordList.ensureIndexIsVisible(translitWordList.getSelectedIndex()+20);
						        	}
									else {
										fidalWordList.ensureIndexIsVisible(fidalWordList.getSelectedIndex()+(fidalWordList.getModel().getSize()-fidalWordList.getSelectedIndex()-1));
										translitWordList.ensureIndexIsVisible(translitWordList.getSelectedIndex()+(translitWordList.getModel().getSize()-translitWordList.getSelectedIndex()-1));
									}
						        	//translitWordList.ensureIndexIsVisible(translitWordList.getSelectedIndex());
						        	//fidalWordList.ensureIndexIsVisible(fidalWordList.getSelectedIndex());
						         //  System.out.println("Event on "+ s);
						        
						    }
						    }
						});
				        errorPane.setCaretPosition(0);
				        errorWarning.pack();
				        this.addChild(errorWarning,1400,10,150,200);
					       // desk.add(errorWarning);
					     
					      
					        errorWarning.setVisible(true);
					        errorWarning.moveToFront();
					        errorWarning.setFocusable(true);
					        errorWarning.toFront();
					        errorWarning.requestFocus();
 		
 		
 		}
 		//if(e.equals("Search")) showSearchWindow();
 		/*if (e.equals("Undo")) {undoProcess();}
 		if (e.equals("Redo")) {redoProcess();}
 		if (e.equals("Ge'ez Keyboard")){
 			GeezFrame geez=new GeezFrame();
 			
 			geez.pack();
 			 
 			 this.addChild(geez,10, 10, 350, 400);
              geez.setVisible(true);
 			 geez.moveToFront();
 			 
 		}
 		if (e.equals("Latin Transcription Keyboard")){
 			LatinFrame transcr=new LatinFrame();
 			
 			transcr.pack();
 			 
 			 this.addChild(transcr,10, 10, 400, 200);
              transcr.setVisible(true);
 			 transcr.moveToFront();
 			 
 		}
 		if(e.equals("Show cursor position")){
 		    if ((showPosition !=null) && showPosition.isClosed()) {
 		    	showPosition=new ChildFrame("Show cursor position");
 				showPositionText= new JTextArea (30,30);
 				showPositionText.setFont(etiopic1);
 				JScrollPane sp=new JScrollPane(showPositionText);
 				showPosition.add(sp);
 				showPosition.pack(); 
 				this.addChild(showPosition,10, 10, 750, 120);
 				showPosition.moveToFront();
 		    }
 		}   tree = new JTree(top);
 		if(e.equals("Show selected word's annotation")){
 		    if ((showAnnotation !=null) && showAnnotation.isClosed()) {
 		    	showAnnotation=new ChildFrame("Show selected word's annotation");
 				showAnnotationText= new JTextArea (30,30);
 				showAnnotationText.setFont(etiopic1);
 				JScrollPane sa=new JScrollPane(showAnnotationText);
 				showAnnotation.add(sa);
 				showAnnotation.pack(); 
 				this.addChild(showAnnotation,780, 10, 750, 120);
 				showAnnotation.moveToFront();"number
 		    }
 		}*/ 
	 }
	public void defineFirstLineNumber() {
		 final ChildFrame nrWindow=new ChildFrame("Set the starting number for LB",Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
		 Container c= nrWindow.getContentPane();
		 c.setLayout(new GridBagLayout());
		 GridBagConstraints gbc=new GridBagConstraints();
		 gbc.gridx=0; gbc.gridy=0;
		 gbc.gridwidth=1; 
		 gbc.weightx=100;
		gbc.weighty=100;
		 gbc.anchor=GridBagConstraints.NORTHWEST;
		 gbc.fill=GridBagConstraints.HORIZONTAL;
		 JTextField setnr=new JTextField(10);
		 setnr.setText("1");
		 JButton ok=new JButton("OK");
		 JButton cancel=new JButton("Cancel");
		 JLabel labelnr=new JLabel("Nr. for first Line Break");
		 c.add(labelnr,gbc);
		 gbc.gridx=1; c.add(setnr,gbc);
		 gbc.gridx=0;gbc.gridy=1;
		 c.add(ok,gbc);
		 gbc.gridx=1;
		 c.add(cancel,gbc);
		 ok.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 startLBnr=Integer.parseInt(setnr.getText());
				 firstLB.setEnabled(false);
				 nrLB=startLBnr;
				// System.out.println("val lb :"+nrLB);
				 nrWindow.doDefaultCloseAction();
			 }
		 });
		 cancel.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				
				 nrWindow.doDefaultCloseAction();
			 }
		 });
		 nrWindow.setLocation(100, 100);
			//defLevelWindow.setSize(50,50);
			nrWindow.pack();
			nrWindow.show();
			nrWindow.pack();
			 addChild(nrWindow,100,150,250,150);
			 
			nrWindow.setVisible(true);
			nrWindow.moveToFront();
	}
	 public void createMetadata() {
		 final ChildFrame metaWindow=new ChildFrame("Document Metadata",Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
		 Container cfin=metaWindow.getContentPane();
		 JPanel c=new JPanel();
		 c.setLayout(new GridBagLayout());
		 GridBagConstraints gbc=new GridBagConstraints();
		 gbc.gridx=0; gbc.gridy=0;
		 gbc.gridwidth=1; 
		 gbc.weightx=100;
		gbc.weighty=100;
		 gbc.anchor=GridBagConstraints.NORTHWEST;
		 gbc.fill=GridBagConstraints.HORIZONTAL;
		 JPanel c1=new JPanel();
		final  JTextField linkServer=new JTextField(20);
		if (metadataDoc !=null) linkServer.setText(metadataDoc.getId());
		final JLabel labelLink=new JLabel("Please Insert BM Id");
	    c.add(labelLink,gbc);
		final JButton checkB=new JButton("Check");
		 c1.add(linkServer);c1.add(checkB);
		 gbc.gridy=1; gbc.gridwidth=2;
		 c.add(c1,gbc);
	JPanel c2=new JPanel(new GridLayout(4,0,5,3));
	JPanel c3=new JPanel(new GridLayout(4,0,5,3));
		final  JLabel titleENLabel=new JLabel("Title EN");
		c2.add(titleENLabel);
	final	 JTextField titleEN=new JTextField(30);
		 titleEN.setFont(etiopic1);
			if (metadataDoc !=null) titleEN.setText(metadataDoc.getTitleEN());
		 titleEN.setEditable(false);
		 titleEN.setEnabled(false);
	   c3.add(titleEN);
		 JLabel titleETLabel=new JLabel("Title G\u01ddʿ\u01ddz");
		c2.add(titleETLabel);
		
	final	 JTextField titleET=new JTextField(30);
		 titleET.setEditable(false);
		 titleET.setEnabled(false);
		 titleET.setFont(etiopic1);
			if (metadataDoc !=null) titleET.setText(metadataDoc.getTitleET());
	c3.add(titleET);
		 JLabel annotatorLabel =new JLabel("Annotator"); 
		c2.add(annotatorLabel);
		final JTextField annotator=new JTextField(30);
		 annotator.setFont(etiopic1);
		 annotator.setEnabled(false);
			if (metadataDoc !=null) annotator.setText(metadataDoc.getAnnotator());
	c3.add(annotator);
		 JLabel zoteroLabel=new JLabel("Zotero Link");	
		final JTextField zoteroLink=new JTextField(30);
		 zoteroLink.setEnabled(false);
			if (metadataDoc !=null) zoteroLink.setText(metadataDoc.getLinkZotero());
		c2.add(zoteroLabel);c3.add(zoteroLink);
		gbc.gridy=2;gbc.gridwidth=1;gbc.gridheight=4;
		c.add(c2,gbc);
		//gbc.gridx=1;
		JPanel c21=new JPanel();
		c21.add(c2); c21.add(c3);
		c.add(c21,gbc);
		 JLabel commLabel=new JLabel("Comment");
		gbc.gridy=6; gbc.gridheight=1; c.add(commLabel,gbc);
		final HTMLEditorPane commPane =new HTMLEditorPane();	      
		    commPane.setCaretPosition(0);
		    commPane.setEnabled(false);
		//    JScrollPane scommPane=new JScrollPane();
		//    scommPane.add(commPane);
		    gbc.fill=GridBagConstraints.BOTH;
		   commPane.setPreferredSize(new Dimension(300,300));
		   commPane.setAutoscrolls(true);
			if (metadataDoc !=null) commPane.setText(metadataDoc.getComment());
	JPanel c4=new JPanel();
	gbc.gridwidth=2;gbc.gridheight=3;gbc.gridy=7; gbc.fill=GridBagConstraints.BOTH;
	c.add(commPane,gbc);
		    JButton saveB=new JButton("Save");
		    saveB.setEnabled(false);
		  
		 
		    JButton cancelB=new JButton("Clear");
		 cancelB.setEnabled(false);
		 if (metadataDoc !=null) cancelB.setEnabled(true);
		 JPanel c5=new JPanel(); gbc.fill=GridBagConstraints.HORIZONTAL;
		 c5.add(saveB); c5.add(cancelB);
		 gbc.gridwidth=2;gbc.gridheight=1;gbc.gridy=10;
		 c.add(c5,gbc);
		 cfin.add(c);
		 
		 checkB.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				  String s=linkServer.getText();
				  String url="https://betamasaheft.eu/api/"+s+"/tei";
				  String answer= sendGet(url);
				  //System.out.println(answer);
				  if(!answer.equals("Error") &&(answer.indexOf("<TEI")>=0)) {
					  titleEN.setEnabled(true);
					  titleET.setEnabled(true);
					  annotator.setEnabled(true);
					  zoteroLink.setEnabled(true);
					  commPane.setEnabled(true);
					  saveB.setEnabled(true);
					  cancelB.setEnabled(true);
					  checkB.setEnabled(false);
					  try {
					  org.jdom2.input.SAXBuilder saxBuilder = new SAXBuilder();
						saxBuilder.setIgnoringBoundaryWhitespace(true);
						saxBuilder.setIgnoringElementContentWhitespace(true);
						InputStream stream = new ByteArrayInputStream(answer.getBytes("UTF-8"));
						org.jdom2.Document doc = saxBuilder.build(stream);
						Namespace ns=doc.getRootElement().getNamespace();
						//System.out.println("Children "+doc.getRootElement().getChild("teiHeader",ns).getChild("fileDesc",ns).getChild("titleStmt",ns).getChildren("title",ns).size());
						for(int i=0; i<doc.getRootElement().getChild("teiHeader",ns).getChild("fileDesc",ns).getChild("titleStmt",ns).getChildren("title",ns).size();i++) {
							//System.out.println("I= "+doc.getRootElement().getChild("teiHeader",ns).getChild("fileDesc",ns).getChild("titleStmt",ns).getChildren("title",ns).get(i).getAttributes().toString());
							if(doc.getRootElement().getChild("teiHeader",ns).getChild("fileDesc",ns).getChild("titleStmt",ns).getChildren("title",ns).get(i).getAttribute("type")!=null) {
								if(doc.getRootElement().getChild("teiHeader",ns).getChild("fileDesc",ns).getChild("titleStmt",ns).getChildren("title",ns).get(i).getAttribute("type").getValue().equals("normalized")) {
									titleET.setText((doc.getRootElement().getChild("teiHeader",ns).getChild("fileDesc",ns).getChild("titleStmt",ns).getChildren("title",ns).get(i).getTextNormalize()));
							}
							}
							else	if(doc.getRootElement().getChild("teiHeader",ns).getChild("fileDesc",ns).getChild("titleStmt",ns).getChildren("title",ns).get(i).getAttribute("corresp")!=null) {
							
								//System.out.println("Tile EN "+doc.getRootElement().getChild("teiHeader",ns).getChild("fileDesc",ns).getChild("titleStmt",ns).getChildren("title",ns).get(i).getTextNormalize());
									titleEN.setText((doc.getRootElement().getChild("teiHeader",ns).getChild("fileDesc",ns).getChild("titleStmt",ns).getChildren("title",ns).get(i).getTextNormalize()));
							}
						}
						
					  }
					  catch(Exception ex) {}
						
				  }
				  else  if(!answer.equals("Error")) {
					  JOptionPane.showMessageDialog(new JFrame(), "Please check the CLAVIS Id", "Dialog",
			       		        JOptionPane.ERROR_MESSAGE);
				  }
				  else {
					  JOptionPane.showMessageDialog(new JFrame(), "Please check the connection to Server", "Dialog",
			       		        JOptionPane.ERROR_MESSAGE);
				  }
			 }
		 });
		 saveB.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 //String id, String et,String en, String ann, String toolVer, String c, String linkZ, int typeVoc, int typeScr
				 
				 metadataDoc=new Metadata(linkServer.getText(),titleET.getText(),titleEN.getText(),annotator.getText(),commPane.getText(),zoteroLink.getText());
			   metaWindow.doDefaultCloseAction();
			 }
		 });
		 cancelB.addActionListener(new ActionListener() {
			 
			 public void actionPerformed(ActionEvent e) {
				 linkServer.setText("");
				 titleEN.setText(""); titleET.setText("");
				 annotator.setText("");
				 zoteroLink.setText("");
				 commPane.setText("");
				 checkB.setEnabled(true);
				 titleEN.setEnabled(false);
				 titleET.setEnabled(false);
				 annotator.setEnabled(false);
				 zoteroLink.setEnabled(false);
				 commPane.setEnabled(false);
				 saveB.setEnabled(false);
				 checkB.setEnabled(true);
			 }
		 });
		 metaWindow.setLocation(100, 100);
			//defLevelWindow.setSize(50,50);
			metaWindow.pack();
			metaWindow.show();
			metaWindow.pack();
			 addChild(metaWindow,100,150,450,550);
			 
			 metaWindow.setVisible(true);
			metaWindow.moveToFront();
	 }
	 public void doUndo1(){
		 words.clear();
		 for (int i=0;i<wordsBackup1.size();i++){
			 words.add(wordsBackup1.get(i).copyWortNode());
		 }
		
		tokens.clear();
		 for (int i=0;i<tokensBackup1.size();i++){
			 tokens.add(tokensBackup1.get(i).copyToken());
		 }
	 }
	 public String getPBs(int i) {
		 String s="";
		s= words.get(i).getFidalChildren().get(0).getPB()+ words.get(i).getFidalChildren().get(words.get(i).getFidalChildren().size()-1).getPB();
		 return s;
	 }
	 public void doUndo(){
		 words.clear();
		 for (int i=0;i<wordsBackup.size();i++){
			 words.add(wordsBackup.get(i).copyWortNode());
		 }
		 createIndexWords();
		tokens.clear();
		 for (int i=0;i<tokensBackup.size();i++){
			 tokens.add(tokensBackup.get(i).copyToken());
		 }
		 createIndexTokens();
		if(divisions!=null){
		 divisions.clear();
		// for (int i=0;i<divisionsBackup.size();i++){
			// divisions.add(divisionsBackup.get(i).copyDivision());
		 //}
		 createIndexDivisions();
		 }
		 verifyIndex();
		 wordsBackup.clear();
		 tokensBackup.clear();
		if (divisionsBackup!=null)
		 divisionsBackup.clear();
		 translitWordList.revalidate();
    		translitWordList.repaint();
    		 fidalWordList.revalidate();
     		fidalWordList.repaint();
		 undoMenu.setEnabled(false);
	 }
	 public void importNE(){
		
			ReadNE leserNE=new ReadNE(getInterf());
			nelist=leserNE.readNEFile(FileOpenedName.substring(0,FileOpenedName.lastIndexOf('.'))+"W.xml");
			//int i=0;
			//System.out.println("NO NEs "+ nelist.size());
			for(int i=0;i<nelist.size();i++){
				//System.out.println("i= "+i);
				ArrayList<RefWord> refw=nelist.get(i).getRefIDS();
			//	System.out.println("No Refs "+refw.size()+ " "+ nelist.get(i).getrefId());
				String labelRef="";
				for (int j=0;j<refw.size();j++){
					//System.out.println("ID WORD "+refw.get(j).getWID());
					if (words.get(indexIdWord.get(refw.get(j).getWID()).intValue()).getNERef().length()==0)
					words.get(indexIdWord.get(refw.get(j).getWID()).intValue()).setNERef(nelist.get(i).getId());
					for(int k=0;k<words.get(indexIdWord.get(refw.get(j).getWID()).intValue()).getTokenIds().size();k++) {
						tokens.get(indexIdToken.get(words.get(indexIdWord.get(refw.get(j).getWID()).intValue()).getTokenIds().get(k)).intValue()).setNERef(nelist.get(i).getId());
					}
					labelRef=labelRef+words.get(indexIdWord.get(refw.get(j).getWID()).intValue()).getTranslitLabel(typScript)+" ";
				//.out.println(words.get(indexIdWord.get(refw.get(j).getWID()).intValue()).getTranslitLabel(typScript));
				}
			/*	if(i==0){
				labelRef=labelRef.substring(0, labelRef.length()-1);
				int k=indexIdWord.get(refw.get(refw.size()-1).getWID()).intValue()+1;
				int start=k;
				while ((start+refw.size())<words.size()){
					String wordL="";
					//System.out.println("START "+start+ "REFW "+refw.size());
					for (int x=0;x<refw.size();x++)
						wordL=wordL+words.get(start+x).getTranslitLabel(typScript)+" ";
					//System.out.println("WORDL "+wordL);
					
					wordL=wordL.substring(0, wordL.length()-1);
					
				    if(wordL.indexOf(labelRef)>=0){
				    	ArrayList<RefWord> tempR=new ArrayList<RefWord>();
				    	for (int x=0;x<refw.size();x++){
				    		tempR.add(new RefWord(words.get(start+x).getTranslitLabel(typScript),words.get(start+x).getTokenIds()));
				    	}
				    	NamedEntity neTemp=new NamedEntity("N"+UUID.randomUUID(), nelist.get(i).getrefId(),nelist.get(i).getTyp(),tempR,nelist.get(i).getAttr(),false,true);
				    	//nelist.add(neTemp);
				    	for (int x=0;x<refw.size();x++){
				    	words.get(start+x).setNERef(neTemp.getId());
				    	}
				    	start=start+refw.size();
				    	}
				    else start=start+1;
				}
			}*/
			}
			translitWordList.revalidate();translitWordList.repaint();

	 }
	 
	 
	  public ArrayList<String> hasSameTokens(String wid,ArrayList<String>tid){
		   ArrayList<String> identTokens=new ArrayList<String>();
		   ArrayList<String> wTokens=words.get(indexIdWord.get(wid).intValue()).getTokenIds();
		   for(int i=0;i<wTokens.size();i++)
			   for(int j=0;j<tid.size();j++)
				   if(tokens.get(indexIdToken.get(wTokens.get(i)).intValue()).getLabel().equals(tokens.get(indexIdToken.get(tid.get(j)).intValue()).getLabel()))
					   identTokens.add(wTokens.get(i));
		   return identTokens;
	   }
	 public Map<String,Integer> getIndexIdWords(){
		 return indexIdWord;
	 }
	 public void clearLevels() {
		 int confirm= JOptionPane.showOptionDialog(desk,
	                "You are going now delete all Divisions. After this operationYou have to redefine your structure Are you sure?",
	                "Delete Structure Confirmation", JOptionPane.YES_NO_OPTION,
	                JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (confirm == JOptionPane.YES_OPTION) {
		 noStruct=0;
		 div1Menu.setEnabled(false);div1Menu.setSelected(false);
		 div2Menu.setEnabled(false);div2Menu.setSelected(false);
		 div3Menu.setEnabled(false);div3Menu.setSelected(false);
		 structLevels.clear();
		 structLevels.put("1", "");
		 structLevels.put("2", "");
		 structLevels.put("3", "");
		 structLevels.put("4", "");
		 structLevelsBegin.clear();
		 structLevelsBegin.put("1", "1");
		 structLevelsBegin.put("2", "1");
		 structLevelsBegin.put("3", "1");
		 structLevelsBegin.put("4", "1");
		 structLevelsBeginName.clear();
		 structLevelsBeginName.put("1", "");
		 structLevelsBeginName.put("2", "");
		 structLevelsBeginName.put("3", "");
		 structLevelsBeginName.put("4", "");
		 hasStructSpec=false;
		 structDiv.setEnabled(true);
		 isSaved=false; saveFile.setEnabled(true);
		 divisions.clear();
		 divisionsBackup.clear();
		 indexIdDivision.clear();
		 delDivs.setEnabled(false);
		 for(int i=0;i<words.size();i++) {
			 if(words.get(i).getStrukturIds()!=null)
				 words.get(i).getStrukturIds().clear();
		 }
		 
			}	 
	 }
	 public void defineLevels(){
		 final ChildFrame defLevelWindow=new ChildFrame("Text Structure Levels",Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
			Container c=defLevelWindow.getContentPane();
			c.setLayout(new GridBagLayout());
			GridBagConstraints gbc=new GridBagConstraints();
			gbc.gridx=0; gbc.gridy=0;
			gbc.gridwidth=1;gbc.gridheight=1;
			gbc.weightx=100;gbc.weighty=100;
			gbc.insets.top=2;gbc.insets.bottom=2;gbc.insets.left=2;gbc.insets.right=2;
			gbc.anchor=GridBagConstraints.NORTHWEST;
			gbc.fill=GridBagConstraints.NONE;
			
			cbl1=new JCheckBox("Level 1");cbl1.setFont(etiopicMenu);
			tl1=new JTextField(4);tl1.setFont(etiopicMenu);
			tlN1=new JTextField(10);tlN1.setFont(etiopicMenu);
			cbl2=new JCheckBox("Level 2");cbl2.setFont(etiopicMenu);
			cbl2.setEnabled(false);
			tl2=new JTextField(4);tl2.setFont(etiopicMenu);tl2.setEnabled(false);tl2.setEditable(false);
			tlN2=new JTextField(10);tlN2.setFont(etiopicMenu);tlN2.setEnabled(false);tl2.setEditable(false);
			 cbl3=new JCheckBox("Level 3");cbl3.setFont(etiopicMenu);
			cbl3.setEnabled(false);
			tl3=new JTextField(4);tl3.setFont(etiopicMenu);tl3.setEnabled(false);tl3.setEditable(false);
			tlN3=new JTextField(10);tlN3.setFont(etiopicMenu);tlN3.setEnabled(false);tlN3.setEditable(false);
			cbl4=new JCheckBox("Level 4");cbl4.setFont(etiopicMenu);
			cbl4.setEnabled(false);
			tl4=new JTextField(4);tl4.setFont(etiopicMenu);tl4.setEnabled(false);tl4.setEditable(false);
			tlN4=new JTextField(10);tlN4.setFont(etiopicMenu);tlN4.setEnabled(false);tlN4.setEditable(false);
			String[] emptyN = { "-"};
			level1List = new JComboBox(nameStruct);level1List.setFont(etiopicMenu);level1List.setEnabled(false);
			if(structLevels.get("1").length()>0) {level1List.setSelectedItem(structLevels.get("1"));
			
			cbl1.setSelected(true); cbl1.setEnabled(false);
			}
			tl1.setText(structLevelsBegin.get("1"));
			tlN1.setText(structLevelsBeginName.get("1"));
			 level2List = new JComboBox(nameStruct);level2List.setFont(etiopicMenu);
			level2List.setEnabled(false);
			//level2List.addActionListener(new ComboListener(level2List));
			if(structLevels.get("2").length()>0) {level2List.setSelectedItem(structLevels.get("2"));
			cbl2.setSelected(true); cbl2.setEnabled(false);
			
			}
			tl2.setEnabled(true);tl2.setEditable(true);tl2.setText(structLevelsBegin.get("2"));
			tlN2.setEnabled(true);tlN2.setEditable(true);tlN2.setText(structLevelsBeginName.get("2"));
			 level3List = new JComboBox(nameStruct);level3List.setFont(etiopicMenu);
			level3List.setEnabled(false);
			if(structLevels.get("3").length()>0) {level3List.setSelectedItem(structLevels.get("3"));
			cbl3.setSelected(true); cbl3.setEnabled(false);
			
			}
			tl3.setEnabled(true);tl3.setEditable(true);tl3.setText(structLevelsBegin.get("3"));
			tlN3.setEnabled(true);tlN3.setEditable(true);tlN3.setText(structLevelsBeginName.get("3"));
			level4List = new JComboBox(nameStruct);level4List.setFont(etiopicMenu);
			level4List.setEnabled(false);
			if(structLevels.get("4").length()>0) {level4List.setSelectedItem(structLevels.get("4"));
			cbl4.setSelected(true); cbl4.setEnabled(false);
			
			}
			tl4.setEnabled(true);tl4.setEditable(true);tl4.setText(structLevelsBegin.get("4"));
			tlN4.setEnabled(true);tlN4.setEditable(true);tlN4.setText(structLevelsBeginName.get("4"));
			//level1List.setSelectedIndex(0);
			final JButton okB=new JButton("OK");okB.setFont(etiopicMenu);
			final JButton resB=new JButton("Reset");resB.setFont(etiopicMenu);
			if(hasStructSpec){ okB.setEnabled(false);resB.setEnabled(false);}
			cbl1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if (cbl1.isSelected()) {
						level1List.setEnabled(true);
						tl1.setEnabled(true);
						tlN1.setEditable(true);
					}
					else {
						level1List.setSelectedIndex(0);
						level1List.setEnabled(false);
						cbl2.setSelected(false);
						cbl2.setEnabled(false);
						tl2.setEnabled(false);
						tl2.setEditable(false);
						tlN2.setEnabled(false);
						tlN2.setEditable(false);
						level2List.setSelectedIndex(0);
						level2List.setEnabled(false);
						cbl3.setSelected(false);
						cbl3.setEnabled(false);
						level3List.setSelectedIndex(0);
						level3List.setEnabled(false);
						tl3.setEnabled(false);
						tl3.setEditable(false);
						tlN3.setEnabled(false);
						tlN3.setEditable(false);
						cbl4.setSelected(false);
						cbl4.setEnabled(false);
						level4List.setSelectedIndex(0);
						level4List.setEnabled(false);
						tl4.setEnabled(false);
						tl4.setEditable(false);
						tlN4.setEnabled(false);
						tlN4.setEditable(false);
					}
				}
			});
			cbl2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if (cbl2.isSelected()) {
						
						    level2List.setEnabled(true);
						    tl2.setEnabled(true);
							tl2.setEditable(true);
							 tlN2.setEnabled(true);
								tlN2.setEditable(true);
					}
					else {
						level2List.setSelectedIndex(0);
						level2List.setEnabled(false);
						tl2.setEnabled(false);
						tl2.setEditable(false);
						tlN2.setEnabled(false);
						tlN2.setEditable(false);
						cbl3.setSelected(false);
						cbl3.setEnabled(false);
					}
				}
			});
			cbl3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if (cbl3.isSelected()) {
						
						    level3List.setEnabled(true);
						    tl3.setEnabled(true);
							tl3.setEditable(true);
							 tlN3.setEnabled(true);
								tlN3.setEditable(true);
					}
					else {
						level3List.setSelectedIndex(0);
						level3List.setEnabled(false);
						tl3.setEnabled(false);
						tl3.setEditable(false);
						tlN3.setEnabled(false);
						tlN3.setEditable(false);
						cbl4.setSelected(false);
						cbl4.setEnabled(false);
					}
				}
			});
			cbl4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if (cbl4.isSelected()) {
						
						    level4List.setEnabled(true);
						    tl4.setEnabled(true);
							tl4.setEditable(true);
							  tlN4.setEnabled(true);
								tlN4.setEditable(true);
						   
					}
					else {
						level4List.setSelectedIndex(0);
						level4List.setEnabled(true);
						
					}
				}
			});
			level1List.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if (!level1List.getSelectedItem().toString().equals("-")) {
						cbl2.setEnabled(true);
						tlN1.setText(level1List.getSelectedItem().toString());
					}
					
					else {
						cbl1.setSelected(false);
						cbl2.setSelected(false);
						cbl2.setEnabled(false);
						tl2.setEnabled(false);
						tl2.setEditable(false);
						tlN2.setEnabled(false);
						tlN2.setEditable(false);
						level2List.setSelectedIndex(0);
						level2List.setEnabled(false);
						cbl3.setSelected(false);
						cbl3.setEnabled(false);
						tl3.setEnabled(false);
						tl3.setEditable(false);
						tlN3.setEnabled(false);
						tlN3.setEditable(false);
						level3List.setSelectedIndex(0);
						level3List.setEnabled(false);
						cbl4.setSelected(false);
						cbl4.setEnabled(false);
						tl4.setEnabled(false);
						tl4.setEditable(false);
						tlN4.setEnabled(false);
						tlN4.setEditable(false);
						level4List.setSelectedIndex(0);
						level4List.setEnabled(false);
					}
				}
			});
			level2List.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if (!level2List.getSelectedItem().toString().equals("-")){
						if((level2List.getSelectedIndex()==level1List.getSelectedIndex())||(level2List.getSelectedIndex()==level3List.getSelectedIndex())||(level2List.getSelectedIndex()==level4List.getSelectedIndex())){
							cbl2.setSelected(false);level2List.setSelectedIndex(0);level2List.setEnabled(false);cbl3.setEnabled(false);level3List.setSelectedIndex(0);cbl4.setEnabled(false);level4List.setSelectedIndex(0);
							tl2.setEnabled(false);
							tl2.setEditable(false);
							tlN2.setEnabled(false);
							tlN2.setEditable(false);
							level3List.setEnabled(false);cbl3.setSelected(false);
							tl3.setEnabled(false);
							tl3.setEditable(false);
							tlN3.setEnabled(false);
							tlN3.setEditable(false);
						    level4List.setEnabled(false);cbl4.setSelected(false);
						    tl4.setEnabled(false);
							tl4.setEditable(false);
							 tlN4.setEnabled(false);
								tlN4.setEditable(false);
						}
						else {cbl3.setEnabled(true);tlN2.setText(level2List.getSelectedItem().toString());}
					}
					else {
						cbl2.setSelected(false);
						//level2List.setSelectedIndex(0);
						//level2List.setEnabled(false);
						cbl3.setSelected(false);
						cbl3.setEnabled(false);
						tl3.setEnabled(false);
						tl3.setEditable(false);
						tlN3.setEnabled(false);
						tlN3.setEditable(false);
						level3List.setSelectedIndex(0);
						level3List.setEnabled(false);
						cbl4.setSelected(false);
						cbl4.setEnabled(false);
						level4List.setSelectedIndex(0);
						level4List.setEnabled(false);
						tl4.setEnabled(false);
						tl4.setEditable(false);
						tlN4.setEnabled(false);
						tlN4.setEditable(false);
					}
				}
			});
			level3List.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if (!level3List.getSelectedItem().toString().equals("-")) {
						if((level3List.getSelectedIndex()==level1List.getSelectedIndex())||(level3List.getSelectedIndex()==level2List.getSelectedIndex())||(level3List.getSelectedIndex()==level4List.getSelectedIndex())){
							cbl3.setSelected(false);level3List.setSelectedIndex(0);level3List.setEnabled(false);cbl4.setEnabled(false);level4List.setSelectedIndex(0);cbl4.setSelected(false);level4List.setEnabled(false);
							tl3.setEnabled(false);
							tl3.setEditable(false);
							tlN3.setEnabled(false);
							tlN3.setEditable(false);
						}
						else { cbl4.setEnabled(true);tlN3.setText(level3List.getSelectedItem().toString());}
					}
					else {
						cbl3.setSelected(false);
						cbl4.setSelected(false);
						cbl4.setEnabled(false);
						level4List.setSelectedIndex(0);
						level4List.setEnabled(false);
						tl4.setEnabled(false);
						tl4.setEditable(false);
						tlN4.setEnabled(false);
						tlN4.setEditable(false);
					}
				}
			});
			level4List.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if (!level4List.getSelectedItem().toString().equals("-")) {
						if((level4List.getSelectedIndex()==level1List.getSelectedIndex())||(level4List.getSelectedIndex()==level2List.getSelectedIndex())||(level4List.getSelectedIndex()==level3List.getSelectedIndex())){
							level4List.setSelectedIndex(0);cbl4.setSelected(false);level4List.setEnabled(false);
							tl4.setEnabled(false);
							tl4.setEditable(false);
							tlN4.setEnabled(false);
							tlN4.setEditable(false);
						}
						else {tlN4.setText(level4List.getSelectedItem().toString());}
					}
					
				}
			});
			okB.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if (!level1List.getSelectedItem().equals("-")){
						try{
							  int num = Integer.parseInt(tl1.getText());
							  structLevels.put("1",level1List.getSelectedItem().toString());
								noStruct=noStruct+1; div1Menu.setEnabled(true);div1Menu.setSelected(true);
								structLevelsBegin.put("1",tl1.getText());
								structLevelsBeginName.put("1",tlN1.getText());
							} catch (NumberFormatException e1) {
								 JOptionPane.showMessageDialog(new JFrame(), "Please insert a positive number", "Dialog",
						       		        JOptionPane.ERROR_MESSAGE);
							}
						
					}
					if (!level2List.getSelectedItem().equals("-")){
						try{
							  int num = Integer.parseInt(tl1.getText());
							 
							  structLevels.put("2",level2List.getSelectedItem().toString());
								noStruct=noStruct+1;
								structLevelsBegin.put("2",tl2.getText());div2Menu.setEnabled(true);div2Menu.setSelected(true);
								structLevelsBeginName.put("2",tlN2.getText());
							} catch (NumberFormatException e1) {
								 JOptionPane.showMessageDialog(new JFrame(), "Please insert a positive number", "Dialog",
						       		        JOptionPane.ERROR_MESSAGE);
							}
						
					}
					if (!level3List.getSelectedItem().equals("-")){
						try{
							  int num = Integer.parseInt(tl1.getText());
							 
							  structLevels.put("3",level3List.getSelectedItem().toString());
								noStruct=noStruct+1;
								structLevelsBegin.put("3",tl3.getText());div3Menu.setEnabled(true);div3Menu.setSelected(true);
								structLevelsBeginName.put("3",tlN3.getText());
							} catch (NumberFormatException e1) {
								 JOptionPane.showMessageDialog(new JFrame(), "Please insert a positive number", "Dialog",
						       		        JOptionPane.ERROR_MESSAGE);
							}
						
					}
					if (!level4List.getSelectedItem().equals("-")){
						try{
							  int num = Integer.parseInt(tl1.getText());
							 
							  structLevels.put("4",level4List.getSelectedItem().toString());
								noStruct=noStruct+1;
								structLevelsBegin.put("4",tl4.getText());div4Menu.setEnabled(true);div4Menu.setSelected(true);
								structLevelsBeginName.put("4",tlN4.getText());
							} catch (NumberFormatException e1) {
								 JOptionPane.showMessageDialog(new JFrame(), "Please insert a positive number", "Dialog",
						       		        JOptionPane.ERROR_MESSAGE);
							}
						
					}
					hasStructSpec=true; delDivs.setEnabled(true); 
					//recognSentMenu.setEnabled(true);
					  int confirm= JOptionPane.showOptionDialog(desk,
	             	                "Automatic Sentence Recognition?  You cannot do this later ! ",
	             	                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
	             	                JOptionPane.QUESTION_MESSAGE, null, null, null);
	             			if (confirm == JOptionPane.YES_OPTION) {
	             			recognizeSent();
	             			}
					defLevelWindow.doDefaultCloseAction();
				}
			});
			resB.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					cbl1.setSelected(false);
					cbl2.setSelected(false);cbl2.setEnabled(false);
					cbl3.setSelected(false);cbl3.setEnabled(false);
					cbl4.setSelected(false);cbl4.setEnabled(false);
					level1List.setSelectedIndex(0);level1List.setEnabled(false);
					level2List.setSelectedIndex(0);level2List.setEnabled(false);
					level3List.setSelectedIndex(0);level3List.setEnabled(false);
					level4List.setSelectedIndex(0);level4List.setEnabled(false);
					tl1.setEnabled(false);tl1.setEditable(false);
					tl2.setEnabled(false);tl2.setEditable(false);
					tl3.setEnabled(false);tl3.setEditable(false);
					tl4.setEnabled(false);tl4.setEditable(false);
					
					tlN1.setEnabled(false);tlN1.setEditable(false);
					tlN2.setEnabled(false);tlN2.setEditable(false);
					tlN3.setEnabled(false);tlN3.setEditable(false);
					tlN4.setEnabled(false);tlN4.setEditable(false);
					hasStructSpec=false;
					noStruct=0;
				}
			});
			//final JButton cancelB=new JButton("Cancel");
			c.add(cbl1,gbc);
			gbc.gridx=1; c.add(level1List,gbc);
			gbc.gridx=2; c.add(tl1,gbc);
			gbc.gridx=3; c.add(tlN1,gbc);
			gbc.gridx=0;gbc.gridy=1;
			c.add(cbl2,gbc); gbc.gridx=1; c.add(level2List,gbc);
			gbc.gridx=2; c.add(tl2,gbc);
			gbc.gridx=3; c.add(tlN2,gbc);
			gbc.gridx=0;gbc.gridy=2;
			c.add(cbl3,gbc); gbc.gridx=1; c.add(level3List,gbc);
			gbc.gridx=2; c.add(tl3,gbc);
			gbc.gridx=3; c.add(tlN3,gbc);
			gbc.gridx=0;gbc.gridy=3;
			c.add(cbl4,gbc); gbc.gridx=1; c.add(level4List,gbc);
			gbc.gridx=2; c.add(tl4,gbc);
			gbc.gridx=3; c.add(tlN4,gbc);
			//gbc.gridwidth=4;
			gbc.anchor=GridBagConstraints.CENTER;
			gbc.fill=GridBagConstraints.HORIZONTAL;
			gbc.gridx=0;gbc.gridy=4;
			c.add(okB,gbc); gbc.gridx=1; c.add(resB,gbc);
			defLevelWindow.setLocation(100, 100);
			//defLevelWindow.setSize(50,50);
			defLevelWindow.pack();
			defLevelWindow.show();
			
			 addChild(defLevelWindow,100,150,550,300);
			 defLevelWindow.setVisible(true);
			 defLevelWindow.moveToFront();
			 
			
	 }
	 
	 public void showRulesFile(){
		 Map<String,ArrayList<TranslitVariant>> chainFidal=new HashMap<String, ArrayList<TranslitVariant>>();
		 Map<String,ArrayList<TranslitVariant>> fidalTrVar=new HashMap<String, ArrayList<TranslitVariant>>();
		 for (String key : index.keySet()) {
			 String initialF=key.substring(0,key.indexOf('*'));
			    String tokenis=key.substring(key.indexOf('*')+1);
			    String translit=tokenis.replaceAll("-", "");
			    if (fidalTrVar.containsKey(initialF)) {
			    	if(fidalTrVar.get(initialF).size()==0) {
			    		
			    		ArrayList<TokenisationVariant> temp =new ArrayList<TokenisationVariant>();
			    		ArrayList <String>  annVar=new ArrayList<String>();
			    		 for(int i=0;i<index.get(key).size();i++){
			    		    WortNode w=words.get(indexIdWord.get(index.get(key).get(i)).intValue());
			    			//System.out.println("No token "+w.getTokenIds().size());
	      			    	boolean isC=true;
	      			    	int j=0;
	      			    	while((j<w.getTokenIds().size())&&isC){
	      			    		if(tokens.get(indexIdToken.get(w.getTokenIds().get(j)).intValue()).getMorphoAnnotation()!=null) 
	      			    			if(tokens.get(indexIdToken.get(w.getTokenIds().get(j)).intValue()).getMorphoAnnotation().isComplete()) j=j+1;
	      			    			else isC=false;
	      			    		else isC=false; 
	      			    	}
	      			    	//System.out.println (w.getTranslitLabel(typScript)+ " "+isC);
	      			    	if(isC){
	      			    		String sumAnn="";
	      			    		//System.out.println (w.getTranslitLabel(typScript));
	      			    		for(int k=0;k<w.getTokenIds().size();k++){
	      			    			AnnotationVariant ann; ann=new AnnotationVariant();
	      			    			ann.encodeAnnotationVariant(tokens.get(indexIdToken.get(w.getTokenIds().get(k)).intValue()).getMorphoAnnotation().getListTag().get(0));
	      			    			if(k==0) sumAnn=ann.getAnnotationVariant();
	      			    			else sumAnn=sumAnn+"|"+ann.getAnnotationVariant();
	      			    		}
	      			       
	      			    	boolean foundAnn=false;
	      			    	j=0;
	      			    	while((j<annVar.size())&& (!foundAnn))
	      			    			if(annVar.get(j).equals(sumAnn))foundAnn=true;
	      			    			else j=j+1;
	      			    	if((j==0)||(!foundAnn)) annVar.add(sumAnn);
	      			    	//System.out.println("SumAnn "+sumAnn);
	      			    	}
	      			    	 
			    		 }
			    		TokenisationVariant tok=new TokenisationVariant(tokenis,annVar);
			    		temp.add(tok);
			    		TranslitVariant t =new TranslitVariant(translit,temp);
			    		fidalTrVar.get(initialF).add(t);
			    	}
			    	else{
			    		boolean found=false; int k=0;
			    		while((k<fidalTrVar.get(initialF).size())&&(!found)) 
			    				if (fidalTrVar.get(initialF).get(k).getTranslitVariant().equals(translit)){
			    					boolean found1=false; int k1=0;
			    					while((k1<fidalTrVar.get(initialF).get(k).getTokenisVariants().size())&&(!found1)){
			    						if (fidalTrVar.get(initialF).get(k).getTokenisVariants().get(k1).getTokenisationVariant().equals(tokenis)){
			    							found=true;found1=true;
			    						}
			    					
			    					else {
			    						ArrayList <String>  annVar=new ArrayList<String>();
			    						 for(int i=0;i<index.get(key).size();i++){
			    				    		    WortNode w=words.get(indexIdWord.get(index.get(key).get(i)).intValue());
			    				    			//System.out.println("No token "+w.getTokenIds().size());
			    		      			    	boolean isC=true;
			    		      			    	int j=0;
			    		      			    	while((j<w.getTokenIds().size())&&isC){
			    		      			    		if(tokens.get(indexIdToken.get(w.getTokenIds().get(j)).intValue()).getMorphoAnnotation()!=null) 
			    		      			    			if(tokens.get(indexIdToken.get(w.getTokenIds().get(j)).intValue()).getMorphoAnnotation().isComplete()) j=j+1;
			    		      			    			else isC=false;
			    		      			    		else isC=false; 
			    		      			    	}
			    		      			    	//System.out.println (w.getTranslitLabel(typScript)+ " "+isC);
			    		      			    	if(isC){
			    		      			    		String sumAnn="";
			    		      			    		
			    		      			    		for(int k2=0;k2<w.getTokenIds().size();k2++){
			    		      			    			AnnotationVariant ann; ann=new AnnotationVariant();
			    		      			    			ann.encodeAnnotationVariant(tokens.get(indexIdToken.get(w.getTokenIds().get(k2)).intValue()).getMorphoAnnotation().getListTag().get(0));
			    		      			    			if(k2==0) sumAnn=ann.getAnnotationVariant();
			    		      			    			else sumAnn=sumAnn+"|"+ann.getAnnotationVariant();
			    		      			    		}
			    		      			       
			    		      			    	boolean foundAnn=false;
			    		      			    	j=0;
			    		      			    	while((j<annVar.size())&& (!foundAnn))
			    		      			    			if(annVar.get(j).equals(sumAnn))foundAnn=true;
			    		      			    			else j=j+1;
			    		      			    	if((j==0)||(!foundAnn)) annVar.add(sumAnn);
			    		      			    	//System.out.println("SumAnn "+sumAnn);
			    		      			    	}
			    				    		 }
			    						TokenisationVariant tok=new TokenisationVariant(tokenis,annVar);
			    						fidalTrVar.get(initialF).get(k).getTokenisVariants().add(tok);k1=k1+1;
			    					}
			    					}	
			    				}
			    				else k=k+1;
			    		if(!found){
			    			ArrayList <String>  annVar=new ArrayList<String>();
							 for(int i=0;i<index.get(key).size();i++){
					    		    WortNode w=words.get(indexIdWord.get(index.get(key).get(i)).intValue());
					    			//System.out.println("No token "+w.getTokenIds().size());
			      			    	boolean isC=true;
			      			    	int j=0;
			      			    	while((j<w.getTokenIds().size())&&isC){
			      			    		if(tokens.get(indexIdToken.get(w.getTokenIds().get(j)).intValue()).getMorphoAnnotation()!=null) 
			      			    			if(tokens.get(indexIdToken.get(w.getTokenIds().get(j)).intValue()).getMorphoAnnotation().isComplete()) j=j+1;
			      			    			else isC=false;
			      			    		else isC=false; 
			      			    	}
			      			    	//System.out.println (w.getTranslitLabel(typScript)+ " "+isC);
			      			    	if(isC){
			      			    		String sumAnn="";
			      			    		
			      			    		for(int k2=0;k2<w.getTokenIds().size();k2++){
			      			    			AnnotationVariant ann; ann=new AnnotationVariant();
			      			    			ann.encodeAnnotationVariant(tokens.get(indexIdToken.get(w.getTokenIds().get(k2)).intValue()).getMorphoAnnotation().getListTag().get(0));
			      			    			if(k2==0) sumAnn=ann.getAnnotationVariant();
			      			    			else sumAnn=sumAnn+"|"+ann.getAnnotationVariant();
			      			    		}
			      			       
			      			    	boolean foundAnn=false;
			      			    	j=0;
			      			    	while((j<annVar.size())&& (!foundAnn))
			      			    			if(annVar.get(j).equals(sumAnn))foundAnn=true;
			      			    			else j=j+1;
			      			    	if((j==0)||(!foundAnn)) annVar.add(sumAnn);
			      			    	//System.out.println("SumAnn "+sumAnn);
			      			    	}
					    		 }
			    			ArrayList<TokenisationVariant> temp =new ArrayList<TokenisationVariant>();
				    		TokenisationVariant tok=new TokenisationVariant(tokenis,annVar);
				    		temp.add(tok);
				    		TranslitVariant t =new TranslitVariant(translit,temp);
				    		fidalTrVar.get(initialF).add(t);
			    		}
			    	}
			    }
			    else {
			    	ArrayList <String>  annVar=new ArrayList<String>();
					 for(int i=0;i<index.get(key).size();i++){
			    		    WortNode w=words.get(indexIdWord.get(index.get(key).get(i)).intValue());
			    			//System.out.println("No token "+w.getTokenIds().size());
	      			    	boolean isC=true;
	      			    	int j=0;
	      			    	while((j<w.getTokenIds().size())&&isC){
	      			    		if(tokens.get(indexIdToken.get(w.getTokenIds().get(j)).intValue()).getMorphoAnnotation()!=null) 
	      			    			if(tokens.get(indexIdToken.get(w.getTokenIds().get(j)).intValue()).getMorphoAnnotation().isComplete()) j=j+1;
	      			    			else isC=false;
	      			    		else isC=false; 
	      			    	}
	      			    	//System.out.println (w.getTranslitLabel(typScript)+ " "+isC);
	      			    	if(isC){
	      			    		String sumAnn="";
	      			    		
	      			    		for(int k2=0;k2<w.getTokenIds().size();k2++){
	      			    			AnnotationVariant ann; ann=new AnnotationVariant();
	      			    			ann.encodeAnnotationVariant(tokens.get(indexIdToken.get(w.getTokenIds().get(k2)).intValue()).getMorphoAnnotation().getListTag().get(0));
	      			    			if(k2==0) sumAnn=ann.getAnnotationVariant();
	      			    			else sumAnn=sumAnn+"|"+ann.getAnnotationVariant();
	      			    		}
	      			       
	      			    	boolean foundAnn=false;
	      			    	j=0;
	      			    	while((j<annVar.size())&& (!foundAnn))
	      			    			if(annVar.get(j).equals(sumAnn))foundAnn=true;
	      			    			else j=j+1;
	      			    	if((j==0)||(!foundAnn)) annVar.add(sumAnn);
	      			    	//System.out.println("SumAnn "+sumAnn);
	      			    	}
			    		 }
			    	
			    	ArrayList<TokenisationVariant> temp =new ArrayList<TokenisationVariant>();
		    		TokenisationVariant tok=new TokenisationVariant(tokenis,annVar);
		    		temp.add(tok);
		    		TranslitVariant t =new TranslitVariant(translit,temp);
			    	ArrayList<TranslitVariant> temp1=new ArrayList<TranslitVariant>();
			    	temp1.add(t);
			    	fidalTrVar.put(initialF, temp1);
			    }
		 }
		 
		 for (String key:fidalTrVar.keySet()){
			 //System.out.println(key);
			 for (int kk=0;kk<fidalTrVar.get(key).size();kk++){
				// System.out.println("\t"+fidalTrVar.get(key).get(kk).getTranslitVariant());
				 for (int kk1=0;kk1<fidalTrVar.get(key).get(kk).getTokenisVariants().size();kk1++){
					// System.out.println("\t\t"+fidalTrVar.get(key).get(kk).getTokenisVariants().get(kk1).getTokenisationVariant());
					 if(fidalTrVar.get(key).get(kk).getTokenisVariants().get(kk1).getTokenVariantAnnot()!=null){
					 for (int kk2=0;kk2<fidalTrVar.get(key).get(kk).getTokenisVariants().get(kk1).getTokenVariantAnnot().size();kk2++){
						// System.out.println("\t\t\t"+fidalTrVar.get(key).get(kk).getTokenisVariants().get(kk1).getTokenVariantAnnot().get(kk2));
					 }
					 }
				 }
			 }
		 }
		 //System.out.println("Finish");
		

		 String nameFile=FileOpenedName;
		  try{
			   
			    Writer out1 = new BufferedWriter(new OutputStreamWriter(
			    			
			    new FileOutputStream(nameFile.substring(0, nameFile.indexOf("json"))+"txt"), "UTF-8"));
			     
			    	try {
		 
		 for (String key : fidalTrVar.keySet()){
		
			 out1.write(key+"\n");
			 for(int i=0;i<fidalTrVar.get(key).size();i++){
				out1.write("\t"+fidalTrVar.get(key).get(i).getTranslitVariant()+"\n");
				 for(int k=0;k<fidalTrVar.get(key).get(i).getTokenisVariants().size();k++){
					 out1.write("\t\t"+fidalTrVar.get(key).get(i).getTokenisVariants().get(k).getTokenisationVariant()+"\n");
					 for(int j=0;j<fidalTrVar.get(key).get(i).getTokenisVariants().get(k).getTokenVariantAnnot().size();j++)
						 out1.write("\t\t\t"+fidalTrVar.get(key).get(i).getTokenisVariants().get(k).getTokenVariantAnnot().get(j)+"\n");
						 
				 }
			 }
		 }
			out1.close();    	}catch(Exception e){}
		  }catch(Exception ex){};	  	
		 
	 }
	 
	 public void verifyNouns(){
		 for(int i=0;i<tokens.size();i++){
			 if (tokens.get(i).getMorphoAnnotation()!=null){
				 if(tokens.get(i).getMorphoAnnotation().getSpecificTag("Common Noun")!=null || tokens.get(i).getMorphoAnnotation().getSpecificTag("Proper Name")!=null){
					 if (tokens.get(i).getMorphoAnnotation().getMorphoValue("number").indexOf("Singular")>=0)
							 tokens.get(i).getMorphoAnnotation().setMorphoValue("number", "SingularP");
					 else if (tokens.get(i).getMorphoAnnotation().getMorphoValue("number").indexOf("PluralE")>=0)
						 tokens.get(i).getMorphoAnnotation().setMorphoValue("number", "PluralExtP");
					 else if (tokens.get(i).getMorphoAnnotation().getMorphoValue("number").indexOf("PluralI")>=0)
						 tokens.get(i).getMorphoAnnotation().setMorphoValue("number", "PluralIntP");
					 else if (tokens.get(i).getMorphoAnnotation().getMorphoValue("number").indexOf("PluralPL")>=0)
						 tokens.get(i).getMorphoAnnotation().setMorphoValue("number", "PluralPl");
				if(tokens.get(i).getMorphoAnnotation().isComplete()){
					tokens.get(i).getMorphoAnnotation().setComplete(false);
					tokens.get(i).getMorphoAnnotation().setInProgress(true);
				 }
			 }
				 else if(tokens.get(i).getMorphoAnnotation().getSpecificTag("Preposition")!=null){
					 if (tokens.get(i).getMorphoAnnotation().getMorphoValue("gender")!=null)
						 tokens.get(i).getMorphoAnnotation().setMorphoNewName("gender", "state");
				 }
		 }
	 }
		 verifyNouns.setEnabled(false);checkedN=true;translitWordList.revalidate();translitWordList.repaint();
		
	 }
	 //
	 public void correctInscriptions() {
		 final JLabel modiflb=new JLabel ("Increase/Decrease line break numbers with");
		 final JComboBox plusminus=new JComboBox();
		 plusminus.addItem("+");
		 plusminus.addItem("-");
		 final JTextField newlb=new JTextField(4);
		 final JButton okB=new JButton("OK");
		 final ChildFrame corrI=new ChildFrame("Correct LB numbers",Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
			Container c=corrI.getContentPane();
			c.setLayout(new GridBagLayout());
			GridBagConstraints gbc1=new GridBagConstraints();
			gbc1.gridx=0;
			gbc1.gridy=0;
			gbc1.insets.left=2;
			gbc1.insets.top=2;
			gbc1.insets.bottom=2;
			gbc1.insets.right=2;
			gbc1.weighty=100.0;
			gbc1.weightx=100.0;
			gbc1.fill=GridBagConstraints.HORIZONTAL;
			gbc1.anchor=GridBagConstraints.NORTHWEST;
			c.add(modiflb,gbc1);
			gbc1.gridx=1;
			c.add(plusminus,gbc1);
			gbc1.gridx=2;
			c.add(newlb,gbc1);
			gbc1.fill=GridBagConstraints.NONE;
			gbc1.anchor=GridBagConstraints.CENTER;
			gbc1.gridwidth=3;
			gbc1.gridx=0; gbc1.gridy=1;
			c.add(okB,gbc1);
			okB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 int n=Integer.parseInt(newlb.getText());
					 if(plusminus.getSelectedIndex()==0)
						 startLBnr=startLBnr+n;
					 else startLBnr=startLBnr-n;
							 
					 for (int i=0;i<words.size();i++) {
						 for(int j=0;j<words.get(i).getFidalChildren().size();j++){
				                  if(words.get(i).getFidalChildren().get(j).getEdAnnot()!=null)
				                	  if(words.get(i).getFidalChildren().get(j).getEdAnnot().getSpecificTag("lb")!=null)
				                		  if(plusminus.getSelectedIndex()==0)
				                		  words.get(i).getFidalChildren().get(j).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).setValue( ""+(Integer.parseInt(words.get(i).getFidalChildren().get(j).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).getValue())+n));
				                		  else
				                			  words.get(i).getFidalChildren().get(j).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).setValue( ""+(Integer.parseInt(words.get(i).getFidalChildren().get(j).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).getValue())-n));
			}
					 }
					 corrI.doDefaultCloseAction();
				}
			});
			corrI.setLocation(100, 100);
			corrI.setSize(50,50);
			corrI.pack();
			corrI.show();
			 addChild(corrI,500,450,400,100);
		        corrI.setVisible(true);
			corrI.moveToFront();
	 }
	 // correct names in features
	 public void correctMorpho(){
		 try{
			   
			    Writer out1 = new BufferedWriter(new OutputStreamWriter(
			    			
			    new FileOutputStream("Error.log"), "UTF-8"));
		 for(int i=0;i<tokens.size();i++){
			 if (tokens.get(i).getMorphoAnnotation()!=null){
				 if(tokens.get(i).getMorphoAnnotation().getSpecificTag("Preposition")!=null){
					 if (!tokens.get(i).getMorphoAnnotation().getMorphoValue("gender").isEmpty()){
						   // Attribut a = new Attribut("state",tokens.get(i).getMorphoAnnotation().getMorphoValue("gender"));
							 tokens.get(i).getMorphoAnnotation().setMorphoNewName("gender", "state");
					        // tokens.get(i).getMorphoAnnotation().getSpecificTag("Preposition").removeAttribute("gender");;
					 }
				 } 
					 if (!tokens.get(i).getMorphoAnnotation().getMorphoValue("status").isEmpty()){
						   // Attribut a = new Attribut("state",tokens.get(i).getMorphoAnnotation().getMorphoValue("gender"));
							 tokens.get(i).getMorphoAnnotation().setMorphoNewName("status", "state");
					        // tokens.get(i).getMorphoAnnotation().getSpecificTag("Preposition").removeAttribute("gender");;
					 }
					 
					 if(tokens.get(i).getMorphoAnnotation().getSpecificTag("Pronominal Suffixes")!=null){
						 tokens.get(i).getMorphoAnnotation().setSpecificTag("Pronominal Suffixes", "Pronominal Suffix");
						 
							   
							    	try {
							    		//System.out.println("write to file");
							    		String s="";
							    		
							    		
							    		out1.write(tokens.get(i).getLabel());out1.write("***\n");
							    	}catch(Exception e) {}
						 	
						 
						 
						 
					 }
			 
				 if(tokens.get(i).getMorphoAnnotation().getSpecificTag("Verb")!=null){
					 if (!tokens.get(i).getMorphoAnnotation().getMorphoValue("tamp").isEmpty()){
							// tokens.get(i).getMorphoAnnotation().setMorphoValue("tam", tokens.get(i).getMorphoAnnotation().getMorphoValue("tamp"));
					        // tokens.get(i).getMorphoAnnotation().getSpecificTag("Verb").removeAttribute("tamp");;
					         tokens.get(i).getMorphoAnnotation().setMorphoNewName("tamp", "tam");
					     //    System.out.println("Replaced tamp tam");
					 }
					 
						 tokens.get(i).getMorphoAnnotation().findDoubleTagName("case", "state","state");
					 
					
			 }
				 
				 tokens.get(i).getMorphoAnnotation().findDoubleTags();	
		 }
	 }
		// verifyNouns.setEnabled(false);checkedN=true;translitWordList.revalidate();translitWordList.repaint();
	 out1.close();
		 }catch(Exception e) {}
	 }
	 
	 //verify index
	 public void showProgress(){
		 int totalWords=words.size();
		 int totalTokens=tokens.size();
		 int completeAnno=0;
		 int manualA=0;
		 int AutomA=0;
		 int manualAComplete=0;
		 int automaticAComplete=0;
		 int wordComplete=0;
		 int tokenA=0;
		 for(int i=0;i<tokens.size();i++){
			 if(tokens.get(i).getMorphoAnnotation() !=null){
				 tokenA=tokenA+1;
				 if(tokens.get(i).getMorphoAnnotation().isComplete()) completeAnno=completeAnno+1;
			     if(tokens.get(i).getMorphoAnnotation().isAutom()&& tokens.get(i).getMorphoAnnotation().isComplete()) automaticAComplete=automaticAComplete+1;
			     if(tokens.get(i).getMorphoAnnotation().isAutom()) AutomA=AutomA+1;	 
			 }   	 
			     
		 }
		 manualAComplete=completeAnno-automaticAComplete;
		 manualA=tokenA-AutomA;
		 DefaultPieDataset dataset = new DefaultPieDataset();
		 dataset.setValue("Tokens not annotated", totalTokens-tokenA);
		 dataset.setValue("Manual Complete", manualAComplete);
		 dataset.setValue("Manual Incomplete", manualA-manualAComplete);
		 dataset.setValue("Automatic Complete", automaticAComplete);
		 dataset.setValue("Automatic Incomplete", AutomA-automaticAComplete);
		 JFreeChart chart = ChartFactory.createPieChart(
				 "Progress Annotation Tokens",
				 dataset,
				 true, // legend?
				 true, // tooltips?
				 false // URLs?
				 );
		 PiePlot plot = (PiePlot) chart.getPlot();
	
		 plot.setSectionPaint("Manual Complete", Color.BLUE);
		 plot.setSectionPaint("Automatic Complete", Color.CYAN);
		 plot.setSectionPaint("Automatic Incomplete", Color.RED);
		 plot.setSectionPaint("Manual Incomplete", Color.BLACK);
		 plot.setSectionPaint("Tokens not annotated", Color.GRAY);
		 plot.setExplodePercent("Manual Complete", 0.15);
		 plot.setExplodePercent("Manual Incomplete", 0.15);
		 plot.setExplodePercent("Automatic Complete", 0.15);
		 plot.setExplodePercent("Automatic Incomplete", 0.15);
		 ChartFrame frame = new ChartFrame("Token Statistics", chart);
		 frame.pack();
		 frame.setVisible(true);
		 int noAutom=0;
		 int notokenis=0;
		 int nounitComplete=0;
		 int nounitComplete1=0;
		 for (int i=0; i<words.size();i++){
			 ArrayList<String> tokid=words.get(i).getTokenIds();
			 if (tokid.size()>1) notokenis=notokenis+1;
			 boolean complunit=true;
			 for(int j=0;j<tokid.size();j++)
                        if(tokens.get(indexIdToken.get(tokid.get(j)).intValue()).getMorphoAnnotation()!=null)
                        	complunit=complunit && tokens.get(indexIdToken.get(tokid.get(j)).intValue()).getMorphoAnnotation().isComplete();
                        else complunit=false;
			 if(complunit) {
				 nounitComplete=nounitComplete+1;
				 if(!words.get(i).getTranslitLabel(typScript).equals(".")) nounitComplete1=nounitComplete1+1;
			 }
		 }
		 dataset = new DefaultPieDataset();
		 dataset.setValue("Word Units at least 2 Tokens",notokenis);
		 dataset.setValue("Word Units 1 Token or not tokenised",totalWords-notokenis);
		 
		 JFreeChart chart1 = ChartFactory.createPieChart(
				 "Progress Tokensation",
				 dataset,
				 true, // legend?
				 true, // tooltips?
				 false // URLs?
				 );
		 PiePlot plot1 = (PiePlot) chart1.getPlot();
			
		 plot1.setSectionPaint("Word Units at least 2 Tokens", Color.DARK_GRAY);
		 plot1.setSectionPaint("Word Units 1 Token or not tokenised", Color.LIGHT_GRAY);
		
		 ChartFrame frame1 = new ChartFrame("Graphic units", chart1);
		 frame1.pack();
		 frame1.setVisible(true);
		 frame1.setLocation((int)frame.getLocationOnScreen().getX()+10,(int)frame.getLocationOnScreen().getY()+10);
		 
		 
		 dataset = new DefaultPieDataset();
		 dataset.setValue("Graphic Units not finished",totalWords-nounitComplete);
		 dataset.setValue("Graphic Units entirely linguistically annotated - Punctuation",nounitComplete-nounitComplete1);
		 dataset.setValue("Graphic Units entirely linguistically annotated",nounitComplete1);
		
		 
		 JFreeChart chart2 = ChartFactory.createPieChart(
				 "Completed ",
				 dataset,
				 true, // legend?
				 true, // tooltips?
				 false // URLs?
				 );
		 PiePlot plot2 = (PiePlot) chart2.getPlot();
			
		 plot2.setSectionPaint("Word Units entirely linguistically annotated - Punctuation", Color.CYAN);
		 plot2.setSectionPaint("Word Units entirely linguistically annotated", Color.BLUE);
		 plot2.setSectionPaint("Word Units not finished", Color.LIGHT_GRAY);
		 plot2.setExplodePercent("Word Units entirely linguistically annotated", 0.30);
		
		 ChartFrame frame2 = new ChartFrame("Linguistic Annotation", chart2);
		 frame2.pack();
		 frame2.setVisible(true);
		 frame2.setLocation((int)frame1.getLocationOnScreen().getX()+10,(int)frame1.getLocationOnScreen().getY()+10);
	 }
	 
	 public void verifyIndex(){
		 int confirm= JOptionPane.showOptionDialog(desk,
	                "You are going now to regenerate the index. This will take  time and you cannot interrupt the process. Are you sure?",
	                "Reindexing Confirmation", JOptionPane.YES_NO_OPTION,
	                JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (confirm == JOptionPane.YES_OPTION) {
				final ChildFrame statusIndex=new ChildFrame("Reading",Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
				Container c=statusIndex.getContentPane();
				final JTextArea progressEntry=new JTextArea();
				progressEntry.setFont(etiopicText);
				//progress="Reading...\n";
				progressEntry.setText("Indexing...\n");
				JScrollPane jp=new JScrollPane(progressEntry);
				c.add(jp);
				statusIndex.setLocation(100, 100);
				statusIndex.setSize(50,50);
				statusIndex.pack();
				statusIndex.show();
				 addChild(statusIndex,500,450,300,100);
			        statusIndex.setVisible(true);
				statusIndex.moveToFront();
				 errorPane.setText("");
				new Thread() {
			        public void run() {
				       progressEntry.setText("Start indexing...\n");
				       createIndex1(progressEntry);
				       translitWordList.revalidate();fidalWordList.revalidate();
					      translitWordList.repaint();fidalWordList.repaint();
						 errorPane.setText(updateErrors());
					      errorWarning.revalidate();errorWarning.repaint();
				       statusIndex.doDefaultCloseAction();
				}
			}.start();
			errorPane.setCaretPosition(0);
		       
		      
			}
			//else if (confirm == JOptionPane.NO_OPTION)System.exit(0);
		
	 }
	 //close File
	 
	 public void closeFile(){
			if (isSaved){
				showPosition.dispose();
				showPositionPageBreak.dispose();
				showAnnotationX=showAnnotation.getX();
				showAnnotationY=showAnnotation.getY();
				showAnnotation.dispose();
				fidalText.dispose(); translitText.dispose(); 
				modelOrig.clear();modelTranslit.clear();
				errorWarning.dispose();Fehlern="";
				words.clear();
				wordsCopy.clear();
				tokens.clear();index.clear();
				divisions.clear();
				specDivisions.clear();
				indexT.clear();
				indexIdWord.clear();
				indexIdToken.clear();
				indexIdDivision.clear();
				indexIdSpecDivision.clear();
				nelist.clear();
				metadataDoc=null;
				inLingannot.clear();
				wselEdit.clear();
				tselEdit.clear();
				wselEdit1.clear();
				tselEdit1.clear();
				attr.clear();
				last_SIGN=0;
				typDoc=0;
				typScript=0;
				noStruct=0;
				hasStructSpec=false;
				nrLB=0;
				nrPB=0;
				startGlobalNE=0;endGlobalNE=0;
				close=false;
				activateMenus(0);
			}
			else{
				typDoc=0;
				typScript=0;
		 			int confirm= JOptionPane.showOptionDialog(desk,
		 	                "Last changes are not saved. Save and Close? ",
		 	                "Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION,
		 	                JOptionPane.QUESTION_MESSAGE, null, null, null);
		 			if (confirm == JOptionPane.YES_OPTION) {
		 				if (saveFile.isEnabled()) saveToFile();
		 				else saveAsToFile();
		 				showPosition.dispose();
		 				showPositionPageBreak.dispose();
		 				showAnnotationX=showAnnotation.getX();
						showAnnotationY=showAnnotation.getY();
		 				showAnnotation.dispose();
						fidalText.dispose(); translitText.dispose();
						errorWarning.dispose();Fehlern="";
						modelOrig.clear();modelTranslit.clear();
						words.clear();
						wordsCopy.clear();
						tokens.clear();index.clear();divisions.clear();
						indexT.clear();
						indexIdWord.clear();
						indexIdToken.clear();
						inLingannot.clear();
						wselEdit.clear();
						tselEdit.clear();
						wselEdit1.clear();
						tselEdit1.clear();
						attr.clear();
						typDoc=0;
						typScript=0;
						close=false;
						activateMenus(0);
		 			}
		 			else if (confirm == JOptionPane.NO_OPTION){
		 				showPosition.dispose();
		 				showPositionPageBreak.dispose();
		 				showAnnotationX=showAnnotation.getX();
						showAnnotationY=showAnnotation.getY();
		 				showAnnotation.dispose();
						fidalText.dispose(); translitText.dispose();
						translitWordList.addListSelectionListener(null);
						modelOrig.clear();modelTranslit.clear();
						errorWarning.dispose();Fehlern="";
						words.clear();
						wordsCopy.clear();
						tokens.clear();divisions.clear();
						index.clear();
						indexT.clear();
						indexIdWord.clear();
						indexIdToken.clear();indexIdDivision.clear();
						inLingannot.clear();
						wselEdit.clear();
						tselEdit.clear();
						wselEdit1.clear();
						tselEdit1.clear();
						typDoc=0;
						typScript=0;
						attr.clear();
						close=false;
						activateMenus(0);
		 			}
		 		}
			
		}
	 
	 //
	 public void exportTEILing(){
			String nameFile=FileOpenedName;
			   try{
				   
				    Writer out1 = new BufferedWriter(new OutputStreamWriter(
				    			
				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"TEILing.xml"), "UTF-8"));
				   
				    	try {
				    		//System.out.println("write to file");
				    	
				    		String s="";
				    		
				    		out1.write("<TEI xmlns=\"https://www.tei-c.org/ns/1.0\">");
				    		out1.write("<teiHeader><fileDesc><titleStmt><title></title></titleStmt><publicationStmt><ab></ab></publicationStmt><sourceDesc><ab></ab></sourceDesc></fileDesc></teiHeader><text><body>");;
						
				    		for(int i=0;i<words.size();i++){
				    			s="";
				    			if(words.get(i).getStrukturIds().size()>0) {
				    				Division d=divisions.get(indexIdDivision.get(words.get(i).getStrukturIds().get(0)).intValue());
				    				if(d.getWbegin().equals(words.get(i).getId())) {
				    		  	             s="<div type=\""+d.getName() +"\" n=\""+d.getNr()+"\" resp=\""+d.getCreator()+"\" style=\""+d.getGenre()+"\" xml:id=\""+"DIV"+d.getId()+"\">";
				    				s=s+"<p>";
				    				}
				    			}
				    			s=s+"<fs type=\"graphunit\" xml:id=\""+words.get(i).getId()+"\"><f name=\"fidäl\">"+words.get(i).getFidalLabel(typScript, typDoc)+"</f>"+
							    		  "<f name=\"translit\">"+words.get(i).getTranslitLabel(typScript)+"</f>";
				    			
							      s=s+"<f name=\"analysis\"><fs type=\"tokens\">";
							      for(int j=0;j<words.get(i).getTokenIds().size();j++) {
							    	  Token t=tokens.get(indexIdToken.get(words.get(i).getTokenIds().get(j)).intValue());
							    	  s=s+"<f name=\"lit\" fVal=\""+t.getLabel()+"\"";
							    	  if (!t.getNERef().isEmpty()) {
							    		  String ner=t.getNERef();
							    		  int l=0; boolean foundn=false;
							    		  while((l<nelist.size())&&(!foundn)) {
							    			  if (nelist.get(l).getId().equals(ner)) {
							    				  ner=nelist.get(l).getrefId(); foundn=true;
							    			  }
							    			  else l++;
							    		  }
							    		  if (foundn)
							    		     s=s+" corresp=\"#"+ner+"\"";
							    	  }
							    	  s=s+">";
							    	  if (t.getMorphoAnnotation()!=null) {
							    		  s=s+"<fs type=\"morpho\">";
							    		
							    		  s=s+"<f name=\"pos\">"+t.getMorphoAnnotation().getListTag().get(0).getName()+"</f>";
							    		  if (t.getMorphoAnnotation().getListTag().get(0).getAttrList()!=null) {
							    		  for (int k=0;k<t.getMorphoAnnotation().getListTag().get(0).getAttrList().size();k++)
							    			  if(!t.getMorphoAnnotation().getListTag().get(0).getAttrList().get(k).getName().equalsIgnoreCase("Comment"))
							    			  s=s+"<f name=\""+t.getMorphoAnnotation().getListTag().get(0).getAttrList().get(k).getName()+"\">"+t.getMorphoAnnotation().getListTag().get(0).getAttrList().get(k).getValue()+"</f>";
							    		  }
							    		  s=s+"</fs>";
							    	  }
							    	//  s=s+"</fs>";
								      s=s+"</f>";
							      }
							      s=s+"</fs>";
							      s=s+"</f>";
							      s=s+"</fs>";
							      if(words.get(i).getStrukturIds().size()>0) {
					    				Division d=divisions.get(indexIdDivision.get(words.get(i).getStrukturIds().get(0)).intValue());
					    				if(d.getWend().equals(words.get(i).getId())) {
					    		  	             s=s+"</p></div>";
					    				}
					    			}
							      out1.write(s);
							}
				    		out1.write("</body></text></TEI>");
				    		//out1.write("*******");
				   
				    	
				    	} finally {
				    		out1.close();
				    	
				    	}
				    }catch (Exception e) {}
			
			
		}
	 //
	 
	 public void exportTEIDivs(){
			String nameFile=FileOpenedName;
			   try{
				   
				    Writer out1 = new BufferedWriter(new OutputStreamWriter(
				    			
				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"TEIDiv.xml"), "UTF-8"));
				   
				    	try {
				    		//System.out.println("write to file");
				    		String s="";
				    		
				    		
				    		out1.write("<TEI xmlns=\"https://www.tei-c.org/ns/1.0\">\n");
				    		out1.write("<doc xmlns:xsi=\"https://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"wordsId.xsd\">\n");;
							for(int i=0;i<words.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
								if (words.get(i).getStrukturIds().size()>0) {
								ArrayList<Division> d=new ArrayList<Division>();
								String ss="";
								for(int j=0;j<divisions.size();j++) {
									if (divisions.get(j).getWend().equals(words.get(i).getId())) {
										if( j==0) ss=ss+"</p>";
										ss=ss+"</div>";
									}
									else if (divisions.get(j).getWbegin().equals(words.get(i).getId())) d.add(divisions.get(j));
								}
								if (d.size()>1) {
									for(int k=0;k<d.size();k++) {
										ss=ss+"<div type=\""+d.get(k).getName() +"\" n=\""+d.get(k).getNr()+"\" sameAs=\""+d.get(k).getInternalNr()+"\" resp=\""+d.get(k).getCreator()+"\" style=\""+d.get(k).getGenre()+"\" xml:id=\""+d.get(k).getId()+"\">\n";
									}
									ss=ss+"<p>";	
								}
								else if(d.size()==1) {
									ss="<div type=\""+d.get(0).getName() +"\" n=\""+d.get(0).getNr()+"\" sameAs=\""+d.get(0).getInternalNr()+"\" resp=\""+d.get(0).getCreator()+"\" style=\""+d.get(0).getGenre()+"\" xml:id=\""+d.get(0).getId()+"\">\n";
									ss=ss+"<p>";
								}
								ss=ss+words.get(i).getTranslitLabel(typScript)+" ";
								out1.write(ss);
								}
								else out1.write(words.get(i).getTranslitLabel(typScript)+" ");
							}
				    		out1.write("</TEI>");
				    		//out1.write("*******");
				   
				    	
				    	} finally {
				    		out1.close();
				    	
				    	}
				    }catch (Exception e) {}
			
			
		}
	 //
	 
	 public void exportWordsIds(){
			String nameFile=FileOpenedName;
			   try{
				   
				    Writer out1 = new BufferedWriter(new OutputStreamWriter(
				    			
				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"Word.xml"), "UTF-8"));
				    Writer out2 = new BufferedWriter(new OutputStreamWriter(
			    			
						    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"WordT.txt"), "UTF-8"));
                     Writer out3 = new BufferedWriter(new OutputStreamWriter(
			    			
						    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"WordF.txt"), "UTF-8"));
				   
				    	try {
				    		//System.out.println("write to file");
				    		String s="";
				    		
				    		
				    		out1.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
				    		out1.write("<doc xmlns:xsi=\"https://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"wordsId.xsd\">\n");;
							for(int i=0;i<words.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
								
					    		out1.write("<w tr=\""+words.get(i).getTranslitLabel(typScript)+"\" id=\""+words.get(i).getId()+"\"></w>\n");
					    		out2.write(words.get(i).getTranslitLabel(typScript)+" ");
					    		out3.write(words.get(i).getFidalLabel(typDoc,typScript)+" ");
					    	//	System.out.println(""+i);
							}
				    		out1.write("</doc>");
				    		//out1.write("*******");
				   
				    	
				    	} finally {
				    		out1.close();
				    		out2.close();
				    		out3.close();
				    	}
				    }catch (Exception e) {}
			
			
		}
	 
	 
	 //export ANNIS
	 
	 public void exportANNIS(){
			String nameFile=FileOpenedName;
			  correctMorpho();
			   try{
				   String homeDir=fileRoot.getParent();
				   String nameFileShort=fileRoot.getName();
				   String nameOhneExt= nameFileShort.substring(0,nameFileShort.indexOf(".json"));
				   DateFormat dateFormat = new SimpleDateFormat("yy_MM_dd_HH_mm_ss");
				
				   Date date = new Date();
				   
				   File directory = new File(homeDir,"Export"+nameOhneExt+"Ver"+dateFormat.format(date));
				 
				   directory.mkdir();
				   
				   File aux=new File(directory,nameOhneExt);
				   String nameFileShort1=aux.getAbsolutePath();
				  // System.out.println(nameFileShort1);
				   //nameFileShort.substring(0,nameFileShort.indexOf('.'))
				    Writer out1 = new BufferedWriter(new OutputStreamWriter(
				    			
				    new FileOutputStream( nameFileShort1+dateFormat.format(date)+"_EA.json"), "UTF-8"));
				     Writer out2 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFileShort1+dateFormat.format(date)+"_IWEA.ind"), "UTF-8"));
				     Writer out4 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFileShort1+dateFormat.format(date)+"_ITEA.ind"), "UTF-8"));
			    		
				     Writer out3 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFileShort1+dateFormat.format(date)+"_TEA.ann"), "UTF-8"));
				     Writer out5 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFileShort1+dateFormat.format(date)+"_DEA.ann"), "UTF-8"));
				     Writer out6 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFileShort1+dateFormat.format(date)+"_MetaEA.ann"), "UTF-8"));
				     Writer out7 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFileShort1+dateFormat.format(date)+"_NEA.ann"), "UTF-8"));
				    	try {
				    		//System.out.println("write to file");
				    		String s="";
				    		
				    		JSONObject o=new JSONObject();
				    		JSONObject o1=new JSONObject();
				    		JSONArray a =new JSONArray();
				    		
				    		o.put("TR", typDoc);
				    		o.put("SCR", typScript);
				    		
				    		//out1.write(o.toString());
				    		//out1.write("!!!!");
							for(int i=0;i<words.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
								a.put(words.get(i).toJson1(typScript,typDoc));
								//out1.write(words.get(i).toJson1(typScript,typDoc).toString());
								//if(i<words.size()-1)
					    		//out1.write("****");
							}
				    		o.put("FIDALWORDS", a);
			    		String outS=o.toString(); 
				    		//System.out.println(o.toString());
				    		//System.out.println(o.toString());
				    		out1.write(o.toString());
				    		//out1.write("*******");
				    		
				    		a=new JSONArray();
				    		for(int i=0;i<tokens.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
								a.put(tokens.get(i).toJson1());
								//out3.write(tokens.get(i).toJson1().toString());
								//if(i<tokens.size()-1)
					    		 //   out3.write("****");
							}
				    		
				    		out3.write(a.toString());
					    		
					    	
					    		a=new JSONArray();
				    		for(int i=0;i<divisions.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
							//	a.put(tokens.get(i).toJson());
							a.put(divisions.get(i).toJson());
								//if(i<divisions.size()-1)
					    		   // out5.write("****");
							}
				    		
				    		
					    		out5.write(a.toString());
				    		//NEs
					    		a=new JSONArray();
				    		for(int i=0;i<nelist.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
							//	a.put(tokens.get(i).toJson());
								a.put(nelist.get(i).toJson1());
								//if(i<nelist.size()-1)
					    		   // out6.write("****");
							}
				    		
				    		
				    		out7.write(a.toString());
				    		//o1.put("TOKENS", a);
				    		
				    	 if(metadataDoc !=null) {
				    		 metadataDoc.setDateExp(dateFormat.format(date));
				    		 metadataDoc.setTypeDoc(typDoc, typScript);
				    		 metadataDoc.setNoGrUnits(words.size());
				    		 metadataDoc.setNoTokens(tokens.size());
				    		 metadataDoc.setToolVer(versTool);
				    		for(int i=0;i<divisions.size();i++) {
				    			if(divisions.get(i).getParent().isEmpty())
				    				metadataDoc.getDivNames().add(divisions.get(i).getNr());				    			
				    		}
				    		out6.write(metadataDoc.toJSON1().toString());
				    	 }
				    		
				    		
		                	   FileOpenedName=nameFile; 
		                	   saveFile.setEnabled(true);
		                	   fidalText.setTitle(nameFile);
		                	   translitText.setTitle(nameFile);
		                	   
		                	   for (Map.Entry<String, ArrayList<String>> entry : index.entrySet()) {
		   	            	    String key = entry.getKey();
		   	            	    ArrayList<String> values = entry.getValue();
		   	            	    //System.out.println(values.size());
		   	            	    out2.write(key+"|");
		   	            	    for(int i=0;i<values.size();i++){
		   	            	    	//System.out.println(values.get(i));
		   	            	         if(i<values.size()-1) out2.write(values.get(i)+",");
		   	            	         else out2.write(values.get(i)+"\n");
		   	            	    } 
		   	            	}
		                	   for (Map.Entry<String, ArrayList<String>> entry : indexT.entrySet()) {
			   	            	    String key = entry.getKey();
			   	            	    ArrayList<String> values = entry.getValue();
			   	            	    //System.out.println(values.size());
			   	            	    out4.write(key+"|");
			   	            	    for(int i=0;i<values.size();i++){
			   	            	    	//System.out.println(values.get(i));
			   	            	         if(i<values.size()-1) out4.write(values.get(i)+",");
			   	            	         else out4.write(values.get(i)+"\n");
			   	            	    } 
			   	            	}
				    	} finally {
				    		out1.close();
				    		out2.close();
				    		out3.close();
				    		out4.close();
				    		out5.close();
				    		out6.close();
				    		out7.close();
				    		close=true; isSaved=true;
				    	}
				    }catch (Exception e) {}
			
			
		}
	 
	 //save Operations
		public void saveToFile(){
			String nameFile=FileOpenedName;
			correctMorpho();
			   try{
				   
				    Writer out1 = new BufferedWriter(new OutputStreamWriter(
				    			
				    new FileOutputStream(nameFile), "UTF-8"));
				     Writer out2 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"IW.ind"), "UTF-8"));
				     Writer out4 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"IT.ind"), "UTF-8"));
			    		
				     Writer out3 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"T.ann"), "UTF-8"));
				     Writer out5 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"D.ann"), "UTF-8"));
				     Writer out6 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"Meta.ann"), "UTF-8"));
				     Writer out7 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"NE.ann"), "UTF-8"));
				    	try {
				    		//System.out.println("write to file");
				    		String s="";
				    		
				    		JSONObject o=new JSONObject();
				    		JSONObject o1=new JSONObject();
				    		JSONArray a =new JSONArray();
				    		o.put("FWsize", fidalText.getWidth());
				    		o.put("FHsize", fidalText.getHeight());
				    		o.put("TWsize", translitText.getWidth());
				    		o.put("THsize", translitText.getHeight());
				    		if (!translitWordList.isSelectionEmpty()) o.put("SIGN",translitWordList.getSelectedIndex());
				    		else o.put("SIGN",0);
				    		o.put("TR", typDoc);
				    		o.put("SCR", typScript);
				    		o.put("CN", checkedN);
				    		if(noStruct>0) o.put("NOLEV", noStruct);
				    		if(noStruct>=1) {o.put("LEV1", structLevels.get("1")); o.put("NOL1", structLevelsBegin.get("1"));o.put("NOLN1", structLevelsBeginName.get("1"));}
				    		if(noStruct>=2) { o.put("LEV2", structLevels.get("2"));o.put("NOL2", structLevelsBegin.get("2"));o.put("NOLN2", structLevelsBeginName.get("2"));}
				    		if(noStruct>=3) {o.put("LEV3", structLevels.get("3"));o.put("NOL3", structLevelsBegin.get("3"));o.put("NOLN3", structLevelsBeginName.get("3"));}
				    		if(noStruct>=4) {o.put("LEV4", structLevels.get("4"));o.put("NOL4", structLevelsBegin.get("4"));o.put("NOLN4", structLevelsBeginName.get("4"));}
				    		//System.out.println(o.toString());
				    		out1.write(o.toString());
				    		out1.write("!!!!");
							for(int i=0;i<words.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
							//	a.put(words.get(i).toJson());
								out1.write(words.get(i).toJson().toString());
								if(i<words.size()-1)
					    		out1.write("****");
							}
				    		//o.put("FIDALWORDS", a);
				    		//System.out.println(o.toString());
				    		//System.out.println(o.toString());
				    		//out1.write(o.toString());
				    		//out1.write("*******");
				    		
				    		//a=new JSONArray();
				    		for(int i=0;i<tokens.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
							//	a.put(tokens.get(i).toJson());
								out3.write(tokens.get(i).toJson().toString());
								if(i<tokens.size()-1)
					    		    out3.write("****");
							}
				    		
				    		for(int i=0;i<divisions.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
							//	a.put(tokens.get(i).toJson());
								out5.write(divisions.get(i).toJson().toString());
								if(i<divisions.size()-1)
					    		    out5.write("****");
							}
				    	//	o1.put("TOKENS", a);
				    		//
				    		for(int i=0;i<nelist.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
							//	a.put(tokens.get(i).toJson());
								out7.write(nelist.get(i).toJson().toString());
								if(i<nelist.size()-1)
					    		    out7.write("****");
							}
				    		//
				    		if (metadataDoc !=null)
				    	out6.write(metadataDoc.toJSON().toString());
				    		
				    	//	out3.write(o1.toString());
		                	   FileOpenedName=nameFile; 
		                	   saveFile.setEnabled(true);
		                	   fidalText.setTitle(nameFile);
		                	   translitText.setTitle(nameFile);
		                	   
		                	   for (Map.Entry<String, ArrayList<String>> entry : index.entrySet()) {
		   	            	    String key = entry.getKey();
		   	            	    ArrayList<String> values = entry.getValue();
		   	            	    //System.out.println(values.size());
		   	            	    out2.write(key+"|");
		   	            	    for(int i=0;i<values.size();i++){
		   	            	    	//System.out.println(values.get(i));
		   	            	         if(i<values.size()-1) out2.write(values.get(i)+",");
		   	            	         else out2.write(values.get(i)+"\n");
		   	            	    } 
		   	            	}
		                	   for (Map.Entry<String, ArrayList<String>> entry : indexT.entrySet()) {
			   	            	    String key = entry.getKey();
			   	            	    ArrayList<String> values = entry.getValue();
			   	            	    //System.out.println(values.size());
			   	            	    out4.write(key+"|");
			   	            	    for(int i=0;i<values.size();i++){
			   	            	    	//System.out.println(values.get(i));
			   	            	         if(i<values.size()-1) out4.write(values.get(i)+",");
			   	            	         else out4.write(values.get(i)+"\n");
			   	            	    } 
			   	            	}
				    	} finally {
				    		out1.close();
				    		out2.close();
				    		out3.close();
				    		out4.close();
				    		out5.close();
				    		out6.close();
				    		out7.close();
				    		close=true; isSaved=true;
				    	}
				    }catch (Exception e) {}
			
			
		}
		
		public void recognizeSent() {
			int start=0;
			int i=0;
			int level=0;
			if (!structLevels.get("4").isEmpty()) level=4;
			else if (!structLevels.get("3").isEmpty()) level=3; 
			else if (!structLevels.get("2").isEmpty()) level=2;
			else level=1;
			int nr=Integer.parseInt(structLevelsBegin.get(level+""));
			while(start<words.size()) {
				i=start+1;
				while((!words.get(i).getFidalLabel(typDoc, typScript).equals("\u1362"))&&(i<(words.size()-1))){
					i=i+1;
				}
				if (words.get(i).getFidalLabel(typDoc, typScript).equals("\u1362")) {
					Division d= new Division(words.get(start).getId(),words.get(i).getId(),level+"D"+UUID.randomUUID(),level,nr,false);
					d.setCreator("Annotator");
					d.setNr(""+d.getInternalNr());
					d.setName(structLevelsBeginName.get(""+level));
					divisions.add(d); createIndexDivisions();
					for (int j=start;j<=i;j++)
					  words.get(j).getStrukturIds().add(d.getId());
					
					start=i+1;nr=nr+1;
					
				}
				else {
					if((i==(words.size()-1))){
						Division d= new Division(words.get(start).getId(),words.get(i).getId(),level+"D"+UUID.randomUUID(),level,nr,false);
						d.setCreator("Annotator");
						d.setNr(""+d.getInternalNr());
						d.setName(structLevelsBeginName.get(""+level));
						divisions.add(d); createIndexDivisions();
						for (int j=start;j<=i;j++)
						  words.get(j).getStrukturIds().add(d.getId());
						start=i+1;nr=nr+1;
					}
				}
				
			}
			recognSentMenu.setEnabled(false);
			fidalWordList.repaint();
		}
		public void savePartsToFile() {
			 int confirm= JOptionPane.showOptionDialog(desk,
     	                "After completing this operation the file you are working currently on will be closed. Do you want to continue? ",
     	                "Close Confirmation", JOptionPane.YES_NO_OPTION,
     	                JOptionPane.QUESTION_MESSAGE, null, null, null);
     		if (confirm == JOptionPane.YES_OPTION) {
			final ChildFrame divSelFrame=new ChildFrame("Select Divisions",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
			Container c=divSelFrame.getContentPane();
			c.setLayout(new GridBagLayout());
			JPanel c1=new JPanel();
			c1.setFont(etiopic1);
			c1.setLayout(new GridBagLayout());
			GridBagConstraints gbc1=new GridBagConstraints();
			gbc1.gridx=0;
			gbc1.gridy=0;
			gbc1.insets.left=2;
			gbc1.insets.top=2;
			gbc1.insets.bottom=2;
			gbc1.insets.right=2;
			gbc1.weighty=100.0;
			gbc1.weightx=100.0;
			gbc1.fill=GridBagConstraints.HORIZONTAL;
			gbc1.anchor=GridBagConstraints.NORTHWEST;
			//gbc.gridwidth=3;
			
			JCheckBox[] divs=new JCheckBox[divisions.size()];
			ArrayList<String> idDivSel=new ArrayList<String>();
			for (int i=0;i<divisions.size();i++) {
				//System.out.println(divisions.get(i).getName()+" "+divisions.get(i).getNr()+" ["+words.get(indexIdWord.get(divisions.get(i).getWbegin()).intValue()).getFidalLabel(typDoc,typScript)+"..."+words.get(indexIdWord.get(divisions.get(i).getWend()).intValue()).getFidalLabel(typDoc,typScript)+"]");
				divs[i]=new JCheckBox(divisions.get(i).getName()+" "+divisions.get(i).getNr()+" ["+words.get(indexIdWord.get(divisions.get(i).getWbegin()).intValue()).getFidalLabel(typDoc,typScript)+"..."+words.get(indexIdWord.get(divisions.get(i).getWend()).intValue()).getFidalLabel(typDoc,typScript)+"]");
			   divs[i].setFont(etiopic1);
			   gbc1.gridy=i;
				c1.add(divs[i],gbc1);
			   
			}
			GridBagConstraints gbc=new GridBagConstraints();
			gbc.gridx=0;
			gbc.gridy=0;
			gbc.insets.left=2;
			gbc.insets.top=2;
			gbc.insets.bottom=2;
			gbc.insets.right=2;
			gbc.weighty=95.0;
			gbc.weightx=100.0;
			gbc.fill=GridBagConstraints.BOTH;
			gbc.anchor=GridBagConstraints.NORTHWEST;
	        gbc.gridwidth=2;
	        JScrollPane jp=new JScrollPane(c1);
	        c.add(jp,gbc);
	        
	        JButton okB=new JButton("OK");
	        JButton cancelB=new JButton("Cancel");
	        gbc.weighty=5.0;
	        gbc.fill=GridBagConstraints.NONE;
	        gbc.gridwidth=1;
	        gbc.gridy=1;
	        c.add(okB,gbc);
	        gbc.gridx=1;
	        c.add(cancelB,gbc);
		
			for (int i=0;i<divisions.size();i++) {
				int pos=i;
				divs[i].addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e) {
			    		if (divs[pos].isSelected()){
			    			idDivSel.add(divisions.get(pos).getId());
			                //int anfang1=indexIdWord.get(divisions.get(pos).getWbegin()).intValue();
			                //int ende1= indexIdWord.get(divisions.get(pos).getWend()).intValue();
			    			
			    			//System.out.println("["+indexIdWord.get(divisions.get(pos).getWbegin()).intValue()+" "+indexIdWord.get(divisions.get(pos).getWend()).intValue()+"]");
			    			
			    			Division d=divisions.get(pos).copyDivision();
							//divisionSel.add(divisions.get(indexIdDivision.get(idDivSel.get(i)).intValue()));
							anfang1=indexIdWord.get(d.getWbegin()).intValue();
						 ende1=indexIdWord.get(d.getWend()).intValue();
							fidalWordList.setSelectionInterval(anfang1, ende1);
							
							for(int j=0;j<divisions.size();j++)
								if ((indexIdWord.get(divisions.get(j).getWbegin()).intValue()>=anfang1)&&(indexIdWord.get(divisions.get(j).getWend()).intValue()<=ende1))
									if(j!=pos) {
									divs[indexIdDivision.get(divisions.get(j).getId()).intValue()].setEnabled(false);
									idDivSel.add(divisions.get(j).getId());
									}
									
			    			
			    			
			    			
			    		}
			    		else {
			    			idDivSel.remove(divisions.get(pos).getId());
			    			Division d=divisions.get(pos).copyDivision();
							//divisionSel.add(divisions.get(indexIdDivision.get(idDivSel.get(i)).intValue()));
							int anfang=indexIdWord.get(d.getWbegin()).intValue();
							int ende=indexIdWord.get(d.getWend()).intValue();
							for(int j=0;j<divisions.size();j++)
								if ((indexIdWord.get(divisions.get(j).getWbegin()).intValue()>=anfang)&&(indexIdWord.get(divisions.get(j).getWend()).intValue()<=ende))
									if(j!=pos) {
										divs[indexIdDivision.get(divisions.get(j).getId()).intValue()].setEnabled(true);
										idDivSel.remove(divisions.get(j).getId());
										}
									
			    		}
			    	}
			    });
			}
		okB.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				saveToFile();
			ArrayList<WortNode> wordsSel=new ArrayList<WortNode>();
			ArrayList<Token> tokensSel=new ArrayList<Token>();
			ArrayList<Division> divisionSel=new ArrayList<Division>();
			
			for(int i=0;i<idDivSel.size();i++) {
				Division d=divisions.get(indexIdDivision.get(idDivSel.get(i)).intValue()).copyDivision();
				String idp=d.getParent();
				int k=0; boolean b=false;
				while ((k<divisionSel.size())&& (!b)) {
					if(divisionSel.get(k).getId().equals(idp)) b=true;
					else k=k+1;
				}
				if(!b) d.setParent("");
				 divisionSel.add(d);
			}
			for(int j=0;j<divisionSel.size();j++) {
				boolean swapped = true;
			    int k= 0;
			    Division tmp;
			    while (swapped) {
			        swapped = false;
			        k++;
			        for (int kk = 0; kk< divisionSel.size() - k; kk++) {
			            if (indexIdWord.get(divisionSel.get(kk).getWbegin()).intValue() > indexIdWord.get(divisionSel.get(kk+1).getWbegin()).intValue()) {
			                tmp = divisionSel.get(kk).copyDivision();
			                divisionSel.set(kk,divisionSel.get(kk + 1));
			                divisionSel.set(kk + 1, tmp);
			                swapped = true;
			            }
			        }
			    }
			}
			for(int i=0;i<divisionSel.size();i++) {
				  if (divisionSel.get(i).getParent().isEmpty()) { 
				for(int j=indexIdWord.get(divisionSel.get(i).getWbegin()).intValue();j<=indexIdWord.get(divisionSel.get(i).getWend()).intValue();j++) {
			       WortNode w=words.get(j);
			    wordsSel.add(w);
			       for(int k=0;k<w.getTokenIds().size();k++)
			    	   tokensSel.add(tokens.get(indexIdToken.get(w.getTokenIds().get(k)).intValue()));
			      
				}
			}
			}	
			for (int i=0;i<wordsSel.size();i++) {
				ArrayList<String> tempid=new ArrayList<String>();
				for (int j=0;j<wordsSel.get(i).getStrukturIds().size();j++) {
					int x=0; boolean found =false;
					while((x<divisionSel.size())&&(!found)) {
						if(divisionSel.get(x).getId().equals(wordsSel.get(i).getStrukturIds().get(j))) found=true;
						else x=x+1;
					}
					if (!found) tempid.add(wordsSel.get(i).getStrukturIds().get(j));
				}
				for(int j=0;j<tempid.size();j++) {
					wordsSel.get(i).getStrukturIds().remove(tempid.get(j));
				}
			}
			int neMax=0;
			for(int i=0;i<divisionSel.size();i++) {
				if (divisionSel.get(i).getLevel()>neMax) neMax=divisionSel.get(i).getLevel();
			}
			noStruct=neMax;
			Map<String,Integer> indexIdWordSel=new HashMap<String, Integer>();
			Map<String,Integer> indexIdTokenSel=new HashMap<String, Integer>();
			Map<String,Integer> indexIdDivisionSel=new HashMap<String, Integer>();
			 for(int i=0;i<wordsSel.size();i++){
				 indexIdWordSel.put(wordsSel.get(i).getId(), new Integer(i));
			 }
			 for(int i=0;i<tokensSel.size();i++){
				 indexIdTokenSel.put(tokensSel.get(i).getId(), new Integer(i));
			 }
			 for(int i=0;i<divisionSel.size();i++){
				 indexIdDivisionSel.put(divisionSel.get(i).getId(), new Integer(i));
			 }
			 //

				
			 ArrayList<String> indSel=new ArrayList<String>();
			 ArrayList<String> indTSel=new ArrayList<String>();
			 Map <String, ArrayList<String>> indexSel = new HashMap<String, ArrayList<String>>();
			 Map <String, ArrayList<String>> indexTSel = new HashMap<String, ArrayList<String>>();
			
			 String s1="";
			 String s2="";
			
	 for(int i=0;i<wordsSel.size();i++){
		 s1=wordsSel.get(i).getFidalInternLabel(typScript,typDoc);
		 s2=wordsSel.get(i).getTranslitLabel(typScript);
		 if (!indexSel.containsKey(s1+"*"+s2)){
			 indSel=new ArrayList<String>();
			 indSel.add(wordsSel.get(i).getId());
			 indexSel.put(s1+"*"+s2, indSel);
		 }
		 else indexSel.get(s1+"*"+s2).add(wordsSel.get(i).getId());
		// 
			 }
			
			
			 for(int i=0;i<tokensSel.size();i++){
				 
				 s2=tokensSel.get(i).getLabel();
				 if (!indexTSel.containsKey(s2)){
					 indTSel=new ArrayList<String>();
					 indTSel.add(tokensSel.get(i).getId());
					 indexTSel.put(s2, indTSel);
				 }
				 else indexTSel.get(s2).add(tokensSel.get(i).getId());
					 }
		for(int i=0;i<wordsSel.size();i++){	 
			char ch=verifyWordInIndex(i,wordsSel.get(i)).charAt(0);
			 if((!Character.isDigit(ch))||(!Character.isDigit(verifyTokensWordIndex(i,wordsSel.get(i)).charAt(0)))){
	   	        if (!wordsSel.get(i).hasErrorNode(typScript,typDoc))wordsSel.get(i).setError(1);
	   	        else wordsSel.get(i).setError(2);
	       }
	        else if (wordsSel.get(i).hasErrorNode(typScript,typDoc))wordsSel.get(i).setError(3);
	        else wordsSel.get(i).setError(0);
		}
		last_SIGN=0;
			 //
			String userhome = System.getProperty("user.home");
			JFileChooser fileChooser = new JFileChooser(userhome);
			//final Component modalToComponent;
			if (fileChooser.showSaveDialog(divSelFrame) == JFileChooser.APPROVE_OPTION) {
			  File file = fileChooser.getSelectedFile();
			  if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("json")) {
				    // filename is OK as-is
				} else {
				    file = new File(file.toString() + ".json");  // append .txt if "foo.jpg.xml" is OK
				    file = new File(file.getParentFile(), FilenameUtils.getBaseName(file.getName())+".json"); // ALTERNATIVELY: remove the extension (if any) and replace it with ".xml"
				}
			  String nameFile=file.getAbsolutePath();
			  String homeDir=file.getParent();
			   String nameFileShort=file.getName();
			   String nameOhneExt= nameFileShort.substring(0,nameFileShort.indexOf(".json"));
			   if(nameOhneExt.indexOf("VER")>0)
			   nameOhneExt=nameOhneExt.substring(0,nameOhneExt.indexOf("VER"));
			   DateFormat dateFormat = new SimpleDateFormat("yy_MM_dd_HH_mm_ss");
			
			   Date date = new Date();
			   
			   File directory = new File(homeDir,nameOhneExt+"VER"+dateFormat.format(date));
			 
			   directory.mkdir();
			   
			   File aux=new File(directory,nameOhneExt);
			   String nameFileShort1=aux.getAbsolutePath();
			   nameFile=nameFileShort1+"GeTa "+versTool+".json";
			   fileRoot=new File(nameFile);
			 // System.out.println(nameFile);
			   try{
				   
				    Writer out1 = new BufferedWriter(new OutputStreamWriter(
				    			
				    new FileOutputStream(nameFile), "UTF-8"));
				     Writer out2 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"IW.ind"), "UTF-8"));
				     Writer out4 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"IT.ind"), "UTF-8"));
			    		
				     Writer out3 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"T.ann"), "UTF-8"));
				     Writer out5 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"D.ann"), "UTF-8"));
				     Writer out6 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"Meta.ann"), "UTF-8"));
				     Writer out7 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"NE.ann"), "UTF-8"));
				     
				    	try {
				    		//System.out.println("write to file");
				    		String s="";
				    		
				    		JSONObject o=new JSONObject();
				    		JSONObject o1=new JSONObject();
				    		JSONArray a =new JSONArray();
				    		o.put("FWsize", fidalText.getWidth());
				    		o.put("FHsize", fidalText.getHeight());
				    		o.put("TWsize", translitText.getWidth());
				    		o.put("THsize", translitText.getHeight());
				    		//if (!translitWordList.isSelectionEmpty()) o.put("SIGN",translitWordList.getSelectedIndex());
				    		//else
				    			o.put("SIGN",0);
				    		o.put("TR", typDoc);
				    		o.put("SCR", typScript);
				    		o.put("CN", checkedN);
				    		if(noStruct>0) o.put("NOLEV", noStruct);
				    		if(noStruct>=1) {o.put("LEV1", structLevels.get("1"));o.put("NOL1", structLevelsBegin.get("1"));o.put("NOLN1", structLevelsBeginName.get("1"));}
				    		if(noStruct>=2) {o.put("LEV2", structLevels.get("2"));o.put("NOL2", structLevelsBegin.get("2"));o.put("NOLN2", structLevelsBeginName.get("2"));}
				    		if(noStruct>=3) {o.put("LEV3", structLevels.get("3"));o.put("NOL3", structLevelsBegin.get("3"));o.put("NOLN3", structLevelsBeginName.get("3"));}
				    		if(noStruct==4) {o.put("LEV4", structLevels.get("4"));o.put("NOL4", structLevelsBegin.get("4"));o.put("NOLN4", structLevelsBeginName.get("4"));}
				    		//System.out.println(o.toString());
				    		out1.write(o.toString());
				    		out1.write("!!!!");
							for(int i=0;i<wordsSel.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
							//	a.put(words.get(i).toJson());
								out1.write(wordsSel.get(i).toJson().toString());
								if(i<wordsSel.size()-1)
					    		 out1.write("****");
							}
				    		//o.put("FIDALWORDS", a);
				    		//System.out.println(o.toString());
				    		//System.out.println(o.toString());
				    		//out1.write(o.toString());
				    		//out1.write("*******");
				    		
				    		//a=new JSONArray();
				    		for(int i=0;i<tokensSel.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
							//	a.put(tokens.get(i).toJson());
								out3.write(tokensSel.get(i).toJson().toString());
								if(i<tokensSel.size()-1)
					    		   out3.write("****");
							}
				    		
				    		for(int i=0;i<divisionSel.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
							//	a.put(tokens.get(i).toJson());
								out5.write(divisionSel.get(i).toJson().toString());
								if(i<divisionSel.size()-1)
					    		    out5.write("****");
							}
				    	//	o1.put("TOKENS", a);
				    		
				    	if(metadataDoc!=null) {
				    		
				    		out6.write(metadataDoc.toJSON().toString());
				    	}
				    	
				    	
				    	for(int i=0;i<nelist.size();i++){
							//System.out.println(i+"---"+words.get(i).toJson());
						//	a.put(tokens.get(i).toJson());
				    		boolean found1=false;
				    		ArrayList <RefWord> delRef=new ArrayList<RefWord>();
				    		for(int j=0; j<nelist.get(i).getRefIDS().size(); j++) {
				    			String tempid=nelist.get(i).getRefIDS().get(j).getWID();
				    			int x=0; boolean found=false;
				    			while ((x<wordsSel.size())&&(!found)) {
				    				if( wordsSel.get(x).getId().equals(tempid))found=true;
				    				else x=x+1;
				    		}
				    			if(!found) delRef.add(nelist.get(i).getRefIDS().get(j));
				    			found1=found|found1;
				    			}
							if (found1) {
								for( int k=0;k<delRef.size();k++)
								nelist.get(i).getRefIDS().remove(delRef.get(k));
								out7.write(nelist.get(i).toJson().toString());
							}
				    		
							if(i<nelist.size()-1)
				    		    out7.write("****");
						}
				    	//	out3.write(o1.toString());
		                	 /*  FileOpenedName=nameFile; 
		                	   saveFile.setEnabled(true);
		                	   fidalText.setTitle(nameFile);
		                	   translitText.setTitle(nameFile);
		                	   */
		                	   for (Map.Entry<String, ArrayList<String>> entry : indexSel.entrySet()) {
		   	            	    String key = entry.getKey();
		   	            	    ArrayList<String> values = entry.getValue();
		   	            	    //System.out.println(values.size());
		   	            	    out2.write(key+"|");
		   	            	    for(int i=0;i<values.size();i++){
		   	            	    	//System.out.println(values.get(i));
		   	            	         if(i<values.size()-1) out2.write(values.get(i)+",");
		   	            	         else out2.write(values.get(i)+"\n");
		   	            	    } 
		   	            	}
		                	   for (Map.Entry<String, ArrayList<String>> entry : indexTSel.entrySet()) {
			   	            	    String key = entry.getKey();
			   	            	    ArrayList<String> values = entry.getValue();
			   	            	    //System.out.println(values.size());
			   	            	    out4.write(key+"|");
			   	            	    for(int i=0;i<values.size();i++){
			   	            	    	//System.out.println(values.get(i));
			   	            	         if(i<values.size()-1) out4.write(values.get(i)+",");
			   	            	         else out4.write(values.get(i)+"\n");
			   	            	    } 
			   	            	}
		                	   close=true;isSaved=true;
				    	} finally {
				    		out1.close();
				    		out2.close();
				    		out3.close();
				    		out4.close();
				    		out5.close();
				    		out6.close();
				    	    out7.close();
				    		close=true;
				    	}
				    }catch (Exception ex) {} 
			}
			else close=false;
			
		closeFile();divSelFrame.doDefaultCloseAction();
		}
		});
		cancelB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				divSelFrame.doDefaultCloseAction();
			}
		});
		divSelFrame.setLocation(400, 100);
		divSelFrame.setSize(50,50);
		divSelFrame.pack();
		divSelFrame.show();
		 addChild(divSelFrame,400,100,300,800);
	        divSelFrame.setVisible(true);
		divSelFrame.moveToFront();
		}
		}
		
		public void saveAsToFile(){
			correctMorpho();
			String userhome = System.getProperty("user.home");
			JFileChooser fileChooser = new JFileChooser(userhome);
			//final Component modalToComponent;
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			  File file = fileChooser.getSelectedFile();
			  if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("json")) {
				    // filename is OK as-is
				} else {
				    file = new File(file.toString() + ".json");  // append .txt if "foo.jpg.xml" is OK
				    file = new File(file.getParentFile(), FilenameUtils.getBaseName(file.getName())+".json"); // ALTERNATIVELY: remove the extension (if any) and replace it with ".xml"
				}
			  String nameFile=file.getAbsolutePath();
			  String homeDir=file.getParent();
			   String nameFileShort=file.getName();
			   String nameOhneExt= nameFileShort.substring(0,nameFileShort.indexOf(".json"));
			   if(nameOhneExt.indexOf("VER")>0)
			   nameOhneExt=nameOhneExt.substring(0,nameOhneExt.indexOf("VER"));
			   DateFormat dateFormat = new SimpleDateFormat("yy_MM_dd_HH_mm_ss");
			
			   Date date = new Date();
			   
			   File directory = new File(homeDir,nameOhneExt+"VER"+dateFormat.format(date));
			 
			   directory.mkdir();
			   
			   File aux=new File(directory,nameOhneExt);
			   String nameFileShort1=aux.getAbsolutePath();
			   nameFile=nameFileShort1+"GeTa "+versTool+".json";
			   fileRoot=new File(nameFile);
			 // System.out.println(nameFile);
			   try{
				   
				    Writer out1 = new BufferedWriter(new OutputStreamWriter(
				    			
				    new FileOutputStream(nameFile), "UTF-8"));
				     Writer out2 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"IW.ind"), "UTF-8"));
				     Writer out4 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"IT.ind"), "UTF-8"));
			    		
				     Writer out3 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"T.ann"), "UTF-8"));
				     Writer out5 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"D.ann"), "UTF-8"));
				     Writer out6 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"Meta.ann"), "UTF-8"));
				     Writer out7 = new BufferedWriter(new OutputStreamWriter(
				    			
	           				    new FileOutputStream(nameFile.substring(0,nameFile.indexOf('.'))+"NE.ann"), "UTF-8"));
				    	try {
				    		//System.out.println("write to file");
				    		String s="";
				    		
				    		JSONObject o=new JSONObject();
				    		JSONObject o1=new JSONObject();
				    		JSONArray a =new JSONArray();
				    		o.put("FWsize", fidalText.getWidth());
				    		o.put("FHsize", fidalText.getHeight());
				    		o.put("TWsize", translitText.getWidth());
				    		o.put("THsize", translitText.getHeight());
				    		if (!translitWordList.isSelectionEmpty()) o.put("SIGN",translitWordList.getSelectedIndex());
				    		else o.put("SIGN",0);
				    		o.put("TR", typDoc);
				    		o.put("SCR", typScript);
				    		o.put("CN", checkedN);
				    		if(noStruct>0) o.put("NOLEV", noStruct);
				    		if(noStruct>=1) {o.put("LEV1", structLevels.get("1"));o.put("NOL1", structLevelsBegin.get("1"));o.put("NOLN1", structLevelsBeginName.get("1"));}
				    		if(noStruct>=2) {o.put("LEV2", structLevels.get("2"));o.put("NOL2", structLevelsBegin.get("2"));o.put("NOLN2", structLevelsBeginName.get("2"));}
				    		if(noStruct>=3) {o.put("LEV3", structLevels.get("3"));o.put("NOL3", structLevelsBegin.get("3"));o.put("NOLN3", structLevelsBeginName.get("3"));}
				    		if(noStruct==4) {o.put("LEV4", structLevels.get("4"));o.put("NOL4", structLevelsBegin.get("4"));o.put("NOLN4", structLevelsBeginName.get("4"));}
				    		//System.out.println(o.toString());
				    		out1.write(o.toString());
				    		out1.write("!!!!");
							for(int i=0;i<words.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
							//	a.put(words.get(i).toJson());
								out1.write(words.get(i).toJson().toString());
								if(i<words.size()-1)
					    		 out1.write("****");
							}
				    		//o.put("FIDALWORDS", a);
				    		//System.out.println(o.toString());
				    		//System.out.println(o.toString());
				    		//out1.write(o.toString());
				    		//out1.write("*******");
				    		
				    		//a=new JSONArray();
				    		for(int i=0;i<tokens.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
							//	a.put(tokens.get(i).toJson());
								out3.write(tokens.get(i).toJson().toString());
								if(i<tokens.size()-1)
					    		   out3.write("****");
							}
				    		
				    		for(int i=0;i<divisions.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
							//	a.put(tokens.get(i).toJson());
								out5.write(divisions.get(i).toJson().toString());
								if(i<divisions.size()-1)
					    		    out5.write("****");
							}
				    		
				    		for(int i=0;i<nelist.size();i++){
								//System.out.println(i+"---"+words.get(i).toJson());
							//	a.put(tokens.get(i).toJson());
								out7.write(nelist.get(i).toJson().toString());
								if(i<nelist.size()-1)
					    		    out7.write("****");
							}
				    	//	o1.put("TOKENS", a);
				    		if (metadataDoc !=null)
				    		out6.write(metadataDoc.toJSON().toString());
				    	
				    		
				    	//	out3.write(o1.toString());
		                	   FileOpenedName=nameFile; 
		                	   saveFile.setEnabled(true);
		                	   fidalText.setTitle(nameFile);
		                	   translitText.setTitle(nameFile);
		                	   
		                	   for (Map.Entry<String, ArrayList<String>> entry : index.entrySet()) {
		   	            	    String key = entry.getKey();
		   	            	    ArrayList<String> values = entry.getValue();
		   	            	    //System.out.println(values.size());
		   	            	    out2.write(key+"|");
		   	            	    for(int i=0;i<values.size();i++){
		   	            	    	//System.out.println(values.get(i));
		   	            	         if(i<values.size()-1) out2.write(values.get(i)+",");
		   	            	         else out2.write(values.get(i)+"\n");
		   	            	    } 
		   	            	}
		                	   for (Map.Entry<String, ArrayList<String>> entry : indexT.entrySet()) {
			   	            	    String key = entry.getKey();
			   	            	    ArrayList<String> values = entry.getValue();
			   	            	    //System.out.println(values.size());
			   	            	    out4.write(key+"|");
			   	            	    for(int i=0;i<values.size();i++){
			   	            	    	//System.out.println(values.get(i));
			   	            	         if(i<values.size()-1) out4.write(values.get(i)+",");
			   	            	         else out4.write(values.get(i)+"\n");
			   	            	    } 
			   	            	}
		                	   close=true;isSaved=true;
				    	} finally {
				    		out1.close();
				    		out2.close();
				    		out3.close();
				    		out4.close();
				    		out5.close();
				    		out6.close();
				    		out7.close();
				    		close=true;
				    	}
				    }catch (Exception e) {} 
			}
			else close=false;
			
		}
	 //
	 //reading Operatio
		public void readAnnotFile(){
		//lex=new Lexicon();
		
		JFileChooser fc = Utils.getFileChooser();
		String userhome = System.getProperty("user.home");
		 fc = new JFileChooser(userhome);
		//JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("ANNOT FILES", "json");
		fc.setFileFilter(filter);
		int returnVal = fc.showOpenDialog(this);
		
		
		if(returnVal==JFileChooser.APPROVE_OPTION){
	     //case JFileChooser.APPROVE_OPTION:
	    	 //Utils.setLastDir(fc.getSelectedFile());
		 fileRoot=fc.getSelectedFile();
		Utils.setLastDir(fileRoot);
		//File fileRoot=fc.getSelectedFile();
		// lex=new Lexicon();
		final ChildFrame statusLoad=new ChildFrame("Reading",Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
		Container c=statusLoad.getContentPane();
		final JTextArea progressEntry=new JTextArea();
		progressEntry.setFont(etiopicText);
		//progress="Reading...\n";
		progressEntry.setText("Reading...\n");
		JScrollPane jp=new JScrollPane(progressEntry);
		c.add(jp);
		statusLoad.setLocation(100, 100);
		statusLoad.setSize(50,50);
		statusLoad.pack();
		statusLoad.show();
		 addChild(statusLoad,100,100,300,200);
	        statusLoad.setVisible(true);
		statusLoad.moveToFront();
		
		FileOpenedName=fileRoot.getAbsolutePath();
		docID =fileRoot.getName();
		//System.out.println("Loaded "+ fileRoot.getName());
		new Thread() {
	        public void run() {
	        	boolean stop=false;
	        	while (!stop){
		try{
		//InputStreamReader r = new InputStreamReader(new FileInputStream(FileOpenedName),"UTF-8");
		//InputStreamReader r1 = new InputStreamReader(new FileInputStream(FileOpenedName.substring(0,FileOpenedName.indexOf('.'))+"T.ann"),"UTF-8");
		InputStreamReader r = new InputStreamReader(new FileInputStream(FileOpenedName.substring(0,FileOpenedName.lastIndexOf('.'))+"IT.ind"),"UTF-8");
		//InputStreamReader r3 = new InputStreamReader(new FileInputStream(FileOpenedName.substring(0,FileOpenedName.indexOf('.'))+"IW.ind"),"UTF-8");
		//String jsonTxt = IOUtils.toString(r);
		//String jsonTxt1 = IOUtils.toString(r1);
		//String indW=IOUtils.toString(r3);
		String ind=IOUtils.toString(r);ind=ind.replace("\uFEFF", "");
		StringTokenizer st=new StringTokenizer(ind,"\n");
		//progress=progress+"Start read index Tokens\n";
		progressEntry.append("Start read index Tokens\n");
	
		while(st.hasMoreTokens()){
			
			String tt=st.nextToken();
			String ttkey=tt.substring(0,tt.indexOf('|'));
			ArrayList<String> ttValue=new ArrayList<String>();
			StringTokenizer stt=new StringTokenizer(tt.substring(tt.indexOf('|')+1),",");
			while(stt.hasMoreTokens())
				ttValue.add(stt.nextToken());
			indexT.put(ttkey, ttValue);
		}
		progress=progress+"Finish read index Tokens\n";
		progressEntry.append("Start read index Tokens\n");
		r = new InputStreamReader(new FileInputStream(FileOpenedName.substring(0,FileOpenedName.lastIndexOf('.'))+"IW.ind"),"UTF-8");	
		ind=IOUtils.toString(r);ind=ind.replace("\uFEFF", "");
		 st=new StringTokenizer(ind,"\n");
		// progress=progress+"Start read index Word units\n";
			progressEntry.append("Start read index Word units\n");
			
		
		while(st.hasMoreTokens()){
			String tt=st.nextToken();
			String ttkey=tt.substring(0,tt.indexOf('|'));
			ArrayList<String> ttValue=new ArrayList<String>();
			StringTokenizer stt=new StringTokenizer(tt.substring(tt.indexOf('|')+1),",");
			while(stt.hasMoreTokens())
				ttValue.add(stt.nextToken());
			index.put(ttkey, ttValue);
		}
		// progress=progress+"End read index Word units\n";
			progressEntry.append("End read index Word units\n");
        // System.out.println("LINE "+jsonTxt);
		//JSONObject jsonW = new JSONObject(jsonTxt); 
		//JSONObject jsonT = new JSONObject(jsonTxt1); 
		//JSONArray w=jsonW.getJSONArray("FIDALWORDS");
		//JSONArray t=jsonT.getJSONArray("TOKENS");
		
		//JSONArray f,l;
		//System.out.println(w.toString());
		r = new InputStreamReader(new FileInputStream(FileOpenedName),"UTF-8");
		ind=IOUtils.toString(r);
		//System.out.println("Char "+ind.charAt(0));
		ind=ind.replace("\uFEFF", "");
		String dimens=ind.substring(0,ind.indexOf("!!!!"));

		JSONObject jsonW=new JSONObject(dimens);
		fidalW_Size=jsonW.getInt("FWsize");
		fidalH_Size=jsonW.getInt("FHsize");
		translitW_Size=jsonW.getInt("TWsize");
		translitH_Size=jsonW.getInt("THsize");
		if(jsonW.has("SIGN")) last_SIGN=jsonW.getInt("SIGN");
		if(jsonW.has("TR")) typDoc=jsonW.getInt("TR");
		else typDoc=0;
		if(jsonW.has("SCR")) typScript=jsonW.getInt("SCR");
		else typScript=0;
		structLevels=new HashMap<String,String>();
		structLevelsBegin=new HashMap<String,String>();
		structLevelsBeginName=new HashMap<String,String>();
		if(jsonW.has("NOLEV")) {
			noStruct=jsonW.getInt("NOLEV");
			hasStructSpec=true;
			structDiv.setEnabled(false);
			
			if (jsonW.has("LEV1")) {
				//noStruct=noStruct+1;
				structLevels.put("1", jsonW.getString("LEV1"));
				if(jsonW.has("NOL1"))
				structLevelsBegin.put("1", jsonW.getString("NOL1"));
				else structLevelsBegin.put("1","1");
				if(jsonW.has("NOLN1"))
					structLevelsBeginName.put("1", jsonW.getString("NOLN1"));
					else structLevelsBeginName.put("1",jsonW.getString("LEV1"));
				
				}
			else {structLevels.put("1", "");structLevelsBegin.put("1", "1");structLevelsBeginName.put("1", "");}
			if (jsonW.has("LEV2")) {
				//noStruct=noStruct+1;
				structLevels.put("2", jsonW.getString("LEV2"));
				if(jsonW.has("NOL2"))
				structLevelsBegin.put("2", jsonW.getString("NOL2"));
				else structLevelsBegin.put("2","1");
				if(jsonW.has("NOLN2"))
					structLevelsBeginName.put("2", jsonW.getString("NOLN2"));
					else structLevelsBeginName.put("2", jsonW.getString("LEV2"));
				}
			else {structLevels.put("2", "");structLevelsBegin.put("2", "1");structLevelsBeginName.put("2", "");}
			if (jsonW.has("LEV3")) {
				//noStruct=noStruct+1;
				structLevels.put("3", jsonW.getString("LEV3"));
				if(jsonW.has("NOL3"))
				structLevelsBegin.put("3", jsonW.getString("NOL3"));
				else structLevelsBegin.put("3","1");
				if(jsonW.has("NOLN3"))
					structLevelsBeginName.put("3", jsonW.getString("NOLN3"));
					else structLevelsBeginName.put("3","1");
				}
			else {structLevels.put("3", "");structLevelsBegin.put("3", "1");structLevelsBeginName.put("3", "");}
			if (jsonW.has("LEV4")) {
				//noStruct=noStruct+1;
				structLevels.put("4", jsonW.getString("LEV4"));
				if(jsonW.has("NOL4"))
				structLevelsBegin.put("4", jsonW.getString("NOL4"));
				else structLevelsBegin.put("4","1");
				if(jsonW.has("NOLN4"))
					structLevelsBeginName.put("4", jsonW.getString("NOLN4"));
					else structLevelsBegin.put("4","1");
				}
			else {structLevels.put("4", "");structLevelsBeginName.put("4", "1");structLevelsBeginName.put("4", "");}
		}
		else {
			structDiv.setEnabled(true);
			hasStructSpec=false;
			structLevels.put("1", "");
			structLevels.put("2", "");
			structLevels.put("3", "");
			structLevels.put("4", "");
			structLevelsBegin.put("1", "1");
			structLevelsBegin.put("2", "1");
			structLevelsBegin.put("3", "1");
			structLevelsBegin.put("4", "1");
			structLevelsBeginName.put("1", "");
			structLevelsBeginName.put("2", "");
			structLevelsBeginName.put("3", "");
			structLevelsBeginName.put("4", "");
		}
		if(typScript==1){
			 fidalWordList.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			scrollerFidal.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		 }
	 else 
	{
		 fidalWordList.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollerFidal.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	 }
		if(jsonW.has("CN")){checkedN=Boolean.valueOf(jsonW.get("CN").toString());verifyNouns.setEnabled(!checkedN);}
		else {checkedN=false;verifyNouns.setEnabled(!checkedN);}
		 //let;
	       // progress=progress+"Start creating Word structure";
			progressEntry.append("Start creating Word unit structure");
			String aux="";
			 Map<String,ArrayList<LatinLetterNode>> indexLetterToken=new HashMap<String, ArrayList<LatinLetterNode>>();
			///indexLetterToken.clear();
			 ind=ind.substring(ind.indexOf("!!!!")+4);
			 st=new StringTokenizer(ind,"****");
		//for(int i=0;i<w.length();i++)
			 int sizeW=st.countTokens();
			 int nrword=0;
			 while(st.hasMoreTokens()){
			//a fidal word
				 String w=st.nextToken();
				// System.out.println(w);
			 JSONObject jsonobject =new JSONObject(w);
			
			 WortNode wn=new WortNode(jsonobject.get("Id").toString());
			 if(jsonobject.has("Col")) wn.setFColor(new Color(jsonobject.getInt("Col")));
			 if(jsonobject.has("Comm")) wn.setComment(jsonobject.get("Comm").toString());
			 
			 if(jsonobject.has("Sid")) {
				 JSONArray levarr=jsonobject.getJSONArray("Sid");
				 ArrayList<String> mapw=new ArrayList<String>();
				 for (int i=0;i<levarr.length();i++){
					 mapw.add(levarr.getString(i));
					 
				    
				 }
				 wn.setStrukturIds(mapw);
			 }
			 if(jsonobject.has("NE")) {
			
				wn.setNERef(jsonobject.get("NE").toString());			 
				    
				 }
				
			wn.setAutomToken(Boolean.valueOf(jsonobject.get("AT").toString()));
			 JSONArray f=jsonobject.getJSONArray("FC");
			 //iterate for each fidal sillable
			// FidalNode fn;
			 //LatinLetterNode ln;
			 for(int j=0;j<f.length(); j++){
				JSONObject fidalobject=f.getJSONObject(j);
				String endS="";
				if ((fidalobject.has("endS"))) endS=fidalobject.getString("endS");
				 FidalNode fn=new FidalNode(wn,Integer.parseInt(fidalobject.get("Nr").toString()),Boolean.valueOf(fidalobject.get("end").toString()),endS);
				
				 JSONArray l=fidalobject.getJSONArray("LL");
				 for(int k=0;k<l.length();k++){
					JSONObject  letterobject=l.getJSONObject(k);
					 LatinLetterNode ln=new LatinLetterNode(letterobject.get("L").toString().charAt(0),(byte)Integer.parseInt(letterobject.get("T").toString()),(byte)Integer.parseInt(letterobject.get("Nr").toString()),fn);
					 fn.addChild(ln);
					 ln.setTokenId(letterobject.get("Tid").toString());
					 ln.setParent(fn);
					// System.out.println("Parent "+fn.getTranslitLabel(typScript)+ "Letter "+ln.getEntireTranslit());
					 if(indexLetterToken.containsKey(letterobject.get("Tid").toString()))
						 
				        indexLetterToken.get(letterobject.get("Tid").toString()).add(ln);
					 else {
						 ArrayList<LatinLetterNode> let =new ArrayList<LatinLetterNode>();
						 let.add(ln);
						 indexLetterToken.put(letterobject.get("Tid").toString(),let);
					 }
					 
				 }
				if (fidalobject.has("Ed")){
					JSONObject edobj=fidalobject.getJSONObject("Ed");
					JSONArray edList=edobj.getJSONArray("LT");
					ArrayList<Tag> edTag=new ArrayList<Tag>();
					for(int k=0;k<edList.length();k++){
						JSONObject  edobject=edList.getJSONObject(k);
						
						if (edobject.getString("NT").equals("ed")){
							
							JSONArray bracketList=edobject.getJSONArray("AL");
							ArrayList<Attribut> ated=new ArrayList<Attribut>();
							for(int k1=0;k1<bracketList.length();k1++){
								JSONObject bracketo=bracketList.getJSONObject(k1);
								Attribut a=new Attribut(bracketo.getString("N"),bracketo.getString("V"));
								ated.add(a);
								
							}
							Tag edT=new Tag("ed",ated);
							edTag.add(edT);
						}
						else if (edobject.getString("NT").equals("lb")){
							JSONArray bracketList=edobject.getJSONArray("AL");
							ArrayList<Attribut> atlb=new ArrayList<Attribut>();
							for(int k1=0;k1<bracketList.length();k1++){
								JSONObject bracketo=bracketList.getJSONObject(k1);
								Attribut a=new Attribut(bracketo.getString("N"),bracketo.getString("V"));
								atlb.add(a);
								
							}
							Tag lbT=new Tag("lb",atlb);
							edTag.add(lbT);
						}
						else if (edobject.getString("NT").equals("pb")){
							JSONArray bracketList=edobject.getJSONArray("AL");
							ArrayList<Attribut> atlb=new ArrayList<Attribut>();
							for(int k1=0;k1<bracketList.length();k1++){
								JSONObject bracketo=bracketList.getJSONObject(k1);
								Attribut a=new Attribut(bracketo.getString("N"),bracketo.getString("V"));
								atlb.add(a);
								
							}
							Tag pbT=new Tag("pb",atlb);
							edTag.add(pbT);
						}
					}
					Annotation edanno=new Annotation(false,false,true,false,edTag);
					fn.setEdAnnot(edanno);
				}
				 if (fidalobject.has("pLB")) fn.setPosLB(fidalobject.getInt("pLB"));
				 wn.addChild(j, fn);
				 
				
			 }
			 JSONArray id=jsonobject.getJSONArray("Tid");
			 ArrayList<String>tid=new ArrayList<String>();
			 for(int kk=0;kk<id.length();kk++) tid.add(id.getString(kk));
			 wn.setTokenIds(tid);
			 //System.out.println(wn.getFidalLabel());
			
			 words.add(wn);
			 nrword=nrword+1;
			// System.out.println(wn.getFidalLabel());
			// aux= wn.getFidalLabel();
			// progress=progress+"Read "+ wn.getFidalLabel()+"\n";
			//	progressEntry.append("Read "+ wn.getFidalLabel()+"\n");
		   if(nrword==sizeW/2) progressEntry.append("read " +nrword +" Word units\n");
			// progressBar_1.setValue((int) Math.round(lengthPerPercent * i));
		}
		//progressBar_1.setValue(0);
			 progressEntry.append("Finish creating Word unit structure");
			// div1B=new EnumSet<String>();div2B=new Set<String>();div3B=new Set<String>();div4B=new Set<String>();
			 //div1E=new Set<String>();div2E=new Set<String>();div3E=new Set<String>();div4E=new Set<String>();
			 File nfdiv=new File(FileOpenedName.substring(0,FileOpenedName.lastIndexOf('.'))+"NE.ann");
			 if (nfdiv.exists()){
				 progressEntry.append("Start creating NE structure");
				 r = new InputStreamReader(new FileInputStream(FileOpenedName.substring(0,FileOpenedName.lastIndexOf('.'))+"NE.ann"),"UTF-8");
				 ind=IOUtils.toString(r);
				 ind=ind.replace("\uFEFF", "");
				 st=new StringTokenizer(ind,"****");
				// System.out.println(st.countTokens());
				
				 int sizeT1=st.countTokens();
				 int nrtok1=0;
			   while(st.hasMoreTokens()){
				JSONObject neobject=new JSONObject(st.nextToken());
				
				 String s1=neobject.getString("Id");
				// System.out.println(s1);
				 String s2=neobject.getString("T");
				 String s3=neobject.getString("R");
				boolean s4=neobject.getBoolean("imp");
				boolean s5=neobject.getBoolean("aut");
				 if (neobject.has("ref")){
					 JSONArray refw=neobject.getJSONArray("ref");
				ArrayList<RefWord> rr=new ArrayList<RefWord>();
				 for(int i=0;i<refw.length();i++){
					   
						JSONObject o=refw.getJSONObject(i);
						String ss=o.getString("WId");
						ArrayList<String> tids=new ArrayList<String>();
						 JSONArray a=o.getJSONArray("TID");
						 for(int j=0;j<a.length();j++)
							 tids.add(a.getString(j));
						 rr.add(new RefWord(ss,tids));
					 }
				 ArrayList<Attribut> attr=new ArrayList<Attribut>();
				 if(neobject.has("feat")) {
					 JSONArray feat=neobject.getJSONArray("feat");
					 if(feat!=null) {
					 for(int i=0;i<feat.length();i++) {
						 JSONObject o1=feat.getJSONObject(i);
						 if (o1.has("V"))
						 attr.add(new Attribut(o1.getString("N"),o1.getString("V")));
						 else 
							 attr.add(new Attribut(o1.getString("N"),""));
					 }
					 }
				 }
				NamedEntity neEntry=new NamedEntity(s1, s3,s2, rr, attr,s4,s5);
				nelist.add(neEntry);
				 }
			   }
			   progressEntry.append("Finish creating NE structure");
			 }
		File fdiv=new File(FileOpenedName.substring(0,FileOpenedName.lastIndexOf('.'))+"D.ann");
		if (fdiv.exists()){
			 progressEntry.append("Start creating division structure");
			 r = new InputStreamReader(new FileInputStream(FileOpenedName.substring(0,FileOpenedName.lastIndexOf('.'))+"D.ann"),"UTF-8");
			 ind=IOUtils.toString(r);
			 ind=ind.replace("\uFEFF", "");
			 st=new StringTokenizer(ind,"****");
			// System.out.println(st.countTokens());
			
			 int sizeT1=st.countTokens();
			 int nrtok1=0;
		   while(st.hasMoreTokens()){
			JSONObject divobject=new JSONObject(st.nextToken());
			 
				 String s1=divobject.getString("Id");
				 String s2=divobject.getString("LE");
				 String s3=divobject.getString("WB");
				 String s4=divobject.getString("WE");
				 String s5=divobject.getString("NRI");
				 String s6=divobject.getString("NR");
				 String s7=divobject.getString("C");
				 String s8=divobject.getString("DP");
				 String s9=divobject.getString("G");
				 String s10=divobject.getString("NA");
				 String s11=divobject.getString("CR");
				 ArrayList<String> fc=new ArrayList<String>();
				 if (divobject.has("DC")){
					 JSONArray atr=divobject.getJSONArray("DC");
				 for(int j=0;j<atr.length();j++){
						// JSONObject o=atr.getJSONObject(j);
						 //fc.add(o.toString());
					 fc.add(atr.get(j).toString());
					 }
				 }
				 
				 boolean b=true;
				if (s6.equals(s5)) b=false;
				 Division div=new Division(s3,s4,s1,Integer.parseInt(s2),Integer.parseInt(s5),b);
				 div.setGenre(s9);
				 div.setChildren(fc);
				 div.setNr(s6);
				 div.setComment(s7);
				 div.setParent(s8);
				 div.setName(s10);
				 div.setCreator(s11);
				 divisions.add(div);
			
			 
		} 
		 //.revalidate();  fidalWordList.repaint();
		progressEntry.append("Finish creating Division structure");
		}
			 //
			 progressEntry.append("Start creating token structure");
			 r = new InputStreamReader(new FileInputStream(FileOpenedName.substring(0,FileOpenedName.lastIndexOf('.'))+"T.ann"),"UTF-8");
			 ind=IOUtils.toString(r);
			 ind=ind.replace("\uFEFF", "");
			 st=new StringTokenizer(ind,"****");
			// System.out.println(st.countTokens());
			 JSONObject morpho;
			 JSONArray tags;
			 JSONObject tag;
			 String tagName;
			 Tag tt;
			 JSONArray attr;
			 ArrayList<Attribut> atribute;
			 ArrayList<Tag> tl;
			 Attribut a;
			 JSONObject at;
			 Annotation an;
			// ArrayList<String> tokenString=new ArrayList<String>();
			 int sizeT=st.countTokens();
			 int nrtok=0;
			 String tt1="";
		while(st.hasMoreTokens()){
		
			 //System.out.println(t1);
			 tt1=st.nextToken();
			// System.out.println(tt1);
			JSONObject jsonobject1=new JSONObject(tt1);
			 //System.out.println(jsonobject1);
			// l=jsonobject1.getJSONArray("LC");
			 ArrayList<LatinLetterNode> let=new ArrayList<LatinLetterNode>();
			//System.out.println(indexLetterToken.get(jsonobject1.get("Id").toString()));
			let=indexLetterToken.get(jsonobject1.get("Id").toString());
		// for(int k=0;k<indexLetterToken.get(jsonobject1.get("Id").toString()).size();k++){
				// letterobject=l.getJSONObject(k);
				//LatinLetterNode laux=indexLetterToken.get(jsonobject1.get("Id").toString()).get(k);
				//LatinLetterNode ln=new LatinLetterNode(letterobject.get("L").toString().charAt(0),(byte)Integer.parseInt(letterobject.get("T").toString()),(byte)Integer.parseInt(letterobject.get("Nr").toString()),laux.getParent());
			   
			    // let.add(indexLetterToken.get(jsonobject1.get("Id").toString()).get(k));
			// }	
			  an=null;
			
					
			 if(jsonobject1.has("M")){
	         morpho=jsonobject1.getJSONObject("M");
			 tags= morpho.getJSONArray("LT");
			 tag=tags.getJSONObject(0);
			 tagName=tag.getString("NT"); 
			 //System.out.println(tagName);
			 
			 if(tag.has("AL")){
			 attr= tag.getJSONArray("AL");
		      atribute=new ArrayList<Attribut>();
			 for(int k=0;k<attr.length();k++){
				   at=attr.getJSONObject(k);
				  a=new Attribut(at.get("N").toString(),at.getString("V").toString());
				 atribute.add(a);
			 }
			 tt=new Tag(tagName,atribute);
			 }
			 
			 else {tt=new Tag(tagName);}
			//System.out.println(morpho.get("c").toString());
		      tl=new ArrayList<Tag>(); tl.add(tt);
		      boolean b=false;
		      if (morpho.has("ne"))
		         b=Boolean.valueOf(morpho.get("ne").toString());
			  an=new Annotation(Boolean.valueOf(morpho.get("aut").toString()),Boolean.valueOf(morpho.get("g").toString()),Boolean.valueOf(morpho.get("c").toString()),b,tl);
			 if(morpho.has("p"))
			  an.setInProgress(Boolean.valueOf(morpho.get("p").toString()));
			  // System.out.println("is Complete" + morpho.get("c").toString());
			  //System.out.println("is Complete" + Boolean.getBoolean(morpho.get("c").toString()));
			 // System.out.println("is Complete" + an.isComplete());
			 }
			 Token to=new Token(jsonobject1.get("Id").toString(),Boolean.valueOf(jsonobject1.get("A").toString()),let);
			 if (jsonobject1.has("M")){
				 to.setMorphoAnnotation(an);
				 //System.out.println("is Complete" + to.getMorphoAnnotation().isComplete());
			 }
			  if(jsonobject1.has("NEId")) {
					
					 to.setNERef(jsonobject1.get("NEId").toString());			 
					    
					 }
			 tokens.add(to);
			// progress=progress+"Read "+ to.getLabel()+"\n";
			 
			if(nrtok==sizeT/2)progressEntry.append("Read "+ nrtok+ "tokens\n");
		//	 progressBar_1.setValue((int) Math.round(lengthPerPercent * i));
			 }
		//progressBar_1.setValue(50);
		
		
	File metaInfo=new File(FileOpenedName.substring(0,FileOpenedName.lastIndexOf('.'))+"Meta.ann");
		if (metaInfo.exists()){
			progressEntry.append("Create Metadata");
			 r = new InputStreamReader(new FileInputStream(FileOpenedName.substring(0,FileOpenedName.lastIndexOf('.'))+"Meta.ann"),"UTF-8");
			 ind=IOUtils.toString(r);
			 ind=ind.replace("\uFEFF", "");
			 if(!ind.isEmpty()) {
			 JSONObject o =new JSONObject(ind);
			
			 metadataDoc=new Metadata(o.getString("ID"),o.getString("NAMEGEZ"),o.getString("NAMEEN"),o.getString("ANNOT"),o.getString("Comm"),o.getString("EDITION"));
			 } 
			 progressEntry.append("Finish creating Metadata");
		}
		   r.close();
		 
        saveFile.setEnabled(true);
		saveAsFile.setEnabled(true);
	//	progrFrame.dispose();
		
		
		// progress=progress+"Create index Tokens\n";
			progressEntry.setText("Create index tokens \n");
			
			createIndexTokens();
			// progress=progress+"Create index words \n";
						progressEntry.append("Create index words \n");
	         createIndexWords();
	       progress=progress+"Create index divisions \n";
				progressEntry.append("Create index divisions \n");
				createIndexDivisions();
	         progressEntry.setText("Create user interface... \n");
	        
	   doActionsOnFile(2);
	   statusLoad.doDefaultCloseAction();
	   isSaved=true;
		saveAsFile.setEnabled(true);
	    stop=true;
	    //System.out.println("thread stopped");
	//	r1.close();	
		//r2.close();	
		//r3.close();	
		} catch(FileNotFoundException ex){
			

       	 JOptionPane.showMessageDialog(new JFrame(), "Could not open file Please close window and restart", "Dialog",
       		        JOptionPane.ERROR_MESSAGE);stop=true;
			
		}
		catch(IOException ex1){}
	        } }}.start();
	         
	}
	}
	 
		
		
		
		
	 public void readNewFidalFile(){
 		
 		//lex=new Lexicon();
		// modelOrig.clear();modelTranslit.clear();
		
 		JFileChooser fc = Utils.getFileChooser();
 		String userhome = System.getProperty("user.home");
 		 fc = new JFileChooser(userhome);
 		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
 		fc.setFileFilter(filter);
 		int returnVal = fc.showOpenDialog(this);	
 		if(returnVal==JFileChooser.APPROVE_OPTION){
 			
 		fileRoot=fc.getSelectedFile();
 		FileOpenedName=fileRoot.getAbsolutePath();
 		int confirm=NegativeDefaultJOptionPane.showConfirmDialog(desk, "South Arabic Script", "Script Type", JOptionPane.YES_NO_OPTION );
 	/*int confirm= JOptionPane.showOptionDialog(desk,
                "South Arabic Script?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);*/
		if (confirm == JOptionPane.YES_OPTION) {typScript=1; typDoc=1;}
		else{
 		 confirm=NegativeDefaultJOptionPane.showConfirmDialog(desk, "Only Consonant Transcription?", "Vocalization Type", JOptionPane.YES_NO_OPTION );
 				 /*JOptionPane.showOptionDialog(desk,
	                "Only Consonant Transcription?",
	                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
	                JOptionPane.QUESTION_MESSAGE, null, null, null);*/
 		if (confirm == JOptionPane.YES_OPTION) typDoc=1;
		}
		//System.out.println("Typ Script "+typScript);
 		//System.out.println("Typ Transcr "+typDoc);
 		new Thread() {
 	          public void run() {
 		long totalLength = fileRoot.length();
 		//System.out.println("lengthfile "+ totalLength);
        //double lengthPerPercent = 50.0 / totalLength;
        long readLength = 0;
 		final ChildFrame statusLoad=new ChildFrame("Reading",Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
		Container cc=statusLoad.getContentPane();
		final JTextArea progressEntry=new JTextArea();
		progressEntry.setFont(etiopicText);
		//progress="Reading...\n";
		progressEntry.setText("Reading...\n");
		JScrollPane jp=new JScrollPane(progressEntry);
		cc.add(jp);
		statusLoad.setLocation(100, 100);
		statusLoad.setSize(50,50);
		statusLoad.pack();
		statusLoad.show();
		 addChild(statusLoad,100,100,300,200);
	        statusLoad.setVisible(true);
		statusLoad.moveToFront();
        
       //progressBar_1.setMinimum(0);
       //progressBar_1.setMaximum(200);
        
        //progressBar_1.setStringPainted(true);
        //progressBar_1.setVisible(true);
       // progrFrame.setLayout(new FlowLayout());
        //progrFrame.getContentPane().add(progressBar_1);
        //progrFrame.pack();
        //addChild(progrFrame,100,100,200,100);
        //progrFrame.moveToFront();
 		Utils.setLastDir(fileRoot);
 		docID =fileRoot.getName();
 		int lineNr=0;
 		String fidal="";
 		 fidalW_Size=750;
 	    fidalH_Size=650;
 	    translitW_Size=750;
 	    translitH_Size=650;
 	    checkedN=true;
 	    structLevels=new HashMap<String,String>();
 	    structLevels.put("1", "");
 	   structLevels.put("2", "");
 	  structLevels.put("3", "");
 	 structLevels.put("4", "");
 	structLevelsBegin=new HashMap<String,String>();
	    structLevelsBegin.put("1", "1");
	   structLevelsBegin.put("2", "1");
	  structLevelsBegin.put("3", "1");
	  structLevelsBegin.put("4", "1");
	  structLevelsBeginName=new HashMap<String,String>();
	    structLevelsBegin.put("1", "");
	   structLevelsBegin.put("2", "");
	  structLevelsBegin.put("3", "");
	  structLevelsBegin.put("4", "");
 		try{
 		InputStreamReader r = new InputStreamReader(new FileInputStream(fileRoot),"UTF-8");		 
 			try{
 			BufferedReader in = new BufferedReader(
 					   new InputStreamReader(
 			                      new FileInputStream(fileRoot), "UTF8"));
 			BufferedInputStream in1= new BufferedInputStream (new FileInputStream(fileRoot));
 			
 					String str;
 					//UpdateThread progr=new UpdateThread();
 					//progr.start();
 					int pos=-1;
 					boolean wr=true;
 					str = in.readLine();
 				
 					 if(typScript==1){
							 fidalWordList.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
							scrollerFidal.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
						 }
 					 else 
 					{
						 fidalWordList.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
						scrollerFidal.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
					 }
 					 while (str != null) {
 						 readLength += str.length();
 						
 				         //  progressBar_1.setValue((int) Math.round(lengthPerPercent * readLength));
 						 //Thread.sleep(500);
 						 str=fidalClean(str);
 						 StringTokenizer st;
 						
 						
 						 if(typScript==0)
 						   st=new StringTokenizer(str," ");
 						 else {st=new StringTokenizer(str,new String(Character.toChars(Character.toCodePoint('\uD802','\uDE7D'))));
 						// System.out.println("ST "+ new String(Character.toChars(Character.toCodePoint('\uD802','\uDE7D'))));
 						 }
 						 boolean newLine=false;
 						// if(!(fidal.replaceAll("\\P{InEthiopic}", "").isEmpty())){
 						// lineNr++;newLine=true;}
 						 if(checkEthiopic(fidal)){
 	 						 lineNr++;newLine=true;
 	 						
 	 						 }
 						 String temp;
 						 WortNode w;
 						 while (st.hasMoreTokens()){
 							 fidal= st.nextToken();
 							//System.out.println("Fidal "+fidal);
 							 //in temp we maintain the original word which may have editorial annotations -brackets 
 							 temp=fidal;
 							 //fidal=fidal.replaceAll("\\P{InEthiopic}", "");
 							 fidal=cleanEthiopic(fidal);
 							// System.out.println(fidal);
 							 if (!fidal.isEmpty()){
 								 char c=fidal.charAt(fidal.length()-1);
 								 if (c=='\u1362') {
 									// randomInt = randomGenerator.nextInt(99999); 
 									
 									 createStructure(fidal.substring(0,fidal.length()-1),"W"+UUID.randomUUID());
 									
 									 //randomInt = randomGenerator.nextInt(99999); 
 									createStructure("\u1362","W"+UUID.randomUUID());
 								 }
 								
 								 else {
 									// randomInt = randomGenerator.nextInt(99999); 
 									createStructure(fidal,"W"+UUID.randomUUID());
 									  
 								 }
 								 
 							 }
 						 }
 						 //System.out.println("finished read");
 						 // = randomGenerator.nextInt(99999);
 							//if(newLine) createStructure(" \u204b","W"+UUID.randomUUID());
 						 //System.out.println(readLength);
 						// if ((readLength >=(totalLength/2))&& wr) {progressEntry.append("Read half of the file \n");wr=false;}
 							str = in.readLine();
 							// if (typScript==1) str=new StringBuilder(str).reverse().toString();
 						 }
 						 pos=pos+1;
 						
 						
 					 
 					// progressBar_1.setValue(50);
 					in.close(); 
 					
 					}catch(Exception ex1){
 						JOptionPane mess=new JOptionPane();
 						mess.setFont(etiopic2);
 						JFrame tempW=new JFrame();
 						tempW.setFont(etiopic2);
 						mess.showMessageDialog(tempW, "<html><p><font size=+1 face=\"Ethiopic Unicode\" >Could not open file.Problem 1 in " +lineNr+" with word "+fidal+" . Wrong Format</font></p></html>", "Dialog",
 		    		        JOptionPane.ERROR_MESSAGE); 
 						//System.out.println("Could not open file.Problem 1 in " +lineNr+" with word "+fidal);
 						System.exit(0);}	
 			               //  MsgBox message = new MsgBox
 			                             // (tempW , "Could not open file.Problem 1 in " +lineNr+"with word "+fidal+" . Wrong Format", false);}
 					//saveAsMenu.setEnabled(true);
 				 
 					  
 					   // fidalWordList.addMouseListener(mouseListener);
 						r.close();
 						isSaved=false;
 						progressEntry.append("Start create index. This may take time...\n");
 						createIndex();
 						progressEntry.append("Finish create index. This may take time...\n");
 						progressEntry.append("Start create words and token indexes. This may take time...\n");
 						createIndexWords();
 						createIndexTokens();
 						progressEntry.append("Finish create words and token indexes. This may take time...\n");
 						progressEntry.append("Create user interface\n");
 						doActionsOnFile(1);
 						statusLoad.doDefaultCloseAction();
 			}catch(FileNotFoundException ex){
 				JOptionPane mess=new JOptionPane();
 				mess.setFont(etiopic1);
 				JFrame tempW=new JFrame();
					tempW.setFont(etiopic1);
                       	mess.showMessageDialog(tempW, "<html><p><font size=+1 face=\"Ethiopic Unicode\" >Could not open file. Problem 2 in " +lineNr+"with word "+fidal +". Please close window and restart</font></p></html>", "Dialog",
 		       		        JOptionPane.ERROR_MESSAGE);}
 		catch(IOException ex1){}
 		}}.start();	
 	}
 	
 }
	 public void createIndexWords(){
		 indexIdWord.clear();
		 for(int i=0;i<words.size();i++){
			 indexIdWord.put(words.get(i).getId(), new Integer(i));
		 }
	 }
	 
	 public void createIndexTokens(){
		 indexIdToken.clear();
		 for(int i=0;i<tokens.size();i++){
			 indexIdToken.put(tokens.get(i).getId(), new Integer(i));
		 }
	 }
	 public void createIndexDivisions(){
		 indexIdDivision.clear();
		 for(int i=0;i<divisions.size();i++){
			 indexIdDivision.put(divisions.get(i).getId(), new Integer(i));
		 }
	 }
	 public void createIndex1(JTextArea t){
			
		 ArrayList<String> ind=new ArrayList<String>();
		 ArrayList<String> indT=new ArrayList<String>();
		
		 index.clear();indexT.clear();
		
		 String s1="";
		 String s2="";
		 t.append("Start index Word Units\n");
 for(int i=0;i<words.size();i++){
	 s1=words.get(i).getFidalInternLabel(typScript,typDoc);
	 s2=words.get(i).getTranslitLabel(typScript);
	 if (!index.containsKey(s1+"*"+s2)){
		 ind=new ArrayList<String>();
		 ind.add(words.get(i).getId());
		 index.put(s1+"*"+s2, ind);
	 }
	 else index.get(s1+"*"+s2).add(words.get(i).getId());
	// 
		 }
		
		 t.append("Indexed Words Units\n");
		 t.append("Start index Tokens....\n");
		 //nr=0;
		 for(int i=0;i<tokens.size();i++){
			 
			 s2=tokens.get(i).getLabel();
			 if (!indexT.containsKey(s2)){
				 indT=new ArrayList<String>();
				 indT.add(tokens.get(i).getId());
				 indexT.put(s2, indT);
			 }
			 else indexT.get(s2).add(tokens.get(i).getId());
				 }
	for(int i=0;i<words.size();i++){	 
		char ch=verifyWordInIndex(i,words.get(i)).charAt(0);
		 if((!Character.isDigit(ch))||(!Character.isDigit(verifyTokensWordIndex(i,words.get(i)).charAt(0)))){
   	        if (!words.get(i).hasErrorNode(typScript,typDoc))words.get(i).setError(1);
   	        else words.get(i).setError(2);
       }
        else if (words.get(i).hasErrorNode(typScript,typDoc))words.get(i).setError(3);
        else words.get(i).setError(0);
	}
	 }
	 
	/* public void createIndex1(JTextArea t){
			
		 ArrayList<String> ind=new ArrayList<String>();
		 ArrayList<String> indT=new ArrayList<String>();
		 ArrayList<WortNode> indexDel=new ArrayList<WortNode>();
		 ArrayList<Token> indexDelT=new ArrayList<Token>();
		 ArrayList<WortNode> wordsCopy1=new ArrayList<WortNode>();
		 ArrayList<Token> tokenCopy=new ArrayList<Token>();
		 index.clear();indexT.clear();
		 for(int i=0;i<words.size();i++){
			 wordsCopy1.add(words.get(i));
		 }
		 for(int i=0;i<tokens.size();i++){
			 tokenCopy.add(tokens.get(i));
		 }
		 String s1="";
		 String s2="";
		// System.out.println("No. words "+words.size());
		 int nr=0;
		 t.append("Start index Word Units\n");
		 String stemp="";
		 while(!wordsCopy1.isEmpty()){
			 ind=new ArrayList<String>();
			 //indT=new ArrayList<String>();
			 indexDel=new ArrayList<WortNode>();
			 s1=wordsCopy1.get(0).getFidalInternLabel(typScript,typDoc);
			 s2=wordsCopy1.get(0).getTranslitLabel(typScript);
			 if (!index.containsKey(s1+"*"+s2)){
				 ind.add(wordsCopy1.get(0).getId());
				// indT.add("T0>"+wordsCopy1.get(0).getId());
				 indexDel.add(wordsCopy1.get(0));
				 for(int j=1;j<wordsCopy1.size();j++){
					 stemp=wordsCopy1.get(j).getFidalInternLabel(typScript,typDoc)+"*"+wordsCopy1.get(j).getTranslitLabel(typScript);
					 if(stemp.equals(s1)){
						 ind.add(wordsCopy1.get(j).getId());
						 //indT.add("T0>"+wordsCopy1.get(j).getId());
						 indexDel.add(wordsCopy1.get(j));
					 }
				 }
				 for(int k=0;k<indexDel.size();k++)
					 wordsCopy1.remove(indexDel.get(k));
				 if(s1.indexOf("S")<0){
				 index.put(s1+"*"+s2, ind);
				 //indexT.put(s2,indT);
				 nr=nr+1;
				//System.out.println(nr +" "+s1+"|"+ind.size()+"** "+s2+"|"+indT.size());
				//System.out.println(nr +" "+s1+"|"+ind.size());
				 }
			 }
		 }
		 wordsCopy1.clear();
		// System.out.println("No. Unique words "+index.size());
		 t.append("Indexed Words Units\n");
		 t.append("Start index Tokens....\n");
		 nr=0;
		 while(!tokenCopy.isEmpty()){
		
			 indT=new ArrayList<String>();
			 indexDelT=new ArrayList<Token>();
			 s2=tokenCopy.get(0).getLabel();
			 if (!indexT.containsKey(s2)){
				 
				 indT.add(tokenCopy.get(0).getId());
				 indexDelT.add(tokenCopy.get(0));
				 for(int j=1;j<tokenCopy.size();j++)
					 if(tokenCopy.get(j).getLabel().equals(s2)){
						
						 indT.add(tokenCopy.get(j).getId());
						 indexDelT.add(tokenCopy.get(j));
					 }
				 for(int k=0;k<indexDelT.size();k++)
					 tokenCopy.remove(indexDelT.get(k));
				 
				 indexT.put(s2,indT);nr=nr+1;
				System.out.println(nr +s2+"|"+indT.size());
				 
			 }
		 }
		 t.append("Indexed tokens");
		 tokenCopy.clear();
		 System.out.println("No. Unique tokens "+indexT.size());
		// Set listindex=index.keySet();
		 /*for (Map.Entry<String, ArrayList<String>> entry : index.entrySet()) {
			    String key = entry.getKey();
			   int noEntries = entry.getValue().size();
			    System.out.println(key+"----"+noEntries);
			}*/
		 
		
	// }
	 
	 public void createIndex(){
		
		 ArrayList<String> ind=new ArrayList<String>();
		 ArrayList<String> indT=new ArrayList<String>();
		 ArrayList<WortNode> indexDel=new ArrayList<WortNode>();
		 String s1="";
		 String s2="";
	//	 System.out.println("No. words "+words.size());
		 int nr=0;
		 while(!wordsCopy.isEmpty()){
			 ind=new ArrayList<String>();
			 indT=new ArrayList<String>();
			 indexDel=new ArrayList<WortNode>();
			 s1=wordsCopy.get(0).getFidalInternLabel(typScript,typDoc);
			 s2=wordsCopy.get(0).getTranslitLabel(typScript);
			 if (!index.containsKey(s1+"*"+s2)){
				 ind.add(wordsCopy.get(0).getId());
				 indT.add("T0>"+wordsCopy.get(0).getId());
				 indexDel.add(wordsCopy.get(0));
				 for(int j=1;j<wordsCopy.size();j++)
					 if(wordsCopy.get(j).getFidalInternLabel(typScript,typDoc).equals(s1)){
						 ind.add(wordsCopy.get(j).getId());
						 indT.add("T0>"+wordsCopy.get(j).getId());
						 indexDel.add(wordsCopy.get(j));
					 }
				 for(int k=0;k<indexDel.size();k++)
					 wordsCopy.remove(indexDel.get(k));
				 if(s1.indexOf("S")<0){
				 index.put(s1+"*"+s2, ind);
				 indexT.put(s2,indT);nr=nr+1;
				//System.out.println(nr +" "+s1+"|"+ind.size()+"** "+s2+"|"+indT.size());
				 }
			 }
		 }
	 
		// Set listindex=index.keySet();
		 /*for (Map.Entry<String, ArrayList<String>> entry : index.entrySet()) {
			    String key = entry.getKey();
			   int noEntries = entry.getValue().size();
			    System.out.println(key+"----"+noEntries);
			}*/
		 wordsCopy.clear();
		// System.out.println("No. Unique words "+index.size());
		
	 }
	 
	 public ArrayList<WortNode> getWords(){
		 return words;
	 }
	 public void initialize(ArrayList<WortNode> words){
   		 modelOrig=new WortListModel(this);
   	    modelTranslit=new TranslitListModel(this);
   	   Iterator<WortNode> it=words.iterator();
   	   showDiv1=true;showDiv2=true;showDiv3=true;showDiv4=true;
   	showDiv1o=true;showDiv2o=true;showDiv3o=true;showDiv4o=true;
   	showDiv1e=true;showDiv2e=true;showDiv3e=true;showDiv4e=true;
   	  int length=words.size();
   	 String fehlern="";
   	  int n=0;
   		while (it.hasNext()){
   			n++;
   			//Sentence sent=it.next();
   			//LinkedList<WortFidal> sentW= sent.getTokens();
   			//Iterator<WortFidal> it1=sentW.iterator();
   			//while(it1.hasNext()){
   				//WortFidal w=it1.next();
   				WortNode w=it.next();
   				//System.out.println("PROCESS "+w);
   				//if(n<50){
   				modelOrig.addElement(w);
   				modelTranslit.addElement(w);
  	          int pos1=indexIdWord.get(w.getId()).intValue();
  		       String fehler="<a href=\"https://"+ pos1+"\">"+w.getTranslitLabel(typScript)+"</a> ";
  		    if(w.getError()>0)
  		       fehlern=errorPane.getText()+"\n"+fehler;
  		      
   				//System.out.println((int) Math.round(50+n*100/length));
   				progressBar_1.setValue((int) Math.round(50+n*100/length));
   			//}
   			
   		}
   	 errorPane.setText(fehlern);
   		fidalWordList.setModel(modelOrig);
   		fidalWordList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
   		fidalWordList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
   		//fidalWordList.setVisibleRowCount(3000);
   		fidalWordList.setVisibleRowCount(0);
   		fidalWordList.setFixedCellWidth(300);
   		if(typScript==0)
   		fidalWordList.setFont(etiopicText1);
   		else fidalWordList.setFont(etiopicText1);
   		System.out.println(last_SIGN);
   		fidalWordList.setSelectedIndex(last_SIGN);
   	 /* ListSelectionListener l = new ListSelectionListener() {
          @Override
          public void valueChanged(ListSelectionEvent e) {
              ((JXList)e.getSource()).invalidateCellSizeCache();
          }
      };*/
    //  fidalWordList.addListSelectionListener(l);
   		translitWordList.setModel(modelTranslit);
   		translitWordList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
   		translitWordList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
   		//translitWordList.setVisibleRowCount(3000);
   		translitWordList.setFont(etiopicText);
   		translitWordList.setVisibleRowCount(0);
   		translitWordList.setFixedCellWidth(300);
   		translitWordList.setSelectedIndex(last_SIGN);
   	}
	 //show main panels
	 
	 public Token getTokenLabel(WortNode w, int i){
		//System.out.println("Proble in "+i+ " node "+ w.getTranslitLabel(typScript)+" fidal "+w.getFidalLabel(typScript, typDoc)+" id "+w.getId());
		if (indexIdToken.get(w.getTokenIds().get(i))!=null){
		Token tok=tokens.get(indexIdToken.get(w.getTokenIds().get(i)).intValue());
		return tok;}
		else {
			//System.out.println("Problem in "+i+ " node "+ w.getTranslitLabel(typScript)+" fidal "+w.getFidalLabel(typScript, typDoc)+" id "+w.getId());
			return null;
		}
	 }
	 public void doActionsOnFile(int flag){
 		
		    if(typScript==1) {typFont=etiopic2; typFontString="Ethiopic Unicode";}
		    else {typFont=etiopic2;typFontString="Ethiopic Unicode";}
			fidalText= new ChildFrame(" Original  " + docID,mainFrameColor,WindowConstants.DO_NOTHING_ON_CLOSE);
			fidalText.putClientProperty("JDesktopPane.dragMode", "outline");
            fidalText.setDoubleBuffered(true);
			fidalText.setClosable(false);
		
			if (typScript==1) fidalText.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			 translitText= new ChildFrame(" Transliterated   " + docID,mainFrameColor,WindowConstants.DO_NOTHING_ON_CLOSE);
			translitText.setClosable(false);	
				 fidalWordList.setFont(typFont);
				fidalWordList.setCellRenderer( new MyListRenderer());
				 translitWordList.setFont(typFont);
				 fidalWordList.isOptimizedDrawingEnabled();
					translitWordList.setCellRenderer( new MyListRenderer() );
					 translitWordList.isOptimizedDrawingEnabled();
					 errorWarning=new ChildFrame("Error", Color.RED,WindowConstants.DISPOSE_ON_CLOSE);
					// errorWarning.setSize(100,50);
						Container cs= errorWarning.getContentPane();
						
						
						cs.setLayout(new GridBagLayout());
						GridBagConstraints gbc=new GridBagConstraints();
						gbc.gridx=0;
						gbc.gridy=0;
						gbc.insets.left=2;
						gbc.insets.top=2;
						gbc.insets.bottom=2;
						gbc.insets.right=2;
						gbc.weighty=100.0;
						gbc.weightx=100.0;
						gbc.fill=GridBagConstraints.BOTH;
						gbc.anchor=GridBagConstraints.NORTHWEST;
					
						 errorPane=new JTextPane();
						errorPane.setSize(100, 50);
						  errorPane.setContentType("text/html");
					        errorPane.setEditorKit(new HTMLEditorKit());
					      errorPane.setEditable(false);
					      String Text="";
					         errorPane = new JEditorPane("text/html",Text);
					         errorPane.setFont(typFont);
					         String bodyRule1 = "body { font-family: " + typFont.getFamily() + "; " +
					                 "font-size: " + typFont.getSize() + "pt; }";
					         ((HTMLDocument)errorPane.getDocument()).getStyleSheet().addRule(bodyRule1);
					         //searchPane.setEditable(false);
					      //   editor.set
					      
					       errorPane.setCaretPosition(0); 
					       MyLinkController controller = new MyLinkController();
					        errorPane.addMouseListener(controller);
					        errorPane.addMouseListener(controller);
					        JScrollPane ep=new JScrollPane(errorPane);
					        cs.add(ep, gbc);
					        errorPane.addHyperlinkListener(new HyperlinkListener() {
							    public void hyperlinkUpdate(HyperlinkEvent e) {
							        if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
							        	String s=e.getURL().toString().substring(7);
							        	int pos=Integer.parseInt(s);
							        	translitWordList.setSelectedIndex(pos);
							        	fidalWordList.setSelectedIndex(pos);
							        	if ((fidalWordList.getSelectedIndex()+20)<fidalWordList.getModel().getSize()){
										     fidalWordList.ensureIndexIsVisible(fidalWordList.getSelectedIndex()+20);
										     translitWordList.ensureIndexIsVisible(translitWordList.getSelectedIndex()+20);
							        	}
										else {
											fidalWordList.ensureIndexIsVisible(fidalWordList.getSelectedIndex()+(fidalWordList.getModel().getSize()-fidalWordList.getSelectedIndex()-1));
											translitWordList.ensureIndexIsVisible(translitWordList.getSelectedIndex()+(translitWordList.getModel().getSize()-translitWordList.getSelectedIndex()-1));
										}
							        	//translitWordList.ensureIndexIsVisible(translitWordList.getSelectedIndex());
							        	//fidalWordList.ensureIndexIsVisible(fidalWordList.getSelectedIndex());
							         //  System.out.println("Event on "+ s);
							        }
							    }
							});
					       /* errorPane.getDocument().addDocumentListener(new DocumentListener(){
					        	public void insertUpdate(DocumentEvent e) {
					               //errorPane.setText(Fehlern);
					            }
					            public void removeUpdate(DocumentEvent e) {
					            	// errorPane.setText(Fehlern);
					            }
					            public void changedUpdate(DocumentEvent e) {
					            	((TranslitListModel) translitWordList.getModel()).update();
					            	((WortListModel) fidalWordList.getModel()).update();
					            }

					        });*/
					        errorWarning.pack();
					       
				 initialize(words);
				/* ListSelectionModel twsm=translitWordList.getSelectionModel();					
				 ListSelectionModel fwsm=fidalWordList.getSelectionModel();
				 fidalWordList.setSelectionModel(translitWordList.getSelectionModel());
				 translitWordList.setSelectionModel(fidalWordList.getSelectionModel());*/
				 
				 
				// JScrollPane scrollerFidal = new JScrollPane(fidalWordList);
				 scrollerFidal.setPreferredSize( new Dimension( fidalW_Size, fidalH_Size ) );
				 JScrollBar sBar1 = scrollerFidal.getVerticalScrollBar();
				scrollerTranslit = new JScrollPane(translitWordList);
				 scrollerTranslit.setPreferredSize( new Dimension( translitW_Size, translitH_Size ) );
				 JScrollBar sBar2 = scrollerTranslit.getVerticalScrollBar();
				 
				 sBar2.setModel(sBar1.getModel());
				    
				 
				 fidalText.add(scrollerFidal);
				 translitText.add(scrollerTranslit);
				 fidalText.pack();
				 translitText.pack();
				 progressBar_1.setValue(205);
				 this.addChild(fidalText,10, 140, fidalW_Size, fidalH_Size);
				 this.addChild(translitText,780, 140, translitW_Size, translitH_Size);
				 fidalText.setVisible(true);
				 fidalText.moveToFront();
				 translitText.setVisible(true);
				 translitText.moveToFront();
				 this.addChild(errorWarning,1400,10,150,200);
			       // desk.add(errorWarning);
			     
			        errorPane.setText( updateErrors());
			        errorPane.revalidate();
   			        errorPane.repaint();
			        errorWarning.setVisible(true);
			        errorWarning.moveToFront();
			        errorWarning.setFocusable(true);
			        errorWarning.toFront();
			        errorWarning.requestFocus();
				 showPosition=new ChildFrame("Show cursor position",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
				 showPositionPageBreak=new ChildFrame("Show page breaks",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
				//	
				// showPBText=new JTextPane();
					//showPBText.setSize(30, 30);
					  //showPBText.setContentType("text/html");
				       //showPBText.setEditorKit(new HTMLEditorKit());
				     //showPBText.setEditable(false);
				      String Text1="";
				      
				         showPBText = new JEditorPane("text/html",Text1);
				         showPBText.setFont(typFont);
				         String bodyRule2 = "body { font-family: " + typFont.getFamily() + "; " +
				                 "font-size: " + typFont.getSize() + "pt; }";
				         ((HTMLDocument)showPBText.getDocument()).getStyleSheet().addRule(bodyRule2);
				         //searchPane.setEditable(false);
				      //   editor.set
				      
				    //   showPBText.setCaretPosition(0); 
				       // controller1 = new MyLinkController();
				       // showPBText.addMouseListener(controller);
				        //showPBText.addMouseListener(controller);
				        //JScrollPane ep1=new JScrollPane(showPBText);
				      //  cs.add(ep, gbc);
				        /*showPBText.addHyperlinkListener(new HyperlinkListener() {
						    public void hyperlinkUpdate(HyperlinkEvent e) {
						        if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
						        	String s=e.getURL().toString().substring(8);
						        	int pos=Integer.parseInt(s);
						        	translitWordList.setSelectedIndex(pos);
						        	fidalWordList.setSelectedIndex(pos);
						        	if ((fidalWordList.getSelectedIndex()+20)<fidalWordList.getModel().getSize()){
									     fidalWordList.ensureIndexIsVisible(fidalWordList.getSelectedIndex()+20);
									     translitWordList.ensureIndexIsVisible(translitWordList.getSelectedIndex()+20);
						        	}
									else {
										fidalWordList.ensureIndexIsVisible(fidalWordList.getSelectedIndex()+(fidalWordList.getModel().getSize()-fidalWordList.getSelectedIndex()-1));
										translitWordList.ensureIndexIsVisible(translitWordList.getSelectedIndex()+(translitWordList.getModel().getSize()-translitWordList.getSelectedIndex()-1));
									}
						        	//translitWordList.ensureIndexIsVisible(translitWordList.getSelectedIndex());
						        	//fidalWordList.ensureIndexIsVisible(fidalWordList.getSelectedIndex());
						         //  System.out.println("Event on "+ s);
						        }
						    }
						});
				 
				 */
				 
				 //
				 showPositionText= new JTextArea (30,30);
					showPositionText.setFont(typFont);
					//showPBText= new JTextArea (30,30);
					//showPBText.setFont(typFont);
					JScrollPane sp=new JScrollPane(showPositionText);
					showPosition.add(sp);
					showPosition.pack(); 
					//sp1=new JScrollPane(showPBText);
					//showPositionPageBreak.add(sp1);
					//showPositionPageBreak.pack(); 
					this.addChild(showPosition,10, 10, 740, 120);
					showPosition.moveToFront();
					//this.addChild(showPositionPageBreak,610, 10, 140, 120);
					//showPositionPageBreak.moveToFront();
				showAnnotation=new ChildFrame("Show current word annotation",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
				showAnnotationText= new JEditorPane("text/html","");
				  String bodyRule = "body { font-family: " + typFont.getFamily() + "; " +
			                 "font-size: " + typFont.getSize() + "pt; }";
			         ((HTMLDocument)showAnnotationText.getDocument()).getStyleSheet().addRule(bodyRule);
				showAnnotationText.setFont(typFont);
					JScrollPane sa=new JScrollPane(showAnnotationText);
					showAnnotation.add(sa);
					showAnnotation.addInternalFrameListener(new InternalFrameListener() {
						public void internalFrameActivated(InternalFrameEvent event) {
						                            		}
						public void internalFrameClosed(InternalFrameEvent event) {
							showAnnotationX=showAnnotation.getX();
							showAnnotationY=showAnnotation.getY();
						}
						public void internalFrameOpened(InternalFrameEvent event) {
							
						}
						public void internalFrameClosing(InternalFrameEvent event) {
							showAnnotationX=showAnnotation.getX();
							showAnnotationY=showAnnotation.getY();
							
						}
						public void internalFrameDeiconified(InternalFrameEvent event) {
						}
						public void internalFrameDeactivated(InternalFrameEvent event) {
							showAnnotationX=showAnnotation.getX();
							showAnnotationY=showAnnotation.getY();

						}
						public void internalFrameIconified(InternalFrameEvent event) {
							showAnnotationX=showAnnotation.getX();
							showAnnotationY=showAnnotation.getY();
						}
						});

					showAnnotation.pack(); 
					this.addChild(showAnnotation,showAnnotationX, showAnnotationY, 730, 120);
					showPosition.moveToFront();
					showPositionPageBreak.moveToFront();
					//
					
					
					//
		          activateMenus(flag);
		          if(checkedN) verifyNouns.setEnabled(false);
		          else verifyNouns.setEnabled(true);
		          // tooltip
		         fidalWordList.addListSelectionListener(new ListSelectionListener(){
		        	  public void valueChanged(ListSelectionEvent ke){
		        	    //if(ke.getKeyCode() == KeyEvent.VK_DOWN)
		        	    //{
		        	     // ke.consume();
		        	      int index = fidalWordList.getSelectedIndex();
		        	      translitWordList.setSelectedIndex(index);
		        	      scrollerTranslit.getVerticalScrollBar().setCursor(  scrollerFidal.getVerticalScrollBar().getCursor());
		        	      int inde=fidalWordList.getSelectedIndex();
		        	      if(index>=0){
                   	   WortNode w=words.get(index);
                   	  // translitWordList.setToolTipText(((WortNode)translitWordList.getModel().getElementAt(inde)).getCooment());
                   	   String annotation ="";
                   	  // for (int i=1;i<=4;i++){
                   		 //  if 
                   	   //}
                	   for(int i=0; i<w.getFidalChildren().size();i++){
                     		 if (w.getFidalChildren().get(i).getEdAnnot()!=null){
                     			 if(w.getFidalChildren().get(i).getEdAnnot().getSpecificTag("lb")!=null)
                     				 annotation=annotation+"LB "+w.getFidalChildren().get(i).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).getValue()+"";
                     			
                     		 }
                     		 }
                	   annotation=annotation+"\n";
                	  
                	   if (w.getFidalChildren().get(0).getEdAnnot()!=null){
               			 if(w.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb")!=null)
               			 {
               				for (int k=0;k<w.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().size();k++) {
               				 if( w.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(0,1).equals("-"))
               				 annotation="PB "+
               						 Jsoup.parse( w.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(1)).text()+annotation;
               			
               			 else {
               				annotation="PB "+
               					 Jsoup.parse( w.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue()).text()+annotation;
               				// annotation="PB "+
                           		//	 w.getFidalChildren().get(w.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(0).getValue().substring(1)+annotation;
               			 }
               				}
               			annotation=annotation+"\n";
               			 }
               		 }
                	   
                		 if (w.getFidalChildren().get(w.getFidalChildren().size()-1).getEdAnnot()!=null){
                			
                 			 if(w.getFidalChildren().get(w.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb")!=null)
                 			 {
                 				 for (int k=0;k<w.getFidalChildren().get(w.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().size();k++) {
                 				 if( w.getFidalChildren().get(w.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(0,1).equals("+"))
                 				 annotation=annotation+"PB "+
                 						 Jsoup.parse( w.getFidalChildren().get(w.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(1)).text();
                 			 //}
                 			 else {
                 				annotation=annotation+"PB "+
                 						 Jsoup.parse( w.getFidalChildren().get(w.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue()).text();
                 				// annotation="PB "+
                             		//	 w.getFidalChildren().get(w.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(0).getValue().substring(1)+annotation;
                 			 }
                 			 } 
                 	annotation=annotation+"\n";
                 			 }
                 		 }
                		// annotation=annotation+"\n";
                   	   if (w.getStrukturIds()!=null){
                   		   
                   		   for (int i=1;i<=4;i++){
                   			   if (w.getStrukturIds().size()>0){
                   			   if(! w.getStrukturLevel(i).isEmpty()){
                   				if( w.getStrukturLevel(i)!=null){
                       				Division d=divisions.get(indexIdDivision.get(w.getStrukturLevel(i)).intValue()).copyDivision();
                       				Division dorig=divisions.get(indexIdDivision.get(w.getStrukturLevel(i)).intValue());
                       				String s="";
                       				String sgiven="";
                       			int nrtabs=0;
                       				while(!d.getParent().isEmpty()) {
                       					s=d.getInternalNr()+"."+s;
                       					sgiven=d.getNr()+"."+sgiven;
                       					d=divisions.get(indexIdDivision.get(d.getParent()).intValue()).copyDivision();
                       					nrtabs=nrtabs+1;
                       					
                       				}
                   				if (nrtabs==1) annotation=annotation+"\t";
                   				else if (nrtabs==2) annotation=annotation+"\t\t";
                   				if (nrtabs==3) annotation=annotation+"\t\t\t";
                   				   annotation=annotation + "Level "+ structLevels.get(""+i)+": ";
                   				// System.out.println(annotation);
                   				//System.out.println(w.getStrukturIds().get(""+i));
                   				//System.out.println(findDivisionId(w.getStrukturIds().get(""+i)).getValueForName("name"));
                   				
                       				s=d.getInternalNr()+"."+s;
                       				sgiven=d.getNr()+"."+sgiven;
                       				
                   				  if( findDivisionId(w.getStrukturLevel(i)).getName()!=null){
                   					
                   				//	System.out.println(findDivisionId(w.getStrukturIds().get(""+i)).getId());
                   					  annotation=annotation+ " "+divisions.get(indexIdDivision.get(w.getStrukturLevel(i)).intValue()).getName();
                   					
                   				  }
                   				 // else System.out.println("null");
                   			   
                   			
                   				
             					  annotation=annotation+ " "+sgiven.substring(0, sgiven.length()-1)+ " (Internal Nr.: "+s.substring(0, s.length()-1)+")\n";
             					// System.out.println(annotation);
                   			}
                   			annotation=annotation+"\n";  }}
                   			  
                   			 }
                   		   
                   			   
                   	  }
             //
                   	   String annotation1="";
                   	String annotation2="";
                   	int npb=0;
         
              /*     for(int l=0;l<words.size();l++) {
                	   System.out.println(l);
                	   WortNode w1=words.get(l);
                	   annotation1="";
                	 
                	  if(l!=index) {
                		  boolean foundpb=false;
                   	   if (w1.getFidalChildren().get(0).getEdAnnot()!=null){
                   		
                 			 if(w1.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb")!=null)
                 			 {
                 				if (l<index) npb=npb+1;
                 				//foundpb=true;
                 				for (int k=0;k<w1.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().size();k++) {
                 				 if( w1.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(0,1).equals("-"))
                 				 {if (l<index) npb=npb+1;
                 					 annotation1="PB "+
                 						 Jsoup.parse( w1.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(1)).text()+annotation1;
                 				 }
                 			 else {
                 				if (l<index) npb=npb+1;
                 				annotation1="PB "+
                 					 Jsoup.parse( w1.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue()).text()+annotation1;
                 				// annotation="PB "+
                             		//	 w.getFidalChildren().get(w.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(0).getValue().substring(1)+annotation;
                 			 }
                 				}
                 			annotation1=annotation1+"<br/>";if (l<index) npb=npb+1;
                 			 }
                 		 }
                  	   
                  		 if (w1.getFidalChildren().get(w1.getFidalChildren().size()-1).getEdAnnot()!=null){
                  			
                   			 if(w1.getFidalChildren().get(w1.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb")!=null)
                   			 {
                   				 if(l<index) {npb=npb+1;}
                   				 for (int k=0;k<w1.getFidalChildren().get(w1.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().size();k++) {
                   				 if( w1.getFidalChildren().get(w1.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(0,1).equals("+"))
                   				 { annotation1=annotation1+"PB "+
                   						 Jsoup.parse( w1.getFidalChildren().get(w1.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(1)).text();
                   				if (l<index) npb=npb+1;}
                   			 else {
                   				if (l<index) npb=npb+1;
                   				annotation1=annotation1+"PB "+
                   						 Jsoup.parse( w1.getFidalChildren().get(w1.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue()).text();
                   				// annotation="PB "+
                               		//	 w.getFidalChildren().get(w.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(0).getValue().substring(1)+annotation;
                   			 }
                   			 } 
                   	annotation1=annotation1+"<br/>";if (l<index) npb=npb+1;
                   			 }
                   		 }
                  		 System.out.println("Annot 1 "+annotation1);
                  		 if(!annotation1.isEmpty())
                  		            annotation2= annotation2+"<a href=\"https://"+ indexIdWord.get(w1.getId()).intValue()+"\">"+w1.getFidalLabel(typScript, typDoc)+"</a>"+"<br/>"+annotation1+"---------------<br/>";
                   }
                	 
                	   else {
                		   boolean foundpb=false;
                		   if (w1.getFidalChildren().get(0).getEdAnnot()!=null){
                   			 if(w1.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb")!=null)
                   			 {
                   				 npb=npb+1;
                   				for (int k=0;k<w1.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().size();k++) {
                   				 if( w1.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(0,1).equals("-"))
                   				 { annotation1="PB "+
                   						 Jsoup.parse( w1.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(1)).text()+annotation1;
                   			npb=npb+1;}
                   			 else {npb=npb+1;
                   				annotation1="PB "+
                   					 Jsoup.parse( w1.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue()).text()+annotation1;
                   				// annotation="PB "+
                               		//	 w.getFidalChildren().get(w.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(0).getValue().substring(1)+annotation;
                   			 }
                   				}
                   			annotation1=annotation1+"<br/>"; npb=npb+1;
                   			 }
                   		 }
                    	   
                    		 if (w1.getFidalChildren().get(w1.getFidalChildren().size()-1).getEdAnnot()!=null){
                    			
                     			 if(w1.getFidalChildren().get(w1.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb")!=null)
                     			 {
                     				 if(!foundpb) npb=npb+1;
                     				 for (int k=0;k<w1.getFidalChildren().get(w1.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().size();k++) {
                     				 if( w1.getFidalChildren().get(w1.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(0,1).equals("+"))
                     				 annotation1=annotation1+"PB "+
                     						 Jsoup.parse( w1.getFidalChildren().get(w1.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(1)).text();
                     			 //}
                     			 else {
                     				annotation1=annotation1+"PB "+
                     						 Jsoup.parse( w1.getFidalChildren().get(w1.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue()).text();
                     				// annotation="PB "+
                                 		//	 w.getFidalChildren().get(w.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(0).getValue().substring(1)+annotation;
                     			 }
                     			 } 
                     	annotation1=annotation1+"<br/>";
                     			 }
                     		 }
                    		 System.out.println("Annot 1 "+annotation1);
                    		 if(!annotation1.isEmpty())
                    		            annotation2= annotation2+"<a href=\"https://"+ indexIdWord.get(w1.getId()).intValue()+"\">"+w1.getFidalLabel(typScript, typDoc)+"</a>"+"<br/>"+"<font color=\"red\"><b>**</b></font>"+annotation1+"---------------<br/>";
                	   }
                	   
                   }   */
                   	
                  // 	   
                   	 
                   	  // if(w.getFlagMorphoAnnot()>0) showPositionText.setText(w.getTextMorphoAnnot());
                   	   showPositionText.setText(annotation);
                   	   showPositionText.setCaretPosition(0);
                   	
                   //	 showPBText.setText(annotation2);
                 //  	 System.out.println("no words with pb up to word " +npb);
                     //showPBText.setCaretPosition(npb);
                    // showPBText.setCaretColor(Color.ORANGE);
                 //  sp1.getVerticalScrollBar().setValue(npb);
                 	  // showPBText.setCaretPosition(0);
                   	   
		        	   } 
		        	    }});
		          
		          
		          translitWordList.addListSelectionListener(new ListSelectionListener(){
		        	  public void valueChanged(ListSelectionEvent ke){
		        	    //if(ke.getKeyCode() == KeyEvent.VK_DOWN)
		        	    //{
		        	     // ke.consume();
		        	      int index = translitWordList.getSelectedIndex();
		        	      int inde=translitWordList.getSelectedIndex();
		        	      fidalWordList.setSelectedIndex(index);
		        	      if(index>=0){
                   	   WortNode w=words.get(index);
                   	  // translitWordList.setToolTipText(((WortNode)translitWordList.getModel().getElementAt(inde)).getCooment());
                   	   String annotation ="";
                   	   for(int i=0; i<w.getTokenIds().size();i++){
                   		   Token temp=tokens.get(indexIdToken.get(w.getTokenIds().get(i)).intValue());
                   		   if (temp.getMorphoAnnotation()!=null){
                   			   annotation=annotation+temp.getLabel()+"<br/>";
                   			   Annotation atemp=temp.getMorphoAnnotation();
                   			   String nes="";
                   			   if (atemp.isNEPart()) nes=" <font color='blue'> is NE part</font>";
                   			   annotation=annotation+atemp.getListTag().get(0).getName()+" "+atemp.getEntireMorphoValue1()+nes+"<br/>";
                   			 //  System.out.println("Annotation "+ annotation);
                   			//translitWordList.setSelectedIndex(index);
                   		   }
                   	   }
                   	   
                   	  // if(w.getFlagMorphoAnnot()>0) showPositionText.setText(w.getTextMorphoAnnot());
                   	   showAnnotationText.setText(annotation);
                   	   showAnnotationText.setCaretPosition(0);
                   	   
		        	   } 
		        	    }});
		         translitWordList.addMouseMotionListener(new MouseMotionAdapter() {
		              @Override
		              public void mouseMoved(MouseEvent e) {
		                 // JList l = (JList)e.getSource();
		                //  ListModel m = l.getModel();
		                  int index = translitWordList.getSelectedIndex();
		                  UIManager.put("ToolTip.font",
		                		  new FontUIResource("Ethiopic Unicode", Font.BOLD, 18));
		                  String s=null;
		                  if (index>0)
		                  if(!Jsoup.parse(words.get(index).getCooment()).text().isEmpty()) s="<html>"+words.get(index).getCooment()+"</html>";
		                  else s=null;
		                  if( index>-1 ) {
		                	 /// translitWordList.setToolTipText(""+Jsoup.parse(words.get(index).getCooment()).text());
		                	 // translitWordList.setToolTipText("<html>"+words.get(index).getCooment()+"</html>");
		                	  translitWordList.setToolTipText(s);
		                  }
		              }
		          });
		         //
		         fidalWordList.addMouseListener(new MouseAdapter() {
					   public void mousePressed(MouseEvent e)
			    		{
			    			if (e.isPopupTrigger())
			    				popupTriggered(e);
			    		}

			    		@Override
			    		public void mouseReleased(MouseEvent e)
			    		{
			    			if (e.isPopupTrigger())
			    				popupTriggered(e);
			    		}
			    		
			    		 public void popupTriggered(MouseEvent ev) {
							   
							  //public void mousePressed(MouseEvent ev) {
						            if (ev.isPopupTrigger()) {
						            	 final int [] indexes= fidalWordList.getSelectedIndices();
						            	 final WortNode wselect = words.get(indexes[0]);
						            	 isSaved=false;
						         		saveFile.setEnabled(true);
						           if(!wselect.getFidalLabel(typScript,typDoc).equals("\u204b")){ 	 
						            	final JPopupMenu popupMenu = new JPopupMenu();
						             	final ArrayList<JMenuItem> menuFLetters=new ArrayList<JMenuItem>();
						             	final ArrayList<JMenuItem> menuFLetters1=new ArrayList<JMenuItem>();
						             	final ArrayList<JMenuItem> menuFLetters2=new ArrayList<JMenuItem>();
						             	final ArrayList<JMenuItem> menuFLetters2_1=new ArrayList<JMenuItem>();
						             	final ArrayList<JMenuItem> menuFLetters3=new ArrayList<JMenuItem>();
								        final JMenu edition=new JMenu("Edition") ; 
								        final JMenuItem vizStructure=new JMenuItem("Vizualize Structure");
								        final JMenu ending=new JMenu("Insert Modify Fidal Separator") ;  
					                	final JMenu lineBreak=new JMenu("Line Break");
					                	final JMenu pageBreak=new JMenu("Page Break");
					                	final JMenuItem insertLBBefore=new JMenuItem("Insert LB Before");
					                	final JMenuItem removeLBBefore=new JMenuItem("Remove LB Before");
					                	final JMenuItem insertLineBreak =new JMenu("Insert LB");
					                	final JMenuItem insertPageBreak =new JMenuItem("Insert PB");
					                	final JMenuItem modifyPageBreak =new JMenuItem("Modify PB(s)");
					                	final JMenuItem deletePageBreak =new JMenuItem("Delete PB(s)");
					                	final JMenu specDiv=new JMenu("Special Divisions");
					                	final JMenuItem insertSpecDiv =new JMenuItem("Insert Special Div.");
					                	final JMenuItem modifySpecDiv =new JMenuItem("Modify Special Div(s)");
					                	final JMenuItem deleteSpecDiv =new JMenuItem("Delete Special Div(s)");
					                	if( (wselect.getFidalChildren().get(wselect.getFidalChildren().size()-1).getEdAnnot()!=null)||(wselect.getFidalChildren().get(0).getEdAnnot()!=null)) {
					                		if (wselect.getFidalChildren().get(wselect.getFidalChildren().size()-1).getEdAnnot()!=null){
		                                      if( (wselect.getFidalChildren().get(wselect.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb")!=null)){
		                                    	  modifyPageBreak.setEnabled(true);
		                                    	  deletePageBreak.setEnabled(true);
		                                      }
		                                      else {
		                                    	  modifyPageBreak.setEnabled(false);
		                                    	  deletePageBreak.setEnabled(false);
		                                      }
						           }
					                		else if (wselect.getFidalChildren().get(0).getEdAnnot()!=null){
			                                      if( (wselect.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb")!=null)){
			                                    	  modifyPageBreak.setEnabled(true);
			                                    	  deletePageBreak.setEnabled(true);
			                                      }
			                                      else {
			                                    	  modifyPageBreak.setEnabled(false);
			                                    	  deletePageBreak.setEnabled(false);
			                                      }
					                		}
					                		
						           }
					                	else {
					                		modifyPageBreak.setEnabled(false);
	                                    	  deletePageBreak.setEnabled(false);
					                	}
					                	//
					                	if (wselect.getSpecDivs()!=null) {
		                                     
		                                    	  modifySpecDiv.setEnabled(true);
		                                    	  deleteSpecDiv.setEnabled(true);
		                                      }
		                                      else {
		                                    	  modifySpecDiv.setEnabled(false);
		                                    	  deleteSpecDiv.setEnabled(false);
		                                      }
					              
					                	
					                	pageBreak.add(insertPageBreak);pageBreak.add(modifyPageBreak);pageBreak.add(deletePageBreak);
					                	specDiv.add(insertSpecDiv);specDiv.add(modifySpecDiv);specDiv.add(deleteSpecDiv);
		                                  	final JMenu deleteLineBreak =new JMenu("Delete LB");
					                	final JMenu insertLineBreak1 =new JMenu();
					                	insertLineBreak1.setFont(etiopic1);
					                	insertLineBreak1.setText("Insert LB before "+"\u1361");
					                	popupMenu.add(vizStructure);
					                	 final JMenu submenuAnnotStruct =new JMenu(" Text Structure");
					                	 if (hasStructSpec) submenuAnnotStruct.setEnabled(true);
					                	 else submenuAnnotStruct.setEnabled(false);
                                           final JMenu submenuAnnotStructDivisions =new JMenu(" Divisions");
                                           final JMenu submenuDelStructDivisions =new JMenu(" Delete Division");
                                           final JMenu submenuEditStructDivisions =new JMenu(" Modify Division Information");
                                       //    System.out.println("No struktur "+wselect.getStrukturIds().size());
                                           final JMenuItem [] edDiv=new JMenuItem[wselect.getStrukturIds().size()];
                                        if(wselect.getStrukturIds().size()>0) {
                                         	  //submenuDelStructDivisions.setEnabled(false);
                                           // else {
                                         	 //  submenuDelStructDivisions.setEnabled(true);
                                         	//   final JMenuItem [] delDivision= new JMenuItem[wselect.getStrukturIds().size()];
                                        	
                                         	   for(int i=0;i<wselect.getStrukturIds().size();i++) {
                                         		   posDiv1=i;
                                       		    did1=wselect.getStrukturIds().get(posDiv1);
                                       		
                                       		   Division dd=divisions.get(indexIdDivision.get(did1).intValue());
                                       		Division dd1=divisions.get(indexIdDivision.get(did1).intValue());
                                       		 String sd="";
                               				while(!dd1.getParent().isEmpty()) {
                               					sd=dd1.getInternalNr()+"."+sd;
                               					//if(divisions.get(indexIdDivision.get(dd.getParent()).intValue()).getLevel()-dd.getLevel()>1)
                               					//sd="0."+sd;
                               						dd1=divisions.get(indexIdDivision.get(dd.getParent()).intValue()).copyDivision();
                               					
                               					
                               				}
                               				sd=dd1.getInternalNr()+"."+sd;
                               				sd=sd.substring(0, sd.length()-1);
                                       		  edDiv[i]=new JMenuItem("Div "+sd);
                                  		 
                                 		   submenuEditStructDivisions.add(edDiv[i]);
                                 		   edDiv[i].addActionListener(new ActionListener() {
                                 			   public void actionPerformed(ActionEvent e) {
                                 				 Division dd1=divisions.get(indexIdDivision.get(did1).intValue());
                                 				 String sd="";
                                    				while(!dd1.getParent().isEmpty()) {
                                    					sd=dd1.getInternalNr()+"."+sd;
                                    				//if(divisions.get(indexIdDivision.get(dd.getParent()).intValue()).getLevel()>1)
                                           				//	sd="0."+sd;
                                    					dd1=divisions.get(indexIdDivision.get(dd.getParent()).intValue()).copyDivision();
                                    					
                                    					
                                    				}
                                    				sd=dd1.getInternalNr()+"."+sd;
                                    				sd=sd.substring(0, sd.length()-1);
                                    				//dd=divisions.get(indexIdDivision.get(did1).intValue());
                                 				  JLabel startP =new JLabel("First GraphicUnit");
                                 				 startP.setEnabled(false);
							                		JLabel endP=new JLabel("Last GraphicUnit");
							                		endP.setEnabled(false);
							                		JTextField startW=new JTextField(15);
							                		startW.setEditable(false);
							                		startW.setFont(etiopic1);
							                		startW.setText(words.get(indexIdWord.get(dd.getWbegin()).intValue()).getFidalLabel(typDoc, typScript));
							                		startSel=indexIdWord.get(dd.getWbegin()).intValue();
							                		endSel=indexIdWord.get(dd.getWend()).intValue();
							                		fidalWordList.setSelectionInterval(startSel, endSel);
							                		
							                		JTextField endW=new JTextField(15);
							                		endW.setEditable(false);
							                		endW.setFont(etiopic1);
							                		endW.setText(words.get(indexIdWord.get(dd.getWend()).intValue()).getFidalLabel(typDoc, typScript));
							                		JLabel divTL=new JLabel("Division Type");
							                		JComboBox divT=new JComboBox();
							                		divT.addItem("-");
							                		for (int j=1;j<=structLevels.size();j++) {
							                			if(!structLevels.get(""+j).isEmpty())
							                		divT.addItem(structLevels.get(""+j));
							                		}
							                		divT.setSelectedIndex(dd.getLevel()); divT.setEnabled(false);
							                		/*if(( noStruct>=2)&&(structLevels.get("2").length()>0)) divT.addItem(structLevels.get("2"));
							                		if(( noStruct>=3)&&(structLevels.get("3").length()>0)) divT.addItem(structLevels.get("3"));
							                		if(( noStruct>=4)&&(structLevels.get("4").length()>0)) divT.addItem(structLevels.get("4"));*/
							                		JLabel divNL=new JLabel("Division Name");
							                		JTextField divN=new JTextField(10);
							                		divN.setEditable(true);divN.setEnabled(true); divN.setText(dd.getName());
							                		JLabel divZL=new JLabel("Division Internal Number");
							                		JTextField divZ=new JTextField(10);divZ.setEditable(false);
							                		divZ.setEnabled(false); divZ.setText(sd);
							                	//	divZ.setText(calculateDivisionNumber(fidalWordList.getSelectedIndex()));
							                		
							                		JLabel divZEL=new JLabel("Division Number");
							                		JTextField divZE=new JTextField(10);
							                		divZE.setEditable(true);divZE.setEnabled(true);
							                		if (!dd.getChange()) divZE.setText(sd);
                                 		               else divZE.setText(dd.getNr());
							                		JLabel divSTL=new JLabel("Style");
							                		JComboBox divST=new JComboBox();
							                		divST.setMaximumRowCount(7);
							                		divST.addItem("-");
							                		divST.addItem("Bible");
							                		divST.addItem("Hagiography");
							                		divST.addItem("Chronicles");
							                		divST.addItem("Theology");
							                		divST.addItem("Apocrypha Pseudoepigrapha");
							                		divST.addItem("Amharic Literature");
							                		divST.addItem("Apocrypha");
							                		divST.addItem("Pseudoepigrapha");
							                		divST.addItem("Miracle");
							                		divST.addItem("New Testament");
							                		divST.addItem("Old Testament");
							                		divST.addItem("Beta Esrael Literature");
							                		divST.addItem("Biography");
							                		divST.addItem("Canon Law");
							                		divST.addItem("Commentary");
							                		divST.addItem("Christian Literature");
							                		divST.addItem("Chronography");
							                		divST.addItem("Crucifixion");
							                		divST.addItem("Fiction");
							                		divST.addItem("History and Historiography");
							                		divST.addItem("Homily");
							                		divST.addItem("Islamic Literature");
							                		divST.addItem("Koran");
							                		divST.addItem("Legal Document");
							                		divST.addItem("Liturgy");
							                		divST.addItem("Chants");
							                		divST.addItem("Lectionary");
							                		divST.addItem("Missal");
							                		divST.addItem("Rituals");
							                		divST.addItem("Rituals an Rites");
							                		divST.addItem("Christian Literature");
							                		divST.addItem("Tigrinya Literature");
							                		divST.addItem("Translation");
							                		divST.addItem("Vocabulary");
							                		divST.addItem("Magic");
							                		divST.addItem("Medicine");
							                		divST.addItem("Other");
							                		divST.addItem("Philosophy");
							                		divST.addItem("Poetry");
							                		divST.addItem("Prayers");
							                		divST.addItem("Religion");
							                		divST.addItem("Christian Content");
							                		divST.setEnabled(true); divST.setSelectedItem(dd.getGenre());
							                		JLabel divAutL=new JLabel("Creator");
							                		JComboBox divAut=new JComboBox();
							                		divAut.addItem("Edition");
							                		divAut.addItem("Annotator");
							                        divAut.setEnabled(true); divAut.setSelectedItem(dd.getCreator());
							                		JLabel commL=new JLabel("Comments");
							                		final HTMLEditorPane hp =new HTMLEditorPane();
							                		//hp.setText(words.get(position).getCooment());
							                		//System.out.println("Comment"+ Jsoup.parse(hp.getText()).text()+"***");
							                		
							                		if(Jsoup.parse(hp.getText()).text().isEmpty())
							                			hp.setText(dd.getComment());
							                		
							                		hp.setEnabled(true);
							                		final ChildFrame strAnnFrame=new ChildFrame("Modify Division Information",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
							                		Container strAnn=strAnnFrame.getContentPane();
							                		strAnn.setLayout(new GridBagLayout());
							                		GridBagConstraints gbc=new GridBagConstraints();
							                		gbc.gridx=0;
							                		gbc.gridy=0;
							                		gbc.weightx=100;
							                		gbc.weighty=100;
							                		gbc.insets.left=2;
							                		gbc.insets.right=2;
							                		gbc.insets.top=2;
							                		gbc.gridwidth=2;
							                		gbc.anchor=GridBagConstraints.NORTHWEST;
							                		gbc.insets.bottom=2;
							                		strAnn.add(startP,gbc); gbc.gridx=1; strAnn.add(startW,gbc);
							                		gbc.gridx=0; gbc.gridy=1;
							                		strAnn.add(endP,gbc);gbc.gridx=1; strAnn.add(endW,gbc);
							                		gbc.gridx=0; gbc.gridy=2;
							                		strAnn.add(divTL,gbc);gbc.gridx=1; strAnn.add(divT,gbc);
							                		gbc.gridx=0; gbc.gridy=3;
							                		strAnn.add(divNL,gbc);gbc.gridx=1; strAnn.add(divN,gbc);
							                		gbc.gridx=0; gbc.gridy=4;
							                		strAnn.add(divZL,gbc);gbc.gridx=1; strAnn.add(divZ,gbc);
							                		gbc.gridx=0;  gbc.gridy=5;
							                		strAnn.add(divZEL,gbc);gbc.gridx=1; strAnn.add(divZE,gbc);
							                		gbc.gridx=0; gbc.gridy=6;
							                		strAnn.add(divSTL,gbc);gbc.gridx=1; strAnn.add(divST,gbc);
							                		gbc.gridx=0; gbc.gridy=7;
							                		strAnn.add(divAutL,gbc);gbc.gridx=1; strAnn.add(divAut,gbc);
							                		gbc.gridwidth=2;gbc.gridx=0; gbc.gridy=8;
							                		strAnn.add(commL,gbc); gbc.gridy=9;
							                		strAnn.add(hp,gbc);
							                		JButton okB=new JButton("OK"); JButton cancelB=new JButton("Cancel");
							                		okB.setEnabled(true);
							                		gbc.gridwidth=1;gbc.gridx=0; gbc.gridy=10;
							                		gbc.anchor=GridBagConstraints.CENTER;
							                		strAnn.add(okB,gbc); gbc.gridx=1;strAnn.add(cancelB,gbc);
							                		
							                		cancelB.addActionListener(new ActionListener(){
							                			public void actionPerformed(ActionEvent e){
							                				strAnnFrame.doDefaultCloseAction();
							                			}
							                			});
							                		okB.addActionListener(new ActionListener(){
							                			public void actionPerformed(ActionEvent e){
							                			//	divisionsBackup.clear();
							                				//for(int i=0;i<divisions.size();i++)
							                					//divisionsBackup.add(divisions.get(i).copyDivision());
							                				undoMenu.setEnabled(true);
							                				 Division dd=divisions.get(indexIdDivision.get(did1).intValue());
							                				//String id= divT.getSelectedIndex()+"D"+UUID.randomUUID();
							                				// dd.setWbegin(words.get(startSel).getId());
							                				 //dd.setWend(words.get(endSel).getId());
							                				String nrdivtot=divZ.getText();
							                				
							                				String nrdiv=nrdivtot.substring(nrdivtot.lastIndexOf('.')+1);
							                				int nrd;
							                				if(nrdiv.isEmpty()) nrd=Integer.parseInt(nrdivtot);
							                				else nrd=Integer.parseInt(nrdiv);
							                				boolean change1=true;
							                				if(divZ.getText().equals(divZE.getText())) change1=false;
							                				
							                				dd.setName(divN.getText());
							                				if(divZE.getText().equals(divZ.getText()))
							                				{     dd.setChangeNr(false);}
							                				else {dd.setChangeNr(true); dd.setNr(divZE.getText());}
							                			    dd.setComment(hp.getText());
							                			    dd.setCreator(divAut.getSelectedItem().toString());
							                			    dd.setGenre(divST.getSelectedItem().toString());
							                			 //   dd.setChangeNr(change1);
							                			    //if (!dd.getNr().equals(dd.getInternalNr())) dd.setChangeNr(true); 
							                			    //else dd.setChangeNr(false); 
							                			    divisions.set(indexIdDivision.get(did1).intValue(), dd);
							                			    strAnnFrame.doDefaultCloseAction();
							                			
							                			}
							                		});
							                		addChild(strAnnFrame,950,30,250,500);
							                		strAnnFrame.pack();
					        			               strAnnFrame.setVisible(true);
					        			              strAnnFrame.moveToFront();
                                 			   }
                                 		   });
                                       		   
                                         	   }
                                           }  

                                           
                                          if(wselect.getStrukturIds().size()>0) {
                                        	  //submenuDelStructDivisions.setEnabled(false);
                                          // else {
                                        	 //  submenuDelStructDivisions.setEnabled(true);
                                        	   final JMenuItem [] delDivision= new JMenuItem[wselect.getStrukturIds().size()];
                                        	   for(int i=0;i<wselect.getStrukturIds().size();i++) {
                                        		  
                                        		   
                                        		    int posDiv1=i;
                                        		String did1=wselect.getStrukturIds().get(posDiv1);
                                        		   Division dd=divisions.get(indexIdDivision.get(did1).intValue());
                                        		   Division dd1=divisions.get(indexIdDivision.get(did1).intValue());
                                        		   String sd="";
                                      				while(!dd1.getParent().isEmpty()) {
                                      					sd=dd1.getInternalNr()+"."+sd;
                                      					
                                      					dd1=divisions.get(indexIdDivision.get(dd1.getParent()).intValue()).copyDivision();
                                      					
                                      					
                                      				}
                                      				sd=dd1.getInternalNr()+"."+sd;
                                      				sd=sd.substring(0, sd.length()-1);
                                        		 delDivision[i]=new JMenuItem("Div "+sd);
                                        		  
                                        		   submenuDelStructDivisions.add(delDivision[i]);
                                        		   delDivision[i].addActionListener(new ActionListener() {
                                        			   public void actionPerformed(ActionEvent e) {
                                        				   //i imi da Level-ul
                                        				  // String divId=wselect.getStrukturLevel(did1);
                                        				   int startSel=indexIdWord.get(divisions.get(indexIdDivision.get(did1).intValue()).getWbegin()).intValue();
                                        				   int endSel=indexIdWord.get(divisions.get(indexIdDivision.get(did1).intValue()).getWend()).intValue();
                                        				   fidalWordList.setSelectionInterval(startSel,endSel);
                                        				   int confirm= JOptionPane.showOptionDialog(desk,
       							             	                "You are going to delete this division. Are you sure? ",
       							             	                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
       							             	                JOptionPane.QUESTION_MESSAGE, null, null, null);
       							             			if (confirm == JOptionPane.YES_OPTION) {
       							             				String idp= divisions.get(indexIdDivision.get(did1)).getParent();
       							             				int level=divisions.get(indexIdDivision.get(did1)).getLevel();
       							             			 //   System.out.println("IDP"+idp+ " Level "+level);
       							             			//divisionsBackup.clear();
						                				//for(int i=0;i<divisions.size();i++)
						                				//	divisionsBackup.add(divisions.get(i).copyDivision());
						                				//undoMenu.setEnabled(true);
       							             			    // new implem
       							             			 if(!idp.isEmpty()) 
       							             				 divisions.get(indexIdDivision.get(idp).intValue()).getChildren().remove(did1);
       							             			 
       							             			for(int k=startSel;k<=endSel;k++) {
					                						words.get(k).getStrukturIds().remove(did1);
					                						//System.out.println("k="+k);
					                					}
					                					
					                			//       System.out.println("remove div "+ divisions.get(indexIdDivision.get(did1).intValue()).getInternalNr()+"position "+indexIdDivision.get(did1).intValue() );
					                					divisions.remove(indexIdDivision.get(did1).intValue());
					                				
					                					//indexIdDivision.remove(did1);  		
					                					createIndexDivisions();
       							             			// alle Divs mit Parent iddiv; mit level gleich mit d und mit Wbegin >endSel internal nr +1; wenn change=false nr++ auch
					                			//ln("End Div "+endSel);
						                					for(int j=0;j<divisions.size();j++) {
						                		//				System.out.println("j= "+j+" starts "+indexIdWord.get(divisions.get(j).getWbegin()));
						                						if((indexIdWord.get(divisions.get(j).getWbegin()).intValue()>endSel)&&
						                						(divisions.get(j).getLevel()==level)&&
						                						(divisions.get(j).getParent().equals(idp))){
						                			//				   System.out.println("!!!! "+(divisions.get(j).getInternalNr())+ " "+divisions.get(j).getNr()+ " "+divisions.get(j).getChange());
							                			            //    divisions.get(j).setInternalNr(divisions.get(j).getInternalNr()-1);
							                			                if (!divisions.get(j).getChange()) {
							                			     //           	System.out.println("CHANGE");
							                			                	divisions.get(j).setNr(""+(divisions.get(j).getInternalNr()-1));
							                			                }
							                			                divisions.get(j).setInternalNr(divisions.get(j).getInternalNr()-1);
									                					}
							                		          }
						       
						                				int[] numbersPrev=new int[noStruct];
						                				int[] numbersLevels=new int[noStruct];
						                				for (int j=0;j<noStruct;j++) {
						                					   int b=j+1;
						                						//numbers[i]=Integer.parseInt(structLevelsBegin.get(""+b));
						                						numbersLevels[j]=0;
						                						numbersPrev[j]=0;
						                				}
						                				if(!idp.isEmpty()) {
						                					ArrayList<String> childid1p= divisions.get(indexIdDivision.get(idp).intValue()).getChildren();
						                					for (int j=0;j<childid1p.size();j++) {
						                						if((divisions.get(indexIdDivision.get(childid1p.get(j)).intValue()).getLevel()>level)&&
						                						(indexIdWord.get(divisions.get(indexIdDivision.get(childid1p.get(j)).intValue()).getWend()).intValue()<startSel))
						                							numbersPrev[divisions.get(indexIdDivision.get(childid1p.get(j)).intValue()).getLevel()-1]++;
						                					}
						                				}
						                				else {
						                					for(int j=0;j<divisions.size();j++) {
						                						if((divisions.get(j).getLevel()>level)&&
						                								(divisions.get(j).getParent().isEmpty())&&
						                						(indexIdWord.get(divisions.get(j).getWend()).intValue()<startSel)){
						                							numbersPrev[divisions.get(j).getLevel()-1]++;
						                					}
						                					}
						                				}
						                					//alles Divs zwischen Wbegin und Wende mit parent iddiv  und level > d.level bekommen parent d und d bekommt sie als children
						                					for(int j=0;j<divisions.size();j++) {
						                						if((indexIdWord.get(divisions.get(j).getWbegin()).intValue()>=startSel)&&
						                								((indexIdWord.get(divisions.get(j).getWend()).intValue()<=endSel))&&
						                								(divisions.get(j).getParent().equals(did1))&&
						                								(divisions.get(j).getLevel()>level) ){
						                							divisions.get(j).setParent(idp);
						                							numbersLevels[divisions.get(j).getLevel()-1]++;
						                							divisions.get(j).setInternalNr(numbersPrev[divisions.get(j).getLevel()-1]+divisions.get(j).getInternalNr());
						                							 if (!divisions.get(j).getChange()) 
							                			                	divisions.get(j).setNr(""+divisions.get(j).getInternalNr());
						                						if(!idp.isEmpty())
						                							divisions.get(indexIdDivision.get(idp).intValue()).getChildren().add(divisions.get(j).getId());
						                						divisions.get(j).setParent(idp);
						                						}
						                					}               				
						                					// alle Divs cu parent iddiv cu Wbegin > Wende nr internal bzw nr  --
						                					for(int j=0;j<divisions.size();j++) {
						                						if(!idp.isEmpty()) {
						                						if((indexIdWord.get(divisions.get(j).getWbegin()).intValue()>endSel)&&
						                								divisions.get(j).getParent().equals(idp)) {
						                							/*if(divisions.get(j).getLevel()==level)  {
						                								System.out.println("Fom "+ divisions.get(j).getInternalNr());
						                								divisions.get(j).setInternalNr(divisions.get(j).getInternalNr()-1);
						                							  System.out.println("Transformed in " +divisions.get(j).getInternalNr());
						                							}*/
						                							//else
						                								if(divisions.get(j).getLevel()>level)
						                							 divisions.get(j).setInternalNr(divisions.get(j).getInternalNr()+numbersLevels[divisions.get(j).getLevel()-1]);
							                			                if (!divisions.get(j).getChange()) 
							                			                	divisions.get(j).setNr(""+divisions.get(j).getInternalNr());
						                					}
						                					}
						                						else {
						                							if((indexIdWord.get(divisions.get(j).getWbegin()).intValue()>endSel)&&
							                								divisions.get(j).getParent().isEmpty()) {
							                							/*if(divisions.get(j).getLevel()==level)  {
							                								System.out.println("*** "+(divisions.get(j).getInternalNr()-1));
							                								divisions.get(j).setInternalNr(divisions.get(j).getInternalNr()-1);
							                							}
							                							else */
							                								if(divisions.get(j).getLevel()>level)
							                							 divisions.get(j).setInternalNr(divisions.get(j).getInternalNr()+numbersLevels[divisions.get(j).getLevel()-1]);
								                			                if (!divisions.get(j).getChange()) 
								                			                	divisions.get(j).setNr(""+divisions.get(j).getInternalNr());
							                					}
						                							
						                						}
						                					}
						                				
						                					
						                				
						                					
						                				/*	for(int k=startSel;k<=endSel;k++) {
						                						words.get(k).getStrukturIds().remove(did1);
						                						System.out.println("k="+k+words.get(k).getStrukturIds().size());
						                					}*/
						                					fidalWordList.revalidate();fidalWordList.repaint();
       							             			//fidalWordList.setSelectionInterval(indexIdWord.get(wselect.getId()).intValue(),indexIdWord.get(wselect.getId()).intValue());
       							             			}
       							             			//else fidalWordList.setSelectionInterval(indexIdWord.get(wselect.getId()).intValue(),indexIdWord.get(wselect.getId()).intValue());
                                        			   }
                                        		   });
                                        	   }
                                           }
                                          submenuAnnotStructDivisions.add(submenuEditStructDivisions);
                                          submenuAnnotStructDivisions.add(submenuDelStructDivisions);
						            	final JMenuItem jmInsDiv=new JMenuItem("Insert Division");
						            	  
						            		  jmInsDiv.addActionListener(new ActionListener(){
								                	@Override public void actionPerformed(ActionEvent ae){
								                		int[] selind=fidalWordList.getSelectedIndices();
								                	
								                		JLabel startP =new JLabel("First GraphicUnit");
								                		JButton endP=new JButton("Last GraphicUnit");
								                		JTextField startW=new JTextField(15);
								                		startW.setEditable(false);startW.setFont(etiopic1);
								                		startW.setText(words.get(fidalWordList.getSelectedIndex()).getFidalLabel(typScript, typDoc));
								                		startSel=fidalWordList.getSelectedIndex();
								                		endSel=-1;
								                		JTextField endW=new JTextField(15);
								                		endW.setEditable(false);endW.setFont(etiopic1);
								                		JLabel divTL=new JLabel("Division Type");
								                		JComboBox divT=new JComboBox();
								                		divT.addItem("-");
								                		for (int j=0;j<structLevels.size();j++) {
								                			int x=j+1;
								                			if(!structLevels.get(""+x).isEmpty())
								                		divT.addItem(structLevels.get(""+x));
								                		}
								                		divT.setSelectedIndex(0);
								                	divT.setEnabled(false);
								                		/*if(( noStruct>=2)&&(structLevels.get("2").length()>0)) divT.addItem(structLevels.get("2"));
								                		if(( noStruct>=3)&&(structLevels.get("3").length()>0)) divT.addItem(structLevels.get("3"));
								                		if(( noStruct>=4)&&(structLevels.get("4").length()>0)) divT.addItem(structLevels.get("4"));*/
								                		JLabel divNL=new JLabel("Division Name");
								                		JTextField divN=new JTextField(10);
								                		divN.setEditable(false);divN.setEnabled(false);
								                		JLabel divZL=new JLabel("Division Internal Number");
								                		JTextField divZ=new JTextField(10);divZ.setEditable(false);
								                		divZ.setEnabled(false);
								                	//	divZ.setText(calculateDivisionNumber(fidalWordList.getSelectedIndex()));
								                		
								                		JLabel divZEL=new JLabel("Division Number");
								                		JTextField divZE=new JTextField(10);
								                		divZE.setEditable(false);divZE.setEnabled(false);
								                		JLabel divSTL=new JLabel("Style");
								                		JComboBox divST=new JComboBox();
								                		divST.addItem("-");
								                		divST.setMaximumRowCount(7);
								                		divST.addItem("-");
								                		divST.addItem("Bible");
								                		divST.addItem("Hagiography");
								                		divST.addItem("Chronicles");
								                		divST.addItem("Theology");
								                		divST.addItem("Apocrypha Pseudoepigrapha");
								                		divST.addItem("Amharic Literature");
								                		divST.addItem("Apocrypha");
								                		divST.addItem("Pseudoepigrapha");
								                		divST.addItem("Miracle");
								                		divST.addItem("New Testament");
								                		divST.addItem("Old Testament");
								                		divST.addItem("Beta Esrael Literature");
								                		divST.addItem("Biography");
								                		divST.addItem("Canon Law");
								                		divST.addItem("Commentary");
								                		divST.addItem("Christian Literature");
								                		divST.addItem("Chronography");
								                		divST.addItem("Crucifixion");
								                		divST.addItem("Fiction");
								                		divST.addItem("History and Historiography");
								                		divST.addItem("Homily");
								                		divST.addItem("Islamic Literature");
								                		divST.addItem("Koran");
								                		divST.addItem("Legal Document");
								                		divST.addItem("Liturgy");
								                		divST.addItem("Chants");
								                		divST.addItem("Lectionary");
								                		divST.addItem("Missal");
								                		divST.addItem("Rituals");
								                		divST.addItem("Rituals an Rites");
								                		divST.addItem("Christian Literature");
								                		divST.addItem("Tigrinya Literature");
								                		divST.addItem("Translation");
								                		divST.addItem("Vocabulary");
								                		divST.addItem("Magic");
								                		divST.addItem("Medicine");
								                		divST.addItem("Other");
								                		divST.addItem("Philosophy");
								                		divST.addItem("Poetry");
								                		divST.addItem("Prayers");
								                		divST.addItem("Religion");
								                		divST.addItem("Christian Content");
								                		divST.setEnabled(false);
								                		JLabel divAutL=new JLabel("Creator");
								                		JComboBox divAut=new JComboBox();
								 
								                		divAut.addItem("Edition");
								                		divAut.addItem("Annotator");
								                        divAut.setEnabled(false);
								                		JLabel commL=new JLabel("Comments");
								                		final HTMLEditorPane hp =new HTMLEditorPane();
								                		//hp.setText(words.get(position).getCooment());
								                		//System.out.println("Comment"+ Jsoup.parse(hp.getText()).text()+"***");
								                		
								                		if(Jsoup.parse(hp.getText()).text().isEmpty())
								                			hp.setText("<div style=\'font-family:Ethiopic Unicode\'></div>");
								                		
								                		hp.setEnabled(false);
								                		final ChildFrame strAnnFrame=new ChildFrame("Insert Structure Annotation",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
								                		Container strAnn=strAnnFrame.getContentPane();
								                		strAnn.setLayout(new GridBagLayout());
								                		GridBagConstraints gbc=new GridBagConstraints();
								                		gbc.gridx=0;
								                		gbc.gridy=0;
								                		gbc.weightx=100;
								                		gbc.weighty=100;
								                		gbc.insets.left=2;
								                		gbc.insets.right=2;
								                		gbc.insets.top=2;
								                		gbc.gridwidth=2;
								                		gbc.anchor=GridBagConstraints.NORTHWEST;
								                		gbc.insets.bottom=2;
								                		strAnn.add(startP,gbc); gbc.gridx=1; strAnn.add(startW,gbc);
								                		gbc.gridx=0; gbc.gridy=1;
								                		strAnn.add(endP,gbc);gbc.gridx=1; strAnn.add(endW,gbc);
								                		gbc.gridx=0; gbc.gridy=2;
								                		strAnn.add(divTL,gbc);gbc.gridx=1; strAnn.add(divT,gbc);
								                		gbc.gridx=0; gbc.gridy=3;
								                		strAnn.add(divNL,gbc);gbc.gridx=1; strAnn.add(divN,gbc);
								                		gbc.gridx=0; gbc.gridy=4;
								                		strAnn.add(divZL,gbc);gbc.gridx=1; strAnn.add(divZ,gbc);
								                		gbc.gridx=0;  gbc.gridy=5;
								                		strAnn.add(divZEL,gbc);gbc.gridx=1; strAnn.add(divZE,gbc);
								                		gbc.gridx=0; gbc.gridy=6;
								                		strAnn.add(divSTL,gbc);gbc.gridx=1; strAnn.add(divST,gbc);
								                		gbc.gridx=0; gbc.gridy=7;
								                		strAnn.add(divAutL,gbc);gbc.gridx=1; strAnn.add(divAut,gbc);
								                		gbc.gridwidth=2;gbc.gridx=0; gbc.gridy=8;
								                		strAnn.add(commL,gbc); gbc.gridy=9;
								                		strAnn.add(hp,gbc);
								                		JButton okB=new JButton("OK"); JButton cancelB=new JButton("Cancel");
								                		okB.setEnabled(false);
								                		gbc.gridwidth=1;gbc.gridx=0; gbc.gridy=10;
								                		gbc.anchor=GridBagConstraints.CENTER;
								                		strAnn.add(okB,gbc); gbc.gridx=1;strAnn.add(cancelB,gbc);
								                		
								                		endP.addActionListener(new ActionListener(){
								                			public void actionPerformed(ActionEvent e){
								                				divT.setEnabled(true);
								                				endW.setText(words.get(fidalWordList.getSelectedIndex()).getFidalLabel(typScript, typDoc));
								                			    endSel=fidalWordList.getSelectedIndex();
								                			    if (endSel<startSel){
								                			    	int tmp=startSel;
								                			    	startSel=endSel;
								                			    	endSel=tmp;
								                			    	
								                			    }
								                			    fidalWordList.setSelectionInterval(startSel, endSel);
								                			}
								                		});
								                		cancelB.addActionListener(new ActionListener(){
								                			public void actionPerformed(ActionEvent e){
								                				strAnnFrame.doDefaultCloseAction();
								                			}
								                		});
								                		divT.addItemListener(new ItemListener(){
								                			
								                			public void itemStateChanged(ItemEvent e) {
								                				if (divT.getSelectedIndex()>0) {
								                					
								                					//verify if this division is possible
								                					//check if anfang div 
								                					int  kk=0;
								                					/*boolean isPossib2=true;
								                					while((kk<divisions.size()) && isPossib2){
								                						if((indexIdWord.get(divisions.get(kk).getWbegin()).intValue()>=startSel) &&
								                						                   (indexIdWord.get(divisions.get(kk).getWend()).intValue()<=endSel))
								                						           if (divisions.get(kk).getLevel()<=divT.getSelectedIndex()) isPossib2=false;
								                						            else kk=kk+1;
								                					}
								                					System.out.println("possib2 "+isPossib2);*/
								                				
								                				if( isDivisionPossible(startSel,endSel,divT.getSelectedIndex())){
								                					divAut.setEnabled(true);
								                					divST.setEnabled(true);
								                					hp.setEnabled(true);
								                					divZE.setEnabled(true);;divZE.setEditable(true);
								                					divZ.setEnabled(true);
								                					divN.setEnabled(true);;divN.setEditable(true);
								                					divN.setText(structLevelsBeginName.get(""+divT.getSelectedIndex()));
								                					okB.setEnabled(true);
								                					int j=0; boolean found=false; String iddiv="";
								                					int level=0;
								                					while((j<words.get(startSel).getStrukturIds().size())) {
								                						String indDiv1=words.get(startSel).getStrukturIds().get(j);
								                						
								                						if((divisions.get(indexIdDivision.get(indDiv1).intValue()).getLevel()<divT.getSelectedIndex())&&(divisions.get(indexIdDivision.get(indDiv1).intValue()).getLevel()>level)) {
								                							iddiv=divisions.get(indexIdDivision.get(indDiv1).intValue()).getId();
								                							level=divisions.get(indexIdDivision.get(indDiv1).intValue()).getLevel();
								                						//	System.out.println("level "+level);
								                						}
								                						j=j+1;
								                					}
								                					int k=0; int nn1=Integer.parseInt(structLevelsBegin.get(divT.getSelectedIndex()+""));
								                					System.out.println("Parent "+iddiv);
								                					if(!iddiv.isEmpty()) {
								                					 boolean found1=false;
								                					if(divisions.get(indexIdDivision.get(iddiv).intValue()).getChildren().size()>0) {
								                					//	System.out.println("size children "+ divisions.get(indexIdDivision.get(iddiv).intValue()).getChildren().size());
								                					while((k<divisions.get(indexIdDivision.get(iddiv).intValue()).getChildren().size())) {
								                						String idChild=divisions.get(indexIdDivision.get(iddiv).intValue()).getChildren().get(k);
								                						if((indexIdWord.get(divisions.get(indexIdDivision.get(idChild).intValue()).getWbegin()).intValue()<startSel)&&
								                								(divisions.get(indexIdDivision.get(idChild).intValue()).getLevel()==divT.getSelectedIndex()))
								                						{ nn1=nn1+1;
								                							//nn1=divisions.get(indexIdDivision.get(idChild).intValue()).getInternalNr();
								                						System.out.println("nn1 "+nn1);
								                						}
								                						k=k+1;
								                					}
								                					}
								                					//else nn1=-1;
								                					//nn1=nn1+1;
								                					Division d=divisions.get(indexIdDivision.get(iddiv).intValue());
								                					String did1="";
								                					String num=""+d.getInternalNr();
								                					while (!d.getParent().isEmpty()) {
								                						d=divisions.get(indexIdDivision.get(d.getParent()).intValue());
								                						num=d.getInternalNr()+"."+num;
								                					}
								                					
								                					divZ.setText(num+"."+nn1);divZE.setText(num+"."+nn1);
								                					}
								                					else {
								                						boolean found2=false; k=0;int nn=Integer.parseInt(structLevelsBegin.get(divT.getSelectedIndex()+"")); 
								                						while((k<divisions.size())) {
								                							if ((indexIdWord.get(divisions.get(k).getWbegin()).intValue()<startSel)) {
								                							   if((divisions.get(k).getParent().isEmpty())&&(divisions.get(k).getLevel()==divT.getSelectedIndex())){
								                								   //nn=divisions.get(k).getInternalNr();
								                								      nn=nn+1;
								                							   }
								                							}  
								                							   k=k+1;
								                							   
								                								
								                							
								                						}
								                					//	nn=nn+1;
								                						divZ.setText(""+nn);divZE.setText(""+nn);
								                						divN.setText(structLevelsBeginName.get(divT.getSelectedIndex()+""));
								                					}
								                				}	
								                				else {
								                					JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" >You cannot insert overlapping divisions</font></p></html>", "Dialog",
											         		    		      JOptionPane.ERROR_MESSAGE); 
								                				}
								                			}
								                				else {
								                					divAut.setEnabled(false);
								                					divST.setEnabled(false);
								                					hp.setEnabled(false);
								                					divZE.setEnabled(false);;divZE.setEditable(false);
								                					divZ.setEnabled(false);
								                					divN.setEnabled(false);;divN.setEditable(false);
								                					okB.setEnabled(false);
								                				}
								                			}
								                		});
								                		
								                		okB.addActionListener(new ActionListener(){
								                			public void actionPerformed(ActionEvent e){
								                			//	divisionsBackup.clear();
								                				//for(int i=0;i<divisions.size();i++)
								                					//divisionsBackup.add(divisions.get(i).copyDivision());
								                				//undoMenu.setEnabled(true);
								                				String id= divT.getSelectedIndex()+"D"+UUID.randomUUID();
								                				String nrdivtot=divZ.getText();
								                				
								                				String nrdiv=nrdivtot.substring(nrdivtot.lastIndexOf('.')+1);
								                				int nrd;
								                				if(nrdiv.isEmpty()) nrd=Integer.parseInt(nrdivtot);
								                				else nrd=Integer.parseInt(nrdiv);
								                				boolean change1=true;
								                				if(divZ.getText().equals(divZE.getText())) change1=false;
								                				Division d=new Division(words.get(startSel).getId(),words.get(endSel).getId(),id,divT.getSelectedIndex(),nrd,change1);
								                			//    System.out.println("Created "+ d.getLevel()+"***"+d.getInternalNr());
								                				d.setName(divN.getText());
								                			    d.setNr(divZE.getText());
								                			    d.setComment(hp.getText());
								                			    d.setCreator(divAut.getSelectedItem().toString());
								                			    d.setGenre(divST.getSelectedItem().toString());
								                			    int j=0; boolean found=false; String iddiv="";
								                			    int level=0;
							                					while((j<words.get(indexIdWord.get(d.getWbegin()).intValue()).getStrukturIds().size())) {
							                						String indDiv1=words.get(indexIdWord.get(d.getWbegin()).intValue()).getStrukturIds().get(j);
							                				//		System.out.println("INDDIV1 "+indDiv1);
							                						
							                						if((divisions.get(indexIdDivision.get(indDiv1).intValue()).getLevel()<divT.getSelectedIndex())&&(divisions.get(indexIdDivision.get(indDiv1).intValue()).getLevel()>level)) {
							                							iddiv=divisions.get(indexIdDivision.get(indDiv1).intValue()).getId();
							                							level=divisions.get(indexIdDivision.get(indDiv1).intValue()).getLevel();
							                							
							                						}
							                						j=j+1;
							                					}
							                					if(!iddiv.isEmpty()) {
							                						d.setParent(iddiv);
							                						divisions.get(indexIdDivision.get(iddiv).intValue()).getChildren().add(d.getId());
							                					}
							                					else {
							                						d.setParent("");
							                					}
							                					// alle Divs mit Parent iddiv; mit level gleich mit d und mit Wbegin >endSel internal nr +1; wenn change=false nr++ auch
							                					for(int i=0;i<divisions.size();i++) {
							                						if((indexIdWord.get(divisions.get(i).getWbegin()).intValue()>endSel)&&
							                						(divisions.get(i).getLevel()==d.getLevel())&&
							                						(divisions.get(i).getParent().equals(iddiv))){
								                			                divisions.get(i).setInternalNr(divisions.get(i).getInternalNr()+1);
								                			                if (!divisions.get(i).getChange()) 
								                			                	divisions.get(i).setNr(""+divisions.get(i).getInternalNr());
								                			            	
										                					}
								                		          }
							       
							                				int[] numbers=new int[noStruct];
							                				int[] numbersLevels=new int[noStruct];
							                				for (int i=0;i<noStruct;i++) {
							                					   int b=i+1;
							                						numbers[i]=Integer.parseInt(structLevelsBegin.get(""+b));
							                						numbersLevels[i]=0;
							                				}
							                					//alles Divs zwischen Wbegin und Wende mit parent iddiv  und level > d.level bekommen parent d und d bekommt sie als children
							                					for(int i=0;i<divisions.size();i++) {
							                						if((indexIdWord.get(divisions.get(i).getWbegin()).intValue()>=startSel)&&
							                								((indexIdWord.get(divisions.get(i).getWend()).intValue()<=endSel))&&
							                								(divisions.get(i).getParent().equals(iddiv))&&
							                								(divisions.get(i).getLevel()>d.getLevel())) {
							                							divisions.get(i).setParent(d.getId());
							                							
							                							divisions.get(i).setInternalNr(numbers[divisions.get(i).getLevel()-1]+numbersLevels[divisions.get(i).getLevel()-1]);
							                							numbersLevels[divisions.get(i).getLevel()-1]++;
							                							 if (!divisions.get(i).getChange()) 
								                			                	divisions.get(i).setNr(""+divisions.get(i).getInternalNr());
							                							//numbers[divisions.get(i).getLevel()-1]++;
							                							d.getChildren().add(divisions.get(i).getId());
							                						}
							                					}               				
							                					// alle Divs cu parent iddiv cu Wbegin > Wende nr internal bzw nr  --
							                					for(int i=0;i<divisions.size();i++) {
							                						if((indexIdWord.get(divisions.get(i).getWbegin()).intValue()>endSel)&&
							                								divisions.get(i).getParent().equals(iddiv)) {
							                							 divisions.get(i).setInternalNr(divisions.get(i).getInternalNr()-numbersLevels[divisions.get(i).getLevel()-1]);
								                			                if (!divisions.get(i).getChange()) 
								                			                	divisions.get(i).setNr(""+divisions.get(i).getInternalNr());
							                					}
							                					}
							                				
							                					
							                					for(int k=startSel;k<=endSel;k++) {
							                						words.get(k).addStrId(d.getId());
							                					}
							                					
							                			
							                					divisions.add(d);
							                					createIndexDivisions();
							                				
							                			//		indexIdDivision.put(d.getId(), new Integer(divisions.size()-1));
							                					
							                					strAnnFrame.doDefaultCloseAction();
								                			}
								                		});
								                		//strAnnFrame.pack();
								                		addChild(strAnnFrame,950,30,250,500);
								                		strAnnFrame.pack();
						        			               strAnnFrame.setVisible(true);
						        			              strAnnFrame.moveToFront();
								                		
								                	}
								                }); 
						            		  

						            	 
						            	   submenuAnnotStructDivisions.add(jmInsDiv);
						            	   submenuAnnotStructDivisions.add(submenuDelStructDivisions);
						           
						            	   
						            	   final JMenuItem jmannotH = new JMenuItem("Head");
						            	   jmannotH.setEnabled(false);
						            	   final JMenuItem jmannotQ = new JMenuItem("Quotation");
						            	   submenuAnnotStruct.add(submenuAnnotStructDivisions);
						            	   jmannotQ.setEnabled(false);
						            	   submenuAnnotStruct.add(jmannotH);
						            	   submenuAnnotStruct.add(jmannotQ);
						            	   popupMenu.add(submenuAnnotStruct);
						            	   popupMenu.add(new JPopupMenu.Separator());
							                popupMenu.add(submenuAnnotStruct);
							                popupMenu.add(new JPopupMenu.Separator());
							               
					                	//	final WortNode wselect = words.get(translitWordList.getSelectedIndex());
					                		for (int i=0;i<wselect.getFidalChildren().size();i++){
					                			
					                			JMenuItem menF=new JMenuItem(wselect.getFidalChildren().get(i).getFidalInternLabel(typScript,typDoc));
					                			if(typScript==0)
					                			 menF.setFont(etiopicMenu1);
					                			else  menF.setFont(etiopicMenu_1);
					                			menuFLetters.add(menF);
					                			edition.add(menF);
					                           
					                		
					                			
					                		}
					                		popupMenu.add(edition);
					                		
					                		for (int i=0;i<wselect.getFidalChildren().size();i++){
					                			
					                			JMenuItem menF1=new JMenuItem(wselect.getFidalChildren().get(i).getFidalInternLabel(typScript,typDoc));
					                			if(typScript==0)
					                			menF1.setFont(etiopicMenu1);
					                			else menF1.setFont(etiopicMenu_1);
					                			menuFLetters1.add(menF1);
					                			ending.add(menF1);
					                           
					                		
					                			
					                		}
					                		popupMenu.add(ending);
	                                           for (int i=0;i<wselect.getFidalChildren().size();i++){
					                			
					                			JMenuItem menF2=new JMenuItem(wselect.getFidalChildren().get(i).getFidalInternLabel(typScript,typDoc));
					                			if(typScript==0)
					                			menF2.setFont(etiopicMenu1);
					                			else menF2.setFont(etiopicMenu_1);
					                			menuFLetters2.add(menF2);
					                			insertLineBreak.add(menF2);
					                           
					                		
					                			
					                		}
	                                           
	                                           for (int i=0;i<wselect.getFidalChildren().size();i++){
						                			
						                			JMenuItem menF2_1=new JMenuItem(wselect.getFidalChildren().get(i).getFidalInternLabel(typScript,typDoc));
						                			if(typScript==0)
						                			menF2_1.setFont(etiopicMenu1);
						                			else menF2_1.setFont(etiopicMenu_1);
						                			if(wselect.getFidalChildren().get(i).hasEnding()) menF2_1.setEnabled(true);
						                			else menF2_1.setEnabled(false);
						                			menuFLetters2_1.add(menF2_1);
						                			insertLineBreak1.add(menF2_1);
						                           
						                		
						                			
						                		}
	                                           lineBreak.add(insertLineBreak);
	                                           lineBreak.add(insertLineBreak1);
	                                           
	                                           for (int i=0;i<wselect.getFidalChildren().size();i++){
						                			
						                			JMenuItem menF3=new JMenuItem(wselect.getFidalChildren().get(i).getFidalInternLabel(typScript,typDoc));
						                			if(typDoc==0)
						                			menF3.setFont(etiopicMenu1);
						                			else menF3.setFont(etiopicMenu_1);
						                			if(wselect.getFidalChildren().get(i).getEdAnnot()!=null)
							            				if( wselect.getFidalChildren().get(i).getEdAnnot().getSpecificTag("lb")!=null)
							            					menF3.setEnabled(true);
							            				else menF3.setEnabled(false);
						                			else menF3.setEnabled(false);
						                			menuFLetters3.add(menF3);
						                			deleteLineBreak.add(menF3);
						                           
						                			
						                		} 
	                                           insertLBBefore.addActionListener(new ActionListener() {
	                                        	   public void actionPerformed(ActionEvent e) {
	                                        			ArrayList<Attribut> valLB=new ArrayList<Attribut>();
							            				Attribut a=new Attribut("vl",""+(startLBnr));
							            				valLB.add(a);
							            				Tag t=new Tag("lb1",valLB);
								            			if(wselect.getFidalChildren().get(0).getEdAnnot()!=null){
								            				
								            				wselect.getFidalChildren().get(0).getEdAnnot().addTag(t);
								            			}
								            			else {
								            				ArrayList<Tag> tagLB=new ArrayList<Tag>();
								            				tagLB.add(t);
								            				Annotation lbannot=new Annotation(false,false,true,false,tagLB);
								            				wselect.getFidalChildren().get(0).setEdAnnot(lbannot);
								            			}
								            			
	                                        	   }
	                                           });
	                                          removeLBBefore.addActionListener(new ActionListener() {
	                                        	   public void actionPerformed(ActionEvent e) {
	                                        			
								            			if(wselect.getFidalChildren().get(0).getEdAnnot()!=null){
								            				if(wselect.getFidalChildren().get(0).getEdAnnot().getSpecificTag("lb1")!=null) {
								            					Tag t=wselect.getFidalChildren().get(0).getEdAnnot().getSpecificTag("lb1");
								            				            wselect.getFidalChildren().get(0).getEdAnnot().removeTag(t);
								            				}
								            			}
								            	
								            			
	                                        	   }
	                                           });
	                                          lineBreak.add(deleteLineBreak);
					                		popupMenu.add(lineBreak);
					                		if(indexes[0]==0) {
					                			popupMenu.add(insertLBBefore);
					                			popupMenu.add(removeLBBefore);
					                		}
					                		popupMenu.add(pageBreak);
					                		popupMenu.add(specDiv);
					                		//popupMenu.add(insertPageBreak);
					                		//popupMenu.add(modifyPageBreak);
					                		//popupMenu.add(deletePageBreak);
					                		//
                                     /*        deletePageBreak.addActionListener(new ActionListener(){
							            		
							            		public void actionPerformed(ActionEvent e){
							            			int confirm= JOptionPane.showOptionDialog(desk,
							             	                "You are going to delete this Page Break. Are you sure? ",
							             	                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
							             	                JOptionPane.QUESTION_MESSAGE, null, null, null);
							             			if (confirm == JOptionPane.YES_OPTION) {
							            			//if(wselect.getFidalChildren().get(pos).getEdAnnot()!=null){
							            				Tag t=wselect.getFidalChildren().get(wselect.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb");
							            				
							            				wselect.getFidalChildren().get(	wselect.getFidalChildren().size()-1).getEdAnnot().removeTag(t);
							            		
							            	
							            			//}
							            		
							            			fidalWordList.repaint();fidalWordList.revalidate();
							            		}
							            		}
							            		});*/
					                		//
                                             
                                             deletePageBreak.addActionListener(new ActionListener(){
  							            		
  							            		public void actionPerformed(ActionEvent e){
  							            			
  							            			//Tag t=new Tag("lb");
  							            			final int position=fidalWordList.getSelectedIndex();
  							            			final WortNode wsel=words.get(position);
  							            			
  							            			int nrpb1copy=0;
  							            			if( wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot()!=null){
  							            			  if( wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb")!=null) {
  							            				for (int k=0;k<wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().size();k++) {
 							            					if (wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(0, 1).equals("+"))
 							            				        nrpb1copy=nrpb1copy+1;
 							            				}
 							            				// nrpb1=wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().size();
 							            			}
  							            			   
  							            			     else nrpb1copy=0;
                                             }
  							            			  else nrpb1copy=0;
  							            			final int nrpb1=nrpb1copy;
  							            			int nrpb2copy=0;
  							            			if (wsel.getFidalChildren().get(0).getEdAnnot()!=null) {
  							            			if (wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb")!=null) {
  							            				for (int k=0;k<wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().size();k++) {
 							            					if (wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(0, 1).equals("-"))
  							            			                  nrpb2copy=nrpb2copy+1;
  							            				}
  							            			}
  							            			//nrpb2= wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().size();
  							            			else nrpb2copy=0;
  							            			}
  							            			else nrpb2copy=0;
  							            			final int nrpb2=nrpb2copy;
  							                		final int nrpb=nrpb1+nrpb2;
  							            			final HTMLEditorPane[] hp =new HTMLEditorPane[nrpb];
  							                		final JCheckBox[] cp =new JCheckBox[nrpb];
  							                		for(int i=0;i<nrpb1;i++) {
  							                			hp[i]=new HTMLEditorPane();
  							                			cp[i]=new JCheckBox("Delete");
  							                		String s="";
  							                		
  							                				s=wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(i).getValue().substring(1);
  							                		
  							                		
  							                			hp[i].setText("<div style=\'font-family:Ethiopic Unicode\'>"+s+"</div>");
  							               
  							                		}
  							                		int j=0;
  							                		for(int i=nrpb1;i<nrpb;i++) {
  							                			hp[i]=new HTMLEditorPane();
  							                			cp[i]=new JCheckBox("Delete");
  							                		String s="";
  							                		
  							                				s=wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().get(j).getValue().substring(1);
  							                		 j=j+1;
  							                		
  							                			hp[i].setText("<div style=\'font-family:Ethiopic Unicode\'>"+s+"</div>");
  							               
  							                		}
  							                		final ChildFrame pbFrame=new ChildFrame("Delete Page Break(s)",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
  							                		Container chp1=pbFrame.getContentPane();
  							                		JPanel chp=new JPanel();
  							                		chp.setLayout(new GridBagLayout());
  							                		GridBagConstraints gbc=new GridBagConstraints();
  							                		gbc.gridx=0;
  							                		gbc.gridy=0;
  							                		gbc.weightx=100;
  							                		gbc.weighty=100;
  							                		gbc.insets.left=2;
  							                		gbc.insets.right=2;
  							                		gbc.insets.top=2;
  							                		gbc.gridwidth=2;
  							                		gbc.anchor=GridBagConstraints.NORTHWEST;
  							                		gbc.insets.bottom=2;
  							                        for (int i=0;i<nrpb;i++) {
  							                        	JPanel jp=new JPanel();
  							                        	jp.add(cp[i]); jp.add(hp[i]);
  							                        	gbc.gridy=i;
  							                		        
  							                		         chp.add(jp,gbc);
  							                		      
  							                        }
  							                		final JButton saveC=new JButton("Save");
  							                		final JButton cancelC=new JButton("Cancel");
  							                		gbc.gridy=nrpb;gbc.gridx=0;
  							                		gbc.gridwidth=1;
  							                		gbc.anchor=GridBagConstraints.CENTER;
  							                		
  							                		chp.add(saveC,gbc);
  							                		gbc.gridx=1;
  							                		chp.add(cancelC,gbc);
  							                		JScrollPane schp=new JScrollPane(chp);
  							                		chp1.add(schp);
  							                		pbFrame.pack();
  							                		addChild(pbFrame,30,30,400,300);
  							                		pbFrame.pack();
  					        			               pbFrame.setVisible(true);
  					        			               pbFrame.moveToFront();
  					        			               saveC.addActionListener(new ActionListener(){
  										                	@Override public void actionPerformed(ActionEvent ae){
  										                		ArrayList<Integer> a=new ArrayList<Integer>();
  										                		for(int i=0;i<nrpb;i++){
  										                		
  										            				if(cp[i].isSelected())
  										            				a.add(new Integer(i));;
  											                	
  										                		} 
  										                		for(int i=0;i<a.size();i++) {
  										                			if (a.get(i).intValue()>=nrpb1) {
  										                			wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().remove(a.get(i).intValue()-nrpb1);
  										                	if(wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().size()==0) {
  										                		wsel.getFidalChildren().get(0).getEdAnnot().removeTag(wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb"));
  										                	if(wsel.getFidalChildren().get(0).getEdAnnot().getListTag().size()==0)
  										                		wsel.getFidalChildren().get(0).setEdAnnot(null);
  										                	}
  										                			}
  										                	else {
  										                		wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().remove(a.get(i).intValue());
  										                		if(wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().size()==0) {
  	  										                		wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().removeTag(wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb"));
  	  										                	if(wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getListTag().size()==0)
  	  										                		wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).setEdAnnot(null);
  										                	}
  										                	}
  										                			}
  										                			//
  										                		
  										                		pbFrame.doDefaultCloseAction();
  										                	}
  					        			               });	
  					        			               cancelC.addActionListener(new ActionListener(){
  										                	@Override public void actionPerformed(ActionEvent ae){
  										                		
  										                		pbFrame.doDefaultCloseAction();
  										                	}
  					        			               });	
  							            	
  							            			
  							            			
  							            		//	System.out.println(nrLB);
  							            			
  							            			
  							            		
  							            			fidalWordList.repaint();fidalWordList.revalidate();
  							            		}
  							            		});
                                              
                                             
                                            // 
                                             modifyPageBreak.addActionListener(new ActionListener(){
 							            		
 							            		public void actionPerformed(ActionEvent e){
 							            			
 							            			//Tag t=new Tag("lb");
 							            			final int position=fidalWordList.getSelectedIndex();
 							            			final WortNode wsel=words.get(position);
 							            			  int nrpb1copy=0;
 							            		 int nrpb2copy=0;
 							            			if(wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot()!=null) {
 							            			if(wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb")!=null) {
 							            				for (int k=0;k<wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().size();k++) {
 							            					if (wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(0, 1).equals("+"))
 							            				        nrpb1copy=nrpb1copy+1;
 							            				}
 							            				// nrpb1=wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().size();
 							            			}    
 							            			else nrpb1copy=0;
 							            			}
 							            			else nrpb1copy=0;
 							            			if(wsel.getFidalChildren().get(0).getEdAnnot()!=null) {
 							            			if(wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb")!=null) {
 							            				for (int k=0;k<wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().size();k++) {
 							            					if (wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().get(k).getValue().substring(0, 1).equals("-"))
 							            				        nrpb2copy=nrpb2copy+1;
 							            				}
 							            				// nrpb1=wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().size();
 							            			} 
 							            			else nrpb2copy=0;
 							            			}
 							            			else nrpb2copy=0;
 							            			final int nrpb1=nrpb1copy;
 							            			final int nrpb2=nrpb2copy;
 							            			final int nrpb=nrpb2+nrpb1;
 							                		final HTMLEditorPane[] hp =new HTMLEditorPane[nrpb];
 							                		final JRadioButton[] beforeB =new JRadioButton[nrpb];
 							                		final JRadioButton[] afterB =new JRadioButton[nrpb];
 							                		ButtonGroup bgr;
 							                		for(int i=0;i<nrpb1;i++) {
 							                			hp[i]=new HTMLEditorPane();
 							                		String s="";
 							                		String ss=wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(i).getValue().substring(0,1);
 							                				s=wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(i).getValue().substring(1);
 							                		beforeB[i]=new JRadioButton("Before");
 							                		afterB[i]=new JRadioButton("After");
 							                		 bgr =new ButtonGroup();
 							                		 bgr.add(beforeB[i]);bgr.add(afterB[i]);
 							                		//if(ss.equals("-")) beforeB[i].setSelected(true);
 							                		//else afterB[i].setSelected(true);
 							                		afterB[i].setSelected(true);
 							                	beforeB[i].setEnabled(false);
 							                	afterB[i].setEnabled(false);
 							                			hp[i].setText("<div style=\'font-family:Ethiopic Unicode\'>"+s+"</div>");
 							               
 							                		}
 							                		int j=0;
 							                		for(int i=nrpb1;i<nrpb;i++) {
 							                			
 							                			hp[i]=new HTMLEditorPane();
 							                		String s="";
 							                		String ss=wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().get(i).getValue().substring(0,1);
 							                				s=wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().get(i).getValue().substring(1);
 							                		beforeB[i]=new JRadioButton("Before");
 							                		afterB[i]=new JRadioButton("After");
 							                		 bgr =new ButtonGroup();
 							                		 bgr.add(beforeB[i]);bgr.add(afterB[i]);
 							                		//if(ss.equals("-")) beforeB[i].setSelected(true);
 							                		//else afterB[i].setSelected(true);
 							                		beforeB[i].setSelected(true);
 	 							                	beforeB[i].setEnabled(false);
 	 							                	afterB[i].setEnabled(false);
 							                			hp[i].setText("<div style=\'font-family:Ethiopic Unicode\'>"+s+"</div>");
 							                       j=j+1;
 							                		}
 							                		final ChildFrame pbFrame=new ChildFrame("Modify Page Break(s)",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
 							                		Container chp1=pbFrame.getContentPane();
 							                		JPanel chp=new JPanel();
 							                		chp.setLayout(new GridBagLayout());
 							                		GridBagConstraints gbc=new GridBagConstraints();
 							                		gbc.gridx=0;
 							                		gbc.gridy=0;
 							                		gbc.weightx=100;
 							                		gbc.weighty=100;
 							                		gbc.insets.left=2;
 							                		gbc.insets.right=2;
 							                		gbc.insets.top=2;
 							                		gbc.gridwidth=2;
 							                		gbc.anchor=GridBagConstraints.NORTHWEST;
 							                		gbc.insets.bottom=2;
 							                		GridBagConstraints gbc1=new GridBagConstraints();
 							                		gbc1.gridx=0;
 							                		gbc1.gridy=0;
 							                		gbc1.weightx=100;
 							                		gbc1.weighty=100;
 							                		gbc1.insets.left=2;
 							                		gbc1.insets.right=2;
 							                		gbc1.insets.top=2;
 							                		gbc1.gridwidth=1;
 							                		gbc1.anchor=GridBagConstraints.NORTHWEST;
 							                		gbc1.insets.bottom=2;
 							                		JPanel[] chpi=new JPanel[nrpb];
 							                        for (int i=0;i<nrpb;i++) {
 							                        	chpi[i]=new JPanel();
 							                        	chpi[i].setLayout(new GridBagLayout());
 							                        	gbc1.gridx=0;gbc1.gridy=0;
 							                        	chpi[i].add(beforeB[i], gbc1);
 							                        	gbc1.gridx=1;
 							                        	chpi[i].add(afterB[i], gbc1);
 							                        	gbc1.gridx=2;
 							                        	chpi[i].add(hp[i], gbc1);
 							                        	
 							                        	gbc.gridy=i;
 							                		           chp.add(chpi[i],gbc);
 							                        }
 							                		final JButton saveC=new JButton("Save");
 							                		final JButton cancelC=new JButton("Cancel");
 							                		gbc.gridy=nrpb;gbc.gridx=0;
 							                		gbc.gridwidth=1;
 							                		gbc.anchor=GridBagConstraints.CENTER;
 							                		chp.add(saveC,gbc);
 							                		gbc.gridx=1;
 							                		chp.add(cancelC,gbc);
 							                		JScrollPane schp=new JScrollPane(chp);
 							                		chp1.add(schp);
 							                		pbFrame.pack();
 							                		addChild(pbFrame,30,30,300,300);
 							                		pbFrame.pack();
 					        			               pbFrame.setVisible(true);
 					        			               pbFrame.moveToFront();
 					        			               saveC.addActionListener(new ActionListener(){
 										                	@Override public void actionPerformed(ActionEvent ae){
 										                		for(int i=0;i<nrpb;i++){
 										                		  String b="";
 										                		  if(beforeB[i].isSelected()) {
 										                			  b="-";
 										                
 										            				wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().get(i-nrpb1).setValue(b+hp[i].getText());;
 										                		  }
 										            				else {
 										            					b="+";
 										            					wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(i).setValue(b+hp[i].getText());;
 										            				}
 										                		}  
 										                		
 										                		
 											            		
 										                			//
 										                		
 										                		pbFrame.doDefaultCloseAction();
 										                	}
 					        			               });	
 					        			               cancelC.addActionListener(new ActionListener(){
 										                	@Override public void actionPerformed(ActionEvent ae){
 										                		
 										                		pbFrame.doDefaultCloseAction();
 										                	}
 					        			               });	
 							            	
 							            			
 							            			
 							            		//	System.out.println(nrLB);
 							            			
 							            			
 							            		
 							            			fidalWordList.repaint();fidalWordList.revalidate();
 							            		}
 							            		});
                                             
                                            // 
                                             
                                             insertSpecDiv.addActionListener(new ActionListener(){
 							            		
 							            		public void actionPerformed(ActionEvent e){
 							            			//Tag t=new Tag("lb");
 							            			final int position=fidalWordList.getSelectedIndex();
 							            			final WortNode wsel=words.get(position);
 							                		final HTMLEditorPane hp =new HTMLEditorPane();
 							                		String s="";
 							                		/*if(wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot()!=null){
 							                			if(wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb")!=null) {
 							                				s=wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(0).getValue();
 							                			}
 							                		}*/
 							                		//hp.setText(StringEscapeUtils.unescapeHtml(words.get(position).getCooment()));
 							                		//System.out.println("Comment"+ Jsoup.parse(hp.getText()).text()+"***");
 							                		
 							                		//if(s.isEmpty())
 							                			hp.setText("<div style=\'font-family:Ethiopic Unicode\'></div>");
 							                	//	else hp.setText(StringEscapeUtils.unescapeHtml(s));
 							                		final ChildFrame pbFrame=new ChildFrame("Add Special Division",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
 							                		//
 							                		
 							               		JLabel divNL=new JLabel("Division Name");
						                		JTextField divN=new JTextField(10);
						                		
						                	//	divZ.setText(calculateDivisionNumber(fidalWordList.getSelectedIndex()));0						                		
						                		JLabel divZEL=new JLabel("Division Number");
						                		JTextField divZE=new JTextField(10);
						                		//divZE.setEditable(false);
						                		JLabel divSTL=new JLabel("Style");
						                		JComboBox divST=new JComboBox();
						                		divST.addItem("-");
						                		divST.setMaximumRowCount(7);
						                		divST.addItem("-");
						                		divST.addItem("Bible");
						                		divST.addItem("Hagiography");
						                		divST.addItem("Chronicles");
						                		divST.addItem("Theology");
						                		divST.addItem("Apocrypha Pseudoepigrapha");
						                		divST.addItem("Amharic Literature");
						                		divST.addItem("Apocrypha");
						                		divST.addItem("Pseudoepigrapha");
						                		divST.addItem("Miracle");
						                		divST.addItem("New Testament");
						                		divST.addItem("Old Testament");
						                		divST.addItem("Beta Esrael Literature");
						                		divST.addItem("Biography");
						                		divST.addItem("Canon Law");
						                		divST.addItem("Commentary");
						                		divST.addItem("Christian Literature");
						                		divST.addItem("Chronography");
						                		divST.addItem("Crucifixion");
						                		divST.addItem("Fiction");
						                		divST.addItem("History and Historiography");
						                		divST.addItem("Homily");
						                		divST.addItem("Islamic Literature");
						                		divST.addItem("Koran");
						                		divST.addItem("Legal Document");
						                		divST.addItem("Liturgy");
						                		divST.addItem("Chants");
						                		divST.addItem("Lectionary");
						                		divST.addItem("Missal");
						                		divST.addItem("Rituals");
						                		divST.addItem("Rituals an Rites");
						                		divST.addItem("Christian Literature");
						                		divST.addItem("Tigrinya Literature");
						                		divST.addItem("Translation");
						                		divST.addItem("Vocabulary");
						                		divST.addItem("Magic");
						                		divST.addItem("Medicine");
						                		divST.addItem("Other");
						                		divST.addItem("Philosophy");
						                		divST.addItem("Poetry");
						                		divST.addItem("Prayers");
						                		divST.addItem("Religion");
						                		divST.addItem("Christian Content");
						                		
						                		JLabel divAutL=new JLabel("Creator");
						                		JComboBox divAut=new JComboBox();
						 
						                		divAut.addItem("Edition");
						                		divAut.addItem("Annotator");
						                        
						                		JLabel commL=new JLabel("Comments");
 							                		//
 							                		Container chp=pbFrame.getContentPane();
 							                		chp.setLayout(new GridBagLayout());
 							                		GridBagConstraints gbc=new GridBagConstraints();
 							                		gbc.gridx=0;
 							                		gbc.gridy=0;
 							                		gbc.weightx=100;
 							                		gbc.weighty=100;
 							                		gbc.insets.left=2;
 							                		gbc.insets.right=2;
 							                		gbc.insets.top=2;
 							                		gbc.gridwidth=1;
 							                		gbc.anchor=GridBagConstraints.NORTHWEST;
 							                		gbc.insets.bottom=2;
 							                		chp.add(divNL,gbc);
 							                		gbc.gridx=1; chp.add(divN,gbc);
 							                		gbc.gridy=1;gbc.gridx=0;
 							                		chp.add(divZEL,gbc);
 							                		gbc.gridx=1; chp.add(divZE,gbc);
 							                		gbc.gridy=2; gbc.gridx=0;
 							                		chp.add(divSTL,gbc);
 							                		gbc.gridx=1; chp.add(divST,gbc);
 							                		gbc.gridy=3;gbc.gridx=0;
 							                		chp.add(divAutL,gbc);gbc.gridx=1;
 							                		chp.add(divAut,gbc);
 							                		gbc.gridy=4; gbc.gridx=0;
 							                		chp.add(commL,gbc);
 							                		gbc.gridx=1;
 							                		chp.add(hp,gbc);
 							                		final JButton saveC=new JButton("Save");
 							                		final JButton cancelC=new JButton("Cancel");
 							                		gbc.gridy=5;gbc.gridx=0;
 							                		gbc.gridwidth=1;
 							                		gbc.anchor=GridBagConstraints.CENTER;
 							                		chp.add(saveC,gbc);
 							                		gbc.gridx=1;
 							                		chp.add(cancelC,gbc);
 							                		pbFrame.pack();
 							                		addChild(pbFrame,30,30,300,300);
 							                		pbFrame.pack();
 					        			               pbFrame.setVisible(true);
 					        			               pbFrame.moveToFront();
 					        			               saveC.addActionListener(new ActionListener(){
 										                	@Override public void actionPerformed(ActionEvent ae){
 										                		Division d=new Division(wsel.getId(),wsel.getId(),"SD"+UUID.randomUUID(), -1, -1, false);
 										                			//
 										                		d.setCreator(divAut.getSelectedItem().toString());
 										                		d.setGenre(divST.getSelectedItem().toString());
 										                		d.setName(divN.getText());
 										                		d.setNr(divZE.getText());
 										                		d.setComment(hp.getText());
 										                	
 										                		wsel.addSpecId(d.getId());specDivisions.add(d);
 										                		words.set(position, wsel);
 										               // 		System.out.println("Specifical divs "+wsel.getSpecDivs().size());
 										                		fidalWordList.revalidate();fidalWordList.repaint();
 										                		pbFrame.doDefaultCloseAction();
 										                	}
 					        			               });	
 					        			               cancelC.addActionListener(new ActionListener(){
 										                	@Override public void actionPerformed(ActionEvent ae){
 										                		
 										                		pbFrame.doDefaultCloseAction();
 										                	}
 					        			               });	
 							            	
 							            			
 							            			
 							            		//	System.out.println(nrLB);
 							            			
 							            			
 							            		
 							            			fidalWordList.repaint();fidalWordList.revalidate();
 							            		}
 							            		});
                                             
                                             
                                             
                                             //
                                              insertPageBreak.addActionListener(new ActionListener(){
							            		
							            		public void actionPerformed(ActionEvent e){
							            			//Tag t=new Tag("lb");
							            			final int position=fidalWordList.getSelectedIndex();
							            			final WortNode wsel=words.get(position);
							                		final HTMLEditorPane hp =new HTMLEditorPane();
							                		String s="";
							                		/*if(wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot()!=null){
							                			if(wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb")!=null) {
							                				s=wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().get(0).getValue();
							                			}
							                		}*/
							                		//hp.setText(StringEscapeUtils.unescapeHtml(words.get(position).getCooment()));
							                		//System.out.println("Comment"+ Jsoup.parse(hp.getText()).text()+"***");
							                		
							                		//if(s.isEmpty())
							                			hp.setText("<div style=\'font-family:Ethiopic Unicode\'></div>");
							                	//	else hp.setText(StringEscapeUtils.unescapeHtml(s));
							                		final ChildFrame pbFrame=new ChildFrame("Add Page Break",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
							                		Container chp=pbFrame.getContentPane();
							                		chp.setLayout(new GridBagLayout());
							                		GridBagConstraints gbc=new GridBagConstraints();
							                		gbc.gridx=0;
							                		gbc.gridy=0;
							                		gbc.weightx=100;
							                		gbc.weighty=100;
							                		gbc.insets.left=2;
							                		gbc.insets.right=2;
							                		gbc.insets.top=2;
							                		gbc.gridwidth=4;
							                		gbc.anchor=GridBagConstraints.NORTHWEST;
							                		gbc.insets.bottom=2;
							                		chp.add(hp,gbc);
							                		final JButton saveC=new JButton("Save");
							                		final JButton cancelC=new JButton("Cancel");
							                		final JRadioButton beforeB=new JRadioButton("Before");
							                		final JRadioButton afterB=new JRadioButton("After");
							                		ButtonGroup bgrp1=new ButtonGroup();
							                		bgrp1.add(beforeB);bgrp1.add(afterB);
							                		afterB.setSelected(true);
							                		gbc.gridy=1;gbc.gridx=0;
							                		gbc.gridwidth=1;
							                		gbc.anchor=GridBagConstraints.CENTER;
							                		chp.add(beforeB,gbc);
							                		gbc.gridx=1;
							                		chp.add(afterB,gbc);
							                		gbc.gridx=2;
							                		chp.add(saveC,gbc);
							                		gbc.gridx=3;
							                		chp.add(cancelC,gbc);
							                		pbFrame.pack();
							                		addChild(pbFrame,30,30,300,300);
							                		pbFrame.pack();
					        			               pbFrame.setVisible(true);
					        			               pbFrame.moveToFront();
					        			               saveC.addActionListener(new ActionListener(){
										                	@Override public void actionPerformed(ActionEvent ae){
										                		String b="+";
									            				if (beforeB.isSelected())b="-";
									            				if(b.equals("+")) {
										                		if (wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot()!=null){
										                		  if(wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb")!=null) {
										                		
										            				Attribut a=new Attribut("vb",b+hp.getText());
										            				wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().getSpecificTag("pb").getAttrList().add(a);
											                	
										                		}  
										                		  else {
										                			  
											            				ArrayList<Attribut> valLB=new ArrayList<Attribut>();
											            				
											            				Attribut a=new Attribut("vb",b+hp.getText());
											            				valLB.add(a);
											            				Tag t=new Tag("pb",valLB);
												            		
											            				wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).getEdAnnot().addTag(t);
										                		  }
										                		}
											            			else {
											            				ArrayList<Tag> tagLB=new ArrayList<Tag>();
											            				ArrayList<Attribut> valLB=new ArrayList<Attribut>();
											            				Attribut a=new Attribut("vb",b+hp.getText());
											            				valLB.add(a);
											            				Tag t=new Tag("pb",valLB);
												            			
											            				tagLB.add(t);
											            				Annotation lbannot=new Annotation(false,false,true,false,tagLB);
											            				wsel.getFidalChildren().get(wsel.getFidalChildren().size()-1).setEdAnnot(lbannot);
											            			}
									            				}
									            				else {
									            					if (wsel.getFidalChildren().get(0).getEdAnnot()!=null){
												                		  if(wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb")!=null) {
												                		
												            				Attribut a=new Attribut("vb",b+hp.getText());
												            				wsel.getFidalChildren().get(0).getEdAnnot().getSpecificTag("pb").getAttrList().add(a);
													                	
												                		}  
												                		  else {
												                			  
													            				ArrayList<Attribut> valLB=new ArrayList<Attribut>();
													            				
													            				Attribut a=new Attribut("vb",b+hp.getText());
													            				valLB.add(a);
													            				Tag t=new Tag("pb",valLB);
														            		
													            				wsel.getFidalChildren().get(0).getEdAnnot().addTag(t);
												                		  }
												                		}
													            			else {
													            				ArrayList<Tag> tagLB=new ArrayList<Tag>();
													            				ArrayList<Attribut> valLB=new ArrayList<Attribut>();
													            				Attribut a=new Attribut("vb",b+hp.getText());
													            				valLB.add(a);
													            				Tag t=new Tag("pb",valLB);
														            			
													            				tagLB.add(t);
													            				Annotation lbannot=new Annotation(false,false,true,false,tagLB);
													            				wsel.getFidalChildren().get(0).setEdAnnot(lbannot);
													            			}
									            				}
										                			//
										                		
										                		pbFrame.doDefaultCloseAction();
										                	}
					        			               });	
					        			               cancelC.addActionListener(new ActionListener(){
										                	@Override public void actionPerformed(ActionEvent ae){
										                		
										                		pbFrame.doDefaultCloseAction();
										                	}
					        			               });	
							            	
							            			
							            			
							            		//	System.out.println(nrLB);
							            			
							            			
							            		
							            			fidalWordList.repaint();fidalWordList.revalidate();
							            		}
							            		});
					                		//
					                		
					                		vizStructure.addActionListener(new ActionListener(){
							            		
							            		public void actionPerformed(ActionEvent e){
							            			
							            			 ListenableGraph g = new ListenableDirectedGraph( DefaultEdge.class );
							            		
							            			 jgAdapter = new JGraphModelAdapter( g );

							            			ConnectionSet cs=new ConnectionSet();
							            	       
							            		      g.addVertex(wselect);
							            		      positionVertexAt( wselect, 198, 10, 100,-1,cs );
							            		      for (int i=0;i<wselect.getFidalChildren().size();i++){
							            		    	  g.addVertex(wselect.getFidalChildren().get(i));
							            		    	 
							            		    	  g.addEdge(wselect,wselect.getFidalChildren().get(i));
							            		    	  cs.connect(wselect,wselect.getFidalChildren().get(i), true);
							            		    	  int dim2= (i*30)+600*i/wselect.getFidalChildren().size();
							            		    	  positionVertexAt( wselect.getFidalChildren().get(i), dim2, 80,30,-1,cs );
							            		    	 
							            		    	  for(int j=0;j<wselect.getFidalChildren().get(i).getTranslitChildren().size();j++){
							            		    		  LatinLetterNode childL=wselect.getFidalChildren().get(i).getTranslitChildren().get(j);
							            		        	  g.addVertex(childL);
							            		        	  g.addEdge(wselect.getFidalChildren().get(i),wselect.getFidalChildren().get(i).getTranslitChildren().get(j));
							            		        	  cs.connect(wselect.getFidalChildren().get(i),childL, true);
							            		        	  int col=-1;
							            		        	  if (wselect.getFidalChildren().get(i).getTranslitChildren().get(j).getTokenId().indexOf("T0")>=0) col=0;
							            		        	  else if (wselect.getFidalChildren().get(i).getTranslitChildren().get(j).getTokenId().indexOf("T1")>=0) col=1;
							            		        	  else if (wselect.getFidalChildren().get(i).getTranslitChildren().get(j).getTokenId().indexOf("T2")>=0) col=2;
							            		        	  else if (wselect.getFidalChildren().get(i).getTranslitChildren().get(j).getTokenId().indexOf("T3")>=0) col=3;
							            		        	  else if (wselect.getFidalChildren().get(i).getTranslitChildren().get(j).getTokenId().indexOf("T4")>=0) col=4;
							            		        	  else if (wselect.getFidalChildren().get(i).getTranslitChildren().get(j).getTokenId().indexOf("T5")>=0) col=5;
							            		        	  else if (wselect.getFidalChildren().get(i).getTranslitChildren().get(j).getTokenId().indexOf("T6")>=0) col=6;
							            		        	  else if (wselect.getFidalChildren().get(i).getTranslitChildren().get(j).getTokenId().indexOf("T7")>=0) col=7;
							            		        	  else if (wselect.getFidalChildren().get(i).getTranslitChildren().get(j).getTokenId().indexOf("T8")>=0) col=8;
							            		        	  positionVertexAt(wselect.getFidalChildren().get(i).getTranslitChildren().get(j), (dim2+j*40), 160, 30,col,cs );
							            		    	  }
							            		      
							            		      }
							            		      
							            		      final ChildFrame structFrame=new ChildFrame("Word Structure",mainFrameColor,WindowConstants.DO_NOTHING_ON_CLOSE);
							            		     
							            		      jgraph = new JGraph(jgAdapter);
							            		      jgraph.setFont(etiopic1);
							            		      jgraph.setForeground(Color.BLACK);
							            		      removeEdgeLabels();
							            		    
							            		      structFrame.getContentPane().add(jgraph);
							            		      structFrame.pack();
								            			structFrame.moveToFront();
								            			structFrame.pack();
								            			
								                		addChild(structFrame,100,100,700,700);
								                		structFrame.pack();
							   			               structFrame.setVisible(true);
							   			             structFrame.moveToFront();
							            		}
							            		  private void adjustDisplaySettings( JGraph jg, Color c1) {
							            		        jg.setPreferredSize( new Dimension( 700, 700 ) );

							            		        Color  c        = Color.decode( "#FAFBFF" );
							            		        String colorStr = null;

							            		       // try {
							            		         //   colorStr = getParameter( "bgcolor" );
							            		        //}
							            		         //catch( Exception e ) {}

							            		        if( colorStr != null ) {
							            		            c = Color.decode( colorStr );
							            		        }

							            		        jg.setBackground( c );
							            		     }


							            		    @SuppressWarnings({ "rawtypes", "unchecked" })
							            		    /**
							            		     * Set a position for the vertices
							            		     */
							            			private void positionVertexAt( Object vertex, int x, int y , int n,int col, ConnectionSet cs) {
							            		        DefaultGraphCell cell = jgAdapter.getVertexCell( vertex );
							            		      AttributeMap em=jgAdapter.getDefaultEdgeAttributes();
							            		        AttributeMap vertexAttributes=cell.getAttributes();
							            		        Rectangle2D rect=vertexAttributes.createRect(x,y,n,30);
							            		     
							            		        Map attr = cell.getAttributes();      
							            		        GraphConstants.setForeground(cell.getAttributes(), Color.BLACK);
							            		        GraphConstants.setBorderColor(cell.getAttributes(), Color.BLACK);
							            		        GraphConstants.setFont(cell.getAttributes(), etiopic1);
							            		        if(col==0) GraphConstants.setBackground(cell.getAttributes(), Color.YELLOW);
							            		        else if (col==1) GraphConstants.setBackground(cell.getAttributes(), Color.decode("#ff0061"));
							            		        else if (col==2) GraphConstants.setBackground(cell.getAttributes(), Color.decode("#98c1c1"));
							            		        else if (col==3) GraphConstants.setBackground(cell.getAttributes(), Color.GRAY);
							            		        else if (col==4) GraphConstants.setBackground(cell.getAttributes(), Color.GREEN);
							            		        else if(col==5) GraphConstants.setBackground(cell.getAttributes(), Color.ORANGE);
							            		        else if(col==6) GraphConstants.setBackground(cell.getAttributes(), Color.MAGENTA);
							            		        else if (col==7) GraphConstants.setBackground(cell.getAttributes(), Color.CYAN);
							            		        else if(col==8) GraphConstants.setBackground(cell.getAttributes(), Color.PINK);
							            		        else GraphConstants.setBackground(cell.getAttributes(), Color.decode("#f7ebff"));
							            		        
							            		        //GraphConstants.setOpaque(cell.getAttributes(), true);
							            		        GraphConstants.setBounds( attr,rect);
							            		        GraphConstants.setLabelAlongEdge(em,false);
							            		        Map cellAttr = new HashMap(  );
							            		        cellAttr.put( cell, attr );
							            		       

							            		        ParentMap m=new ParentMap();
							            		        jgAdapter.edit(cellAttr, cs, m, null);
							            		    }
							            		    
							            		    public void removeEdgeLabels() {
							            				GraphLayoutCache cache = jgraph.getGraphLayoutCache();
							            				CellView[] cells = cache.getCellViews();
							            				for (CellView cell : cells) {
							            					if (cell instanceof EdgeView) {
							            						EdgeView ev = (EdgeView) cell;
							            						DefaultEdge eval = (DefaultEdge) ev.getCell();
							            						eval.setUserObject("");
							            					}
							            				}
							            				cache.reload();
							            				jgraph.repaint();
							            			}
							            		
							            		});
					                		
					                		for(int i=0;i<menuFLetters3.size();i++){
							            		final int pos=i;
							            		
							            		
							            		menuFLetters3.get(pos).addActionListener(new ActionListener(){
							            		
							            		public void actionPerformed(ActionEvent e){
							            		
							            			//if(wselect.getFidalChildren().get(pos).getEdAnnot()!=null){
							            				Tag t=wselect.getFidalChildren().get(pos).getEdAnnot().getSpecificTag("lb");
							            				wselect.getFidalChildren().get(pos).getEdAnnot().removeTag(t);
							            				int addit=1;
								            			for(int k=pos+1;k<wselect.getFidalChildren().size();k++)
							            					if(wselect.getFidalChildren().get(k).getEdAnnot()!=null)
							            				    if(wselect.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb")!=null){
							            					nrLB=Integer.parseInt(wselect.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).getValue());
							            					wselect.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).setValue(""+(nrLB-1));
							            					addit=addit+1;
							            				}
							            				int j=indexIdWord.get(wselect.getId()).intValue()+1;
								            			while(j<words.size()){
								            				WortNode w=words.get(j);
								            				for(int k=0;k<w.getFidalChildren().size();k++)
								            					if(w.getFidalChildren().get(k).getEdAnnot()!=null)
								            				    if(w.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb")!=null){
								            					nrLB=Integer.parseInt(w.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).getValue());
								            					w.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).setValue(""+(nrLB-addit));
								            				}
								            			   j=j+1;
								            			}
							            			//}
							            		
							            			fidalWordList.repaint();fidalWordList.revalidate();
							            		}
							            		});
					                		}		
					                		
					                		for(int i=0;i<menuFLetters2_1.size();i++){
							            		final int pos=i;
							            		
							            		
							            		menuFLetters2_1.get(pos).addActionListener(new ActionListener(){
							            		
							            		public void actionPerformed(ActionEvent e){
							            			//Tag t=new Tag("lb");
							            			int j=0;
							            			//nrLB=0;
							            		
							            			while(j<indexIdWord.get(wselect.getId()).intValue()){
							            			
							            				WortNode w=words.get(j);
							            			//	System.out.println(w.getFidalLabel(typScript,typDoc));
							            				for(int k=0;k<w.getFidalChildren().size();k++)
							            					if(w.getFidalChildren().get(k).getEdAnnot()!=null)
							            				    if(w.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb")!=null){
							            				    //	System.out.println(w.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).getValue());
							            					     nrLB=Integer.parseInt(w.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).getValue());
							            				    }
							            					     j=j+1;
							            			}
							            		//	System.out.println(nrLB);
							            			int addit=0;
							            			for(int k=0;k<wselect.getFidalChildren().size()-1;k++)
						            					if(wselect.getFidalChildren().get(k).getEdAnnot()!=null)
						            				    if(wselect.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb")!=null){
						            					nrLB=Integer.parseInt(wselect.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).getValue());
						            					//wselect.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).setValue(""+(nrLB+1));
						            					addit=addit+1;
						            				}
							            			ArrayList<Attribut> valLB=new ArrayList<Attribut>();
						            				Attribut a=new Attribut("vl",""+(nrLB+addit));
						            				valLB.add(a);
						            				Tag t=new Tag("lb",valLB);
							            			if(wselect.getFidalChildren().get(pos).getEdAnnot()!=null){
							            				
							            				wselect.getFidalChildren().get(pos).getEdAnnot().addTag(t);
							            			}
							            			else {
							            				ArrayList<Tag> tagLB=new ArrayList<Tag>();
							            				tagLB.add(t);
							            				Annotation lbannot=new Annotation(false,false,true,false,tagLB);
							            				wselect.getFidalChildren().get(pos).setEdAnnot(lbannot);
							            			}
							            			wselect.getFidalChildren().get(pos).setPosLB(1);
							            			j=indexIdWord.get(wselect.getId()).intValue()+1;
							            			while(j<words.size()){
							            				WortNode w=words.get(j);
							            				for(int k=0;k<w.getFidalChildren().size();k++)
							            					if(w.getFidalChildren().get(k).getEdAnnot()!=null)
							            				    if(w.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb")!=null){
							            					nrLB=Integer.parseInt(w.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).getValue());
							            					w.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).setValue(""+(nrLB+addit));
							            				}
							            			   j=j+1;
							            			}
							            			fidalWordList.repaint();fidalWordList.revalidate();
							            		}
							            		});
					                		}		
					                		
					                		
					                		for(int i=0;i<menuFLetters2.size();i++){
							            		final int pos=i;
							            		
							            		
							            		menuFLetters2.get(pos).addActionListener(new ActionListener(){
							            		
							            		public void actionPerformed(ActionEvent e){
							            			//Tag t=new Tag("lb");
							            			int j=0;
							            		//	nrLB=0;
							            			while(j<indexIdWord.get(wselect.getId()).intValue()){
							            			
							            				WortNode w=words.get(j);
							            			//	System.out.println(w.getFidalLabel(typScript,typDoc));
							            				for(int k=0;k<w.getFidalChildren().size();k++)
							            					if(w.getFidalChildren().get(k).getEdAnnot()!=null)
							            				    if(w.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb")!=null){
							            				    //	System.out.println(w.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).getValue());
							            					     nrLB=Integer.parseInt(w.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).getValue());
							            				    }
							            					     j=j+1;
							            			}
							            			//System.out.println(nrLB);
							            			ArrayList<Attribut> valLB=new ArrayList<Attribut>();
						            				Attribut a=new Attribut("vl",""+(nrLB+1));
						            				valLB.add(a);
						            				Tag t=new Tag("lb",valLB);
							            			if(wselect.getFidalChildren().get(pos).getEdAnnot()!=null){
							            				
							            				wselect.getFidalChildren().get(pos).getEdAnnot().addTag(t);
							            			}
							            			else {
							            				ArrayList<Tag> tagLB=new ArrayList<Tag>();
							            				tagLB.add(t);
							            				Annotation lbannot=new Annotation(false,false,true,false,tagLB);
							            				wselect.getFidalChildren().get(pos).setEdAnnot(lbannot);
							            			}
							            			int addit=0;
							            			for(int k=pos+1;k<wselect.getFidalChildren().size();k++)
						            					if(wselect.getFidalChildren().get(k).getEdAnnot()!=null)
						            				    if(wselect.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb")!=null){
						            					nrLB=Integer.parseInt(wselect.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).getValue());
						            					wselect.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).setValue(""+(nrLB+1));
						            					addit=addit+1;
						            				}
							            			j=indexIdWord.get(wselect.getId()).intValue()+1;
							            			while(j<words.size()){
							            				WortNode w=words.get(j);
							            				for(int k=0;k<w.getFidalChildren().size();k++)
							            					if(w.getFidalChildren().get(k).getEdAnnot()!=null)
							            				    if(w.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb")!=null){
							            					nrLB=Integer.parseInt(w.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).getValue());
							            					w.getFidalChildren().get(k).getEdAnnot().getSpecificTag("lb").getAttrList().get(0).setValue(""+(nrLB+addit+1));
							            				}
							            			   j=j+1;
							            			}
							            			fidalWordList.repaint();fidalWordList.revalidate();
							            		}
							            		});
					                		}		
					                		
					                		
					                		for(int i=0;i<menuFLetters1.size();i++){
							            		final int pos=i;
							            		
							            		
							            		menuFLetters1.get(pos).addActionListener(new ActionListener(){
							            		
							            		public void actionPerformed(ActionEvent e){
							            			final ChildFrame editionFrame1=new ChildFrame("Letter Separator",mainFrameColor,WindowConstants.DO_NOTHING_ON_CLOSE);
							            			Container c=editionFrame1.getContentPane();
							            			c.setLayout(new GridBagLayout());
							            			GridBagConstraints gbc2=new GridBagConstraints();
							            			gbc2.gridx=0; gbc2.gridy=0;
							            			gbc2.gridwidth=1;gbc2.gridheight=1;
							            			gbc2.weightx=100;gbc2.weighty=100;
							            			gbc2.insets.top=2;gbc2.insets.bottom=2;gbc2.insets.left=2;gbc2.insets.right=2;
							            			gbc2.anchor=GridBagConstraints.NORTHWEST;
							            			gbc2.fill=GridBagConstraints.NONE;
							            			final JComboBox bracketsL=new JComboBox();
							            			
							            			bracketsL.addItem("none");
							            			bracketsL.addItem("[");bracketsL.addItem("(");bracketsL.addItem("{");bracketsL.addItem("<");
							            		//	System.out.println(wselect.getFidalChildren().get(pos).getEndSymbol().indexOf('\u1361'));
							            			if(wselect.getFidalChildren().get(pos).getEndSymbol().indexOf('\u1361')<=0)
							            			bracketsL.setSelectedIndex(0);
							            			else {
							            				//System.out.println("Symbol"+StringEscapeUtils.unescapeHtml(wselect.getFidalChildren().get(pos).getEndSymbol()));
							            				bracketsL.setSelectedItem(""+StringEscapeUtils.unescapeHtml(wselect.getFidalChildren().get(pos).getEndSymbol()).charAt(0));
							            			}
							            			
							            
							            			final JComboBox bracketsR=new JComboBox();
							            			bracketsR.addItem("none");
							            			bracketsR.addItem("]");bracketsR.addItem(")");bracketsR.addItem("}");bracketsR.addItem(">");
							            			if((wselect.getFidalChildren().get(pos).getEndSymbol().indexOf('\u1361')<0)||(wselect.getFidalChildren().get(pos).getEndSymbol().indexOf('\u1361')==wselect.getFidalChildren().get(pos).getEndSymbol().length()-1))
							            			bracketsR.setSelectedIndex(0);
							            			else if((wselect.getFidalChildren().get(pos).getEdEndSymbol().indexOf('\u1361')>0)||(wselect.getFidalChildren().get(pos).getEndSymbol().indexOf('\u1361')<wselect.getFidalChildren().get(pos).getEndSymbol().length()-1)) 
							            				bracketsR.setSelectedItem(""+StringEscapeUtils.unescapeHtml(wselect.getFidalChildren().get(pos).getEndSymbol()).charAt(StringEscapeUtils.unescapeHtml(wselect.getFidalChildren().get(pos).getEndSymbol()).length()-1));
							            			JLabel leftB=new JLabel("Before");
							            			JLabel rightB=new JLabel("After");
							            			c.add(leftB,gbc2); gbc2.gridx=1; c.add(rightB,gbc2);
							            			gbc2.gridx=0;gbc2.gridy=1;
							            			c.add(bracketsL,gbc2);
							            			gbc2.gridx=1;
							            			c.add(bracketsR,gbc2);
							            			final HTMLEditorPane hp =new HTMLEditorPane();
							            			if(!wselect.getFidalChildren().get(pos).hasEnding())
							            				hp.setText("<div style=\'font-family:Ethiopic Unicode\'></div>");
							            			else
							            				hp.setText("<div style=\'font-family:Ethiopic Unicode\'>"+"\u1361"+"</div>");
							                		//System.out.println("Comment"+ Jsoup.parse(hp.getText()).text()+"***");
							                		
							                	//	if(Jsoup.parse(hp.getText()).text().isEmpty())
							                		//	hp.setText("<div style=\'font-family:Ethiopic Unicode\'></div>");
							            			gbc2.gridwidth=2; gbc2.fill=GridBagConstraints.HORIZONTAL;
							            			gbc2.gridy=2;gbc2.gridx=0;
							            			c.add(hp,gbc2);
							            			gbc2.gridy=3;gbc2.gridx=0;
							            			gbc2.gridwidth=1;
							            			gbc2.fill=GridBagConstraints.NONE;
							            			gbc2.anchor=GridBagConstraints.CENTER;
							            			
							            			final JButton okE=new JButton("OK");
							            			final JButton cancelE=new JButton("Cancel");
							            			c.add(okE,gbc2);
							            			gbc2.gridx=1;
							            			c.add(cancelE,gbc2);
							            			
							            			okE.addActionListener(new ActionListener(){
							            				public void actionPerformed(ActionEvent e){
							            					String bb=bracketsL.getSelectedItem().toString();
							            					String ab=bracketsR.getSelectedItem().toString();
							            					if (bb.equals("none")) bb="";
							            					if(ab.equals("none")) ab="";
							            				//	System.out.println("Text "+ Jsoup.parse(hp.getText()).text());
							            					if( Jsoup.parse(hp.getText()).text().equals("\u1361")){
							            					
							            						if(!wselect.getFidalChildren().get(pos).hasEnding()){
							            							wselect.getFidalChildren().get(pos).setHasEnding(true);
							            							wselect.getFidalChildren().get(pos).setEndSymbol(StringEscapeUtils.escapeHtml(bb)+"\u1361"+StringEscapeUtils.escapeHtml(ab));
							            							fidalWordList.repaint();fidalWordList.revalidate();
									            					editionFrame1.doDefaultCloseAction();
							            						}
							            						
							            						else {
							            							
							            							wselect.getFidalChildren().get(pos).setEndSymbol(StringEscapeUtils.escapeHtml(bb)+"\u1361"+StringEscapeUtils.escapeHtml(ab));
							            							//System.out.println(bb+"\u1361"+ab);
							            							fidalWordList.repaint();fidalWordList.revalidate();
									            					editionFrame1.doDefaultCloseAction();
							            						}
							            				}
							            					else if(Jsoup.parse(hp.getText()).text().isEmpty()){
						            							wselect.getFidalChildren().get(pos).setHasEnding(false);
						            							
						            							fidalWordList.repaint();fidalWordList.revalidate();
								            					editionFrame1.doDefaultCloseAction();
						            						}
							            					else {
						            							JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" >Please either insert"+ "\u1361"+ "or remove it. No other character is allowed</font></p></html>", "Dialog",
											         		    		      JOptionPane.ERROR_MESSAGE); 
						            						}
							            				}
							            			});
							            			cancelE.addActionListener(new ActionListener(){
							            				public void actionPerformed(ActionEvent e){
							            					editionFrame1.doDefaultCloseAction();
							            				}
							            			});
							            				
							            			
							            			editionFrame1.pack();
							            			editionFrame1.moveToFront();
							            			editionFrame1.pack();
							                		addChild(editionFrame1,100,100,550,200);
							                		editionFrame1.pack();
						   			               editionFrame1.setVisible(true);
						   			             editionFrame1.moveToFront();
							            		}
							            		});
							            }
					                		
						           
					                		for(int i=0;i<menuFLetters.size();i++){
							            		final int pos=i;
							            		
							            		
							            		menuFLetters.get(pos).addActionListener(new ActionListener(){
							            		
							            		public void actionPerformed(ActionEvent e){
							            			final ChildFrame editionFrame=new ChildFrame("Edition",mainFrameColor,WindowConstants.DO_NOTHING_ON_CLOSE);
							            			Container c=editionFrame.getContentPane();
							            			c.setLayout(new GridBagLayout());
							            			GridBagConstraints gbc2=new GridBagConstraints();
							            			gbc2.gridx=0; gbc2.gridy=0;
							            			gbc2.gridwidth=1;gbc2.gridheight=1;
							            			gbc2.weightx=100;gbc2.weighty=100;
							            			gbc2.insets.top=2;gbc2.insets.bottom=2;gbc2.insets.left=2;gbc2.insets.right=2;
							            			gbc2.anchor=GridBagConstraints.NORTHWEST;
							            			gbc2.fill=GridBagConstraints.NONE;
							            			final JComboBox bracketsL=new JComboBox();
							            			
							            			bracketsL.addItem("none");
							            			bracketsL.addItem("[");bracketsL.addItem("(");bracketsL.addItem("{");bracketsL.addItem("<");
							            			if(wselect.getFidalChildren().get(pos).hasLeftED()!=null)
							            			  if(wselect.getFidalChildren().get(pos).hasLeftED().equals(""))
							            			     bracketsL.setSelectedIndex(0);
							            			   else bracketsL.setSelectedItem(wselect.getFidalChildren().get(pos).hasLeftED());
							            			else  bracketsL.setSelectedIndex(0);
							            			final JComboBox bracketsR=new JComboBox();
							            			bracketsR.addItem("none");
							            			bracketsR.addItem("]");bracketsR.addItem(")");bracketsR.addItem("}");bracketsR.addItem(">");
							            			if(wselect.getFidalChildren().get(pos).hasRightED()!=null)
							            			   if(wselect.getFidalChildren().get(pos).hasRightED().equals(""))
							            			      bracketsR.setSelectedIndex(0);
							            			    else bracketsR.setSelectedItem(wselect.getFidalChildren().get(pos).hasRightED());
							            			else  bracketsR.setSelectedIndex(0);
							            			JLabel leftB=new JLabel("Before");
							            			JLabel rightB=new JLabel("After");
							            			c.add(leftB,gbc2); gbc2.gridx=1; c.add(rightB,gbc2);
							            			gbc2.gridx=0;gbc2.gridy=1;
							            			c.add(bracketsL,gbc2);
							            			gbc2.gridx=1;
							            			c.add(bracketsR,gbc2);
							            			final HTMLEditorPane hp =new HTMLEditorPane();
							            			if(wselect.getFidalChildren().get(pos).hasCommentED().equals(""))
							                		hp.setText("");
							            			else
							            				hp.setText(StringEscapeUtils.unescapeHtml(wselect.getFidalChildren().get(pos).hasCommentED()));
							                		//System.out.println("Comment"+ Jsoup.parse(hp.getText()).text()+"***");
							                		
							                		if(Jsoup.parse(hp.getText()).text().isEmpty())
							                			hp.setText("<div style=\'font-family:Ethiopic Unicode\'></div>");
							            			gbc2.gridwidth=2; gbc2.fill=GridBagConstraints.HORIZONTAL;
							            			gbc2.gridy=2;gbc2.gridx=0;
							            			c.add(hp,gbc2);
							            			gbc2.gridy=3;gbc2.gridx=0;
							            			gbc2.gridwidth=1;
							            			gbc2.fill=GridBagConstraints.NONE;
							            			gbc2.anchor=GridBagConstraints.CENTER;
							            			
							            			final JButton okE=new JButton("OK");
							            			final JButton cancelE=new JButton("Cancel");
							            			c.add(okE,gbc2);
							            			gbc2.gridx=1;
							            			c.add(cancelE,gbc2);
							            			
							            			okE.addActionListener(new ActionListener(){
							            				public void actionPerformed(ActionEvent e){
							            					String bb=bracketsL.getSelectedItem().toString();
							            					String ab=bracketsR.getSelectedItem().toString();
							            					ArrayList<Attribut> edAttr=new ArrayList<Attribut>();
							            					if(!bb.equals("none")){
							            						Attribut a1=new Attribut("b",bb);
							            						edAttr.add(a1);
							            					}
							            					if(!ab.equals("none")){
							            						Attribut a2=new Attribut("a",ab);
							            						edAttr.add(a2);
							            					}
							            					if(!hp.getText().isEmpty()){
							            						Attribut a1=new Attribut("c",StringEscapeUtils.unescapeHtml(hp.getText()));
							            						edAttr.add(a1);
							            					}
							            					Tag t=new Tag("ed",edAttr);
							            					ArrayList<Tag> tList=new ArrayList<Tag>();
							            					tList.add(t);
							            					Annotation edAn=new Annotation(false, false, true,false,tList);
							            					wselect.getFidalChildren().get(pos).setEdAnnot(edAn);
							            					fidalWordList.repaint();fidalWordList.revalidate();
							            					editionFrame.doDefaultCloseAction();
							            				}
							            			});
							            			cancelE.addActionListener(new ActionListener(){
							            				public void actionPerformed(ActionEvent e){
							            					editionFrame.doDefaultCloseAction();
							            				}
							            			});
							            				
							            			
							            			editionFrame.pack();
							            			editionFrame.moveToFront();
							            			editionFrame.pack();
							                		addChild(editionFrame,100,100,550,200);
							                		editionFrame.pack();
						   			               editionFrame.setVisible(true);
						   			             editionFrame.moveToFront();
							            		}
							            		});
							            }
						            fidalWordList.addMouseListener(new MouseAdapter() {
					                    public void mouseClicked(MouseEvent me) {
					                       if (SwingUtilities.isRightMouseButton(me)    // if right mouse button clicked
					                             && !fidalWordList.isSelectionEmpty()            // and list selection is not empty
					                             && (fidalWordList.locationToIndex(me.getPoint()) // and clicked point is
					                                >= fidalWordList.getSelectedIndices()[0])
					                               && ( fidalWordList.locationToIndex(me.getPoint()) // and clicked point is
							                                <= fidalWordList.getSelectedIndices()[fidalWordList.getSelectedIndices().length-1]) 		
					                    		   ) {       //   itnside selected item bounds
					                    	   popupMenu.show(fidalWordList, me.getX(), me.getY());
					                       }
					                     
					                    }
					                 });
						            }
						            }
			    		 }
			    		 });
						
 	
		   //calculate division number
		        /* public String calculateDivisionNumber(int n){
		        	 int nr=0;
		        	 WortNode wn=words.get(n);
		        	 if (wn.getStrukturIds()!=null){
		        	    for (int i=0; i<wn.getStrukturIds().size();i++){
		        	    	int ns=""+divisions.get(indexIdDivision.get(wn.getStrukturIds().get(i))).getLevel();
		        	    	if ns>nr
		        	    }
		        	 }
		         }*/
	      //menus for right click
		          
		          translitWordList.addMouseListener(new MouseAdapter() {
					   public void mousePressed(MouseEvent e)
			    		{
			    			if (e.isPopupTrigger())
			    				popupTriggered(e);
			    		}

			    		@Override
			    		public void mouseReleased(MouseEvent e)
			    		{
			    			if (e.isPopupTrigger())
			    				popupTriggered(e);
			    		}
			    		
			    		 public void popupTriggered(MouseEvent ev) {
							   
							  //public void mousePressed(MouseEvent ev) {
						            if (ev.isPopupTrigger()) {
						            	 final int [] indexes= translitWordList.getSelectedIndices();
						            	 final WortNode wselect = words.get(indexes[0]);
						            isSaved=false; saveFile.setEnabled(true);
						          //     System.out.println("yes");
						            	if(!wselect.getFidalLabel(typScript,typDoc).equals("\u204b")){ 	 
						            	final JPopupMenu popupMenu = new JPopupMenu();
						            	final JMenu jmmod= new JMenu("Edit Transliteration");
						
						            	final ArrayList<JMenuItem> menuTokens=new ArrayList<JMenuItem>();
					                	
					           
					                	
				                	//	final WortNode wselect = words.get(translitWordList.getSelectedIndex());
				                		for (int i=0;i<wselect.getTokenIds().size();i++){
				                			
				                			JMenuItem men1=new JMenuItem(tokens.get(indexIdToken.get(wselect.getTokenIds().get(i)).intValue()).getLabel());
				                			men1.setFont(etiopicMenu1);
				                			menuTokens.add(men1);
				                			jmmod.add(men1);
				           
				                		
				                			
				                		}
				                		
				                		
						            	for(int i=0;i<menuTokens.size();i++){
						            		final int pos=i;
						            		
						            		
						            		menuTokens.get(pos).addActionListener(new ActionListener(){
						            		
						            		public void actionPerformed(ActionEvent e){
						            			//System.out.println("wselect "+wselect.getTranslitInternLabel(typScript)+" ID "+wselect.getId());
						            			final Token t= tokens.get(indexIdToken.get(wselect.getTokenIds().get(pos)).intValue());
						            			 final String walt=wselect.getFidalInternLabel(typScript,typDoc)+"*"+wselect.getTranslitLabel(typScript);
						            			 final String talt=t.getLabel();	
						            			tokenNewLabel=""; 
						            			tokenNLabel.clear();
						            			tokenALabel.clear();
						            			
						            			wordNLabel.clear();
						            			wordALabel.clear();
						            			wordsBackup1.clear();tokensBackup1.clear();
						            			for(int i=0;i<words.size();i++) {
						            				wordsBackup1.add(words.get(i).copyWortNode());
						            			}
						            			for(int i=0;i<tokens.size();i++) {
						            				tokensBackup1.add(tokens.get(i).copyToken());
						            			}
						            			nrop=0;
						            		trmodel=new LetterListModel();
					                		for(int j=0;j<t.getLetters().size();j++){
						                		 trmodel.addElement(t.getLetters().get(j));
						                		}
					                		
					                		 tlist=new JList();
					                		
					                		tlist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
					                   		tlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					                   		tlist.setCellRenderer( new MyLetterListRenderer());
					                   		tlist.setVisibleRowCount(1);
					                   		tlist.setFixedCellWidth(50);
					                   		tlist.setFont(etiopicText);
					                   		tlist.setModel(trmodel);
					                   		
					                   		 
					                   		
					                		
					                   		final JScrollPane ptlist= new JScrollPane(tlist);
					                		final ChildFrame modifyFrame=new ChildFrame("Modify",mainFrameColor,WindowConstants.DO_NOTHING_ON_CLOSE);
					                //	modifyFrame.setClosable(false);
					                final Container c =modifyFrame.getContentPane();
					                final JButton modif= new JButton("Modify");
					                modif.setEnabled(false);
					                final JButton iSchwa= new JButton("Insert \u01dd");
					                iSchwa.setEnabled(false);
					                final JButton dSchwa= new JButton("Delete \u01dd");
					                dSchwa.setEnabled(false);
					                final JButton iGemm= new JButton("Geminate");
					                iGemm.setEnabled(false);
					                final JButton dGemm= new JButton("Remove Gemination");
					                dGemm.setEnabled(false);
					                final JRadioButton global = new JRadioButton("Global");
					                global.setSelected(false);
					                final JRadioButton local = new JRadioButton("Local");
	                                 local.setSelected(false);
	                                 final JRadioButton lg = new JRadioButton("nichts");
	                                 lg.setSelected(true);
	                                 ButtonGroup bgrp=new ButtonGroup();
	                                 bgrp.add(local);bgrp.add(global);bgrp.add(lg);
	                                 tlist.setEnabled(false);
					                final JButton cancel = new JButton("Cancel");
					                JLabel wordLabel=new JLabel("Token");
					                final JPanel c1=new JPanel(new GridBagLayout());
					                GridBagConstraints gbc1=new GridBagConstraints();
					                gbc1.gridx=0;
			                		gbc1.weightx=100;
			                		gbc1.weighty=100;
			                		gbc1.gridy=0;
			                		gbc1.insets.left=2;
			                		gbc1.insets.right=2;
			                		gbc1.anchor=GridBagConstraints.CENTER;
			                 		gbc1.insets.top=2;
			                		gbc1.insets.bottom=2;
			                		gbc1.fill=GridBagConstraints.NONE;
			                		gbc1.gridwidth=3;
					                c1.add(wordLabel,gbc1);
					                gbc1.gridy=1;
					                c1.add(ptlist,gbc1);
					                gbc1.gridy=2;
					                gbc1.gridwidth=3;
					                gbc1.anchor=GridBagConstraints.NORTHWEST;
					                c1.add(iSchwa,gbc1);
					                gbc1.gridx=2;
					                gbc1.insets.right=0;
					                c1.add(dSchwa,gbc1);
					                
					                gbc1.gridy=3;
					                gbc1.gridx=0;
					                gbc1.gridwidth=3;
					                gbc1.anchor=GridBagConstraints.NORTHWEST;
					                c1.add(iGemm,gbc1);
					                gbc1.gridx=2;
					                gbc1.insets.right=0;
					                c1.add(dGemm,gbc1);
					                
					                gbc1.insets.right=2;
					                gbc1.gridy=4;
					                gbc1.gridwidth=1;
					                gbc1.gridx=0;
					                gbc1.anchor=GridBagConstraints.NORTHWEST;
					                c1.add(global,gbc1);
					                gbc1.insets.right=0;
					                gbc1.gridx=1;
					                c1.add(local,gbc1);
					                gbc1.gridx=2;
					                c1.add(modif,gbc1);
					                gbc1.gridx=3;
					                c1.add(cancel,gbc1);
			                		c.add(c1);
			                		modifyFrame.pack();
			                		addChild(modifyFrame,100,100,550,200);
			                		modifyFrame.pack();
		   			               modifyFrame.setVisible(true);
		   			             modifyFrame.moveToFront();
		   			              posSel=new ArrayList<Integer>();
		   			              counttoken=0;
		   			         //  wselEdit=new ArrayList<String>();tselEdit=new ArrayList<String>();
		   			           global.addItemListener(new ItemListener(){
		                			public void itemStateChanged(ItemEvent e){
		                				if (global.isSelected()){
		                					wselEdit.clear();tselEdit.clear();
		                					wselEdit1.clear();
		                					tselEdit1.clear();
		                					wordsSel.clear();tokenSel.clear();
		                					//for(int kk=0;kk<posSelW.size();kk++) {
		                						//posSelW_copy
		                					//}
		                					posSelW.clear();posSelT.clear();
		                				tlist.setEnabled(true);
		                				local.setEnabled(false);global.setEnabled(false);
		                				
		                				int k=0;
		                				for(int i=0;i<indexT.get(t.getLabel()).size();i++){
		                					String s1=indexT.get(t.getLabel()).get(i);
		                					tselEdit.add(s1);
		                					tokenALabel.add(talt);
		                					tokenNLabel.add(talt);
		                					 Token tt=(Token)tokens.get(indexIdToken.get(s1).intValue());
			                				   tokenSel.add(new Token(tt.copyToken()));
			                				   posSelT.add(indexIdToken.get(s1));
		                				}
		                				for (int i=0;i<tselEdit.size();i++){
		                					int k1=0; boolean found=false;
		                					String s=tselEdit.get(i).substring(tselEdit.get(i).indexOf(">")+1);
		                					while(k1<wselEdit.size() &&(!found)){
		                						if(wselEdit.get(k).equals(s)) found=true;
		                						else k1=k1+1;
		                					}
		                					if (!found) wselEdit.add(s);
		                				}
		                				for (int i=0;i<wselEdit.size();i++){
		                					wordALabel.add(words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getFidalInternLabel(typScript,typDoc)+"*"+words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getTranslitLabel(typScript));
		                					wordNLabel.add(words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getFidalInternLabel(typScript,typDoc)+"*"+words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getTranslitLabel(typScript));
		                					 posSelW.add(indexIdWord.get(wselEdit.get(i)));
		                				}	                				
		                				}
		                			}
		                		});
		   			       local.addItemListener(new ItemListener(){
	                			public void itemStateChanged(ItemEvent e){
	                				if (local.isSelected()){
	                				tlist.setEnabled(true);local.setEnabled(false);global.setEnabled(false);
	                				wselEdit.clear();tselEdit.clear();
	                				wselEdit1.clear();
	                				tselEdit1.clear();
	                				posSelW.clear();posSelT.clear();
	                				wordsSel.clear();tokenSel.clear();
	                				wselEdit.add(wselect.getId());tselEdit.add(t.getId());
	                				wselEdit1.add(wselect.getId());tselEdit1.add(t.getId());
	                				tokenALabel.add(talt);
	                				tokenNLabel.add(talt);
	                				wordALabel.add(words.get(indexIdWord.get(wselect.getId()).intValue()).getFidalInternLabel(typScript,typDoc)+"*"+words.get(indexIdWord.get(wselect.getId()).intValue()).getTranslitLabel(typScript));
                					wordNLabel.add(words.get(indexIdWord.get(wselect.getId()).intValue()).getFidalInternLabel(typScript,typDoc)+"*"+words.get(indexIdWord.get(wselect.getId()).intValue()).getTranslitLabel(typScript));
                				
	                				//cancel.setEnabled(false);
	                				
	                				wordsSel.add(words.get(indexIdWord.get(wselEdit.get(0)).intValue()).copyWortNode());
	                				   posSelW.add(indexIdWord.get(wselEdit.get(0)));
	                				   tokenSel.add(tokens.get(indexIdToken.get(tselEdit.get(0)).intValue()).copyToken());
	                				   posSelT.add(indexIdToken.get(tselEdit.get(0)));
	                				
	                				}
	                			}
	                		});
		   			       	tlist.addMouseListener(new MouseAdapter() {
		     					
		     			    		 public void mouseClicked(MouseEvent ev) {
		     							  
		     							  //public void mousePressed(MouseEvent ev) {
		     						            if (tlist.isEnabled()) {
		     						            	char c=((LatinLetterNode) trmodel.get(tlist.getSelectedIndex())).getLetter();
		     						            	if (geez.specialSymbols.contains(""+c)){
		     						            		iGemm.setEnabled(false); dGemm.setEnabled(false);
		     						            		iSchwa.setEnabled(false); dSchwa.setEnabled(false);
		     						            	}
		     						            	else if(c>='a'){
		     						            	if((c!='a')&&(c!='\u01dd')&&(c!='\u0101')&&(c!='e')&&(c!='u')&&(c!='o')&&(c!='i')){
		     						            		
		     						            		if(tlist.getSelectedIndex()<trmodel.size()-1){
		     						            			char c1=((LatinLetterNode) trmodel.get(tlist.getSelectedIndex()+1)).getLetter();
		     						            			if (c1!=c) {
		     						            				if(tlist.getSelectedIndex()==0){
		     						            				iGemm.setEnabled(true); dGemm.setEnabled(false);}
		     						            				else{
		     						            					char c2=((LatinLetterNode) trmodel.get(tlist.getSelectedIndex()-1)).getLetter();
		     						            					if(c2!=c){iGemm.setEnabled(true); dGemm.setEnabled(false);}
		     						            					else {iGemm.setEnabled(false); dGemm.setEnabled(false);}
		     						            				}
		     						            				if((c1!='a')&&(c1!='\u01dd')&&(c1!='\u0101')&&(c1!='e')&&(c1!='u')&&(c1!='o')&&(c1!='i')) {
		     						            					if((tlist.getSelectedIndex()+1)<(trmodel.size()-1)) iSchwa.setEnabled(false);
		     						            					iSchwa.setEnabled(true);
		     						            					dSchwa.setEnabled(false);
		     						            				}
		     						            				else{
		     						            					iSchwa.setEnabled(false);
		     						            					dSchwa.setEnabled(false);
		     						            				}
		     						            			}
		     						            			else {dGemm.setEnabled(true);iGemm.setEnabled(false);iSchwa.setEnabled(false);dSchwa.setEnabled(false);}
		     						            		}
		     						            		else{
		     						            			if(trmodel.size()>1){
		     						            			char c1=((LatinLetterNode) trmodel.get(tlist.getSelectedIndex()-1)).getLetter();
		     						            			if(c1!=c){iGemm.setEnabled(true);dGemm.setEnabled(false);}
		     						            			else{iGemm.setEnabled(false);
		     						            			     dGemm.setEnabled(true);
		     						            			     }
		     						            			}
		     						            			iSchwa.setEnabled(true);
		     						            		}
		     						            	}
		     						            	else if(c=='\u01dd') {dSchwa.setEnabled(true);iSchwa.setEnabled(false);iGemm.setEnabled(false);dGemm.setEnabled(false);}
		     						            	else{dSchwa.setEnabled(false);iSchwa.setEnabled(false);iGemm.setEnabled(false);dGemm.setEnabled(false);}
		     						            }	
		     						            }
		     			    		 }
		                   		});	
		   			              
		   			           cancel.addActionListener(new ActionListener(){
		   			            	  public void actionPerformed(ActionEvent e1){
		   			            		  if(local.isSelected()||global.isSelected()){
		   			            		 /* for (int i=0;i<posSelW.size();i++){
		   			            			 // System.out.println(posSelW.get(i).intValue()+" "+wordsSel.get(i).getTranslitLabel(typScript));
		   			            			 
		   			            			words.set(posSelW.get(i).intValue(),wordsSel.get(i));
		   			            			//modelTranslit.set(posSelW.get(i).intValue(),wordsSel.get(i));
		   			            		//	System.out.println(words.get(posSelW.get(i).intValue()).getTranslitLabel(typScript));
		   			            		  }
		   			            		 for (int i=0;i<posSelT.size();i++){
		   			            			  
			   			            			tokens.set(posSelT.get(i).intValue(),tokenSel.get(i));
			   			            			
			   			            		  }*/
		   			            			  doUndo1();
		   			            		translitWordList.revalidate();
		   			            		translitWordList.repaint();}
		   			            		  modifyFrame.doDefaultCloseAction();
		   			            	  }
		   			           });
		   			           
		   			        modifyFrame.addInternalFrameListener(new InternalFrameListener() {
		                          
                            	public void internalFrameActivated(InternalFrameEvent event) {
                            		
                            		    
                            		
                            		}
                            	
                            		public void internalFrameClosed(InternalFrameEvent event) {
                            		
                            			
		   			            		 // modifyFrame.doDefaultCloseAction();
		   			            	  
                            		
                            		}
                            		public void internalFrameOpened(InternalFrameEvent event) {
	                            			 
	                            		
                            		}
                            	
                            		public void internalFrameClosing(InternalFrameEvent event) {
                            			  if(local.isSelected()||global.isSelected()){
                            			/*for (int i=0;i<posSelW.size();i++){
		   			            			//  System.out.println(posSelW.get(i).intValue()+" "+wordsSel.get(i).getTranslitLabel(typScript));
		   			            			 
		   			            			words.set(posSelW.get(i).intValue(),wordsSel.get(i));
		   			            			//modelTranslit.set(posSelW.get(i).intValue(),wordsSel.get(i));
		   			            			//System.out.println(words.get(posSelW.get(i).intValue()).getTranslitLabel(typScript));
		   			            		  }
		   			            		 for (int i=0;i<posSelT.size();i++){
		   			            			  
			   			            			tokens.set(posSelT.get(i).intValue(),tokenSel.get(i));
			   			            			
			   			            		  }*/
                            				  doUndo1();
		   			            		translitWordList.revalidate();
		   			            		translitWordList.repaint();
                            			  }
                            		}
                            		
                                    public void internalFrameDeiconified(InternalFrameEvent event) {
	                         
	                            		
                            		}
                                    public void internalFrameDeactivated(InternalFrameEvent event) {
	                         
	                            		
                            		}
                            		public void internalFrameIconified(InternalFrameEvent event) {
	                         
	                            		
                            		}

                            
                        });
		   			           
		   			        modif.addActionListener(new ActionListener(){
	   			            	  public void actionPerformed(ActionEvent e1){
	   			            	   
	   			            		 // System.out.println("W alt "+walt);
	   			            		  for(int i=0;i<tselEdit.size();i++){
	   			            		//	if(!tselEdit1.get(i).equals("-1")){
	   			            			//  if(!tokenNewLabel.equals(talt)){
	   			            	//	if(!schwaExcluded.containsKey(""+i)){	  
	   			                     // System.out.println("Tsel edit position "+indexIdToken.get(tselEdit.get(i)).intValue());
	   			            		 indexT.get(tokenALabel.get(i)).remove(tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getId());
	   			            		  if (indexT.get(tokenALabel.get(i)).size()==0) indexT.remove(tokenALabel.get(i));
	   			            		 // System.out.println("Look if already exists "+tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLabel());
	   			            		 //tokens.get(indexIdToken.get(tselEdit.get(i)).intValue())
	   			            		//  if(indexT.containsKey(t.getLabel())) indexT.get(t.getLabel()).add(tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getId());
	   			            		if(indexT.containsKey(tokenNLabel.get(i))) indexT.get(tokenNLabel.get(i)).add(tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getId());
	   			            		  else {
	   			            			  ArrayList<String> newl=new ArrayList<String>();
	   			            			  newl.add(tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getId());
	   			            			  indexT.put(tokenNLabel.get(i), newl);
	   			            		  }
	   			            			 // }
	   			            		createIndexTokens();
	   			            		//}
	   			            		   }
	   			            		  //index.remove(walt);
	   			            		 // auswahl.clear();
	   			            		
	   			            		for(int i=0;i<wselEdit.size();i++){
	   			            			//if(!wselEdit1.get(i).equals("-1")){
	   			            			//if(!wordNLabel.get(i).equals(wordALabel.get(i))){
	   			            		//  System.out.println("WselEdit label old "+i + " "+ wordALabel.get(i));
	   			            		  if(index.get(wordALabel.get(i))!=null){
	   			            			
	   			            		 // if(!auswahl.containsKey(words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getId())){
	   			            			//auswahl.put(words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getId(),"a");
	   			            		 index.get(wordALabel.get(i)).remove(words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getId());
	   			            		  if (index.get(wordALabel.get(i)).size()==0) index.remove(wordALabel.get(i));
	   			            		  }
	   			            		//String tokenkey=words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getFidalInternLabel()+"*"+words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getTranslitLabel(typScript);
	   			            		//System.out.println("Size "+ wordNLabel.size());
	   			            		 // System.out.println(i +" "+ wordNLabel.get(i));
	   			            		  if(index.containsKey(wordNLabel.get(i))) {
	   			            			    index.get(wordNLabel.get(i)).add(words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getId());
	   			            		  // System.out.println("Added once miGore "+ words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getTranslitLabel(typScript));
	   			            		  }
	   			            		  else {
	   			            			  ArrayList<String> newl=new ArrayList<String>();
	   			            			  newl.add(words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getId());
	   			            			//  System.out.println("The new Word is "+words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getTranslitLabel(typScript));
	   			            			  index.put(wordNLabel.get(i), newl);
	   			            		  }
	   			            		 // }
	   			            		  
	   			              		createIndexWords();
	   			              	   //  System.out.println("Change previous po "+indexIdWord.get(wselEdit.get(i)).intValue())
	   			              	//	System.out.println("Change on position "+indexIdWord.get(wselEdit.get(i)).intValue()+" "+words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getTranslitLabel(typScript));
	   			            		//modelTranslit.set(indexIdWord.get(wselEdit.get(i)).intValue(), words.get(indexIdWord.get(wselEdit.get(i)).intValue()));
	   			         
	   			            		//}
	   			            		//}
	   			            		}
	   			            	 
	   			            	 translitWordList.revalidate();
	   			            		translitWordList.repaint();
	   			             //modifyFrame.doDefaultCloseAction();
	   			            		modifyFrame.dispose();
	   			            	  }
	   			           });
		   			        iSchwa.addActionListener(new ActionListener(){
	   			            	  public void actionPerformed(ActionEvent e1){
	   			            		 // System.out.println("Tsel size "+tselEdit.size());
	   			            		  wordsBackup.clear();tokensBackup.clear();
	   			            		for(int i=0;i<words.size();i++){
	   			            	    	wordsBackup.add(words.get(i).copyWortNode());
	   			            	    }
	   			            	  for(int i=0;i<tokens.size();i++){
	   			            	//	    System.out.println("Token "+tokens.get(i).getLabel());
	   			            	    	tokensBackup.add(tokens.get(i).copyToken());
	   			            	    }
	   			            	  undoMenu.setEnabled(true);
	   			            		
	   			            		//schwaExcluded.clear();
	   			            		  for(int i=0;i<tselEdit.size();i++){
	   			            			//boolean sel=false; int k=0;
	   			            		
	   			            		  LatinLetterNode let=(LatinLetterNode)tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLetters().get(tlist.getSelectedIndex());
	   			            	//	 System.out.println("pos to insert "+let.getNr());
	   			            		  temp=new LatinLetterNode('\u01dd',(byte)0,(byte)(let.getNr()+1),let.getParent());
	   			            		//  System.out.println("Parent "+ let.getParent());
	   			            					  temp.setTokenId(let.getTokenId());
	   			            					  FidalNode fd=temp.getParent();
	   			            					  
	   			            					  int nr=fd.getNr();
	   			            			//		 System.out.println("fidel to insert "+fd.getNr());
	   			            					 String s=tselEdit.get(i).substring(tselEdit.get(i).indexOf(">")+1);
	   			            					char voc=fd.getTranslitChildren().get(fd.getTranslitChildren().size()-1).getLetter();
	   			            			//		System.out.println("VOC "+voc);
	   			            					if((voc!='a')&&(voc!='u')&&(voc!='o')&&(voc!='e')&&(voc!='i')&&(voc!='\u0101')){
	   			            					words.get(indexIdWord.get(s).intValue()).getFidalChildren().get(nr).addChild(temp);
	   			            					words.get(indexIdWord.get(s).intValue()).getFidalChildren().get(nr).increaseChildren();
	   			            					tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLetters().add(tlist.getSelectedIndex()+1,temp);
	   			            					int k=0; boolean found=false;
	   			            					while((k<wselEdit.size())&&(!found)){
		   			        	                    if(wselEdit.get(k).equals(s)) found=true;
		   			        	                    else k=k+1;
		   			                              }
	   			            						tokenNLabel.set(i,tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLabel());
	   			            						//if(nrop>1)
	   			            						wordNLabel.set(k,words.get(indexIdWord.get(s).intValue()).getFidalInternLabel(typScript,typDoc)+"*"+words.get(indexIdWord.get(s).intValue()).getTranslitLabel(typScript));
	   			            					//    System.out.println("wrote in index "+wordNLabel.get(k));
	   			            					}
	   			            					/*
	   			            					 * else{
	   			            						//tselEdit1.set(i, "-1");
	   			            						 wselEdit1.set(i, "-1");
	   			            					}*/
	   			            					//else schwaExcluded.put(""+i,""+indexIdWord.get(wselEdit.get(i)).intValue());
	   			            					//else 
	   			            							//wordNLabel.add(words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getFidalInternLabel()+"*"+words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getTranslitLabel(typScript));
	   			            						
	   			            					//createIndexWords();	
	   			            					//createIndexTokens();
	   			            					 }		      
	   			            			
	   			            		 trmodel.add(tlist.getSelectedIndex()+1,temp);
	   			            		  tlist.revalidate();
	   			            		  tlist.repaint();
	   			            		  //global.setEnabled(true);
	   			            	/* (int i=0;i<tselEdit1.size();i++){
	   			            			if(tselEdit1.get(i).equals("-1")) tselEdit.remove(tselEdit1.get(i));
	   			            		}
	   			            		for (int i=0;i<wselEdit1.size();i++){
	   			            			if(wselEdit1.get(i).equals("-1")) wselEdit.remove(wselEdit1.get(i));
	   			            		}*/
	   			            		  modif.setEnabled(true);
	   			            		  modifyFrame.pack();
	   			            		  iSchwa.setEnabled(false); iGemm.setEnabled(false);
	   			            		  modif.setEnabled(true);
	   			            	  }
	   			           });
		   			     iGemm.addActionListener(new ActionListener(){
		   			    	
  			            	  public void actionPerformed(ActionEvent e1){
  			            		nrop=nrop+1; wordsBackup.clear();tokensBackup.clear();
  			            		for(int i=0;i<words.size();i++){
   			            	    	wordsBackup.add(words.get(i).copyWortNode());
   			            	    }
   			            	  for(int i=0;i<tokens.size();i++){
   			            		//    System.out.println("Token "+tokens.get(i).getLabel());
   			            	    	tokensBackup.add(tokens.get(i).copyToken());
   			            	    }
   			            	  undoMenu.setEnabled(true);
  			            		LatinLetterNode temp=new LatinLetterNode('A',(byte)0,(byte)0,null);
  			            		 for(int i=0;i<tselEdit.size();i++){
  			            			 String s=tselEdit.get(i).substring(tselEdit.get(i).indexOf(">")+1);
  			            	//	System.out.println("Index in token list "+tlist.getSelectedIndex());
  			            		//System.out.println("Index Id Token "+indexIdToken.get(tselEdit.get(i)).intValue());
  			            		  LatinLetterNode let=(LatinLetterNode)tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLetters().get(tlist.getSelectedIndex());
  			            		
  			            		  if(let.getTyp()==1){
  			            			  temp=new LatinLetterNode(let.getLetter(),(byte)0,(byte)1,let.getParent());
  			            		  }
  			            		  else{ temp=new LatinLetterNode(let.getLetter(),let.getTyp(),(byte)1,let.getParent());}
  			            		 
  			
  			            					  temp.setTokenId(tselEdit.get(i));
  			            					  FidalNode fd=temp.getParent();
  			            					  int nr=fd.getNr();
  			            					 // System.out.println("nr "+nr);
  			            					//  System.out.println("word "+words.get(indexIdWord.get(s).intValue()).getTranslitLabel(typScript));
  			            					 // int nr1=let.getNr();
  			            					  FidalNode fnew=words.get(indexIdWord.get(s).intValue()).getFidalChildren().get(nr).copyFidalNode();
  			            					  fnew.increaseChildren();fnew.addChild(0,temp);
  			            					  if (fnew.getFidalInternLabel(typScript, typDoc)!=null){
  			            					words.get(indexIdWord.get(s).intValue()).getFidalChildren().get(nr).increaseChildren();
  			            					
  			            					words.get(indexIdWord.get(s).intValue()).getFidalChildren().get(nr).addChild(0, temp);
  			            			
  			            					tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLetters().add(tlist.getSelectedIndex(),temp);
  			            					int k=0; boolean found=false;
   			            					while((k<wselEdit.size())&&(!found)){
	   			        	                    if(wselEdit.get(k).equals(s)) found=true;
	   			        	                    else k=k+1;
	   			                              }
			            						//if(i==0) {  tokenNewLabel=tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLabel();}
			            						//if(nrop>1)
			            						tokenNLabel.set(i,tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLabel());
			            						
	   			            						wordNLabel.set(k,words.get(indexIdWord.get(s).intValue()).getFidalInternLabel(typScript,typDoc)+"*"+words.get(indexIdWord.get(s).intValue()).getTranslitLabel(typScript));
	   			            						//else 
  			            					  }			
  			            					  //wordNLabel.add(words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getFidalInternLabel()+"*"+words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getTranslitLabel(typScript));
	   			            						
  			            					//createIndexTokens();createIndexWords();
  			            		 }		   
  			            			
  			            			     
  			            		 trmodel.add(tlist.getSelectedIndex(),temp);
  			            	
  			            		  tlist.revalidate();
  			            		  tlist.repaint();
  			            		
  			            		  modif.setEnabled(true);
  			            		  modifyFrame.pack();
  			            		  iGemm.setEnabled(false); iSchwa.setEnabled(false);
  			            		  modif.setEnabled(true);
  			            	  }
  			           });
		   			   dSchwa.addActionListener(new ActionListener(){
			            	  public void actionPerformed(ActionEvent e1){
			            		  nrop=nrop+1;  wordsBackup.clear();tokensBackup.clear();
			            		  for(int i=0;i<words.size();i++){
	   			            	    	wordsBackup.add(words.get(i).copyWortNode());
	   			            	    }
	   			            	  for(int i=0;i<tokens.size();i++){
	   			            		   // System.out.println("Token "+tokens.get(i).getLabel());
	   			            	    	tokensBackup.add(tokens.get(i).copyToken());
	   			            	    }
	   			            	  undoMenu.setEnabled(true);
			            		 // System.out.println("Tsel "+tselEdit.size());
			            		  for(int i=0;i<tselEdit.size();i++){
			            			 // System.out.println(indexIdToken.get(tselEdit.get(i)).intValue());
			            			  String s=tselEdit.get(i).substring(tselEdit.get(i).indexOf(">")+1);
			            		  LatinLetterNode let=(LatinLetterNode)tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLetters().get(tlist.getSelectedIndex());
			            		  
			            					  FidalNode fd=let.getParent();
			            					  int nr=fd.getNr();
			            					// int nr1=let.getNr();
			            					 int nr1=fd.getTranslitChildren().size();
			            					 FidalNode fnew=words.get(indexIdWord.get(s).intValue()).getFidalChildren().get(nr).copyFidalNode();
			            				//	System.out.println("to delete in "+words.get(indexIdWord.get(s).intValue()).getTranslitLabel(typScript));
			            					// System.out.println("fnew "+fnew.getTranslitLabel(typDoc)+ "nr1 "+nr1);
 			            					  fnew.removeChild(nr1-1);fnew.decreaseChildren();
 			            					
 			            					  if (fnew.getFidalInternLabel(typScript, typDoc)!=null){
			            					  words.get(indexIdWord.get(s).intValue()).getFidalChildren().get(nr).removeChild(nr1-1);
			            					  words.get(indexIdWord.get(s).intValue()).getFidalChildren().get(nr).decreaseChildren();
			            					  tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLetters().remove(tlist.getSelectedIndex());
			            					  int k=0; boolean found=false;
  			            					while((k<wselEdit.size())&&(!found)){
	   			        	                    if(wselEdit.get(k).equals(s)) found=true;
	   			        	                    else k=k+1;
	   			                              }
 			            						//if(i==0) {  tokenNewLabel=tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLabel();}
 			            						//if(nrop>1){
 			            							//System.out.println("more "+ i);
 			            						tokenNLabel.set(i,tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLabel());	
 			            						wordNLabel.set(k,words.get(indexIdWord.get(s).intValue()).getFidalInternLabel(typScript,typDoc)+"*"+words.get(indexIdWord.get(s).intValue()).getTranslitLabel(typScript));
	   			            						//System.out.println ("REplace "+i+ " "+words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getFidalInternLabel()+"*"+words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getTranslitLabel(typScript));
 			            					  }
	   			            						//}
	   			            					//	else {
	   			            						//	System.out.println("1 "+ i);
	   			            							//wordNLabel.add(words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getFidalInternLabel()+"*"+words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getTranslitLabel(typScript));
	   			            						//}
	   			            						
			            		  
			            		  }	      
			            					  trmodel.remove(tlist.getSelectedIndex());

			            		  tlist.revalidate();
			            		  tlist.repaint();
			            		 // global.setEnabled(true);
			            		  modif.setEnabled(true);
			            		  modifyFrame.pack();
			            		  dSchwa.setEnabled(false);
			            		  modif.setEnabled(true);
			            	  }
			           });
						   
		   			     dGemm.addActionListener(new ActionListener(){
 			            	  public void actionPerformed(ActionEvent e1){
 			            		  nrop=nrop+1;  wordsBackup.clear();tokensBackup.clear();
 			            		 for(int i=0;i<words.size();i++){
	   			            	    	wordsBackup.add(words.get(i).copyWortNode());
	   			            	    }
	   			            	  for(int i=0;i<tokens.size();i++){
	   			            		//    System.out.println("Token "+tokens.get(i).getLabel());
	   			            	    	tokensBackup.add(tokens.get(i).copyToken());
	   			            	    }
	   			            	  undoMenu.setEnabled(true);
 			            		 for(int i=0;i<tselEdit.size();i++){
 			            			 String s=tselEdit.get(i).substring(tselEdit.get(i).indexOf(">")+1);
 			            		  LatinLetterNode let=(LatinLetterNode)tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLetters().get(tlist.getSelectedIndex());
 			            		  
 			            					  FidalNode fd=let.getParent();
 			            					  int nr=fd.getNr();
 			            					 FidalNode fnew=words.get(indexIdWord.get(s).intValue()).getFidalChildren().get(nr).copyFidalNode();
			            					  fnew.removeChild(0);fnew.decreaseChildren();
			            					  if (fnew.getFidalInternLabel(typScript, typDoc)!=null){
 			            					 words.get(indexIdWord.get(s).intValue()).getFidalChildren().get(nr).removeChild(0);
 			            					words.get(indexIdWord.get(s).intValue()).getFidalChildren().get(nr).decreaseChildren();
 			            					tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLetters().remove(tlist.getSelectedIndex());
 			            					
			            						//if(i==0) {  tokenNewLabel=tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLabel();}
			            						//if(nrop>1)
 			            					 int k=0; boolean found=false;
   			            					while((k<wselEdit.size())&&(!found)){
 	   			        	                    if(wselEdit.get(k).equals(s)) found=true;
 	   			        	                    else k=k+1;
 	   			                              }
			            						tokenNLabel.set(i,tokens.get(indexIdToken.get(tselEdit.get(i)).intValue()).getLabel());	
			            						wordNLabel.set(k,words.get(indexIdWord.get(s).intValue()).getFidalInternLabel(typScript,typDoc)+"*"+words.get(indexIdWord.get(s).intValue()).getTranslitLabel(typScript));
	   			            						//else 
	   			            							//wordNLabel.add(words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getFidalInternLabel()+"*"+words.get(indexIdWord.get(wselEdit.get(i)).intValue()).getTranslitLabel(typScript));
			            					  }				 
 			            		 
 			            		 
 			            		 }
 			            				      trmodel.remove(tlist.getSelectedIndex());

 			            		  tlist.revalidate();
 			            		  tlist.repaint();
 			            		//  global.setEnabled(true);
 			            		  modif.setEnabled(true);
 			            		  modifyFrame.pack();
 			            		  dGemm.setEnabled(false);
 			            		  modif.setEnabled(true);
 			            	  }
 			           });
						            		}
						            		});
						            	}
						            	final JMenuItem jmreplace=new JMenuItem("Replace GraphicUnit");
						            	final JMenuItem jminsertB=new JMenuItem("Insert GraphicUnit Before");
						            	//if(indexes[0]==0) jminsertB.setEnabled(false);
						            	//final JMenuItem jmannotS=new JMenuItem("Annotate Text Structure");
						            	final JMenuItem jmdelete=new JMenuItem("Delete GraphicUnit");
						            	final JMenuItem jmtokenise=new JMenuItem("Tokenise");
						            	
						            	final JMenuItem jmDelToken=new JMenuItem("Remove Tokens");
						            	if((words.get(indexes[0])).getTokenIds().size()>1){
		                                              jmDelToken.setEnabled(true);
		                                              jmtokenise.setEnabled(false);
		                                              
		                                 }        
						            	else {
						            		jmDelToken.setEnabled(false);
                                            jmtokenise.setEnabled(true);
						            	}
						            	final JMenuItem jminsertA=new JMenuItem("Insert GraphicUnit After");
						            	final JMenuItem jmjoin=new JMenuItem("Join GraphicUnits");
						            	jmjoin.setEnabled(false);
						            	final JMenuItem jmjoinM=new JMenuItem("Join Morphologically");
						            	 jmjoinM.setEnabled(false);
						            	final JMenuItem jmsplit=new JMenuItem("Split GraphicUnit");
						            	jmsplit.setEnabled(false);
						            	final JMenu jmcomm=new JMenu("Comment GraphicUnit");
						            	final JMenuItem jmcolor=new JMenuItem("Add color");
						            	final JMenuItem jmcomm1=new JMenuItem("Add comment");
						            	jmcomm.add(jmcolor);jmcomm.add(jmcomm1);
						            	if (indexes.length<2) jmjoin.setEnabled(false);
						            	if (indexes.length!=2) jmjoinM.setEnabled(false);
						            	if (indexes.length>1) jmsplit.setEnabled(false);
						            	//if (indexes[indexes.length-1]==modelOrig.size()-1) jminsertA.setEnabled(false);
						            	//final JMenu editSillable= new JMenu("")
						            	/*final JMenu submenuAnnotStruct = new JMenu("Annotate Text Structure");
						            	final JMenu submenuAnnotStructDivisions =new JMenu("Text Divisions");
						            	
						            	final JMenuItem jmannotD = new JMenu("Insert New  Division");
						            	final JMenuItem jmannotDE = new JMenu("Edit Division");
						            	   final JMenuItem jmannotDelS =new JMenu("Delete Division(s)");
						            	   submenuAnnotStructDivisions.add(jmannotD);
						            	   submenuAnnotStructDivisions.add(jmannotD);
						            	   submenuAnnotStructDivisions.add(jmannotDelS);
						           
						            	   
						            	   final JMenuItem jmannotH = new JMenuItem("Head");
						            	   final JMenuItem jmannotQ = new JMenuItem("Quotation");
						            	   submenuAnnotStruct.add(submenuAnnotStructDivisions);
						            	   submenuAnnotStruct.add(jmannotH);
						            	   submenuAnnotStruct.add(jmannotQ);*/
						            	final JMenu jmne=new JMenu(" NE");
						            	final JMenuItem jmneI=new JMenuItem("Insert / Modify NE");
						            //	final JMenuItem jmneM=new JMenuItem("Modify NE");
						            	final JMenuItem jmneD=new JMenuItem("Delete NE");
						            	final JMenuItem jmneG=new JMenuItem("Annotate Global");
						            	jmne.add(jmneI);
						           // 	jmne.add(jmneM);
						            	jmne.add(jmneD);
						            	jmne.add(jmneG);
						            	if ((words.get(indexes[0]).getNERef().length()>0)&&(words.get(indexes[indexes.length-1]).getNERef().length()>0)&&(words.get(indexes[0]).getNERef().equals(words.get(indexes[indexes.length-1]).getNERef())))
						            	{  jmneG.setEnabled(true);jmneD.setEnabled(true);}
						            	else {jmneG.setEnabled(false);jmneG.setEnabled(false);}
						            	if ((words.get(indexes[0]).getNERef().length()>0))
						            	{ jmneD.setEnabled(true);}
						            	else {jmneG.setEnabled(false);}
						            	   final JMenu lingannot=new JMenu("Linguistic Annotation");
						            	   
						                	final JMenu jmlingmod=new JMenu("Insert or Modify Annotation");
						                	final JMenu jmdela=new JMenu("Delete Annotation");
						                	final ArrayList<JMenuItem> menuTokensI=new ArrayList<JMenuItem>();
						                	final ArrayList<JMenuItem> menuTokensD=new ArrayList<JMenuItem>();
					                		//final WortNode wselect = words.get(translitWordList.getSelectedIndex());
					                		for (int i=0;i<wselect.getTokenIds().size();i++){
					                			//System.out.println(tokens.get(indexIdToken.get(wselect.getTokenIds().get(i)).intValue()).getLabel());
					                			JMenuItem men1=new JMenuItem(tokens.get(indexIdToken.get(wselect.getTokenIds().get(i)).intValue()).getLabel());
					                			JMenuItem men2=new JMenuItem(tokens.get(indexIdToken.get(wselect.getTokenIds().get(i)).intValue()).getLabel());
					                			men1.setFont(etiopicMenu1);
					                		if(tokens.get(indexIdToken.get(wselect.getTokenIds().get(i)).intValue()).getMorphoAnnotation()==null)
					                			men2.setEnabled(false);
					                			men2.setFont(etiopicMenu1);
					                			//men2.setEnabled(false);
					                			menuTokensI.add(men1);
					                			jmlingmod.add(men1);
					                			menuTokensD.add(men2);
					                			jmdela.add(men2);		
					                		}
						                	lingannot.add(jmlingmod);
						                	lingannot.add(jmdela);
						                	 popupMenu.add(jmreplace);
							                popupMenu.add(jminsertB);
								            popupMenu.add(jminsertA);
							                popupMenu.add(jmdelete);
							                popupMenu.add(jmjoin);
							                popupMenu.add(jmjoinM);
							                popupMenu.add(jmsplit);
						                    popupMenu.add(jmmod);
						                    popupMenu.add(new JPopupMenu.Separator());
						                    popupMenu.add(jmcomm);
							                popupMenu.add(new JPopupMenu.Separator());
							                 popupMenu.add(jmtokenise);
							                 popupMenu.add(jmDelToken);
							               
							              // popupMenu.add(new JPopupMenu.Separator());
							               // popupMenu.add(submenuAnnotStruct);
							                 popupMenu.add(new JPopupMenu.Separator());
							                popupMenu.add(jmne);
							                popupMenu.add(new JPopupMenu.Separator());
							                popupMenu.add(lingannot);
							                if(indexes.length>1){
							                	jmtokenise.setEnabled(false);
							                	jmreplace.setEnabled(false);
							                	jmDelToken.setEnabled(false);
							                	jminsertA.setEnabled(false);
							                	jminsertB.setEnabled(false);
							                	jmtokenise.setEnabled(false);
							                	lingannot.setEnabled(false);
							                	jmcomm.setEnabled(false);
							                }
							                if((!Character.isDigit(verifyWordInIndex(indexes[0],wselect).charAt(0)))||(!Character.isDigit(verifyTokensWordIndex(indexes[0],wselect).charAt(0)))){
							                	 jmlingmod.setEnabled(false);
							                	 jmdela.setEnabled(false);
							                	// jminsertB.setEnabled(false);
							                	// jminsertA.setEnabled(false);
							                	// jmdelete.setEnabled(false);
							                	 jmjoin.setEnabled(false);
							                	 jmjoinM.setEnabled(false);
							                	 jmsplit.setEnabled(false);
							                	 jmmod.setEnabled(false);
							                	 jmne.setEnabled(false);
							                	 jmcomm.setEnabled(false);
							                	 jmtokenise.setEnabled(false);
							                	 jmDelToken.setEnabled(false);
							                	 lingannot.setEnabled(false);
							                //	 if(!Character.isDigit(verifyWordInIndex(indexes[0],wselect).charAt(0))){
							                	//	 JOptionPane.showMessageDialog(new JFrame(), "Error in the graphic unit index", "Dialog",
								         		  //  		      JOptionPane.ERROR_MESSAGE); 
							                	 //}
							                //	if (!Character.isDigit(verifyTokensWordIndex(indexes[0],wselect).charAt(0))){
							                	//	JOptionPane.showMessageDialog(new JFrame(), "Error in the token index", "Dialog",
							         		    	//	      JOptionPane.ERROR_MESSAGE); 
							                	
							                	 //}
							                 }
							                // join Morphologically
							              //NE
							                //NE Delete
							                jmneD.addActionListener(new ActionListener(){
							                	@Override public void actionPerformed(ActionEvent e){
							                		int confirm= JOptionPane.showOptionDialog(desk,
							             	                "You are going to delete this NE. Are you sure? ",
							             	                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
							             	                JOptionPane.QUESTION_MESSAGE, null, null, null);
							             			if (confirm == JOptionPane.YES_OPTION) {
							                		final int[] selection1=translitWordList.getSelectedIndices();
							                		String refId=words.get(selection1[0]).getNERef();
							                		int kk=0; boolean found=false;
							                		
							                		while((kk<nelist.size())&&(!found)){
							                			if (nelist.get(kk).getId().equals(refId)){
							                				rr=nelist.get(kk); found=true;
							                			}
							                			else kk=kk+1;
							                		}
							                	   final int indNE=kk;
							                	   nelist.remove(rr);
							                	   for (int i=0;i<rr.getRefIDS().size();i++) {
							                		//   words.get(indexIdWord.get(rr.getRefIDS().get(i)).intValue()).getTokenIds()
							                		   words.get(indexIdWord.get(rr.getRefIDS().get(i).getWID()).intValue()).setNERef("");
							                		   for(int j=0; j< words.get(indexIdWord.get(rr.getRefIDS().get(i).getWID()).intValue()).getTokenIds().size();j++)
							                			   tokens.get(indexIdToken.get( words.get(indexIdWord.get(rr.getRefIDS().get(i).getWID()).intValue()).getTokenIds().get(j)).intValue()).setNERef("");
							                	   }
							             			}
							                	}
							                });
							                
							                
							                //NE insert
							                
							                jmneI.addActionListener(new ActionListener(){
							                	@Override public void actionPerformed(ActionEvent e){
							                		
							                		int  selectionInit=translitWordList.getSelectedIndex();
							                		String refId=words.get(selectionInit).getNERef();
							                		int kk=0; boolean found=false;
							                		rr=null;
							                		while((kk<nelist.size())&&(!found)){
							                			if (nelist.get(kk).getId().equals(refId)){
							                				rr=nelist.get(kk); found=true;
							                			}
							                			else kk=kk+1;
							                		}
							                	   final int indNE=kk;
							                	   if (rr!=null) {
							                		   selection1=new int[rr.getRefIDS().size()];
							                		   for(int i=0;i<rr.getRefIDS().size();i++)
							                			   selection1[i]=indexIdWord.get(rr.getRefIDS().get(i).getWID()).intValue();
							                		      translitWordList.setSelectedIndices(selection1);
							                	   }
							                	   else selection1=translitWordList.getSelectedIndices();
							        				String labelRef="";
							        				for (int j=0;j<selection1.length;j++){
							     
							        					labelRef=labelRef+words.get(selection1[j]).getTranslitLabel(typScript)+" ";
							        					
							        					//System.out.println(words.get(indexIdWord.get(refw.get(j).getWID()).intValue()).getTranslitLabel(typScript));
							        				}
							        		//		System.out.println("LabelRef "+labelRef+" "+selection1.length);
							        				  wordref="";
							        				  
							        				//if(i==0){
							        					ArrayList<JCheckBox> tokenCB=new ArrayList<JCheckBox>();
								                		 JPanel[] panelToken=new JPanel[selection1.length];
								                		 JPanel tokensP=new JPanel();
								                		 tokensP.setLayout(new GridLayout(1,selection1.length));
								                		 int nrtokens=0;
								                		 for(int i=0;i<selection1.length;i++){
								                			 nrtokens=nrtokens+words.get(selection1[i]).getTokenIds().size();
								                		 }
								                		 JCheckBox[] tcb=new JCheckBox[nrtokens];
								                		 int num=0;
								                		for(int i=0;i<selection1.length;i++){
								                			panelToken[i]=new JPanel();
								                			panelToken[i].setLayout(new GridLayout(words.get(selection1[i]).getTokenIds().size(),1));
								                			for(int j=0;j<words.get(selection1[i]).getTokenIds().size();j++){
								                				 tcb[num]=new JCheckBox(tokens.get(indexIdToken.get(words.get(selection1[i]).getTokenIds().get(j)).intValue()).getLabel());
								                				tcb[num].setFont(etiopicText1);
								                				int k=0; boolean found1=false;
								                				if (rr!=null) {
								                					if(!tokens.get(indexIdToken.get(words.get(selection1[i]).getTokenIds().get(j)).intValue()).getNERef().isEmpty()) {
								                						tcb[num].setSelected(true);
								                					}
								                					else {
								                						tcb[num].setSelected(false);
								                					}
							                                  
								                				}
								                					
								                				else {tcb[num].setSelected(true);}
								                				panelToken[i].add(tcb[num]);num++;
								                			}	
								                			tokensP.add(panelToken[i]);
								                			
								                		}
							        				 
							        				 
							        				 //
							        				labelRef=labelRef.substring(0, labelRef.length()-1);
							                		final JRadioButton personB=new JRadioButton("Person");
							                		final JRadioButton placeB=new JRadioButton("Place");
							                		final JRadioButton orgB=new JRadioButton("Organisation");
							                		final JRadioButton titleB=new JRadioButton("Title");
							                		final JRadioButton workB=new JRadioButton("Work");
							                		final JRadioButton dateB=new JRadioButton("Date");
							                		
							                		
							                		final JRadioButton noneB=new JRadioButton("None"); 
							                		if (rr !=null) {
							                		    if (rr.getTyp().equalsIgnoreCase("person")) personB.setSelected(true);
								                	    else if (rr.getTyp().equalsIgnoreCase("place")) placeB.setSelected(true);
								                	    else if (rr.getTyp().equalsIgnoreCase("organisation")) orgB.setSelected(true);
								                	    else if (rr.getTyp().equalsIgnoreCase("title")) titleB.setSelected(true);
								                	    else if (rr.getTyp().equalsIgnoreCase("work")) workB.setSelected(true);
								                	    else if (rr.getTyp().equalsIgnoreCase("date")) dateB.setSelected(true);
							                		}
							                		else noneB.setSelected(true);
							                	    final ButtonGroup neGrp=new ButtonGroup();
							                	    neGrp.add(personB);neGrp.add(placeB);neGrp.add(orgB);neGrp.add(titleB);
							                	    neGrp.add(dateB);neGrp.add(noneB);
							                	 
							                	
							                		final JCheckBox abrevB=new JCheckBox("Abbreviation");
							                		if (rr !=null)
							                		   if (rr.hasAbbreviation()) abrevB.setSelected(true);
							                		final JButton linkB=new JButton("Link");
							                		linkB.setEnabled(false);
							                		final JRadioButton andB=new JRadioButton("AND");
							                		final JRadioButton orB=new JRadioButton("OR");
							                		final ButtonGroup andor=new ButtonGroup();
							                		andor.add(andB); andor.add(orB);
							                		andB.setSelected(true);
							                		final JLabel searchLabel=new JLabel("Search type");
							                		final JButton manualIns=new JButton ("Type NE Id");
							                		final JLabel automIns=new JLabel("Authority Lists");
							                		final JButton checkB=new JButton("Check");
							                		checkB.setEnabled(false);
							                		final JTextField manualLink=new JTextField(20);
							                		manualLink.setFont(etiopic1); 	manualLink.setEnabled(false);manualLink.setEditable(false);
							                		 nrmanins=0;
							                		 manualIns.addActionListener(new ActionListener() {
							                			 public void actionPerformed(ActionEvent e) {
							                				 manualLink.setEnabled(true); manualLink.setEditable(true);
							                				 checkB.setEnabled(true);
							                				 nrmanins=1;
							                			 }
							                		 });
							                		final JButton saveB=new JButton("Save");
							                		final JButton cancelB=new JButton("Cancel");
							                		//final HTMLEditorPane dethp =new HTMLEditorPane();
							                		final XmlTextPane dethp =new  XmlTextPane();
							                		
							                		//dethp.setAutoscrolls(true); 
							                		dethp.setSize(300, 500);
							                		dethp.setFont(etiopic1);
							                		dethp.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
							                		
							                		JScrollPane dethps=new JScrollPane(dethp);
							                		dethps.setSize(300, 500);
							                	//	if (rr !=null) {
							                		//	dethp.setText(rr.giveAttributes());
							                		//}
							                		final JComboBox selNE= new JComboBox();linkNE=new ArrayList<String>();
							                		selNE.setFont(etiopic1);
							                		if (rr!=null) {selNE.addItem(rr.getrefId());
							                		selNE.setSelectedIndex(0);
							                		String s="";
						                			
						                				s=sendGet("https://betamasaheft.eu/api/"+selNE.getSelectedItem()+"/tei");
						                				
						                				s=s.replaceAll(">   ", ">\n");
						                					dethp.setText("<div style=\'font-family:Ethiopic Unicode\'>"+s+"</div>");
						                					dethp.setCaretPosition(0);
						                			
							                		}
							                		JPanel searchP=new JPanel();
							                		searchP.setLayout(new GridBagLayout());
							                		GridBagConstraints gbc1=new GridBagConstraints();
							                		gbc1.gridx=0; gbc1.gridy=0; gbc1.insets.left=2;
							                		gbc1.insets.right=2; gbc1.insets.bottom=2;
							                		gbc1.insets.top=2;
							                		gbc1.anchor=GridBagConstraints.NORTHWEST;
							                		gbc1.fill=GridBagConstraints.HORIZONTAL;
							                		searchP.add(searchLabel,gbc1);
							                		gbc1.gridx=1;
							                		searchP.add(andB,gbc1);
							                		gbc1.gridx=2; searchP.add(orB,gbc1);
							                		gbc1.gridx=0;gbc1.gridy=1;
							                		searchP.add(automIns,gbc1);
							                		gbc1.gridx=1; searchP.add(linkB,gbc1);
							                		gbc1.gridx=2; searchP.add(selNE,gbc1);
							                		gbc1.gridx=0;gbc1.gridy=2;
							                		searchP.add(manualIns,gbc1);
							                		gbc1.gridx=1; searchP.add(manualLink,gbc1);
							                		gbc1.gridx=2; searchP.add(checkB,gbc1);
							                	
							                		final JTextField detailsB=new JTextField(50);
							                		personB.addActionListener(new ActionListener() {
							                			public void actionPerformed(ActionEvent e) {
							                				if (personB.isSelected()){
							                					linkB.setEnabled(true);
							                				}
							                			}
							                		});
							                		placeB.addActionListener(new ActionListener() {
							                			public void actionPerformed(ActionEvent e) {
							                				if (placeB.isSelected()){
							                					linkB.setEnabled(true);
							                				}
							                			}
							                		});
							                		workB.addActionListener(new ActionListener() {
							                			public void actionPerformed(ActionEvent e) {
							                				if (workB.isSelected()){
							                					linkB.setEnabled(true);
							                				}
							                			}
							                		});
							                		dateB.addActionListener(new ActionListener() {
							                			public void actionPerformed(ActionEvent e) {
							                				if (dateB.isSelected()){
							                					linkB.setEnabled(false);
							                				}
							                			}
							                		});
							                		orgB.addActionListener(new ActionListener() {
							                			public void actionPerformed(ActionEvent e) {
							                				if (orgB.isSelected()){
							                					linkB.setEnabled(false);
							                				}
							                			}
							                		});
							                		titleB.addActionListener(new ActionListener() {
							                			public void actionPerformed(ActionEvent e) {
							                				if (titleB.isSelected()){
							                					linkB.setEnabled(false);
							                				}
							                			}
							                		});
							                		
							                		//selNE.addItem("-");
							                		linkNE=new ArrayList<String>();
							                		selNE.addItemListener(new ItemListener() {
							                			public void itemStateChanged(ItemEvent e) {
							                				//String s=selNE.getSelectedItem().toString();
							                			  System.out.println ("Selected "+ selNE.getSelectedIndex()+ " "+linkNE.size());
							                				if(selNE.getSelectedItem()!=null) {
							                					String s=linkNE.get(selNE.getSelectedIndex());
							                			//		System.out.println("ID ist "+s);
							                					String neTyp="";
							                					
							                					String answer = sendGet("https://betamasaheft.eu/api/"+s+"/tei");
							                					answer=answer.replaceAll(">   ", ">\n");
							                					dethp.setText("<font face='Ethiopic Unicode'>"+answer+"</font>");
							                					dethp.setCaretPosition(0);
							                				
							                				}
							                			}
							                		});
							                		
							                	//	
							                		checkB.addActionListener(new ActionListener() {
								                		public void actionPerformed(ActionEvent e) {
								                			selNE.removeAll();
								                			
								                			if(!wordref.isEmpty()) {
								                			
								                			
								                				 String result = sendGet("https://betamasaheft.eu/api/"+manualLink.getText()+"/tei");
								             
								                			
								                			if(result.indexOf("No results")>0) {
								                              //   linkNE.add(detailsB.getText());
								                				JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" > There is no NE with this ID. Please enhance the authority list</font></p></html>", "Dialog",
								   			  		       		        JOptionPane.ERROR_MESSAGE);
								                                
								                			}
								                			else {
								                				dethp.setText("<font face='Ethiopic Unicode'>"+result+"</font>");
								                				dethp.setCaretPosition(0);
								                			}
								                				
								                		}
								                			else {
								                				JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" > Please select at least one token</font></p></html>", "Dialog",
								   			  		       		        JOptionPane.ERROR_MESSAGE);
								                			}
								                		}
								                	});
							                		
							                	//	
							                	linkB.addActionListener(new ActionListener() {
							                		public void actionPerformed(ActionEvent e) {
							                			selNE.removeAll();wordref="";
							                		//	System.out.println("egg "+tcb.length);
							                			for(int i=0;i<tcb.length;i++) {
							                				if(tcb[i].isSelected()) wordref=wordref+" "+tcb[i].getText();
							                			}
							                			if(!wordref.isEmpty()) {
							                			StringTokenizer st=new StringTokenizer(wordref," ");
							                			String suche="";
							                			while(st.hasMoreTokens()) {
							                				if(andB.isSelected())
                                                                 suche=suche+st.nextToken()+"%20AND%20";
							                				else suche=suche+st.nextToken()+"%20OR%20";

							                			}
							                			suche=suche.substring(0,suche.length()-9);
							                			//suche=suche+"\"";
							                		//	selNE.removeAll();
							                			
							                		/*	String suche="\"";
							                					if(wordref.indexOf(" ")>0)
							                					      suche=wordref.substring(0,wordref.indexOf(" "));
							                					else suche=wordref;
							                					suche=suche+"\"";*/
							                			String suchclass="";
							                			String suchcollection="";
							                			if (personB.isSelected()) {
							                				suchclass="persName";suchcollection="persons";
							                			}
							                			else if (placeB.isSelected()) {
							                				suchclass="placeName"; suchcollection="places";
							                			}
							                			else if (workB.isSelected()) {
							                				suchclass="title";suchcollection="works";
							                			}
							                			String getString="https://betamasaheft.eu/api/search?homophones=false&element="+suchclass+"&collection="+suchcollection+"&q="+suche;
							                			String result=sendGet(getString);
							                		//	System.out.println(result);
							                			
							                		if((result.indexOf("No results")>0)||(result.indexOf("Error")>0)) {
							                	//			if(result.indexOf("Error")>0) {
							                              //   linkNE.add(detailsB.getText());
							                               //  String answer = sendGet("https://betamasaheft.eu/api/"+detailsB.getText()+"/tei");
							                					dethp.setText("<font face='Ethiopic Unicode'>"+"No Items found"+"</font>");
							                					//manualLink.setEnabled(true); manualLink.setEditable(true);
							                					//checkB.setEnabled(true);
							                			}
							                			else {
							                				JSONObject o=new JSONObject(result);
							                				if(o.getInt("total")==1) {
							                				
							                				JSONObject o1=o.getJSONObject("items");
							                			
							                				if (personB.isSelected())	{
								                				  if(o1.getString("id").indexOf("PRS")==0) {
								                					  linkNE.add(o1.getString("id"));
								                					  selNE.addItem(o1.getString("title"));
								                					  selNE.setSelectedItem(o1.getString("title"));
								                				     
								                				  }
								                				}
								                				else if (placeB.isSelected())	{
									                				  if(o1.getString("id").indexOf("LOC")==0) {
									                					  linkNE.add(o1.getString("id"));
									                					  selNE.addItem(o1.getString("title"));
									                					
									                				  }
									                				}
								                				else if (workB.isSelected())	{
									                				  if(o1.getString("id").indexOf("LIT")==0) {
									                					  linkNE.add(o1.getString("id"));
									                					  selNE.addItem(o1.getString("title"));
									                					  
									                				  }
								                				}
								                				
							                			}
							                			
							                			else {
							                			JSONArray obj=o.getJSONArray("items");
							                			
							                			for(int i=0;i<obj.length();i++) {
							                				JSONObject o1=obj.getJSONObject(i);
							                				if (personB.isSelected())	{
							                				  if(o1.getString("id").indexOf("PRS")==0) {
							                					  linkNE.add(o1.getString("id"));
							                					  selNE.addItem(o1.getString("title"));
							                				      
							                				  }
							                				
							                				}
							                				else if (placeB.isSelected())	{
								                				  if(o1.getString("id").indexOf("LOC")==0) {
								                					  linkNE.add(o1.getString("id"));
								                					  selNE.addItem(o1.getString("title"));
								                					 
								                				  }
								                				}
							                				else if (workB.isSelected())	{
								                				  if(o1.getString("id").indexOf("LIT")==0) {
								                					  linkNE.add(o1.getString("id"));
								                					  selNE.addItem(o1.getString("title"));
								                					
								                				  }
								                				}
							                				//selNE.revalidate();
							                				//selNE.setSelectedIndex(1);
							                			}
							                		}
							                			}
							                		}
							                			else {
							                				JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" > Please select at least one token</font></p></html>", "Dialog",
							   			  		       		        JOptionPane.ERROR_MESSAGE);
							                			}
							                		}
							                	});
							                		
							                		
							                		final HTMLEditorPane hp =new HTMLEditorPane();
							                		hp.setFont(etiopic1);
							                	
							                		detailsB.addActionListener(new ActionListener(){
							                			public void actionPerformed(ActionEvent e){
							                				String s="";
							                			if (selNE.getSelectedIndex()>=0) {
							                				s=sendGet("https://betamasaheft.eu/api/"+linkNE.get(selNE.getSelectedIndex())+"/tei");
							                				s=s.replaceAll(">   ", ">\n");
						                					
							                					dethp.setText("<div style=\'font-family:Ethiopic Unicode\'>"+s+"</div>");
							                					dethp.setCaretPosition(0);
							                			}
							                				
							                			}
							                		});
							                		final ChildFrame neFrame=new ChildFrame("Insert NE: "+labelRef,mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
							                		neFrame.getComponent(1).setFont(typFont);
							                		
							                		JPanel nePanel =new JPanel();
							                		nePanel.setLayout(new GridLayout(3,3));
							                		nePanel.add(personB);nePanel.add(placeB);nePanel.add(orgB);nePanel.add(titleB);nePanel.add(workB);
							                		nePanel.add(dateB);nePanel.add(abrevB);
							                		
							                		Container chp=neFrame.getContentPane();
							                		chp.setFont(etiopicText);
							                		chp.setLayout(new GridBagLayout());
							                		GridBagConstraints gbc=new GridBagConstraints();
							                		gbc.gridx=0;
							                		gbc.gridy=0;
							                		
							                		gbc.gridwidth=2;
							                		
							                		gbc.anchor=GridBagConstraints.NORTHWEST;
							                	//	gbc.fill=GridBagConstraints.HORIZONTAL;
							                		gbc.fill=GridBagConstraints.BOTH;
							                		gbc.weightx=100;
							                		gbc.weighty=100;
							                		chp.add(tokensP,gbc);
							                		gbc.gridy=1;
							                		chp.add(nePanel,gbc);
							                		gbc.gridy=2;
							                		chp.add(hp,gbc);
							                		gbc.gridy=3;
							                		chp.add(searchP,gbc);
							                		gbc.gridy=4;gbc.gridheight=4;
							                		
							                	        dethp.setBounds( 0, 0, 300, 400 );
							                	       
							                	      dethps.setPreferredSize( new Dimension( 300, 400 ) );
							                		chp.add(dethps,gbc);
							                		gbc.fill=GridBagConstraints.NONE;
							                		gbc.anchor=GridBagConstraints.CENTER;
							                        gbc.gridwidth=1; gbc.gridheight=1;gbc.gridy=8;gbc.gridx=0;
							                        chp.add(saveB,gbc);
							                        gbc.gridx=1;
							                        chp.add(cancelB,gbc);
							                	saveB.addActionListener(new ActionListener() {
							                		public void actionPerformed(ActionEvent e)
							                		{
							                			ArrayList<RefWord> rw=new ArrayList<RefWord>();
							                			//NamedEntity(String id, String refid,String typ, ArrayList<RefWord>rw, ArrayList<Attribut> f,boolean imp,boolean autom)
							                		  for(int i=0;i<selection1.length;i++) {
							                			  ArrayList <String> t=new ArrayList<String>();
							                			  for(int j=0;j<words.get(selection1[i]).getTokenIds().size();j++) {
							                				  String tlabel=tokens.get(indexIdToken.get(words.get(selection1[i]).getTokenIds().get(j)).intValue()).getLabel();
							                				 // System.out.println("Token "+tlabel);
							                				  boolean found=false;
							                				  int k=0;
							                				  while((k<tcb.length)&&(!found)) {
							                					  if(tcb[k].isSelected())
							                						  if(tcb[k].getText().equals(tlabel)) found=true;
							                						  else k=k+1;
							                					  else k=k+1;
							                				  }
							                				  if (found) {
							                					//  t.add(words.get(selection1[i]).getTokenIds().get(j));
							                					  t.add(tokens.get(indexIdToken.get(words.get(selection1[i]).getTokenIds().get(j)).intValue()).getId());
							                					  System.out.println("Added "+ words.get(selection1[i]).getTokenIds().get(j));
							                				  }
							                			  }
							                				  rw.add(new RefWord(words.get(selection1[i]).getId(),t)); 
							                			  }
							                		//	  rw.add(new RefWord(words.get(selection1[i]).getId(),t)); 
							                			  String typNE="";
							                			  if(dateB.isSelected()) typNE="date";
							                			  else if(placeB.isSelected()) typNE="place";
							                			  else if(workB.isSelected()) typNE="work";
							                			  else if(personB.isSelected()) typNE="person";
							                			  else if(titleB.isSelected()) typNE="title";
							                			  else if(orgB.isSelected()) typNE="organisation";
							                			  ArrayList<Attribut> alist=new ArrayList<Attribut>();
							                			  if (!hp.getText().isEmpty()) alist.add(new Attribut("Comm",hp.getText()));
							                			  if(abrevB.isSelected()) alist.add(new Attribut("Abrev", "yes") );
							                			  NamedEntity ne=null;
							                			
							                			  if (rr!=null) {
							                				  if (linkB.isEnabled()) {
							                					  if (nrmanins==0) {
							                			              ne=new NamedEntity(rr.getId(), linkNE.get(selNE.getSelectedIndex()),typNE,rw,alist,false,false);
							                			              saveB.setEnabled(true);
							                					  }
							                					  else {
							                						  if (!manualLink.getText().isEmpty()) {
							                						  ne=new NamedEntity("N"+UUID.randomUUID(), manualLink.getText(),typNE,rw,alist,false,false);
							                						  saveB.setEnabled(true);
							                						  }
							                					  }
							                				  }
							                				  else 
							                					  {ne=new NamedEntity(rr.getId(),rr.getrefId(),typNE,rw,alist,false,false);}
							                			  }
							                			      else {
							                			    	  if(typNE.equals("date")||typNE.equals("organisation")||typNE.equals("title")) {
							                			    		  ne=new NamedEntity("N"+UUID.randomUUID(), manualLink.getText(),typNE,rw,null,false,false);
							                						  saveB.setEnabled(true);
							                			    	  }
							                			    	  else {
							                			    	  if(nrmanins==0)
							                				  ne=new NamedEntity("N"+UUID.randomUUID(), linkNE.get(selNE.getSelectedIndex()),typNE,rw,alist,false,false);
							                			    	  else ne=new NamedEntity("N"+UUID.randomUUID(), manualLink.getText(),typNE,rw,alist,false,false);
							                			    	  }
							                			    	  }
							                			  for (int i1=0;i1<rw.size();i1++) {
							                				  words.get(indexIdWord.get(rw.get(i1).getWID()).intValue()).setNERef(ne.getId());
							                				  for(int j1=0;j1<rw.get(i1).getTIDs().size();j1++)
							                					  tokens.get(indexIdToken.get(rw.get(i1).getTIDs().get(j1)).intValue()).setNERef(ne.getId());;
							                			  }
							                			if(ne!=null) {     
							                			  if (rr !=null)
							                			  nelist.set(indNE, ne);
							                			  else  nelist.add(ne);
							                			//  System.out.println("NE" +ne.getRefIDS().toString());
							                			}
							                				neFrame.doDefaultCloseAction();  
							                		
							                		}
							                			});
							                		
							                		
							                		
							                		neFrame.pack();
							                		addChild(neFrame,30,30,800,900);
							                	neFrame.pack();
					        			               neFrame.setVisible(true);
					        			               neFrame.moveToFront();
							                	}
							                });	
							                
							                //NE global
							                jmneG.addActionListener(new ActionListener(){
							                	@Override public void actionPerformed(ActionEvent ae){
							                		String refId=words.get(indexes[0]).getNERef();
							                		int kk=0; boolean found=false;
							                		NamedEntity r=null;
							                		while((kk<nelist.size())&&(!found)){
							                			if (nelist.get(kk).getId().equals(refId)){
							                				r=nelist.get(kk); found=true;
							                			}
							                			else kk=kk+1;
							                		}
							                		rr=r;
							                		final ArrayList<RefWord> refw=r.getRefIDS();
							        				//System.out.println("No Refs "+refw.size());
							        				String labelRef1="";
							        				for (int j=0;j<refw.size();j++){
							                          ArrayList<String> reftok=refw.get(j).getTIDs();
							                          for(int k=0;k<reftok.size();k++)
							                        	  labelRef1=labelRef1+tokens.get(indexIdToken.get(reftok.get(k)).intValue()).getLabel()+" ";
							        					//labelRef=labelRef+words.get(indexes[j]).getTranslitLabel(typScript)+" ";
							        					//System.out.println(words.get(indexIdWord.get(refw.get(j).getWID()).intValue()).getTranslitLabel(typScript));
							        				}
							        				//if(i==0){
							        				labelRef1=labelRef1.substring(0, labelRef1.length()-1);
							        		//		System.out.println("NE ="+labelRef1);
							        				final String labelRef=labelRef1;
							        				int k=indexIdWord.get(refw.get(refw.size()-1).getWID()).intValue()+1;
							        				startGlobalNE=k;  endGlobalNE=words.size();
							        				
							        				final ChildFrame neSelFrame=new ChildFrame("Insert Global NE "+labelRef,mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
							        				Container  co= neSelFrame.getContentPane();
							        				final JCheckBox vorher=new JCheckBox("Before"); vorher.setSelected(false);
							        				final JCheckBox nach=new JCheckBox("After");nach.setSelected(false);
							        				final JButton selA=new JButton("Select All");selA.setEnabled(false);
							        				final JButton selC=new JButton("Clear");selC.setEnabled(false);
							        				final JButton ins=new JButton("Insert"); ins.setEnabled(false);
							        				final JButton showB=new JButton("Show"); showB.setEnabled(false);
							        				final JEditorPane selNEed=new JEditorPane("text/html",Text);
							        				
							        				selNEed.setContentType("text/html");
							        				selNEed.setEditorKit(new HTMLEditorKit());
							        				selNEed.setEditable(false);
			                			 
							        				selNEed.setFont(typFont);
			                				         String bodyRule = "body { font-family: " + typFont.getFamily() + "; " +
			                				                 "font-size: " + typFont.getSize() + "pt; }";
			                				         ((HTMLDocument)selNEed.getDocument()).getStyleSheet().addRule(bodyRule);
							        				
							        				
							        				co.setFont(etiopicText);
							                		co.setLayout(new GridBagLayout());
							                		JPanel contr=new JPanel();
							                		contr.setLayout(new GridLayout(2,3));
							                		GridBagConstraints gbc=new GridBagConstraints();
							                		gbc.gridx=0;
							                		gbc.gridy=0;
							                		
							                		gbc.insets.left=2;
							                		gbc.insets.right=2;
							                		gbc.insets.top=2;
							                		gbc.gridwidth=1;
							                		gbc.anchor=GridBagConstraints.NORTHWEST;
							                		gbc.fill=GridBagConstraints.HORIZONTAL;
							                		gbc.insets.bottom=2;
							                		contr.add(vorher);
							                
							                		contr.add(nach);
							                	
							                		contr.add(showB);
							                		
							                		contr.add(selA);
							                	
							                		contr.add(selC);
							                		
							                		contr.add(ins);
							                		co.add(contr,gbc);
							                	gbc.weightx=100;
							                	gbc.weighty=100;
                                                        JScrollPane scrollNE=new JScrollPane(selNEed);
                                                    	gbc.fill=GridBagConstraints.BOTH;
					        				    gbc.gridy=1;
								                		co.add(scrollNE,gbc);
							                		vorher.addActionListener(new ActionListener() {
							                			public void actionPerformed(ActionEvent e) {
							                				if(vorher.isSelected() ) {
							                					startGlobalNE=0; endGlobalNE=indexIdWord.get(refw.get(0).getWID()).intValue();;
							                					showB.setEnabled(true);nach.setEnabled(false);
							                				}
							                				else if(!nach.isSelected()) {
							                					showB.setEnabled(false);
							                					ins.setEnabled(false);
							                					selA.setEnabled(false);
							                					selC.setEnabled(false);
							                				}
							                			}
							                		});
							        			
							                		nach.addActionListener(new ActionListener() {
							                			public void actionPerformed(ActionEvent e) {
							                				if(nach.isSelected() ) {
							                					startGlobalNE=indexIdWord.get(refw.get(refw.size()-1).getWID()).intValue()+1;; 
							                					endGlobalNE=words.size();vorher.setEnabled(false);
							                					showB.setEnabled(true);
							                				}
							                				else if(!vorher.isSelected()) {
							                					showB.setEnabled(false);
							                					ins.setEnabled(false);
							                					selA.setEnabled(false);
							                					selC.setEnabled(false);
							                				}
							                			}
							                		});
							                		showB.addActionListener(new ActionListener() {
							                			public void actionPerformed(ActionEvent e) {
							                		
							        				int start=startGlobalNE;
							        			         posNE=new ArrayList<String>();
							        			   //      System.out.println("start "+ start+ " end "+ endGlobalNE+ " size "+refw.size());
							        			 hm=new HashMap<String,ArrayList<RefWord>>(); 
							        			 
							        				while ((start+refw.size())<endGlobalNE-1){
							        					if(start!=indexIdWord.get(refw.get(0).getWID()).intValue()) {
							        					String wordL="";
							        					//System.out.println("START "+start+ "REFW "+refw.size());
							        					 ArrayList<RefWord> rws=new ArrayList<RefWord>();
							        					int x=0; boolean foundne=true;
							        					while((x<refw.size())&&(foundne)) {
							        						
							        						int xx=0; boolean foundt=true;
							        						ArrayList<String> tids=new ArrayList<String>();
							        						while((xx<words.get(start+x).getTokenIds().size())&&(foundt)) {
							        							String t=tokens.get(indexIdToken.get(words.get(start+x).getTokenIds().get(xx)).intValue()).getLabel();
							        				//			System.out.println("T = "+t);
							        							int xxx=0; boolean foundtr=false;
							        							 while((xxx<refw.get(x).getTIDs().size()) &&(!foundtr)){
							        								 String s1=tokens.get(indexIdToken.get(refw.get(x).getTIDs().get(xxx)).intValue()).getLabel();
							        					//			 System.out.println("S1 = "+s1);
							        								 if(t.equals(s1)) {foundtr=true; }
							        								 else xxx=xxx+1;
							        							 }
							        							 if (foundtr) {wordL=wordL+t+" ";
							        							 tids.add(tokens.get(indexIdToken.get(words.get(start+x).getTokenIds().get(xx)).intValue()).getId());
							        							
							        							 }
							        							 foundt=foundt && foundtr;
							        							 if (foundt) xx=xx+1;
							        						             //wordL=wordL+words.get(start+x).getTranslitLabel(typScript)+" ";
							        						}
							        						foundne=foundne && foundt;
							        						if(foundne) { 
							        							RefWord rw=new RefWord(words.get(start+x).getId(),tids); 
							        							rws.add(rw);
							        							x=x+1;
							        						}
							        					//System.out.println("WORDL "+wordL);
							        					}
							        					if(!wordL.isEmpty())
							        					wordL=wordL.substring(0, wordL.length()-1);
							        					//System.out.println("WORDL = "+wordL);
							        				    if(wordL.equals(labelRef)){
							        				    	posNE.add(""+start);
							        				    	hm.put(words.get(start).getId(), rws);
							        				    	start=start+refw.size();
							        				    	
							        				    }
							        				    else start=start+1;
							        				}
							        				else start=indexIdWord.get(refw.get(refw.size()-1).getWID()).intValue()+1;
							                	}
							        				
							        				
							        				
							        				//String Text="<html><body>";
							        				String Text="";
					        				         String TextOhneHtml="";
					        				         for (int i=0; i<posNE.size();i++){
					        				        	 int posRed=Integer.parseInt(posNE.get(i));
					        				        	 //System.out.println("Position in red " + posRed);
					        				        	 int anfang;
					        				        	 int ende;
					        				        	 if ((posRed-10)>0) {anfang=posRed-10;}
					        				        	 else anfang=0;
					        				        	 if((posRed+indexes.length+10)<words.size()){ende=posRed+indexes.length+10;}
					        				        	 else ende=words.size()-1;
					        				        	 
					        				        	 int j=anfang;
					        				        	 while (j<=ende){
					        				        		 if(j<posRed){
					        				        			 Text=Text+"<font face='Ethiopic Unicode' color='black'>"+words.get(j).getTranslitLabel(typScript)+"</font>"+" ";
					        				        			 //System.out.println("Black 1" + Text + "**");
					        				        			 j++;
					        				        		 }
					        				        		 if((j>=posRed)&&(j<posRed+indexes.length)){
					        				        			 String s="";
					        				        			 for (int jj=0;jj<refw.size();jj++)
					        				        				 s=s+words.get(j+jj).getTranslitLabel(typScript)+" ";
					        				        			 s=s.substring(0,s.length()-1);
					        				        			 Text=Text+"<font face='Ethiopic Unicode' color='red'>"+s+"</font>"+" ";
					        				        			 if(! words.get(j).getNERef().isEmpty()) {
					        				        				 int n=0; boolean fne=false;
					        				        				 while((n<nelist.size())&&(!fne)) {
					        				        					 if(words.get(j).getNERef().equals(nelist.get(n).getId())) {
					        				        				                   Text=Text+"<font face='Ethiopic Unicode' color='green'><sup>"+nelist.get(n).getTyp()+"</sup></font>"+" ";
					        				        				                   fne=true;
					        				        					 }
					        				        					 else n=n+1;
					        				        				 }
					        				        			 }
					        				        			 j=posRed+refw.size();
					        				        			 //System.out.println("Red " + Text + "**");
					        				        		 }
					        				        		 if (j==(posRed+indexes.length)){
					        				        		 Text=Text+"<input type='checkbox' id='"+words.get(posRed).getId()+"'>"+"<font color='blue' size='-1'>" +i+"</font>"+" ";
					        				        		 j++;
					        				        		 }
					        				        		 if (j>posRed+indexes.length){
					        				        			 Text=Text+"<font face='Ethiopic Unicode' color='black'>"+words.get(j).getTranslitLabel(typScript)+"</font>"+" ";
					        				        			// System.out.println("Black 2" + Text + "**");
					        				        			 j++;
					        				        		 }
					        				        		 
					        				        	 }
					        				        	 Text=Text+"<br/><br/>";
					        				        	 
					        				         }
					     					
					        				        // Text=Text+"</body></html>";
					        				         selNEed.setText(Text);selNEed.setCaretPosition(0);
					        				         selA.setEnabled(true);
					        				         selC.setEnabled(true);
					        				         showB.setEnabled(false);
					        				         vorher.setEnabled(false);
					        				         nach.setEnabled(false);
					        				         ins.setEnabled(true);
							                			}
							                		});
								                		//
								                		selA.addActionListener(new ActionListener(){
								                			public void actionPerformed(ActionEvent e){
								                				//String Text="<html><body>";
								                				String Text="";
								        				         String TextOhneHtml="";
								        				         for (int i=0; i<posNE.size();i++){
								        				        	 int posRed=Integer.parseInt(posNE.get(i));
								        				        	 //System.out.println("Position in red " + posRed);
								        				        	 int anfang;
								        				        	 int ende;
								        				        	 if ((posRed-10)>0) {anfang=posRed-10;}
								        				        	 else anfang=0;
								        				        	 if((posRed+indexes.length+10)<words.size()){ende=posRed+indexes.length+10;}
								        				        	 else ende=words.size()-1;
								        				        	 
								        				        	 int j=anfang;
								        				        	 while (j<=ende){
								        				        		 if(j<posRed){
								        				        			 Text=Text+"<font face='Ethiopic Unicode' color='black'>"+words.get(j).getTranslitLabel(typScript)+"</font>"+" ";
								        				        			 //System.out.println("Black 1" + Text + "**");
								        				        			 j++;
								        				        		 }
								        				        		 if((j>=posRed)&&(j<posRed+indexes.length)){
								        				        			 String s="";
								        				        			 for (int jj=0;jj<refw.size();jj++)
								        				        				 s=s+words.get(j+jj).getTranslitLabel(typScript)+" ";
								        				        			 s=s.substring(0,s.length()-1);
								        				        			 Text=Text+"<font face='Ethiopic Unicode' color='red'>"+s+"</font>"+" ";
								        				        			 if(! words.get(j).getNERef().isEmpty()) {
								        				        				 int n=0; boolean fne=false;
								        				        				 while((n<nelist.size())&&(!fne)) {
								        				        					 if(words.get(j).getNERef().equals(nelist.get(n).getId())) {
								        				        				                   Text=Text+"<font face='Ethiopic Unicode' color='green'><sup>"+nelist.get(n).getTyp()+"</sup></font>"+" ";
								        				        				                   fne=true;
								        				        					 }
								        				        					 else n=n+1;
								        				        				 }
								        				        			 }
								        				        			 //System.out.println("Red " + Text + "**");
								        				        			 j=j+refw.size();
								        				        		 }
								        				        		 if (j==(posRed+indexes.length)){
								        				        		 Text=Text+"<input type='checkbox' id='"+words.get(posRed).getId()+"checked='checked'>"+"<font color='blue' size='-1'>" +i+"</font>"+" ";
								        				        		 j++;
								        				        		 }
								        				        		 if (j>posRed+indexes.length){
								        				        			 Text=Text+"<font face='Ethiopic Unicode' color='black'>"+words.get(j).getTranslitLabel(typScript)+"</font>"+" ";
								        				        			// System.out.println("Black 2" + Text + "**");
								        				        			 j++;
								        				        		 }
								        				        		
								        				        	 }
								        				        	 Text=Text+"<br/><br/>";
								        				        	 
								        				         }
								     					
								        				        // Text=Text+"</body></html>";
								        				         selNEed.setText(Text);selNEed.setCaretPosition(0);
								                			}
								                		});
								                		
								                		selC.addActionListener(new ActionListener(){
								                			public void actionPerformed(ActionEvent e){
								                				//String Text="<html><body>";
								                				String Text="";
								        				         String TextOhneHtml="";
								        				         for (int i=0; i<posNE.size();i++){
								        				        	 int posRed=Integer.parseInt(posNE.get(i));
								        				        	 //System.out.println("Position in red " + posRed);
								        				        	 int anfang;
								        				        	 int ende;
								        				        	 if ((posRed-10)>0) {anfang=posRed-10;}
								        				        	 else anfang=0;
								        				        	 if((posRed+indexes.length+10)<words.size()){ende=posRed+indexes.length+10;}
								        				        	 else ende=words.size()-1;
								        				        	 
								        				        	 int j=anfang;
								        				        	 while (j<=ende){
								        				        		 if(j<posRed){
								        				        			 Text=Text+"<font face='Ethiopic Unicode' color='black'>"+words.get(j).getTranslitLabel(typScript)+"</font>"+" ";
								        				        			 //System.out.println("Black 1" + Text + "**");
								        				        			 j++;
								        				        		 }
								        				        		 if((j>=posRed)&&(j<posRed+indexes.length)){
								        				        			 String s="";
								        				        			 for (int jj=0;jj<refw.size();jj++)
								        				        				 s=s+words.get(j+jj).getTranslitLabel(typScript)+" ";
								        				        			 s=s.substring(0,s.length()-1);
								        				        			 Text=Text+"<font face='Ethiopic Unicode' color='red'>"+s+"</font>"+" ";
								        				        			 if(! words.get(j).getNERef().isEmpty()) {
								        				        				 int n=0; boolean fne=false;
								        				        				 while((n<nelist.size())&&(!fne)) {
								        				        					 if(words.get(j).getNERef().equals(nelist.get(n).getId())) {
								        				        				                   Text=Text+"<font face='Ethiopic Unicode' color='green'><sup>"+nelist.get(n).getTyp()+"</sup></font>"+" ";
								        				        				                   fne=true;
								        				        					 }
								        				        					 else n=n+1;
								        				        				 }
								        				        			 }
								        				        			 //System.out.println("Red " + Text + "**");
								        				        			 j=j+refw.size();
								        				        		 }
								        				        		 if (j==(posRed+indexes.length)){
								        				        		 Text=Text+"<input type='checkbox' id='"+words.get(posRed).getId()+"'>"+"<font color='blue' size='-1'>" +i+"</font>"+" ";
								        				        		 j++;
								        				        		 }
								        				        		 if (j>posRed+indexes.length){
								        				        			 Text=Text+"<font face='Ethiopic Unicode' color='black'>"+words.get(j).getTranslitLabel(typScript)+"</font>"+" ";
								        				        			// System.out.println("Black 2" + Text + "**");
								        				        			 j++;
								        				        		 }
								        				        		
								        				        	 }
								        				        	 Text=Text+"<br/><br/>";
								        				        	 
								        				         }
								     					
								        				        // Text=Text+"</body></html>";
								        				         selNEed.setText(Text);selNEed.setCaretPosition(0);
								                			}
								                		});
							        				//
								                	ins.addActionListener(new ActionListener() {
								                		public void actionPerformed(ActionEvent e) {
								                			
								                			 HTMLDocument doc = (HTMLDocument) selNEed.getDocument();
				        								        ElementIterator it = new ElementIterator(doc);
				        								        Element element;
				        								        int k=0;
                                                        ArrayList<String> selectW=new ArrayList<String>();
				        								        while ( (element = it.next()) != null )
				        								        {
				        								            //System.out.println("*** "+element.getName()+"  "+element.getClass()+" **");
	                                                             if(element.getName().equals("input")){
	                                                           	  k++;
	                                                           	  //System.out.println("Checkbox "+k);
				        								            AttributeSet as = element.getAttributes();
				        								            Enumeration enumm = as.getAttributeNames();
	                                                               boolean issel=false;
	                                                               String idWort="";
				        								            while( enumm.hasMoreElements() )
				        								            {
				        								                Object name = enumm.nextElement();
				        								                Object value = as.getAttribute( name );
				        								         
				        								               //System.out.println( "\t" + name + " : " + value );
	                                                                  if((""+name).equals("id")) {
	                                                                	  //System.out.println("$$$"+value);
	                                                                  idWort=""+value;
	                                                                  //System.out.println("\t Word"+getWord(idWort).getTranslitLabel(typScript));
	                                                                  }
	                                                                 
	                                                                 if (value instanceof ToggleButtonModel)
				        								                {
				        								                	ToggleButtonModel model = (ToggleButtonModel)value;
				        								                	//if(global.isSelected()) model.setSelected(true);
				        								                	
				        								                	if( model.isSelected()) {
				        								                		//System.out.println("!!!!!"+ idWort+" "+getWord(idWort).getTranslitLabel(typScript));
				        								                	    int posC=idWort.indexOf("checked");
				        								                	    if (posC>=0) idWort=idWort.substring(0,posC);
				        								                //	   System.out.println("Selected "+idWort + " position "+indexIdWord.get(idWort).intValue());
				        								                	   if(hm.containsKey(idWort)) { 
				        								                		   
				        								                	//	   System.out.println ("Words "+ hm.get(idWort).size());
				        								                		NamedEntity ne=new NamedEntity("N"+UUID.randomUUID(), rr.getrefId(),rr.getTyp(),hm.get(idWort),rr.getAttr(),false,true);
				        								                		
				        								                	
				        								                		for(int j=0;j<hm.get(idWort).size();j++) {
				        								                			if(!words.get(indexIdWord.get(hm.get(idWort).get(j).getWID()).intValue()).getNERef().isEmpty()) {
				        								                				int x=0; boolean fa=false;
				        								                				while((x<nelist.size())&&(!fa)) {
				        								                					if(nelist.get(x).getId().equals(words.get(indexIdWord.get(hm.get(idWort).get(j).getWID()).intValue()).getNERef())) {
				        								                						fa=true;
				        								                						
				        								                					}
				        								                					else x=x+1;
				        								                				}
				        								                				if(fa) nelist.remove(x);
				        								                			}
				        								                			nelist.add(ne);
				        								                			words.get(indexIdWord.get(hm.get(idWort).get(j).getWID()).intValue()).setNERef(ne.getId());
				        								                			for(int kk=0;kk<hm.get(idWort).get(j).getTIDs().size();kk++) {
				        								                				String tt=hm.get(idWort).get(j).getTIDs().get(kk);
				        								                				tokens.get(indexIdToken.get(tt).intValue()).setNERef(ne.getId());
				        								                			}
				        								                		}
				        								                	   }
				        								                	   else {}
				        								                	   //System.out.println("not in hm");
				        								                	   
				        								                	    issel=true;
				        								                	}
				        								                }
				        								            }
	                                                             }
				        								        }
				        								        neSelFrame.doDefaultCloseAction();
								                		}
								                	});	
								                //		
							        				neSelFrame.pack();
							                		addChild(neSelFrame,30,30,700,500);
							                		
					        			               neSelFrame.setVisible(true);
					        			               neSelFrame.moveToFront();
							        				
							                		
							                	}
							                });
							                
							                //NE modify
							           //     jmneM.addActionListener(new ActionListener(){});	
							                
							                
							                //comment - text
							              
							                jmcomm1.addActionListener(new ActionListener(){
							                	@Override public void actionPerformed(ActionEvent ae){
							                		final int position=translitWordList.getSelectedIndex();
							                		final HTMLEditorPane hp =new HTMLEditorPane();
							                		
							                		hp.setText(StringEscapeUtils.unescapeHtml(words.get(position).getCooment()));
							                		//System.out.println("Comment"+ Jsoup.parse(hp.getText()).text()+"***");
							                		
							                		if(Jsoup.parse(hp.getText()).text().isEmpty())
							                			hp.setText("<div style=\'font-family:Ethiopic Unicode\'></div>");
							                		final ChildFrame commFrame=new ChildFrame("Add a Comment",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
							                		Container chp=commFrame.getContentPane();
							                		chp.setLayout(new GridBagLayout());
							                		GridBagConstraints gbc=new GridBagConstraints();
							                		gbc.gridx=0;
							                		gbc.gridy=0;
							                		gbc.weightx=100;
							                		gbc.weighty=100;
							                		gbc.insets.left=2;
							                		gbc.insets.right=2;
							                		gbc.insets.top=2;
							                		gbc.gridwidth=2;
							                		gbc.anchor=GridBagConstraints.NORTHWEST;
							                		gbc.insets.bottom=2;
							                		chp.add(hp,gbc);
							                		final JButton saveC=new JButton("Save");
							                		final JButton cancelC=new JButton("Cancel");
							                		gbc.gridy=1;gbc.gridx=0;
							                		gbc.gridwidth=1;
							                		gbc.anchor=GridBagConstraints.CENTER;
							                		chp.add(saveC,gbc);
							                		gbc.gridx=1;
							                		chp.add(cancelC,gbc);
							                		commFrame.pack();
							                		addChild(commFrame,30,30,300,300);
							                		commFrame.pack();
					        			               commFrame.setVisible(true);
					        			               commFrame.moveToFront();
					        			               saveC.addActionListener(new ActionListener(){
										                	@Override public void actionPerformed(ActionEvent ae){
										                		words.get(position).setComment(StringEscapeUtils.escapeHtml(hp.getText()));
										                		commFrame.doDefaultCloseAction();
										                	}
					        			               });	
					        			               cancelC.addActionListener(new ActionListener(){
										                	@Override public void actionPerformed(ActionEvent ae){
										                		
										                		commFrame.doDefaultCloseAction();
										                	}
					        			               });	
							                	}
							                	});
							                //comment - colour
							                jmcolor.addActionListener(new ActionListener(){
							                	@Override public void actionPerformed(ActionEvent ae){
							                		final int position=translitWordList.getSelectedIndex();
							                		//System.out.println("color");
							                		final JColorChooser colorChooser = new JColorChooser(Color.BLACK); // default color is black
							                		colorChooser.setBorder(null);
							                		colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
							                		    public void stateChanged(ChangeEvent e) {
							                		    	words.get(position).setFColor(colorChooser.getSelectionModel().getSelectedColor());
							                		    	
							                		    }
							                		});
							                		// colorChooser.setBounds(this.getX(), button.getY() + 20, 600, 300);
							                	       // colorChooser.setVisible(true);
							                	      //  contentPane.add(colorChooser);
							                		final ChildFrame colorFrame=new ChildFrame("Add a Comment",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
							                		colorFrame.getContentPane().add(colorChooser);
							                		colorFrame.pack();
							                		addChild(colorFrame,30,30,300,300);
							                		colorFrame.pack();
					        			               colorFrame.setVisible(true);
					        			               colorFrame.moveToFront();
							                	}
							                });
							                // replace
							                
							                jmreplace.addActionListener(new ActionListener(){
							                	@Override public void actionPerformed(ActionEvent ae){
							                		final int indexF = translitWordList.getSelectedIndex();
							                	
							                		int kk=0; boolean foundne=false;
						                		
						                				if (!words.get(indexF).getNERef().isEmpty()) foundne=true;
						                				else if (!words.get(indexF).getFidalChildren().get(words.get(indexF).getFidalChildren().size()-1).getPB().isEmpty()) foundne=true;
						                				else {
						                					int kkk=0; boolean foundlb=false;
						                					while((kkk<words.get(indexF).getFidalChildren().size())&&(!foundlb)) {
						                						if(!words.get(indexF).getFidalChildren().get(kkk).getLB().isEmpty()) foundlb=true;
						                						else kkk=kkk+1;
						                					}
						                					if (foundlb) foundne=true;
						                					else kk=kk+1;
						                				}
						                			
						                			if(!foundne) {
							                		//	
							                		int confirm= JOptionPane.showOptionDialog(desk,
							             	                "You are going to replace this unit. Are you sure? ",
							             	                "Replace Confirmation", JOptionPane.YES_NO_OPTION,
							             	                JOptionPane.QUESTION_MESSAGE, null, null, null);
							             			if (confirm == JOptionPane.YES_OPTION) {
							             				WortNode toRepl=words.get(indexF).copyWortNode();
							             				
							             				final ChildFrame insertFrame=new ChildFrame("New Graphic Unit",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
								                		//insertFrame.setSize(200, 100);
								                		Container c=insertFrame.getContentPane();
								                		final JTextArea wordField=new JTextArea(10,15);
								                		wordField.setFont(typFont1);
								                		wordField.addFocusListener(new FocusListener() {

								                            @Override
								                            public void focusGained(FocusEvent e) {
								                                positionCursor=wordField;
								                               GeezFrame.setInputField(wordField);
								                                //System.out.println("Focus gt roots");
								                            }

								                            @Override
								                            public void focusLost(FocusEvent e) {
								                                //positionCursor=nothingSelected;
								                          	  //System.out.println("Focus lost");
								                            }
								                        });
								                		JLabel eticheta= new JLabel("Please insert text in FIDEL");
								                		eticheta.setForeground(Color.RED);
								                		wordField.setEditable(true);
								                		wordField.setEnabled(true);
								                		wordField.setLineWrap(true);
								                		GridBagConstraints gbc=new GridBagConstraints();
								                		gbc.gridx=0;
								                		gbc.gridy=0;
								                		gbc.weightx=100;
								                		gbc.weighty=100;
								                		gbc.insets.left=2;
								                		gbc.insets.right=2;
								                		gbc.insets.top=2;
								                		gbc.gridwidth=2;
								                		gbc.anchor=GridBagConstraints.NORTHWEST;
								                		gbc.insets.bottom=2;
								                	c.setLayout(new GridBagLayout());
								                	//	pp.setLayout(new GridBagLayout());
								                	//	String wordInsert="";
								                	c.add(eticheta,gbc);
								                		JScrollPane scrp =new JScrollPane(wordField);
								                		
								                		
								                		
								                		gbc.gridy=1;gbc.insets.top=0;
								                		gbc.fill=GridBagConstraints.HORIZONTAL;
								                		c.add(scrp,gbc);
								                		gbc.fill=GridBagConstraints.NONE;
								                		gbc.gridy=2;
	                                                    gbc.gridwidth=1;
	                                                    final	JButton unclearB=new JButton("\u25a7");
	                                                    final	JButton erasedB=new JButton("\u2026");
								                	final	JButton insertA=new JButton("Insert");
								                		JButton cancel=new JButton("Cancel");
								                		insertA.setEnabled(false);
								                		insertA.setFocusable(false);
								                		insertA.setEnabled(false);
								                		
								                	 c.add(insertA,gbc);
								                	 gbc.insets.left=0;gbc.gridx=1;
								                	 c.add(cancel,gbc);gbc.gridy=2;
								                	 gbc.gridx=2; c.add(unclearB);
								                	 gbc.gridx=3; c.add(erasedB);
								                	 
								                	 unclearB.addActionListener(new ActionListener(){
								                			@Override public void actionPerformed(ActionEvent e){
								                				wordField.setText(wordField.getText()+"\u25a7");
								                			}
								                		});
								                	 erasedB.addActionListener(new ActionListener(){
								                			@Override public void actionPerformed(ActionEvent e){
								                				wordField.setText(wordField.getText()+"\u2026");
								                			}
								                		});
								                		
								                		cancel.addActionListener(new ActionListener(){
								                			@Override public void actionPerformed(ActionEvent e){
								                				insertFrame.doDefaultCloseAction();
								                			}
								                		});
								                		
								                		
								                		wordField.getDocument().addDocumentListener(new DocumentListener() {
							                				  public void changedUpdate(DocumentEvent e) {
							                				    changed();
							                				  }
							                				  public void removeUpdate(DocumentEvent e) {
							                				    changed();
							                				  }
							                				  public void insertUpdate(DocumentEvent e) {
							                				    changed();
							                				  }

							                				  public void changed() {
								                				     if (!wordField.getText().equals("")){
								                				    	 String aux=wordField.getText().replaceAll("\\p{InEthiopic}", "");
								                				    	 if((aux.isEmpty())){
								                				    		 
								                				             insertA.setEnabled(true);
								                				    	 }
								                				    	 else if((aux.replaceAll("\\s", "").isEmpty()) && (StringUtils.countMatches(aux, " ")==(aux.length()-1))) insertA.setEnabled(true);
								                				    	 else if( (aux.indexOf("\u25a7")>0)||(aux.indexOf("\u2026")>0)) insertA.setEnabled(true);
								                				    	 
								                				     }
								                				   

								                				  }
							                				});
								                			
								                		insertA.addActionListener(new ActionListener(){
								                			@Override public void actionPerformed(ActionEvent e){
								                				 wordsBackup.clear();tokensBackup.clear();
								                				for(int i=0;i<words.size();i++){
								   			            	    	wordsBackup.add(words.get(i).copyWortNode());
								   			            	    }
								   			            	  for(int i=0;i<tokens.size();i++){
								   			            		    //System.out.println("Token "+tokens.get(i).getLabel());
								   			            	    	tokensBackup.add(tokens.get(i).copyToken());
								   			            	    }
								   			            	  undoMenu.setEnabled(true);
								                				String text=wordField.getText().replaceAll("\n","");
				
								                				    WortNode wNew=createStructureWord(text,toRepl.getId());
								                				    Token to =new Token ("T0>"+wNew.getId(),false,wNew.getLetters());
								                				    int posintrod=0;
								                				  ArrayList<String> newToken=new ArrayList<String>();
								                				  newToken.add(to.getId());
								                				  wNew.setTokenIds(newToken);
								                				 								                	
								                				   String keyinsert=wNew.getFidalInternLabel(typScript,typDoc)+"*"+wNew.getTranslitLabel(typScript);
								                				    if (index.containsKey(keyinsert))
								                				    	index.get(keyinsert).add(wNew.getId());
								                				    else{ 
								                				    	ArrayList<String> tempw=new ArrayList<String>();
								                				    	tempw.add(wNew.getId());
								                				    	index.put(keyinsert,tempw);
								                				    }
								                				    if (indexT.containsKey(to.getLabel()))
								                				    	indexT.get(to.getLabel()).add(to.getId());
								                				    else{ 
								                				    	ArrayList<String> tempw=new ArrayList<String>();
								                				    	tempw.add(to.getId());
								                				    	indexT.put(to.getLabel(),tempw);
								                				    }
								                				   
								                				    
								                				labelRepl=words.get(indexF).getTranslitLabel(typScript);
								                				  //words.set(indexF,wNew);
								                				  words.get(indexF).setAutomToken(wNew.getAutomToken());
								                				
								                				  words.get(indexF).setComment(wNew.getCooment());
								                				  words.get(indexF).setFColor(wNew.getFcolor());
								                				  words.get(indexF).setFidalChildren(wNew.getFidalChildren());
								                				  words.get(indexF).setQuotIds(wNew.getQuotIds());
								                				  words.get(indexF).setStrukturIds(wNew.getStrukturIds());
								                				  words.get(indexF).setTokenIds(wNew.getTokenIds());
								                				 
										             			//	modelOrig.set(indexF, wNew);
												             		//modelTranslit.set(indexF, wNew);
										             				
										             				String keydel=toRepl.getFidalInternLabel(typScript,typDoc)+"*"+toRepl.getTranslitLabel(typScript);
										             				if(index.get(keydel)!=null){
										             				index.get(keydel).remove(toRepl.getId());
										             				//if(index.get(keydel)!=null){
										             				if(index.get(keydel).size()==0) index.remove(keydel);}
										             				//}
										             				createIndexWords();
										             	//		System.out.println("create words");	
										             			tokens.set(indexIdToken.get(toRepl.getTokenIds().get(0)).intValue(), to);
										             				for(int i=1;i<toRepl.getTokenIds().size();i++){
										             				//	System.out.println(tokens.get(indexIdToken.get(toDel1.get(j).getTokenIds().get(i)).intValue()).getLabel());
										             				//	System.out.println(tokens.get(indexIdToken.get(toDel1.get(j).getTokenIds().get(i)).intValue()).getId());
										             					if(indexT.get(tokens.get(indexIdToken.get(toRepl.getTokenIds().get(i)).intValue()).getLabel())!=null){
										             					indexT.get(tokens.get(indexIdToken.get(toRepl.getTokenIds().get(i)).intValue()).getLabel()).remove(tokens.get(indexIdToken.get(toRepl.getTokenIds().get(i)).intValue()).getId());
											             				if(indexT.get(tokens.get(indexIdToken.get(toRepl.getTokenIds().get(i)).intValue()).getLabel()).size()==0){
											             				//	System.out.println("***" +tokens.get(indexIdToken.get(toDel1.get(j).getTokenIds().get(i)).intValue()).getLabel());
											             					indexT.remove(tokens.get(indexIdToken.get(toRepl.getTokenIds().get(i)).intValue()).getLabel());
											             				}
											             				tokens.remove(indexIdToken.get(toRepl.getTokenIds().get(i)).intValue());
											             				createIndexTokens();
										             					}
										             					
											             				
										             				//	System.out.println("create tokens");	
										             					
										             				}
										             				//int p=translitWordList.getSelectedIndex();
										             		
										             				
			                                                      //   translitWordList.getContents().remove(p);
										             				
										             				//fidalWordList.getContents().remove(p);
										             			//	System.out.println("reapinted");	
										             				
										             					
										             			//	fidalWordList.setModel(modelOrig);
										             				//translitWordList.setModel(modelTranslit);
										             				//did1Repl=true;Fehlern="";
										             				 words.get(indexF).setError(0);
										             				fidalWordList.revalidate();
										             				translitWordList.revalidate();
										             				
										             				fidalWordList.repaint();
										             			translitWordList.repaint();	
										             	       
										   			        errorPane.setText(updateErrors());
										   			        //errorPane.revalidate();
										   			        //errorPane.repaint();
								                				
								                				
								                				
								                				insertFrame.doDefaultCloseAction();
								                			}
								                		});
								                		insertFrame.pack();
								                		addChild(insertFrame,30,30,200,300);
								                		insertFrame.pack();
						        			               insertFrame.setVisible(true);
						        			               insertFrame.moveToFront();
							             						
						        			             
							             				
							             				
							             				
							             				
							             				
							             				//
							             				
							             				
							             			}
							             			
							                	}
						                			else {
								                		JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" > Graphic Unit is part of a NE or contain PBs or LBs. Please check first the NE, PBs and LBs and delete them</font></p></html>", "Dialog",
						   			  		       		        JOptionPane.ERROR_MESSAGE);
								                	}
							                	}
							                	
							                });
							                	
							                
							                //delete
							                
							                jmdelete.addActionListener(new ActionListener(){
							                	@Override public void actionPerformed(ActionEvent ae){
							                		boolean okdel=true;
							                		int k=0; boolean foundw=false;
							                		while((k<indexes.length)&&(!foundw)) {
							                			ArrayList<String> indexDiv=words.get(indexes[k]).getStrukturIds();
							                			if (indexDiv.size()>0) {
							                				int kk=0;
							                				while((kk<indexDiv.size())&&(!foundw)) {
							                					if ((divisions.get(indexIdDivision.get(indexDiv.get(kk)).intValue()).getWbegin().equals(words.get(indexes[k]).getId()))||
							                					(divisions.get(indexIdDivision.get(indexDiv.get(kk)).intValue()).getWend().equals(words.get(indexes[k]).getId())))
							                						foundw=true;
							                					else kk=kk+1;
							                				}
							                				if(!foundw) k=k+1;
							                			}
							                			else k=k+1;
							                		}
							                	okdel=!foundw;		
							                	if(okdel) {	
							                		boolean foundne=false;
							                		int kk=0;
							                			while	((kk<indexes.length)&&(!foundne)) {
							                				if (!words.get(indexes[kk]).getNERef().isEmpty()) foundne=true;
							                				else if (!words.get(indexes[kk]).getFidalChildren().get(words.get(indexes[kk]).getFidalChildren().size()-1).getPB().isEmpty()) foundne=true;
							                				else {
							                					int kkk=0; boolean foundlb=false;
							                					while((kkk<words.get(indexes[kk]).getFidalChildren().size())&&(!foundlb)) {
							                						if(!words.get(indexes[kk]).getFidalChildren().get(kkk).getLB().isEmpty()) foundlb=true;
							                						else kkk=kkk+1;
							                					}
							                					if (foundlb) foundne=true;
							                					else kk=kk+1;
							                				}
							                			}
							                			if(!foundne) {
							                		final int indexF = translitWordList.getMinSelectionIndex();
							                		String units="";
							                		if (indexes.length==1)units="GraphUnit";
							                		else units="GraphUnits";
							                		int confirm= JOptionPane.showOptionDialog(desk,
							             	                "You are going to delete this "+units+". Are you sure? ",
							             	                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
							             	                JOptionPane.QUESTION_MESSAGE, null, null, null);
							             			if (confirm == JOptionPane.YES_OPTION) {
							             				ArrayList<WortNode> toDel=new ArrayList<WortNode>();
							             				ArrayList<WortNode> toDel1=new ArrayList<WortNode>();
							             				for (int j=0;j<indexes.length;j++){
							             					toDel.add(words.get(indexes[j]));
							             					toDel1.add(words.get(indexes[j]).copyWortNode());
							             				}
							             				
							             					for (int j=0;j<indexes.length;j++){	
							             					//WortNode wdel= words.get(indexes[j]);
							             						modelTranslit.removeElement(indexes[j]);
							             						modelOrig.removeElement(indexes[j]);
							             				words.remove(toDel.get(j));
							             				//modelTranslit.update();
							             				//modelOrig.update();
	                                                // translitWordList.getContents().removeElement(toDel.get(j));
							             				
							             				//fidalWordList.getContents().removeElement(toDel.get(j));
							                        //   modelTranslit=new TranslitListModel(getInterf());
							                          
							                          // modelOrig=new WortListModel(getInterf());
							                         //  for( int k=0;k<words.size();k++){
							                        	//   modelTranslit.addElement(words.get(k));
							                        	  // modelOrig.addElement(words.get(k));
							                      //     }
							             				//modelTranslit.remove(indexes[j]);
							             				//modelOrig.remove(indexes[j]);
							             		
							             				
							             				String keydel=toDel.get(j).getFidalInternLabel(typScript,typDoc)+"*"+toDel.get(j).getTranslitLabel(typScript);
							             				if(index.get(keydel)!=null){
							             				index.get(keydel).remove(toDel.get(j).getId());
							             				//if(index.get(keydel)!=null){
							             				if(index.get(keydel).size()==0) index.remove(keydel);}
							             				//}
							             				createIndexWords();
							             		//	System.out.println("create words");	
							             				
							             				for(int i=0;i<toDel1.get(j).getTokenIds().size();i++){
							             				//	System.out.println(tokens.get(indexIdToken.get(toDel1.get(j).getTokenIds().get(i)).intValue()).getLabel());
							             				//	System.out.println(tokens.get(indexIdToken.get(toDel1.get(j).getTokenIds().get(i)).intValue()).getId());
							             					if(indexT.get(tokens.get(indexIdToken.get(toDel1.get(j).getTokenIds().get(i)).intValue()).getLabel())!=null){
							             					indexT.get(tokens.get(indexIdToken.get(toDel1.get(j).getTokenIds().get(i)).intValue()).getLabel()).remove(tokens.get(indexIdToken.get(toDel1.get(j).getTokenIds().get(i)).intValue()).getId());
								             				if(indexT.get(tokens.get(indexIdToken.get(toDel1.get(j).getTokenIds().get(i)).intValue()).getLabel()).size()==0){
								             				//	System.out.println("***" +tokens.get(indexIdToken.get(toDel1.get(j).getTokenIds().get(i)).intValue()).getLabel());
								             					indexT.remove(tokens.get(indexIdToken.get(toDel1.get(j).getTokenIds().get(i)).intValue()).getLabel());
								             				}
								             				tokens.remove(indexIdToken.get(toDel1.get(j).getTokenIds().get(i)).intValue());
							             					}
							             					
								             				
							             					createIndexTokens();
							             					//System.out.println("create tokens");	
							             					
							             				}
							             				//int p=translitWordList.getSelectedIndex();
							             		
							             				
                                                      //   translitWordList.getContents().remove(p);
							             				
							             				//fidalWordList.getContents().remove(p);
							             			//	System.out.println("reapinted");	
							             				}
							             					
							             			//	fidalWordList.setModel(modelOrig);
							             				//translitWordList.setModel(modelTranslit);
							             				fidalWordList.revalidate();
							             				translitWordList.revalidate();
							             				
							             				fidalWordList.repaint();
							             			translitWordList.repaint();
							             				
							             			}
							                	}
							                	else {
							                		JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" > Graphic Units are part of a NE or contain PBs or LBs. Please check first the NE, PBs and LBs and delete them</font></p></html>", "Dialog",
					   			  		       		        JOptionPane.ERROR_MESSAGE);
							                	}
							                	}	
							                	else {
							                		JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" > Graphic Units which are margins of a division  cannot be deleted. Please check first the division</font></p></html>", "Dialog",
					   			  		       		        JOptionPane.ERROR_MESSAGE);
							                	}
							                }
							                });
							                //insert After
							                
							                jminsertA.addActionListener(new ActionListener(){
							                	@Override public void actionPerformed(ActionEvent ae){
							                		final int indexF = translitWordList.getMinSelectionIndex();
							                		ArrayList<String> divw=words.get(indexF).getStrukturIds();
							                		boolean okins=true;
							                		int k=0;
							                		boolean foundw=false;
							                		while((k<divw.size())&&(!foundw)) {
							                			if(divisions.get(indexIdDivision.get(divw.get(k)).intValue()).getWend().equals(words.get(indexF).getId()))
							                				
							                			{foundw=true;}
							                			else k=k+1;
							                		}
							                		//okins=!foundw;
							                		if(foundw) {
							                			
							                			int confirm= JOptionPane.showOptionDialog(desk,
								             	                "You are going to insert after a division margin . Are you sure? ",
								             	                "Insert Confirmation", JOptionPane.YES_NO_OPTION,
								             	                JOptionPane.QUESTION_MESSAGE, null, null, null);
								             			if (confirm == JOptionPane.YES_OPTION) okins=true;
								             			else okins=false;
							                		}
							                		else okins=true;
							                		if(okins)	{
							                		final ChildFrame insertFrame=new ChildFrame("Insert Graphic Unit  After",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
							                		//insertFrame.setSize(200, 100);
							                		Container c=insertFrame.getContentPane();
							                		final JTextArea wordField=new JTextArea(10,15);
							                		wordField.setFont(typFont1);
							                		wordField.addFocusListener(new FocusListener() {

							                            @Override
							                            public void focusGained(FocusEvent e) {
							                                positionCursor=wordField;
							                               GeezFrame.setInputField(wordField);
							                                //System.out.println("Focus gt roots");
							                            }

							                            @Override
							                            public void focusLost(FocusEvent e) {
							                                //positionCursor=nothingSelected;
							                          	  //System.out.println("Focus lost");
							                            }
							                        });
							                		JLabel eticheta= new JLabel("Please insert text in FIDEL");
							                		eticheta.setForeground(Color.RED);
							                		wordField.setEditable(true);
							                		wordField.setEnabled(true);
							                		wordField.setLineWrap(true);
							                		GridBagConstraints gbc=new GridBagConstraints();
							                		gbc.gridx=0;
							                		gbc.gridy=0;
							                		gbc.weightx=100;
							                		gbc.weighty=100;
							                		gbc.insets.left=2;
							                		gbc.insets.right=2;
							                		gbc.insets.top=2;
							                		gbc.gridwidth=2;
							                		gbc.anchor=GridBagConstraints.NORTHWEST;
							                		gbc.insets.bottom=2;
							                	c.setLayout(new GridBagLayout());
							                	//	pp.setLayout(new GridBagLayout());
							                	//	String wordInsert="";
							                		JScrollPane scrp =new JScrollPane(wordField);
							                		c.add(eticheta,gbc);
							                		
							                		gbc.gridy=1;gbc.insets.top=0;
							                		gbc.fill=GridBagConstraints.HORIZONTAL;
							                		c.add(scrp,gbc);
							                		gbc.fill=GridBagConstraints.NONE;
							                		gbc.gridy=2;
                                                    gbc.gridwidth=1;
                                                    final	JButton unclearB=new JButton("\u25a7");
                                                    final	JButton erasedB=new JButton("\u2026");
							                	final	JButton insertA=new JButton("Insert");
							                		JButton cancel=new JButton("Cancel");
							                		insertA.setEnabled(false);
							                		insertA.setFocusable(false);
							                		insertA.setEnabled(false);
							                		
							                	 c.add(insertA,gbc);
							                	 gbc.insets.left=0;gbc.gridx=1;
							                	 c.add(cancel,gbc);gbc.gridy=2;
							                	 gbc.gridx=2; c.add(unclearB);
							                	 gbc.gridx=3; c.add(erasedB);
							                	 
							                	 unclearB.addActionListener(new ActionListener(){
							                			@Override public void actionPerformed(ActionEvent e){
							                				wordField.setText(wordField.getText()+"\u25a7");
							                			}
							                		});
							                	 erasedB.addActionListener(new ActionListener(){
							                			@Override public void actionPerformed(ActionEvent e){
							                				wordField.setText(wordField.getText()+"\u2026");
							                			}
							                		});
							                		
							                	 c.add(insertA,gbc);
							                	 gbc.insets.left=0;gbc.gridx=1;
							                	 c.add(cancel,gbc);
							                		
							                		cancel.addActionListener(new ActionListener(){
							                			@Override public void actionPerformed(ActionEvent e){
							                				insertFrame.doDefaultCloseAction();
							                			}
							                		});
							                		
							                		wordField.getDocument().addDocumentListener(new DocumentListener() {
						                				  public void changedUpdate(DocumentEvent e) {
						                				    changed();
						                				  }
						                				  public void removeUpdate(DocumentEvent e) {
						                				    changed();
						                				  }
						                				  public void insertUpdate(DocumentEvent e) {
						                				    changed();
						                				  }

						                				  public void changed() {
							                				     if (!wordField.getText().equals("")){
							                				    	 String aux=wordField.getText().replaceAll("\\p{InEthiopic}", "");
							                				    	 if((aux.isEmpty())){
							                				    		 
							                				             insertA.setEnabled(true);
							                				    	 }
							                				    	 else if((aux.replaceAll("\\s", "").isEmpty()) && (StringUtils.countMatches(aux, " ")==(aux.length()-1))) insertA.setEnabled(true);
							                				    	 else if( (aux.indexOf("\u25a7")>0)||(aux.indexOf("\u2026")>0)) insertA.setEnabled(true);
							                				    	 
							                				     }
							                				   

							                				  }
						                				});
							                		
							                		insertA.addActionListener(new ActionListener(){
							                			@Override public void actionPerformed(ActionEvent e){
							                				 wordsBackup.clear();tokensBackup.clear();
							                				for(int i=0;i<words.size();i++){
							   			            	    	wordsBackup.add(words.get(i).copyWortNode());
							   			            	    }
							   			            	  for(int i=0;i<tokens.size();i++){
							   			            		  //  System.out.println("Token "+tokens.get(i).getLabel());
							   			            	    	tokensBackup.add(tokens.get(i).copyToken());
							   			            	    }
							   			            	  undoMenu.setEnabled(true);
							                				String text=wordField.getText().replaceAll("\n","");
							                				StringTokenizer st;
							                				if(typScript==0)
							                				 st=new StringTokenizer(text," ");
							                				else 
							                					st=new StringTokenizer(text,"\uD802\uDE7D");
							                				int nrtok=0;
							                				while(st.hasMoreTokens()){
							                				
							                				    WortNode w=createStructureWord(st.nextToken(),"W"+UUID.randomUUID());
							                				    for(int x=0;x<divw.size();x++)
							                				    	w.getStrukturIds().add(divw.get(x));
							                				    Token to =new Token ("T0>"+w.getId(),false,w.getLetters());
							                				    int posintrod=0;
							                				  
							                				  //  words.add(indexes[indexes.length-1]+1+nrtok,w);
							                				    words.add(indexIdWord.get(wselect.getId()).intValue()+1+nrtok,w);
							                				  modelOrig.addElement(w,indexes[indexes.length-1]+1+nrtok);
							                				  modelTranslit.addElement(w,indexes[indexes.length-1]+1+nrtok);
							                				 
							                		
							                				  //  System.out.println("Inserted "+w.getFidalLabel());
							                				    tokens.add(indexIdToken.get(wselect.getTokenIds().get(wselect.getTokenIds().size()-1)).intValue()+1+nrtok,to);
							                				   String keyinsert=w.getFidalInternLabel(typScript,typDoc)+"*"+w.getTranslitLabel(typScript);
							                				    if (index.containsKey(keyinsert))
							                				    	index.get(keyinsert).add(w.getId());
							                				    else{ 
							                				    	ArrayList<String> tempw=new ArrayList<String>();
							                				    	tempw.add(w.getId());
							                				    	index.put(keyinsert,tempw);
							                				    }
							                				    if (indexT.containsKey(to.getLabel()))
							                				    	indexT.get(to.getLabel()).add(to.getId());
							                				    else{ 
							                				    	ArrayList<String> tempw=new ArrayList<String>();
							                				    	tempw.add(to.getId());
							                				    	indexT.put(to.getLabel(),tempw);
							                				    }
							                				    createIndexWords();createIndexTokens();nrtok=nrtok+1;
							                				    //fidalWordList.revalidate();
							                				    //translitWordList.revalidate();
							                				    }
							                				fidalWordList.revalidate();fidalWordList.repaint();
							                				translitWordList.revalidate();translitWordList.repaint();
							                				insertFrame.doDefaultCloseAction();
							                			}
							                		});
							                		insertFrame.pack();
							                		addChild(insertFrame,30,30,200,300);
							                		insertFrame.pack();
					        			               insertFrame.setVisible(true);
					        			               insertFrame.moveToFront();
				                              // insert.setEnabled(true);
							                	}
							                	else {
							                		//System.out.println(okins+"");
							                	}
							                	}
							                }
							                		);
							                
					//
							                jminsertB.addActionListener(new ActionListener(){
							                	@Override public void actionPerformed(ActionEvent ae){
							                		final int indexF = translitWordList.getMinSelectionIndex();
							                		ArrayList<String> divw=words.get(indexF).getStrukturIds();
							                		boolean okins=true;
							                		int k=0;
							                		boolean foundw=false;
							                		while((k<divw.size())&&(!foundw)) {
							                			if(divisions.get(indexIdDivision.get(divw.get(k)).intValue()).getWbegin().equals(words.get(indexF).getId()))
							                				
							                			{foundw=true;}
							                			else k=k+1;
							                		}
							                		//okins=!foundw;
							                		if(foundw) {
							                			
							                			int confirm= JOptionPane.showOptionDialog(desk,
								             	                "You are going to insert before a division margin . Are you sure? ",
								             	                "Insert Confirmation", JOptionPane.YES_NO_OPTION,
								             	                JOptionPane.QUESTION_MESSAGE, null, null, null);
								             			if (confirm == JOptionPane.YES_OPTION) okins=true;
								             			else okins=false;
							                		}
							                		else okins=true;
							                		if(okins)	{
							                		final ChildFrame insertFrame=new ChildFrame("Insert Graphic Unit  Before",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
							                		//insertFrame.setSize(200, 100);
							                		Container c=insertFrame.getContentPane();
							                		final JTextArea wordField=new JTextArea(10,25);
							                		wordField.setFont(typFont1);
							                		//wordField.setFont(typFont);
							                		wordField.addFocusListener(new FocusListener() {

							                            @Override
							                            public void focusGained(FocusEvent e) {
							                              //  positionCursor=wordField;
							                               //GeezFrame.setInputField(wordField);
							                                //System.out.println("Focus gt roots");
							                            }

							                            @Override
							                            public void focusLost(FocusEvent e) {
							                                //positionCursor=nothingSelected;
							                          	  //System.out.println("Focus lost");
							                            }
							                        });
							                		JLabel eticheta= new JLabel("Please insert text in FIDEL");
							                		eticheta.setForeground(Color.RED);
							                		//wordField.setEditable(true);
							                		wordField.setEnabled(true);
							                		//wordField.setLineWrap(true);
							                		GridBagConstraints gbc=new GridBagConstraints();
							                		gbc.gridx=0;
							                		gbc.gridy=0;
							                		gbc.weightx=100;
							                		gbc.weighty=100;
							                		gbc.insets.left=2;
							                		gbc.insets.right=2;
							                		gbc.insets.top=2;
							                		gbc.gridwidth=4;
							                		gbc.anchor=GridBagConstraints.NORTHWEST;
							                		gbc.insets.bottom=2;
							                	c.setLayout(new GridBagLayout());
							                	//	pp.setLayout(new GridBagLayout());
							                	//	String wordInsert="";
							                		JScrollPane scrp =new JScrollPane(wordField);
							                		
							                		
							                		
							                		c.add(eticheta,gbc);
							                		gbc.gridy=1;gbc.insets.top=0;
							                		gbc.fill=GridBagConstraints.HORIZONTAL;
							                		c.add(scrp,gbc);
							                		gbc.fill=GridBagConstraints.NONE;
							                		gbc.gridy=2;
                                                    gbc.gridwidth=1;
                                                    final	JButton unclearB=new JButton("\u25a7");
                                                    final	JButton erasedB=new JButton("\u2026");
							                	final	JButton insertB=new JButton("Insert");
							                		JButton cancel=new JButton("Cancel");
							                		insertB.setEnabled(false);
							                		insertB.setFocusable(false);
							                		insertB.setEnabled(false);
							                		
							                	 c.add(insertB,gbc);
							                	 gbc.insets.left=0;gbc.gridx=1;
							                	 c.add(cancel,gbc);gbc.gridy=2;
							                	 gbc.gridx=2; c.add(unclearB);
							                	 gbc.gridx=3; c.add(erasedB);
							                	 
							                	 unclearB.addActionListener(new ActionListener(){
							                			@Override public void actionPerformed(ActionEvent e){
							                				wordField.setText(wordField.getText()+"\u25a7");
							                			}
							                		});
							                	 erasedB.addActionListener(new ActionListener(){
							                			@Override public void actionPerformed(ActionEvent e){
							                				wordField.setText(wordField.getText()+"\u2026");
							                			}
							                		});
							                		
							                		cancel.addActionListener(new ActionListener(){
							                			@Override public void actionPerformed(ActionEvent e){
							                				insertFrame.doDefaultCloseAction();
							                			}
							                		});
							                		
							                		wordField.getDocument().addDocumentListener(new DocumentListener() {
						                				  public void changedUpdate(DocumentEvent e) {
						                				    changed();
						                				  }
						                				  public void removeUpdate(DocumentEvent e) {
						                				    changed();
						                				  }
						                				  public void insertUpdate(DocumentEvent e) {
						                				    changed();
						                				  }

						                				  public void changed() {
						                				     if (!wordField.getText().equals("")){
						                				    	 String aux=wordField.getText().replaceAll("\\p{InEthiopic}", "");
						                				    	 if((aux.isEmpty())){
						                				    		 
						                				             insertB.setEnabled(true);
						                				    	 }
						                				    	 else if((aux.replaceAll("\\s", "").isEmpty()) && (StringUtils.countMatches(aux, " ")==(aux.length()-1))) insertB.setEnabled(true);
						                				    	 else if( (aux.indexOf("\u25a7")>0)||(aux.indexOf("\u2026")>0)) insertB.setEnabled(true);
						                				    	 
						                				     }
						                				   

						                				  }
						                				});
							                		
							                		insertB.addActionListener(new ActionListener(){
							                			@Override public void actionPerformed(ActionEvent e){
							                				 wordsBackup.clear();tokensBackup.clear();
							                				for(int i=0;i<words.size();i++){
							   			            	    	wordsBackup.add(words.get(i).copyWortNode());
							   			            	    }
							   			            	  for(int i=0;i<tokens.size();i++){
							   			            		   // System.out.println("Token "+tokens.get(i).getLabel());
							   			            	    	tokensBackup.add(tokens.get(i).copyToken());
							   			            	    }
							   			            	  undoMenu.setEnabled(true);
							                				String text=wordField.getText().replaceAll("\n","");
							                				StringTokenizer st=new StringTokenizer(text," ");
							                				int nrtok=0;
							                				int posintrod=indexIdWord.get(wselect.getId()).intValue();
							                				int posintrod1=indexIdToken.get(wselect.getTokenIds().get(wselect.getTokenIds().size()-1)).intValue();
							                				while(st.hasMoreTokens()){
							                				
							                				    WortNode w=createStructureWord(st.nextToken(),"W"+UUID.randomUUID());
							                				    for(int x=0;x<divw.size();x++) {
							                				    	w.getStrukturIds().add(divw.get(x));
							                				    }
							                				    Token to =new Token ("T0>"+w.getId(),false,w.getLetters());
							                				    
							                				  
							                				   /* words.add(indexes[indexes.length-1],w);
							                				    tokens.add(indexIdToken.get(wselect.getTokenIds().get(0)).intValue(),to);
							                				    createIndexWords();createIndexTokens();
							                				  modelOrig.addElement(w,indexes[indexes.length-1]);
							                				    modelTranslit.addElement(w,indexes[indexes.length-1]);*/
							                				
							                				   /* if(indexIdWord.get(wselect.getId()).intValue()>0){
							                				    words.add(indexIdWord.get(wselect.getId()).intValue()-1,w);
							                				    tokens.add(indexIdToken.get(wselect.getTokenIds().get(wselect.getTokenIds().size()-1)).intValue()-1,to);
							                				    createIndexWords();createIndexTokens();
								                				  modelOrig.addElement(w,indexes[indexes.length-1]-1;
								                				  modelTranslit.addElement(w,indexes[indexes.length-1]-1);
							                				    }*/
							                				   // else{
							                				    	 words.add(posintrod,w);
									                				    tokens.add(posintrod1,to);
									                				    createIndexWords();createIndexTokens();
										                				  modelOrig.addElement(w,posintrod);
										                				  modelTranslit.addElement(w,posintrod); posintrod=posintrod+1;posintrod1=posintrod1+1;
							                				    //}
								                		
								                				  //  System.out.println("Inserted "+w.getFidalLabel());
								                				    
							                				    
							                				 //   System.out.println("Inserted "+w.getFidalLabel());
							                				    String keyinsert=w.getFidalInternLabel(typScript,typDoc)+"*"+w.getTranslitLabel(typScript);
							                				    if (index.containsKey(keyinsert))
							                				    	index.get(keyinsert).add(w.getId());
							                				    else{ 
							                				    	ArrayList<String> tempw=new ArrayList<String>();
							                				    	tempw.add(w.getId());
							                				    	index.put(keyinsert,tempw);
							                				    }
							                				    if (indexT.containsKey(to.getLabel()))
							                				    	indexT.get(to.getLabel()).add(to.getId());
							                				    else{ 
							                				    	ArrayList<String> tempw=new ArrayList<String>();
							                				    	tempw.add(to.getId());
							                				    	indexT.put(to.getLabel(),tempw);
							                				    }
							                				    nrtok=nrtok+1;
							                				    }
							                				fidalWordList.revalidate();fidalWordList.repaint();
							                				translitWordList.revalidate();translitWordList.repaint();
							                				insertFrame.doDefaultCloseAction();
							                			}
							                		});
							                		insertFrame.pack();
							                		addChild(insertFrame,30,30,200,300);
							                		insertFrame.pack();
					        			               insertFrame.setVisible(true);
					        			               insertFrame.moveToFront();
				                              // insert.setEnabled(true);
							                	}	
							                	}
							                }
							                		);
							                
							                
							                
							                
							                
							                
							                //linguistic Annotation
							                for(int i=0;i<menuTokensI.size();i++){
							                	final int pos=i;
							                	menuTokensD.get(i).addActionListener(new ActionListener(){
								                	public void actionPerformed(ActionEvent e){
								                		final Token t= tokens.get(indexIdToken.get(wselect.getTokenIds().get(pos)).intValue());
								                		final ChildFrame deleteAnnotFrame=new ChildFrame("Delete Annotation",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
									                	
										                final Container c =deleteAnnotFrame.getContentPane();
										                final JButton delT= new JButton("Delete Annotation");
										                final JButton cancel= new JButton("Cancel");
										                final JCheckBox global = new JCheckBox("Global");
										                String s="";
										                ArrayList<Attribut> attr=t.getMorphoAnnotation().getListTag().get(0).getAttrList();
										                if(attr!=null){
										                for (int i=0; i<attr.size();i++){
										                	s=s+attr.get(i).getValue()+" ";
										                }
										                }
										                s=t.getMorphoAnnotation().getListTag().get(0).getName()+ "<br>"+s;
										                JLabel wordLabel=new JLabel("<html><p><fonr size=+1 colour=red><b>You are going to delete  morphological annotation</b></font></p></html>");
										                JLabel annotLabel=new JLabel("<html><p><fonr size=+1 colour=red><b></b>"+s+"</font></p></html>");
										                wordLabel.setForeground(Color.RED);
										                final JPanel c1=new JPanel(new GridBagLayout());
										                GridBagConstraints gbc1=new GridBagConstraints();
										                gbc1.gridx=0;
								                		gbc1.weightx=100;
								                		gbc1.weighty=100;
								                		gbc1.gridy=0;
								                		gbc1.insets.left=2;
								                		gbc1.insets.right=2;
								                		gbc1.anchor=GridBagConstraints.CENTER;
								                 		gbc1.insets.top=2;
								                		gbc1.insets.bottom=2;
								                		gbc1.insets.right=0;
								                		gbc1.fill=GridBagConstraints.NONE;
								                		gbc1.gridwidth=2;
								                		c1.add(wordLabel);
								                		gbc1.gridy=1;
								                		gbc1.gridwidth=2;
								             
								                		gbc1.insets.right=2;
								                		c1.add(annotLabel,gbc1);
								                		gbc1.anchor=GridBagConstraints.NORTHWEST;
								                		gbc1.gridy=2;
								                		c1.add(global,gbc1);
								                		gbc1.gridwidth=1;
								                		gbc1.gridy=3;
								                		gbc1.gridx=0;
								                		gbc1.insets.left=2;
								                		c1.add(delT,gbc1);
								                		gbc1.insets.left=0;
								                		gbc1.gridx=1;
								                		c1.add(cancel,gbc1);
								                		c.add(c1);
								                		deleteAnnotFrame.pack();
								                		addChild(deleteAnnotFrame,100,100,550,200);
								                		deleteAnnotFrame.setResizable(false);
								                		deleteAnnotFrame.pack();
							   			               deleteAnnotFrame.setVisible(true);
							   			              deleteAnnotFrame.moveToFront();
	                                                  
							   			           cancel.addActionListener(new ActionListener(){
							   			            	  public void actionPerformed(ActionEvent e1){
							   			            		  deleteAnnotFrame.doDefaultCloseAction();
							   			            	  }
							   			           });
							   			        delT.addActionListener(new ActionListener(){
						   			            	  public void actionPerformed(ActionEvent e1){
						   			            		 wordsBackup.clear();tokensBackup.clear();
						   			            		for(int i=0;i<words.size();i++){
						   			            	    	wordsBackup.add(words.get(i).copyWortNode());
						   			            	    }
						   			            	  for(int i=0;i<tokens.size();i++){
						   			            		 //   System.out.println("Token "+tokens.get(i).getLabel());
						   			            	    	tokensBackup.add(tokens.get(i).copyToken());
						   			            	    }
						   			            	  undoMenu.setEnabled(true);
								                	     t.setMorphoAnnotation(null);
								                	     if(global.isSelected()){
								                	     ArrayList<String> restId=indexT.get(t.getLabel());
									                		for(int k=0;k<restId.size();k++){
									                			if (!tokens.get((indexIdToken.get(restId.get(k)).intValue())).getId().equals(t.getId()))
									                			 tokens.get((indexIdToken.get(restId.get(k)).intValue())).setMorphoAnnotation(null);
									                		}
						   			            	  }
									                		translitWordList.revalidate();
										                	translitWordList.repaint();
										                	deleteAnnotFrame.doDefaultCloseAction();
								                	}
								                	});
								                	}
								                	
								                	});
							                	
							                   menuTokensI.get(i).addActionListener(new ActionListener(){
							                	public void actionPerformed(ActionEvent e){
							                		// i wivielte Token im Wort wselect
							                		
							                		final Token t= tokens.get(indexIdToken.get(wselect.getTokenIds().get(pos)).intValue());
							                		if (inLingannot.contains(t.getId())){
							                			JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" >You already opended a window for this token</font></p></html>", "Dialog",
						   			  		       		        JOptionPane.ERROR_MESSAGE);
							                		}
							                		else{
							                			inLingannot.add(t.getId());
							                		final Annotation ta= t.getMorphoAnnotation();
							                		JLabel posLabel=new JLabel("PoS");
							                        final JTextField posPart=new JTextField(20);
							                        posPart.setEditable(false);
							                        JLabel labelLemma=new JLabel("Lemma Id");
							                        final JTextField lemmaId=new JTextField(20);
							                        lemmaId.setFont(typFont);
							                        lemmaId.setEditable(false);
							                        JLabel deepLabel=new JLabel("Deep Annotation");
							                        final JEditorPane deepArea=new JEditorPane("text/html","");
							                        String bodyRule = "body { font-family: " + typFont.getFamily() + "; " +
							       		                 "font-size: " + typFont.getSize() + "pt; }";
							       		         ((HTMLDocument)deepArea.getDocument()).getStyleSheet().addRule(bodyRule);
							                      deepArea.setPreferredSize(new Dimension(100,80));
							                     //   deepArea.setSize(new Dimension(100,80));
							                       deepArea.setEditable(false);
							                        deepArea.setFont(etiopicText1);
							                        
							                       //deepArea.setLineWrap(true);
							                        //deepArea.setWrapStyleWord(true);
							                      
							                        final JScrollPane deepPanel=new JScrollPane(deepArea);
							                       deepPanel.setPreferredSize(new Dimension(100,80));
							                        final JButton selectPos=new JButton("Select PoS");
							                        final JButton selectLemma =new JButton("Select Lemma");
							                        final JButton selectLemmaBM =new JButton("Insert Lemma BM");
							                        final JButton selectDeep=new JButton("Deep Annotation");
							                        final JRadioButton global=new JRadioButton("Global");
							                        final JRadioButton globalC=new JRadioButton("Global-Complete");
							                        final JRadioButton local=new JRadioButton("Local");
							                        local.setSelected(true);
							                        ButtonGroup globalGrp=new ButtonGroup();
							                        globalGrp.add(local);globalGrp.add(global); globalGrp.add(globalC);
							                        final JCheckBox complete=new JCheckBox("Complete");
							                        final JButton annot=new JButton("Annotate");
							                        final JButton clear=new JButton("Clear");
							                        final JButton cancel=new JButton("Cancel");
							                        final JCheckBox partNE=new JCheckBox("is NE part?");
							                        partNE.setSelected(false);
							                      //  partNE.setEnabled(false);
							                        final JInternalFrame lingAnnotFrame=new JInternalFrame();
							                    //  lingAnnotFrame.setFont(ttfReal);
							                      //lingAnnotFrame.setDefaultLocale(getLocale());
							                      lingAnnotFrame.getComponent(1).setFont(typFont);
							                        lingAnnotFrame.setTitle("Linguistic Annotation "+ t.getLabel());
							                       
							                        lingAnnotFrame.setIconifiable(true);
							                        lingAnnotFrame.setMaximizable(true);
							                        lingAnnotFrame.setResizable(true);
							                        lingAnnotFrame.setClosable(true);
							                        lingAnnotFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
							            			setBackground(mainFrameColor);
							                        final Container c =lingAnnotFrame.getContentPane();
							                        final JPanel c1=new JPanel(new GridBagLayout());
							                      
							                        lingAnnotFrame.addInternalFrameListener(new InternalFrameListener() {
							                          
							                            	public void internalFrameActivated(InternalFrameEvent event) {
							                            		
							                            		    
							                            		
							                            		}
							                            	
							                            		public void internalFrameClosed(InternalFrameEvent event) {
							                            		
							                            			inLingannot.remove(t.getId());
							                            		
							                            		}
							                            		public void internalFrameOpened(InternalFrameEvent event) {
								                            			 
								                            		
							                            		}
							                            	
							                            		public void internalFrameClosing(InternalFrameEvent event) {
							                            		
							                            		}
							                            		
                                                                public void internalFrameDeiconified(InternalFrameEvent event) {
								                         
								                            		
							                            		}
                                                                public void internalFrameDeactivated(InternalFrameEvent event) {
								                         
								                            		
							                            		}
							                            		public void internalFrameIconified(InternalFrameEvent event) {
								                         
								                            		
							                            		}

							                            
							                        });

							                        GridBagConstraints gbc1=new GridBagConstraints();
									                gbc1.gridx=0;
							                		gbc1.weightx=100;
							                		gbc1.weighty=100;
							                		gbc1.gridy=0;
							                		gbc1.insets.left=2;
							                		gbc1.insets.right=2;
							                		gbc1.anchor=GridBagConstraints.NORTHWEST;
							                 		gbc1.insets.top=2;
							                		gbc1.insets.bottom=2;
							                		gbc1.insets.right=0;
							                		gbc1.fill=GridBagConstraints.HORIZONTAL;
							                		c1.add(posLabel,gbc1);
							                		gbc1.gridx=1;
							                		gbc1.insets.left=0;
							                		c1.add(posPart,gbc1);
							                		gbc1.gridx=2;
							                		c1.add(selectPos,gbc1);
							                		gbc1.gridx=3;
							                		c1.add(partNE,gbc1);
							                		gbc1.gridx=0;gbc1.gridy=1;
							                		
							                		gbc1.insets.left=2;
							                		c1.add(labelLemma,gbc1);
							                		gbc1.gridx=1;
							                		gbc1.insets.left=0;
							                		c1.add(lemmaId,gbc1);
							                		gbc1.gridx=2;
							                		c1.add(selectLemma,gbc1);
							                		gbc1.gridy=2;
							                		c1.add(selectLemmaBM,gbc1);
							                		gbc1.gridx=0;gbc1.gridy=3;
							                		gbc1.insets.left=2;
							                		c1.add(deepLabel,gbc1);
							                		gbc1.gridx=1;
							                		gbc1.insets.left=0;
							                		gbc1.fill=GridBagConstraints.BOTH;
							                		gbc1.gridheight=3;
							                		c1.add(deepPanel,gbc1);
							                		gbc1.fill=GridBagConstraints.HORIZONTAL;
							                		gbc1.gridheight=1;
							                		gbc1.gridx=2;
							                		c1.add(selectDeep,gbc1);
							                		gbc1.gridy=4;
							                		c1.add(partNE,gbc1);
							               
							                		 final JPanel c2=new JPanel(new GridBagLayout());
										                GridBagConstraints gbc2=new GridBagConstraints();
										                gbc2.gridx=0;
								                		gbc2.weightx=100;
								                		gbc2.weighty=100;
								                		gbc2.gridy=0;
								                		gbc2.insets.left=2;
								                		gbc2.insets.right=2;
								                		gbc2.anchor=GridBagConstraints.NORTHWEST;
								                 		gbc2.insets.top=2;
								                		gbc2.insets.bottom=2;
								                		gbc2.insets.right=0;
								                		gbc2.fill=GridBagConstraints.BOTH;
							                		   c2.add(complete,gbc2);
							                		   gbc2.insets.left=0;gbc2.gridx=1;
							                		   c2.add(local,gbc2);
							                		   gbc2.gridx=2; c2.add(global,gbc2);
							                		   gbc2.gridx=3; c2.add(globalC,gbc2);
							                		   final JPanel c3=new JPanel(new GridBagLayout());
										                GridBagConstraints gbc3=new GridBagConstraints();
										                gbc3.gridx=0;
								                		gbc3.weightx=100;
								                		gbc3.weighty=100;
								                		gbc3.gridy=0;
								                		gbc3.insets.left=2;
								                		gbc3.insets.right=2;
								                		gbc3.anchor=GridBagConstraints.NORTHWEST;
								                 		gbc3.insets.top=2;
								                		gbc3.insets.bottom=2;
								                		gbc3.insets.right=0;
								                		gbc3.fill=GridBagConstraints.BOTH;
							                		   c3.add(annot,gbc3);
							                		   gbc3.insets.left=2;gbc3.gridx=1;
							                		   c3.add(clear,gbc3);
							                		   gbc3.gridx=2;
							                		   c3.add(cancel,gbc3);
							                		   c.setLayout(new GridBagLayout());
										                GridBagConstraints gbc=new GridBagConstraints();
										                gbc.gridx=0;
								                		gbc.weightx=100;
								                		gbc.weighty=100;
								                		gbc.gridy=0;
								                		
								                		gbc.anchor=GridBagConstraints.NORTHWEST;
								                 		
								                		gbc.fill=GridBagConstraints.BOTH;
								                		c.add(c1,gbc);
								                		gbc.gridy=1;
								                		c.add(c2,gbc);
								                		gbc.gridy=2;
								                		c.add(c3,gbc);
								                		pressDeep=0;pressPoS=0;
								                		if (ta!=null){
								                			
								                			lemmaId.setText(ta.getMorphoValue("lex"));
								                			LexIDSel=ta.getMorphoValue("lex");
								                		}
								                		if (ta!=null){
								                			if (ta.isAutom()) global.setSelected(true); complete.setEnabled(false);
								                		}
								                		if (ta!=null){
								                			if (ta.isComplete()) complete.setSelected(true);
								                		}
								                		if (ta!=null){
								                			if (ta.isNEPart()) partNE.setSelected(true);
								                		}
								                		if (ta!=null){
								                			posPart.setText(ta.getListTag().get(0).getName());
								                		}
								                		if (ta!=null){
								                			deepArea.setText(ta.getEntireDeepValues());
								                		}
								                		lingAnnotFrame.pack();
								                		addChild(lingAnnotFrame,100,100,550,550);
								                		//lingAnnotFrame.setResizable(false);
								                		lingAnnotFrame.pack();
								                		lingAnnotFrame.setVisible(true);
								                		lingAnnotFrame.moveToFront();
							                		
							                		globalC.addItemListener(new ItemListener(){
							                			public void itemStateChanged(ItemEvent e){
							                				if (globalC.isSelected())
							                				complete.setSelected(true);
							                			}
							                		});
							                		local.addItemListener(new ItemListener(){
							                			public void itemStateChanged(ItemEvent e){
							                				if (local.isSelected())
							                				complete.setEnabled(true);
							                			}
							                		});
							                		global.addItemListener(new ItemListener(){
							                			public void itemStateChanged(ItemEvent e){
							                				if (global.isSelected())
							                				complete.setEnabled(false);
							                			}
							                		});
							                		clear.addActionListener(new ActionListener(){
									                	public void actionPerformed(ActionEvent e){
									                		posPart.setText("");
									                		deepArea.setText("");
									                		attr.clear();
									                		LexIDSel="";
									                		lemmaId.setText("");
									                		local.setSelected(true);
									                		complete.setSelected(false);
									                	}
							                		});
							                		cancel.addActionListener(new ActionListener(){
									                	public void actionPerformed(ActionEvent e){
									                		posPart.setText("");
									                		deepArea.setText("");
									                		attr.clear();
									                		LexIDSel="";
									                		lemmaId.setText("");
									                		global.setSelected(false);
									                		complete.setSelected(false);
									                		lingAnnotFrame.doDefaultCloseAction();
									                	}
							                		});
							                		annot.addActionListener(new ActionListener(){
									                	public void actionPerformed(ActionEvent e){
									                	if(posPart.getText().isEmpty())	
									                		 JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" >Please select at least a PoS</font></p></html>", "Dialog",
								   			  		       		        JOptionPane.ERROR_MESSAGE);
									                	else{
									                		 wordsBackup.clear();tokensBackup.clear();
									                		for(int i=0;i<words.size();i++){
							   			            	    	wordsBackup.add(words.get(i).copyWortNode());
							   			            	    }
							   			            	  for(int i=0;i<tokens.size();i++){
							   			            		 //   System.out.println("Token "+tokens.get(i).getLabel());
							   			            	    	tokensBackup.add(tokens.get(i).copyToken());
							   			            	    }
							   			            	  undoMenu.setEnabled(true);
									                		if (!lemmaId.getText().isEmpty()){
						
									                		
									                		  Attribut a=new Attribut("lex",lemmaId.getText()); 
									                		  attr.add(a);
									                		}
									                		
									                if((ta!=null)&(pressDeep==0)&&(pressPoS==0)){
									                	if (ta.getListTag().get(0).getAttrList()!=null)
									                	      for (int i=0;i<ta.getListTag().get(0).getAttrList().size();i++)
									                	          if(!ta.getListTag().get(0).getAttrList().get(i).getName().equalsIgnoreCase("lex"))
									                	        	  attr.add(new Attribut(ta.getListTag().get(0).getAttrList().get(i).getName(),ta.getListTag().get(0).getAttrList().get(i).getValue()));
									                }
									                		Tag tag;
									                		
									                	if(attr.size()==0) {
									                		tag=new Tag(posPart.getText());
									                	}
									                	else {
									                		//System.out.println("Deep Area! "+deepArea.getText());
									                	
									                		 tag=new Tag(posPart.getText(),attr);
									                	}
									                	ArrayList<Tag> an=new ArrayList<Tag>();
									                	an.add(tag);
									                	Annotation ann;
									                	if(complete.isSelected()){
									                	  ann=new Annotation(false,false,true,partNE.isSelected(),an);
									                	}
									                	else{
									                		 ann=new Annotation(false,false,false,partNE.isSelected(),an);
									                	}
									                	t.setMorphoAnnotation(ann);
									                	t.setAutom(false);
									                	tokens.set(indexIdToken.get(wselect.getTokenIds().get(pos)).intValue(),t);
									                	
									                	if(global.isSelected()||globalC.isSelected()){
									                		Annotation ann1;
									                		if(global.isSelected()){
									                		    ann1=new Annotation(true,true,false,partNE.isSelected(),an);
									                		}
									                		else if(globalC.isSelected()){
									                			 ann1=new Annotation(false,false,complete.isSelected(),partNE.isSelected(),an);
									                		}
									                		else{
									                			 ann1=new Annotation(true,false,complete.isSelected(),partNE.isSelected(),an);
									                		}
									                		ArrayList<String> restId=indexT.get(t.getLabel());
									                	//	System.out.println("Do for "+t.getLabel()+""+ indexT.get(t.getLabel()));
									                		for(int k=0;k<restId.size();k++){
									                		//	System.out.println("Token "+restId.get(k));
									                			//System.out.println("Position token "+indexIdToken.get(restId.get(k)));
									                			
									                			if (!tokens.get((indexIdToken.get(restId.get(k)).intValue())).getId().equals(t.getId()))
									                				if(tokens.get((indexIdToken.get(restId.get(k)).intValue())).getMorphoAnnotation()!=null)
									                					if(!tokens.get((indexIdToken.get(restId.get(k)).intValue())).getMorphoAnnotation().isComplete())
									                			                  tokens.get((indexIdToken.get(restId.get(k)).intValue())).setMorphoAnnotation(ann1);
									                					else{;}
									                				else tokens.get((indexIdToken.get(restId.get(k)).intValue())).setMorphoAnnotation(ann1);
									                		}
									                	}
									                	//System.out.println(t.getMorphoAnnotation().getListTag().get(0).getName());
									                	translitWordList.revalidate();
									                	translitWordList.repaint();
									                	inLingannot.remove(t.getId());
									                	lingAnnotFrame.doDefaultCloseAction();
									                	LexIDSel="";attr.clear();
									                	menuTokensD.get(pos).setEnabled(true);
									                	}
									                	} 
							                		});	
							                		
							                		selectDeep.addActionListener(new ActionListener(){
									                	public void actionPerformed(ActionEvent e){
									                		String s=posPart.getText();
									                		assignedFeatures=false;
									                		pressDeep=1;
									                		final ChildFrame deepFrame=new ChildFrame("Deep Annotation ",mainFrameColor,WindowConstants.DISPOSE_ON_CLOSE);
									                		Container c=deepFrame.getContentPane();
									                		c.setLayout(new GridBagLayout());
									                		GridBagConstraints gbc=new GridBagConstraints();
											                gbc.gridx=0;
									                		gbc.weightx=100;
									                		gbc.weighty=100;
									                		gbc.gridy=0;
									                		gbc.insets.left=2;
									                		gbc.insets.right=2;
									                		gbc.anchor=GridBagConstraints.NORTHWEST;
									                 		gbc.insets.top=2;
									                		gbc.insets.bottom=2;
									                		gbc.insets.right=0;
									                		gbc.fill=GridBagConstraints.NONE;
									                		selectDeep.setEnabled(false);
									                		if(s.equals("Common Noun")) {
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final GenderPanel gp;
									                			
									                			if (ta!=null){
									                				
									                			 gp=new GenderPanel(ta.getMorphoValue("gender"));
									                			c.add(gp.getGenderPanel(),gbc);
									                			}
									                			else
									                				{ gp=new GenderPanel("");
									                				c.add(gp.getGenderPanel(),gbc);}
									                			
									                			gbc.insets.left=0;
									                			gbc.gridx=1;
									                			
									                			final NumberNounPanel np;
									                			if (ta!=null){
									                				
									                			np=new NumberNounPanel(ta.getMorphoValue("number"));
									                			c.add(np.getNumberNounPanel(),gbc);
									                			}
									                			else{
									                				np=new NumberNounPanel("");
										                			c.add(np.getNumberNounPanel(),gbc);
									                			}
									                			final Case0AccPanel cp;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=1;
									                			if (ta!=null){
									                			   cp=new Case0AccPanel(ta.getMorphoValue("case"));
									                			
									                			    c.add(cp.getCasePanel(),gbc);
									                			}
									                			else{
									                				cp=new Case0AccPanel("");
										                			
									                			    c.add(cp.getCasePanel(),gbc);
									                			}
									                			gbc.insets.left=0;
									                			gbc.gridx=1;
									                			final Status0ConstructPanel sp;
									                			if (ta!=null){
									                			sp=new Status0ConstructPanel(ta.getMorphoValue("state"));
									                			
									                			c.add(sp.getStatusPanel(),gbc);
									                			}
									                			else{
									                				sp=new Status0ConstructPanel("");
										                			
										                			c.add(sp.getStatusPanel(),gbc);
									                			}
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=2;gbc.gridwidth=2;
									                			
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			//final JTextArea commCN=new JTextArea(10,10);
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                			
									                			commCN.setFont(typFont);
									                		//.setPreferredSize(new Dimension(70,100));
									                			//commCN.setAutoscrolls(true);
									                			if (ta!=null){
									                				String co=ta.getMorphoValue("comment");
									                				//System.out.println(co);
									                				commCN.setText("<div style=\'font-family:"+typFontString+"\'>"+Jsoup.parse(co)+"</div>");
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:"+typFontString+"\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                			scommCN.setPreferredSize(new Dimension(100,200));
									                			gbc.gridy=3;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=4;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					Attribut a;String s="";
									                					 attr=new ArrayList<Attribut>();
									                					 assignedFeatures=true;
									                					 a=new Attribut("gender",gp.getGenderValue());
									                					 attr.add(a);s=s+gp.getGenderValue();
									                					 a=new Attribut("number",np.getNumberValue());
									                					 attr.add(a);s=s+"\n"+np.getNumberValue();
									                					 a=new Attribut("case",cp.getCaseValue());
									                					 attr.add(a);s=s+"\n"+cp.getCaseValue();
									                					 a=new Attribut("state",sp.getStatusValue());
									                					 attr.add(a);s=s+"\n"+sp.getStatusValue();
									                					 if (!Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                			});
									                		}
									                		else if(s.equals("Proper Name")) {
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final GenderPanel gp;
									                			
									                			if (ta!=null){
									                				
									                			 gp=new GenderPanel(ta.getMorphoValue("gender"));
									                			c.add(gp.getGenderPanel(),gbc);
									                			}
									                			else
									                				{ gp=new GenderPanel("");
									                				c.add(gp.getGenderPanel(),gbc);}
									                			
									                			gbc.insets.left=0;
									                			gbc.gridx=1;
									                			
									                			final NumberNounPanel np;
									                			if (ta!=null){
									                				
									                			np=new NumberNounPanel(ta.getMorphoValue("number"));
									                			c.add(np.getNumberNounPanel(),gbc);
									                			}
									                			else{
									                				np=new NumberNounPanel("");
										                			c.add(np.getNumberNounPanel(),gbc);
									                			}
									                			final Case0AccPanel cp;
									                			//gbc.insets.left=2;
									                			gbc.gridx=2;gbc.gridy=0;
									                			if (ta!=null){
									                			   cp=new Case0AccPanel(ta.getMorphoValue("case"));
									                			
									                			    c.add(cp.getCasePanel(),gbc);
									                			}
									                			else{
									                				cp=new Case0AccPanel("");
										                			
									                			    c.add(cp.getCasePanel(),gbc);
									                			}
									                			
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=1;gbc.gridwidth=3;
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                			//commCN.setPreferredSize(new Dimension(20,40));
									                			commCN.setFont(typFont);
									                			//commCN.setPreferredSize(new Dimension(10,10));
									                			if (ta!=null){
									                				commCN.setText(ta.getMorphoValue("Comment"));
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:Ethiopic Unicode\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                			scommCN.setPreferredSize(new Dimension(100,200));
									                			gbc.gridy=2;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=4;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					Attribut a;String s="";
									                					 attr=new ArrayList<Attribut>();
									                					 assignedFeatures=true;
									                					 a=new Attribut("gender",gp.getGenderValue());
									                					 attr.add(a);s=s+gp.getGenderValue();
									                					 a=new Attribut("number",np.getNumberValue());
									                					 attr.add(a);s=s+"\n"+np.getNumberValue();
									                					 a=new Attribut("case",cp.getCaseValue());
									                					 attr.add(a);s=s+"\n"+cp.getCaseValue();
									    
									                					 if (!Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("Comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                			});
									                		}
									                		else if(s.equals("Independent Personal Pronoun")) {
									                			gbc.fill=GridBagConstraints.BOTH;
                                                                final PersonPanel pp;
									                			
									                			if (ta!=null){
									                				
									                			 pp=new PersonPanel(ta.getMorphoValue("person"));
									                			c.add(pp.getPersonPanel(),gbc);
									                			}
									                			else
									                				{ pp=new PersonPanel(" ");
									                				c.add(pp.getPersonPanel(),gbc);}
									                			final GenderPron gp;
									                			
									                			if (ta!=null){
									                				
									                			 gp=new GenderPron(ta.getMorphoValue("gender"));
									                			c.add(gp.getGenderPanel(),gbc);
									                			}
									                			else
									                				{ gp=new GenderPron(" ");

										                			gbc.insets.left=0;
										                			gbc.gridx=1;
									                				c.add(gp.getGenderPanel(),gbc);}
									                			
									                			gbc.insets.left=2;
									                			gbc.gridx=0;
									                			gbc.gridy=1;
									                			final NumberPron np;
									                			if (ta!=null){
									                				
									                			np=new NumberPron(ta.getMorphoValue("number"));
									                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			else{
									                				np=new NumberPron("");
										                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			final Case0AccPron cp;
									                			gbc.insets.left=0;
									                			gbc.gridx=1;gbc.gridy=1;
									                			if (ta!=null){
									                			   cp=new Case0AccPron(ta.getMorphoValue("case"));
									                			
									                			    c.add(cp.getCasePanel(),gbc);
									                			}
									                			else{
									                				cp=new Case0AccPron("Nominative");
										                			
									                			    c.add(cp.getCasePanel(),gbc);
									                			}
									                			
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=2;gbc.gridwidth=2;
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                			commCN.setFont(typFont);
									                			//commCN.setPreferredSize(new Dimension(20,10));
									                			if (ta!=null){
									                				commCN.setText(ta.getMorphoValue("Comment"));
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:Ethiopic Unicode\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                			
									                			gbc.gridy=3;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=4;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					assignedFeatures=true;
									                					Attribut a;String s="";
									                					 attr=new ArrayList<Attribut>();
									                					 a=new Attribut("person",pp.getPersonValue());
									                					 attr.add(a);s=s+pp.getPersonValue();
									                					 a=new Attribut("gender",gp.getGenderValue());
									                					 attr.add(a);s=s+"\n"+gp.getGenderValue();
									                					 a=new Attribut("number",np.getNumberValue());
									                					 attr.add(a);s=s+"\n"+np.getNumberValue();
									                					 a=new Attribut("case",cp.getCaseValue());
									                					 attr.add(a);s=s+"\n"+cp.getCaseValue();
									    
									                					 if (!Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("Comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                			});
									                		}
									                		else if(s.indexOf("Pronominal Suffix")>=0) {
									                			gbc.fill=GridBagConstraints.BOTH;
                                                                final PersonPanel1 pp;
									                			
									                			if (ta!=null){
									                				
									                			 pp=new PersonPanel1(ta.getMorphoValue("person"),false);
									                			c.add(pp.getPersonPanel(),gbc);
									                			}
									                			else
									                				{ pp=new PersonPanel1(" ",false);
									                				c.add(pp.getPersonPanel(),gbc);}
									                			final GenderPron gp;
									                			
									                			if (ta!=null){
									                				gbc.insets.left=0;
										                			gbc.gridx=1;
									                			 gp=new GenderPron(ta.getMorphoValue("gender"));
									                			c.add(gp.getGenderPanel(),gbc);
									                			}
									                			else
									                				{ gp=new GenderPron(" ");

										                			gbc.insets.left=0;
										                			gbc.gridx=1;
									                				c.add(gp.getGenderPanel(),gbc);}
									                			
									                			gbc.insets.left=2;
									                			gbc.gridx=2;
									                			gbc.gridy=0;
									                			final NumberPron np;
									                			if (ta!=null){
									                				
									                			np=new NumberPron(ta.getMorphoValue("number"));
									                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			else{
									                				np=new NumberPron("");
										                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			
									                			
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=1;gbc.gridwidth=3;
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                			commCN.setFont(typFont);
									                			//commCN.setPreferredSize(new Dimension(20,10));
									                			if (ta!=null){
									                				commCN.setText(ta.getMorphoValue("Comment"));
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:"+typFontString+"\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                			scommCN.setPreferredSize(new Dimension(100,200));
									                			gbc.gridy=2;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=3;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					Attribut a;String s="";
									                					 attr=new ArrayList<Attribut>();
									                					 assignedFeatures=true;
									                					 a=new Attribut("person",pp.getPersonValue());
									                					 attr.add(a);s=s+pp.getPersonValue();
									                					 a=new Attribut("gender",gp.getGenderValue());
									                					 attr.add(a);s=s+"\n"+gp.getGenderValue();
									                					 a=new Attribut("number",np.getNumberValue());
									                					 attr.add(a);s=s+"\n"+np.getNumberValue();
									                					
									    
									                					 if (!Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("Comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                			});
									                		}
									                		else if(s.equals("Demonstrative Pronoun")) {
									                			gbc.fill=GridBagConstraints.BOTH;
                                                                
									                			final GenderPron1 gp;
									                			
									                			if (ta!=null){
									                				gbc.insets.left=2;
										                			gbc.gridx=0;
									                			 gp=new GenderPron1(ta.getMorphoValue("gender"));
									                			c.add(gp.getGenderPanel(),gbc);
									                			}
									                			else
									                				{ gp=new GenderPron1(" ");

										                			gbc.insets.left=2;
										                			gbc.gridx=0;
									                				c.add(gp.getGenderPanel(),gbc);}
									                			
									                			gbc.insets.left=0;
									                			gbc.gridx=1;
									                			gbc.gridy=0;
									                			final NumberPron np;
									                			if (ta!=null){
									                				
									                			np=new NumberPron(ta.getMorphoValue("number"));
									                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			else{
									                				np=new NumberPron("");
										                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			final Case0AccPron cp;
									                			gbc.insets.left=2;
									                			gbc.gridx=2;gbc.gridy=0;
									                			if (ta!=null){
									                			   cp=new Case0AccPron(ta.getMorphoValue("case"));
									                			
									                			    c.add(cp.getCasePanel(),gbc);
									                			}
									                			else{
									                				cp=new Case0AccPron(" ");
										                			
									                			    c.add(cp.getCasePanel(),gbc);
									                			}
									                		//	final DistancePanel dp;
									                		//	gbc.insets.left=0;
									                		//	gbc.gridx=2;gbc.gridy=1;
									                		//	if (ta!=null){
									                			//   dp=new DistancePanel(ta.getMorphoValue("distance"));
									                			
									                			  //  c.add(dp.getDistPanel(),gbc);
									                			//}
									                		//	else{
									                			//	dp=new DistancePanel(" ");
										                			
									                			  //  c.add(dp.getDistPanel(),gbc);
									                			//}
									                			
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=1;gbc.gridwidth=3;
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                			commCN.setFont(typFont);
									                			//commCN.setPreferredSize(new Dimension(20,10));
									                			if (ta!=null){
									                				commCN.setText(ta.getMorphoValue("Comment"));
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:"+typFontString+"\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                			scommCN.setPreferredSize(new Dimension(100,200));
									                			gbc.gridy=2;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=3;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					Attribut a;String s="";
									                					 attr=new ArrayList<Attribut>();
									                					 assignedFeatures=true;
									                					 a=new Attribut("gender",gp.getGenderValue());
									                					 attr.add(a);s=s+"\n"+gp.getGenderValue();
									                					 a=new Attribut("number",np.getNumberValue());
									                					 attr.add(a);s=s+"\n"+np.getNumberValue();
									                					 a=new Attribut("case",cp.getCaseValue());
									                					 attr.add(a);s=s+"\n"+cp.getCaseValue();
									                					// a=new Attribut("distance",dp.getDistanceValue());
									                					 //attr.add(a);s=s+"\n"+dp.getDistanceValue();
									                					 if (Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("Comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                			});
									                		}
									                		else if(s.equals("Relative Pronoun")) {
									                			gbc.fill=GridBagConstraints.BOTH;
                                                                
									                			final GenderPronRel gp;
									                			
									                			if (ta!=null){
									                				
									                			 gp=new GenderPronRel(ta.getMorphoValue("gender"));
									                			c.add(gp.getGenderPanel(),gbc);
									                			}
									                			else
									                				{ gp=new GenderPronRel(" ");

										                			gbc.insets.left=2;
										                			gbc.gridx=0;
									                				c.add(gp.getGenderPanel(),gbc);}
									                			
									                			gbc.insets.left=0;
									                			gbc.gridx=1;
									                			gbc.gridy=0;
									                			final NumberPron np;
									                			if (ta!=null){
									                				
									                			np=new NumberPron(ta.getMorphoValue("number"));
									                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			else{
									                				np=new NumberPron("");
										                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			
									                			
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=1;gbc.gridwidth=2;
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                			commCN.setFont(typFont);
									                			//commCN.setPreferredSize(new Dimension(20,10));
									                			if (ta!=null){
									                				commCN.setText(ta.getMorphoValue("Comment"));
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:"+typFontString+"\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                			scommCN.setPreferredSize(new Dimension(100,200));
									                			gbc.gridy=2;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=4;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					Attribut a;String s="";
									                					 attr=new ArrayList<Attribut>();
									                					 assignedFeatures=true;
									                					 a=new Attribut("gender",gp.getGenderValue());
									                					 attr.add(a);s=s+"\n"+gp.getGenderValue();
									                					 a=new Attribut("number",np.getNumberValue());
									                					 attr.add(a);s=s+"\n"+np.getNumberValue();
									                					 
									                					 if (!Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("Comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                			});
									                		}
									                		else if(s.equals("Interrogative Pronoun")) {
									                			gbc.fill=GridBagConstraints.BOTH;
                                                                
									                		
									                			
									                			gbc.insets.left=2;
									                			gbc.gridx=0;
									                			gbc.gridy=0;
									                			final NumberPron np;
									                			if (ta!=null){
									                				
									                			np=new NumberPron(ta.getMorphoValue("number"));
									                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			else{
									                				np=new NumberPron("");
										                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			gbc.insets.left=0;
									                			gbc.gridx=1;
									                			gbc.gridy=0;
									                			final Case0AccPron cp;
									                			if (ta!=null){
									                				
									                			cp=new Case0AccPron(ta.getMorphoValue("number"));
									                			c.add(cp.getCasePanel(),gbc);
									                			}
									                			else{
									                				cp=new Case0AccPron("");
										                			c.add(cp.getCasePanel(),gbc);
									                			}
									                			
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=1;gbc.gridwidth=2;
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                			commCN.setFont(typFont);
									                			//commCN.setPreferredSize(new Dimension(20,10));
									                			if (ta!=null){
									                				commCN.setText(ta.getMorphoValue("Comment"));
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:"+typFontString+"\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                			scommCN.setPreferredSize(new Dimension(100,200));
									                			gbc.gridy=2;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=4;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					Attribut a;String s="";
									                					 attr=new ArrayList<Attribut>();
									                				
									                					 assignedFeatures=true;
									                					 a=new Attribut("number",np.getNumberValue());
									                					 attr.add(a);s=s+"\n"+np.getNumberValue();
									                					 a=new Attribut("case",cp.getCaseValue());
									                					 attr.add(a);s=s+"\n"+cp.getCaseValue();
									                					 if (!Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("Comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                			});
									                		}
									                		else if(s.equals("Pronoun of Totality Base")||s.equals("Pronoun of Solitude Base")) {
									                			gbc.fill=GridBagConstraints.BOTH;
                                     
									                			gbc.insets.left=2;
									                			gbc.gridx=0;
									                			gbc.gridy=0;
									                			final Case0AccPron cp;
									                			if (ta!=null){
									                				
									                			cp=new Case0AccPron(ta.getMorphoValue("case"));
									                			c.add(cp.getCasePanel(),gbc);
									                			}
									                			else{
									                				cp=new Case0AccPron("");
										                			c.add(cp.getCasePanel(),gbc);
									                			}
									                			
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=1;gbc.gridwidth=1;
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                			commCN.setFont(typFont);
									                			//commCN.setPreferredSize(new Dimension(20,10));
									                			if (ta!=null){
									                				commCN.setText(StringEscapeUtils.unescapeHtml(ta.getMorphoValue("Comment")));
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:"+typFontString+"\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                			scommCN.setPreferredSize(new Dimension(100,200));
									                			gbc.gridy=2;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=4;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					Attribut a;String s="";
									                					 attr=new ArrayList<Attribut>();
									                					 assignedFeatures=true;
									                					 a=new Attribut("case",cp.getCaseValue());
									                					 attr.add(a);s=s+"\n"+cp.getCaseValue();
									                					 if (!Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("Comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                			});
									                		}
									                		else if(s.equals("Possesive Pronoun Base")) {
									                			gbc.fill=GridBagConstraints.BOTH;
                                                                
									                			final GenderPron gp;
									                			
									                			if (ta!=null){
									                				
									                			 gp=new GenderPron(ta.getMorphoValue("gender"));
									                			c.add(gp.getGenderPanel(),gbc);
									                			}
									                			else
									                				{ gp=new GenderPron(" ");

										                			gbc.insets.left=2;
										                			gbc.gridx=0;
									                				c.add(gp.getGenderPanel(),gbc);}
									                			
									                			gbc.insets.left=0;
									                			gbc.gridx=1;
									                			gbc.gridy=0;
									                			final NumberPron np;
									                			if (ta!=null){
									                				
									                			np=new NumberPron(ta.getMorphoValue("number"));
									                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			else{
									                				np=new NumberPron("");
										                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			
									                			
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=1;gbc.gridwidth=2;
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                			commCN.setFont(typFont);
									                			//commCN.setPreferredSize(new Dimension(20,10));
									                			if (ta!=null){
									                				commCN.setText(ta.getMorphoValue("Comment"));
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:"+typFontString+"\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                			scommCN.setPreferredSize(new Dimension(100,200));
									                			gbc.gridy=2;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=3;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					Attribut a;String s="";
									                					 attr=new ArrayList<Attribut>();
									                					 assignedFeatures=true;
									                					 a=new Attribut("gender",gp.getGenderValue());
									                					 attr.add(a);s=s+"\n"+gp.getGenderValue();
									                					 a=new Attribut("number",np.getNumberValue());
									                					 attr.add(a);s=s+"\n"+np.getNumberValue();
									                					 
									                					 if (!Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("Comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                			});
									                		}
									                		else if(s.equals("Cardinal Numeral")||s.equals("Ordinal Numeral")) {
									                			gbc.fill=GridBagConstraints.BOTH;
                                                                
									                			final GenderNum gp;
									                			
									                			if (ta!=null){
									                				
									                			 gp=new GenderNum(ta.getMorphoValue("gender"));
									                			c.add(gp.getGenderPanel(),gbc);
									                			}
									                			else
									                				{ gp=new GenderNum(" ");

										                			gbc.insets.left=2;
										                			gbc.gridx=0;
									                				c.add(gp.getGenderPanel(),gbc);}
									                			
									                			gbc.insets.left=0;
									                			gbc.gridx=1;
									                			gbc.gridy=0;
									                			final NumberPron np;
									                			if (ta!=null){
									                				
									                			np=new NumberPron(ta.getMorphoValue("number"));
									                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			else{
									                				np=new NumberPron("None");
										                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			final Case0AccPron cp;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=1;
									                			if (ta!=null){
									                			   cp=new Case0AccPron(ta.getMorphoValue("case"));
									                			
									                			    c.add(cp.getCasePanel(),gbc);
									                			}
									                			else{
									                				cp=new Case0AccPron("none");
										                			
									                			    c.add(cp.getCasePanel(),gbc);
									                			}
									                			final StatusNumeralPanel sp;
									                			gbc.insets.left=0;
									                			gbc.gridx=1;gbc.gridy=1;
									                			if (ta!=null){
									                			   sp=new StatusNumeralPanel(ta.getMorphoValue("state"));
									                			
									                			    c.add(sp.getStatusPanel(),gbc);
									                			}
									                			else{
									                				sp=new StatusNumeralPanel("None");
										                			
									                			    c.add(sp.getStatusPanel(),gbc);
									                			}
									                			JLabel labelLogo=new JLabel("Logogram");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=2;gbc.gridwidth=2;
									                			c.add(labelLogo,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final JTextField logoText=new JTextField();
									                			logoText.setFont(typFont);
									                			//commCN.setPreferredSize(new Dimension(20,10));
									                			if (ta!=null){
									                				logoText.setText(ta.getMorphoValue("logogram"));
									                			}
									                			gbc.gridy=3;
									                			logoText.getDocument().addDocumentListener(new DocumentListener() {
									                				  public void changedUpdate(DocumentEvent e) {
									                				    changed();
									                				  }
									                				  public void removeUpdate(DocumentEvent e) {
									                				    changed();
									                				  }
									                				  public void insertUpdate(DocumentEvent e) {
									                				    changed();
									                				  }

									                				  public void changed() {
									                				     if (!logoText.getText().equals("")){
									                				       sp.clearValues();cp.clearValues();np.clearValues();gp.clearValues();
									                				     }
									                				     else {
									                				       np.getNumber1().setSelected(true);cp.getNom().setSelected(true);
									                				       sp.getStatus1().setSelected(true);
									                				    }

									                				  }
									                				});
									                			
									                			c.add(logoText,gbc);
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=4;gbc.gridwidth=2;
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                		
									                			//final NoWrapJTextPane commCN = new NoWrapJTextPane();
									                			commCN.setFont(typFont);
									                	
									                			
									                		commCN.setPreferredSize(new Dimension(100,200));
									                		
									                	//	commCN.setSize(20, 10);
									                			if (ta!=null){
									                				commCN.setText(ta.getMorphoValue("Comment"));
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:"+typFontString+"\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                		//	scommCN.setPreferredSize(new Dimension(100,200));
									                		
									                			gbc.gridy=5;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=6;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					Attribut a;String s="";
									                					 attr=new ArrayList<Attribut>();
									                					 assignedFeatures=true;
									                					 a=new Attribut("gender",gp.getGenderValue());
									                					 attr.add(a);s=s+"\n"+gp.getGenderValue();
									                					 a=new Attribut("number",np.getNumberValue());
									                					 attr.add(a);s=s+"\n"+np.getNumberValue();
									                					 a=new Attribut("case",cp.getCaseValue());
									                					 attr.add(a);s=s+"\n"+cp.getCaseValue();
									                					 a=new Attribut("state",sp.getStatusValue());
									                					 attr.add(a);s=s+"\n"+sp.getStatusValue();
									                					 if (logoText.getText()!=null){
									                						 a=new Attribut("logogram",logoText.getText());
									                						   s=s+"\n"+logoText.getText();
										                					 attr.add(a);
									                					 }
									                					 if (!Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("Comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                			});
									                		}
									                		else if(s.equals("Verb")) {
									                			gbc.fill=GridBagConstraints.BOTH;
                                                              
                                                                final PersonPanel pp;
									                			
									                			if (ta!=null){
									                				
									                			 pp=new PersonPanel(ta.getMorphoValue("person"));
									                			
									                			}
									                			else
									                				{ pp=new PersonPanel(" ");}
									                				
									                			
									                			final GenderVerb gp;
									                			
									                			if (ta!=null){
									                				
									                			 gp=new GenderVerb(ta.getMorphoValue("gender"));
									                			
									                			}
									                			else
									                				{ gp=new GenderVerb(" ");

									                				}
									                			
									          
									                			final NumberPron np;
									                			if (ta!=null){
									                				
									                			np=new NumberPron(ta.getMorphoValue("number"));
									                
									                			}
									                			else{
									                				np=new NumberPron("Singular");
										                	
									                			}
									                			final Case0AccPanelVerb cp;
									                
									                			if (ta!=null){
									                			   cp=new Case0AccPanelVerb(ta.getMorphoValue("case"));
									                												                			    
									                			}
									                			else{
									                				cp=new Case0AccPanelVerb("Nominative");

									                			}
									                			final StatusVerbPanel sp;
												                
									                			if (ta!=null){
									                			   sp=new StatusVerbPanel(ta.getMorphoValue("state"));
									                			
									                			    
									                			}
									                			else{
									                				sp=new StatusVerbPanel("Absolute state");
										                			
									                			   
									                			}
									                			
									                			  final TAM tamp;
	                                                     
										                			if (ta!=null){
										                				//System.out.println(ta.getMorphoValue("tamp"));
										                			 tamp=new TAM(ta.getMorphoValue("tam"),pp.getPerso1(),pp.getPerso2(),pp.getPerso3(),pp.getPersoVoid(),gp.getGenus1(),gp.getGenus2(),gp.getGenus3(),gp.getGenusVoid(),np.getNumber1(),np.getNumber2(),np.getNumberVoid(),cp.getCase1(),cp.getCase2(),cp.getCase3(),cp.getCase4(),cp.getCaseVoid(),sp.getStatus1(),sp.getStatus2(),sp.getStatus3(),sp.getStatus4(),sp.getStatusVoid());
										                			;c.add(tamp.getStatusPanel(),gbc);
										                			
										                			}
										                			else
										                				{ tamp=new TAM(" ",pp.getPerso1(),pp.getPerso2(),pp.getPerso3(),pp.getPersoVoid(),gp.getGenus1(),gp.getGenus2(),gp.getGenus3(),gp.getGenusVoid(),np.getNumber1(),np.getNumber2(),np.getNumberVoid(),cp.getCase1(),cp.getCase2(),cp.getCase3(),cp.getCase4(),cp.getCaseVoid(),sp.getStatus1(),sp.getStatus2(),sp.getStatus3(),sp.getStatus4(),sp.getStatusVoid());
										                				c.add(tamp.getStatusPanel(),gbc);
										                			}		
								                				
										                			gbc.insets.left=0;gbc.gridx=1;
								                				c.add(pp.getPersonPanel(),gbc);
								                				gbc.insets.left=2;gbc.gridx=0;gbc.gridy=1;
								                				c.add(gp.getGenderPanel(),gbc);
								                				gbc.insets.left=0;gbc.gridx=1;gbc.gridy=1;
								                				c.add(np.getNumberPanel(),gbc);
								                				gbc.insets.left=2;gbc.gridx=0;gbc.gridy=2;
								                				c.add(cp.getCasePanel(),gbc);
								                				gbc.insets.left=2;gbc.gridx=1;gbc.gridy=2;
								                				c.add(sp.getStatusPanel(),gbc);
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=3;gbc.gridwidth=2;
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                			commCN.setFont(typFont);
									                			//commCN.setPreferredSize(new Dimension(20,10));
									                			if (ta!=null){
									                				commCN.setText(ta.getMorphoValue("Comment"));
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:"+typFontString+"\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                			scommCN.setPreferredSize(new Dimension(100,200));
									                			gbc.gridy=4;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=5;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					Attribut a;String s="";
									                					assignedFeatures=true;
									                					 attr=new ArrayList<Attribut>();
									                					 a=new Attribut("tam",tamp.getTAMValue());
									                					 attr.add(a);s=s+tamp.getTAMValue();
									                					 if (!tamp.getTAMValue().equals("Infinitive")){
									                					 a=new Attribut("person",pp.getPersonValue());
									                					 attr.add(a);s=s+pp.getPersonValue();
									                					 a=new Attribut("gender",gp.getGenderValue());
									                					 attr.add(a);s=s+"\n"+gp.getGenderValue();
									                					 a=new Attribut("number",np.getNumberValue());
									                					 attr.add(a);s=s+"\n"+np.getNumberValue();
									                					 }
									                					 else  if (tamp.getTAMValue().equals("Infinitive")){
									                						 a=new Attribut("gender",gp.getGenderValue());
										                					 attr.add(a);s=s+"\n"+gp.getGenderValue();
										                					 a=new Attribut("number",np.getNumberValue());
										                					 attr.add(a);s=s+"\n"+np.getNumberValue();
									                					 a=new Attribut("case",cp.getCaseValue());
									                					 attr.add(a);s=s+"\n"+cp.getCaseValue();
									                					 a=new Attribut("state",sp.getStatusValue());
									                					 attr.add(a);s=s+"\n"+sp.getStatusValue();
									                					 }
									    
									                					 if (!Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("Comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                			});
									                		}
									                		else if(s.equals("Affirmative Particle")) {
									                			gbc.fill=GridBagConstraints.BOTH;
                                                                final PersonPanel1 pp;
									                			
									                			if (ta!=null){
									                				
									                			 pp=new PersonPanel1(ta.getMorphoValue("person"),true);
									                			c.add(pp.getPersonPanel(),gbc);
									                			}
									                			else
									                				{ pp=new PersonPanel1(" ",true);
									                				c.add(pp.getPersonPanel(),gbc);}
									                			final GenderPron gp;
									                			
									                			if (ta!=null){
									                				
									                			 gp=new GenderPron(ta.getMorphoValue("gender"));
									                			c.add(gp.getGenderPanel(),gbc);
									                			}
									                			else
									                				{ gp=new GenderPron(" ");

										                			gbc.insets.left=0;
										                			gbc.gridx=1;
									                				c.add(gp.getGenderPanel(),gbc);}
									                			
									                			gbc.insets.left=2;
									                			gbc.gridx=2;
									                			gbc.gridy=0;
									                			final NumberPron np;
									                			if (ta!=null){
									                				
									                			np=new NumberPron(ta.getMorphoValue("number"));
									                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			else{
									                				np=new NumberPron("");
										                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			
									                			
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=1;gbc.gridwidth=3;
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                			commCN.setFont(typFont);
									                			//commCN.setPreferredSize(new Dimension(20,10));
									                			if (ta!=null){
									                				commCN.setText(ta.getMorphoValue("Comment"));
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:"+typFontString+"\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                			scommCN.setPreferredSize(new Dimension(100,200));
									                			gbc.gridy=2;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=3;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					Attribut a;String s="";
									                					assignedFeatures=true;
									                					 attr=new ArrayList<Attribut>();
									                					 a=new Attribut("person",pp.getPersonValue());
									                					 attr.add(a);s=s+pp.getPersonValue();
									                					 a=new Attribut("gender",gp.getGenderValue());
									                					 attr.add(a);s=s+"\n"+gp.getGenderValue();
									                					 a=new Attribut("number",np.getNumberValue());
									                					 attr.add(a);s=s+"\n"+np.getNumberValue();
									                					
									    
									                					 if (!Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("Comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                			});
									                		}
									                		else if(s.equals("Deictic Imperative Particle")){
									                			gbc.fill=GridBagConstraints.BOTH;
                                                                final PersonPanel1 pp;
									                			
									                			if (ta!=null){
									                				
									                			 pp=new PersonPanel1(ta.getMorphoValue("person"),true);
									                			c.add(pp.getPersonPanel(),gbc);
									                			}
									                			else
									                				{ pp=new PersonPanel1(" ",true);
									                				c.add(pp.getPersonPanel(),gbc);}
									                			final GenderPron gp;
									                			
									                			if (ta!=null){
									                				
									                			 gp=new GenderPron(ta.getMorphoValue("gender"));
									                			 gbc.insets.left=0;
										                			gbc.gridx=1;
									                			c.add(gp.getGenderPanel(),gbc);
									                			}
									                			else
									                				{ gp=new GenderPron(" ");

										                			gbc.insets.left=0;
										                			gbc.gridx=1;
									                				c.add(gp.getGenderPanel(),gbc);}
									                			
									                			gbc.insets.left=2;
									                			gbc.gridx=2;
									                			gbc.gridy=0;
									                			final NumberPron np;
									                			if (ta!=null){
									                				
									                			np=new NumberPron(ta.getMorphoValue("number"));
									                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			else{
									                				np=new NumberPron("");
										                			c.add(np.getNumberPanel(),gbc);
									                			}
									                			
									                			
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=1;gbc.gridwidth=3;
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                			commCN.setFont(typFont);
									                			//commCN.setPreferredSize(new Dimension(20,10));
									                			if (ta!=null){
									                				commCN.setText(ta.getMorphoValue("Comment"));
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:"+typFontString+"\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                			scommCN.setPreferredSize(new Dimension(100,200));
									                			gbc.gridy=2;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=3;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					Attribut a;String s="";
									                					 attr=new ArrayList<Attribut>();
									                					 assignedFeatures=true;
									                					 a=new Attribut("person",pp.getPersonValue());
									                					 attr.add(a);s=s+pp.getPersonValue();
									                					 a=new Attribut("gender",gp.getGenderValue());
									                					 attr.add(a);s=s+"\n"+gp.getGenderValue();
									                					 a=new Attribut("number",np.getNumberValue());
									                					 attr.add(a);s=s+"\n"+np.getNumberValue();
									                					
									    
									                					 if (!Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("Comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                			});
									                		}
									                		else if(s.equals("Preposition")) {
									                			gbc.fill=GridBagConstraints.BOTH;
                                                                
									                			final StatusPrepPanel sp;
									                			
									                			if (ta!=null){
									                				
									                			 sp=new StatusPrepPanel(ta.getMorphoValue("state"));
									                			c.add(sp.getStatusPanel(),gbc);
									                			}
									                			else
									                				{ sp=new StatusPrepPanel("Nominal state");

										                			gbc.insets.left=2;
										                			gbc.gridx=0;
									                				c.add(sp.getStatusPanel(),gbc);}
									                			
									               
									                			
									                			
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=1;gbc.gridwidth=1;
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                			commCN.setFont(typFont);
									                			//commCN.setPreferredSize(new Dimension(20,10));
									                			if (ta!=null){
									                				commCN.setText(ta.getMorphoValue("Comment"));
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:"+typFontString+"\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                			scommCN.setPreferredSize(new Dimension(100,200));
									                			gbc.gridy=2;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=3;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					assignedFeatures=true;
									                					Attribut a;String s="";
									                					 attr=new ArrayList<Attribut>();
									                				
									                					 a=new Attribut("state",sp.getStatusValue());
									                					 attr.add(a);s=s+"\n"+sp.getStatusValue();
									                					 
									                					 if (!Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("Comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                			});
									                		}
									                		else if(s.equals("Interjection")) {
									                			gbc.fill=GridBagConstraints.BOTH;
                                                                
									                			final StatusPrepPanel sp;
									                			
									                			if (ta!=null){
									                				
									                			 sp=new StatusPrepPanel(ta.getMorphoValue("state"));
									                			c.add(sp.getStatusPanel(),gbc);
									                			}
									                			else
									                				{ sp=new StatusPrepPanel("");

										                			gbc.insets.left=2;
										                			gbc.gridx=0;
									                				c.add(sp.getStatusPanel(),gbc);}
									                			
									               
									                			
									                			
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=1;gbc.gridwidth=1;
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                			commCN.setFont(typFont);
									                			//commCN.setPreferredSize(new Dimension(20,10));
									                			if (ta!=null){
									                				commCN.setText(ta.getMorphoValue("Comment"));
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:"+typFontString+"\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                			scommCN.setPreferredSize(new Dimension(100,200));
									                			gbc.gridy=2;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=3;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					assignedFeatures=true;
									                					Attribut a;String s="";
									                					 attr=new ArrayList<Attribut>();
									                				
									                					 a=new Attribut("state",sp.getStatusValue());
									                					 attr.add(a);s=s+"\n"+sp.getStatusValue();
									                					 
									                					 if (!Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("Comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                			});
									                		}
									                		else{
									                			gbc.fill=GridBagConstraints.BOTH;
                                                                
									                		
									                			
									                			JLabel labelComm=new JLabel("Comment");
									                			gbc.fill=GridBagConstraints.BOTH;
									                			gbc.insets.left=2;
									                			gbc.gridx=0;gbc.gridy=0;gbc.gridwidth=1;
									                			c.add(labelComm,gbc);
									                			gbc.fill=GridBagConstraints.BOTH;
									                			final HTMLEditorPane commCN = new HTMLEditorPane();
									                			commCN.setFont(typFont);
									                			//commCN.setPreferredSize(new Dimension(20,10));
									                			if (ta!=null){
									                				commCN.setText(ta.getMorphoValue("Comment"));
									                			}
									                			if(Jsoup.parse(commCN.getText()).text().isEmpty())
										                			commCN.setText("<div style=\'font-family:"+typFontString+"\'></div>");
									                			final JScrollPane  scommCN=new JScrollPane(commCN);
									                			scommCN.setPreferredSize(new Dimension(100,200));
									                			gbc.gridy=1;
									                			
									                			
									                			c.add(scommCN,gbc);
									                			gbc.gridy=3;
									                			gbc.fill=GridBagConstraints.NONE;
									                			gbc.anchor=GridBagConstraints.CENTER;
									                			final JButton assignCN=new JButton("Assign");
									                			c.add(assignCN,gbc);
									                			assignCN.addActionListener(new ActionListener(){
									                				public void actionPerformed(ActionEvent e){
									                					assignedFeatures=true;
									                					Attribut a;String s="";
									                				     //attr.clear();
									                					 attr=new ArrayList<Attribut>();
									                				
									                					 if (!Jsoup.parse(commCN.getText()).text().isEmpty()){
									                						 a=new Attribut("Comment",commCN.getText());
									                						   s=s+"\n"+commCN.getText();
										                					 attr.add(a);
									                					 }
									                					 deepArea.setText(s);
									                					 deepFrame.doDefaultCloseAction();
									                					 selectDeep.setEnabled(true);
									                				}
									                				
									                			});
									                			
									                		}
									                		//deepFrame.pack();
									                		
									                		 deepFrame.addInternalFrameListener(new InternalFrameListener() {
										                          
									                            	public void internalFrameActivated(InternalFrameEvent event) {
									                            		
									                            		    
									                            		
									                            		}
									                            	
									                            		public void internalFrameClosed(InternalFrameEvent event) {
									                            		
									                            			 if (assignedFeatures==false) attr.clear();
									                            		
									                            		}
									                            		public void internalFrameOpened(InternalFrameEvent event) {
										                            			 
										                            		
									                            		}
									                            	
									                            		public void internalFrameClosing(InternalFrameEvent event) {
									                            		
									                            		}
									                            		
		                                                                public void internalFrameDeiconified(InternalFrameEvent event) {
										                         
										                            		
									                            		}
		                                                                public void internalFrameDeactivated(InternalFrameEvent event) {
										                         
										                            		
									                            		}
									                            		public void internalFrameIconified(InternalFrameEvent event) {
										                         
										                            		
									                            		}

									                            
									                        });
									                		
									                		addChild1(deepFrame,300, 230);
				 			                				deepFrame.pack();
				 			                	             deepFrame.setVisible(true);
				 			                				 deepFrame.moveToFront();
									                	}
							                		});
							                		selectLemmaBM.addActionListener(new ActionListener(){
							                		
									                	public void actionPerformed(ActionEvent e){
									                		String lm="";
									                		if(ta!=null){
									                			lm=ta.getMorphoValue("lex");
									                			
									                		}
									                		LexiconFrame1 lf=new LexiconFrame1(lm);
									                		LexIDSel="";
									                		lf.addPropertyChangeListener(new PropertyChangeListener(){
				 			                					public void propertyChange(PropertyChangeEvent p){
				 			                					  if(p.getPropertyName().equals("closed")){
				 			                						
				 			                					  if (LexIDSel.length()>0){lemmaId.setText(LexIDSel);}
				 			                					  
				 			                					  }
				 			                					}
				 			                				});
									                		lf.pack();
									                		//lf.setResizable(b);
				 			                				//System.out.println("Lexicon Entry **"+LexIDSel);
				 			                				addChild(lf,500, 200, 500, 300);
				 			                				lf.pack();
				 			                	             lf.setVisible(true);
				 			                				 lf.moveToFront();
									                		
									                	}
							                		});
							                		selectLemma.addActionListener(new ActionListener(){
									                	public void actionPerformed(ActionEvent e){
									                		//Lexicon lex=new Lexicon();
									                		String lm="";
									                		if(ta!=null){
									                			lm=ta.getMorphoValue("lex");
									                			
									                		}
									                		LexiconFrame lf=new LexiconFrame(lex,lm);
									                		LexIDSel="";
									                		lf.addPropertyChangeListener(new PropertyChangeListener(){
				 			                					public void propertyChange(PropertyChangeEvent p){
				 			                					  if(p.getPropertyName().equals("closed")){
				 			                						
				 			                					  if (LexIDSel.length()>0){lemmaId.setText(LexIDSel);}
				 			                					  
				 			                					  }
				 			                					}
				 			                				});
									                		lf.pack();
									                		//lf.setResizable(b);
				 			                				//System.out.println("Lexicon Entry **"+LexIDSel);
				 			                				addChild(lf,500, 200, 500, 300);
				 			                				lf.pack();
				 			                	             lf.setVisible(true);
				 			                				 lf.moveToFront();
									                	}
									                	});
							                		selectPos.addActionListener(new ActionListener(){
									                	public void actionPerformed(ActionEvent e){
									                		 JTreePOS t = new JTreePOS();
									                		    DefaultMutableTreeNode tModel=t.createNodes();			                		    
									                		     treePOS=new JTree(tModel);
									                		    treePOS.expandPath(new TreePath(treePOS.getModel().getRoot()));
									                		    treePOS.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
									                		     for (int i = 0; i < treePOS.getRowCount(); i++) {
									                		        treePOS.expandRow(i);
									                			}
									                		    treePOS.setFont(etiopicText);
									                		    final JScrollPane posPane=new JScrollPane(treePOS);
									                		posPane.setPreferredSize(new Dimension(300,600));
									                		posPane.setEnabled(false);
									                		final JButton setPos=new JButton("Select");
									                		final ChildFrame posFrame=new ChildFrame("PoS ",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
									                		
									                		Container c=posFrame.getContentPane();
									                		c.setLayout(new GridBagLayout());
									                		GridBagConstraints gbc=new GridBagConstraints();
											                gbc.gridx=0;
									                		gbc.weightx=100;
									                		gbc.weighty=100;
									                		gbc.gridy=0;
									                		gbc.insets.left=2;
									                		gbc.insets.right=2;
									                		gbc.anchor=GridBagConstraints.NORTHWEST;
									                 		gbc.insets.top=2;
									                		gbc.insets.bottom=2;
									                		gbc.insets.right=0;
									                		gbc.fill=GridBagConstraints.NONE;
								                		   c.add(posPane,gbc);
								                		   gbc.gridy=1;
								                		   gbc.anchor=GridBagConstraints.CENTER;
								                		   c.add(setPos,gbc);
								                		   posFrame.pack();
									                		addChild(posFrame,700,100,550,850);
									                		//lingAnnotFrame.setResizable(false);
									                		posFrame.pack();
									                		posFrame.setVisible(true);
									                		posFrame.moveToFront();
									                		 treePOS.addTreeSelectionListener(new TreeSelectionListener() {
									         		            public void valueChanged(TreeSelectionEvent e) {
									         		                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
									         		                                   treePOS.getLastSelectedPathComponent();

									         		            /* if nothing is selected */ 
									         		                if (node == null) return;
									         		              
									         		            /* retrieve the node that was selected */ 
									         		                Object nodeInfo = node.getUserObject(); 
									         		                 
									         		                	 TreePath[] paths = treePOS.getSelectionPaths();
									                                       // System.out.println(paths.length+ " selected");
									         		                     for (TreePath path : paths) {
									         		                    	posPart.setText(path.getLastPathComponent().toString());
									         		                        // System.out.println("You've selected: " + path.getLastPathComponent());
									         		                    
									         		                    
									         		                	// System.out.println(nodeInfo);
									         		                 }
									         		            }
									         		        });
									                		
									                		setPos.addActionListener(new ActionListener(){
											                	public void actionPerformed(ActionEvent e){
											                		deepArea.setText(""); pressPoS=1;
											                		//LexIDSel="";
											                		//lemmaId.setText("");
											                		attr.clear();
											                		/*if (!lemmaId.getText().isEmpty()){
											                			
											                			
											                			attr.add(new Attribut("lex",lemmaId.getText()));
											                		}*/
											                		posFrame.doDefaultCloseAction();
											                	}
									                		});
									                		
									                	}
							                		});
							                		}
							                	}
							                });
							                	
							                }
							                //tokenisation
							                jmDelToken.addActionListener(new ActionListener(){
							                	public void actionPerformed(ActionEvent e){
							                		final ChildFrame deleteTokenFrame=new ChildFrame("Delete Tokens",mainFrameColor,WindowConstants.DISPOSE_ON_CLOSE);
								                	final int selectedWord=translitWordList.getSelectedIndex();
									                final Container c =deleteTokenFrame.getContentPane();
									                final JButton delT= new JButton("Delete Tokens");
									                final JButton cancel= new JButton("Cancel");
									                final JCheckBox global = new JCheckBox("Global");
									                JLabel wordLabel=new JLabel("<html><p><fonr size=+1 colour=red><b>You are going to delete existent tokens <br/> together with their morphological & NE annotation</b></font></p></html>");
									                wordLabel.setForeground(Color.RED);
									                final JPanel c1=new JPanel(new GridBagLayout());
									                GridBagConstraints gbc1=new GridBagConstraints();
									                gbc1.gridx=0;
							                		gbc1.weightx=100;
							                		gbc1.weighty=100;
							                		gbc1.gridy=0;
							                		gbc1.insets.left=2;
							                		gbc1.insets.right=2;
							                		gbc1.anchor=GridBagConstraints.CENTER;
							                 		gbc1.insets.top=2;
							                		gbc1.insets.bottom=2;
							                		gbc1.insets.right=0;
							                		gbc1.fill=GridBagConstraints.NONE;
							                		gbc1.gridwidth=2;
							                		c1.add(wordLabel);
							                		gbc1.gridy=1;
							                		gbc1.gridwidth=2;
							                		gbc1.anchor=GridBagConstraints.NORTHWEST;
							                		gbc1.insets.right=2;
							                		c1.add(global,gbc1);
							                		gbc1.gridwidth=1;
							                		gbc1.gridy=2;
							                		gbc1.gridx=0;
							                		gbc1.insets.left=2;
							                		c1.add(delT,gbc1);
							                		gbc1.insets.left=0;
							                		gbc1.gridx=1;
							                		c1.add(cancel,gbc1);
							                		c.add(c1);
							                		deleteTokenFrame.pack();
							                		addChild(deleteTokenFrame,100,100,550,200);
							                		deleteTokenFrame.setResizable(false);
							                		deleteTokenFrame.pack();
						   			               deleteTokenFrame.setVisible(true);
						   			              deleteTokenFrame.moveToFront();
                                                  
						   			           cancel.addActionListener(new ActionListener(){
						   			            	  public void actionPerformed(ActionEvent e1){
						   			            		  deleteTokenFrame.doDefaultCloseAction();
						   			            	  }
						   			           });
						   			           
						   			           global.addActionListener(new ActionListener(){
						   			        	   public void actionPerformed(ActionEvent e){
						   			        	//	if(e.getStateChange()==ItemEvent.SELECTED){
						   			        		    WortNode wsel=words.get(indexes[0]);
						   			        		 String labelWsel=wsel.getFidalInternLabel(typScript,typDoc)+"*"+wsel.getTranslitLabel(typScript);
					                                    haveAnnot=new ArrayList<String>();
					                                    
						   			        		if( global.isSelected()){
							                			//System.out.println(wsel.getFidalInternLabel());
							                			//System.out.println(wsel.getTranslitLabel(typScript));
							                			//System.out.println(index.get(wsel.getFidalInternLabel()+"*"+wsel.getTranslitLabel(typScript)).size());
							                			global.setSelected(true);
							                		//	System.out.println("SEl "+translitWordList.getSelectedIndex());
						   			        			for(int i=0; i<index.get(labelWsel).size();i++){
							                				 if(i!=selectedWord){
							                			        WortNode wn=words.get(indexIdWord.get(index.get(labelWsel).get(i)).intValue());
							                			        boolean found=false;
							                			        int j=0;
							                			        while((j<wn.getTokenIds().size())&&(!found)){
							                			        	Token t=tokens.get(indexIdToken.get(wn.getTokenIds().get(j)).intValue());
							                			        	if( t.getMorphoAnnotation()!=null){
							                			        		if( (t.getMorphoAnnotation().isComplete())) found=true;
							                			        		else j=j+1;
							                			        	}
							                			        	else j=j+1;
							                			        }
							                			        if(found) {haveAnnot.add(wn.getId());
							                			       // System.out.println("Have already  annotation "+wn.getId());
							                			        }
						   			        			}
							                			}
							                			if( haveAnnot.size()>0){
							                				int confirm= JOptionPane.showOptionDialog(desk,
							                		                "You may erase annotations which are marked as Complete. See a List and decide ?",
							                		                "Delete Complete Annotation Confirmation", JOptionPane.YES_NO_CANCEL_OPTION,
							                		                JOptionPane.QUESTION_MESSAGE, null, null, null);
							                				if (confirm == JOptionPane.YES_OPTION) {
							                					//saveAsToFile();
							                					//for (int i=0;i<haveAnnot.size();i++)
							                						//System.out.println ("Has Annotation "+haveAnnot.get(i));
							                					final ChildFrame showTokenised=new ChildFrame("Already Tokenised",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
							                					Container c = showTokenised.getContentPane();
							                					c.setLayout(new GridBagLayout());
							                					GridBagConstraints gbc=new GridBagConstraints();
							                					gbc.gridx=0;gbc.gridy=0;
							                					gbc.insets.left=2;
							                					gbc.insets.top=2;
							                					gbc.insets.bottom=2;
							                					gbc.insets.right=2;
							                					gbc.weighty=100.0;
							                					gbc.weightx=100.0;
							                					tokenisedPane=new JTextPane();
							                					tokenisedPane.setSize(300, 200);
							                					  tokenisedPane.setContentType("text/html");
							                				        tokenisedPane.setEditorKit(new HTMLEditorKit());
							                				      tokenisedPane.setEditable(false);
							                				      String Text="";
							                				         tokenisedPane = new JEditorPane("text/html",Text);
							                				         tokenisedPane.setFont(typFont);
							                				         String bodyRule = "body { font-family: " + typFont.getFamily() + "; " +
							                				                 "font-size: " + typFont.getSize() + "pt; }";
							                				         ((HTMLDocument)tokenisedPane.getDocument()).getStyleSheet().addRule(bodyRule);
							                				         //searchPane.setEditable(false);
							                				      //   editor.set
							                				      
							                				       tokenisedPane.setCaretPosition(0);
							                				       MyLinkController controller = new MyLinkController();
							                				        tokenisedPane.addMouseListener(controller);
							                				        tokenisedPane.addMouseListener(controller);
							                				        String s="";
							                				       for(int i=0;i<haveAnnot.size();i++){
							                				    	  // System.out.println("Position "+indexIdWord.get(haveAnnot.get(i)).intValue());
							                				    	   int posTok=indexIdWord.get(haveAnnot.get(i)).intValue();
							                				    	   if ((posTok>0)&&(posTok<words.size()-1))
							                				    	   s=s+words.get(posTok-1).getTranslitLabel(typScript)+" <a href=\"https://"+ indexIdWord.get(haveAnnot.get(i)).intValue()+"\">"+words.get(posTok).getTranslitLabel(typScript)+"</a>" +"<input type='checkbox' id='"+haveAnnot.get(i)+"'> "+words.get(posTok+1).getTranslitLabel(typScript)+"<br/>";
							                				    	   else if (posTok>0)
							                				    		   s=s+words.get(posTok-1).getTranslitLabel(typScript)+" <a href=\"https://"+ indexIdWord.get(haveAnnot.get(i)).intValue()+"\">"+"<br/>";
							                				    	   else if (posTok<words.size()-1)
						   			                                        s=s+"<a href=\"https://"+ indexIdWord.get(haveAnnot.get(i)).intValue()+"\">"+words.get(posTok).getTranslitLabel(typScript)+"</a>" +"<input type='checkbox' id='"+haveAnnot.get(i)+"'> "+words.get(posTok+1).getTranslitLabel(typScript)+"<br/>";
							                				    	   else
							                				    		   s=s+"<a href=\"https://"+ indexIdWord.get(haveAnnot.get(i)).intValue()+"\">"+words.get(posTok).getTranslitLabel(typScript)+"</a>" +"<input type='checkbox' id='"+haveAnnot.get(i)+"'>"+"<br/>";
							                				       }
							                				       
							                		
							                				       tokenisedPane.setText(s);
							                				      
							                				       //searchPane.setEnabled(false);
							                				        final  JScrollPane tokenEditorP=new JScrollPane(tokenisedPane);
							                				        // scrollEditorP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
							                				         tokenEditorP.setPreferredSize(new Dimension(600, 300));
							                				        tokenEditorP.getVerticalScrollBar().setValue(0);
							                				        JLabel tokenisedL=new JLabel("Following tokens are marked as \"complete annotated\". Select the ones you want to change");
							                				        final JButton cancel=new JButton("Cancel");
							                				        final JButton ok=new JButton("OK");
							                				       gbc.gridwidth=2;
							                				       gbc.anchor=GridBagConstraints.CENTER;
							                				       c.add(tokenisedL,gbc);
							                				       gbc.gridy=1;
							                				       c.add(tokenEditorP,gbc);
							                				       gbc.gridy=2;
							                				       gbc.gridwidth=1;
							                				       c.add(ok,gbc);
							                				       gbc.gridx=1;
							                				       c.add(cancel,gbc);
							                				       
							                				   	tokenisedPane.addHyperlinkListener(new HyperlinkListener() {
							                					    public void hyperlinkUpdate(HyperlinkEvent e) {
							                					        if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
							                					        	String s=e.getURL().toString().substring(7);
							                					        	int pos=Integer.parseInt(s);
							                					        	translitWordList.setSelectedIndex(pos);
							                					        	translitWordList.ensureIndexIsVisible(translitWordList.getSelectedIndex());
							                					         //  System.out.println("Event on "+ s);
							                					        }
							                					    }
							                					});
							                				   	ok.addActionListener(new ActionListener(){
							                				   		
							                				   		public void actionPerformed(ActionEvent e){
							                				   		 HTMLDocument doc = (HTMLDocument)tokenisedPane.getDocument();
						        								        ElementIterator it = new ElementIterator(doc);
						        								        Element element;
						        								        int k=0;
                                                                   ArrayList<String> selectW=new ArrayList<String>();
						        								        while ( (element = it.next()) != null )
						        								        {
						        								            //System.out.println("*** "+element.getName()+"  "+element.getClass()+" **");
			                                                             if(element.getName().equals("input")){
			                                                           	  k++;
			                                                           	  //System.out.println("Checkbox "+k);
						        								            AttributeSet as = element.getAttributes();
						        								            Enumeration enumm = as.getAttributeNames();
			                                                               boolean issel=false;
			                                                               String idWort="";
						        								            while( enumm.hasMoreElements() )
						        								            {
						        								                Object name = enumm.nextElement();
						        								                Object value = as.getAttribute( name );
						        								         
						        								               //System.out.println( "\t" + name + " : " + value );
			                                                                  if((""+name).equals("id")) {
			                                                                	  //System.out.println("$$$"+value);
			                                                                  idWort=""+value;
			                                                                  //System.out.println("\t Word"+getWord(idWort).getTranslitLabel(typScript));
			                                                                  }
			                                                                 
			                                                                 if (value instanceof ToggleButtonModel)
						        								                {
						        								                	ToggleButtonModel model = (ToggleButtonModel)value;
						        								                	//if(global.isSelected()) model.setSelected(true);
						        								                	
						        								                	if( model.isSelected()) {
						        								                		//System.out.println("!!!!!"+ idWort+" "+getWord(idWort).getTranslitLabel(typScript));
						        								                	    int posC=idWort.indexOf("checked");
						        								                	    if (posC>=0) idWort=idWort.substring(0,posC);
						        								                	    selectW.add(idWort);
						        								                	  //  System.out.println("Selected "+ idWort);
						        								                	    haveAnnot.remove(idWort);
						        								                		issel=true;
						        								                	}
						        								                }
						        								                    
						        								                }
			                                                             }
						        								            }
							                				   			showTokenised.doDefaultCloseAction();
							                				   		}
							                				   		
							                				   	});
							                				       
							                				       showTokenised.pack();
											                		addChild(showTokenised,100,100,550,200);
											                		showTokenised.pack();
										   			               showTokenised.setVisible(true);
										   			              showTokenised.moveToFront();
							                				}
							                				else if (confirm == JOptionPane.NO_OPTION){
							                					//System.out.println ("Do it blind");
							                					haveAnnot.clear();
							                				}
							                				else {
							                					//System.out.println ("Cancel action");
							                				}
 
							                			}
							                				
							                			
							                		}
						   			        		//}
						   			        	   }
						   			           });
						   			        delT.addActionListener(new ActionListener(){
					   			            	  public void actionPerformed(ActionEvent e1){
					   			            		 wordsBackup.clear();tokensBackup.clear();
					   			            		for(int i=0;i<words.size();i++){
					   			            	    	wordsBackup.add(words.get(i).copyWortNode());
					   			            	    }
					   			            	  for(int i=0;i<tokens.size();i++){
					   			            		 //   System.out.println("Token "+tokens.get(i).getLabel());
					   			            	    	tokensBackup.add(tokens.get(i).copyToken());
					   			            	    }
					   			            	  undoMenu.setEnabled(true);
							                		WortNode wsel=words.get(selectedWord);
							                		ArrayList<String> globalSel=new ArrayList<String>();
				                                    String labelWsel=wsel.getFidalInternLabel(typScript,typDoc)+"*"+wsel.getTranslitLabel(typScript);
				                                   // ArrayList<String> haveAnnot=new ArrayList<String>();
							                		if( global.isSelected()){
							                			//System.out.println(wsel.getFidalInternLabel());
							                			//System.out.println(wsel.getTranslitLabel(typScript));
							                			//System.out.println(index.get(wsel.getFidalInternLabel()+"*"+wsel.getTranslitLabel(typScript)).size());
							                			for(int i=0; i<index.get(labelWsel).size();i++){
							                			        globalSel.add(index.get(labelWsel).get(i));	
							                			       
							                			}
							                			
							                		}
							                		else globalSel.add(wsel.getId());
							                		if(haveAnnot.size()>0)
							                			for(int i=0;i<haveAnnot.size();i++)
							                				globalSel.remove(haveAnnot.get(i));
							                	for(int k=0;k<globalSel.size();k++){
							                		wsel=words.get(indexIdWord.get(globalSel.get(k)).intValue());
							                		ArrayList<String> tselId=new ArrayList<String>();
							                		for(int kk=0;kk<wsel.getTokenIds().size();kk++)
							                				tselId.add(wsel.getTokenIds().get(kk));
							                		//System.out.println(wsel.getFidalInternLabel());
							                		//System.out.println(wsel.getTranslitLabel(typScript));
							                		String oldLabel=wsel.getFidalInternLabel(typScript,typDoc)+"*"+wsel.getTranslitLabel(typScript);
							                		//System.out.println("old label "+wsel.getFidalInternLabel(typScript,typDoc)+"*"+wsel.getTranslitLabel(typScript));
							                		//if(index.get(oldLabel)!=null){
							                		if (index.get(oldLabel).size()>0) index.get(oldLabel).remove(wsel.getId());
							                		if(index.get(oldLabel).size()==0)
							                			index.remove(oldLabel);
							                		//}
							                		//wsel.deleteTokenID();
							                		Token t=new Token("T0>"+wsel.getId(),false,wsel.getLetters());
							                		//System.out.println("NEW Token "+ t.getLabel());
							                	    for(int i=0;i<tselId.size();i++){
							                	    	
							                	    	if (i==0){
							                	    		String label=tokens.get(indexIdToken.get(tselId.get(0)).intValue()).getLabel();
							                                indexT.get(label).remove(tselId.get(i));
							                                int pos=indexIdToken.get(tselId.get(0)).intValue();
							                                if ( indexT.get(label).size()==0) indexT.remove(label);
							                	    		tokens.set(pos,t);
							                	    		if (indexT.containsKey(t.getLabel()))
							                	    			indexT.get(t.getLabel()).add(t.getId());
							                	    		else {
							                	    			ArrayList<String> aux=new ArrayList<String>();
							                	    			aux.add(t.getId());
							                	    			indexT.put(t.getLabel(), aux);
							                	    		}
							                	    		//wsel.setTokenIds(new ArrayList<String>());;
							                	    		//wsel.getTokenIds().add(t.getId());
							                	    		
							                	    	}
							                	    	else {
							                	    		String label=tokens.get(indexIdToken.get(tselId.get(i)).intValue()).getLabel();
							                                indexT.get(label).remove(tselId.get(i));
							                                if ( indexT.get(label).size()==0) indexT.remove(label);
							                	    		tokens.remove(indexIdToken.get(tselId.get(i)).intValue());
							                	    		indexIdToken.remove(tselId.get(i));
							                	    	}
							                	    	 createIndexTokens();
							                	    }
							                	  
							                		//ArrayList<String> nt=new ArrayList<String>();
							                		//nt.add(t.getId());
							                		//wsel.setTokenIds(nt);;
							                	    wsel.deleteTokenID();
					                	    		//createIndexTokens();
							                	    String newLabel=wsel.getFidalInternLabel(typScript,typDoc)+"*"+wsel.getTranslitLabel(typScript);
							                	    if(index.containsKey(newLabel)) index.get(newLabel).add(wsel.getId());
							                	    else {
							                	    	ArrayList<String> aux=new ArrayList<String>();
							                	    	aux.add(wsel.getId());
							                	    	index.put(newLabel, aux);
							                	    }
							                	   /* if(indexT.containsKey(t.getLabel())) indexT.get(t.getLabel()).add(t.getId());
							                	    else {
							                	    	ArrayList<String> aux=new ArrayList<String>();
							                	    	aux.add(t.getId());
							                	    	indexT.put(t.getLabel(), aux);
							                	    }*/
							                	    wsel.setAutomToken(false);
							                	    //System.out.println("Inserted "+wsel.getTranslitLabel(typScript));
							                	//    modelTranslit.set(indexIdWord.get(wsel.getId()), wsel);
					   			            		
					   			            		words.set(indexIdWord.get(wsel.getId()), wsel);
							                	} 
					   			            		translitWordList.revalidate();
					   			            		translitWordList.repaint();
					   			            		deleteTokenFrame.doDefaultCloseAction();
					   			            	  }
						   			        });
							                	}
							                	
							                });
		//Tokenise action
							                jmtokenise.addActionListener(new ActionListener(){
							                	public void actionPerformed(ActionEvent e){
							                		ltid = ((WortNode)modelTranslit.get(indexes[0])).getTokenIds().get(0);
							                		//System.out.println("Token "+ltid);
							                	    wid=ltid.substring(ltid.indexOf(">")+1);
							                		//System.out.println("Tokenise Word "+wid);
							                		
							                		tsel=tokens.get(indexIdToken.get(ltid).intValue());
							                		wsel=words.get(indexIdWord.get(wid).intValue());
							                		
							                		//System.out.println("ID token "+ltid.get(0));
							                		//System.out.println("Index token "+indexIdToken.get(ltid.get(0)));
							                		//System.out.println(Integer.valueOf((indexIdToken.get(ltid.get(0)))));
							                	     trmodel=new LetterListModel();
							                		for(int i=0;i<wsel.getLetters().size();i++){
								                		 trmodel.addElement(wsel.getLetters().get(i));
								                		}
							                		 tlist=new JList();
							                		tlist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
							                   		tlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
							                   		tlist.setCellRenderer( new MyLetterListRenderer());
							                   		tlist.setVisibleRowCount(1);
							                   		tlist.setFixedCellWidth(50);
							                   		tlist.setFont(etiopicText);
							                   		tlist.setModel(trmodel);
							                		
							                   		final JScrollPane ptlist= new JScrollPane(tlist);
							                		final ChildFrame tokeniseFrame=new ChildFrame("Tokenisation",mainFrameColor,WindowConstants.HIDE_ON_CLOSE);
							                	
							                final Container c =tokeniseFrame.getContentPane();
							                final JButton tokenA= new JButton("Tokenise");
							                tokenA.setEnabled(false);
							                final JButton tokenNew= new JButton("New Token");
							                final JButton tokenDel= new JButton("Delete Token");
							                final JCheckBox global = new JCheckBox("Global");
							                global.setEnabled(false);
							                final JButton cancel = new JButton("Cancel");
							                JLabel wordLabel=new JLabel("Graphic Unit");
							                final JPanel c1=new JPanel(new GridBagLayout());
							                GridBagConstraints gbc1=new GridBagConstraints();
							                gbc1.gridx=0;
					                		gbc1.weightx=100;
					                		gbc1.weighty=100;
					                		gbc1.gridy=0;
					                		gbc1.insets.left=2;
					                		gbc1.insets.right=2;
					                		gbc1.anchor=GridBagConstraints.CENTER;
					                 		gbc1.insets.top=2;
					                		gbc1.insets.bottom=2;
					                		gbc1.fill=GridBagConstraints.NONE;
					                		gbc1.gridwidth=3;
							                c1.add(wordLabel,gbc1);
							                gbc1.gridy=1;
							                c1.add(ptlist,gbc1);
							                gbc1.gridy=2;
							                gbc1.gridwidth=3;
							                gbc1.anchor=GridBagConstraints.NORTHWEST;
							                c1.add(tokenNew,gbc1);
							                gbc1.gridx=2;
							                gbc1.insets.right=0;
							                c1.add(tokenDel,gbc1);
							                gbc1.insets.right=2;
							                gbc1.gridy=3;
							                gbc1.gridwidth=1;
							                gbc1.gridx=0;
							                gbc1.anchor=GridBagConstraints.NORTHWEST;
							                c1.add(global,gbc1);
							                gbc1.insets.right=0;
							                gbc1.gridx=1;
							                c1.add(tokenA,gbc1);
							                gbc1.gridx=2;
							                c1.add(cancel,gbc1);
					                		c.add(c1);
					                		tokeniseFrame.pack();
					                		addChild(tokeniseFrame,100,100,550,200);
					                		tokeniseFrame.pack();
				   			               tokeniseFrame.setVisible(true);
				   			              tokeniseFrame.moveToFront();
				   			              posSel=new ArrayList<Integer>();
				   			              counttoken=1;
				   			           cancel.addActionListener(new ActionListener(){
				   			            	  public void actionPerformed(ActionEvent e1){
				   			            		  tokeniseFrame.doDefaultCloseAction();
				   			            	  }
				   			           });	  
				   			           tokenNew.addActionListener(new ActionListener(){
				   			            	  public void actionPerformed(ActionEvent e1){
				   			            		 int selLet= tlist.getSelectedIndex();
				   			            		   LatinLetterNode selL=(LatinLetterNode)trmodel.get(selLet);
				   			            		 if(selL.getLetter()=='-') 
				   			            			 JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" >Please select a token symbol</font></p></html>", "Dialog",
					   			  		       		        JOptionPane.ERROR_MESSAGE);
				   			            		 else{	 
				   			            		 LatinLetterNode temp=new LatinLetterNode('-',(byte)0,(byte)0,null);
				   			            		 
				   			            		 if (selLet<trmodel.size()-1){
				   			            			 if(((LatinLetterNode)trmodel.get(selLet+1)).getLetter()!='-'){
				   			            				
				   			        	               trmodel.add(selLet+1, temp);
				   			        	              
				   			        	          
				   			        	           // System.out.println("Added "+ (selLet-counttokenpre+1));
				   			        	      counttoken=counttoken+1;
					   			            		if (counttoken>=1) {tokenA.setEnabled(true);global.setEnabled(true);}
					   			            	 tlist.repaint();
				   			            		 tlist.revalidate();
				   			            			 }  
				   			            			else JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" >You already tokenised</font></p></html>", "Dialog",
					   			  		       		        JOptionPane.ERROR_MESSAGE);
				   			                       }
				   			            		 
				   			            		 else  {
				   			            			 if(((LatinLetterNode)trmodel.get(selLet-1)).getLetter()!='-'){
				   			            				
				   			            			 trmodel.add(selLet,temp);
				   			            			//counttoken=counttoken+1;
				   			            			
				   			            			counttoken=counttoken+1;
					   			            		if (counttoken>=1) {tokenA.setEnabled(true);global.setEnabled(true);}
					   			            	 tlist.repaint();
				   			            		 tlist.revalidate();
				   			            			//System.out.println("Added "+ (selLet-counttoken));
				   			            			 }
				   			            			 else JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" >You already tokenised</font></p></html>", "Dialog",
						   			  		       		        JOptionPane.ERROR_MESSAGE);
				   			            		 }
				   			            		
				   			            		tokeniseFrame.pack();
				   			            		
				   			            	  } 
				   			            	  }
				   			              });
				   			        tokenDel.addActionListener(new ActionListener(){
			   			            	  public void actionPerformed(ActionEvent e1){
			   			            		 int selLet= tlist.getSelectedIndex();
			   			            		counttokenpre=0;
			   			            		
			   			            		 char c=((LatinLetterNode)trmodel.get(selLet)).getLetter();
			   			            		 if (c=='-'){
			   			            			 trmodel.removeElementAt(selLet);
			   			            			 
			                                   //  System.out.println("Deleted " + selLet);
			                        
			   			            		
			   			            			counttoken=counttoken-1;
			   			            			 if (counttoken==0) {tokenA.setEnabled(false);global.setEnabled(false);}
			   			            			tokeniseFrame.pack();
				   			            		 tlist.repaint();
				   			            		 tlist.revalidate();
			   			            		 }
			   			            		 else 
			   			            			JOptionPane.showMessageDialog(new JFrame(), "<html><p><font size=+1 face=\"Ethiopic Unicode\" >Please select a token sepprator symbol</font></p></html>", "Dialog",
			   			  		       		        JOptionPane.ERROR_MESSAGE);
			   			            	
			   			            		 
			   			            	  }
			   			              });
				   			              tokenA.addActionListener(new ActionListener(){
				   			            	  public void actionPerformed(ActionEvent e1){
				   			            		 wordsBackup.clear();tokensBackup.clear();
				   			            		for(int i=0;i<words.size();i++){
				   			            	    	wordsBackup.add(words.get(i).copyWortNode());
				   			            	    }
				   			            	  for(int i=0;i<tokens.size();i++){
				   			            		//    System.out.println("Token "+tokens.get(i).getLabel());
				   			            	    	tokensBackup.add(tokens.get(i).copyToken());
				   			            	    }
				   			            	  undoMenu.setEnabled(true);
				   			            		ArrayList<LatinLetterNode> l;
				   			            		ArrayList<String> newt=new ArrayList<String>();
				   			            		ArrayList<String> globalSel=new ArrayList<String>();
				   			            		if(global.isSelected()){
				   			            			//System.out.println("Look for "+wsel.getFidalInternLabel()+"*"+wsel.getTranslitLabel(typScript));
				   			            			//System.out.println(" no repl "+ index.get(wsel.getFidalInternLabel()+"*"+wsel.getTranslitLabel(typScript)).size());
				   			            			for(int i=0;i<index.get(wsel.getFidalInternLabel(typScript,typDoc)+"*"+wsel.getTranslitLabel(typScript)).size();i++)
				   			            		     globalSel.add(index.get(wsel.getFidalInternLabel(typScript,typDoc)+"*"+wsel.getTranslitLabel(typScript)).get(i));
				   			            			}
				   			            		     
				   			            		else {globalSel=new ArrayList<String>(); globalSel.add(wid);}
				   			                    String wref=wid;
				   			            		//System.out.println("Global "+globalSel.size());
				   			            		
				   			            		int lung=0;
				   			            		//posSel.add(new Integer(0));
				   			            		int posi=0;
				   			            		for(int i=0;i<trmodel.size();i++){
				   			            			if (((LatinLetterNode)trmodel.get(i)).getLetter()=='-')
				   			            				{posSel.add(new Integer(lung));lung=0;posi=i;}
				   			            			else lung=lung+1;
				   			            		}
				   			            		posSel.add(new Integer(trmodel.getSize()-1-posi));
				   			            		
				   			            	//	int finish= wsel=words.get(indexIdWord.get(globalSel.get(0)).intValue()).size();
				   			            for(int k=0;k<globalSel.size();k++)	{
				   			            	//System.out.println("k "+ k);
				   			            	    wsel=words.get(indexIdWord.get(globalSel.get(k)).intValue());
				   			            	    wid=wsel.getId();
				   			            	    tsel=tokens.get(indexIdToken.get(wsel.getTokenIds().get(0)).intValue());
				   			            	    ltid=tsel.getId();
				   			            	 String oldkey=wsel.getFidalInternLabel(typScript,typDoc)+"*"+wsel.getTranslitLabel(typScript);
				   			            	    if(index.containsKey(oldkey)){
			   			            		    	index.get(oldkey).remove(wsel.getId());
			   			            		    //	System.out.println("Remain still "+index.get(wsel.getFidalInternLabel()+"*"+wsel.getTranslitLabel(typScript)).size()+" elem" );
			   			            		    	if (index.get(oldkey).size()==0)
			   			            		    		index.remove(oldkey);
				   			            		}
				   			            	   // else System.out.println("No key contained");
				   			            		
				   			            		//wsel.getTokenIds().clear();
				   			            	    wsel.setTokenIds(new ArrayList<String>());
				   			            		//System.out.println("No tokens "+ counttoken);
				   			            		int begin=0;int end=0;
				   			            		for(int i=0;i<counttoken;i++){
				   			            			
				   			            			if(i==0) 
				   			            				{begin=0; end=posSel.get(i).intValue();}
				   			            			else if(i==counttoken-1){
				   			            				begin=end; end=wsel.getLetters().size();
				   			            			}
				   			            			else {
				   			            				begin=end; end=begin+posSel.get(i).intValue();
				   			            			}
				   			            			//System.out.println(i + " "+begin+" "+end+" "+posSel.get(i));
				   			            			l=new ArrayList<LatinLetterNode>();
				   			            		//	System.out.println("Begin "+begin+ " End "+end);
				   			            			for(int j=begin;j<end;j++){
				   			            				wsel.modifyTokenID(j, "T"+i+">"+wid);
				   			            				l.add(wsel.getLetters().get(j));
				   			            				/*wsel.getLetters().get(j).setTokenId("T"+i+"-"+wid);*/
				   			            			}
				   			            			
				   			            			Token t1;
				   			            			if (wid.equals(wref)) {t1=new Token("T"+i+">"+wid,false,l);
				   			            				wsel.setAutomToken(false);
				   			            				}
				   			            			else {t1=new Token("T"+i+">"+wid,true,l);wsel.setAutomToken(true);}
				   			            			wsel.getTokenIds().add(t1.getId());
				   			            			if((indexIdToken.get(ltid).intValue()+i)<tokens.size())
				   			            			tokens.add(indexIdToken.get(ltid).intValue()+i,t1);
				   			            			else tokens.add(t1);
				   			            		//	System.out.println(t.getLabel());
				   			            		    indexIdToken.put(t1.getId(), indexIdToken.get(ltid).intValue()+i);
				   			            		    String valt=t1.getLabel();
				   			            		    if(indexT.containsKey(valt)){
				   			            		    	indexT.get(valt).add(t1.getId());
				   			            		    }
				   			            		    else {
				   			            		    	newt=new ArrayList<String>();
				   			            		    	newt.add(t1.getId());
				   			            		    	indexT.put(valt, newt);
				   			            		    }
				   			            			
				   			            		}
				   			            		
				   			            		indexIdToken.remove(tsel.getLabel());
				   			            		
				   			            		tokens.remove(tsel);
				   			            		createIndexTokens();
				   			            	//	System.out.println("Look for "+ tsel.getLabel());
				   			            		if(indexT.containsKey(tsel.getLabel())){
			   			            		    	indexT.get(tsel.getLabel()).remove(tsel .getId());
			   			            		  //  	System.out.println("Remove "+ tsel.getId());
			   			            		    	if( indexT.get(tsel.getLabel()).size()==0) indexT.remove(tsel.getLabel());
			   			            		    }
			   			            		    else indexT.remove(tsel.getLabel());
				   			            		//System.out.println("New label "+wsel.getFidalInternLabel()+"*"+wsel.getTranslitLabel(typScript));
				   			            	 String newkey=wsel.getFidalInternLabel(typScript,typDoc)+"*"+wsel.getTranslitLabel(typScript);
				   			            		if(index.containsKey(newkey)){
				   			            			
			   			            		    	index.get(newkey).add(wsel.getId());
			   			            		    	//System.out.println("Index contains");
				   			            		}
				   			            		else{
				   			            		    ArrayList<String> aux= new ArrayList<String>();
				   			            		    aux.add(wsel.getId());
				   			            		 //System.out.println("Index does not contain");
				   			            			index.put(newkey, aux);
				   			            		}

				   			            	//	modelTranslit.set(indexIdWord.get(wid), wsel);
				   			            		//System.out.println("updated model "+ wid);
				   			            		words.set(indexIdWord.get(wid), wsel);
				   			            		//System.out.println("updated word id index");
				   			            		//System.out.println ("Global sel "+ globalSel.size());
				   			            	  }
				   			            		translitWordList.revalidate();
				   			            		translitWordList.repaint();
				   			            		tokeniseFrame.doDefaultCloseAction();
				   			            	  }
				   			              });
							                	}
							                });	
							                
		//					                
							                
							                
							                translitWordList.addMouseListener(new MouseAdapter() {
							                    public void mouseClicked(MouseEvent me) {
							                       if (SwingUtilities.isRightMouseButton(me)    // if right mouse button clicked
							                             && !translitWordList.isSelectionEmpty()            // and list selection is not empty
							                             && (translitWordList.locationToIndex(me.getPoint()) // and clicked point is
							                                >= translitWordList.getSelectedIndices()[0])
							                               && ( translitWordList.locationToIndex(me.getPoint()) // and clicked point is
									                                <= translitWordList.getSelectedIndices()[translitWordList.getSelectedIndices().length-1]) 		
							                    		   ) {       //   itnside selected item bounds
							                    	   popupMenu.show(translitWordList, me.getX(), me.getY());
							                       }
							                       else if (SwingUtilities.isLeftMouseButton(me) && !translitWordList.isSelectionEmpty()            // and list selection is not empty
								                             && translitWordList.locationToIndex(me.getPoint()) // and clicked point is
								                                == translitWordList.getSelectedIndex()){
							                    	   int inde=translitWordList.getSelectedIndex();
							                    	   WortNode w=words.get(inde);
							                    	  // translitWordList.setToolTipText(((WortNode)translitWordList.getModel().getElementAt(inde)).getCooment());
							                    	   String annotation ="";
							                    	   for(int i=0; i<w.getTokenIds().size();i++){
							                    		   Token temp=tokens.get(indexIdToken.get(w.getTokenIds().get(i)).intValue());
							                    		   if (temp.getMorphoAnnotation()!=null){
							                    			   annotation=annotation+temp.getLabel()+"<br/>";
							                    			   Annotation atemp=temp.getMorphoAnnotation();
							                    			   annotation=annotation+atemp.getListTag().get(0).getName()+" "+atemp.getEntireMorphoValue1()+"<br/>";
							                    			 //  System.out.println("Annotation "+ annotation);
							                    		   }
							                    	   }
							                    	   
							                    	  // if(w.getFlagMorphoAnnot()>0) showPositionText.setText(w.getTextMorphoAnnot());
							                    	   showAnnotationText.setText(annotation);
							                    	   showAnnotationText.setCaretPosition(0);
							                    	   
							                       }
							                      
							                    }
							                 });
						           }  
						            else {}
						            	//System.out.println("no");
						            }
						           
			    		 }           
		          });		
		 //         
	 
	 
	 
	 }
	 
	 public String verifyWordInIndex(int i, WortNode w){
		 String c="";
		 if(index.containsKey(w.getFidalInternLabel(typScript, typDoc)+"*"+w.getTranslitLabel(typScript))){
			 ArrayList<String> ls=index.get(w.getFidalInternLabel(typScript, typDoc)+"*"+w.getTranslitLabel(typScript));
			 boolean found=false; int j=0;
			 while((j<ls.size())&&(!found)){
				 if (ls.get(j).equals(w.getId())) found=true;
				 else j=j+1;
			 }
			 if (found) c=""+i;
			 else c="+"+i;
		 }
		 else c="-"+i;
		 return c;
	 }
	 
	 public String verifyTokensWordIndex(int i, WortNode w){
		ArrayList<String> lTid= w.getTokenIds();
		int j=0; boolean incorrect=false;
		boolean tc=false;
		while (j<lTid.size()&& (!incorrect)){
			if (indexT.containsKey(tokens.get((indexIdToken.get(lTid.get(j)).intValue())).getLabel())){
				ArrayList<String> lt=indexT.get(tokens.get((indexIdToken.get(lTid.get(j)).intValue())).getLabel());
				boolean tcc=false;int k=0;
				while(k<lt.size()&&(!tcc)){
					//if (lt.get(k).equals(lTid.get(j))) tcc=true;
					//System.out.println(lt.get(k));
					//System.out.println(w.getId());
					if (lt.get(k).indexOf(w.getId())>=0) tcc=true;
					else k=k+1;
					//System.out.println(k);
				}
			 
					tc=tc|tcc;
				j=j+1;
			}
			else incorrect=true;
		}
		if(incorrect){  
			//System.out.println("+"+j);
			return "+"+j;
			}
		else if(!tc) { 
			//System.out.println("-"+j);
			return "-"+j;
			}
		else {
			//System.out.println(""+j); 
			return ""+j;
			}
	 }
	 //
	 public boolean conditionsDivision(int level, int start, int end){
		 WortNode ws=words.get(start);
		 WortNode we=words.get(end);
		 if( (start>0)&&(end<words.size()-1)){
			 WortNode wpred=words.get(start-1);
			 WortNode wsucc=words.get(end+1);
			 if ((wpred.getStrukturIds()!=null) && (wsucc.getStrukturIds()!=null)){
				 
			 }
		 }
		 else if((start==0)&&(end<words.size()-1)){
			
			 WortNode wsucc=words.get(end+1);
		 }
         else if((start>0)&&(end==words.size()-1)){
        	 WortNode wpred=words.get(start-1);
			 
			 
		 }
         else if((start==0)&&(end==words.size()-1)){
			 
		 }
		 return false;
	 }
	 //
	 public Division findDivisionId(String id){
		 Division s=null;
		 int i=0; boolean found=false;
		 while ((i<divisions.size())&&(!found)){
			 if (divisions.get(i).getId().equals(id)) {
				 s=divisions.get(i);
				 found=true;
			 }
			 else i=i+1;
		 }
		 return s;
	 }
	 
	 //
	 
	 public void createStructure(String s,String n){
		 WortNode w=new WortNode(n);
		 ArrayList<LatinLetterNode> let=new ArrayList<LatinLetterNode>();
		 if(s.charAt(0)!='S'){ 
			 char e=s.charAt(s.length()-1);
			// if(typScript==1)e=s.charAt(0);
			 int m=s.length();
			// System.out.println("Lenght "+m);
			 if ((e=='\u1361')) m=s.length()-1;
			 int i=0;
			 int limit;
			 int limit1;
			 if(typDoc==0) limit1=1;
			 else limit1=2;
			 int noChild=0;
		while(i<m){
			//System.out.println("i= "+i);
			FidalNode f;
			LatinLetterNode l;
			if(i+2<=m) limit=i+2;
			else limit=m;
		    
			 if (i==m-limit1){
				 if((e=='\u1361'))
                    f=new FidalNode(w,i,true,"\u1361");
				 else  f=new FidalNode(w,i,false,"");
			 }
			 else f=new FidalNode(w,i,false,"");
			 String tr="";
			 if(typScript==0)
			   tr=geez.geezTranslit.get(""+s.charAt(i));
			 else {
				 tr=geez.suedArabTranslit.get(s.substring(i,limit));
			 }
			 //System.out.println( "orig " + s.substring(i,limit) +" tr "+tr);
			 //if it is a number
			 if(geez.specialSymbols.contains(""+s.charAt(i))){
				 l=new LatinLetterNode(tr.charAt(0),(byte)0,(byte)1,f);
				 f.addChild(0,l);
			 }	
			 else if (geez.suedArabSymbols.contains(s.substring(i,limit))){
				// System.out.println("built "+ tr.charAt(0));
				 l=new LatinLetterNode(tr.charAt(0),(byte)0,(byte)1,f);
				 f.addChild(0,l);
				//System.out.println("Added child");
			 }
			 else if ((s.charAt(i)>='\u1369')&&(s.charAt(i)<='\u137C')){
				 //1..9
				 if(s.charAt(i)<='\u1371'){
					 l=new LatinLetterNode(tr.charAt(0),(byte)0,(byte)1,f);
					 f.addChild(0,l);
				 }
				 //10..90
				 else if(s.charAt(i)<='\u137A'){
					 l=new LatinLetterNode(tr.charAt(0),(byte)2,(byte)1,f);
					 f.addChild(0,l);
				 }	
				 //100
				 else if(s.charAt(i)=='\u137B'){
					 l=new LatinLetterNode(tr.charAt(0),(byte)3,(byte)1,f);
					 f.addChild(0,l);
				 }
				 //10000
				 else{
					 l=new LatinLetterNode(tr.charAt(0),(byte)4,(byte)1,f);
					 f.addChild(0,l);
				 }
			 }
			 else{
				 if (tr.length()==2){
					 // if labiovelar 6. order intern
					 if ((tr.charAt(1)=='\u02b7')&&(i<m-1)){
						 l=new LatinLetterNode(tr.charAt(0),(byte)1,(byte)1,f);
						 f.addChild(0,l);
						 if(typDoc==0){
						 l=new LatinLetterNode('\u01dd',(byte)0,(byte)2,f);
						 f.addChild(1,l);
						 }
					 }
					 //if labiovelar 6.order last
					 else if((tr.charAt(1)=='\u02b7')&&(i==m-1)){
						 l=new LatinLetterNode(tr.charAt(0),(byte)1,(byte)1,f);
						 f.addChild(0,l);
					 }
					 //normal konsonante+vokale
					 else{
						 l=new LatinLetterNode(tr.charAt(0),(byte)0,(byte)1,f);
						 f.addChild(0,l);
						 if(typDoc==0){
						 l=new LatinLetterNode(tr.charAt(1),(byte)0,(byte)2,f);
						 f.addChild(1,l);
						 }
					 }
				 }
				 //labiovelar Vokale
				 else  if(tr.length()==3){
					 if(i<s.length()-1){
					 l=new LatinLetterNode(tr.charAt(0),(byte)1,(byte)1,f);
					 f.addChild(0,l);{
					 if(typDoc==0)
					 l=new LatinLetterNode(tr.charAt(2),(byte)0,(byte)2,f);
					 f.addChild(1,l);}
					 }
					 else {
						 l=new LatinLetterNode(tr.charAt(0),(byte)1,(byte)1,f);
						 f.addChild(0,l);
						
					 }
				 }
				 else if(tr.length()==1){
					 //6.order intern
					 if(i<m-1){
						 l=new LatinLetterNode(tr.charAt(0),(byte)0,(byte)1,f);
						 f.addChild(0,l);
						 if(typDoc==0){
						 l=new LatinLetterNode('\u01dd',(byte)0,(byte)2,f);
						 f.addChild(1,l); 
					 }
					 }
					 //6.order last
					 else{
						 l=new LatinLetterNode(tr.charAt(0),(byte)0,(byte)1,f);
						 f.addChild(0,l);
					 }
				 }
			 }
			 
			 w.addChild(noChild, f);
			 noChild=noChild+1;
			 if(typScript==0)
			 i=i+1;
			 else i=i+2;
		 }
		 }
		 //System.out.println("Finisched built");
		 ArrayList<String> tid=new ArrayList<String>();
		 tid.add("T0>"+w.getId());
		 w.setTokenIds(tid);
		 w.setAutomToken(false);
		 let=new ArrayList<LatinLetterNode>();
		 for(int i=0;i<w.getFidalChildren().size();i++){
			 for(int j=0;j<w.getFidalChildren().get(i).getTranslitChildren().size();j++){
				 let.add(w.getFidalChildren().get(i).getTranslitChildren().get(j));
			       w.getFidalChildren().get(i).getTranslitChildren().get(j).setTokenId("T0>"+w.getId());
			 }
		 }
		 words.add(w);
		 wordsCopy.add(w);
 		 tokens.add(new Token("T0>"+w.getId(),false,let));
 		 //System.out.println("Finished Word");
	 }
	 
	 
	 public WortNode createStructureWord(String s,String n){
		 WortNode w=new WortNode(n);
		 ArrayList<LatinLetterNode> let=new ArrayList<LatinLetterNode>();
		 if(s.charAt(0)!='S'){ 
			 char e=s.charAt(s.length()-1);
			 int m=s.length();
			 if (e=='\u1361') m=s.length()-1;
		 for(int i=0;i<m;i++){
			FidalNode f;
			LatinLetterNode l;
			 if (i==m-1){
				 if(e=='\u1361')
                    f=new FidalNode(w,i,true,"\u1361");
				 else  f=new FidalNode(w,i,false,"");
			 }
			 else f=new FidalNode(w,i,false,"");
			 String tr=geez.geezTranslit.get(""+s.charAt(i));
			 //if it is a number
			 if(geez.specialSymbols.contains(""+s.charAt(i))){
				 l=new LatinLetterNode(tr.charAt(0),(byte)0,(byte)1,f);
				 f.addChild(0,l);
			 }		 
			 else if ((s.charAt(i)>='\u1369')&&(s.charAt(i)<='\u137C')){
				 //1..9
				 if(s.charAt(i)<='\u1371'){
					 l=new LatinLetterNode(tr.charAt(0),(byte)0,(byte)1,f);
					 f.addChild(0,l);
				 }
				 //10..90
				 else if(s.charAt(i)<='\u137A'){
					 l=new LatinLetterNode(tr.charAt(0),(byte)2,(byte)1,f);
					 f.addChild(0,l);
				 }	
				 //100
				 else if(s.charAt(i)=='\u137B'){
					 l=new LatinLetterNode(tr.charAt(0),(byte)3,(byte)1,f);
					 f.addChild(0,l);
				 }
				 //10000
				 else{
					 l=new LatinLetterNode(tr.charAt(0),(byte)4,(byte)1,f);
					 f.addChild(0,l);
				 }
			 }
			 else{
				 if (tr.length()==2){
					 // if labiovelar 6. order intern
					 if ((tr.charAt(1)=='\u02b7')&&(i<s.length()-1)){
						 l=new LatinLetterNode(tr.charAt(0),(byte)1,(byte)1,f);
						 f.addChild(0,l);
						 if(typDoc==0){
						 l=new LatinLetterNode('\u01dd',(byte)0,(byte)2,f);
						 f.addChild(1,l);
						 }
					 }
					 //if labiovelar 6.order last
					 else if((tr.charAt(1)=='\u02b7')&&(i==s.length()-1)){
						 l=new LatinLetterNode(tr.charAt(0),(byte)1,(byte)1,f);
						 f.addChild(0,l);
					 }
					 //normal konsonante+vokale
					 else{
						 l=new LatinLetterNode(tr.charAt(0),(byte)0,(byte)1,f);
						 f.addChild(0,l);
						 if(typDoc==0){
						 l=new LatinLetterNode(tr.charAt(1),(byte)0,(byte)2,f);
						 f.addChild(1,l);
						 }
					 }
				 }
				 //labiovelar Vokale
				 else  if(tr.length()==3){
					 l=new LatinLetterNode(tr.charAt(0),(byte)1,(byte)1,f);
					 f.addChild(0,l);
					 if(typDoc==0){
					 l=new LatinLetterNode(tr.charAt(2),(byte)0,(byte)2,f);
					 f.addChild(1,l);
					 }
				 }
				 else if(tr.length()==1){
					 //6.order intern
					 if(i<m-1){
						 l=new LatinLetterNode(tr.charAt(0),(byte)0,(byte)1,f);
						 f.addChild(0,l);
						 if(typDoc==0){
						 l=new LatinLetterNode('\u01dd',(byte)0,(byte)2,f);
						 f.addChild(1,l); 
						 }
					 }
					 //6.order last
					 else{
						 l=new LatinLetterNode(tr.charAt(0),(byte)0,(byte)1,f);
						 f.addChild(0,l);
					 }
				 }
			 }
			 w.addChild(i, f);    
		 }
		 }
		 ArrayList<String> tid=new ArrayList<String>();
		 tid.add("T0>"+w.getId());
		 w.setTokenIds(tid);
		 w.setAutomToken(false);
		 let=new ArrayList<LatinLetterNode>();
		 for(int i=0;i<w.getFidalChildren().size();i++){
			 for(int j=0;j<w.getFidalChildren().get(i).getTranslitChildren().size();j++){
				 let.add(w.getFidalChildren().get(i).getTranslitChildren().get(j));
			       w.getFidalChildren().get(i).getTranslitChildren().get(j).setTokenId("T0>"+w.getId());
			 }
		 }
       return w;
	 }
	 
	 //
	 //preprocessing Fidal
	 public String fidalClean(String s){
 		String val=s;
 		if (val.charAt(0)=='\uFEFF')val=val.substring(1);
 		val=val.replaceAll("\\s+", " ").trim();
 		if((val.indexOf("\u1360")>0))
 			
 			val=val.replaceAll("(?:\u1360)", "\u1362");
         if((val.indexOf("\u1368")>0))
 			
 			val=val.replaceAll("(?:\u1368)", "\u1362");
        if((val.indexOf("\u1363")>0))
 			
 			val=val.replaceAll("(?:\u1363)", "\u1361");
        if((val.indexOf("\u1364")>0))
 			
 			val=val.replaceAll("(?:\u1364)", "\u1361");
         if((val.indexOf("\u1365")>0))
 			
 			val=val.replaceAll("(?:\u1365)", "\u1361");
          if((val.indexOf("\u1366")>0))
 			
 			val=val.replaceAll("(?:\u1366)", "\u1361");
          if((val.indexOf("\u1367")>0))
  			
  			val=val.replaceAll("(?:\u13670)", "\u1361");
          if((val.indexOf(" \u1361")>0))
      		
  			val=val.replaceAll("(?: \u1361)", "\u1361");
          if((val.indexOf(" \u1362")>0))
       		
   			val=val.replaceAll("(?: \u1362)", "\u1362");
 		if((val.indexOf("\u1361\u1361")>0))
 		
 			val=val.replaceAll("(?:\u1361\u1361)", "\u1361");
 		if ((val.indexOf("\u1361\u1362")>0))
 			val=val.replaceAll("(?:\u1361\u1362)", "\u1361");
 			
 		return val;
 	}
	 
	 public void initializePositionWindows(){
		  showAnnotationX=780;
		  showAnnotationY=10;
		/*  errorWarningX
		  errorWarningY
		  defLevelWindowX
		  defLevelWindowY
			 fidalTextX
			 fidalTextY
			 translitTextX
			 translitTextY
			 showPositionX
			 showPositionY
			 strAnnFrameX
			 strAnnFrameY;
			structFrameX
			structFrameY
			editionFrameX
			editionFrameY
			modifyFrameX
			modifyFrameY
			neFrameX
			neFrameY
			 commFrameX
			 commFrameY
			 colorFrameX
			 colorFrameY
			 insertFrameX
			 insertFrameY
			 deleteAnnotFrameX
			 deleteAnnotFrameY
			 lingAnnotFrameX
			 lingAnnotFrameY
			 deepFrameX
			 deepFrameY
			 posFrameX
			 posFrameY
			 deleteTokenFrameX
			 deleteTokenFrameY
			 showTokenisedX
			 showTokenisedY
			 tokeniseFrameX
			 tokeniseFrameY
			 searchWX
			 searchWY*/
	 }
	 //Searching Functionality
	 
	 public void showSearchWindow(){
			ChildFrame searchW=new ChildFrame("Search", Color.gray,WindowConstants.DISPOSE_ON_CLOSE);
			searchW.setSize(700,600);
			Container cs= searchW.getContentPane();
			JPanel c =new JPanel();
			
			c.setLayout(new GridBagLayout());
			GridBagConstraints gbc=new GridBagConstraints();
			gbc.gridx=0;
			gbc.gridy=0;
			gbc.insets.left=2;
			gbc.insets.top=2;
			gbc.insets.bottom=2;
			gbc.insets.right=2;
			gbc.weighty=100.0;
			gbc.weightx=100.0;
			//gbc.fill=GridBagConstraints.BOTH;
			gbc.anchor=GridBagConstraints.NORTHWEST;
			JPanel searchPanel=new JPanel();
			searchPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc1=new GridBagConstraints();
			gbc1.gridx=0;
			gbc1.gridy=0;
			gbc1.insets.left=2;
			gbc1.insets.top=2;
			gbc1.insets.bottom=2;
			gbc1.insets.right=2;
			gbc1.weighty=100.0;
			gbc1.weightx=100.0;
			gbc.fill=GridBagConstraints.BOTH;
			gbc1.anchor=GridBagConstraints.CENTER;
			gbc1.gridwidth=1;
			gbc1.gridheight=1;
			JLabel searchLabel=new JLabel("Search word");
			final JTextField searchField=new JTextField(20);
			searchField.setFont(typFont);
			searchPanel.add(searchLabel,gbc1);
			JLabel contextL=new JLabel("Context");
			final JTextField context=new JTextField(2);
			gbc1.gridx=1;
			searchPanel.add(contextL,gbc1);
			final JComboBox posL=new JComboBox();
			posL.addItem("no PoS annotation");
			posL.addItem("PoS annotated with");
			posL.addItem("not annotated");
			posL.setSelectedIndex(0);
			
			gbc1.gridx=2;
			searchPanel.add(posL,gbc1);
			gbc1.gridx=0;
			gbc1.gridy=1;
			searchPanel.add(searchField,gbc1);
			gbc1.gridx=1;
			searchPanel.add(context,gbc1);
			gbc1.gridx=2;gbc1.gridheight=3;
			treePOS.clearSelection();
			final JScrollPane posPane=new JScrollPane(treePOS);
			posPane.setPreferredSize(new Dimension(300,200));
			posPane.setEnabled(false);
			searchPanel.add(posPane,gbc1);
			final JComboBox searchType=new JComboBox();
			searchType.addItem("contained in");
			searchType.addItem("identical");
			searchType.addItem("at the beginning");
			searchType.addItem("at the end");
			searchType.addItem("is token (only translit. input)");
			searchType.setSelectedIndex(0);
			gbc1.gridx=1;
	        gbc1.gridy=2;gbc1.gridheight=1;
	        searchPanel.add(searchType,gbc1);
	        final JComboBox searchPoint=new JComboBox();
	        searchPoint.addItem("global");
	        searchPoint.addItem("From selected index");
	        searchPoint.setSelectedIndex(0);
	        gbc1.gridx=0;
	        searchPanel.add(searchPoint,gbc1);
			  final JButton searchB=new JButton("search");
			  gbc1.gridx=0;
		        gbc1.gridy=3;gbc1.gridheight=1;
		        searchPanel.add(searchB,gbc1);
		        c.add(searchPanel,gbc);
		        gbc1.anchor=GridBagConstraints.CENTER;
			 searchPane=new JTextPane();
			searchPane.setSize(300, 200);
			  searchPane.setContentType("text/html");
		        searchPane.setEditorKit(new HTMLEditorKit());
		      searchPane.setEditable(false);
		      String Text="";
		         searchPane = new JEditorPane("text/html",Text);
		         searchPane.setFont(typFont);
		         String bodyRule = "body { font-family: " + typFont.getFamily() + "; " +
		                 "font-size: " + typFont.getSize() + "pt; }";
		         ((HTMLDocument)searchPane.getDocument()).getStyleSheet().addRule(bodyRule);
		         //searchPane.setEditable(false);
		      //   editor.set
		      
		       searchPane.setCaretPosition(0);
		       MyLinkController controller = new MyLinkController();
		        searchPane.addMouseListener(controller);
		        searchPane.addMouseListener(controller);
		       
		       //searchPane.setEnabled(false);
		        final  JScrollPane scrollEditorP=new JScrollPane(searchPane);
		        // scrollEditorP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		         scrollEditorP.setPreferredSize(new Dimension(600, 300));
		        scrollEditorP.getVerticalScrollBar().setValue(0);
		       
		        gbc.gridy=2;
		        	c.add(scrollEditorP,gbc);
			    cs.add(c);  
			        searchW.pack();
			        desk.add(searchW);
			        searchW.setVisible(true);
			        searchW.moveToFront();
			        selectedPos="";
			       
			        treePOS.addTreeSelectionListener(new TreeSelectionListener() {
			            public void valueChanged(TreeSelectionEvent e) {
			                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
			                                   treePOS.getLastSelectedPathComponent();

			            /* if nothing is selected */ 
			                if (node == null) return;
			              
			            /* retrieve the node that was selected */ 
			                Object nodeInfo = node.getUserObject(); 
			                 int x=posL.getSelectedIndex();
			                 if(x==0) 
			                	 //System.out.println("No POS");
			                	 {}
			                 else  {
			                	 
			                	 TreePath[] paths = treePOS.getSelectionPaths();
	                              // System.out.println(paths.length+ " selected");
			                     for (TreePath path : paths) {
			                        // System.out.println("You've selected: " + path.getLastPathComponent());
			                         selectedPos=path.getLastPathComponent().toString();
			                     }
			                    
			                	// System.out.println(nodeInfo);
			                 }
			            }
			        });
			searchB.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String wordS=searchField.getText();
					scrollEditorP.getVerticalScrollBar().setValue(0);
					char typeWord='l';
					char c=wordS.charAt(0);
					if (((c>='\u1200')&&(c<='\u1360'))||((c>='\u1369')&&(c<='\u137C'))||(c=='\u1362')) {
						if (wordS.indexOf("\u1361")>0) typeWord='s';
						else typeWord='f';
					}
					else if (wordS.indexOf("-")>0) typeWord='t';
					else typeWord='l';
		
				
				    	
				    	 JSONTokener tokener;
	               	  JSONObject word;
	               	
	               	WortNode t; 
	               	String foundS;String foundS1;String foundST;String foundST1;
	                String result="";int resfound=0;
	              //  System.out.println("Words "+modelTranslit.size());
	                
	                int typeS=searchType.getSelectedIndex();
	                char ts='c';
	                if (typeS==1) ts='i';
	                else if(typeS==2) ts='b';
	                else if(typeS==3) ts='e';
	                else if(typeS==4) ts='t';
	                int possel=posL.getSelectedIndex();
	                String windowC=context.getText();
		        	 int window=10;
		        	 boolean doIt=false;
		        	 if(!windowC.equals("")){
		        		 if (StringUtils.isNumeric(windowC)){
		        		    window=Integer.parseInt(windowC);doIt=true;
		        		 }
		        		 else JOptionPane.showMessageDialog(null, "Please insert a positive number");
		        		 
		        	 }
		        	 else doIt=true;
		        	 if(doIt){
		        		
		        		 int start=0;
		        		 if (searchPoint.getSelectedIndex()==1){
		        			 int selPosition=translitWordList.getSelectedIndex();
		        			 if (selPosition>0) start=selPosition;
		        		 }
		        		 
		        		 
	               	  for (int j=start;j<modelTranslit.size();j++){
	               		
	               		  t=words.get(j);
	               		 
	               		  foundS=t.getFidalInternLabel(typScript,typDoc); foundS1=t.getFidalLabel(typScript,typDoc);
	               		foundST=t.getTranslitInternLabel(typScript); foundST1=t.getTranslitLabel(typScript);
	               		//System.out.println(foundS1);
	               		String posFound="";
	               		boolean foundWord=false;
	               		if (typeWord=='s'){
	               			if(ts=='c'){
	               			if(foundS1.indexOf(wordS)>=0) foundWord=true;
	               			}
	               			else if(ts=='i'){
	               				if(foundS1.equals(wordS)) foundWord=true;
	               			}
	               			else if(ts=='b'){
	               				if(foundS1.indexOf(wordS)==0) foundWord=true;
	               			}
	               			else if(ts=='e'){
	               				if(foundS1.endsWith(wordS)) foundWord=true;
	               			}
	               			else if(ts=='t'){
	               				if(foundST1.endsWith("-"+wordS)||foundST1.equals(wordS)||(foundST1.indexOf(wordS+"-")==0)||(foundST1.indexOf("-"+wordS+"-")>0)) foundWord=true;
	               			}
	               		}
	               		else if(typeWord=='f'){
	               			if(ts=='c'){
	               			if((foundS.indexOf(wordS)>=0)||(foundS1.indexOf(wordS)>=0))foundWord=true;
	               			}
	               			else if(ts=='i'){
	               				if((foundS.equals(wordS))||(foundS1.equals(wordS)))foundWord=true;
	               			}
	               			else if(ts=='b'){
	               				if((foundS.indexOf(wordS)==0)||(foundS1.indexOf(wordS)==0))foundWord=true;
	               			}
	               			else if(ts=='e'){
	               				if((foundS.endsWith(wordS))||(foundS1.endsWith(wordS))) foundWord=true;
	               			}
	               			else if(ts=='t'){
	               				if(foundST1.endsWith("-"+wordS)||foundST1.equals(wordS)||(foundST1.indexOf(wordS+"-")==0)||(foundST1.indexOf("-"+wordS+"-")>0)) foundWord=true;
	               			}
	               		}
	               		else if (typeWord=='t'){
	               			if(ts=='c'){
	               			if(foundST1.indexOf(wordS)>=0) foundWord=true;
	               			}
	               			else if(ts=='i'){
	               				if(foundST1.equals(wordS)) foundWord=true;
	               			}
	               			else if(ts=='b'){
	               				if(foundST1.indexOf(wordS)==0) foundWord=true;
	               			}
	               			else if(ts=='e'){
	               				if(foundST1.endsWith(wordS)) foundWord=true;
	               			}
	               			else if(ts=='t'){
	               				if(foundST1.endsWith("-"+wordS)||foundST1.equals(wordS)||(foundST1.indexOf(wordS+"-")==0)||(foundST1.indexOf("-"+wordS+"-")>0)) foundWord=true;
	               			}
	               		}
	               		else if (typeWord=='l'){
	               			if(ts=='c'){
	               			if((foundST.indexOf(wordS)>=0)||(foundST1.indexOf(wordS)>=0))foundWord=true;
	               			}
	               			else if(ts=='i'){
	               				if((foundST.equals(wordS))||(foundST1.equals(wordS)))foundWord=true;
	               			}
	               			else if(ts=='b'){
	               				if((foundST.indexOf(wordS)==0)||(foundST1.indexOf(wordS)==0))foundWord=true;
	               			}
	               			else if(ts=='e'){
	               				if((foundST.endsWith(wordS))||(foundST1.endsWith(wordS)))foundWord=true;
	               			}
	               			else if(ts=='t'){
	               				if(foundST1.endsWith("-"+wordS)||foundST1.equals(wordS)||(foundST1.indexOf(wordS+"-")==0)||(foundST1.indexOf("-"+wordS+"-")>0)) foundWord=true;
	               			}
	               		}
	               		boolean found1=false;;
	               		if(possel==1){
	               			int k1=0;
	               			while((k1<t.getTokenIds().size())&&(!found1)){
	               				Token tt=tokens.get(indexIdToken.get(t.getTokenIds().get(k1)).intValue());
	               				if (tt.getMorphoAnnotation()!=null){
	               					if (tt.getMorphoAnnotation().getListTag().get(0).getName().equalsIgnoreCase(selectedPos)) found1=true;
	               					else k1=k1+1;
	               				}
	               				else k1=k1+1;
	               			}
	               		}
	               		else if(possel==2){
	               			int k1=0;
	               			while((k1<t.getTokenIds().size())&&(!found1)){
	               				Token tt=tokens.get(indexIdToken.get(t.getTokenIds().get(k1)).intValue());
	               				if (tt.getMorphoAnnotation()!=null){
	               					k1=k1+1;
	               				}
	               				else if(k1<t.getTokenIds().size()-1) k1=k1+1; 
	               				else found1=true;
	               			}
	               		}
	               		else found1=true;
	               		if((foundWord)&&(found1)){
	               			posFound =""+j;
	               			resfound=resfound+1;
	               			//if (result!=null)
	               		      ///result=result+"<br/><br/>"+foundST1;
	               			//else result=foundST1;
	               		//	System.out.println(posFound);
	               		
			        	 //System.out.println("Position in red " + posRed);
			        	 int anfang;
			        	 int ende;
			        	 
			        	 if ((j-window)>=0) {anfang=j-window;}
			        	 else anfang=0;
			        	 if((j+window)<words.size()){ende=j+window;}
			        	 else ende=modelTranslit.size()-1;
			        	 
			        	 int k=anfang;
			        	 while (k<=ende){
			        		 if(k<j){
			        			 t=words.get(k);
			               		 
			        			 result=result+"<font face='Ethiopic Unicode' color='black'>"+t.getTranslitLabel(typScript)+"</font>"+" ";
			        			 //System.out.println("Black 1" + Text + "**");
			        		 }
			        		 if(k==j){
			        			 t=words.get(k);
			               		  
			        			// result=result+"<font face='Ethiopic Unicode' color='red'>"+word.getString("TSL")+"</font>"+" ";
			        			// result=result+"<form><input type='submit' value="+ word.getString("TSL")+"></form>";
			               		result=result+"<a href=\"https://"+ j+"\">"+t.getTranslitLabel(typScript)+"</a> ";
			        			 //System.out.println("Red " + Text + "**");
			        		 }
			        		
			        		 if (k>j){
			        			 t=words.get(k);
			               		 
			        			 result=result+"<font face='Ethiopic Unicode' color='black'>"+t.getTranslitLabel(typScript)+"</font>"+" ";
			        			// System.out.println("Black 2" + Text + "**");
			        		 }
			        		 k++;
			        	 }
			        	 result=result+"<br/><br/>";
	               		
	               		}
				    }
	               	  if (resfound==0)  result=wordS+" does not occur";
	               	  else if (resfound==1) result= resfound + " <b>result found</b><br/><br/>"+result;
	               	  else result= resfound + " <b>results found</b><br/><br/>"+result;
	               	
	               	  searchPane.setText(result);
	               	  resfound=0;searchPane.setCaretPosition(0);
	                 treePOS.clearSelection();
		        	 }	  
				
				}
			});
			searchPane.addHyperlinkListener(new HyperlinkListener() {
			    public void hyperlinkUpdate(HyperlinkEvent e) {
			        if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			        	String s=e.getURL().toString().substring(8);
			        	int pos=Integer.parseInt(s);
			        	translitWordList.setSelectedIndex(pos);
			        	translitWordList.ensureIndexIsVisible(translitWordList.getSelectedIndex());
			         //  System.out.println("Event on "+ s);
			        }
			    }
			});
		}
	 
	 
	 public int getTypScript(){
		 return typScript;
	 }
	 public int getTypDoc(){
		 return typDoc;
	 }
	 
	 public boolean checkEthiopic(String s){
		 String s1=s;
		 for (int i=0;i<s.length();i++){
			 if (!geez.geezSymbols.contains(s.charAt(i)+"")&&!geez.specialSymbols.contains(s.charAt(i)+"")&&!geez.suedArabSymbols.contains(s.charAt(i)+""))
					 s1=s1.replace(s.charAt(i)+"","");
		 }
			if(s1.isEmpty()) return true;
			else return false;
		
	 }
	 public String cleanEthiopic(String s){
		 String s1=s;
		 for (int i=0;i<s.length();i++){
			 if (!geez.geezSymbols.contains(s.charAt(i)+"")&&!geez.specialSymbols.contains(s.charAt(i)+"")&&!geez.suedArabSymbols.contains(s.charAt(i)+""))
					 s1=s1.replace(s.charAt(i)+"","");
		 }
			return s1;
		
	 }
	 //
 	public void exitTool(){
 		if(isSaved) System.exit(0);
 		else{
 			int confirm= JOptionPane.showOptionDialog(desk,
 	                "Last changes are not saved. Save and then Exit? ",
 	                "Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION,
 	                JOptionPane.QUESTION_MESSAGE, null, null, null);
 			if (confirm == JOptionPane.YES_OPTION) {
 				if (saveFile.isEnabled()) saveToFile();
 				else saveAsToFile();
 				//System.exit(0);
 				return;
 			}
 			else if (confirm == JOptionPane.NO_OPTION)System.exit(0);
 		}

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



 public void windowClosing(WindowEvent event)
	{
		
		if(isSaved) System.exit(0);
		else{
			int confirm= JOptionPane.showOptionDialog(desk,
	                "Last changes are not saved. Save and then Exit? ",
	                "Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION,
	                JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (confirm == JOptionPane.YES_OPTION) {
				if (saveFile.isEnabled()) saveToFile();
 				else saveAsToFile();
 				//System.exit(0);
				//return;
			}
			else if (confirm == JOptionPane.NO_OPTION)System.exit(0);
		}

	
	}
 public void setCtrlAccelerator(JMenuItem mi, char acc){
		KeyStroke ks=KeyStroke.getKeyStroke(
				acc, Event.CTRL_MASK
				);
		mi.setAccelerator(ks);
	}
	private void mainWin_componentResized(ComponentEvent e)  
	{  
	//System.out.println("\nmainWin_componentResized(ComponentEvent e) called.");  
	// TODO: Add any handling code here  
	   
	}
	//isDivisonPossible(startSel,endSel,,divT.getSelectedIndex())
	public boolean isDivisionPossible(int startSel, int endSel, int selIndex) {
		boolean isPossib=true;
		if(words.get(startSel).getStrukturIds().size()>0) {
		 int j=0;
			while((j<words.get(startSel).getStrukturIds().size())&&(isPossib)) {
			   String indDiv1=words.get(startSel).getStrukturIds().get(j);
			  if (divisions.get(indexIdDivision.get(indDiv1).intValue()).getLevel()<selIndex) {
				  if(indexIdWord.get(divisions.get(indexIdDivision.get(indDiv1).intValue()).getWend()).intValue()>=endSel)
                          j=j+1;
				  else isPossib=false;
			  }
			  else if (divisions.get(indexIdDivision.get(indDiv1).intValue()).getLevel()>=selIndex) {
				  if(indexIdWord.get(divisions.get(indexIdDivision.get(indDiv1).intValue()).getWend()).intValue()<=endSel)
                      j=j+1;
			  else isPossib=false;
		  }
			  else isPossib=false;
			}	
		
		}
		else if (words.get(endSel).getStrukturIds().size()>0) isPossib=false;
		
		boolean isPossib1=true;
		if(words.get(endSel).getStrukturIds().size()>0) {
			int  j=0;
			while((j>words.get(endSel).getStrukturIds().size())&&(isPossib1)) {
			  String  indDiv1=words.get(endSel).getStrukturIds().get(j);
			  if (divisions.get(indexIdDivision.get(indDiv1).intValue()).getLevel()<selIndex) {
				  if(indexIdWord.get(divisions.get(indexIdDivision.get(indDiv1).intValue()).getWbegin()).intValue()<=endSel)
                          j=j+1;
				  else isPossib1=false;
			  }
			  else if (divisions.get(indexIdDivision.get(indDiv1).intValue()).getLevel()>=selIndex) {
				  if(indexIdWord.get(divisions.get(indexIdDivision.get(indDiv1).intValue()).getWbegin()).intValue()>=endSel)
                      j=j+1;
			  else isPossib1=false;
		  }
			  else isPossib1=false;
			}	
		
		}
		else if (words.get(startSel).getStrukturIds().size()>0) isPossib1=false;
	return (isPossib1 && isPossib);
	}
	 public String updateErrors(){
		 String fehlern="";
		 for (int i=0;i<words.size();i++){
			 
	        if (words.get(i).getError()>0){
	    	 int pos1=indexIdWord.get(words.get(i).getId()).intValue();
		       String fehler="<a href=\"https://"+ pos1+"\">"+words.get(i).getTranslitLabel(typScript)+"</a><br/> ";
  	          // System.out.println(fehler);      
		       fehlern=fehlern+fehler;
  	
  	                 }
        
	}
	return fehlern;	
	 }
	
	public void addChild(JInternalFrame child, int x, int y, int w, int h){
	 child.setLocation(x,y);
	 child.setSize(w,h);
	 child.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 desk.add(child);
	 child.setVisible(true);
	}
	public void addChild1(JInternalFrame child, int x, int y){
		 child.setLocation(x,y);
		
		 child.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 desk.add(child);
		 child.setVisible(true);
		}

	public void addChild(JInternalFrame child, int x, int y){
		 child.setLocation(x,y);
		 //child.setSize(w,h);
		 child.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 desk.add(child);
		 child.setVisible(true);
		}

	 
	 class WindowClosingAdapter extends WindowAdapter
		{
		private boolean exitSystem;
		 
		/**
		  * Erzeugt einen WindowClosingAdapter zum Schliessen
		  * des Fensters. Ist exitSystem true, wird das komplette
		  * Programm beendet.
		  */
		public WindowClosingAdapter(boolean exitSystem)
		{
		this.exitSystem = exitSystem;
		}
		 
		/**
		  * Erzeugt einen WindowClosingAdapter zum Schliessen
		  * des Fensters. Das Programm wird nicht beendet.
		  */
		public WindowClosingAdapter()
		{
			this(true);
		}
		 
		public void windowClosing(WindowEvent event)
		{
			
			if(isSaved) System.exit(0);
			else{
				int confirm= JOptionPane.showOptionDialog(desk,
		                "Last changes are not saved. Save and then Exit? ",
		                "Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION,
		                JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == JOptionPane.YES_OPTION) {
					if (saveFile.isEnabled()) saveToFile();
	 				else saveAsToFile();
				}
				else if (confirm == JOptionPane.NO_OPTION)System.exit(0);
			}

		
		}
		//

		
		}
	 
	 //Cell Renderer
	 class MyListRenderer extends DefaultListCellRenderer
	    {
		 private HashMap theChosen=new HashMap();
		    
	  
	        public Component getListCellRendererComponent( JList list,
	                Object value, int index, boolean isSelected,
	                boolean cellHasFocus )
	        {
	            Component c=super.getListCellRendererComponent( list, value, index,
	                    isSelected, cellHasFocus );
	//System.out.println("CEll "+value);
	            String s=""+value;
	           
                WortNode w= words.get(index);
               // ArrayList<DivLevel> d=w.getStructureAnnot().getDivisions(); 
             //   c.setFont(new Font("Ethiopic Unicode", Font.PLAIN,14));
               
               // if(w.getFcolor()!=null)
                 //    c.setBackground(Color.WHITE);
                Border bord1 =BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED);
	              Border bord2 =BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
	              Border lineSpace =BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE);
	              CompoundBorder bord12 = new CompoundBorder(new CompoundBorder( bord1, lineSpace), bord2);
	              char ch=verifyWordInIndex(index,w).charAt(0);
                if((!Character.isDigit(ch))||(!Character.isDigit(verifyTokensWordIndex(index,w).charAt(0)))){
	      	        if (!w.hasErrorNode(typScript,typDoc))w.setError(1);
	      	        else w.setError(2);
	          }
                else if (w.hasErrorNode(typScript,typDoc))w.setError(3);
                else w.setError(0);
	         // System.out.println( "Model "+s);
	            String temp=list.getModel().getClass()+"";
	          //  System.out.println( temp);
	            //if(temp.equals("WortListModel")) list.
	            Border empty = BorderFactory.createEmptyBorder(0, 0, 0, -1);
	            //Border fullDiv1 = BorderFactory.createDashedBorder(null, 5, 5);
	            Border lineDiv1o=BorderFactory.createMatteBorder(2, 2, 2, -1, Color.BLUE);
	           // Border lineDiv1 = new CompoundBorder(empty, fullDiv1);
	            Border linePartLing =BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY);
	           // Border lineDiv1 =BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE);
	            Border lineDiv2o =BorderFactory.createMatteBorder(2, 2, 2, -1, Color.RED);
	            Border lineDiv3o =BorderFactory.createMatteBorder(2, 2, 2, -1, new Color(0,100,0));
	            Border lineDiv4o =BorderFactory.createMatteBorder(2, 2, 2, -1, Color.MAGENTA);
	            Border lineDivInvo =BorderFactory.createMatteBorder(2, 2, 2, -1, Color.WHITE);
	            CompoundBorder lineDivInv12o = new CompoundBorder(new CompoundBorder( lineDivInvo, lineSpace), lineDivInvo);
	            CompoundBorder lineDivInv123o = new CompoundBorder(lineDivInv12o,new CompoundBorder(lineSpace,lineDivInvo)) ;
	            CompoundBorder lineDivInv1234o = new CompoundBorder(lineDivInv123o,new CompoundBorder(lineSpace,lineDivInvo));
	            CompoundBorder lineDiv12o = new CompoundBorder(new CompoundBorder( lineDiv1o, lineSpace), lineDiv2o);
	            CompoundBorder lineDiv123o = new CompoundBorder(lineDiv12o,new CompoundBorder(lineSpace,lineDiv3o)) ;	            
	            CompoundBorder lineDiv1234o = new CompoundBorder(lineDiv123o,new CompoundBorder(lineSpace,lineDiv4o));
	            CompoundBorder lineDiv23o=new CompoundBorder(new CompoundBorder( lineDiv2o, lineSpace), lineDiv3o);
	            CompoundBorder lineDiv13o=new CompoundBorder(new CompoundBorder( lineDiv1o, lineSpace), lineDiv3o);
	            CompoundBorder lineDiv34o=new CompoundBorder(new CompoundBorder( lineDiv3o, lineSpace), lineDiv4o);
	            CompoundBorder lineDiv14o=new CompoundBorder(new CompoundBorder( lineDiv1o, lineSpace), lineDiv4o);
	            CompoundBorder lineDiv24o=new CompoundBorder(new CompoundBorder( lineDiv2o, lineSpace), lineDiv4o);
	            CompoundBorder lineDiv124o=new CompoundBorder(new CompoundBorder( lineDiv1o, lineSpace), lineDiv24o);
	            CompoundBorder lineDiv234o=new CompoundBorder(new CompoundBorder( lineDiv23o, lineSpace), lineDiv4o);
	            CompoundBorder lineDiv134o=new CompoundBorder(new CompoundBorder( lineDiv13o, lineSpace), lineDiv4o);
	            //
	            CompoundBorder SlineDivInv12o = new CompoundBorder(new CompoundBorder( lineDivInvo, lineSpace), lineDivInvo);
	            CompoundBorder SlineDivInv123o = new CompoundBorder(SlineDivInv12o,new CompoundBorder(lineSpace,lineDivInvo)) ;
	            CompoundBorder SlineDivInv1234o = new CompoundBorder(SlineDivInv123o,new CompoundBorder(lineSpace,lineDivInvo));
	            CompoundBorder SlineDiv12o = new CompoundBorder(new CompoundBorder( lineDivInvo, lineSpace), lineDivInvo);
	            CompoundBorder SlineDiv123o = new CompoundBorder(SlineDiv12o,new CompoundBorder(lineSpace,lineDivInvo)) ;	            
	            CompoundBorder SlineDiv1234o = new CompoundBorder(SlineDiv123o,new CompoundBorder(lineSpace,lineDivInvo));
	            CompoundBorder SlineDiv23o=new CompoundBorder(new CompoundBorder( lineDivInvo, lineSpace), lineDivInvo);
	            CompoundBorder SlineDiv13o=new CompoundBorder(new CompoundBorder( lineDivInvo, lineSpace), lineDivInvo);
	            CompoundBorder SlineDiv34o=new CompoundBorder(new CompoundBorder( lineDivInvo, lineSpace), lineDivInvo);
	            CompoundBorder SlineDiv14o=new CompoundBorder(new CompoundBorder( lineDivInvo, lineSpace), lineDivInvo);
	            CompoundBorder SlineDiv24o=new CompoundBorder(new CompoundBorder( lineDivInvo, lineSpace), lineDivInvo);
	            CompoundBorder SlineDiv124o=new CompoundBorder(new CompoundBorder( lineDivInvo, lineSpace), SlineDiv24o);
	            CompoundBorder SlineDiv234o=new CompoundBorder(new CompoundBorder( SlineDiv23o, lineSpace), lineDivInvo);
	            CompoundBorder SlineDiv134o=new CompoundBorder(new CompoundBorder( SlineDiv13o, lineSpace), lineDivInvo);
	            
	            //
	            
	            Border lineDiv1e=BorderFactory.createMatteBorder(2, -1, 2, 2, Color.BLUE);
	            Border lineDiv2e =BorderFactory.createMatteBorder(2, -1, 2, 2, Color.RED);
	            Border lineDiv3e =BorderFactory.createMatteBorder(2, -1, 2, 2, new Color(0,100,0));
	            Border lineDiv4e =BorderFactory.createMatteBorder(2, -1, 2, 2, Color.MAGENTA);
	            Border lineDivInve =BorderFactory.createMatteBorder(2, -1, 2, 2, Color.WHITE);
	            //
	            CompoundBorder SlineDivInv12e = new CompoundBorder(new CompoundBorder( lineDivInve, lineSpace), lineDivInve);
	            CompoundBorder SlineDivInv123e = new CompoundBorder(SlineDivInv12e,new CompoundBorder(lineSpace,lineDivInve)) ;
	            CompoundBorder SlineDivInv1234e = new CompoundBorder(SlineDivInv123e,new CompoundBorder(lineSpace,lineDivInve));
	            CompoundBorder SlineDiv12e = new CompoundBorder(new CompoundBorder( lineDivInve, lineSpace), lineDivInve);
	            CompoundBorder SlineDiv123e = new CompoundBorder(SlineDiv12e,new CompoundBorder(lineSpace,lineDivInve)) ;	            
	            CompoundBorder SlineDiv1234e = new CompoundBorder(SlineDiv123e,new CompoundBorder(lineSpace,lineDivInve));
	            CompoundBorder SlineDiv23e=new CompoundBorder(new CompoundBorder( lineDivInve,lineSpace), lineDivInve);
	            CompoundBorder SlineDiv13e=new CompoundBorder(new CompoundBorder( lineDivInve, lineSpace), lineDivInve);
	            CompoundBorder SlineDiv34e=new CompoundBorder(new CompoundBorder( lineDivInve, lineSpace), lineDivInve);
	            CompoundBorder SlineDiv14e=new CompoundBorder(new CompoundBorder( lineDivInve, lineSpace), lineDivInve);
	            CompoundBorder SlineDiv24e=new CompoundBorder(new CompoundBorder( lineDivInve, lineSpace), lineDivInve);
	            CompoundBorder SlineDiv124e=new CompoundBorder(new CompoundBorder( lineDivInve, lineSpace), SlineDiv24e);
	            CompoundBorder SlineDiv234e=new CompoundBorder(new CompoundBorder( SlineDiv23e, lineSpace), lineDivInve);
	            CompoundBorder SlineDiv134e=new CompoundBorder(new CompoundBorder( SlineDiv13e, lineSpace), lineDivInve);
	            //
	            CompoundBorder lineDivInv12e = new CompoundBorder(new CompoundBorder( lineDivInve, lineSpace), lineDivInve);
	            CompoundBorder lineDivInv123e = new CompoundBorder(lineDivInv12e,new CompoundBorder(lineSpace,lineDivInve)) ;
	            CompoundBorder lineDivInv1234e = new CompoundBorder(lineDivInv123e,new CompoundBorder(lineSpace,lineDivInve));
	            CompoundBorder lineDiv12e = new CompoundBorder(new CompoundBorder( lineDiv1e, lineSpace), lineDiv2e);
	            CompoundBorder lineDiv123e = new CompoundBorder(lineDiv12e,new CompoundBorder(lineSpace,lineDiv3e)) ;	            
	            CompoundBorder lineDiv1234e = new CompoundBorder(lineDiv123e,new CompoundBorder(lineSpace,lineDiv4e));
	            CompoundBorder lineDiv23e=new CompoundBorder(new CompoundBorder( lineDiv2e, lineSpace), lineDiv3e);
	            CompoundBorder lineDiv13e=new CompoundBorder(new CompoundBorder( lineDiv1e, lineSpace), lineDiv3e);
	            CompoundBorder lineDiv34e=new CompoundBorder(new CompoundBorder( lineDiv3e, lineSpace), lineDiv4e);
	            CompoundBorder lineDiv14e=new CompoundBorder(new CompoundBorder( lineDiv1e, lineSpace), lineDiv4e);
	            CompoundBorder lineDiv24e=new CompoundBorder(new CompoundBorder( lineDiv2e, lineSpace), lineDiv4e);
	            CompoundBorder lineDiv124e=new CompoundBorder(new CompoundBorder( lineDiv1e, lineSpace), lineDiv24e);
	            CompoundBorder lineDiv234e=new CompoundBorder(new CompoundBorder( lineDiv23e, lineSpace), lineDiv4e);
	            CompoundBorder lineDiv134e=new CompoundBorder(new CompoundBorder( lineDiv13e, lineSpace), lineDiv4e);
	            if ((div1Menu.isSelected()) && (w.hasStrukturLevel(1))){
             		 if ((findDivisionId(w.getStrukturLevel(1)).getWbegin().equals(w.getId())))
                        showDiv1o=true;
             		 else showDiv1o=false;
                     if ((findDivisionId(w.getStrukturLevel(1)).getWend().equals(w.getId())))
                      showDiv1e=true;
                     
             		 else showDiv1e=false;
                     if (showDiv1o || showDiv1e) showDiv1=true;
                     else showDiv1=false;
                  }
                  else showDiv1=false;
                  if((div2Menu.isSelected()) && (w.hasStrukturLevel(2))){
	              		 if ((findDivisionId(w.getStrukturLevel(2)).getWbegin().equals(w.getId())))
                            showDiv2o=true;
	              		 else showDiv2o=false;
	              		 if ((findDivisionId(w.getStrukturLevel(2)).getWend().equals(w.getId())))
                            showDiv2e=true;
	              		 else showDiv2e=false;
	              		 if(showDiv2o || showDiv2e) showDiv2=true;
	              		 else showDiv2=false;
                  }
	                   else showDiv2=false;
                  if ((div3Menu.isSelected()) && (w.hasStrukturLevel(3))){
               	  
	              		 if ((findDivisionId(w.getStrukturLevel(3)).getWbegin().equals(w.getId())))
                            showDiv3o=true;
	              		 else showDiv3o=false;
	              		if ((findDivisionId(w.getStrukturLevel(3)).getWend().equals(w.getId())))
                            showDiv3e=true;
	              		 else showDiv3e=false;
	              		if(showDiv3o || showDiv3e) showDiv3=true;
	              		else showDiv3=false;
                  }
	                   else showDiv3=false;
                  if ((div4Menu.isSelected()) && (w.hasStrukturLevel(4))){
	              		 if ((findDivisionId(w.getStrukturLevel(4)).getWbegin().equals(w.getId())))
                            showDiv4o=true;
	              		 else showDiv4o=false;
	              		if ((findDivisionId(w.getStrukturLevel(4)).getWend().equals(w.getId())))
                            showDiv4e=true;
	              		 else showDiv4e=false;
	              		if(showDiv4o || showDiv4e) showDiv4=true;
	              		else showDiv4=false;
                  } 
	                   else showDiv4=false;  
	      if (temp.equals("class TranslitListModel")){
	    	 /* char ch=verifyWordInIndex(index,w).charAt(0);
	    	   if((!Character.isDigit(ch))||(!Character.isDigit(verifyTokensWordIndex(index,w).charAt(0)))){
	      	        if (!w.hasErrorNode(typScript,typDoc))w.setError(1);
	      	        else w.setError(2);
	          }
               else if (w.hasErrorNode(typScript,typDoc))w.setError(3);
               else w.setError(0);*/
	        	 TranslitListModel model=(TranslitListModel)list.getModel();
	             // int  n =model.getFlagAnnotStructAt(index);
	           //  w= (WortNode)model.elementAt(index);
	             // if (w.getFidalLabel(typScript,typDoc).equals("\u204b")) c.setBackground(Color.YELLOW);
	            //  c.setFont(new Font("Ethiopic Unicode", Font.PLAIN,14));
	              if(w.getFcolor()!=null) c.setBackground(w.getFcolor());
	            //  Border bord1 =BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED);
	              //Border bord2 =BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
	             // CompoundBorder bord12 = new CompoundBorder(new CompoundBorder( bord1, lineSpace), bord2);
	              Border bord = BorderFactory.createLineBorder(Color.black);
	              if (translitWordList.isSelectedIndex(index)) ((JComponent) c).setBorder(bord);
	           if(w.getError()>0){   
	          if(w.getError()==1)((JComponent) c).setBorder(bord1);
	      	  else if (w.getError()==2)((JComponent) c).setBorder(bord12);
	          else if (w.getError()==3)((JComponent) c).setBorder(bord2);
	         }
	          else {
	          if (w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
	            	
	        	     if( showDiv4o && showDiv3o && showDiv2o && showDiv1o) ((JComponent) c).setBorder(SlineDiv1234o);
	            	 else if( showDiv4o && showDiv3o && showDiv2o) ((JComponent) c).setBorder(SlineDiv234o);
	            	 else if( showDiv4o && showDiv2o && showDiv1o) ((JComponent) c).setBorder(SlineDiv124o);
	            	 else if( showDiv4o && showDiv3o &&  showDiv1o) ((JComponent) c).setBorder(SlineDiv134o);
	            	 else if( showDiv4o && showDiv3o) ((JComponent) c).setBorder(SlineDiv34o);
	            	 else if( showDiv4o && showDiv2o) ((JComponent) c).setBorder(SlineDiv24o);
	            	 else if( showDiv4o && showDiv1o) ((JComponent) c).setBorder(SlineDiv14o);
	            	 else if( showDiv4o) ((JComponent) c).setBorder(lineDivInvo);
	            	 else if( showDiv3o && showDiv2o && showDiv1o) ((JComponent) c).setBorder(SlineDiv123o);
	            	 else  if( showDiv3o && showDiv2o ) ((JComponent) c).setBorder(SlineDiv23o);
	            	 else  if( showDiv3o && showDiv1o) ((JComponent) c).setBorder(SlineDiv13o);
	            	 else  if( showDiv2o && showDiv1o) ((JComponent) c).setBorder(SlineDiv12o);
	            	 else  if( showDiv2o) ((JComponent) c).setBorder(lineDivInvo);
	            	 else if( showDiv3o) ((JComponent) c).setBorder(lineDivInvo);
	            	 else if( showDiv1o ) ((JComponent) c).setBorder(lineDivInvo);
	          }
	          else if (w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
	        	 
	        	  if(( showDiv3o && showDiv2o && showDiv1o)) ((JComponent) c).setBorder(SlineDiv123o);
	            	 else  if(( showDiv3o && showDiv2o && showDiv1o))((JComponent) c).setBorder(SlineDiv23o);
	            	 else  if(( showDiv3o && showDiv2o )) ((JComponent) c).setBorder(SlineDiv23o);
	            	 else  if(( showDiv3o && showDiv1o)) ((JComponent) c).setBorder(SlineDiv13o);
	            	 else  if(( showDiv2o && showDiv1o)) ((JComponent) c).setBorder(SlineDiv12o);
	            	 else  if( showDiv2o) ((JComponent) c).setBorder(lineDivInvo);
	            	 else if(( showDiv3o ))((JComponent) c).setBorder(lineDivInvo);
	            	 else if( (showDiv1o )) ((JComponent) c).setBorder(lineDivInvo);
	          }
	          else if(w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
	        	  
	            	
	            	 if( showDiv4o && showDiv2o && showDiv1o) ((JComponent) c).setBorder(SlineDiv124o);
	            	
	            	
	            	 else if( showDiv4o && showDiv2o) ((JComponent) c).setBorder(SlineDiv24o);
	            	 else if( showDiv4o && showDiv1o) ((JComponent) c).setBorder(SlineDiv14o);
	            	 else  if( showDiv2o && showDiv1o) ((JComponent) c).setBorder(SlineDiv12o);
	            	 else if( showDiv4o) ((JComponent) c).setBorder(lineDivInvo);
	           
	            	 
	            	 else  if( showDiv2o) ((JComponent) c).setBorder(lineDivInvo);
	            	
	            	 else if( showDiv1o ) ((JComponent) c).setBorder(lineDivInvo);
	        	  
	          }
	          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
	        	  
	            	 
	            	
	            	 if( showDiv4o && showDiv3o &&  showDiv1o) ((JComponent) c).setBorder(SlineDiv134o);
	            	 else if( showDiv4o && showDiv3o) ((JComponent) c).setBorder(SlineDiv34o);
	            	
	            	 else if( showDiv4o && showDiv1o) ((JComponent) c).setBorder(SlineDiv14o);
	            	 else  if( showDiv3o && showDiv1o) ((JComponent) c).setBorder(SlineDiv13o);
	            	 else if( showDiv4o) ((JComponent) c).setBorder(lineDivInvo);		          	
	            	 else if( showDiv3o ) ((JComponent) c).setBorder(lineDivInvo);
	            	 else if( showDiv1o ) ((JComponent) c).setBorder(lineDivInvo);
	          }
	          else if  ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
	        	  
	            	 if( showDiv4o && showDiv3o && showDiv2o) ((JComponent) c).setBorder(SlineDiv234o);
	            	 
	            	 else if( showDiv4o && showDiv3o) ((JComponent) c).setBorder(SlineDiv34o);
	            	 else if( showDiv4o && showDiv2o) ((JComponent) c).setBorder(SlineDiv24o);
	            	 else  if( showDiv3o && showDiv2o ) ((JComponent) c).setBorder(SlineDiv23o);
	            	 else if( showDiv4o) ((JComponent) c).setBorder(lineDivInvo);
	            	 else  if( showDiv2o) ((JComponent) c).setBorder(lineDivInvo);
	            	 else if( showDiv3o ) ((JComponent) c).setBorder(lineDivInvo);
	           
	            	
	          }
	          else if (w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && (!w.hasStrukturLevel(4)))	{
	        		 
	            	
	            	 if( showDiv2o && showDiv1o) ((JComponent) c).setBorder(SlineDiv12o);
	            	 else  if( showDiv2o) ((JComponent) c).setBorder(lineDivInvo);
	           
	            	 else if( showDiv1o ) ((JComponent) c).setBorder(lineDivInvo);
	          }
	          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
	        	 
	            	 if( showDiv4o && showDiv1o) ((JComponent) c).setBorder(SlineDiv14o);
	            	 else if( showDiv4o) ((JComponent) c).setBorder(lineDivInvo);
	            
	            	 else if( showDiv1o ) ((JComponent) c).setBorder(lineDiv1o);
	          }
	          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
	        	  
	            	if( showDiv3o && showDiv1o) ((JComponent) c).setBorder(SlineDiv13o);
	            	
	            	 else if( showDiv3o ) ((JComponent) c).setBorder(lineDivInvo);
	            	 else if( showDiv1o ) ((JComponent) c).setBorder(lineDivInvo);
	          }
	          else if ((!w.hasStrukturLevel(1)) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
	        	  
	            	 if( showDiv4o && showDiv3o) ((JComponent) c).setBorder(SlineDiv34o);
	            
	            	 else if( showDiv4o) ((JComponent) c).setBorder(lineDivInvo);
	            	 
	            	 else if( showDiv3o ) ((JComponent) c).setBorder(lineDivInvo);
	            
	          }
	          else if ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
	        	  
	              if( showDiv4o && showDiv2o) ((JComponent) c).setBorder(SlineDiv24o);
	            	
	            	 else if( showDiv4o) ((JComponent) c).setBorder(lineDivInvo);
	            	
	            	 else  if( showDiv2o) ((JComponent) c).setBorder(lineDivInvo);
	            	 
	          }
	          else if ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
	        	 
	            	 if( showDiv3o && showDiv2o ) ((JComponent) c).setBorder(SlineDiv23o);
	            	
	            	 else  if( showDiv2o) ((JComponent) c).setBorder(lineDivInvo);
	            	 else if( showDiv3o ) ((JComponent) c).setBorder(lineDivInvo);
	            	 
	          }
	          else if ((!w.hasStrukturLevel(1)) && (!w.hasStrukturLevel(2)) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
	        	  if( showDiv4o) ((JComponent) c).setBorder(lineDivInvo);
	          }
	          else if ((!w.hasStrukturLevel(1)) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
	        	  if( showDiv3o) ((JComponent) c).setBorder(lineDivInvo);
	          }
	          else if ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && (!w.hasStrukturLevel(4))){
	        	  if( showDiv2o) ((JComponent) c).setBorder(lineDivInvo);
	          }
	          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && (!w.hasStrukturLevel(3)) && (!w.hasStrukturLevel(4))){
	        	  if( showDiv1o) ((JComponent) c).setBorder(lineDivInvo);
	          }
		            
		            
		      if (w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
		            	
		        	     if( showDiv4e && showDiv3e && showDiv2e && showDiv1e) ((JComponent) c).setBorder(SlineDiv1234e);
		            	 else if( showDiv4e && showDiv3e && showDiv2e) ((JComponent) c).setBorder(SlineDiv234e);
		            	 else if( showDiv4e && showDiv2e && showDiv1e) ((JComponent) c).setBorder(SlineDiv124e);
		            	 else if( showDiv4e && showDiv3e &&  showDiv1e) ((JComponent) c).setBorder(SlineDiv134e);
		            	 else if( showDiv4e && showDiv3e) ((JComponent) c).setBorder(SlineDiv34e);
		            	 else if( showDiv4e && showDiv2e) ((JComponent) c).setBorder(SlineDiv24e);
		            	 else if( showDiv4e && showDiv1e) ((JComponent) c).setBorder(SlineDiv14e);
		            	 else if( showDiv4e) ((JComponent) c).setBorder(lineDivInve);
		            	 else if( showDiv3e && showDiv2e && showDiv1e) ((JComponent) c).setBorder(SlineDiv123e);
		            	 else  if( showDiv3e && showDiv2e ) ((JComponent) c).setBorder(SlineDiv23e);
		            	 else  if( showDiv3e && showDiv1e) ((JComponent) c).setBorder(SlineDiv13e);
		            	 else  if( showDiv2e && showDiv1e) ((JComponent) c).setBorder(SlineDiv12e);
		            	 else  if( showDiv2e) ((JComponent) c).setBorder(lineDivInve);
		            	 else if( showDiv3e) ((JComponent) c).setBorder(lineDivInve);
		            	 else if( showDiv1e ) ((JComponent) c).setBorder(lineDivInve);
		          }
		          else if (w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
		        	 
		        	  if(( showDiv3e && showDiv2e && showDiv1e)) ((JComponent) c).setBorder(SlineDiv123e);
		            	 else  if(( showDiv3e && showDiv2e && showDiv1e))((JComponent) c).setBorder(SlineDiv23e);
		            	 else  if(( showDiv3e && showDiv2e )) ((JComponent) c).setBorder(SlineDiv23e);
		            	 else  if(( showDiv3e && showDiv1e)) ((JComponent) c).setBorder(SlineDiv13e);
		            	 else  if(( showDiv2e && showDiv1e)) ((JComponent) c).setBorder(SlineDiv12e);
		            	 else  if( showDiv2e) ((JComponent) c).setBorder(lineDivInve);
		            	 else if(( showDiv3e ))((JComponent) c).setBorder(lineDivInve);
		            	 else if( (showDiv1e )) ((JComponent) c).setBorder(lineDivInve);
		          }
		          else if(w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
		        	  
		            	
		            	 if( showDiv4e && showDiv2e && showDiv1e) ((JComponent) c).setBorder(SlineDiv124e);
		            	
		            	
		            	 else if( showDiv4e && showDiv2e) ((JComponent) c).setBorder(SlineDiv24e);
		            	 else if( showDiv4e && showDiv1e) ((JComponent) c).setBorder(SlineDiv14e);
		            	 else  if( showDiv2e && showDiv1e) ((JComponent) c).setBorder(SlineDiv12e);
		            	 else if( showDiv4e) ((JComponent) c).setBorder(lineDivInve);
		           
		            	 
		            	 else  if( showDiv2e) ((JComponent) c).setBorder(lineDivInve);
		            	
		            	 else if( showDiv1e ) ((JComponent) c).setBorder(lineDivInve);
		        	  
		          }
		          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
		        	  
		            	 
		            	
		            	 if( showDiv4e && showDiv3e &&  showDiv1e) ((JComponent) c).setBorder(SlineDiv134e);
		            	 else if( showDiv4e && showDiv3e) ((JComponent) c).setBorder(SlineDiv34e);
		            	
		            	 else if( showDiv4e && showDiv1e) ((JComponent) c).setBorder(SlineDiv14e);
		            	 else  if( showDiv3e && showDiv1e) ((JComponent) c).setBorder(SlineDiv13e);
		            	 else if( showDiv4e) ((JComponent) c).setBorder(lineDivInve);		          	
		            	 else if( showDiv3e ) ((JComponent) c).setBorder(lineDivInve);
		            	 else if( showDiv1e ) ((JComponent) c).setBorder(lineDivInve);
		          }
		          else if  ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
		        	  
		            	 if( showDiv4e && showDiv3e && showDiv2e) ((JComponent) c).setBorder(SlineDiv234e);
		            	 
		            	 else if( showDiv4e && showDiv3e) ((JComponent) c).setBorder(SlineDiv34e);
		            	 else if( showDiv4e && showDiv2e) ((JComponent) c).setBorder(SlineDiv24e);
		            	 else  if( showDiv3e && showDiv2e ) ((JComponent) c).setBorder(SlineDiv23e);
		            	 else if( showDiv4e) ((JComponent) c).setBorder(lineDivInve);
		            	 else  if( showDiv2e) ((JComponent) c).setBorder(lineDivInve);
		            	 else if( showDiv3e ) ((JComponent) c).setBorder(lineDivInve);
		           
		            	
		          }
		          else if (w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && (!w.hasStrukturLevel(4)))	{
		        		 
		            	
		            	 if( showDiv2e && showDiv1e) ((JComponent) c).setBorder(SlineDiv12e);
		            	 else  if( showDiv2e) ((JComponent) c).setBorder(lineDivInve);
		           
		            	 else if( showDiv1e ) ((JComponent) c).setBorder(lineDivInve);
		          }
		          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
		        	 
		            	 if( showDiv4e && showDiv1e) ((JComponent) c).setBorder(SlineDiv14e);
		            	 else if( showDiv4e) ((JComponent) c).setBorder(lineDivInve);
		            
		            	 else if( showDiv1e ) ((JComponent) c).setBorder(lineDivInve);
		          }
		          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
		        	  
		            	if( showDiv3e && showDiv1e) ((JComponent) c).setBorder(SlineDiv13e);
		            	
		            	 else if( showDiv3e ) ((JComponent) c).setBorder(lineDivInve);
		            	 else if( showDiv1e ) ((JComponent) c).setBorder(lineDivInve);
		          }
		          else if ((!w.hasStrukturLevel(1)) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
		        	  
		            	 if( showDiv4e && showDiv3e) ((JComponent) c).setBorder(SlineDiv34e);
		            
		            	 else if( showDiv4e) ((JComponent) c).setBorder(lineDivInve);
		            	 
		            	 else if( showDiv3e ) ((JComponent) c).setBorder(lineDivInve);
		            
		          }
		          else if ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
		        	  
		              if( showDiv4e && showDiv2e) ((JComponent) c).setBorder(SlineDiv24e);
		            	
		            	 else if( showDiv4e) ((JComponent) c).setBorder(lineDivInve);
		            	
		            	 else  if( showDiv2e) ((JComponent) c).setBorder(lineDivInve);
		            	 
		          }
		          else if ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
		        	 
		            	 if( showDiv3e && showDiv2e ) ((JComponent) c).setBorder(SlineDiv23e);
		            	
		            	 else  if( showDiv2e) ((JComponent) c).setBorder(lineDivInve);
		            	 else if( showDiv3e ) ((JComponent) c).setBorder(lineDivInve);
		            	 
		          }
		          else if ((!w.hasStrukturLevel(1)) && (!w.hasStrukturLevel(2)) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
		        	  if( showDiv4e) ((JComponent) c).setBorder(lineDivInve);
		          }
		          else if ((!w.hasStrukturLevel(1)) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
		        	  if( showDiv3e) ((JComponent) c).setBorder(lineDivInve);
		          }
		          else if ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && (!w.hasStrukturLevel(4))){
		        	  if( showDiv2e) ((JComponent) c).setBorder(lineDivInve);
		          }
		          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && (!w.hasStrukturLevel(3)) && (!w.hasStrukturLevel(4))){
		        	  if( showDiv1e) ((JComponent) c).setBorder(lineDivInve);
		          }      
	          
	          
	          
	          
	          //
	        	           
		            
		            
	          translitWordList.repaint();
	          
                // errorPane.revalidate();errorPane.repaint();
		       }
	       
	              }
	     
	      if (temp.equals("class WortListModel")){
        	 // WortListModel model=(WortListModel)list.getModel();
	            //       WortNode w= (WortNode)model.elementAt(index);
                      // ArrayList<DivLevel> d=w.getStructureAnnot().getDivisions(); 
	                //   c.setFont(new Font("Ethiopic Unicode", Font.PLAIN,14));
	                  
	                  // if(w.getFcolor()!=null)
	                    //    c.setBackground(Color.WHITE);
	                  // Border bord1 =BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED);
	 	              //Border bord2 =BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
	 	              //CompoundBorder bord12 = new CompoundBorder(new CompoundBorder( bord1, lineSpace), bord2);
	    	 /* char ch=verifyWordInIndex(index,w).charAt(0);
	    	 if((!Character.isDigit(ch))||(!Character.isDigit(verifyTokensWordIndex(index,w).charAt(0)))){
	      	        if (!w.hasErrorNode(typScript,typDoc))w.setError(1);
	      	        else w.setError(2);
	          }
               else if (w.hasErrorNode(typScript,typDoc))w.setError(3);
               else w.setError(0);*/
	    	  if(w.getError()==1)((JComponent) c).setBorder(bord1);
	      	  else if (w.getError()==2)((JComponent) c).setBorder(bord12);
	          else if (w.getError()==3)((JComponent) c).setBorder(bord2);
	                   
	            if (w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
            	
        	     if( showDiv4o && showDiv3o && showDiv2o && showDiv1o) ((JComponent) c).setBorder(lineDiv1234o);
            	 else if( showDiv4o && showDiv3o && showDiv2o) ((JComponent) c).setBorder(lineDiv234o);
            	 else if( showDiv4o && showDiv2o && showDiv1o) ((JComponent) c).setBorder(lineDiv124o);
            	 else if( showDiv4o && showDiv3o &&  showDiv1o) ((JComponent) c).setBorder(lineDiv134o);
            	 else if( showDiv4o && showDiv3o) ((JComponent) c).setBorder(lineDiv34o);
            	 else if( showDiv4o && showDiv2o) ((JComponent) c).setBorder(lineDiv24o);
            	 else if( showDiv4o && showDiv1o) ((JComponent) c).setBorder(lineDiv14o);
            	 else if( showDiv4o) ((JComponent) c).setBorder(lineDiv4o);
            	 else if( showDiv3o && showDiv2o && showDiv1o) ((JComponent) c).setBorder(lineDiv123o);
            	 else  if( showDiv3o && showDiv2o ) ((JComponent) c).setBorder(lineDiv23o);
            	 else  if( showDiv3o && showDiv1o) ((JComponent) c).setBorder(lineDiv13o);
            	 else  if( showDiv2o && showDiv1o) ((JComponent) c).setBorder(lineDiv12o);
            	 else  if( showDiv2o) ((JComponent) c).setBorder(lineDiv2o);
            	 else if( showDiv3o) ((JComponent) c).setBorder(lineDiv3o);
            	 else if( showDiv1o ) ((JComponent) c).setBorder(lineDiv1o);
          }
          else if (w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
        	 
        	  if(( showDiv3o && showDiv2o && showDiv1o)) ((JComponent) c).setBorder(lineDiv123o);
            	 else  if(( showDiv3o && showDiv2o && showDiv1o))((JComponent) c).setBorder(lineDiv23o);
            	 else  if(( showDiv3o && showDiv2o )) ((JComponent) c).setBorder(lineDiv23o);
            	 else  if(( showDiv3o && showDiv1o)) ((JComponent) c).setBorder(lineDiv13o);
            	 else  if(( showDiv2o && showDiv1o)) ((JComponent) c).setBorder(lineDiv12o);
            	 else  if( showDiv2o) ((JComponent) c).setBorder(lineDiv2o);
            	 else if(( showDiv3o ))((JComponent) c).setBorder(lineDiv3o);
            	 else if( (showDiv1o )) ((JComponent) c).setBorder(lineDiv1o);
          }
          else if(w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
        	  
            	
            	 if( showDiv4o && showDiv2o && showDiv1o) ((JComponent) c).setBorder(lineDiv124o);
            	
            	
            	 else if( showDiv4o && showDiv2o) ((JComponent) c).setBorder(lineDiv24o);
            	 else if( showDiv4o && showDiv1o) ((JComponent) c).setBorder(lineDiv14o);
            	 else  if( showDiv2o && showDiv1o) ((JComponent) c).setBorder(lineDiv12o);
            	 else if( showDiv4o) ((JComponent) c).setBorder(lineDiv4o);
           
            	 
            	 else  if( showDiv2o) ((JComponent) c).setBorder(lineDiv2o);
            	
            	 else if( showDiv1o ) ((JComponent) c).setBorder(lineDiv1o);
        	  
          }
          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
        	  
            	 
            	
            	 if( showDiv4o && showDiv3o &&  showDiv1o) ((JComponent) c).setBorder(lineDiv134o);
            	 else if( showDiv4o && showDiv3o) ((JComponent) c).setBorder(lineDiv34o);
            	
            	 else if( showDiv4o && showDiv1o) ((JComponent) c).setBorder(lineDiv14o);
            	 else  if( showDiv3o && showDiv1o) ((JComponent) c).setBorder(lineDiv13o);
            	 else if( showDiv4o) ((JComponent) c).setBorder(lineDiv4o);		          	
            	 else if( showDiv3o ) ((JComponent) c).setBorder(lineDiv3o);
            	 else if( showDiv1o ) ((JComponent) c).setBorder(lineDiv1o);
          }
          else if  ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
        	  
            	 if( showDiv4o && showDiv3o && showDiv2o) ((JComponent) c).setBorder(lineDiv234o);
            	 
            	 else if( showDiv4o && showDiv3o) ((JComponent) c).setBorder(lineDiv34o);
            	 else if( showDiv4o && showDiv2o) ((JComponent) c).setBorder(lineDiv24o);
            	 else  if( showDiv3o && showDiv2o ) ((JComponent) c).setBorder(lineDiv23o);
            	 else if( showDiv4o) ((JComponent) c).setBorder(lineDiv4o);
            	 else  if( showDiv2o) ((JComponent) c).setBorder(lineDiv2o);
            	 else if( showDiv3o ) ((JComponent) c).setBorder(lineDiv3o);
           
            	
          }
          else if (w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && (!w.hasStrukturLevel(4)))	{
        		 
            	
            	 if( showDiv2o && showDiv1o) ((JComponent) c).setBorder(lineDiv12o);
            	 else  if( showDiv2o) ((JComponent) c).setBorder(lineDiv2o);
           
            	 else if( showDiv1o ) ((JComponent) c).setBorder(lineDiv1o);
          }
          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
        	 
            	 if( showDiv4o && showDiv1o) ((JComponent) c).setBorder(lineDiv14o);
            	 else if( showDiv4o) ((JComponent) c).setBorder(lineDiv4o);
            
            	 else if( showDiv1o ) ((JComponent) c).setBorder(lineDiv1o);
          }
          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
        	  
            	if( showDiv3o && showDiv1o) ((JComponent) c).setBorder(lineDiv13o);
            	
            	 else if( showDiv3o ) ((JComponent) c).setBorder(lineDiv3o);
            	 else if( showDiv1o ) ((JComponent) c).setBorder(lineDiv1o);
          }
          else if ((!w.hasStrukturLevel(1)) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
        	  
            	 if( showDiv4o && showDiv3o) ((JComponent) c).setBorder(lineDiv34o);
            
            	 else if( showDiv4o) ((JComponent) c).setBorder(lineDiv4o);
            	 
            	 else if( showDiv3o ) ((JComponent) c).setBorder(lineDiv3o);
            
          }
          else if ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
        	  
              if( showDiv4o && showDiv2o) ((JComponent) c).setBorder(lineDiv24o);
            	
            	 else if( showDiv4o) ((JComponent) c).setBorder(lineDiv4o);
            	
            	 else  if( showDiv2o) ((JComponent) c).setBorder(lineDiv2o);
            	 
          }
          else if ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
        	 
            	 if( showDiv3o && showDiv2o ) ((JComponent) c).setBorder(lineDiv23o);
            	
            	 else  if( showDiv2o) ((JComponent) c).setBorder(lineDiv2o);
            	 else if( showDiv3o ) ((JComponent) c).setBorder(lineDiv3o);
            	 
          }
          else if ((!w.hasStrukturLevel(1)) && (!w.hasStrukturLevel(2)) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
        	  if( showDiv4o) ((JComponent) c).setBorder(lineDiv4o);
          }
          else if ((!w.hasStrukturLevel(1)) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
        	  if( showDiv3o) ((JComponent) c).setBorder(lineDiv3o);
          }
          else if ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && (!w.hasStrukturLevel(4))){
        	  if( showDiv2o) ((JComponent) c).setBorder(lineDiv2o);
          }
          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && (!w.hasStrukturLevel(3)) && (!w.hasStrukturLevel(4))){
        	  if( showDiv1o) ((JComponent) c).setBorder(lineDiv1o);
          }
	            
	            
	      if (w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
	            	
	        	     if( showDiv4e && showDiv3e && showDiv2e && showDiv1e) ((JComponent) c).setBorder(lineDiv1234e);
	            	 else if( showDiv4e && showDiv3e && showDiv2e) ((JComponent) c).setBorder(lineDiv234e);
	            	 else if( showDiv4e && showDiv2e && showDiv1e) ((JComponent) c).setBorder(lineDiv124e);
	            	 else if( showDiv4e && showDiv3e &&  showDiv1e) ((JComponent) c).setBorder(lineDiv134e);
	            	 else if( showDiv4e && showDiv3e) ((JComponent) c).setBorder(lineDiv34e);
	            	 else if( showDiv4e && showDiv2e) ((JComponent) c).setBorder(lineDiv24e);
	            	 else if( showDiv4e && showDiv1e) ((JComponent) c).setBorder(lineDiv14e);
	            	 else if( showDiv4e) ((JComponent) c).setBorder(lineDiv4e);
	            	 else if( showDiv3e && showDiv2e && showDiv1e) ((JComponent) c).setBorder(lineDiv123e);
	            	 else  if( showDiv3e && showDiv2e ) ((JComponent) c).setBorder(lineDiv23e);
	            	 else  if( showDiv3e && showDiv1e) ((JComponent) c).setBorder(lineDiv13e);
	            	 else  if( showDiv2e && showDiv1e) ((JComponent) c).setBorder(lineDiv12e);
	            	 else  if( showDiv2e) ((JComponent) c).setBorder(lineDiv2e);
	            	 else if( showDiv3e) ((JComponent) c).setBorder(lineDiv3e);
	            	 else if( showDiv1e ) ((JComponent) c).setBorder(lineDiv1e);
	          }
	          else if (w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
	        	 
	        	  if(( showDiv3e && showDiv2e && showDiv1e)) ((JComponent) c).setBorder(lineDiv123e);
	            	 else  if(( showDiv3e && showDiv2e && showDiv1e))((JComponent) c).setBorder(lineDiv23e);
	            	 else  if(( showDiv3e && showDiv2e )) ((JComponent) c).setBorder(lineDiv23e);
	            	 else  if(( showDiv3e && showDiv1e)) ((JComponent) c).setBorder(lineDiv13e);
	            	 else  if(( showDiv2e && showDiv1e)) ((JComponent) c).setBorder(lineDiv12e);
	            	 else  if( showDiv2e) ((JComponent) c).setBorder(lineDiv2e);
	            	 else if(( showDiv3e ))((JComponent) c).setBorder(lineDiv3e);
	            	 else if( (showDiv1e )) ((JComponent) c).setBorder(lineDiv1e);
	          }
	          else if(w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
	        	  
	            	
	            	 if( showDiv4e && showDiv2e && showDiv1e) ((JComponent) c).setBorder(lineDiv124e);
	            	
	            	
	            	 else if( showDiv4e && showDiv2e) ((JComponent) c).setBorder(lineDiv24e);
	            	 else if( showDiv4e && showDiv1e) ((JComponent) c).setBorder(lineDiv14e);
	            	 else  if( showDiv2e && showDiv1e) ((JComponent) c).setBorder(lineDiv12e);
	            	 else if( showDiv4e) ((JComponent) c).setBorder(lineDiv4e);
	           
	            	 
	            	 else  if( showDiv2e) ((JComponent) c).setBorder(lineDiv2e);
	            	
	            	 else if( showDiv1e ) ((JComponent) c).setBorder(lineDiv1e);
	        	  
	          }
	          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
	        	  
	            	 
	            	
	            	 if( showDiv4e && showDiv3e &&  showDiv1e) ((JComponent) c).setBorder(lineDiv134e);
	            	 else if( showDiv4e && showDiv3e) ((JComponent) c).setBorder(lineDiv34e);
	            	
	            	 else if( showDiv4e && showDiv1e) ((JComponent) c).setBorder(lineDiv14e);
	            	 else  if( showDiv3e && showDiv1e) ((JComponent) c).setBorder(lineDiv13e);
	            	 else if( showDiv4e) ((JComponent) c).setBorder(lineDiv4e);		          	
	            	 else if( showDiv3e ) ((JComponent) c).setBorder(lineDiv3e);
	            	 else if( showDiv1e ) ((JComponent) c).setBorder(lineDiv1e);
	          }
	          else if  ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
	        	  
	            	 if( showDiv4e && showDiv3e && showDiv2e) ((JComponent) c).setBorder(lineDiv234e);
	            	 
	            	 else if( showDiv4e && showDiv3e) ((JComponent) c).setBorder(lineDiv34e);
	            	 else if( showDiv4e && showDiv2e) ((JComponent) c).setBorder(lineDiv24e);
	            	 else  if( showDiv3e && showDiv2e ) ((JComponent) c).setBorder(lineDiv23e);
	            	 else if( showDiv4e) ((JComponent) c).setBorder(lineDiv4e);
	            	 else  if( showDiv2e) ((JComponent) c).setBorder(lineDiv2e);
	            	 else if( showDiv3e ) ((JComponent) c).setBorder(lineDiv3e);
	           
	            	
	          }
	          else if (w.hasStrukturLevel(1) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && (!w.hasStrukturLevel(4)))	{
	        		 
	            	
	            	 if( showDiv2e && showDiv1e) ((JComponent) c).setBorder(lineDiv12e);
	            	 else  if( showDiv2e) ((JComponent) c).setBorder(lineDiv2e);
	           
	            	 else if( showDiv1e ) ((JComponent) c).setBorder(lineDiv1e);
	          }
	          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
	        	 
	            	 if( showDiv4e && showDiv1e) ((JComponent) c).setBorder(lineDiv14e);
	            	 else if( showDiv4e) ((JComponent) c).setBorder(lineDiv4e);
	            
	            	 else if( showDiv1e ) ((JComponent) c).setBorder(lineDiv1e);
	          }
	          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
	        	  
	            	if( showDiv3e && showDiv1e) ((JComponent) c).setBorder(lineDiv13e);
	            	
	            	 else if( showDiv3e ) ((JComponent) c).setBorder(lineDiv3e);
	            	 else if( showDiv1e ) ((JComponent) c).setBorder(lineDiv1e);
	          }
	          else if ((!w.hasStrukturLevel(1)) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && w.hasStrukturLevel(4)){
	        	  
	            	 if( showDiv4e && showDiv3e) ((JComponent) c).setBorder(lineDiv34e);
	            
	            	 else if( showDiv4e) ((JComponent) c).setBorder(lineDiv4e);
	            	 
	            	 else if( showDiv3e ) ((JComponent) c).setBorder(lineDiv3e);
	            
	          }
	          else if ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
	        	  
	              if( showDiv4e && showDiv2e) ((JComponent) c).setBorder(lineDiv24e);
	            	
	            	 else if( showDiv4e) ((JComponent) c).setBorder(lineDiv4e);
	            	
	            	 else  if( showDiv2e) ((JComponent) c).setBorder(lineDiv2e);
	            	 
	          }
	          else if ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
	        	 
	            	 if( showDiv3e && showDiv2e ) ((JComponent) c).setBorder(lineDiv23e);
	            	
	            	 else  if( showDiv2e) ((JComponent) c).setBorder(lineDiv2e);
	            	 else if( showDiv3e ) ((JComponent) c).setBorder(lineDiv3e);
	            	 
	          }
	          else if ((!w.hasStrukturLevel(1)) && (!w.hasStrukturLevel(2)) && (!w.hasStrukturLevel(3)) && w.hasStrukturLevel(4)){
	        	  if( showDiv4e) ((JComponent) c).setBorder(lineDiv4e);
	          }
	          else if ((!w.hasStrukturLevel(1)) && (!w.hasStrukturLevel(2)) && w.hasStrukturLevel(3) && (!w.hasStrukturLevel(4))){
	        	  if( showDiv3e) ((JComponent) c).setBorder(lineDiv3e);
	          }
	          else if ((!w.hasStrukturLevel(1)) && w.hasStrukturLevel(2) && (!w.hasStrukturLevel(3)) && (!w.hasStrukturLevel(4))){
	        	  if( showDiv2e) ((JComponent) c).setBorder(lineDiv2e);
	          }
	          else if (w.hasStrukturLevel(1) && (!w.hasStrukturLevel(2)) && (!w.hasStrukturLevel(3)) && (!w.hasStrukturLevel(4))){
	        	  if( showDiv1e) ((JComponent) c).setBorder(lineDiv1e);
	          }      
	            
	            
          fidalWordList.repaint();
	      }
     
	 
	   //   else {
        //	  WortListModel model=(WortListModel)list.getModel();
             // int  n =model.getFlagAnnotStructAt(index);
          //    WortNode w= (WortNode)model.elementAt(index);
            // c.setBackground(w.getFcolor());
             //if (translitWordList.isSelectedIndex(index)) c.setForeground(Color.BLUE);
             // if (w.getFidalLabel(typScript,typDoc).equals("\u204b")) c.setBackground(Color.YELLOW);
            //} 
	        
	            
	            return( this );
	        }
	    }
	 
	 
	 class MyLetterListRenderer extends DefaultListCellRenderer
	 {
		 
		    

	     public Component getListCellRendererComponent( JList list,
	             Object value, int index, boolean isSelected,
	             boolean cellHasFocus )
	     {
	         Component c=super.getListCellRendererComponent( list, value, index,
	                 isSelected, cellHasFocus );
	//System.out.println("CEll "+value);
	         
	         
	        /* String s=""+value;
	        
	         if (s.equals("*")) {
	         	
	         	c.setBackground(Color.YELLOW);
	         }
	         if (s.equals("|")) {
	          	
	          	c.setBackground(Color.CYAN);
	          }*/
	         

	         return( this );
	     }
	 }
}
