import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


@SuppressWarnings("serial")
public class World extends JComponent implements ActionListener
{
    // Creation date: 16.09.2023

    // identifying the characters characteristics
    private boolean IsFriend;
    private boolean IsEnemy;
    private Timer timer;
    private Color color;
    private int height;
    private int face;
    private int back;

    // identifying objects that will be used in the class
    private Parser engine;
    private Graphics g;
    private String plot;
    private Actor actor;
    private Actor friend;
    private Actor enemy;
    private Starter start;

  
  public World(Color shade,int height,boolean IsFriend,boolean IsEnemy, int face,int back)
  {
      // initialising the initial variables
      color = shade;
      this.height = height;
      this.IsFriend = IsFriend;
      this.IsEnemy = IsEnemy;
      this.face = face;
      this.back = back;
      int delay =1;
      setBackground(Color.white);
      setForeground(color);

      // initialising the thread
      timer = new Timer(delay, this);
      start = new Starter(timer);

      // creating the character that will be used in the animation
      actor = new Actor(740,450, color,height,g, start,face);

      // creating the additioal characters
      // friend
      if(IsFriend)
      {
          friend = new Actor(840,450, Color.green,height,g,start,100);
      }

      // enemy
      if(IsEnemy)
      {
          enemy = new Actor(640,450, Color.red,height,g,start,200);
      }
      
      setPreferredSize(new Dimension(1000, 1000));
      setVisible(true);
  }

  // starting the animation
  public void Lets_Get_It_started()
  {
        engine = new Parser(plot, actor, friend,enemy, start);
  }
  

  // repainting the animation frame by frame
  @Override
  public void actionPerformed(ActionEvent arg0) {   
    repaint();
  }

  // creating the canvas and painting the world created by characteristics chosen
  @Override
  protected void paintComponent(Graphics g)
  {
    // world characteristics
    int width = 1000;
    int height = 500;
    double scale = 1.0;

    // identifying the graphical context
    this.g = g;
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // creating the canvas on which the animation appears
    g2d.setColor(Color.white);
    g.fillRect(250, 0, width, height);
    // identifying the color for the world
    g2d.setColor(Color.black);
    g2d.drawRect(250, 0, width - 1, height - 1);
    // identifying  the color for characters
    g2d.setColor(color);
    g2d.scale(scale, scale);
    // creating the background image
    Image img = null;
    if(back > 0)
    {
     img = new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("images/World" + back + ".jpg"))).getImage();
     g2d.drawImage(img, 250, 0, width - 1, height - 1, null);
    }
    // creating the characters
    actor.Create(g2d);
    
    if(IsFriend)
      {
         friend.Create(g2d); 
      }
    
    if(IsEnemy)
      {
         enemy.Create(g2d); 
      }
    
    g.dispose();
  }
  
    // mutators
  public void setMessage(String plot )
  {
      this.plot=plot;
  }
  public void Stop()
    {
        engine.StopTimer();
    }
}
