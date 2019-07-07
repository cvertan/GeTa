
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
/**
 * builds the container for Case with following values: nominative, accusative, vocative
 * @author Cristina Vertan
 *
 */
public class Case0AccPanel {
	JPanel casep;
	JRadioButton nominative;
	JRadioButton accusative;
	JRadioButton vocative;
	JRadioButton zeroaccusative;
	JRadioButton none;
	ButtonGroup caseGroup;
	public Case0AccPanel(String s){
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
    	zeroaccusative=new JRadioButton("0-Accusative");
    	vocative=new JRadioButton("Vocative");
    	none=new JRadioButton("None");
    	if(s.equalsIgnoreCase("Nominative")) nominative.setSelected(true);
    	else if (s.equalsIgnoreCase("Accusative")) accusative.setSelected(true);
    	else if (s.equalsIgnoreCase("0-Accusative")) zeroaccusative.setSelected(true);
    	else if (s.equalsIgnoreCase("Vocative")) vocative.setSelected(true);
    	else nominative.setSelected(true);
    	caseGroup=new ButtonGroup();
    	caseGroup.add(nominative);
    	caseGroup.add(accusative);
    	caseGroup.add(zeroaccusative);
    	caseGroup.add(vocative);
    	caseGroup.add(none);
    	g2.gridy=1;
    	casep.add(caseLabel,g2);
    	g2.gridy=2;
    	casep.add(nominative,g2);
    	g2.gridy=3;
    	casep.add(accusative,g2);
    	g2.gridy=4;
    	casep.add(zeroaccusative,g2);
    	g2.gridy=5;
    	casep.add(vocative,g2);
	}
	public JPanel getCasePanel(){
		return casep;
	}
    public String getCaseValue(){
    	
    	String value="";
    	if (nominative.isSelected()) value= "Nominative";
    	else if (accusative.isSelected()) value="Accusative";
    	else if (zeroaccusative.isSelected()) value="0-Accusative";
    	else if (vocative.isSelected()) value= "Vocative";
    	
    	return value;
    }
    public ButtonGroup getCaseGrp(){
    	return caseGroup;
    }
    
    public JRadioButton getCase1(){
    	return nominative;
    }
    public JRadioButton getCase2(){
    	return accusative;
    }
    public JRadioButton getCase3(){
    	return zeroaccusative;
    }
    public JRadioButton getCase4(){
    	return vocative;
    }
    public JRadioButton getCaseVoid(){
    	return none;
    }
    public void clearValues(){
    	none.setSelected(true);
    }
}
