import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Intro
{
    // Creation date: 15.01.2024

    private Custom cust;
    private boolean instructions = false;
    private JLabel welcome;
    public JFrame window = new JFrame("Welcome!");
    private JPanel buttonsPanel = new JPanel();
    private JPanel buttonsPanel1 = new JPanel();
    private Container content;

    public Intro()
    {
        CreateWindow();
    }
    
    private void CreateWindow()
    {
        // creating the container
        content = window.getContentPane();
        content.setBackground( Color.white );
        content.setLayout(null);

        // Texts on the frame
        welcome = new JLabel("Welcome to your Stickman studio",SwingConstants.CENTER);
        welcome.setFont(new Font("Serif",Font.PLAIN,40));
        welcome.setBounds(400,-70,700,200);
        //adding to the container
        content.add(welcome);

        welcome = new JLabel("Do you need helping instrucions?",SwingConstants.CENTER);
        welcome.setFont(new Font("Serif",Font.PLAIN,18));
        welcome.setBounds(610,45,700,200);
        content.add(welcome);

        buttonsPanel.add(CreateButton("Start",1));
        buttonsPanel.setBounds(550,560,400,40);

        buttonsPanel1.add(CreateButton("Use instructions",2));
        buttonsPanel1.setBounds(550,600,400,40);
        
        content.add(buttonsPanel);
        content.add(buttonsPanel1);
        content.add(CreateImage());

        // adding pictures on the frame
        JLabel messageCloud  = new JLabel();
        messageCloud.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("images/message.png")).getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH)));
        messageCloud.setBounds(800,20,400,300);
        content.add(messageCloud);

        // initialisng the container on the frame
        window.setContentPane(content);
        // creating the frame based on dimensions
        window.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-50), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-150));
        window.setVisible(true);
    }
    //method to create the uniform button
    private JButton CreateButton(String str, int num)
    {
        JButton start = new JButton (str);
        start.setPreferredSize(new Dimension(400, 40));
        start.setText(str);
        start.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    // if the button was pressed
                    if (arg0.getSource()  == start) 
                    {
                        // the instruction for first button
                        if (num ==1) {
                            try {
                                // go to the next frame
                                cust = new Custom(instructions);
                            } catch (Exception ignore) {
                            }
                            window.setVisible(false);
                        }
                        // the instruction for second button
                        if (num ==2) {
                            // turn on the instruction mode
                            instructions = true;
                            start.setBackground(Color.GREEN);
                        }
                    }
                }
            });
        return start;
    }
    // method to create the image
    private JLabel CreateImage()
    {
        JLabel lbIntro = new JLabel();
        //added background image
        lbIntro.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("images/image.png")).getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)));
        lbIntro.setBounds(500,80,500,500);

        return lbIntro;
    }
}
