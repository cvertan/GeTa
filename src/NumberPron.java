import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class NumberPron {
	JPanel number;
	JRadioButton singularButton;
	JRadioButton pluralButton;
	JRadioButton none;
	ButtonGroup groupNr;
	public NumberPron(String s){
		 number = new JPanel(new GridBagLayout());
    	number.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	
    	//number.setPreferredSize(new Dimension(300,100));
    	 GridBagConstraints g1=new GridBagConstraints();
    	 JLabel numberLabel=new JLabel("Number");
    	// numberLabel.setForeground(Color.BLUE);
    	  singularButton = new JRadioButton("Singular");
    	  pluralButton = new JRadioButton("Plural");
    	  none=new JRadioButton("None");
    	  if (s.equalsIgnoreCase("Singular")) singularButton.setSelected(true);
    	  else if(s.equalsIgnoreCase("Plural")) pluralButton.setSelected(true);
    	  else none.setSelected(true);
    	 groupNr = new ButtonGroup();
    	 groupNr.add(singularButton);
    	 groupNr.add(pluralButton);
    	 groupNr.add(none);
 
    	 g1.gridx=0;
	    	g1.gridy=0;
	    	g1.weightx=0.0;
	    	g1.weighty=0.0;
	    	g1.anchor=GridBagConstraints.NORTHWEST;
	    	g1.fill=GridBagConstraints.BOTH;
	    	g1.insets.top=5;
	    	g1.insets.bottom=2;
	    	g1.insets.left=5;
	    	g1.insets.right=5;
	    	g1.gridwidth=1;
	    	number.add(numberLabel,g1);
	    	g1.gridwidth=1;
	    	g1.gridy=1;
	    	number.add(singularButton,g1);
	    	g1.gridy=2;
	    	number.add(pluralButton,g1);
	    	
	}
	public JPanel getNumberPanel(){
		return number;
	}
	public String getNumberValue(){
		String val="";
		if(singularButton.isSelected()) val="Singular";
		else if (pluralButton.isSelected()){
			val="Plural";
		}
		else val="";
		return val;
	}
  public ButtonGroup getNumberGrp(){
	  return groupNr;
  }
  public void clearValues(){
	  none.setSelected(true);
  }
  public JRadioButton getNumber1(){
	  return singularButton;
  }
  public JRadioButton getNumber2(){
	  return pluralButton;
  }
  public JRadioButton getNumberVoid(){
	  return none;
  }
}
