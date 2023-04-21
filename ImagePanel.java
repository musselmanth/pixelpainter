import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

   private BufferedImage image;


   public ImagePanel() {
      this.setBackground(Color.getHSBColor(1.0F, 0.0F, 0.03F));
      Dimension d = new Dimension(this.getPreferredSize());
      d.width = 7;
      this.setPreferredSize(d);
   }

   public void update(char a, float h, float s, float b) {
      int height = SettingsPanel.hSlider.getHeight() - 16;
      this.image = new BufferedImage(10, height, 1);
      int rc;
      int cc;
      float b2;
      if(a == 104) {
         for(rc = 0; rc < height; ++rc) {
            for(cc = 0; cc < 10; ++cc) {
               b2 = 1.0F - (float)rc / (float)height;
               this.image.setRGB(cc, rc, Color.getHSBColor(b2, s, b).getRGB());
            }
         }
      }

      if(a == 115) {
         for(rc = 0; rc < height; ++rc) {
            for(cc = 0; cc < 10; ++cc) {
               b2 = 1.0F - (float)rc / (float)height;
               this.image.setRGB(cc, rc, Color.getHSBColor(h, b2, b).getRGB());
            }
         }
      }

      if(a == 98) {
         for(rc = 0; rc < height; ++rc) {
            for(cc = 0; cc < 10; ++cc) {
               b2 = 1.0F - (float)rc / (float)height;
               this.image.setRGB(cc, rc, Color.getHSBColor(h, s, b2).getRGB());
            }
         }
      }

   }

   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(this.image, 8, 8, (ImageObserver)null);
   }
}
