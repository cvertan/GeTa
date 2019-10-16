import net.atlanticbb.tantlinger.shef.*;
import java.awt.Dimension;
public class NoWrapJTextPane extends HTMLEditorPane {
  
 /*   public boolean getScrollableTracksViewportWidth() {
        // Only track viewport width when the viewport is wider than the preferred width
        return getUI().getPreferredSize(this).width 
            <= getParent().getSize().width;
    };*/

    @Override
    public Dimension getPreferredSize() {
        // Avoid substituting the minimum width for the preferred width when the viewport is too narrow
        return getUI().getPreferredSize(this);
    };
}