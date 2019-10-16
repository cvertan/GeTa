import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;



	class ChildFrame extends JInternalFrame {
		public ChildFrame(String title, Color c,int op){
			super (title);
			setIconifiable(true);
			setMaximizable(true);
			this.setResizable(true);
			this.setClosable(true);
			this.setDefaultCloseOperation(op);
			setBackground(c);

		}
		
	}
