import java.awt.*;
import javax.swing.*;

public class Dance
{
    //Creation date: 21.07.2023

    // identifying the stickman parameters
    private int angleLeg=15;
    private int angleHandX=15;
    private int angleHandY=40;
    private int baseX;
    private int baseY;
    private int top;
    private int height;
    private Graphics g;
    private int i = 0;
    private int face;
    public Dance(int baseX,int baseY,Graphics g,int top,int height,int i,int face)
    {
        this.baseX=baseX; 
        this.baseY=baseY;
        this.g=g;
        this.top=top;
        this.height = height;
        this.i = i;
        this.face = face;
    }

    public void dance(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        //draw the head
        int face_pos = 0;
        int neck = 25;
        if (height < 100)
        {
          face_pos = (100 - height)*3/10;
          neck = neck - face_pos;
        }
        // painting the head part
        g2d.drawOval (baseX-12, top-face_pos, 25, 25);
        // adding the face
        if(face > 0)
        {
            Image img = new javax.swing.ImageIcon(getClass().getResource("images/face" + face+ ".png")).getImage();
            g2d.drawImage(img, baseX-12, top-face_pos, 25, 25, null);
        }
        //neck
        g2d.drawLine (baseX, top+neck, baseX, baseY-6*height/10);
        // draw the body
        g2d.drawLine (baseX, baseY-6*height/10, baseX, baseY-height/10); //60
        // identifier method
        definer(i);
        // creating the body parts with parameter identifiers
        g.drawLine (baseX, baseY-height/10, baseX-angleLeg, baseY+2*height/10);  // legs
        g.drawLine (baseX, baseY-height/10, baseX+angleLeg, baseY+2*height/10);
        g.drawLine (baseX-angleLeg, baseY+2*height/10, baseX-angleLeg-5, baseY+4*height/10);  // legs
        g.drawLine (baseX+angleLeg, baseY+2*height/10, baseX+angleLeg+5, baseY+4*height/10);
        // draw the arms
          
        g.drawLine (baseX, baseY-6*height/10, baseX-angleHandX, baseY-angleHandY);  // arms
        g.drawLine (baseX, baseY-6*height/10, baseX+angleHandX, baseY-angleHandY);
        g.drawLine (baseX-angleHandX, baseY-angleHandY, baseX-angleHandX-5, baseY-angleHandY-2*height/10);  // arms
        g.drawLine (baseX+angleHandX, baseY-angleHandY, baseX+angleHandX+5, baseY-angleHandY-2*height/10);
    }   

    // based on entered parameter hands go up or down every 10 iterations
    private void definer(int i)
    {
     if ((i/10)%2==0){angleLeg = 15; angleHandX = 15;angleHandY=4*height/10;}
     else{ angleLeg = 5;angleHandX = 20;angleHandY=5*height/10;}
    }
}
