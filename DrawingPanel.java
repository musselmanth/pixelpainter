import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {

   private static final long serialVersionUID = -2694980854822935795L;
   private static Pixel[][] pixels;
   private static int gridHeight;
   private static int gridWidth;
   private static final int defaultWidth = 20;
   private static final int defaultHeight = 20;
   private static Color drawColor;


   public DrawingPanel(int width, int height) {
      gridHeight = height;
      gridWidth = width;
      drawColor = SettingsPanel.getColor();
      GridLayout grid = new GridLayout(width, height);
      this.setLayout(grid);
      pixels = new Pixel[gridWidth][gridHeight];

      for(int i = 0; i < gridWidth; ++i) {
         for(int j = 0; j < gridHeight; ++j) {
            if((i % 2 != 0 || j % 2 != 0) && (i % 2 == 0 || j % 2 == 0)) {
               pixels[i][j] = new Pixel(Color.getHSBColor(1.0F, 0.0F, 0.08F));
            } else {
               pixels[i][j] = new Pixel(Color.black);
            }

            pixels[i][j].setOpaque(true);
            this.add(pixels[i][j]);
            pixels[i][j].addMouseListener(new pixelListener(i, j));
         }
      }

   }

   public static Pixel[][] getPixels() {
      return pixels;
   }

   public static Color getDrawColor() {
      return drawColor;
   }

   public static void setDrawColor(Color x) {
      drawColor = x;
   }

   public static int getDefaultWidth() {
      return 20;
   }

   public static int getDefaultHeight() {
      return 20;
   }

   public static int getGridWidth() {
      return gridWidth;
   }

   public static int getGridHeight() {
      return gridHeight;
   }
}
