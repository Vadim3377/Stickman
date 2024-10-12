import java.awt.*;
import javax.swing.*;
// Creation date: 30.06.2023
public class Go
{
    // identifying the stickman parameters
    private int height;
    private int angleLeg=15;
    private int angleHandX=15;
    private int angleHandY=40;
    private int baseX;
    private int baseY;
    private int top;
    private Graphics g;

    private int j = 0;
    private int face;
    
    public Go(int baseX,int baseY,Graphics g,int top,int j,int height, int face)
    {
        this.baseX=baseX; 
        this.baseY=baseY;
        this.g=g;
        this.top=top;

        this.j = j;
        this.height = height;
        this.face = face;
    }

   public void go(Graphics g)
   {
    
    Graphics2D g2d = (Graphics2D) g;
    int facePos = 0;
    int neck = 25;
    // Abnormal data validation
    // painting the head part
    if (height < 100)
    {
      facePos = (100 - height)*3/10;
      neck = neck - facePos;
    }
    g2d.drawOval (baseX-12, top-facePos, 25, 25);
    // adding the face
    if(face > 0)
        {
            Image img = new javax.swing.ImageIcon(getClass().getResource("images/face" + face+ ".png")).getImage();
            g2d.drawImage(img, baseX-12, top-facePos, 25, 25, null);
        }
    //neck
    g2d.drawLine (baseX, top+neck, baseX, baseY-6*height/10);
    // draw the body
    g2d.drawLine (baseX, baseY-6*height/10, baseX, baseY-height/10);

    // draw the legs with parameters enterred
    g.drawLine (baseX, baseY-height/10, baseX-angleLeg+j, baseY+2*height/10);  // legs
    g.drawLine (baseX, baseY-height/10, baseX+angleLeg-j, baseY+2*height/10);
        
    g.drawLine (baseX-angleLeg+j, baseY+2*height/10, baseX-20+j, baseY+4*height/10);  // legs
    g.drawLine (baseX+angleLeg-j, baseY+2*height/10, baseX+20-j, baseY+4*height/10);
    // draw the arms
        
    g.drawLine (baseX, baseY-6*height/10, baseX-angleHandX+j, baseY-4*height/10);  // arms
    g.drawLine (baseX, baseY-6*height/10, baseX+angleHandX-j, baseY-4*height/10);

    // drawing arms with parameter entered
    g.drawLine (baseX-angleHandX+j, baseY-4*height/10, baseX-20+j, baseY-2*height/10);  // arms
    g.drawLine (baseX+angleHandX-j, baseY-4*height/10, baseX+20-j, baseY-2*height/10);
   }  
}
