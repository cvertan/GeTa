

import java.util.ArrayList;
import org.json.*;
/**
 * main class
 * @author Cristina Vertan
 *
 */
public class TaggingToolVer4 {
   public static void main(String arg[]){
	  
	   javax.swing.SwingUtilities.invokeLater(new Runnable() {

           @Override
          
           public void run() {
        	  
        	   TagGUI desktop= new TagGUI();
       		desktop.setLocation(100,100);
       		desktop.setSize(1600,1000);
       		desktop.setVisible(true);
           }
       });
	   
	
   }
}
