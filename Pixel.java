import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;

public class Pixel extends JLabel {

   private Color defaultColor;


   public Pixel(Color makeit) {
      this.defaultColor = makeit;
      this.setBackground(makeit);
      Dimension x = new Dimension(600 / DrawingPanel.getGridWidth(), 600 / DrawingPanel.getGridHeight());
      this.setPreferredSize(x);
   }

   public Color getDefaultColor() {
      return this.defaultColor;
   }
}
