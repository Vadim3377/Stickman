import java.awt.*;
import java.util.Objects;
import javax.swing.*;

// Creation date: 20.06.2023

public class Animator extends JComponent
{
      private int baseX;     // center of figure
      private int baseY;     // floor (bottom of feet)
      private int height;
      private int angleLeg=15;
      private int angleHand=15;
      private int face = 0;
      private Color color;   // color of stick figure
      
      public Animator(int center, int bottom, Color shade, int size, int face)
      {
          setSize(1000,1000);
          baseX = center;
          baseY = bottom;
          color = shade;
          height = size;
          this.face = face;
          setBackground(Color.white);
      }
      
      protected void paintComponent(Graphics g) {
          // Abnormal data validation
          if(height<50){height = 50;}
          if(height>150){height = 150;}

          int top = baseY - height; // determining the initial y-point of stickman
        
        Graphics2D g2d = (Graphics2D) g; // initialising 2D sketching module
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // defining more advanced sketching module
        g2d.setColor(color); // determine Stickman's color
        g2d.scale(1.0, 1.0); // determine the fixed scale for character
    
        //Settings for head drawing
        int head = 0; // determine the head coordinate deviation from the positon
        int neck = 25; // determine the length of the neck



        if (height < 100) // conditional settings for stickmen with other heights
        {
            head = (100 - height)*3/10; // percentage positioning for any stickman's height
            neck = neck - head; // regulating neck length for any stickman's height
        }
        g2d.drawOval (baseX-12, top-head, 25, 25); // drawing head
        
        if(face > 0)
        {
            Image img = new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("images/face" + face + ".png"))).getImage();
            g2d.drawImage(img, baseX-12, top-head, 25, 25, null);
        }
        
        //neck
        g2d.drawLine (baseX, top+neck, baseX, baseY-6*height/10);
        // draw the body
        g2d.drawLine (baseX, baseY-6*height/10, baseX, baseY-height/10);


        // draw the legs
        g.drawLine (baseX, baseY-height/10, baseX-angleLeg, baseY+2*height/10);  // legs
        g.drawLine (baseX-angleLeg, baseY+2*height/10, baseX-20, baseY+4*height/10);  // legs

        g.drawLine (baseX, baseY-height/10, baseX+angleLeg, baseY+2*height/10);
        g.drawLine (baseX+angleLeg, baseY+2*height/10, baseX+20, baseY+4*height/10);
        // draw the arms
        
        g.drawLine (baseX, baseY-6*height/10, baseX-angleHand, baseY-4*height/10);  // arms
        g.drawLine (baseX, baseY-6*height/10, baseX+angleHand, baseY-4*height/10);
        
        g.drawLine (baseX-angleHand, baseY-4*height/10, baseX-20, baseY-2*height/10);  // arms
        g.drawLine (baseX+angleHand, baseY-4*height/10, baseX+20, baseY-2*height/10);
        
        g.dispose();
      }
      
// mutator method
      public void setFace(int face){this.face = face;}
}
