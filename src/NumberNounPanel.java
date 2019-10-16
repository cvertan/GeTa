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
import javax.swing.JCheckBox;

public class NumberNounPanel {
	JPanel number;
	JRadioButton singularPButton;
	JRadioButton pluralExtButton ;
	JRadioButton pluralIntButton ;
	JRadioButton pluralPlButton ;
	JRadioButton unmarkedPButton;
	 JCheckBox singularS;
	 JCheckBox pluralS;
	 JCheckBox unmarkedS;
	 
	public NumberNounPanel(String n){
		 number = new JPanel(new GridBagLayout());
    	number.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	 GridBagConstraints g1=new GridBagConstraints();
    	 JLabel numberLabel=new JLabel("Number");
    	 singularPButton = new JRadioButton("Singular");
    	 singularPButton.setSelected(true);
    	  pluralExtButton = new JRadioButton("Plural external");
    	  pluralIntButton = new JRadioButton("Plural internal");
    	  pluralPlButton = new JRadioButton("Plural of the plural");
    	 unmarkedPButton = new JRadioButton("Unmarked");
    	 ButtonGroup patternGroup =new ButtonGroup();
    	 patternGroup.add(singularPButton);
    	 patternGroup.add(pluralExtButton);
    	 patternGroup.add(pluralIntButton);
    	 patternGroup.add(pluralPlButton);
    	 patternGroup.add(unmarkedPButton);
    	 singularS=new JCheckBox("Singular");
    	 pluralS=new JCheckBox("Plural");
    	  unmarkedS=new JCheckBox("Unmarked");
    	  singularPButton.setSelected(true);
    	 // if(n.isEmpty()) singularS.setSelected(true);
    	  JLabel patternL=new JLabel("Pattern");
    	  JLabel syntaxL=new JLabel("Syntax");
    	 if(n.indexOf("SingularP")>=0) {singularPButton.setSelected(true);
    	 }
    	 else if (n.indexOf("PluralExtP")>=0){
    		 pluralExtButton.setSelected(true);
    	 }
    	 else if (n.indexOf("PluralIntP")>=0){
    		 pluralIntButton.setSelected(true);
    		
    	 }
    	 else if (n.indexOf("PluralPlP")>=0){
    		 pluralPlButton.setSelected(true);
    		
    	 }
    	 else if(n.indexOf("Unmarked")>=0){
    		 unmarkedPButton.setSelected(true);
    		
    	 }
    	
        if(n.indexOf("SingularS")>=0) {singularS.setSelected(true);
    	 }
    	 else if (n.indexOf("PluralS")>=0){
    		 pluralS.setSelected(true);
    	 }
    	 else if(n.indexOf("UnmarkedS")>=0){
    		 unmarkedS.setSelected(true);
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
	    	number .add(patternL,g1);
	    	g1.gridx=1;
	    	number.add(singularPButton,g1);
	    	g1.gridy=2;
	    	number.add(pluralExtButton,g1);
	    	g1.gridy=3;
	    	number.add(pluralIntButton,g1);
	    	g1.gridy=4;
	    	number.add(pluralPlButton,g1);
	    	g1.gridy=5;
	    	number.add(unmarkedPButton,g1);
	    	g1.gridx=0;g1.gridy=6;
	    	number.add(syntaxL,g1);
	    	g1.gridx=1;
	    	number.add(singularS,g1);
	    	g1.gridy=7;
	    	number.add(pluralS,g1);
	    	g1.gridy=8;
	    	number.add(unmarkedS,g1);
	    	;
	    	
	    	singularS.addItemListener(new ItemListener(){
	    		public void itemStateChanged(ItemEvent e){
	    			if (singularS.isSelected()){
	    				unmarkedS.setSelected(false);
	    				singularS.setSelected(true);
	    				
	    			}
	    		}
	    	});
	    	singularPButton.addItemListener(new ItemListener(){
	    		public void itemStateChanged(ItemEvent e){
	    			if (singularPButton.isSelected()){
	    				
	    				//singularS.setSelected(true);
	    				
	    			}
	    		}
	    	});
	    	pluralExtButton.addItemListener(new ItemListener(){
	    		public void itemStateChanged(ItemEvent e){
	    			if (pluralExtButton.isSelected()){
	    				
	    				//pluralS.setSelected(true);
	    				
	    			}
	    		}
	    	});
	    	pluralIntButton.addItemListener(new ItemListener(){
	    		public void itemStateChanged(ItemEvent e){
	    			if (pluralIntButton.isSelected()){
	    				
	    			//	pluralS.setSelected(true);
	    				
	    			}
	    		}
	    	});
	    	pluralPlButton.addItemListener(new ItemListener(){
	    		public void itemStateChanged(ItemEvent e){
	    			if (pluralPlButton.isSelected()){
	    				
	    			//	pluralS.setSelected(true);
	    				
	    			}
	    		}
	    	});
	    	unmarkedPButton.addItemListener(new ItemListener(){
	    		public void itemStateChanged(ItemEvent e){
	    			if (unmarkedPButton.isSelected()){
	    				
	    		//		unmarkedS.setSelected(true);
	    				
	    			}
	    		}
	    	});
	     	pluralS.addItemListener(new ItemListener(){
	    		public void itemStateChanged(ItemEvent e){
	    			if (pluralS.isSelected()){
	    				unmarkedS.setSelected(false);
	    				pluralS.setSelected(true);
	    				
	    			}
	    		}
	    	});
	     	unmarkedS.addItemListener(new ItemListener(){
	    		public void itemStateChanged(ItemEvent e){
	    			if (unmarkedS.isSelected()){
	    				singularS.setSelected(false);
	    				pluralS.setSelected(false);
	    				unmarkedS.setSelected(true);
	    			}
	    		}
	    	});
	    	
	    	
	}
	public JPanel getNumberNounPanel(){
		return number;
	}
	public String getNumberValue(){
		String val="";
		if(singularPButton.isSelected()){ val="SingularP";
		}
		else if (pluralExtButton.isSelected()){
			 val="PluralExtP";
			
		}
		else if (pluralIntButton.isSelected()){
			 val="PluralIntP";	
		}
		else if (pluralPlButton.isSelected()){
			 val="PluralPlP";
			
		}
		else if (unmarkedPButton.isSelected()){
			 val="unmarkedP";
			
		}
		if (singularS.isSelected()&& pluralS.isSelected()) val=val+" SingularS+ PluralS";
		else if (singularS.isSelected()) val=val+" SingularS";
		else if(pluralS.isSelected()) val=val+" PluralS";
		else if(unmarkedS.isSelected()) val=val+" UnmarkedS";
		System.out.println(val);
		return val;
	}
	
		

}
