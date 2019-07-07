import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.*;
import java.awt.event.*;

public class TAM{
	JPanel tense;
	JRadioButton perfect;
	JRadioButton imperfect;
	JRadioButton infinitive;
	JRadioButton imperative;
	JRadioButton subjunctive;
	JRadioButton gerund;
	JRadioButton none;
	
	public TAM(String s,JRadioButton p1,JRadioButton p2, JRadioButton p3,JRadioButton pn, JRadioButton g1,JRadioButton g2, JRadioButton gb3,JRadioButton gn,JRadioButton n1,JRadioButton n2,JRadioButton nn, JRadioButton c1,JRadioButton c2, JRadioButton c3,JRadioButton cn,JRadioButton s1,JRadioButton s2, JRadioButton s3,JRadioButton sn){
		 tense=new JPanel(new GridBagLayout());
    	//casePanel.setPreferredSize(new Dimension(300,100));
    	tense.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
    	JLabel tamLabel=new JLabel("Tense-Aspect-Mood");
    	//statusLabel.setForeground(Color.YELLOW);
    	GridBagConstraints g3=new GridBagConstraints();
    	
    	g3.gridx=0;
    	g3.weightx=0.0;
    	g3.weighty=0.0;
    	g3.anchor=GridBagConstraints.NORTHWEST;
    	g3.fill=GridBagConstraints.BOTH;
    	g3.insets.top=5;
    	g3.insets.bottom=5;
    	g3.insets.left=5;
    	g3.insets.right=5;
    	//g3.gridwidth=2;
    	 perfect=new JRadioButton("Perfect");
    	imperfect=new JRadioButton("Imperfect");
    	infinitive=new JRadioButton("Infinitive");
    	imperative=new JRadioButton("Imperative");
    	subjunctive=new JRadioButton("Subjunctive");
    	gerund=new JRadioButton("Gerund");
    	none=new JRadioButton("none");
    	if(s.equalsIgnoreCase("Perfect")) perfect.setSelected(true);
    	else if(s.equalsIgnoreCase("Imperfect")) imperfect.setSelected(true);
    	else if(s.equalsIgnoreCase("Infinitive")) infinitive.setSelected(true);
    	else if(s.equalsIgnoreCase("Imperative")) imperative.setSelected(true);
    	else if(s.equalsIgnoreCase("Gerund")) gerund.setSelected(true);
    	else if (s.equalsIgnoreCase("Subjunctive")) subjunctive.setSelected(true);
    	else none.setSelected(true);
    	ButtonGroup tamGroup=new ButtonGroup();
    	tamGroup.add(perfect);
    	tamGroup.add(imperfect);
    	tamGroup.add(infinitive);
    	tamGroup.add(imperative);
    	tamGroup.add(subjunctive);
    	tamGroup.add(gerund);
    	tamGroup.add(none);
    	g3.gridy=1;
    	tense.add(tamLabel,g3);
    	g3.gridy=2;
    	tense.add(perfect,g3);
    	g3.gridy=3;
    	tense.add(imperfect,g3);
    	g3.gridy=4;
    	tense.add(subjunctive,g3);
    	g3.gridy=2;g3.gridx=1;
    	tense.add(imperative,g3);
    	g3.gridy=3;
    	tense.add(gerund,g3);
    	g3.gridy=4;
    	tense.add(infinitive,g3);
    	final JRadioButton pp1=p1;final JRadioButton pp2=p2;final JRadioButton pp3=p3;final JRadioButton ppn=pn;
    	final JRadioButton gg1=g1;final JRadioButton gg2=g2;final JRadioButton gg3=gb3;final JRadioButton ggn=gn;
    	final JRadioButton nn1=n1;final JRadioButton nn2=n2;final JRadioButton nnn=nn;
    	final JRadioButton cc1=c1;final JRadioButton cc2=c2;final JRadioButton cc3=c3;final JRadioButton ccn=cn;
    	final JRadioButton ss1=s1;final JRadioButton ss2=s2;final JRadioButton ss3=s3;final JRadioButton ssn=sn;
    	if(none.isSelected()){
    	pp1.setEnabled(false);pp2.setEnabled(false);pp3.setEnabled(false);ppn.setSelected(true);
		gg1.setEnabled(false);gg2.setEnabled(false);gg3.setEnabled(false);ggn.setSelected(true);gg3.setText("Communis");gg3.repaint();
		nn1.setEnabled(false);nn2.setEnabled(false);nnn.setSelected(true);
		cc1.setEnabled(false);cc2.setEnabled(false);cc3.setEnabled(false);ccn.setSelected(true);
		ss1.setEnabled(false);ss2.setEnabled(false);ss3.setEnabled(false);ssn.setSelected(true);}
    	else if(infinitive.isSelected()){
    		pp1.setEnabled(false);pp2.setEnabled(false);pp3.setEnabled(false);ppn.setSelected(true);
    		gg1.setEnabled(true);gg2.setEnabled(true);gg3.setEnabled(true);gg3.setText("Unmarked");gg3.repaint();
    		nn1.setEnabled(true);nn2.setEnabled(true);
    		cc1.setEnabled(true);cc2.setEnabled(true);cc3.setEnabled(true);
    		ss1.setEnabled(true);ss2.setEnabled(true);ss3.setEnabled(true);
    	}
    	else if (imperative.isSelected()){
    		pp1.setEnabled(false);pp2.setEnabled(true);pp3.setEnabled(false);pp2.setSelected(true);
    		gg1.setEnabled(true);gg2.setEnabled(true);gg3.setEnabled(true);gg3.setText("Communis");gg3.repaint();
    		nn1.setEnabled(true);nn2.setEnabled(true);
    		cc1.setEnabled(false);cc2.setEnabled(false);cc3.setEnabled(false);ccn.setSelected(true);
    		ss1.setEnabled(false);ss2.setEnabled(false);ss3.setEnabled(false);ssn.setSelected(true);
    	}
    	else {
    		pp1.setEnabled(true);pp2.setEnabled(true);pp3.setEnabled(true);
    		gg1.setEnabled(true);gg2.setEnabled(true);gg3.setEnabled(true);gg3.setText("Communis");gg3.repaint();
    		nn1.setEnabled(true);nn2.setEnabled(true);
    		cc1.setEnabled(false);cc2.setEnabled(false);cc3.setEnabled(false);ccn.setSelected(true);
    		ss1.setEnabled(false);ss2.setEnabled(false);ss3.setEnabled(false);ssn.setSelected(true);
    	}
    	perfect.addItemListener(new ItemListener(){
    		public void itemStateChanged(ItemEvent e){
    			if(perfect.isSelected()){
    				pp1.setEnabled(true);pp2.setEnabled(true);pp3.setEnabled(true);
    				//ppn.setSelected(true);
    				gg1.setEnabled(true);gg2.setEnabled(true);gg3.setEnabled(true);gg3.setText("Communis");gg3.repaint();
    				//ggn.setSelected(true);
    				nn1.setEnabled(true);nn2.setEnabled(true);
    			//	nnn.setSelected(true);
    				cc1.setEnabled(false);cc2.setEnabled(false);cc3.setEnabled(false);
    				ccn.setSelected(true);
    				ss1.setEnabled(false);ss2.setEnabled(false);ss3.setEnabled(false);
    				ssn.setSelected(true);
    			
    			}
    		
    			
    		}
    	});
    	imperfect.addItemListener(new ItemListener(){
    		public void itemStateChanged(ItemEvent e){
    			if(imperfect.isSelected()){
    				pp1.setEnabled(true);pp2.setEnabled(true);pp3.setEnabled(true);
    				//ppn.setSelected(true);
    				gg1.setEnabled(true);gg2.setEnabled(true);gg3.setEnabled(true);gg3.setText("Communis");gg3.repaint();
    				//ggn.setSelected(true);
    				nn1.setEnabled(true);nn2.setEnabled(true);
    				//nnn.setSelected(true);
    				cc1.setEnabled(false);cc2.setEnabled(false);cc3.setEnabled(false);
    				ccn.setSelected(true);
    				ss1.setEnabled(false);ss2.setEnabled(false);ss3.setEnabled(false);
    				ssn.setSelected(true);
    			
    			}
    			
    		}
    	});
    	imperative.addItemListener(new ItemListener(){
    		public void itemStateChanged(ItemEvent e){
    			if(imperative.isSelected()){
    				pp1.setEnabled(false);pp2.setEnabled(true);pp3.setEnabled(false);
    				//pp2.setSelected(true);
    				gg1.setEnabled(true);gg2.setEnabled(true);gg3.setEnabled(true);gg3.setText("Communis");gg3.repaint();
    				//ggn.setSelected(true);
    				nn1.setEnabled(true);nn2.setEnabled(true);
    				//nnn.setSelected(true);
    				cc1.setEnabled(false);cc2.setEnabled(false);cc3.setEnabled(false);
    				ccn.setSelected(true);
    				ss1.setEnabled(false);ss2.setEnabled(false);ss3.setEnabled(false);
    				ssn.setSelected(true);
    			
    			}
    		
    		}
    	});
    	gerund.addItemListener(new ItemListener(){
    		public void itemStateChanged(ItemEvent e){
    			if(gerund.isSelected()){
    				pp1.setEnabled(true);pp2.setEnabled(true);pp3.setEnabled(true);
    				//ppn.setSelected(true);
    				gg1.setEnabled(true);gg2.setEnabled(true);gg3.setEnabled(true);gg3.setText("Communis");gg3.repaint();
    				//ggn.setSelected(true);
    				nn1.setEnabled(true);nn2.setEnabled(true);
    				//nnn.setSelected(true);
    				cc1.setEnabled(false);cc2.setEnabled(false);cc3.setEnabled(false);
    				ccn.setSelected(true);
    				ss1.setEnabled(false);ss2.setEnabled(false);ss3.setEnabled(false);
    				ssn.setSelected(true);
    			
    			}
    		
    		}
    	});
    	subjunctive.addItemListener(new ItemListener(){
    		public void itemStateChanged(ItemEvent e){
    			if(subjunctive.isSelected()){
    				pp1.setEnabled(true);pp2.setEnabled(true);pp3.setEnabled(true);
    				//ppn.setSelected(true);
    				gg1.setEnabled(true);gg2.setEnabled(true);gg3.setEnabled(true); gg3.setText("Communis");gg3.repaint();
    				//ggn.setSelected(false);
    				nn1.setEnabled(true);nn2.setEnabled(true);
    				//nnn.setSelected(true);
    				cc1.setEnabled(false);cc2.setEnabled(false);cc3.setEnabled(false);
    				ccn.setSelected(true);
    				ss1.setEnabled(false);ss2.setEnabled(false);ss3.setEnabled(false);
    				ssn.setSelected(true);
    			
    			}
    			
    		}
    	});
    	infinitive.addItemListener(new ItemListener(){
    		public void itemStateChanged(ItemEvent e){
    			if(infinitive.isSelected()){
    				pp1.setEnabled(false);pp2.setEnabled(false);pp3.setEnabled(false);
    				ppn.setSelected(true);
    				gg1.setEnabled(true);gg2.setEnabled(true);gg3.setEnabled(true);gg3.setText("Unmarked");gg3.repaint();
    				gg3.setSelected(true);
    				nn1.setEnabled(true);nn2.setEnabled(true);
    				nn1.setSelected(true);
    				cc1.setEnabled(true);cc2.setEnabled(true);cc3.setEnabled(true);
    				//ccn.setSelected(true);
    				ss1.setEnabled(true);ss2.setEnabled(true);ss3.setEnabled(true);
    				//ssn.setSelected(true);
    			}
    			
    		}
    	});
    	
	}
   public JPanel getStatusPanel(){
	   return tense;
   }
   public String getTAMValue(){
	   String val="";
	   if (perfect.isSelected()) val="Perfect";
	   if (imperfect.isSelected()) val= "Imperfect";
	   if  (infinitive.isSelected()) val="Infinitive";
	   if  (imperative.isSelected()) val="Imperative";
	   if  (subjunctive.isSelected()) val="Subjunctive";
	   if  (gerund.isSelected()) val="Gerund";
	   return val;
   }
}
