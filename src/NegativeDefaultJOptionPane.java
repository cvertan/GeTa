import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class NegativeDefaultJOptionPane {

public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType) {
    List<Object> options = new ArrayList<Object>();
    Object defaultOption;
    switch(optionType){
    case JOptionPane.OK_CANCEL_OPTION:
        options.add(UIManager.getString("OptionPane.okButtonText"));
        options.add(UIManager.getString("OptionPane.cancelButtonText"));
        defaultOption = UIManager.getString("OptionPane.cancelButtonText");
        break;
    case JOptionPane.YES_NO_OPTION:
        options.add(UIManager.getString("OptionPane.yesButtonText"));
        options.add(UIManager.getString("OptionPane.noButtonText"));
        defaultOption = UIManager.getString("OptionPane.noButtonText");
        break;
    case JOptionPane.YES_NO_CANCEL_OPTION:
        options.add(UIManager.getString("OptionPane.yesButtonText"));
        options.add(UIManager.getString("OptionPane.noButtonText"));
        options.add(UIManager.getString("OptionPane.cancelButtonText"));
        defaultOption = UIManager.getString("OptionPane.cancelButtonText");
        break;
        default:
            throw new IllegalArgumentException("Unknown optionType "+optionType);
    }
    return JOptionPane.showOptionDialog(parentComponent, message, title, optionType, JOptionPane.QUESTION_MESSAGE, null, options.toArray(), defaultOption);
}

}