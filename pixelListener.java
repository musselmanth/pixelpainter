import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class pixelListener implements MouseListener {

   private int i;
   private int j;


   public pixelListener(int i, int j) {
      this.i = i;
      this.j = j;
      MainFrame.clicking = false;
      MainFrame.rightClicking = false;
   }

   public void mouseClicked(MouseEvent e) {}

   public void mousePressed(MouseEvent e) {
      if(MainFrame.isSampling) {
         Color yada = DrawingPanel.getPixels()[this.i][this.j].getBackground();
         float[] hsbvals = new float[3];
         Color.RGBtoHSB(yada.getRed(), yada.getGreen(), yada.getBlue(), hsbvals);
         System.out.println(hsbvals[0]);
         float h = hsbvals[0] * 360.0F;
         float s = hsbvals[1] * 100.0F;
         float b = hsbvals[2] * 100.0F;
         if((int)h == 0) {
            h = 360.0F;
         }

         SettingsPanel.hSlider.setValue((int)h);
         SettingsPanel.sSlider.setValue((int)s);
         SettingsPanel.bSlider.setValue((int)b);
         Main.frame.setCursor(Cursor.getDefaultCursor());
         MainFrame.isSampling = false;
      } else if(e.getButton() == 1) {
         MainFrame.clicking = true;
         DrawingPanel.getPixels()[this.i][this.j].setBackground(DrawingPanel.getDrawColor());
      } else if(e.getButton() == 3) {
         MainFrame.rightClicking = true;
         DrawingPanel.getPixels()[this.i][this.j].setBackground(DrawingPanel.getPixels()[this.i][this.j].getDefaultColor());
      }

   }

   public void mouseReleased(MouseEvent e) {
      MainFrame.clicking = false;
      MainFrame.rightClicking = false;
   }

   public void mouseEntered(MouseEvent e) {
      if(MainFrame.clicking) {
         DrawingPanel.getPixels()[this.i][this.j].setBackground(DrawingPanel.getDrawColor());
      }

      if(MainFrame.rightClicking) {
         DrawingPanel.getPixels()[this.i][this.j].setBackground(DrawingPanel.getPixels()[this.i][this.j].getDefaultColor());
      }

   }

   public void mouseExited(MouseEvent e) {}
}
