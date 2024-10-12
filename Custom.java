import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class Custom
{
    // Creation date: 08.07.2023

    // initial stickman characteristics
    private int face;
    private int back;
    private int height = 100;
    private boolean flag = false;
    private boolean enemy = false;
    private boolean friend = false;
    private Color color= Color.black;
    // the mode of the program
    private boolean instructions;
    // objects that will be used in code
    private Canvas command;
    private Container container;
    private JMenuBar menuBar = new JMenuBar();
    private JMenuBar button1 = new JMenuBar();
    private JMenuBar button2 = new JMenuBar();
    private JMenuBar button3 = new JMenuBar();
    private JMenuBar button4 = new JMenuBar();
    // the class for additional GUI elements
    private Checkbox checkbox = new Checkbox();
    private JFrame window = new JFrame("Stickman Customisation");
    private Animator stickman = new Animator(100,110,Color.black,100,0);
    
    // constructor
    public Custom(boolean instructions) throws InterruptedException {
        // defining the mode
        this.instructions = instructions;
        // creating the GUI elements
        // creating the box where stickman appears using the additional class
        Rect rectangle1 = new Rect(500,150,300,200);
        rectangle1.setVisible(true);
        // creating the buttons
        button1.add(createButton("Add a friend",1));
        button1.setBounds(800,400,200,50);
        
        button2.add(createButton("Add an enemy",2));
        button2.setBounds(800,450,200,50);
        
        button3.add(createButton("Create stickman",3));
        button3.setBounds(800,500,200,50);
        
        menuBar.add(createButton("Reset",4));
        menuBar.add(createButton("Next",5));
        menuBar.setBounds(500,500,200,50);
        // creating the container
        container = window.getContentPane();
        container.setLayout(null);
        // adding GUI elelments to container to make them visisble
        container.add(rectangle1);
        container.add(menuBar);
        container.add(button1);
        container.add(button2);
        container.add(button3);
        // adding the additional GUI elements to container
        container.add(checkbox.getMenu());

        // initialising the window settings
        window.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-50), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-150));
        window.setLocation(101, 101);
        window.setVisible(true);
        //turning on the instruction mode
        if(instructions)
        {
            // additional button for hints
            button4.add(createButton("Show instructions",6));
            button4.setBounds(800,550,200,50);
            container.add(button4);
            SwingUtilities.updateComponentTreeUI(window);
        }

    }
    // the method to create the uniform button
    private JPanel createButton(String title,int num)
    {
        JButton button = new JButton ();
        JPanel buttonsPanel = new JPanel();
        
        button.setPreferredSize(new Dimension(200, 50));
        button.setText(title);
        button.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    // method to perform the different button operations
                    ChooseOperation(num, button);
                }
            });
            
        buttonsPanel.add(button);
        return buttonsPanel;
    }

    // performing button opertions for number defining purposes
    public void ChooseOperation(int num, JButton button)
    {
        if(num ==1)
        {
            // adding a friend
            button.setBackground(Color.GREEN);
            friend = true;
        }
        
        if (num == 2)
        {
            // adding an enemy
            button.setBackground(Color.RED);
            enemy = true;
        }
        
        if(num == 3)
        {
            // creating stickman in the box
            stickman.setVisible(false);
            color = checkbox.getColor();
            height = checkbox.getHeight();
            if(height<50){height = 50;}
            if(height>150){height = 150;}
            face = checkbox.getFace();
            stickman = new Animator(600,350,color,height,face);
            container.add(stickman);
            SwingUtilities.updateComponentTreeUI(window);
            flag = true;
        }
        
        if(num == 4)
        {
            // clearing the stickman from the box if the user is not satisfied
            stickman.setVisible(false);
            button.setBackground(null);
            friend = false;
            button.setBackground(null);
            enemy = false;
            flag = false;
        }
        
        if(num == 5)
        {
            // go to the next frame if stickman is created
            if (flag)
            { 
                 window.setVisible(false);
                 back = checkbox.getBack();
                 command = new Canvas(color,height,friend,enemy, face,back, instructions);
            }
        }
        if(num == 6)
        {
            // calling the method making the pop-up window to appear
            callHint();
        }

    }
    // method for window
    public void callHint()
    {

        // initializing the class hint fof pop-up window
        Hint h = new Hint(1);

    }

}
