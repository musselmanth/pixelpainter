import javax.swing.ImageIcon;

public class Main {

   public static MainFrame frame;


   public static void main(String[] args) {
      frame = new MainFrame("pixelPAINTER");
      ImageIcon icon = new ImageIcon("icon.png");
      frame.setIconImage(icon.getImage());
      frame.setVisible(true);
   }
}
