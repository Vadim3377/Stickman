import java.awt.*;
import javax.swing.*;

public class Stand
{
    // identifying the stickman parameters
    private int angleLeg=15;
    private int angleHandX=15;
    private int angleHandY=40;
    private int baseX;
    private int baseY;
    private int top;
    private Graphics g;
    private Color color;
    private int height;
    private int face;
    
    public Stand(int baseX,int baseY,Graphics g,int top, Color color, int height, int face)
    {
        this.baseX=baseX; 
        this.baseY=baseY;
        this.g=g;
        this.top=top;
        this.color=color;
        this.height = height;
        this.face = face;
    }

    public void stand(Graphics g)
    {
        // initial variables
        angleLeg = 15; 
        angleHandX = 15;
        angleHandY=40;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color); 
        
        int face_pos = 0;
        int neck = 25;
        // painting the head part
        if (height < 100)
        {
            face_pos = (100 - height)*3/10;
            neck = neck - face_pos;
        }
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
        // draw the legs
        g.drawLine (baseX, baseY-height/10, baseX-angleLeg, baseY+2*height/10);  // legs
        g.drawLine (baseX, baseY-height/10, baseX+angleLeg, baseY+2*height/10);
        
        g.drawLine (baseX-angleLeg, baseY+2*height/10, baseX-20, baseY+4*height/10);  // legs
        g.drawLine (baseX+angleLeg, baseY+2*height/10, baseX+20, baseY+4*height/10);
        // draw the arms
        
        g.drawLine (baseX, baseY-6*height/10, baseX-angleHandX, baseY-4*height/10);  // arms
        g.drawLine (baseX, baseY-6*height/10, baseX+angleHandX, baseY-4*height/10);
        
        g.drawLine (baseX-angleHandX, baseY-4*height/10, baseX-20, baseY-2*height/10);  // arms
        g.drawLine (baseX+angleHandX, baseY-4*height/10, baseX+20, baseY-2*height/10);
        
    }
}
