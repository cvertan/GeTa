import java.io.*;
import javax.swing.JFileChooser;
public class Utils {
    private static String lastDir = null;

    public static JFileChooser getFileChooser() {
        if(lastDir != null) {
            JFileChooser fc = new JFileChooser(lastDir);
            return fc;
        } else {
            JFileChooser fc = new JFileChooser();
            return fc;
        }
    }

    public static void setLastDir(File file) {
        lastDir = file.getParent();
    }
}