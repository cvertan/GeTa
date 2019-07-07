
import javax.swing.tree.*;

/**
 * builds the tree of PoS for the Search panel as well as for the linguistic annotation
 * @author Cristina Vertan
 * @see TagGUI
 *
 */
class JTreePOS  {
	String[] Nouns={"Common Noun","Proper Name"};
    String[] Pronouns={"Independent Personal Pronoun","Pronominal Suffix","Subject Pronoun Base","Object Pronoun Base","Possesive Pronoun Base","Demonstrative Pronoun","Relative Pronoun","Interrogative Pronoun","Pronoun of Totality Base","Pronoun of Solitude Base"};
    String[] Numerals={"Cardinal Numeral","Ordinal Numeral"};
    String[] Existentials={"Existential Affirmative Base","Existential Negative Base"};
    String[] Adverbs={"Interrogative Adverb","Other Adverb"};
    String[] FurtherParticles={"Accusative Particle","Affirmative Particle","Deictic Imperative Particle","Interrogative Particle","Negative Particle","Presentational Particle Base","Quotative Particle","Vocative Particle","Other Particle"};
   

	public DefaultMutableTreeNode createNodes() {
		
	    DefaultMutableTreeNode category = null;
	    DefaultMutableTreeNode category1 = null;
	    DefaultMutableTreeNode top = null;
	    DefaultMutableTreeNode POS = null;
	  
	   top = new DefaultMutableTreeNode("POS G\u01dd\u02bf\u01ddz");
	    category = new DefaultMutableTreeNode("Noun");
	    
	    for (int i=0;i<Nouns.length;i++){
	    //original Tutorial
	       POS = new DefaultMutableTreeNode(Nouns[i]);
	     category.add(POS);
	    }
	    category1 = new DefaultMutableTreeNode("Nominal");
	    category1.add(category);
	    category = new DefaultMutableTreeNode("Pronoun");
	    for (int i=0;i<Pronouns.length;i++){
		    //original Tutorial
		       POS = new DefaultMutableTreeNode(Pronouns[i]);
		     category.add(POS);
		    }
	    category1.add(category);
	    category = new DefaultMutableTreeNode("Numeral");
	    for (int i=0;i<Numerals.length;i++){
		    //original Tutorial
		       POS = new DefaultMutableTreeNode(Numerals[i]);
		     category.add(POS);
		    }
	    category1.add(category);
	    top.add(category1);
	    category1 = new DefaultMutableTreeNode("Verb");
	    top.add(category1);
	    
	    category1 = new DefaultMutableTreeNode("Existential");
	    for (int i=0;i<Existentials.length;i++){
		    //original Tutorial
		       POS = new DefaultMutableTreeNode(Existentials[i]);
		     category1.add(POS);
		    }
	    top.add(category1);
	    
	    category1 = new DefaultMutableTreeNode("Particle");
	    category = new DefaultMutableTreeNode("Adverb");
	    for (int i=0;i<Adverbs.length;i++){
		    //original Tutorial
		       POS = new DefaultMutableTreeNode(Adverbs[i]);
		     category.add(POS);
		    }
	    category1.add(category);
	    category = new DefaultMutableTreeNode("Preposition");
	    category1.add(category);
	    category = new DefaultMutableTreeNode("Conjunction");
	    category1.add(category);
	    category = new DefaultMutableTreeNode("Interjection");
	    category1.add(category);
	    category = new DefaultMutableTreeNode("Further Particle");
	    for (int i=0;i<FurtherParticles.length;i++){
		    //original Tutorial
		       POS = new DefaultMutableTreeNode(FurtherParticles[i]);
		     category.add(POS);
		    }
	    category1.add(category);
	    top.add(category1);
	    category1 = new DefaultMutableTreeNode("Foreign Material");
	    top.add(category1);
	    category1 = new DefaultMutableTreeNode("Punctuation");
	    top.add(category1);
	   
	    return top;
	    
}
}
