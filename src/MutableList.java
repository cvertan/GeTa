import javax.swing.*;
class MutableList extends JList {
    MutableList() {
	super(new DefaultListModel());
    }
    DefaultListModel getContents() {
	return (DefaultListModel)getModel();
    }
} 