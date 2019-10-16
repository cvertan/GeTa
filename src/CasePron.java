import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
/**
 * builds the Case container for pronouns where only the values nominative or accusative are possible
 * @author Cristina Vertan
 *
 */
public class CasePron {
	JPanel casep;
	JRadioButton nominative;
	JRadioButton accusative;
	JRadioButton none;
	public CasePron(String s){
		casep=new JPanel(new GridBagLayout());
    	//casePanel.setPreferredSize(new Dimension(300,100));
    	casep.setBorder(BorderFactory.createLineBorder(Color.GREEN));
    	JLabel caseLabel=new JLabel("Case");
    	//caseLabel.setForeground(Color.GREEN);
    	GridBagConstraints g2=new GridBagConstraints();
    	g2.gridy=0;
    	g2.weightx=0.0;
    	g2.weighty=0.0;
    	g2.anchor=GridBagConstraints.NORTHWEST;
    	g2.fill=GridBagConstraints.BOTH;
    	g2.insets.top=5;
    	g2.insets.bottom=5;
    	g2.insets.left=5;
    	g2.insets.right=5;
    	g2.gridwidth=2;
    	nominative=new JRadioButton("Nominative");
    	accusative=new JRadioButton("Accusative");
    	none=new JRadioButton("none");
    	if(s.equalsIgnoreCase("Nominative")) nominative.setSelected(true);
    	else if (s.equalsIgnoreCase("Accusative")) accusative.setSelected(true);
    	else none.setSelected(true);
    	ButtonGroup caseGroup=new ButtonGroup();
    	caseGroup.add(nominative);
    	caseGroup.add(accusative);
    	caseGroup.add(none);
    	g2.gridy=1;
    	casep.add(caseLabel,g2);
    	g2.gridy=2;
    	casep.add(nominative,g2);
    	g2.gridy=3;
    	casep.add(accusative,g2);
	}
	public JPanel getCasePanel(){
		return casep;
	}
	 public String getCaseValue(){
	    	
	    	String value="";
	    	if (nominative.isSelected()) value= "Nominative";
	    	else if (accusative.isSelected()) value="Accusative";
	    
	    	return value;
	    }
	 public void clearValues(){
		 none.setSelected(true);
	 }
	 
	 public JRadioButton getNom(){
		 return nominative;
	 }

}
