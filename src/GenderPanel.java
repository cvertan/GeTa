import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
/**
 * builds the gendel panel incase the values of the gender have to be differentited after nature, pattern and syntax
 * @author Cristina Vertan
 *
 */
public class GenderPanel {
   JPanel gender;
   JCheckBox  mascBox;
	 JCheckBox  femBox;
	 JCheckBox  unmBox;
	 JCheckBox  natureBox1;
	 JCheckBox  patternBox1;
	 JCheckBox  syntaxBox1;
	 JCheckBox  natureBox2;
	 JCheckBox  patternBox2;
	 JCheckBox  syntaxBox2;
	public GenderPanel(String f){
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
        // genderLabel.setForeground(Color.RED);
        // gender.setPreferredSize(new Dimension(300,100));
         gender.setBorder(BorderFactory.createLineBorder(Color.RED));
    	 mascBox = new JCheckBox("masculine");
    	 femBox = new JCheckBox("feminine");
    	unmBox = new JCheckBox("unmarked");
    	  natureBox1 = new JCheckBox("Nature");
    	  patternBox1 = new JCheckBox("Pattern");
    	 syntaxBox1 = new JCheckBox("Syntax");
    	 natureBox2 = new JCheckBox("Nature");
    	 patternBox2 = new JCheckBox("Pattern");
    	 syntaxBox2 = new JCheckBox("Syntax");
    	 if (f.indexOf("unmarked")>=0) {unmBox.setSelected(true);unmBox.setEnabled(true);
    	     femBox.setEnabled(false);
    	     mascBox.setEnabled(false);
    	 }
    	 else if ((f.indexOf("masculine")>=0)||(f.indexOf("feminine")>=0)){
    	 unmBox.setEnabled(false);
    	  if (f.indexOf("masculineNPS")>=0) {
    		 mascBox.setSelected(true);
    		 mascBox.setEnabled(true);
    		 natureBox1.setSelected(true);
    		 natureBox1.setEnabled(true);
    		 patternBox1.setSelected(true);
    		 patternBox1.setEnabled(true);
    		 syntaxBox1.setSelected(true);
    		 syntaxBox1.setEnabled(true);
    		 patternBox2.setEnabled(false);
    		 natureBox2.setEnabled(false);
    	 }
    	 else if (f.indexOf("masculineNS")>=0) {
    		 mascBox.setSelected(true);
    		 mascBox.setEnabled(true);
    		 natureBox1.setSelected(true);
    		 natureBox1.setEnabled(true);
    		 syntaxBox1.setSelected(true);
    		 syntaxBox1.setEnabled(true);
    		 natureBox2.setEnabled(false);
    		
    	 }
    	 else if (f.indexOf("masculineNP")>=0) {
    		 mascBox.setSelected(true);
    		 mascBox.setEnabled(true);
    		 natureBox1.setSelected(true);
    		 natureBox1.setEnabled(true);
    		 patternBox1.setSelected(true);
    		 patternBox1.setEnabled(true);
    		 natureBox2.setEnabled(false);
    		
    	 }
    	 else if (f.indexOf("masculinePS")>=0) {
    		 mascBox.setSelected(true);
    		 mascBox.setEnabled(true);
    		 syntaxBox1.setSelected(true);
    		 syntaxBox1.setEnabled(true);
    		 patternBox1.setSelected(true);
    		 patternBox1.setEnabled(true);
    		 patternBox2.setEnabled(false);
    		
    	 }
    	 else if (f.indexOf("masculineN")>=0) {
    		 mascBox.setSelected(true);
    		 mascBox.setEnabled(true);
    		 natureBox1.setSelected(true);
    		 natureBox1.setEnabled(true);
    		 natureBox2.setEnabled(false);
    		
    	 }
    	 else if (f.indexOf("masculineP")>=0) {
    		 mascBox.setSelected(true);
    		 mascBox.setEnabled(true);
    		 patternBox1.setSelected(true);
    		 patternBox1.setEnabled(true);
    		 patternBox2.setEnabled(false);
    		
    		 
    	 }
    	 else if (f.indexOf("masculineS")>=0) {
    		 mascBox.setSelected(true);
    		 mascBox.setEnabled(true);
    		 syntaxBox1.setSelected(true);
    		 syntaxBox1.setEnabled(true);
    		
    		 
    	 }
    	 else  if (f.indexOf("masculine")>=0) {
    		 mascBox.setSelected(true);
    		 mascBox.setEnabled(true);
    		
    		
    	 }
    	  if (f.indexOf("feminineNPS")>=0) {
    		 femBox.setSelected(true);
    		 natureBox2.setSelected(true);
    		 patternBox2.setSelected(true);
    		 syntaxBox2.setSelected(true);
    		 natureBox1.setEnabled(false);
    		 patternBox1.setEnabled(false);
    	 }
    	 else if (f.indexOf("feminineNS")>=0) {
    		 femBox.setSelected(true);
    		 natureBox2.setSelected(true);
    		 syntaxBox2.setSelected(true);
    		 natureBox1.setEnabled(false);
    	 }
    	 else if (f.indexOf("feminineNP")>=0) {
    		 femBox.setSelected(true);
    		 natureBox2.setSelected(true);
    		 patternBox2.setSelected(true);
    		 natureBox1.setEnabled(false);
    		 patternBox1.setEnabled(false);
    	 }
    	 else if (f.indexOf("masculinePS")>0) {
    		 mascBox.setSelected(true);
    		 syntaxBox2.setSelected(true);
    		 patternBox2.setSelected(true);
    		 patternBox1.setEnabled(false);
    	 }
    	 else if (f.indexOf("feminineN")>=0) {
    		 femBox.setSelected(true);
    		 natureBox2.setSelected(true);
    		 natureBox1.setEnabled(false);
    		 
    	 }
    	 else if (f.indexOf("feminineS")>=0) {
    		 femBox.setSelected(true);
    		 syntaxBox2.setSelected(true);
    		 
    	 }
    	 else if (f.indexOf("feminineP")>=0) {
    		 femBox.setSelected(true);
    		patternBox2.setSelected(true);
    		patternBox1.setEnabled(false);
    	 }
    	 else  if (f.indexOf("feminine")>=0) {
    		 femBox.setSelected(true);
    		
    	 }}
    	 
    	 else{
    		 syntaxBox2.setEnabled(false);
        	 natureBox2.setEnabled(false);
        	 patternBox2.setEnabled(false);
        	 syntaxBox1.setEnabled(false);
        	 patternBox1.setEnabled(false);
        	 natureBox1.setEnabled(false);
    	 }
    	 g.gridwidth=2;
    	 gender.add(genderLabel,g);
    	 g.gridwidth=1;
    	 g.gridy=1;
    	 gender.add(mascBox,g);
    	 g.gridx=1;
    	 gender.add(natureBox1,g);
    	 g.gridy=2;
    	
    	 gender.add(patternBox1,g);
    	
    	 g.gridy=3;
    	 gender.add(syntaxBox1,g);
    	
    	 g.gridy=4;g.gridx=0;
    	 gender.add(femBox,g);
    	 g.gridx=1;
    	 gender.add(natureBox2,g);
    	 
    	 g.gridy=5;
    	 gender.add(patternBox2,g);
    	
    	 g.gridy=6;
    	 gender.add(syntaxBox2,g);
    	 
    	 g.gridx=0;
    	 g.gridy=7;
    	 gender.add(unmBox,g);
    	
    	 
    	 unmBox.addItemListener(new ItemListener(){
    		 public void itemStateChanged(ItemEvent e){
    			 if (unmBox.isSelected()){
    				 mascBox.setSelected(false);
    				 mascBox.setEnabled(false);
    				 patternBox1.setSelected(false);
    				 patternBox1.setEnabled(false);
    				 syntaxBox1.setSelected(false);
    				 syntaxBox1.setEnabled(false);
    				 natureBox1.setSelected(false);
    				 natureBox1.setEnabled(false);
    				 femBox.setSelected(false);
    				 femBox.setEnabled(false);
    				 patternBox2.setSelected(false);
    				 patternBox2.setEnabled(false);
    				 syntaxBox2.setSelected(false);
    				 syntaxBox2.setEnabled(false);
    				 natureBox2.setSelected(false);
    				 natureBox2.setEnabled(false);
    			 }
    			 else {
    				 mascBox.setEnabled(true);
    				 femBox.setEnabled(true);;
    			 }
    		 }
    	 });
    	 mascBox.addItemListener(new ItemListener(){
    		 public void itemStateChanged(ItemEvent e){
    			 if(mascBox.isSelected()){
    				 if (!patternBox2.isSelected()) patternBox1.setEnabled(true);
    				 syntaxBox1.setEnabled(true);
    				 if (!natureBox2.isSelected()) natureBox1.setEnabled(true);
    				 unmBox.setSelected(false);
    				 unmBox.setEnabled(false);
    			 }
    			 else{
    				 patternBox1.setSelected(false);
    				 patternBox1.setEnabled(false);
    				syntaxBox1.setSelected(false);
    				 syntaxBox1.setEnabled(false);
    				 natureBox1.setSelected(false);
    				 natureBox1.setEnabled(false);
    				 if (!femBox.isSelected()) unmBox.setEnabled(true);
    			 }
    		 }
    	 });
    	 femBox.addItemListener(new ItemListener(){
    		 public void itemStateChanged(ItemEvent e){
    			 if(femBox.isSelected()){
    				 if (!patternBox1.isSelected()) patternBox2.setEnabled(true);
    				 syntaxBox2.setEnabled(true);
    				 if (!natureBox1.isSelected()) natureBox2.setEnabled(true);
    				 unmBox.setSelected(false);
    				 unmBox.setEnabled(false);
    			 }
    			 else{
    				 patternBox2.setSelected(false);
    				 patternBox2.setEnabled(false);
    				syntaxBox2.setSelected(false);
    				 syntaxBox2.setEnabled(false);
    				 natureBox2.setSelected(false);
    				 natureBox2.setEnabled(false);
    				 if (!mascBox.isSelected()) unmBox.setEnabled(true);
    			 }
    		 }
    	 });
    	 
    	 patternBox1.addItemListener(new ItemListener(){
    		 public void itemStateChanged(ItemEvent e){
    		    if (patternBox1.isSelected()){
    		    	patternBox2.setSelected(false);
    		    	patternBox2.setEnabled(false);
    		    }
    		    else patternBox2.setEnabled(true);
    		 }
    		 });
    	 patternBox2.addItemListener(new ItemListener(){
    		 public void itemStateChanged(ItemEvent e){
    		    if (patternBox2.isSelected()){
    		    	patternBox1.setSelected(false);
    		    	patternBox1.setEnabled(false);
    		    }
    		    else patternBox1.setEnabled(true);
    		 }
    		 });
    	 natureBox1.addItemListener(new ItemListener(){
    		 public void itemStateChanged(ItemEvent e){
    		    if (natureBox1.isSelected()){
    		    	natureBox2.setSelected(false);
    		    	natureBox2.setEnabled(false);
    		    }
    		    else natureBox2.setEnabled(true);
    		 }
    		 });
    	natureBox2.addItemListener(new ItemListener(){
    		 public void itemStateChanged(ItemEvent e){
    		    if (natureBox2.isSelected()){
    		    	natureBox1.setSelected(false);
    		    	natureBox1.setEnabled(false);
    		    }
    		    else natureBox1.setEnabled(true);
    		 }
    		 });
	}
	public JPanel getGenderPanel(){
		return gender;
	}
	public String getTypeGender(JCheckBox cb){
		String val="";
		if (cb.isSelected()){
			if (cb.getText().equals("Nature")) val="N";
			if (cb.getText().equals("Pattern")) val="P";
			if(cb.getText().equals("Syntax")) val="S";
			if (cb.getText().equals("masculine")) val="masculine";
			if (cb.getText().equals("feminine")) val="feminine";
			if (cb.getText().equals("unmarked")) val="unmarked";
		}
		return val;
	}
	public String getGenderValue(){
		String value="";
		
		value= getTypeGender(mascBox)+getTypeGender(natureBox1)+getTypeGender(patternBox1)+getTypeGender(syntaxBox1);
		if (!value.isEmpty()) value=value+" ";
		value=value+getTypeGender(femBox)+getTypeGender(natureBox2)+getTypeGender(patternBox2)+getTypeGender(syntaxBox2);
        value=value+getTypeGender(unmBox);				
        if (!value.isEmpty())if(value.lastIndexOf(" ")==value.length()-1) value=value.substring(0,value.length()-1);
		return value;
	}
	public boolean isMasculineCorrectSelected(){
		if (mascBox.isSelected()&&(natureBox1.isSelected()||syntaxBox1.isSelected()||patternBox1.isSelected())) return true;
		else if (mascBox.isSelected())return false; 
		else return true;
	}
	
	public boolean isFeminineCorrectSelected(){
		if (femBox.isSelected()&&(natureBox2.isSelected()||syntaxBox2.isSelected()||patternBox2.isSelected())) return true;
		else if (femBox.isSelected())return false; 
		else return true;
	}
}
