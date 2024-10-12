import java.awt.*;
import javax.swing.*;

public class Fight
{
    //Creation date: 21.07.2023

    // identifying the stickman parameters
    private int baseX;
    private int baseY;
    private int top;
    private int j;
    private int height;
    private Graphics g;
    private int face;
    
    public Fight(int baseX,int baseY,Graphics g,int top,int j, int height, int face)
    {
        this.baseX=baseX; 
        this.baseY=baseY;
        this.g=g;
        this.top=top;
        this.j=j;
        this.height=height;
        this.face = face;
    }

    public void fight(Graphics g)
    {
    // Abnormal data validation
    // painting the head part
     Graphics2D g2d = (Graphics2D) g;
     // adding the face
     int facePos = 0;
     int neck = 25;
     if (height < 100)
     {
            facePos = (100 - height)*3/10;
            neck = neck - facePos;
     }
     g2d.drawOval (baseX-12, top-facePos, 25, 25);
     
     if(face > 0)
        {
            Image img = new javax.swing.ImageIcon(getClass().getResource("images/face" + face+ ".png")).getImage();
            g2d.drawImage(img, baseX-12, top-facePos, 25, 25, null);
        }
        //neck
     g2d.drawLine (baseX, top+neck, baseX, baseY-6*height/10);
        // draw the body
     g2d.drawLine (baseX, baseY-6*height/10, baseX, baseY-height/10); //60
             
     
    //initial fixed position of fighting pose
     int angleLegXL=10;
     int angleLegYL= height/10;
      
     int angleLegXR=10;
     int angleLegYR= height/10;
      
     int angleHandX=15;
     int angleHandY=4*height/10;

     // drawing the body parts with parameters entered from another class
     g.drawLine (baseX, baseY-angleLegYR, baseX-angleLegXL, baseY+angleLegYL);  // left leg is not changing during execution
     g.drawLine (baseX-angleLegXL, baseY+angleLegYL, baseX-angleLegXL-5, baseY+3*angleLegYR); 
          
     g.drawLine (baseX, baseY-angleLegYR, baseX+angleLegXR+j, baseY+angleLegYR-j); // right leg is going up
     g.drawLine (baseX+angleLegXR+j, baseY+angleLegYR-j, baseX+angleLegXL+5+j, baseY+angleLegYR+2*angleLegYR-j);
         // draw the arms
         
     g.drawLine (baseX, baseY-6*angleLegYR, baseX-angleHandX-j, baseY-angleHandY-j);  // left arm goes up
     g.drawLine (baseX-angleHandX-j, baseY-angleHandY-j, baseX-20-j, baseY-2*angleLegYR-3*j);
        
     g.drawLine (baseX, baseY-6*angleLegYR, baseX+angleHandX+j, baseY-angleHandY-j); // right arm becomes straight
     g.drawLine (baseX+angleHandX+j, baseY-angleHandY-j, baseX+20+2*j, baseY-2*angleLegYR-2*j);
    
    }
}
