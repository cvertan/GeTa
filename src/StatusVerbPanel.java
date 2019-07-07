import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
/** 
 * builds the status panel with value in case of a verbs: Absolute state, Construct state and Pronominal state
 * @author Cristina Vertan
 *
 */
public class StatusVerbPanel {
	JPanel status;
	JRadioButton absolute;
	JRadioButton construct;
	JRadioButton zeroconstruct;
	JRadioButton prono;
	JRadioButton none;
	ButtonGroup statusGroup;
	public StatusVerbPanel(String s){
		 status=new JPanel(new GridBagLayout());
    	//casePanel.setPreferredSize(new Dimension(300,100));
    	status.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
    	JLabel statusLabel=new JLabel("Status");
    	//statusLabel.setForeground(Color.YELLOW);
    	GridBagConstraints g3=new GridBagConstraints();
    	g3.gridy=0;
    	g3.weightx=0.0;
    	g3.weighty=0.0;
    	g3.anchor=GridBagConstraints.NORTHWEST;
    	g3.fill=GridBagConstraints.BOTH;
    	g3.insets.top=5;
    	g3.insets.bottom=5;
    	g3.insets.left=5;
    	g3.insets.right=5;
    	g3.gridwidth=2;
    	 absolute=new JRadioButton("Absolute State");
    	 construct=new JRadioButton("Construct State");
    	 zeroconstruct=new JRadioButton("0-Construct State");
    	 none=new JRadioButton("None");
    	prono=new JRadioButton("Pronominal State");
       if(s.equalsIgnoreCase("Absolute state"))absolute.setSelected(true);
       else if (s.equalsIgnoreCase("Construct state")) construct.setSelected(true);
       else if (s.equalsIgnoreCase("0-Construct state")) zeroconstruct.setSelected(true);
    //	else if(s.equalsIgnoreCase("Pronominal state")) prono.setSelected(true);
    	else none.setSelected(true);
    	statusGroup=new ButtonGroup();
    	statusGroup.add(absolute);
    	statusGroup.add(construct);
    	statusGroup.add(zeroconstruct);
    //	statusGroup.add(prono);
    	statusGroup.add(none);
    	g3.gridy=1;
    	status.add(statusLabel,g3);
    	g3.gridy=2;
    	status.add(absolute,g3);
    	g3.gridy=3;
    	status.add(construct,g3);
    	g3.gridy=4;
    	status.add(zeroconstruct,g3);
    	//g3.gridy=5;
    	//status.add(prono,g3);
	}
   public JPanel getStatusPanel(){
	   return status;
   }
   public String getStatusValue(){
	   String val="";
	   if (absolute.isSelected()) val="Absolute state";
	   if (construct.isSelected()) val= "Construct state";
	   if (zeroconstruct.isSelected()) val= "0-Construct state";
	//   if  (prono.isSelected()) val="Pronominal state";
	   return val;
   }
   public ButtonGroup getStatusGrp(){
	   return statusGroup;
   }
   public JRadioButton getStatus1(){
	   return absolute;
   }
   public JRadioButton getStatus2(){
	   return construct;
   }
   public JRadioButton getStatus3(){
	   return zeroconstruct;
   }
   public JRadioButton getStatus4(){
	   return prono;
   }
   public JRadioButton getStatusVoid(){
	   return none;
   }
   public void clearValues(){
	   none.setSelected(true);
   }
}
