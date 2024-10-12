import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
public class Canvas
{
    // Creation date: 14.07.2023

    // mode of the program
    private boolean instructions;
    //charachter charachteristics
    private int face;
    private int back;
      private int height;
    private boolean Ready = true;
    private boolean IsFriend;
    private boolean IsEnemy;
    private Color color;
    // objects that will be used in class
      private World world;

      private JTextArea textBox;
      private JFrame frame = new JFrame("Stickman studio");
      private JPanel wrapper = new JPanel();
      private Recorder recorder;

      
      public Canvas(Color color, int height, boolean Isfriend, boolean Isenemy, int face,int back, boolean instructions)
      {
        // initializing the instance varibles
        this.height = height;
        this.color = color;
        IsFriend = Isfriend;
        IsEnemy = Isenemy;
        this.face = face;
        this.back = back;
        this.instructions = instructions;
        // method calling the pop-up window with tooltips
        if (instructions)
        {
            callHint();
        }
        
        wrapper.setLayout(new BoxLayout(wrapper,BoxLayout.PAGE_AXIS));

        // creating the text box
        JLabel text = new JLabel("Write your plot: ");
        textBox = new JTextArea("SAY: HELLO \nEND", 5, 80);
        
        JPanel panelText = new JPanel();
        JPanel panelTop = new JPanel();

        // initializing the recorder
        recorder = new Recorder();

        panelTop.add(text);
        panelText.add(new JScrollPane(textBox));

        // creating the animation environment
        world = new World(color,height,Isfriend,Isenemy,face,back);

        // combining the animation environment with the canvas created
        wrapper.add(world);
        wrapper.add(panelTop);
        wrapper.add(panelText);
        wrapper.add(CreateButton("Start",world,1));
        wrapper.add(CreateButton("Save and exit",world,2));
    
        frame.getContentPane().add(wrapper);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-50), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-150));
        frame.setVisible(true);
         
      }

      // method to create the button
      private JPanel CreateButton(String title,World world, int num)
      {
        JButton button = new JButton (title);
        JPanel buttonsPanel = new JPanel();
        
        button.setPreferredSize(new Dimension(200, 40));
        button.setText(title);
        button.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    if(num==1)
                    {
                        // method that starts animation and recording
                        StartMovie();
                    }
                    if(num ==2)
                    {
                        // method that stops animation and recording
                        recorder.stopScreenRecording();
                        world.Stop();
                        frame.setVisible(false);
                        Final finall = new Final();

                    }
                }
            });
        buttonsPanel.add(button);
        return buttonsPanel;
        
      }
    // method that starts the animation and recording
    public void StartMovie()
    {
        // obtaining the plot wriiten
        String str = textBox.getText();
        // transferring the plot to the world enevironment
        world.setMessage(str);
        // starting the animation
        world.Lets_Get_It_started();
        // error prevention
        if(Ready)
        {
            // starting the recording
            recorder.StartRecoding();
            Ready = false;

        }
    }
    // creating the pop-up window with a hint on the canvas
    public void callHint()
          {
              Hint h = new Hint(2);
          }

        

      


}
 