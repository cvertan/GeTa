import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DistancePanel {
   JPanel dist;
   JRadioButton  nearBox;
   JRadioButton  farBox; 
   JRadioButton none;
	public DistancePanel(String s){
		dist=new JPanel(new GridBagLayout());
    	GridBagConstraints g=new GridBagConstraints();
    	g.gridx=0;
    	g.gridy=0;
    	g.weightx=0.0;
    	g.weighty=0.0;
    	g.anchor=GridBagConstraints.NORTHWEST;
    	g.fill=GridBagConstraints.BOTH;
    	g.insets.top=5;
    	g.insets.bottom=5;
    	g.insets.left=5;
    	g.insets.right=5;
    	
         JLabel distLabel= new JLabel("Deixis");
        // genderLabel.setForeground(Color.CYAN);
        // gender.setPreferredSize(new Dimension(300,100));
         dist.setBorder(BorderFactory.createLineBorder(Color.CYAN));
    	  nearBox = new JRadioButton("near");
    	farBox = new JRadioButton("far");
    	none=new JRadioButton("none");
    	if(s.equalsIgnoreCase("Near")) nearBox.setSelected(true);
    	else if (s.equalsIgnoreCase("Far")) farBox.setSelected(true);
    	else none.setSelected(true);
    	 ButtonGroup distGroup=new ButtonGroup();
    	 distGroup.add(nearBox);
    	 distGroup.add(farBox);
    	 distGroup.add(none);
    	 g.gridwidth=2;
    	 dist.add(distLabel,g);
    	 g.gridwidth=1;
    	 g.gridy=1;
    	 dist.add(nearBox,g);
    	 g.gridy=2;
    	 dist.add(farBox,g);
    	 
	}
	public JPanel getDistPanel(){
		return dist;
	}
	
	public String getDistanceValue(){
		String val="";
		if (nearBox.isSelected()) val="Near";
		else if (farBox.isSelected()) val="Far";
		
		return val;
	}
}