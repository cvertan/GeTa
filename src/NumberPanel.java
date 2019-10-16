import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class NumberPanel {
	JPanel number;
	JRadioButton singularButton;
	JRadioButton pluralButton ;
	JRadioButton collectiveButton;
	JRadioButton unmarkedButton;
	JRadioButton internalButton;
	 JRadioButton externalButton;
	 JRadioButton plplButton ;
	 JRadioButton nonIE;
	public NumberPanel(String n){
		 number = new JPanel(new GridBagLayout());
    	number.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	//number.setPreferredSize(new Dimension(300,100));
    	 GridBagConstraints g1=new GridBagConstraints();
    	 JLabel numberLabel=new JLabel("Number");
    	 //numberLabel.setForeground(Color.BLUE);
    	 singularButton = new JRadioButton("Singular");
    	 singularButton.setSelected(true);
    	  pluralButton = new JRadioButton("Plural");
    	 collectiveButton = new JRadioButton("Collective");
    	 unmarkedButton = new JRadioButton("unmarked");
    	internalButton = new JRadioButton("internal");
    	 externalButton = new JRadioButton("external");
    	 nonIE=new JRadioButton("none");
    	 plplButton = new JRadioButton("plural");
    	 ButtonGroup groupNr = new ButtonGroup();
    	 groupNr.add(singularButton);
    	 groupNr.add(pluralButton);
    	 groupNr.add(collectiveButton);
    	 groupNr.add(unmarkedButton);
    	 ButtonGroup pl=new ButtonGroup();
    	 pl.add(internalButton);
    	 pl.add(externalButton);
    	 pl.add(plplButton);
    	 pl.add(nonIE);
    	 singularButton.setSelected(true);
 		internalButton.setEnabled(false);
			externalButton.setEnabled(false);
			plplButton.setEnabled(false);
			nonIE.setSelected(true);
    	 if(n.indexOf("Singular")>=0) {singularButton.setSelected(true);
    		internalButton.setEnabled(false);
			externalButton.setEnabled(false);
			plplButton.setEnabled(false);
			nonIE.setSelected(true);
    	 }
    	 else if (n.indexOf("PluralI")>=0){
    		 pluralButton.setSelected(true);
    		 internalButton.setSelected(true);
    	 }
    	 else if (n.indexOf("PluralE")>=0){
    		 pluralButton.setSelected(true);
    		 externalButton.setSelected(true);
    	 }
    	 else if (n.indexOf("PluralPL")>=0){
    		 pluralButton.setSelected(true);
    		 plplButton.setSelected(true);
    	 }
    	 else if (n.indexOf("Plural")>=0){
    		 pluralButton.setSelected(true);
    		
    	 }
    	 else if (n.indexOf("Collective")>=0){
    		 collectiveButton.setSelected(true);
    		 
    	 }
    	 else if (n.indexOf("Unmarked")>=0){
    		unmarkedButton.setSelected(true);
    		
    	 }
    	 
    	 g1.gridx=0;
	    	g1.gridy=0;
	    	g1.weightx=0.0;
	    	g1.weighty=0.0;
	    	g1.anchor=GridBagConstraints.NORTHWEST;
	    	g1.fill=GridBagConstraints.BOTH;
	    	g1.insets.top=5;
	    	g1.insets.bottom=5;
	    	g1.insets.left=5;
	    	g1.insets.right=5;
	    	g1.gridwidth=2;
	    	number.add(numberLabel,g1);
	    	g1.gridwidth=1;
	    	g1.gridy=1;
	    	number.add(singularButton,g1);
	    	g1.gridy=2;
	    	number.add(pluralButton,g1);
	    	g1.gridx=1;
	    	number.add(internalButton,g1);
	    	g1.gridy=3;
	    	number.add(externalButton,g1);
	    	g1.gridy=4;
	    	number.add(plplButton,g1);
	    	g1.gridx=0;
	    	g1.gridy=5;
	    	number.add(collectiveButton,g1);
	    	g1.gridx=0;
	    	g1.gridy=6;
	    	number.add(unmarkedButton,g1);
	    	
	    	singularButton.addItemListener(new ItemListener(){
	    		public void itemStateChanged(ItemEvent e){
	    			internalButton.setEnabled(false);
	    			externalButton.setEnabled(false);
	    			plplButton.setEnabled(false);
	    			nonIE.setSelected(true);
	    		}
	    	});
	    	
	    	pluralButton.addItemListener(new ItemListener(){
	    		public void itemStateChanged(ItemEvent e){
	    			internalButton.setEnabled(true);
	    			externalButton.setEnabled(true);
	    			plplButton.setEnabled(true);
	    			nonIE.setSelected(true);
	    			//singularButton.setEnabled(false);
	    		}
	    	});
	}
	public JPanel getNumberPanel(){
		return number;
	}
	public String getNumberValue(){
		String val="";
		if(singularButton.isSelected()){ val="Singular";
		internalButton.setEnabled(true);
		externalButton.setEnabled(true);
		plplButton.setEnabled(true);
		nonIE.setSelected(true);
		}
		if (pluralButton.isSelected()){
			if (internalButton.isSelected()) val="PluralI";
			else if(externalButton.isSelected()) val="PluralE";
			else if(plplButton.isSelected()) val="PluralPL";
			else val="Plural";
		}
		
		if (collectiveButton.isSelected()) val="Collective";
		if(unmarkedButton.isSelected()) val="Unmarked";
		return val;
	}
	
		

}
