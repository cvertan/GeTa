import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class ComboRenderer extends JLabel implements ListCellRenderer {

    public ComboRenderer() {
      setOpaque(true);
      setBorder(new EmptyBorder(1, 1, 1, 1));
    }

    public Component getListCellRendererComponent(JList list, Object value,
        int index, boolean isSelected, boolean cellHasFocus) {
      if (isSelected) {
        setBackground(list.getSelectionBackground());
        setForeground(list.getSelectionForeground());
      } else {
        setBackground(list.getBackground());
        setForeground(list.getForeground());
      }
      if (!((CanEnable) value).isEnabled()) {
        setBackground(list.getBackground());
        setForeground(UIManager.getColor("Label.disabledForeground"));
      }
      setFont(list.getFont());
      setText((value == null) ? "" : value.toString());
      return this;
    }
  }

  class ComboListener implements ActionListener {
    JComboBox combo;

    Object currentItem;

    ComboListener(JComboBox combo) {
      this.combo = combo;
      combo.setSelectedIndex(0);
      currentItem = combo.getSelectedItem();
    }

    public void actionPerformed(ActionEvent e) {
      Object tempItem = combo.getSelectedItem();
      if (!((CanEnable) tempItem).isEnabled()) {
        combo.setSelectedItem(currentItem);
      } else {
        currentItem = tempItem;
      }
    }
  }

  class ComboItem implements CanEnable {
    Object obj;

    boolean isEnable;

    ComboItem(Object obj, boolean isEnable) {
      this.obj = obj;
      this.isEnable = isEnable;
    }

    ComboItem(Object obj) {
      this(obj, true);
    }

    public boolean isEnabled() {
      return isEnable;
    }

    public void setEnabled(boolean isEnable) {
      this.isEnable = isEnable;
    }

    public String toString() {
      return obj.toString();
    }
  }


interface CanEnable {

  public void setEnabled(boolean isEnable);

  public boolean isEnabled();

}