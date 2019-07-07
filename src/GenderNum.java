import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * builds the container for the gender in case of a number 
 * the possible features are masculine and feminine
 * @author Cristina Vertan
 *
 */
public class GenderNum {
   JPanel gender;
   JRadioButton  mascBox;
   JRadioButton  femBox;
   JRadioButton  unmBox;
   JRadioButton  none;
	public GenderNum(String s){
		gender=new JPanel(new GridBagLayout());
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
    	
         JLabel genderLabel= new JLabel("Gender");
        // genderLabel.setForeground(Color.CYAN);
        // gender.setPreferredSize(new Dimension(300,100));
         gender.setBorder(BorderFactory.createLineBorder(Color.RED));
    	  mascBox = new JRadioButton("masculine");
    	 femBox = new JRadioButton("feminine");
    	 unmBox = new JRadioButton("unmarked");
    	 none=new JRadioButton("none");
    	 if(s.equalsIgnoreCase("masculine")) mascBox.setSelected(true);
    	 else if(s.equalsIgnoreCase("feminine")) femBox.setSelected(true);
    	// else unmBox.setSelected(true);
    	 ButtonGroup genGroup=new ButtonGroup();
    	 genGroup.add(mascBox);
    	 genGroup.add(femBox);
    	 genGroup.add(unmBox);
    	 genGroup.add(none);
    	 g.gridwidth=2;
    	 gender.add(genderLabel,g);
    	 g.gridwidth=1;
    	 g.gridy=1;
    	 gender.add(mascBox,g);
    	 g.gridy=2;
    	 gender.add(femBox,g);
    	 g.gridy=3;
    	 gender.add(unmBox,g);
	}
	public JPanel getGenderPanel(){
		return gender;
	}
	public void clearValues(){
		none.setSelected(true);
	}
	public String getGenderValue(){
		String value="";
		 if (mascBox.isSelected()){
			value="Masculine";
			
			
		}
		 else if(femBox.isSelected()) {
			 value="Feminine";
			 
		 }
		 else if(unmBox.isSelected()){
			 value="Unmarked";
		 }
		 
		
		return value;
	}
}
