import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

   private static final long serialVersionUID = 2269971701250845501L;
   private static SettingsPanel settings;
   public static DrawingPanel draw;
   public static boolean clicking;
   public static boolean rightClicking;
   public static boolean isSampling = false;


   public MainFrame(String title) {
      super(title);
      this.setSize(600, 450);
      this.setResizable(false);
      this.setDefaultCloseOperation(3);
      BorderLayout bl = new BorderLayout();
      this.setLayout(bl);
      settings = new SettingsPanel();
      draw = new DrawingPanel(DrawingPanel.getDefaultWidth(), DrawingPanel.getDefaultWidth());
      this.add(settings, "West");
      this.add(draw, "East");
      this.pack();
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
      settings.hImage.update('h', 1.0F, 1.0F, 1.0F);
      settings.sImage.update('s', 1.0F, 1.0F, 1.0F);
      settings.bImage.update('b', 1.0F, 1.0F, 1.0F);
   }

   public void reset(int size) {
      draw.setVisible(false);
      this.remove(draw);
      draw = new DrawingPanel(size, size);
      this.add(draw, "East");
      draw.setVisible(true);
      this.pack();
      int rval = SettingsPanel.getColor().getRed();
      int bval = SettingsPanel.getColor().getBlue();
      int gval = SettingsPanel.getColor().getGreen();
      float[] hsb = new float[3];
      Color.RGBtoHSB(rval, gval, bval, hsb);
      float hval = hsb[0];
      float sval = hsb[1];
      float brval = hsb[2];
      settings.hImage.update('h', hval, sval, brval);
      settings.sImage.update('s', hval, sval, brval);
      settings.bImage.update('b', hval, sval, brval);
      settings.hImage.repaint();
      settings.sImage.repaint();
      settings.bImage.repaint();
   }

   public void saveImage(int multiplier, int background, String dir, String filename) {
      int dimension = DrawingPanel.getGridWidth() * multiplier;
      BufferedImage x = new BufferedImage(dimension, dimension, 2);

      for(int e = 0; e < dimension; ++e) {
         for(int j = 0; j < dimension; ++j) {
            int hi = (int)Math.ceil((double)e / (double)multiplier) - 1;
            int hj = (int)Math.ceil((double)j / (double)multiplier) - 1;
            if(hi == -1) {
               hi = 0;
            }

            if(hj == -1) {
               hj = 0;
            }

            Color y = new Color(1.0F, 1.0F, 1.0F, 0.0F);
            if(DrawingPanel.getPixels()[hi][hj].getBackground() != DrawingPanel.getPixels()[hi][hj].getDefaultColor()) {
               y = DrawingPanel.getPixels()[hi][hj].getBackground();
            } else {
               if(background == 1) {
                  y = Color.white;
               }

               if(background == 2) {
                  y = Color.black;
               }

               if(background == 3) {
                  y = DrawingPanel.getPixels()[hi][hj].getBackground();
               }
            }

            x.setRGB(j, e, y.getRGB());
         }
      }

      try {
         File var13 = new File(dir + "/" + filename + ".png");
         ImageIO.write(x, "png", var13);
      } catch (IOException var12) {
         System.out.println("failed.");
      }

   }
}
