import java.awt.*;
import javax.swing.*;

public class Actor
{
   //Cretion date: 28.06.2023
  // initial character characteristics
  private int baseX;     // center of figure
  private int baseY;     // floor (bottom of feet)
  private Color color;
  private int height;
  private int top;
  private int face;
  private int init;
  private int angleLegY=0;
  // setting the used indexes
  private int i=0;
  private int j=0;
  // setting the command number
  private int command=0;
  // setting the step
  private int step=0;
  // setting other objects used in class
  private String str;
  private Graphics g;
  private Starter start;



  
  public Actor(int center, int bottom, Color shade, int size, Graphics g, Starter start, int face)
  {
   // initialising the instance variables
   baseX = center;
   baseY = bottom;
   init = bottom;
   color = shade;
   height = size;
   
   this.g = g;
   this.start = start;
   this.face = face;
  }

  // drawing the executor
  protected void Create(Graphics g){
    // creating basic characteristics
    top = baseY - height;
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(color); 

    // setting the message
    if (str != null){g.drawString(str,baseX+30, top+5);}
    
    // defining the command execution receieved from Parser Class
    if (command==0)
    {
        // creating the animation class
        Stand st = new Stand(baseX,baseY,g,top,color, height,face);
        st.stand(g);
        // nulling the indexes
        i = 0;
        j = 0;
    }
    
    if (command==1)
    {
        // increasing the index
        i++;
        // transferring index to the animation class
        Dance d = new Dance(baseX,baseY,g,top,height,i,face);
        d.dance(g);
    }
    
    if (command==2)
    {
        // setting the limit for animation
        int limit = 2*height/10;
        // increasing the index until the limit
        if (i<limit){i++;}
        Fight f = new Fight(baseX,baseY,g,top,i,height,face);
        f.fight(g);
    }
    
    if (command==3)
    {
        // increasing the x-axis coordinate
        baseX+=step;
        // increasing the index
        if (j<20){j++;}
        else
        {
            Stand st = new Stand(baseX,baseY,g,top,color, height,face);
            st.stand(g);
            j=0;
        }
        Go go = new Go(baseX,baseY,g,top,j,height,face);
        go.go(g);
    }
    
    if (command==4)
    {
        // calling additional method for animation
        jump(g);
    }
    
    if (command==5)
    {
        // calling the animation method
        Sit sit =new Sit(baseX,baseY,g,top,angleLegY,height,face);
        sit.sit(g);
    }

  }

  // additional method for jump animation
  private void jump(Graphics g)
  {
    // setting the upper limit
    int jumpLimit = height*35/100;
    // setting the lower limit
    int sitLimit = height*15/100;
    // setting the jump height
    int jump = 5*height/100;
    // increasing the index until the parameter
    if(j<jumpLimit){j++;}
    // stating the fixed sit position before the jump
    if(j<sitLimit)
    {
        Sit sit =new Sit(baseX,baseY,g,top,angleLegY,height,face);
        sit.sit(g);
        // increasing the leg angle during the jump
        angleLegY++;
        // decreasing the Y position for landing process
        baseY-=j;
    }
    // during the jump, increasing the y=coordinate
    if((j>=sitLimit)&&(j<jumpLimit))
    {
        Sit sit = new Sit(baseX,baseY,g,top,angleLegY,height,face);
        sit.sit(g);
        baseY+=jump;
    }
    
    if(j==jumpLimit)
    {
        // nulling the indexes
        j=0;
        angleLegY=0;
        // returning to previous position
        baseY=init;
        Stand st = new Stand(baseX,baseY,g,top,color, height,face);
        st.stand(g);
        // stopping the animation
        start.stop();
    }
  }
  
  // mutator methods
  public void setMessage(String str){this.str=str;}
  public void setI(int i){this.i=i;}
  public void setBaseX(int baseX){this.baseX=baseX;}
  public void setJ(int j){this.j=j;}
  public void setStep(int step){this.step=step;}
  public void setCommand(int command){this.command=command;}

  
  
}

