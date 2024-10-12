import java.awt.*;
import javax.swing.*;

//Creation date: 21.07.2023
public class Sit
{
    // identifying the stickman parameters
    private int angleLeg=15;
    private int angleHandX=15;
    private int angleHandY=40;
    private int angleLegY;
    private int baseX;
    private int baseY;
    private int top;
    private int height;
    private int face;
    private Graphics g;
    
    public Sit(int baseX,int baseY,Graphics g,int top, int angleLegY, int height,int face)
    {
        this.baseX=baseX; 
        this.baseY=baseY;
        this.g=g;
        this.top=top;
        this.angleLegY=angleLegY;
        this.height = height;
        this.face = face;
    }

    public void sit(Graphics g)
    {
        // sit limit
        int downDist=2*height/10;
        // leg angle limit
        angleHandY=4*height/10;        
        Graphics2D g2d = (Graphics2D) g;
        // initial variables
        int face_pos = 0;
        int neck = 25;
        // painting the head component
        if (height < 100)
        {
          face_pos = (100 - height)*3/10;
          neck = neck - face_pos;
        }
        g2d.drawOval (baseX-12, top-face_pos+downDist, 25, 25);
        // adding the face picture
        if(face > 0)
        {
            Image img = new javax.swing.ImageIcon(getClass().getResource("images/face" + face+ ".png")).getImage();
            g2d.drawImage(img, baseX-12, top-face_pos+downDist, 25, 25, null);
        }
        //neck
        g2d.drawLine (baseX, top+neck+downDist, baseX, baseY-6*height/10+downDist);
        // draw the body
        g2d.drawLine (baseX, baseY-6*height/10+downDist, baseX, baseY-height/10+downDist);

        // drawing the legs with down parameter
        g.drawLine (baseX, baseY-height/10+downDist, baseX-angleLeg, baseY+downDist+angleLegY);  // legs
        g.drawLine (baseX-angleLeg, baseY+downDist+angleLegY, baseX-20, baseY+2*height/10+downDist);
        
        g.drawLine (baseX, baseY-height/10+downDist, baseX+angleLeg, baseY+downDist+angleLegY);// legs
        g.drawLine (baseX+angleLeg, baseY+downDist+angleLegY, baseX+20, baseY+2*height/10+downDist);
        // draw the arms
        
        g.drawLine (baseX, baseY-6*height/10+downDist, baseX-angleHandX, baseY-4*height/10+downDist);  // arms
        g.drawLine (baseX, baseY-6*height/10+downDist, baseX+angleHandX, baseY-4*height/10+downDist);

        g.drawLine (baseX-angleHandX, baseY-angleHandY+downDist, baseX-20, baseY-2*height/10+downDist);  // arms
        g.drawLine (baseX+angleHandX, baseY-angleHandY+downDist, baseX+20, baseY-2*height/10+downDist);    
    }
}
