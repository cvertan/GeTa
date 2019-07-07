import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PersonPanel {
	JPanel personp;
	JRadioButton first;
	JRadioButton second;
	JRadioButton third;
	JRadioButton none;
	ButtonGroup persoGroup;
	public PersonPanel(String s){
		personp=new JPanel(new GridBagLayout());
    	//casePanel.setPreferredSize(new Dimension(300,100));
    	personp.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
    	JLabel persoLabel=new JLabel("Person");
    	//persoLabel.setForeground(Color.MAGENTA);
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
    	 first=new JRadioButton("First");
    	 second=new JRadioButton("Second");
    	 third=new JRadioButton("Third");
    	 none=new JRadioButton("None");
    	 if(s.equalsIgnoreCase("First")) first.setSelected(true);
    	 else if(s.equalsIgnoreCase("Second")) second.setSelected(true);
    	 else if (s.equalsIgnoreCase("Third")) third.setSelected(true);
    	 else none.setSelected(true);
    	 persoGroup=new ButtonGroup();
    	persoGroup.add(first);
    	persoGroup.add(second);
    	persoGroup.add(third);
    	persoGroup.add(none);
    	personp.add(persoLabel,g2);
    	g2.gridy=1;
    	personp.add(first,g2);
    	g2.gridy=2;
    	personp.add(second,g2);
    	g2.gridy=3;
    	personp.add(third,g2);

    	
	}
	public JPanel getPersonPanel(){
		return personp;
	}
	public String getPersonValue(){
		String val="";
		if(first.isSelected()) val="First";
		else if(second.isSelected()) val="Second";
		else if(third.isSelected()) val="Third";
		else val="";
		return val;
	}
	
	public JRadioButton getPerso1(){
		return first;
	}
	public JRadioButton getPerso2(){
		return second;
	}
	public JRadioButton getPerso3(){
		return third;
	}
	public JRadioButton getPersoVoid(){
		return none;
	}

}
